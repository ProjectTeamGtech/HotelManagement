package com.apm.Inventory.web.action;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.DoubleListUIBean;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Inventory.web.form.ProductForm;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Log.eu.bi.AccountLogDAO;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPackageMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class InventoryCatalogueAction extends BaseAction implements ModelDriven<ProductForm>, Preparable {

	ProductForm productForm = new ProductForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(25, 1);

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
			
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Catalogue")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Catalogue");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("Catalogue");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Catalogue");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			
			String categoryid= productForm.getCategory_id();
			if(categoryid==null){
				categoryid = (String)session.getAttribute("category");
			} else if(categoryid.equals("0")){
				categoryid = (String)session.getAttribute("category");	
			}
			 
			if (categoryid == null) {
				categoryid = "2";
			}
			String searchtext = productForm.getSearchtext();
			String sub_categoryid = productForm.getSubcategory_id();
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (sub_categoryid == null) {
				sub_categoryid = "0";
			}

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile profile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());

			if (profile.getProcurementType() != null) {
				if (profile.getProcurementType().equals("1")) {
					loginInfo.setProcurementType(true);
				} else {
					loginInfo.setProcurementType(false);
				}
			} else {
				loginInfo.setProcurementType(false);
			}
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);

			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {

				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
				session.setAttribute("location", location);
			} else {

				String loc = priscription.getLocation();

				if (loc != null) {
					session.setAttribute("location", loc);
					location = loc;
				} else {
					location = "0";
				}
			}

			int count = inventoryProductDAO.geTotalProductCountfrCatalogue(null, categoryid, searchtext, location,
					sub_categoryid);
			pagination.setPreperties(count);
			productForm.setTotalRecords(count);
			ArrayList<Product> productList = inventoryProductDAO.getAllProdfrCatalogue(pagination, 0, null, categoryid,
					searchtext, location, sub_categoryid, loginInfo);
			pagination.setPage_records(productList.size());
			productForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productForm.setProductList(productList);
			productForm.setEdit_catalogue(priscription.getEdit_catalogue());
			productForm.setDelete_catalogue(priscription.getDelete_catalogue());

			productForm.setDirect_transfer(priscription.getDirect_transfer());
			ArrayList<Master> medicineTypeList = inventoryProductDAO.getMedicineTypeList();

			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandListByCategory(categoryid);
			productForm.setBrandnameList(brandnameList);

			// ArrayList<Product> prodnamelist =
			// inventoryProductDAO.getAllProdName();
			productForm.setMedicineTypeList(medicineTypeList);

			productForm.setCategory_id(categoryid);

			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			productForm.setVendorList(vendorList);

			ArrayList<Master> cellList = inventoryProductDAO.getcellList("32,33,34,35,36");
			productForm.setCellList(cellList);

			ArrayList<Master> locationListPharmacy = pharmacyDAO.getAllLocation();
			productForm.setLocationListPharmacy(locationListPharmacy);

			productForm.setLocation(location);
			productForm.setInventory_transfer(priscription.getInventory_transfer());
			ArrayList<Master> genericnamelist = new ArrayList<Master>();
			ArrayList<Master> mfglist = new ArrayList<Master>();
			if(loginInfo.isAuto_generic_name()){
				genericnamelist = inventoryProductDAO.getGenericMasterList();
				mfglist = inventoryProductDAO.getMFGMasterList();
			}
			productForm.setGenericnamelist(genericnamelist);
			productForm.setMfglist(mfglist);
			
			ArrayList<String> unitOfMeseurementList = inventoryProductDAO.getUnitOfMessurementList();
			productForm.setUnitOfMeseurementList(unitOfMeseurementList);
			productForm.setSubcategory_id(sub_categoryid);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "liststock";
	}
	
	public String svecat(){
		
		return null;
	}
	
	public String manage(){
		
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			ArrayList<Master>catlogList = inventoryProductDAO.getCatalogueList();
			productForm.setCatlogList(catlogList);
			
			ArrayList<Master>secstoreList = inventoryProductDAO.getSecStoreNameList();
			productForm.setSecstoreList(secstoreList);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "managecatlogue";
	}

	public String deleteprod() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			int res = inventoryProductDAO.deleteProduct(id);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			String userid = loginInfo.getUserId();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			res = inventoryProductDAO.addToProductDeletedLog(userid, date, location, 1, id);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}
		return execute();
	}

	public String deletecheck() throws Exception { 

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			for (String s : id.split(",")) {

				if (s.equals("0")) {
					continue;
				}

				int r = inventoryProductDAO.deleteProduct(s);
				String location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
				String userid = loginInfo.getUserId();
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				int res = inventoryProductDAO.addToProductDeletedLog(userid, date, location, 1, id);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}

	public String addtocart() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// InventoryProductDAO inventoryProductDAO= new
			// JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			String newprodid="0";
			if (session.getAttribute("prodidname") != null) {
				prodid = (String) session.getAttribute("prodidname");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					newprodid = newprodid + "," + t;
					count++;
				}
			}

			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				boolean flag= false;
				for (String tt : newprodid.split(",") ) {
					if (tt.equals("0")) {
						continue;
					}
					if (tt.equals(s)) {
						flag = true;
						break;
					}
				}
				if(!flag){
					prodid = prodid + "," + s;
					count++;
				}
				
			}
			session.setAttribute("prodidname", prodid);
			String data = "<a href='#' data-toggle='modal' onclick='showCartPopUp()'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "
					+ count + " </span></a>";

			session.setAttribute("tcount", "" + count);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String showtransfermedicine() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ArrayList<Master> locationlist = inventoryProductDAO.getAllLocation(null);
			StringBuilder builder = new StringBuilder();
			int i = 0;
			int j = 1;
			if (session.getAttribute("prodidname") != null) {
				String prodid = (String) session.getAttribute("prodidname");
				for (String mdicinenameid : prodid.split(",")) {
					if (mdicinenameid.equals("0")) {
						continue;
					}
					int isinadjustment = inventoryProductDAO.checkProductInAdjustment(mdicinenameid);
					if(isinadjustment==1){
						continue;
					}
					
					isinadjustment = pharmacyDAO.checkProductInSale(mdicinenameid);
					if(isinadjustment==1){
						continue;
					}
					
					Product product = inventoryProductDAO.getProduct(mdicinenameid);

					String hsnno = "";
					if (product.getHsnno() != null) {
						hsnno = product.getHsnno();
					}

					String location = pharmacyDAO.getLocationName(product.getLocation());

					builder.append("<tr>");
					builder.append("<td>" + j + "</td>");
					builder.append("<input type='hidden' class='ajclass' value='" + i + "' class='form-control' />");
					builder.append("<input type='hidden' name='allproduct[" + i + "].product_id' id='product_id" + i+ "' value='" + i + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].id' id='id" + i + "' value='"+ mdicinenameid + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].location' id='location" + i+ "' value='" + product.getLocation() + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].hsnno' id='hsnno" + i+ "' value='" + hsnno + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].product_name' id='product_name" + i+ "' value='" + product.getProduct_name() + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].batch_no' id='batch_no" + i+ "' value='" + product.getBatch_no() + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].expiry_date' id='expiry_date" + i+ "' value='" + product.getExpiry_date() + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].loc' id='loc" + i+ "' value='" + location + "'>");
					builder.append("<input type='hidden' name='allproduct[" + i + "].stock' id='stock" + i+ "' value='" + product.getStock() + "'>");
					builder.append("<td>" + hsnno + "</td>");
					builder.append("<td>"+product.getProduct_name()+"</td>");
					builder.append("<td>"+product.getBatch_no()+"</td>");
					builder.append("<td>"+product.getExpiry_date()+"</td>");
					builder.append("<td>"+location+"</td>");
					builder.append("<td>"+product.getStock()+"</td>");
					if (i == 0) {
						builder.append("<td id='loctd" + i + "'>");
						builder.append(
								"<select class='form-control showToolTip chosen-select' onchange='selectAllLocation("
										+ i + ")' name='allproduct[" + i + "].tlocation' id='tlocation" + i + "''>");
						builder.append("<option value='0'>Select Location</option>");
						for (Master master : locationlist) {
							String loc = product.getLocation();
							String loc1 = "" + master.getId();

							if (loc.equals(loc1)) {
								continue;
							}
							builder.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
						}
						builder.append("</select>");
						builder.append("</td/>");
					} else {
						builder.append("<td id='loctd" + i + "'>");
						builder.append("<select class='form-control showToolTip chosen-select' name='allproduct[" + i
								+ "].tlocation' id='tlocation" + i + "''>");
						builder.append("<option value='0'>Select Location</option>");
						for (Master master : locationlist) {
							String loc = product.getLocation();
							String loc1 = "" + master.getId();

							if (loc.equals(loc1)) {
								continue;
							}
							builder.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
						}
						builder.append("</select>");
						builder.append("</td/>");
					}

					builder.append("<td><input type='text' class='form-control' name='allproduct[" + i
							+ "].tqty' id='tqty" + i + "' onmouseout='checkNotGreterThanStock2(" + i
							+ ")'  onblur='checkNotGreterThanStock2(" + i + ")'></td>");
					// builder.append("<td class='hidden'><input type='text'
					// class='form-control' name='allproduct["+i+"].comment'
					// id='comment"+i+"' ></td>");
					// builder.append("<td><i
					// onclick='deleteCartProductTemp("+i+")' style='cursor:
					// pointer;' class='fa fa-trash-o' ></i></td/>");
					builder.append("<td><i onclick='deleteCartProductTemp2(" + i + "," + mdicinenameid
							+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
					builder.append("</tr>");
					// medicinelist.add(product);
					i++;
					j++;
				}
			}
			// session.setAttribute("medicinelist", medicinelist);
			builder.append("~");
			builder.append("" + j + "");
			// getproductdata();
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
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
			int indentcount = inventoryProductDAO.getTotalTransferIdentCount();

			int mainParentId=0;
			
			int tcount = Integer.parseInt(request.getParameter("tcount"));
			String firstlocation = "";
			int i = 0;
			String comment1 = productForm.getComment();
			for (Product product : productForm.getAllproduct()) {
				if (product == null) {
					continue;
				}
				if (i == 0) {
					firstlocation = product.getLocation();
				}
				if (!firstlocation.equals(product.getLocation())) {
					continue;
				}
				int prodid = product.getId();
				String location = product.getTlocation();
				String qty = product.getTqty();
				String comment = product.getComment();
				int result = inventoryProductDAO.transferProductTemporary(prodid, firstlocation, location, qty,
						comment,loginInfo.getLoginsessionid());
				i++;
			}
             
			//String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String todate=DateTimeUtils.getCommencingDate1(productForm.getIssue_date());
			String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];

			ArrayList<String> list = inventoryProductDAO.getToLocationList(loginInfo.getLoginsessionid());
			String userid = loginInfo.getUserId();
			for (String string : list) {
				int parentid = inventoryProductDAO.saveParentProductTransfer(firstlocation, string, todate, time,
						comment1, userid, indentcount + 1);
				mainParentId = parentid;
				ArrayList<Product> arrayList = inventoryProductDAO.getTempTransferData(string,loginInfo.getLoginsessionid());
				for (Product product2 : arrayList) {
					int prodid = product2.getId();
					String location = product2.getTlocation();
					double qty = Double.parseDouble(product2.getStock());
					Product product1 = inventoryProductDAO.getProduct("" + prodid);
					

					String catalogueid= product1.getCatalogueid(); 
					Product pmaster= inventoryProductDAO.getProductCatalogueDetails(catalogueid);
					double minorder =Double.parseDouble((pmaster.getMinorder()));
					double allstock= inventoryProductDAO.getTotalStockProduct(catalogueid);
					double totstockAfterTransfer= allstock - (qty);
					if(totstockAfterTransfer<=minorder){
						product1.setDate(todate);
			    		double maxorder=Double.parseDouble((pmaster.getMaxorder()));
			    		double orderqty=maxorder- totstockAfterTransfer; 
			    		if(orderqty<0){
			    			orderqty=0;
			    		}
						int res = procurementDAO.saveNewTempPo(product1);
			    	}
					product1.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					product1.setUserid(loginInfo.getUserId());
					int result = inventoryProductDAO.transferProductPTP(product1, location, qty, prodid, parentid,
							product2.getComment(),"1");
				}
			}
			int ans = inventoryProductDAO.truncatetemporaytable(loginInfo.getLoginsessionid());
			session.setAttribute("prodidname", "0");
			session.setAttribute("tcount", null);
			session.setAttribute("transferMainParentId", ""+mainParentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "deliverPrintDirect";
	}

	public String showreqmedicinefrtransfer() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			//ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			//ArrayList<Product> medicinelist = new ArrayList<Product>();
			StringBuilder builder = new StringBuilder();
			int i = 0;
			int j = 1;
			String parentid = (String) session.getAttribute("reqparentid");
			Product product2 = inventoryProductDAO.getParentTransferData(parentid);
			String fromlocation = product2.getFrom_location();

			if (session.getAttribute("prodidname") != null) {
				String prodid = (String) session.getAttribute("prodidname");
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
					builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct["
							+ i + "].hsnno' id='hsnno" + i + "' value='" + hsnno + "' ></td>");
					builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct["
							+ i + "].product_name' id='product_name" + i + "' value='" + product.getProduct_name()
							+ "' ></td>");
					builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct["
							+ i + "].batch_no' id='batch_no" + i + "' value='" + product.getBatch_no() + "' ></td>");
					builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct["
							+ i + "].expiry_date' id='expiry_date" + i + "' value='" + product.getExpiry_date()
							+ "' ></td>");
					builder.append("<td><input type='text' readonly='readonly' class='form-control' name='allproduct["
							+ i + "].loc' id='loc" + i + "' value='" + location + "'></td>");
					builder.append("<td><input type='text' disabled='disabled' class='form-control' name='allproduct["
							+ i + "].stock' id='stock" + i + "' value='" + product.getStock() + "' ></td>");

					/*
					 * builder.
					 * append("<select class='form-control showToolTip chosen-select' name='allproduct["
					 * +i+"].tlocation' id='tlocation"+i+"''>"); builder.
					 * append("<option value='0'>Select Location</option>"); for
					 * (Master master : locationlist) { String loc =
					 * product.getLocation(); String loc1 = ""+master.getId();
					 * 
					 * if(loc.equals(loc1)){ continue; }
					 * builder.append("<option value='"+master.getId()+"'>"+
					 * master.getName()+"</option>"); }
					 * builder.append("</select>");
					 */

					builder.append("<td><input type='text' readonly='readonly' class='form-control' name='allproduct["
							+ i + "].tlocation' id='tlocation" + i + "' value='" + fromlocation + "'></td>");
					builder.append("<td><input type='text' class='form-control' name='allproduct[" + i
							+ "].tqty' id='tqty" + i + "' ></td>");
					builder.append("<td><i onclick='deleteCartProductTemp(" + i
							+ ")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
					builder.append("</tr>");
					// medicinelist.add(product);
					i++;
					j++;
				}
			}
			// session.setAttribute("medicinelist", medicinelist);
			builder.append("~");
			builder.append("" + j + "");
			// getproductdata();
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

	public String transferrequestedproductdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			//int tcount = Integer.parseInt(request.getParameter("tcount"));
			//String firstlocation = "";
			int i = 0;
			String parentid = (String) session.getAttribute("reqparentid");
			for (Product product : productForm.getAllproduct()) {
				if (product == null) {
					continue;
				}

				int prodid = product.getId();
				String qty = product.getTqty();
				String location = product.getLocation();
				Product product1 = inventoryProductDAO.getProduct("" + prodid);
				int result = inventoryProductDAO.requestProductTemporarySave(prodid, product1.getLocation(), qty,
						parentid);
				i++;
			}
			int result = inventoryProductDAO.updateParentProductStatus(parentid);
			session.setAttribute("prodidname", "0");
			session.setAttribute("tcount", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferproductdata";
	}

	public void prepare() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ArrayList<Master> medicineTypeList = inventoryProductDAO.getMedicineTypeList();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			productForm.setLocationListPharmacy(locationlist);
			productForm.setMedicineTypeList(medicineTypeList);

			String location = (String) session.getAttribute("location");
			if (location == null) {

				location = "0";
			}

			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			ArrayList<Vendor> vendorList = inventoryVendorDAO.getAllVendorList(location);
			productForm.setVendorList(vendorList);

			ArrayList<Master> cellList = inventoryProductDAO.getcellList("32,33,34,35,36");
			productForm.setCellList(cellList);

			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			ArrayList<Product> subcategoryList = inventoryProductDAO.getAllSubCategoryList(null);
			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandList(null);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList();
			productForm.setWarehouseList(warehouseList);
			productForm.setCategoryList(categoryList);
			productForm.setSubcategoryList(subcategoryList);
			productForm.setVendorList(vendorList);
			productForm.setBrandnameList(brandnameList);
			productForm.setLocation(location);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

	}

	public String addtocart2() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			//InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			if (session.getAttribute("prodidname") != null) {
				prodid = (String) session.getAttribute("prodidname");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					count++;
				}
			}

			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				prodid = prodid + "," + s;
				count++;
			}
			session.setAttribute("prodidname", prodid);
			String data = "<a href='#' data-toggle='modal' onclick='showCartPopUp2()'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "
					+ count + " </span></a>";

			session.setAttribute("tcount", "" + count);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return null;
	}

	public String deleteCartProductTemp() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// InventoryProductDAO inventoryProductDAO= new
			// JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			String product_id = "0";
			if (session.getAttribute("prodidname") != null) {
				prodid = (String) session.getAttribute("prodidname");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if (t.equals(id)) {
						continue;
					}
					product_id = product_id + "," + t;
					count++;
				}
			}

			session.setAttribute("prodidname", product_id);

			session.setAttribute("tcount", "" + count);
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

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getsubcatagory() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String subid = request.getParameter("id");
			String val = request.getParameter("val");
			StringBuffer buffer = new StringBuffer();
			/*if (!subid.equals("2")) {
				ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);
				// buffer.append("<label>Select Sub Category</label>");
				buffer.append("<select name='productdata[" + val
						+ "].subcategory_id' class='form-control chosen' id='subcategory_id" + val + "' >");
				buffer.append("<option value='0'>Select Sub Category</option>");
				for (Product product : subcategoryList) {

					buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
				}
				buffer.append("</select>");

			} else {
				ArrayList<Master> medicineTypeList = inventoryProductDAO.getMedicineTypeList();
				// buffer.append("<label>Select Sub Category</label>");
				buffer.append("<select name='productdata[" + val
						+ "].subcategory_id' class='form-control chosen' id='subcategory_id" + val + "' >");
				buffer.append("<option value='0'>Select Sub Category</option>");
				for (Master product : medicineTypeList) {

					buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
				}
				buffer.append("</select>");
			}*/
			ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);
			// buffer.append("<label>Select Sub Category</label>");
			buffer.append("<select name='productdata[" + val
					+ "].subcategory_id' class='form-control chosen' id='subcategory_id" + val + "' >");
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

	public String addnewrow() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ArrayList<Master> genericnamelist = new ArrayList<Master>();
			ArrayList<Master> mfglist = new ArrayList<Master>();
			if(loginInfo.isAuto_generic_name()){
				genericnamelist = inventoryProductDAO.getGenericMasterList();
				mfglist = inventoryProductDAO.getMFGMasterList();
			}
			
			ArrayList<String> unitOfMeseurementList = inventoryProductDAO.getUnitOfMessurementList();
			productForm.setUnitOfMeseurementList(unitOfMeseurementList);
			
			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<input type='hidden' class='akash' value='" + index + "' class='form-control' />");
			buffer.append("<td>");
			buffer.append("<select name='productdata[" + index + "].category_id' id='category_id" + index
					+ "' class='form-control showToolTip chosen' onchange='getsubcatagory(this.value," + index + ")'>");
			buffer.append("<option value='0'>Select Category</option>");
			for (Product master : categoryList) {
				buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td id='subcatdiv" + index + "'>");
			buffer.append("<select name='productdata[" + index + "].subcategory_id' id='subcategory_id" + index
					+ "' class='form-control showToolTip chosen'>");
			buffer.append("<option value='0'>Select Subcategory</option>");
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td>");
			buffer.append("<select name='productdata[" + index + "].prodtype' id='prodtype" + index
					+ "' class='form-control showToolTip chosen'>");
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='Regular'>Regular</option>");
			buffer.append("<option value='H1'>H1</option>");
			buffer.append("<option value='Narcotics'>Narcotics</option>");
			buffer.append("<option value='High Risk Medicine'>High Risk Medicine</option>");
			buffer.append("<option value='Vaccination'>Vaccination</option>");
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td><input type='text' class='form-control' id='product_name" + index
					+ "' name='productdata[" + index
					+ "].product_name' placeholder='Product Name' onchange='chkNameExist(this)' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			if(loginInfo.isAuto_generic_name()){
				buffer.append("<td>");
				buffer.append("<select name='productdata[" + index + "].generic_name' id='generic_name" + index
						+ "' class='form-control showToolTip chosen'>");
				buffer.append("<option value=''>Generic Name</option>");
				for (Master master : genericnamelist) {
					buffer.append("<option value='" + master.getName() + "'>" + master.getName() + "</option>");
				}
				buffer.append("</select>");
				buffer.append("</td>");
			}else{
				buffer.append("<td><input type='text' class='form-control' id='generic_name" + index+ "' name='productdata[" + index+ "].generic_name' placeholder='Generic Name' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			}
			
			buffer.append("<td><input type='text' class='form-control' id='pro_code" + index
					+ "' name='productdata[" + index
					+ "].pro_code' placeholder='Product Code' onchange='checkProductCodeExistNew("+index+",this.value)' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			
			if(loginInfo.isLmh()){
				buffer.append("<td><input type='hidden' value='1' class='form-control' id='pack" + index + "' onchange='getcalsaleprice(" + index
						+ ")' name='productdata["+ index + "].pack' placeholder='Pack' style='background-color: rgba(245, 245, 245, 0.46);'>");
				
				buffer.append("<select name='productdata[" + index + "].uom' id='uom" + index
						+ "' class='form-control showToolTip chosen'>");
				buffer.append("<option value=''>Select</option>");
				for (String uom : unitOfMeseurementList) {
					buffer.append("<option value='" + uom + "'>" + uom + "</option>");
				}
				buffer.append("</select>");
				buffer.append("</td>");
			}else{
				buffer.append("<td><input type='number' class='form-control' id='pack" + index + "' onchange='getcalsaleprice(" + index
						+ ")' name='productdata["+ index + "].pack' placeholder='Pack' style='background-color: rgba(245, 245, 245, 0.46);'>");
				buffer.append("<input type='hidden' class='form-control' value='' id='uom" + index + "' name='productdata[0].uom' placeholder='UOM' style='background-color: rgba(245, 245, 245, 0.46);'>");
				buffer.append("</td>");
			}
			buffer.append("<td><input type='number' class='form-control' onchange='getcalsaleprice(" + index
					+ ")'  id='mrp" + index + "' name='productdata[" + index
					+ "].mrp' placeholder='MRP' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			buffer.append("<td><input type='number' class='form-control' id='purchase_price" + index
					+ "' name='productdata[" + index
					+ "].purchase_price' placeholder='Rate' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			buffer.append("<td><input type='number' class='form-control' id='sale_price" + index + "' name='productdata["
					+ index
					+ "].sale_price' placeholder='Sale Price' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			
			if(loginInfo.isAuto_generic_name()){
				buffer.append("<td>");
				buffer.append("<select name='productdata[" + index + "].mfg' id='mfg" + index
						+ "' class='form-control showToolTip chosen'>");
				buffer.append("<option value=''>MFG</option>");
				for (Master master : mfglist) {
					buffer.append("<option value='" + master.getName() + "'>" + master.getName() + "</option>");
				}
				buffer.append("</select>");
				buffer.append("</td>");
			}else{
				buffer.append("<td><input type='text' class='form-control' id='mfg" + index + "' name='productdata[" + index + "].mfg' placeholder='MFG' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			}
			
			

			buffer.append("<td>");
			buffer.append("<select name='productdata[" + index + "].vat' id='vat" + index
					+ "' class='form-control showToolTip chosen'>");
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='0'>0%</option>");
			buffer.append("<option value='5'>5%</option>");
			buffer.append("<option value='12'>12%</option>");
			buffer.append("<option value='18'>18%</option>");
			buffer.append("<option value='28'>28%</option>");
			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("<td><input type='number' class='form-control' id='hsnno" + index + "' name='productdata["
					+ index
					+ "].hsnno' placeholder='HSN NO' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			buffer.append("<td><input type='number' class='form-control' id='minorder" + index
					+ "' name='productdata[" + index
					+ "].minorder' placeholder='Min Order' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			buffer.append("<td><input type='number' class='form-control' id='maxorder" + index
					+ "' name='productdata[" + index
					+ "].maxorder' placeholder='Max Order' style='background-color: rgba(245, 245, 245, 0.46);'></td>");
			buffer.append("<td class='hidden'><textarea rows='2' class='form-control' id='description" + index
					+ "' placeholder='Description' name='productdata[" + index
					+ "].description' style='background-color: rgba(245, 245, 245, 0.46)'></textarea></td>");
			/*buffer.append(
					"<td><a href='#' onclick='deleteProductRow(this)'><i class='fa fa-times fa-2x text-danger' ></i></a></td>");*/
			
			buffer.append("</tr>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			connection.close();
		}

		return null;
	}

	public String saveproducts() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PrescriptionMasterDAO prescriptionMasterDAO= new JDBCPrescriptionMasterDAO(connection); 
			for (Product product : productForm.getProductdata()) {
				if (product == null) {
					continue;
				}
				String date = "";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				date = dateFormat.format(cal.getTime());
				product.setDate(date);
				String loc = (String) session.getAttribute("location");
				if (loc == null) {
					loc = "0";
				}
				product.setLocation(loc);
				if (product.getGeneric_name() == null) {
					product.setGeneric_name("GEN");
				} else if (product.getGeneric_name().equals("")) {
					product.setGeneric_name("GEN");
				}
				
				String subcat = product.getSubcategory_id();
				int res = inventoryProductDAO.addNewProduct(product);
				if(loginInfo.isKalmegha()) {
					String firstlet=product.getProduct_name().substring(0, 1);
					String vendocode=firstlet.toUpperCase()+"00"+res;
					int re=inventoryProductDAO.updateProductCode(res,vendocode);
			   }
			//	int result = inventoryProductDAO.addNewProductToVendor(loc, res);
				
				//  26-11-2019
				if(loginInfo.getGrn_to_prisc_location()!=0){
					if(loginInfo.getGrn_to_prisc_location()==DateTimeUtils.convertToInteger(product.getLocation())){
						int result=prescriptionMasterDAO.addToMedicineMaster(product,res,"0");
					}
				}
				if(loginInfo.isAyushman()) {
					//save to apm_medicine_details for ayushman
					int result=prescriptionMasterDAO.addToMedicineMaster(product,res,"0");
				}
				
//				if(product.getCategory_id().equals("2")){
//					//add to medicine master
//					int result=prescriptionMasterDAO.addToMedicineMaster(product,res,"0");
//				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferproductdata";
	}

	public String addtoreturn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// InventoryProductDAO inventoryProductDAO= new
			// JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			String newprodid = "0";
			if (session.getAttribute("returnprodid") != null) {
				prodid = (String) session.getAttribute("returnprodid");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					newprodid = newprodid + "," + t;
					count++;
				}
			}

			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				boolean flag= false;
				for (String tt : newprodid.split(",") ) {
					if (tt.equals("0")) {
						continue;
					}
					if (tt.equals(s)) {
						flag = true;
						break;
					}
				}
				if(!flag){
					prodid = prodid + "," + s;
					count++;
				}
				/*prodid = prodid + "," + s;
				count++;*/
			}
			session.setAttribute("returnprodid", prodid);
			String data = "<a href='#' data-toggle='modal' onclick='showReturnPopUp()'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "
					+ count + " </span></a>";

			session.setAttribute("returncount", "" + count);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String showreturnmedicine() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ArrayList<Master> locationlist = inventoryProductDAO.getAllLocation(null);
			//ArrayList<Product> medicinelist = new ArrayList<Product>();
			StringBuilder builder = new StringBuilder();
			int i = 0;
			int j = 1;
			if (session.getAttribute("returnprodid") != null) {
				String prodid = (String) session.getAttribute("returnprodid");
				for (String mdicinenameid : prodid.split(",")) {
					if (mdicinenameid.equals("0")) {
						continue;
					}
					int isinadjustment = inventoryProductDAO.checkProductInAdjustment(mdicinenameid);
					if(isinadjustment==1){
						continue;
					}
					isinadjustment = pharmacyDAO.checkProductInSale(mdicinenameid);
					if(isinadjustment==1){
						continue;
					}
					Product product = inventoryProductDAO.getProduct(mdicinenameid);

					String hsnno = "";
					if (product.getHsnno() != null) {
						hsnno = product.getHsnno();
					}

					String location = pharmacyDAO.getLocationName(product.getLocation());

					builder.append("<tr>");
					builder.append("<input type='hidden' class='akash' value='" + i + "' />");
					builder.append("<td>" + j + "</td>");
					builder.append("<input type='hidden' name='returnproduct[" + i+ "].product_id' id='return_product_id" + i + "' value='" + i + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].id' id='return_id" + i+ "' value='" + mdicinenameid + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].location' id='return_location"+ i + "' value='" + product.getLocation() + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].hsnno' id='return_hsnno"+ i + "' value='" + hsnno + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].product_name' id='return_product_name"+ i + "' value='" + product.getProduct_name() + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].batch_no' id='return_batch_no"+ i + "' value='" + product.getBatch_no() + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].expiry_date' id='return_expiry_date"+ i + "' value='" + product.getExpiry_date() + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].loc' id='return_loc"+ i + "' value='" + location + "'>");
					builder.append("<input type='hidden' name='returnproduct[" + i + "].stock' id='return_stock"+ i + "' value='" + product.getStock() + "'>");
					builder.append("<td>"+hsnno+"</td>");
					builder.append("<td>"+product.getProduct_name()+"</td>");
					builder.append("<td>"+product.getBatch_no()+"</td>");
					builder.append("<td>"+product.getExpiry_date()+"</td>");
					builder.append("<td>"+location+"</td>");
					builder.append("<td>"+product.getStock()+"</td>");

					builder.append("<td>");
					builder.append("<select class='form-control showToolTip chosen-select' name='returnproduct[" + i
							+ "].tlocation' id='return_tlocation" + i + "''>");
					builder.append("<option value='0'>Select Location</option>");
					for (Master master : locationlist) {
						String loc = product.getLocation();
						String loc1 = "" + master.getId();

						if (loc.equals(loc1)) {
							continue;
						}
						builder.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
					}
					builder.append("</select>");
					builder.append("</td/>");
					// builder.append("<td><input type='text'
					// class='form-control' name='returnproduct["+i+"].tqty'
					// id='tqty"+i+"' value='"+product.getStock()+"'
					// onmouseout='checkNotGreterThanStock2("+i+")'
					// onblur='checkNotGreterThanStock2("+i+")'></td>");
					builder.append("<td><input type='text' class='form-control' name='returnproduct[" + i
							+ "].tqty' id='return_tqty" + i + "' value='" + product.getStock() + "'></td>");
					// builder.append("<td><i
					// onclick='deleteCartProductTemp2("+i+","+mdicinenameid+")'
					// style='cursor: pointer;' class='fa fa-times fa-2x
					// text-danger' ></i></td/>");
					builder.append("<td><i onclick='deleteReturnProduct(this," + mdicinenameid
							+ "," + i + ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
					builder.append("</tr>");
					i++;
					j++;
				}
			}

			builder.append("~");
			builder.append("" + j + "");

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

	public String returnproductdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			//int indentcount = inventoryProductDAO.getTotalTransferIdentCount();

			//int tcount = Integer.parseInt(request.getParameter("returncount"));
			String firstlocation = "";
			int i = 0;
			String comment1 = productForm.getReturncomment();
			for (Product product : productForm.getReturnproduct()) {
				if (product == null) {
					continue;
				}
				if (i == 0) {
					firstlocation = product.getLocation();
				}
				if (!firstlocation.equals(product.getLocation())) {
					continue;
				}
				int prodid = product.getId();
				String location = product.getTlocation();
				String qty = product.getTqty();
				String comment = product.getComment();
				// Product product1 = inventoryProductDAO.getProduct(""+prodid);
				int result = inventoryProductDAO.transferProductTemporary(prodid, firstlocation, location, qty,
						comment,loginInfo.getLoginsessionid());
				i++;
				// int result =
				// inventoryProductDAO.transferProductPTP(product1,location,qty,prodid);
			}
			/*
			 * String todate =""; DateFormat dateFormat = new
			 * SimpleDateFormat("yyyy-MM-dd"); Calendar cal =
			 * Calendar.getInstance(); todate =
			 * dateFormat.format(cal.getTime()); //todate =
			 * DateTimeUtils.getCommencingDate1(todate);
			 * 
			 * String time=""; DateFormat dateFormat2 = new
			 * SimpleDateFormat("hh:mm:ss"); Calendar cal1 =
			 * Calendar.getInstance(); time =
			 * dateFormat2.format(cal1.getTime());
			 */

			String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];

			ArrayList<String> list = inventoryProductDAO.getToLocationList(loginInfo.getLoginsessionid());
			String userid = loginInfo.getUserId();
			for (String string : list) {
				int parentid = inventoryProductDAO.saveParentProductReturn(firstlocation, string, todate, time,
						comment1, userid, 0);
				ArrayList<Product> arrayList = inventoryProductDAO.getTempTransferData(string,loginInfo.getLoginsessionid());
				for (Product product2 : arrayList) {
					int prodid = product2.getId();
					String location = product2.getTlocation();
					double qty = Double.parseDouble(product2.getStock());
					Product product1 = inventoryProductDAO.getProduct("" + prodid);
					product1.setDate(todate);
					product1.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					product1.setUserid(loginInfo.getUserId());
					int result = inventoryProductDAO.transferProductPTP(product1, location, qty, prodid, parentid,
							product2.getComment(),"2");
					
					// reorder level  // 
					//min /max reorder level code by // 
					String catalogueid= product1.getCatalogueid(); 
					Product pmaster= inventoryProductDAO.getProductCatalogueDetails(catalogueid);
					double minorder =Double.parseDouble((pmaster.getMinorder()));
					double allstock= inventoryProductDAO.getTotalStockProduct(catalogueid);
					//  01/10/2019 commented because min order is in qty
					double totstockAfterTransfer= allstock -(qty);
					if(totstockAfterTransfer<=minorder){
						//add to po que list
						product1.setDate(todate);
			    		double maxorder=Double.parseDouble((pmaster.getMaxorder()));
			    		double orderqty=maxorder- totstockAfterTransfer; 
			    		if(orderqty<0){
			    			orderqty=0;
			    		}
			    		//add to po que
						int res = procurementDAO.saveNewTempPo(product1);
			    	}
				}
			}

			int ans = inventoryProductDAO.truncatetemporaytable(loginInfo.getLoginsessionid());
			session.setAttribute("returnprodid", "0");
			session.setAttribute("returncount", null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferproductdata1";
	}

	public String deletereturnproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// InventoryProductDAO inventoryProductDAO= new
			// JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			String product_id = "0";
			if (session.getAttribute("returnprodid") != null) {
				prodid = (String) session.getAttribute("returnprodid");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if (t.equals(id)) {
						continue;
					}
					product_id = product_id + "," + t;
					count++;
				}
			}
			session.setAttribute("returnprodid", product_id);
			session.setAttribute("returncount", "" + count);
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
	public String addtoissue() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// InventoryProductDAO inventoryProductDAO= new
			// JDBCInventoryProductDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			if (session.getAttribute("issueprodid") != null) {
				prodid = (String) session.getAttribute("issueprodid");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					count++;
				}
			}

			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				prodid = prodid + "," + s;
				count++;
			}
			session.setAttribute("issueprodid", prodid);
			String data = "<a href='#' data-toggle='modal' onclick='showIssuePopUp(0)'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "
					+ count + " </span></a>";

			session.setAttribute("issuecount", "" + count);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + data + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	public String addnewcathlabtemp() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			//ArrayList<Master> locationlist = inventoryProductDAO.getAllLocation(null);
			String count = request.getParameter("count");
			String pid = request.getParameter("pid");
			StringBuilder builder = new StringBuilder();
			
			int j = Integer.parseInt(count);
			
			String location1=(String)session.getAttribute("location");
			if(location1==null){
				location1="0";
			}
			
				Product product = inventoryProductDAO.getProduct(pid);
				//int totalstock = inventoryProductDAO.getProductStockByCatlogue(product.getCatalogueid(),location1);
				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}
				Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
				Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
				String categoryname = productcat.getName();
				String subcategoryname =productsubcat.getName();

				String location = pharmacyDAO.getLocationName(product.getLocation());

				builder.append("<tr>");
				builder.append("<td>" + j + "</td/>");
				builder.append("<input type='hidden' class='issueclass' value='" +pid + "' class='form-control' />");
				builder.append("<input type='hidden' class='issueprodclass' value='" + pid + "' class='form-control' />");
				builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='issuedata[" + pid + "].catalogueid' class='form-control' />");
				builder.append("<input type='hidden'  value='" + 0 + "' name='issuedata[" + pid + "].consumeid' class='form-control' />");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].product_id' id='issueproduct_id" + pid + "' value='" + pid + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].id' id='issueid" + pid + "' value='"+ pid + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].location' id='issuelocation" + pid+ "' value='" + product.getLocation() + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].hsnno' id='issuehsnno" + pid+ "' value='" + hsnno + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].product_name' id='issueproduct_name" + pid+ "' value='" + product.getProduct_name() + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].batch_no' id='issuebatch_no" + pid+ "' value='" + product.getBatch_no() + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid+ "].expiry_date' id='issueexpiry_date" + pid+ "' value='" + product.getExpiry_date() + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].loc' id='issueloc" + pid+ "' value='" + location + "'>");
				builder.append("<input type='hidden' name='issuedata[" + pid + "].stock' id='issuestock" + pid+ "' value='" + product.getStock() + "'>");
				
				builder.append("<td>" + categoryname + "</td>");
				builder.append("<td>" + subcategoryname + "</td>");
				builder.append("<td>" + product.getProduct_name()+ "</td>");
				builder.append("<td>" + master.getPro_code()+ "</td>");
				builder.append("<td>" + product.getBatch_no() + "</td>");
				builder.append("<td>" + product.getMfg() + "</td>");
				builder.append("<td>" + product.getExpiry_date() + "</td>");
				/*builder.append("<td>" + location + "</td>");*/
				builder.append("<td>" + product.getStock() + "</td>");
				builder.append("<td><input type='text' class='form-control'  name='issuedata[" +pid+ "].tqty' id='issuetqty" + pid + "' onmouseout='checkNotGreterThanStock3(" + pid+ ")'  onblur='checkNotGreterThanStock3(" + pid + ")'></td>");
				builder.append("<td><i onclick='deleteBOMKITProduct(this," + pid + "," + pid+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
				builder.append("</tr>");
			
			builder.append("~");
			builder.append("" + j + "");
			
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
	
	public String showissuemedicine() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			//ArrayList<Master> locationlist = inventoryProductDAO.getAllLocation(null);
			String isfromcathlab = request.getParameter("isfromcathlab");
			String val = request.getParameter("val");
			String isfrombimkit= request.getParameter("isfrombimkit");
			String id = request.getParameter("id");
			StringBuilder builder = new StringBuilder();
			int i = 0;
			int j = 1;
			String clientname="";
			String tempname="";
			String tempid="";
			String clientid="";
			String remark="";
			if(isfrombimkit!=null){
				if(isfrombimkit.equals("")){
					isfrombimkit="0";
				}
			}else{
				isfrombimkit="0";
			}
			
			if(isfromcathlab!=null){
				if(isfromcathlab.equals("")){
					isfromcathlab="0";
				}
			}else{
				isfromcathlab="0";
			}
			String location1=(String)session.getAttribute("location");
			if(location1==null){
				
				location1="0";
			}
			if(isfrombimkit.equals("1")){
				ArrayList<Product> arrayList = inventoryProductDAO.getCathDataChildList(id,"0");
				Product product5 = inventoryProductDAO.getCathParentData(id); 
				clientname=product5.getClientname();
				tempid=product5.getTempid();
				clientid=product5.getClientid();
				tempname = product5.getTempname();
				remark = product5.getComment();
				for (Product product1 : arrayList) {
					
					Product product = inventoryProductDAO.getProduct(product1.getProduct_id());
					Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
					Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
					Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
					String categoryname = productcat.getName();
					String subcategoryname =productsubcat.getName();
					//int totalstock = inventoryProductDAO.getProductStockByCatlogue(product.getCatalogueid(),location1);
					String hsnno = "";
					if (product.getHsnno() != null) {
						hsnno = product.getHsnno();
					}

					String location = pharmacyDAO.getLocationName(product.getLocation());

					builder.append("<tr>");
					builder.append("<td>" + j + "</td/>");
					builder.append("<input type='hidden' class='issueclass' value='" + i + "' class='form-control' />");
					builder.append("<input type='hidden' class='issueprodclass' value='" + product1.getProduct_id() + "' class='form-control' />");
					builder.append("<input type='hidden'  value='" + product1.getConsumeid() + "' name='issuedata[" + i + "].consumeid' class='form-control' />");
					builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='issuedata[" + i + "].catalogueid' class='form-control' />");
					builder.append("<input type='hidden' name='issuedata[" + i + "].product_id' id='issueproduct_id" + i + "' value='" + i + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].id' id='issueid" + i + "' value='"+ product1.getProduct_id() + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].location' id='issuelocation" + i+ "' value='" + product.getLocation() + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].hsnno' id='issuehsnno" + i+ "' value='" + hsnno + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].product_name' id='issueproduct_name" + i+ "' value='" + product.getProduct_name() + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].batch_no' id='issuebatch_no" + i+ "' value='" + product.getBatch_no() + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].expiry_date' id='issueexpiry_date" + i+ "' value='" + product.getExpiry_date() + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].loc' id='issueloc" + i+ "' value='" + location + "'>");
					builder.append("<input type='hidden' name='issuedata[" + i + "].stock' id='issuestock" + i+ "' value='" + product.getStock() + "'>");
					builder.append("<td>" + categoryname + "</td>");
					builder.append("<td>" + subcategoryname + "</td>");
					builder.append("<td>" + product.getProduct_name()+ "</td>");
					builder.append("<td>" + master.getPro_code()+ "</td>");
					builder.append("<td>" + product.getBatch_no() + "</td>");
					builder.append("<td>" + product.getMfg() + "</td>");
					builder.append("<td>" + product.getExpiry_date() + "</td>");
					/*builder.append("<td>" + location + "</td>");*/
					builder.append("<td>" + product.getStock() + "</td>");
					
					builder.append("<td><input type='text' class='form-control' value='"+product1.getQty()+"' name='issuedata[" +i+ "].tqty' id='issuetqty" + i + "'></td>");
					/*builder.append("<td><i onclick='deleteIssueProduct(" + i + "," + product1.getProduct_id()+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");*/
					builder.append("<td><i onclick='deleteIssueProduct(this," + product1.getProduct_id()+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
					builder.append("</tr>");
					i++;
					j++;
				}
			}else if(isfromcathlab.equals("1")){
				
					ArrayList<Product> arrayList = inventoryProductDAO.getchildCathData(val,location1);
					
					for (Product product1 : arrayList) {
						
						Product product = inventoryProductDAO.getProduct(product1.getProduct_id());
						
						Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
						Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
						Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
						String categoryname = productcat.getName();
						String subcategoryname =productsubcat.getName();
						
						//int totalstock = inventoryProductDAO.getProductStockByCatlogue(product.getCatalogueid(),location1);
						String hsnno = "";
						if (product.getHsnno() != null) {
							hsnno = product.getHsnno();
						}

						String location = pharmacyDAO.getLocationName(product.getLocation());
						builder.append("<tr>");
						builder.append("<td>" + j + "</td/>");
						builder.append("<input type='hidden' class='issueclass' value='" + product1.getProduct_id() + "' class='form-control' />");
						builder.append("<input type='hidden' class='issueprodclass' value='" + product1.getProduct_id() + "' class='form-control' />");
						builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='issuedata[" + product1.getProduct_id() + "].catalogueid' class='form-control' />");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].product_id' id='issueproduct_id" + product1.getProduct_id() + "' value='" + product1.getProduct_id() + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].id' id='issueid" + product1.getProduct_id() + "' value='"+ product1.getProduct_id() + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].location' id='issuelocation" + product1.getProduct_id()+ "' value='" + product.getLocation() + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].hsnno' id='issuehsnno" + product1.getProduct_id()+ "' value='" + hsnno + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].product_name' id='issueproduct_name" + product1.getProduct_id()+ "' value='" + product.getProduct_name() + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].batch_no' id='issuebatch_no" + product1.getProduct_id()+ "' value='" + product.getBatch_no() + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].expiry_date' id='issueexpiry_date" + product1.getProduct_id()+ "' value='" + product.getExpiry_date() + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].loc' id='issueloc" + product1.getProduct_id()+ "' value='" + location + "'>");
						builder.append("<input type='hidden' name='issuedata[" + product1.getProduct_id() + "].stock' id='issuestock" + product1.getProduct_id()+ "' value='" + product.getStock() + "'>");
						builder.append("<td>" + categoryname + "</td>");
						builder.append("<td>" + subcategoryname + "</td>");
						builder.append("<td>" + product.getProduct_name()+ "</td>");
						builder.append("<td>" + master.getPro_code()+ "</td>");
						builder.append("<td>" + product.getBatch_no() + "</td>");
						builder.append("<td>" + product.getMfg() + "</td>");
						builder.append("<td>" + product.getExpiry_date() + "</td>");
					/*	builder.append("<td>" + location + "</td>");*/
						builder.append("<td>" + product.getStock() + "</td>");
						builder.append("<td><input type='text' class='form-control' value='"+product1.getQty()+"'  name='issuedata[" +product1.getProduct_id()+ "].tqty' id='issuetqty" + product1.getProduct_id() + "' onmouseout='checkNotGreterThanStock3(" + product1.getProduct_id()+ ")'  onblur='checkNotGreterThanStock3(" + product1.getProduct_id() + ")'></td>");
						builder.append("<td><i onclick='deleteBOMKITProduct(this," + product1.getProduct_id() + "," + product1.getProduct_id()+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
						builder.append("</tr>");
						i++;
						j++;
					}
			}else{
				if (session.getAttribute("issueprodid") != null) {
					String prodid = (String) session.getAttribute("issueprodid");
					for (String mdicinenameid : prodid.split(",")) {
						if (mdicinenameid.equals("0")) {
							continue;
						}
						int isinadjustment = inventoryProductDAO.checkProductInAdjustment(mdicinenameid);
						if(isinadjustment==1){
							continue;
						}
						isinadjustment = pharmacyDAO.checkProductInSale(mdicinenameid);
						if(isinadjustment==1){
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
						builder.append("<input type='hidden' class='issueclass' value='" + i + "' class='form-control' />");
						builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='issuedata[" + i + "].catalogueid' class='form-control' />");
						builder.append("<input type='hidden' name='issuedata[" + i + "].product_id' id='issueproduct_id" + i + "' value='" + i + "'>");
						builder.append("<input type='hidden' name='issuedata[" + i + "].id' id='issueid" + i + "' value='"+ mdicinenameid + "'>");
						builder.append("<input type='hidden' name='issuedata[" + i + "].location' id='issuelocation" + i+ "' value='" + product.getLocation() + "'>");
						builder.append("<td>"+hsnno+"<input type='hidden' class='form-control' name='issuedata["+ i + "].hsnno' id='issuehsnno" + i + "' value='" + hsnno + "' ></td>");
						builder.append("<td>" + product.getProduct_name()+ "<input type='hidden' class='form-control' name='issuedata["+ i + "].product_name' id='issueproduct_name" + i + "' value='" + product.getProduct_name()+ "' ></td>");
						builder.append("<td>" + product.getBatch_no() + "<input type='hidden' class='form-control' name='issuedata["+ i + "].batch_no' id='issuebatch_no" + i + "' value='" + product.getBatch_no() + "' ></td>");
						builder.append("<td>" + product.getExpiry_date()+ "<input type='hidden' class='form-control' name='issuedata["+ i + "].expiry_date' id='issueexpiry_date" + i + "' value='" + product.getExpiry_date()+ "' ></td>");
						builder.append("<td>" + location + "<input type='hidden' class='form-control' name='issuedata["+ i + "].loc' id='issueloc" + i + "' value='" + location + "'></td>");
						builder.append("<td style='text-align: center;'>" + product.getStock() + "<input type='hidden' class='form-control' name='issuedata["+ i + "].stock' id='issuestock" + i + "' value='" + product.getStock() + "' ></td>");
						//builder.append("<input type='hidden' name='issuedata[" +i+ "].clientid' id='issueclientid" + i + "'  class='form-control' />");
						//builder.append("<td><input type='text' class='form-control' name='issuedata[" +i+ "].tlocation' id='issuetlocation" + i + "' onclick='getpatientpop(" + i + ")'></td>");
						builder.append("<td><input type='text' class='form-control' name='issuedata[" +i+ "].tqty' id='issuetqty" + i + "' onmouseout='checkNotGreterThanStock3(" + i+ ")'  onblur='checkNotGreterThanStock3(" + i + ")'></td>");
						builder.append("<td style='text-align: center;'><i onclick='deleteIssueProduct(this," + mdicinenameid+ ")' style='cursor: pointer;' class='fa fa-times text-danger' ></i></td/>");
						builder.append("</tr>");
						
						i++;
						j++;
					}
				}
			}
			
			
			builder.append("~");
			builder.append("" + j + "");
			
			builder.append("~");
			builder.append("" + isfrombimkit + "");
			
			builder.append("~");
			builder.append("" + clientname + "");
			
			builder.append("~");
			builder.append("" + tempid + "");
			
			builder.append("~");
			builder.append("" + clientid + "");
			
			builder.append("~");
			builder.append("<select id='cathtempid' name='cathtempid' class='form-control chosen'>");
			builder.append("<option value='"+tempid+"'>"+tempname+"</option>");
			builder.append("</select>");
			builder.append("<input type='hidden' name='maintransferidcath' id='maintransferidcath' value='"+id+"'>");
			
			builder.append("~");
			builder.append("" + remark + "");
			
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
	public String deleteissueproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("data");
			String prodid = "0";
			int count = 0;
			String product_id = "0";
			if (session.getAttribute("issueprodid") != null) {
				prodid = (String) session.getAttribute("issueprodid");
				for (String t : prodid.split(",")) {
					if (t.equals("0")) {
						continue;
					}
					if (t.equals(id)) {
						continue;
					}
					product_id = product_id + "," + t;
					count++;
				}
			}
			session.setAttribute("issueprodid", product_id);
			session.setAttribute("issuecount", "" + count);
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
	
	public String showAllpatientList() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			String val = request.getParameter("val");
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
			
			productForm.setAllPatientList(allPatientList);
			
			
			StringBuffer str = new StringBuffer();
			str.append("<table class='table table-bordered' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>Id</th> ");
			str.append("<th>Name</th> ");
			str.append("<th>Mobile</th> ");
			str.append("<th>Email</th> ");
			str.append("<th>PostCode</th> ");
			str.append("<th>Dob</th> ");
			str.append("<th>LastModified</th> ");

			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Client client1:allPatientList){
				String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName(); 	
				String color = "";
				if(!client1.getCasualtyid().equals("0")){
					color = "#f5a0b4";
				}
			str.append("<tr style='background-color:"+color+"' >");
			if(client1.getAbrivationid()==null){
				str.append("<td>"+client1.getId()+"</td>");
			}else{
				str.append("<td>"+client1.getAbrivationid()+"/"+client1.getId()+"</td>");
			}
			String firstName= client1.getFirstName();
			
			int payee=0;
			if(client1.getWhopay()!=null){
				
				if(client1.getWhopay().equals("Client")){
					 payee=0;
				} else {
					payee=1;
				}
				
			}
			
			/*str.append("<td style='cursor: pointer;'; onclick = setPatientForTransfer('"+firstName+"','"+client1.getId()+"','"+client1.getGender()+"','"+val+"')>"+name+"</td>");*/
			/*str.append("<td style='cursor: pointer;'; onclick = setPatientForTransferNew("+client1.getId()+",'"+val+"')>"+name+"</td>");*/
			str.append("<td style='cursor: pointer;'; onclick = setPatientForTransferJson("+client1.getId()+",'"+val+"')>"+name+"</td>");
			if(client1.getOldclientId()==null){
				client1.setOldclientId("");
			}
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getEmail()+"</td>");
			str.append("<td>"+client1.getPostCode()+"</td>");
			str.append("<td>"+client1.getDob()+"</td>");
			if(client1.getLastModified()==null){
				client1.setLastModified("");
			}
			str.append("<td>"+client1.getLastModified()+"</td>");

			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
		
	}
	
	public String getPatientDetails() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String val = request.getParameter("val");
			String clientid = request.getParameter("id");
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			StringBuffer buffer = new StringBuffer();
			Client client = clientDAO.getClientDetails(clientid);
			buffer.append(""+client.getFullname()+"");
			buffer.append("~");
			buffer.append(""+clientid+"");
			buffer.append("~");
			buffer.append(""+val+"");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+""); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
	}
	
	public String searchParticularPatient() throws SQLException{
 		if(!verifyLogin(request)){
			return "login";
		}
		String searchClient = request.getParameter("searchText");
		String val = request.getParameter("val");
		Connection connection = null;
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getClient(searchClient,loginInfo.getId(),"");
			
		//	clientForm.setAllPatientList(allPatientList);
        StringBuffer str = new StringBuffer();
			

			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>Id</th> ");

			str.append("<th>Name</th> ");
			str.append("<th>Mobile</th> ");
			str.append("<th>Email</th> ");
			str.append("<th>PostCode</th> ");
			str.append("<th>Dob</th> ");
			str.append("<th>LastModified</th> ");

			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Client client1:allPatientList){
				String name = client1.getTitle()+"/"+client1.getFirstName()+"/"+client1.getLastName(); 	
				String name1=client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 	
				
				String gpname=clientDAO.getGPname(client1.getGpid());
				String temo[] = gpname.split(" ");
				String doctorname = "";
				for(int i=0;i<temo.length;i++){
					doctorname += temo[i] + "/";
				}
				
//				System.out.println(doctorname);
				
			str.append("<tr>");
			if(client1.getAbrivationid()==null){
				str.append("<td>"+client1.getId()+"</td>");
			}else{
				str.append("<td>"+client1.getAbrivationid()+"/"+client1.getId()+"</td>");
			}

			//str.append("<td style='cursor: pointer;'; onclick = setPatientForMrd('"+name+"','"+client1.getId()+"','"+client1.getGender()+"','"+client1.getTown()+"')>"+name1+"</td>");
			//str.append("<td style='cursor: pointer;'; onclick = setPatientForTransfer('"+client1.getFirstName()+"','"+client1.getId()+"','"+client1.getGender()+"','"+val+"')>"+name+"</td>");
			/*str.append("<td style='cursor: pointer;'; onclick = setPatientForTransferNew("+client1.getId()+",'"+val+"')>"+name1+"</td>");*/
			str.append("<td style='cursor: pointer;'; onclick = setPatientForTransferJson("+client1.getId()+",'"+val+"')>"+name1+"</td>");
			if(client1.getOldclientId()==null){
				client1.setOldclientId("");
			}
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getEmail()+"</td>");
			str.append("<td>"+client1.getPostCode()+"</td>");
			str.append("<td>"+client1.getDob()+"</td>");
			if(client1.getLastModified()==null){
				client1.setLastModified("");
			}
			str.append("<td>"+client1.getLastModified()+"</td>");

			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	public String transferissueproductdata() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String firstlocation = "";
			int i = 0;
			String comment1 = productForm.getComment();
			String issuepatientid = productForm.getIssuepatientid();
			String issueproceid = productForm.getIssueproceid();
			String hisuserfilter = productForm.getHisuserfilter();
			String issueuserid = productForm.getIssueuserid();
			String hisdepartmentfilter = productForm.getHisdepartmentfilter();
			String isfromcathlab = productForm.getIsfromcathlab();
			String cathtempid=productForm.getCathtempid();
			String isfrombimkit = request.getParameter("isfrombimkit");
			if(issuepatientid!=null){
				if(issuepatientid.equals("")){
					issuepatientid="0";
				}
			}else{
				issuepatientid="0";
			}
			
			if(isfromcathlab!=null){
				if(isfromcathlab.equals("")){
					isfromcathlab="0";
				}
			}else{
				isfromcathlab="0";
			}
			if(issueuserid!=null){
				if(issueuserid.equals("")){
					issueuserid="0";
				}
			}else{
				issueuserid="0";
			}
			
			if(issueproceid!=null){
				if(issueproceid.equals("")){
					issueproceid="0";
				}
			}else{
				issueproceid="0";
			}
			
			if(isfrombimkit!=null){
				if(isfrombimkit.equals("")){
					isfrombimkit="0";
				}
			}else{
				isfrombimkit="0";
			}
			if(isfrombimkit.equals("1")){
				//int parentid = indentDAO.getIssueToProductSrNo();
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int parentid = Integer.parseInt(request.getParameter("maintransferidcath"));
				int res55 = inventoryProductDAO.updateCathStatus(parentid,"1");
				int res555 = inventoryProductDAO.updateCathComment(parentid,comment1);
				for (Product product : productForm.getIssuedata()) {
					if (product == null) {
						continue;
					}
					/*if (i == 0) {
						firstlocation = product.getLocation();
					}
					if (!firstlocation.equals(product.getLocation())) {
						continue;
					}*/
					String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int prodid = product.getId();
					String location = product.getTlocation();
					String qty = product.getTqty();
					String product_name = product.getProduct_name();
					double stock = Double.parseDouble(product.getStock());
					String clientid = product.getClientid();
					String tqty = product.getTqty();
					String catalogueid = product.getCatalogueid();
					product.setClientid(issuepatientid);
					clientid = product.getClientid();
					product.setIssueproceid(issueproceid);
					product.setIssueuserid(issueuserid);
					product.setHisuserfilter(hisuserfilter);
					product.setParentid(String.valueOf(parentid));
					product.setHisdepartmentfilter(hisdepartmentfilter);
					String consumeid = product.getConsumeid();
					if(isfromcathlab.equals("1")){
						String procedureid = inventoryProductDAO.getCathlabTemplateProcid(cathtempid);
						product.setIssueproceid(procedureid);
						product.setHisdepartmentfilter("0");
						product.setHisdepartmentfilter("0");
						product.setHisuserfilter("0");
					}
					product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					product.setUserid(loginInfo.getUserId());
					product.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
					if(consumeid.equals("0")){
						int result = indentDAO.transferIssueProduct(product,comment1,loginInfo.getUserId(),todate,"1");
						product.setConsumeid(String.valueOf(result));
						int result1 = indentDAO.transferIssueProductUpdate(product,comment1,loginInfo.getUserId(),todate,String.valueOf(result));
					}else{
						int result = indentDAO.transferIssueProductUpdate(product,comment1,loginInfo.getUserId(),todate,consumeid);
					}
					
					
					if(catalogueid!=null){
						if(!catalogueid.equals("")){
							Product product2 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
							String medlocation = product.getLocation();
							boolean flag = indentDAO.checkLocationInWarehouseid(medlocation);
							if(flag){
								if(product2.getMinorder()!=null){
									if(product2.getMinorder().equals("")){
										product2.setMinorder("0");
									}
								}else{
									product2.setMinorder("0");
								}
								int minorder = Integer.parseInt(product2.getMinorder());
								int stock1 = indentDAO.getStockByProdId(prodid);
								
								if(stock1<minorder){
									int reqqty= minorder-stock1;
									int res = inventoryProductDAO.addTempPoRequest(""+prodid, "0", reqqty, product_name, todate, medlocation, 0, catalogueid);
								}
							}
						}
					}
					i++;
				}
			}else if(isfromcathlab.equals("1")){
				//int parentid = indentDAO.getIssueToProductSrNo();
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int parentid = indentDAO.savePatientTranferlog(isfromcathlab,loginInfo.getUserId(),datetime,cathtempid,comment1); 
				for (Product product : productForm.getIssuedata()) {
					if (product == null) {
						continue;
					}
					
					String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int prodid = product.getId();
					String location = product.getTlocation();
					String qty = product.getTqty();
					String product_name = product.getProduct_name();
					double stock = Double.parseDouble(product.getStock());
					String clientid = product.getClientid();
					String tqty = product.getTqty();
					String catalogueid = product.getCatalogueid();
					product.setClientid(issuepatientid);
					clientid = product.getClientid();
					product.setIssueproceid(issueproceid);
					product.setIssueuserid(issueuserid);
					product.setHisuserfilter(hisuserfilter);
					product.setParentid(String.valueOf(parentid));
					product.setHisdepartmentfilter(hisdepartmentfilter);
					
					if(isfromcathlab.equals("1")){
						String procedureid = inventoryProductDAO.getCathlabTemplateProcid(cathtempid);
						product.setIssueproceid(procedureid);
						product.setHisdepartmentfilter("0");
						product.setHisdepartmentfilter("0");
						product.setHisuserfilter("0");
					}
					product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					product.setUserid(loginInfo.getUserId());
					product.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
					int result = indentDAO.transferIssueProduct(product,comment1,loginInfo.getUserId(),todate,isfromcathlab);
					if(isfromcathlab.equals("0")){
						if(catalogueid!=null){
							if(!catalogueid.equals("")){
								Product product2 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
								String medlocation = product.getLocation();
								boolean flag = indentDAO.checkLocationInWarehouseid(medlocation);
								if(flag){
									if(product2.getMinorder()!=null){
										if(product2.getMinorder().equals("")){
											product2.setMinorder("0");
										}
									}else{
										product2.setMinorder("0");
									}
									int minorder = Integer.parseInt(product2.getMinorder());
									int stock1 = indentDAO.getStockByProdId(prodid);
									
									if(stock1<minorder){
										int reqqty= minorder-stock1;
										int res = inventoryProductDAO.addTempPoRequest(""+prodid, "0", reqqty, product_name, todate, medlocation, 0, catalogueid);
									}
								}
							}
						}
					}
					
					i++;
				}
			}else{
			
			//int parentid = indentDAO.getIssueToProductSrNo();
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int parentid = indentDAO.savePatientTranferlog(isfromcathlab,loginInfo.getUserId(),datetime,cathtempid,comment1); 
			for (Product product : productForm.getIssuedata()) {
				if (product == null) {
					continue;
				}
				if (i == 0) {
					firstlocation = product.getLocation();
				}
				if (!firstlocation.equals(product.getLocation())) {
					continue;
				}
				//String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String tm=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				String todate=DateTimeUtils.getCommencingDate1(productForm.getIssue_date())+" "+tm;
				int prodid = product.getId();
				String location = product.getTlocation();
				String qty = product.getTqty();
				String product_name = product.getProduct_name();
				double stock = Double.parseDouble(product.getStock());
				String clientid = product.getClientid();
				String tqty = product.getTqty();
				String catalogueid = product.getCatalogueid();
				product.setClientid(issuepatientid);
				clientid = product.getClientid();
				product.setIssueproceid(issueproceid);
				product.setIssueuserid(issueuserid);
				product.setHisuserfilter(hisuserfilter);
				product.setParentid(String.valueOf(parentid));
				product.setHisdepartmentfilter(hisdepartmentfilter);
				
				if(isfromcathlab.equals("1")){
					String procedureid = inventoryProductDAO.getCathlabTemplateProcid(cathtempid);
					product.setIssueproceid(procedureid);
					product.setHisdepartmentfilter("0");
					product.setHisdepartmentfilter("0");
					product.setHisuserfilter("0");
				}
				product.setDateTime(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
				product.setUserid(loginInfo.getUserId());
				product.setDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
					
				int result = indentDAO.transferIssueProduct(product,comment1,loginInfo.getUserId(),todate,isfromcathlab);
				if(isfromcathlab.equals("0")){
					if(catalogueid!=null){
						if(!catalogueid.equals("")){
							Product product2 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
							String medlocation = product.getLocation();
							boolean flag = indentDAO.checkLocationInWarehouseid(medlocation);
							if(flag){
								int minorder = Integer.parseInt(product2.getMinorder());
								int stock1 = indentDAO.getStockByProdId(prodid);
								
								if(stock1<minorder){
									int reqqty= minorder-stock1;
									int res = inventoryProductDAO.addTempPoRequest(""+prodid, "0", reqqty, product_name, todate, medlocation, 0, catalogueid);
								}
							}
						}
					}
				}
				i++;
			}
			String addchargetopatientstatus = request.getParameter("addchargetopatientstatus");
			
			if(!issuepatientid.equals("0")){
				if(DateTimeUtils.numberCheck(addchargetopatientstatus).equals("1")){
					
				
				//add charge process
				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				int result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
				
				String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String temp[] = cdate.split(" ");
				String curdate = temp[0];
				ArrayList<Product> childlist = inventoryProductDAO.getCathDataChildList(""+parentid,"0");
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(issuepatientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				for(Product product : childlist){
					String payBuy = "0";
					if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
						payBuy = "1";
					}
				
					CompleteAppointment completeAppointment = new CompleteAppointment();
				
					completeAppointment.setUser(clientname);
					completeAppointment.setApmtType("0");
					completeAppointment.setManualcharge(product.getProduct_name());
					//String saleprice = ""+Double.parseDouble(DateTimeUtils.numberCheck(product.getSale_price())) * Integer.parseInt(product.getQty());
					/*String saleprice = ""+Double.parseDouble(DateTimeUtils.numberCheck(product.getSale_price()));*/
					String saleprice =DateTimeUtils.numberCheck(product.getSale_price());
					completeAppointment.setCharges(saleprice);
					completeAppointment.setClientId(issuepatientid);
				
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String date = dateFormat.format(cal.getTime());
					completeAppointment.setCommencing(date);
					completeAppointment.setPayBuy(payBuy);
					completeAppointment.setMarkAppointment("1");
					completeAppointment.setQuantity((product.getQty()));
					completeAppointment.setMasterchargetype("INVENTORY CHARGES");
					completeAppointment.setProdid(0);
					completeAppointment.setAppointmentid("0");
					completeAppointment.setPractitionerId("0");
					completeAppointment.setPractitionerName("");
					completeAppointment.setGinvstid("0");
					result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
				}
				AccountLogDAO accountLogDAO = new JDBCAccountLogDAO(connection);
				InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
				String standard_chargeid="0";
				String ipdid="0";
				String clientid = issuepatientid;
				String practitionerid = "0";
				String clientName = clientname;
				String practitionerName = "";
				String appointmentid = "0";
				String action = "Charge";
				String location = "0";
				String ipd = "0";
				String gpriscid = "0";
				String date= null;
				
				if(date==null){
					date= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				} 
				String ginvstid = "0";
				String invreqid="";
				String wardid="0";
				
				
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setClientId(clientid);
				completeAppointment.setPractitionerId(practitionerid);
				completeAppointment.setUser(clientName);
				completeAppointment.setInvoiceDate(date);
				
				completeAppointment.setChargeType(action);
				completeAppointment.setAppointmentid(appointmentid);
				completeAppointment.setLocation(location);
				completeAppointment.setPractitionerName(practitionerName);
				
				completeAppointment.setIpd(ipd);
				completeAppointment.setGpriscid(gpriscid);
				completeAppointment.setGinvstid(ginvstid);
				completeAppointment.setAdditionalcharge_id(standard_chargeid);
				completeAppointment.setWardid(wardid);
				int pkgid =0;
				completeAppointment.setPkgid(pkgid);
			    completeAppointment.setIpdid(Integer.parseInt(ipdid));
				
			    ArrayList<Accounts> payByList = completeAptmDAO.getPayByList(appointmentid,loginInfo.getId());
				ArrayList<CompleteAppointment>assesmentList = completeAptmDAO.getCompleteApmtList(appointmentid,loginInfo.getId());
				
				int chkPolicyExcess = 0;
				for(CompleteAppointment c1 : assesmentList){
					if(c1.getApmtType().equalsIgnoreCase(Constants.POLICYEXCESS)){
						chkPolicyExcess = 1;
						completeAppointment.setPolicyExcess(Integer.toString(chkPolicyExcess));
					}
				}
				int selfInvoice = 0;
				int invoice = 0;
				int tpInvoice = 0;
				int selfInvoice1 = 0;
				int invoice1 = 0;
				int tpInvoice1 = 0;
				
				for(Accounts accounts : payByList){
					if(accounts.getPayBy()==0){
						completeAppointment.setPayBuy("0");
						if(chkPolicyExcess==1){
							completeAppointment.setPolicyExcess("1");
						}else{
							completeAppointment.setPolicyExcess("0");
						}
						selfInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
						invoice = selfInvoice;
						invoice1 = selfInvoice1;
					}else{
						completeAppointment.setPayBuy("1");
						completeAppointment.setPolicyExcess("0");
						tpInvoice = completeAptmDAO.saveAmpmInvoice(completeAppointment,loginInfo.getId(),loginInfo.getUserId());
						invoice = tpInvoice;
						invoice1 = tpInvoice1;
					}
					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					int z = 1;
					for(CompleteAppointment appointment : assesmentList){
						if(accounts.getPayBy() == Integer.parseInt(appointment.getPayBuy())){
							appointment.setInvestigation_request_id(invreqid);
					    	result = completeAptmDAO.saveInvoiceAssesment(appointment,invoice);
					    	if(ginvstid!=null){
								if(ginvstid.equals("")||ginvstid.equals("0")){
									
								}else{
									int realInvestigationId=investigationDAO.getinvestigationIdByApmtName(appointment.getApmtType(), invreqid,pkgid);
									investigationDAO.saveChargesToInvestigation(appointment.getCharges(), ""+realInvestigationId, String.valueOf(result),invreqid);
								}
							}
						}
					}
					//logdata
					String status = "Created";
					selfInvoice1 = accountLogDAO.saveAmpmInvoice(completeAppointment,invoice,status,DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
					invoice1 = selfInvoice1;
					
				}
				result = completeAptmDAO.deleteComplteApmt(loginInfo.getId());
			}
			}
			}
			session.setAttribute("issueprodid", "0");
			session.setAttribute("issuecount", null);
			if(isfromcathlab.equals("1")){
				return "consumptioreport";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "transferproductdata1";
	}
	
	
	public String catnameexist() throws Exception{

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			String name=request.getParameter("name");
			
			String location=(String)session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			boolean flag= inventoryProductDAO.checkCatalogueExist(name,location);
			String res="0";
			if(flag) {
				 res="1";
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(res); 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return null;
	}
	
	public String catnameexistnew() throws Exception{

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			String name=request.getParameter("name");
			
			String location= request.getParameter("warehouse");
			boolean flag= inventoryProductDAO.checkCatalogueExist(name,location);
			String res="0";
			if(flag) {
				 res="1";
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(res); 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return null;
	}
	
public String getpatientlistfrissue() throws Exception {
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		DischargeOutcomeDAO dischargeOutcomeDAO = new JDBCDischargeOutcomeDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String val = jsonObject.getString("val");
		String clientid = jsonObject.getString("id");
		
		StringBuffer stringBuffer = new StringBuffer();
		Client client = clientDAO.getClientDetails(clientid);
		String procedure = clientDAO.getProceFromClientId(clientid); 
		ArrayList<Master> procdurelist = dischargeOutcomeDAO.getNewChargeTypeListProc();
		
		/*stringBuffer.append("<label>Select Procedure</label>");*/
		stringBuffer.append("<select id='issueproceid' name='issueproceid' class='form-control chosen'>");
		stringBuffer.append("<option value='0'>Select Procedure</option>");
		for (Master master : procdurelist) {
			if(procedure.equals(master.getName())){
				stringBuffer.append("<option value='"+master.getId()+"' selected='selected'>"+master.getName()+"</option>");
			}else{
				stringBuffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
	    }
		stringBuffer.append("</select>");
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("pname", client.getFullname());
		jsonobj.put("clientid", clientid);
		jsonobj.put("val1", val);
		jsonobj.put("procedurelist", stringBuffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
//  07 Jun 2018
public String getuserlistfrissue() throws Exception {
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String val = jsonObject.getString("val");
		
		StringBuffer stringBuffer = new StringBuffer();
		
		//User list
		ArrayList<UserProfile> arrayList = userProfileDAO.getAllUserList();
		stringBuffer.append("<select id='issueuserid' name='issueuserid' class='form-control chosen'>");
		stringBuffer.append("<option value='0'>Select User</option>");
		for (UserProfile master : arrayList) {
			stringBuffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", val);
		jsonobj.put("userlist", stringBuffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

//  07 Jun 2018
public String checkproductcodeexist() throws Exception {
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
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
	
		String val = jsonObject.getString("val");
		
		StringBuffer stringBuffer = new StringBuffer();
		
		int res = inventoryProductDAO.checkProductCodeExist(val);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", res);
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String addtocathtemp() throws Exception {

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		// InventoryProductDAO inventoryProductDAO= new
		// JDBCInventoryProductDAO(connection);
		String id = request.getParameter("data");
		String prodid = "0";
		int count = 0;
		if (session.getAttribute("cathtempprodid") != null) {
			prodid = (String) session.getAttribute("cathtempprodid");
			for (String t : prodid.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				count++;
			}
		}

		for (String s : id.split(",")) {
			if (s.equals("0")) {
				continue;
			}
			prodid = prodid + "," + s;
			count++;
		}
		session.setAttribute("cathtempprodid", prodid);
		String data = "<a href='#' data-toggle='modal' onclick='showCathTempPopUp()'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Create Bom Kit with :  "
				+ count + " </span></a>";

		session.setAttribute("cathtempcount", "" + count);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + data + "");
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}

public String showcathtempmedicine() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		//ArrayList<Master> locationlist = inventoryProductDAO.getAllLocation(null);
		StringBuilder builder = new StringBuilder();
		int i = 0;
		int j = 1;
		if (session.getAttribute("cathtempprodid") != null) {
			String prodid = (String) session.getAttribute("cathtempprodid");
			for (String mdicinenameid : prodid.split(",")) {
				if (mdicinenameid.equals("0")) {
					continue;
				}
				Product product = inventoryProductDAO.getProduct(mdicinenameid);
				Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				String location = pharmacyDAO.getLocationName(product.getLocation());
				Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
				Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
				String categoryname = productcat.getName();
				String subcategoryname =productsubcat.getName();
				
				builder.append("<tr>");
				builder.append("<td>" + j + "</td/>");
				builder.append("<input type='hidden' class='cathtempclass' value='" + i + "' class='form-control' />");
				builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='cathtemplatedata[" + i + "].catalogueid' class='form-control' />");
				builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].product_id' id='cathtempproduct_id" + i + "' value='" + i + "'>");
				builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].id' id='issueid" + i + "' value='"+ mdicinenameid + "'>");
				builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].location' id='cathtemploc" + i+ "' value='" + product.getLocation() + "'>");
				builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].location'  value='" + location + "'>");
				builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].loc' id='cathtemploc" + i+ "' value='" + product.getProduct_name() + "'>");
				/*builder.append("<td><input type='text' readonly='readonly' class='form-control' name='cathtemplatedata["+ i + "].hsnno' id='cathtemphsnno" + i + "' value='" + hsnno + "' ></td>");*/
				builder.append("<td>" + categoryname + "</td>");
				builder.append("<td>" + subcategoryname + "</td>");
				builder.append("<td>" + product.getProduct_name()+ "</td>");
				builder.append("<td>" + master.getPro_code()+ "</td>");
				builder.append("<td>" + product.getBatch_no() + "</td>");
				builder.append("<td>" + product.getMfg() + "</td>");
				builder.append("<td>" + product.getExpiry_date() + "</td>");
				builder.append("<td>" + location + "</td>");
				builder.append("<td>" + product.getStock() + "</td>");
				builder.append("<td><input type='text' class='form-control' name='cathtemplatedata[" +i+ "].tqty' id='cathtemptqty" + i + "'></td>");
				builder.append("<td><i onclick='deleteCathTempProduct(" + i + "," + mdicinenameid+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
				builder.append("</tr>");
				i++;
				j++;
			}
		}
		builder.append("~");
		builder.append("" + j + "");
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

public String deletecathtempproduct() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String id = request.getParameter("data");
		String prodid = "0";
		int count = 0;
		String product_id = "0";
		if (session.getAttribute("cathtempprodid") != null) {
			prodid = (String) session.getAttribute("cathtempprodid");
			for (String t : prodid.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				if (t.equals(id)) {
					continue;
				}
				product_id = product_id + "," + t;
				count++;
			}
		}
		session.setAttribute("cathtempprodid", product_id);
		session.setAttribute("cathtempcount", "" + count);
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

public String savecathlabtemplate() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		String firstlocation = "";
		int i = 0;
		String comment1 = productForm.getComment();
		String cathtempprocedure = productForm.getCathtempprocedure();
		String cathtemplatename = productForm.getCathtemplatename();
		String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		int parentid= indentDAO.saveParentCathlabTemplate(cathtempprocedure,cathtemplatename,loginInfo.getUserId(),datetime,comment1);
		
		for (Product product : productForm.getCathtemplatedata()) {
			if (product == null) {
				continue;
			}
			if (i == 0) {
				firstlocation = product.getLocation();
			}
			if (!firstlocation.equals(product.getLocation())) {
				continue;
			}
			//String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int prodid = product.getId();
			String product_name = product.getProduct_name();
			String tqty = product.getTqty();
			String catalogueid = product.getCatalogueid();
			int result = indentDAO.saveChildCathlabTemplate(prodid,product_name,tqty,catalogueid,parentid);
			i++;
		}
		session.setAttribute("cathtempprodid", "0");
		session.setAttribute("cathtempcount", null);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return "transferproductdata2";
}

//cathlabtemplate

public String cathlabtemplate() throws Exception{
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		StringBuffer str = new StringBuffer();
		
		ArrayList<Product> parentCathtemplist = indentDAO.getCathlabTemplateList("0");
		
		str.append("<select onchange='showTemplateoteq(this.value)' name='templatename' id='eottemplatename' class='form-control chosen-select'>");
		str.append("<option value='0'>Select Template</option>");
		for(Product priscription : parentCathtemplist){
			str.append("<option value='"+priscription.getId()+"'>"+priscription.getName()+"</option>");
		}
		str.append("</select>");
		
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(str.toString());
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

//  07 Jun 2018
public String getdepartmentfrissue() throws Exception {
	Connection connection = null;
	try {
		if (!verifyLogin(request)) {
			return "login";
		}
		connection = Connection_provider.getconnection();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String val = jsonObject.getString("val");
		String loctioid = jsonObject.getString("loctioid");
		loctioid = (String) session.getAttribute("location");
		StringBuffer stringBuffer = new StringBuffer();
		
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		//ArrayList<Master> issuedeptlist = masterDAO.getAllLocation(null);
		
		//ArrayList<Master> issuedeptlist = masterDAO.getAllLocationNew(loctioid);
		
		ArrayList<Master> issuedeptlist = masterDAO.getAllLocationNew(null);
		
		stringBuffer.append("<select id='hisdepartmentfilter' name='hisdepartmentfilter' class='form-control chosen'>");
		stringBuffer.append("<option value='0'>Select Department</option>");
		for (Master master : issuedeptlist) {
			stringBuffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
		}
		stringBuffer.append("</select>");
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("val1", val);
		jsonobj.put("userlist", stringBuffer.toString());
		
		String response1 = jsonobj.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(response1);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String getcathtemplatedata() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		DischargeOutcomeDAO dischargeOutcomeDAO = new JDBCDischargeOutcomeDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String id = jsonObject.getString("id");
		int i = 0;
		int j = 1;
		StringBuilder builder = new StringBuilder();
		ArrayList<Product> childtemplatlist = inventoryProductDAO.getChildCathTempData(id);
		
		for (Product product1 : childtemplatlist) {
			
			Product product = inventoryProductDAO.getProduct(product1.getProduct_id());
			Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
			String location = pharmacyDAO.getLocationName(product.getLocation());
			Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
			Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
			String categoryname = productcat.getName();
			String subcategoryname =productsubcat.getName();
			
			builder.append("<tr>");
			builder.append("<td>" + j + "</td/>");
			builder.append("<input type='hidden' class='cathtempclass' value='" + i + "' class='form-control' />");
			builder.append("<input type='hidden' class='cathtempproclass' value='" + product1.getProduct_id() + "' class='form-control' />");
			builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='cathtemplatedata[" + i + "].catalogueid' class='form-control' />");
			builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].product_id' id='cathtempproduct_id" + i + "' value='" + i + "'>");
			builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].productid' id='issueid" + i + "' value='"+ product1.getProduct_id() + "'>");
			builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].location' id='cathtemploc" + i+ "' value='" + product.getLocation() + "'>");
			builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].loc'  value='" + location + "'>");
			builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].product_name'  value='" + product.getProduct_name() + "'>");
			builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].childid' id='cathtempchildid" + i+ "' value='" + product1.getId() + "'>");
			builder.append("<td>" + categoryname + "</td>");
			builder.append("<td>" + subcategoryname + "</td>");
			builder.append("<td>" + product.getProduct_name()+ "</td>");
			builder.append("<td>" + master.getPro_code()+ "</td>");
			builder.append("<td>" + product.getBatch_no() + "</td>");
			builder.append("<td>" + master.getMfg() + "</td>");
			builder.append("<td>" + product.getExpiry_date() + "</td>");
			builder.append("<td>" + location + "</td>");
			builder.append("<td>" + product.getStock() + "</td>");
			builder.append("<td><input type='text' class='form-control' name='cathtemplatedata[" +i+ "].tqty' value='"+product1.getQty()+"' id='cathtemptqty" + i + "'></td>");
			builder.append("<td><i onclick='deleteBomKitTemplate(this," + product1.getId() + "," + product1.getProduct_id()+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
			builder.append("</tr>");
			i++;
			j++;
		}
		
		ArrayList<Master> procdurelist = dischargeOutcomeDAO.getNewChargeTypeListProc();
		
		//String procedure = inventoryProductDAO.getCathlabTemplateProcid(id);
		Product product = inventoryProductDAO.getCathTemplateData(id);
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append("<select id='cathtempprocedure' name='cathtempprocedure' class='form-control chosen'>");
		stringBuffer.append("<option value='0'>Select Procedure</option>");
		for (Master master : procdurelist) {
			if(product.getProcedureid().equals(""+master.getId())){
				stringBuffer.append("<option value='"+master.getId()+"' selected='selected'>"+master.getName()+"</option>");
			}else{
				stringBuffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
	    }
		stringBuffer.append("</select>");	
		
		
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("count", j);
		jsonobj.put("list", builder.toString());
		jsonobj.put("procedurelist", stringBuffer.toString());
		jsonobj.put("templatename", product.getTempname());
		jsonobj.put("remark", product.getRemark());
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

public String addcathtemplatedata() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		DischargeOutcomeDAO dischargeOutcomeDAO = new JDBCDischargeOutcomeDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject jsonObject = new JSONObject(data);
	
		String count = jsonObject.getString("count");
		String pid = jsonObject.getString("pid");
		int i = 0;
		int j = 1;
		
		if(count!=null){
			if(!count.equals("")){
				j = Integer.parseInt(count);
			}
		}
		i=j;
		StringBuffer builder = new StringBuffer(); 
		Product product = inventoryProductDAO.getProduct(pid);
		Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
		String location = pharmacyDAO.getLocationName(product.getLocation());
		Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
		Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
		String categoryname = productcat.getName();
		String subcategoryname =productsubcat.getName();
		String zerovalue="0";
		builder.append("<tr>");
		builder.append("<td>" + j + "</td/>");
		builder.append("<input type='hidden' class='cathtempclass' value='" + i + "' class='form-control' />");
		builder.append("<input type='hidden' class='cathtempproclass' value='" + pid + "' class='form-control' />");
		builder.append("<input type='hidden'  value='" + product.getCatalogueid() + "' name='cathtemplatedata[" + i + "].catalogueid' class='form-control' />");
		builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].product_id' id='cathtempproduct_id" + i + "' value='" + i + "'>");
		builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].productid' id='issueid" + i + "' value='"+ pid + "'>");
		builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].location' id='cathtemploc" + i+ "' value='" + product.getLocation() + "'>");
		builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].loc'  value='" + location + "'>");
		builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].product_name'  value='" + product.getProduct_name() + "'>");
		builder.append("<input type='hidden' name='cathtemplatedata[" + i + "].childid' id='cathtempchildid" + i+ "' value='"+zerovalue+"'>");
		builder.append("<td>" + categoryname + "</td>");
		builder.append("<td>" + subcategoryname + "</td>");
		builder.append("<td>" + product.getProduct_name()+ "</td>");
		builder.append("<td>" + master.getPro_code()+ "</td>");
		builder.append("<td>" + product.getBatch_no() + "</td>");
		builder.append("<td>" + master.getMfg() + "</td>");
		builder.append("<td>" + product.getExpiry_date() + "</td>");
		builder.append("<td>" + location + "</td>");
		builder.append("<td>" + product.getStock() + "</td>");
		builder.append("<td><input type='text' class='form-control' name='cathtemplatedata[" +i+ "].tqty'  id='cathtemptqty" + i + "'></td>");
		builder.append("<td><i onclick='deleteBomKitTemplate(this, "+zerovalue+" ," + pid+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
		builder.append("</tr>");
		
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("count2", ""+j);
		jsonobj.put("list5", builder.toString());
		
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

public String updatecathlabtemplate() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		IndentDAO indentDAO = new JDBCIndentDAO(connection);
		/*String firstlocation = "";
		int i = 0;
		String comment1 = productForm.getComment();
		*/String cathtempprocedure = productForm.getCathtempprocedure();
		String cathtemplatename = productForm.getCathtemplatename();
		String cathtempparentid = productForm.getCathtempparentid();
		String cathtempdeleteids = productForm.getCathtempdeleteids();
		
		int parentid = Integer.parseInt(cathtempparentid);
		int resparentid = indentDAO.updateParentCathlabTemplate(cathtempprocedure,cathtemplatename,parentid);
		
		for (Product product : productForm.getCathtemplatedata()) {
			if (product == null) {
				continue;
			}
			int prodid = Integer.parseInt(product.getProductid());
			String product_name = product.getProduct_name();
			String tqty = product.getTqty();
			String catalogueid = product.getCatalogueid();
			String childid = product.getChildid();
			if(Integer.parseInt(childid)>0){
				int result = indentDAO.updateChildCathlabTemplate(prodid,product_name,tqty,catalogueid,Integer.parseInt(childid));
			}else{
				int result = indentDAO.saveChildCathlabTemplate(prodid,product_name,tqty,catalogueid,parentid);
			}
			
			/*i++;*/
		}
		if(cathtempdeleteids!=null){
			for (String t : cathtempdeleteids.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				int n = indentDAO.deleteChildCathTempData(t);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return "bomkittemplate";
}


public String setchargesofbomkit(){
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String ipdclientid = request.getParameter("ipdclientid");
		String bomkitid = request.getParameter("bomkitid");
		String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		String temp[] = cdate.split(" ");
		String curdate = temp[0];
		ArrayList<Product> childlist = inventoryProductDAO.getCathDataChildList(bomkitid,"1");
		for(Product product : childlist){
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(ipdclientid);
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
		
			String payBuy = "0";
			if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				payBuy = "1";
			}
		
			CompleteAppointment completeAppointment = new CompleteAppointment();
		
			completeAppointment.setUser(clientname);
			completeAppointment.setApmtType("0");
			completeAppointment.setManualcharge(product.getProduct_name());
			completeAppointment.setCharges(product.getMrp());
			completeAppointment.setClientId(ipdclientid);
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			completeAppointment.setCommencing(date);
			completeAppointment.setPayBuy(payBuy);
			completeAppointment.setMarkAppointment("1");
			completeAppointment.setQuantity((product.getQty()));
			completeAppointment.setMasterchargetype("CATHLAB CHARGES");
			completeAppointment.setProdid(0);
			completeAppointment.setAppointmentid("0");
			completeAppointment.setPractitionerId("0");
			completeAppointment.setPractitionerName("");
			completeAppointment.setGinvstid("0");
		
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			int result = completeAptmDAO.saveCharge(completeAppointment,"0",loginInfo.getId());
			
	}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
}

public String updatebomkitstatus() throws Exception{
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String bomkitid = request.getParameter("bomkitid");
		int res55 = inventoryProductDAO.updateCathStatus(Integer.parseInt(bomkitid),"2");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("");
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return null;
}

public String getsubcatagoryinitemwise() throws Exception {

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
		buffer.append("<select name='subcategoryid' class='form-control chosen' id='subcategoryid' >");
		buffer.append("<option value='0'>Select Sub-Category</option>");
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

public String addtopurchaseque() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		String location=(String) session.getAttribute("location");
		if(location==null){
			location="0";
		}
		StringBuilder builder = new StringBuilder();
		int i = 0;
		int j = 1;
			String prodid = request.getParameter("data");
			for (String mdicinenameid : prodid.split(",")) {
				if (mdicinenameid.equals("0")) {
					continue;
				}
				Product product = inventoryProductDAO.getProduct(mdicinenameid);

				String location1 = inventoryProductDAO.getProductCatalogueLocation(product.getCatalogueid());
				
				if(!location.equals(location1)){
					continue;
				}
				
				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}

				String locationname = pharmacyDAO.getLocationName(location1);

				builder.append("<tr>");
				builder.append("<td>" + j + "</td>");
				builder.append("<input type='hidden' class='addtopoclass' value='" + i + "' class='form-control' />");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].product_id' value='" + i + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].id' value='"+ mdicinenameid + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].location' value='" + product.getLocation() + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].hsnno' value='" + hsnno + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].product_name'  value='" + product.getProduct_name() + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].catalogueid' value='" + product.getCatalogueid() + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].vendor_id'  value='" + product.getVendor_id() + "'>");
				builder.append("<input type='hidden' name='addtopoproduct[" + i + "].loc'  value='" + locationname + "'>");
				builder.append("<td>"+product.getProduct_name()+"</td>");
				builder.append("<td>"+locationname+"</td>");
				builder.append("<td><input type='text' class='form-control' name='addtopoproduct[" + i + "].tqty' id='addtopo_qty" + i + "'></td>");
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

