package com.apm.Inventory.web.action;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Expence.eu.bi.ExpenManagementDAO;
import com.apm.Expence.eu.bi.blogic.jdbc.JDBCExpenceManagementDAO;
import com.apm.Expence.eu.entity.Expence;
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
import com.apm.Inventory.web.form.ProcurementForm;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class ProcurementAction extends BaseAction implements ModelDriven<ProcurementForm>, Preparable {

	ProcurementForm procurementForm = new ProcurementForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(25, 1);

	String categoryid;

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
				if(breadcrumbs.getName().equals("Procurement Dashboard")){
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
				breadcrumbs.setName("Procurement Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("Procurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Procurement");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {

				categoryid = "2";
			}
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			String voucherno = (String) session.getAttribute("confirmpo_voucherno");
			String fromdate = (String) session.getAttribute("confirmpo_fromdate");
			String todate = (String) session.getAttribute("confirmpo_todate");
			String iswithpo = (String) session.getAttribute("confirmpo_iswithpo");
			String vendorid = (String) session.getAttribute("confirmpo_vendor_id");
			/*
			 * String ispodashboard =(String)
			 * session.getAttribute("confirmpo_ispodashboard");
			 */
			if (fromdate == null) {
				fromdate = procurementForm.getFromdate();
			} else {
				session.removeAttribute("confirmpo_fromdate");
			}
			if (todate == null) {
				todate = procurementForm.getTodate();
			} else {
				session.removeAttribute("confirmpo_todate");
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
			if (voucherno == null) {
				voucherno = procurementForm.getVoucherno();
			} else {
				session.removeAttribute("confirmpo_voucherno");
			}

			if (voucherno != null) {
				if (voucherno.equals("")) {
					voucherno = null;
				}
			}
			if (vendorid == null) {
				vendorid = procurementForm.getVendor_id();
			} else {
				session.removeAttribute("confirmpo_vendor_id");
			}

			if (vendorid == null) {
				vendorid = "";
			}

			if (iswithpo == null) {
				iswithpo = procurementForm.getIswithpo();
			} else {
				session.removeAttribute("confirmpo_iswithpo");
			}

			if (iswithpo != null) {
				if (iswithpo.equals("")) {
					iswithpo = "0";
				}
			} else {
				iswithpo = "0";
			}
			String ispodashboard = procurementForm.getIspodashboard();

			if (ispodashboard != null) {
				if (ispodashboard.equals("")) {
					ispodashboard = "0";
				}
			} else {
				ispodashboard = "0";
			}
			procurementForm.setIswithpo(iswithpo);
			procurementForm.setVendor_id(vendorid);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());

			boolean isAmcCmc=false;
			
			int totalCount = procurementDAO.getProcurementCount(categoryid, location, fromdate, todate, voucherno,
					iswithpo, vendorid,isAmcCmc);
			pagination.setPreperties(totalCount);

			ArrayList<Procurement> procurementList = procurementDAO.getAllProcurementList(categoryid, pagination,
					location, voucherno, userProfile.getProcurementType(), fromdate, todate, vendorid, iswithpo,isAmcCmc,loginInfo);
			procurementForm.setProcurementList(procurementList);

			/*
			 * //lokesh //lokesh if(procurementForm.getIswithpo()==null){
			 * procurementForm.setIswithpo("0"); }
			 * if(procurementForm.getIswithpo().equals("1")){
			 * procurementForm.setIswithpo("1"); }else
			 * if(procurementForm.getIswithpo().equals("2")){
			 * procurementForm.setIswithpo("2"); //without po }else{
			 * procurementForm.setIswithpo("0"); }
			 * if(procurementForm.getIswithpo().equals("1")){
			 * ArrayList<Procurement> withpolist= new ArrayList<Procurement>();
			 * for(Procurement p:procurementList){
			 * if(!p.getGrnno().equals("0")){ withpolist.add(p); } }
			 * procurementForm.setProcurementList(withpolist); }
			 * 
			 * if(procurementForm.getIswithpo().equals("2")){
			 * ArrayList<Procurement> withpolist= new ArrayList<Procurement>();
			 * for(Procurement p:procurementList){ if(p.getGrnno().equals("0")){
			 * withpolist.add(p); } }
			 * procurementForm.setProcurementList(withpolist); }
			 */
			ArrayList<Vendor> vlist = procurementDAO.getVendorList();
			procurementForm.setVendorList(vlist);
			pagination.setPage_records(procurementList.size());
			procurementForm.setTotalRecords(totalCount);
			procurementForm.setPagerecords(Integer.toString(pagination.getPage_records()));

			procurementForm.setCategory_id(categoryid);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());
			procurementForm.setDate(date);

			procurementForm.setVoucherno(voucherno);

			// product name list
			ArrayList<Product> poproductList = inventoryProductDAO.getPoProductList(categoryid);
			procurementForm.setPoproductList(poproductList);

			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);

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

			if (priscription.getEdit_purchaseorder() != null)
				if (priscription.getEdit_purchaseorder().equals("0"))
					loginInfo.setEdit_purchaseorder(false);
				else
					loginInfo.setEdit_purchaseorder(true);

			if (priscription.getDelete_purchaseorder() != null)
				if (priscription.getDelete_purchaseorder().equals("0"))
					loginInfo.setDelete_purchaseorder(false);
				else
					loginInfo.setDelete_purchaseorder(true);

			if (priscription.isApprove_po()) {
				loginInfo.setApprove_po(true);
			} else {
				loginInfo.setApprove_po(false);
			}

			/*
			 * String fromdate=""; DateFormat dateFormat1 = new
			 * SimpleDateFormat("dd-MM-yyyy"); Calendar cal =
			 * Calendar.getInstance(); fromdate =
			 * dateFormat1.format(cal.getTime()); fromdate =
			 * DateTimeUtils.getCommencingDate1(fromdate);
			 */

			int totalgrn = procurementDAO.getTotalTodayGRN(fromdate, todate, location);
			procurementForm.setTotalgrn(totalgrn);

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
			session.removeAttribute("vendorid");

			ArrayList<String> unitOfMeseurementList = inventoryProductDAO.getUnitOfMessurementList();
			procurementForm.setUnitOfMeseurementList(unitOfMeseurementList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return super.execute();
	}

	public String po() throws Exception {

		try {
			categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {

				categoryid = "1";
			}
			procurementForm.setCategory_id(categoryid);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "createpo";
	}

	public String complete() throws Exception {
		try {
			categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {

				categoryid = "1";
			}
			procurementForm.setCategory_id(categoryid);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "completepo";
	}

	public String payment() throws Exception {
		String selectedid = request.getParameter("selectedid");
		String vendorid = request.getParameter("vendorid");
		if (selectedid == null) {
			selectedid = (String) session.getAttribute("sessionprocurementid");
		}
		if (vendorid == null) {
			vendorid = (String) session.getAttribute("sessionvendorid");
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
				if(breadcrumbs.getName().equals("Payment And History")){
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
				breadcrumbs.setName("Payment And History");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("paymentProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Payment And History");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Product> voucherList = poPaymenytDAO.getAllVoucherList(vendorid);
			procurementForm.setVoucherList(voucherList);
			Procurement procurement = poPaymenytDAO.getPaymentHistoryDetails(selectedid);
			procurementForm.setProd_id(procurement.getProd_id());
			procurementForm.setVendor(procurement.getVendor());
			procurementForm.setBrand(procurement.getBrand());
			procurementForm.setProcurementid(selectedid);
			procurementForm.setVendor_id(procurement.getVendor_id());
			ArrayList<Procurement> payrecieptList = poPaymenytDAO.getPaymentRecieptListByVendor(vendorid);
			double totalAmt = 0;
			double sumofpayamount = 0;
			double balance = 0;
			int size1 = voucherList.size();
			if (size1 > 0) {
				totalAmt = voucherList.get(size1 - 1).getTotalAmt();
				sumofpayamount = voucherList.get(size1 - 1).getTotalpaidamt();
				balance = voucherList.get(size1 - 1).getTotalbalance();
			}
			if (totalAmt == 0) {
				int size2 = payrecieptList.size();
				if (size2 > 0) {
					totalAmt = payrecieptList.get(size2 - 1).getTotalnetamt();
				}
			}

			/*
			 * double totalAmt = poPaymenytDAO.getTotalAountoFVendor(vendorid);
			 */
			procurementForm.setTotal(DateTimeUtils.changeFormat(Math.round(totalAmt * 100.0) / 100.0));

			procurementForm.setPayrecieptList(payrecieptList);

			// get Product Return List Account
			ArrayList<Procurement> returnAccountList = poPaymenytDAO.getProductReturnListByVendor(vendorid);
			procurementForm.setReturnAccountList(returnAccountList);

			int size = returnAccountList.size();
			if (size > 0) {
				String grnreturnids = returnAccountList.get(size - 1).getGrnreturnids();
				procurementForm.setGrnreturnids(grnreturnids);
			} else {
				procurementForm.setGrnreturnids("0");
			}

			// get return amount
			double sumofreturn = poPaymenytDAO.getTotalReturedAmount(vendorid);
			procurementForm.setSumofreturn(DateTimeUtils.changeFormat(sumofreturn));
			// get balance amount

			/* double sumofpayamount = poPaymenytDAO.getTotalPaid(vendorid); */
			/* double balance = totalAmt - sumofpayamount; */
			if (balance < 0) {
				balance = 0;
			}
			procurementForm.setBalance(DateTimeUtils.changeFormat(Math.round(balance * 100.0) / 100.0));
			session.setAttribute("sessionvendorid", vendorid);
			procurementForm.setCategory_id(categoryid);
			procurementForm.setVendor_id(vendorid);

			ArrayList<Master> bankNameList = masterDAO.getBankNameList();
			procurementForm.setBankNameList(bankNameList);

			String paymentsucess = request.getParameter("paymentsucess");
			if (paymentsucess != null) {
				if (paymentsucess.equals("1")) {
					procurementForm.setSucessmsg("Payment done Suceessfully");
					addActionMessage("Payment done Suceessfully");
				} else {
					procurementForm.setSucessmsg("0");
				}
			} else {
				procurementForm.setSucessmsg("0");
			}
			ArrayList<Master> ledgerList = appointmentTypeDAO.getExpenceLedgerAmountList(loginInfo);
			procurementForm.setLedgerList(ledgerList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "payment";
	}

	public String medicinepo() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String allpolist = request.getParameter("allpolist");
			String commencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			for (String str : allpolist.split(",")) {

				int id = Integer.parseInt(str);
				if (id == 0) {
					continue;
				}
				String pid = str;
				Product product = inventoryProductDAO.getProduct(pid);
				String qty = request.getParameter("qty" + str);
				double qty1=Double.parseDouble(qty);
				int result = procurementDAO.addTempPo(pid, product.getVendor_id(),qty, 0, "0", "0", 0,
						product.getVat(), loginInfo.getLoginsessionid(),"","","0");
				result = pharmacyDAO.addUpdateMedicinePoList(pid, qty, commencing);
				// remove from requested if exist
				result = pharmacyDAO.updateRequestedStatus(pid);

			}
			int procurementid = 0;

			ArrayList<Product> polistByVendor = procurementDAO.getAllPoListByVendor(loginInfo.getLoginsessionid(),"0");
			for (Product product : polistByVendor) {

				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				procurementid = procurementDAO.saveParengtPrecurementData(product.getVendor_id(), date, 0, "0", 0,"","0");

				ArrayList<Product> polist = procurementDAO.getPoProductByVendor(product.getVendor_id(),
						loginInfo.getLoginsessionid(),"0");
				for (Product poproduct : polist) {

					int result = procurementDAO.saveProcurementData(poproduct, ""+poproduct.getQty(), 0,
							product.getVendor_id(), "0", procurementid, 0, 0, poproduct.getVat());

				}
			}

			int result = procurementDAO.truncateTempPo(loginInfo.getLoginsessionid());
			categoryid = "2";
			procurementForm.setCategory_id(categoryid);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return execute();

	}

	public String savenewpo() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			for (Product product : procurementForm.getProcurements()) {

				String pid = product.getProduct_name();
				Product product1 = procurementDAO.getProductDetails(pid);

				// add temp po
				int result = procurementDAO.addTempPo(pid, product1.getVendor_id(), ""+product.getQty(), 0, "0", "0", 0,
						product1.getVat(), loginInfo.getLoginsessionid(),"","","0");

				// update medicine_type and pack to inventory product
				// int j=
				// inventoryProductDAO.updatePackandMedicineType(product.getPack(),product.getMedicine_type(),pid);

			}

			int procurementid = 0;

			ArrayList<Product> polistByVendor = procurementDAO.getAllPoListByVendor(loginInfo.getLoginsessionid(),"0");
			for (Product product : polistByVendor) {

				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				procurementid = procurementDAO.saveParengtPrecurementData(product.getVendor_id(), date, 0, "0", 0,"","0");

				ArrayList<Product> polist = procurementDAO.getPoProductByVendor(product.getVendor_id(),
						loginInfo.getLoginsessionid(),"0");
				for (Product poproduct : polist) {

					int result = procurementDAO.saveProcurementData(poproduct, ""+poproduct.getQty(), 0,
							product.getVendor_id(), "0", procurementid, 0, 0, poproduct.getVat());
					result = procurementDAO.updateProcurementStatus(poproduct.getId(), "0");
				}
			}

			int result = procurementDAO.truncateTempPo(loginInfo.getLoginsessionid());

			procurementForm.setCategory_id(categoryid);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "savepo";
	}

	public String savepo() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("vendor");

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int procurementid = procurementDAO.saveParengtPrecurementData(vendorid, date, 0, "0", 0,"","0");

			for (Product product : procurementForm.getProcurements()) {

				String pid = product.getProduct_name();
				Product product1 = procurementDAO.getProductDetails(pid);
				String qty = product.getQuantity();
				
				String brandid = product.getBrand();

				double total = Double.parseDouble(product1.getPurchase_price()) * Double.parseDouble(qty);

				int result = procurementDAO.saveProcurementData(product1,qty, total, vendorid, brandid, procurementid,
						0, 0, product1.getVat());

				// int
				// res=procurementDAO.getTotalQuantityCount(product.getId());

				// int r=procurementDAO.updateProduct(res,product.getId());

				procurementForm.setCategory_id(categoryid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return execute();
	}

	public String updategoods() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String proc_id = request.getParameter("proc_id");
			int result = procurementDAO.updategoodsPO(proc_id);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return execute();
	}

	public String confirmpo() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String procurementid = request.getParameter("proc_id");
			String remark = request.getParameter("remark");
			String vendoremailid = request.getParameter("venderemail");
			String saveconfirmpo = request.getParameter("saveconfirmpo");
			String isAmcCMCDash = request.getParameter("isAmcCMCDash");

			String voucherno = procurementForm.getVoucherno();
			String fromdate = procurementForm.getFromdate();
			String todate = procurementForm.getTodate();
			String iswithpo = procurementForm.getIswithpo();
			String vendor_id = procurementForm.getVendor_id();
			String ispodashboard = procurementForm.getIspodashboard();

			session.setAttribute("confirmpo_voucherno", voucherno);
			session.setAttribute("confirmpo_fromdate", fromdate);
			session.setAttribute("confirmpo_todate", todate);
			session.setAttribute("confirmpo_iswithpo", iswithpo);
			session.setAttribute("confirmpo_vendor_id", vendor_id);
			session.setAttribute("confirmpo_ispodashboard", ispodashboard);
			
			String po_disc = request.getParameter("po_disc");
			String userid=loginInfo.getUserId();
			if (saveconfirmpo != null) {
				if (saveconfirmpo.equals("")) {
					saveconfirmpo = "0";
				} else if (saveconfirmpo.equals("1")) {
					saveconfirmpo = "1";
				}
			} else {
				saveconfirmpo = "0";
			}
			if (saveconfirmpo.equals("1")) {
				ArrayList<Product> allprocurementList = procurementDAO
						.getProcurementListBeforeProcurement(procurementid);
				for (Product product : allprocurementList) {
					String qty = request.getParameter("qty" + product.getId());
					String discount = request.getParameter("discounts" + product.getId());
					String rate = request.getParameter("rate" + product.getId());
					String vat = request.getParameter("vat" + product.getId());
					String poDescription = request.getParameter("poDescription"+ product.getId());
					String poHsnno = request.getParameter("poHsnno"+product.getId());
					int res = procurementDAO.updateDiscQty(qty, discount, product.getId(), rate, vat,poDescription,poHsnno,userid);
					//for vspm update log table 
					if(loginInfo.isLmh()) {
						res=procurementDAO.updateDiscQtyLog(qty, discount, product.getId(), rate, vat,poDescription,poHsnno,userid);
					}
				}
				int res = procurementDAO.updateProcurementPoDisc(DateTimeUtils.convertToDouble(po_disc),procurementid);
			} else {
				ArrayList<Product> allprocurementList = procurementDAO
						.getProcurementListBeforeProcurement(procurementid);

				for (Product product : allprocurementList) {

					String qty = request.getParameter("qty" + product.getId());
					String discount = request.getParameter("discounts" + product.getId());
					String rate = request.getParameter("rate" + product.getId());
					String vat = request.getParameter("vat" + product.getId());
					String poDescription = request.getParameter("poDescription"+ product.getId());
					String poHsnno = request.getParameter("poHsnno"+product.getId());
					int res = procurementDAO.updateDiscQty(qty, discount, product.getId(), rate, vat,poDescription,poHsnno,userid);
					//for vspm update log table
					if(loginInfo.isLmh()) {
					    res=procurementDAO.updateDiscQtyLog(qty, discount, product.getId(), rate, vat,poDescription,poHsnno,userid);
					}
					int r = procurementDAO.updateQtyByAdmin(qty, product.getId());
					r = procurementDAO.updateTempPoQtyByAdmin(qty, product.getNewpo());
				}
				int res1 = procurementDAO.updateProcurementPoDisc(DateTimeUtils.convertToDouble(po_disc),procurementid);
				int result = procurementDAO.updateConfirmPOStatus(procurementid);
				result = procurementDAO.addConfirmProcurement(remark, procurementid);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				int res = procurementDAO.updateProcurementAproveUserid(procurementid, loginInfo.getUserId(), date);
				procurementForm.setCategory_id(categoryid);

				String checkemailsend = procurementForm.getMailcheck();
				if (checkemailsend.equals("0")) {
					email(procurementid);
				}
			}
			
			if(DateTimeUtils.numberCheck(isAmcCMCDash).equals("1")){
				return "amcCmcMethod";
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "confirmpo";
	}

	public String product() throws SQLException {
		String supid = request.getParameter("supid");
		String count = request.getParameter("count");
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String brandidseries = procurementDAO.getBrandidseries(supid);
			ArrayList<Master> brandnameList = procurementDAO.getBrandNameList(supid, brandidseries);
			ArrayList<Product> productList = procurementDAO.getProductList(supid);

			StringBuffer str = new StringBuffer();

			for (int i = 0; i < Integer.parseInt(count); i++) {

				str.append("<tr>");
				str.append("<td>" + (i + 1) + ")</td>");
				str.append("<td id='tdprodcode" + i + "'>0</td>");

				str.append("<td id='tdbrand" + i + "'>");
				str.append("<select onchange='setbrandname(this.value," + i + ")' name='procurements[" + i
						+ "].brand' id='brandname" + i + "'>");
				str.append("<option value='0'>Select Product Name</option>");
				for (Master master : brandnameList) {
					str.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
				}
				str.append("</select>");
				str.append("</td>");

				str.append("<td id='tdproduct" + i + "'>");
				str.append("<select onchange='setproductdetails(this.value," + i + ")'  name='procurements[" + i
						+ "].product_name' id='vendorprodlist" + i + "'>");
				str.append("<option value='0'>Select Product</option>");
				for (Product product : productList) {
					str.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
				}
				str.append("</select>");
				str.append("</td>");

				str.append("<td id='tdmrp" + i + "'></td>");
				str.append("<td id='tdpurprice" + i + "'></td>");
				str.append("<td id='tdstock" + i + "'></td>");

				str.append(" <td id='tdqty" + i + "'><input onchange='setprotctTotal(this.value," + i
						+ ")' name='procurements[" + i + "].quantity'  id='txtQty" + i
						+ "' type='text' style='text-align:center' ></td>");
				str.append("<td id='tdtotal" + i + "'></td>");
				str.append("<td></td>");
				str.append("</tr>");

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String brand() throws Exception {
		String bid = request.getParameter("bid");
		String row = request.getParameter("row");
		String vendor = request.getParameter("vendor");
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			ArrayList<Product> productList = procurementDAO.getBrandProductList(vendor, bid);

			StringBuffer str = new StringBuffer();

			str.append("<select onchange='setproductdetails(this.value," + row + ")' name='procurements[" + row
					+ "].product_name' id='vendorprodlist" + row + "'>");
			str.append("<option value='0'>Select Product</option>");
			for (Product product : productList) {
				str.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return null;
	}

	public String pdtails() throws Exception {

		String pid = request.getParameter("pid");
		String row = request.getParameter("row");

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			Product product = procurementDAO.getProductDetails(pid);

			String str = product.getId() + "~" + product.getProduct_code() + "~" + product.getMrp() + "~"
					+ product.getPurchase_price();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return null;
	}

	public String updatepo() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");

			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			int res = procurementDAO.updatePoData(id);

			procurementForm.setCategory_id(categoryid);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return execute();
	}

	public String deletepo() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String id = request.getParameter("id");
			String delete_reason = request.getParameter("delete_reason");
			Procurement procurement = procurementDAO.getProcurement(id, 0);
			ArrayList<Product> list = procurementDAO.getProcurementProductList(procurement.getProcurementid(), 0);

			for (Product product : list) {
				int t = productDAO.updateTOZeroStock(product.getProduct_id());
				// t=
				// procurementDAO.cancelPo(String.valueOf(product.getId()),delete_reason);
				// stock log
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int qtyinout = 1;
				String source = "Cancel Procurement";
				if (product.getStock() != "0") {
					if (product.getStock().equals("")) {
						product.setStock("0");
					}
				} else {
					product.setStock("0");
				}
				int currentstock = productDAO.getPreviousStock(product.getProduct_id());
				int previousstock =Integer.parseInt(product.getStock());
				int changeqty = 0;
				if (currentstock > previousstock) {
					changeqty = currentstock - previousstock;
				} else {
					changeqty = previousstock - currentstock;
				}
				int res = productDAO.insertIntoProductLog(loginInfo.getUserId(), datetime, procurement.getLocation(),
						qtyinout, product.getProduct_id(), product.getCatalogueid(), source, currentstock,
						previousstock, changeqty, procurement.getProcurementid(), "0", 0, 0, 0, "0");

				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(product.getProduct_id(), date);
				if (!checkopningstockexist) {
					int r = pharmacyDAO.saveOpeningStock(product.getProduct_id(), date, previousstock, "0");
				}
				t = procurementDAO.cancelPo(String.valueOf(product.getId()));
			}
			// int res=
			// procurementDAO.deleteVendorProcurementDetails(procurement.getVoucherno(),
			// procurement.getProcurementid());
			String userid = loginInfo.getUserId();

			String loc = (String) session.getAttribute("location");
			if (loc == null) {
				loc = "0";
			}
			int res = procurementDAO.saveUpDeleteProcurment(id, delete_reason, userid, loc);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return execute();
	}

	public String deletepoajax() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			
			Procurement procurement =  procurementDAO.getProcurement(id, 0);
			int count = procurementDAO.getProcurementEntryCount(procurement.getProcurementid());
			int ressult =0;
			if(count>1){
				String productname= inventoryProductDAO.getProductNameFromID(procurement.getProduct_id());
				procurement.setProduct(productname);
				int res = procurementDAO.deletePo(id);
				if(res>0){
					ressult=1;
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int deletelog = procurementDAO.saveDeletePoProductLog(procurement,loginInfo.getUserId(),datetime);
				}
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+ressult+"");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;

	}

	public String setproductajax() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String supid = request.getParameter("id");
			String brandidseries = procurementDAO.getBrandidseries(supid);
			ArrayList<Master> brandnameList = procurementDAO.getBrandNameList(supid, brandidseries);
			ArrayList<Product> productList = procurementDAO.getProductList(supid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<select name='brandname' id='brandname' onchange='setproductName(this.value)'>");
			buffer.append("<option value='0'>Select Product Name</option>");
			for (Master master : brandnameList) {

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

	public String getproductnames() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			String vendor = request.getParameter("vendor");

			ArrayList<Product> productList = procurementDAO.getBrandProductList(vendor, id);

			StringBuffer str = new StringBuffer();

			str.append("<select onchange='setproductinfo(this.value)' id='product' name='product'>");
			str.append("<option value='0'>Select Product</option>");
			for (Product product : productList) {
				str.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String updatepro() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String id = request.getParameter("id");
			String vendor_id = request.getParameter("vendor");
			String prodid = request.getParameter("prodid");
			String brand_id = request.getParameter("brand_id");
			String product = request.getParameter("product");
			String mrp = request.getParameter("mrp");
			String purchase_price = request.getParameter("purchase_price");
			String stock = request.getParameter("stock");
			String quantity = request.getParameter("quantity");
			String total = request.getParameter("total");

			Procurement procurement = new Procurement();
			procurement.setId(Integer.parseInt(id));
			procurement.setVendor_id(vendor_id);
			procurement.setProduct_id(prodid);
			procurement.setBrand_id(brand_id);
			procurement.setMrp(mrp);
			procurement.setPurchase_price(purchase_price);
			procurement.setStock(stock);
			procurement.setQuantity(quantity);
			procurement.setTotal(total);

			int result = procurementDAO.updatePoductpo(procurement);

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
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String rowcount = request.getParameter("rowcount");

			int i = (Integer.parseInt(rowcount));
			String brandidseries = procurementDAO.getBrandidseries(vendorid);
			ArrayList<Master> brandnameList = procurementDAO.getBrandNameList(vendorid, brandidseries);
			ArrayList<Product> productList = procurementDAO.getProductList(vendorid);

			StringBuffer str = new StringBuffer();

			str.append("<td>" + (i + 1) + ")</td>");
			str.append("<td id='tdprodcode" + i + "'>0</td>");

			str.append("<td id='tdbrand" + i + "'>");
			str.append("<select onchange='setbrandname(this.value," + i + ")' name='procurements[" + i
					+ "].brand' id='brandname" + i + "'>");
			str.append("<option value='0'>Select Product Name</option>");
			for (Master master : brandnameList) {
				str.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			str.append("</select>");
			str.append("</td>");

			str.append("<td id='tdproduct" + i + "'>");
			str.append("<select onchange='setproductdetails(this.value," + i + ")'  name='procurements[" + i
					+ "].product_name' id='vendorprodlist" + i + "'>");
			str.append("<option value='0'>Select Product</option>");
			for (Product product : productList) {
				str.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
			}
			str.append("</select>");
			str.append("</td>");

			str.append("<td id='tdmrp" + i + "'></td>");
			str.append("<td id='tdpurprice" + i + "'></td>");
			str.append("<td id='tdstock" + i + "'></td>");

			str.append(" <td id='tdqty" + i + "'><input onchange='setprotctTotal(this.value," + i
					+ ")' name='procurements[" + i + "].quantity'  id='txtQty" + i
					+ "' type='text' style='text-align:center' ></td>");
			str.append("<td id='tdtotal" + i + "'></td>");
			str.append("<td></td>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return null;
	}

	public String addnewpo() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String count = request.getParameter("count");

			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {
				categoryid = "2";
			}
			ArrayList<Product> poproductList = inventoryProductDAO.getPoProductList(categoryid);
			StringBuffer buffer = new StringBuffer();

			buffer.append("<td>");
			buffer.append("<select class='form-control chosen' id='product_id" + count + "' name='procurements[" + count
					+ "].product_name'>");
			buffer.append("<option value='0'>Select Product</option>");
			for (Product product : poproductList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");
			}

			buffer.append("</select>");
			buffer.append("</td>");

			buffer.append("	<td><input class='productQty form-control' onkeyup='getTotal(1)' name='procurements["
					+ count + "].qty' id='txtQty1' type='text'></td>");
			buffer.append(
					"<td><a href='#' onclick=deleteTemp(this)><i class='fa fa-times fa-2x' style='color:#d9534f;' aria-hidden='true'></i></a></td>");

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

	public String getreceipt() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");

			Procurement procurement = procurementDAO.getProcurement(id, 0);

			StringBuffer buffer = new StringBuffer();

			ArrayList<Procurement> allprocurementList = procurementDAO
					.getListProcurement(procurement.getProcurementid());
			int i = 0;
			for (Procurement proc : allprocurementList) {

				Product product = inventoryProductDAO.getProduct(proc.getProd_id());
				buffer.append("<tr>");
				buffer.append("<td>" + product.getProduct_name() + "</td>");
				buffer.append("<td class='hidden'>" + product.getBrand() + "</td>");
				buffer.append("<td>" + product.getVendor() + "</td>");
				buffer.append("<td id='qtyreq" + i + "'>" + proc.getQuantity() + "</td>");
				buffer.append("<td id='pack" + i + "'>" + product.getPack() + "</td>");
				buffer.append("<td id='medcinetype" + i + "'>" + product.getMedicine_type() + "</td>");
				buffer.append("</tr>");
				i++;
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());
			ArrayList<Master> cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");

			buffer.append("~");
			i = 0;
			for (Procurement procurement2 : allprocurementList) {

				Product pmaster = inventoryProductDAO.getProduct(procurement2.getProd_id());

				buffer.append("<tr>");
				buffer.append("<td>" + (i + 1) + "<input type='hidden' value='" + procurement2.getId()
						+ "' name='procurements[" + i + "].id' /></td>");
				buffer.append("<td>" + date + "<input type='hidden' value='" + procurement2.getProd_id()
						+ "' name='procurements[" + i + "].product_id' /></td>");
				if (i == 0) {
					buffer.append(
							"<td><input type='text' class='form-control' onkeyup='setAllVoucher(this.value)' id='voucherno"
									+ i + "' required='required' name='procurements[" + i + "].voucherno' /></td>");
				} else {
					buffer.append("<td><input type='text' disabled='disabled' class='form-control'  id='voucherno" + i
							+ "' required='required' name='procurements[" + i + "].voucherno' /></td>");
				}
				buffer.append("<td><input type='text' class='form-control' required='required' name='procurements[" + i
						+ "].batch_no'> </td>");
				buffer.append("<td><input type='text' class='form-control' name='procurements[" + i
						+ "].expiry_date' id='expiry_date" + i + "'  required='required' placeholder='MM/YYYY'></td>");
				buffer.append("<td><input type='text' class='form-control' name='procurements[" + i
						+ "].purchase_date' value='" + date + "' id='purchase_date" + i
						+ "' required='required'/></td>");
				buffer.append("<td><input type='text' class='form-control' name='procurements[" + i
						+ "].mfg'  required='required'></td>");
				buffer.append("<td class=''><input type='text' onkeyup='shoeunitprice(this.value," + i
						+ ")' class='form-control' id='mrp" + i + "' name='procurements[" + i
						+ "].mrp' required='required'></td>");
				buffer.append("<td><input type='text' class='form-control' id='packs" + i + "' value='"
						+ pmaster.getPack() + "' name='procurements[" + i + "].pack'  required='required'></td>");
				buffer.append("<td><input type='text' class='form-control' id='sale_price" + i + "' name='procurements["
						+ i + "].sale_price' required='required'></td>");
				buffer.append("<td><input type='text' value='0' class='form-control' name='procurements[" + i
						+ "].vat'  required='required'></td>");
				buffer.append("<td><input  type='text' value='0' class='form-control' id='purchase_price" + i
						+ "' name='procurements[" + i + "].purchase_price' required='required'></td>");
				buffer.append("<td><input type='text' class='form-control' name='procurements[" + i
						+ "].received_qty' required='required'></td>");
				buffer.append("<td>");
				buffer.append("<select class='form-control' name='procurements[" + i + "].shelf'>");
				buffer.append("<option value='0'>Select Shelf</option>");
				for (Master master : cellList) {

					buffer.append("<option value='" + master.getName() + "'>" + master.getName() + "</option>");
				}
				buffer.append("</select>");
				buffer.append("</td>");

				buffer.append("<td><input type='button' onclick='opennewBatchProduct(" + procurement2.getProd_id() + ","
						+ i + ")' class='btn btn-primary' value='+' /> <input type='hidden' id='newproduct" + i
						+ "' value='0' name='procurements[" + i + "].newproduct' /> </td>");
				buffer.append("</tr>");

				buffer.append("<p id='disp" + i + "'></p>");
				i++;
			}

			buffer.append("<input type='hidden' value='" + i + "' id='tcount' />");

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

	public String vieworder() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");

			Procurement procurement = procurementDAO.getProcurement(id, 0);
			Vendor vendor = inventoryVendorDAO.getVendor(procurement.getVendor_id());
			Product vendorPO = procurementDAO.getProcAccountDetails(procurement.getProcurementid());
			StringBuffer buffer = new StringBuffer();
			double subTotal = 0;

			ArrayList<Procurement> allprocurementList = procurementDAO
					.getListProcurement(procurement.getProcurementid());
			int i = 0;
			for (Procurement proc : allprocurementList) {

				Product product = inventoryProductDAO.getProduct(proc.getProd_id());
				buffer.append("<tr>");
				buffer.append("<td>" + product.getProduct_name() + "</td>");
				buffer.append("<td class='hidden'>" + product.getBrand() + "</td>");
				buffer.append("<td>" + product.getVendor() + "</td>");
				buffer.append("<td id='qtyreq" + i + "'>" + proc.getQuantity() + "</td>");
				buffer.append("<td id='pack" + i + "'>" + product.getPack() + "</td>");
				buffer.append("<td id='medcinetype" + i + "'>" + product.getMedicine_type() + "</td>");
				buffer.append("</tr>");

				subTotal = subTotal + Double.parseDouble(proc.getTotal());

				i++;
			}

			buffer.append("~");
			i = 0;
			for (Procurement procurement2 : allprocurementList) {

				Product pmaster = inventoryProductDAO.getProduct(procurement2.getProd_id());

				buffer.append("<tr>");
				buffer.append("<td>" + (i + 1) + "</td>");
				// buffer.append("<td>"+DateTimeUtils.getCommencingDate1(procurement2.getDate())+"</td>");
				// buffer.append("<td>"+procurement2.getVoucherno()+"</td>");
				buffer.append("<td>" + pmaster.getProduct_name() + "</td>");
				buffer.append("<td>" + pmaster.getHsnno() + "</td>");
				buffer.append("<td>" + pmaster.getBatch_no() + "</td>");
				buffer.append("<td>" + pmaster.getExpiry_date() + "</td>");
				// buffer.append("<td>"+DateTimeUtils.getCommencingDate1(procurement2.getDate())+"</td>");
				buffer.append("<td>" + pmaster.getMfg() + "</td>");
				buffer.append("<td class=''>" + pmaster.getMrp() + "</td>");
				buffer.append("<td>" + pmaster.getPack() + "</td>");
				buffer.append("<td>" + pmaster.getShedule() + "</td>");
				buffer.append("<td>" + pmaster.getSale_price() + "</td>");
				buffer.append("<td>" + pmaster.getVat() + "</td>");
				buffer.append("<td>" + procurement2.getPurchase_price() + "</td>");
				buffer.append("<td>" + procurement2.getQuantity() + "</td>");
				buffer.append("<td>" + pmaster.getShelf() + "</td>");

				buffer.append("</tr>");
				i++;
			}
			buffer.append("~");
			String purdate = DateTimeUtils.getCommencingDate1(procurement.getDate());
			String voucherdate = DateTimeUtils.getCommencingDate1(procurement.getVoucherdate());
			buffer.append("<b>Supplier Name:</b><span>" + vendor.getName()
					+ "</span> &nbsp; | &nbsp; <b>Voucher No:</b><span>" + procurement.getVoucherno()
					+ "</span> &nbsp; | &nbsp; <b>Security Inward No:</b><span>" + vendorPO.getSecurity_no()
					+ "</span> &nbsp; | &nbsp; <b>Security Inward Date:</b><span>" + vendorPO.getSecurity_date()
					+ "</span> &nbsp; | &nbsp; <b>Voucher Date:</b><span>" + voucherdate
					+ "</span> &nbsp; | &nbsp; <b>Purchase Date:</b><span>" + purdate + "</span>");

			buffer.append("~");

			double disc = poPaymenytDAO.getTotalVoucherDiscount(procurement.getVoucherno(),
					procurement.getProcurementid());
			int dmcmplted = procurementDAO.checkIsComplatedDm(procurement.getProcurementid());
			double netAmt = poPaymenytDAO.getTotalVoucherAmount(procurement.getVoucherno(),
					procurement.getProcurementid(), dmcmplted);
			Product returnDate = procurementDAO.getVendorReturnAccountData(procurement.getVendor_id());
			String credit = "0";
			String debit = "0";
			if (returnDate != null) {
				debit = returnDate.getDebit();
				credit = returnDate.getCredit();
			}
			buffer.append("<h5> Rs.<span>" + DateTimeUtils.changeFormat(subTotal) + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + DateTimeUtils.changeFormat(disc) + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + vendorPO.getCgst() + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + vendorPO.getSgst() + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + vendorPO.getIgst() + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + vendorPO.getSurcharge() + "</span> </h5>");
			buffer.append("<h5>	 Rs.<span>" + debit + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + credit + "</span> </h5>");
			buffer.append("<h5> Rs.<span>" + DateTimeUtils.changeFormat(netAmt) + "</span> </h5>");

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

	public String updateprocurement() throws Exception {

		Connection connection = null;
		try {
			String deleteids = request.getParameter("deleteids");

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			// deleting items
			if (deleteids != null) {
				for (String xid : deleteids.split(",")) {
					if (xid.equals("0")) {
						continue;
					}
					Procurement procurement = procurementDAO.getProcurement(xid, 0);
					int res = procurementDAO.deletePo(xid);
					if (res > 0) {
						String prodid = procurementDAO.getprodidfromPro(xid);
						inventoryProductDAO.updateStock(0, prodid);
						inventoryProductDAO.saveDeletedProcurement(xid, loginInfo.getUserId(),
								procurement.getQuantity(), procurement.getProduct_id(), procurement.getPurchase_price(),
								procurement.getProcurementid());
					}
				}
			}
			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getJobTitle().equals("Admin")) {
				location = request.getParameter("location");
				if (location == null) {
					location = request.getParameter("warehouse");
					if (location == null) {
						location = request.getParameter("iseditlocation");
						if (location == null) {
							location = "0";
						}
					}
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}

			String selectedid = request.getParameter("id");
			String disctype = request.getParameter("disctype");
			String paymode = request.getParameter("paymode");
			String netpay = request.getParameter("netpay");
			if (selectedid == null) {
				selectedid = "0";
			}
			if (selectedid.equals("0")) {
				selectedid = "0";
			}

			if (!selectedid.equals("0")) {
				String iseditlocation = request.getParameter("iseditlocation");
				if (iseditlocation != null) {
					if (!iseditlocation.equals("") || !iseditlocation.equals("0")) {
						location = iseditlocation;
					}
				}
			}

			String isfromlongpo = request.getParameter("isfromlongpo");

			String lastmodified = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String voucherno = "";
			int i = 0;
			String vendorid = "0";
			String procurementid = "0";
			String voucherdate = request.getParameter("voucherdate");
			String security_date = request.getParameter("security_date");
			String security_no = request.getParameter("security_no");
			String remark = request.getParameter("remark");

			String firstnetamout = procurementForm.getFirstnetamout();
			
			if (voucherdate != null) {
				if (voucherdate.equals("")) {
					voucherdate = lastmodified;
				} else {
					voucherdate = DateTimeUtils.getCommencingDate1(voucherdate);
				}
			} else {
				voucherdate = lastmodified;
			}

			String isdelivermemo = procurementForm.getIsdelivermemo();
			String delivermemodate = procurementForm.getDelivermemodate();
			String delivermemoid = procurementForm.getDelivermemoid();

			session.setAttribute("vendorid", null);
			int paraentpoid = 0;
			boolean pending = false;
			boolean islongpo = false;
			String procchildids = "0";
			int prodidscount = 1;
			boolean iseditprocurement = false;
			for (Product product : procurementForm.getProcurements()) {

				if (product == null) {
					continue;
				}
				if (product.getId() == 0) {
					continue;
				}
				prodidscount++;

				String expirydate3 ="";
				if(!DateTimeUtils.isNull(product.getExpiry_date()).equals("")){
					String expirydate = product.getExpiry_date();
					String[] data = expirydate.split("/");

					Calendar mycal = new GregorianCalendar(Integer.parseInt(data[1]), Integer.parseInt(data[0]) - 1, 1);
					int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
					expirydate3 = daysInMonth + "-" + data[0] + "-" + data[1];
				}
				
				/* product.setExpiry_date(expirydate); */
				product.setExpiry_date(expirydate3);
				// String catalogueid = product.getCatalogueid();
				Product master = inventoryProductDAO.getProduct(product.getProduct_id());
				String medicinetype = master.getSubcategory_id(); // MeDicineSub
																	// Category
				if (medicinetype == null) {
					medicinetype = "1";
				}
				if (medicinetype.equals("0") || medicinetype.equals("")) {
					medicinetype = "1";
				}
				if (i == 0) {
					voucherno = product.getVoucherno();
					if (voucherno != null) {

						if (voucherno.equals("")) {
							voucherno = request.getParameter("voucherno");
						}
					} else {
						voucherno = request.getParameter("voucherno");
					}
					if (voucherno == null) {
						voucherno = "";
					}
				}
				String freeqty = product.getFreeqty();
				if (freeqty != null) {
					if (freeqty.equals("")) {
						freeqty = "0";
					}
				} else {
					freeqty = "0";
				}
				double fqty = Double.parseDouble(freeqty);
				product.setVoucherno(voucherno);
				product.setLocation(location);
				String dmno=procurementForm.getDmno();
				product.setDmno(dmno);
				String actqty = product.getReceived_qty();
				boolean isstrip = inventoryProductDAO.isStrip(medicinetype);
				if (isstrip) {

					double pack = Double.parseDouble(product.getPack());
					double stock = pack * Double.parseDouble(product.getReceived_qty());
					product.setReceived_qty(Double.toString(stock));
					fqty=(pack) * fqty;
				}

				product.setFreeqty(String.valueOf(fqty));

				double tempVat = Double.parseDouble(product.getVat());

				Procurement procurement = procurementDAO.getProcurement(String.valueOf(product.getId()), 0);
				vendorid = procurement.getVendor_id();
				product.setVendor_id(vendorid);
				procurementid = procurement.getProcurementid();
				double tempurch = Double.parseDouble(product.getPurchase_price());

				double total = (tempurch) * Double.parseDouble(actqty);
				double vattot = total * tempVat / 100;
				// total=total+vattot;*/

				product.setVoucherdate(voucherdate);
				product.setTotal("" + total + "");

				String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				String userid = loginInfo.getUserId();
				product.setTime(time);
				product.setUserid(userid);
				String scheme_disc=product.getScheme();
				product.setScheme(scheme_disc);
				double discminus=0;
				//start for parihar
				if(loginInfo.isParihar()) {
				if(!scheme_disc.equals("0") || !product.getDiscount().equals("0")){	
                    double discamt=total*Double.parseDouble((product.getDiscount())) / 100;
					double schemeamt=total-(discamt);
					double schmeamtafterdis=0;
					if(!scheme_disc.equals("0")){
						schmeamtafterdis=schemeamt*(Double.parseDouble(scheme_disc)/100);
					}
					discminus=total-(discamt+schmeamtafterdis);
					discminus =(Math.round(discminus*100D)/100D);
					
				 }
			   }	
				product.setTotalschmedisc(Double.toString(discminus));
				//end for parihar
				/*
				 * String totalscmeamt=product.getTotalschmedisc(); String
				 * temp[]=procurementForm.getTotalschmedisc().split(","); totalscmeamt=temp[i];
				 */
				//////////////////////////////////////////////////////////
				int result = procurementDAO.updatePocurement(product);
				double stck=Double.parseDouble(master.getStock());
				double rece =Double.parseDouble(product.getReceived_qty());
				
				if (selectedid.equals("0")) {
					double nowstock =stck +rece + (fqty);
					product.setStock(Double.toString(nowstock));
				} else {
					/*
					 * int nowstock= Integer.parseInt(product.getReceived_qty())
					 * +fqty; if(nowstock<0){ nowstock=0; } int st=
					 * inventoryProductDAO.updateStock(nowstock,product.
					 * getProduct_id()); Product pmaster=
					 * inventoryProductDAO.getProduct(product.getProduct_id());
					 * int t= Integer.parseInt(pmaster.getStock()) +
					 * Integer.parseInt(product.getReceived_qty()) +fqty;
					 */
					// product.setStock(master.getStock());
					
					//commenting below beacuse stock not added properly
					/*
					 * String nowstock =(product.getReceived_qty()) + fqty;
					 * product.setStock(nowstock);
					 */
					double nowstock =Double.parseDouble(product.getReceived_qty()) + fqty;
					product.setStock(Double.toString(nowstock));
				}

				// int updatepo =
				// procurementDAO.updateParentProcurment(procurementid);

				//   02 Aug 2018 if edit done then it update inventory
				// product stock if it transfer
				if (product.getIsedit() != null) {
					if (product.getIsedit().equals("1")) {
						iseditprocurement = true;
						result = inventoryProductDAO.updateProductByProcurement(product, 1);

						//   12-09-2019 Edit procurement when stock transfer
						// or sale
						double previous_grn_qty = Double.parseDouble(procurement.getQuantity())+Double.parseDouble(master.getFreeqty());
						String previousstocknew =master.getStock();
						double totalavailqty = previous_grn_qty - Double.parseDouble(previousstocknew);
						double currentstocknew =Double.parseDouble(product.getStock());
						double finalstock = currentstocknew - totalavailqty;
						double updatetsock = inventoryProductDAO.updateStock(finalstock, product.getProduct_id());

						// Store in log of grn edit after of stock transfer
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						String grnupdateuserid = loginInfo.getUserId();
						String grn_pre_pack = master.getPack();
						String grn_new_pack = product.getPack();
						String grn_pre_freeqty = master.getFreeqty();
						String grn_new_freeqty = product.getFreeqty();
						String grn_pre_stock = master.getStock();
						double grn_new_stock = finalstock;
						String grn_pre_qty = procurement.getQuantity();
						String grn_new_qty = product.getReceived_qty();
						String grn_procurementid = procurement.getProcurementid();
						String grn_child_procid = String.valueOf(product.getId());

						int updateproclog = inventoryProductDAO.saveEditProcurementLog(datetime, grnupdateuserid,
								grn_pre_pack, grn_new_pack, grn_pre_freeqty, grn_new_freeqty, grn_pre_stock,
								grn_new_stock, grn_pre_qty, grn_new_qty, grn_procurementid, grn_child_procid);

						/*
						 * String date = lastmodified; int openingstock =
						 * pharmacyDAO.getOpeningStock(product.getProduct_id());
						 * boolean checkopningstockexist =
						 * pharmacyDAO.checkopeningstockexist(product.
						 * getProduct_id(),date); if(checkopningstockexist){
						 * Product openingproduct =
						 * pharmacyDAO.checkopeningstockexistData(product.
						 * getProduct_id(),date); int indentqty =
						 * Integer.parseInt(qty)+openingproduct.getPurchaseqty()
						 * ; int ress =
						 * pharmacyDAO.updateIndentQtyInOpening(openingproduct,
						 * indentqty); }
						 */

						// stock log

						int qtyinout = 0;
						String source = "Update Procurement";
						if (product.getStock() != "0") {
							if (product.getStock().equals("")) {
								product.setStock("0");
							}
							
						} else {
							product.setStock("0");
						}
						if (master.getStock() != "0") {
							if (master.getStock().equals("")) {
								master.setStock("0");
							}
						} else {
							master.setStock("0");
						}
						// int
						// currentstock=Integer.parseInt(product.getStock());
						double currentstock = finalstock;
						double previousstock =Double.parseDouble(master.getStock());
						double changeqty = 0.0;
						if (currentstock > previousstock) {
							changeqty = currentstock - previousstock;
						} else {
							changeqty = previousstock - currentstock;
						}
						int res = inventoryProductDAO.insertIntoProductLog(userid, datetime, procurement.getLocation(),
								qtyinout, product.getProduct_id(), master.getCatalogueid(), source, currentstock,
								previousstock, changeqty, procurementid, "0", 0, 0, 0, "0");

						//   27 OCT 2018 for update transfer product data
						// also
						String oldprodid = product.getProduct_id();
						ArrayList<String> directtransferlist = indentDAO.getDirectTransferProduct(oldprodid);
						for (String string : directtransferlist) {
							product.setProduct_id(string);
							int result1 = inventoryProductDAO.updateProductByProcurement(product, 1);
						}
						ArrayList<String> requesttransferlist = indentDAO.getRequestTransferProduct(oldprodid);
						for (String string : requesttransferlist) {
							product.setProduct_id(string);
							int result1 = inventoryProductDAO.updateProductByProcurement(product, 1);
						}
						product.setProduct_id(oldprodid);
					} else {
						result = inventoryProductDAO.updateProductByProcurement(product, 0);
						// stock log
						String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int qtyinout = 0;
						String source = "Update Procurement";
						if (product.getStock() != "0") {
						     if (product.getStock().equals("")) {
								product.setStock("0");
							}
						} else {
							product.setStock("0");
						}
						if (master.getStock() != "0") {
							if (master.getStock().equals("")) {
								master.setStock("0");
							}
						} else {
							master.setStock("0");
						}
						double currentstock = Double.parseDouble(product.getStock());
						double previousstock =Double.parseDouble(master.getStock());
						double changeqty = 0.0;
						if (currentstock > previousstock) {
							changeqty = currentstock - previousstock;
						} else {
							changeqty = previousstock - currentstock;
						}
						int res = inventoryProductDAO.insertIntoProductLog(userid, datetime, procurement.getLocation(),
								qtyinout, product.getProduct_id(), master.getCatalogueid(), source, currentstock,
								previousstock, changeqty, procurementid, "0", 0, 0, 0, "0");

						String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
						boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(product.getProduct_id(),
								date);
						if (!checkopningstockexist) {
							int r = pharmacyDAO.saveOpeningStock(product.getProduct_id(), date, previousstock, "0");
						}
					}
				} else {
					result = inventoryProductDAO.updateProductByProcurement(product, 0);
					// stock log
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int qtyinout = 0;
					String source = "Procurement";
					if (product.getStock() != null) {
						if (product.getStock().equals("")) {
							product.setStock("0");
						}
					} else {
						product.setStock("0");
					}
					if (master.getStock() != null) {
						if (master.getStock().equals("")) {
							master.setStock("0");
						}
					} else {
						master.setStock("0");
					}
					double currentstock = Double.parseDouble(product.getStock());
					double previousstock =Double.parseDouble(master.getStock());
					double changeqty = 0;
					if (currentstock > previousstock) {
						changeqty = currentstock - previousstock;
					} else {
						changeqty = previousstock - currentstock;
					}
					int res = inventoryProductDAO.insertIntoProductLog(userid, datetime, procurement.getLocation(),
							qtyinout, product.getProduct_id(), master.getCatalogueid(), source, currentstock,
							previousstock, changeqty, procurementid, "0", 0, 0, 0, "0");

					String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
					boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(product.getProduct_id(), date);
					if (!checkopningstockexist) {
						int r = pharmacyDAO.saveOpeningStock(product.getProduct_id(), date, previousstock, "0");
					}
				}

				master.setMedicine_shedule(product.getMedicine_shedule());
				// result = inventoryProductDAO.addTOMedicineMaster(master);

				// for 7Star update catalogue
				if(DateTimeUtils.isNull(product.getCatalogueid()).equals("")){
					product.setCatalogueid(master.getCatalogueid());
				}
				int d = inventoryProductDAO.updateCatalogueMRP(product);

				String newproduct = product.getNewproduct();
				if (!newproduct.equals("0")) {

					String str[] = newproduct.split("#");
					String prodid = str[0];
					String batch = str[1];
					String expiry = str[2];
					String qty = str[3];
					expiry = DateTimeUtils.getCommencingDate1(expiry);
					result = procurementDAO.addNewProduct(prodid, batch, expiry, qty, lastmodified, location);

					if (result > 0) {
						// add to procurement
						tempurch = Double.parseDouble(product.getPurchase_price());
						total = tempurch * Integer.parseInt(qty);
						vattot = total * tempVat / 100;
						total = total + vattot;
						product.setTotal("" + total + "");

						if (isstrip) {
							int pack = Integer.parseInt(master.getPack());
							int stock = pack * Integer.parseInt(qty);
							qty = String.valueOf(stock);
						}

						product.setQuantity((qty));
						product.setProcurementid(procurement.getProcurementid());
						product.setProduct_id(String.valueOf(result));
						result = procurementDAO.saveNewProcurement(product);
					}

				}

				//   04 Jun 2018 //ready to transfer status change
				int res = indentDAO.changeIndentRTTstatus(procurement);
				int res1 = procurementDAO.updateProcurmentGST(product.getId(), product.getVat());

				if (isfromlongpo != null) {
					if (isfromlongpo.equals("1")) {
						islongpo = true;
						if (product.getParentpoid() > 0) {
							if(product.getReqpoqty()==null || product.getReqpoqty().equals("")) {
								//RAHUL this line is added because reqpoqty getting null exception occured 
								product.setReqpoqty("0");
							}
							if (Double.parseDouble(product.getReceived_qty()) < Double.parseDouble(product.getReqpoqty())) {
								pending = true;
							} else {
								int res22 = procurementDAO.updatePoConfirmData(product.getParentpoid(),
										procurement.getCatalogueid());
							}
						}
					}
				}
				procchildids = procchildids + "," + procurement.getId();
				i++;
			}

			if (islongpo) {
				if (isfromlongpo != null) {
					if (isfromlongpo.equals("1")) {
						pending = false;
						for (Product product : procurementForm.getProcurements()) {
							if (product == null) {
								continue;
							}
							if (product.getId() == 0) {
								continue;
							}
							if (product.getParentpoid() > 0) {
								Procurement procurement = procurementDAO.getProcurement(String.valueOf(product.getId()),
										0);
								procurementid = procurement.getProcurementid();
								paraentpoid = product.getParentpoid();
								int recqty = procurementDAO.getRecivedQtyAgainstPO(product.getParentpoid(),
										procurement.getCatalogueid(), procchildids, procurementid);
								if (recqty < Double.parseDouble(product.getReqpoqty())) {
									pending = true;
								} else {
									int res22 = procurementDAO.updatePoConfirmData(product.getParentpoid(),
											procurement.getCatalogueid());
								}
							}
						}
					}
				}

				if (pending) {
					if (paraentpoid > 0) {
						int res11 = procurementDAO.updatePendingPoStatus(paraentpoid, 1);
						int res12 = procurementDAO.updateProc_ConditionStatus(procurementid);
						int res13 = procurementDAO.updateDeletePoPending(procchildids, procurementid);
						int res14 = procurementDAO.deletePoPendingNotDone(procurementid);
					}
				} else {
					if (paraentpoid > 0) {
						int parentreqcount = procurementDAO.getParentReqCount(paraentpoid);
						int completepos = procurementDAO.getCompletedPoCount(paraentpoid);
						if (parentreqcount > completepos) {
							int res11 = procurementDAO.updatePendingPoStatus(paraentpoid, 1);
							int res12 = procurementDAO.updateProc_ConditionStatus(procurementid);
						} else {
							int res11 = procurementDAO.updatePendingPoStatus(paraentpoid, 2);
							int res12 = procurementDAO.updateProc_ConditionStatus(procurementid);
						}
						int res13 = procurementDAO.updateDeletePoPending(procchildids, procurementid);
						int res14 = procurementDAO.deletePoPendingNotDone(procurementid);
					}
				}
			}
			
			//  06-07-2020 // for storing grn amount 
			int updategrnamount = procurementDAO.updateGRNEnteredAmount(procurementid,firstnetamout); 
			
			//   if it is DM then
			boolean flag = false;
			if (isdelivermemo != null) {
				if (isdelivermemo.equals("on")) {
					flag = true;
					String todaydate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int dmseq = procurementDAO.getDmSeq(location);
					int olddmseqno = procurementDAO.getOldDmSeqNo(procurementid);
					if (olddmseqno > 0) {
						dmseq = olddmseqno;
					} else {
						dmseq = dmseq + 1;
					}
					int updparent = procurementDAO.updateParentProcurment(delivermemodate, delivermemoid, todaydate,
							procurementid, dmseq);
				}
			}

			if (!flag) {
				if (!iseditprocurement) {
					boolean checkingflag = procurementDAO.checkingProIDSeq(procurementid);
					if (!checkingflag) {
						String todaydate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int seqno = procurementDAO.getProcurmentSeq(location);
						seqno = seqno + 1;
						int res = procurementDAO.insertProcSeqNo(location, seqno, loginInfo.getUserId(), procurementid,
								todaydate, "0", "0");
					}
				}
			}

			int d = procurementDAO.deleteVatAllocations(procurementid, voucherno);

			if (procurementForm.getVatallocations() != null) {

				for (Product product : procurementForm.getVatallocations()) {

					product.setProcurementid(procurementid);
					product.setVoucherno(voucherno);
					int r = procurementDAO.savePoVatAllocations(product);
				}

			}

			String vat = request.getParameter("vat");
			String cgst = request.getParameter("cgst");
			String sgst = request.getParameter("sgst");
			String igst = request.getParameter("igst");

			String discount = request.getParameter("discount");
			String schemeamt = request.getParameter("totalscheme");
			String surcharge = request.getParameter("surcharge");
			String debit = request.getParameter("debit");
			String credit = request.getParameter("credit");
			if (surcharge == null) {
				surcharge = "0";
			}
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			Procurement procurement = new Procurement();
			procurement.setCredit(credit);
			procurement.setDebit(debit);
			procurement.setVat(vat);
			procurement.setCgst(cgst);
			procurement.setIgst(igst);
			procurement.setSgst(sgst);
			procurement.setSurcharge(surcharge);
			procurement.setDiscount(discount);
			procurement.setVendor_id(vendorid);
			procurement.setVoucherno(voucherno);
			procurement.setProcurementid(procurementid);
			procurement.setDate(date);
			procurement.setRemark(remark);
			procurement.setSecurity_date(security_date);
			procurement.setSecurity_no(security_no);
			procurement.setUserid(loginInfo.getUserId());
			procurement.setDisctype(disctype);
			procurement.setPaymode(paymode);

			//   16 NOV 2018 check already entry entered for procurementid
			boolean flag1 = procurementDAO.checkVendorVatSaved(procurement.getProcurementid());

			if (!flag1) {
				int result = procurementDAO.addVendorAccountProcurement(procurement);
			} else {
				int result = procurementDAO.updateVendorAccountProcurement(procurement);
			}
			// commented because it create double entry
			/*
			 * if (selectedid.equals("0")) { int result =
			 * procurementDAO.addVendorAccountProcurement(procurement); } else {
			 * int result =
			 * procurementDAO.updateVendorAccountProcurement(procurement); }
			 */

			if (paymode != null) {
				if (paymode.equals("Cash")) {
					PoPaymenytDAO paymenytDAO = new JDBCPoPaymengtDAO(connection);
					procurement.setPaymentType("Cash");
					procurement.setPaymentDate(date);
					procurement.setPaymentAmount(netpay);
					if (netpay != null) {
						if (netpay.equals("")) {
							netpay = "0";
						}
					} else {
						netpay = "0";
					}
					double paymt = Double.parseDouble(netpay);
					int paymentids = paymenytDAO.saveParentPaymentData(vendorid, loginInfo.getUserId(), date, netpay,
							"0", "0", paymt, 0);
					int res = paymenytDAO.savePayment(procurement, paymentids);
					int test = paymenytDAO.updateProcurmnetPaymentStatus(procurement.getProcurementid());
				}
			}

			//   15-DEC-2018
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			int vendoridnew = procurementDAO.getVendoridFromProc(procurementid);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			boolean vendorpresentinledger = procurementDAO.checkVendorLedgerPresentStatus(vendoridnew);
			if (!vendorpresentinledger) {
				int saveid = procurementDAO.insertLedger(connection, vendoridnew, vendor.getName());
			}
			if (!flag) {
				//   20 OCT 2018 ledger work
				// ledger for credit invoice
				ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
				ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
				double debit1 = Double.parseDouble(netpay);
				String colmnname = "proc_invoiceid";

				if (Integer.parseInt(procurementid) > 0) {
					String locationname = pharmacyDAO.getLocationName(location);
					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
					String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0", "1", vendoridnew);

					// create purchase voucher
					int expenceparentid = createPurchaseVoucher(ledgerid, vendoridnew, connection, netpay);

					if (ledgerid != null) {
						if (ledgerid.equals("")) {
							ledgerid = "0";
						}
					}
					int ledgersaveid = chargesAccountProcessingDAO.getLedgerSheetId(ledgerid, procurementid, colmnname);
					if (ledgersaveid > 0) {
						// update debit in expences
						String colname = "debit";
						int res = expenManagementDAO.updateExpenceLedgerNew(ledgerid,
								DateTimeUtils.changeFormat(debit1), colname, procurementid, colmnname);

						// update credit in official ledger
						colname = "credit";
						res = expenManagementDAO.updateExpenceLedgerNew(ledgerid, DateTimeUtils.changeFormat(debit1),
								colname, procurementid, colmnname);
					} else {
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + debit1;
						String credit1 = "" + debit1 + "";
						String ldebit = "0";
						String product = "xxxxx";
						String partyid = "0";

						// int
						// vendoridnew=procurementDAO.getVendoridFromProc(procurementid);

						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit1, lbal,
								ledgerid, lcommencing, "0", 0, "0", procurementid, "0", "0", "0", vendoridnew, 0,
								location);
						int updateres = chargesAccountProcessingDAO.updateLedgerSheetExpenceID(saveledger,
								expenceparentid);
						// second effect
						lbal = 0;
						credit1 = "0";
						ldebit = "" + debit1 + "";
						product = "xxxxx";
						partyid = "0";
						lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit1, lbal,
								ledgerid, lcommencing, "0", 0, "0", procurementid, "0", "0", "0", vendoridnew, 0,
								location);
						updateres = chargesAccountProcessingDAO.updateLedgerSheetExpenceID(saveledger, expenceparentid);
						/*
						 * double lbal =0; String credit1 = "0"; String ldebit =
						 * "" + debit1 + ""; String product = "xxxxx"; String
						 * partyid = "0"; String lcommencing =
						 * DateTimeUtils.getUKCurrentDataTime(loginInfo.
						 * getTimeZone()); int saveledger =
						 * chargesAccountProcessingDAO.saveLedger(partyid,
						 * product, ldebit, credit1, lbal, ledgerid,
						 * lcommencing, "0",
						 * 0,"0",procurementid,"0","0","0",vendoridnew,0,
						 * location);
						 */
					}
				}

				// Discount ledger
				Double discount1 = Double.parseDouble(discount);
				if (discount1 > 0) {

					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("Discount");
					String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0", "1", 0);

					int ledgersaveid = chargesAccountProcessingDAO.getLedgerSheetId(ledgerid, procurementid, colmnname);
					if (ledgersaveid > 0) {
						// update debit in expences
						String colname = "debit";
						int res = expenManagementDAO.updateExpenceLedgerNew(ledgerid,
								DateTimeUtils.changeFormat(discount1), colname, procurementid, colmnname);

						// update credit in official ledger
						colname = "credit";
						res = expenManagementDAO.updateExpenceLedgerNew(ledgerid, DateTimeUtils.changeFormat(discount1),
								colname, procurementid, colmnname);
					} else {
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + discount1;
						String credit1 = "" + discount1 + "";
						String ldebit = "0";
						String product = "xxxxx";
						String partyid = "0";
						// int
						// vendoridnew=procurementDAO.getVendoridFromProc(procurementid);

						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit1, lbal,
								ledgerid, lcommencing, "0", 0, "0", procurementid, "0", "0", "0", vendoridnew, 0,
								location);
						// second effect
						lbal = 0;
						credit1 = "0";
						ldebit = "" + discount1 + "";
						product = "xxxxx";
						partyid = "0";
						lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit1, lbal,
								ledgerid, lcommencing, "0", 0, "0", procurementid, "0", "0", "0", vendoridnew, 0,
								location);
					}
				}

				// For GST ledger
				Double vat1 = Double.parseDouble(vat);
				if (vat1 > 0) {

					String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds("GST");
					String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0", "1", 0);

					int ledgersaveid = chargesAccountProcessingDAO.getLedgerSheetId(ledgerid, procurementid, colmnname);
					if (ledgersaveid > 0) {
						// update debit in expences
						String colname = "debit";
						int res = expenManagementDAO.updateExpenceLedgerNew(ledgerid, DateTimeUtils.changeFormat(vat1),
								colname, procurementid, colmnname);

						// update credit in official ledger
						colname = "credit";
						res = expenManagementDAO.updateExpenceLedgerNew(ledgerid, DateTimeUtils.changeFormat(vat1),
								colname, procurementid, colmnname);
					} else {
						double lbal = chargesAccountProcessingDAO.getLedgerBalance(ledgerid);
						lbal = lbal + vat1;
						String credit1 = "" + vat1 + "";
						String ldebit = "0";
						String product = "" + procurementid + "";
						String partyid = "0";
						// int
						// vendoridnew=procurementDAO.getVendoridFromProc(procurementid);
						String lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						int saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit1, lbal,
								ledgerid, lcommencing, "0", 0, "0", procurementid, "0", "0", "0", vendoridnew, 0,
								location);

						// second effect
						lbal = 0;
						credit = "0";
						ldebit = "" + vat1 + "";
						product = "xxxxx";
						partyid = "0";
						lcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						saveledger = chargesAccountProcessingDAO.saveLedger(partyid, product, ldebit, credit, lbal,
								ledgerid, lcommencing, "0", 0, "0", procurementid, "0", "0", "0", vendoridnew, 0,
								location);
					}

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "savepo";
	}

	private int createPurchaseVoucher(String ledgerid, int vendoridnew, Connection connection, String netpay) {
		int expenceparentid = 0;
		ExpenManagementDAO expenManagementDAO = new JDBCExpenceManagementDAO(connection);
		try {

			String uid = loginInfo.getUserId();
			String pcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			String column = "purno";
			int purno = expenManagementDAO.getExpenceMaxno(column);

			int parentid = expenManagementDAO.saveParentExpData(ledgerid, "Purchase", "0", "", pcommencing, uid, 0, 0,
					0, 0, purno);
			expenceparentid = parentid;
			Expence expence = new Expence();
			expence.setAmount(netpay);
			expence.setLastmodified(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()));
			expence.setCurrency("INR");
			expence.setUserid(uid);
			expence.setParantid("" + parentid + "");
			expence.setCredit("0");
			expence.setLedgerid(ledgerid);
			expence.setTransid("0");

			DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String datestr = dateFormat1.format(date).toString();
			expence.setCaldate(datestr);

			String vendorname = expenManagementDAO.getVendorName(vendoridnew);
			expence.setCategory(vendorname);

			int expenceid = expenManagementDAO.addPaymentVoucher(expence, parentid, "Purchase");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return expenceparentid;
	}

	public String getvouchertotal() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vouchers = request.getParameter("vouchers");
			double sumTotal = 0;
			for (String str : vouchers.split(",")) {

				int id = Integer.parseInt(str);
				if (id == 0) {
					continue;
				}

				Procurement procurement = procurementDAO.getProcurement(str, 0);
				sumTotal = sumTotal + Double.parseDouble(procurement.getTotal());

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(DateTimeUtils.changeFormat(sumTotal));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public ProcurementForm getModel() {
		// TODO Auto-generated method stub
		return procurementForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			// ArrayList<Master> locationListPharmacy =
			// pharmacyDAO.getAllLocation();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationListPharmacy = masterDAO.getAllLocation(null);
			procurementForm.setLocationListPharmacy(locationListPharmacy);

			String location = (String) session.getAttribute("location");
			procurementForm.setLocation(location);

			ArrayList<Vendor> vendorList = procurementDAO.getVendorList();
			procurementForm.setVendorList(vendorList);
			categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {

				categoryid = "1";
			}
			/*
			 * ArrayList<Product> productList=
			 * inventoryProductDAO.geProductList(categoryid);
			 * procurementForm.setProductList(productList);
			 */

			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String nowdate = dateFormat.format(date);
			procurementForm.setPaymentDate(nowdate);

			ArrayList<Product> brandnameList = inventoryProductDAO.getAllBrandListByCategory(categoryid);
			procurementForm.setBrandnameList(brandnameList);
			procurementForm.setPurchase_date(nowdate);
			procurementForm.setPaymentDate(nowdate);

			// cell list

			ArrayList<Master> cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
			procurementForm.setCellList(cellList);

			ArrayList<Master> medicineTypeList = inventoryProductDAO.getMedicineTypeList();
			procurementForm.setMedicineTypeList(medicineTypeList);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList();
			procurementForm.setWarehouseList(warehouseList);

			ArrayList<Product> categoryList = inventoryProductDAO.getAllCategories(null);
			ArrayList<Product> subcategoryList = inventoryProductDAO.getAllSubCategoryList(null);
			procurementForm.setCategoryList(categoryList);
			procurementForm.setSubcategoryList(subcategoryList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		// ArrayList<Vendor>vendorList

	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String purorder() throws Exception {
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
				if(breadcrumbs.getName().equals("GRN Without PO")){
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
				breadcrumbs.setName("GRN Without PO");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("purorderProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("GRN Without PO");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = dateFormat.format(calendar.getTime());

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			} else if (location.equals("")) {
				location = "0";
			}

			boolean haslocation = procurementDAO.hasLocationToUser(loginInfo.getClinicid());
			if (haslocation) {
				procurementForm.setHaslocation(1);
			} else {
				procurementForm.setHaslocation(0);
			}
			procurementForm.setWarehouse(location);
			procurementForm.setDate(date);
			ArrayList<Vendor> vendorList = procurementDAO.getVendorListBylocationWise(location);
			procurementForm.setVendorList(vendorList);
			session.removeAttribute("vendorid");

			String expiryDateAlert = procurementDAO.getExpiryAlertDateSetting(loginInfo.getClinicid());
			procurementForm.setExpiryDateAlert(expiryDateAlert);
			ArrayList<Master> cellList = new ArrayList<Master>();
			if (!location.equals("0")) {
				cellList = inventoryProductDAO.getcellList(location);
			} else {
				cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
			}
			procurementForm.setCellList(cellList);

			ArrayList<Master> genericnamelist = new ArrayList<Master>();
			if (loginInfo.isAuto_generic_name()) {
				genericnamelist = inventoryProductDAO.getGenericMasterList();
			}
			procurementForm.setGenericnamelist(genericnamelist);
			
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListNew(location);
			procurementForm.setWarehouseList(warehouseList);
			
			ArrayList<String> unitOfMeseurementList = inventoryProductDAO.getUnitOfMessurementList();
			procurementForm.setUnitOfMeseurementList(unitOfMeseurementList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "purorder";
	}

	public String reorder() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = dateFormat.format(calendar.getTime());

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			String procurementid = request.getParameter("procurementid");
			Procurement procurement = procurementDAO.getProcurementDataByProcurementID(procurementid);
			Vendor vendor = inventoryVendorDAO.getVendor(procurement.getVendor_id());
			procurementForm.setVendor(vendor.getName());
			ArrayList<Product> procurementReorderList = procurementDAO.getProcurementProductList(procurementid, 0);
			procurementForm.setVendor_id(procurement.getVendor_id());
			procurementForm.setProcurementReorderList(procurementReorderList);

			ArrayList<Product> productList = catalogueDAO.getAllProductList("0");
			procurementForm.setProductList(productList);
			boolean haslocation = procurementDAO.hasLocationToUser(loginInfo.getClinicid());
			if (haslocation) {
				procurementForm.setHaslocation(1);
			} else {
				procurementForm.setHaslocation(0);
			}
			procurementForm.setWarehouse(location);
			procurementForm.setDate(date);
			ArrayList<Vendor> vendorList = procurementDAO.getVendorListBylocationWise(location);
			procurementForm.setVendorList(vendorList);
			session.removeAttribute("vendorid");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "reorder";
	}

	public String savereorder() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			String security_date = request.getParameter("security_date");
			String security_no = request.getParameter("security_no");
			String remark = request.getParameter("remark");
			String newprocurementid = request.getParameter("procurementid");
			String voucherdate = request.getParameter("voucherdate");
			String voucherno = request.getParameter("voucherno");
			String disctype = request.getParameter("disctype");
			String paymode = request.getParameter("paymode");
			String netpay = request.getParameter("netpay");

			String vendorid = procurementForm.getVendor_id();
			String dateTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int procurementid = 0;
			if (newprocurementid != null) {
				if (newprocurementid.equals("0")) {
					procurementid = procurementDAO.saveParengtPrecurementData(vendorid, dateTime, 0, "0", 0,"","0");
				} else {
					procurementid = Integer.parseInt(newprocurementid);
				}
			} else {
				procurementid = procurementDAO.saveParengtPrecurementData(vendorid, dateTime, 0, "0", 0,"","0");
			}
			// delete if exist
			int res = procurementDAO.deleteProcurementofThis(procurementid);
			for (Product product : procurementForm.getProcurements()) {

				if (product == null) {
					continue;
				}
				if (product.getId() == 0) {
					continue;
				}

				String catalogueid = product.getCatalogueid();
				Product master = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
				Product pmaster = new Product();
				pmaster.setVendor_id(vendorid);
				pmaster.setProduct_name(master.getProduct_name());
				pmaster.setGenericname(master.getGenericname());
				pmaster.setMedicine_type(master.getMedicine_type());
				pmaster.setCatalogueid(catalogueid);
				pmaster.setPack(master.getPack());
				pmaster.setCategory_id(master.getCategory_id());
				pmaster.setMedicine_shedule(master.getMedicine_shedule());
				pmaster.setLocation(location);
				pmaster.setGrnno(0);
				pmaster.setProcurement(0);
				int pid = procurementDAO.saveNewProduct(pmaster);
				pmaster.setProduct_id(String.valueOf(pid));
				pmaster.setProcurementid(String.valueOf(procurementid));
				pmaster.setGrnno(0);
				voucherdate = DateTimeUtils.getCommencingDate1(voucherdate);
				pmaster.setVoucherdate(voucherdate);
				pmaster.setVoucherno(voucherno);
				int id = procurementDAO.saveNewProcurement(pmaster);
				product.setId(id);
				String expirydate = product.getExpiry_date();
				String[] data = expirydate.split("/");
				// String expirydate2 = data[1]+"-"+data[0]+"-"+"28";
				String expirydate3 = "28" + "-" + data[0] + "-" + data[1];
				product.setExpiry_date(expirydate3);
				String medicinetype = master.getSubcategory_id(); // MeDicineSub
																	// Category
				if (medicinetype == null) {
					medicinetype = "1";
				}
				if (medicinetype.equals("0") || medicinetype.equals("")) {
					medicinetype = "1";
				}

				String freeqty = product.getFreeqty();
				if (freeqty != null) {
					if (freeqty.equals("")) {
						freeqty = "0";
					}
				} else {
					freeqty = "0";
				}
				int fqty = Integer.parseInt(freeqty);
				product.setLocation(location);
				product.setVoucherdate(voucherdate);
				product.setVoucherno(voucherno);
				product.setProcurementid(String.valueOf(procurementid));
				product.setLocation(location);
				product.setGrnno(0);
				product.setProcurement(0);

				double actqty = Double.parseDouble(product.getReceived_qty());
				boolean isstrip = inventoryProductDAO.isStrip(medicinetype);
				if (isstrip) {

					int pack = Integer.parseInt(product.getPack());
					double stock = pack * Double.parseDouble(product.getReceived_qty());
					product.setReceived_qty(Double.toString(stock));
					fqty = pack * fqty;
				}

				product.setFreeqty(String.valueOf(fqty));

				double tempVat = Double.parseDouble(product.getVat());
				Procurement procurement = procurementDAO.getProcurement(String.valueOf(id), 0);
				vendorid = procurement.getVendor_id();
				product.setVendor_id(vendorid);
				double tempurch = Double.parseDouble(product.getPurchase_price());

				double total = tempurch * (actqty);
				double vattot = total * tempVat / 100;
				product.setTotal("" + total + "");

				String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				String userid = loginInfo.getUserId();
				product.setTime(time);
				product.setUserid(userid);
				int result = procurementDAO.updatePocurement(product);
				double nowstock =Double.parseDouble(product.getReceived_qty()) + fqty;
				product.setStock(Double.toString(nowstock));
				result = inventoryProductDAO.updateProductByProcurement(product, 0);
				master.setMedicine_shedule(product.getMedicine_shedule());
				// result = inventoryProductDAO.addTOMedicineMaster(master);

			}
			int d = procurementDAO.deleteVatAllocations(String.valueOf(procurementid), voucherno);

			if (procurementForm.getVatallocations() != null) {

				for (Product product : procurementForm.getVatallocations()) {

					product.setProcurementid(String.valueOf(procurementid));
					product.setVoucherno(voucherno);
					int r = procurementDAO.savePoVatAllocations(product);
				}
			}

			String vat = request.getParameter("vat");
			String cgst = request.getParameter("cgst");
			String sgst = request.getParameter("sgst");
			String igst = request.getParameter("igst");

			String discount = request.getParameter("discount");
			String surcharge = request.getParameter("surcharge");
			String debit = request.getParameter("debit");
			String credit = request.getParameter("credit");
			if (surcharge == null) {
				surcharge = "0";
			}
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			Procurement procurement = new Procurement();
			procurement.setCredit(credit);
			procurement.setDebit(debit);
			procurement.setVat(vat);
			procurement.setCgst(cgst);
			procurement.setIgst(igst);
			procurement.setSgst(sgst);
			procurement.setSurcharge(surcharge);
			procurement.setDiscount(discount);
			procurement.setVendor_id(vendorid);
			procurement.setVoucherno(voucherno);
			procurement.setProcurementid(String.valueOf(procurementid));
			procurement.setDate(date);
			procurement.setRemark(remark);
			procurement.setSecurity_date(security_date);
			procurement.setSecurity_no(security_no);
			procurement.setUserid(loginInfo.getUserId());
			procurement.setDisctype(disctype);
			procurement.setPaymode(paymode);

			int result = procurementDAO.addVendorAccountProcurement(procurement);

			if (paymode != null) {
				if (paymode.equals("Cash")) {
					PoPaymenytDAO paymenytDAO = new JDBCPoPaymengtDAO(connection);
					procurement.setPaymentType("Cash");
					procurement.setPaymentDate(date);
					procurement.setPaymentAmount(netpay);
					if (netpay != null) {
						if (netpay.equals("")) {
							netpay = "0";
						}
					} else {
						netpay = "0";
					}
					double paymt = Double.parseDouble(netpay);
					int paymentids = paymenytDAO.saveParentPaymentData(vendorid, loginInfo.getUserId(), date, netpay,
							"0", "0", paymt, 0);
					res = paymenytDAO.savePayment(procurement, paymentids);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "savepo";
	}

	public String addtempproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String count = request.getParameter("count");
			String pid = request.getParameter("pid");
			String expiry = request.getParameter("expiry");
			String batch = request.getParameter("batch");
			String qty = request.getParameter("qty");

			Product product = inventoryProductDAO.getProduct(pid);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<td>" + product.getProduct_name() + "</td>");
			buffer.append("<td>" + expiry + "</td>");
			buffer.append("<td>" + batch + "</td>");
			buffer.append("<td>" + qty + "</td>");

			buffer.append("~");
			String data = pid + "#" + batch + "#" + expiry + "#" + qty;
			buffer.append(data);

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

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			String vendorid = request.getParameter("vendorid");
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			ArrayList<Product> productList = inventoryVendorDAO.getVendorProducts(vendorid);

			double netdebittoal = procurementDAO.getTotalDebitofVendor(vendorid);
			double netcredittotal = procurementDAO.getTotalCreditofvendor(vendorid);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<label>Select Product</label>");
			buffer.append(
					"<select name='product_id' id='product_id' onchange='getprodData(this.value)' style='width:100%' class='form-control chosen'>");
			buffer.append("<option value='0'>Select Product Name</option>");
			for (Product product : productList) {

				buffer.append("<option value='" + product.getId() + "'>" + product.getProduct_name() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("~");
			buffer.append(DateTimeUtils.changeFormat(netdebittoal));
			buffer.append("~");
			buffer.append(DateTimeUtils.changeFormat(netcredittotal));
			buffer.append("~" + vendor.getState());

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

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String catalogueid = request.getParameter("catalogueid");
			String repeat = request.getParameter("repeat");
			int count = Integer.parseInt(request.getParameter("count"));
			String procid = request.getParameter("procurementid");
			int procurementid = 0;
			if (procid == null) {
				procurementid = 0;
			} else if (procid.equals("")) {
				procurementid = 0;
			} else {
				procurementid = Integer.parseInt(procid);
			}

			int grnno = 0;
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = request.getParameter("warehouse");
				if (location == null) {
					location = "0";
				}
				/*
				 * if(location.equals("1")){ location="32"; }
				 * if(location.equals("2")){ location="36"; }
				 */
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			//   23 June 2018
			/*
			 * When Click on Add (in Without PO)-> addnewproduct()
			 * [ProcurmnetAction.java] Its create Procurment Id which is used in
			 * all table like
			 * inventory_procurement,inventory_po_vat_allocations,
			 * inventory_vendor_procurement as procurmentid
			 */
			if (procurementid == 0) {

				procurementid = procurementDAO.saveParengtPrecurementData(vendorid, date, 0, "0", 0,"","0");
			}
			StringBuffer buffer = new StringBuffer();

			// boolean exists=
			// procurementDAO.isThisProductExist(String.valueOf(procurementid),
			// catalogueid);

			ArrayList<Master> mfglist = new ArrayList<Master>();
			if (loginInfo.isAuto_generic_name()) {
				mfglist = inventoryProductDAO.getMFGMasterList();
			}

			Product master = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
			double stock = inventoryProductDAO.getStockAvailableNew(catalogueid, location);
			String medicine_shedule = master.getMedicine_shedule();
			Product product = new Product();
			product.setVendor_id(vendorid);
			product.setProduct_name(master.getProduct_name());
			product.setGenericname(master.getGenericname());
			product.setMedicine_type(master.getMedicine_type());
			product.setCatalogueid(catalogueid);
			product.setPack(master.getPack());
			product.setCategory_id(master.getCategory_id());
			product.setMedicine_shedule(master.getMedicine_shedule());
			product.setLocation(location);
			product.setGrnno(grnno);
			String pack = master.getPack();
			product.setProcurement(1);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			String fullname = vendor.getName();
			int pid = procurementDAO.saveNewProduct(product);
			int globlestatus = inventoryProductDAO.updateGlobalProductId(pid, pid);
			product.setProcurementid(String.valueOf(procurementid));
			product.setGrnno(grnno);
			int id = procurementDAO.saveNewProcurement(product);
			ArrayList<Master> cellList = new ArrayList<Master>();
			ArrayList<Master> cellList1 = new ArrayList<Master>();
			if (!location.equals("0")) {
				cellList = inventoryProductDAO.getcellList(location);
				//cellList1 = inventoryProductDAO.getcellList(location);
			} else {
				cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				//cellList1 = inventoryProductDAO.getcellList("32,33,34,35,36,105");
			}

			Product productshelf = inventoryProductDAO.getPreviousShelfId(catalogueid,location);
			int previouscellid = productshelf.getPreviouscellid();
			int previousseccellid = productshelf.getPreviousseccellid();
			String shedule = "Regular";
			if (medicine_shedule == null) {
				shedule = "Regular";
				medicine_shedule = "0";
			} else if (medicine_shedule.equals("0")) {
				shedule = "Regular";
			} else if (medicine_shedule.equals("1")) {
				shedule = "Narcotics";
			} else if (medicine_shedule.equals("2")) {
				shedule = "H1";
			} else {
				shedule = "Regular";
				medicine_shedule = "0";
			}
			buffer.append("<td>" + (count + 1) + "</td> <input type='hidden' value='" + count
					+ "' class='dclass'  /> <input type='hidden' value='" + grnno + "'  name='procurements[" + count
					+ "].grnno'/>  <input type='hidden' value='" + id + "' name='procurements[" + count + "].id' />");
			buffer.append("<td><a href='#' onclick='viewallsupplier(" + pid + ")' >" + master.getPro_code() + " - "
					+ master.getProduct_name() + "</a> <input type='hidden' value='" + pid + "' name='procurements["
					+ count + "].product_id' /> <input type='hidden' value='" + catalogueid + "' name='procurements["
					+ count + "].catalogueid' /> <br><small style='font-size: 11px;'>(" + pack + " / " + shedule
					+ " (Stock- " + stock + ") <input type='hidden' value='" + medicine_shedule
					+ "' name='procurements[" + count + "].medicine_shedule' id='medicine_shedule" + count
					+ "'  />) </small>");
			buffer.append("<input type='text' name='procurements[" + count + "].barcode' id='barcode" + count
					+ "' placeholder='Barcode' />");
			buffer.append("<input type='hidden' id='minorder" + count + "' value='" + master.getMinorder() + "' />");
			buffer.append("<input type='hidden' id='maxorder" + count + "' value='" + master.getMaxorder() + "' />");
			buffer.append("</td>");
			buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' value='" + pack
					+ "' onkeyup='calSalPer()' onchange='calSalPer()' class='form-control' name='procurements[" + count + "].pack' id='pack"
					+ count + "'  /></td>");

			/*
			 * //  29 August 2018 if(master.getPro_code()!=null){
			 * if(master.getPro_code().equals("")){ buffer.
			 * append("<td><input type='text' onchange='checkProductCodeExist("
			 * +count+",this.value)' value='" + master.getPro_code() +
			 * "' class='form-control' name='procurements[" + count +
			 * "].pro_code' id='pro_code" + count+ "' /></td>"); }else{ buffer.
			 * append("<td><input type='text' onchange='checkProductCodeExist("
			 * +count+",this.value)' value='" + master.getPro_code() +
			 * "' class='form-control' readonly='readonly' name='procurements["
			 * + count + "].pro_code' id='pro_code" + count+ "' /></td>");
			 * 
			 * } }else{ buffer.append("<td><input type='text' value='" +
			 * master.getPro_code() + "' onchange='checkProductCodeExist("
			 * +count+",this.value)' class='form-control' name='procurements[" +
			 * count + "].pro_code' id='pro_code" + count+ "' /></td>");
			 * 
			 * }
			 */

			buffer.append("<td><input type='number' style='margin: 0;padding: 0;outline: 0;' maxlength='8' value='"
					+ DateTimeUtils.isNull(master.getHsnno()) + "' class='form-control' name='procurements[" + count + "].hsnno' id='hsnno"
					+ count + "' /></td>");

			if (loginInfo.isAuto_generic_name()) {
				buffer.append("<td>");
				buffer.append("<select class='form-control' style='margin: 0;padding: 0;outline: 0;' id='mfg" + count
						+ "' name='procurements[" + count + "].mfg' >");
				buffer.append("<option value=''>MFG</option>");
				for (Master master2 : mfglist) {
					buffer.append("<option value='" + master2.getName() + "'>" + master2.getName() + "</option>");
				}
				buffer.append("</td>");
			} else {
				buffer.append(
						"<td><input type='text' style='margin: 0;padding: 0;outline: 0;' class='form-control' name='procurements["
								+ count + "].mfg' value='" + DateTimeUtils.isNull(master.getMfg()) + "' id='mfg" + count
								+ "' required='required'></td>");
			}

			buffer.append(
					"<td class=''><input type='number' style='margin: 0;padding: 0;outline: 0;width:57px;' onkeyup='calSalPer()' onchange='calSalPer()' class='form-control' id='mrp"
							+ count + "' value='" + master.getMrp() + "'  name='procurements[" + count
							+ "].mrp' required='required'></td>");
			buffer.append(
					"<td><input type='number' style='margin: 0;padding: 0;outline: 0;' class='form-control' id='sale_price"
							+ count + "' value='" + master.getSale_price() + "' name='procurements[" + count
							+ "].sale_price' required='required'></td>");
			buffer.append("<td>");
			// buffer.append("<input type='text' value='0' class='form-control
			// hidden' onkeyup='calVatTotal()' id='vat"+count+"'
			// name='procurements["+count+"].vat' required='required'>");
			buffer.append("<select class='form-control' style='margin: 0;padding: 0;outline: 0;width:53px;' id='vat" + count
					+ "' onchange='calVatTotal()' name='procurements[" + count + "].vat' >");

			if (master.getVat() == null) {
				master.setVat("0");
			} else if (master.getVat().equals("")) {
				master.setVat("0");
			}

			String value = master.getVat();

			int tvat = Integer.parseInt(master.getVat());
			buffer.append("<option value='GST'>GST</option>");
			if (tvat == 0) {
				buffer.append("<option selected='selected' value='0'>0%</option>");
			} else {
				buffer.append("<option value='0'>0%</option>");
			}
			if (tvat == 5) {
				buffer.append("<option selected='selected' value='5'>5%</option>");
			} else {
				buffer.append("<option value='5'>5%</option>");
			}
			if (tvat == 12) {
				buffer.append("<option selected='selected' value='12'>12%</option>");
			} else {
				buffer.append("<option value='12'>12%</option>");
			}
			if (tvat == 18) {
				buffer.append("<option value='18' selected='selected'>18%</option>");
			} else {
				buffer.append("<option value='18'>18%</option>");
			}
			if (tvat == 28) {
				buffer.append("<option selected='selected' value='28'>28%</option>");
			} else {
				buffer.append("<option value='28'>28%</option>");
			}

			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append(
					"<td><input type='number' style='margin: 0;padding: 0;outline: 0;width:60px;' class='form-control' onkeyup='calTotalAmt()' value='"
							+ master.getPurchase_price() + "' onchange='calTotalAmt()'  id='purchase_price" + count + "' name='procurements["
							+ count + "].purchase_price' required='required'></td>");
			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><input type='text' style='margin: 0;padding: 0;outline: 0;' class='form-control search-text' name='procurements["
							+ count + "].batch_no' id='batch_no" + count + "' /></td>");
			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><input  style='margin: 0;padding: 0;outline: 0;' type='text' class='form-control' name='procurements["
							+ count + "].expiry_date' id='expiry_date" + count
							+ "' required='required' placeholder='MM/YYYY' onchange='checkExpiryInfo(this.value)' ></td>");

			/*
			 * buffer.append(
			 * "<td style='background-color: rgba(210, 105, 30, 0.11);'><input type='text' class='form-control' name='procurements["
			 * + count + "].expiry_date' id='expiry_date" + count +
			 * "' required='required' placeholder='DD-MM-YYYY' ></td>");
			 */
			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' onchange='calTotalAmt()' id='received_qty"
							+ count + "'  name='procurements[" + count + "].received_qty' required='required'></td>");

			//   26 June 2018
			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><input style='margin: 0;padding: 0;outline: 0;width:40px;' type='number' class='form-control' readonly='readonly' id='newreceived_qty"
							+ count + "'  name='procurements[" + count
							+ "].newreceived_qty' style='background-color: #ccc;' required='required'></td>");

			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><input style='margin: 0;padding: 0;outline: 0;width:40px;'  type='number' value='0' class='form-control' id='discount"
							+ count + "' onkeyup='calTotalAmt()'  name='procurements[" + count + "].discount' ></td>");
			if(loginInfo.isParihar()) {
				buffer.append(
						"<td style='background-color: rgba(210, 105, 30, 0.11);'><input style='margin: 0;padding: 0;outline: 0;'  type='number' value='0' class='form-control' id='scheme"
								+ count + "' onkeyup='calTotalAmt()'  name='procurements[" + count + "].scheme' ></td>");
			}else {
				buffer.append(
						"<td class='hidden' style='background-color: rgba(210, 105, 30, 0.11);'><input style='margin: 0;padding: 0;outline: 0;'  type='number' value='0' class='form-control' id='scheme"
								+ count + "' disabled='true' onkeyup='calTotalAmt()'  name='procurements[" + count + "].scheme' ></td>");
			}
			
			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' name='procurements["
							+ count + "].freeqty' id='freeqty" + count + "' value='0' required='required'></td>");
		if(loginInfo.isParihar()) {	
			buffer.append(
					"<td class='hidden' style='background-color: rgba(210, 105, 30, 0.11);'><select style='margin: 0;padding: 0;outline: 0;' class='form-control' name='procurements["
							+ count + "].shelf'>");
		}else {
			buffer.append(
					"<td style='background-color: rgba(210, 105, 30, 0.11);'><select style='margin: 0;padding: 0;outline: 0;' class='form-control' name='procurements["
							+ count + "].shelf'>");
		}
			for (Master master2 : cellList) {
				if (previouscellid == master2.getId()) {
					buffer.append("<option value='" + master2.getName() + "' selected='selected'>" + master2.getName()
							+ "</option>");
				} else {
					buffer.append("<option value='" + master2.getName() + "'>" + master2.getName() + "</option>");
				}
			}
			buffer.append("</select>");
			
			buffer.append("<select style='margin: 0;padding: 0;outline: 0;' class='form-control' name='procurements["+ count + "].secondary_shelf'>");
			for (Master master2 : cellList) {
				if (previousseccellid == master2.getId()) {
					buffer.append("<option value='" + master2.getName() + "' selected='selected'>" + master2.getName()+ "</option>");
				} else {
					buffer.append("<option value='" + master2.getName() + "'>" + master2.getName() + "</option>");
				}
			}
			buffer.append("</select>");
			
			buffer.append("</td>");
			buffer.append(
					"<td class='hidden'><i class='fa fa-plus-circle' aria-hidden='true' title='Add Batch' onclick='opennewBatchProduct("
							+ pid + "," + count
							+ ")' style='color:#6699CC;cursor:pointer;'></i> <input type='hidden' id='newproduct"
							+ count + "' value='0' name='procurements[" + count + "].newproduct'> </td>");
			buffer.append(
					"<td>Rs.<span style='margin: 0;padding: 0;outline: 0;' id='amount" + count + "'></span></td>");
			
			
			if(loginInfo.isParihar()) {
				buffer.append(
						"<td>Rs.<span class='cdschme' style='margin: 0;padding: 0;outline: 0;' id='disamt" + count + "'></span></td>");

				
			}else {
				buffer.append(
						"<td class='hidden'>Rs.<span class='cdschme' style='margin: 0;padding: 0;outline: 0;' id='disamt" + count + "'></span></td>");

			}
			if(loginInfo.isParihar()) {
				buffer.append(
						"<td>Rs.<span style='margin: 0;padding: 0;outline: 0;' id='discountamt" + count + "'></span></td>");
				buffer.append("<td hidden><input type='hidden' name='totalschmedisc' id='totalschmedisc" + count + "'></td>");

				
			}else {
				buffer.append(
						"<td class='hidden'>Rs.<span style='margin: 0;padding: 0;outline: 0;' id='discountamt" + count + "'></span></td>");
				buffer.append("<td class='hidden'><input type='hidden' name='totalschmedisc' id='totalschmedisc" + count + "'></td>");

			}
			
			buffer.append("<td>Rs.<span style='margin: 0;padding: 0;outline: 0;' id='gstcalamount" + count
					+ "'></span></td>");
			buffer.append(
					"<td>Rs.<span style='margin: 0;padding: 0;outline: 0;' id='netamount" + count + "'></span></td>");
			buffer.append(
					"<td><i class='fa fa-times' aria-hidden='true' title='Delete Row' onclick=deleteRow(this) style='color:#d9534f;cursor:pointer;'></i> </td>");
            buffer.append("<td class='hidden'><span id='qun" + count +"'></span></td>");
            buffer.append("~" + fullname + "~" + procurementid);
			
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

	public String calnetvat() {

		try {

			String totalvat = request.getParameter("totalvat");
			String totalamt = request.getParameter("totalamt");
			String totaldiscrate = request.getParameter("totaldiscrate");
			HashSet<String> vatList = new HashSet<String>();

			for (String vat : totalvat.split(",")) {

				if (vat.equals("")) {
					continue;
				}

				vatList.add(vat);
			}
			StringBuffer buffer = new StringBuffer();
			int i = 0;
			//double skinrateot = 0;
			double schemeafterdisc=0;
			for (String vatRate : vatList) {
				double vatPer = Double.parseDouble(vatRate);
				buffer.append("<tr>");
				buffer.append("<td><span id='vatrate" + i + "'>" + vatRate
						+ "</span>%</td><input type='hidden' id='vatrateAmt" + i + "' name='vatallocations[" + i
						+ "].vatrate' value='" + vatRate + "' /> ");

				double totAmt = 0;

				for (String amt : totalamt.split(",")) {

					if (amt.equals("0")) {
						continue;
					}
					String[] vatAmtRate = amt.split("~");
					if (vatAmtRate[1].equals(vatRate)) {

						totAmt = totAmt + Double.parseDouble(vatAmtRate[0]);
					}

				}
				double discotot = 0;
				double schemerateot = 0;
			if(!loginInfo.isParihar()) {
				   for (String disc : totaldiscrate.split(",")) {
					if (disc.equals("0")) {
						continue;
					}

					String[] discAmtRate = disc.split("~");
					if (discAmtRate[1].equals(vatRate)) {

						double tempamt = Double.parseDouble(discAmtRate[0]) * Double.parseDouble(discAmtRate[2]) / 100;
						discotot = discotot + tempamt;
					}

				}
			}else {
				//for parihar scheme discount code
				for (String disc : totaldiscrate.split(",")) {
					 
					if (disc.equals("0")) {
						continue;
					}

					String[] discAmtRate = disc.split("~");
					if (discAmtRate[1].equals(vatRate)) {

						double tempamt = Double.parseDouble(discAmtRate[0]) * Double.parseDouble(discAmtRate[2]) / 100;
						double cdamt=Double.parseDouble(discAmtRate[0])-tempamt;
						double schemeamt=cdamt* Double.parseDouble(discAmtRate[3]) / 100;
						double cdamtscshmeamt=tempamt+schemeamt;
						discotot=discotot+cdamtscshmeamt;
						
					}

				}
			}
				double tAxAmt = totAmt * vatPer / 100;
				double discMinus = totAmt - (discotot+schemerateot);
				buffer.append("<td id='totalVatAmt" + i + "' style='text-align:right;'>" + DateTimeUtils.changeFormat(totAmt)
						+ " </td> <input type='hidden' value='" + DateTimeUtils.changeFormat(totAmt)
						+ "' name='vatallocations[" + i + "].totalamt' />");
				buffer.append("<td style='text-align:right;'><input type='number' id='vatgiven" + i
						+ "' class='form-control' name='vatallocations[" + i + "].taxable' value='"
						+ DateTimeUtils.changeFormat(discMinus) + "'  onchange='calVatDiscount()'  /></td>");
				buffer.append("<td style='text-align:right;' id='discvat" + i + "'>" + DateTimeUtils.changeFormat(discotot)
						+ "</td> <input type='hidden' id='discvatVal" + i + "' value='"
						+ DateTimeUtils.changeFormat(discotot) + "' name='vatallocations[" + i + "].discvat' /> ");
				buffer.append("<td style='text-align:right;' id='taxAmt" + i + "'>" + DateTimeUtils.changeFormat(tAxAmt)
						+ "</td><input type='hidden' id='taxAmtVal" + i + "' name='vatallocations[" + i
						+ "].taxamt' /> ");
				
				if(loginInfo.isParihar()) {
					buffer.append("<td class='hidden' style='text-align:right;' id='schemeAmt" + i + "'>" + DateTimeUtils.changeFormat(schemerateot)
					+ "</td><input type='hidden' id='schemeAmtVal" + i + "' name='scheme[" + i
					+ "].schemeAmt' /> ");
				}else {
					buffer.append("<td style='text-align:right;' class='hidden' id='schemeAmt" + i + "'>" + DateTimeUtils.changeFormat(schemerateot)
				    + "</td><input type='hidden' id='schemeAmtVal" + i + "' name='scheme[" + i
				    + "].schemeAmt' /> ");
				
				}
				buffer.append("</tr>");

				i++;
			}
			buffer.append("<input type='hidden' id='vatcount' value='" + vatList.size() + "' />");
			//buffer.append("<input type='hidden' name='totalscheme' id='totalscheme' value='" + skinrateot + "' />");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public String vendorname() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String id = request.getParameter("vendorid");
			Vendor vendor = inventoryVendorDAO.getVendor(id);

			String fulname = vendor.getName();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(fulname);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String saveproductajax() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String vendorid = request.getParameter("vendorid");
			String genericname = request.getParameter("genericname");
			String prod_name = request.getParameter("prod_name");
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String location = "0";
			if (loginInfo.getUserType() == 2) {
				location = request.getParameter("location");
				if (location == null) {
					location = "0";
				}
			} else {
				location = (String) session.getAttribute("location");
				if (location == null) {
					location = "0";
				}
			}
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			Product product = new Product();
			product.setGenericname(genericname);
			product.setProduct_name(prod_name);
			product.setVendor_id(vendorid);
			product.setLastmodified(date);
			product.setExpiry_date(date);
			product.setCategory_id("2");
			product.setLocation(location);

			int pid = procurementDAO.saveNewProduct(product);
			int result = inventoryVendorDAO.updateProductByVendor(vendorid, String.valueOf(pid));

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + pid + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String checkvoucher() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			String voucher = request.getParameter("voucher");
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			boolean result = procurementDAO.isVoucherExist(voucher);
			String t = "0";
			if (result) {
				t = "1";
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + t + "");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String editpo() throws Exception {

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
				if(breadcrumbs.getName().equals("EDIT PO")){
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
				breadcrumbs.setName("EDIT PO");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("editpoProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("EDIT PO");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");

			Procurement procurement = procurementDAO.getProcurement(id, 0);
			Vendor vendor = vendorDAO.getVendor(procurement.getVendor_id());
			ArrayList<Product> productList = procurementDAO.getProcurementProductList(procurement.getProcurementid(),
					0);
			
			procurementForm.setProductList(productList);
			int size = productList.size();
			if (size > 0) {
				String iseditlocation = productList.get(size - 1).getLocation();
				procurementForm.setIseditlocation(iseditlocation);
				int isinadjustment = productList.get(size - 1).getIsinadjustment();
				if(isinadjustment==1){
					return "isinadjusterrorpage";
				}
			} else {
				procurementForm.setIseditlocation("0");
			}

			procurementForm.setName(vendor.getName());
			procurementForm.setVendor(procurement.getVendor_id());
			procurementForm.setVoucherno(procurement.getVoucherno());
			procurementForm.setVoucherdate(DateTimeUtils.getCommencingDate1(procurement.getVoucherdate()));

			ArrayList<Product> vendorProductList = vendorDAO.getVendorProducts(procurement.getVendor_id());
			procurementForm.setVendorProductList(vendorProductList);
			ArrayList<Product> vatAllocationList = procurementDAO.getVatAllocationList(procurement.getProcurementid(),
					procurement.getVoucherno());
			procurementForm.setVatAllocationList(vatAllocationList);

			procurementForm.setId(Integer.parseInt(id));

			Product vendorPO = procurementDAO.getProcAccountDetails(procurement.getProcurementid());
			procurementForm.setSurcharge(vendorPO.getSurcharge());
			procurementForm.setRemark(vendorPO.getRemark());
			procurementForm.setSecurity_date(vendorPO.getSecurity_date());
			procurementForm.setSecurity_no(vendorPO.getSecurity_no());
			procurementForm.setDiscount(vendorPO.getDiscvat());
			procurementForm.setDisctype(vendorPO.getDisctype());
			procurementForm.setDebit(vendorPO.getDebit());
			procurementForm.setCredit(vendorPO.getCredit());
			procurementForm.setSurcharge(vendorPO.getSurcharge());
			procurementForm.setProcurementid(procurement.getProcurementid());

			Procurement procurement2 = procurementDAO.getParentProcurmentData(procurement.getProcurementid());
			procurementForm.setIsdelivermemo(procurement2.getIsdelivermemo());
			procurementForm.setDelivermemoid(procurement2.getDelivermemoid());
			procurementForm.setDelivermemodate(procurement2.getDelivermemodate());
			procurementForm.setFirstnetamout(procurement2.getFirstnetamout());
			ArrayList<Master> mfglist = new ArrayList<Master>();
			if (loginInfo.isAuto_generic_name()) {
				mfglist = inventoryProductDAO.getMFGMasterList();
			}
			procurementForm.setMfglist(mfglist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "editpurorder";
	}

	public String returntosupplier() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryVendorDAO vendorDAO = new JDBCInventoryVendorDAO(connection);
			String voucherno = request.getParameter("voucherno");
			ArrayList<Product> productList = new ArrayList<Product>();
			String alldata = request.getParameter("alldata");

			if (alldata != null) {

				for (String str : alldata.split(",")) {

					if (str.equals("0")) {
						continue;
					}
					String dfl[] = str.split("-");
					Product product = procurementDAO.getProcProdDetails(dfl[0], dfl[1]);
					voucherno = product.getVoucherno();
					productList.add(product);
				}

			}

			Procurement procurement = procurementDAO.getProcurementByVoucher(voucherno);
			Vendor vendor = vendorDAO.getVendor(procurement.getVendor_id());
			procurementForm.setProductList(productList);
			procurementForm.setVendor(vendor.getName());

			procurementForm.setVoucherno(procurement.getVoucherno());
			procurementForm.setVendor_id(procurement.getVendor_id());
			procurementForm.setVoucherdate(DateTimeUtils.getCommencingDate1(procurement.getVoucherdate()));

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());
			procurementForm.setDate(date);
			procurementForm.setId(procurement.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "returnpurorder";
	}

	public String returnstock() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			ArrayList<Product> productList = new ArrayList<Product>();
			String vendorid = procurementForm.getVendor_id();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());

			double subtotal = 0;
			double nettotal = 0;
			for (Product product : procurementForm.getProcurements()) {

				double t = Integer.parseInt(product.getPurchase_price()) *Double.parseDouble(product.getReceived_qty());
				subtotal = subtotal + t;
				int res = procurementDAO.returnToSupplier(product);
			}

			double vat = Double.parseDouble(procurementForm.getVat());
			double disc = Double.parseDouble(procurementForm.getDiscount());
			nettotal = (subtotal + vat) - disc;

			Product product = procurementDAO.getVendorReturnAccountData(vendorid);
			if (product == null) {

				int res = procurementDAO.saveVendorDebitOrCreditAmt(vendorid, DateTimeUtils.changeFormat(nettotal), "0",
						date);
			} else {

				double debit = Double.parseDouble(product.getDebit());
				nettotal = nettotal + debit;
				int res = procurementDAO.updateVendorDebitOrCreditAmt(vendorid, DateTimeUtils.changeFormat(nettotal),
						"0", date);
			}

			procurementForm.setProductList(productList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "returnpurorder";
	}

	public String getvouchersvendor() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			PoPaymenytDAO paymenytDAO = new JDBCPoPaymengtDAO(connection);
			String vendorid = request.getParameter("vendorid");

			ArrayList<Product> voucherProductList = procurementDAO.getVoucherProductList(vendorid);
			StringBuffer buffer = new StringBuffer();

			for (Product product : voucherProductList) {
				int dmcmplted = procurementDAO.checkIsComplatedDm(product.getProcurementid());
				double total = paymenytDAO.getTotalVoucherAmount(product.getVoucherno(), product.getProcurementid(),
						dmcmplted);
				buffer.append("<tr>");
				buffer.append(
						"<td><label class='checkbox checkbox-custom-alt m-0' style='margin-top: 2px !important;'><input class='case' type='checkbox' value='"
								+ product.getId() + "'  ><i></i> " + product.getVoucherno()
								+ " &nbsp; | &nbsp; <a href='#' onclick='openHideDiv(" + product.getId()
								+ ")' data-toggle='collapse' ><i class='fa fa-arrow-down' style='font-size: 14px;' aria-hidden='true'></i></a></label> </td>");
				buffer.append("<td>" + product.getVoucherdate() + "</td>");
				buffer.append("<td class='text-right'>Rs." + DateTimeUtils.changeFormat(total) + "</td>");
				buffer.append("</tr>");

				if (product.getProductVoucherList().size() != 0) {
					buffer.append("<tr id='vprod" + product.getId() + "' style='display: none;'>");
					buffer.append("<td colspan='4'>");

					buffer.append("<table class='table'>");
					buffer.append(" <thead>");
					buffer.append("<tr>");

					buffer.append("<th style='background-color: rgba(165, 42, 42, 0.57);width: 32%;'>Product</th>");
					buffer.append("<th style='background-color: rgba(165, 42, 42, 0.57);width: 6%;'>Pack</th>");
					buffer.append("<th style='background-color: rgba(165, 42, 42, 0.57);width: 8%;'>Batch</th>");
					buffer.append("<th style='background-color: rgba(165, 42, 42, 0.57);width: 9%;'>Expiry</th>");
					buffer.append(
							"<th style='background-color: rgba(165, 42, 42, 0.57);width: 9%;' class='text-right'>MRP</th>");
					buffer.append(
							"<th style='background-color: rgba(165, 42, 42, 0.57);width: 7%;' class='text-center'>Stock</th>");
					buffer.append(
							"<th style='background-color: rgba(165, 42, 42, 0.57);width: 7%;' class='text-center'>Qty</th>");
					buffer.append(
							"<th style='background-color: rgba(165, 42, 42, 0.57);width: 8%;' class='text-right'>Rate</th>");
					buffer.append(" <th style='background-color: rgba(165, 42, 42, 0.57);width: 8%;'></th>");
					buffer.append("<th style='background-color: rgba(165, 42, 42, 0.57);'></th>");
					buffer.append("</tr>");
					buffer.append("</thead>");
					buffer.append("<tbody>");

					for (Product master : product.getProductVoucherList()) {

						buffer.append("<tr>");
						buffer.append("<td>" + master.getProduct_name() + " </td> ");
						buffer.append("<td>" + master.getPack() + " </td> ");
						buffer.append("<td>" + master.getBatch_no() + "</td>");
						buffer.append("<td>" + DateTimeUtils.getCommencingDate1(master.getExpiry_date()) + "</td>");
						buffer.append("<td class='text-right'>Rs." + master.getSale_price() + "</td>");
						buffer.append(" <td class='text-center'>" + master.getStock() + "</td>");
						buffer.append(" <td class='text-center'>" + master.getQty() + "</td>");
						buffer.append("<td class='text-right'>Rs." + master.getPurchase_price() + "</td>");
						buffer.append(
								"<td><label class='checkbox checkbox-custom-alt m-0' style='margin-top: 2px !important;'><input class='dcase' value='"
										+ master.getId() + "'  type='checkbox'><i></i> Return</label></td>");
						buffer.append("<td><input id='retqty" + master.getId()
								+ "' type='text' class='form-control' style='height: 18px;'></td>");
						buffer.append("</tr>");
					}
					buffer.append("</tbody>");
					buffer.append("</table>");

					buffer.append("</td>");
					buffer.append("</tr>");
				}

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

	public String getstate() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(loginInfo.getUserId());
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(priscription.getState());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addtopo() throws Exception {

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
				if(breadcrumbs.getName().equals("Goods Receipt")){
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
				breadcrumbs.setName("Goods Receipt");
				breadcrumbs.setOn(false);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("addtopoProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Goods Receipt");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String procurementid = request.getParameter("procurementid");
			double poDiscAmt = 0;
			if(loginInfo.isLmh()){
				poDiscAmt = inventoryProductDAO.getPoDiscountAmount(procurementid);
			}
			procurementForm.setPoDiscAmt(poDiscAmt);
			boolean checkispending = procurementDAO.checkIsPendingPo(procurementid);
			if (!checkispending) {
				Procurement procurement2 = procurementDAO.getParentProcurmentData(procurementid);
				boolean flag = procurementDAO.checkPreviousCompletePo(procurementid);
				int newprocurementid = 0;
				if (flag) {
					newprocurementid = procurementDAO.getProcurementPOPending(procurementid, flag, procurement2,
							loginInfo.getTimeZone());
				} else {
					newprocurementid = procurementDAO.getPreviousCompletePoProcId(procurementid);
				}
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
				int res = procurementDAO.updateProcuremeentGRnDate(newprocurementid, date);
				int nrewgrnno = procurementDAO.getNewGrnPoNo(procurementid);
				procurementForm.setNewgrno("" + nrewgrnno);

				Product grndata = procurementDAO.getGrnDetails(nrewgrnno);
				procurementForm.setPoSequence(grndata.getPoSequence());
				procurementForm.setIsfromworkorder(grndata.getIsfromworkorder());
				
				procurementForm.setNewprocurementid("" + newprocurementid);

				ArrayList<Product> productList = procurementDAO
						.getProcurementListBeforeProcurement("" + newprocurementid);
				procurementForm.setProductList(productList);

				int size = productList.size();
				if (size > 0) {
					String location = productList.get(size - 1).getLocation();
					procurementForm.setLocation(location);
				} else {
					procurementForm.setLocation("0");
				}
                procurementForm.setSize(size);
				ArrayList<Product> requestedPoList = procurementDAO.getProcPoRequestedList(procurementid);
				procurementForm.setRequestedPoList(requestedPoList);
				procurementForm.setProcurementid(procurementid);

				String expiryDateAlert = procurementDAO.getExpiryAlertDateSetting(loginInfo.getClinicid());
				procurementForm.setExpiryDateAlert(expiryDateAlert);

				String vendorid = procurement2.getVendor_id();
				procurementForm.setVendoridlongpo(vendorid);
			} else {
				ArrayList<Product> productList = procurementDAO.getProcurementListBeforeProcurement(procurementid);
				procurementForm.setProductList(productList);
				ArrayList<Product> requestedPoList = procurementDAO.getProcRequestedList(procurementid);
				procurementForm.setRequestedPoList(requestedPoList);
				procurementForm.setProcurementid(procurementid);

				int size = productList.size();
				if (size > 0) {
					String location = productList.get(size - 1).getLocation();
					procurementForm.setLocation(location);
				} else {
					procurementForm.setLocation("0");
				}

				int nrewgrnno = procurementDAO.getNewGrnPoNo(procurementid);
				procurementForm.setNewgrno("" + nrewgrnno);
				
				Product grndata = procurementDAO.getGrnDetails(nrewgrnno);
				procurementForm.setPoSequence(grndata.getPoSequence());
				procurementForm.setIsfromworkorder(grndata.getIsfromworkorder());
				
				String expiryDateAlert = procurementDAO.getExpiryAlertDateSetting(loginInfo.getClinicid());
				procurementForm.setExpiryDateAlert(expiryDateAlert);

				Procurement procurement2 = procurementDAO.getParentProcurmentData(procurementid);
				String vendorid = procurement2.getVendor_id();
				procurementForm.setVendoridlongpo(vendorid);
			}
			ArrayList<Master> mfglist = new ArrayList<Master>();
			if (loginInfo.isAuto_generic_name()) {
				mfglist = inventoryProductDAO.getMFGMasterList();
			}
			procurementForm.setMfglist(mfglist);
			procurementForm.setClinicid1(loginInfo.getClinicid1());
			ArrayList<String> unitOfMeseurementList = inventoryProductDAO.getUnitOfMessurementList();
			procurementForm.setUnitOfMeseurementList(unitOfMeseurementList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "longpo";
	}

	public String getconfirmprod() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String procurementid = request.getParameter("id");
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -31);
			String fromdate = dateFormat.format(cal.getTime());
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);

			Calendar cal1 = Calendar.getInstance();
			String todate = dateFormat.format(cal1.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);

			Vendor vendor = procurementDAO.getVendorDetailsOfProcurement(procurementid);
			ArrayList<Product> allprocurementList = procurementDAO.getProcurementListBeforeProcurement(procurementid);
			int grnno = 0;
			String date = "";
			float totalAmt = 0;
			double totalnetamount = 0;
			double gstamount = 0;
			double nettotalx = 0;
			StringBuffer buffer = new StringBuffer();
			int i = 0;
			int isagreement = 0;
			String location = "0";
			for (Product proc : allprocurementList) {
				if (i == 0) {
					grnno = proc.getGrnno();
					location = proc.getLocation();
				}
				//   23 June 2018
				double purchaseprice = 0;
				double vat = 0;
				String qty = "0";
				if (proc.getPurchase_price() != null) {
					if (!proc.getPurchase_price().equals("")) {
						purchaseprice = Double.parseDouble(proc.getPurchase_price());
					}
				}

				if (proc.getVat() != null) {
					if (!proc.getVat().equals("")) {
						vat = Double.parseDouble(proc.getVat());
					}
				}

				/*if (proc.getQuantity() != null) {
					if (!proc.getQuantity().equals("")) {
						qty = Integer.parseInt(proc.getQuantity());
					}
				}*/
				if (!DateTimeUtils.isNull(proc.getQuantity()).equals("")) {
						qty =proc.getQuantity();
					
				}
				double consumed = 0;
				
				/*
				 * if(proc.getLocation().equals("36")){ Product
				 * directtransferproductout =
				 * inventoryProductDAO.getDirectTransferBetweenDate(proc.
				 * getCatalogueid(),fromdate,proc.getLocation(),todate); Product
				 * requesttransferproductout =
				 * inventoryProductDAO.getRequestTransferBetweenDate(proc.
				 * getCatalogueid(),fromdate,proc.getLocation(),todate); Product
				 * consume =
				 * inventoryProductDAO.getConsumeBetweenDate(proc.getCatalogueid
				 * (),fromdate,todate,proc.getLocation()); consumed =
				 * directtransferproductout.getTotalqty() +
				 * requesttransferproductout.getTotalqty()
				 * +consume.getTotalqty(); }else{ Product
				 * directtransferproductout =
				 * inventoryProductDAO.getDirectTransferBetweenDate(proc.
				 * getCatalogueid(),fromdate,proc.getLocation(),todate); Product
				 * requesttransferproductout =
				 * inventoryProductDAO.getRequestTransferBetweenDate(proc.
				 * getCatalogueid(),fromdate,proc.getLocation(),todate); Product
				 * salepatient =
				 * inventoryProductDAO.getPatientSaleBetweenDate(proc.
				 * getCatalogueid(),fromdate,todate,proc.getLocation()); Product
				 * consume =
				 * inventoryProductDAO.getConsumeBetweenDate(proc.getCatalogueid
				 * (),fromdate,todate,proc.getLocation()); consumed
				 * =salepatient.getTotalqty() +
				 * directtransferproductout.getTotalqty() +
				 * requesttransferproductout.getTotalqty()
				 * +consume.getTotalqty(); }
				 */
				Product directtransferproductout = inventoryProductDAO
						.getDirectTransferBetweenDate(proc.getCatalogueid(), fromdate, proc.getLocation(), todate);
				Product requesttransferproductout = inventoryProductDAO
						.getRequestTransferBetweenDate(proc.getCatalogueid(), fromdate, proc.getLocation(), todate);
				Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(proc.getCatalogueid(), fromdate,
						todate, proc.getLocation());
				Product consume = inventoryProductDAO.getConsumeBetweenDate(proc.getCatalogueid(), fromdate, todate,
						proc.getLocation());
				consumed = salepatient.getTotalqty() + directtransferproductout.getTotalqty()
						+ requesttransferproductout.getTotalqty() + consume.getTotalqty();

				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * Double.parseDouble(qty);
				netamount = Math.round(netamount * 100.0) / 100.0;
				double nettotal = netamount + Float.parseFloat(proc.getTotal());
				double temp = (nettotal * Double.parseDouble(proc.getDiscount())) / 100;
				nettotal = nettotal - temp;
				isagreement = proc.getIsagreement();
				if (proc.getIsagreement() > 0) {
					if (proc.getProduct_name() == null) {
						proc.setProduct_name("");
					}
					buffer.append("<tr style='color: black !important;'>");
					buffer.append("<td>" + (++i) + " </td><input type='hidden' class='myclassJ' value='" + i + "' />");
					buffer.append("<td>" + proc.getProduct_name() + "</td>");
					buffer.append("<td>" + proc.getPro_code() + "</td>");
					buffer.append("<input type='hidden' value='" + proc.getPurchase_price() + "' id='previouspurprice"
							+ i + "' />");
					buffer.append("<td><input type='number' id='approve_gst" + i + "' class='form-control' value='"
							+ proc.getVat() + "' readonly='readonly'  /></td>");
					buffer.append("<td><input type='number' id='qty" + i
							+ "' readonly='readonly' onchange='setAllConfirmData()'  name='qty" + proc.getId()
							+ "' class='form-control' value='" + proc.getQuantity() + "' /></td>");
					buffer.append("<td><input type='number' id='rate" + i + "' readonly='readonly' name='rate"
							+ proc.getId() + "' onchange='setAllConfirmData()' class='form-control'  value='"
							+ proc.getPurchase_price() + "'   /></td>");
					buffer.append("<td><input type='number' id='total" + i + "' class='form-control' value='"
							+ proc.getTotal() + "' readonly='readonly'  /></td>");
					buffer.append("<td><input type='number' id='netgsttotal" + i + "' class='form-control' value='"
							+ String.format("%.2f", Math.round(netamount * 100.0) / 100.0)
							+ "' readonly='readonly'  /></td>");
					buffer.append(
							"<td><input type='number' class='form-control' readonly='readonly' onchange='setAllConfirmData()' value='"
									+ proc.getDiscount() + "' id='discounts" + i + "' name='discounts" + proc.getId()
									+ "'   /></td>");
					buffer.append("<td><input type='number' id='nettotal" + i + "' class='form-control' value='"
							+ String.format("%.2f", Math.round(nettotal * 100.0) / 100.0)
							+ "' readonly='readonly'  /></td>");
					buffer.append("<td><input type='number' id='instock" + i + "' class='form-control' value='"
							+ proc.getStockqty() + "' readonly='readonly'  /></td>");
					buffer.append("<td>" + proc.getOldvenderpurprise() + "</td>");
					buffer.append("<td><b>" + consumed + "</b> (" + DateTimeUtils.getCommencingDate1(fromdate) + " to "
							+ DateTimeUtils.getCommencingDate1(todate) + ")</td>");
					buffer.append("<td><a href='#' onclick=deletePO(this,'" + proc.getId()
							+ "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>");
					buffer.append("</tr>");
					date = proc.getDate();
					totalAmt = totalAmt + Float.parseFloat(proc.getTotal());
					totalnetamount = totalnetamount + netamount;
					gstamount = gstamount + netamount;
					nettotalx = nettotal + nettotalx;
				} else {
					if (proc.getProduct_name() == null) {
						proc.setProduct_name("");
					}
					buffer.append("<tr style='color: black !important;'>");
					buffer.append("<td>" + (++i) + " </td><input type='hidden' class='myclassJ' value='" + i + "' />");
					buffer.append("<td>" + proc.getProduct_name() + "");
					//buffer.append("<textarea rows='3' class='form-control' name='poDescription" + proc.getId() + "' id='poDescription" + proc.getId() + "'>" +DateTimeUtils.isNull(proc.getPoDescription()) + "</textarea>");
					 if(loginInfo.isLmh()){
						buffer.append("<br>Description");
						buffer.append("<textarea rows='2' class='form-control' name='poDescription" + proc.getId() + "' id='poDescription" + proc.getId() + "'>" + DateTimeUtils.isNull(proc.getPoDescription()) + "</textarea>");	
						buffer.append("<br>HSS NO");
						buffer.append("<input type='text' class='form-control' name='poHsnno" + proc.getId() + "' id='poHsnno" + proc.getId() + "' value='"+DateTimeUtils.isNull(proc.getPoHsnno())+"'>");
					}
					buffer.append("</td>");
					buffer.append("<td>" + proc.getPro_code() + "</td>");
					if(loginInfo.isLmh()) { 
						  buffer.append("<td>" + proc.getUom() + "");
						  }
					buffer.append("<input type='hidden' value='" + proc.getPurchase_price() + "' id='previouspurprice"
							+ i + "' />");
					/*
					 * buffer.append("<td><input type='number' id='approve_gst"
					 * +i+"' class='form-control' value='"+proc.getVat()
					 * +"' readonly='readonly'  /></td>");
					 */
					buffer.append("<td>");

					buffer.append(
							"<select class='form-control' style='margin: 0;padding: 0;outline: 0;'  id='approve_gst" + i
									+ "' onchange='setAllConfirmData()' name='vat" + proc.getId() + "' >");
					int tvat = Integer.parseInt(proc.getVat());
					if (tvat == 0) {
						buffer.append("<option selected='selected' value='0'>0%</option>");
					} else {
						buffer.append("<option value='0'>0%</option>");
					}
					if (tvat == 5) {
						buffer.append("<option selected='selected' value='5'>5%</option>");
					} else {
						buffer.append("<option value='5'>5%</option>");
					}
					if (tvat == 12) {
						buffer.append("<option selected='selected' value='12'>12%</option>");
					} else {
						buffer.append("<option value='12'>12%</option>");
					}
					if (tvat == 18) {
						buffer.append("<option value='18' selected='selected'>18%</option>");
					} else {
						buffer.append("<option value='18'>18%</option>");
					}
					if (tvat == 28) {
						buffer.append("<option selected='selected' value='28'>28%</option>");
					} else {
						buffer.append("<option value='28'>28%</option>");
					}
					buffer.append("</select>");
					buffer.append("</td>");

					buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' id='qty" + i
							+ "' onchange='setAllConfirmData()'  name='qty" + proc.getId()
							+ "' class='form-control' value='" + proc.getQuantity() + "' /></td>");
					/*
					 * buffer.append("<td><input type='number' id='rate"
					 * +i+"' onchange='changeRateToCatalogueByProduct(this.value,"
					 * +proc.getCatalogueid()+",1,"+
					 * i+")' class='form-control'  value='" +
					 * proc.getPurchase_price() + "'   /></td>");
					 */
					buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' id='rate" + i
							+ "' onchange='setAllConfirmData()' name='rate" + proc.getId()
							+ "' class='form-control'  value='" + proc.getPurchase_price() + "'   /></td>");
					buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' id='total" + i
							+ "' class='form-control' value='" + proc.getTotal() + "' readonly='readonly'  /></td>");
					buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' id='netgsttotal"
							+ i + "' class='form-control' value='"
							+ String.format("%.2f", Math.round(netamount * 100.0) / 100.0)
							+ "' readonly='readonly'  /></td>");
					buffer.append(
							"<td><input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' onchange='setAllConfirmData()' value='"
									+ proc.getDiscount() + "' id='discounts" + i + "' name='discounts" + proc.getId()
									+ "'   /></td>");
					buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' id='nettotal" + i
							+ "' class='form-control' value='"
							+ String.format("%.2f", Math.round(nettotal * 100.0) / 100.0)
							+ "' readonly='readonly'  /></td>");
					buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' id='instock" + i
							+ "' class='form-control' value='" + proc.getStockqty() + "' readonly='readonly'  /></td>");
					buffer.append("<td>" + proc.getOldvenderpurprise() + "</td>");
					buffer.append("<td><b>" + consumed + "</b> (" + DateTimeUtils.getCommencingDate1(fromdate) + " to "
							+ DateTimeUtils.getCommencingDate1(todate) + ")</td>");
					buffer.append("<td><a style='margin: 0;padding: 0;outline: 0;' href='#' onclick=deletePO(this,'"
							+ proc.getId()
							+ "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>");
					buffer.append("</tr>");
					date = proc.getDate();
					totalAmt = totalAmt + Float.parseFloat(proc.getTotal());
					totalnetamount = totalnetamount + netamount;
					gstamount = gstamount + netamount;
					nettotalx = nettotal + nettotalx;
				}
			}
			
			boolean discCashFlag =false;;
			
			if(vendor.getPodiscamt()>0){
				double tempTotal = totalAmt;
				double po_disc= vendor.getPodiscamt();
				double discratenew = (po_disc / tempTotal) * 100.0;
				double tempgst = gstamount * discratenew /100;
				gstamount = gstamount - tempgst;
				nettotalx = tempTotal - po_disc;
				nettotalx = nettotalx + gstamount; 
				discCashFlag = true;
			}
			
			// double totalallnetamount2 = totalAmt+totalnetamount;
			buffer.append("~" + vendor.getName() + "~" + vendor.getId() + "~" + grnno + "~" + date + "~"
					+ vendor.getVendoremail());

			buffer.append("~");
			buffer.append("<tr><td></td><td></td><td></td><td></td><td><b>Total :</b></td><td></td><td><b id='totalAll'>");
			buffer.append(""+String.format("%.2f", Math.round(totalAmt * 100.0) / 100.0)+"");
			buffer.append("</b></td><td><b id='netgsttotalAll'>");
			buffer.append(""+String.format("%.2f", Math.round(gstamount * 100.0) / 100.0)+"");
			buffer.append("</b></td><td id='netdisctotalAll'>");				 
			if(loginInfo.isLmh_po_discount()){
				buffer.append("<select class='form-control' name='po_discType' id='po_discType' onchange='changePoDiscType(this.value)'>");
				buffer.append("<option value='0'>%</option>");
				if(discCashFlag){
					buffer.append("<option value='1' selected='select'>Cash</option>");
				}else{
					buffer.append("<option value='1'>Cash</option>");
				}
				
				buffer.append("</select>");
				//buffer.append("<label>Disc Cash</label>");
				buffer.append("<input type='number' id='po_disc' readonly='true' name='po_disc' value='"+vendor.getPodiscamt()+"' onchange='calculatePoDisc(this.value)' class='form-control'>");
			}else{
				buffer.append("<select class='form-control hidden' name='po_discType' id='po_discType' onchange='changePoDiscType(this.value)'>");
				buffer.append("<option value='0'>Percent</option>");
				buffer.append("<option value='1'>Cash</option>");
				buffer.append("</select>");
				buffer.append("<input type='hidden' id='po_disc' name='po_disc' value='"+vendor.getPodiscamt()+"' onchange='calculatePoDisc(this.value)' class='form-control'>");
			}
			buffer.append("</td><td style='text-align: center;'><b id='nettotalAll'>");				 
			buffer.append(""+String.format("%.2f", Math.round(nettotalx * 100.0) / 100.0)+"");				
			buffer.append("</b></td><td></td><td></td><td></td><td></td></tr>");
			
			buffer.append("~");
			buffer.append("" + isagreement + "");
			buffer.append("~");
			
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListNew(location);
			buffer.append("<label style='color: black !important;'>Store</label>");
			buffer.append("<select name='warehouse' id='warehouse' onchange='setProductofStoreInPO(this.value)' class='form-control chosen'  > ");
			buffer.append("<option value='0'>Select Store</option>");
			for (Master master : warehouseList) {
				buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			buffer.append("</select>");
			
			buffer.append("~");
			Product grndata = procurementDAO.getGrnDetails(grnno);
			if(loginInfo.isLmh()){
				buffer.append(""+grndata.getPoSequence()+"");
			}else{
				buffer.append(""+grnno+"");
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

	public String printconfirm() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String procurementid = request.getParameter("id");
			String newprocurementid = procurementid;
			String oldprocid = procurementDAO.getOldParentProcId(procurementid);
			if (oldprocid != null) {
				if (!oldprocid.equals("0")) {
					procurementid = oldprocid;
				}
			}
			Vendor vendor = procurementDAO.getVendorDetailsOfProcurement(procurementid);
			procurementForm.setVendorGstNo(vendor.getTinno());
			String remark = procurementDAO.getAdminPoRemark(procurementid);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			Procurement procurement = procurementDAO.getProcurementDataByProcurementID(procurementid);
			
			ArrayList<Product> productList = procurementDAO.getProcurementListBeforeProcurement(procurementid);
			String seconderyletterhead = procurementDAO.secondryLetterhead();
			procurementForm.setSeconderyletterhead(seconderyletterhead);
			String subtotal = "0";
			String totalnetamount = "0";
			String totalgstamount = "0";
			String totaldiscount="0";
			int isCancel =0;
			for (Product product : productList) {
				totalnetamount = product.getTotalnetamount();
				subtotal = product.getSubTotal();
				totalgstamount = product.getTotalgstamount();
				isCancel = product.getDeleted();
			}
			Collections.sort(productList);
			procurementForm.setIsCancel(isCancel);
			if(vendor.getPodiscamt()>0){
				double nettotalx =0;
				double tempTotal = DateTimeUtils.convertToDouble(subtotal);
				double gstamount = DateTimeUtils.convertToDouble(totalgstamount);
				double po_disc= vendor.getPodiscamt();
				double discratenew = (po_disc / tempTotal) * 100.0;
				double tempgst = gstamount * discratenew /100;
				gstamount = gstamount - tempgst;
				totalgstamount = ""+gstamount;
				nettotalx = tempTotal - po_disc;
				nettotalx = nettotalx + gstamount; 
				totalnetamount = ""+Math.round(nettotalx);
				totaldiscount = ""+po_disc;
			}
			procurementForm.setTotaldiscount(totaldiscount);
			procurementForm.setProductList(productList);
			procurementForm.setTotalnetamount(""+Math.round(DateTimeUtils.convertToDouble(totalnetamount)));
			//procurementForm.setTotalnetamount(""+(DateTimeUtils.convertToDouble(totalnetamount)));
            procurementForm.setTotalgstamount(totalgstamount);
			Product grndata = procurementDAO.getGrnDetails(Integer.parseInt(procurement.getGrnno()));
			procurementForm.setPoSequence(grndata.getPoSequence());
			procurementForm.setNoteSheetNo(grndata.getNoteSheetNo());
			procurementForm.setRateContractNo(grndata.getRateContractNo());
			procurementForm.setPoDepartmentId(grndata.getPoDepartmentId());
			procurementForm.setPoDepartment(grndata.getPoDepartment());
			procurementForm.setSupplierRefNo(grndata.getSupplierRefNo());
			procurementForm.setIsfromworkorder(grndata.getIsfromworkorder());
			procurementForm.setNoteSheetDate(grndata.getNoteSheetDate());
			procurementForm.setDurationFrom(grndata.getDurationFrom());
			procurementForm.setDurationTo(grndata.getDurationTo());
			procurementForm.setAmcCmc(grndata.getAmcCmc());
			
			String purdate = DateTimeUtils.getCommencingDate1(procurement.getDate());
			if (loginInfo.isHidecalinpoprint()) {
				procurementForm.setHidecalinpoprint(grndata.isHidecalinpoprint());
			} else {
				procurementForm.setHidecalinpoprint(false);
			}
			procurementForm.setCreatedby(procurement.getUserid() + "|" + purdate + "" + procurement.getTime());
			procurementForm.setNewpo(procurement.getLongpo());
			procurementForm.setVendor(vendor.getName());
			procurementForm.setProcurementid(procurement.getProcurementid());
			procurementForm.setLocation(pharmacyDAO.getLocationName(procurement.getLocation()));
			procurementForm.setSubTotal(subtotal);
			procurementForm.setRemark(remark);
			procurementForm.setDate(purdate);
			procurementForm.setGrnno(String.valueOf(grndata.getGrnno()));
			procurementForm.setGrndate(grndata.getDate());
			procurementForm.setProcurementid(procurementid);
			procurementForm.setAddress(DateTimeUtils.isNull(vendor.getAddress()));
			procurementForm.setMobile(DateTimeUtils.isNull(vendor.getMobile_pri()));
			procurementForm.setVendoremail(DateTimeUtils.isNull(vendor.getEmail()));
			String printedby = loginInfo.getUserId() + "/" + purdate + " " + time;
			procurementForm.setPrintedby(printedby);

			if (procurement.getLocation() != null) {
				if (procurement.getLocation().equals("32") || procurement.getLocation().equals("33")
						|| procurement.getLocation().equals("34")) {
					UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
					procurementForm.setClinicName(userProfile.getClinicname());
					procurementForm.setClinicaddress(userProfile.getAddress());
					procurementForm.setLandLine(userProfile.getPhone());
					procurementForm.setFullname(userProfile.getFullname());
					procurementForm.setClinicemail(userProfile.getEmail());
					procurementForm.setPlace(userProfile.getCity());
					procurementForm.setWebsiteUrl(userProfile.getWebsite());
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					procurementForm.setClinicLogo(clinic.getUserImageFileName());
					procurementForm.setGstnno(clinic.getGstn_no());
					ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
					Clinic clinic2 = new Clinic();
					clinic2.setAddress(DateTimeUtils.isNull(userProfile.getAddress()));
					locationAdressList.add(clinic2);
					procurementForm.setLocationAdressList(locationAdressList);
				} else {
					// letter head
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
					locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					procurementForm.setClinicName(clinic.getClinicName());
					procurementForm.setClinicOwner(clinic.getClinicOwner());
					procurementForm.setOwner_qualification(clinic.getOwner_qualification());
					procurementForm.setLocationAdressList(locationAdressList);
					// procurementForm.setAddress(clinic.getAddress());
					procurementForm.setLandLine(clinic.getLandLine());
					procurementForm.setClinicemail(clinic.getEmail());
					procurementForm.setWebsiteUrl(clinic.getWebsiteUrl());
					procurementForm.setClinicLogo(clinic.getUserImageFileName());
					procurementForm.setGstnno(clinic.getGstn_no());
				}
			} else {
				// letter head
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				procurementForm.setClinicName(clinic.getClinicName());
				procurementForm.setClinicOwner(clinic.getClinicOwner());
				procurementForm.setOwner_qualification(clinic.getOwner_qualification());
				procurementForm.setLocationAdressList(locationAdressList);
				// procurementForm.setAddress(clinic.getAddress());
				procurementForm.setLandLine(clinic.getLandLine());
				procurementForm.setClinicemail(clinic.getEmail());
				procurementForm.setWebsiteUrl(clinic.getWebsiteUrl());
				procurementForm.setClinicLogo(clinic.getUserImageFileName());
				procurementForm.setGstnno(clinic.getGstn_no());
			}

			ArrayList<Product> termsandconditionlist = inventoryProductDAO.getAllTermsAndCondition(grndata.getIsfromworkorder());
			procurementForm.setTermsandconditionlist(termsandconditionlist);
			// lokesh
			procurementForm.setAccountno(vendor.getAccountno());
			procurementForm.setIfsc(vendor.getIfsc());
			procurementForm.setBranch(vendor.getBranch());
			procurementForm.setBankname(vendor.getBankname());
			boolean ter = procurementDAO.checkTermsNcondition(procurementid);
			if (!ter) {
				int k = 1;
				String str = "";
				StringBuffer bf = new StringBuffer();
				for (Product p : termsandconditionlist) {
					bf.append(k + ". " + p.getName() + ".  " + "\n" + " ");
					k = k + 1;
				}
				str = bf.toString();
				procurementForm.setTermsncond(str);
			} else {
				String terms = procurementDAO.getTermNconditions(procurementid);
				procurementForm.setTermsncond(terms);
			}
			boolean paymentterm = procurementDAO.checkPayTermsNcondition(procurementid);
			String terms="";
			if(paymentterm){
				terms = procurementDAO.getPaymentTermNCondition(procurementid);
			}
			procurementForm.setPaymenttermsncond(terms);
			boolean issubjectavailable = true;
			if (grndata.getSubject_msg() != null) {
				if (grndata.getSubject_msg().equals("")) {
					issubjectavailable = false;
				}
			} else {
				issubjectavailable = false;
			}
			procurementForm.setIssubjectavailable(issubjectavailable);
			procurementForm.setSubject_msg(grndata.getSubject_msg());

			boolean ismail_content = true;
			if (grndata.getMail_content() != null) {
				if (grndata.getMail_content().equals("")) {
					ismail_content = false;
				}
			} else {
				ismail_content = false;
			}

			procurementForm.setIsmail_content(ismail_content);
			procurementForm.setMail_content(grndata.getMail_content());
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			procurementForm.setLocationListPharmacy(locationlist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "printconfirmgrn";
	}

	public String setproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			String vendorid = request.getParameter("vendorid");
			Product product = procurementDAO.getLastMedicineData(id, vendorid);
			String data = product.getMedicine_type() + "^" + product.getPack() + "^" + product.getMedicine_shedule();
			ArrayList<Master> medicineTypeList = new ArrayList<Master>();
			StringBuffer buffer = new StringBuffer();
			buffer.append(data + "~");
			if (product.getCategory_id().equals("2")) {

				medicineTypeList = inventoryProductDAO.getMedicineTypeList();
				buffer.append("<label>Product Type</label>");
				buffer.append("<select name='medicine_type' id='medicine_type' class='form-control' >");
				buffer.append("<option value='0'>Product Type</option>");
				for (Master master : medicineTypeList) {
					int ijd = Integer.parseInt(product.getMedicine_type());
					if (ijd == master.getId()) {
						buffer.append("<option selected='selected' value='" + master.getId() + "'>" + master.getName()
								+ "</option>");
					} else {
						buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
					}
				}
				buffer.append("</select>");

			} else {

				Product product2 = inventoryProductDAO.getSubCategory(product.getSubcategory_id());
				buffer.append("<label>Product Type</label>");
				buffer.append("<select name='medicine_type' id='medicine_type' class='form-control' >");
				buffer.append("<option value='" + product2.getId() + "'>" + product2.getName() + "</option>");
				buffer.append("</select>");

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

	public String grnprint() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String id = request.getParameter("id");
			String isfromdelivermemo = request.getParameter("isfromdelivermemo");
			int procid = 0;
			if (isfromdelivermemo != null) {
				if (isfromdelivermemo.equals("1")) {
					procid = procurementDAO.getOldProcurementId(id);
				}
			}
			String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];

			Procurement procurement = procurementDAO.getProcurement(id, procid);
			if (procid > 0) {
				procurement.setProcurementid("" + procid);
			}
			Vendor vendor = inventoryVendorDAO.getVendor(procurement.getVendor_id());
			
			procurementForm.setAddress(vendor.getAddress());
			procurementForm.setMobile(vendor.getMobile_pri());
			procurementForm.setVendoremail(vendor.getVendoremail());
			procurementForm.setVendorGstNo(vendor.getTinno());
			procurementForm.setDmno(procurement.getDmno());
			
			//   20-09-2018
			ArrayList<Product> productList = new ArrayList<Product>();

			Product procdata = procurementDAO.getProcAccountDetails(procurement.getProcurementid());
			String subtotal = "0";
			double disc = 0;
			double netAmt = 0;
			productList = procurementDAO.getProcurementProductList(procurement.getProcurementid(), procid);
			for (Product product : productList) {
				subtotal = product.getSubTotal();
			}
			disc = poPaymenytDAO.getTotalVoucherDiscount(procurement.getVoucherno(), procurement.getProcurementid());
			int dmcmplted = procurementDAO.checkIsComplatedDm(procurement.getProcurementid());
			netAmt = poPaymenytDAO.getTotalVoucherAmount(procurement.getVoucherno(), procurement.getProcurementid(),
					dmcmplted);
			procurementForm.setCgst(procdata.getCgst());
			procurementForm.setSgst(procdata.getSgst());
			procurementForm.setIgst(procdata.getIgst());
			procurementForm.setCredit(DateTimeUtils.changeStringFormat(procdata.getCredit()));
			procurementForm.setDebit(DateTimeUtils.changeStringFormat(procdata.getDebit()));
			procurementForm.setSurcharge(DateTimeUtils.changeStringFormat(procdata.getSurcharge()));

			procurementForm.setProductList(productList);

			Product grndata = procurementDAO.getGrnDetails(Integer.parseInt(procurement.getGrnno()));
			String purdate = DateTimeUtils.getCommencingDate1(procurement.getDate());
			String voucherdate = DateTimeUtils.getCommencingDate1(procurement.getVoucherdate());

			ArrayList<Product> requestedPoList = procurementDAO.getProcRequestedList(procurement.getProcurementid());
			procurementForm.setCreatedby(procurement.getUserid() + "|" + purdate + "" + procurement.getTime());
			procurementForm.setRequestedPoList(requestedPoList);
			procurementForm.setNewpo(procurement.getLongpo());
			procurementForm.setVendor(vendor.getName());
			procurementForm.setProcurementid(procurement.getProcurementid());
			procurementForm.setVoucherno(procdata.getVoucherno());
			procurementForm.setSecurity_date(procdata.getSecurity_date());
			procurementForm.setSecurity_no(procdata.getSecurity_no());
			procurementForm.setLocation(pharmacyDAO.getLocationName(procurement.getLocation()));
			procurementForm.setVoucherdate(voucherdate);
			procurementForm.setSubTotal(DateTimeUtils.changeStringFormat(subtotal));
			procurementForm.setNetTotal(DateTimeUtils.changeFormat(netAmt));
			procurementForm.setDiscount(DateTimeUtils.changeFormat(disc));
			procurementForm.setRemark(procdata.getRemark());
			procurementForm.setDate(purdate);
			procurementForm.setPaymode(procdata.getPaymode());
			procurementForm.setGrnno(String.valueOf(grndata.getGrnno()));
			procurementForm.setGrndate(grndata.getDate());
			procurementForm.setPoSequence(grndata.getPoSequence());
			String printedby = loginInfo.getUserId() + "/" + purdate + " " + time;
			procurementForm.setPrintedby(printedby);

			if (procurement.getLocation() != null) {
				if (procurement.getLocation().equals("32") || procurement.getLocation().equals("33")
						|| procurement.getLocation().equals("34")) {
					UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
					UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
					procurementForm.setClinicName(userProfile.getClinicname());
					procurementForm.setClinicaddress(userProfile.getAddress());
					procurementForm.setLandLine(userProfile.getPhone());
					procurementForm.setFullname(userProfile.getFullname());
					procurementForm.setClinicemail(userProfile.getEmail());
					procurementForm.setPlace(userProfile.getCity());
					procurementForm.setWebsiteUrl(userProfile.getWebsite());
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					procurementForm.setClinicLogo(clinic.getUserImageFileName());
					ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
					Clinic clinic2 = new Clinic();
					clinic2.setAddress(DateTimeUtils.isNull(userProfile.getAddress()));
					locationAdressList.add(clinic2);
					procurementForm.setLocationAdressList(locationAdressList);
					
				} else {
					// letter head
					Clinic clinic = new Clinic();
					ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
					AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
					ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
					locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
					clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
					procurementForm.setClinicName(clinic.getClinicName());
					procurementForm.setClinicOwner(clinic.getClinicOwner());
					procurementForm.setOwner_qualification(clinic.getOwner_qualification());
					procurementForm.setLocationAdressList(locationAdressList);
					procurementForm.setLandLine(clinic.getLandLine());
					procurementForm.setClinicemail(clinic.getEmail());
					procurementForm.setWebsiteUrl(clinic.getWebsiteUrl());
					procurementForm.setClinicLogo(clinic.getUserImageFileName());
				}
			} else {
				// letter head
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				procurementForm.setClinicName(clinic.getClinicName());
				procurementForm.setClinicOwner(clinic.getClinicOwner());
				procurementForm.setOwner_qualification(clinic.getOwner_qualification());
				procurementForm.setLocationAdressList(locationAdressList);
				procurementForm.setLandLine(clinic.getLandLine());
				procurementForm.setClinicemail(clinic.getEmail());
				procurementForm.setWebsiteUrl(clinic.getWebsiteUrl());
				procurementForm.setClinicLogo(clinic.getUserImageFileName());
			}

			JDBCProcurementDAO jdbcProcurementDAO = new JDBCProcurementDAO(connection);
			int res = jdbcProcurementDAO.getProcurmentSeqNo(procurement.getProcurementid());
			String proSeqNo = "";
			if (res > 0) {
				proSeqNo = String.valueOf(res);
			} else {
				proSeqNo = procurement.getProcurementid();
			}
			procurementForm.setProSeqNo(proSeqNo);
			int isdelivermemo = jdbcProcurementDAO.IsDeliverMemo(procurement.getProcurementid());
			procurementForm.setIsdelivermemo(String.valueOf(isdelivermemo));
			if (isdelivermemo > 0) {
				int dmseq = jdbcProcurementDAO.getDeliverSeqNo(procurement.getProcurementid());

				procurementForm.setDmseq(String.valueOf(dmseq));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "grnprint";
	}

	public String viewsupplierhistory() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String pid = request.getParameter("pid");
			Product master = inventoryProductDAO.getProduct(pid);

			ArrayList<Product> alllist = procurementDAO.getAllSupplierProductList(master.getProduct_name(),
					master.getCatalogueid());
			StringBuffer buffer = new StringBuffer();
			for (Product product : alllist) {
				/*
				 * buffer.append("<tr>"); buffer.append("<td>" +
				 * product.getVendor() + "</td>"); buffer.append("<td>" +
				 * product.getDate() + "</td>"); buffer.append("<td>" +
				 * product.getQuantity() + "</td>"); buffer.
				 * append("<td style='background-color: rgba(165, 42, 42, 0.07);text-align:right;'>"
				 * + product.getPurchase_price() + "</td>");
				 * buffer.append("<td>" + product.getDiscount() + "</td>");
				 * buffer.append("<td>" + product.getVat() + "</td>");
				 * buffer.append("</tr>");
				 */
				buffer.append("<tr>");
				buffer.append("<td>" + product.getVendor() + "</td>");
				buffer.append("<td>" + product.getDate() + "</td>");
				buffer.append("<td>" + product.getProSeqNo() + "</td>");
				buffer.append("<td>" + product.getVoucherno() + "</td>");
				buffer.append("<td>" + product.getVat() + "</td>");
				buffer.append("<td>" + product.getQuantity() + "</td>");
				buffer.append("<td>" + product.getFreeqty() + "</td>");
				buffer.append("<td style='background-color: rgba(165, 42, 42, 0.07);text-align:right;'>"
						+ product.getMrp() + "</td>");
				buffer.append("<td style='background-color: rgba(165, 42, 42, 0.07);text-align:right;'>"
						+ product.getSale_price() + "</td>");
				buffer.append("<td style='background-color: rgba(165, 42, 42, 0.07);text-align:right;'>"
						+ product.getPurchase_price() + "</td>");
				buffer.append("<td>" + product.getDiscount() + "</td>");
				buffer.append("<td>" + product.getTotal() + "</td>");
				buffer.append("</tr>");
			}

			buffer.append("~" + master.getProduct_name());
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

	public String requestedpo() throws Exception {

		Connection connection = null;
		try {
			
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			String isfromworkorder = DateTimeUtils.numberCheck(request.getParameter("isfromworkorder"));
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(isfromworkorder.equals("0") && breadcrumbs.getName().equals("PO with GRN")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else if(isfromworkorder.equals("1") && breadcrumbs.getName().equals("Work Order")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else if(isfromworkorder.equals("2") && breadcrumbs.getName().equals("AMC/CMC")){
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
				if(isfromworkorder.equals("0")){
					breadcrumbs.setName("PO with GRN");
					breadcrumbs.setShowingname("PO with GRN");
				}else if(isfromworkorder.equals("1")){
					breadcrumbs.setName("Work Order");
					breadcrumbs.setShowingname("Work Order");
				}else if(isfromworkorder.equals("2")){
					breadcrumbs.setName("AMC/CMC");
					breadcrumbs.setShowingname("AMC/CMC");
				}
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("requestedpoProcurement");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (location.equals("")) {
				location = "0";
			}
			String searchtext = procurementForm.getSearchtext();
			if (searchtext != null) {
				if (searchtext.equals("")) {
					searchtext = null;
					procurementForm.setSearchtext("");
				} else {
					procurementForm.setSearchtext(searchtext);
				}
			} else {
				procurementForm.setSearchtext("");
			}
			String supplierPoId = DateTimeUtils.numberCheck(procurementForm.getSupplierPoId());
			String locationPoId = DateTimeUtils.numberCheck(procurementForm.getLocationPoId());
			
			procurementForm.setWarehouse(location);
			ArrayList<Product> requestedPOList = new ArrayList<>();
			ArrayList<Vendor> supplierList = new ArrayList<>();
			if(isfromworkorder.equals("0")){
				int res = procurementDAO.updateGrnWithPoMinusQty();
				int count = procurementDAO.getTotalRequestedPOCount(location, searchtext,supplierPoId,locationPoId);
				pagination.setPreperties(count);
				procurementForm.setTotalRecords(count);
				requestedPOList = procurementDAO.getRequestedPoList(location, pagination, searchtext,supplierPoId,locationPoId);
				pagination.setPage_records(requestedPOList.size());
				procurementForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				procurementForm.setRequestedPOList(requestedPOList);
				supplierList = inventoryVendorDAO.getAllVendorList(location);
			}
			procurementForm.setRequestedPOList(requestedPOList);
			procurementForm.setSupplierList(supplierList);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			String fromdate = dateFormat.format(cal.getTime());
			procurementForm.setFromdate(fromdate);

			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseList(location);
			procurementForm.setWarehouseList(warehouseList);
			procurementForm.setWarehouseid(location);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			ArrayList<Product> list = new ArrayList<Product>();
			if (!location.equals("0")) {
				list = catalogueDAO.getAllProductList(location);
			}
			procurementForm.setProductList(list);
			ArrayList<Product> selectedPOList = new ArrayList<Product>();
			selectedPOList = procurementDAO.getRequestedPoListFromTempData(loginInfo.getLoginsessionid(), location,isfromworkorder);
			procurementForm.setSelectedPOList(selectedPOList);
			
			ArrayList<Master> genericnamelist = new ArrayList<Master>();
			if (loginInfo.isAuto_generic_name()) {
				genericnamelist = inventoryProductDAO.getGenericMasterList();
			}
			procurementForm.setGenericnamelist(genericnamelist);
			ArrayList<String> unitOfMeseurementList = inventoryProductDAO.getUnitOfMessurementList();
			procurementForm.setUnitOfMeseurementList(unitOfMeseurementList);
			
			procurementForm.setIsfromworkorder(isfromworkorder);
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			procurementForm.setLocationListPharmacy(locationlist);
			
			procurementForm.setSupplierPoId(supplierPoId);
			procurementForm.setLocationPoId(locationPoId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "requestedpo";
	}

	public String addtoselectedpo() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("data");
			String prodid = "0";
			if (session.getAttribute("selectedgrnwithpoids") != null) {
				prodid = (String) session.getAttribute("selectedgrnwithpoids");
			}
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (location.equals("")) {
				location = "0";
			}

			for (String s : id.split(",")) {
				if (s.equals("0")) {
					continue;
				}
				//   20-12-2019 grn with po data temporaray
				String cataloguid = s;
				boolean flag = inventoryProductDAO.checkAlreadyInTempGRNPO(loginInfo.getLoginsessionid(), cataloguid,
						location,"0");
				if (!flag) {
					Product master = inventoryProductDAO.getProductCatalogueDetails(cataloguid);
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int checkedentry = 1;
					int isfrompolist = 1;
					Product product = procurementDAO.getRequestedPoListByID(cataloguid, location,
							master.getVendor_id());
					int result = procurementDAO.saveTempDataInGRNWIthPO(datetime, loginInfo.getLoginsessionid(),
							product.getId(), master, product.getProduct_id(), location, product.getQty(),
							product.getVendor_id(), checkedentry, isfrompolist, product.getParentid(),master.getHsnno(),"0");
				}

				prodid = prodid + "," + s;
			}
			session.setAttribute("selectedgrnwithpoids", prodid);
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

	public String warehousecategory() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			ArrayList<Master> categoryList = inventoryProductDAO.getCategoryFromWareHouse(id);
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select name='category_id' id='category_id' onchange='getsubcategory(this.value)' class='form-control chosen'  > ");
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

	public String warehousecategory1() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			ArrayList<Master> categoryList = inventoryProductDAO.getCategoryFromWareHouse(id);
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select name='category_id' id='category_id' onchange='getvendorsubcategory(this.value)' class='form-control chosen'  > ");
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

	public String getvendorsubcategories() throws Exception {

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
			buffer.append(
					"<select name='subcategory_id' onchange='getVendorSubPoproduct(this.value)' class='form-control chosen' id='subcategory_id' >");
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
			buffer.append(
					"<select name='subcategory_id' onchange='getSubPoproduct(this.value)' class='form-control chosen' id='subcategory_id' >");
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

	public String setmedicineproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String category = request.getParameter("category");
			String medicinetype = request.getParameter("medicinetype");
			ArrayList<Product> list = inventoryProductDAO.getMedinceListofType(medicinetype, category);
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select id='product_id' class='form-control chosen' onchange='setdiscRate(this.value)' name='product_id' >");
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

	public String setvendorproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String category = request.getParameter("category");
			String medicinetype = request.getParameter("medicinetype");
			String vendorid = request.getParameter("vendorid");
			ArrayList<Product> list = null;
			if (category.equals("2")) {
				list = inventoryVendorDAO.getVendorProducts(vendorid);
			} else {
				list = inventoryProductDAO.getVenodrMedinceListofType(medicinetype, category, vendorid);
			}

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select id='product_id' class='form-control chosen' onchange='setdiscRateVendor(this.value)' name='product_id' >");
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

	public String setproddisc() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			StringBuffer buffer = new StringBuffer();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			String warehouse = request.getParameter("val");
			Product product = inventoryProductDAO.getProductCatalogueDetails(id);
			ArrayList<Product> vendorList = procurementDAO.getAllVendorOfProduct(warehouse);

			String data = product.getPurchase_price() + "~" + "0";
			buffer.append(data + "~");
			buffer.append("<label style='color: black !important;'>Supplier</label>");
			buffer.append("<select class='form-control chosen' id='vendorid'  >");
			buffer.append("<option value='0'>Select Supplier</option>");
			for (Product product2 : vendorList) {
				if (product2.getOn() == 1) {
					buffer.append("<option selected='selected' value='" + product2.getId() + "'>" + product2.getData()
							+ "</option>");
				} else {
					buffer.append("<option value='" + product2.getId() + "'>" + product2.getData() + "</option>");
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

	public String setproddiscvendor() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			String vendorid = request.getParameter("vendorid");
			Product product = inventoryProductDAO.getProduct(id);
			Product master = procurementDAO.getLastProductDetailsOfSupplier(product.getProduct_name(), vendorid);
			double rate = Double.parseDouble(master.getPurchase_price());
			if (rate <= 0) {
				master.setPurchase_price(product.getPurchase_price());
			}

			String data = master.getPurchase_price() + "~" + master.getDiscount();
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

	public String savenewgrn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String qty = request.getParameter("qty");
			String cataloguid = request.getParameter("product_id");
			String product_id = inventoryProductDAO.getProdidFromCatagoryid(cataloguid);
			String vendorid = request.getParameter("vendorid");
			String warehouse = request.getParameter("warehouse");
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String rate =request.getParameter("rate");
			String isfromworkorder = request.getParameter("isfromworkorder");
			
			Product product = new Product();
			product.setProduct_id(product_id);
			product.setVendor_id(vendorid);
			product.setQty(Double.parseDouble(qty));
			product.setDate(date);
			product.setWarehouse_id(warehouse);
			product.setCatalogueid(cataloguid);
			product.setIsfromworkorder(isfromworkorder);
			int res = procurementDAO.saveNewTempPo(product);
			//   20-12-2019 grn with po data temporaray
			boolean flag = inventoryProductDAO.checkAlreadyInTempGRNPO(loginInfo.getLoginsessionid(), cataloguid,
					warehouse,isfromworkorder);
			if (!flag) {
				Product master = inventoryProductDAO.getProductCatalogueDetails(cataloguid);
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int checkedentry = 1;
				int isfrompolist = 0;
				master.setPurchase_price(rate);
				int result = procurementDAO.saveTempDataInGRNWIthPO(datetime, loginInfo.getLoginsessionid(), res,
						master, product_id, warehouse, Double.parseDouble(qty), vendorid, checkedentry, isfrompolist, "0",master.getHsnno(),isfromworkorder);
			}

			String prodid = "0";
			if (session.getAttribute("selectedgrnwithpoids"+isfromworkorder) != null) {
				prodid = (String) session.getAttribute("selectedgrnwithpoids"+isfromworkorder);
			}
			prodid = prodid + "," + cataloguid;
			session.setAttribute("selectedgrnwithpoids"+isfromworkorder, prodid);

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

	public String deletenewgrn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			String catalogueid = request.getParameter("catalogueid");
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (location == "") {
				location = "0";
			}

			// int res = procurementDAO.deleteNewGrn(id);
			int res = procurementDAO.deleteNewGrnBycatId(catalogueid, location);

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

	public String addnewgrnproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String catalogueid = request.getParameter("product_id"); // new
																		// changes
																		// by
																		// jitu
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -31);
			String fromdate = dateFormat.format(cal.getTime());
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);

			Calendar cal1 = Calendar.getInstance();
			String todate = dateFormat.format(cal1.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);

			String count = request.getParameter("count");
			String qty = request.getParameter("qty");
			String vendorid = request.getParameter("vendorid");
			String procurementid = request.getParameter("procurementid");
			String warehouseid = request.getParameter("warehouse");
			String discount = request.getParameter("discount");
			String rate1 = request.getParameter("rate");
			if (discount != null) {
				if (discount.equals("")) {
					discount = "0";
				}
			} else {
				discount = "0";
			}
			Product product = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
			product.setPurchase_price(rate1);
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			product.setProcurementid(procurementid);
			product.setVendor(vendorid);
			product.setVendor_id(vendorid);
			product.setCatalogueid(catalogueid);
			product.setWarehouse_id(warehouseid);
			product.setDate(date);
			product.setQty(Double.parseDouble(qty));

			StringBuffer buffer = new StringBuffer();
			boolean chisexist = procurementDAO.isThisProductExist(procurementid, catalogueid);
			if (!chisexist) {
				int grnno = procurementDAO.getGrnNoFromProcId(procurementid);
				Product grndata = procurementDAO.getGrnDetails(grnno);
				product.setGrnno(grnno);
				product.setDiscount(discount);
				product.setIs_po_prod(1);
				product.setIsfromworkorder(grndata.getIsfromworkorder());
				int pid = inventoryProductDAO.savetempProduct(product);
				int globleproductid = inventoryProductDAO.updateGlobalProductId(pid, pid);
				product.setProduct_id(String.valueOf(pid));
				product.setId(pid);
				int longo = procurementDAO.saveNewTempPo(product);
				product.setNewpo(longo);
				product.setGrnwithpo_child(1);
				int res = procurementDAO.saveProcurementData(product, ""+product.getQty(), 0, vendorid, "0",
						Integer.parseInt(procurementid), 0, 0, product.getVat());
				//for vspm save procurement log table
				if(loginInfo.isLmh()) {
					res=procurementDAO.saveProcurementDataLog(product, ""+product.getQty(), 0, vendorid, "0",
							Integer.parseInt(procurementid), 0, 0, product.getVat());
				}
				
				Product master = procurementDAO.getLastProductDetailsOfSupplier(product.getProduct_name(), vendorid);

				Product proc = procurementDAO.getProcurementBeforeProcurement(procurementid, catalogueid);
				// lokesh
				proc.setStockqty(procurementDAO.getStock(catalogueid, proc.getLocation()));
				proc.setOldvenderpurprise(procurementDAO.getoldpurprice(catalogueid));

				double rate = Double.parseDouble(rate1);
				if (rate <= 0) {
					master = product;
				}
				master.setPurchase_price(rate1);
				int q = Integer.parseInt(qty);
				String total = DateTimeUtils.changeFormat(rate * q);

				double consumed = 0;
				if (proc.getLocation().equals("36")) {
					Product directtransferproductout = inventoryProductDAO
							.getDirectTransferBetweenDate(proc.getCatalogueid(), fromdate, proc.getLocation(), todate);
					Product requesttransferproductout = inventoryProductDAO
							.getRequestTransferBetweenDate(proc.getCatalogueid(), fromdate, proc.getLocation(), todate);
					Product consume = inventoryProductDAO.getConsumeBetweenDate(proc.getCatalogueid(), fromdate, todate,
							proc.getLocation());
					consumed = directtransferproductout.getTotalqty() + requesttransferproductout.getTotalqty()
							+ consume.getTotalqty();
				} else {
					Product directtransferproductout = inventoryProductDAO
							.getDirectTransferBetweenDate(proc.getCatalogueid(), fromdate, proc.getLocation(), todate);
					Product requesttransferproductout = inventoryProductDAO
							.getRequestTransferBetweenDate(proc.getCatalogueid(), fromdate, proc.getLocation(), todate);
					Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(proc.getCatalogueid(), fromdate,
							todate, proc.getLocation());
					Product consume = inventoryProductDAO.getConsumeBetweenDate(proc.getCatalogueid(), fromdate, todate,
							proc.getLocation());
					consumed = salepatient.getTotalqty() + directtransferproductout.getTotalqty()
							+ requesttransferproductout.getTotalqty() + consume.getTotalqty();
				}

				/*
				 * buffer.append("<td>" + count + "  </td>"); buffer.
				 * append("<td><input type='text' class='form-control' value='"
				 * + master.getProduct_name() +
				 * "' readonly='readonly' /> </td>");
				 * buffer.append("<td><input type='number' name='qty" + res +
				 * "' class='form-control' value='" + qty + "' /></td>");
				 * buffer.
				 * append("<td><input type='number' class='form-control' value='"
				 * + master.getPurchase_price() +
				 * "' readonly='readonly'  /></td>"); buffer.
				 * append("<td><input type='number' class='form-control' value='"
				 * + total + "' readonly='readonly'  /></td>");
				 * buffer.append("<td><a href='#' onclick=deletePO(this,'" + res
				 * +
				 * "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>"
				 * );
				 */

				buffer.append("<td>" + count + " </td><input type='hidden' class='myclassJ' value='" + count + "' />");
				buffer.append("<td>" + proc.getProduct_name() + "");
				if(loginInfo.isLmh()){
					buffer.append("<br>Description");
					buffer.append("<textarea rows='2' class='form-control' name='poDescription' id='poDescription" + proc.getId() + "'>" + DateTimeUtils.isNull(proc.getPoDescription()) + "</textarea>");	
					buffer.append("<br>HSS NO");
					buffer.append("<input type='text' class='form-control' name='poHsnno" + proc.getId() + "' id='poHsnno" + proc.getId() + "' value='"+DateTimeUtils.isNull(proc.getPoHsnno())+"'>");
				}
				buffer.append("</td>");
				buffer.append("<td>" + proc.getPro_code() + "</td>");
				/*
				 * buffer.append("<td><input type='number' id='approve_gst"
				 * +count+"' class='form-control' value='"+proc.getVat()
				 * +"' readonly='readonly'  /></td>");
				 */
				buffer.append("<select class='form-control'  id='approve_gst" + count
						+ "' onchange='setAllConfirmData()' name='vat" + proc.getId() + "' >");
				int tvat = Integer.parseInt(proc.getVat());
				if (tvat == 0) {
					buffer.append("<option selected='selected' value='0'>0%</option>");
				} else {
					buffer.append("<option value='0'>0%</option>");
				}
				if (tvat == 5) {
					buffer.append("<option selected='selected' value='5'>5%</option>");
				} else {
					buffer.append("<option value='5'>5%</option>");
				}
				if (tvat == 12) {
					buffer.append("<option selected='selected' value='12'>12%</option>");
				} else {
					buffer.append("<option value='12'>12%</option>");
				}
				if (tvat == 18) {
					buffer.append("<option value='18' selected='selected'>18%</option>");
				} else {
					buffer.append("<option value='18'>18%</option>");
				}
				if (tvat == 28) {
					buffer.append("<option selected='selected' value='28'>28%</option>");
				} else {
					buffer.append("<option value='28'>28%</option>");
				}
				buffer.append("</select>");
				buffer.append("</td>");

				buffer.append("<td><input type='number' id='qty" + count + "' onchange='setAllConfirmData()'  name='qty"
						+ proc.getId() + "' class='form-control' value='" + proc.getQuantity() + "' /></td>");
				buffer.append("<input type='hidden' value='" + proc.getPurchase_price() + "' id='previouspurprice"
						+ count + "' />");
				/*
				 * buffer.append("<td><input type='number' id='rate"
				 * +count+"' onchange='changeRateToCatalogueByProduct(this.value,"
				 * +proc.getCatalogueid()+",1,"+
				 * count+")' class='form-control'  value='" +
				 * proc.getPurchase_price() + "'   /></td>");
				 */
				buffer.append("<td><input type='number' id='rate" + count + "' name='rate" + proc.getId()
						+ "' onchange='setAllConfirmData()' class='form-control'  value='" + proc.getPurchase_price()
						+ "'   /></td>");

				buffer.append("<td><input type='number' id='total" + count + "' class='form-control' value='"
						+ proc.getTotal() + "' readonly='readonly'  /></td>");

				double purchaseprice = 0;
				double vat = 0;
				double qty1 = 0;
				if (proc.getPurchase_price() != null) {
					if (!proc.getPurchase_price().equals("")) {
						purchaseprice = Double.parseDouble(proc.getPurchase_price());
					}
				}

				if (proc.getVat() != null) {
					if (!proc.getVat().equals("")) {
						vat = Double.parseDouble(proc.getVat());
					}
				}

				/*if (proc.getQuantity() != null) {
					if (!proc.getQuantity().equals("")) {
						qty1 = Integer.parseInt(proc.getQuantity());
					}
				}*/
				if (proc.getQuantity().equals("0")) {
					 qty1 =Double.parseDouble(proc.getQuantity());
				}
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty1;
				netamount = Math.round(netamount * 100.0) / 100.0;
				double nettotal = netamount + Float.parseFloat(proc.getTotal());
				double temp = (nettotal * Double.parseDouble(proc.getDiscount())) / 100;
				nettotal = nettotal - temp;
				buffer.append("<td><input type='number' id='netgsttotal" + count + "' class='form-control' value='"
						+ netamount + "' readonly='readonly'  /></td>");
				buffer.append("<td><input type='number' class='form-control' onchange='setAllConfirmData()' value='"
						+ proc.getDiscount() + "' id='discounts" + count + "' name='discounts" + proc.getId()
						+ "'   /></td>");
				buffer.append("<td><input type='number' id='nettotal" + count + "' class='form-control' value='"
						+ nettotal + "' readonly='readonly'  /></td>");

				buffer.append("<td><input type='number' id='instock" + count + "' class='form-control'  value='"
						+ proc.getStockqty() + "' readonly='readonly'  /></td>");

				buffer.append("<td>" + proc.getOldvenderpurprise() + "</td>");

				buffer.append("<td>" + consumed + "(" + DateTimeUtils.getCommencingDate1(fromdate) + "to"
						+ DateTimeUtils.getCommencingDate1(todate) + ")</td>");

				buffer.append("<td><a href='#' onclick=deletePO(this,'" + proc.getId()
						+ "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>");

			} else {
				buffer.append("0");
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

	public String savegrn() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String categoryid = (String) session.getAttribute("category");

			if (categoryid == null) {
				categoryid = "2";
			}
			/*
			 * String location = (String) session.getAttribute("location"); if
			 * (location == null) { location = "0"; }
			 */
			String location = procurementForm.getWarehouseid();
			if (location != null) {
				if (location.equals("")) {
					location = "0";
				}
			} else {
				location = "0";
			}

			String isagreement = procurementForm.getIsagreement();

			if (isagreement != null) {
				if (isagreement.equals("on")) {
					isagreement = "1";
				} else {
					isagreement = "0";
				}
			} else {
				isagreement = "0";
			}
			
			String podisc = request.getParameter("po_disc");
			String isfromworkorder = DateTimeUtils.numberCheck(procurementForm.getIsfromworkorder());
			if (isagreement.equals("1")) {
				// Agreement Code
				String datetime[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
				String datetime1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int parentid = procurementDAO.saveParentAgreement(loginInfo.getUserId(), datetime1, location);
				for (Product product : procurementForm.getProducts()) {
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
					product.setUserid(loginInfo.getUserId());
					product.setLastmodified(datetime[0]);
					product.setLocation(location);
					// for medicine
					String catalogueid = product.getCatalogueid();
					Product master = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
					product.setPurchase_price(product.getRate());
					product.setCatalogueid(catalogueid);
					product.setParentid("" + parentid);
					product.setPack(master.getPack());
					product.setVat(product.getVat());
					int res = procurementDAO.saveChildAgreement(product);
					// update po temp
					int result = procurementDAO.updateTempPoStatus(product.getId());
				}

			} else {
				boolean hidecalinpoprint = procurementForm.isHidecalinpoprint();
				// Normal GRN With PO code
				String datetime[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
				for (Product product : procurementForm.getProducts()) {
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
					product.setUserid(loginInfo.getUserId());
					product.setCategory_id(categoryid);
					product.setLastmodified(datetime[0]);
					product.setLocation(location);
					// for medicine
					String catalogueid = product.getCatalogueid();
					Product master = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
					product.setProduct_name(master.getProduct_name());
					product.setGenericname(master.getGenericname());
					product.setMrp(master.getMrp());
					product.setPurchase_price(product.getRate());
					product.setMedicinenameid(product.getProduct_id());
					product.setCatalogueid(catalogueid);
					product.setIs_po_prod(1);
					product.setPack(master.getPack());
					
					String poDescription = product.getPoDescription();
					String pohsnno = product.getPoHsnno();
					
					int res = inventoryProductDAO.savetempProduct(product);
					int globleid = inventoryProductDAO.updateGlobalProductId(res, res);
					int result = procurementDAO.addTempPo(String.valueOf(res), product.getVendor_id(), ""+product.getQty(),
							product.getId(), "0", product.getRate(), 0, product.getVat(),
							loginInfo.getLoginsessionid(),poDescription,pohsnno,isfromworkorder);
					// update po temp
					result = procurementDAO.updateTempPoStatus(product.getId());
				}
				int procurementid = 0;
				int grnno = 0;
				ArrayList<Product> polistByVendor = procurementDAO.getAllPoListByVendor(loginInfo.getLoginsessionid(),isfromworkorder);
				for (Product product : polistByVendor) {
					String date1 = procurementForm.getFromdate();
					String date = "";
					if (date1 != null) {
						if (!date1.equals("")) {
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							String time = dateFormat.format(cal.getTime());
							date = DateTimeUtils.getCommencingDate1(date1);
							date = date + " " + time;
						} else {
							date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						}
					} else {
						date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					}
					int grnwithpo = 1;
					String type="P";
					if(isfromworkorder.equals("1")){
						type = "W";
					}else if(isfromworkorder.equals("2")){
						type = request.getParameter("amcCmc");
					}
					String grnYearSeq= procurementDAO.generateYearPoSequence(location,loginInfo.isLmh(),type,date1);
					
					procurementid = procurementDAO.saveParengtPrecurementData(product.getVendor_id(), date, 0, "0",
							grnwithpo,grnYearSeq,isfromworkorder);
					grnno = procurementDAO.saveParentGrnData(product.getVendor_id(), date, hidecalinpoprint,grnYearSeq,isfromworkorder);
					
					if(isfromworkorder.equals("1")){
						String poDepartmentId = request.getParameter("poDepartmentId");
						int res = procurementDAO.updatePoDetails(""+grnno, poDepartmentId, "poDepartmentId");
					}
					if(isfromworkorder.equals("1") || isfromworkorder.equals("2")){
						String noteSheetNo = request.getParameter("noteSheetNo");
						String noteSheetDate = request.getParameter("noteSheetDate");
						int res = procurementDAO.updatePoDetails(""+grnno, noteSheetNo, "noteSheetNo");
						res = procurementDAO.updatePoDetails(""+grnno, DateTimeUtils.getCommencingDate1(noteSheetDate), "noteSheetDate");
					}
					if(isfromworkorder.equals("2")){
						String durationFrom = request.getParameter("durationFrom");
						String durationTo = request.getParameter("durationTo");
						String amcCmc = request.getParameter("amcCmc");
						int res = procurementDAO.updatePoDetails(""+grnno, DateTimeUtils.getCommencingDate1(durationFrom), "durationFrom");
						res = procurementDAO.updatePoDetails(""+grnno, DateTimeUtils.getCommencingDate1(durationTo), "durationTo");
						res = procurementDAO.updatePoDetails(""+grnno, amcCmc, "amcCmc");
					}
					
					ArrayList<Product> polist = procurementDAO.getPoProductByVendor(product.getVendor_id(),
							loginInfo.getLoginsessionid(),isfromworkorder);
					int grnseqno = procurementDAO.getMaxGRNSeqNo(location);
					for (Product poproduct : polist) {
						poproduct.setLocation(location);
						poproduct.setGrnno(grnno);
						String catalogueid = procurementDAO.getCatalogueIdFromProduct(poproduct.getId());
						poproduct.setCatalogueid(catalogueid);
						poproduct.setGrnwithpo_child(1);
						poproduct.setUserid(loginInfo.getUserId());
						int result = procurementDAO.saveProcurementData(poproduct, ""+poproduct.getQty(), 0,
								product.getVendor_id(), "0", procurementid, 0, 0, poproduct.getVat());
						//for vspm insert into log table
						if(loginInfo.isLmh()) {
							result=procurementDAO.saveProcurementDataLog(poproduct, ""+poproduct.getQty(), 0,
									product.getVendor_id(), "0", procurementid, 0, 0, poproduct.getVat());
						}
						// result =
						// procurementDAO.updateProcurementStatus(poproduct.getId(),
						// "0");
						int updateresult = procurementDAO.updateTempPoStatus(catalogueid, location);
					}
					if(isfromworkorder.equals("0")){
						grnseqno = grnseqno + 1;
						int res = procurementDAO.updateProcurmentSeqNo(grnseqno, procurementid);
					}
					int res = procurementDAO.updateProcurementPoDisc(DateTimeUtils.convertToDouble(podisc), ""+procurementid);
				}
				int result = procurementDAO.truncateTempPo(loginInfo.getLoginsessionid());
			}
			int result = procurementDAO.truncateTempPoNew(loginInfo.getLoginsessionid(),isfromworkorder);
			session.removeAttribute("selectedgrnwithpoids"+isfromworkorder);
			if(isfromworkorder.equals("2")){
				return "amcCmcMethod";
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "savepo";
	}

	public String getsubcat() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String subid = request.getParameter("id");
			StringBuffer buffer = new StringBuffer();

			if (!subid.equals("2")) {
				ArrayList<Product> subcategoryList = inventoryProductDAO.getSubCategoryList(subid);
				// buffer.append("<label>Select Sub Category</label>");
				buffer.append("<select class='form-control chosen' id='subcategory_id0' >");
				buffer.append("<option value='0'>Select Sub Category</option>");
				for (Product product : subcategoryList) {
					buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
				}
				buffer.append("</select>");
			} else {
				ArrayList<Master> medicineTypeList = inventoryProductDAO.getMedicineTypeList();
				// buffer.append("<label>Select Sub Category</label>");
				buffer.append("<select class='form-control chosen' id='subcategory_id0' >");
				buffer.append("<option value='0'>Select Sub Category</option>");
				for (Master product : medicineTypeList) {
					buffer.append("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
				}
				buffer.append("</select>");
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

	public String addnewitem() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String category_id = request.getParameter("category_id");
			String subcategory_id = request.getParameter("subcategory_id");
			String prodtype = request.getParameter("prodtype");
			String product_name = request.getParameter("product_name");
			String pack = request.getParameter("pack");
			String mrp = request.getParameter("mrp");
			String purchase_price = request.getParameter("purchase_price");
			String sale_price = request.getParameter("sale_price");
			String mfg = request.getParameter("mfg");
			String vat = request.getParameter("vat");
			String description = request.getParameter("description");
			Product product = new Product();
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
			product.setCategory_id(category_id);
			product.setSubcategory_id(subcategory_id);
			product.setProdtype(prodtype);
			product.setProduct_name(product_name);
			product.setPack(pack);
			product.setMrp(mrp);
			product.setPurchase_price(purchase_price);
			product.setSale_price(sale_price);
			product.setMfg(mfg);
			product.setVat(vat);
			product.setDescription(description);
			int result = inventoryProductDAO.addNewProduct(product);

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

	// @ruchi send email and pdf on aprove procurment GRN with PO.

	public void createPdf(String procurementid) throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			Vendor vendor = procurementDAO.getVendorDetailsOfProcurement(procurementid);
			ArrayList<Product> allprocurementList = procurementDAO.getProcurementListBeforeProcurement(procurementid);

			String date = "";
			float totalAmt = 0;
			StringBuffer buffer = new StringBuffer();
			int i = 0;

			// new Header and Footer
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			int clinicid = loginInfo.getClinicid();
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getClinicid());
			String clinicname = userProfile.getClinicname();
			String clinicAdd = userProfile.getAddress();
			String clinicPhone = userProfile.getPhone();
			String clinicNameFullname = userProfile.getFullname();
			String clinicEmail = userProfile.getEmail();
			String clinicCity = userProfile.getCity();
			String website = userProfile.getWebsite();
			// String inst1=userProfile.getInstruction1();
			//// String inst2=userProfile.getInstruction2();
			String inst3 = userProfile.getInstruction3();

			// String inst4=userProfile.getInstruction4();
			String dlno = userProfile.getDlno();
			String tinno = userProfile.getTinno();
			String ownerQualification = userProfile.getQualification();
			int grnnoo = 0;
			String location = "0";
			for (Product proc : allprocurementList) {
				location = proc.getLocation();
				grnnoo = proc.getGrnno();
				break;
			}
			Product grndata = procurementDAO.getGrnDetails(grnnoo);
			boolean hidecalinpoprint = false;
			if (loginInfo.isHidecalinpoprint()) {
				hidecalinpoprint = grndata.isHidecalinpoprint();
			}
			if (location != null) {
				if (location.equals("32") || location.equals("33") || location.equals("34")) {
					UserProfile userProfile1 = userProfileDAO.getPharmacyUserDetails(1);
					clinicname = userProfile1.getClinicname();
					clinicAdd = userProfile1.getAddress();
					dlno = userProfile1.getDlno();
				}
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
			String dateString = sdf.format(cal.getTime());

			String vendorname = vendor.getName();

			// String currentusername=loginInfo.getUserName();
			String currentusername = loginInfo.getUserId();
			if (clinicNameFullname == null) {
				clinicNameFullname = "";
			}
			if (clinicname == null) {
				clinicname = "";
			}
			if (clinicAdd == null) {
				clinicAdd = "";
			}
			if (dlno == null) {
				dlno = "";
			}
			if (ownerQualification == null) {
				ownerQualification = "";
			}
			if (dateString == null) {
				dateString = "";
			}
			if (procurementid == null) {
				procurementid = "";
			}
			if (date == null) {
				date = "";
			}
			if (vendorname == null) {
				vendorname = "";
			}
			if (date.equals("")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar calendar = Calendar.getInstance();
				date = dateFormat.format(calendar.getTime());
			}

			buffer.append(
					" <style type='text/css'>body {padding-top: 0 !important;padding-bottom: 0 !important;padding-top: 0 !important;");
			buffer.append(
					"padding-bottom: 0 !important;margin:0 !important;width: 100% !important;-webkit-text-size-adjust: 100% !important;");
			buffer.append("-ms-text-size-adjust: 100% !important;-webkit-font-smoothing: antialiased !important;}");
			buffer.append(
					".tableContent img {border: 0 !important;display: block !important;outline: none !important;}a{color:#382F2E;}");
			buffer.append(
					"p, h1,ul,ol,li,div{ margin:0; padding:0;} td,table{vertical-align: top;}td.middle{vertical-align: middle;}");
			buffer.append(
					"a.link1 {background: #6699CC;color: #ffffff;font-size: 15px;padding: 8px 12px;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;text-decoration: none;}");
			buffer.append("h2{color:#000000;font-size:22px;font-weight: normal;}");
			buffer.append(".bgBody {background:#EAEAEA;}.bgItem {background:#FFFFFF;}");
			buffer.append("p{color:#555555;font-size:14px; line-height:22px;}");
			buffer.append("@media only screen and (max-width:480px){");
			buffer.append(
					"table[class='MainContainer'], td[class='cell']{width: 100% !important;height:auto !important;}");
			buffer.append(
					" td[class='specbundle']{width: 100% !important;float:left !important;font-size:13px !important;line-height:17px !important;display:block !important; }");
			buffer.append(
					"td[class='specbundle1']{width: 100% !important;float:left !important;font-size:13px !important;line-height:17px !important;display:block !important;padding-bottom:20px !important; }");
			buffer.append(
					"td[class='specbundle2']{width:90% !important;float:left !important;font-size:14px !important;line-height:18px !important;display:block !important;padding-left:5% !important;padding-right:5% !important;}");
			buffer.append(
					"td[class='specbundle3']{width:90% !important;float:left !important;font-size:14px !important;line-height:18px !important;display:block !important;padding-left:5% !important;padding-right:5% !important;padding-bottom:20px !important;}");
			buffer.append(
					"td[class='specbundle4']{width: 100% !important;float:left !important;font-size:13px !important;line-height:17px !important;display:block !important;padding-bottom:20px !important;text-align:center !important;}");
			buffer.append("td[class='spechide']{display:none !important;}");
			buffer.append("img[class='banner']{width: 100% !important;height: auto !important;}");
			buffer.append("td[class='left_pad']{padding-left:15px !important;padding-right:15px !important;}}");
			buffer.append("@media only screen and (max-width:540px){");
			buffer.append(
					"table[class='MainContainer'], td[class='cell']{width: 100% !important;height:auto !important;}");
			buffer.append(
					"td[class='specbundle']{width: 100% !important;float:left !important;font-size:13px !important;line-height:17px !important;display:block !important;}");
			buffer.append(
					"td[class='specbundle1']{width: 100% !important;float:left !important;font-size:13px !important;line-height:17px !important;display:block !important;padding-bottom:20px !important;}");
			buffer.append(
					"td[class='specbundle2']{width:90% !important;float:left !important;font-size:14px !important;line-height:18px !important;display:block !important;padding-left:5% !important;padding-right:5% !important;}");
			buffer.append(
					"td[class='specbundle3']{width:90% !important;float:left !important;font-size:14px !important;line-height:18px !important;display:block !important;padding-left:5% !important;padding-right:5% !important;padding-bottom:20px !important;}");
			buffer.append(
					"td[class='specbundle4']{width: 100% !important;float:left !important;font-size:13px !important;line-height:17px !important;display:block !important;padding-bottom:20px !important;text-align:center !important;}");
			buffer.append("td[class='spechide']{display:none !important;}");
			buffer.append("img[class='banner']{width: 100% !important;height: auto !important;}");
			buffer.append("td[class='left_pad']{padding-left:15px !important;padding-right:15px !important;}");
			buffer.append(".font{font-size:18px !important;line-height:26px !important;}}</style>");
			buffer.append(
					"<script type='colorScheme' class='swatch active'>{'name':'Default','bgBody':'EAEAEA','link':'0000FF','color':'555555','bgItem':'ffffff','title':'000000'}</script>");
			buffer.append(
					"<body paddingwidth='0' paddingheight='0' class='bgBody'  style='padding-top: 0; padding-bottom: 0; padding-top: 0; padding-bottom: 0; background-repeat: repeat; width: 100% !important;-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-font-smoothing: antialiased;' ");
			buffer.append("offset='0' toppadding='0' leftpadding='0'>");
			buffer.append(
					"<table width='100%' border='0' cellspacing='0' cellpadding='0' class='tableContent' align='center' bgcolor='#EAEAEA' style='font-family:helvetica, sans-serif;'>");
			buffer.append("<tbody>");
			buffer.append("<tr><td height='20' bgcolor='#EAEAEA'></td></tr>");
			buffer.append("<tr><td>");
			buffer.append(
					"<table width='600' border='0' cellspacing='0' cellpadding='0' align='center' class='MainContainer' bgcolor='#FFFFFF'><tbody>");
			buffer.append("<tr><td class='movableContentContainer'>");
			buffer.append("<div class='movableContent' style='border: 0px; padding-top: 0px; position: relative;'>");
			buffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>");
			buffer.append("<td bgcolor='#6699CC' width='235' valign='top' class='specbundle2'>");
			buffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			buffer.append("<tr><td height='10' colspan='3'></td></tr>");
			buffer.append("<tr><td width='30'></td>");
			buffer.append("<td><table width='100%;' border='0' cellspacing='0' cellpadding='0'>");
			buffer.append("<tr><td>");
			buffer.append("<div style='text-align: center;' class='contentEditableContainer contentTextEditable'>");
			buffer.append("<div class='contentEditable'>");
			buffer.append("<p style='line-height:19px;font-size:18px;color:#ffffff;'>");
			buffer.append("<span style='font-size: 18px;'>" + clinicname + "</span><br />");
			buffer.append("<span style='font-size: 12px;'>" + clinicNameFullname + "," + clinicAdd + "</span><br />");
			buffer.append("<span style='font-size: 10px; line-height: 8px;'>" + dlno + "</span><br />");
			buffer.append("<span style='font-size: 8px;'>" + ownerQualification + "</span>");
			buffer.append("</p></div></div></td></tr>");
			buffer.append("<tr><td></td></tr>");
			buffer.append("<tr><td></td></tr></table></td><td width='30'></td></tr>");
			buffer.append("<tr><td height='10' colspan='3'></td></tr>");
			buffer.append("</table></td></tr></table></div>");
			buffer.append("<div class='movableContent' style='border: 0px; padding-top: 0px; position: relative;'>");
			buffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'><tbody><tr>");
			buffer.append("<td valign='top' width='30'>&nbsp;</td>");
			buffer.append("<td valign='top'><table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			buffer.append("<tr><td height='12'></td></tr>");
			buffer.append("<tr><td>");
			buffer.append("<div style='font-size: 12px;' class='contentEditableContainer contentTextEditable'>");
			buffer.append("<div class='contentEditable'>");
			buffer.append("<p style='font-size: 12px;'>Dear, Sir/Mam<br />The aproved indent.</p><br />");
			buffer.append("<span style='color: #339966; margin-bottom: 10px;'>Aproved Date/Time: " + dateString
					+ "</span><br />");

			buffer.append("<span style='color: brown;'>Purchase Order For :-" + vendorname + " <br>PO No: " + grnnoo
					+ "<br> PO Date:-" + date + "</span><br /><br /><p>");
			buffer.append(
					"<table class='table table-bordered' style='border: 1px solid #ddd; width: 100%; font-size: 12px;'><thead>");
			if (hidecalinpoprint) {
				buffer.append(
						"<tr style='background-color: #ddd;'><th>#Sr No</th><th>Product Name</th><th>Quantity</th></tr></thead>");
			} else {
				buffer.append(
						"<tr style='background-color: #ddd;'><th>#Sr No</th><th>Product Name</th><th>Quantity</th><th>Rate</th><th>Amount</th></tr></thead>");
			}
			buffer.append("<tbody>");
			for (Product proc : allprocurementList) {
				if (hidecalinpoprint) {
					buffer.append("<tr> <th scope='row'>" + (++i) + "</th> <td style='border-left: 1px solid #ddd;'>"
							+ proc.getProduct_name() + "</td> <td style='border-left: 1px solid #ddd;'>"
							+ proc.getQuantity() + "</td> </tr>");
				} else {
					buffer.append("<tr> <th scope='row'>" + (++i) + "</th> <td style='border-left: 1px solid #ddd;'>"
							+ proc.getProduct_name() + "</td> <td style='border-left: 1px solid #ddd;'>"
							+ proc.getQuantity() + "</td> <td style='border-left: 1px solid #ddd;'>"
							+ proc.getPurchase_price() + "</td><td style='border-left: 1px solid #ddd;'>"
							+ proc.getTotal() + "</td> </tr>");
					totalAmt = totalAmt + Float.parseFloat(proc.getTotal());
				}
			}
			if (!hidecalinpoprint) {
				buffer.append(
						"<tr><td></td><td></td><td></td><td><b>Total :</b></td><td><b>" + totalAmt + "</b></td></tr>");
			}
			buffer.append("</tbody></table><br /><div>");
			/*
			 * buffer.
			 * append("<span style='float: left; margin-top: 15px;'><a target='_blank' href='#' class='link1' style='color:#ffffff;'>Reject</a>&nbsp;&nbsp;<a target='_blank' href='#' class='link1' style='color:#ffffff;'>Approved</a></span>"
			 * );
			 */
			buffer.append("<span style='float: right;'> " + currentusername + "<br /> " + dateString + "</span></div>");
			buffer.append("</p></div></div></td></tr>");
			buffer.append("<tr><td height='37'></td></tr></table></td>");
			buffer.append("<td width='30'>&nbsp;</td>");
			buffer.append("</tr> </tbody></table> </div>");
			buffer.append("<div class='movableContent' style='border: 0px; padding-top: 0px; position: relative;'>");
			buffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#EAEAEA'>");
			buffer.append("<tr> <td width='30' bgcolor='#EAEAEA'>&nbsp;</td>");
			buffer.append("<td valign='top'><table width='100%' cellpadding='0' cellspacing='0' border='0'>");
			buffer.append("<tr><td  bgcolor='#EAEAEA' align='center'>");
			buffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			buffer.append("<tr><td align='center' style='font-size: 12px; color: #339966;'>");
			buffer.append("<div class='contentEditableContainer contentTextEditable'>");
			buffer.append("<div class='contentEditable'>");
			buffer.append(
					"<p><a target='_blank' href='[CLIENTS.WEBSITE]' style='font-size:13px;color:#999999;text-decoration:none;line-height:19px;'>"
							+ clinicname + "</a></p>");
			buffer.append("</div></div></td></tr></table></td></tr></table></td>");
			buffer.append("<td width='30'>&nbsp;</td>");
			buffer.append("</tr></table></div>");
			buffer.append("</td></tr></table></td></tr></tbody></table></body>");

			String filePath = request.getRealPath("/liveData/document/");
			// String filename = "Invoice_"+clientid+"_"+invoiceid+".pdf";
			String filename = "Procurement_" + procurementid + "_" + vendor.getName() + ".pdf";
			htmlToPdfFile(buffer.toString(), filePath, filename);

			session.setAttribute("pdfFileName", filePath + "/" + filename);

			// accountsForm.setFilename(filename1);
			// System.out.println("pdf done");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public void htmlToPdfFile(String htmlString, String filepath, String fileaName) throws Exception {

		try {

			CYaHPConverter converter = new CYaHPConverter();
			File fout = new File(filepath + "/" + fileaName);
			FileOutputStream out = new FileOutputStream(fout);
			Map properties = new HashMap();
			ArrayList headerFooterList = new ArrayList();

			properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
			// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH,
			// fontPath);
			converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4P, (java.util.List) headerFooterList,
					"file:///temp/", // root for
										// relative
										// external
										// CSS and
										// IMAGE
					out, properties);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String email(String procurementid) throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			createPdf(procurementid);

			int loginId = loginInfo.getId();
			connection = Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			Vendor vendor = procurementDAO.getVendorDetailsOfProcurement(procurementid);
			String id = procurementid;
			String to = vendor.getEmail();// "ruchitaghugal@gmail.com";
			String cc = "";
			String subject = "Purchase Order Confirmation";
			String notes = "";
			String filename = (String) session.getAttribute("pdfFileName");
			String[] temp1 = filename.split("/");
			String filename1 = temp1[1];
			int procurementid1 = Integer.parseInt(procurementid);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String d1 = dateFormat.format(date);
			String[] temp = d1.split("\\s+");
			String date1 = temp[0];
			String time = temp[1];

			String type = "";

			int result = accountsDAO.saveEmailLogDetails(to, cc, subject, notes, filename1, procurementid1,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), time, type);

			// set deliver status

			String status = "Email";
			// int upPaymentStatus =
			// accountsDAO.updatePaymentDeliverStatus(id,status);

			EmailLetterLog emailLetterLog = new EmailLetterLog();

			emailLetterLog.setType(status);

			EmbeddedImageEmailUtil.sendMailAttachment(connection, loginId, to, cc, subject, notes, filename, loginInfo,
					emailLetterLog, procurementid);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String setvendorpoproduct() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			String vendorid = request.getParameter("id");
			String warehouseid = request.getParameter("warehouse");
			// ArrayList<Product> productList =
			// inventoryVendorDAO.getVendorProducts(vendorid);
			ArrayList<Product> productList = catalogueDAO.getAllProductList(warehouseid);
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			String stateid = vendor.getState();
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select id='product_id' class='form-control chosen' onchange='setvendorPoProduct(this.value)'  >");
			buffer.append("<option value='0'>Select Product</option>");

			for (Product product : productList) {

				String data = product.getProduct_name() + " - " + product.getGenericname() + "";
				if (product.getPro_code() != null) {
					if (!product.getPro_code().equals("")) {
						data = data + " - " + product.getPro_code();
					}
				}
				buffer.append("<option value='" + product.getId() + "'>" + data + "</option>");
			}

			buffer.append("</select>");
			buffer.append("~" + stateid);
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

	public String setvendorpoproduct1() {
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
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String vendorid = jsonObject.getString("id");
			String warehouseid = jsonObject.getString("warehouse");
			ArrayList<Product> productList = catalogueDAO.getAllProductList(warehouseid);

			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			String stateid = vendor.getState();
			StringBuffer buffer1 = new StringBuffer();
			buffer1.append(
					"<select id='product_id' class='form-control chosen' onchange='setvendorPoProduct(this.value)'  >");
			buffer1.append("<option value='0'>Select Product</option>");

			for (Product product : productList) {

				String data1 = product.getProduct_name() + " - " + product.getGenericname() + "";
				if (product.getPro_code() != null) {
					if (!product.getPro_code().equals("")) {
						data1 = data1 + " - " + product.getPro_code();
					}
				}
				buffer1.append("<option value='" + product.getId() + "'>" + data1 + "</option>");
			}

			buffer1.append("</select>");

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", buffer1.toString());
			jsonobj.put("stateid", stateid);

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String setvendorprod() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String id = request.getParameter("id");
			Product product = inventoryProductDAO.getProductCatalogueDetails(id);

			String data = product.getCatalogueid() + "~" + product.getSubcategory_id() + "~"
					+ product.getPurchase_price();
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

	public String getstoreproducts() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			InventoryCatalogueDAO catalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			ArrayList<Product> list = catalogueDAO.getAllProductList(id);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<label style='color: black !important;'>Product</label>");
			buffer.append("<select id='product_id' class='form-control chosen' onchange='setdiscRate(this.value)'  name='product_id' >"); // onchange='setProdCatandType(this.value)'
			buffer.append("<option value='0'>Select Product</option>");
			for (Product product : list) {
				String data = product.getProduct_name() + " - " + product.getGenericname() + "";
				if (product.getPro_code() != null) {
					if (!product.getPro_code().equals("")) {
						data = data + " - " + product.getPro_code();
					}
				}
				buffer.append("<option value='" + product.getId() + "'>" + data + "</option>");
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

	public String openuploadgrn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			ArrayList<Vendor> vendorList = procurementDAO.getVendorListBylocationWise(location);
			procurementForm.setVendorList(vendorList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "uploadgrn";
	}

	public String uploadgrn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryCatalogueDAO inventoryCatalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			String filePath = request.getRealPath("/image/");
			String vendorid = procurementForm.getVendor_id();
			String extension = procurementForm.getUserImageFileName().split("\\.")[1];
			String ext = "ods";
			connection = (Connection) Connection_provider.getconnection();
			if (!extension.equalsIgnoreCase(ext)) {
				addActionError("Please Upload only .ods file");
				return "uploadgrn";
			} else if (procurementForm.getUserImageFileName().equalsIgnoreCase("")
					|| procurementForm.getUserImageFileName() == null) {
				addActionError("Please Upload File");
				return "uploadgrn";
			}
			File fileToCreate = new File(filePath, procurementForm.getUserImageFileName());
			FileUtils.copyFile(procurementForm.getUserImage(), fileToCreate);
//			System.out.println(procurementForm.getUserImageFileName());

			Sheet sheet = SpreadSheet.createFromFile(fileToCreate).getSheet(0);
			String voucherno = sheet.getCellAt(1, 1).getValue().toString();
			String voucherdate = sheet.getCellAt(2, 1).getValue().toString();

			try {
				SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
				Date date3 = (Date) formatter.parse(voucherdate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date3);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				voucherdate = format.format(calendar.getTime());
			} catch (Exception e) {
			}

			String secoutno = sheet.getCellAt(3, 1).getValue().toString();
			String discount = sheet.getCellAt(4, 1).getValue().toString();
			String cgst = sheet.getCellAt(5, 1).getValue().toString();
			String sgst = sheet.getCellAt(6, 1).getValue().toString();
			String igst = sheet.getCellAt(7, 1).getValue().toString();
			double vat = 0;
			if (cgst.equals("0")) {
				vat = Double.parseDouble(igst);
			} else {
				vat = Double.parseDouble(cgst) + Double.parseDouble(sgst);
			}
			String netamt = sheet.getCellAt(8, 1).getValue().toString();
			Product product = new Product();
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			String dateTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			Procurement procurement = new Procurement();
			int procurementid = procurementDAO.saveParengtPrecurementData(vendorid, dateTime, 0, "0", 0,"","0");
			int nColCount = sheet.getColumnCount();
			int nRowCount = sheet.getRowCount();
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			Cell cell;
			int i = 0;
			ArrayList<Product> products = new ArrayList<Product>();

			for (int nRowIndex = 4; nRowIndex < nRowCount; nRowIndex++) {
				// Iterating through each column
				int nColIndex = 0;
				product = new Product();
				product.setCategory_id("2");
				product.setTime(time);
				product.setDateTime(dateTime);
				product.setProcurement(0);
				product.setLocation(location);
				product.setLastmodified(date);
				product.setVoucherno(voucherno);
				product.setDate(date);
				product.setService_date(date);
				product.setVendor_id(vendorid);
				product.setSecurity_no(secoutno);
				product.setNetVal(netamt);
				product.setCgst(cgst);
				product.setUserid(loginInfo.getUserId());
				product.setSgst(sgst);
				product.setVoucherdate(voucherdate);
				product.setIgst(igst);
				product.setDiscount(discount);
				product.setVat(String.valueOf(vat));
				product.setProcurementid(String.valueOf(procurementid));

				for (; nColIndex < nColCount; nColIndex++) {
					cell = sheet.getCellAt(nColIndex, nRowIndex);
					if (cell.getValue() == null) {
						break;
					}
					if (cell.getValue().toString().equals("") || cell.getValue().toString().equals(" ")) {
						break;
					}

					if (nColIndex == 0) {
						String prodname = cell.getValue().toString();
						product.setProduct_name(prodname);
					}
					if (nColIndex == 1) {
						String genericname = cell.getValue().toString();
						product.setGenericname(genericname);
					}
					if (nColIndex == 2) {
						String mfg = cell.getValue().toString();
						product.setMfg(mfg);
					}
					if (nColIndex == 3) {
						String hsnno = cell.getValue().toString();
						product.setHsnno(hsnno);
					}
					if (nColIndex == 4) {
						String type = cell.getValue().toString();
						String subcategoryid = inventoryCatalogueDAO.getSubcategoryId(type);
						product.setSubcategory_id(subcategoryid);
					}
					if (nColIndex == 5) {
						String pack = cell.getValue().toString();
						product.setPack(pack);
					}
					if (nColIndex == 6) {
						String batch_no = cell.getValue().toString();
						product.setBatch_no(batch_no);
					}
					if (nColIndex == 7) {
						String expiry = cell.getValue().toString();
						try {
							SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
							Date date3 = (Date) formatter.parse(expiry);
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date3);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							expiry = format.format(calendar.getTime());

						} catch (Exception e) {
							e.printStackTrace();
						}
						if (expiry != null) {

							if (expiry.equals("")) {

								expiry = "2060-12-12";
							}
						} else {
							expiry = "2060-12-12";
						}

						product.setExpiry_date(expiry);
					}
					if (nColIndex == 8) {
						String mrp = cell.getValue().toString();
						product.setMrp(mrp);
					}
					if (nColIndex == 9) {
						String sale_price = cell.getValue().toString();
						product.setSale_price(sale_price);
					}
					if (nColIndex == 10) {
						String purchase_price = cell.getValue().toString();
						product.setPurchase_price(purchase_price);
					}
					if (nColIndex == 11) {
						String received = cell.getValue().toString();
						product.setReceived_qty((received));
						product.setQuantity((received));
					}
					if (nColIndex == 12) {
						String gst = cell.getValue().toString();
						product.setVat(gst);
					}
					if (nColIndex == 13) {
						String disc = cell.getValue().toString();
						product.setDiscount(disc);
					}
					if (nColIndex == 14) {
						String free = cell.getValue().toString();
						int freeqty = Integer.parseInt(product.getPack()) * Integer.parseInt(free);
						product.setFreeqty(String.valueOf(freeqty));
					}
					if (nColIndex == 15) {
						String total = cell.getValue().toString();
						product.setTotal(total);
						String totalAmt = DateTimeUtils.changeStringFormat(total);
						product.setTotalAmt(Double.parseDouble(totalAmt));
					}

					double stock = Integer.parseInt(product.getPack()) *Double.parseDouble((product.getQuantity()));
					product.setStock(Double.toString(stock));

				}

				int catalogueid = inventoryCatalogueDAO.isExistThisName(product.getProduct_name());
				if (catalogueid == 0) {
					catalogueid = inventoryCatalogueDAO.saveNewCatalogue(product);
				}
				product.setCatalogueid(String.valueOf(catalogueid));
				int productid = procurementDAO.saveNewProduct(product);
				product.setProduct_id(String.valueOf(productid));
				int id = procurementDAO.saveNewProcurement(product);
				products.add(product);
				i++;

			}

			date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			procurement.setCredit("0");
			procurement.setDebit("0");
			procurement.setVat(String.valueOf(vat));
			procurement.setCgst(cgst);
			procurement.setIgst(igst);
			procurement.setSgst(sgst);
			procurement.setSurcharge("0");
			procurement.setDiscount(discount);
			procurement.setVendor_id(vendorid);
			procurement.setVoucherno(voucherno);
			procurement.setProcurementid(product.getProcurementid());
			procurement.setDate(date);
			procurement.setRemark("Direct Uploaded GRN ");
			procurement.setSecurity_date(product.getSecurity_date());
			procurement.setSecurity_no(product.getSecurity_no());
			procurement.setUserid(loginInfo.getUserId());
			procurement.setDisctype("1");

			procurement.setPaymode("Credit");

			int result = procurementDAO.addVendorAccountProcurement(procurement);

			addActionMessage("GRN Uploaded...");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "uploadgrn";
	}

	public String savecatalogue() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(connection);
			InventoryCatalogueDAO inventoryCatalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);

			String loc = (String) session.getAttribute("location");
			if (loc == null) {
				loc = "0";
			}
			Product product = new Product();
			String product_name = request.getParameter("prodname");
			String genericname = request.getParameter("genericname");
			String vendorid = request.getParameter("vendorid");
			String pack = request.getParameter("pack");
			String subcategory = request.getParameter("subcategory");
			String productcategoryid = request.getParameter("productcategoryid");
			String pro_code = request.getParameter("pro_code");
			//   30 March 2018 add get wgile adding new product in catalogue
			String gst = request.getParameter("addgst");
			product.setVat(gst);
			product.setProduct_name(product_name);
			product.setGeneric_name(genericname);
			/* product.setCategory_id("2"); */

			//   07-03-2018 add catagory id instead of 2
			product.setCategory_id(productcategoryid);
			product.setPack(pack);
			product.setSubcategory_id(subcategory);
			product.setLocation(loc);
			product.setUserid(loginInfo.getUserId());
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			product.setDate(date);
			product.setPro_code(pro_code);
			int res = inventoryProductDAO.addNewProduct(product);
			// int result =
			// inventoryProductDAO.addProductToVendor(res,vendorid);
			int result = prescriptionMasterDAO.addToMedicineMaster(product, res, "0");

			ArrayList<Product> productList = inventoryCatalogueDAO.getAllProductList(
					"0");/* inventoryVendorDAO.getVendorProducts(vendorid); */
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			String stateid = vendor.getState();
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"<select id='product_id' class='form-control chosen' onchange='setvendorPoProduct(this.value)'  >");
			buffer.append("<option value='0'>Select Product</option>");

			for (Product master : productList) {
				String data = master.getProduct_name() + " - " + master.getGenericname() + "";
				if (master.getPro_code() != null) {
					if (!master.getPro_code().equals("")) {
						data = data + " - " + master.getPro_code();
					}
				}

				if (res == master.getId()) {
					buffer.append("<option selected='selected' value='" + master.getId() + "'>" + data + "</option>");
				} else {
					buffer.append("<option value='" + master.getId() + "'>" + data + "</option>");
				}
			}

			buffer.append("</select>");
			buffer.append("~" + stateid);
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

	public String savecatalogue1() throws Exception {
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
			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(connection);
			InventoryCatalogueDAO inventoryCatalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);

			String loc = (String) session.getAttribute("location");
			if (loc == null) {
				loc = "0";
			}

			Product product = new Product();
			String product_name = jsonObject.getString("prodname");
			String genericname = jsonObject.getString("genericname");
			String vendorid = jsonObject.getString("vendorid");
			String pack = jsonObject.getString("pack");
			String subcategory = jsonObject.getString("subcategory");
			String productcategoryid = jsonObject.getString("productcategoryid");
			String pro_code = jsonObject.getString("pro_code");
			//   30 March 2018 add get wgile adding new product in catalogue
			String gst = jsonObject.getString("addgst");
			String warehouse = jsonObject.getString("warehouse");
			String medicine_shedule = jsonObject.getString("medicine_shedule");
			String uom = jsonObject.getString("uom");
			product.setVat(gst);
			product.setProduct_name(product_name);
			product.setGeneric_name(genericname);
			product.setProdtype(medicine_shedule);
			/* product.setCategory_id("2"); */

			//   07-03-2018 add catagory id instead of 2
			product.setCategory_id(productcategoryid);
			product.setPack(pack);
			product.setSubcategory_id(subcategory);
			product.setLocation(warehouse);
			product.setUserid(loginInfo.getUserId());
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			product.setDate(date);
			product.setPro_code(pro_code);
			product.setUom(uom);
			int res = inventoryProductDAO.addNewProduct(product);
			if(loginInfo.isKalmegha()) {
				String firstlet=product.getProduct_name().substring(0, 1);
				String vendocode=firstlet.toUpperCase()+"00"+res;
				int re=inventoryProductDAO.updateProductCode(res,vendocode);
				}
			//   26-11-2019
			if (loginInfo.getGrn_to_prisc_location() != 0) {
				if (loginInfo.getGrn_to_prisc_location() == DateTimeUtils.convertToInteger(product.getLocation())) {
					int result = prescriptionMasterDAO.addToMedicineMaster(product, res, "0");
				}
			}else {
				if(loginInfo.isAyushman()) {
					//save to apm_medicine_details for ayushman
					int result=prescriptionMasterDAO.addToMedicineMaster(product,res,"0");
				}
				
			}
			// int
			// result=prescriptionMasterDAO.addToMedicineMaster(product,res,"0");

			/*
			 * ArrayList<Product> productList =
			 * inventoryCatalogueDAO.getAllProductList("0");inventoryVendorDAO.
			 * getVendorProducts(vendorid);
			 */
			ArrayList<Product> productList = new ArrayList<Product>();
			String stateid = "0";
			Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
			if (vendor.getState() != null) {
				if (vendor.getState().equals("")) {
					stateid = "0";
				} else {
					stateid = vendor.getState();
					productList = inventoryCatalogueDAO.getAllProductList(
							"0");/*
									 * inventoryVendorDAO.getVendorProducts(
									 * vendorid);
									 */
				}
			} else {
				stateid = "0";
			}

			StringBuffer buffer1 = new StringBuffer();
			buffer1.append("<select id='product_id' class='form-control chosen' onchange='setvendorPoProduct(this.value)'  >");
			buffer1.append("<option value='0'>Select Product</option>");

			for (Product master : productList) {
				String data1 = master.getProduct_name() + " - " + master.getGenericname() + "";
				if (master.getPro_code() != null) {
					if (!master.getPro_code().equals("")) {
						data1 = data1 + " - " + master.getPro_code();
					}
				}

				if (res == master.getId()) {
					buffer1.append("<option selected='selected' value='" + master.getId() + "'>" + data1 + "</option>");
				} else {
					buffer1.append("<option value='" + master.getId() + "'>" + data1 + "</option>");
				}
			}
			buffer1.append("</select>");

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", buffer1.toString());
			jsonobj.put("stateid", stateid);

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String savetermsandcondition() {
		try {

			/*
			 * String[] x= new String[10]; int y=0; ArrayList<Product> list= new
			 * ArrayList<Product>(); list=(ArrayList<Product>)
			 * procurementForm.getTermslist();
			 * if(procurementForm.getTermslist().size()!=0) for(Product
			 * p:procurementForm.getTermslist()){ x[y]= p.getName(); y=y+1; }
			 */

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

			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String procureid = jsonObject.getString("procureid");
			String condition = jsonObject.getString("terms");
			String y = procureid;
			String x = condition;
			boolean isavailable = procurementDAO.checkTermsNcondition(procureid);
			int z;
			if (isavailable) {
				z = procurementDAO.updatetoTermsNcondition(condition, procureid);
			} else {
				z = procurementDAO.insertoTermsNcondition(condition, procureid);
			}
			x = x.replaceAll("\n", "<br>");

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("terms", x);

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String dmdashboard() throws Exception {
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
				if(breadcrumbs.getName().equals("DM Dashboard")){
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
				breadcrumbs.setName("DM Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("dmdashboardProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("DM Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (vendorid != null) {
				if (vendorid.equals("0")) {
					vendorid = "";
				}
			} else {
				vendorid = "";
			}
			ArrayList<Procurement> dmlist = procurementDAO.getDMList(location, vendorid);
			procurementForm.setDmlist(dmlist);
			procurementForm.setVendor_id(vendorid);
			/*
			 * ArrayList<Procurement> dmlist =
			 * procurementDAO.getDMList(location);
			 * procurementForm.setDmlist(dmlist);
			 */

			ArrayList<Vendor> vendorList = procurementDAO.getVendorListBylocationWise(location);
			procurementForm.setVendorList(vendorList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dmdashboard";
	}

	public String updatedm() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String dmgrnids = procurementForm.getDmgrnids();
			String voucherno = procurementForm.getVoucherno();
			String voucherdt = procurementForm.getVoucherdate();
			String security = procurementForm.getSecurity_no();
			String sec_date = procurementForm.getSecurity_date();
			String vendor_id = procurementForm.getVendor_id();
			// int dmseq = procurementDAO.getDmSeq();
			// dmseq=dmseq+1;
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int newprocurementid = procurementDAO.saveParengtPrecurementData(vendor_id, date, 0, "0", 0,"","0");
			int parentid = procurementDAO.saveParentDMData(date, loginInfo.getUserId(), newprocurementid);
			String[] ids = dmgrnids.split(",");
			double vat = 0;
			double cgst = 0;
			double sgst = 0;
			double igst = 0;
			double discount = 0;
			double credit = 0;
			double debit = 0;
			double surcharge = 0;
			String paymode = "Credit";
			String dateonly = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			for (String string : ids) {
				if (string.equals("0")) {
					continue;
				}
				int res = procurementDAO.updateChildProcDM(string, voucherno, voucherdt);
				res = procurementDAO.updatePParentProcDM(string, voucherno, voucherdt, security, sec_date, 0, parentid);
				res = procurementDAO.updateVendorProcDM(string, voucherno, voucherdt, security, sec_date);
				res = procurementDAO.saveChildDMData(string, parentid);
				res = procurementDAO.updatePoVatAllocation(string, voucherno);
				String lastdate = procurementDAO.getProcurementGRNDate(string);
				// Now change old proc id with procid
				res = procurementDAO.updateProcurementId(newprocurementid, string, lastdate, dateonly);
				res = procurementDAO.updatePoVatProcurementId(newprocurementid, string);
				// res =
				// procurementDAO.updateVendorProcurementId(newprocurementid,string);
				Procurement procurement = procurementDAO.getVendorPoDetails(string);
				vat = vat + Double.parseDouble(procurement.getVat());
				cgst = cgst + Double.parseDouble(procurement.getCgst());
				sgst = sgst + Double.parseDouble(procurement.getSgst());
				igst = igst + Double.parseDouble(procurement.getIgst());
				discount = discount + Double.parseDouble(procurement.getDiscount());
				credit = credit + Double.parseDouble(procurement.getCredit());
				debit = debit + Double.parseDouble(procurement.getDebit());
				surcharge = surcharge + Double.parseDouble(procurement.getSurcharge());
				paymode = procurement.getPaymode();

			}

			Procurement procurement = new Procurement();
			procurement.setCredit(DateTimeUtils.changeFormat(credit));
			procurement.setDebit(DateTimeUtils.changeFormat(debit));
			procurement.setVat(DateTimeUtils.changeFormat(vat));
			procurement.setCgst(DateTimeUtils.changeFormat(cgst));
			procurement.setIgst(DateTimeUtils.changeFormat(igst));
			procurement.setSgst(DateTimeUtils.changeFormat(sgst));
			procurement.setSurcharge(DateTimeUtils.changeFormat(surcharge));
			procurement.setDiscount(DateTimeUtils.changeFormat(discount));
			procurement.setVendor_id(vendor_id);
			procurement.setVoucherno(voucherno);
			procurement.setProcurementid("" + newprocurementid);
			procurement.setDate(date);
			procurement.setRemark("");
			procurement.setSecurity_date(sec_date);
			procurement.setSecurity_no(security);
			procurement.setUserid(loginInfo.getUserId());
			procurement.setDisctype("" + 0);
			procurement.setPaymode(paymode);
			int result = procurementDAO.addVendorAccountProcurement(procurement);

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			int seqno = procurementDAO.getProcurmentSeq(location);
			seqno = seqno + 1;
			int res = procurementDAO.insertProcSeqNo(location, seqno, loginInfo.getUserId(), "" + newprocurementid,
					date, "1", "" + parentid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "savepo";
	}

	public String newDmList() {
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
				if(breadcrumbs.getName().equals("DM List")){
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
				breadcrumbs.setName("DM List");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("newDmListProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("DM List");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			String todate = procurementForm.getTodate();
			String fromdate = procurementForm.getFromdate();
			//   12/11/2018
			String vendorid = procurementForm.getVendor_id();
			String invoice_created = procurementForm.getInvoice_created();
			if (invoice_created == null) {
				invoice_created = "2";
			}
			if (vendorid == null) {
				vendorid = "0";
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
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			/*
			 * UserProfile userProfile =
			 * userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
			 * int totalCount = procurementDAO.getProcurementCount(categoryid,
			 * location,fromdate,todate,voucherno);
			 * pagination.setPreperties(totalCount);
			 * 
			 * ArrayList<Procurement> procurementList =
			 * procurementDAO.getAllProcurementList(categoryid, pagination,
			 * location, voucherno,
			 * userProfile.getProcurementType(),fromdate,todate,vendorid);
			 * procurementForm.setProcurementList(procurementList);
			 */

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			int count = procurementDAO.getDmlistCount(fromdate, todate, location, vendorid, invoice_created);
			pagination.setPreperties(count);
			ArrayList<Procurement> dmlist = procurementDAO.getDmlist(fromdate, todate, pagination, location, vendorid,
					invoice_created);
			procurementForm.setDmlist(dmlist);
			pagination.setPage_records(dmlist.size());
			procurementForm.setTotalRecords(count);
			procurementForm.setPagerecords(Integer.toString(pagination.getPage_records()));

			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
			/* pagination.setPreperties(); */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newDmList";
	}

	public String setvendorfordm() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("id");

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			ArrayList<Procurement> dmlist = procurementDAO.getDMList(location, vendorid);
			int i = 0;
			StringBuffer buffer = new StringBuffer();
			for (Procurement procurement : dmlist) {
				buffer.append("<tr>");
				buffer.append("<td>");
				buffer.append("<article>");
				buffer.append("<ul class='vertical default_list' id=''>");
				buffer.append(
						"<li><label class='checkbox checkbox-custom-alt m-0 mt-5'><input onchange='calculatedmdata()' name='status"
								+ procurement.getId() + "' value='" + procurement.getId()
								+ "' type='checkbox' class='form-control case' /><i></i> </label></li>");
				buffer.append("</ul>");
				buffer.append("</article>");
				buffer.append("</td>");
				buffer.append(" <td>" + (++i) + "");
				buffer.append("<input type='hidden' id='cgst" + procurement.getId() + "' value='"
						+ procurement.getCgst() + "'>");
				buffer.append("<input type='hidden' id='sgst" + procurement.getId() + "' value='"
						+ procurement.getSgst() + "'>");
				buffer.append("<input type='hidden' id='igst" + procurement.getId() + "' value='"
						+ procurement.getIgst() + "'>");
				buffer.append("<input type='hidden' id='total" + procurement.getId() + "' value='"
						+ procurement.getTotal() + "'>");
				buffer.append("<input type='hidden' id='discount" + procurement.getId() + "' value='"
						+ procurement.getDiscount() + "'>");
				buffer.append("<input type='hidden' id='netAmt" + procurement.getId() + "' value='"
						+ procurement.getNetAmt() + "'>");
				buffer.append("</td>");
				if (procurement.getGrnno() != null) {
					if (procurement.getGrnno().equals("0")) {
						buffer.append("<td>-</td>");
					} else {
						buffer.append("<td>" + procurement.getGrnno() + "</td>");
					}
				} else {
					buffer.append("<td>-</td>");
				}
				buffer.append("<td>" + procurement.getGrndate() + "</td>");
				buffer.append("<td>" + procurement.getDelivermemoid() + "</td>");
				buffer.append("<td>" + procurement.getDelivermemodate() + "</td>");
				buffer.append("<td>" + procurement.getVendor() + "</td>");
				buffer.append("<td>-</td>");
				buffer.append("<td>" + procurement.getNetAmt() + "</td>");
				buffer.append("<td><a onclick='printDMgrn(" + procurement.getChildid() + ")'>Print</a></td>");
				buffer.append(" </tr>");
				for (Product product : procurement.getProcurmentlist()) {
					buffer.append("<tr>");
					buffer.append("<td>");
					buffer.append("</td>");
					buffer.append("<td>-<td>");
					buffer.append("<td>-</td>");
					buffer.append("<td>-</td>");
					buffer.append("<td>-</td>");
					buffer.append("<td>-</td>");
					buffer.append("<td>" + product.getProduct_name() + "</td>");
					buffer.append("<td>" + product.getTotal() + "</td>");
					buffer.append("<td>-</td>");
					buffer.append(" </tr>");
				}

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

	public String cancelPO() {
		Connection connection = null;
		try {
			String procurid = request.getParameter("procureid");
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			int result = procurementDAO.cancelPOnew(procurid);
			String resp = "";
			if (result > 0) {
				resp = "yes";
			} else {
				resp = "no";
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// lokesh 17-7-18

	public String emailPOList() {
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
				if(breadcrumbs.getName().equals("PO E-Mail log")){
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
				breadcrumbs.setName("PO E-Mail log");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("remailPOListProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("PO E-Mail log");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);

			String todate = procurementForm.getTodate();
			String fromdate = procurementForm.getFromdate();

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
			
			String voucherno = procurementForm.getVoucherno();
			voucherno = DateTimeUtils.isNull(voucherno);
			String vendor_id = procurementForm.getVendor_id();
			vendor_id = DateTimeUtils.numberCheck(vendor_id);

			connection = Connection_provider.getconnection();
			ArrayList<Procurement> emaillist = new ArrayList<Procurement>();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			emaillist = procurementDAO.getemailPOList(fromdate, todate, vendor_id, voucherno);
			procurementForm.setEmaillist(emaillist);
			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
			
			ArrayList<Vendor> vlist = procurementDAO.getVendorList();
			procurementForm.setVendorList(vlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "emailPOList";
	}

	public String getvendorstateforlongpo() throws Exception {
		Connection connection = null;
		try {
			String procurementid = request.getParameter("procurementid");
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			int result = procurementDAO.getvendorstateforlongpo(procurementid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String detailgrnreport() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			/*String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}*/
			
			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = procurementForm.getLocation();
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
			procurementForm.setLocation(location);
			
			String isfromcathlab = request.getParameter("isfromcathlab");

			if (isfromcathlab != null) {
				if (isfromcathlab.equals("")) {
					isfromcathlab = "0";
				}
			} else {
				isfromcathlab = procurementForm.getIsfromcathlab();
				if (isfromcathlab != null) {
					if (isfromcathlab.equals("")) {
						isfromcathlab = "0";
					}
				} else {
					isfromcathlab = "0";
				}
			}

			if (isfromcathlab.equals("1")) {
				location = "105";
				procurementForm.setLocation(location);
			}

			String fromdate = procurementForm.getFromdate();
			String todate = procurementForm.getTodate();
			String vendorid = procurementForm.getVendor_id();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {

				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}
			String voucherno = procurementForm.getVoucherno();
			if (voucherno != null) {
				if (voucherno.equals("")) {
					voucherno = null;
				}
			}

			procurementForm.setIsfromcathlab(isfromcathlab);

			if (vendorid != null) {
				if (vendorid.equals("0")) {
					vendorid = null;
				}
			}

			ArrayList<Procurement> procurementList = procurementDAO.getAllProcurementList(location, voucherno, fromdate,
					todate, vendorid);
			procurementForm.setProcurementList(procurementList);
			int size = procurementList.size();
			if (size > 0) {
				String totalmrp = procurementList.get(size - 1).getTotalmrp();
				String totalrate = procurementList.get(size - 1).getTotalrate();
				String tttotal = procurementList.get(size - 1).getTttotal();
				double netamtttl = procurementList.get(size - 1).getNetamtttl();
				double ttlgst = procurementList.get(size - 1).getTtlgst();
				// totalmrp,totalrate,tttotal;
				procurementForm.setTotalmrp(totalmrp);
				procurementForm.setTotalrate(totalrate);
				procurementForm.setTttotal(tttotal);
				procurementForm.setNetamtttl(netamtttl);
				procurementForm.setTtlgst(ttlgst);
			} else {
				procurementForm.setTotalmrp("0");
				procurementForm.setTotalrate("0");
				procurementForm.setTttotal("0");
			}
			procurementForm.setVendor_id(vendorid);
			procurementForm.setVoucherno(voucherno);
			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}

			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
			procurementForm.setLocation(location);
			setLetterData(location, connection);
			procurementForm.setIsfrombincard(1);
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			procurementForm.setLocationListPharmacy(locationlist);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "detailgrnreport";

	}
	
	private void setLetterData(String location,Connection connection) {
		try {
			location = DateTimeUtils.isNull(location);
			if (location.equals("32") || location.equals("33") || location.equals("34")) {
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());
				procurementForm.setClinicName(userProfile.getClinicname());
				procurementForm.setClinicaddress(userProfile.getAddress());
				procurementForm.setLandLine(userProfile.getPhone());
				procurementForm.setFullname(userProfile.getFullname());
				procurementForm.setEmail(userProfile.getEmail());
				procurementForm.setPlace(userProfile.getCity());
				procurementForm.setWebsiteUrl(userProfile.getWebsite());
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				procurementForm.setClinicLogo(clinic.getUserImageFileName());
			} else {
				// letter head
				Clinic clinic = new Clinic();
				ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
				AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
				ArrayList<Clinic> locationAdressList = new ArrayList<Clinic>();
				accountsDAO.getLocationAddress(loginInfo.getClinicid());
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				procurementForm.setClinicName(clinic.getClinicName());
				procurementForm.setClinicOwner(clinic.getClinicOwner());
				procurementForm.setOwner_qualification(clinic.getOwner_qualification());
				procurementForm.setLocationAdressList(locationAdressList);
				// procurementForm.setAddress(clinic.getAddress());
				procurementForm.setLandLine(clinic.getLandLine());
				procurementForm.setClinicemail(clinic.getEmail());
				procurementForm.setWebsiteUrl(clinic.getWebsiteUrl());
				procurementForm.setClinicLogo(clinic.getUserImageFileName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String showponotification() throws Exception {
		Connection connection = null;
		int i = 0;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String fromDate, toDate;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();

			toDate = dateFormat.format(cal.getTime());
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -5);
			fromDate = dateFormat1.format(cal1.getTime());

			ArrayList<Procurement> notificationlist = procurementDAO.getNotificationlist(fromDate, toDate);

			for (Procurement procurement : notificationlist) {
				i = i + 1;
				buffer.append("<tr>");
				buffer.append("<td>" + i + "</td>");

				buffer.append("<td>" + procurement
						.getGrnno()/* +"/"+procurement.getGrnseqno()+ */ + "</td>");

				buffer.append("<td>" + procurement.getGrndate() + "</td>");
				buffer.append("<td>" + procurement.getVendor() + "</td>");
				buffer.append("<td>" + procurement.getLocationname() + "</td>");
				// buffer.append("<td>"+procurement.getConfirm()+"</td");
				buffer.append("</tr>");
			}
			if (buffer.toString() == null) {
				buffer.append("");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String printpayment() {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String payid = request.getParameter("payid");
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			Procurement procurement = poPaymenytDAO.getPaymentDetailsParent(payid);
			ArrayList<Procurement> payrecieptList = poPaymenytDAO.getPaymentDetailsProcurementParent(payid);
			/*
			 * procurementForm.setAmount(procurement.getAmount());
			 * procurementForm.setPaymentType(procurement.getPaymentType());
			 * procurementForm.setBankName(procurement.getBankName());
			 * procurementForm.setCheqNo(procurement.getCheqNo());
			 * procurementForm.setDate(procurement.getDate());
			 * procurementForm.setCheqType(procurement.getCheqType());
			 * procurementForm.setHandoverTo(procurement.getHandoverTo());
			 * procurementForm.setVoucherno(procurement.getVoucherno());
			 * procurementForm.setCheq_receiver(procurement.getCheq_receiver());
			 * procurementForm.setVendor(procurement.getVendor());
			 */
			procurementForm.setRefundcheck(procurement.getRefundcheck());
			procurementForm.setSumofreturn(procurement.getSumofreturn());
			procurementForm.setTotal_amt(procurement.getTotal_amt());
			procurementForm.setTotalpaid(procurement.getTotalpaid());
			procurementForm.setPrintedby(loginInfo.getUserId());
			procurementForm.setUserid(procurement.getUserid());
			procurementForm.setInvoiceid("" + procurement.getId());
			procurementForm.setDate(procurement.getDate());
			procurementForm.setPayrecieptList(payrecieptList);
			int size = payrecieptList.size();
			if (size > 0) {
				String amount = payrecieptList.get(size - 1).getTotal();
				String vendor = payrecieptList.get(size - 1).getVendor();
				String address = payrecieptList.get(size - 1).getAddress();
				String paymentType = payrecieptList.get(size - 1).getPaymentType();
				String cheqNo = payrecieptList.get(size - 1).getCheqNo();
				procurementForm.setAmount(amount);
				procurementForm.setVendor(vendor);
				procurementForm.setAddress(address);
				procurementForm.setPaymentType(paymentType);
				procurementForm.setCheqNo(cheqNo);
			} else {
				procurementForm.setAmount("0");
				procurementForm.setVendor("");
				procurementForm.setAddress("");
				procurementForm.setPaymentType("");
				procurementForm.setCheqNo("");
			}
			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			procurementForm.setClinicName(clinic.getClinicName());
			procurementForm.setClinicOwner(clinic.getClinicOwner());
			procurementForm.setOwner_qualification(clinic.getOwner_qualification());

			procurementForm.setClinicaddress(clinic.getAddress());
			procurementForm.setLandLine(clinic.getLandLine());
			procurementForm.setClinicemail(clinic.getEmail());
			procurementForm.setWebsiteUrl(clinic.getWebsiteUrl());
			procurementForm.setClinicLogo(clinic.getUserImageFileName());
			procurementForm.setGstnno(clinic.getGstn_no());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printpayment";
	}

	public String agreementdashboard() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			String fromdate = procurementForm.getFromdate();
			String todate = procurementForm.getTodate();

			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {
				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
			String voucherno = procurementForm.getVoucherno();
			if (voucherno != null) {
				if (voucherno.equals("")) {
					voucherno = null;
				}
			}

			int totalCount = procurementDAO.getAgreementCount(location, fromdate, todate, voucherno);
			pagination.setPreperties(totalCount);

			ArrayList<Procurement> agreementList = procurementDAO.getAllAgreementList(pagination, location, voucherno,
					fromdate, todate);
			procurementForm.setAgreementList(agreementList);

			pagination.setPage_records(agreementList.size());
			procurementForm.setTotalRecords(totalCount);
			procurementForm.setPagerecords(Integer.toString(pagination.getPage_records()));

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());
			procurementForm.setDate(date);
			procurementForm.setVoucherno(voucherno);

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "agreementdashboard";
	}

	public String deletemultiplenewgrn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("ids");

			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}
			if (location == "") {
				location = "0";
			}
			if (id != null) {
				for (String catalogueid : id.split(",")) {
					if (catalogueid.equals("0")) {
						continue;
					}
					if (catalogueid.equals("")) {
						continue;
					}
					int res = procurementDAO.deleteNewGrnBycatId(catalogueid, location);
				}
			}

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

	public String agreementconfirmpopup() throws Exception {
		try {
			if (!verifyLogin(request)) {
				return "login";
			}

			Connection connection = Connection_provider.getconnection();
			StringBuilder buffer1 = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer1.append(line);
			}
			String data = buffer1.toString();
			JSONObject jsonObject = new JSONObject(data);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String agreementidid = jsonObject.getString("id");

			Vendor vendor = procurementDAO.getVendorDetailsOfAgreement(agreementidid);
			ArrayList<Product> allprocurementList = procurementDAO.getAgreementListBeforeProcurement(agreementidid);
			String date = "";
			float totalAmt = 0;
			double totalnetamount = 0;
			double gstamount = 0;
			double nettotalx = 0;
			int i = 0;
			for (Product proc : allprocurementList) {

				buffer.append("<tr>");
				buffer.append("<td>" + (++i) + " </td><input type='hidden' class='myclassJ' value='" + i + "' />");
				buffer.append("<td>" + proc.getProduct_name() + "</td>");
				buffer.append("<td>" + proc.getPro_code() + "</td>");
				buffer.append("<input type='hidden' value='" + proc.getPurchase_price() + "' id='previouspurprice" + i
						+ "' />");
				buffer.append("<td><input type='number' id='approve_gst" + i + "' class='form-control' value='"
						+ proc.getVat() + "' readonly='readonly'  /></td>");
				buffer.append("<td><input type='number' id='qty" + i + "' onkeyup='setAllAgreConfirmData()'  name='qty"
						+ proc.getId() + "' class='form-control' value='" + proc.getQuantity() + "' /></td>");
				buffer.append("<td><input type='number' id='rate" + i + "' name='ratepur" + proc.getId()
						+ "' onchange='setAllAgreConfirmData()' class='form-control'  value='"
						+ proc.getPurchase_price() + "'   /></td>");
				buffer.append("<td><input type='number' id='total" + i + "' class='form-control' value='"
						+ proc.getTotal() + "' readonly='readonly'  /></td>");

				//   23 June 2018
				double purchaseprice = 0;
				double vat = 0;
				double qty = 0;
				purchaseprice = Double.parseDouble(proc.getPurchase_price());
				vat = Double.parseDouble(proc.getVat());
				qty = Double.parseDouble(proc.getQuantity());

				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				double nettotal = netamount + Float.parseFloat(proc.getTotal());
				double temp = (nettotal * Double.parseDouble(proc.getDiscount())) / 100;
				nettotal = nettotal - temp;
				buffer.append("<td><input type='number' id='netgsttotal" + i + "' class='form-control' value='"
						+ netamount + "' readonly='readonly'  /></td>");
				buffer.append("<td><input type='number' class='form-control' onchange='setAllAgreConfirmData()' value='"
						+ proc.getDiscount() + "' id='discounts" + i + "' name='discounts" + proc.getId()
						+ "'   /></td>");
				buffer.append("<td><input type='number' id='nettotal" + i + "' class='form-control' value='"
						+ Math.round(nettotal * 100.0) / 100.0 + "' readonly='readonly'  /></td>");

				// lokesh
				buffer.append("<td><input type='number' id='instock" + i + "' class='form-control' value='"
						+ proc.getStockqty() + "' readonly='readonly'  /></td>");

				buffer.append("<td><input type='number' id='oldpurprise" + i + "' class='form-control' value='"
						+ proc.getOldvenderpurprise() + "' readonly='readonly'  /></td>");

				buffer.append("<td><a href='#' onclick=deleteAgreement(this,'" + proc.getId()
						+ "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>");
				buffer.append("</tr>");
				date = proc.getDate();
				totalAmt = totalAmt + Float.parseFloat(proc.getTotal());
				totalnetamount = totalnetamount + netamount;
				gstamount = gstamount + netamount;
				nettotalx = nettotal + nettotalx;
			}
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append(
					"<tr><td></td><td></td><td></td><td></td><td><b>Total :</b></td><td></td><td><b id='totalAll'>"
							+ Math.round(totalAmt * 100.0) / 100.0 + "</b></td><td><b id='netgsttotalAll'>"
							+ Math.round(gstamount * 100.0) / 100.0 + "</b></td><td></td><td><b id='nettotalAll'>"
							+ Math.round(nettotalx * 100.0) / 100.0 + "</b></td><td></td><td></td><td></td></tr>");

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", buffer.toString());
			jsonobj.put("vendorname", vendor.getName());
			jsonobj.put("vendorid", vendor.getId());
			jsonobj.put("agreementidid", agreementidid);
			jsonobj.put("datetime", date);
			jsonobj.put("vendormail", vendor.getVendoremail());
			jsonobj.put("tfootproductlist", buffer2.toString());

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addnewagreementproduct() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String catalogueid = request.getParameter("product_id");
			String count = request.getParameter("count");
			String qty = request.getParameter("qty");
			String vendorid = request.getParameter("vendorid");
			String procurementid = request.getParameter("procurementid");
			String warehouseid = request.getParameter("warehouse");
			String discount = request.getParameter("discount");
			String rate1 = request.getParameter("rate");
			if (discount != null) {
				if (discount.equals("")) {
					discount = "0";
				}
			} else {
				discount = "0";
			}
			Product product = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			product.setProcurementid(procurementid);
			product.setVendor(vendorid);
			product.setVendor_id(vendorid);
			product.setCatalogueid(catalogueid);
			product.setWarehouse_id(warehouseid);
			product.setDate(date);
			product.setQty(Double.parseDouble(qty));
			String datetime[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			StringBuffer buffer = new StringBuffer();
			boolean chisexist = procurementDAO.isThisProductExistINAgreement(procurementid, catalogueid);
			if (!chisexist) {
				product.setDiscount(discount);
				product.setProduct_id("0");
				int longo = procurementDAO.saveNewTempPo(product);
				product.setUserid(loginInfo.getUserId());
				product.setLastmodified(datetime[0]);
				product.setLocation(warehouseid);
				// for medicine
				product.setPurchase_price(rate1);
				// product.setCatalogueid(catalogueid);
				product.setParentid(procurementid);
				int res = procurementDAO.saveChildAgreement(product);
				int result = procurementDAO.updateTempPoStatus(longo);

				Product proc = procurementDAO.getAgreementListBeforeProcurement(procurementid, catalogueid);
				// lokesh
				proc.setStockqty(procurementDAO.getStock(catalogueid, proc.getLocation()));
				proc.setOldvenderpurprise(procurementDAO.getoldpurprice(catalogueid));

				double rate = Double.parseDouble(proc.getPurchase_price());
				int q = Integer.parseInt(qty);
				String total = DateTimeUtils.changeFormat(rate * q);

				/*
				 * buffer.append("<td>" + count + "  </td>"); buffer.
				 * append("<td><input type='text' class='form-control' value='"
				 * + master.getProduct_name() +
				 * "' readonly='readonly' /> </td>");
				 * buffer.append("<td><input type='number' name='qty" + res +
				 * "' class='form-control' value='" + qty + "' /></td>");
				 * buffer.
				 * append("<td><input type='number' class='form-control' value='"
				 * + master.getPurchase_price() +
				 * "' readonly='readonly'  /></td>"); buffer.
				 * append("<td><input type='number' class='form-control' value='"
				 * + total + "' readonly='readonly'  /></td>");
				 * buffer.append("<td><a href='#' onclick=deletePO(this,'" + res
				 * +
				 * "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>"
				 * );
				 */

				buffer.append("<td>" + count + " </td><input type='hidden' class='myclassJ' value='" + count + "' />");
				buffer.append("<td>" + proc.getProduct_name() + "</td>");
				buffer.append("<td>" + proc.getPro_code() + "</td>");
				buffer.append("<td><input type='number' id='approve_gst" + count + "' class='form-control' value='"
						+ proc.getVat() + "' readonly='readonly'  /></td>");
				buffer.append(
						"<td><input type='number' id='qty" + count + "' onkeyup='setAllAgreConfirmData()'  name='qty"
								+ proc.getId() + "' class='form-control' value='" + proc.getQuantity() + "' /></td>");
				buffer.append("<input type='hidden' value='" + proc.getPurchase_price() + "' id='previouspurprice"
						+ count + "' />");
				buffer.append("<td><input type='number' id='rate" + count + "' name='ratepur" + proc.getId()
						+ "' onchange='setAllAgreConfirmData()' class='form-control'  value='"
						+ proc.getPurchase_price() + "'   /></td>");

				buffer.append("<td><input type='number' id='total" + count + "' class='form-control' value='"
						+ proc.getTotal() + "' readonly='readonly'  /></td>");

				double purchaseprice = 0;
				double vat = 0;
				double qty1 = 0;
				purchaseprice = Double.parseDouble(proc.getPurchase_price());
				vat = Double.parseDouble(proc.getVat());
				qty1 =Double.parseDouble(proc.getQuantity());
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty1;
				netamount = Math.round(netamount * 100.0) / 100.0;
				double nettotal = netamount + Float.parseFloat(proc.getTotal());
				double temp = (nettotal * Double.parseDouble(proc.getDiscount())) / 100;
				nettotal = nettotal - temp;
				buffer.append("<td><input type='number' id='netgsttotal" + count + "' class='form-control' value='"
						+ netamount + "' readonly='readonly'  /></td>");
				buffer.append("<td><input type='number' class='form-control' onchange='setAllAgreConfirmData()' value='"
						+ proc.getDiscount() + "' id='discounts" + count + "' name='discounts" + proc.getId()
						+ "'   /></td>");
				buffer.append("<td><input type='number' id='nettotal" + count + "' class='form-control' value='"
						+ nettotal + "' readonly='readonly'  /></td>");

				// lokesh
				buffer.append("<td><input type='number' id='instock" + count + "' class='form-control'  value='"
						+ proc.getStockqty() + "' readonly='readonly'  /></td>");

				buffer.append("<td><input type='number' id='oldpurprise" + count + "' class='form-control'  value='"
						+ proc.getOldvenderpurprise() + "' readonly='readonly'  /></td>");

				buffer.append("<td><a href='#' onclick=deleteAgreement(this,'" + proc.getId()
						+ "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>");

			} else {
				buffer.append("0");
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

	public String deleteagreementajax() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			int res = procurementDAO.deleteAgreement(id);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("okok");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String confirmagreement() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String procurementid = request.getParameter("proc_id");
			String remark = request.getParameter("remark");
			String saveconfirmpo = request.getParameter("saveconfirmpo");
			String cancelconfirmpo = request.getParameter("cancelconfirmpo");

			if (cancelconfirmpo != null) {
				if (cancelconfirmpo.equals("")) {
					cancelconfirmpo = "0";
				}
			} else {
				cancelconfirmpo = "0";
			}

			if (saveconfirmpo != null) {
				if (saveconfirmpo.equals("")) {
					saveconfirmpo = "0";
				} else if (saveconfirmpo.equals("1")) {
					saveconfirmpo = "1";
				}
			} else {
				saveconfirmpo = "0";
			}
			if (cancelconfirmpo.equals("1")) {
				String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				int result = procurementDAO.cancelAgreement(procurementid, loginInfo.getUserId(), datetime, remark);
			} else {
				if (saveconfirmpo.equals("1")) {
					ArrayList<Product> allprocurementList = procurementDAO
							.getAgreementListBeforeProcurement(procurementid);
					for (Product product : allprocurementList) {
						String qty = request.getParameter("qty" + product.getId());
						String discount = request.getParameter("discounts" + product.getId());
						String ratepur = request.getParameter("ratepur" + product.getId());
						int res = procurementDAO.updateAgreementData(qty, discount, product.getId(), ratepur);
					}
				} else {
					ArrayList<Product> allprocurementList = procurementDAO
							.getAgreementListBeforeProcurement(procurementid);
					for (Product product : allprocurementList) {
						String qty = request.getParameter("qty" + product.getId());
						String discount = request.getParameter("discounts" + product.getId());
						String ratepur = request.getParameter("ratepur" + product.getId());
						int res = procurementDAO.updateAgreementData(qty, discount, product.getId(), ratepur);
					}
					String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					int result = procurementDAO.updateConfirmAgreementStatus(procurementid, "1", loginInfo.getUserId(),
							datetime, remark);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "agreementdash";
	}

	public String agreementorderpopup() throws Exception {
		try {
			if (!verifyLogin(request)) {
				return "login";
			}

			Connection connection = Connection_provider.getconnection();
			StringBuilder buffer1 = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer1.append(line);
			}
			String data = buffer1.toString();
			JSONObject jsonObject = new JSONObject(data);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			StringBuffer buffer = new StringBuffer();
			String agreementidid = jsonObject.getString("id");

			Vendor vendor = procurementDAO.getVendorDetailsOfAgreement(agreementidid);
			ArrayList<Product> allprocurementList = procurementDAO.getAgreementListBeforeProcurement(agreementidid);
			String date = "";
			float totalAmt = 0;
			double totalnetamount = 0;
			double gstamount = 0;
			double nettotalx = 0;
			int j = 0;
			for (Product proc : allprocurementList) {
				int orderqty = procurementDAO.getAgreementOrderQty(proc.getId());
				int i = proc.getId();
				buffer.append("<tr>");
				buffer.append("<td><input type='checkbox' onclick='setAllAgreOrderData()' class='myclassJ' value='" + i
						+ "' />  " + (++j) + " </td>");
				buffer.append("<td>" + proc.getProduct_name() + "</td>");
				buffer.append("<td>" + proc.getPro_code() + "</td>");
				buffer.append("<input type='hidden' value='" + proc.getPurchase_price() + "' id='previouspurprice" + i
						+ "' />");
				buffer.append("<input type='hidden' value='" + proc.getQuantity() + "' id='agreementqty" + i + "' />");
				buffer.append("<td><input type='number' id='approve_gst" + i + "' class='form-control' value='"
						+ proc.getVat() + "' readonly='readonly'  /></td>");
				buffer.append("<td><input type='number'  readonly='readonly' id='agreementqty" + i
						+ "'   class='form-control' value='" + proc.getQuantity() + "' /></td>");
				buffer.append("<td><input type='number'  readonly='readonly' id='orderedqty" + i
						+ "'   class='form-control' value='" + orderqty + "' /></td>");
				buffer.append("<td><input type='number' id='qty" + i + "' onkeyup='setAllAgreOrderData()'  name='qty"
						+ proc.getId() + "' class='form-control' value='" + 0 + "' /></td>");
				buffer.append("<td><input type='number' id='rate" + i + "' readonly='readonly' name='ratepur"
						+ proc.getId() + "' onchange='setAllAgreConfirmData()' class='form-control'  value='"
						+ proc.getPurchase_price() + "'   /></td>");
				buffer.append("<td><input type='number' id='total" + i + "' class='form-control' value='" + 0
						+ "' readonly='readonly'  /></td>");

				//   23 June 2018
				double purchaseprice = 0;
				double vat = 0;
				int qty = 0;
				purchaseprice = Double.parseDouble(proc.getPurchase_price());
				vat = Double.parseDouble(proc.getVat());
				qty = 0;

				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				double nettotal = netamount + Float.parseFloat(proc.getTotal());
				double temp = (nettotal * Double.parseDouble(proc.getDiscount())) / 100;
				nettotal = nettotal - temp;
				buffer.append("<td><input type='number' id='netgsttotal" + i + "' class='form-control' value='"
						+ netamount + "' readonly='readonly'  /></td>");
				buffer.append(
						"<td><input type='number' class='form-control' readonly='readonly' onchange='setAllAgreConfirmData()' value='"
								+ proc.getDiscount() + "' id='discounts" + i + "' name='discounts" + proc.getId()
								+ "'   /></td>");
				buffer.append("<td><input type='number' id='nettotal" + i + "' class='form-control' value='" + 0
						+ "' readonly='readonly'  /></td>");

				buffer.append("<td><a href='#' onclick=deleteOrderAgreement(this,'" + proc.getId()
						+ "')><i class='fa fa-times text-danger fa-2x' aria-hidden='true'></i></a></td>");
				buffer.append("</tr>");
				date = proc.getDate();
				totalAmt = totalAmt + Float.parseFloat(proc.getTotal());
				totalnetamount = totalnetamount + netamount;
				gstamount = gstamount + netamount;
				nettotalx = nettotal + nettotalx;
			}
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append(
					"<tr><td></td><td></td><td></td><td></td><td></td><td></td><td><b>Total :</b></td><td></td><td><b id='totalAll'>"
							+ Math.round(totalAmt * 100.0) / 100.0 + "</b></td><td><b id='netgsttotalAll'>"
							+ Math.round(gstamount * 100.0) / 100.0 + "</b></td><td></td><td><b id='nettotalAll'>"
							+ Math.round(nettotalx * 100.0) / 100.0 + "</b></td><td></td></tr>");

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("productlist", buffer.toString());
			jsonobj.put("vendorname", vendor.getName());
			jsonobj.put("vendorid", vendor.getId());
			jsonobj.put("agreementidid", agreementidid);
			jsonobj.put("datetime", date);
			jsonobj.put("vendormail", vendor.getVendoremail());
			jsonobj.put("tfootproductlist", buffer2.toString());

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String orderagreement() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

			String categoryid = (String) session.getAttribute("category");

			if (categoryid == null) {
				categoryid = "2";
			}
			String location = "0";
			String proc_id = request.getParameter("proc_id");
			String cancelorderagree = request.getParameter("cancelorderagree");
			String orderchildids = request.getParameter("orderchildids");
			if (cancelorderagree != null) {
				if (cancelorderagree.equals("")) {
					cancelorderagree = "0";
				}
			} else {
				cancelorderagree = "0";
			}
			String agreementid = proc_id;
			if (orderchildids != null) {
				if (orderchildids.equals("")) {
					orderchildids = "0";
				}
			} else {
				orderchildids = "0";
			}
			int isagrement = 1;
			if (cancelorderagree.equals("1")) {

			} else {
				// Normal GRN With PO code
				String datetime[] = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
				for (String childid : orderchildids.split(",")) {
					if (childid.equals("0")) {
						continue;
					}
					if (childid.equals("")) {
						continue;
					}
					Product product = procurementDAO.getAgreementBeforeProcurement(childid);
					product.setUserid(loginInfo.getUserId());
					product.setCategory_id(categoryid);
					product.setLastmodified(datetime[0]);
					// for medicine
					String catalogueid = product.getCatalogueid();
					product.setVendor(product.getVendor_id());
					String qty = request.getParameter("qty" + childid);
					product.setIs_po_prod(1);
					int res = inventoryProductDAO.savetempProduct(product);

					int result = procurementDAO.addTempPo(String.valueOf(res), product.getVendor_id(),qty,
							product.getId(), childid, product.getPurchase_price(), isagrement, product.getVat(),
							loginInfo.getLoginsessionid(),"","","0");
					location = product.getLocation();
				}

				int procurementid = 0;
				int grnno = 0;
				ArrayList<Product> polistByVendor = procurementDAO.getAllPoListByVendor(loginInfo.getLoginsessionid(),"0");
				for (Product product : polistByVendor) {
					String date1 = procurementForm.getFromdate();
					String date = "";
					if (date1 != null) {
						if (!date1.equals("")) {
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							String time = dateFormat.format(cal.getTime());
							date = DateTimeUtils.getCommencingDate1(date1);
							date = date + " " + time;
						} else {
							date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
						}
					} else {
						date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					}
					procurementid = procurementDAO.saveParengtPrecurementData(product.getVendor_id(), date, isagrement,
							agreementid, 1,"","0");
					grnno = procurementDAO.saveParentGrnData(product.getVendor_id(), date, true,"","0");

					ArrayList<Product> polist = procurementDAO.getPoProductByVendor(product.getVendor_id(),
							loginInfo.getLoginsessionid(),"0");
					int grnseqno = procurementDAO.getMaxGRNSeqNo(location);
					for (Product poproduct : polist) {
						poproduct.setLocation(location);
						poproduct.setGrnno(grnno);
						String catalogueid = procurementDAO.getCatalogueIdFromProduct(poproduct.getId());
						poproduct.setCatalogueid(catalogueid);
						product.setGrnwithpo_child(1);
						int result = procurementDAO.saveProcurementData(poproduct, ""+poproduct.getQty(), 0,
								product.getVendor_id(), "0", procurementid, isagrement, 0, poproduct.getVat());
						// result =
						// procurementDAO.updateProcurementStatus(poproduct.getId(),
						// "0");
					}
					grnseqno = grnseqno + 1;
					int res = procurementDAO.updateProcurmentSeqNo(grnseqno, procurementid);
				}
				int result = procurementDAO.truncateTempPo(loginInfo.getLoginsessionid());
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}
		return "agreementdash";
	}

	public String deletereturngrn() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");

			// int res = procurementDAO.deleteNewGrn(id);
			int res = procurementDAO.deleteReturnGrnProduct(id);

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

	public String vendorvoucherexistance() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String isfromeditpo = request.getParameter("isfromeditpo");
			String procurementid = request.getParameter("procurementid");
			String voucher = request.getParameter("voucher");
			if (isfromeditpo != null) {
				if (isfromeditpo.equals("")) {
					isfromeditpo = "0";
				}
			} else {
				isfromeditpo = "0";
			}
			int res = procurementDAO.vendorVoucherExistance(vendorid, isfromeditpo, procurementid, voucher);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + res + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String vendorvoucherexistancefordm() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String voucher = request.getParameter("voucherno");
			int res = procurementDAO.vendorVoucherExistanceForDm(vendorid, voucher);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + res + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addnewproductingrnwithpo() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String vendorid = request.getParameter("vendorid");
			String procurementid = request.getParameter("procurementid");
			String count = request.getParameter("count");
			String location = request.getParameter("location");
			String grnno = request.getParameter("grnno");
			String catalogueid = request.getParameter("catalogueid");
			String parentpoid = request.getParameter("parentpoid");

			Product product = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			product.setProcurementid(procurementid);
			product.setVendor(vendorid);
			product.setVendor_id(vendorid);
			product.setCatalogueid(catalogueid);
			product.setWarehouse_id(location);
			product.setLocation(location);
			product.setDate(date);
			product.setQty(0.0);

			StringBuffer buffer = new StringBuffer();
			product.setGrnno(Integer.parseInt(grnno));
			product.setDiscount("0");
			int pid = inventoryProductDAO.savetempProduct(product);
			product.setProduct_id(String.valueOf(pid));
			product.setId(pid);
			int longo = procurementDAO.saveNewTempPo(product);
			product.setNewpo(longo);
			product.setParentpoid(Integer.parseInt(parentpoid));
			if (Integer.parseInt(parentpoid) > 0) {
				product.setProc_condition(1);
			}
			int res = procurementDAO.saveProcurementData(product, "0", 0, vendorid, "0",
					Integer.parseInt(procurementid), 0, 1, product.getVat());

			Product products = procurementDAO.getProcurementDataBeforeProcurement(procurementid, res);
			if (products.getPro_code() == null) {
				products.setPro_code("");
			}
			buffer.append("<tr>");
			buffer.append("<td>" + count + "</td>");
			buffer.append("<td><input type='checkbox' id='checkbox "+products.getId() + "' class='case' class='form-control' onclick='chngecolor(this.id,"+product.getId()+"' />");
            //buffer.append("<td></td>");
			buffer.append("<td><a href='#' onclick='viewallsupplier(" + products.getProduct_id() + ")'>"
					+ products.getPro_code() + "-" + products.getProduct_name() + "</a>");
			buffer.append("<input type='hidden' value='" + products.getParentpoid() + "' name='procurements[" + count
					+ "].parentpoid' />");
			buffer.append("<input type='hidden' value='" + count + "' class='dclass' />");
			buffer.append(
					"<input type='hidden' value='" + products.getId() + "' name='procurements[" + count + "].id' />");
			buffer.append("<input type='hidden' value='" + products.getProduct_id() + "' name='procurements[" + count
					+ "].product_id' />");
			buffer.append("<input type='hidden' value='" + products.getMinorder() + "' id='minorder" + count + "' />");
			buffer.append(
					"<input type='hidden' value='" + products.getMaxorder() + "' id='maxorder" + count + "' /> <br>");
			buffer.append("<small style='font-size: 11px;'>(" + products.getPack() + " / Regular) </small>");
			buffer.append("<input type='text' name='procurements[" + count + "].barcode' id='barcode" + count
					+ "' placeholder='Barcode' /></td>");
			buffer.append(
					"<td><input style='margin: 0;padding: 0;outline: 0;' type='number' onkeyup='calSalPer()' class='form-control' value='"
							+ products.getPack() + "' ");
			buffer.append("name='procurements[" + count + "].pack' id='pack" + count + "' /></td> ");
			//buffer.append("<td>" + products.getQuantity() + "</td>");
			//we get error while we doing grn with po
			buffer.append("<td><span id='qun" + count + "'>"+products.getQuantity()+"</span></td>");
			buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='text' class='form-control' value='"
					+ products.getHsnno() + "' placeholder='hsn no' ");
			buffer.append("name='procurements[" + count + "].hsnno' id='hsnno" + count + "' /></td> ");
			buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='text' class='form-control' value='"
					+ products.getMfg() + "' placeholder='mfg' ");
			buffer.append("name='procurements[" + count + "].mfg' id='mfg" + count + "' /></td> ");
			buffer.append(
					"<td><input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' value='"
							+ products.getMrp() + "' placeholder='mrp' ");
			buffer.append(
					"onkeyup='calSalPer()' name='procurements[" + count + "].mrp' id='mrp" + count + "' /></td> ");
			buffer.append(
					"<td><input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' placeholder='sale_price' value='"
							+ products.getSale_price() + "' ");
			buffer.append("name='procurements[" + count + "].sale_price' id='sale_price" + count + "' /> </td>");
			buffer.append(
					"<td><select style='margin: 0;padding: 0;outline: 0;' class='form-control' onchange='calVatTotal()' name='procurements["
							+ count + "].vat' id='vat" + count + "'> ");
			buffer.append("<option value='0'>GST</option>");
			if (products.getVat().equals("0")) {
				buffer.append("<option selected='selected' value='0'>0%</option>");
			} else {
				buffer.append("<option value='0'>0%</option>");
			}
			if (products.getVat().equals("5")) {
				buffer.append("<option selected='selected' value='5'>5%</option>");
			} else {
				buffer.append("<option value='5'>5%</option>");
			}
			if (products.getVat().equals("12")) {
				buffer.append("<option selected='selected' value='12'>12%</option>");
			} else {
				buffer.append("<option value='12'>12%</option>");
			}
			if (products.getVat().equals("18")) {
				buffer.append("<option selected='selected' value='18'>18%</option>");
			} else {
				buffer.append("<option value='18'>18%</option>");
			}
			if (products.getVat().equals("28")) {
				buffer.append("<option selected='selected' value='28'>28%</option>");
			} else {
				buffer.append("<option value='28'>28%</option>");
			}
			buffer.append("</select></td>");
			buffer.append("<td><input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' ");
			buffer.append("placeholder='Rate' value='" + products.getPurchase_price() + "' ");
			buffer.append("name='procurements[" + count + "].purchase_price' ");
			buffer.append("onkeyup='calTotalAmt()' id='purchase_price" + count + "' /></td> ");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'> ");
			buffer.append("<input style='margin: 0;padding: 0;outline: 0;' type='text' class='form-control' value='' ");
			buffer.append("placeholder='batch no' name='procurements[" + count + "].batch_no' id='batch_no" + count
					+ "' /></td> ");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'>");
			buffer.append(
					"<input style='margin: 0;padding: 0;outline: 0;' type='text' class='form-control' placeholder='MM/YYYY' ");
			buffer.append("name='procurements[" + count + "].expiry_date' id='expiry_date" + count + "' /> </td>");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'>");
			buffer.append(
					"<input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' placeholder='Pack Qty' ");
			buffer.append("onkeyup='calTotalAmt()' name='procurements[" + count + "].received_qty' id='received_qty"
					+ count + "' /></td> ");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'> ");
			buffer.append(
					"<input style='margin: 0;padding: 0;outline: 0;' type='number' style='background-color: #ccc;' ");
			buffer.append("class='form-control' readonly='readonly' placeholder='Rec Qty' ");
			buffer.append(
					"name='procurements[" + count + "].newreceived_qty' id='newreceived_qty" + count + "' /></td> ");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'>");
			buffer.append(
					"<input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' placeholder='Discount' ");
			buffer.append("onkeyup='calTotalAmt()' name='procurements[" + count + "].discount' id='discount" + count
					+ "' /></td> ");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'> ");
			buffer.append(
					"<input type='number' style='margin: 0;padding: 0;outline: 0;' class='form-control' placeholder='Free' ");
			buffer.append("name='procurements[" + count + "].freeqty' id='freeqty" + count + "' /></td> ");
			buffer.append("<td style='background-color: rgba(210, 105, 30, 0.11);'> ");
			buffer.append(
					"<select name='procurements[" + count + "].shelf' id='shelf" + count + "' class='form-control'> ");
			for (Master master : products.getShelfList()) {
				if (master.getStatus() == 1) {
//					System.out.println("First Shelfid selected"+master.getId()+" Name"+master.getName());
					buffer.append("<option value='" + master.getName() + "' selected='selected'>" + master.getName()
							+ "</option> ");
				} else {
//					System.out.println("First Shelfid"+master.getId()+" Name"+master.getName());
					buffer.append("<option value='" + master.getName() + "'>" + master.getName() + "</option> ");
				}
			}
			buffer.append("</select>");
			
			buffer.append("<select name='procurements[" + count + "].secondary_shelf' id='secondary_shelf" + count + "' class='form-control'> ");
			for (Master master : products.getSecondshelfList()) {
				if (master.getStatus() == 1) {
//					System.out.println("Second Shelfid selected"+master.getId()+" Name"+master.getName());
					buffer.append("<option value='" + master.getName() + "' selected='selected'>" + master.getName()
							+ "</option> ");
				} else {
//					System.out.println("Second Shelfid"+master.getId()+" Name"+master.getName());
					buffer.append("<option value='" + master.getName() + "'>" + master.getName() + "</option> ");
				}
			}
			buffer.append("</select>");
			
			buffer.append("</td>");
			buffer.append("<td><i class='fa fa-plus-circle' aria-hidden='true' title='Add Batch' ");
			buffer.append("onclick='addProductForGrnWithPoOtherBatch(" + products.getVendor_id() + ","
					+ products.getProcurementid() + "," + products.getLocation() + "," + products.getGrnno() + ","
					+ products.getCatalogueid() + "," + products.getParentpoid() + ")' ");
			buffer.append("style='color: #6699CC; cursor: pointer;'></i> ");
			buffer.append("<input type='hidden' id='newproduct" + count + "' value='0' name='procurements[" + count
					+ "].newproduct'></td> ");
			buffer.append("<td>Rs.<span id='amount" + count + "'>00.00</span></td> ");
			buffer.append("<td>Rs.<span id='gstcalamount" + count + "'>00.00</span></td>");
			buffer.append("<td>Rs.<span id='netamount" + count + "'>00.00</span></td>");
			if(loginInfo.isParihar()) {
				//For parihar scheme rate 
				buffer.append(
						"<input style='margin: 0;padding: 0;outline: 0;' type='number' class='form-control' placeholder='sceme' ");
				buffer.append("onkeyup='calTotalAmt()' name='procurements[" + count + "].scheme' id='scheme" + count
						+ "' /></td> ");
			}else {
				buffer.append(
						"<input style='margin: 0;padding: 0;outline: 0;' type='hidden' class='form-control' placeholder='scheme' ");
				buffer.append("onkeyup='calTotalAmt()' name='procurements[" + count + "].scheme' id='scheme" + count
						+ "' /></td> ");
			}
			//buffer.append("<td class='hidden'><span id='scheme" + count + "'></span></td> ");
			buffer.append(
					"<td><a href='#' onclick='deleteLongPoProduct(this)'><i class='fa fa-times fa-x text-danger' ></i></a></td>");
			buffer.append("</tr>");
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

	public String getpreviousledgerbalance() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String ledgername = request.getParameter("ledgername");
			double balance = procurementDAO.getpreviousledgerbalance(ledgername);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + balance + "");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String completependingpo() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			String reason = request.getParameter("completependingpo");
			String userid = loginInfo.getUserId();

			String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res = procurementDAO.updatePOCompleteParent(id, reason, userid, date);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return execute();
	}

	public String changevendorid() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			IndentDAO indentDAO = new JDBCIndentDAO(connection);
			String newvendorid = request.getParameter("newvendorid");
			String procurementid = request.getParameter("procurementid");
			String oldvendorid = request.getParameter("oldvendorid");
			String voucherno = request.getParameter("changepopup_voucherno");

			//   12-04-2019
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			int vendoridnew = Integer.parseInt(newvendorid);
			Vendor vendor = inventoryVendorDAO.getVendor(newvendorid);
			boolean vendorpresentinledger = procurementDAO.checkVendorLedgerPresentStatus(vendoridnew);
			if (!vendorpresentinledger) {
				int saveid = procurementDAO.insertLedger(connection, vendoridnew, vendor.getName());
			}

			int res = 0;
			res = procurementDAO.updateParentVendorid(procurementid, newvendorid);
			res = procurementDAO.updateChildVendorid(procurementid, newvendorid);
			res = procurementDAO.updateVendorPorcVendorid(procurementid, newvendorid, voucherno);
			ArrayList<String> arrayList = procurementDAO.getProductIdFromProcId(procurementid);
			for (String oldprodid : arrayList) {
				res = procurementDAO.updateProductSupplierid(oldprodid, newvendorid);
				ArrayList<String> directtransferlist = indentDAO.getDirectTransferProduct(oldprodid);
				for (String string : directtransferlist) {
					res = procurementDAO.updateProductSupplierid(string, newvendorid);
				}
				ArrayList<String> requesttransferlist = indentDAO.getRequestTransferProduct(oldprodid);
				for (String string : requesttransferlist) {
					res = procurementDAO.updateProductSupplierid(string, newvendorid);
				}
			}

			ArrayList<String> arrayList2 = procurementDAO.getParentPoProcIDs(procurementid);
			int res2 = 0;
			for (String string : arrayList2) {
				res2 = procurementDAO.updateParentVendorid(string, newvendorid);
				res2 = procurementDAO.updateChildVendorid(string, newvendorid);
				res2 = procurementDAO.updateVendorPorcVendorid(string, newvendorid, voucherno);
				ArrayList<String> arrayList1 = procurementDAO.getProductIdFromProcId(string);
				for (String oldprodid : arrayList1) {
					res = procurementDAO.updateProductSupplierid(oldprodid, newvendorid);
					ArrayList<String> directtransferlist = indentDAO.getDirectTransferProduct(oldprodid);
					for (String string1 : directtransferlist) {
						res = procurementDAO.updateProductSupplierid(string1, newvendorid);
					}
					ArrayList<String> requesttransferlist = indentDAO.getRequestTransferProduct(oldprodid);
					for (String string1 : requesttransferlist) {
						res = procurementDAO.updateProductSupplierid(string1, newvendorid);
					}
				}
			}

			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int res1 = procurementDAO.saveVendorIdChangeLog(procurementid, newvendorid, oldvendorid, datetime,
					loginInfo.getUserId());

			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			String location = procurementDAO.getLocationIdFromProcurement(procurementid);
			String locationname = pharmacyDAO.getLocationName(location);
			String serviceid = chargesAccountProcessingDAO.getLedgrServiceIds(locationname);
			String ledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0", "1", vendoridnew);
			String oldledgerid = chargesAccountProcessingDAO.getledgerIDNew(serviceid, "0", "0", "1",
					Integer.parseInt(oldvendorid));
			if (ledgerid != null) {
				if (ledgerid.equals("")) {
					ledgerid = "0";
				}
			} else {
				ledgerid = "0";
			}
			if (oldledgerid != null) {
				if (oldledgerid.equals("")) {
					oldledgerid = "0";
				}
			} else {
				oldledgerid = "0";
			}
			int ress = procurementDAO.updateLedgerVendor(ledgerid, vendoridnew, procurementid, oldledgerid);
			int expenseid = procurementDAO.getPurchaseExpenceId(ledgerid, procurementid);
			ress = procurementDAO.updateExpenceParentLedger(expenseid, ledgerid);
			ress = procurementDAO.updateExpenceChildLedger(expenseid, vendor.getName(), ledgerid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + res + "");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String savepodetails() {
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

			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String grnno = jsonObject.getString("grnno");
			String textdata = jsonObject.getString("textdata");
			String columname = jsonObject.getString("columname");
			String isdate = jsonObject.getString("isdate");
			if(DateTimeUtils.numberCheck(isdate).equals("1")){
				textdata = DateTimeUtils.getCommencingDate1(textdata);
			}
			
			int res = procurementDAO.updatePoDetails(grnno, textdata,columname);

			JSONObject jsonobj = new JSONObject();

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String savemailcontentofpo() {
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

			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String grnno = jsonObject.getString("grnno");
			String textdata = jsonObject.getString("textdata");
			int res = procurementDAO.updatemailcontentOfPO(grnno, textdata);

			JSONObject jsonobj = new JSONObject();

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deletenewgrnfromindent() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String id = request.getParameter("id");
			int res = procurementDAO.deleteNewGrn(id);
			
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
	public String openuploadgrndata() throws Exception {

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
				if(breadcrumbs.getName().equals("Upload Stock")){
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
				breadcrumbs.setName("Upload Stock");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("openuploadgrndataProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Upload Stock");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			} else if (location.equals("")) {
				location = "0";
			}
			
			ArrayList<Vendor> vendorList = procurementDAO.getVendorListBylocationWise(location);
			procurementForm.setVendorList(vendorList);
			
			ArrayList<Master> warehouseList = inventoryProductDAO.getWareHouseListNew(location);
			procurementForm.setWarehouseList(warehouseList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "openuploadgrndata";
	}
	
	public String uploadexcelgrndata() throws Exception {

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
				if(breadcrumbs.getName().equals("Upload GRN")){
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
				breadcrumbs.setName("Upload GRN");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("uploadexcelgrndataProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Upload GRN");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryCatalogueDAO inventoryCatalogueDAO = new JDBCInventoryCatalogueDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			
			String filePath = request.getRealPath("/image/");
			String vendorid = procurementForm.getVendor_id();
			String warehouse = procurementForm.getWarehouse();
			String extension = procurementForm.getUserImageFileName().split("\\.")[1];
			connection = (Connection) Connection_provider.getconnection();
			boolean flagcheckextension=false; 
			/*if(extension.equalsIgnoreCase("xls")){
				flagcheckextension = true;
			}else if(extension.equalsIgnoreCase("ods")){
				flagcheckextension = true;
			}else if(extension.equalsIgnoreCase("xlsx")){
				flagcheckextension = true;
			}*/
			
			if(extension.equalsIgnoreCase("ods")){
				flagcheckextension = true;
			}
			
			if (!flagcheckextension) {
				addActionError("Please Upload only .ods");
				return "openuploadgrndata";
			} else if (procurementForm.getUserImageFileName() == null ||procurementForm.getUserImageFileName().equalsIgnoreCase("")) {
				addActionError("Please Upload File");
				return "openuploadgrndata";
			}
			File fileToCreate = new File(filePath, procurementForm.getUserImageFileName());
			FileUtils.copyFile(procurementForm.getUserImage(), fileToCreate);
			
			if(extension.equalsIgnoreCase("ods")){
				Sheet sheet = SpreadSheet.createFromFile(fileToCreate).getSheet(0);
				
				int nColCount = 12;
				int nRowCount = sheet.getRowCount();
				
				ArrayList<Product> arrayList = new ArrayList<Product>();
				
				// Iterating through each row of the selected sheet
				MutableCell cell = null;
				int i = 0;
				String catagoryid="2",subcatagoryid="1",prodname="",lastmodified="",mrp="0",
						purchaseprice="0",sale_price="0",vat="0",hsncode="0"; 
				String pack="0", mfg="",generic_name="",min="0",max="0",
						shedule="Regular", packqty="0", batchno="",expirydate="",freeqty="";
				
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				lastmodified=dateFormat.format(calendar.getTime());
				
				String date1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String location =warehouse;
				int grnno=0;
				int procurementid=0;
				
				for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {
					// Iterating through each column
					int nColIndex = 0;
					for (; nColIndex < nColCount; nColIndex++) {
						  cell = sheet.getCellAt(nColIndex, nRowIndex);
							if(i>0){
								if (nColIndex == 0) {
									prodname = cell.getValue().toString();
								}
								if (nColIndex == 1) {
									pack = cell.getValue().toString();
								}
								if (nColIndex == 2) {
									mrp = cell.getValue().toString();
								}
								if (nColIndex == 3) {
									sale_price = cell.getValue().toString();
								}
								if (nColIndex == 4) {
									purchaseprice = cell.getValue().toString();
								}
								if (nColIndex == 5) {
									vat = cell.getValue().toString();
								}
								if (nColIndex == 6) {
									mfg = cell.getValue().toString();
								}
								if (nColIndex == 7) {
									hsncode = cell.getValue().toString();
								}
								if (nColIndex == 8) {
									packqty = cell.getValue().toString();
								}
								if (nColIndex == 9) {
									batchno = cell.getValue().toString();
								}
								if (nColIndex == 10) {
									expirydate = cell.getValue().toString();
									if(DateTimeUtils.isNull(expirydate).equals("")){
										expirydate="";
									}else{
										String dateStr = expirydate;
										DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
										Date date = (Date)formatter.parse(dateStr);
										
										Calendar cal = Calendar.getInstance();
										cal.setTime(date);
										String year = ""+cal.get(Calendar.YEAR);
										String month =  ""+(cal.get(Calendar.MONTH) + 1);
										if(Integer.parseInt(month)<10){
											month ="0"+month;
										}
										expirydate =month+"/"+year;
									}
									
								}
								if (nColIndex == 11) {
									freeqty = cell.getValue().toString();
								}
								
							}
					}
					if(i>0){
						if(DateTimeUtils.isNull(prodname).equals("")){
							break;
						}
						if (procurementid == 0) {
							procurementid = procurementDAO.saveParengtPrecurementData(vendorid, date1, 0, "0", 0,"","0");
						}
						int catalogueid0 =inventoryProductDAO.getCatIdFromName(prodname,location);
						/*if(catalogueid0==0){
							catalogueid0= inventoryCatalogueDAO.addCatalogueDataNew(lastmodified,catagoryid,subcatagoryid,prodname,mrp,purchaseprice,sale_price,vat,generic_name,pack,hsncode,min,max,shedule,warehouse,mfg);
						}*/
						if(catalogueid0==0){
							continue;
						}
						String catalogueid = ""+catalogueid0;
						Product master = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
						double stock = inventoryProductDAO.getStockAvailableNew(catalogueid, location);
						String medicine_shedule = master.getMedicine_shedule();
						if(DateTimeUtils.isNull(medicine_shedule).equals("")){
							medicine_shedule ="Regular";
						}
						Product product = new Product();
						product.setVendor_id(vendorid);
						product.setProduct_name(prodname);
						product.setGenericname(master.getGenericname());
						product.setMedicine_type(master.getMedicine_type());
						product.setCatalogueid(catalogueid);
						product.setPack(pack);
						product.setCategory_id(master.getCategory_id());
						product.setMedicine_shedule(medicine_shedule);
						product.setLocation(location);
						product.setGrnno(grnno);
						String pack1 = master.getPack(); 
						product.setProcurement(1);
						Vendor vendor = inventoryVendorDAO.getVendor(vendorid);
						String fullname = vendor.getName();
						int pid = procurementDAO.saveNewProduct(product);
						int globlestatus = inventoryProductDAO.updateGlobalProductId(pid, pid);
						product.setProcurementid(String.valueOf(procurementid));
						product.setGrnno(grnno);
						int id = procurementDAO.saveNewProcurement(product);
						ArrayList<Master> cellList = new ArrayList<Master>();
						if (!location.equals("0")) {
							cellList = inventoryProductDAO.getcellList(location);
						} else {
							cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
						}
						product.setShelfList(cellList);
						
						product.setProduct_id(""+pid);
						product.setId(id);
						product.setMinorder(master.getMinorder());
						product.setMaxorder(master.getMaxorder());
						product.setReceived_qty((packqty));
						product.setExpiry_date(expirydate);
						product.setVat(vat);
						product.setMrp(mrp);
						product.setSale_price(sale_price);
						product.setPurchase_price(purchaseprice);
						product.setBatch_no(batchno);
						product.setHsnno(hsncode);
						product.setMfg(mfg);
						product.setFreeqty(DateTimeUtils.numberCheck(freeqty));
						arrayList.add(product);
					}
					i++;
					
				}
				boolean haslocation = procurementDAO.hasLocationToUser(loginInfo.getClinicid());
				if (haslocation) {
					procurementForm.setHaslocation(1);
				} else {
					procurementForm.setHaslocation(0);
				}
				ArrayList<Vendor> vendorList = procurementDAO.getVendorListBylocationWise(location);
				procurementForm.setVendorList(vendorList);
				procurementForm.setWarehouse(warehouse);
				procurementForm.setProcurementid(""+procurementid);
				procurementForm.setIsfromuploadstock(1);
				procurementForm.setProductList(arrayList);
			}
			addActionMessage("GRN Uploaded...");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "purorder";
	}
	public String amcCmcDashboard() throws Exception {

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
				if(breadcrumbs.getName().equals("AMC/CMC Dashboard")){
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
				breadcrumbs.setName("AMC/CMC Dashboard");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("amcCmcDashboardProcurement");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("AMC/CMC Dashboard");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			categoryid = (String) session.getAttribute("category");
			if (categoryid == null) {
				categoryid = "2";
			}
			String location = (String) session.getAttribute("location");
			if (location == null) {
				location = "0";
			}

			String voucherno = (String) session.getAttribute("confirmpo_voucherno");
			String fromdate = (String) session.getAttribute("confirmpo_fromdate");
			String todate = (String) session.getAttribute("confirmpo_todate");
			String iswithpo = (String) session.getAttribute("confirmpo_iswithpo");
			String vendorid = (String) session.getAttribute("confirmpo_vendor_id");
			/*
			 * String ispodashboard =(String)
			 * session.getAttribute("confirmpo_ispodashboard");
			 */
			if (fromdate == null) {
				fromdate = procurementForm.getFromdate();
			} else {
				session.removeAttribute("confirmpo_fromdate");
			}
			if (todate == null) {
				todate = procurementForm.getTodate();
			} else {
				session.removeAttribute("confirmpo_todate");
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
			if (voucherno == null) {
				voucherno = procurementForm.getVoucherno();
			} else {
				session.removeAttribute("confirmpo_voucherno");
			}

			if (voucherno != null) {
				if (voucherno.equals("")) {
					voucherno = null;
				}
			}
			if (vendorid == null) {
				vendorid = procurementForm.getVendor_id();
			} else {
				session.removeAttribute("confirmpo_vendor_id");
			}

			if (vendorid == null) {
				vendorid = "";
			}

			if (iswithpo == null) {
				iswithpo = procurementForm.getIswithpo();
			} else {
				session.removeAttribute("confirmpo_iswithpo");
			}

			if (iswithpo != null) {
				if (iswithpo.equals("")) {
					iswithpo = "0";
				}
			} else {
				iswithpo = "0";
			}
			String ispodashboard = procurementForm.getIspodashboard();

			if (ispodashboard != null) {
				if (ispodashboard.equals("")) {
					ispodashboard = "0";
				}
			} else {
				ispodashboard = "0";
			}
			procurementForm.setIswithpo(iswithpo);
			procurementForm.setVendor_id(vendorid);
			UserProfile userProfile = userProfileDAO.getPharmacyUserDetails(loginInfo.getClinicid());

			boolean isAmcCmc=true;
			
			int totalCount = procurementDAO.getProcurementCount(categoryid, location, fromdate, todate, voucherno,
					iswithpo, vendorid,isAmcCmc);
			pagination.setPreperties(totalCount);

			ArrayList<Procurement> procurementList = procurementDAO.getAllProcurementList(categoryid, pagination,
					location, voucherno, userProfile.getProcurementType(), fromdate, todate, vendorid, iswithpo,isAmcCmc,loginInfo);
			procurementForm.setProcurementList(procurementList);
			
			ArrayList<Vendor> vlist = procurementDAO.getVendorList();
			procurementForm.setVendorList(vlist);
			pagination.setPage_records(procurementList.size());
			procurementForm.setTotalRecords(totalCount);
			procurementForm.setPagerecords(Integer.toString(pagination.getPage_records()));

			procurementForm.setCategory_id(categoryid);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());
			procurementForm.setDate(date);

			procurementForm.setVoucherno(voucherno);

			// product name list
			ArrayList<Product> poproductList = inventoryProductDAO.getPoProductList(categoryid);
			procurementForm.setPoproductList(poproductList);

			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			String userid = loginInfo.getUserId();
			Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(userid);

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

			if (priscription.getEdit_purchaseorder() != null)
				if (priscription.getEdit_purchaseorder().equals("0"))
					loginInfo.setEdit_purchaseorder(false);
				else
					loginInfo.setEdit_purchaseorder(true);

			if (priscription.getDelete_purchaseorder() != null)
				if (priscription.getDelete_purchaseorder().equals("0"))
					loginInfo.setDelete_purchaseorder(false);
				else
					loginInfo.setDelete_purchaseorder(true);

			if (priscription.isApprove_po()) {
				loginInfo.setApprove_po(true);
			} else {
				loginInfo.setApprove_po(false);
			}

			int totalgrn = procurementDAO.getTotalTodayGRN(fromdate, todate, location);
			procurementForm.setTotalgrn(totalgrn);

			if (fromdate != null) {
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			}

			if (todate != null) {
				todate = DateTimeUtils.getCommencingDate1(todate);
			}
			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
			session.removeAttribute("vendorid");

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "amcCmcDashboard";
	}
	
	public String savepaytermsandcondition() {
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

			String data = buffer.toString();
			JSONObject jsonObject = new JSONObject(data);
			String procureid = jsonObject.getString("procureid");
			String condition = jsonObject.getString("terms");
			String y = procureid;
			String x = condition;
			boolean isavailable = procurementDAO.checkPayTermsNcondition(procureid);
			int z;
			if (isavailable) {
				z = procurementDAO.updatePaytoTermsNcondition(condition, procureid);
			} else {
				z = procurementDAO.inserPaytoTermsNcondition(condition, procureid);
			}
			x = x.replaceAll("\n", "<br>");

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("terms", x);

			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String pendingPOProduct() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			
			String location = "0";
			if (loginInfo.getUserType() == 2 || loginInfo.getPharmacyUserType() == 2) {
				location = procurementForm.getLocation();
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
			procurementForm.setLocation(location);
			
			String fromdate = procurementForm.getFromdate();
			String todate = procurementForm.getTodate();
			String vendorid = procurementForm.getVendor_id();
			if (fromdate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			} else {

				if (fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
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
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}

			}
			String voucherno = procurementForm.getVoucherno();
			if (voucherno != null) {
				if (voucherno.equals("")) {
					voucherno = null;
				}
			}
			if (vendorid != null) {
				if (vendorid.equals("0")) {
					vendorid = null;
				}
			}
			ArrayList<Procurement> procurementList = new ArrayList<>();
			if(vendorid!=null){
				procurementList = procurementDAO.getPendingPoProductList(location, voucherno, fromdate,todate, vendorid);
			}
			procurementForm.setProcurementList(procurementList);
			
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			
			procurementForm.setFromdate(fromdate);
			procurementForm.setTodate(todate);
			procurementForm.setLocation(location);
			setLetterData(location, connection);
			procurementForm.setIsfrombincard(1);
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			procurementForm.setLocationListPharmacy(locationlist);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "pendingpoproduct";

	}
	public String vendorratereport() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			String fromDate=procurementForm.getFromdate();
			String toDate=procurementForm.getTodate();
			String product = procurementForm.getProduct();
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
			if (product == null) {
				product = "";
			}
			ArrayList<Procurement> vendorratelist = procurementDAO.getvendorrate(fromDate,toDate,product);
			procurementForm.setVendorrateList(vendorratelist);
			//productForm.setRequestlimitqtylist(requestedlimitqtylist);
			procurementForm.setFromdate(DateTimeUtils.getCommencingDate1(fromDate));
			procurementForm.setTodate(DateTimeUtils.getCommencingDate1(toDate));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "vendorrpt";
		
		
		
	}

}