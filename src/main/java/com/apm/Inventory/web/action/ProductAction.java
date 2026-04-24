package com.apm.Inventory.web.action;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.PoPaymenytDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryCatalogueDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCPoPaymengtDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Inventory.web.form.ProductForm;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.bi.ShelfMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCShelfMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.ShelfMaster;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class ProductAction extends BaseAction implements ModelDriven<ProductForm>, Preparable {

	ProductForm productForm = new ProductForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	Pagination pagination = new Pagination(25, 1);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public ProductForm getModel() {
		// TODO Auto-generated method stub
		return productForm;
	}
	
	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(connection);

			String categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {
				categoryid = "2";
			}
			productForm.setCategory_id(categoryid);

			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);

			productForm.setCategoryList(categoryList);

			boolean manual_entry = productForm.isManual_entry();
			productForm.setManual_entry(manual_entry);

			ArrayList<Product> productList = inventoryProductDAO.getAllProductList(categoryid);
			productForm.setProductList(productList);

			ArrayList<Priscription> genericnameList = prescriptionMasterDAO.getAllMedicineGenericList();
			productForm.setGenericnameList(genericnameList);

			/*
			 * String category_id=productForm.getCategory_id();
			 * if(category_id!=null){ ArrayList<Product>
			 * subcategoryList=inventoryProductDAO.getSubCategoryList(
			 * category_id); productForm.setSubcategoryList(subcategoryList);
			 * productForm.setCategory_id(category_id); } else {
			 * 
			 * String subcategory_id=productForm.getSubcategory_id();
			 * 
			 * ArrayList<Product>
			 * subcategoryList=inventoryProductDAO.getAllSubCategoryList(null);
			 * productForm.setSubcategoryList(subcategoryList);
			 * 
			 * if(subcategory_id!=null){
			 * productForm.setSubcategory_id(subcategory_id); }
			 * 
			 * }
			 * 
			 * ArrayList<Vendor>
			 * vendorList=inventoryVendorDAO.getAllVendorList();
			 * productForm.setVendorList(vendorList);
			 * 
			 * String vendor_id=productForm.getVendor_id(); if(vendor_id!=null){
			 * 
			 * ArrayList<Product>
			 * brandnameList=inventoryVendorDAO.getBrandList(vendor_id);
			 * productForm.setBrandnameList(brandnameList);
			 * productForm.setVendor_id(vendor_id); } else { ArrayList<Product>
			 * brandnameList=inventoryProductDAO.getAllBrandList(null);
			 * productForm.setBrandnameList(brandnameList); }
			 */

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return super.execute();
	}

	public String addnewrow() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		int index = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(connection);
			String rowCount = request.getParameter("rowcount");
			if (rowCount != null) {

				index = Integer.parseInt(rowCount);
			}
			index--;
			String categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {
				categoryid = "1";
			}

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			ArrayList<Product> brandnameList = inventoryProductDAO.getAllProductList(categoryid);

			buffer.append("<td>" + (index) + "</td>");

			buffer.append("<td><select name='products[" + index
					+ "].brand' class='form-control' onchange='getvendor(this.value," + index + ")' id='brand" + index
					+ "' style='width:100%'>");
			buffer.append("<option value='0'>Select Product</option>");
			for (Product product : brandnameList) {
				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td id='listvendor" + index + "'><select name='products[" + index + "].vendor' id='vendor"
					+ index + "' class='form-control' style='width:100%'>");
			buffer.append("<option value='0'>Select Vendor</option>");
			for (Vendor vendor : vendorList) {

				buffer.append("<option value='" + vendor.getId() + "'>" + vendor.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");

			if (categoryid.equals("1")) {
				buffer.append("<td><input type='text' name='products[" + index + "].genericname' id='genericname"
						+ index + "' style='width:100%' class='form-control' placeholder='Enter Generic Name'> </td>");
			} else {
				ArrayList<Priscription> genericnameList = prescriptionMasterDAO.getAllMedicineGenericList();
				buffer.append("<td>");
				buffer.append("<select name='products[" + index + "].genericname' class='form-control' id='genericname"
						+ index + "' style='width:100%' >");
				buffer.append("<option value='0'>Select Generic Name</option>");
				for (Priscription priscription : genericnameList) {
					buffer.append("<option value='" + priscription.getId() + "'>" + priscription.getGenericname()
							+ "</option>");
				}
				buffer.append("</td>");
			}

			buffer.append("<td><input type='text' name='products[" + index + "].mrp' id='mrp" + index
					+ "' style='width:100%' class='form-control' placeholder='Enter MRP'> </td>");

			buffer.append(
					"<td><select name='products[" + index + "].vat'  class='form-control' id='vat" + index + "'>");
			buffer.append("<option id='0'>0</option>");
			buffer.append("<option id='6'>6</option>");
			buffer.append("<option id='15.5'>13.5</option>");
			buffer.append("</select></td>");

			buffer.append("<td><input type='text' name='products[" + index + "].purchase_price' id='purchase_price"
					+ index + "' class='form-control' placeholder='Purchase Price'>");
			buffer.append(
					"<td><input type='text' name='products[" + index + "].sale_price' style='width:100%' id='sale_price"
							+ index + "' class='form-control' placeholder='Sale Price'></td>");
			buffer.append("<td><a href='#' onclick=deleteRow('table13') >Remove</a></td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String getsubcategories() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String subid = request.getParameter("id");
			StringBuffer buffer = new StringBuffer();
			ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);
			buffer.append("<label>Select Sub Category</label>");
			buffer.append(
					"<select name='subcategory_id' onchange='getsubproduct(this.value)' class='form-control chosen' id='subcategory_id' >");
			buffer.append("<option value='0'>Select Sub Category</option>");
			for (Product product : subcategoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;
	}

	public String getbrands() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryProductDAO = new JDBCInventoryVendorDAO(connection);

			String venid = request.getParameter("id");
			String index = request.getParameter("index");
			ArrayList<Product> brandnameList = inventoryProductDAO.getBrandList(venid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<select name='products[" + index + "].brand_id' class='form-control' id='brand_id" + index
					+ "' >");
			buffer.append("<option value='0'>Select Product Name</option>");
			for (Product product : brandnameList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getBrand() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("<input class='txtPCode mar5 form-control' type='text' id='sale_discount" + index
					+ "' name='products[" + index + "].sale_discount' placeholder='Sale Discount'>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;
	}

	public String productdettails() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");

			int count = inventoryProductDAO.getCountProduct(id);
			pagination.setPreperties(count);
			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(id);
			pagination.setPage_records(productList.size());
			productForm.setProductList(productList);
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "liststock";

	}

	public String list() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String location = "0";
			/*
			 * String categoryid=(String)session.getAttribute("category");
			 * if(categoryid==null){ categoryid=productForm.getCategory_id(); }
			 */
			
			String isfromcathlab = request.getParameter("isfromcathlab");

			if (isfromcathlab != null) {
				if (isfromcathlab.equals("")) {
					isfromcathlab = "0";
				}
			} else {
				isfromcathlab = productForm.getIsfromcathlab();
				if (isfromcathlab != null) {
					if (isfromcathlab.equals("")) {
						isfromcathlab = "0";
					}
				} else {
					isfromcathlab = "0";
				}
			}

			// Single Page application code
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (isfromcathlab.equals("0") || isfromcathlab.equals("2")) {
					if (breadcrumbs.getName().equals("Inventory Dashboard")) {
						isavilablemodule = true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					} else {
						indentflowlist.add(breadcrumbs);
					}
				} else if (isfromcathlab.equals("1")) {
					if (breadcrumbs.getName().equals("Cathlab Stock Dashboard")) {
						isavilablemodule = true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					} else {
						indentflowlist.add(breadcrumbs);
					}
				}
			}
			if (!isavilablemodule) {
				if (isfromcathlab.equals("0") || isfromcathlab.equals("2")) {
					Breadcrumbs breadcrumbs = new Breadcrumbs();
					breadcrumbs.setName("Inventory Dashboard");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("listProduct?isfromcathlab=0");
					breadcrumbs.setIscurrent(true);
					breadcrumbs.setShowingname("Inventory Dashboard");
					indentflowlist.add(breadcrumbs);
				} else if (isfromcathlab.equals("1")) {
					Breadcrumbs breadcrumbs = new Breadcrumbs();
					breadcrumbs.setName("Cathlab Stock Dashboard");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("listProduct?isfromcathlab=1");
					breadcrumbs.setIscurrent(true);
					breadcrumbs.setShowingname("Cathlab Dashboard");
					indentflowlist.add(breadcrumbs);
				}
			}
			session.setAttribute("indentflowlist", indentflowlist);

			String categoryid = (String) session.getAttribute("stockstatus_category_id");
			if (categoryid == null) {
				categoryid = productForm.getCategory_id();
			} else {
				String[] str = categoryid.split(",");
				for (String string : str) {
					categoryid = string;
					break;
				}
				session.removeAttribute("stockstatus_category_id");
			}

			if (categoryid == null) {
				categoryid = (String) session.getAttribute("category");
			}

			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);

			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = (String) session.getAttribute("location");

			} else {
				boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
				if (ispharmacist) {
					location = priscription.getLocation();
				} else {
					location = inventoryProductDAO.getHISUserLocation(userid);
				}
			}

			if (location == null) {
				location = "0";
			}
			session.setAttribute("location", location);

			/*
			 * if(loginInfo.getUserType()==2 ||
			 * loginInfo.getPharmacyUserType()==2) {
			 * 
			 * String loc= (String) session.getAttribute("location");
			 * if(loc!=null){
			 * 
			 * if(loc.equals("All")){ location="0"; loc="0"; } else { location=
			 * loc; } } else { location= "0"; } session.setAttribute("location",
			 * loc); } else { location= priscription.getLocation();
			 * session.setAttribute("location", location);
			 * 
			 * }
			 */

			productForm.setLocation(location);

			if (categoryid == null) {
				/* categoryid="2"; */
				categoryid = "0";
				session.setAttribute("category", categoryid);
			} else {
				session.setAttribute("category", categoryid);
			}

			String fromdate = productForm.getFromdate();
			String issuedate = productForm.getIssue_date();

			String medicine_shedule = productForm.getMedicine_shedule();

			String searchtext = (String) session.getAttribute("stockstatus_searchtext");
			if (searchtext == null) {
				searchtext = productForm.getSearchtext();
			} else {
				session.removeAttribute("stockstatus_searchtext");
			}
			if (searchtext != null) {
				if (searchtext.equals("")) {
					searchtext = null;
				}
			}
			String product_type = (String) session.getAttribute("stockstatus_product_type");
			if (product_type == null) {
				product_type = productForm.getProduct_type();
			} else {
				session.removeAttribute("stockstatus_product_type");
			}

			if (product_type != null) {
				if (product_type.equals("")) {
					product_type = "0";
				}
			} else {
				product_type = "0";
			}

			if (medicine_shedule != null) {
				if (medicine_shedule.equals("") || medicine_shedule.equals("0")) {
					medicine_shedule = null;
				} else if (medicine_shedule.equals("4")) {
					medicine_shedule = "0";
				}
			}

			if (fromdate != null) {
				if (fromdate.equals("")) {
					fromdate = null;
				}
			}

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}
			
			if (issuedate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				issuedate = dateFormat.format(cal.getTime());
				//issuedate = DateTimeUtils.getCommencingDate(issuedate);
			} else {

				if (issuedate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					issuedate = dateFormat.format(cal.getTime());
					//issuedate = DateTimeUtils.getCommencingDate1(issuedate);
				} else {
					issuedate = DateTimeUtils.getCommencingDate1(issuedate);
				}
			}
            productForm.setIssue_date(issuedate);			
            String report_filter = productForm.getReport_filter();

			String withstock_filter = (String) session.getAttribute("stockstatus_withstock_filter");
			if (withstock_filter == null) {
				withstock_filter = productForm.getWithstock_filter();
			} else {
				session.removeAttribute("stockstatus_withstock_filter");
			}

			String shelf_filter = (String) session.getAttribute("stockstatus_shelf_filter");
			if (shelf_filter == null) {
				shelf_filter = productForm.getShelf_filter();
			} else {
				session.removeAttribute("stockstatus_shelf_filter");
			}
			shelf_filter = DateTimeUtils.isNull(shelf_filter);
			productForm.setShelf_filter(shelf_filter);
			String orderby = productForm.getOrderby();

			if (isfromcathlab.equals("1")) {
				location = "105";
				productForm.setLocation(location);
				session.setAttribute("location", location);
			} else if (isfromcathlab.equals("2")) {
				location = "85";
				productForm.setLocation(location);
				session.setAttribute("location", location);
			}

			if (withstock_filter != null) {
				if (withstock_filter.equals("")) {
					withstock_filter = "2";
				}
			} else {
				withstock_filter = "2";
			}

			productForm.setWithstock_filter(withstock_filter);

			/*
			 * InventoryProductDAO inventoryProductDAO=new
			 * JDBCInventoryProductDAO(connection);
			 */
			
			String subcategoryid = productForm.getSubcategoryid();
			subcategoryid = DateTimeUtils.numberCheck(subcategoryid);
			
			int count = inventoryProductDAO.geTotalProductCount(fromdate, categoryid, searchtext, medicine_shedule,
					location, withstock_filter, product_type, shelf_filter,subcategoryid);
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(pagination, 0, fromdate, categoryid,
					searchtext, medicine_shedule, location, loginInfo, report_filter, withstock_filter, orderby,
					product_type, shelf_filter,subcategoryid);

			pagination.setPage_records(productList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setProductList(productList);
			productForm.setCategory_id(categoryid);
			productForm.setEdit_catalogue(priscription.getEdit_catalogue());
			productForm.setDelete_catalogue(priscription.getDelete_catalogue());

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				productForm.setFromdate(fromdate);
			}

			// ArrayList<Product>
			// allWillExpireProduct=inventoryProductDAO.getAllWillExpireList();
			ArrayList<Product> allWillExpireProduct = new ArrayList<Product>();
			productForm.setAllWillExpireProduct(allWillExpireProduct);
			productForm.setCount(allWillExpireProduct.size());
			ArrayList<Master> cellList = new ArrayList<Master>();
			if (!DateTimeUtils.numberCheck(location).equals("0")) {
				cellList = inventoryProductDAO.getcellList(location);
			} else {
				cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105,85");
			}

			productForm.setCellList(cellList);

			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandListByCategory(categoryid);
			productForm.setBrandnameList(brandnameList);
			productForm.setDirect_transfer(priscription.getDirect_transfer());
			productForm.setReturn_stock(priscription.getReturn_stock());
			productForm.setInventory_transfer(priscription.getInventory_transfer());
			productForm.setSearchtext(searchtext);
			/*
			 * ArrayList<ShelfMaster> shelflist = shelfMasterDAO.getallShelf();
			 * productForm.setShelflist(shelflist);
			 */

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> issuedeptlist = masterDAO.getAllLocation(null);
			productForm.setIssuedeptlist(issuedeptlist);

			DischargeOutcomeDAO dischargeOutcomeDAO = new JDBCDischargeOutcomeDAO(connection);
			ArrayList<Master> procedurelist = dischargeOutcomeDAO.getNewChargeTypeListProc();
			productForm.setProcedurelist(procedurelist);

			productForm.setProduct_type(product_type);

			productForm.setIsfromcathlab(isfromcathlab);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			ArrayList<Product> cathtemplist = indentDAO.getCathlabTemplateList("0");
			productForm.setCathtemplist(cathtemplist);
			session.setAttribute("isfromcathlab", isfromcathlab);
			
			ArrayList<Product> subcategoryList = new ArrayList<Product>();
			if(!categoryid.equals("0")){
				subcategoryList = inventoryProductDAO.getSubCategoryList(categoryid);
			}
			
			productForm.setSubcategoryid(subcategoryid);
			productForm.setSubcategoryList(subcategoryList);
			
			int isStoreLocation = inventoryProductDAO.checkLocationIsStore(location);
			productForm.setIsStoreLocation(isStoreLocation);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "listcatalogue";
	}

	public String listsale() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			String categoryid = (String) session.getAttribute("categoryid");
			if (categoryid == null) {

				categoryid = "2";
			}

			String location = (String) session.getAttribute("location");
			if (location == null) {

				location = "0";
			}

			connection = Connection_provider.getconnection();
			pagination = new Pagination(25, 1);

			String gowdown = productForm.getGodown();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();

			if (gowdown != null) {

				if (gowdown.equals("0")) {
					gowdown = null;
				}
			}

			if (fromdate != null) {

				if (fromdate.equals("")) {
					fromdate = null;
				}
			}
			if (todate != null) {

				if (todate.equals("")) {
					todate = null;
				}
			}
			if (fromdate != null) {

				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {

				todate = DateTimeUtils.getCommencingDate1(todate);
			}

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			int count = inventoryProductDAO.getSaleProductCount(gowdown, fromdate, todate);
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			pagination.setPage_records(Integer.parseInt(productForm.getPagerecords()));
			ArrayList<Product> soldproductList = inventoryProductDAO.getSoldProduct(pagination, gowdown, fromdate,
					todate);
			productForm.setSoldproductList(soldproductList);
			pagination.setPage_records(soldproductList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			ArrayList<Product> godownlist = inventoryProductDAO.getGodownList();
			productForm.setGodownlist(godownlist);

			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(null, 0, null, categoryid, null, null,
					location, loginInfo, "", "", "", "0", "","0");
			productForm.setProductList(productList);

			if (fromdate != null) {

				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				productForm.setFromdate(fromdate);
			}

			if (todate != null) {

				todate = DateTimeUtils.getCommencingDate1(todate);
				productForm.setTodate(todate);
			}

			productForm.setGodown(gowdown);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "listsale";

	}

	public String sale() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String categoryid = (String) session.getAttribute("categoryid");
			if (categoryid == null) {

				categoryid = "2";
			}

			String location = (String) session.getAttribute("location");
			if (location == null) {

				location = "0";
			}

			boolean manual_entry = productForm.isManual_entry();
			productForm.setManual_entry(manual_entry);

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			ArrayList<Product> godownlist = inventoryProductDAO.getGodownList();
			productForm.setGodownlist(godownlist);

			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			ArrayList<Product> subcategoryList = inventoryProductDAO.getAllSubCategoryList(null);
			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandList(null);
			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(null, 0, null, categoryid, null, null,
					location, loginInfo, "", "", "", "0", "","0");
			productForm.setProductList(productList);

			productForm.setCategoryList(categoryList);
			productForm.setSubcategoryList(subcategoryList);
			productForm.setVendorList(vendorList);
			productForm.setBrandnameList(brandnameList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "sale";
	}

	public String saveall() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String categoryid = (String) session.getAttribute("categoryid");
			if (categoryid == null) {

				categoryid = "1";
			}

			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);

			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());

			if (priscription.getSale_bill() != null)
				if (priscription.getSale_bill().equals("0"))
					loginInfo.setSale_bill(false);
				else
					loginInfo.setSale_bill(true);

			if (priscription.getDisscount() != null)
				if (priscription.getDisscount().equals("0"))
					loginInfo.setDisscount(false);
				else
					loginInfo.setDisscount(true);

			if (priscription.getAccount() != null)
				if (priscription.getAccount().equals("0"))
					loginInfo.setAccount(false);
				else
					loginInfo.setAccount(true);

			if (priscription.getLedger() != null)
				if (priscription.getLedger().equals("0"))
					loginInfo.setLedger(false);
				else
					loginInfo.setLedger(true);

			if (priscription.getIslogin() != null)
				if (priscription.getIslogin().equals("0"))
					loginInfo.setIslogin(false);
				else
					loginInfo.setIslogin(true);

			if (priscription.getPurchase_order() != null)
				if (priscription.getPurchase_order().equals("0"))
					loginInfo.setPurchase_order(false);
				else
					loginInfo.setPurchase_order(true);

			if (!loginInfo.isIslogin()) {
				if (loginInfo.getUserType() == 2) {

				} else {
					addActionError("Inactive pharmacy user");
					return "loginerror";
				}
			}
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String datetime[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");

			for (Product product : productForm.getProducts()) {

				product.setUserid(loginInfo.getUserId());
				product.setLastmodified(datetime[0]);
				int res = inventoryProductDAO.addProduct(product);

			}

			ArrayList<Product> productList = inventoryProductDAO.getAllProducts("2");
			productForm.setProductList(productList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "showstock";
	}

	public String deletestock() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			String categoryid = (String) session.getAttribute("categoryid");
			if (categoryid == null) {

				categoryid = "2";
			}
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String id = request.getParameter("selectedid");
			int res = inventoryProductDAO.deleteProduct(id);
			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(categoryid);
			productForm.setProductList(productList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "liststock";
	}

	public String quicksearch() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			pagination = new Pagination(25, 1);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String brand = productForm.getBrand();
			String vendor = productForm.getVendor();

			if (fromdate != null) {
				if (fromdate.equals("")) {
					fromdate = null;
				}
			}

			if (todate != null) {
				if (todate.equals("")) {
					todate = null;
				}
			}

			if (brand != null) {

				if (brand.equals("0")) {
					brand = null;
				}
			}

			if (vendor != null) {
				if (vendor.equals("0")) {
					vendor = null;
				}

			}

			if (fromdate != null) {

				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			int count = inventoryProductDAO.geTotalProductCountByPurchased(fromdate, todate, brand, vendor);
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> productList = inventoryProductDAO.getListandHistoryProduct(pagination, fromdate, todate,
					brand, vendor);
			productForm.setProductList(productList);
			pagination.setPage_records(productList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandList(null);
			productForm.setVendorList(vendorList);
			productForm.setBrandnameList(brandnameList);

			if (fromdate != null) {

				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				productForm.setFromdate(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
				productForm.setTodate(todate);
			}

			productForm.setBrand(brand);
			productForm.setVendor(vendor);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "quicksearch";
	}

	public String updatestock() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			Product product = new Product();

			product.setCategory(productForm.getCategory());
			product.setSubcategory(productForm.getSubcategory());
			product.setVendor(productForm.getVendor());
			product.setBrand(productForm.getBrand_id());
			product.setId(productForm.getId());
			product.setProduct_code(productForm.getProduct_code());
			product.setProduct_name(productForm.getProduct_name());
			product.setMrp(productForm.getMrp());
			product.setPurchase_price(productForm.getPurchase_price());
			product.setSale_price(productForm.getSale_price());
			product.setPurchase_discount(productForm.getPurchase_discount());
			product.setSale_discount(productForm.getSale_discount());
			product.setWeight(productForm.getWeight());
			product.setUnit(productForm.getUnit());
			product.setDescription(productForm.getDescription());

			int result = inventoryProductDAO.updateProduct(product);
			String categoryid = (String) session.getAttribute("categoryid");
			if (categoryid == null) {

				categoryid = "1";
			}

			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(categoryid);
			productForm.setProductList(productList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "liststock";
	}

	public String order() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		return "ordertrack";
	}

	public String editstock() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String prodcode = "";
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			Product product = inventoryProductDAO.getProductCatalogueDetails(id);
			if (product.getPro_code() == null) {
				prodcode = "";
			} else {
				prodcode = product.getPro_code();
			}
			String str = product.getId() + "~" + product.getGenericname() + "~" + product.getProduct_name() + "~"
					+ product.getMrp() + "~" + product.getPurchase_price() + "~" + product.getSale_price() + "~"
					+ product.getVat() + "~" + product.getMfg() + "~" + product.getBatch_no() + "~" + product.getMfg()
					+ "~" + product.getShelf() + "~" + product.getMedicine_shedule() + "~" + product.getMedicine_type()
					+ "~" + product.getLocation() + "~" + product.getHsnno() + "~" + product.getPack() + "~"
					+ product.getCategory_id() + "~" + product.getSubcategory_id() + "~" + product.getMedicine_shedule()
					+ "~" + product.getDescription() + "~" + product.getMinorder() + "~" + product.getMaxorder() + "~"
					+ prodcode+"~"+product.getUom();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}
		return null;
	}

	public String editproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			Product product = inventoryProductDAO.getProduct(id);
			String expiryDate = DateTimeUtils.getCommencingDate1(product.getExpiry_date());

			try {
				String newexpirydate ="";
				if(!DateTimeUtils.isNull(product.getExpiry_date()).equals("")){
					String[] expirydate = product.getExpiry_date().split("-");
					newexpirydate = expirydate[1] + "/" + expirydate[0];
				}
				// String newexpirydate =
				// expirydate[2]+"-"+expirydate[1]+"-"+expirydate[0];
				product.setNewexpirydate(newexpirydate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int isinadjustment = inventoryProductDAO.checkProductInEdit(id);
			String str = product.getId() + "~~" + product.getGenericname() + "~~" + product.getProduct_name() + "~~"
					+ product.getMrp() + "~~" + product.getPurchase_price() + "~~" + product.getSale_price() + "~~"
					+ product.getVat() + "~~" + product.getMfg() + "~~" + product.getBatch_no() + "~~"
					+ product.getStock() + "~~" + product.getShelf() + "~~" + product.getMedicine_shedule() + "~~"
					+ product.getMedicine_type() + "~~" + product.getLocation() + "~~" + product.getHsnno() + "~~"
					+ product.getPack() + "~~" + expiryDate + "~~" + product.getNewexpirydate() + "~~"
					+ DateTimeUtils.isNull(product.getBarcode()) + "~~" + product.getSecondary_shelf() + "~~"
					+ isinadjustment;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}
		return null;
	}

	public String setproductinfo() throws Exception {

		Connection connection = null;

		if (!verifyLogin(request)) {

			return "login";
		}

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("selectedid");

			Product product = inventoryProductDAO.getProduct(id);
			String data = product.getProduct_code() + "~" + product.getProduct_name() + "~" + product.getMrp() + "~"
					+ product.getPurchase_price() + "~" + product.getSale_price() + "~" + product.getPurchase_discount()
					+ "~" + product.getSale_discount() + "~" + product.getWeight() + "~" + product.getDescription();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String getsubproducts() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String subid = request.getParameter("id");
			ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<select name='subcategory_id' style='width:100%' class='form-control'>");
			buffer.append("<option value='0'>Select Product Type</option>");
			for (Product product : subcategoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;

	}

	public String getproducts() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryProductDAO = new JDBCInventoryVendorDAO(connection);

			String venid = request.getParameter("id");
			ArrayList<Product> brandnameList = inventoryProductDAO.getBrandList(venid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<select name='brand_id' id='brand_id' style='width:100%' class='form-control'>");
			buffer.append("<option value='0'>Select Product Name</option>");
			for (Product product : brandnameList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getBrand() + "</option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;

	}

	public String getbarcodeproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String pid = request.getParameter("productid");
			Product product = inventoryProductDAO.getProduct(pid);
			int index = Integer.parseInt(request.getParameter("rowcount"));
			StringBuffer buffer = new StringBuffer();
			buffer.append("<td>" + (index + 1) + "</td>");
			buffer.append("<td>" + product.getSubcategory() + "</td>");
			buffer.append("<td>" + product.getVendor() + "</td>");
			buffer.append("<td>" + product.getProduct_name() + "</td><input type='hidden' value='" + pid
					+ "' name='procurements[" + index + "].product_name'/>");
			buffer.append("<td id='mrp" + index + "'>Rs. " + product.getMrp() + "</td>");
			buffer.append("<td>");
			buffer.append("<div class='form-inline'>");
			buffer.append("<div class='form-group' style='width: 59%;'>");
			buffer.append("<input type='text' class='form-control' id='bqty" + index + "' name='procurements[" + index
					+ "].quantity' onchange='gettotalamt(" + index + ",this.value," + pid
					+ ")' value='1' style='width:100%'>");
			buffer.append("</div>");
			buffer.append("</div>");
			buffer.append("</td>");
			buffer.append("<td id='t" + index + "'>Rs. " + product.getMrp() + "</td>");
			buffer.append("<td><a href=''>Remove</a></td>");
			buffer.append("~");
			buffer.append("" + product.getMrp() + "");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String savepobarcode() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			for (Product product : productForm.getProcurements()) {

				String pid = product.getProduct_name();
				Product productmaster = inventoryProductDAO.getProduct(pid);
				int procurementid = procurementDAO.saveParengtPrecurementData(productmaster.getVendor_id(), date, 0,
						"0", 0,"","0");
				String qty = product.getQuantity();
				double total = Double.parseDouble(productmaster.getMrp()) * Double.parseDouble((qty));
				int result = procurementDAO.saveProcurementData(productmaster, qty, total, productmaster.getVendor_id(),
						productmaster.getBrand_id(), procurementid, 0, 0, productmaster.getVat());

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "procurement";
	}

	public String getsumqty() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("pid");
			int qty = Integer.parseInt(request.getParameter("qty"));
			Product product = inventoryProductDAO.getProduct(id);

			int mrp = Integer.parseInt(product.getMrp());
			int total = mrp * qty;

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + total + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public void prepare() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);

			/*
			 * String location=(String) session.getAttribute("location");
			 * if(location==null){ location="0"; }
			 */

			String userid = loginInfo.getUserId();
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
			String location = "";
			if (ispharmacist) {
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				location = priscription.getLocation();
			} else {
				location = inventoryProductDAO.getHISUserLocation(userid);
			}

			if (location == null) {
				location = "0";
			}

			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			ArrayList<Product> subcategoryList = inventoryProductDAO.getAllSubCategoryList(null);
			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandList(null);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList();
			productForm.setWarehouseList(warehouseList);
			productForm.setCategoryList(categoryList);
			productForm.setSubcategoryList(subcategoryList);
			productForm.setVendorList(vendorList);
			productForm.setBrandnameList(brandnameList);
			ArrayList<Master> medicineTypeList = inventoryProductDAO.getMedicineTypeList();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationListPharmacy(locationlist);
			productForm.setMedicineTypeList(medicineTypeList);
			//   17 Nov 2017 to show all location
			// ArrayList<Master> locationListPharmacy=
			// pharmacyDAO.getAllLocation();
			// productForm.setLocationListPharmacy(locationListPharmacy);

			productForm.setLocation(location);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String addsaleproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			int index = Integer.parseInt(request.getParameter("rowcount"));
			StringBuffer buffer = new StringBuffer();

			ArrayList<Product> godownlist = inventoryProductDAO.getGodownList();
			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			ArrayList<Product> subcategoryList = inventoryProductDAO.getAllSubCategoryList(null);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			String categoryid = (String) session.getAttribute("categoryid");
			if (categoryid == null) {

				categoryid = "1";
			}
			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(categoryid);

			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index + "].gowdown'>");
			buffer.append("<option value='0'>Select Gowdown</option>");

			for (Product product : godownlist) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index
					+ "].category_id' onchange='getprodtype(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Category</option>");

			for (Product product : categoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td id='tdsub" + index + "'>");
			buffer.append("<select class='form-control' name='sales[" + index + "].subcategory_id'>");
			buffer.append("<option value='0'>Select Sub Category</option>");

			for (Product product : subcategoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index
					+ "].vendor_id' onchange='getsalesproduct(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Supplier</option>");

			for (Vendor vendor : vendorList) {

				buffer.append("<option value='" + vendor.getId() + "'>" + vendor.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td id='tdprod" + index + "'>");
			buffer.append("<select class='form-control' name='sales[" + index
					+ "].product_id' onchange='getstockandprice(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Product</option>");

			for (Product product : productList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td><span id='minstock" + index + "'>50</span></td>");
			buffer.append("<td><input class='form-control' type='text' name='sales[" + index
					+ "].quantity' placeholder='enter qty'></td>");
			buffer.append("<td><span id='saleprice" + index + "'>Rs. 130</span></td>");
			buffer.append("<td><a href='#'><i class='fa fa-edit'></i></a></td>");
			buffer.append("<td><a href='#'>Remove</a></td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String getsubproductssale() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String subid = request.getParameter("id");
			String index = request.getParameter("index");
			ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select name='sales[" + index + "].subcategory_id' style='width:100%' class='form-control'>");
			buffer.append("<option value='0'>Select Product Type</option>");
			for (Product product : subcategoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;

	}

	public String getsalesproduct() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String index = request.getParameter("index");
			ArrayList<Product> productList = inventoryProductDAO.getProductByVendor(vendorid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<select name='sales[" + index
					+ "].product_id' style='width:100%' onchange='getstockandprice(this.value," + index
					+ ")' class='form-control'>");
			buffer.append("<option value='0'>Select Product Name</option>");
			for (Product product : productList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;

	}

	public String getproddetails() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String pid = request.getParameter("pid");
			Product product = inventoryProductDAO.getProduct(pid);
			String data = product.getStock() + "~" + product.getSale_price();
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addtosale() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String date[] = datetime.split(" ");

			for (Product product : productForm.getSales()) {

				product.setLastmodified(datetime);
				product.setSale_date(date[0]);
				int r = inventoryProductDAO.addSaleProduct(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "savesale";
	}

	public String getsaledetails() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			Product product = inventoryProductDAO.getSaleProducDetails(id);
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<td>" + product.getGowdown() + "</td><input type='hidden' id='saleidhidden' value='" + id + "'>");
			buffer.append("<td>" + product.getSale_date() + "</td>");
			buffer.append("<td>" + product.getCategory() + "</td>");
			buffer.append("<td>" + product.getSubcategory() + "</td>");
			buffer.append("<td>" + product.getVendor() + "</td>");
			buffer.append("<td>" + product.getProduct_name() + "</td>");
			buffer.append("<td>" + product.getQuantity() + "</td>");
			buffer.append("<td><input class='form-control' type='text' id='retqty' placeholder='qty return'><td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String returnsaleqty() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String lastmodified = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String id = request.getParameter("id");
			String returnqty = request.getParameter("returnqty");

			int i = inventoryProductDAO.returnsaleQty(id, returnqty, lastmodified);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addmoresaleproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			int index = Integer.parseInt(request.getParameter("rowcount"));
			StringBuffer buffer = new StringBuffer();

			ArrayList<Product> godownlist = inventoryProductDAO.getGodownList();
			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			ArrayList<Product> subcategoryList = inventoryProductDAO.getAllSubCategoryList(null);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			String categoryid = (String) session.getAttribute("category");

			if (categoryid == null) {

				categoryid = productForm.getCategory_id();

			}
			ArrayList<Product> productList = inventoryProductDAO.getAllProducts(categoryid);

			buffer.append("<td>" + (index + 1) + "</td>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index + "].gowdown'>");
			buffer.append("<option value='0'>Select Gowdown</option>");

			for (Product product : godownlist) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index
					+ "].category_id' onchange='getprodtype(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Category</option>");

			for (Product product : categoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td id='tdsub" + index + "'>");
			buffer.append("<select class='form-control' name='sales[" + index + "].subcategory_id'>");
			buffer.append("<option value='0'>Select Sub Category</option>");

			for (Product product : subcategoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index
					+ "].vendor_id' onchange='getsalesproduct(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Supplier</option>");

			for (Vendor vendor : vendorList) {

				buffer.append("<option value='" + vendor.getId() + "'>" + vendor.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td id='tdprod" + index + "'>");
			buffer.append("<select class='form-control' name='sales[" + index
					+ "].product_id' onchange='getstockandprice(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Product</option>");

			for (Product product : productList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td><span id='minstock" + index + "'>50</span></td>");
			buffer.append("<td><input class='form-control' type='text' name='sales[" + index
					+ "].quantity' id='quantity" + index + "' placeholder='enter qty'></td>");
			buffer.append("<td><span id='saleprice" + index + "'>Rs. 130</span></td>");
			buffer.append("<td><a href=''><img src='inventory/img/close_pop.png' class='setcross'/></a></td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addmoresaleproductbarcode() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String categoryid = (String) session.getAttribute("category");

			if (categoryid == null) {

				categoryid = productForm.getCategory_id();

			}
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			int index = Integer.parseInt(request.getParameter("rowcount"));
			String pid = request.getParameter("pid");
			Product product = inventoryProductDAO.getProduct(pid);
			StringBuffer buffer = new StringBuffer();
			ArrayList<Product> godownlist = inventoryProductDAO.getGodownList();

			buffer.append("<td>" + (index + 1) + "</td>");

			buffer.append("<td>");
			buffer.append("<select class='form-control' name='sales[" + index + "].gowdown'>");
			buffer.append("<option value='0'>Select Godown</option>");

			for (Product godown : godownlist) {

				buffer.append("<option value='" + godown.getId() + "'>" + godown.getName() + "</option>");

			}
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td>" + product.getCategory() + "</td>");

			buffer.append("<td>" + product.getSubcategory() + "</td>");

			buffer.append("<td>" + product.getVendor() + "</td>");

			buffer.append("<td>" + product.getProduct_name() + "</td><input type='hidden' name='sales[" + index
					+ "].product_id' value='" + pid + "'>");

			buffer.append("<td>" + product.getStock() + "</td>");
			buffer.append("<td><input class='form-control' type='text' name='sales[" + index
					+ "].quantity' id='quantity" + index + "' placeholder='enter qty'></td>");
			buffer.append("<td>" + product.getSale_price() + "</td>");
			buffer.append("<td><a href=''><img src='inventory/img/close_pop.png' class='setcross1'/></a></td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String setcategory() {
		if (!verifyLogin(request)) {
			return "login";
		}
		try {
			String category = request.getParameter("category");

			session.setAttribute("category", category);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public String getvendors() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String brandid = request.getParameter("brandid");
			String index = request.getParameter("index");
			StringBuffer buffer = new StringBuffer();

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorListFromBrand(brandid);
			buffer.append("<select class='form-control' name='products[" + index
					+ "].vendor' onchange='getproduct(this.value," + index + ")'  id='vendor" + index + "'>");
			buffer.append("<option value='0'>Select Vendor</option>");
			for (Vendor vendor : vendorList) {

				buffer.append("<option value='" + vendor.getId() + "'>" + vendor.getName() + "</option>");

			}

			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String getvendorproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String index = request.getParameter("index");

			ArrayList<Product> productList = inventoryVendorDAO.getVendorProducts(vendorid);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<select name='products[" + index + "].product_id' id='product" + index
					+ "' class='form-control chosen'>");
			buffer.append("<option value='0'> Select Product </option>");
			for (Product product : productList) {
				buffer.append("<option value='" + product.getId() + "'> " + product.getProduct_name() + " </option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addnewproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String count = request.getParameter("count");
			StringBuffer buffer = new StringBuffer();

			String categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {
				categoryid = "2";
			}

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);

			buffer.append("<td id='listvendor" + count + "' >");
			buffer.append("<select class='form-control chosen' name='products[" + count + "].vendor' id='vendor" + count
					+ "' onchange='getproduct(this.value," + count + ")'>");
			buffer.append("<option value='0'>Select Supplier</option>");
			for (Vendor vendor : vendorList) {

				buffer.append("<option value='" + vendor.getId() + "'>" + vendor.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td id='listproduct" + count + "' >");
			buffer.append("<select class='form-control' id='product" + count + "' name='products[" + count
					+ "].product_id'  > ");
			buffer.append("<option value='0' >Select Product</option>");
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td><input type='number' class='form-control' placeholder='enter quantity' name='products["
					+ count + "].quantity' id='quantity" + count + "' /></td>");

			buffer.append(
					"<td><a href='#' onclick=deleteRow('mytable')><i class='fa fa-times fa-2x' style='color:#d9534f;' aria-hidden='true'></i></a></td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String supplierledgerreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			if (fromdate == null) {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -30);
				fromdate = dateFormat.format(calendar.getTime());

			} else {

				if (fromdate.equals("")) {

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -30);
					fromdate = dateFormat.format(calendar.getTime());
				} else {

					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}

			}
			if (todate == null) {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				todate = dateFormat.format(calendar.getTime());

			} else {

				if (todate.equals("")) {

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					todate = dateFormat.format(calendar.getTime());
				} else {

					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Purchase Report", loginInfo.getUserId(), fromdate, todate,
					date, "supplierledgerreport");
			ArrayList<Product> graphSupplierReport = procurementDAO.getSupplierGraphReport(fromdate, todate);
			productForm.setGraphSupplierReport(graphSupplierReport);
			session.setAttribute("graphSupplierReport", graphSupplierReport);

			ArrayList<Procurement> supplierProcurementList = procurementDAO.getSupplierWiseReport(fromdate, todate,
					location);
			productForm.setSupplierProcurementList(supplierProcurementList);

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "supplierledgerreport";
	}

	public String vatreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();

			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = productForm.getLocation();
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			productForm.setLocation(location);
			productForm.setIsfrombincard(1);

			if (fromdate == null) {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());

			} else {

				if (fromdate.equals("")) {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());

				} else {

					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}

			}
			if (todate == null) {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());

			} else {

				if (todate.equals("")) {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("GST Report", loginInfo.getUserId(), fromdate, todate, date,
					"vatreport");

			ArrayList<Product> vatReportList = inventoryProductDAO.getVatReportList(fromdate, todate, location);
			productForm.setVatReportList(vatReportList);
			todate = DateTimeUtils.getCommencingDate1(todate);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			if (vatReportList.size() > 1) {
				int size = vatReportList.size();
				Product product = vatReportList.get(size - 1);
				productForm.setAllsixtaxTot(product.getAllsixtaxTot());
				productForm.setAllthirttaxTot(product.getAllthirttaxTot());
				productForm.setAllsixVatPer(product.getAllsixVatPer());
				productForm.setAllthirVatPer(product.getAllthirVatPer());
				productForm.setAlltableValtot(product.getAlltotvatTotal());
				productForm.setAlltotvatTotal(product.getAlltotvatTotal());
				productForm.setAlltotalGross(product.getAlltotalGross());
				productForm.setAlltotalNet(product.getAlltotalNet());

			}
			
			setLetterData(location, connection);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "vatreport";
	}

	public String saveproducts() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String categoryid = (String) session.getAttribute("category");

			if (categoryid == null) {
				categoryid = "2";
			}
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String datetime[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");

			for (Product product : productForm.getProducts()) {

				product.setUserid(loginInfo.getUserId());
				product.setCategory_id(categoryid);
				product.setLastmodified(datetime[0]);
				product.setLocation(location);
				// for medicine
				String pid = product.getProduct_id();
				Product master = inventoryProductDAO.getProduct(pid);
				product.setProduct_name(master.getProduct_name());
				product.setGenericname(master.getGenericname());
				product.setMedicinenameid(pid);
				int res = inventoryProductDAO.savetempProduct(product);
				int result = procurementDAO.addTempPo(String.valueOf(res), product.getVendor(), product.getQuantity(),
						0, "0", "0", 0, product.getVat(), loginInfo.getLoginsessionid(),"","","0");

			}

			int procurementid = 0;

			ArrayList<Product> polistByVendor = procurementDAO.getAllPoListByVendor(loginInfo.getLoginsessionid(),"0");
			for (Product product : polistByVendor) {

				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				procurementid = procurementDAO.saveParengtPrecurementData(product.getVendor_id(), date, 0, "0", 1,"","0");

				ArrayList<Product> polist = procurementDAO.getPoProductByVendor(product.getVendor_id(),
						loginInfo.getLoginsessionid(),"0");
				for (Product poproduct : polist) {
					poproduct.setLocation(location);
					product.setGrnwithpo_child(1);
					int result = procurementDAO.saveProcurementData(poproduct, ""+poproduct.getQty(), 0,
							product.getVendor_id(), "0", procurementid, 0, 0, poproduct.getVat());
					result = procurementDAO.updateProcurementStatus(poproduct.getId(), "0");
				}
			}

			int result = procurementDAO.truncateTempPo(loginInfo.getLoginsessionid());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "procurement";
	}

	public String viewsupplierHistory() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			PoPaymenytDAO paymenytDAO = new JDBCPoPaymengtDAO(connection);

			String vendorid = request.getParameter("vendorid");
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			StringBuffer buffer = new StringBuffer();
			String data = vendor.getName() + " | " + vendor.getCityName() + " | " + vendor.getMobile_pri();
			ArrayList<Product> producBillListSupplier = inventoryProductDAO.getSupplierBillProcurementandDates(vendorid,
					fromdate, todate);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			for (Product mastProduct : producBillListSupplier) {

				ArrayList<Product> productBillList = inventoryProductDAO.getSupplierBillList(vendorid,
						mastProduct.getDate(), mastProduct.getVoucherno(), mastProduct.getProcurementid());

				if (productBillList.size() > 0) {

					double totalPaid = paymenytDAO.getPaidAmtofVoucher(mastProduct.getVoucherno(),
							mastProduct.getProcurementid());
					int dmcmplted = procurementDAO.checkIsComplatedDm(mastProduct.getProcurementid());
					double totalVoucherBill = paymenytDAO.getTotalVoucherAmount(mastProduct.getVoucherno(),
							mastProduct.getProcurementid(), dmcmplted);
					double totalBal = totalVoucherBill - totalPaid;
					buffer.append("<b style='color: #e69e18;font-weight: normal;'>Voucher No: "
							+ mastProduct.getVoucherno() + " | Date: " + mastProduct.getDate() + " | Memo: "
							+ mastProduct.getPayType() + " </b>");
					buffer.append("<div class=''>");
					buffer.append(
							"<table class='table table-bordered' cellspacing='0' width='100%' style='margin-bottom: 0px;'>");
					buffer.append("<thead>");
					String subTotal = "0";
					String allTotal = "0";

					buffer.append("<tr class='tableback'>");
					buffer.append("<th style='width: 6%;text-align:center'>Qty</th>");
					buffer.append("<th style='width: 6%;text-align:center'>pack</th>");
					buffer.append("<th style='width: 38%;'>Product Name</th>");
					buffer.append("<th style='width: 4%;'>Mfg</th>");
					buffer.append("<th style='width: 6%;'>Batch No</th>");
					buffer.append("<th style='width: 6%;text-align:right'>Price</th>");
					buffer.append("<th style='width: 6%;text-align:right'>Total</th>");
					buffer.append(" </tr>");
					buffer.append("</thead>");
					buffer.append("<tbody>");

					for (Product product : productBillList) {

						buffer.append("<tr>");
						buffer.append("<td class='text-center'>" + product.getQty() + "</td>");
						buffer.append("<td class='text-center'>" + product.getPack() + "</td>");
						buffer.append("<td>" + product.getProduct_name() + "</td>");
						buffer.append("<td>" + product.getMfg() + "</td>");
						buffer.append("<td>" + product.getBatch_no() + "</td>");
						buffer.append("<td class='text-right'>Rs." + product.getPurchase_price() + "</td>");
						buffer.append(" <td class='text-right'>Rs." + product.getTotal() + "</td>");
						buffer.append("</tr>");
						subTotal = product.getSubTotal();
						allTotal = product.getAllTotal();

					}
					buffer.append("</tbody>");
					buffer.append("</table>");

					buffer.append(
							"<div class='' style='border-top: 1px solid #000;border-bottom: 1px solid #000;padding-left: 0px;'>");
					buffer.append("<div class='text-right'>");
					buffer.append("<div class='' style=''>");
					buffer.append("<h4 style='font-weight: bold;font-size: 13px;'>Total : Rs." + subTotal + "</h4>");
					buffer.append("<h4 style='color: red;font-size: 13px;'> Balance : Rs."
							+ DateTimeUtils.changeFormat(totalBal) + "</h4>");
					buffer.append("<h4 style='font-size: 13px;color:green;'>Paid : Rs."
							+ DateTimeUtils.changeFormat(totalPaid) + "</h4>");

					buffer.append("</div></div>");

				}

			}

			buffer.append("~" + data);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String expirymedicine() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = (String) session.getAttribute("location");
			String report = productForm.getReport();
			String vendor_id = productForm.getVendor_id();
			if (vendor_id == null) {
				vendor_id = "0";
			} else if (vendor_id.equals("")) {
				vendor_id = "0";
			}
			if (location == null) {
				location = "0";
			}

			if (report == null) {
				report = "2";
			}

			if (report.equals("")) {
				report = "2";
			}

			if (fromdate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				// calendar.add(Calendar.DATE, -30);
				// calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());
			} else {
				if (fromdate.equals("")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					// calendar.add(Calendar.DATE, -30);
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}
			if (todate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				todate = dateFormat.format(calendar.getTime());
			} else {
				if (todate.equals("")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					todate = dateFormat.format(calendar.getTime());
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}
			String days = productForm.getDays();
			productForm.setVendor_id(vendor_id);
			if (days == null) {
				days = "0";
			}
			if (days.equals("")) {
				days = "0";
			}
			// double[] useandDeadPer=
			// inventoryProductDAO.getGraphPerUseandDead(fromdate,todate,days);
			//   28 sep 2017 Pagination code
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			int result = chargesReportDAO.saveMisReportLog("Expiry Product Report", loginInfo.getUserId(), fromdate,
					todate, date, "expirymedicine");

			if (report.equals("2") || report.equals("3")) {
				int i = Integer.parseInt(days);
				if (report.equals("2")) {
					i = -Integer.parseInt(days);
				}
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Date d = sdf1.parse(todate);
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(d);
				cal1.add(Calendar.DATE, i);
				String dt = sdf1.format(cal1.getTime());
				int count = 0;
				/*
				 * if(report.equals("2")){ if(days.equals("0")){ count =
				 * inventoryProductDAO.getCountExpiryMedicineReportNew(fromdate,
				 * todate,days,location,report); }else{ count =
				 * inventoryProductDAO.getCountExpiryMedicineReportNew(dt,todate
				 * ,days,location,report); } }else{ if(days.equals("0")){ count
				 * =
				 * inventoryProductDAO.getCountExpiryMedicineReportNew(fromdate,
				 * todate,days,location,report); }else{ count =
				 * inventoryProductDAO.getCountExpiryMedicineReportNew(todate,dt
				 * ,days,location,report); }
				 * 
				 * }
				 */

				ArrayList<Product> expiryMedicineReport = new ArrayList<Product>();
				if (report.equals("2")) {
					if (days.equals("0")) {
						count = inventoryProductDAO.getCountExpiryMedicineReportNew(fromdate, todate, days, location,
								report, vendor_id);
						pagination.setPreperties(count);
						expiryMedicineReport = inventoryProductDAO.getExpiryMedicineReportNew(fromdate, todate, days,
								location, report, pagination, vendor_id);
					} else {
						count = inventoryProductDAO.getCountExpiryMedicineReportNew(dt, todate, days, location, report,
								vendor_id);
						pagination.setPreperties(count);
						expiryMedicineReport = inventoryProductDAO.getExpiryMedicineReportNew(dt, todate, days,
								location, report, pagination, vendor_id);
					}
				} else {
					if (days.equals("0")) {
						count = inventoryProductDAO.getCountExpiryMedicineReportNew(fromdate, todate, days, location,
								report, vendor_id);
						pagination.setPreperties(count);
						expiryMedicineReport = inventoryProductDAO.getExpiryMedicineReportNew(fromdate, todate, days,
								location, report, pagination, vendor_id);
					} else {
						count = inventoryProductDAO.getCountExpiryMedicineReportNew(todate, dt, days, location, report,
								vendor_id);
						pagination.setPreperties(count);
						expiryMedicineReport = inventoryProductDAO.getExpiryMedicineReportNew(todate, dt, days,
								location, report, pagination, vendor_id);
					}

				}

				productForm.setExpiryMedicineReport(expiryMedicineReport);
				pagination.setPage_records(expiryMedicineReport.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			} else {
				int count = inventoryProductDAO.getCountExpiryMedicineReport(fromdate, todate, days, location, report);
				pagination.setPreperties(count);
				ArrayList<Product> expiryMedicineReport = inventoryProductDAO.getExpiryMedicineReport(fromdate, todate,
						days, location, report, pagination);
				productForm.setExpiryMedicineReport(expiryMedicineReport);
				pagination.setPage_records(expiryMedicineReport.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			}

			// session.setAttribute("usedead", useandDeadPer);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);
			productForm.setDays(days);
			productForm.setReport(report);
			ArrayList<Vendor> vendorList = procurementDAO.getVendorList();
			productForm.setVendorList(vendorList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "expirymedicine";
	}

	public String expencereport() {
		if (!verifyLogin(request)) {
			return "login";
		}
		return "expencereport";

	}

	public String profitloss() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {
				categoryid = "2";
			}
			productForm.setCategory_id(categoryid);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());

			}

			double openingstock = inventoryProductDAO.getOpeningStock(fromdate, todate);
			double purchasestock = inventoryProductDAO.getPurchaseStock(fromdate, todate);
			double soldstock = inventoryProductDAO.getSoldMedicineAmt(fromdate, todate);
			double totalDebit = openingstock + purchasestock;
			double closingStock = openingstock + soldstock;
			double totalCredit = soldstock + closingStock;

			productForm.setOpeningstock(DateTimeUtils.changeFormat(openingstock));
			productForm.setPurchasestock(DateTimeUtils.changeFormat(purchasestock));
			productForm.setSoldstock(DateTimeUtils.changeFormat(soldstock));
			productForm.setTotalCredit(DateTimeUtils.changeFormat(totalCredit));
			productForm.setTotalDebit(DateTimeUtils.changeFormat(totalDebit));
			productForm.setClosingStock(DateTimeUtils.changeFormat(closingStock));

			double netProfit = totalCredit - totalDebit;
			double netLoss = 0;
			if (netProfit > 0) {
				netLoss = 0;
			} else {
				netLoss = totalDebit - totalCredit;
				netProfit = 0;
			}
			productForm.setNetProfit(DateTimeUtils.changeFormat(netProfit));
			productForm.setNetLoss(DateTimeUtils.changeFormat(netLoss));

			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));

			session.setAttribute("productForm", productForm);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "profitloss";
	}

	public String profitlossreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Profit / Loss Report", loginInfo.getUserId(), fromdate,
					todate, date, "profitlossreport");
			ArrayList<Product> profitlossreportList = inventoryProductDAO.getProfitandLostReport();
			productForm.setProfitlossreportList(profitlossreportList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "profitlossreport";
	}

	public String itemwisereport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String location = productForm.getReportlocation();
			if (location != null) {
				if (location.equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);

			// ArrayList<Product> productList=
			// catalogueDAO.getAllProductListItemWiseSale(location);
			String product_id = productForm.getProduct_name();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String product_type = productForm.getProduct_type();

			String isfromoepning = request.getParameter("isfromoepning");
			int isfromoepningclosing = 0;
			String catalogueid = "0";
			if (isfromoepning != null) {
				if (isfromoepning.equals("1")) {
					isfromoepningclosing = 1;
					catalogueid = request.getParameter("catalogueid");
				}
			}

			ArrayList<Product> itemWiseListReport = new ArrayList<Product>();
			if (isfromoepningclosing == 1) {
				fromdate = request.getParameter("fromdate");
				todate = request.getParameter("todate");
			}
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					if (isfromoepningclosing == 0)
						fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					if (isfromoepningclosing == 0)
						todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());

			}

			if (product_id != null) {
				if (product_id.equals("0")) {
					product_id = "";
				}
			} else {
				product_id = "";
			}
			if (product_type != null) {
				if (product_type.equals("")) {
					product_type = "0";
				}
			} else {
				product_type = "0";
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			int count = inventoryProductDAO.getAllItemWiseReportListCount(fromdate, todate, product_id, location,
					product_type, isfromoepningclosing, catalogueid);
			pagination.setPreperties(count);

			int result = chargesReportDAO.saveMisReportLog("Item Wise Sale Report", loginInfo.getUserId(), fromdate,
					todate, date, "itemwisereport");
			// itemWiseListReport=
			// inventoryProductDAO.getItemWiseReportList(fromdate,todate,product_id,location);
			// itemWiseListReport=
			// inventoryProductDAO.getItemWiseReportListNew(fromdate,todate,product_id,location);
			itemWiseListReport = inventoryProductDAO.getAllItemWiseReportList(fromdate, todate, product_id, location,
					pagination, product_type, isfromoepningclosing, catalogueid);
			productForm.setItemWiseListReport(itemWiseListReport);
			// productForm.setProductList(productList);

			pagination.setTotal_records(itemWiseListReport.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getTotal_records()));

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			ArrayList<Master> reportlocationList = pharmacyDAO.getAllLocation();
			productForm.setReportlocationList(reportlocationList);
			productForm.setReportlocation(location);
			productForm.setProduct_name(product_id);
			
			setLetterData(location, connection);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "itemwisereport";
	}

	public String updateprod() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			String genericname = request.getParameter("genericname");
			String category_id = request.getParameter("category_id");
			String subcategory_id = request.getParameter("subcategory_id");
			String product_name = request.getParameter("product_name");
			String mrp = request.getParameter("mrp");
			String purchase_price = request.getParameter("purchase_price");
			String sale_price = request.getParameter("sale_price");
			String stock = request.getParameter("stock");
			String mfg = request.getParameter("mfg");
			String hsnno = request.getParameter("hsnno");
			String location = request.getParameter("location");
			String gst = request.getParameter("gst");
			String comment = request.getParameter("comment");
			String medicine_shedule = request.getParameter("medicine_shedule");
			String description = request.getParameter("description");
			String pack = request.getParameter("pack");
			String minorder = request.getParameter("minorder");
			String maxorder = request.getParameter("maxorder");
			String prod_code = request.getParameter("productcode");
			String uom = request.getParameter("uom");
			if (pack == null) {
				pack = "1";
			}
			if (pack.equals("")) {
				pack = "1";
			}
			Product product = new Product();
			product.setId(Integer.parseInt(id));
			product.setGenericname(genericname);
			product.setProduct_name(product_name);
			product.setMrp(mrp);
			double stock1=Double.parseDouble(stock);
			product.setStock((stock));
			product.setPack(pack);
			product.setMedicine_shedule(medicine_shedule);
			product.setDescription(description);
			product.setSale_price(sale_price);
			product.setPurchase_price(purchase_price);
			product.setMfg(mfg);
			product.setHsnno(hsnno);
			product.setVat(gst);
			product.setLocation(location);
			product.setComment(comment);
			product.setCategory_id(category_id);
			product.setSubcategory_id(subcategory_id);
			product.setMinorder(minorder);
			product.setMaxorder(maxorder);
			product.setPro_code(prod_code);
			product.setUom(uom);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			Product product2 = inventoryProductDAO.getProductCatalogueDetails("" + product.getId());
			int result = inventoryProductDAO.updateProductCatalogue(product);
			if (location == null) {
				location = "0";
			}
			String userid = loginInfo.getUserId();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = inventoryProductDAO.addToProductUpdatedLog(userid, date, location, 1, "0", product.getComment(),
					id, stock, stock, product2, product, datetime);
			
			inventoryProductDAO.updateProductNameFromCatalogue(product_name,id);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "viewcatalogue";
	}

	public String updateprodstock() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			String stock = request.getParameter("stock");
			String expiry_date = request.getParameter("expiry_date");
			String batch_no = request.getParameter("batch_no");
			String shelf = request.getParameter("shelf");
			String secondary_shelf = request.getParameter("secondary_shelf");
			String mrp = request.getParameter("mrp");
			String sale_price = request.getParameter("sale_price");
			String pack = request.getParameter("pack");
			String barcode = request.getParameter("barcode");
			/*
			 * String remark = request.getParameter("remark"); String
			 * description= request.getParameter("description");;
			 */
			String previousqty = request.getParameter("previousqty");
			String isfromcathlab = productForm.getIsfromcathlab();
			if (shelf == null) {
				shelf = "";
			}
			String gst = request.getParameter("gst");
			String purchase_price = request.getParameter("purchase_price");
			Product product = new Product();
			product.setId(Integer.parseInt(id));
			product.setStock((stock));
			product.setBatch_no(batch_no);
			product.setMrp(mrp);
			product.setSale_price(sale_price);
			product.setPack(pack);
			
			String expirydate3 ="";
			if(!DateTimeUtils.isNull(expiry_date).equals("")){
				String[] data = expiry_date.split("/");
				Calendar mycal = new GregorianCalendar(Integer.parseInt(data[1]), Integer.parseInt(data[0]) - 1, 1);
				int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
				expirydate3 = daysInMonth + "-" + data[0] + "-" + data[1];
				// String expirydate3 = "28"+"-"+data[0]+"-"+data[1];
			}
			expiry_date = DateTimeUtils.getCommencingDate1(expirydate3);
			product.setExpiry_date(expiry_date);
			product.setShelf(shelf);
			product.setVat(gst);
			product.setBarcode(barcode);
			product.setPurchase_price(purchase_price);
			product.setSecondary_shelf(secondary_shelf);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			Product product2 = inventoryProductDAO.getProduct(id);
			int result = inventoryProductDAO.updateProductStockStatus(product);

			int status_type = 1;
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date1 = dateFormat.format(cal.getTime());
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			int status = 1;
			int parentd = inventoryProductDAO.saveParentAdjustmentData(status_type, userid, date1, location, status);
			int res1 = inventoryProductDAO.saveChildMultiEditData(product, product2, userid, date1, location, parentd);

			if (result > 0) {
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int res = inventoryProductDAO.addToProductUpdatedLog(userid, date, product2.getLocation(), 1, id,
						product.getComment(), "0", product2.getStock(), product.getStock(), product2, product,
						datetime);
			}

			String searchtext = productForm.getSearchtext();
			String category_id = productForm.getCategory_id();
			String withstock_filter = productForm.getWithstock_filter();
			String product_type = productForm.getProduct_type();
			String shelf_filter = productForm.getShelf_filter();

			session.setAttribute("stockstatus_searchtext", searchtext);
			session.setAttribute("stockstatus_category_id", category_id);
			session.setAttribute("stockstatus_withstock_filter", withstock_filter);
			session.setAttribute("stockstatus_product_type", product_type);
			session.setAttribute("stockstatus_shelf_filter", shelf_filter);
			if (isfromcathlab != null) {
				if (isfromcathlab.equals("1")) {
					return "transferproductdata2";
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "listproduct";
	}

	public String productReturnReport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		return "productReturnReport";

	}

	public String bincardreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			productForm.setIsfrombincard(1);
			connection = Connection_provider.getconnection();
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			ArrayList<Product> productList = catalogueDAO.getAllProductList(location);
			productForm.setProductList(productList);

			if (location.equals("0")) {
				productForm.setLocationName("Admin");
			} else {

				String locationName = pharmacyDAO.getLocationName(location);
				productForm.setLocationName(locationName);
			}

			String catalogueid = productForm.getCatalogueid();
			if (catalogueid == null) {
				catalogueid = "0";
			}

			String userwise = productForm.getUserwise();
			if (userwise == null) {
				userwise = "0";
			} else if (userwise.equals("")) {
				userwise = "0";
			}
			String location_filter = productForm.getLocation_filter();
			if (location_filter != null) {
				if (location_filter.equals("")) {
					location_filter = "0";
				}
			} else {
				location_filter = "0";
			}

			productForm.setLocation_filter(location_filter);
			productForm.setUserwise(userwise);
			if (!catalogueid.equals("0")) {

				String fromdate = productForm.getFromdate();
				String todate = productForm.getTodate();
				if (fromdate == null) {

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -30);
					fromdate = dateFormat.format(calendar.getTime());
				} else {

					if (!fromdate.equals("")) {
						fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					} else {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DATE, -30);
						fromdate = dateFormat.format(calendar.getTime());
					}

				}
				if (todate == null) {

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					todate = dateFormat.format(calendar.getTime());
				} else {

					if (!todate.equals("")) {
						todate = DateTimeUtils.getCommencingDate1(todate);
					} else {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						todate = dateFormat.format(calendar.getTime());
					}
				}
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());

				int result = chargesReportDAO.saveMisReportLog("Bin Card Report", loginInfo.getUserId(), fromdate,
						todate, date, "bincardreport");
				ArrayList<Product> bincardReportList = productDAO.getBinCardReport(location, catalogueid, fromdate,
						todate, clinicabrivation, userwise, location_filter);
				double receipt = 0;
				int issue = 0;
				int balance = 0;
				for (Product product : bincardReportList) {

					if (product.getQty()!=0.0) {
						receipt = receipt + product.getQty();
					}
					if (!product.getIssueqty().equals("-")) {
						System.out.println(product.getTranstype());
						issue = issue + Integer.parseInt(DateTimeUtils.numberCheck(product.getIssueqty()));
						System.out.println(product.getIssueqty());
					}
					balance = product.getBalqty();
				}

				productForm.setBalance(String.valueOf(balance));
				productForm.setIssueqty(String.valueOf(issue));
				productForm.setReceiptqty(String.valueOf(receipt));

				productForm.setBincardReportList(bincardReportList);
				Product product = catalogueDAO.getProductDetails(catalogueid);
				productForm.setProduct_name(product.getProduct_name());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
				productForm.setFromdate(fromdate);
				productForm.setTodate(todate);
			} else {

				productForm.setProduct_name("-");
				productForm.setFromdate("");
				productForm.setTodate("");
			}
			ArrayList<UserProfile> userlist = userProfileDAO.getAllUserProfileList();
			productForm.setUserlist(userlist);

			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationlist(locationlist);

			productForm.setHidecatagoryid("1");

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "bincardReport";

	}

	public String productReceivedReport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = productForm.getVendor_id();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location_filter = productForm.getLocation_filter();
			String purchaseinvoice = productForm.getPurchaseinvoice();
			ArrayList<Product> productWiseReport = new ArrayList<Product>();
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}
			if (vendorid != null) {

				if (vendorid.equals("0")) {
					vendorid = null;
				}
			}
			if (location_filter == null) {
				location_filter = "0";
			} else if (location_filter.equals("")) {
				location_filter = "0";
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("GRN Report", loginInfo.getUserId(), fromdate, todate, date,
					"productReceivedReport");
			int count = inventoryProductDAO.getTotalGrnReportCount(fromdate, todate, vendorid, location_filter,
					purchaseinvoice);
			pagination.setPreperties(count);
			productWiseReport = inventoryProductDAO.getProductWiseReportList(fromdate, todate, vendorid,
					location_filter, purchaseinvoice, pagination);
			productForm.setTotalRecords(count);
			pagination.setTotal_records(productWiseReport.size());
			pagination.setPage_records(productWiseReport.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			Vendor vendor = vendorDAO.getVendor(vendorid);
			productForm.setVendor(vendor.getName());

			int totalgrn = procurementDAO.getTotalTodayGRN(fromdate, todate, location_filter);
			productForm.setTotalgrn(totalgrn);

			productForm.setProductWiseReport(productWiseReport);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList();
			productForm.setWarehouseList(warehouseList);
			int size = productWiseReport.size();
			if (size > 0) {
				double payment = productWiseReport.get(size - 1).getTotal_amount();
				double totalsubtotal = productWiseReport.get(size - 1).getTotalsubtotal();
				double totalvat = productWiseReport.get(size - 1).getTotalvat();
				productForm.setTotal_amount(payment);
				productForm.setTotalvat(Math.round(totalvat * 100.00) / 100.00);
				productForm.setTotalsubtotal(Math.round(totalsubtotal * 100.00) / 100.00);
			} else {
				// productForm.setTotalReceived("0.00");
				productForm.setTotal_amount(0.00);
				productForm.setTotalvat(0.00);
				productForm.setTotalsubtotal(0.00);
			}

			productForm.setLocation(location);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

			productForm.setIsfrombincard(1);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "productReceivedReport";
	}

	public String salereport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			// PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();

			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = productForm.getLocation();
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			productForm.setLocation(location);
			productForm.setIsfrombincard(1);

			// String paymode=productForm.getPaymode();
			// if(paymode==null){
			// paymode="";
			// }
			// productForm.setPaymode(paymode);
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Sale Report", loginInfo.getUserId(), fromdate, todate, date,
					"salereport");

			int count = inventoryProductDAO.getCountSaleReturnReport(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype());
			pagination.setPreperties(count);
			ArrayList<Product> salesReturnProduct = inventoryProductDAO.getSaleReturnReport(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype(), pagination);
			pagination.setPage_records(salesReturnProduct.size());
			productForm.setTotalRecords(count);
			int size = salesReturnProduct.size() ;
			if(size > 0) {
			 productForm.setTtldisc(salesReturnProduct.get(salesReturnProduct.size() - 1).getTotaldiscount());
			}
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			/*
			 * int size= salesReturnProduct.size(); if(size>0){ double payment=
			 * salesReturnProduct.get(size-1).getPayment();
			 * productForm.setTotalReceived(DateTimeUtils.changeFormat(payment))
			 * ; } else { productForm.setTotalReceived("0.00"); }
			 */
			// for getting all total after adding pagination
			int payment = inventoryProductDAO.getTotalBillByBillDate(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype());
			productForm.setTotalReceived("" + payment);
			productForm.setSalesReturnProduct(salesReturnProduct);
			//productForm.setTotaldebit(salesReturnProduct.get(salesReturnProduct.size() - 1).getTotaldebit());
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			if(size > 0) {
			productForm.setFinaldeciamount(
					(int) salesReturnProduct.get(salesReturnProduct.size() - 1).getTotaldecimalbillamount());
			productForm.setGstamt(String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(
					Double.parseDouble((salesReturnProduct.get(salesReturnProduct.size() - 1).getGstamount()))))));
			productForm.setTotaldebit(salesReturnProduct.get(salesReturnProduct.size() - 1).getTotaldebit());
			}
			setLetterData(location,connection);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "salereport";
	}

	private void setLetterData(String location,Connection connection) {
		try {
			location = DateTimeUtils.isNull(location);
			if (location.equals("32") || location.equals("33") || location.equals("34")) {
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
				productForm.setClinicName(userProfile.getClinicname());
				productForm.setClinicaddress(userProfile.getAddress());
				productForm.setLandLine(userProfile.getPhone());
				productForm.setFullname(userProfile.getFullname());
				productForm.setEmail(userProfile.getEmail());
				productForm.setPlace(userProfile.getCity());
				productForm.setWebsiteUrl(userProfile.getWebsite());
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				productForm.setClinicLogo(clinic.getUserImageFileName());
			} else {
				// letter head
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				productForm.setClinicName(clinic.getClinicName());
				productForm.setClinicOwner(clinic.getClinicOwner());
				productForm.setOwner_qualification(clinic.getOwner_qualification());
				productForm.setLocationAdressList(locationAdressList);
				// procurementForm.setAddress(clinic.getAddress());
				productForm.setLandLine(clinic.getLandLine());
				productForm.setClinicemail(clinic.getEmail());
				productForm.setWebsiteUrl(clinic.getWebsiteUrl());
				productForm.setClinicLogo(clinic.getUserImageFileName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String transferdashboard() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String location = "0";
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Indent Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Indent Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("transferdashboardProduct");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				// location=(String) session.getAttribute("location");
				boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
				if (ispharmacist) {
					location = priscription.getLocation();
				} else {
					location = inventoryProductDAO.getHISUserLocation(userid);
				}
			} else {
				boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
				if (ispharmacist) {
					location = priscription.getLocation();
				} else {
					location = inventoryProductDAO.getHISUserLocation(userid);
				}
			}

			if (location == null) {
				location = "0";
			}
			session.setAttribute("location", location);
			String filter_status = productForm.getFilter_status();
			String searchtext = productForm.getSearchtext();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String filter_bydate = productForm.getFilter_bydate();
			String department=DateTimeUtils.isNull(productForm.getSpecialityName());

			if (searchtext != null) {
				if (searchtext.equals("")) {
					searchtext = null;
				}
			}

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "10";
				}
			} else {
				filter_status = "10";
			}
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
			String location1 = "";
			if (ispharmacist) {
				// Priscription priscription1 =
				// pharmacyDAO.getPharacyUsrLInfo(userid);
				location1 = priscription.getLocation();
			} else {
				if (loginInfo.getJobTitle().equals("Admin")) {
					location1 = "0";
				} else {
					location1 = inventoryProductDAO.getHISUserLocation(userid);
				}
			}
			if (location1 == null) {
				location1 = "0";
			}
			String location2 = location1;
			String indentloc = userProfileDAO.getIndentApprovelocations(userid);
			String locatioon = "";
			if (indentloc == null) {
				locatioon = location1;
			} else if (indentloc.equals("")) {
				locatioon = location1;
			} else {
				if (loginInfo.getJobTitle().equals("Admin") || loginInfo.getSuperadminid() == 1) {
					locatioon = "0";
				} else {
					locatioon = indentloc;
				}
			}

			boolean flag = false;
			if (!location1.equals("0")) {
				flag = indentDAO.checkLocationInWarehouseid(location1);
			}

			String indentapprove = "0";
			/*if (priscription.getIndent_approve() != null) {
				if (priscription.getIndent_approve().equals("1")) {
					indentapprove = "1";
				}
			}*/
			if (loginInfo.getSuperadminid() == 1 || loginInfo.isIndent_self_approve() || loginInfo.isIndent_all_approve()) {
				indentapprove = "1";
			}
			//Coder- Show department filter to only super admin, warehouse user and all department approve user
			int haveAllDeptAccess=0;
			if (loginInfo.getSuperadminid() == 1 || flag || loginInfo.isIndent_all_approve()) {
				haveAllDeptAccess =1;
			}
			productForm.setHaveAllDeptAccess(haveAllDeptAccess);

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {

				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					// fromdate = null;
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
					// todate = null;
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}
			if (filter_bydate != null) {
				if (filter_bydate.equals("")) {
					filter_bydate = "0";
				}
			} else {
				filter_bydate = "0";
			}

			if (priscription.getShowindentreq() > 0) {
				if (locatioon.equals("0")) {
					priscription.setShowindentreq(0);
				}
			}

			int count = inventoryProductDAO.getCountTransferDashboard(searchtext, fromdate, todate, locatioon,
					filter_status, flag, indentapprove, filter_bydate, priscription.getShowindentreq(),department,loginInfo.isLmh());
			pagination.setPreperties(count);
			// int result =
			// inventoryProductDAO.checkForProcurmentItemReturn(searchtext,fromdate,todate,location1,filter_status,pagination);

			ArrayList<Product> parenttransferlist = inventoryProductDAO.getParentProductTransferData(searchtext,
					fromdate, todate, locatioon, filter_status, pagination, flag, indentapprove, filter_bydate,
					priscription.getShowindentreq(),department,loginInfo.isLmh());
			pagination.setPage_records(parenttransferlist.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			int indentid = inventoryProductDAO.getTotalIdentCount();
			productForm.setParenttransferlist(parenttransferlist);

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];

			// productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			productForm.setDate(DateTimeUtils.getCommencingDate1(date));
			productForm.setLocation(pharmacyDAO.getLocationName(location2));
			productForm.setUserid(loginInfo.getUserId());
			indentid++;
			productForm.setIndentid(String.valueOf(indentid));
			productForm.setFilter_status(filter_status);
			productForm.setCurr_location(location2);
			productForm.setCancel_indent(priscription.getCancel_indent());
			productForm.setIsindentapprove(indentapprove);

			if (flag == true) {
				productForm.setIsstoreuser("1");
			} else {
				productForm.setIsstoreuser("0");
			}

			ArrayList<Product> requestparenttransferlist = new ArrayList<Product>();
			ArrayList<Product> aproveparenttransferlist = new ArrayList<Product>();
			int warehoseshelfrequest = 0;
			if (flag) {
				aproveparenttransferlist = inventoryProductDAO.getApproveParentProductTransferData(searchtext,
						locatioon,department);
				if (!indentapprove.equals("1")) {
					warehoseshelfrequest = 1;
					//   05-02-2020
					String fromdate1 = "";
					if(fromdate.equals(todate)){
						DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal1 = Calendar.getInstance();
						cal1.add(Calendar.DATE, -7);
						fromdate1 = dateFormat1.format(cal1.getTime());
					}else{
						fromdate1 =  DateTimeUtils.getCommencingDate1(fromdate);
					}
					String todate1 = DateTimeUtils.getCommencingDate1(todate);
					if (searchtext != null) {
						if (fromdate != null) {
							fromdate = DateTimeUtils.getCommencingDate1(fromdate);
						}

						if (todate != null) {
							todate = DateTimeUtils.getCommencingDate1(todate);
						}
						fromdate1 = fromdate;
						todate1 = todate;
					}
					int forwarehouseonly = 1;
					requestparenttransferlist = inventoryProductDAO.getRequestParentProductTransferData(searchtext,
							locatioon, fromdate1, todate1, forwarehouseonly,false,department,loginInfo.isLmh());

				}
			}
			productForm.setWarehoseshelfrequest(warehoseshelfrequest);
			if (indentapprove.equals("1")) {
				String fromdate1 = "";
				if(fromdate.equals(todate)){
					DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal1 = Calendar.getInstance();
					cal1.add(Calendar.DATE, -7);
					fromdate1 = dateFormat1.format(cal1.getTime());
				}else{
					fromdate1 =  DateTimeUtils.getCommencingDate1(fromdate);
				}
				String todate1 = DateTimeUtils.getCommencingDate1(todate);
				if (searchtext != null) {
					if (fromdate != null) {
						fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					}

					if (todate != null) {
						todate = DateTimeUtils.getCommencingDate1(todate);
					}
					fromdate1 = fromdate;
					todate1 = todate;
				}
				int forwarehouseonly = 0;
				boolean multipleApprove = false;
				
				if (loginInfo.getSuperadminid() == 1 || loginInfo.isIndent_all_approve()) {
					multipleApprove = true;
				}
				requestparenttransferlist = inventoryProductDAO.getRequestParentProductTransferData(searchtext,
						locatioon, fromdate1, todate1, forwarehouseonly,multipleApprove,department,loginInfo.isLmh());
			}

			productForm.setRequestparenttransferlist(requestparenttransferlist);
			productForm.setAproveparenttransferlist(aproveparenttransferlist);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListRequest();
			productForm.setWarehouseList(warehouseList);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ArrayList<Master>list=masterDAO.getdepartment();
			productForm.setDisciplineList(list);
			productForm.setIndentapprove(indentapprove);
			productForm.setSpecialityName(department);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferdashboard";
	}

	public String showtransferdmedicine() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			ArrayList<Product> childtransferlist = inventoryProductDAO.getChildTranfserData(parentid);
			Product product3 = inventoryProductDAO.getChildTransferCal(parentid);
			StringBuilder builder = new StringBuilder();

			builder.append("" + product.getFrom_location() + "");
			builder.append("~");

			builder.append("" + product.getRequest_date() + "");
			builder.append("~");

			builder.append("" + product.getTo_location() + "");
			builder.append("~");
			int i = 1;
			for (Product product2 : childtransferlist) {
				builder.append("<tr>");
				builder.append("<td>" + i + "</td>");
				String hsnno = "";
				if (product2.getHsnno() != null) {
					hsnno = product2.getHsnno();
				}
				builder.append("<td>" + hsnno + "</td>");
				builder.append("<td>" + product2.getProduct_name() + "</td>");
				builder.append("<td>" + product2.getBatch_no() + "</td>");
				builder.append("<td>" + product2.getExpiry_date() + "</td>");
				builder.append("<td>" + product2.getStock() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getSale_price() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getAmountno() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getMrp() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getAmountmrp() + "</td>");
				builder.append("</tr>");
			}
			builder.append("<tr>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>GRAND TOTAL</td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>" + product3.getTotal_amount()
					+ "</td>");
			builder.append("<td></td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>" + product3.getTotolmrp()
					+ "</td>");
			builder.append("</tr>");

			builder.append("~");
			builder.append("" + parentid + "");

			builder.append("~");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			builder.append("<h4>" + userProfile.getClinicname() + "</h4>");
			builder.append("<h5>" + userProfile.getAddress() + "</h5>");
			builder.append("<h5>Website:" + userProfile.getWebsite() + ", Email:" + userProfile.getEmail()
					+ ", Contact : " + userProfile.getPhone() + "</h5>");

			String userid = loginInfo.getUserId();
			int selectedid = loginInfo.getId();
			// UserProfile userProfile2
			// =userProfileDAO.getUserprofileDetails(selectedid);
			// String username = userProfile2.getInitial()+" "+
			// userProfile2.getFirstname()+" "+ userProfile2.getLastname();
			String datetime = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			Calendar cal = Calendar.getInstance();
			datetime = dateFormat.format(cal.getTime());
			builder.append("~");
			builder.append("" + userid + "");
			builder.append("~");
			builder.append("" + datetime + "");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String showrequestedmedicinefraprove() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String parentid = request.getParameter("parentid");
			String val = request.getParameter("val");
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			ArrayList<Product> childtransferlist = inventoryProductDAO.getChildRequestedTempData(parentid);
			Product product3 = inventoryProductDAO.getChildRequestTempCal(parentid);
			StringBuilder builder = new StringBuilder();

			builder.append("" + product.getFrom_location() + "");
			builder.append("~");

			builder.append("" + product.getRequest_date() + "");
			builder.append("~");

			builder.append("" + product.getTo_location() + "");
			builder.append("~");
			int i = 1;
			for (Product product2 : childtransferlist) {
				builder.append("<tr>");
				builder.append("<td>" + i + "</td>");
				String hsnno = "";
				if (product2.getHsnno() != null) {
					hsnno = product2.getHsnno();
				}
				builder.append("<td>" + hsnno + "</td>");
				builder.append("<td>" + product2.getProduct_name() + "</td>");
				builder.append("<td>" + product2.getBatch_no() + "</td>");
				builder.append("<td>" + product2.getExpiry_date() + "</td>");
				builder.append("<td>" + product2.getStock() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getSale_price() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getAmountno() + "</td>");
				builder.append("<td style='text-align: right;' class='hidden'>" + product2.getMrp() + "</td>");
				builder.append("<td style='text-align: right;'>" + product2.getAmountmrp() + "</td>");
				builder.append("<td style='text-align: right;'>"
						+ pharmacyDAO.getPharmacyLocation(product2.getOld_location()) + "</td>");
				builder.append("</tr>");
			}
			builder.append("<tr>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>GRAND TOTAL</td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>" + product3.getTotal_amount()
					+ "</td>");
			builder.append("<td></td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>" + product3.getTotolmrp()
					+ "</td>");
			builder.append("<td></td>");
			builder.append("</tr>");

			builder.append("~");
			builder.append("" + parentid + "");

			builder.append("~");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			builder.append("<h4>" + userProfile.getClinicname() + "</h4>");
			builder.append("<h5>" + userProfile.getAddress() + "</h5>");
			builder.append("<h5>Website:" + userProfile.getWebsite() + ", Email:" + userProfile.getEmail()
					+ ", Contact : " + userProfile.getPhone() + "</h5>");

			String userid = loginInfo.getUserId();
			int selectedid = loginInfo.getId();
			// UserProfile userProfile2
			// =userProfileDAO.getUserprofileDetails(selectedid);
			// String username = userProfile2.getInitial()+" "+
			// userProfile2.getFirstname()+" "+ userProfile2.getLastname();
			String datetime = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			Calendar cal = Calendar.getInstance();
			datetime = dateFormat.format(cal.getTime());
			builder.append("~");
			builder.append("" + userid + "");
			builder.append("~");
			builder.append("" + datetime + "");

			builder.append("~");
			if (val.equals("1")) {
				builder.append("Processing");
				// builder.append("<input type='button'
				// onclick='printDiv('page_printer');' class='btn btn-primary'
				// value='Print'>");
			} else if (val.equals("4")) {
				builder.append("Admin Rejected");
			} else if (val.equals("5")) {
				builder.append("Head Rejected");
			} else if (val.equals("3")) {
				builder.append("Completed");
			} else if (val.equals("2")) {
				builder.append("Admin Accepted");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String updeletereport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String filter = productForm.getFilter();

			if (filter == null) {
				filter = "0";
			}

			if (filter.equals("")) {
				filter = "0";
			}

			if (fromdate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());
			} else if (fromdate.equals("")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());
			} else {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}
			if (todate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				todate = dateFormat.format(calendar.getTime());
			} else if (todate.equals("")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				todate = dateFormat.format(calendar.getTime());
			} else {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			//   28 sep 2017 pagination code

			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Update Cancelled Report", loginInfo.getUserId(), fromdate,
					todate, date, "updeletereport");
			int count = inventoryProductDAO.getCountDelUpdateReportList(fromdate, todate, location, filter);
			pagination.setPreperties(count);
			ArrayList<Product> updelListReport = inventoryProductDAO.getDelUpdateReportList(fromdate, todate, location,
					filter, pagination);
			productForm.setUpdelListReport(updelListReport);
			pagination.setPage_records(updelListReport.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "updeletereport";
	}

	public String showrequestedmedicine() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String parentid = request.getParameter("parentid");
			String val = request.getParameter("val");
			String userid = loginInfo.getUserId();
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			//   new table code 23 oct 2017
			/*
			 * ArrayList<Product> childtransferlist =
			 * inventoryProductDAO.getChildTranfserData(parentid);
			 */

			ArrayList<Product> childtransferlist = indentDAO.getChildTranfserData(parentid);
			Priscription priscription2 = pharmacyDAO.getPharacyUsrLInfo(userid);
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(product.getUserid());
			StringBuilder builder = new StringBuilder();

			builder.append("" + product.getFrom_location() + "");
			builder.append("~");

			builder.append("" + product.getRequest_date() + "");
			builder.append("~");

			builder.append("" + product.getTo_location() + "");
			builder.append("~");
			int i = 0;
			double limit_qty=0;
			for (Product product2 : childtransferlist) {
				double stor_avail_qty = indentDAO.getAllAvailableStockByCatId(product2.getCatalogueid(),
						DateTimeUtils.numberCheck(product.getWarehouse_id()));
				
				builder.append("<tr>");
				builder.append("<input type='hidden' class='indnetclass' value='" + product2.getId() + "' />");
				builder.append("<td>" + (i + 1) + " <input type='hidden' name='products[" + i + "].id' value='"
						+ product2.getId() + "' /> </td>");
				builder.append("<td class='hidden'>" + product2.getProduct_id() + "</td>");
				builder.append("<td>" + product2.getProduct_name() + "</td>");
				// builder.append("<td><input type='text' readonly='readonly'
				// class='form-control'
				// value='"+product2.getPurchase_price()+"'/></td>");
				builder.append("<td style='text-align: center;'>" + stor_avail_qty + "</td>");
				builder.append("<td style='text-align: center;'>" + product2.getAvail_qty() + "</td>");
				// if(priscription2.getLocation().equals("32")){
				if (priscription2.getLocation().equals("" + product.getWarehouse_id() + "")) {
					builder.append("<td><input type='number' class='form-control' readonly='readonly' name='products["
							+ i + "].qty' placeholder='enter required qty' value='" + product2.getStock()
							+ "' id='reqqty" + product2.getId() + "' /></td>");
				} else {
					builder.append("<td><input type='number' class='form-control' name='products[" + i
							+ "].qty' placeholder='enter required qty' value='" + product2.getStock() + "' id='reqqty"
							+ product2.getId() + "' /></td>");
				}
				builder.append("<td><input type='number' class='form-control'  name='products["
						+ i + "].Req_limit_qty' placeholder='enter extra qty' onchange=updatereqqtylimit("+product2.getId()+") value='" + product2.getReq_limit_qty()
						+ "' id='reqlimitqty" + product2.getId() + "' /></td>");
				
				if (loginInfo.getUserType() == 2) {
					builder.append("<input type='hidden' class='form-control' name='products[" + i
							+ "].comment' placeholder='enter comment' value='" + product2.getComment() + "' id='comment"
							+ product2.getId() + "' />");
					builder.append("<td>" + product2.getComment() + "</td>");
				} else if (priscription2.getLocation().equals("" + product.getWarehouse_id() + "")) {
					builder.append("<input type='hidden' class='form-control' name='products[" + i
							+ "].comment' placeholder='enter comment' value='" + product2.getComment() + "' id='comment"
							+ product2.getId() + "' />");
					builder.append("<td>" + product2.getComment() + "</td>");
				} else {
					builder.append("<td><input type='text' class='form-control' name='products[" + i
							+ "].comment' placeholder='enter comment' value='" + product2.getComment() + "' id='comment"
							+ product2.getId() + "' /></td>");
				}

				if (priscription2.getLocation().equals("" + product.getWarehouse_id() + "")) {
					builder.append("<td class='hidden-print'></td>");
				} else {
					builder.append("<td class='hidden-print'><a href='#' onclick=deleteRow(this,'mytable',"
							+ product2.getId() + ") ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
				}

				builder.append("</tr>");
				i++;

			}

			builder.append("~");
			builder.append("" + parentid + "");

			builder.append("~");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			builder.append("<h4>" + userProfile.getClinicname() + "</h4>");
			builder.append("<h5>" + userProfile.getAddress() + "</h5>");
			builder.append("<h5>Website:" + userProfile.getWebsite() + ", Email:" + userProfile.getEmail()
					+ ", Contact : " + userProfile.getPhone() + "</h5>");

			// int selectedid = loginInfo.getId();
			// UserProfile userProfile2
			// =userProfileDAO.getUserprofileDetails(selectedid);
			// String username = userProfile2.getInitial()+" "+
			// userProfile2.getFirstname()+" "+ userProfile2.getLastname();

			String datetime = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			Calendar cal = Calendar.getInstance();
			datetime = dateFormat.format(cal.getTime());
			builder.append("~");
			Priscription priscription3 = pharmacyDAO.getPharacyUsrLInfo(userid);
			if (priscription3.getFullname() != null) {
				builder.append("" + priscription3.getFullname() + "");
			} else {
				builder.append("" + userid + "");
			}

			builder.append("~");
			builder.append("" + datetime + "");
			String indentapprove = "";
			builder.append("~");
			if (val.equals("0")) {
				if(loginInfo.getSuperadminid()==1 || loginInfo.isIndent_self_approve() || loginInfo.isIndent_all_approve()){
					indentapprove="1";
				}
				if (indentapprove.equals("1")) {
					builder.append(
							"<input type='button'  class='btn btn-success' value='APPROVED' onclick='updateapproveindent()'>");
					builder.append(
							"<input type='button'  class='btn btn-danger' value='REJECT' onclick='rejectTransferLog()'>");
				} else {
					builder.append(
							"<input type='button'  class='btn btn-primary hidden-print' value='UPDATE' onclick='updateTransferLog()'>");
					builder.append(
							"<input type='button' onclick=printDiv2('page_printer3') class='btn btn-warning hidden-print' value='PRINT'>");
				}
			} else if (val.equals("2")) {
				builder.append("User ID: " + product.getAdmin_approve_userid() + "");
				builder.append("Admin Rejected");
			} else if (val.equals("1")) {
				if (priscription2.getCheck_stock_available() == null) {
					priscription2.setCheck_stock_available("");
				}
				if (loginInfo.getUserType() == 2 || priscription2.getCheck_stock_available().equals("1")) {
					builder.append(
							"<input type='button'  class='btn btn-success' value='CHECK AVAILABILITY' onclick='showCheckAvailabilityPopUp("
									+ parentid + ")'>");
				} else {
					builder.append("processing");
				}
			} else if (val.equals("3")) {
				if (priscription.getRequisition_auth() == null) {
					priscription.setRequisition_auth("");
				}
				if (loginInfo.getUserType() == 2 || priscription.getRequisition_auth().equals("1")) {
					builder.append(
							"<input type='button'  class='btn btn-success' value='ACCEPT' onclick='finalaproveTransferLog()'>");
					// builder.append("<input type='button' class='btn
					// btn-primary' value='Received'
					// onclick='finalrejectTransferLog()'>");
				} else {
					builder.append(
							"<input type='button' onclick='printDiv2('page_printer3');' class='btn btn-primary' value='PRINT'>");
				}
			} else if (val.equals("4")) {
				builder.append("Completed");
			}

			builder.append("~");
			if (priscription.getFullname() != null) {
				builder.append("" + priscription.getFullname() + "");
			} else {
				builder.append("" + product.getUserid() + "");
			}

			builder.append("~");
			if (val.equals("0")) {
				builder.append(" ");
			} else if (val.equals("2")) {
				builder.append("" + product.getAdmin_notes() + "");
			} else if (val.equals("1")) {
				builder.append(" ");
			} else if (val.equals("3")) {
				builder.append(" ");
			} else if (val.equals("4")) {
				builder.append(" ");
			} else {
				builder.append(" ");
			}
			if (product.getAdmin_notes() == null) {
				product.setAdmin_notes("");
			}
			builder.append("~" + product.getAdmin_notes() + "~" + product.getIndent());

			builder.append("~");
			if (val.equals("0")) {
				if (loginInfo.getUserType() == 2) {
					builder.append(" ");
				} else {
					builder.append("Received By");
				}
			} else if (val.equals("2")) {
				builder.append("Received By");
			} else if (val.equals("1")) {
				builder.append("Received By");
			} else if (val.equals("3")) {
				builder.append("Received By");
			} else if (val.equals("4")) {
				builder.append("Received By");
			} else {
				builder.append("Received By");
			}

			builder.append("~");
			if (val.equals("0")) {
				if (DateTimeUtils.isNull(indentapprove).equals("1")) {
					builder.append(
							"<textarea rows='3'  placeholder='Write Note' class='form-control' id='notes' name='notes'></textarea>");
				} else {
					builder.append("<input type='hidden' id='notes' name='notes'> ");
				}
			} else if (val.equals("2")) {
				builder.append(
						"<textarea rows='3'  placeholder='Write Note' class='form-control' id='notes' name='notes'></textarea>");
			} else if (val.equals("1")) {
				builder.append(
						"<textarea rows='3'  placeholder='Write Note' class='form-control' id='notes' name='notes'></textarea>");
			} else if (val.equals("3")) {
				builder.append(
						"<textarea rows='3'  placeholder='Write Note' class='form-control' id='notes' name='notes'></textarea>");
			} else if (val.equals("4")) {
				builder.append(
						"<textarea rows='3'  placeholder='Write Note' class='form-control' id='notes' name='notes'></textarea>");
			} else {
				builder.append(
						"<textarea rows='3'  placeholder='Write Note' class='form-control' id='notes' name='notes'></textarea>");
			}
			builder.append("~");
			int xx = 0;
			ArrayList<Product> arrayList = indentDAO.getchildDeleteList(parentid);
			if (arrayList.size() == 0) {
				builder.append("hideit");
			} else {
				for (Product product2 : arrayList) {
					builder.append("<tr>");
					builder.append("<td>" + (xx + 1) + " </td>");
					builder.append("<td>" + product2.getProd_name() + "</td>");
					builder.append("<td>" + product2.getQty() + "</td>");
					builder.append("<td>" + product2.getUserid() + "</td>");
					builder.append("<td>" + product2.getDateTime() + "</td>");
					builder.append("</tr>");
				}
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String getAllAvailableMedicine() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String parentid = request.getParameter("parentid");
			session.setAttribute("reqparentid1", parentid);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ArrayList<Product> arrayList = inventoryProductDAO.getChildTranfserData(parentid);
			ArrayList<String> arrayList2 = new ArrayList<String>();
			for (Product product : arrayList) {
				String pname = product.getProduct_name();
				arrayList2.add(pname);
			}
			Product product = inventoryProductDAO.getParentTransferData(parentid);

			String fromlocation = product.getLocation();
			ArrayList<Product> productList = inventoryProductDAO.getReqTransAllProdfrCatalogue(null, arrayList2,
					fromlocation);

			ArrayList<Product> childtransferlist = inventoryProductDAO.getChildTranfserData(parentid);

			StringBuilder builder = new StringBuilder();

			int i = 1;
			for (Product product2 : childtransferlist) {
				builder.append("<tr>");
				builder.append("<td>" + i + "</td>");
				i++;
				builder.append("<td>" + product2.getProduct_id() + "</td>");
				builder.append("<td>" + product2.getProduct_name() + "</td>");
				builder.append("<td>" + product2.getStock() + "</td>");
				builder.append("</tr>");
			}

			builder.append("~");
			builder.append("" + parentid + "");

			builder.append("~");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			builder.append("<h4>" + userProfile.getClinicname() + "</h4>");
			builder.append("<h5>" + userProfile.getAddress() + "</h5>");
			builder.append("<h5>Website:" + userProfile.getWebsite() + ", Email:" + userProfile.getEmail()
					+ ", Contact : " + userProfile.getPhone() + "</h5>");

			builder.append("~");
			for (Product product3 : productList) {
				builder.append("<tr>");
				builder.append("<td><input type='checkbox' class='case' value='" + product3.getId()
						+ "' class='form-control' /></td>");
				builder.append("<td>" + product3.getId() + "</td>");
				builder.append("<td>" + product3.getProduct_name() + "</td>");
				builder.append("<td>" + product3.getHsnno() + "</td>");
				builder.append("<td>" + product3.getExpiry_date() + "</td>");
				builder.append("<td>" + product3.getBatch_no() + "</td>");
				builder.append("<td>" + product3.getStock() + "</td>");
				builder.append("</tr>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String addToTransferTemp() {
		if (!verifyLogin(request)) {
			return "login";
		}
		try {
			String id = request.getParameter("data");
			String parentid = request.getParameter("parentid");
			StringBuilder builder = new StringBuilder();
			String prodid = "0";
			int count = 0;
			/*
			 * if(session.getAttribute("prodidname1")!=null){ prodid = (String)
			 * session.getAttribute("prodidname1"); for(String
			 * t:prodid.split(",")){ if(t.equals("0")){ continue; } count++; } }
			 */
			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				prodid = prodid + "," + s;
				count++;
			}
			session.setAttribute("prodidname1", prodid);
			// String data = "<a href='#' data-toggle='modal'
			// onclick='showCartPopUp()'><span style='background-color:
			// brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "+count+"
			// </span></a>";
			session.setAttribute("tcount", "" + count);
			builder.append("" + prodid + "");
			builder.append("" + parentid + "");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String transferTempory() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String prodid = request.getParameter("prodid");
			// String parentid = request.getParameter("parentid");

			StringBuilder builder = new StringBuilder();
			int i = 0;
			int j = 1;

			String parentid = (String) session.getAttribute("reqparentid1");
			Product product2 = inventoryProductDAO.getParentTransferData(parentid);
			String fromlocation = product2.getFrom_location();

			// if(session.getAttribute("prodidname")!=null){
			// String prodid = (String) session.getAttribute("prodidname");
			for (String mdicinenameid : prodid.split(",")) {
				if (mdicinenameid.equals("0")) {
					continue;
				}
				Product product = inventoryProductDAO.getProduct(mdicinenameid);

				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}

				String location = pharmacyDAO.getLocationName(product.getLocation());

				builder.append("<tr>");
				builder.append("<td>" + j + "</td/>");
				builder.append("<input type='hidden' name='allproduct[" + i + "].product_id' id='product_id" + i
						+ "' value='" + i + "'>");
				builder.append("<input type='hidden' name='allproduct[" + i + "].id' id='id" + i + "' value='"
						+ mdicinenameid + "'>");
				builder.append("<input type='hidden' name='allproduct[" + i + "].location' id='location" + i
						+ "' value='" + product.getLocation() + "'>");
				builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct[" + i
						+ "].hsnno' id='hsnno" + i + "' value='" + hsnno + "' ></td>");
				builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct[" + i
						+ "].product_name' id='product_name" + i + "' value='" + product.getProduct_name()
						+ "' ></td>");
				builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct[" + i
						+ "].batch_no' id='batch_no" + i + "' value='" + product.getBatch_no() + "' ></td>");
				builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct[" + i
						+ "].expiry_date' id='expiry_date" + i + "' value='" + product.getExpiry_date() + "' ></td>");
				builder.append("<td><input type='text' readonly='readonly' class='form-control' name='allproduct[" + i
						+ "].loc' id='loc" + i + "' value='" + location + "'></td>");
				builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct[" + i
						+ "].stock' id='stock" + i + "' value='" + product.getStock() + "' ></td>");
				builder.append("<td><input type='text' readonly='readonly' class='form-control' name='allproduct[" + i
						+ "].tlocation' id='tlocation" + i + "' value='" + fromlocation + "'></td>");
				builder.append("<td><input type='text' class='form-control' name='allproduct[" + i + "].tqty' id='tqty"
						+ i + "' ></td>");
				builder.append("<td><i onclick='deleteCartProductTemp(" + i
						+ ")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
				builder.append("</tr>");
				i++;
				j++;
			}
			// }
			builder.append("~");
			builder.append("" + j + "");

			builder.append("~");
			ArrayList<Product> childtransferlist = inventoryProductDAO.getChildTranfserData(parentid);

			int k = 1;
			for (Product product5 : childtransferlist) {
				builder.append("<tr>");
				builder.append("<td>" + k + "</td>");
				k++;
				builder.append("<td>" + product5.getProduct_id() + "</td>");
				builder.append("<td>" + product5.getProduct_name() + "</td>");
				builder.append("<td>" + product5.getStock() + "</td>");
				builder.append("</tr>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String updatefinalAproveTransferLog() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");

			ArrayList<Product> arrayList = pharmacyDAO.getChildTempReqData(parentid);
			// int res = pharmacyDAO.deleteTempRequestedData(parentid);
			for (Product product : arrayList) {
				int result = pharmacyDAO.transferRequestedMedicine(parentid, product);
			}
			String userid = loginInfo.getUserId();
			int result1 = pharmacyDAO.updateAproveTransferLog(parentid, "4", "0", userid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String setmedicineproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String category = request.getParameter("category");
			String medicinetype = request.getParameter("medicinetype");
			ArrayList<Product> list = inventoryProductDAO.getMedinceListofType(medicinetype, category);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<label>Select Product</label>");
			buffer.append("<select id='product_id' class='form-control chosen-select chosen' name='product_id' >");
			buffer.append("<option value='0'>Select Product</option>");
			for (Product product : list) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addnewindent() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String cataloguid = request.getParameter("pid");
			String qty = request.getParameter("qty");
			String count = request.getParameter("count");
			String data = request.getParameter("data");
			String warehouse_id = request.getParameter("warehouse_id");
			if (data == null) {
				data = "0";
			}
			if (data.equals("")) {
				data = "0";
			}
			int index = Integer.parseInt(count);
			index--;
			String exp_date = request.getParameter("exp_date");
			String userid = loginInfo.getUserId();
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
			String location = "";
			if (ispharmacist) {
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				location = priscription.getLocation();
			} else {
				location = inventoryProductDAO.getHISUserLocation(userid);
			}

			if (location == null) {
				location = "0";
			}

			StringBuffer buffer = new StringBuffer();

			Product product = inventoryProductDAO.getProductCatalogueDetails(cataloguid);
			double limit_qty=0;
			if(loginInfo.isKalmegha()) {
				String description=inventoryProductDAO.getdescription(loginInfo.getId());
				limit_qty=inventoryProductDAO.getqtyProductLimit(cataloguid,description);
			}
			// String
			// pid=inventoryProductDAO.getProdidFromCatagoryid(cataloguid);
			String pid = cataloguid;
			boolean flag = false;
			for (String t : data.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				if (t.equals(pid)) {
					flag = true;
					continue;
				}
			}
			if (location.equals("")) {
				buffer.append("1");
			} else if (!flag) {
				// int avail_qty =
				// inventoryProductDAO.getAllAvailableStock(product.getProduct_name(),location);
				double avail_qty = indentDAO.getAllAvailableStockByCatId(cataloguid, location);
				// int stor_avail_qty =
				// indentDAO.getAllAvailableStockByCatId(cataloguid,DateTimeUtils.numberCheck(warehouse_id));

				/*
				 * buffer.append("<tr>"); buffer.append("<td>"+count+"</td>");
				 * buffer.append("<td>"+product.getProduct_name()
				 * +" <input type='hidden' value='"+pid+"' name='products["
				 * +index+"].product_id' /> </td>");
				 * buffer.append("<td>"+avail_qty+"<input type='hidden' value='"
				 * +avail_qty+"' name='products["+index+"].avail_qty' /></td>");
				 * buffer.append("<td>"+qty+" <input type='hidden' value='"
				 * +qty+"' name='products["+index+"].qty' /></td>");
				 * buffer.append("<td>"+exp_date+" <input type='hidden' value='"
				 * +qty+"' name='products["+index+"].expectedDate' /></td>");
				 * buffer.
				 * append("<td><textarea class='form-control' name='products["
				 * +index+"].comment' rows='2' placeholder='Enter Comments' id='comment'></textarea></td>"
				 * ); buffer.
				 * append("<td><a href='#' onclick=deleteIndentRow(this) ><i class='fa fa-times fa-2x text-danger' ></i></a></td>"
				 * ); buffer.append("</tr>");
				 */

				buffer.append("<tr>");
				buffer.append("<td>" + count + " <input type='hidden' class='dclass' value='" + pid + "' /></td>");
				buffer.append("<td>" + product.getProduct_name() + " <input type='hidden' value='" + pid
						+ "' name='product_id" + pid + "' /> </td>");
				buffer.append("<td>" + avail_qty + "<input type='hidden' value='" + avail_qty + "' name='avail_qty"
						+ pid + "' /></td>");
				// buffer.append("<td>"+stor_avail_qty+"</td>");
				buffer.append("<td>" + qty + " <input type='hidden' value='" + qty + "' name='qty" + pid + "' /></td>");
				if(loginInfo.isKalmegha()) {
					buffer.append("<td>" + limit_qty + " <input type='hidden' value='" + limit_qty + "' name='limit" + pid + "' /></td>");
				}
				buffer.append("<td>" + exp_date + " <input type='hidden' value='" + qty + "' name='expectedDate" + pid
						+ "' /></td>");
				buffer.append("<td><textarea class='form-control' name='comment" + pid
						+ "' rows='2' placeholder='Enter Comments' id='comment" + pid + "'></textarea></td>");
				if(loginInfo.isKalmegha()) {
					buffer.append("<td>");
					//buffer.append("&nbsp;&nbsp;<input type='number' onchange='requestedlimitqty(this.value)' name='reqqty' id='reqqty' class='form-control' placeholder='Req Qty'>");
					buffer.append("&nbsp;&nbsp;<input type='number' name='reqqty" + pid + "' id='reqqty' class='form-control' placeholder='Req Qty'>");
					buffer.append("</td>");
					}
				buffer.append(
						"<td><a href='#' onclick=deleteIndentRow(this) ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
				buffer.append("</tr>");
			} else {
				buffer.append("0");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String saveindent() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			
			Product product = new Product();
			String qty = request.getParameter("qty");
			String expected_date = request.getParameter("expected_date");
			String userid = loginInfo.getUserId();
			
			int parentTransferId = DateTimeUtils.convertToInteger(request.getParameter("parentTransferId"));
			int indentModifyButton = DateTimeUtils.convertToInteger(request.getParameter("indentModifyButton"));
			
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
			String location = "";
			if (ispharmacist) {
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				location = priscription.getLocation();
			} else {
				location = inventoryProductDAO.getHISUserLocation(userid);
			}

			if (location == null) {
				location = "0";
			}
			if (location.equals("")) {
				return "transferToTansferDash";
			}
			if(!qty.equals("")) {
				product.setQty(Double.parseDouble(qty));
			}
			//product.setQty(Double.parseDouble(qty));
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			product.setExpectedDate(expected_date);
			product.setDate(date);
			product.setUserid(userid);
			product.setTime(time);
			product.setLocation(location);
			
			String warehouse_id = productForm.getWarehouse_id();
			product.setWarehouse_id(warehouse_id);

			int parentid = parentTransferId;
			if(parentTransferId==0){
				int count = inventoryProductDAO.getTotalIdentCount();
				product.setIndent(count + 1);
				parentid=inventoryProductDAO.saveRequestIndent(product);
			}
			
			String allproductid = request.getParameter("allproductid");
			String[] data = allproductid.split(",");

			for (String string : data) {
				if (string.equals("0")) {
					continue;
				}
				if(DateTimeUtils.convertToInteger(request.getParameter("indentChildId" + string))>0){
					continue;
				}
				
				Product master = new Product();
				master.setDate(date);
				master.setUserid(userid);
				master.setLocation(location);
				String qty2 = request.getParameter("qty" + string);
				String catalogueid = request.getParameter("product_id" + string);
				String comment = request.getParameter("comment" + string);
				String avail_qty = request.getParameter("avail_qty" + string);
				if(loginInfo.isKalmegha()) {
				 String limit_req_qty = request.getParameter("reqqty" + string);
				 String qty_limit = request.getParameter("limit" + string);
				 if(limit_req_qty.equals("")) {
					 limit_req_qty="0";
				 }
				 master.setReq_limit_qty((limit_req_qty));
				 String req_limit_date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				 master.setDateTime(req_limit_date_time);
				 master.setLimit_qty(Double.parseDouble(qty_limit));
				 String description=inventoryProductDAO.getdescription(loginInfo.getId());
				 master.setReq_dept_id(description);
				}
				if (avail_qty != null) {
					if (avail_qty.equals("")) {
						avail_qty = "0.0";
					}
				} else {
					avail_qty = "0.0";
				}
				master.setComment(comment);
				master.setQty(Double.parseDouble(qty2));
				master.setAvail_qty(Double.parseDouble(avail_qty));
				// String catid = indentDAO.getCatIdProdId(productid);
				master.setCatalogueid(catalogueid);
				String productid = indentDAO.getProdIdCatId(catalogueid);
				master.setProduct_id(productid);
				int res = inventoryProductDAO.saveChildRequestIndent(master, parentid);
			}
			if(indentModifyButton==2){
				int res = indentDAO.updateModifyConfirmStatus(parentid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferToTansferDash";
	}

	public String updaterequest() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
			for (Product product : productForm.getProducts()) {

				if (product == null) {
					continue;
				}
				if (product.getId() == 0) {
					continue;
				}

				int res = productDAO.updateRequestIndentChild(product);

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferToTansferDash";

	}

	public String deleteindentreq() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String id = request.getParameter("id");
			Product product = indentDAO.getChildProductFromChildId(id);
			int res = inventoryProductDAO.deleteIndentRequest(id);
			if (res > 0) {
				product.setUserid(loginInfo.getUserId());
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				product.setDateTime(datetime);
				int result = indentDAO.saveDeleteIndentCharge(product);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String updateapproveindent() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			String aprove = "1";
			String userid = loginInfo.getUserId();
			String notes = request.getParameter("notes");
			for (Product product : productForm.getProducts()) {

				if (product == null) {
					continue;
				}
				if (product.getId() == 0) {
					continue;
				}

				int res = productDAO.updateRequestIndentChild(product);

			}

			int result = pharmacyDAO.updateAproveTransferLog(parentid, "1", aprove, userid);
			result = pharmacyDAO.updateNotesofParent(parentid, notes);
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "transferToTansferDash";
	}

	public String deleteindentparent() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			int del = inventoryProductDAO.deleteIndentParent(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "transferToTansferDash";
	}

	public String lastRequestedPopup() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			String parentid = request.getParameter("parentid");
			String val = request.getParameter("val");
			session.setAttribute("reqparentid1", parentid);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ArrayList<Product> arrayList = inventoryProductDAO.getChildTranfserData(parentid);
			String userid = loginInfo.getUserId();
			Priscription priscription2 = pharmacyDAO.getPharacyUsrLInfo(userid);
			ArrayList<String> arrayList2 = new ArrayList<String>();
			for (Product product : arrayList) {
				String pname = product.getProduct_name();
				arrayList2.add(pname);
			}
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(product.getUserid());
			String fromlocation = product.getLocation();
			ArrayList<Product> productList = inventoryProductDAO.getReqTransAllProdfrCatalogue(null, arrayList2,
					fromlocation);
			ArrayList<Product> childtransferlist = inventoryProductDAO.getChildTranfserData(parentid);
			ArrayList<Product> childtempprodlist = inventoryProductDAO.getChildRequestedTempData(parentid);
			StringBuilder builder = new StringBuilder();

			builder.append("" + product.getFrom_location() + "");
			builder.append("~");

			builder.append("" + product.getRequest_date() + "");
			builder.append("~");

			int i = 1;
			for (Product product2 : childtransferlist) {
				builder.append("<tr>");
				builder.append("<td>" + i + "</td>");
				i++;
				builder.append("<td>" + product2.getProduct_id() + "</td>");
				builder.append("<td>" + product2.getProduct_name() + "</td>");
				builder.append("<td>" + product2.getStock() + "</td>");
				builder.append("<td>" + product2.getExpectedDate() + "</td>");
				builder.append("<td>" + product2.getComment() + "</td>");
				builder.append("</tr>");
			}

			builder.append("~");
			builder.append("" + parentid + "");

			builder.append("~");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			builder.append("<h4>" + userProfile.getClinicname() + "</h4>");
			builder.append("<h5>" + userProfile.getAddress() + "</h5>");
			builder.append("<h5>Website:" + userProfile.getWebsite() + ", Email:" + userProfile.getEmail()
					+ ", Contact : " + userProfile.getPhone() + "</h5>");

			int s = 1;
			builder.append("~");
			for (Product product3 : childtempprodlist) {
				builder.append("<tr>");
				builder.append("<td>" + s + "</td>");
				builder.append("<td>" + product3.getHsnno() + "</td>");
				builder.append("<td>" + product3.getProduct_name() + "</td>");
				builder.append("<td>" + product3.getBatch_no() + "</td>");
				builder.append("<td>" + product3.getExpiry_date() + "</td>");
				builder.append("<td>" + product3.getStock() + "</td>");
				builder.append("<td style='text-align: right;'>" + product3.getSale_price() + "</td>");
				builder.append("<td style='text-align: right;'>" + product3.getAmountno() + "</td>");
				builder.append("<td style='text-align: right;'>" + product3.getMrp() + "</td>");
				builder.append("<td style='text-align: right;'>" + product3.getAmountmrp() + "</td>");
				builder.append("<td>" + product3.getFrom_location() + "</td>");
				builder.append("</tr>");
				s++;
			}

			builder.append("~");
			if (val.equals("1")) {
				if (priscription2.getRequisition_auth() == null) {
					priscription2.setRequisition_auth("");
				}
				if (loginInfo.getUserType() == 2 || priscription2.getRequisition_auth().equals("1")) {
					builder.append(
							"<input type='button'  class='btn btn-primary' value='Receive' onclick='finalaproveTransferLog("
									+ parentid + ")'>");
					builder.append(
							"<input type='button' onclick=printDiv2('page_printer2') class='btn btn-primary' value='Print'/>");
				} else {
					builder.append(
							"<input type='button' onclick=printDiv2('page_printer2') class='btn btn-primary' value='Print'/>");
				}
			} else if (val.equals("2")) {
				builder.append(
						"<input type='button' onclick=printDiv2('page_printer2') class='btn btn-primary' value='Print'/>");
			}

			builder.append("~");
			builder.append("" + priscription.getFullname() + "");

			builder.append("~");
			builder.append("" + product.getIndent() + "");

			builder.append("~");
			Product product3 = inventoryProductDAO.getChildTransferTempCal(parentid);
			builder.append("<tr>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td></td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>GRAND TOTAL</td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>" + product3.getTotal_amount()
					+ "</td>");
			builder.append("<td></td>");
			builder.append("<td style='text-align: right;font-weight: bold;color: green;'>" + product3.getTotolmrp()
					+ "</td>");
			builder.append("</tr>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String inventoryOpeningClosing() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String fromDate = productForm.getFromdate();
			String toDate = productForm.getTodate();
			String searchbyprodname = productForm.getSearchbyprodname();
			String isfromcathlab = request.getParameter("isfromcathlab");
			String location_filter = productForm.getLocation_filter();
			String category_id = productForm.getCategory_id();
			String filter_status = productForm.getFilter_status();
			if (location_filter != null) {
				if (location_filter.equals("")) {
					location_filter = "0";
				}
			} else {
				location_filter = "0";
			}
			if (category_id != null) {
				if (category_id.equals("")) {
					category_id = "0";
				}
			} else {
				category_id = "0";
			}

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "1";
				}
			} else {
				filter_status = "1";
			}
			productForm.setFilter_status(filter_status);
			productForm.setLocation_filter(location_filter);
			productForm.setCategory_id(category_id);

			if (isfromcathlab != null) {
				if (isfromcathlab.equals("")) {
					isfromcathlab = "0";
				}
			} else {
				isfromcathlab = productForm.getIsfromcathlab();
				if (isfromcathlab != null) {
					if (isfromcathlab.equals("")) {
						isfromcathlab = "0";
					}
				} else {
					isfromcathlab = "0";
				}
			}
			productForm.setIsfromcathlab(isfromcathlab);

			String ismonthlyreport = request.getParameter("ismonthlyreport");
			if (ismonthlyreport != null) {
				if (ismonthlyreport.equals("")) {
					ismonthlyreport = "0";
				}
			} else {
				ismonthlyreport = productForm.getIsmonthlyreport();
				if (ismonthlyreport != null) {
					if (ismonthlyreport.equals("")) {
						ismonthlyreport = "0";
					}
				} else {
					ismonthlyreport = "0";
				}
			}
			int limit = productForm.getCountval();
			productForm.setPage(limit);
			productForm.setCountval(limit);
			limit = limit * 1000;
			int maxlimit = 0, minlimit = 0;
			maxlimit = limit;
			minlimit = limit - 1000;
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());

			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
			}
			if (searchbyprodname != null) {
				if (searchbyprodname.equals("")) {
					searchbyprodname = null;
				}
			}

			/*
			 * if(!fromDate.equals("")){ String temp[]= fromDate.split("/");
			 * fromDate = temp[2]+"-"+temp[1]+"-"+temp[0]; }
			 * if(!toDate.equals("")){ String temp1[]= toDate.split("/"); toDate
			 * = temp1[2]+"-"+temp1[1]+"-"+temp1[0]; }
			 */
			/*
			 * int
			 * count=inventoryProductDAO.getCountTransferDashboard(searchtext,
			 * fromdate,todate,location1,filter_status);
			 * pagination.setPreperties(count); int result =
			 * inventoryProductDAO.checkForProcurmentItemReturn(searchtext,
			 * fromdate,todate,location1,filter_status,pagination);
			 * ArrayList<Product> parenttransferlist =
			 * inventoryProductDAO.getParentProductTransferData(searchtext,
			 * fromdate,todate,location1,filter_status,pagination);
			 * pagination.setPage_records(parenttransferlist.size());
			 * productForm.setTotalRecords(count);
			 * productForm.setPagerecords(String.valueOf(pagination.
			 * getPage_records()));
			 */

			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			// lokesh
			/*
			 * String fromdate1= fromDate; String todte1= toDate;
			 */
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromDate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(toDate));
			int result = chargesReportDAO.saveMisReportLog("Inventory Closing Report", loginInfo.getUserId(), fromDate,
					toDate, date, "inventoryOpeningClosing");
			ArrayList<Product> openingstockList = new ArrayList<Product>();
			int count = 0;
			productForm.setIsfromcathlab(isfromcathlab);
			productForm.setIsmonthlyreport(ismonthlyreport);
			if (isfromcathlab.equals("1")) {
				count = inventoryProductDAO.getCountCathOpeningStockList(fromDate, toDate);
				pagination.setPreperties(count);
				openingstockList = inventoryProductDAO.getCathOpeningStockList(fromDate, toDate, pagination, minlimit,
						maxlimit, ismonthlyreport);
				productForm.setOpeningstockList(openingstockList);
				Product product = openingstockList.get(openingstockList.size() - 1);
				productForm.setOpentotalcount(product.getOpentotalcount());
				pagination.setPage_records(openingstockList.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			} else {
				if (filter_status.equals("1")) {
					count = inventoryProductDAO.getCountOpeningStockList(fromDate, toDate, searchbyprodname,
							location_filter, category_id);
					pagination.setPreperties(count);
					openingstockList = inventoryProductDAO.getOpeningStockList(fromDate, toDate, pagination, minlimit,
							maxlimit, ismonthlyreport, searchbyprodname, location_filter, category_id);
					productForm.setOpeningstockList(openingstockList);
					Product product = openingstockList.get(openingstockList.size() - 1);
					productForm.setOpentotalcount(product.getOpentotalcount());
					pagination.setPage_records(openingstockList.size());
					productForm.setTotalRecords(count);
					productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				} else {
					String alreadypresentids = inventoryProductDAO.getOpeningStockStringNew(fromDate, toDate,
							ismonthlyreport, searchbyprodname, location_filter, category_id);
					productForm.setOpeningstockList(openingstockList);
					count = inventoryProductDAO.getCountOpeningStockListNew(fromDate, toDate, searchbyprodname,
							location_filter, category_id, alreadypresentids);
					pagination.setPreperties(count);
					openingstockList = inventoryProductDAO.getOpeningStockListNew(fromDate, toDate, pagination,
							minlimit, maxlimit, ismonthlyreport, searchbyprodname, location_filter, category_id,
							alreadypresentids);
					productForm.setOpeningstockList(openingstockList);
					Product product = openingstockList.get(openingstockList.size() - 1);
					productForm.setOpentotalcount(product.getOpentotalcount());
					pagination.setPage_records(openingstockList.size());
					productForm.setTotalRecords(count);
					productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				}

			}

			int size = openingstockList.size();
			if (size > 0) {
				double total_Amount = openingstockList.get(size - 1).getTotal_amount();
				double totalqty = openingstockList.get(size - 1).getTotalqty();
				productForm.setTotal_amt(DateTimeUtils.changeFormat(total_Amount));
				productForm.setTotalqty(totalqty);
				productForm.setTotalopeningval(openingstockList.get(openingstockList.size() - 1).getTotalopeningval());
			} else {
				productForm.setTotal_amt("0.00");
			}

			ArrayList<Product> countlist = new ArrayList<Product>();
			int count3 = 0;
			int size1 = count;
			int pg = 0;
			while (size1 > 0) {
				Product p = new Product();
				count3 = count3 + 1;
				p.setLimit(count3);
				/* p.setLimitstr(count3-1000+"  to "+count3); */
				countlist.add(p);
				size1 = size1 - 1000;
			}

			/* productForm.setPage(pg); */
			productForm.setCountlist(countlist);
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			productForm.setFromdate(fromDate);
			productForm.setTodate(toDate);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "inventoryOpeningClosingReport";
	}

	public String consumptionreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = productForm.getLocation();
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			productForm.setLocation(location);
			productForm.setIsfrombincard(1);

			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}
			//   28 sep 2017 Pagination code added
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Consumption Report", loginInfo.getUserId(), fromdate,
					todate, date, "consumptionreport");
			int count = inventoryProductDAO.getCountConsumptionReport(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype());
			pagination.setPreperties(count);
			ArrayList<Product> salesReturnProduct = inventoryProductDAO.getConsumptionReport(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype(), pagination);
			productForm.setSalesReturnProduct(salesReturnProduct);
			pagination.setPage_records(salesReturnProduct.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			
			setLetterData(location, connection);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "consumptionreport";
	}

	public String getcategorywarehouse() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			ArrayList<Master> categoryList = inventoryProductDAO.getCategoryFromWareHouse(id);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<label>Select Category</label>");
			buffer.append(
					"<select name='category_id' id='category_id' onchange='getsub(this.value)' class='form-control chosen'  > ");
			buffer.append("<option value='0'>Select Category</option>");
			for (Master master : categoryList) {
				buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String transferrequestedproductdata() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			// int tcount = Integer.parseInt(request.getParameter("tcount"));
			String comment = request.getParameter("comment");
			comment=productForm.getComment();
			String firstlocation = "";
			int i = 0;
			// String parentid = (String) session.getAttribute("reqparentid1");
			String parentid = request.getParameter("parentid");

			String transfer_date = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			transfer_date = dateFormat.format(cal.getTime());
			int resul = inventoryProductDAO.updateCheckAvailabilityNotes(parentid, comment, transfer_date);
			int count5 = inventoryProductDAO.getTotalReqTransferTemp(parentid);
			count5++;
			Product product4 = inventoryProductDAO.getParentTransferData(parentid);

			if (productForm.getProductdata() != null) {
				for (Product product : productForm.getProductdata()) {
					if (product == null) {
						continue;
					}

					int prodid = Integer.parseInt(product.getProduct_id());
					// String catid = indentDAO.getCatIdProdId(""+prodid);
					String qty = product.getReqqty();
					Product product1 = inventoryProductDAO.getProduct("" + prodid);
					// String location = product1.getLocation();
					// now commented
					int result = indentDAO.requestProductTemporarySave(prodid, product1.getLocation(), qty, parentid,
							product1.getProduct_name(), transfer_date, count5, product1.getCatalogueid());

					Product newproduct = new Product();
					newproduct.setId(result);
					newproduct.setProduct_id(product.getProduct_id());
					newproduct.setProduct_name(product1.getProduct_name());
					newproduct.setLocation(product1.getLocation());
					newproduct.setReqqty(qty);
					newproduct.setStock(product1.getStock());
					newproduct.setHsnno(product1.getHsnno());
					newproduct.setExpiry_date(product1.getExpiry_date());
					newproduct.setBatch_no(product1.getBatch_no());
					newproduct.setLocation(product1.getLocation());
					newproduct.setExpectedDate(product4.getExpectedDate());
					newproduct.setReq_location(product4.getLocation());
					//   09 oct 2017 set catalogue id
					newproduct.setCatalogueid(product1.getCatalogueid());

					newproduct.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					newproduct.setUserid(loginInfo.getUserId());
					int result2 = pharmacyDAO.transferRequestedMedicine(parentid, newproduct);

					i++;
				}
			}
			ArrayList<String> stringist = indentDAO.getChildProductName(parentid);

			for (String prod_name : stringist) {
				double total_qty = indentDAO.getReqQtyFrmTemp(prod_name, parentid);
				Product product = indentDAO.getReqQtyFrmAct(parentid, prod_name);
				int status = 0;
				/*
				 * if (total_qty == (product.getQty()) || total_qty > (product.getQty())) {
				 * status = 1; } else if (total_qty > 0) { status = 2; } else { status = 0; }
				 */ //this is origanal code commented beacuase pending not show
				if (total_qty == (product.getQty()) || total_qty > (product.getQty())) {
					status = 1;
				} else if (total_qty > 0) {
					status = 2;
				} else {
					status = 0;
				}
			  if(loginInfo.isKalmegha()) {	
				  //for kaalmegh
				if (total_qty< (product.getQty())) {
					status = 0;
				} 
			  }	
				int result = inventoryProductDAO.updateChildTransferStatus(product.getId(), status);
				int res = indentDAO.updateChildTempTransferStatus(prod_name, parentid, status);
			}

			String userid = loginInfo.getUserId();
			int count = inventoryProductDAO.getCountTransferProd(parentid);
			int count1 = inventoryProductDAO.getCountTransferedProd(parentid);
			int all_med_status = 0;
			if (count == count1) {
				all_med_status = 1;
			} else {
				all_med_status = 0;
			}
			int r_status = 3;
			
		if(loginInfo.isKalmegha()) {
			for (String prod_name : stringist) {
				/* this code (for each)is added for kaalmegh */
				double total_qty = indentDAO.getReqQtyFrmTemp(prod_name, parentid);
				Product product = indentDAO.getReqQtyFrmAct(parentid, prod_name);
				int status = 0;
				if(loginInfo.isKalmegha()) {
					if (total_qty< (product.getQty()) || (!product.getReq_limit_qty().equals("0") && product.getApprove_qty_status().equals("0"))) {
						r_status = 6;
						break;
					 }
				}else {
				 if (total_qty< (product.getQty())) {
					r_status = 6;
					break;
				 }
			 }	
				//end hear
			}
		}
		
			if (all_med_status == 0) {
				r_status = 6;
			}

			int result = inventoryProductDAO.updateParentProductStatus(parentid, userid, all_med_status, r_status);
			session.setAttribute("prodidname1", "0");
			session.setAttribute("reqparentid1", "0");
			session.setAttribute("tcount", null);
			if (productForm.getPodata() != null) {
				for (Product product3 : productForm.getPodata()) {
					if (product3 == null) {
						continue;
					}
					String name = product3.getProd_name();
					int id = product3.getId();
					double qty = product3.getQty();
					int res = inventoryProductDAO.updateTempPoStatus(id, qty);
				}
			}

			/*
			 * ArrayList<Product> arrayList =
			 * pharmacyDAO.getChildTempReqData(parentid); //int res =
			 * pharmacyDAO.deleteTempRequestedData(parentid); for (Product
			 * productx : arrayList) {
			 * productx.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo
			 * .getTimeZone())); productx.setUserid(loginInfo.getUserId()); int
			 * result2 =
			 * pharmacyDAO.transferRequestedMedicine(parentid,productx); }
			 */

			// Product product =
			// inventoryProductDAO.getParentTransferData(parentid);
			if (product4.getProcurementid() != null) {
				if (!product4.getProcurementid().equals("")) {
					int zyx = inventoryProductDAO.updateProcurmentStatus(product4.getProcurementid(), "1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferToTansferDash";
	}

	public String addOtherToTempPo() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			String instock = request.getParameter("instock");
			String reqstock = request.getParameter("reqstock");
			String prodid = request.getParameter("prodid");
			String catid = request.getParameter("catid");
			Product product2 = inventoryProductDAO.getParentTransferData(parentid);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			// String catid = inventoryProductDAO.getCatIdProdId(prodid);
			StringBuilder builder = new StringBuilder();
			Product product1 = new Product();
			if (prodid == null) {
				product1 = inventoryProductDAO.getProductCatalogueDetails(catid);
			} else if (prodid.equals("0")) {
				product1 = inventoryProductDAO.getProduct(prodid);
			} else {
				product1 = inventoryProductDAO.getProduct(prodid);
			}
			int req_stock = Integer.parseInt(reqstock) - Integer.parseInt(instock);
			if (req_stock < 0) {
				req_stock = 0;
			}
			int result = inventoryProductDAO.addTempPoRequest(prodid, parentid, req_stock, product1.getProduct_name(),
					todate, product2.getWarehouse_id(), product2.getIndent(), catid);

			ArrayList<Product> polist = inventoryProductDAO.getTempPoList(parentid);
			int i = 0;
			int j = 1;
			for (Product product : polist) {
				builder.append("<tr>");
				builder.append("<td>" + j + "<input type='hidden' value='" + product.getId() + "' name='podata[" + i
						+ "].id' ></td>");
				builder.append("<input type='hidden'  value='" + product.getProd_name()
						+ "' class='form-control' name='podata[" + i + "].prod_name' >");
				builder.append("<td>" + product.getProd_name() + "</td>");
				builder.append(
						"<td><input type='number' value='" + product.getQty() + "' class='form-control' name='podata["
								+ i + "].qty' style='background-color: cornsilk;'></td>");
				builder.append("</tr>");
				i++;
				j++;
			}

			builder.append("~");
			builder.append("" + result + "");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String deliverPrint() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String parentid = request.getParameter("id");
		String status = request.getParameter("status");
		String mainstatus = request.getParameter("mainstatus");
		String printbeforeapprove = request.getParameter("printbeforeapprove");
		Connection connection = null;
		try {
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Indent Print")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Indent Print");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("deliverPrintProduct?id=" + parentid + "&status=" + status + "&mainstatus="
						+ mainstatus + "&printbeforeapprove=" + printbeforeapprove + "");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);

			ArrayList<Product> requestedmedicineList = inventoryProductDAO.getChildTranfserData(parentid);
			ArrayList<Product> checkmedicinelist = inventoryProductDAO.getChildRequestedTempDataPrint(parentid);

			Product product3 = inventoryProductDAO.getChildTransferTempCal(parentid);
			ArrayList<Product> polist = inventoryProductDAO.getTempPoList(parentid);
			ArrayList<Product> callist = new ArrayList<Product>();
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			String userid = loginInfo.getUserId();
			Priscription priscription2 = pharmacyDAO.getPharacyUsrLInfo(userid);
			productForm.setHandover_to(product.getHandover_to());
			productForm.setParentid(parentid);
			productForm.setCallist(callist);
			productForm.setIndentid("" + product.getIndent());
			productForm.setPolist(polist);
			productForm.setCheckmedicinelist(checkmedicinelist);
			productForm.setRequest_date(product.getRequest_date());
			productForm.setUserid(product.getUserid());
			productForm.setFrom_location(product.getFrom_location());
			productForm.setRequestedmedicineList(requestedmedicineList);
			productForm.setIssueno(parentid);
			productForm.setParentid(parentid);
			productForm.setAdmin_notes(product.getAdmin_notes());
			productForm.setTotal_amount(product3.getTotal_amount());
			productForm.setTotolmrp(product3.getTotolmrp());
			productForm.setCheck_availability_note(product.getCheck_availability_note());

			// String userid1 = loginInfo.getUserId();
			// Priscription priscription =
			// pharmacyDAO.getPharacyUsrLInfo(userid1);
			if (priscription2.getFullname() != null) {
				userid = priscription2.getFullname();
			}
			productForm.setCurr_user(userid);
			String temp = product.getCheck_availability_date();
			String datetime = "";
			if (temp != null) {
				String[] data = temp.split(" ");
				String date = DateTimeUtils.getCommencingDate1(data[0]);
				datetime = date + " " + data[1];
			}

			productForm.setCheck_availability_date(datetime);
			productForm.setCheck_avail_userid(product.getCheck_avail_userid());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curr_date = dateFormat.format(cal.getTime());
			productForm.setDate(curr_date);
			productForm.setIssued_date(product.getIssued_date());

			String status2 = "";
			if (status.equals("1")) {
				if (priscription2.getRequisition_auth() == null) {
					priscription2.setRequisition_auth("");
				}
				String jobtitle = "";
				if (loginInfo.getJobTitle() != null) {
					jobtitle = loginInfo.getJobTitle();
				}
				if (loginInfo.getUserType() == 2 || priscription2.getRequisition_auth().equals("1")
						|| (!jobtitle.equals("Pharmacist"))) {
					// builder.append("<input type='button' class='btn
					// btn-success' value='RECEIVE'
					// onclick='finalaproveTransferLog("+parentid+")'>");
					// builder.append("<input type='button'
					// onclick=printDiv2('page_printer2') class='btn
					// btn-warning' value='PRINT'/>");
					status2 = "1";
				} else {
					status2 = "2";
					// builder.append("<input type='button'
					// onclick=printDiv2('page_printer2') class='btn
					// btn-warning' value='PRINT'/>");
				}
			} else if (status.equals("4")) {
				status2 = "4";
			} else {
				status2 = "3";
			}
			productForm.setStatus(status2);
			if (mainstatus == null) {
				productForm.setMainstatus("2");
			} else if (mainstatus.equals("4")) {
				productForm.setMainstatus("4");
			} else if (mainstatus.equals("5")) {
				productForm.setMainstatus("5");
			} else if (mainstatus.equals("6")) {
				productForm.setMainstatus("6");
			}

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

			ArrayList<Product> termsandconditionlist = inventoryProductDAO.getAllTermsAndCondition("0");
			productForm.setTermsandconditionlist(termsandconditionlist);

			if (printbeforeapprove == null) {
				printbeforeapprove = "0";
			} else if (printbeforeapprove.equals("")) {
				printbeforeapprove = "0";
			}
			productForm.setPrintbeforeapprove(printbeforeapprove);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "deliverPrint";
	}

	public String updatefinalAprove() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String parentid = request.getParameter("parentid");

			String userid = loginInfo.getUserId();
			int result1 = pharmacyDAO.updateAproveTransferLog(parentid, "4", "0", userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferToTansferDash";
	}

	public String createPoOnTransfer() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");

			String transfer_date = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			transfer_date = dateFormat.format(cal.getTime());

			String userid = loginInfo.getUserId();
			int result1 = inventoryProductDAO.createPoOnTransferStatus(parentid, "5", transfer_date, userid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String showRequestMedicine() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String parentid = request.getParameter("parentid");
			String val = request.getParameter("val");
			String userid = loginInfo.getUserId();
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			ArrayList<Product> childtransferlist = inventoryProductDAO.getChildTranfserData(parentid);
			// Product product3 =
			// inventoryProductDAO.getChildTransferCal(parentid);
			Priscription priscription2 = pharmacyDAO.getPharacyUsrLInfo(userid);
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(product.getUserid());
			StringBuilder builder = new StringBuilder();

			builder.append("" + product.getFrom_location() + "");
			builder.append("~");

			builder.append("" + product.getRequest_date() + "");
			builder.append("~");

			builder.append("" + product.getTo_location() + "");
			builder.append("~");
			int i = 0;
			for (Product product2 : childtransferlist) {
				builder.append("<tr>");
				builder.append("<td>" + (i + 1) + " </td>");
				builder.append("<td>" + product2.getProduct_id() + "</td>");
				builder.append("<td>" + product2.getProduct_name() + "</td>");
				builder.append("<td>" + product2.getPurchase_price() + "</td>");
				builder.append("<td>" + product2.getAvail_qty() + "</td>");
				builder.append("<td>" + product2.getStock() + "</td>");
				builder.append("<td>" + product2.getComment() + "</td>");
				builder.append("</tr>");
				i++;
			}

			builder.append("~");
			builder.append("" + parentid + "");

			builder.append("~");
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			builder.append("<h4>" + userProfile.getClinicname() + "</h4>");
			builder.append("<h5>" + userProfile.getAddress() + "</h5>");
			builder.append("<h5>Website:" + userProfile.getWebsite() + ", Email:" + userProfile.getEmail()
					+ ", Contact : " + userProfile.getPhone() + "</h5>");

			int selectedid = loginInfo.getId();
			// UserProfile userProfile2
			// =userProfileDAO.getUserprofileDetails(selectedid);
			// String username = userProfile2.getInitial()+" "+
			// userProfile2.getFirstname()+" "+ userProfile2.getLastname();

			String datetime = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			Calendar cal = Calendar.getInstance();
			datetime = dateFormat.format(cal.getTime());
			builder.append("~");
			builder.append("" + userid + "");
			builder.append("~");
			builder.append("" + datetime + "");

			builder.append("~");
			builder.append("" + product.getAdmin_notes() + "");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String deliverPrintDirect() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String parentid = request.getParameter("id");
		String mainstatus = request.getParameter("mainstatus");
		Connection connection = null;
		try {
			String transferMainParentId = (String) session.getAttribute("transferMainParentId"); 
			
			if(!DateTimeUtils.isNull(transferMainParentId).equals("")){
				parentid = transferMainParentId;
				session.removeAttribute("transferMainParentId");
			}
			
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ArrayList<Product> requestedmedicineList = inventoryProductDAO.getChildTranfserData(parentid);
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			Product product3 = inventoryProductDAO.getChildTransferCal(parentid);
			productForm.setHandover_to(product.getHandover_to());
			productForm.setParentid(parentid);
			productForm.setIndentid(product.getTransfer_indentno());
			productForm.setCheckmedicinelist(requestedmedicineList);
			productForm.setRequest_date(product.getRequest_date());
			productForm.setUserid(product.getUserid());
			productForm.setFrom_location(product.getFrom_location());
			productForm.setRequestedmedicineList(requestedmedicineList);
			productForm.setIssueno(parentid);
			productForm.setParentid(parentid);
			productForm.setAdmin_notes(product.getAdmin_notes());
			productForm.setTotal_amount(product3.getTotal_amount());
			productForm.setTotolmrp(product3.getTotolmrp());
			productForm.setCheck_availability_note(product.getCheck_availability_note());
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
			if (priscription.getFullname() != null) {
				userid = priscription.getFullname();
			}
			productForm.setCurr_user(userid);
			String datetime = product.getIssued_date() + " " + product.getTime();
			productForm.setCheck_availability_date(datetime);
			productForm.setCheck_avail_userid(product.getUserid());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curr_date = dateFormat.format(cal.getTime());
			productForm.setDate(curr_date);
			productForm.setIssued_date(product.getRequest_date());
			productForm.setTo_location(product.getTo_location());
			productForm.setComment(product.getComment());

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			// ArrayList<Clinic> locationAdressList =
			// accountsDAO.getLetterHeadDetails(loginInfo.getClinicid());
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

			if (mainstatus == null) {
				mainstatus = "1";
			} else {
				mainstatus = "6";
			}
			productForm.setMainstatus(mainstatus);

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			String req_or_trans = product.getReq_or_transfer();
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (req_or_trans.equals("2")) {
					if (breadcrumbs.getName().equals("Return Indent Print")) {
						isavilablemodule = true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					} else {
						indentflowlist.add(breadcrumbs);
					}
				} else {
					if (breadcrumbs.getName().equals("Direct Indent Print")) {
						isavilablemodule = true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					} else {
						indentflowlist.add(breadcrumbs);
					}
				}

			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				if (req_or_trans.equals("2")) {
					breadcrumbs.setName("Return Indent Print");
				} else {
					breadcrumbs.setName("Direct Indent Print");
				}

				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("deliverPrintDirectProduct?id=" + parentid + "&mainstatus=" + mainstatus + "");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "deliverPrintDirect";
	}

	public String addHandover_To() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			String handover_to = request.getParameter("handover_to");
			int result = inventoryProductDAO.updateHandover_To(parentid, handover_to);
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			StringBuilder builder = new StringBuilder();
			builder.append("<label>Handover To: <span style='font-weight: normal;'>" + product.getHandover_to()
					+ "</span></label>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String deleteindent() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			String delete_reason = request.getParameter("delete_reason");
			// int del=inventoryProductDAO.deleteIndentParent(parentid);
			int del = inventoryProductDAO.cancelIndent(parentid);
			String userid = loginInfo.getUserId();

			String loc = (String) session.getAttribute("location");
			if (loc == null) {
				loc = "0";
			}

			int res = inventoryProductDAO.saveUpDeleteIndent(parentid, delete_reason, userid, loc);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String checkmedicineavability() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		// by   (for check medicine less)
		String parentid = request.getParameter("id");
		Connection connection = null;
		try {
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Indent Transfer Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Indent Transfer Dashboard");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("checkmedicineavabilityProduct?id=" + parentid);
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);
			
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			
			DateFormat dateFormat5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.MINUTE, -10);
			String datetime5 = dateFormat5.format(cal1.getTime());			
			indentDAO.deleteTempIndentInTransfer(datetime5);
			
			int res = indentDAO.checkIndentInTransfer(parentid);
		if(loginInfo.isLmh()) {	
			if(res==0){
				String datetime = dateFormat5.format(new Date());
				res = indentDAO.insertIntoIndentInTransfer(parentid,loginInfo.getUserId(),datetime);
			}else{
				res = indentDAO.checkIndentInTransferUserWise(parentid,loginInfo.getUserId());
				if(res==0){
					Product product = indentDAO.getIndentTempAddLog(parentid);
					productForm.setParentid(product.getParentid());
					productForm.setUserid(product.getUserid());
					productForm.setDate(DateTimeUtils.getIndianDateTimeFormat(product.getDateTime()));
					return "indentError";
				}
			}
		}	
			// ArrayList<Product> requestedmedicineList =
			// inventoryProductDAO.getChildTranfserData(parentid);
			ArrayList<Product> requestedmedicineList = indentDAO.getChildTranfserData(parentid);
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());
			ArrayList<Product> checkmedicinelist = new ArrayList<Product>();
			for (Product prod : requestedmedicineList) {
				if (prod.getCancel_req().equals("1")) {
					continue;
				} else {
					double req_stock = Double.parseDouble(prod.getStock());
					// String catid =
					// indentDAO.getCatIdProdId(prod.getProduct_id());
					String catid = prod.getCatalogueid();
					double instock2 = inventoryProductDAO.getAllProdAvailableStock(catid, product.getWarehouse_id());
					// int instock =
					// inventoryProductDAO.getAvailableStock(prod.getProduct_id(),prod.getProduct_name());
					prod.setCatalogueid(catid);
					prod.setWarehouse_id(product.getWarehouse_id());
					if (instock2 > 0) {
						// ArrayList<Product> lesstockwiselist =
						// inventoryProductDAO.getAscStockWiseProductList(req_stock,prod);
						ArrayList<Product> lesstockwiselist = indentDAO.getAscStockWiseProductList(req_stock, prod);
						checkmedicinelist.addAll(lesstockwiselist);
						if (instock2 < req_stock) {
							double poqty = req_stock - instock2;
							int result = inventoryProductDAO.addTempPoRequest(prod.getProduct_id(), parentid, poqty,
									prod.getProduct_name(), todate, product.getWarehouse_id(), product.getIndent(),
									catid);
						}
					} else {
						int result = inventoryProductDAO.addTempPoRequest(prod.getProduct_id(), parentid, req_stock,
								prod.getProduct_name(), todate, product.getWarehouse_id(), product.getIndent(), catid);
					}
				}
			}
			ArrayList<Product> polist = inventoryProductDAO.getTempPoList(parentid);
			productForm.setIndentid("" + product.getIndent());
			productForm.setPolist(polist);
			productForm.setCheckmedicinelist(checkmedicinelist);
			productForm.setRequest_date(product.getRequest_date());
			productForm.setUserid(product.getUserid());
			productForm.setFrom_location(product.getFrom_location());
			productForm.setRequestedmedicineList(requestedmedicineList);
			productForm.setIssueno(parentid);
			productForm.setParentid(parentid);
			productForm.setAdmin_notes(product.getAdmin_notes());
			productForm.setLocation(product.getLocation());
			String loc = (String) session.getAttribute("location");

			if (loc != null) {
				if (loc.equals("")) {
					loc = "0";
				}
			}

			if (loc == null) {
				loc = "0";
			}
			boolean flag = false;
			if (!loc.equals("0")) {
				flag = indentDAO.checkLocationInWarehouseid(loc);
			}
			String delipo = "0";
			if (flag == true || loc.equals("0") || loginInfo.getUserType() == 2) {
				delipo = "1";
			}
			productForm.setDelipo(delipo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "checkmedicineavability";
	}

	public String checkOtherLocation() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder builder = new StringBuilder();
			String parentid = request.getParameter("parentid");
			String count = request.getParameter("count");
			String check1 = request.getParameter("check1");
			if (check1.equals("true")) {
				int tcount = Integer.parseInt(count);
				int temp = tcount;

				// ArrayList<Product> requestedmedicineList =
				// inventoryProductDAO.getChildTranfserData(parentid);
				ArrayList<Product> requestedmedicineList = indentDAO.getChildTranfserData(parentid);
				Product product = inventoryProductDAO.getParentTransferData(parentid);

				// ArrayList<Product> checkavailablelist =
				// inventoryProductDAO.getAvailableMedicineList(product);
				ArrayList<Product> checkmedicinelist = new ArrayList<Product>();
				for (Product prod : requestedmedicineList) {
					temp++;
					double req_stock = Double.parseDouble(prod.getStock());
					// String catid =
					// indentDAO.getCatIdProdId(prod.getProduct_id());
					String catid = prod.getCatalogueid();
					double instock2 = inventoryProductDAO.getAllProdAvailableStock(catid, product.getWarehouse_id());
					// int instock =
					// inventoryProductDAO.getAvailableStock(prod.getProduct_id(),prod.getProduct_name());
					prod.setCatalogueid(catid);
					prod.setWarehouse_id(product.getWarehouse_id());
					if (instock2 > 0) {

						ArrayList<Product> lesstockwiselist = indentDAO.getAscStockWiseProductList(req_stock, prod);
						int size = lesstockwiselist.size();
						if (size > 0) {
							double total = lesstockwiselist.get(size - 1).getTotalbalance();
							if (total >= req_stock) {
								continue;
							} else {
								ArrayList<Product> locationwiselist1 = indentDAO.getLocationWiseStock(prod, product);
								if (locationwiselist1 != null) {
									for (Product product1 : locationwiselist1) {
										double availStock = product1.getAvail_stock();
										String location = product1.getLocation();
										// ArrayList<Product> allmedicinelist =
										// inventoryProductDAO.getProductListFromOtherLocation(prod,location);
										ArrayList<Product> allmedicinelist = indentDAO
												.getProductLimitedFromOtherLocation(prod, location);
										checkmedicinelist.addAll(allmedicinelist);
									}
								}
							}
						}
					} else {
						ArrayList<Product> locationwiselist2 = indentDAO.getLocationWiseStock(prod, product);
						if (locationwiselist2 != null) {
							for (Product product5 : locationwiselist2) {
								double availStock = product5.getAvail_stock();
								String loc = product5.getFromlocation();
								String location = product5.getLocation();
								// ArrayList<Product> allmedicinelist =
								// inventoryProductDAO.getProductListFromOtherLocation(prod,location);
								ArrayList<Product> allmedicinelist = indentDAO.getProductLimitedFromOtherLocation(prod,
										location);
								checkmedicinelist.addAll(allmedicinelist);
							}
						}
					}
				}
				int temp2 = tcount;
				for (Product product3 : checkmedicinelist) {
					temp2++;
					builder.append("<tr>");
					builder.append("<input type='hidden' value='" + product3.getProduct_id()
							+ "' class='form-control' name='productdata[" + tcount + "].product_id' >");
					builder.append("<td><input type='text' readonly='readonly' value='" + temp2
							+ "' class='form-control' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getHsnno()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].hsnno' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getProduct_name()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].product_name' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getBatch_no()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].batch_no' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getExpiry_date()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].expiry_date' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getFromlocation()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].fromlocation' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getStock()
							+ "' class='form-control' id='stock" + tcount + "'  name='productdata[" + tcount
							+ "].stock' style='background-color: cornsilk;'></td>");
					builder.append("<td>");
					/*
					 * if(Integer.parseInt(product3.getStock())>=Integer.
					 * parseInt(product3.getReqqty())){
					 * builder.append("<input type='text' value='"+product3.
					 * getReqqty()+"' class='form-control' id='reqqty"
					 * +tcount+"' onmouseout='checkNotGreterThanStock("
					 * +tcount+")' onblur='checkNotGreterThanStock("
					 * +tcount+")' name='productdata["
					 * +tcount+"].reqqty' style='background-color: cornsilk;'>"
					 * ); }else {
					 * builder.append("<input type='text' value='"+product3.
					 * getStock()+"' onmouseout='checkNotGreterThanStock("
					 * +tcount+")' onblur='checkNotGreterThanStock("
					 * +tcount+")'  class='form-control' id='reqqty"
					 * +tcount+"' name='productdata["
					 * +tcount+"].reqqty' style='background-color: cornsilk;'>"
					 * ); }
					 */
					builder.append("<input type='text' value='" + product3.getStockqty()
							+ "' onmouseout='checkNotGreterThanStock(" + tcount + ")' onblur='checkNotGreterThanStock("
							+ tcount + ")'  class='form-control' id='reqqty" + tcount + "' name='productdata[" + tcount
							+ "].reqqty' style='background-color: cornsilk;'>");
					builder.append("</td>");
					builder.append(
							"<td><a href='#' onclick='deleteRequetedRow(this)'><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
					builder.append("<td><a href='#' onclick='addOtherToTempPo(" + product3.getProduct_id() + ","
							+ tcount + "," + product3.getReqqty() + "," + product3.getCatalogueid()
							+ ")'><i class='fa fa-plus-square fa-2x text-success' ></i></a></td>");
					builder.append("</tr>");
					tcount++;
				}
				builder.append("~");
				builder.append("" + tcount + "");
			} else {
				String temp = null;
				builder.append(" ");

				builder.append("~");
				builder.append("" + count + "");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String checkmedicineavabilityafterPO() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String parentid = request.getParameter("id");
		String status = request.getParameter("status");
		Connection connection = null;
		try {
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Pending Transfer Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Pending Transfer Dashboard");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs
						.setUrllink("checkmedicineavabilityafterPOProduct?id=" + parentid + "&status=" + status + "");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			ArrayList<Product> requestedmedicineList = inventoryProductDAO.getChildTranfserDataAfterPO(parentid);
			ArrayList<Product> requestedmedicineList1 = inventoryProductDAO.getChildTranfserData(parentid);
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String todate = dateFormat.format(cal.getTime());

			ArrayList<Product> checkmedicinelist = new ArrayList<Product>();
			for (Product prod : requestedmedicineList) {
				double req_stock =Double.parseDouble(prod.getStock());
				// int instock =
				// inventoryProductDAO.getAvailableStock(prod.getProduct_id(),prod.getProduct_name());

				// String catid =
				// indentDAO.getCatIdProdId(prod.getProduct_id());
				String catid = prod.getCatalogueid();
				double instock2 = inventoryProductDAO.getAllProdAvailableStock(catid, product.getWarehouse_id());
				int instock = inventoryProductDAO.getAvailableStock(prod.getProduct_id(), prod.getProduct_name());
				prod.setCatalogueid(catid);
				prod.setWarehouse_id(product.getWarehouse_id());

				if (instock2 > 0) {
					ArrayList<Product> lesstockwiselist = indentDAO.getAscStockWiseProductList(req_stock, prod);
					checkmedicinelist.addAll(lesstockwiselist);
				}

			}
			ArrayList<Product> polist = inventoryProductDAO.getTempPoList(parentid);
			productForm.setIndentid("" + product.getIndent());
			productForm.setPolist(polist);
			productForm.setCheckmedicinelist(checkmedicinelist);
			productForm.setRequest_date(product.getRequest_date());
			productForm.setUserid(product.getUserid());
			productForm.setFrom_location(product.getFrom_location());
			productForm.setRequestedmedicineList(requestedmedicineList1);
			productForm.setIssueno(parentid);
			productForm.setParentid(parentid);
			productForm.setAdmin_notes(product.getAdmin_notes());
			productForm.setMainstatus(status);
			productForm.setLocation(product.getLocation());
			String loc = (String) session.getAttribute("location");
			if (loc != null) {
				if (loc.equals("")) {
					loc = "0";
				}
			}
			if (loc == null) {
				loc = "0";
			}
			boolean flag = false;
			if (!loc.equals("0")) {
				flag = indentDAO.checkLocationInWarehouseid(loc);
			}
			String delipo = "0";
			if (flag == true || loc.equals("0")) {
				delipo = "1";
			}
			/*
			 * String delipo = "0"; if(loc.equals("32") || loc.equals("36") ||
			 * loc.equals("0")){ delipo = "1"; }
			 */
			productForm.setDelipo(delipo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "checkmedicineavability";
	}

	public String checkOtherLocationAfterPO() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder builder = new StringBuilder();
			String parentid = request.getParameter("parentid");
			String count = request.getParameter("count");
			String check1 = request.getParameter("check1");
			if (check1.equals("true")) {
				int tcount = Integer.parseInt(count);
				int temp = tcount;

				ArrayList<Product> requestedmedicineList = inventoryProductDAO.getChildTranfserDataAfterPO(parentid);
				Product product = inventoryProductDAO.getParentTransferData(parentid);

				// ArrayList<Product> checkavailablelist =
				// inventoryProductDAO.getAvailableMedicineList(product);
				ArrayList<Product> checkmedicinelist = new ArrayList<Product>();
				for (Product prod : requestedmedicineList) {
					temp++;
					double req_stock = Double.parseDouble(prod.getStock());
					String catid = indentDAO.getCatIdProdId(prod.getProduct_id());
					double instock2 = inventoryProductDAO.getAllProdAvailableStock(catid, product.getWarehouse_id());
					int instock = inventoryProductDAO.getAvailableStock(prod.getProduct_id(), prod.getProduct_name());
					prod.setCatalogueid(catid);
					prod.setWarehouse_id(product.getWarehouse_id());
					if (instock2 > 0) {

						ArrayList<Product> lesstockwiselist = indentDAO.getAscStockWiseProductList(req_stock, prod);
						int size = lesstockwiselist.size();
						if (size > 0) {
							double total = lesstockwiselist.get(size - 1).getTotalbalance();
							if (total >= req_stock) {
								continue;
							} else {
								ArrayList<Product> locationwiselist1 = indentDAO.getLocationWiseStock(prod, product);
								if (locationwiselist1 != null) {
									for (Product product1 : locationwiselist1) {
										double availStock = product1.getAvail_stock();
										String location = product1.getLocation();
										// ArrayList<Product> allmedicinelist =
										// inventoryProductDAO.getProductListFromOtherLocation(prod,location);
										ArrayList<Product> allmedicinelist = indentDAO
												.getProductLimitedFromOtherLocation(prod, location);
										checkmedicinelist.addAll(allmedicinelist);
									}
								}
							}
						}
					} else {
						ArrayList<Product> locationwiselist2 = indentDAO.getLocationWiseStock(prod, product);
						if (locationwiselist2 != null) {
							for (Product product5 : locationwiselist2) {
								double availStock = product5.getAvail_stock();
								String loc = product5.getFromlocation();
								String location = product5.getLocation();
								// ArrayList<Product> allmedicinelist =
								// inventoryProductDAO.getProductListFromOtherLocation(prod,location);
								ArrayList<Product> allmedicinelist = indentDAO.getProductLimitedFromOtherLocation(prod,
										location);
								checkmedicinelist.addAll(allmedicinelist);
							}
						}
					}
				}
				int temp2 = tcount;
				for (Product product3 : checkmedicinelist) {
					temp2++;
					builder.append("<tr>");
					builder.append("<input type='hidden' value='" + product3.getProduct_id()
							+ "' class='form-control' name='productdata[" + tcount + "].product_id' >");
					builder.append("<td><input type='text' readonly='readonly' value='" + temp2
							+ "' class='form-control' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getHsnno()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].hsnno' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getProduct_name()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].product_name' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getBatch_no()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].batch_no' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getExpiry_date()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].expiry_date' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getFromlocation()
							+ "' class='form-control'  name='productdata[" + tcount
							+ "].fromlocation' style='background-color: cornsilk;'></td>");
					builder.append("<td><input type='text' readonly='readonly' value='" + product3.getStock()
							+ "' class='form-control' id='stock" + tcount + "'  name='productdata[" + tcount
							+ "].stock' style='background-color: cornsilk;'></td>");
					builder.append("<td>");
					/*
					 * if(Integer.parseInt(product3.getStock())>=Integer.
					 * parseInt(product3.getReqqty())){
					 * builder.append("<input type='text' value='"+product3.
					 * getReqqty()+"' class='form-control' id='reqqty"
					 * +tcount+"' onmouseout='checkNotGreterThanStock("
					 * +tcount+")' onblur='checkNotGreterThanStock("
					 * +tcount+")' name='productdata["
					 * +tcount+"].reqqty' style='background-color: cornsilk;'>"
					 * ); }else {
					 * builder.append("<input type='text' value='"+product3.
					 * getStock()+"' onmouseout='checkNotGreterThanStock("
					 * +tcount+")' onblur='checkNotGreterThanStock("
					 * +tcount+")'  class='form-control' id='reqqty"
					 * +tcount+"' name='productdata["
					 * +tcount+"].reqqty' style='background-color: cornsilk;'>"
					 * ); }
					 */
					builder.append("<input type='text' value='" + product3.getStockqty()
							+ "' onmouseout='checkNotGreterThanStock(" + tcount + ")' onblur='checkNotGreterThanStock("
							+ tcount + ")'  class='form-control' id='reqqty" + tcount + "' name='productdata[" + tcount
							+ "].reqqty' style='background-color: cornsilk;'>");
					builder.append("</td>");
					builder.append(
							"<td><a href='#' onclick='deleteRequetedRow(this)'><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
					builder.append(
							"<td class='hidden'><a href='#' onclick='addOtherToTempPo(" + product3.getProduct_id() + ","
									+ tcount + "," + product3.getReqqty() + "," + product3.getCatalogueid()
									+ ")'><i class='fa fa-plus-square fa-2x text-success' ></i></a></td>");
					builder.append("</tr>");
					tcount++;
				}
				builder.append("~");
				builder.append("" + tcount + "");
			} else {
				String temp = null;
				builder.append(" ");

				builder.append("~");
				builder.append("" + count + "");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String transferproductdata() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			// int tcount = Integer.parseInt(request.getParameter("tcount"));
			String comment = request.getParameter("comment");
			String firstlocation = "";
			int i = 0;
			// String parentid = (String) session.getAttribute("reqparentid1");
			String parentid = request.getParameter("parentid");

			String transfer_date = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			transfer_date = dateFormat.format(cal.getTime());
			int resul = inventoryProductDAO.updateTransferNotes(parentid, comment, transfer_date);
			int count5 = inventoryProductDAO.getTotalReqTransferTemp(parentid);
			count5++;
			/*
			 * if(productForm.getProductdata()!=null){ for (Product product :
			 * productForm.getProductdata()) { if(product==null){ continue; }
			 * 
			 * int prodid = Integer.parseInt(product.getProduct_id()); String
			 * qty = product.getReqqty(); Product product1 =
			 * inventoryProductDAO.getProduct(""+prodid); String location =
			 * product1.getLocation(); //now commented String
			 * catid=product1.getCatalogueid(); int result =
			 * indentDAO.requestProductTemporarySave(prodid,product1.getLocation
			 * (),qty,parentid,product1.getProduct_name(),transfer_date,count5,
			 * catid); i++; } }
			 */

			Product product4 = inventoryProductDAO.getParentTransferData(parentid);

			if (productForm.getProductdata() != null) {
				for (Product product : productForm.getProductdata()) {
					if (product == null) {
						continue;
					}

					int prodid = Integer.parseInt(product.getProduct_id());
					// String catid = indentDAO.getCatIdProdId(""+prodid);
					String qty = product.getReqqty();
					Product product1 = inventoryProductDAO.getProduct("" + prodid);
					// String location = product1.getLocation();
					// now commented
					int result = indentDAO.requestProductTemporarySave(prodid, product1.getLocation(), qty, parentid,
							product1.getProduct_name(), transfer_date, count5, product1.getCatalogueid());

					Product newproduct = new Product();
					newproduct.setId(result);
					newproduct.setProduct_id(product.getProduct_id());
					newproduct.setProduct_name(product1.getProduct_name());
					newproduct.setLocation(product1.getLocation());
					newproduct.setReqqty(qty);
					newproduct.setStock(product1.getStock());
					newproduct.setHsnno(product1.getHsnno());
					newproduct.setExpiry_date(product1.getExpiry_date());
					newproduct.setBatch_no(product1.getBatch_no());
					newproduct.setLocation(product1.getLocation());
					newproduct.setExpectedDate(product4.getExpectedDate());
					newproduct.setReq_location(product4.getLocation());
					//   09 oct 2017 set catalogue id
					newproduct.setCatalogueid(product1.getCatalogueid());

					newproduct.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					newproduct.setUserid(loginInfo.getUserId());
					int result2 = pharmacyDAO.transferRequestedMedicine(parentid, newproduct);

					i++;
				}
			}

			ArrayList<String> stringist = indentDAO.getChildProductName(parentid);

			for (String prod_name : stringist) {
				double total_qty = indentDAO.getReqQtyFrmTemp(prod_name, parentid);
				Product product = indentDAO.getReqQtyFrmAct(parentid, prod_name);
				int status = 0;
				if (total_qty == (product.getQty()) || total_qty > (product.getQty())) {
					status = 1;
				} else if (total_qty > 0) {
					status = 2;
				} else {
					status = 0;
				}
				int result = inventoryProductDAO.updateChildTransferStatus(product.getId(), status);
				int res = indentDAO.updateChildTempTransferStatus(prod_name, parentid, status);
			}

			String userid = loginInfo.getUserId();
			int count = inventoryProductDAO.getCountTransferProd(parentid);
			int count1 = inventoryProductDAO.getCountTransferedProd(parentid);
			int all_med_status = 0;
			if (count == count1) {
				all_med_status = 1;
			} else {
				all_med_status = 0;
			}

			int r_status = 3;
			if (all_med_status == 0) {
				r_status = 6;
			}

			int result = inventoryProductDAO.updateParentProductStatus(parentid, all_med_status, r_status);

			session.setAttribute("prodidname1", "0");
			session.setAttribute("reqparentid1", "0");
			session.setAttribute("tcount", null);

			/*
			 * for (Product product3 : productForm.getPodata()) { String name =
			 * product3.getProd_name(); int id = product3.getId(); String qty =
			 * product3.getQty(); int res =
			 * inventoryProductDAO.updateTempPoStatus(id,qty); }
			 */

			/*
			 * ArrayList<Product> arrayList =
			 * inventoryProductDAO.getChildTempReqData(parentid); //int res =
			 * pharmacyDAO.deleteTempRequestedData(parentid); for (Product
			 * productx : arrayList) {
			 * productx.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo
			 * .getTimeZone())); productx.setUserid(loginInfo.getUserId()); int
			 * result2 =
			 * pharmacyDAO.transferRequestedMedicine(parentid,productx); }
			 */

			/*
			 * Product product =
			 * inventoryProductDAO.getParentTransferData(parentid);
			 */
			if (product4.getProcurementid() != null) {
				if (!product4.getProcurementid().equals("")) {
					int zyx = inventoryProductDAO.updateProcurmentStatus(product4.getProcurementid(), "1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferToTansferDash";
	}

	public String updatePendingAprove() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");

			String userid = loginInfo.getUserId();
			int result1 = inventoryProductDAO.updatePendingTransferLog(parentid, userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferToTansferDash";
	}

	public String addHandover_ToNew() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			String handover_to = request.getParameter("handover_to");
			int result = inventoryProductDAO.updateHandover_ToNew(parentid, handover_to);
			Product product = inventoryProductDAO.getParentTransferData(parentid);
			StringBuilder builder = new StringBuilder();
			builder.append("<label>Handover To: <span style='font-weight: normal;'>" + product.getHandover_to()
					+ "</span></label><br>");
			builder.append(
					"<input type='text'  class='hidden-print form-control' id='handover_to2' autofocus='autofocus' placeholder='Handover To' Style='width: 15%;background-color: beige;'><input type='button' value='Handover' class='btn btn-primary hidden-print' onclick='addHandover_ToNew("
							+ parentid + ")'>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String paymentreceivereport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String balance = productForm.getRem_balance();
			/*
			 * String location =(String) session.getAttribute("location");
			 * if(location==null){ location="0"; }
			 */

			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = productForm.getLocation();
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			productForm.setLocation(location);
			productForm.setIsfrombincard(1);

			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}

			if (balance == null) {
				balance = "0";
			}

			if (balance.equals("")) {
				balance = "0";
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Payment Receive Report", loginInfo.getUserId(), fromdate,
					todate, date, "paymentreceivereport");
			int count = inventoryProductDAO.getCountPaymentReceiveReport(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype(), balance);
			pagination.setPreperties(count);
			ArrayList<Product> salesReturnProduct = inventoryProductDAO.getPaymentReceiveReport(fromdate, todate,
					location, productForm.getSalereturn(), productForm.getBilltype(), balance, pagination);
			pagination.setPage_records(salesReturnProduct.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			/*
			 * int size= salesReturnProduct.size(); if(size>0){ double payment=
			 * salesReturnProduct.get(size-1).getPayment();
			 * productForm.setTotalReceived(DateTimeUtils.changeFormat(payment))
			 * ; } else { productForm.setTotalReceived("0.00"); }
			 * 
			 * if(size>0){ double totalbalance =
			 * salesReturnProduct.get(size-1).getTotalbalance();
			 * productForm.setTotalbalance(DateTimeUtils.changeFormat(
			 * totalbalance)); } else { productForm.setTotalbalance("0.00"); }
			 */

			Product product = inventoryProductDAO.getToalBillByPaymentDate(fromdate, todate, location,
					productForm.getSalereturn(), productForm.getBilltype(), balance);
			productForm.setTotalReceived(product.getTotal());
			productForm.setTotalbalance(product.getBalance());
			int size = salesReturnProduct.size();
			if (size > 0) {
				double totalbalance = salesReturnProduct.get(size - 1).getTotalAmt();
				productForm.setTotalbalance(DateTimeUtils.changeFormat(Math.round(totalbalance * 100.0) / 100.0));
			} else {
				productForm.setTotalbalance("0");
			}
			productForm.setSalesReturnProduct(salesReturnProduct);

			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			
			setLetterData(location, connection);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "paymentreceivereport";
	}

	public String itemwisestockreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			// ArrayList<Product>
			// productList=inventoryProductDAO.getProductListForItemWiseStockReport("2",location);
			ArrayList<Product> productList = new ArrayList<Product>();
			String product_id = productForm.getProduct_name();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();

			ArrayList<Product> itemWiseListReport = new ArrayList<Product>();
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					calendar.add(Calendar.DATE, 0);
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				calendar.add(Calendar.DATE, 0);
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());

			}

			if (product_id == null) {
				product_id = "";
			}

			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Item Wise Stock Report", loginInfo.getUserId(), fromdate,
					todate, date, "itemwisestockreport");
			itemWiseListReport = inventoryProductDAO.getItemWiseStockReportList(fromdate, todate, product_id, location);
			productForm.setItemWiseListReport(itemWiseListReport);
			// productForm.setProductList(productList);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);
			productForm.setProduct_name(product_id);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "itemwisestockreport";
	}

	public String transferreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			String filter_status = productForm.getFilter_status();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location_filter = productForm.getLocation_filter();
			String searhText = productForm.getSearchtext();
			// hisuserlist
			String userwise = productForm.getUserwise();
			String transferedlocation = productForm.getTransferedlocation();
			if (transferedlocation != null) {
				if (transferedlocation.equals("")) {
					transferedlocation = "0";
				}
			} else {
				transferedlocation = "0";
			}

			if (userwise != null) {
				if (userwise.equals("") || userwise.equals("0")) {
					userwise = null;
				}
			}

			if (searhText != null) {
				if (searhText.equals("")) {
					searhText = null;
				}
			}

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "0";
				}
			} else {
				filter_status = "0";
			}

			if (location_filter != null) {
				if (location_filter.equals("")) {
					location_filter = "0";
				}
			} else {
				location_filter = "0";
			}

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {

				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
					// todate = null;
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}

			String userid = loginInfo.getUserId();

			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);

			String location1 = "";
			if (ispharmacist) {
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				location1 = priscription.getLocation();
			} else {
				if (loginInfo.getJobTitle().equals("Admin")) {
					location1 = "0";
				} else {
					location1 = inventoryProductDAO.getHISUserLocation(userid);
				}

			}

			// Priscription priscription =
			// pharmacyDAO.getPharacyUsrLInfo(userid);
			// String location1 = priscription.getLocation();

			if (location1 == null) {
				location1 = "0";
			}
			//   28 sep 2017 pagination code
			// int count =
			// inventoryProductDAO.getCountIndentTransferLog(fromdate,todate,location1,filter_status,location_filter,searhText);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Indent Statement Report", loginInfo.getUserId(), fromdate,
					todate, date, "transferreport");
			int count = indentDAO.getCountIndentTransferLog(fromdate, todate, location1, filter_status, location_filter,
					searhText, userwise, transferedlocation);
			pagination.setPreperties(count);
			ArrayList<Product> parenttransferlist = indentDAO.getIndentTransferLog(fromdate, todate, location1,
					filter_status, location_filter, searhText, pagination, userwise, transferedlocation);
			productForm.setParenttransferlist(parenttransferlist);
			pagination.setPage_records(parenttransferlist.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			productForm.setUserid(loginInfo.getUserId());
			productForm.setFilter_status(filter_status);
			productForm.setCurr_location(location);
			productForm.setLocation_filter(location_filter);
			productForm.setSearchtext(searhText);
			// ArrayList<Master> locationlist =
			// inventoryProductDAO.getAllLocation(null);
			/*
			 * ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			 * productForm.setLocationlist(locationlist);//commented because user wise location for lmh 
			 */
			
			ArrayList<Master> locationlist=new ArrayList<Master>();
			//ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			if(loginInfo.isLmh()) {
			  if ( loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 ) {
				   locationlist = masterDAO.getAllLocation(null);
			  }else if(loginInfo.getShow_dept_opd_list().equals("0")) {
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(loginInfo.getId());
				String department=userProfile.getDiciplineName();
				locationlist = masterDAO.getAllLocation(department);    //for location wise @vspm requirement
			 }
		  }else {
			  locationlist = masterDAO.getAllLocation(null);
		  }
			productForm.setLocationlist(locationlist);
			ArrayList<UserProfile> userlist = userProfileDAO.getAllUserProfileList();
			productForm.setUserlist(userlist);
			productForm.setHidecatagoryid("1");
			productForm.setTransferedlocation(transferedlocation);
			
			setLetterData(null, connection);
			productForm.setIsfrombincard(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferreport";
	}

	// @ruchi user access report with access datetime ,who give access.
	public String usersaccessreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("User Access Report", loginInfo.getUserId(), fromdate,
					todate, date, "usersaccessreport");
			int count = pharmacyDAO.getCountAccessList();
			pagination.setPreperties(count);
			ArrayList<Product> accesslist = pharmacyDAO.getAccessList();
			productForm.setUsersaccess(accesslist);
			pagination.setPage_records(accesslist.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "usersaccessreport";
	}

	// @jitu for getting the product of store

	public String getstoreproducts() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			ArrayList<Product> list = new ArrayList<>();
			if(loginInfo.isLmh()){
				list =catalogueDAO.getAllProductListLMH(id);
			}else{
				list =catalogueDAO.getAllProductList(id);
			}
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("<label>Select Product</label>");
			if(loginInfo.isKalmegha()) {
				buffer.append("<select id='product_id' class='form-control chosen'  onchange='showqtylimit(this.value)' name='product_id' >");
			}else {
				buffer.append("<select id='product_id' class='form-control chosen'  name='product_id' >");
			}
			//buffer.append("<select id='product_id' class='form-control chosen'  name='product_id' >"); // onchange='setProdCatandType(this.value)'
			buffer.append("<option value='0'>Select Product</option>");
			for (Product product : list) {
				if(loginInfo.isLmh()){
					buffer.append("<option value='" + product.getId() + "'>" + product.getData() + "</option>");
				}else{
					buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");
				}
				
			}
			buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String setprodcatandtype() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			Product product = catalogueDAO.getProductDetails(id);
			String data = product.getCategory_id() + "~" + product.getSubcategory_id();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;

	}

	// @jitu to add to supplier return que
	public String addtoreturn() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String dateTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String data = request.getParameter("data");
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			boolean presentflag = false;
			String notpresentid = "0";
			for (String str : data.split(",")) {
				int product_id = Integer.parseInt(str);
				if (product_id == 0) {
					continue;
				}
				boolean flag1 = inventoryProductDAO.checkVendorPresentInSystem(str);
				if (!flag1) {
					notpresentid = notpresentid + "," + str;
					presentflag = true;
				} else {
					boolean flag = inventoryProductDAO.checkProdIDInReturnList(str);
					if (!flag) {
						int res = inventoryProductDAO.addToRroductReturntoSupplier(str, dateTime, location);
					}
				}

			}
			StringBuffer buffer = new StringBuffer();
			if (presentflag) {
				buffer.append("!!!Supplier not present for following product: ");
				int i = 0;
				for (String prodid : notpresentid.split(",")) {
					int product_id = Integer.parseInt(prodid);
					if (product_id == 0) {
						continue;
					}
					String productname = inventoryProductDAO.getProductNameFromID(prodid);
					if (i == 0) {
						buffer.append("" + productname + "(" + prodid + ")");
					} else {
						buffer.append("," + productname + "(" + prodid + ")");
					}
					i++;
				}
				buffer.append("!!!So this product not return!!!");
			} else {
				buffer.append("0");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	// @ jitu return que
	public String returnque() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Return Queue List")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Return Queue List");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("returnqueProduct");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Return Queue List");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Product> returnqueList = inventoryProductDAO.getTempReturnQueList(location);
			productForm.setReturnqueList(returnqueList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "returnque";
	}

	// @ Jitu For Return to Dashboard
	public String returnproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			String dateTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			for (Product product : productForm.getProducts()) {

				if (product == null) {
					continue;
				}
				String status = product.getStatus();
				if (status == null) {
					continue;
				}
				if (!status.equals("on")) {
					continue;
				}
				int id = product.getId();
				double qty = product.getQty();
				String productid = product.getProduct_id();
				String vendorid = product.getVendor_id();
				String procurementid = product.getProcurementid();
				if (procurementid.equals("")) {
					procurementid = "0";
				}
				product.setVendor_id(vendorid);
				product.setProduct_id(productid);
				product.setQty(qty);
				product.setDateTime(dateTime);
				product.setLocation(location);
				int res = procurementDAO.addReturnTempPo(productid, vendorid, qty, Integer.parseInt(procurementid),
						loginInfo.getLoginsessionid());
				res = procurementDAO.updateReturnQueStatus(id);

			}

			int grnreturnid = 0;
			//   22 NOV 2018 return product used porcurement table which
			// create problem so created new table store data temporray
			ArrayList<Product> poreturnlistByVendor = procurementDAO
					.getAllReturnListByVendor(loginInfo.getLoginsessionid());
			for (Product product : poreturnlistByVendor) {
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				grnreturnid = procurementDAO.saveParentGrnReturnData(product.getVendor_id(), date);
				ArrayList<Product> poReturnlist = procurementDAO.getReturnProductByVendor(product.getVendor_id(),
						loginInfo.getLoginsessionid());

				for (Product poproduct : poReturnlist) {
					poproduct.setDateTime(date);
					poproduct.setLocation(location);
					int procurementid = poproduct.getNewpo();
					int newprocurementid = 0;
					int newprodid = 0;
					if (procurementid == 0) {
						Product product2 = procurementDAO.getProcurementIdFromProductId(poproduct.getProduct_id());
						if (product2.getStatus() != null) {
							if (product2.getStatus().equals("1")) {
								newprodid = product2.getProdid();
								newprocurementid = product2.getProcid();
							}
						}
					} else {
						newprodid = Integer.parseInt(poproduct.getProduct_id());
						newprocurementid = procurementid;
					}
					poproduct.setProdid(newprodid);
					poproduct.setProcid(newprocurementid);
					poproduct.setProcurementid(String.valueOf(procurementid));
					poproduct.setGrnreturnid(String.valueOf(grnreturnid));
					poproduct.setUserid(loginInfo.getUserId());
					int res = inventoryProductDAO.addToReturnDashBoard(poproduct);
				}
			}
			int result = procurementDAO.deleteReturnProdTempData(loginInfo.getLoginsessionid());

			/*
			 * ArrayList<Product> productReturnList=
			 * inventoryProductDAO.getProductReturnList(location);
			 * productForm.setProductReturnList(productReturnList);
			 */

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "savereturndashboard";

	}

	public String returnproductdashboard() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Product Return Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Product Return Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("returnproductdashboardProduct");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Product Return Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			if (fromdate != null) {

				if (fromdate.equals("")) {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					calendar.add(Calendar.DATE, -30);
					fromdate = dateFormat.format(calendar.getTime());
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}

			} else {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {

				if (todate.equals("")) {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			} else {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}

			String searchtext = productForm.getSearchtext();
			if (searchtext != null) {
				if (searchtext.equals("")) {
					searchtext = null;
				}
			}

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			int count = inventoryProductDAO.getProductReturnCount(fromdate, todate, location, searchtext);
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> productReturnList = inventoryProductDAO.getProductReturnList(fromdate, todate, location,
					searchtext, pagination);
			pagination.setPage_records(productReturnList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			productForm.setProductReturnList(productReturnList);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			int todayreturn = inventoryProductDAO.getTodayReturnCount();
			productForm.setTodayreturn(todayreturn);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "returndashboard";
	}

	public String viewreturngrn() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Product Return To Supplier")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Product Return To Supplier");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Product Return To Supplier");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			connection = Connection_provider.getconnection();

			String grnreturnid = request.getParameter("grnreturnid");
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String vendorid = procurementDAO.getVendorIdfromGRNReturn(grnreturnid);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			productForm.setVendor(vendor.getName());
			productForm.setVendor_id(vendorid);
			productForm.setDate(DateTimeUtils.getCommencingDate1(date));
			ArrayList<Product> grnreturnProductList = procurementDAO.getAllGrnReturnProductList(grnreturnid);
			productForm.setGrnreturnProductList(grnreturnProductList);

			int size = grnreturnProductList.size();
			if (size > 0) {
				int isinadjustment = grnreturnProductList.get(size - 1).getIsinadjustment();
				if (isinadjustment == 1) {
					return "isinadjusterrorpage";
				}
			}

			productForm.setSecurity_no("SECON" + grnreturnid);

			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
			if (vendor.getState() != null) {
				if (vendor.getState().equals("")) {
					vendor.setState("0");
				}
			} else {
				vendor.setState("0");
			}
			if (priscription.getState() != null) {
				if (priscription.getState().equals("")) {
					priscription.setState("0");
				}
			} else {
				priscription.setState("0");
			}
			boolean flag = false;

			if (priscription.getState().equals(vendor.getState())) {
				flag = true;
			}

			double total = 0;
			double disctotal = 0;
			double gst = 0;
			double netpayamt = 0;
			double cgst = 0, igst = 0, sgst = 0;
			for (Product product : grnreturnProductList) {
				total = total + Double.parseDouble(product.getTotal());
				disctotal = disctotal + Double.parseDouble(product.getDiscount());
				gst = gst + +Double.parseDouble(product.getGstamount());
			}
			if (flag) {
				cgst = gst / 2;
				sgst = cgst;
				igst = 0;
				productForm.setIsfromsamestate("1");
			} else {
				cgst = 0;
				sgst = cgst;
				igst = gst;
				productForm.setIsfromsamestate("0");
			}
			netpayamt = (total + cgst + sgst + igst) - disctotal;
			productForm.setGrnreturnid(grnreturnid);
			productForm.setTotal_amount(total);
			productForm.setTotal_amt("" + Math.round(netpayamt));
			productForm.setSale_discount(DateTimeUtils.changeFormat(Math.round(disctotal * 100.0) / 100.0));
			productForm.setCgst(DateTimeUtils.changeFormat(Math.round(cgst * 100.0) / 100.0));
			productForm.setSgst(DateTimeUtils.changeFormat(Math.round(sgst * 100.0) / 100.0));
			productForm.setIgst(DateTimeUtils.changeFormat(Math.round(igst * 100.0) / 100.0));
			productForm.setAlltotvatTotal(DateTimeUtils.changeFormat(Math.round(gst * 100.0) / 100.0));
			//   23 NOV 2018
			// vat calsulation by % wise
			ArrayList<Product> vatAllocationList = new ArrayList<Product>();
			vatAllocationList = procurementDAO.getVatAllocationOfReturn(grnreturnid);
			productForm.setVatAllocationList(vatAllocationList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "viewreturngrn";
	}

	public String printreturngrn() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String status = request.getParameter("status");
			String grnreturnid = request.getParameter("grnreturnid");
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);

			if (status != null) {
				if (status.equals("")) {
					status = "1";
				}
			} else {
				status = "1";
			}

			String vendorid = procurementDAO.getVendorIdfromGRNReturn(grnreturnid);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			productForm.setVendor(vendor.getName());
			productForm.setContact(vendor.getMobile_pri());
			productForm.setVendor_id(vendorid);
			String security_no = "";
			String security_date = "";
			String date = "";
			String returnedby = "";
			String notes = "";
			String voucherno = "";
			ArrayList<Product> grnreturnProductList = procurementDAO.getAllGrnReturnProductListPrint(grnreturnid);
			Product products = procurementDAO.getParentReturnProductData(grnreturnid);
			productForm.setGrnreturnProductList(grnreturnProductList);

			double total = 0, netammt = 0, gst = 0;
			int status1 = 0;
			String location = "0";
			for (Product product : grnreturnProductList) {
				total = total + Double.parseDouble(product.getTotal());
				security_no = product.getSecurity_no();
				security_date = product.getSecurity_date();
				date = product.getDate();
				notes = product.getNotes();
				voucherno = product.getVoucherno();
				returnedby = product.getUserid();
				netammt = netammt + product.getNetammt();
				gst = gst + Double.valueOf(product.getGstamount());
				status1 = Integer.parseInt(product.getStatus());
				location = product.getProductlocation();
			}

			if (date != null) {
				if (!date.equals("")) {
					date = DateTimeUtils.getCommencingDate1(date);
				}
			}

			productForm.setGstamt(DateTimeUtils.changeFormat(gst / 2));
			productForm.setNetammt(DateTimeUtils.changeFormat(netammt));
			productForm.setTotal_amt(DateTimeUtils.changeFormat(total));
			productForm.setSecurity_date(security_date);
			productForm.setSecurity_no(security_no);
			productForm.setNotes(notes);
			productForm.setDate(date);
			productForm.setCreatedby(returnedby);
			productForm.setPrintedby(loginInfo.getUserId());
			productForm.setVoucherno(voucherno);

			/*
			 * Clinic clinic = new Clinic(); ClinicDAO clinicDAO = new
			 * JDBCClinicDAO(connection); clinic =
			 * clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			 * UserProfileDAO userProfileDAO= new
			 * JDBCUserProfileDAO(connection);
			 * 
			 * UserProfile userProfile =
			 * userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			 * // procurementForm.setClinicName(userProfile.getClinicname());
			 * productForm.setClinicName(clinic.getClinicName());
			 * productForm.setClinicaddress(userProfile.getAddress());
			 * productForm.setLandLine(userProfile.getPhone());
			 * productForm.setFullname(userProfile.getFullname());
			 * productForm.setEmail(userProfile.getEmail());
			 * productForm.setPlace(userProfile.getCity());
			 * productForm.setWebsiteUrl(userProfile.getWebsite());
			 */

			if (location != null) {
				if (location.equals("32") || location.equals("33") || location.equals("34")) {
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
					productForm.setClinicName(userProfile.getClinicname());
					productForm.setClinicaddress(userProfile.getAddress());
					productForm.setLandLine(userProfile.getPhone());
					productForm.setFullname(userProfile.getFullname());
					productForm.setEmail(userProfile.getEmail());
					productForm.setPlace(userProfile.getCity());
					productForm.setWebsiteUrl(userProfile.getWebsite());
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					productForm.setClinicLogo(clinic.getUserImageFileName());
				} else {
					// letter head
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
					accountsDAO.getLocationAddress(loginInfo.getClinicid());
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					productForm.setClinicName(clinic.getClinicName());
					productForm.setClinicOwner(clinic.getClinicOwner());
					productForm.setOwner_qualification(clinic.getOwner_qualification());
					productForm.setLocationAdressList(locationAdressList);
					// procurementForm.setAddress(clinic.getAddress());
					productForm.setLandLine(clinic.getLandLine());
					productForm.setClinicemail(clinic.getEmail());
					productForm.setWebsiteUrl(clinic.getWebsiteUrl());
					productForm.setClinicLogo(clinic.getUserImageFileName());
				}
			} else {
				// letter head
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				productForm.setClinicName(clinic.getClinicName());
				productForm.setClinicOwner(clinic.getClinicOwner());
				productForm.setOwner_qualification(clinic.getOwner_qualification());
				productForm.setLocationAdressList(locationAdressList);
				// procurementForm.setAddress(clinic.getAddress());
				productForm.setLandLine(clinic.getLandLine());
				productForm.setClinicemail(clinic.getEmail());
				productForm.setWebsiteUrl(clinic.getWebsiteUrl());
				productForm.setClinicLogo(clinic.getUserImageFileName());
			}

			double netamt = Math.round(products.getNetammt());
			productForm.setTotal_amt("" + netamt);
			productForm.setSale_discount(products.getDiscount());
			productForm.setCgst(products.getCgst());
			productForm.setSgst(products.getSgst());
			productForm.setIgst(products.getIgst());
			productForm.setTotalDebit(products.getTotal());
			ArrayList<Product> vatAllocationList = procurementDAO.getVatAllocationOfReturnPrint(grnreturnid);
			productForm.setVatAllocationList(vatAllocationList);
			productForm.setStatus(status);
			productForm.setAprovedamt(DateTimeUtils.changeFormat(products.getAprovedamt()));
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "printreturngrn";
	}

	public String updatereturnproduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String grnreturnid = request.getParameter("grnreturnid");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String sec_outward = request.getParameter("security_no");
			String security_date = request.getParameter("security_date");
			String total_amt = request.getParameter("total_amt");

			String invoicedate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String dateTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String notes = request.getParameter("remark");
			Product product = new Product();
			product.setSecurity_no(sec_outward);
			product.setSecurity_date(security_date);
			product.setDate(invoicedate);
			product.setDateTime(dateTime);
			product.setNotes(notes);
			product.setGrnreturnid(grnreturnid);
			product.setTotalamt(total_amt);
			//   23 NOV 2018 save vat,total,igst,cgst,sgst,total,subtotal
			double total_amount = productForm.getTotal_amount();
			String sale_discount = productForm.getSale_discount();
			String cgst = productForm.getCgst();
			String sgst = productForm.getSgst();
			String igst = productForm.getIgst();
			String alltotvatTotal = productForm.getAlltotvatTotal();
			int result = inventoryProductDAO.updateParentReturnProductData(grnreturnid, total_amount, sale_discount,
					cgst, sgst, igst, alltotvatTotal, total_amt, notes);

			int res = procurementDAO.updateGrnReturnDataStatus(product);
			ArrayList<Product> grnreturnProductList = procurementDAO.getAllGrnReturnProductList(grnreturnid);
			for (Product pmaster : grnreturnProductList) {
				double remainstock = Double.parseDouble(pmaster.getStock()) - (pmaster.getQty());
				if (remainstock < 0) {
					remainstock = 0;
				}
				int previousstock = inventoryProductDAO.getPreviousStock(pmaster.getProduct_id());
				res = inventoryProductDAO.updateStockToReturn(pmaster.getProduct_id(), remainstock);
				// 23 NOV 2018 Store discount cgst, sgst,igst, total,vat
				String vat = request.getParameter("vat" + pmaster.getId());
				String discper = request.getParameter("discper" + pmaster.getId());
				String discount = request.getParameter("discount" + pmaster.getId());
				String gstamount = request.getParameter("gstamount" + pmaster.getId());
				String total = request.getParameter("newtotal" + pmaster.getId());
				String voucherno = request.getParameter("voucherno" + pmaster.getId());
				String remainfreereturnqty = request.getParameter("remainfreereturnqty" + pmaster.getId());
				String returnqty = request.getParameter("returnqty" + pmaster.getId());
				String returnfreeqty = request.getParameter("returnfreeqty" + pmaster.getId());
				String proc_childid = request.getParameter("proc_childid" + pmaster.getId());
				res = inventoryProductDAO.updateReturnProductData(pmaster.getId(), vat, discper, discount, gstamount,
						total, voucherno, returnqty, returnfreeqty);

				int currentstock = inventoryProductDAO.getPreviousStock(pmaster.getProduct_id());
				int changeqty = 0;
				if (currentstock > previousstock) {
					changeqty = currentstock - previousstock;
				} else {
					changeqty = previousstock - currentstock;
				}
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int qtyinout = 1;
				String source = "Return To Supplier";
				int res1 = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(), datetime,
						pmaster.getReturnlocation(), qtyinout, pmaster.getProduct_id(), pmaster.getCatalogueid(),
						source, currentstock, previousstock, changeqty, "0", grnreturnid, 0, 0, 0, "0");

				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(pmaster.getProduct_id(), date);
				if (!checkopningstockexist) {
					int r = pharmacyDAO.saveOpeningStock(pmaster.getProduct_id(), date, previousstock, "0");
				}

				int returnfreeqty1 = 0;
				int remainfreereturnqty1 = 0;
				if (returnfreeqty != null) {
					if (returnfreeqty.equals("")) {
						returnfreeqty1 = Integer.parseInt(returnfreeqty);
					}
				}
				if (remainfreereturnqty != null) {
					if (remainfreereturnqty.equals("")) {
						remainfreereturnqty1 = Integer.parseInt(remainfreereturnqty);
					}
				}
				int totalfreereturn = remainfreereturnqty1 - returnfreeqty1;
				int resss = inventoryProductDAO.updateRemainFreeQty(proc_childid, totalfreereturn);

				// reorder level // 
				// min /max reorder level code by // 
				String catalogueid = pmaster.getCatalogueid();
				Product pmaster1 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
				int minorder = Integer.parseInt(pmaster1.getMinorder());
				double allstock = inventoryProductDAO.getTotalStockProduct(catalogueid);
				//   01/10/2019 commented because min order is in qty
				double totstockAfterTransfer = allstock - changeqty;
				if (totstockAfterTransfer <= minorder) {
					// add to po que list
					product.setDate(date);
					int maxorder = Integer.parseInt(pmaster1.getMaxorder());
					double orderqty = maxorder - totstockAfterTransfer;
					if (orderqty < 0) {
						orderqty = 0;
					}
					product.setQty((orderqty));
					// add to po que
					int ress = procurementDAO.saveNewTempPo(product);
				}
			}

			// For Storing % wise GST data
			int d = procurementDAO.deleteReturnVatAllocations(grnreturnid);

			if (productForm.getVatallocations() != null) {

				for (Product product2 : productForm.getVatallocations()) {
					product2.setProcurementid(grnreturnid);
					int r = procurementDAO.saveReturnPoVatAllocations(product2);
				}

			}

			// For Vendor Account
			String vendorid = procurementDAO.getVendorIdfromGRNReturn(grnreturnid);
			product.setVendor_id(vendorid);
			res = procurementDAO.saveReturnVendorAcccount(product);
			String location = (String) session.getAttribute("location");

			if (location == null) {
				location = "0";
			}
			/*
			 * ArrayList<Product> productReturnList=
			 * inventoryProductDAO.getProductReturnList(location);
			 * productForm.setProductReturnList(productReturnList);
			 */

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "savereturndashboard";
	}

	public String patientconsumptionreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String searchtext = productForm.getSearchtext();
			String hisuserfilter = productForm.getHisuserfilter();
			String hisdepartmentfilter = productForm.getHisdepartmentfilter();
			if(loginInfo.isLmh()) {
			if (loginInfo.getUserType() == 2 || loginInfo.getJobTitle().equals("Accounts")) {
				 hisdepartmentfilter=hisdepartmentfilter;
            }else {
            	 hisdepartmentfilter = inventoryProductDAO.getHISUserLocation(loginInfo.getUserId());
            }
		 }
			String isfromcathlab = request.getParameter("isfromcathlab");
			if (isfromcathlab != null) {
				if (isfromcathlab.equals("")) {
					isfromcathlab = "0";
				}
			} else {
				isfromcathlab = productForm.getIsfromcathlab();
				if (isfromcathlab != null) {
					if (isfromcathlab.equals("")) {
						isfromcathlab = "0";
					}
				} else {
					isfromcathlab = "0";
				}
			}
			productForm.setIsfromcathlab(isfromcathlab);

			if (fromdate != null) {
				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());
			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}

			if (searchtext != null) {
				if (searchtext.equals("")) {
					searchtext = null;
				}
			}

			if (hisuserfilter != null) {
				if (hisuserfilter.equals("")) {
					hisuserfilter = "0";
				}
			} else {
				hisuserfilter = "0";
			}

			if (hisdepartmentfilter != null) {
				if (hisdepartmentfilter.equals("")) {
					hisdepartmentfilter = "0";
				}
			} else {
				hisdepartmentfilter = "0";
			}

			//   28 sep 2017 Pagination code added
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Patient Consumption Report", loginInfo.getUserId(),
					fromdate, todate, date, "patientconsumptionreport");

			ArrayList<Product> salesReturnProduct = new ArrayList<Product>();
			int count = 0;
			if (isfromcathlab.equals("1")) {
				count = indentDAO.getCountCathLabConsumptionReport(fromdate, todate, searchtext);
				pagination.setPreperties(count);
				salesReturnProduct = indentDAO.getCathLabConsumptionReport(fromdate, todate, pagination, searchtext);
			} else {
				if (hisuserfilter.equals("0")) {
					count = indentDAO.getCountPatientConsumptionReport(fromdate, todate, searchtext);
					pagination.setPreperties(count);
					salesReturnProduct = indentDAO.getPatientConsumptionReport(fromdate, todate, pagination,
							searchtext,hisdepartmentfilter);
				} else if (hisuserfilter.equals("1")) {
					count = indentDAO.getCountUserConsumptionReport(fromdate, todate, searchtext);
					pagination.setPreperties(count);
					salesReturnProduct = indentDAO.getUserConsumptionReport(fromdate, todate, pagination, searchtext);
				} else {
					count = indentDAO.getCountUserPatientConsumptionReport(fromdate, todate, searchtext,
							hisdepartmentfilter);
					pagination.setPreperties(count);
					salesReturnProduct = indentDAO.getUserPatientConsumptionReport(fromdate, todate, pagination,
							searchtext, hisdepartmentfilter);
				}
			}
			productForm.setSalesReturnProduct(salesReturnProduct);
			pagination.setPage_records(salesReturnProduct.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setHisuserfilter(hisuserfilter);
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			int size = salesReturnProduct.size();
			if (size > 0) {
				String total = salesReturnProduct.get(size - 1).getTotal();
				productForm.setTotal_amt(total);
			} else {
				productForm.setTotal_amt("0");
			}

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> issuedeptlist = masterDAO.getAllLocation(null);
			productForm.setHisdepartmentfilter(hisdepartmentfilter);		
			productForm.setIssuedeptlist(issuedeptlist);
			
			productForm.setIsfrombincard(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "patientconsumptionreport";
	}

	public String cancelrequestedentry() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			String delete_reason = request.getParameter("delete_reason");
			String parentid = request.getParameter("parentid");
			String userid = loginInfo.getUserId();
			String dateTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = indentDAO.cancelRequestedEntry(id, delete_reason, userid, dateTime);

			ArrayList<Product> arrayList = inventoryProductDAO.getChildTranfserData(parentid);
			int i = 1;
			StringBuffer buffer = new StringBuffer();
			for (Product product : arrayList) {
				buffer.append("<tr>");
				buffer.append("<td>" + i + "</td>");
				buffer.append("<td>" + product.getProduct_id() + "</td>");
				buffer.append("<td>" + product.getProduct_name() + "</td>");
				buffer.append("<td>" + product.getAvail_qty() + "</td>");
				buffer.append("<td>" + product.getStock() + "</td>");
				buffer.append("<td>" + product.getExpectedDate() + "</td>");
				buffer.append("<td>" + product.getComment() + "</td>");
				if (product.getTotaltransferqt() == null) {
					buffer.append("<td></td>");
				} else {
					buffer.append("<td>" + product.getTotaltransferqt() + "</td>");
				}

				if (product.getStatus().equals("0")) {
					if (product.getCancel_req().equals("0")) {
						buffer.append("<td><span><b>Not Transferred</b></span></td>");
					} else {
						buffer.append("<td><span><b>Cancelled</b></span></td>");
					}
				} else {
					buffer.append("<td><span><b>Transferred</b></span></td>");
				}
				if (product.getCancel_req_note() == null) {
					buffer.append("<td></td>");
				} else {
					buffer.append("<td>" + product.getCancel_req_note() + "</td>");
				}

				if (product.getStatus().equals("0")) {
					if (product.getCancel_req().equals("0")) {
						buffer.append("<td><a href='#' onclick='cancelRequestedRequest(" + product.getId() + ","
								+ product.getParentid() + ")'><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
					} else {
						buffer.append("<td></td>");
					}
				} else {
					buffer.append("<td></td>");
				}
				buffer.append("</tr>");
				i++;
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String indentreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			String filter_status = productForm.getFilter_status();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location_filter = productForm.getLocation_filter();
			String searhText = productForm.getSearchtext();
			// hisuserlist
			String userwise = productForm.getUserwise();

			if (userwise != null) {
				if (userwise.equals("") || userwise.equals("0")) {
					userwise = null;
				}
			}

			if (searhText != null) {
				if (searhText.equals("")) {
					searhText = null;
				}
			}

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "0";
				}
			} else {
				filter_status = "0";
			}

			if (location_filter != null) {
				if (location_filter.equals("")) {
					location_filter = "0";
				}
			} else {
				location_filter = "0";
			}

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {

				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);

				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}

			String userid = loginInfo.getUserId();

			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);

			String location1 = "";
			if (ispharmacist) {
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				location1 = priscription.getLocation();
			} else {
				if (loginInfo.getJobTitle().equals("Admin")) {
					location1 = "0";
				} else {
					location1 = inventoryProductDAO.getHISUserLocation(userid);
				}

			}

			if (location1 == null) {
				location1 = "0";
			}
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int result = chargesReportDAO.saveMisReportLog("Indent Report", loginInfo.getUserId(), fromdate, todate,
					date, "indentreport");
			int count = indentDAO.getNewCountIndentTransferLog(fromdate, todate, location1, filter_status,
					location_filter, searhText, userwise);
			pagination.setPreperties(count);
			ArrayList<Product> parenttransferlist = indentDAO.getIndentLog(fromdate, todate, location1, filter_status,
					location_filter, searhText, pagination, userwise);
			productForm.setParenttransferlist(parenttransferlist);
			pagination.setPage_records(parenttransferlist.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			productForm.setUserid(loginInfo.getUserId());
			productForm.setFilter_status(filter_status);
			productForm.setCurr_location(location);
			productForm.setLocation_filter(location_filter);
			productForm.setSearchtext(searhText);

			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationlist(locationlist);

			ArrayList<UserProfile> userlist = userProfileDAO.getAllUserProfileList();
			productForm.setUserlist(userlist);
			productForm.setHidecatagoryid("1");
			
			setLetterData(null, connection);
			productForm.setIsfrombincard(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "indentreport";
	}

	public String getsubproducttype() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String subid = request.getParameter("id");
			StringBuffer buffer = new StringBuffer();
			ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);
			buffer.append("<label>Sub Category</label>");
			buffer.append("<select name='subcategory'  class='form-control chosen' id='subcategory' >");
			buffer.append("<option value='0'>Select Sub Category</option>");
			for (Product product : subcategoryList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			buffer.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;
	}

	public String changecataloguerate() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String product_id = request.getParameter("product_id");
			String rate = request.getParameter("rate");
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String userid = loginInfo.getUserId();
			String previousrate = procurementDAO.getCatalogueRate(product_id);

			int res = procurementDAO.updateCatalogueRate(product_id, rate);
			if (res > 0) {
				int res1 = procurementDAO.saveCatalogueRateChangeLog(product_id, rate, previousrate, datetime, userid);
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String bomkitdashboard() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Cathlab Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Cathlab Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("bomkitdashboardProduct");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Cathlab Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			String location = "0";

			String isfromcathlab = "1";
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();

			if (isfromcathlab.equals("1")) {
				location = "105";
				productForm.setLocation(location);
				session.setAttribute("location", location);
			}

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());

			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					fromdate = dateFormat.format(cal.getTime());

				}
			}
			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());

			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());

				}
			}
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			int count = inventoryProductDAO.geTotalCathLabCount(fromdate, todate);
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> productList = inventoryProductDAO.geTotalCathLabList(pagination, fromdate, todate);

			pagination.setPage_records(productList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setProductList(productList);

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			productForm.setFromdate(fromdate);

			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setTodate(todate);

			ArrayList<Product> allWillExpireProduct = new ArrayList<Product>();
			productForm.setAllWillExpireProduct(allWillExpireProduct);
			productForm.setCount(allWillExpireProduct.size());
			ArrayList<Master> cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
			productForm.setCellList(cellList);
			ArrayList<ShelfMaster> shelflist = shelfMasterDAO.getallShelf();
			productForm.setShelflist(shelflist);

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> issuedeptlist = masterDAO.getAllLocation(null);
			productForm.setIssuedeptlist(issuedeptlist);

			DischargeOutcomeDAO dischargeOutcomeDAO = new JDBCDischargeOutcomeDAO(connection);
			ArrayList<Master> procedurelist = dischargeOutcomeDAO.getNewChargeTypeListProc();
			productForm.setProcedurelist(procedurelist);

			productForm.setIsfromcathlab(isfromcathlab);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			ArrayList<Product> cathtemplist = indentDAO.getCathlabTemplateList("0");
			productForm.setCathtemplist(cathtemplist);

			ArrayList<Product> newproductlist = inventoryProductDAO.getProductData(location);
			productForm.setNewproductlist(newproductlist);

			ArrayList<Location> locationList = notAvailableSlotDAO.getLocationList(loginInfo.getId());
			productForm.setLocationList(locationList);

			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<AppointmentType> additionalChargeList = appointmentDAO.getAdditionalChaergeTypeList("");
			productForm.setAdditionalChargeList(additionalChargeList);

			ArrayList<Master> masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			productForm.setMasterChageTypeList(masterChageTypeList);
			productForm.setMasterchargetype("Additional Charge");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "bomkitdashboard";
	}

	public String bomkittemplate() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			int count = inventoryProductDAO.getTemplateCathLabCount();
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> productList = inventoryProductDAO.getTemplateCathLabList(pagination);

			pagination.setPage_records(productList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setProductList(productList);

			ArrayList<Product> newproductlist = inventoryProductDAO.getCathlabProductData("105");
			productForm.setNewproductlist(newproductlist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "bomkittemplate";
	}

	public String deletecathlabtemplate() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String userid = loginInfo.getUserId();
			int res = inventoryProductDAO.deleteCathlabTemplate(id, datetime, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bomkittemplatedash";
	}

	public String supplierpaymentreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = productForm.getVendor_id();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			productForm.setIsfrombincard(1);
			ArrayList<Product> productWiseReport = new ArrayList<Product>();
			if (fromdate != null) {
				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());
			}
			if (todate != null) {
				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}
			if (vendorid != null) {
				if (vendorid.equals("")) {
					vendorid = "0";
				}
			} else {
				vendorid = "0";
			}

			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int count = inventoryProductDAO.getTotalSupplierPaymentReportCount(fromdate, todate, vendorid);
			// int count =0;
			pagination.setPreperties(count);
			ArrayList<Procurement> supplierpaymentlist = inventoryProductDAO.getSupplierWisePaymentList(fromdate,
					todate, vendorid, pagination);
			productForm.setTotalRecords(count);
			pagination.setTotal_records(supplierpaymentlist.size());
			pagination.setPage_records(supplierpaymentlist.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));

			productForm.setSupplierpaymentlist(supplierpaymentlist);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			// lettrehead
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "supplierpaymentreport";
	}

	public String getretrurnnetamt() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String grnreturnid = request.getParameter("returngrnid");
			Product products = procurementDAO.getParentReturnProductData(grnreturnid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + products.getNetammt());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String saveaprovereturngrnamt() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String aprovereturnid = request.getParameter("aprovereturnid");
			String aprovereturnbal = request.getParameter("aprovereturnbal");
			String totalenteredpayamt = request.getParameter("totalenteredpayamt");
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = procurementDAO.updateReturnProductStatus(aprovereturnid, "2");
			res = procurementDAO.updateVendorReturnAccount(aprovereturnid, totalenteredpayamt);
			int result = procurementDAO.updateReturnParentProductStatus(aprovereturnbal, aprovereturnid,
					totalenteredpayamt, loginInfo.getUserId(), date);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "returndashboardmethod";
	}

	public String deletereturntosupplier() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String parentid = request.getParameter("parentid");
			String delete_reason = request.getParameter("delete_reason");
			String userid = loginInfo.getUserId();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = inventoryProductDAO.cancelReturnToSupplierReq(parentid, delete_reason, userid, datetime);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String inventoryOpeningClosingBycatalogue() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromDate = productForm.getFromdate();
			String toDate = productForm.getTodate();
			String searchbyprodname = productForm.getSearchbyprodname();
			// String isfromcathlab = request.getParameter("isfromcathlab");
			String location_filter = productForm.getLocation_filter();
			String category_id = productForm.getCategory_id();
			String filter_status = productForm.getFilter_status();
			String iscalculationbase = request.getParameter("iscalculationbase");
			iscalculationbase = DateTimeUtils.isNull(iscalculationbase);

			if (iscalculationbase.equals("")) {
				iscalculationbase = "0";
			}
			productForm.setIscalculationbase(iscalculationbase);
			/*
			 * if(location_filter!=null){ if(location_filter.equals("") ||
			 * location_filter.equals("0")){ location_filter="32"; } }else{
			 * location_filter="32"; }
			 */
			location_filter = DateTimeUtils.numberCheck(location_filter);
			if (category_id != null) {
				if (category_id.equals("")) {
					category_id = "0";
				}
			} else {
				category_id = "0";
			}

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "1";
				}
			} else {
				filter_status = "1";
			}
			productForm.setFilter_status(filter_status);
			productForm.setLocation_filter(location_filter);
			productForm.setCategory_id(category_id);
			productForm.setIsfrombincard(1);
			
			//String filter_bydate = productForm.getFilter_bydate();
			
			/*
			 * if (isfromcathlab != null) { if (isfromcathlab.equals("")) {
			 * isfromcathlab = "0"; } } else { isfromcathlab =
			 * productForm.getIsfromcathlab(); if (isfromcathlab != null) { if
			 * (isfromcathlab.equals("")) { isfromcathlab = "0"; } } else {
			 * isfromcathlab = "0"; } }
			 * productForm.setIsfromcathlab(isfromcathlab);
			 */

			/*
			 * String ismonthlyreport = request.getParameter("ismonthlyreport");
			 * if (ismonthlyreport != null) { if (ismonthlyreport.equals("")) {
			 * ismonthlyreport = "0"; } } else { ismonthlyreport =
			 * productForm.getIsmonthlyreport(); if (ismonthlyreport != null) {
			 * if (ismonthlyreport.equals("")) { ismonthlyreport = "0"; } } else
			 * { ismonthlyreport = "0"; } }
			 */
			int maxlimit = 0, minlimit = 0;
			/*
			 * int limit = productForm.getCountval();
			 * productForm.setPage(limit); productForm.setCountval(limit); limit
			 * = limit * 10; int maxlimit = 0, minlimit = 0; maxlimit = limit;
			 * minlimit = limit - 10;
			 */
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());

			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
			}

			if (searchbyprodname != null) {
				if (searchbyprodname.equals("")) {
					searchbyprodname = null;
				}
			}
			
			String filter_bydate = productForm.getFilter_bydate();
			
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
			Calendar cal2 = Calendar.getInstance(); 
			String todateyear = dateFormat2.format(cal2.getTime());
			
			DateFormat dateFormat1 = new SimpleDateFormat("MM");
			Calendar cal1 = Calendar.getInstance(); 
			String tomonth = dateFormat1.format(cal1.getTime());
			
			if(DateTimeUtils.isNull(filter_bydate).equals("")){
				if(Integer.parseInt(tomonth)>=4){
					filter_bydate = todateyear+"_"+(Integer.parseInt(todateyear)+1);
				}else{
					filter_bydate = (Integer.parseInt(todateyear)-1)+"_"+todateyear;
				}
			}else{
				filter_bydate = filter_bydate.split("-")[0]+"_"+filter_bydate.split("-")[1];
			}
			int currYear = Integer.parseInt(todateyear);
			int preYear = Integer.parseInt(filter_bydate.split("_")[0]);
			int lateYear = Integer.parseInt(filter_bydate.split("_")[1]);
			boolean flag =true;
			if(preYear<=currYear){
				if(lateYear<=(currYear+1)){
					
				}else{
					//greater
					flag = false;	
				}
			}else{
				//greater
				flag = false;
			}
			
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromDate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(toDate));
			int result = chargesReportDAO.saveMisReportLog("Inventory Opening Closing by Catalogue",
					loginInfo.getUserId(), fromDate, toDate, date, "inventoryOpeningClosingByCatalogue");

			ArrayList<Product> openingstockList = new ArrayList<Product>();
			int count = 0;
			if (!location_filter.equals("0")) {
				if (iscalculationbase.equals("1") && flag) {
//					Product productcount = inventoryProductDAO.getCountOfOpeningClosingCatalogue(fromDate, toDate,
//							searchbyprodname, category_id, location_filter);
					//count = productcount.getId();
					count = 0;
					//pagination.setPreperties(count);
					/*openingstockList = inventoryProductDAO.getListOfOpeningClosingCatalogue(fromDate, toDate,
							pagination, searchbyprodname, category_id, location_filter, productcount);*/
					String tablename="product_stock_log"+filter_bydate+"";
					boolean checkView = inventoryProductDAO.checkViewPresentOfTable(tablename);
					if(!checkView){
						String viewFromDate = filter_bydate.split("_")[0]+"-"+"04"+"-"+"01";
						String viewToDate =  filter_bydate.split("_")[1]+"-"+"03"+"-"+"31";
						inventoryProductDAO.createViewOfTable("product_stock_log",tablename,viewFromDate,viewToDate);
					}
					count = inventoryProductDAO.getCountOfOpeningClosingCatalogue1(fromDate, toDate,
							searchbyprodname, category_id, location_filter, filter_bydate);
					pagination.setPreperties(count);
					openingstockList = inventoryProductDAO.getListOfOpeningClosingCatalogue(fromDate, toDate,
							pagination, searchbyprodname, category_id, location_filter, null,filter_bydate);
					productForm.setOpeningstockList(openingstockList);
					Product product = openingstockList.get(openingstockList.size() - 1);
					productForm.setOpentotalcount(product.getOpentotalcount());
					pagination.setPage_records(openingstockList.size());
					productForm.setTotalRecords(count);
					productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
					productForm.setTotalopeningstock(product.getTotalopeningstock());
					productForm
							.setTotalopeingstockvalue(Math.round(product.getTotalopeingstockvalue() * 100.0) / 100.0);
					productForm.setTotalqtyin(product.getTotalqtyin());
					productForm.setTotalqtyinvalue(Math.round(product.getTotalqtyinvalue() * 100.0) / 100.0);
					productForm.setTotalqtyout(product.getTotalqtyout());
					productForm.setTotalstockvalue(Math.round(product.getTotalstockvalue() * 100.0) / 100.0);
					productForm.setTotalssaleprice(Math.round(product.getTotalssaleprice() * 100.0) / 100.0);
					productForm.setTotalclosingstock(product.getTotalclosingstock());
					productForm.setTotalclosingvalue(Math.round(product.getTotalclosingvalue() * 100.0) / 100.0);
					productForm.setTotalunknownvalue(Math.round(product.getTotalunknownvalue() * 100.0) / 100.0);
					productForm.setTotalunknownqty(product.getTotalunknownqty());
				} else {
					count = inventoryProductDAO.getCountOpeningStockListByCatalogueWiseNew(fromDate, toDate, pagination,
							minlimit, maxlimit, searchbyprodname, category_id, location_filter);
					pagination.setPreperties(count);
					openingstockList = inventoryProductDAO.getOpeningStockListByCatalogueWiseNew(fromDate, toDate,
							pagination, minlimit, maxlimit, searchbyprodname, category_id, location_filter);
					productForm.setOpeningstockList(openingstockList);
					Product product = openingstockList.get(openingstockList.size() - 1);
					productForm.setOpentotalcount(product.getOpentotalcount());
					pagination.setPage_records(openingstockList.size());
					productForm.setTotalRecords(count);
					productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
					productForm.setTotalopeningstock(product.getTotalopeningstock());
					productForm
							.setTotalopeingstockvalue(Math.round(product.getTotalopeingstockvalue() * 100.0) / 100.0);
					productForm.setTotalqtyin(product.getTotalqtyin());
					productForm.setTotalqtyinvalue(Math.round(product.getTotalqtyinvalue() * 100.0) / 100.0);
					productForm.setTotalqtyout(product.getTotalqtyout());
					productForm.setTotalstockvalue(Math.round(product.getTotalstockvalue() * 100.0) / 100.0);
					productForm.setTotalssaleprice(Math.round(product.getTotalssaleprice() * 100.0) / 100.0);
					productForm.setTotalclosingstock(product.getTotalclosingstock());
					productForm.setTotalclosingvalue(Math.round(product.getTotalclosingvalue() * 100.0) / 100.0);
					productForm.setTotalunknownvalue(Math.round(product.getTotalunknownvalue() * 100.0) / 100.0);
					productForm.setTotalunknownqty(product.getTotalunknownqty());
				}
			} else {
				count = 0;
				pagination.setPreperties(count);
				productForm.setOpeningstockList(openingstockList);
				//Product product = openingstockList.get(openingstockList.size() - 1);
				productForm.setOpentotalcount("0");
				pagination.setPage_records(openingstockList.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords("0");
				productForm.setTotalopeningstock(0);
				productForm.setTotalopeingstockvalue(0);
				productForm.setTotalqtyin(0);
				productForm.setTotalqtyinvalue(0);
				productForm.setTotalqtyout(0);
				productForm.setTotalstockvalue(0);
				productForm.setTotalssaleprice(0);
				productForm.setTotalclosingstock(0);
				productForm.setTotalclosingvalue(0);
				productForm.setTotalunknownvalue(0);
				productForm.setTotalunknownqty(0);
			}
			filter_bydate = filter_bydate.split("_")[0]+"-"+filter_bydate.split("_")[1];
			productForm.setFilter_bydate(filter_bydate);
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			productForm.setFromdate(fromDate);
			productForm.setTodate(toDate);
			productForm.setIsfrominventorycatalogue("1");
			
			setLetterData(location_filter, connection);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "inventoryOpeningClosingByCatalogue";
	}

	public String getadjustmentdata() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String id = jsonObject.getString("id");

			StringBuffer builder = new StringBuffer();
			Product product = inventoryProductDAO.getProduct(id);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productid", product.getId());
			jsonobj.put("stock", product.getStock());
			jsonobj.put("productname", product.getProduct_name());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String saveadjustmentdata() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String id = request.getParameter("id");
			String adj_type = request.getParameter("adj_type");
			String pre_stock = request.getParameter("pre_stock");
			String change_stock = request.getParameter("change_stock");
			String comment = request.getParameter("comment");
			String isfromcathlab = request.getParameter("isfromcathlab");
			String userid = loginInfo.getUserId();
			String adj_change_stock_indi = request.getParameter("adj_change_stock_indi");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			/*
			 * int stock = Integer.parseInt(pre_stock); int qty =
			 * Integer.parseInt(change_stock); int changerqty =
			 * Integer.parseInt(adj_change_stock_indi);
			 */
			
			double stock = Double.parseDouble(pre_stock);
			double qty = Double.parseDouble(change_stock);
			double changerqty = Double.parseDouble(adj_change_stock_indi);
			/*
			 * if(stock>qty){ changerqty = stock - qty; }else{ changerqty = qty
			 * - stock; }
			 */

			int qtyinout = 1;
			if (adj_type.equals("2")) {
				qtyinout = 0;
			}
			int res = inventoryProductDAO.updateProductStocks(id, qty);
			int adjustmentid = 0;
			Product product = inventoryProductDAO.getProduct(id);
			if (res > 0) {
				String location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
				int parentd = inventoryProductDAO.saveParentAdjustmentData(0, userid, date, location, 1);
				adjustmentid = inventoryProductDAO.insertAdjustmentProductData(id, adj_type, stock, qty, changerqty,
						comment, userid, date, parentd, product);
				adjustmentid = parentd;
			}

			// stock log

			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			String source = "Product Adjustment";
			if (adjustmentid > 0) {
				int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(), datetime,
						product.getLocation(), qtyinout, id, product.getCatalogueid(), source, qty, stock, changerqty,
						"0", "0", 0, adjustmentid, 0, "0");

				String date1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(id, date1);
				if (!checkopningstockexist) {
					int r = pharmacyDAO.saveOpeningStock(id, date1, stock, "0");
				}
			}
			String searchtext = productForm.getSearchtext();
			String category_id = productForm.getCategory_id();
			String withstock_filter = productForm.getWithstock_filter();
			String product_type = productForm.getProduct_type();
			String shelf_filter = productForm.getShelf_filter();

			session.setAttribute("stockstatus_searchtext", searchtext);
			session.setAttribute("stockstatus_category_id", category_id);
			session.setAttribute("stockstatus_withstock_filter", withstock_filter);
			session.setAttribute("stockstatus_product_type", product_type);
			session.setAttribute("stockstatus_shelf_filter", shelf_filter);
			if (isfromcathlab != null) {
				if (isfromcathlab.equals("1")) {
					return "transferproductdata2";
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "listproduct";
	}

	public String productwisereport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String location = productForm.getReportlocation();
			if (location != null) {
				if (location.equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);

			String product_id = productForm.getProduct_name();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String product_type = productForm.getProduct_type();
			ArrayList<Product> itemWiseListReport = new ArrayList<Product>();
			ArrayList<ThirdParty> thirdPartyList=new ArrayList<>();
			String payee=productForm.getPayee();
			String tpid=productForm.getTpid();	
			thirdPartyList=thirdPartyDAO.getMainTPListNew(payee);
			productForm.setThirdPartyTypeList(thirdPartyList);
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());

			}

			/*
			 * if(product_id!=null){ if(product_id.equals("")){ product_id=""; }
			 * } else { product_id="0"; }
			 */
			if (product_id == null) {
				product_id = "";
			}
			if (product_type != null) {
				if (product_type.equals("")) {
					product_type = "0";
				}
			} else {
				product_type = "0";
			}
			int count = inventoryProductDAO.getProductWiseReportListCount(fromdate, todate, product_id, location,
					product_type);
			pagination.setPreperties(count);
			itemWiseListReport = inventoryProductDAO.getProductWiseReportListNew(fromdate, todate, product_id, location,
					pagination, product_type,tpid,payee);
			productForm.setItemWiseListReport(itemWiseListReport);
			pagination.setTotal_records(itemWiseListReport.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			int size = itemWiseListReport.size();
			if (size > 0) {
				int retrunqtycount = itemWiseListReport.get(size - 1).getRetrunqtycount();
				double totalqty = itemWiseListReport.get(size - 1).getTotalqty();
				productForm.setTotalqty(totalqty);
				productForm.setRetrunqtycount(retrunqtycount);
			} else {
				productForm.setTotalqty(0);
				productForm.setRetrunqtycount(0);
			}

			ArrayList<Master> reportlocationList = pharmacyDAO.getAllLocation();
			productForm.setReportlocationList(reportlocationList);
			productForm.setReportlocation(location);
			
			setLetterData(location, connection);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "productwisereport";
	}

	public String getreplaceindentproductlist() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String catalogueid = jsonObject.getString("catalogueid");
			if (catalogueid == null) {
				catalogueid = "0";
			}
			if (catalogueid.equals("")) {
				catalogueid = "0";
			}
			String indentlocationid = jsonObject.getString("indentlocationid");
			String parentid = jsonObject.getString("parentid");
			StringBuffer builder = new StringBuffer();

			Product product2 = inventoryProductDAO.getParentTransferData(parentid);

			String catalogueids = inventoryProductDAO.getCatalogueidsOfIndentRequest(parentid);

			Product product = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
			ArrayList<Product> list = catalogueDAO.getProductListForIndentReplace(product2.getWarehouse_id(),
					catalogueids);
			StringBuffer buffer1 = new StringBuffer();

			buffer1.append("<label>Select Product</label>");
			buffer1.append(
					"<select id='indent_changecatlid' class='form-control chosen'  name='indent_changecatlid' >"); // onchange='setProdCatandType(this.value)'
			buffer1.append("<option value='0'>Select Product</option>");
			for (Product product1 : list) {
				buffer1.append("<option value='" + product1.getId() + "'>" + product1.getProduct_name() + "</option>");
			}
			buffer1.append("</select>");
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("cataloguename", product.getProduct_name());
			jsonobj.put("cataloguelist", buffer1.toString());
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String savereplaceindentproductlist() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String oldcatid = jsonObject.getString("change_indent_oldcatid");
			String childid = jsonObject.getString("change_indent_childid");
			String parentid = jsonObject.getString("change_indent_parentid");
			String changecatlid = jsonObject.getString("indent_changecatlid");
			String comment = jsonObject.getString("change_indent_comment");

			String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String userid = loginInfo.getUserId();
			String productid = indentDAO.getProdIdCatId(changecatlid);

			int res = indentDAO.updateChildIndentProductChange(childid, changecatlid, productid);
			if (res > 0) {
				int updateres = indentDAO.insertIntoChangeIndentProduct(oldcatid, childid, parentid, changecatlid,
						comment, todate, userid);
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("cataloguelist", "1");
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String calculateinventoryopeing() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String fromdate = jsonObject.getString("fromdate");
			String todate = jsonObject.getString("todate");
			String searchbyprodname = jsonObject.getString("searchbyprodname");
			String location_filter = jsonObject.getString("location_filter");

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			Product product = inventoryProductDAO.getOpeingClosingBiferSale(fromdate, todate, searchbyprodname,
					location_filter);

			Product directproduct = inventoryProductDAO.getOpeingClosingBiferDirectTransfer(fromdate, todate,
					searchbyprodname, location_filter);

			Product requestproduct = inventoryProductDAO.getOpeingClosingBiferRequestTransfer(fromdate, todate,
					searchbyprodname, location_filter);

			Product returnproduct = inventoryProductDAO.getOpeingClosingBiferReturnTransfer(fromdate, todate,
					searchbyprodname, location_filter);

			Product returnsuplierproduct = inventoryProductDAO.getOpeingClosingBiferReturnSupplier(fromdate, todate,
					searchbyprodname, location_filter);

			Product consumeproduct = inventoryProductDAO.getOpeingClosingBiferConsume(fromdate, todate,
					searchbyprodname, location_filter);

			Product adjustproduct = inventoryProductDAO.getOpeingClosingBiferAdjust(fromdate, todate, searchbyprodname,
					location_filter);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("pharmacysaleprice", Math.round(product.getSalepricetotal() * 100.0) / 100.0);
			jsonobj.put("directsaleprice", Math.round(directproduct.getSalepricetotal() * 100.0) / 100.0);
			jsonobj.put("requestsaleprice", Math.round(requestproduct.getSalepricetotal() * 100.0) / 100.0);
			jsonobj.put("returnsaleprice", Math.round(returnproduct.getSalepricetotal() * 100.0) / 100.0);
			jsonobj.put("returnsupliersaleprice", Math.round(returnsuplierproduct.getSalepricetotal() * 100.0) / 100.0);
			jsonobj.put("consumesaleprice", Math.round(consumeproduct.getSalepricetotal() * 100.0) / 100.0);
			jsonobj.put("adjustsaleprice", Math.round(adjustproduct.getSalepricetotal() * 100.0) / 100.0);
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String inventorygstreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO=new JDBCProcurementDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = productForm.getWarehouse_id();
			String filter_type = productForm.getFilter_type();
			String vendorid=productForm.getVendor_id();
			String billdate=productForm.getBillDate();
			
			
			
			/*ArrayList<Vendor> vlist = procurementDAO.getVendorList();
			productForm.setVendorList(vlist);*/
			
			billdate=DateTimeUtils.getCommencingDate1(billdate);
			
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			}
			
			String filter_sortby = productForm.getFilter_sortby();
			if (filter_sortby == null) {
				filter_sortby = "1";
			}
			
			productForm.setFilter_sortby(filter_sortby);
			productForm.setIsfrombincard(1);

			if (filter_type == null) {
				filter_type = "0";
			}
			if (filter_type.equals("")) {
				filter_type = "0";
			}
			productForm.setWarehouse_id(location);
			productForm.setFilter_type(filter_type);
			if (fromdate == null) {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());
			} else {
				if (fromdate.equals("")) {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}
			if (todate == null) {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			} else {
				if (todate.equals("")) {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			}

			// ArrayList<Product> vatReportList=
			// inventoryProductDAO.getVatReportList(fromdate,todate,location);
			
			
			if (filter_type.equals("0")) {
				ArrayList<Product> vatReportList1 = inventoryProductDAO.getInventoryGSTReport(fromdate, todate, vendorid,
						location, billdate, filter_sortby);
				productForm.setVatReportList(vatReportList1);
			} else {
				ArrayList<Product> vatReportList1 = inventoryProductDAO.getInventoryGSTReturnReport(fromdate, todate,
						vendorid,location, billdate, filter_sortby);
				productForm.setVatReportList(vatReportList1);
			}

			todate = DateTimeUtils.getCommencingDate1(todate);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList();
			productForm.setWarehouseList(warehouseList);
			
			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList("0");
			productForm.setVendorList(vendorList);

			
			/*
			 * if(vatReportList1.size()>1){ int size=vatReportList1.size();
			 * Product product= vatReportList1.get(size-1);
			 * productForm.setAllsixtaxTot(product.getAllsixtaxTot());
			 * productForm.setAllthirttaxTot(product.getAllthirttaxTot());
			 * productForm.setAllsixVatPer(product.getAllsixVatPer());
			 * productForm.setAllthirVatPer(product.getAllthirVatPer());
			 * productForm.setAlltableValtot(product.getAlltotvatTotal());
			 * productForm.setAlltotvatTotal(product.getAlltotvatTotal());
			 * productForm.setAlltotalGross(product.getAlltotalGross());
			 * productForm.setAlltotalNet(product.getAlltotalNet());
			 * 
			 * }
			 */
			setLetterData(location, connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "inventorygstreport";
	}

	public String calculateinventoryopeinginvalue() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String fromDate = jsonObject.getString("fromdate");
			String toDate = jsonObject.getString("todate");
			String searchbyprodname = jsonObject.getString("searchbyprodname");
			String location_filter = jsonObject.getString("location_filter");

			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			String catalogueid = null;

			// Purchase qty = in fromdate and todate
			Product purproduct = inventoryProductDAO.getPuchaseProductDataBetween(catalogueid, fromDate, toDate,
					location_filter);

			// return from patient = in fromdate and todate
			Product returnpatientqty = inventoryProductDAO.getReturnPatientProductDataBetween(catalogueid, fromDate,
					toDate, location_filter);

			// retruntransfer = in fromdate and todate
			double retruntransferqtyin = 0;
			double retruntransferqtyinvalue = 0;
			double directtransferqtyin = 0;
			double requesttransferqtyin = 0;
			double directtransferqtyinvalue = 0;
			double requesttransferqtyinvalue = 0;
			if (!location_filter.equals("0")) {
				// Return transfer between date
				Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDate(catalogueid,
						fromDate, location_filter, toDate);
				retruntransferqtyin = returntransferproductin.getTotalqty();
				retruntransferqtyinvalue = returntransferproductin.getTotal_amount();

				// Direct transfer between
				Product directtransferproductin = inventoryProductDAO.getDirectTransferBetweenDateIn(catalogueid,
						fromDate, location_filter, toDate);

				// Request Transfer between
				Product requesttransferproductin = inventoryProductDAO.getRequestTransferBetweenDateIn(catalogueid,
						fromDate, location_filter, toDate);

				directtransferqtyin = directtransferproductin.getTotalqty();
				requesttransferqtyin = requesttransferproductin.getTotalqty();
				directtransferqtyinvalue = directtransferproductin.getTotal_amount();
				requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
			}

			Product adjustmentbetweenin = inventoryProductDAO.getAdjustmentDataBetweenIn(catalogueid, fromDate,
					location_filter, toDate);

			/*
			 * int qtyin = purproduct.getTotalqty() +
			 * returnpatientqty.getTotalqty() +retruntransferqtyin +
			 * directtransferqtyin +requesttransferqtyin +
			 * adjustmentbetweenin.getTotalqty() ;
			 * 
			 * double qtyinvalue = purproduct.getTotal_amount() +
			 * returnpatientqty.getTotal_amount() +retruntransferqtyinvalue +
			 * directtransferqtyinvalue +requesttransferqtyinvalue +
			 * adjustmentbetweenin.getTotal_amount() ;
			 */

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("purchasevalue", Math.round(purproduct.getGrnqtyamtttl() * 100.00) / 100.00);
			jsonobj.put("pharmacyreturnvalue", Math.round(returnpatientqty.getTotal_amount() * 100.00) / 100.00);
			jsonobj.put("returntostorevalue", Math.round(retruntransferqtyinvalue * 100.00) / 100.00);
			jsonobj.put("directtranfervalue", Math.round(directtransferqtyinvalue * 100.00) / 100.00);
			jsonobj.put("requesttransfervalue", Math.round(requesttransferqtyinvalue * 100.00) / 100.00);
			jsonobj.put("adjustmentvalue", Math.round(adjustmentbetweenin.getTotal_amount() * 100.00) / 100.00);
			jsonobj.put("purchasefreevalue", Math.round(purproduct.getGrnfreeqtyamtttl() * 100.00) / 100.00);
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String cancelpharmacybillreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			/*
			 * ArrayList<Breadcrumbs> indentflowlist = new
			 * ArrayList<Breadcrumbs>(); ArrayList<Breadcrumbs>
			 * indentflowlisttemp = new ArrayList<Breadcrumbs>(); if
			 * (session.getAttribute("indentflowlist") != null) {
			 * indentflowlisttemp = (ArrayList<Breadcrumbs>)
			 * session.getAttribute("indentflowlist"); } boolean
			 * isavilablemodule= false; int modulecount =0; for (Breadcrumbs
			 * breadcrumbs : indentflowlisttemp) {
			 * breadcrumbs.setIscurrent(false);
			 * breadcrumbs.setSqno(modulecount); modulecount++;
			 * if(breadcrumbs.getName().equals("Pharmacy Cancel Bill Report")){
			 * isavilablemodule =true; breadcrumbs.setIscurrent(true);
			 * indentflowlist.add(breadcrumbs); break; }else{
			 * indentflowlist.add(breadcrumbs); } } if(!isavilablemodule){
			 * Breadcrumbs breadcrumbs = new Breadcrumbs();
			 * breadcrumbs.setName("Pharmacy Cancel Bill Report");
			 * breadcrumbs.setOn(true); breadcrumbs.setSqno(modulecount);
			 * breadcrumbs.setUrllink("cancelpharmacybillreportProduct");
			 * breadcrumbs.setIscurrent(true);
			 * breadcrumbs.setShowingname("Cancel Bill Report");
			 * indentflowlist.add(breadcrumbs); }
			 * session.setAttribute("indentflowlist",indentflowlist);
			 */

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (fromdate != null) {
				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());
			}
			if (todate != null) {
				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}

			int count = inventoryProductDAO.getCountCancelBillReport(fromdate, todate, location);
			pagination.setPreperties(count);
			ArrayList<Product> cancelbillreport = inventoryProductDAO.getCancelBillReport(fromdate, todate, location,
					pagination);
			pagination.setPage_records(cancelbillreport.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			productForm.setCancelbillreport(cancelbillreport);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "cancelpharmacybillreport";
	}

	public String bincardreportnew() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			productForm.setIsfrombincard(1);
			connection = Connection_provider.getconnection();
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			ArrayList<Product> productList = catalogueDAO.getAllProductList(location);
			productForm.setProductList(productList);

			if (location.equals("0")) {
				productForm.setLocationName("Admin");
			} else {

				String locationName = pharmacyDAO.getLocationName(location);
				productForm.setLocationName(locationName);
			}

			String catalogueid = productForm.getCatalogueid();
			if (catalogueid == null) {
				catalogueid = "0";
			}

			String userwise = productForm.getUserwise();
			if (userwise == null) {
				userwise = "0";
			} else if (userwise.equals("")) {
				userwise = "0";
			}
			String location_filter = productForm.getLocation_filter();
			if (location_filter != null) {
				if (location_filter.equals("")) {
					location_filter = "0";
				}
			} else {
				location_filter = "0";
			}

			productForm.setLocation_filter(location_filter);
			productForm.setUserwise(userwise);
			String isfromoepning = request.getParameter("isfromoepning");
			int isfromoepningclosing = 0;
			if (isfromoepning != null) {
				if (isfromoepning.equals("1")) {
					isfromoepningclosing = 1;
					catalogueid = request.getParameter("catalogueid");
				}
			}
			String isfromstockreport = request.getParameter("isfromstockreport");
			if (isfromstockreport != null) {
				if (isfromstockreport.equals("1")) {
					catalogueid = request.getParameter("catalogueid");
				}
			}
			if (catalogueid == null) {
				catalogueid = "0";
			}
			String filter_sortby = productForm.getFilter_sortby();
			if (filter_sortby == null) {
				filter_sortby = "1";
			}
			productForm.setFilter_sortby(filter_sortby);
			if (!catalogueid.equals("0")) {

				String fromdate = productForm.getFromdate();
				String todate = productForm.getTodate();
				if (isfromoepningclosing == 1) {
					fromdate = request.getParameter("fromdate");
					todate = request.getParameter("todate");
				}
				if (fromdate == null) {

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -30);
					fromdate = dateFormat.format(calendar.getTime());
				} else {

					if (!fromdate.equals("")) {
						if (isfromoepningclosing == 0) {
							fromdate = DateTimeUtils.getCommencingDate1(fromdate);
						}

					} else {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DATE, -30);
						fromdate = dateFormat.format(calendar.getTime());
					}

				}
				if (todate == null) {

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					todate = dateFormat.format(calendar.getTime());
				} else {

					if (!todate.equals("")) {
						if (isfromoepningclosing == 0) {
							todate = DateTimeUtils.getCommencingDate1(todate);
						}

					} else {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						todate = dateFormat.format(calendar.getTime());
					}
				}
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
				ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());

				int result = chargesReportDAO.saveMisReportLog("Bin Card Report", loginInfo.getUserId(), fromdate,
						todate, date, "bincardreport");
				ArrayList<Product> bincardReportList = productDAO.getBinCardReportNew(location, catalogueid, fromdate,
						todate, clinicabrivation, userwise, location_filter, filter_sortby);
				double receipt = 0;
				int issue = 0;
				int balance = 0;
				for (Product product : bincardReportList) {

					if (product.getQty()!=0.0) {
						receipt = receipt + (product.getQty());
					}
					if (!product.getIssueqty().equals("-")) {
						issue = issue + Integer.parseInt(product.getIssueqty());
					}
					balance = product.getBalqty();
				}

				productForm.setBalance(String.valueOf(balance));
				productForm.setIssueqty(String.valueOf(issue));
				productForm.setReceiptqty(String.valueOf(receipt));

				productForm.setBincardReportList(bincardReportList);
				Product product = catalogueDAO.getProductDetails(catalogueid);
				productForm.setProduct_name(product.getProduct_name());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
				productForm.setFromdate(fromdate);
				productForm.setTodate(todate);
			} else {

				productForm.setProduct_name("-");
				productForm.setFromdate("");
				productForm.setTodate("");
			}
			ArrayList<UserProfile> userlist = userProfileDAO.getAllUserProfileList();
			productForm.setUserlist(userlist);

			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationlist(locationlist);

			productForm.setHidecatagoryid("1");

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "bincardReportnew";

	}

	public String calculateinventoryopeingoutward() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);

			String fromdate = jsonObject.getString("fromdate");
			String todate = jsonObject.getString("todate");
			String searchbyprodname = jsonObject.getString("searchbyprodname");
			String location_filter = jsonObject.getString("location_filter");

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			Product product = inventoryProductDAO.getOpeingClosingBiferSale(fromdate, todate, searchbyprodname,
					location_filter);

			Product directproduct = inventoryProductDAO.getOpeingClosingBiferDirectTransfer(fromdate, todate,
					searchbyprodname, location_filter);

			Product requestproduct = inventoryProductDAO.getOpeingClosingBiferRequestTransfer(fromdate, todate,
					searchbyprodname, location_filter);

			Product returnproduct = inventoryProductDAO.getOpeingClosingBiferReturnTransfer(fromdate, todate,
					searchbyprodname, location_filter);

			Product returnsuplierproduct = inventoryProductDAO.getOpeingClosingBiferReturnSupplier(fromdate, todate,
					searchbyprodname, location_filter);

			Product consumeproduct = inventoryProductDAO.getOpeingClosingBiferConsume(fromdate, todate,
					searchbyprodname, location_filter);

			Product adjustproduct = inventoryProductDAO.getOpeingClosingBiferAdjust(fromdate, todate, searchbyprodname,
					location_filter);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("pharmacysaleprice", Math.round(product.getTotal_amount() * 100.0) / 100.0);
			jsonobj.put("directsaleprice", Math.round(directproduct.getTotal_amount() * 100.0) / 100.0);
			jsonobj.put("requestsaleprice", Math.round(requestproduct.getTotal_amount() * 100.0) / 100.0);
			jsonobj.put("returnsaleprice", Math.round(returnproduct.getTotal_amount() * 100.0) / 100.0);
			jsonobj.put("returnsupliersaleprice", Math.round(returnsuplierproduct.getTotal_amount() * 100.0) / 100.0);
			jsonobj.put("consumesaleprice", Math.round(consumeproduct.getTotal_amount() * 100.0) / 100.0);
			jsonobj.put("adjustsaleprice", Math.round(adjustproduct.getTotal_amount() * 100.0) / 100.0);
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String indentnewreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);

			String filter_status = productForm.getFilter_status();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location_filter = productForm.getLocation_filter();
			String searhText = productForm.getSearchtext();
			// hisuserlist
			String userwise = productForm.getUserwise();
			String transferedlocation = productForm.getTransferedlocation();
			if (transferedlocation != null) {
				if (transferedlocation.equals("")) {
					transferedlocation = "0";
				}
			} else {
				transferedlocation = "0";
			}

			if (userwise != null) {
				if (userwise.equals("") || userwise.equals("0")) {
					userwise = null;
				}
			}

			if (searhText != null) {
				if (searhText.equals("")) {
					searhText = null;
				}
			}

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "0";
				}
			} else {
				filter_status = "0";
			}

			if (location_filter != null) {
				if (location_filter.equals("")) {
					location_filter = "0";
				}
			} else {
				location_filter = "0";
			}

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {

				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
					// todate = null;
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}

			String userid = loginInfo.getUserId();

			if (filter_status.equals("0")) {
				int count = indentDAO.getCountRequestIndentReport(fromdate, todate, location_filter, searhText);
				pagination.setPreperties(count);
				ArrayList<Product> parenttransferlist = indentDAO.getRequestIndentReport(fromdate, todate,
						location_filter, searhText);
				productForm.setParenttransferlist(parenttransferlist);
				pagination.setPage_records(parenttransferlist.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			} else if (filter_status.equals("1")) {
				int count = indentDAO.getCountDirectIndentReport(fromdate, todate, location_filter, searhText,
						filter_status);
				pagination.setPreperties(count);
				ArrayList<Product> parenttransferlist = indentDAO.getDirectIndentReport(fromdate, todate,
						location_filter, searhText, filter_status);
				productForm.setParenttransferlist(parenttransferlist);
				pagination.setPage_records(parenttransferlist.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			} else if (filter_status.equals("2")) {
				int count = indentDAO.getCountDirectIndentReport(fromdate, todate, location_filter, searhText,
						filter_status);
				pagination.setPreperties(count);
				ArrayList<Product> parenttransferlist = indentDAO.getDirectIndentReport(fromdate, todate,
						location_filter, searhText, filter_status);
				productForm.setParenttransferlist(parenttransferlist);
				pagination.setPage_records(parenttransferlist.size());
				productForm.setTotalRecords(count);
				productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			}

			/*
			 * int count =
			 * indentDAO.getCountIndentTransferLog(fromdate,todate,location,
			 * filter_status,location_filter,searhText,userwise,
			 * transferedlocation); pagination.setPreperties(count);
			 * ArrayList<Product> parenttransferlist =
			 * indentDAO.getIndentTransferLog(fromdate,todate,location,
			 * filter_status,location_filter,searhText,pagination,userwise,
			 * transferedlocation);
			 * productForm.setParenttransferlist(parenttransferlist);
			 * pagination.setPage_records(parenttransferlist.size());
			 * productForm.setTotalRecords(count);
			 * productForm.setPagerecords(String.valueOf(pagination.
			 * getPage_records()));
			 */

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			productForm.setUserid(loginInfo.getUserId());
			productForm.setFilter_status(filter_status);
			// productForm.setCurr_location(location);
			productForm.setLocation_filter(location_filter);
			productForm.setSearchtext(searhText);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationlist(locationlist);

			ArrayList<UserProfile> userlist = userProfileDAO.getAllUserProfileList();
			productForm.setUserlist(userlist);
			productForm.setHidecatagoryid("1");
			productForm.setTransferedlocation(transferedlocation);
			
			setLetterData(null, connection);
			productForm.setIsfrombincard(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "indentnewreport";
	}

	public String expiryproductpopup() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = (String) session.getAttribute("location");

			if (location == null) {
				location = "0";
			}

			if (fromdate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				fromdate = dateFormat.format(calendar.getTime());
			} else {
				if (fromdate.equals("")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					fromdate = dateFormat.format(calendar.getTime());
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			int i = 90;
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf1.parse(fromdate);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d);
			cal1.add(Calendar.DATE, i);
			String dt = sdf1.format(cal1.getTime());
			ArrayList<Product> expiryMedicineReport = new ArrayList<Product>();
			expiryMedicineReport = inventoryProductDAO.getExpiryMedicinePopup(fromdate, dt, location);
			productForm.setExpiryMedicineReport(expiryMedicineReport);

			// session.setAttribute("usedead", useandDeadPer);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "expiryproductpopup";
	}

	public String editgrnlogreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = productForm.getLocation_filter();
			String serchtext = productForm.getSearchtext();
			if (location == null) {
				location = "0";
			}

			if (fromdate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				fromdate = dateFormat.format(calendar.getTime());
			} else {
				if (fromdate.equals("")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					fromdate = dateFormat.format(calendar.getTime());
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				todate = dateFormat.format(calendar.getTime());
			} else {
				if (todate.equals("")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					todate = dateFormat.format(calendar.getTime());
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			}

			if (serchtext != null) {
				if (serchtext.equals("")) {
					serchtext = null;
				}
			}
			ArrayList<Product> editgrnreportlist = new ArrayList<Product>();
			editgrnreportlist = inventoryProductDAO.getEditGRNLogList(fromdate, todate, location, serchtext);
			productForm.setEditgrnreportlist(editgrnreportlist);
			productForm.setLocation_filter(location);
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);
			
			setLetterData(location, connection);
			productForm.setIsfrombincard(1);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "editgrnlogreport";
	}

	public String minimumqtypopup() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String location = (String) session.getAttribute("location");

			if (location == null) {
				location = "0";
			}
			int count = procurementDAO.getTotalRequestedPOCount(location, null,"0","0");
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> requestedPOList = procurementDAO.getRequestedPoList(location, pagination, null,"0","0");
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setReturnqueList(requestedPOList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "minimumqtypopup";
	}

	public String cataloguewisesalereport() throws Exception {
		//   04-02-2020 report created
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String location = productForm.getReportlocation();
			/*
			 * if(location!=null){ if(location.equals("")){ location =(String)
			 * session.getAttribute("location"); if(location==null){
			 * location="0"; } } }else{ location =(String)
			 * session.getAttribute("location"); if(location==null){
			 * location="0"; } }
			 */

			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			} else {
				if (DateTimeUtils.isNull(location).equals("")) {
					location = (String) session.getAttribute("location");
					if (location == null) {
						location = "0";
					}
				}
			}
			productForm.setReportlocation(location);
			productForm.setIsfrombincard(1);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);

			String product_id = productForm.getProduct_name();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String product_type = productForm.getProduct_type();
			ArrayList<Product> itemWiseListReport = new ArrayList<Product>();
			if (fromdate != null) {

				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// calendar.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// calendar.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(calendar.getTime());

			}
			if (todate != null) {

				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {

					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());

			}

			product_id = DateTimeUtils.isNull(product_id);
			product_type = DateTimeUtils.numberCheck(product_type);

			int count = inventoryProductDAO.getCatalogueWiseSaleReprtCount(fromdate, todate, product_id, location,
					product_type);
			pagination.setPreperties(count);
			itemWiseListReport = inventoryProductDAO.getCatalogueWiseSaleReprt(fromdate, todate, product_id, location,
					pagination, product_type);
			productForm.setItemWiseListReport(itemWiseListReport);
			pagination.setTotal_records(itemWiseListReport.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			int size = itemWiseListReport.size();
			if (size > 0) {
				int retrunqtycount = itemWiseListReport.get(size - 1).getRetrunqtycount();
				double totalqty = itemWiseListReport.get(size - 1).getTotalqty();
				productForm.setTotalqty(totalqty);
				productForm.setRetrunqtycount(retrunqtycount);
			} else {
				productForm.setTotalqty(0);
				productForm.setRetrunqtycount(0);
			}

			ArrayList<Master> reportlocationList = pharmacyDAO.getAllLocation();
			productForm.setReportlocationList(reportlocationList);
			productForm.setReportlocation(location);
			
			setLetterData(location, connection);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "cataloguewisesalereport";
	}

	public String savemultiadjustment() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			String isfromcathlab = request.getParameter("isfromcathlab");
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			// Approve adjustment
			int status_type = 2;
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			int status = 0;
			int parentd = inventoryProductDAO.saveParentAdjustmentData(status_type, userid, date, location, status);
			for (Product product : productForm.getAlladjustmentproduct()) {
				if (product == null) {
					continue;
				}
				int id = product.getId();
				int adj_type = product.getAdjustment_type();
				double pre_stock = Double.parseDouble(product.getStock());
				double change_stock = product.getTotalqty();
				String comment = product.getRemark();
				String adj_change_stock_indi = product.getTqty();
				Product product1 = inventoryProductDAO.getProduct("" + id);

				double stock = (pre_stock);
				double qty = change_stock;
				int changerqty = Integer.parseInt(adj_change_stock_indi);

				int qtyinout = 1;
				if (adj_type == 2) {
					qtyinout = 0;
				}
				int adjustmentid = 0;
				if (parentd > 0) {
					adjustmentid = inventoryProductDAO.insertAdjustmentProductData("" + id, "" + adj_type, stock, qty,
							changerqty, comment, userid, date, parentd, product1);
				}
			}
			session.setAttribute("multiadjustcount", null);
			session.setAttribute("adjustmentprodid", null);
			String searchtext = productForm.getSearchtext();
			String category_id = productForm.getCategory_id();
			String withstock_filter = productForm.getWithstock_filter();
			String product_type = productForm.getProduct_type();
			String shelf_filter = productForm.getShelf_filter();
			session.setAttribute("stockstatus_searchtext", searchtext);
			session.setAttribute("stockstatus_category_id", category_id);
			session.setAttribute("stockstatus_withstock_filter", withstock_filter);
			session.setAttribute("stockstatus_product_type", product_type);
			session.setAttribute("stockstatus_shelf_filter", shelf_filter);
			session.removeAttribute("multiadjustcount");
			if (isfromcathlab != null) {
				if (isfromcathlab.equals("1")) {
					return "transferproductdata2";
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "listproduct";
	}

	public String multipleadjusteditdashboard() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);

			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Multiple Adjustment Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Multiple Adjustment Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("multipleadjusteditdashboardProduct");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			String fromdate = (String) session.getAttribute("editadjustrejestproduct_fromdate");
			String todate = (String) session.getAttribute("editadjustrejestproduct_todate");
			String iseditadjustrejestproduct = (String) session.getAttribute("iseditadjustrejestproduct");
			if (fromdate == null) {
				fromdate = productForm.getFromdate();
			} else {
				session.removeAttribute("editadjustrejestproduct_fromdate");
			}

			if (todate == null) {
				todate = productForm.getTodate();
			} else {
				session.removeAttribute("editadjustrejestproduct_todate");
			}
			if (iseditadjustrejestproduct != null) {
				pagination = (Pagination) session.getAttribute("adjusteditdashboard_pagination");
				session.removeAttribute("iseditadjustrejestproduct");
			}

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					// cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					// fromdate = null;
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				}
			}

			if (todate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				if (todate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
					// todate = null;
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			}

			int count = inventoryProductDAO.getMultiAdjustEditDashboardCount(fromdate, todate, location);
			pagination.setPreperties(count);

			ArrayList<Product> parentmultiadjusteditlist = inventoryProductDAO.getMultiAdjustEditDashboard(fromdate,
					todate, location, pagination);
			pagination.setPage_records(parentmultiadjusteditlist.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setParenttransferlist(parentmultiadjusteditlist);

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);
			session.setAttribute("adjusteditdashboard_pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "multipleadjusteditdashboard";
	}

	public String showmultiadjustedit() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String parentid = request.getParameter("parentid");
			String userid = loginInfo.getUserId();
			Product product1 = inventoryProductDAO.getMultiAdjustEditData(parentid);

			ArrayList<Product> childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
			StringBuilder builder = new StringBuilder();

			builder.append("" + product1.getLocationName() + "");
			builder.append("~");

			builder.append("" + product1.getDateTime() + "");
			builder.append("~");

			builder.append("" + product1.getId() + "");
			builder.append("~");

			builder.append("" + product1.getUserid() + "");
			builder.append("~");
			int i = 0;
			int j = 1;
			for (Product product2 : childtransferlist) {
				Product product = inventoryProductDAO.getProduct(product2.getProductid());
				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}
				String location = pharmacyDAO.getLocationName(product.getLocation());

				builder.append("<tr>");
				builder.append("<td>" + j + "</td>");
				builder.append("<input type='hidden' class='multiadjustmentapproveclass' value='" + product2.getId()
						+ "' class='form-control' />");
				builder.append("<td>" + hsnno + "</td>");
				builder.append("<td>" + product.getProduct_name() + "</td>");
				builder.append("<td>" + product.getBatch_no() + "</td>");
				builder.append("<td style='color:" + product.getColor() + "'>"
						+ DateTimeUtils.getCommencingDate1(product.getExpiry_date()) + "</td>");
				builder.append("<td style='text-align: center;'>" + product.getStock() + "</td>");
				builder.append("<td>");
				if (product2.getAdjustment_type() == 1) {
					builder.append("Excess Product Adjustment");
				} else if (product2.getAdjustment_type() == 2) {
					builder.append("Shortage Product Adjustment");
				} else if (product2.getAdjustment_type() == 3) {
					builder.append("Defective Product Adjustment");
				} else {
					builder.append("Expired/Dead Product Adjustment");
				}
				builder.append("</td>");
				builder.append("<td>" + product2.getPre_stock() + "</td>");
				builder.append("<td>" + product2.getChange_qty() + "</td>");
				builder.append("<td>" + product2.getCurrent_qty() + "</td>");
				builder.append("<td>" + product2.getRemark() + "</td>");
				builder.append("<td><textarea class='form-control' rows='2' id='multiadjaprove_remark"
						+ product2.getId() + "' name='remark" + product2.getId()
						+ "' style='background-color: rgba(255, 248, 220, 0.48);' placeholder='Write approve remark here'></textarea></td>");
				builder.append("</tr>");
				i++;
				j++;
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String updatemultiadjustment() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String parentid = request.getParameter("id");
			Product product2 = inventoryProductDAO.getMultiAdjustEditData(parentid);
			ArrayList<Product> childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			for (Product product : childtransferlist) {
				String adj_type = "" + product.getAdjustment_type();
				String pre_stock = "" + product.getPre_stock();
				String change_stock = product.getCurrent_qty();
				String comment = request.getParameter("remark" + product.getId());

				String adj_change_stock_indi = product.getChange_qty();

				int stock = Integer.parseInt(pre_stock);
				int qty = Integer.parseInt(change_stock);
				int changerqty = Integer.parseInt(adj_change_stock_indi);

				int qtyinout = 1;
				if (adj_type.equals("2")) {
					qtyinout = 0;
				}
				String id = product.getProductid();
				int res = inventoryProductDAO.updateProductStocks(id, qty);
				int adjustmentid = Integer.parseInt(parentid);

				// stock log
				Product product1 = inventoryProductDAO.getProduct(id);
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

				String source = "Product Adjustment";
				if (adjustmentid > 0) {
					int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(), datetime,
							product.getLocation(), qtyinout, id, product1.getCatalogueid(), source, qty, stock,
							changerqty, "0", "0", 0, adjustmentid, 0, "0");
					String date1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(id, date1);
					if (!checkopningstockexist) {
						int r = pharmacyDAO.saveOpeningStock(id, date1, stock, "0");
					}
				}

				int res1 = indentDAO.updateMultiChildAdjustment("" + product.getId(), comment);
			}

			int status = 1;
			int res = indentDAO.updateMultiAdjustment(parentid, status, userid, date);

			//   12-03-2020 set filter and pagination as it is when reload
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String iseditadjustrejestproduct = "1";

			session.setAttribute("editadjustrejestproduct_fromdate", fromdate);
			session.setAttribute("editadjustrejestproduct_todate", todate);
			session.setAttribute("iseditadjustrejestproduct", iseditadjustrejestproduct);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "multipleadjust";
	}

	public String multiadjustmentprint() throws SQLException {

		if (!verifyLogin(request)) {
			return "login";
		}
		String parentid = request.getParameter("id");
		Connection connection = null;
		try {
			int showbreadcrumbs = 0;
			String isfromreport = request.getParameter("isfromreport");
			if (isfromreport != null) {
				if (!isfromreport.equals("1")) {
					showbreadcrumbs = 1;
				}
			} else {
				showbreadcrumbs = 1;
			}
			if (showbreadcrumbs == 1) {
				ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
				ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
				if (session.getAttribute("indentflowlist") != null) {
					indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
				}
				boolean isavilablemodule = false;
				int modulecount = 0;
				for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
					breadcrumbs.setIscurrent(false);
					breadcrumbs.setSqno(modulecount);
					modulecount++;
					if (breadcrumbs.getName().equals("Multi Adjustment Print")) {
						isavilablemodule = true;
						breadcrumbs.setIscurrent(true);
						indentflowlist.add(breadcrumbs);
						break;
					} else {
						indentflowlist.add(breadcrumbs);
					}
				}
				if (!isavilablemodule) {
					Breadcrumbs breadcrumbs = new Breadcrumbs();
					breadcrumbs.setName("Multi Adjustment Print");
					breadcrumbs.setOn(true);
					breadcrumbs.setSqno(modulecount);
					breadcrumbs.setUrllink("multiadjustmentprintProduct?id=" + parentid + "");
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
				}
				session.setAttribute("indentflowlist", indentflowlist);
			}
			productForm.setShowbreadcrumbs(showbreadcrumbs);
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			Product product = inventoryProductDAO.getMultiAdjustEditData(parentid);
			ArrayList<Product> childtransferlist = new ArrayList<Product>();
			int isadjustment = Integer.parseInt(DateTimeUtils.numberCheck(product.getStatus()));
			if (DateTimeUtils.numberCheck(product.getStatus()).equals("0")
					|| DateTimeUtils.numberCheck(product.getStatus()).equals("2")) {
				childtransferlist = indentDAO.getChildMultiAdjustprintData(parentid);
			} else {
				childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
			}
			productForm.setIsadjustment(isadjustment);
			productForm.setRequest_statuss(product.getRequest());
			productForm.setRejectremark(product.getCancel_notes());
			String userid = loginInfo.getUserId();
			Priscription priscription2 = pharmacyDAO.getPharacyUsrLInfo(userid);
			productForm.setParentid(parentid);
			productForm.setIndentid("" + product.getIndent());
			productForm.setRequest_date(product.getDateTime());
			productForm.setUserid(product.getUserid());
			productForm.setFrom_location(product.getLocationName());
			productForm.setIssueno(parentid);
			productForm.setParentid(parentid);
			productForm.setAdmin_notes(product.getAdmin_notes());

			productForm.setChildtransferlist(childtransferlist);
			if (priscription2.getFullname() != null) {
				userid = priscription2.getFullname();
			}
			productForm.setCurr_user(userid);
			String temp = product.getApprove_date();
			String datetime = "";
			if (temp != null) {
				String[] data = temp.split(" ");
				String date = DateTimeUtils.getCommencingDate1(data[0]);
				datetime = date + " " + data[1];
			}

			productForm.setCheck_availability_date(datetime);
			productForm.setCheck_avail_userid(product.getAdmin_approve_userid());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curr_date = dateFormat.format(cal.getTime());
			productForm.setDate(curr_date);
			productForm.setIssued_date(product.getIssued_date());

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "multiadjustmentprint";

	}

	public String deleteadjustmentrequest() {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			int result = inventoryProductDAO.deleteadjustmentrequest(id, loginInfo.getUserId());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("0");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public String savemultiedit() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			String isfromcathlab = request.getParameter("isfromcathlab");
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			// Approve adjustment
			int status_type = 3;
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			int status = 0;
			int parentd = inventoryProductDAO.saveParentAdjustmentData(status_type, userid, date, location, status);
			for (Product product : productForm.getAlladjustmentproduct()) {
				if (product == null) {
					continue;
				}
				int id = product.getId();
				Product product1 = inventoryProductDAO.getProduct("" + id);
				String expirydate3 ="";
				if(!DateTimeUtils.isNull(product.getExpiry_date()).equals("")){
					String[] data = product.getExpiry_date().split("/");
					Calendar mycal = new GregorianCalendar(Integer.parseInt(data[1]), Integer.parseInt(data[0]) - 1, 1);
					int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
					expirydate3 = daysInMonth + "-" + data[0] + "-" + data[1];
					expirydate3 = DateTimeUtils.getCommencingDate1(expirydate3);
				}
				product.setExpiry_date(expirydate3);
				int res = inventoryProductDAO.saveChildMultiEditData(product, product1, userid, date, location,
						parentd);
			}
			session.setAttribute("multieditprodid", null);
			session.setAttribute("multieditcount", null);
			String searchtext = productForm.getSearchtext();
			String category_id = productForm.getCategory_id();
			String withstock_filter = productForm.getWithstock_filter();
			String product_type = productForm.getProduct_type();
			String shelf_filter = productForm.getShelf_filter();
			session.setAttribute("stockstatus_searchtext", searchtext);
			session.setAttribute("stockstatus_category_id", category_id);
			session.setAttribute("stockstatus_withstock_filter", withstock_filter);
			session.setAttribute("stockstatus_product_type", product_type);
			session.setAttribute("stockstatus_shelf_filter", shelf_filter);
			session.removeAttribute("multiadjustcount");
			if (isfromcathlab != null) {
				if (isfromcathlab.equals("1")) {
					return "transferproductdata2";
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "listproduct";
	}

	public String showmultieditforapprove() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String parentid = request.getParameter("parentid");
			String userid = loginInfo.getUserId();
			Product product1 = inventoryProductDAO.getMultiAdjustEditData(parentid);

			ArrayList<Product> childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
			StringBuilder builder = new StringBuilder();

			builder.append("" + product1.getLocationName() + "");
			builder.append("~");

			builder.append("" + product1.getDateTime() + "");
			builder.append("~");

			builder.append("" + product1.getId() + "");
			builder.append("~");

			builder.append("" + product1.getUserid() + "");
			builder.append("~");
			int i = 0;
			int j = 1;
			for (Product product2 : childtransferlist) {
				Product product = inventoryProductDAO.getProduct(product2.getProductid());
				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}
				String location = pharmacyDAO.getLocationName(product.getLocation());

				builder.append("<tr>");
				builder.append("<td>" + j + "</td>");
				builder.append("<input type='hidden' class='multieditapproveclass' value='" + product2.getId()
						+ "' class='form-control' />");
				builder.append("<td>" + product.getProduct_name() + "</td>");
				builder.append("<td class='hidden'>" + product2.getPre_pack() + "</td>");
				builder.append("<td class='hidden'>" + product2.getCurr_pack() + "</td>");
				builder.append("<td>" + product2.getPre_mrp() + "</td>");
				builder.append("<td>" + product2.getCurr_mrp() + "</td>");
				builder.append("<td>" + product2.getPre_saleprice() + "</td>");
				builder.append("<td>" + product2.getCurr_saleprice() + "</td>");
				builder.append("<td>" + product2.getPre_purprice() + "</td>");
				builder.append("<td>" + product2.getCurr_purprice() + "</td>");
				builder.append("<td>" + product2.getPre_batch() + "</td>");
				builder.append("<td>" + product2.getCurr_batch() + "</td>");
				builder.append(
						"<td style='color:" + product2.getPreexpcolor() + "'>" + product2.getPre_expdate() + "</td>");
				builder.append(
						"<td style='color:" + product2.getCurrexpcolor() + "'>" + product2.getCurr_expdate() + "</td>");
				builder.append("<td>" + product2.getPre_gst() + "%</td>");
				builder.append("<td>" + product2.getCurr_gst() + "%</td>");
				builder.append("<td>" + DateTimeUtils.isNull(product2.getPre_pshelf()) + "</td>");
				builder.append("<td>" + DateTimeUtils.isNull(product2.getCurr_pshelf()) + "</td>");
				builder.append("<td>" + DateTimeUtils.isNull(product2.getPre_sshelf()) + "</td>");
				builder.append("<td>" + DateTimeUtils.isNull(product2.getCurr_sshelf()) + "</td>");
				builder.append("<td>" + product2.getRemark() + "</td>");
				builder.append("<td><textarea class='form-control' rows='2' id='multieditaprove_remark"
						+ product2.getId() + "' name='remark" + product2.getId()
						+ "' style='background-color: rgba(255, 248, 220, 0.48);' placeholder='Write approve remark here'></textarea></td>");
				builder.append("</tr>");
				i++;
				j++;
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	// 05-03-2020   multiple edit product approve code
	public String updatemultieditappove() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String parentid = request.getParameter("id");
			Product product2 = inventoryProductDAO.getMultiAdjustEditData(parentid);
			ArrayList<Product> childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			for (Product product : childtransferlist) {
				product.setCurr_expdate(DateTimeUtils.getCommencingDate1(product.getCurr_expdate()));
				int res = inventoryProductDAO.updateProductData(product);
				int res1 = indentDAO.updateMultiChildAdjustment("" + product.getId(), product.getRemark());
			}
			int status = 1;
			int res = indentDAO.updateMultiAdjustment(parentid, status, userid, date);
			//   12-03-2020 set filter and pagination as it is when reload
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String iseditadjustrejestproduct = "1";

			session.setAttribute("editadjustrejestproduct_fromdate", fromdate);
			session.setAttribute("editadjustrejestproduct_todate", todate);
			session.setAttribute("iseditadjustrejestproduct", iseditadjustrejestproduct);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "multipleadjust";
	}

	public String rejectmultipleadjsutment() throws Exception {
		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;
		try {
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			StringBuilder buffer1 = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer1.append(line);
			}
			String data = buffer1.toString();
			JSONObject jsonObject = new JSONObject(data);

			String id = jsonObject.getString("id");
			String reason = jsonObject.getString("reason");
			String userid = loginInfo.getUserId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			int status = 2;
			int res = indentDAO.updateMultiAdjustment(id, status, userid, date);
			int ress = inventoryProductDAO.updateRejectResonofMultiAdjustEdit(id, reason);

			//   12-03-2020 set filter and pagination as it is when reload
			String fromdate = jsonObject.getString("fromdate");
			String todate = jsonObject.getString("todate");
			String iseditadjustrejestproduct = "1";

			session.setAttribute("editadjustrejestproduct_fromdate", fromdate);
			session.setAttribute("editadjustrejestproduct_todate", todate);
			session.setAttribute("iseditadjustrejestproduct", iseditadjustrejestproduct);

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", "" + res);
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", "0");
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} finally {
			connection.close();
		}
		return null;
	}

	// 18-03-2020   multiple approve request at a time
	public String multipleapproveadjustedit() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String ids = request.getParameter("approverequestid");
			String approve_notes = request.getParameter("approve_notes");
			for (String parentid : ids.split(",")) {
				if (parentid.equals("0")) {
					continue;
				}
				Product product2 = inventoryProductDAO.getMultiAdjustEditData(parentid);
				if (product2.getStatus().equals("2")) {
					// adjustment

					ArrayList<Product> childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
					String userid = loginInfo.getUserId();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String date = dateFormat.format(cal.getTime());
					for (Product product : childtransferlist) {
						String adj_type = "" + product.getAdjustment_type();
						String pre_stock = "" + product.getPre_stock();
						String change_stock = product.getCurrent_qty();
						String comment = approve_notes;
						String adj_change_stock_indi = product.getChange_qty();

						int stock = Integer.parseInt(pre_stock);
						int qty = Integer.parseInt(change_stock);
						int changerqty = Integer.parseInt(adj_change_stock_indi);

						int qtyinout = 1;
						if (adj_type.equals("2")) {
							qtyinout = 0;
						}
						String id = product.getProductid();
						int res = inventoryProductDAO.updateProductStocks(id, qty);
						int adjustmentid = Integer.parseInt(parentid);

						// stock log
						Product product1 = inventoryProductDAO.getProduct(id);
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

						String source = "Product Adjustment";
						if (adjustmentid > 0) {
							int reslog = inventoryProductDAO.insertIntoProductLog(loginInfo.getUserId(), datetime,
									product.getLocation(), qtyinout, id, product1.getCatalogueid(), source, qty, stock,
									changerqty, "0", "0", 0, adjustmentid, 0, "0");
							String date1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
							boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(id, date1);
							if (!checkopningstockexist) {
								int r = pharmacyDAO.saveOpeningStock(id, date1, stock, "0");
							}
						}

						int res1 = indentDAO.updateMultiChildAdjustment("" + product.getId(), comment);
					}
					int status = 1;
					int res = indentDAO.updateMultiAdjustment(parentid, status, userid, date);
				} else if (product2.getStatus().equals("3")) {
					// edit
					ArrayList<Product> childtransferlist = indentDAO.getChildMultiAdjustEditData(parentid);
					String userid = loginInfo.getUserId();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String date = dateFormat.format(cal.getTime());
					for (Product product : childtransferlist) {
						product.setCurr_expdate(DateTimeUtils.getCommencingDate1(product.getCurr_expdate()));
						int res = inventoryProductDAO.updateProductData(product);
						int res1 = indentDAO.updateMultiChildAdjustment("" + product.getId(), approve_notes);
					}
					int status = 1;
					int res = indentDAO.updateMultiAdjustment(parentid, status, userid, date);
				}

			}

			//   12-03-2020 set filter and pagination as it is when reload
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String iseditadjustrejestproduct = "1";

			session.setAttribute("editadjustrejestproduct_fromdate", fromdate);
			session.setAttribute("editadjustrejestproduct_todate", todate);
			session.setAttribute("iseditadjustrejestproduct", iseditadjustrejestproduct);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "multipleadjust";
	}

	public String editpharmacybillreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			/*
			 * ArrayList<Breadcrumbs> indentflowlist = new
			 * ArrayList<Breadcrumbs>(); ArrayList<Breadcrumbs>
			 * indentflowlisttemp = new ArrayList<Breadcrumbs>(); if
			 * (session.getAttribute("indentflowlist") != null) {
			 * indentflowlisttemp = (ArrayList<Breadcrumbs>)
			 * session.getAttribute("indentflowlist"); } boolean
			 * isavilablemodule= false; int modulecount =0; for (Breadcrumbs
			 * breadcrumbs : indentflowlisttemp) {
			 * breadcrumbs.setIscurrent(false);
			 * breadcrumbs.setSqno(modulecount); modulecount++;
			 * if(breadcrumbs.getName().equals("Pharmacy Cancel Bill Report")){
			 * isavilablemodule =true; breadcrumbs.setIscurrent(true);
			 * indentflowlist.add(breadcrumbs); break; }else{
			 * indentflowlist.add(breadcrumbs); } } if(!isavilablemodule){
			 * Breadcrumbs breadcrumbs = new Breadcrumbs();
			 * breadcrumbs.setName("Pharmacy Cancel Bill Report");
			 * breadcrumbs.setOn(true); breadcrumbs.setSqno(modulecount);
			 * breadcrumbs.setUrllink("cancelpharmacybillreportProduct");
			 * breadcrumbs.setIscurrent(true);
			 * breadcrumbs.setShowingname("Cancel Bill Report");
			 * indentflowlist.add(breadcrumbs); }
			 * session.setAttribute("indentflowlist",indentflowlist);
			 */

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String location = productForm.getLocation();
			String userid = productForm.getUserid();
			String searchtext = productForm.getSearchtext();
			if (fromdate != null) {
				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					fromdate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(calendar.getTime());
			}
			if (todate != null) {
				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					todate = dateFormat.format(calendar.getTime());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(calendar.getTime());
			}
			searchtext = DateTimeUtils.isNull(searchtext);
			location = DateTimeUtils.numberCheck(location);
			userid = DateTimeUtils.numberCheck(userid);

			int count = inventoryProductDAO.getCountEditBillReport(fromdate, todate, location, searchtext, userid);
			pagination.setPreperties(count);
			ArrayList<Product> editbillreport = inventoryProductDAO.getEditBillReport(fromdate, todate, location,
					pagination, searchtext, userid);
			pagination.setPage_records(editbillreport.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			productForm.setCancelbillreport(editbillreport);

			ArrayList<UserProfile> userlist = inventoryProductDAO.getPharmacyAndSystemUserid();
			productForm.setUserlist(userlist);

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationListPharmacy(locationlist);

			productForm.setSearchtext(searchtext);
			productForm.setLocation(location);
			productForm.setUserid(userid);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "editpharmacybillreport";
	}

	public String jobworkdashboard() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String userid = loginInfo.getUserId();

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Job Work Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Job Work Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("jobworkdashboardProduct");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			String filter_status = productForm.getFilter_status();
			String searchtext = productForm.getSearchtext();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();

			searchtext = DateTimeUtils.isNull(searchtext);

			filter_status = DateTimeUtils.isNull(filter_status);

			if (DateTimeUtils.isNull(fromdate).equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (DateTimeUtils.isNull(todate).equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}

			int count = inventoryProductDAO.getCountJobWorkDashboard(searchtext, fromdate, todate, filter_status);
			pagination.setPreperties(count);

			ArrayList<Product> parenttransferlist = inventoryProductDAO.getJobWorkDashboard(searchtext, fromdate,
					todate, filter_status, pagination);
			pagination.setPage_records(parenttransferlist.size());
			// pagination.setPage_records(0);
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setParenttransferlist(parenttransferlist);

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];

			productForm.setDate(DateTimeUtils.getCommencingDate1(date));
			productForm.setFilter_status(filter_status);
			productForm.setSearchtext(searchtext);
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListRequest();
			productForm.setWarehouseList(warehouseList);

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList("0");
			productForm.setVendorList(vendorList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "jobworkdashboard";
	}

	public String savereceiptjobwork() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			String receipt_vendorid = request.getParameter("receipt_vendorid");
			ArrayList<Product> arrayList = inventoryProductDAO.getJobWorkPendingRequest(receipt_vendorid);
			for (Product product : arrayList) {
				String receipt_status = request.getParameter("receipt_status" + product.getId());
				String receipt_comment = request.getParameter("receipt_comment" + product.getId());
				if (DateTimeUtils.numberCheck(receipt_status).equals("0")) {
					continue;
				}
				int res = inventoryProductDAO.updateReceiptJobWorkStatus(loginInfo.getUserId(), datetime,
						receipt_status, receipt_comment, product.getId());
				boolean totalrequest = true;
				int maincount = inventoryProductDAO.getJobWorkCount(totalrequest, product.getParentid());
				totalrequest = false;
				int requestedcount = inventoryProductDAO.getJobWorkCount(totalrequest, product.getParentid());
				if (maincount == requestedcount) {
					res = inventoryProductDAO.updateJobWorkMainStatus(product.getParentid(), "1");
				} else {
					res = inventoryProductDAO.updateJobWorkMainStatus(product.getParentid(), "2");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "jobworkdashboardmethod";
	}

	public String jobworkprint() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		String id = request.getParameter("id");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = loginInfo.getUserId();
			productForm.setCurr_user(userid);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curr_date = dateFormat.format(cal.getTime());
			productForm.setDate(curr_date);

			Product product = inventoryProductDAO.getJobWorkParentData(id);

			ArrayList<Product> jobworkprint = inventoryProductDAO.getJobWorkPrint(id);
			productForm.setJobworkprint(jobworkprint);

			session.setAttribute("jobworkprintproduct", product);

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			// ArrayList<Clinic> locationAdressList =
			// accountsDAO.getLetterHeadDetails(loginInfo.getClinicid());
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "jobworkprint";
	}

	public String jobworkissuereport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String userid = loginInfo.getUserId();

			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Job Work Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Job Work Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("jobworkdashboardProduct");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);

			String filter_status = request.getParameter("filter_status");
			String searchtext = productForm.getSearchtext();
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			String vendor = productForm.getVendor();
			searchtext = DateTimeUtils.isNull(searchtext);

			filter_status = DateTimeUtils.isNull(filter_status);

			if (DateTimeUtils.isNull(fromdate).equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (DateTimeUtils.isNull(todate).equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}

			vendor = DateTimeUtils.numberCheck(vendor);

			int count = inventoryProductDAO.getJobWorkReportCount(searchtext, fromdate, todate, filter_status, vendor);
			pagination.setPreperties(count);

			ArrayList<Product> parenttransferlist = inventoryProductDAO.getJobWorkReport(searchtext, fromdate, todate,
					filter_status, pagination, vendor);
			pagination.setPage_records(parenttransferlist.size());
			// pagination.setPage_records(0);
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setParenttransferlist(parenttransferlist);

			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);

			productForm.setFromdate(fromdate);
			productForm.setTodate(todate);

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];

			productForm.setDate(DateTimeUtils.getCommencingDate1(date));
			productForm.setFilter_status(filter_status);
			productForm.setSearchtext(searchtext);
			productForm.setVendor(vendor);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListRequest();
			productForm.setWarehouseList(warehouseList);

			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList("0");
			productForm.setVendorList(vendorList);

			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "jobworkissuereport";
	}
	
	public String openingClosingByBatch()throws Exception{

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String fromDate = productForm.getFromdate();
			String toDate = productForm.getTodate();
			String searchbyprodname = productForm.getSearchbyprodname();
			String location_filter = productForm.getLocation_filter();
			String filter_status = productForm.getFilter_status();
			String iscalculationbase = request.getParameter("iscalculationbase");
			iscalculationbase = DateTimeUtils.isNull(iscalculationbase);

			if (iscalculationbase.equals("")) {
				iscalculationbase = "0";
			}
			productForm.setIscalculationbase(iscalculationbase);
			location_filter = DateTimeUtils.numberCheck(location_filter);

			if (filter_status != null) {
				if (filter_status.equals("")) {
					filter_status = "1";
				}
			} else {
				filter_status = "1";
			}
			productForm.setFilter_status(filter_status);
			productForm.setLocation_filter(location_filter);
			productForm.setIsfrombincard(1);
			
			int maxlimit = 0, minlimit = 0;
			if (fromDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());

			}
			if (toDate.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				// cal.add(Calendar.DATE, -7);
				toDate = dateFormat.format(cal.getTime());
			}

			if (searchbyprodname != null) {
				if (searchbyprodname.equals("")) {
					searchbyprodname = null;
				}
			}
			
			
			
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy");
			Calendar cal2 = Calendar.getInstance(); 
			String todateyear = dateFormat2.format(cal2.getTime());
			
			DateFormat dateFormat1 = new SimpleDateFormat("MM");
			Calendar cal1 = Calendar.getInstance(); 
			String tomonth = dateFormat1.format(cal1.getTime());
			
			String filter_bydate = productForm.getFilter_bydate();
			if(DateTimeUtils.isNull(filter_bydate).equals("")){
				if(Integer.parseInt(tomonth)>=4){
					filter_bydate = todateyear+"_"+(Integer.parseInt(todateyear)+1);
				}else{
					filter_bydate = (Integer.parseInt(todateyear)-1)+"_"+todateyear;
				}
			}else{
				filter_bydate = filter_bydate.split("-")[0]+"_"+filter_bydate.split("-")[1];
			}
			int currYear = Integer.parseInt(todateyear);
			int preYear = Integer.parseInt(filter_bydate.split("_")[0]);
			int lateYear = Integer.parseInt(filter_bydate.split("_")[1]);
			boolean flag =true;
			if(preYear<=currYear){
				if(lateYear<=(currYear+1)){
					
				}else{
					//greater
					flag = false;	
				}
			}else{
				//greater
				flag = false;
			}
			
			ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromDate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(toDate));
			
			String product_id = productForm.getProduct_id();
			
			product_id = DateTimeUtils.numberCheck(product_id);
			int count = 0;
			String tablename="product_stock_log"+filter_bydate+"";
			boolean checkView = inventoryProductDAO.checkViewPresentOfTable(tablename);
			if(!checkView){
				String viewFromDate = filter_bydate.split("_")[0]+"-"+"04"+"-"+"01";
				String viewToDate =  filter_bydate.split("_")[1]+"-"+"03"+"-"+"31";
				inventoryProductDAO.createViewOfTable("product_stock_log",tablename,viewFromDate,viewToDate);
			}
			String category_id = "0";
			ArrayList<Product> openingstockList = inventoryProductDAO.getListOfOpeningClosingBatch(fromDate, toDate,
					null, searchbyprodname, product_id, location_filter, null,filter_bydate);
			productForm.setOpeningstockList(openingstockList);
			Product product = openingstockList.get(openingstockList.size() - 1);
			productForm.setOpentotalcount(product.getOpentotalcount());
			pagination.setPage_records(openingstockList.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setTotalopeningstock(product.getTotalopeningstock());
			productForm
					.setTotalopeingstockvalue(Math.round(product.getTotalopeingstockvalue() * 100.0) / 100.0);
			productForm.setTotalqtyin(product.getTotalqtyin());
			productForm.setTotalqtyinvalue(Math.round(product.getTotalqtyinvalue() * 100.0) / 100.0);
			productForm.setTotalqtyout(product.getTotalqtyout());
			productForm.setTotalstockvalue(Math.round(product.getTotalstockvalue() * 100.0) / 100.0);
			productForm.setTotalssaleprice(Math.round(product.getTotalssaleprice() * 100.0) / 100.0);
			productForm.setTotalclosingstock(product.getTotalclosingstock());
			productForm.setTotalclosingvalue(Math.round(product.getTotalclosingvalue() * 100.0) / 100.0);
			productForm.setTotalunknownvalue(Math.round(product.getTotalunknownvalue() * 100.0) / 100.0);
			productForm.setTotalunknownqty(product.getTotalunknownqty());
		
			
			filter_bydate = filter_bydate.split("_")[0]+"-"+filter_bydate.split("_")[1];
			productForm.setFilter_bydate(filter_bydate);
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
			toDate = DateTimeUtils.getCommencingDate1(toDate);
			productForm.setFromdate(fromDate);
			productForm.setTodate(toDate);
			productForm.setIsfrominventorycatalogue("1");
			setLetterData(location_filter, connection);
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList();
			productForm.setWarehouseList(warehouseList);
			
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			ArrayList<Product> prodnamelist = new ArrayList<>();
			if(!location_filter.equals("0")){
				prodnamelist = catalogueDAO.getAllProductList(location_filter);
			}
			productForm.setProdnamelist(prodnamelist);
			
			productForm.setProduct_id(product_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	
		return "openingClosingByBatch";
	}
	
	public String setproductofStoreOCR() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			ArrayList<Product> list = catalogueDAO.getAllProductList(id);
			StringBuffer buffer = new StringBuffer();

			/*buffer.append("<label>Select Product</label>");*/
			buffer.append("<select id='product_id' class='form-control chosen'  name='product_id' >");
			buffer.append("<option value='0'>Select Product</option>");
			for (Product product : list) {
				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	public String gatepassdashboard()throws Exception{
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule = false;
			int modulecount = 0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if (breadcrumbs.getName().equals("Gate Pass Dashboard")) {
					isavilablemodule = true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				} else {
					indentflowlist.add(breadcrumbs);
				}
			}
			if (!isavilablemodule) {
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Gate Pass Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("gatepassdashboardProduct");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist", indentflowlist);
			
			String searchtext = DateTimeUtils.isNull(productForm.getSearchtext());
			String fromdate = productForm.getFromdate();
			String todate = productForm.getTodate();
			
			String gatePassTypeFilter = DateTimeUtils.isNull(productForm.getGatePassTypeFilter());
			String vendor_id = DateTimeUtils.numberCheck(productForm.getVendor_id());
			
			if(DateTimeUtils.isNull(fromdate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fromdate = dateFormat.format(new Date());
			}else{
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}
			
			if(DateTimeUtils.isNull(todate).equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				todate = dateFormat.format(new Date());
			}else{
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			
			int count = inventoryProductDAO.getGatePassCount(searchtext,fromdate,todate,gatePassTypeFilter,vendor_id);
			pagination.setPreperties(count);
			
			ArrayList<Product> gatePassList = inventoryProductDAO.getGatePassList(searchtext,fromdate,todate,pagination,gatePassTypeFilter,vendor_id); 
			productForm.setGatePassList(gatePassList);
			
			pagination.setPage_records(gatePassList.size());
			productForm.setTotalRecords(count);
			productForm.setPagerecords(""+gatePassList.size());
			
			productForm.setSearchtext(searchtext);
			productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			productForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListRequest();
			productForm.setWarehouseList(warehouseList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "gatepassdashboard";
	}
	
	public String addnewgatepass() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String cataloguid = request.getParameter("pid");
			String qty = request.getParameter("qty");
			String count = request.getParameter("count");
			String data = request.getParameter("data");
			String warehouse_id = request.getParameter("warehouse_id");
			if (data == null) {
				data = "0";
			}
			if (data.equals("")) {
				data = "0";
			}
			int index = Integer.parseInt(count);
			index--;
			String userid = loginInfo.getUserId();
			boolean ispharmacist = inventoryProductDAO.isPharamcistOrNot(userid);
			String location = "";
			if (ispharmacist) {
				Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);
				location = priscription.getLocation();
			} else {
				location = inventoryProductDAO.getHISUserLocation(userid);
			}

			if (location == null) {
				location = "0";
			}

			StringBuffer buffer = new StringBuffer();

			Product product = inventoryProductDAO.getProductCatalogueDetails(cataloguid);
			// String
			// pid=inventoryProductDAO.getProdidFromCatagoryid(cataloguid);
			String pid = cataloguid;
			boolean flag = false;
			for (String t : data.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				if (t.equals(pid)) {
					flag = true;
					continue;
				}
			}
			if (location.equals("")) {
				buffer.append("1");
			} else if (!flag) {
				buffer.append("<tr>");
				buffer.append("<td>" + count + " <input type='hidden' class='dclass' value='" + pid + "' /></td>");
				buffer.append("<td>" + product.getProduct_name() + " <input type='hidden' value='" + pid
						+ "' name='product_id" + pid + "' /> </td>");
				buffer.append("<td>" + qty + " <input type='hidden' value='" + qty + "' id='qty"+pid+"' name='qty" + pid + "' /></td>");
				buffer.append("<td><textarea class='form-control' name='comment" + pid
						+ "' rows='2' placeholder='Enter Comments' id='comment" + pid + "'></textarea></td>");
				buffer.append(
						"<td><a href='#' onclick=deleteIndentRow(this) ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
				buffer.append("</tr>");
			} else {
				buffer.append("0");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}
	
	public String savegatepass() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			Product product = new Product();
			product.setGatePassType(productForm.getGatePassType());
			product.setDate(DateTimeUtils.getCommencingDate1(productForm.getDate()));
			product.setVehicleNumber(productForm.getVehicleNumber());
			product.setDateOfReturn(DateTimeUtils.getCommencingDate1(productForm.getDateOfReturn()));
			product.setApproximateValue(productForm.getApproximateValue());
			product.setSupplierId(productForm.getSupplierId());
			product.setWarehouse_id(productForm.getWarehouse_id());
			product.setUserid(loginInfo.getUserId());
			product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			product.setVendorcode(productForm.getVendorcode());
			
			String generateYrWise = inventoryProductDAO.generateGatePassSeq();
			product.setSequence(generateYrWise);
			int parentid = inventoryProductDAO.saveGatePassParent(product);

			String allproductid = request.getParameter("allproductid");
			String[] data = allproductid.split(",");

			for (String string : data) {
				if (string.equals("0")) {
					continue;
				}
				Product master = new Product();
				String qty = request.getParameter("qty" + string);
				String catalogueid = request.getParameter("product_id" + string);
				String comment = request.getParameter("comment" + string);
				master.setComment(comment);
				master.setQty(Double.parseDouble(qty));
				master.setCatalogueid(catalogueid);
				master.setParentid(""+parentid);
				int res = inventoryProductDAO.saveChildGatePass(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferGatePassDash";
	}
	
public String gatepassprint()throws Exception{


	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		
		String id = request.getParameter("id");
		
		Product product = inventoryProductDAO.getGatePassDetails(id);
		productForm.setProduct(product);
		Vendor vendor = inventoryVendorDAO.getVendor(product.getSupplierId());
		productForm.setVendorGstNo(vendor.getTinno());
		ArrayList<Product> productList =inventoryProductDAO.getGatePassProductList(id);
		productForm.setProductList(productList);
		String seconderyletterhead = procurementDAO.secondryLetterhead();
		productForm.setSeconderyletterhead(seconderyletterhead);
		String currentDate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		
		productForm.setCreatedby(product.getUserid() + "|" + DateTimeUtils.getIndianDateTimeFormat(product.getDateTime()));
		productForm.setVendor(vendor.getName());
		productForm.setLocation(product.getLocationName());
		productForm.setDate(product.getDate());
		productForm.setAddress(DateTimeUtils.isNull(vendor.getAddress()));
		productForm.setMobile(DateTimeUtils.isNull(vendor.getMobile_pri()));
		productForm.setVendoremail(DateTimeUtils.isNull(vendor.getEmail()));
		String printedby = loginInfo.getUserId() + "/" + DateTimeUtils.getIndianDateTimeFormat(currentDate);
		productForm.setPrintedby(printedby);
		
		productForm.setAccountno(vendor.getAccountno());
		productForm.setIfsc(vendor.getIfsc());
		productForm.setBranch(vendor.getBranch());
		productForm.setBankname(vendor.getBankname());

		if (product.getWarehouse_id() != null) {
			if (product.getWarehouse_id().equals("32") || product.getWarehouse_id().equals("33")
					|| product.getWarehouse_id().equals("34")) {
				UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
				productForm.setClinicName(userProfile.getClinicname());
				productForm.setClinicaddress(userProfile.getAddress());
				productForm.setLandLine(userProfile.getPhone());
				productForm.setFullname(userProfile.getFullname());
				productForm.setClinicemail(userProfile.getEmail());
				productForm.setPlace(userProfile.getCity());
				productForm.setWebsiteUrl(userProfile.getWebsite());
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				productForm.setClinicLogo(clinic.getUserImageFileName());
				productForm.setGstnno(clinic.getGstn_no());
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				Clinic clinic2 = new Clinic();
				clinic2.setAddress(DateTimeUtils.isNull(userProfile.getAddress()));
				locationAdressList.add(clinic2);
				productForm.setLocationAdressList(locationAdressList);
			} else {
				// letter head
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				productForm.setClinicName(clinic.getClinicName());
				productForm.setClinicOwner(clinic.getClinicOwner());
				productForm.setOwner_qualification(clinic.getOwner_qualification());
				productForm.setLocationAdressList(locationAdressList);
				// productForm.setAddress(clinic.getAddress());
				productForm.setLandLine(clinic.getLandLine());
				productForm.setClinicemail(clinic.getEmail());
				productForm.setWebsiteUrl(clinic.getWebsiteUrl());
				productForm.setClinicLogo(clinic.getUserImageFileName());
				productForm.setGstnno(clinic.getGstn_no());
			}
		} else {
			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
			locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			productForm.setClinicName(clinic.getClinicName());
			productForm.setClinicOwner(clinic.getClinicOwner());
			productForm.setOwner_qualification(clinic.getOwner_qualification());
			productForm.setLocationAdressList(locationAdressList);
			// productForm.setAddress(clinic.getAddress());
			productForm.setLandLine(clinic.getLandLine());
			productForm.setClinicemail(clinic.getEmail());
			productForm.setWebsiteUrl(clinic.getWebsiteUrl());
			productForm.setClinicLogo(clinic.getUserImageFileName());
			productForm.setGstnno(clinic.getGstn_no());
		}
		
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
		productForm.setLocationListPharmacy(locationlist);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}

	return "printgatepass";

}

public String modifyindnetrequest(){
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		String parentId = request.getParameter("parentid");
		Product product = inventoryProductDAO.getParentTransferData(parentId);
		ArrayList<Product> childtransferlist = indentDAO.getChildTranfserData(parentId);
		StringBuffer buffer = new StringBuffer();
		int count =0;
		int res =1;
		for (Product product2 : childtransferlist) {
			double limit_qty=0;
			if(loginInfo.isKalmegha()) {
				String description=inventoryProductDAO.getdescription(loginInfo.getId());
				limit_qty=inventoryProductDAO.getqtyProductLimit(product2.getCatalogueid(),description);
			}
			double avail_qty = indentDAO.getAllAvailableStockByCatId(product2.getCatalogueid(), product2.getLocation());
			if(loginInfo.isKalmegha()) {
				if(product2.getReq_limit_qty().equals("0") && product2.getStatus().equals("0")) {
					buffer.append("<tr style='color:red;'>");
				}else {
				buffer.append("<tr>");
				}
			}else {
			buffer.append("<tr>");
			}
			buffer.append("<td>" + res + " <input type='hidden' class='dclass' value='" + product2.getCatalogueid() + "' /></td>");
			buffer.append("<td>" + product2.getProduct_name() + " <input type='hidden' value='" + product2.getCatalogueid()+ "' name='product_id" + product2.getCatalogueid() + "' /> </td>");
			buffer.append("<td>" + avail_qty + "<input type='hidden' value='" + avail_qty + "' name='avail_qty"+ product2.getCatalogueid() + "' /></td>");
			buffer.append("<td>" + product2.getStock() + " <input type='hidden' value='" + product2.getStock() + "' name='qty" + product2.getCatalogueid() + "' /></td>");
			if(loginInfo.isKalmegha()) {
				buffer.append("<td>" + limit_qty + " <input type='hidden' value='" + limit_qty + "' id='limitqty" +  product2.getCatalogueid() + "' name='limit" + product2.getCatalogueid() + "' /></td>");
			}
			buffer.append("<td>" + product2.getExpectedDate() + " <input type='hidden' value='" + product2.getStock() + "' name='expectedDate" + product2.getCatalogueid()+ "' /></td>");
			buffer.append("<td>"+product2.getComment()+"<input type='hidden' name='comment" + product2.getCatalogueid() + "' id='comment" +  product2.getCatalogueid() + "' value='" + product2.getComment() + "' /></td>");
			if(loginInfo.isKalmegha()) {
			  buffer.append("<td>"+product2.getReq_limit_qty()+"<input type='hidden' name='reqqty" + product2.getCatalogueid() + "' id='reqlimitqty" +  product2.getCatalogueid() + "' value='" + product2.getReq_limit_qty() + "' /></td>");
			  buffer.append("<td class='hidden'>"+product2.getStatus()+"<input type='hidden' name='reqqtystatus" + product2.getCatalogueid() + "' id='reqqty_status" +  product2.getCatalogueid() + "' value='" + product2.getStatus() + "' /></td>");
 
			}
			/*buffer.append("<td><textarea class='form-control' name='comment" +  product2.getCatalogueid()
					+ "' rows='2' placeholder='Enter Comments' id='comment" +  product2.getCatalogueid() + "'>"+product2.getComment()+"</textarea></td>");*/
			/*buffer.append(
					"<td><a href='#' onclick=deleteIndentRow(this) ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");*/
			buffer.append("<td><input type='hidden' name='indentChildId" + product2.getCatalogueid() + "' value='" + product2.getId() + "' /></td>");
			buffer.append("</tr>");
			count++;
			res++;
		}
		buffer.append("~~~");
		buffer.append(""+parentId+"");
		buffer.append("~~~");
		buffer.append(""+product.getWarehouse_id()+"");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + buffer.toString() + "");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getlimit() throws Exception{
	
	
	try {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = Connection_provider.getconnection();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
		String qty = jsonObject.getString("qty");
		String product_id = jsonObject.getString("product_id");
		String description=inventoryProductDAO.getdescription(loginInfo.getId());
		double limit_qty=inventoryProductDAO.getqtyProductLimit(product_id,description);
		String data1=qty+"~~"+limit_qty;

		
		 JSONObject object = new JSONObject();
		 object.put("qty", qty);
		 object.put("limit_qty", limit_qty);
		 String response1 = object.toString();
 		 response.setContentType("application/json");
 		 response.setHeader("Cache-Control", "no-cache");
 		 response.getWriter().write(response1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
 }

public String getlimitqty() throws Exception{
	
	if (!verifyLogin(request)) {
		return "login";
	}
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String product_id = request.getParameter("productid");
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		String description=inventoryProductDAO.getdescription(loginInfo.getId());
		double limit_qty=inventoryProductDAO.getqtyProductLimit(product_id,DateTimeUtils.isNull(description));
		double delivrqty=inventoryProductDAO.getdeliverqty(product_id,DateTimeUtils.isNull(description));
		double requetedlimitqty=inventoryProductDAO.getrequestedlimitqty(product_id,DateTimeUtils.isNull(description));
		limit_qty=(limit_qty)-(delivrqty+requetedlimitqty);
		StringBuffer buffer = new StringBuffer();

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+limit_qty);
	} catch (Exception e) {
        e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String saverequestedqty() throws Exception{
	
	
try {
		
		Connection connection=Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		
	    //String patientnae=request.getParameter("patientnm");
		String reqqty=request.getParameter("reqqty");
		String req_product_id=request.getParameter("req_product_id");
		String req_userid=loginInfo.getUserId();
		String date_time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		Product product = new Product();
		
		product.setProduct_id(req_product_id);
		product.setReqqty(reqqty);
		product.setReq_userid(req_userid);
		product.setDateTime(date_time);
		
		int result=inventoryProductDAO.saveRequestedlimitqty(product);
	    
	    response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+"");

		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
		}
	
	return null;
	
	
}

public String approvedlimitqty() throws Exception{
	
	
	if(!verifyLogin(request)){
		return "login";
	}
	
	
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		String fromDate=productForm.getFromdate();
		String toDate=productForm.getTodate();
		if(DateTimeUtils.isNull(fromDate).equals("")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fromDate = dateFormat.format(new Date());
		}else{
			fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		}
		if(DateTimeUtils.isNull(toDate).equals("")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			toDate = dateFormat.format(new Date());
		}else{
			toDate = DateTimeUtils.getCommencingDate1(toDate);
		}
		ArrayList<Product> requestedlimitqtylist = inventoryProductDAO.getrequestedLimitQty(fromDate,toDate);
		productForm.setRequestlimitqtylist(requestedlimitqtylist);
		productForm.setFromdate(DateTimeUtils.getCommencingDate1(fromDate));
		productForm.setTodate(DateTimeUtils.getCommencingDate1(toDate));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "approveqty";
	
	
}

public String approcerequestedqty() throws Exception{
	Connection connection = null;
	try {
        connection=Connection_provider.getconnection();
		
		
		String indentid=request.getParameter("indentid");
		String productid=request.getParameter("productid");
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		
		
		int result=inventoryProductDAO.getApproveLimitQtyStatus(indentid,productid);
		
		
		response.setContentType("text/html"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.getWriter().write(""+result+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
	
}

public String updatereqlimit() throws Exception{
	
	Connection connection = null;
	try {
        connection=Connection_provider.getconnection();
		
		
		String reqlimitqty=request.getParameter("reqreqlimitqty");
		String catalogueid=request.getParameter("catalogueid");
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		
		
		int result=inventoryProductDAO.getupadtereqlimitqty(reqlimitqty,catalogueid);
		
		
		response.setContentType("text/html"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.getWriter().write(""+result+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
	
	
	
}



}