public String savetopurchaseque() throws Exception {
	Connection connection = null;
	try {connection = Connection_provider.getconnection();
	ProcurementDAO procurementDAO= new JDBCProcurementDAO(connection);
	
	String todate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
	for (Product product : productForm.getAddtopoproduct()) {
		if (product == null) {
			continue;
		}
		Product product1 = new Product();
		product1.setDate(todate);
		product1.setQty(Double.parseDouble(product.getTqty()));
		product1.setWarehouse_id(product.getLocation());
		product1.setVendor_id(product.getVendor_id());
		product1.setProduct_name(product.getProduct_name());
		product1.setCatalogueid(product.getCatalogueid());
		product1.setProduct_id(""+product.getId());
		int res = procurementDAO.saveNewTempPo(product1);
		
	}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return "transferproductdata1";
}


public String addtoadjustmentcart() throws Exception {

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String id = request.getParameter("data");
		String prodid = "0";
		int count = 0;
		String newprodid="0";
		if (session.getAttribute("adjustmentprodid") != null) {
			prodid = (String) session.getAttribute("adjustmentprodid");
			for (String t : prodid.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				newprodid = newprodid + "," + t;
				count++;
			}
		}

		for (String s : id.split(",")) {
			if (s.equals("0")) {
				continue;
			}
			boolean flag= false;
			for (String tt : newprodid.split(",") ) {
				if (tt.equals("0")) {
					continue;
				}
				if (tt.equals(s)) {
					flag = true;
					break;
				}
			}
			if(!flag){
				prodid = prodid + "," + s;
				count++;
			}
			
		}
		session.setAttribute("adjustmentprodid", prodid);
		String data = "<a href='#' data-toggle='modal' onclick='showAdjustmentCartPopUp()'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "
				+ count + " </span></a>";

		session.setAttribute("multiadjustcount", "" + count);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + data + "");
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String showadjustmentmedicine() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
		StringBuilder builder = new StringBuilder();
		int i = 0;
		int j = 1;
		if (session.getAttribute("adjustmentprodid") != null) {
			String prodid = (String) session.getAttribute("adjustmentprodid");
			for (String mdicinenameid : prodid.split(",")) {
				if (mdicinenameid.equals("0")) {
					continue;
				}
				int isinadjustment = inventoryProductDAO.checkProductInAdjustment(mdicinenameid);
				if(isinadjustment==1){
					continue;
				}
				isinadjustment = pharmacyDAO.checkProductInSale(mdicinenameid);
				if(isinadjustment==1){
					continue;
				}
				Product product = inventoryProductDAO.getProduct(mdicinenameid);
				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}
				String location = pharmacyDAO.getLocationName(product.getLocation());

				builder.append("<tr>");
				builder.append("<td>" + j + "</td>");
				builder.append("<input type='hidden' class='multiadjustmentclass' value='" + i + "' class='form-control' />");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].product_id' id='multiadj_product_id" + i+ "' value='" + i + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].id' id='multiadj_id" + i + "' value='"+ mdicinenameid + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].location' id='multiadj_location" + i+ "' value='" + product.getLocation() + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].hsnno' id='multiadj_hsnno" + i+ "' value='" + hsnno + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].product_name' id='multiadj_product_name" + i+ "' value='" + product.getProduct_name() + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].batch_no' id='multiadj_batch_no" + i+ "' value='" + product.getBatch_no() + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].expiry_date' id='multiadj_expiry_date" + i+ "' value='" + product.getExpiry_date() + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].loc' id='multiadj_loc" + i+ "' value='" + location + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].stock' id='multiadj_stock" + i+ "' value='" + product.getStock() + "'>");
				builder.append("<td>" + hsnno + "</td>");
				builder.append("<td>"+product.getProduct_name()+"</td>");
				builder.append("<td>"+product.getBatch_no()+"</td>");
				builder.append("<td style='color:"+product.getColor()+"'>"+DateTimeUtils.getCommencingDate1(product.getExpiry_date())+"</td>");
				/*if (i == 0) {
					builder.append("<td id='adjustmenttypetd" + i + "'>");
					builder.append("<select  onchange='resetmultiadjustmentfield(this.value)' name='alladjustmentproduct[" + i + "].adjustment_type'  id='multiadj_adjustment_type" + i + "' class='form-control chosen-select' style='width:95%;'>");
					builder.append("<option value='0'>Select Adjustment</option>");
					builder.append("<option value='1'>Excess Product Adjustment</option>");
					builder.append("<option value='2'>Shortage Product Adjustment</option>");
					builder.append("<option value='3'>Defective Product Adjustment</option>");
					builder.append("<option value='4'>Expired/Dead Product Adjustment</option>");
					builder.append("</select> ");
					builder.append("</td/>");
				} else {
					builder.append("<td id='adjustmenttypetd" + i + "'>");
					builder.append("<select  id='multiadj_adjustment_type" + i + "' name='alladjustmentproduct[" + i + "].adjustment_type' class='form-control chosen-select' style='width:95%;'>");
					builder.append("<option value='0'>Select Adjustment</option>");
					builder.append("<option value='1'>Excess Product Adjustment</option>");
					builder.append("<option value='2'>Shortage Product Adjustment</option>");
					builder.append("<option value='3'>Defective Product Adjustment</option>");
					builder.append("<option value='4'>Expired/Dead Product Adjustment</option>");
					builder.append("</select> ");
					builder.append("</td/>");
				}*/
				builder.append("<td id='adjustmenttypetd" + i + "'>");
				builder.append("<select  id='multiadj_adjustment_type" + i + "' onchange='resetmultiadjustmentfield(this.value,"+i+")' name='alladjustmentproduct[" + i + "].adjustment_type' class='form-control chosen-select' style='width:95%;'>");
				builder.append("<option value='0'>Select Adjustment</option>");
				builder.append("<option value='1'>Excess Product Adjustment</option>");
				builder.append("<option value='2'>Shortage Product Adjustment</option>");
				builder.append("<option value='3'>Defective Product Adjustment</option>");
				builder.append("<option value='4'>Expired/Dead Product Adjustment</option>");
				builder.append("</select> ");
				builder.append("</td/>");
				builder.append("<td style='text-align: center;'>"+product.getStock()+"</td>");
				builder.append("<td><input type='number' class='form-control' name='alladjustmentproduct[" + i+ "].tqty' id='multiadj_qty" + i + "' onchange='calculateChangeMultiAdjData(" + i + ",this.value)'></td>");
				builder.append("<td><input type='text' class='form-control' name='alladjustmentproduct[" + i+ "].totalqty' readonly='readonly' id='multiadj_change_stock" + i + "'></td>");
				//builder.append("<td><input type='text' class='form-control' rows='2' name='alladjustmentproduct[" + i+ "].remark' id='multiadj_remark" + i + "'></td>");
				builder.append("<td><textarea class='form-control' rows='2' id='multiadj_remark" + i + "' name='alladjustmentproduct[" + i+ "].remark' style='background-color: rgba(255, 248, 220, 0.48);' placeholder='Write remark here'></textarea></td>");
				builder.append("<td><i onclick='deleteAdjustmentCartProduct(" + i + "," + mdicinenameid+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
				builder.append("</tr>");
				i++;
				j++;
			}
		}
		builder.append("~");
		builder.append("" + j + "");
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

