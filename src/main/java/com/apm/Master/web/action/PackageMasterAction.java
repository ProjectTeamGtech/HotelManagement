package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPackageMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Master.web.form.PackageMasterForm;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class PackageMasterAction extends BaseAction implements ModelDriven<PackageMasterForm>, Preparable {

	PackageMasterForm packageMasterForm = new PackageMasterForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	String mastername;
	Pagination pagination = new Pagination(25, 1);

	public PackageMasterForm getModel() {
		return packageMasterForm;
	}

	public String execute() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			int count = packageMasterDAO.getTotalPackageMasterCount();
			pagination.setPreperties(count);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {
				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");

			}

			String searchText = packageMasterForm.getSearchText();
			if (searchText != null) {
				if (searchText.equals("")) {
					searchText = null;
				}
			}

			packageMasterForm.setMastername(mastername);
			ArrayList<PackageMaster> arrayList = packageMasterDAO.getAllPackage(searchText, pagination);
			packageMasterForm.setPackagelist(arrayList);
			pagination.setPage_records(arrayList.size());
			packageMasterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			packageMasterForm.setTotalRecords(String.valueOf(count));
			
			String clientid=request.getParameter("clientid");
			if(clientid==null){
				clientid="";
			}
			packageMasterForm.setClientid(clientid);
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
				if(breadcrumbs.getName().equals("New Package")){
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
				breadcrumbs.setName("New Package");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("PackageMaster?clientid="+clientid+"");
				breadcrumbs.setIscurrent(true);
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addChargeTemp() {
		Connection connection = null;
		try {
			String counts = request.getParameter("counts");
			String chargeid = request.getParameter("chargeid");
			String chargetype = request.getParameter("chargetype");
			String packagetype = request.getParameter("packagetype");
			int i = Integer.parseInt(counts);

			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			String chargename = "";
			if (chargetype.equals("0")) {
				chargename = packageMasterDAO.getChargeNameById(chargeid);
			} else {
				chargename = packageMasterDAO.getChargeNameByIdNew(chargeid);
			}
			StringBuilder str = new StringBuilder();

			if (packagetype.equals("packageA")) {
				str.append("<tr>");
				str.append("<input type='hidden' class='akash' value='" + i + "' />");
				if (chargetype.equals("1")) {
					str.append("<input type='hidden' class='akash22' value='" + chargeid + "' />");
				}
				str.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='chargeid" + i + "' value='"
						+ chargeid + "'>");
				str.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='chargetype" + i
						+ "' value='" + chargetype + "'>");
				str.append(
						"<td style='font-size: 14px;'><input type='text' disabled='disabled' class='form-control' name='packagedata["
								+ i + "].chargename' id='chargename" + i + "' value='" + chargename + "' ></td>");
				str.append(
						"<td style='font-size: 14px;'><input type='text' placeholder='%' onchange='calculateamount(this.value,"
								+ i + ")' name='packagedata[" + i + "].percentage' id='percentage" + i
								+ "' class='form-control' style='width: 35%;'></td>");
				str.append("<td style='text-align: right;font-size: 14px;'><input name='packagedata[" + i
						+ "].cal_amount' id='cal_amount" + i
						+ "' style='text-align: right;' type='text' class='form-control'></td>");
				str.append("<td><a href='#' onclick='deletePackageTemp(" + i
						+ ")' style='color:#d9534f;font-size: 15px;'><i class='fa fa-times' aria-hidden='true'></i></a></td>");
				str.append("</tr>");
			} else {
				str.append("<tr>");
				str.append("<input type='hidden' class='akash' value='" + i + "' />");
				if (chargetype.equals("1")) {
					str.append("<input type='hidden' class='akash22' value='" + chargeid + "' />");
				}
				str.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='chargeid" + i + "' value='"
						+ chargeid + "'>");
				str.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='chargetype" + i
						+ "' value='" + chargetype + "'>");
				str.append(
						"<td style='font-size: 14px;'><input type='text' disabled='disabled' class='form-control' name='packagedata["
								+ i + "].chargename' id='chargename" + i + "' value='" + chargename + "' ></td>");
//				if (i == (0)) {
					str.append(
							"<td style='font-size: 14px;'><input type='text' placeholder='%' readonly='readonly' name='packagedata["
									+ i + "].percentage' id='percentage" + i
									+ "' class='form-control' style='width: 35%;'></td>");
//				} else {
//					str.append(
//							"<td style='font-size: 14px;'><input type='text' placeholder='%' onchange='calculateamountpackageB(this.value,"
//									+ i + ")' name='packagedata[" + i + "].percentage' id='percentage" + i
//									+ "' class='form-control' style='width: 35%;'></td>");
//				}
				str.append("<td style='text-align: right;font-size: 14px;'><input name='packagedata[" + i
						+ "].cal_amount' id='cal_amount" + i
						+ "' style='text-align: right;' type='text' class='form-control'></td>");
				if (i == (0)) {
					str.append("<td></td>");
				} else {
					str.append("<td><a href='#' onclick='deletePackageTemp(" + i
							+ ")' style='color:#d9534f;font-size: 15px;'><i class='fa fa-times' aria-hidden='true'></i></a></td>");
				}

				str.append("</tr>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addChargeTempTP() {
		Connection connection = null;
		try {
			String counts = request.getParameter("counts");
			String chargeid = request.getParameter("chargeid");
			String chargetype = request.getParameter("chargetype");
			String packagetype = request.getParameter("packagetype");
			int i = Integer.parseInt(counts);
			String clientid= request.getParameter("clientid");
			if(clientid==null){
				clientid="";
			}
			String code="", charge="";
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			String chargename = "";
			if (chargetype.equals("0")) {
				chargename = packageMasterDAO.getChargeNameById(chargeid);
			}else if(chargetype.equals("2")){
				chargename=packageMasterDAO.nameofApmtCharge(chargeid);
				Master master= packageMasterDAO.getCodeAndAmmountOfTpCharge(clientid, chargename);
				if(master.getCode()==null){
					master.setCode("");
				}
				if(master.getCharge()==null){
					master.setCharge("");
				}
				code= master.getCode();
				charge=master.getCharge();
			} else {
				chargename = packageMasterDAO.getChargeNameByIdNew(chargeid);
			}
			StringBuilder str = new StringBuilder();
			str.append("<tr>");
			if (chargetype.equals("1")) {
				str.append("<input type='hidden' class='lokeshinv' value='" + chargeid + "' />");
			}
			str.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='chargeidtp" + i + "' value='"
					+ chargeid + "'>");
			str.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='chargetypetp" + i
					+ "' value='" + chargetype + "'>");

			str.append(" <td style='font-size: 14px;'><input type='text' name='packagedata[" + i
					+ "].chargename' value='" + chargename + "' class='form-control' readonly></td> ");
			str.append(
					"<td style='font-size: 14px;'><input type='text' placeholder='code' class='tpcode form-control' name='packagedata["
							+ i + "].code' id='code[" + i + "]' value='"+code+"'></td>   ");
			str.append(
					"<td style='font-size: 14px;'><input type='number' placeholder='Amount' class='amounttp form-control'  name='packagedata["
							+ i + "].cal_amount'  id='amounttp[" + i + "]' value='"+charge+"' onkeyup='changeTotalAmtTP(this.value)'></td>   ");
			str.append(" <td style='font-size: 14px;'><a href='#' onclick='deleteTPchargenew(" + i
					+ ")'><i class='fa fa-times' aria-hidden='true'></i></a></td>  ");
			str.append("</tr>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String save() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// String package_name = request.getParameter("package_name");
			// String package_amount = request.getParameter("package_amount");
			String package_name = packageMasterForm.getPackage_name();
			String package_amount = packageMasterForm.getPackage_amount();
			String packagetype = packageMasterForm.getPackagetype();
			String inveschargeid = request.getParameter("invechargeid");
			// String chargetype = packageMasterForm.getChargetype();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);

			int result = packageMasterDAO.storePackageParentData(package_name, package_amount, packagetype,
					inveschargeid,"0","","");
			int result1 = 0;
			if (result > 0) {
				for (PackageMaster packageMaster : packageMasterForm.getPackagedata()) {
					// String pack_name =
					// packageMasterDAO.getChargeNameById(packageMaster.getChargeid());
					String chargename = "";
					String chargetype = packageMaster.getChargetype();
					if (chargetype.equals("0")) {
						chargename = packageMasterDAO.getChargeNameById(packageMaster.getChargeid());
					} else {
						chargename = packageMasterDAO.getChargeNameByIdNew(packageMaster.getChargeid());
					}
					packageMaster.setChargename(chargename);
					packageMaster.setIschargetype(chargetype);
					result1 = packageMasterDAO.storePackageChildData(packageMaster, result);
				
				}
					
		}
//			response.setContentType("text/html");
//			response.setHeader("Cache-Control", "no-cache");
//			response.getWriter().write(result1);
	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "savePackage";
	}

	public String saveTpPkg() {
		Connection connection = null;
		try {
			connection= Connection_provider.getconnection();
			String pkgname= request.getParameter("package_name_tp");
			String pkgamt= request.getParameter("package_amount_tp");
			String pkgtype= request.getParameter("pkgtypetp");
			String inveschargeid=request.getParameter("inveschargeidtp");
			String tp=packageMasterForm.getPkgtypetp();
			String days=request.getParameter("days");
			String description=request.getParameter("description");
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);

			int result = packageMasterDAO.storePackageParentData(pkgname, pkgamt, pkgtype,
					inveschargeid,tp,description,days);
			int result1=0;
			if (result > 0) {
				for (PackageMaster packageMaster : packageMasterForm.getPackagedata()) {
					String code=packageMaster.getCode();
//					System.out.println(code);
					// String pack_name =
					// packageMasterDAO.getChargeNameById(packageMaster.getChargeid());
					String chargename = "";
					String chargetype = packageMaster.getChargetype();
					if(tp.equals("1")){
					if (chargetype.equals("0")){
						chargename = packageMasterDAO.getChargeNameById(packageMaster.getChargeid());
					}else if(chargetype.equals("2")){
						chargename= packageMasterDAO.nameofApmtCharge(packageMaster.getChargeid());
					} else {
						chargename = packageMasterDAO.getChargeNameByIdNew(packageMaster.getChargeid());
					}
					}else{
						chargename= packageMasterDAO.nameofApmtCharge(packageMaster.getChargeid());
					}
					packageMaster.setChargename(chargename);
					packageMaster.setIschargetype(chargetype);
					result1 = packageMasterDAO.storePackageChildData(packageMaster, result);
				
				}
					
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "savePackage";
	}

	public String delete() {
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			PackageMaster packageMaster = new PackageMaster();
			packageMaster.setId(Integer.parseInt(request.getParameter("id")));
			int i = packageMaster.getId();
			int result = packageMasterDAO.deletePackage(packageMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedPackage";
	}

	public String edit() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			String id = request.getParameter("id");
			StringBuilder builder = new StringBuilder();
			// ArrayList<PackageMaster> packagelist =
			// packageMasterDAO.getPerticularPackageForEdit(id);
			PackageMaster packageMaster = packageMasterDAO.getPerticularPackage(id);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			builder.append("" + packageMaster.getId() + "");
			builder.append("#");

			builder.append("" + packageMaster.getName() + "");
			builder.append("#");

			builder.append("" + packageMaster.getAmount() + "");
			builder.append("#");

			ArrayList<PackageMaster> arrayList = packageMasterDAO.getPackageFromChild(packageMaster.getId());
			int i = 0;
			for (PackageMaster packageMaster2 : arrayList) {
				builder.append("<tr>");
				builder.append("<input type='hidden' class='editakash' value='" + i + "' />");
				if (packageMaster.getIschargetype() != null) {
					if (packageMaster.getIschargetype().equals("1")) {
						builder.append("<input type='hidden' class='editakash22' value='" + packageMaster2.getChargeid()
								+ "' />");
					}
				}
				builder.append("<input type='hidden' name='packagedata[" + i + "].child_id' id='editchild_id" + i
						+ "' value='" + packageMaster2.getChild_id() + "'>");
				builder.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='editchargeid" + i
						+ "' value='" + packageMaster2.getChargeid() + "'>");
				builder.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='editchargetype" + i
						+ "' value='" + packageMaster2.getIschargetype() + "'>");
				builder.append(
						"<td style='font-size: 14px;'><input type='text' disabled='disabled' class='form-control' name='packagedata["
								+ i + "].chargename' id='editchargename" + i + "' value='"
								+ packageMaster2.getChargename() + "' ></td>");
				builder.append(
						"<td style='font-size: 14px;'><input type='text' placeholder='%' onblur='calculateamountedit(this.value,"
								+ i + ")' onkeyup='calculateamountedit(this.value," + i + ")' name='packagedata[" + i
								+ "].percentage' id='editpercentage" + i + "' value='" + packageMaster2.getPercentage()
								+ "' class='form-control' style='width: 35%;'></td>");
				builder.append("<td style='text-align: right;font-size: 14px;'><input name='packagedata[" + i
						+ "].cal_amount' id='editcal_amount" + i + "' style='text-align: right;' type='text' value='"
						+ packageMaster2.getCal_amount() + "' class='form-control'></td>");
				builder.append("<td><button onclick='deleteEditPackage(" + packageMaster2.getChild_id() + "," + i
						+ ")' ></button></td>");

				/* builder.append("<td></td>"); */
				builder.append("</tr>");
				i++;
			}
			builder.append("#");
			builder.append("" + i + "");

			builder.append("#");
			if (packageMaster.getIschargetype() != null) {
				if (packageMaster.getIschargetype().equals("0")) {
					builder.append(
							"<select  id='editchargetype' name='pkgchargetype' class='form-control chosen-select' style='width:100%;'>");
					builder.append("<option value='0'>Other</option>");
					builder.append("</select>");
				} else {
					builder.append(
							"<select  id='editchargetype' name='pkgchargetype' class='form-control chosen-select' style='width:100%;'>");
					builder.append("<option value='1'>Investigation</option>");
					builder.append("</select>");
				}
			} else {
				builder.append(" ");
			}
			builder.append("#");
			if (packageMaster.getIschargetype() != null) {
				if (packageMaster.getIschargetype().equals("0")) {
					ArrayList<Master> masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
					builder.append(
							"<select name='charge' id='editcharge' class='form-control chosen-select' style='width:100%;'>");
					builder.append("<option value='0'>Select Charge Type</option>");
					for (Master master : masterChageTypeList) {
						builder.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
					}
					builder.append("</select>");
				} else {
					ArrayList<Master> masterChageTypeList = packageMasterDAO.getmasterChageNameList();
					builder.append(
							"<select name='charge' id='editcharge' class='form-control chosen-select' style='width:100%;'>");
					builder.append("<option value='0'>Select Charge Type</option>");
					for (Master master : masterChageTypeList) {
						builder.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
					}
					builder.append("</select>");
				}
			} else {
				builder.append(
						"<select name='charge' id='editcharge' class='form-control chosen-select' style='width:100%;'>");
				builder.append("<option value='0'>Select Charge Type</option>");
				builder.append("</select>");
			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + builder.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String update() throws Exception {
		Connection connection = null;
		try {

			String parentid = packageMasterForm.getParentid();
			String package_name = packageMasterForm.getPackage_name();
			String package_amount = packageMasterForm.getPackage_amount();
			String inveschargeid = request.getParameter("invechargeid");
			String pkgchargetype = request.getParameter("pkgchargetype");
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			int result = packageMasterDAO.updateParentPackageData(parentid, package_name, package_amount,
					inveschargeid);
			for (PackageMaster packageMaster : packageMasterForm.getPackagedata()) {
				/*
				 * String pack_name =
				 * packageMasterDAO.getChargeNameById(packageMaster.getChargeid(
				 * )); packageMaster.setChargename(pack_name);
				 */
				if (packageMaster == null) {
					continue;
				}
				String chargename = "";
				String chargetype = packageMaster.getChargetype();
				chargetype = pkgchargetype;
				if (chargetype.equals("0")) {
					chargename = packageMasterDAO.getChargeNameById(packageMaster.getChargeid());
				} else {
					chargename = packageMasterDAO.getChargeNameByIdNew(packageMaster.getChargeid());
				}
				packageMaster.setChargename(chargename);
				packageMaster.setIschargetype(chargetype);
				if (packageMaster.getChild_id() == 0) {
					int result1 = packageMasterDAO.storePackageChildData(packageMaster, Integer.parseInt(parentid));
				} else {
					result = packageMasterDAO.updateChildPackageData(packageMaster);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "updatePackage";
	}

	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			packageMasterForm.setMasterlist(masterlist);
			mastername = (String) session.getAttribute("mastername");
			packageMasterForm.setMastername(mastername);
			ArrayList<AppointmentType> additionalChargesList = new ArrayList<AppointmentType>();
			//additionalChargesList = additionalDAO.getAdditionalChargesList();
			packageMasterForm.setAdditionalChargesList(additionalChargesList);
			ArrayList<Master> masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			ArrayList<Master> newmasterChargelist=appointmentDAO.getmasterChageTypeListnew(loginInfo);
			
			packageMasterForm.setMasterChageTypeList(masterChageTypeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String checkpackagename() {
		Connection connection = null;
		try {
			String name = request.getParameter("name");
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			int result = packageMasterDAO.checkPackageName(name);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + result + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getchargenametypelist() throws Exception {
		Connection connection = null;
		try {
			String val = request.getParameter("val");
			String type= request.getParameter("type");
			String clientid=request.getParameter("clientid");
			if(clientid==null){
				clientid="";
			}
			boolean flag=false;
			if(type!=null){
				flag=true;
			}
			connection = Connection_provider.getconnection();
			AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);

			StringBuffer buffer = new StringBuffer();
			if (val.equals("0")||val.equals("2")) {
				ArrayList<Master> masterChageTypeList= new ArrayList<Master>();
				if(val.equals("2")){
					masterChageTypeList=appointmentDAO.getIpOpProcedureList(clientid,loginInfo);
				}else{
					masterChageTypeList= appointmentDAO.getmasterChageTypeList(loginInfo);
					
				}
				
				if(flag){
					buffer.append(
							"<select name='charge' id='chargetp' class='form-control chosen-select' style='width:100%;'>");
				}else{
					buffer.append(
							"<select name='charge' id='charge' class='form-control chosen-select' style='width:100%;'>");
				}
				
				buffer.append("<option value='0'>Select Charge Type</option>");
				for (Master master : masterChageTypeList) {
					buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
				}
				buffer.append("</select>");
			} else {
				ArrayList<Master> masterChageTypeList = packageMasterDAO.getmasterChageNameList();
				if(flag){
					buffer.append(
							"<select name='charge' id='chargetp' class='form-control chosen-select' style='width:100%;'>");
				}else{
					buffer.append(
							"<select name='charge' id='charge' class='form-control chosen-select' style='width:100%;'>");	
				}
				
				buffer.append("<option value='0'>Select Charge Type</option>");
				for (Master master : masterChageTypeList) {
					buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
				}
				buffer.append("</select>");
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + buffer.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String editchargeadd() {
		Connection connection = null;
		try {
			String counts = request.getParameter("counts");
			String chargeid = request.getParameter("chargeid");
			String chargetype = request.getParameter("chargetype");
			String packagetype = request.getParameter("packagetype");
			int i = Integer.parseInt(counts);

			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			String chargename = "";
			if (chargetype.equals("0")) {
				chargename = packageMasterDAO.getChargeNameById(chargeid);
			} else {
				chargename = packageMasterDAO.getChargeNameByIdNew(chargeid);
			}
			StringBuilder str = new StringBuilder();
			str.append("<tr>");
			str.append("<input type='hidden' class='editakash' value='" + i + "' />");
			if (chargetype.equals("1")) {
				str.append("<input type='hidden' class='editakash22' value='" + chargeid + "' />");
			}
			str.append("<input type='hidden' name='packagedata[" + i + "].child_id' id='editchild_id" + i
					+ "' value='0'>");
			str.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='editchargeid" + i + "' value='"
					+ chargeid + "'>");
			str.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='editchargetype" + i
					+ "' value='" + chargetype + "'>");
			str.append(
					"<td style='font-size: 14px;'><input type='text' disabled='disabled' class='form-control' name='packagedata["
							+ i + "].chargename' id='editchargename" + i + "' value='" + chargename + "' ></td>");
			str.append(
					"<td style='font-size: 14px;'><input type='text' placeholder='%' onchange='calculateamountedit(this.value,"
							+ i + ")' name='packagedata[" + i + "].percentage' id='editpercentage" + i
							+ "' class='form-control' style='width: 35%;'></td>");
			str.append("<td style='text-align: right;font-size: 14px;'><input name='packagedata[" + i
					+ "].cal_amount' id='editcal_amount" + i
					+ "' style='text-align: right;' type='text' class='form-control'></td>");
			// str.append("<td><a href='#' onclick='deleteEditPackage("+i+")'
			// style='color:#d9534f;font-size: 15px;'><i class='fa fa-times'
			// aria-hidden='true'></i></a></td>");
			str.append("<td></td>");
			str.append("</tr>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deletechildpkgajax() {

		String childid = request.getParameter("childid");
		Connection connection = null;
		try {
			int res = 0;
			connection = Connection_provider.getconnection();
			PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
			res = packageMasterDAO.deleteChildPkgAjax(childid);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
public String editTp(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		String id=request.getParameter("id");
		StringBuilder builder= new StringBuilder();
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
		PackageMaster packageMaster = packageMasterDAO.getPerticularPackage(id);
		AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		builder.append("" + packageMaster.getId() + "");
		builder.append("#");

		builder.append("" + packageMaster.getName() + "");
		builder.append("#");

		builder.append("" + packageMaster.getAmount() + "");
		builder.append("#");

		ArrayList<PackageMaster> arrayList = packageMasterDAO.getPackageFromChild(packageMaster.getId());
		int i = 0;
		for (PackageMaster packageMaster2 : arrayList) {
		builder.append(" <tr>  ");
		if (packageMaster.getIschargetype() != null) {
			if (packageMaster.getIschargetype().equals("1")) {
				builder.append("<input type='hidden' class='lokeshtpedit' value='" + packageMaster2.getChargeid()
						+ "' />");
			}
		}
		builder.append("<input type='hidden' name='packagedata[" + i + "].child_id' id='editchild_id_tp" + i
				+ "' value='" + packageMaster2.getChild_id() + "'>");
		builder.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='editchargeid_tp" + i
				+ "' value='" + packageMaster2.getChargeid() + "'>");
		builder.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='editchargetype_tp" + i
				+ "' value='" + packageMaster2.getIschargetype() + "'>");
		builder.append(
				"<td style='font-size: 14px;'><input type='text' disabled='disabled' class='form-control' name='packagedata["
						+ i + "].chargename' id='editchargename_tp" + i + "' value='"
						+ packageMaster2.getChargename() + "' ></td>");
		builder.append(
				"<td style='font-size: 14px;'><input type='text' placeholder='code' name='packagedata[" + i
						  
						+ "].code' id='code_edit" + i + "' value='" + packageMaster2.getCode()
						+ "' class='form-control' ></td>");
		builder.append("<td style='text-align: right;font-size: 14px;'><input name='packagedata[" + i
				+ "].cal_amount' id='amt_edit" + i + "' style='text-align: right;' type='text' value='"
				+ packageMaster2.getCal_amount() + "' class='form-control lokesheditpkg' onkeyup='chngeEditAmt(this)'></td>");
		builder.append("<td><button onclick='deleteEditPackageTP(" + packageMaster2.getChild_id() + "," + i
				+ ")' ></button></td>");


		builder.append(" </tr> ");
		i=i+1;
		}		
		builder.append("#");
		builder.append(""+i);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+builder.toString());

	} catch (Exception e) {
		// TODO: handle exception
	}
	return null;
}
public String addTempEditTp(){
	try {
		Connection connection= Connection_provider.getconnection();
		String counts = request.getParameter("counts");
		String chargeid = request.getParameter("chargeid");
		String chargetype = request.getParameter("chargetype");
		String packagetype = request.getParameter("packagetype");
		int i = Integer.parseInt(counts);
		String clientid= request.getParameter("clientid");
		if(clientid==null){
			clientid="";
		}
		connection = Connection_provider.getconnection();
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
		String chargename = "";
		String code="",charge="";
		if (chargetype.equals("0")) {
			chargename = packageMasterDAO.getChargeNameById(chargeid);
		}else if(chargetype.equals("2")){
			chargename=packageMasterDAO.nameofApmtCharge(chargeid);
			Master master= packageMasterDAO.getCodeAndAmmountOfTpCharge(clientid, chargename);
			if(master.getCode()==null){
				master.setCode("");
			}
			if(master.getCharge()==null){
				master.setCharge("");
			}
			code= master.getCode();
			charge=master.getCharge();

		} else {
			chargename = packageMasterDAO.getChargeNameByIdNew(chargeid);
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<tr>");
		if (chargetype.equals("1")) {
			builder.append("<input type='hidden' class='lokeshtpedit' value='" + chargeid + "' />");
		}
		builder.append("<input type='hidden' name='packagedata[" + i + "].child_id' id='editchild_id_tp" + i
				+ "' value='0'>");
		builder.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='editchargeid_tp" + i
				+ "' value='"+ chargeid +"'>");
		builder.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='editchargetype_tp" + i
				+ "' value='" +chargetype+ "'>");
		builder.append(
				"<td style='font-size: 14px;'><input type='text' disabled='disabled' class='form-control' name='packagedata["
						+ i +"].chargename' id='editchargename_tp" + i + "' value='"
						+ chargename +"' ></td>");
		builder.append(
				"<td style='font-size: 14px;'><input type='text' placeholder='code' name='packagedata[" + i
						  
						+ "].code' id='code_edit" + i + "' value='"+code+"' class='form-control' ></td>");
					
		builder.append("<td style='text-align: right;font-size: 14px;'><input name='packagedata[" + i
				+ "].cal_amount' id='amt_edit" + i + "' style='text-align: right;' type='text' value='"+charge+"' class='form-control lokesheditpkg' onkeyup='chngeEditAmt(this)'></td>");
				
		builder.append("<td>	</td>");
		
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+builder.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
} 


public String updateTpPkg(){
	Connection connection = null;
	try {

		String parentid = request.getParameter("edittpparentid");
		String package_name = request.getParameter("package_name_tp_edit");
		String package_amount = request.getParameter("package_amount_tp_edit");
		String inveschargeid = request.getParameter("inveschargeidtp_edit");
		String pkgchargetype = request.getParameter("chargetype_tp_edit");
		connection = Connection_provider.getconnection();
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
		int result = packageMasterDAO.updateParentPackageData(parentid, package_name, package_amount,
				inveschargeid);
		for (PackageMaster packageMaster : packageMasterForm.getPackagedata()) {
			/*
			 * String pack_name =
			 * packageMasterDAO.getChargeNameById(packageMaster.getChargeid(
			 * )); packageMaster.setChargename(pack_name);
			 */
			if (packageMaster == null) {
				continue;
			}
			String chargename = "";
			String chargetype = packageMaster.getChargetype();
			chargetype = pkgchargetype;
			if (chargetype.equals("0")) {
				chargename = packageMasterDAO.getChargeNameById(packageMaster.getChargeid());
			}else if(chargetype.equals("2")){
				chargename= packageMasterDAO.nameofApmtCharge(packageMaster.getChargeid());
			} else {
				chargename = packageMasterDAO.getChargeNameByIdNew(packageMaster.getChargeid());
			}
			packageMaster.setChargename(chargename);
			packageMaster.setIschargetype(chargetype);
			if (packageMaster.getChild_id() == 0) {
				int result1 = packageMasterDAO.storePackageChildData(packageMaster, Integer.parseInt(parentid));
			} else {
				result = packageMasterDAO.updateChildPackageData(packageMaster);
			}

		}
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return "updatePackage";

}

public String getThrdpartyList(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		PackageMasterDAO packageMasterDAO= new JDBCPackageMasterDAO(connection);
		String list= packageMasterDAO.getTPPkgList();
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}


public String getchargenametypelistedit() throws Exception {
	Connection connection = null;
	try {
		String val = request.getParameter("val");
		String type= request.getParameter("type");
		String clientid=request.getParameter("clientid");
		if(clientid==null){
			clientid="";
		}
		boolean flag=false;
		if(type!=null){
			flag=true;
		}
		connection = Connection_provider.getconnection();
		AppointmentDAO appointmentDAO = new JDBCAppointmentDAO(connection);
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);

		StringBuffer buffer = new StringBuffer();
		if (val.equals("0")||val.equals("2")) {
			ArrayList<Master> masterChageTypeList= new ArrayList<Master>();
			if(val.equals("2")){
				masterChageTypeList=appointmentDAO.getIpOpProcedureList(clientid,loginInfo);
			}else{
				masterChageTypeList= appointmentDAO.getmasterChageTypeList(loginInfo);
				
			}
			
			if(flag){
				buffer.append(
						"<select name='charge' id='chargetp_edit' class='form-control chosen-select' style='width:100%;'>");
			}else{
				buffer.append(
						"<select name='charge' id='charge' class='form-control chosen-select' style='width:100%;'>");
			}
			
			buffer.append("<option value='0'>Select Charge Type</option>");
			for (Master master : masterChageTypeList) {
				buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			buffer.append("</select>");
		} else {
			ArrayList<Master> masterChageTypeList = packageMasterDAO.getmasterChageNameList();
			if(flag){
				buffer.append(
						"<select name='charge' id='chargetp_edit' class='form-control chosen-select' style='width:100%;'>");
			}else{
				buffer.append(
						"<select name='charge' id='charge' class='form-control chosen-select' style='width:100%;'>");	
			}
			
			buffer.append("<option value='0'>Select Charge Type</option>");
			for (Master master : masterChageTypeList) {
				buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
			}
			buffer.append("</select>");
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + buffer.toString() + "");

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String addChargeTempSelf() {
	Connection connection = null;
	try {
		String counts = request.getParameter("counts");
		String chargeid = request.getParameter("chargeid");
		String chargetype = request.getParameter("chargetype");
		String packagetype = request.getParameter("packagetype");
		int i = Integer.parseInt(counts);
		String clientid= request.getParameter("clientid");
		if(clientid==null){
			clientid="";
		}
		String code="", charge="";
		connection = Connection_provider.getconnection();
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
		String chargename = "";
		
			chargename=packageMasterDAO.nameofApmtCharge(chargeid);
			Master master= packageMasterDAO.getCodeAndAmmountOfTpCharge(clientid, chargename);
			if(master.getCode()==null){
				master.setCode("");
			}
			if(master.getCharge()==null){
				master.setCharge("");
			}
			code= master.getCode();
			charge=master.getCharge();
		StringBuilder str = new StringBuilder();
		str.append("<tr>");
		if (chargetype.equals("1")) {
			str.append("<input type='hidden' class='lokeshinv' value='" + chargeid + "' />");
		}
		str.append("<input type='hidden' name='packagedata[" + i + "].chargeid' id='chargeidtp" + i + "' value='"
				+ chargeid + "'>");
		str.append("<input type='hidden' name='packagedata[" + i + "].chargetype' id='chargetypetp" + i
				+ "' value='" + chargetype + "'>");

		str.append(" <td style='font-size: 14px;'><input type='text' name='packagedata[" + i
				+ "].chargename' value='" + chargename + "' class='form-control' readonly></td> ");
		str.append(
				"<td style='font-size: 14px;'><input type='text' placeholder='code' class='tpcode form-control' name='packagedata["
						+ i + "].code' id='code[" + i + "]' value='"+code+"'></td>   ");
		str.append(
				"<td style='font-size: 14px;'><input type='number' placeholder='Amount' class='amounttp form-control'  name='packagedata["
						+ i + "].cal_amount'  id='amounttp[" + i + "]' value='"+charge+"' onkeyup='changeTotalAmtTP(this.value)'></td>   ");
		str.append(" <td style='font-size: 14px;'><a href='#' onclick='deleteTPchargenew(" + i
				+ ")'><i class='fa fa-times' aria-hidden='true'></i></a></td>  ");
		str.append("</tr>");

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String packagelist() {
	Connection connection;
	try {
		connection = Connection_provider.getconnection();
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
		String whopay=packageMasterForm.getPaymentmode();
		if(whopay==null){
			whopay="";
		}
		String searchText=packageMasterForm.getSearchText();
		if(searchText==null){
			searchText="";
		}
		ArrayList<PackageMaster> arrayList = packageMasterDAO.getAllPackagelist(searchText,whopay, pagination);
		packageMasterForm.setPackagelist(arrayList);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "packagedashboard";
}
public String childpkglist(){
	Connection connection;
	try {
		connection = Connection_provider.getconnection();
		PackageMasterDAO packageMasterDAO = new JDBCPackageMasterDAO(connection);
		String parentid=request.getParameter("parentid");
		ArrayList<PackageMaster> arrayList = packageMasterDAO.getAllChildPackagelist(parentid);
		int size=arrayList.size();
		packageMasterForm.setPackage_name(arrayList.get(size-1).getName());
		packageMasterForm.setDays(arrayList.get(size-1).getDays());
		packageMasterForm.setDescription(arrayList.get(size-1).getDescription());
		packageMasterForm.setPackage_amount(arrayList.get(size-1).getAmount());
		packageMasterForm.setPackagelist(arrayList);
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		packageMasterForm.setClinicName(clinic.getClinicName());
		packageMasterForm.setClinicOwner(clinic.getClinicOwner());
		packageMasterForm.setOwner_qualification(clinic.getOwner_qualification());
		packageMasterForm.setLocationAdressList(locationAdressList);
		packageMasterForm.setAddress(clinic.getAddress());
		packageMasterForm.setLandLine(clinic.getLandLine());
		packageMasterForm.setClinicemail(clinic.getEmail());
		packageMasterForm.setWebsiteUrl(clinic.getWebsiteUrl());
		packageMasterForm.setClinicLogo(clinic.getUserImageFileName());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "childpackagelist";
	
}
}