public String deleteadjustmentcart() throws Exception {

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String id = request.getParameter("data");
		String prodid = "0";
		int count = 0;
		String product_id = "0";
		if (session.getAttribute("adjustmentprodid") != null) {
			prodid = (String) session.getAttribute("adjustmentprodid");
			for (String t : prodid.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				if (t.equals(id)) {
					continue;
				}
				product_id = product_id + "," + t;
				count++;
			}
		}

		session.setAttribute("adjustmentprodid", product_id);

		session.setAttribute("multiadjustcount", "" + count);
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

public String addtoeditcart() throws Exception {

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String id = request.getParameter("data");
		String prodid = "0";
		int count = 0;
		String newprodid="0";
		if (session.getAttribute("multieditprodid") != null) {
			prodid = (String) session.getAttribute("multieditprodid");
			for (String t : prodid.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				newprodid = newprodid + "," + t;
				count++;
			}
		}

		for (String s : id.split(",")) {
			if (s.equals("0")) {
				continue;
			}
			boolean flag= false;
			for (String tt : newprodid.split(",") ) {
				if (tt.equals("0")) {
					continue;
				}
				if (tt.equals(s)) {
					flag = true;
					break;
				}
			}
			if(!flag){
				int isinadjustment = inventoryProductDAO.checkProductInEdit(s);
				if(isinadjustment==1){
					continue;
				}
				prodid = prodid + "," + s;
				count++;
			}
			
		}
		session.setAttribute("multieditprodid", prodid);
		String data = "<a href='#' data-toggle='modal' onclick='showEditCartPopUp()'><span style='background-color: brown;padding: 5px 4px 6px 4px;color: #fff;'>Total : "
				+ count + " </span></a>";

		session.setAttribute("multieditcount", "" + count);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + data + "");
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String showmultipleeditproduct() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		StringBuilder builder = new StringBuilder();
		int i = 0;
		int j = 1;
		if (session.getAttribute("multieditprodid") != null) {
			String prodid = (String) session.getAttribute("multieditprodid");
			String location1 = (String) session.getAttribute("location");
			if (location1 == null) {
				location1 = "0";
			}
			ArrayList<Master> cellList = new ArrayList<Master>();
			String newlocation="0";
			if (!location1.equals("0")) {
				newlocation =location1;
				cellList = inventoryProductDAO.getcellList(location1);
			} else {
				cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				newlocation = "32,33,34,35,36,105";
			}
			
			
			for (String mdicinenameid : prodid.split(",")) {
				if (mdicinenameid.equals("0")) {
					continue;
				}
				int isinadjustment = inventoryProductDAO.checkProductInAdjustment(mdicinenameid);
				if(isinadjustment==1){
					continue;
				}
				isinadjustment = inventoryProductDAO.checkProductInEdit(mdicinenameid);
				if(isinadjustment==1){
					continue;
				}
				Product product = inventoryProductDAO.getProduct(mdicinenameid);
				String hsnno = "";
				if (product.getHsnno() != null) {
					hsnno = product.getHsnno();
				}

				builder.append("<tr>");
				builder.append("<td>" + j + "</td>");
				builder.append("<input type='hidden' class='multieditclass' value='" + i + "' class='form-control' />");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].product_id' id='multiedit_product_id" + i+ "' value='" + i + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].id' id='multiedit_id" + i + "' value='"+ mdicinenameid + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].location' id='multiedit_location" + i+ "' value='" + product.getLocation() + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].hsnno' id='multiedit_hsnno" + i+ "' value='" + hsnno + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].product_name' id='multiedit_product_name" + i+ "' value='" + product.getProduct_name() + "'>");
				builder.append("<input type='hidden' name='alladjustmentproduct[" + i + "].stock' id='multiedit_stock" + i+ "' value='" + product.getStock() + "'>");
				builder.append("<td>"+product.getProduct_name()+"</td>");
				builder.append("<td><input type='number' readonly='readonly' class='form-control' onchange='calSalePerUnitmultiEdit("+i+")' value='"+DateTimeUtils.numberCheck1(product.getPack())+"' name='alladjustmentproduct[" + i+ "].pack'  id='multiedit_pack" + i + "'></td>");
				builder.append("<td><input type='number' class='form-control' onchange='calSalePerUnitmultiEdit("+i+")' value='"+DateTimeUtils.numberCheck(product.getMrp())+"' name='alladjustmentproduct[" + i+ "].mrp'  id='multiedit_mrp" + i + "'></td>");
				builder.append("<td><input type='number' class='form-control' readonly='readonly' value='"+DateTimeUtils.numberCheck(product.getSale_price())+"' name='alladjustmentproduct[" + i+ "].sale_price'  id='multiedit_sale_price" + i + "'></td>");
				builder.append("<td><input type='number' class='form-control' value='"+DateTimeUtils.numberCheck(product.getPurchase_price())+"' name='alladjustmentproduct[" + i+ "].purchase_price'  id='multiedit_purchase_price" + i + "'></td>");
				builder.append("<td><input type='text' class='form-control' value='"+DateTimeUtils.isNull(product.getBatch_no())+"' name='alladjustmentproduct[" + i+ "].batch_no'  id='multiedit_batch_no" + i + "'></td>");
				String expiry ="";
				if(!DateTimeUtils.isNull(product.getExpiry_date()).equals("")){
					String[] expirydate = product.getExpiry_date().split("-");
					expiry = expirydate[1]+"-"+expirydate[0];
				}
				builder.append("<td><input type='text' class='form-control' value='"+expiry+"' placeholder='MM/YYYY' name='alladjustmentproduct[" + i+ "].expiry_date'  id='multiedit_expiry_date" + i + "'></td>");
				
				int tvat = Integer.parseInt(DateTimeUtils.numberCheck(product.getVat()));
				builder.append("<td>");
				builder.append("<select  id='multiedit_vat" + i + "' name='alladjustmentproduct[" + i + "].vat' class='form-control chosen-select'>");
				if (tvat == 0) {
					builder.append("<option selected='selected' value='0'>0%</option>");
				} else {
					builder.append("<option value='0'>0%</option>");
				}
				if (tvat == 5) {
					builder.append("<option selected='selected' value='5'>5%</option>");
				} else {
					builder.append("<option value='5'>5%</option>");
				}
				if (tvat == 12) {
					builder.append("<option selected='selected' value='12'>12%</option>");
				} else {
					builder.append("<option value='12'>12%</option>");
				}
				if (tvat == 18) {
					builder.append("<option value='18' selected='selected'>18%</option>");
				} else {
					builder.append("<option value='18'>18%</option>");
				}
				if (tvat == 28) {
					builder.append("<option selected='selected' value='28'>28%</option>");
				} else {
					builder.append("<option value='28'>28%</option>");
				}
				builder.append("</select> ");
				builder.append("</td>");
				
				builder.append("<td>");
				builder.append("<select  id='multiedit_pshelf" + i + "' name='alladjustmentproduct[" + i + "].shelf' class='form-control chosen-select' >");
				builder.append("<option value=''></option>");
				int previouscellid = inventoryProductDAO.getShelfIdFromName(product.getShelf(),newlocation);
				for (Master master2 : cellList) {
					if (previouscellid == master2.getId()) {
						builder.append("<option value='" + master2.getName() + "' selected='selected'>" + master2.getName()+ "</option>");
					} else {
						builder.append("<option value='" + master2.getName() + "'>" + master2.getName() + "</option>");
					}
				}
				builder.append("</select> ");
				builder.append("</td>");
				
				builder.append("<td>");
				builder.append("<select  id='multiedit_sshelf" + i + "' name='alladjustmentproduct[" + i + "].secondary_shelf' class='form-control chosen-select' >");
				builder.append("<option value=''></option>");
				int secondarycellid = inventoryProductDAO.getShelfIdFromName(product.getSecondary_shelf(),newlocation);
				for (Master master2 : cellList) {
					if (secondarycellid == master2.getId()) {
						builder.append("<option value='" + master2.getName() + "' selected='selected'>" + master2.getName()+ "</option>");
					} else {
						builder.append("<option value='" + master2.getName() + "'>" + master2.getName() + "</option>");
					}
				}
				builder.append("</select> ");
				builder.append("</td>");
				builder.append("<td><textarea class='form-control' rows='2' id='multiedit_remark" + i + "' name='alladjustmentproduct[" + i+ "].remark' style='background-color: rgba(255, 248, 220, 0.48);' placeholder='Write remark here'></textarea></td>");
				builder.append("<td><i onclick='deleteMultiEditCartProduct(" + i + "," + mdicinenameid+ ")' style='cursor: pointer;' class='fa fa-times fa-2x text-danger' ></i></td/>");
				builder.append("</tr>");
				i++;
				j++;
			}
		}
		builder.append("~");
		builder.append("" + j + "");
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

public String deletemultieditcart() throws Exception {

	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		String id = request.getParameter("data");
		String prodid = "0";
		int count = 0;
		String product_id = "0";
		if (session.getAttribute("multieditprodid") != null) {
			prodid = (String) session.getAttribute("multieditprodid");
			for (String t : prodid.split(",")) {
				if (t.equals("0")) {
					continue;
				}
				if (t.equals(id)) {
					continue;
				}
				int isinadjustment = inventoryProductDAO.checkProductInEdit(t);
				if(isinadjustment==1){
					continue;
				}
				product_id = product_id + "," + t;
				
				count++;
			}
		}

		session.setAttribute("multieditprodid", product_id);

		session.setAttribute("multieditcount", "" + count);
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

public String addlimit() throws Exception{
	
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			String limit=request.getParameter("limit");
			String productid=request.getParameter("productid");
			//String location = (String) session.getAttribute("location");
			String location =request.getParameter("location");
			String userid=loginInfo.getUserId();
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			Product product1 = new Product();
			product1.setLimit(Integer.parseInt(limit));
			product1.setProduct_id(productid);
			product1.setLocation(location);
		    product1.setUserid(userid);
		    product1.setDateTime(datetime);
			
			int res=inventoryProductDAO.saveproductQtyLimit(product1);
		
		
		
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache"); 
		  response.getWriter().write(""+res+"");
		  
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		return null;


 }
}