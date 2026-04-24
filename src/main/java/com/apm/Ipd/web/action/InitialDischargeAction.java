package com.apm.Ipd.web.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Discharge;
import com.apm.Ipd.web.form.IpdForm;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Mrd.eu.bi.MrdDAO;
import com.apm.Mrd.eu.blogic.JDBCMrdDAO;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InitialDischargeAction extends BaseAction implements ModelDriven<IpdForm>, Preparable {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(20, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	IpdForm ipdForm = new IpdForm();

	@Override
	public String execute() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			/*
			 * ArrayList<Bed> wardlist = bedDao.getAllWardList();
			 * ipdForm.setWardlist(wardlist);
			 */

			String searchtext = ipdForm.getSearchtext();
			String fromdate = ipdForm.getFromdate();
			String todate = ipdForm.getTodate();
			String status1 = ipdForm.getFilter_status1();
			// String refreshcheck=ipdForm.getRefresh();
			// String formdatestr="";

			String filter = request.getParameter("filter");
			String maintype = request.getParameter("maintype");
			String fakclientid=request.getParameter("fake_clientid");
			String fuhid=request.getParameter("uhid");
			String actiontype=DateTimeUtils.isNull(request.getParameter("IpdpatientType"));
			
			if (filter != null) {
				status1 = filter;
			}
			String maintypestatus = "0";
			if (maintype != null) {
				if (!maintype.equals("")) {
					maintypestatus = maintype;
					if (maintypestatus.equals("2")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						// cal.add(Calendar.DATE, -7);
						String currDate = dateFormat.format(cal.getTime());
						fromdate = currDate;
						todate = currDate;
					}

				}
			}

			if (fromdate != null) {
				if (!fromdate.equals("")) {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				} else {
					fromdate = null;
				}

			}
			if (todate != null) {
				if (!todate.equals("")) {
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					todate = null;
				}
			}

			if (searchtext != null) {

				if (searchtext.equals("")) {

					searchtext = null;
				}

			}
			String patient_type = ipdForm.getPatient_type();
			
			if(patient_type!=null){
				if(patient_type.equals("")){
					patient_type ="0";
				}
			}else{
				patient_type ="0";
			}
			ipdForm.setPatient_type(patient_type);
			
			if(actiontype.equals("1")) {
				 String fdate=ipdDAO.getfakeDischrgedate(fakclientid);
				 fdate=DateTimeUtils.isNull(fdate);
				  if(!fdate.equals("")) {
					  fdate=fdate.split(" ")[0];
				   }
				  actiontype=DateTimeUtils.isNull(actiontype);
				  if(!fdate.equals("")) {
				    if(actiontype.equals("1")){
					  fromdate=fdate;
					  searchtext=fuhid;
				}
			  }
				
			}
			
			
			
			/*
			 * if(fromdate == null){
			 * 
			 * }else{ formdatestr=fromdate; fromdate =
			 * DateTimeUtils.getCommencingDate1(fromdate); }
			 */

			//   25 jan 2018

			if (maintypestatus.equals("2")) {
				int count = ipdDAO.getTotalDischargeCount(searchtext, fromdate, todate, "6",patient_type);
				pagination.setPreperties(count);
				ipdForm.setTotalRecords(count);
				ArrayList<Bed> bedlist = ipdDAO.getAllIpdDischargeBedList(searchtext, fromdate, todate, "6", null,loginInfo,patient_type);
				ipdForm.setBedlist(bedlist);
				pagination.setTotal_records(bedlist.size());
				ipdForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			}else if(maintypestatus.equals("1")){
				fromdate = null;
				todate = null;
				int count = ipdDAO.getTotalDischargePatientCount(ipdForm.getWardid(), searchtext, fromdate,todate, "1",maintypestatus,patient_type);
				pagination.setPreperties(count);
				ipdForm.setTotalRecords(count);
				ArrayList<Bed> bedlist = ipdDAO.getAllIpdBedList(ipdForm.getWardid(), searchtext, fromdate,todate, "1", null, loginInfo,maintypestatus,patient_type);
				ipdForm.setBedlist(bedlist);
				pagination.setTotal_records(bedlist.size());
				ipdForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			}else {
				if (DateTimeUtils.isNull(status1).equals("6")) {
					int count = ipdDAO.getTotalDischargeCount(searchtext, fromdate, todate, "6",patient_type);
					pagination.setPreperties(count);
					ipdForm.setTotalRecords(count);
					ArrayList<Bed> bedlist = ipdDAO.getAllIpdDischargeBedList(searchtext, fromdate, todate, "6", pagination, loginInfo,patient_type);
					ipdForm.setBedlist(bedlist);
					pagination.setTotal_records(bedlist.size());
					ipdForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				} else {
					if(fromdate==null){
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						String currDate = dateFormat.format(cal.getTime());
						fromdate = currDate;
						fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					}
					if(todate==null){
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						String currDate = dateFormat.format(cal.getTime());
						todate = currDate;
						todate = DateTimeUtils.getCommencingDate1(todate);
					}
					int count = ipdDAO.getTotalDischargePatientCount(ipdForm.getWardid(), searchtext, fromdate,todate, status1,maintypestatus,patient_type);
					pagination.setPreperties(count);
					ipdForm.setTotalRecords(count);
					ArrayList<Bed> bedlist = ipdDAO.getAllIpdBedList(ipdForm.getWardid(), searchtext, fromdate,todate, status1, pagination, loginInfo,maintypestatus,patient_type);
					ipdForm.setBedlist(bedlist);
					pagination.setTotal_records(bedlist.size());
					ipdForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
				}
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
				} else {
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
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
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
			}
			
			ipdForm.setFromdate(fromdate);
			ipdForm.setTodate(todate);
			ipdForm.setFilter_status1(status1);
			
			BedDao bedDao = new JDBCBedDao(connection);
			//ArrayList<Bed> bedlist = bedDao.getAllBedList();
			ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
			//ipdForm.setBedlist(bedlist);
			ipdForm.setWardlist(wardlist);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return super.execute();
	}

	public String status() throws Exception {
		String treatmentepisodeid = request.getParameter("ipdtreatmentepisodeid");
		String clientid = request.getParameter("clientid");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			Discharge discharge = ipdDAO.getDischargeDetails(treatmentepisodeid);
			Client client = clientDAO.getClientDetails(clientid);
			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();

			ArrayList<Bed> arrayList1 = ipdDAO.getDischargeChecklistData(treatmentepisodeid, clientid, "1");
			ArrayList<Bed> arrayList2 = ipdDAO.getDischargeChecklistData(treatmentepisodeid, clientid, "2");
			ArrayList<Bed> arrayList3 = ipdDAO.getDischargeChecklistData(treatmentepisodeid, clientid, "3");
			ArrayList<Bed> arrayList4 = ipdDAO.getDischargeChecklistData(treatmentepisodeid, clientid, "4");

			String str = discharge.getDis_form_status() + "~" + discharge.getDis_form_time() + "~"
					+ discharge.getDis_mdicine_status() + "~" + discharge.getDis_mdicine_time() + "~"
					+ discharge.getDis_bill_status() + "~" + discharge.getDis_bill_time() + "~"
					+ discharge.getDis_nursing_status() + "~" + discharge.getDis_nursing_time() + "~"
					+ discharge.getDis_initiate_status() + "~" + discharge.getDis_initiate_time() + "~" + fullname;

			StringBuilder builder = new StringBuilder();
			builder.append("" + discharge.getDis_form_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_form_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_mdicine_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_mdicine_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_bill_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_bill_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_nursing_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_nursing_time() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_initiate_status() + "");
			builder.append("~");
			builder.append("" + discharge.getDis_initiate_time() + "");
			builder.append("~");
			builder.append("" + fullname + "");
			builder.append("~");
			builder.append("<ul>");
			for (Bed bed : arrayList1) {
				if (bed.getIsexecuted().equals("0")) {
					builder.append(
							"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashaclass"
									+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
									+ bed.getId() + "' type='checkbox'><i></i>" + bed.getDis_checklistname()
									+ "</label></li>");
				} else {
					builder.append(
							"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashaclass"
									+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
									+ bed.getId() + "' type='checkbox' checked><i></i>" + bed.getDis_checklistname()
									+ "</label></li>");
				}
			}
			builder.append("</ul>");

			builder.append("~");
			builder.append("<ul>");
			if (discharge.getDis_mdicine_status().equals("0")) {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input onclick='selectAllChecklist(this.value,this.checked,"
								+ treatmentepisodeid
								+ ")' value='akashbclass' type='checkbox'><i></i>Select All</label></li>");
			} else {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input type='checkbox'><i></i>Select All</label></li>");
			}
			for (Bed bed : arrayList2) {
				if (discharge.getDis_mdicine_status().equals("0")) {
					if (bed.getIsexecuted().equals("0")) {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' onclick='updateDischargeCheckListStatus(" + bed.getId()
										+ ",this.value)' type='checkbox'><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					} else {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' onclick='updateDischargeCheckListStatus(" + bed.getId()
										+ ",this.value)' type='checkbox' checked><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					}
				} else {
					if (bed.getIsexecuted().equals("0")) {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "'  type='checkbox'><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					} else {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "'  type='checkbox' checked><i></i>"
										+ bed.getDis_checklistname() + "</label></li>");
					}
				}

			}
			builder.append("</ul>");

			builder.append("~");
			builder.append("<ul>");
			if (discharge.getDis_bill_status().equals("0")) {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input onclick='selectAllChecklist(this.value,this.checked,"
								+ treatmentepisodeid
								+ ")' value='akashcclass' type='checkbox'><i></i>Select All</label></li>");
			} else {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input type='checkbox'><i></i>Select All</label></li>");
			}

			for (Bed bed : arrayList3) {
				if (discharge.getDis_bill_status().equals("0")) {
					if (bed.getIsexecuted().equals("0")) {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' onclick='updateDischargeCheckListStatus(" + bed.getId()
										+ ",this.value)' type='checkbox'><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					} else {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' onclick='updateDischargeCheckListStatus(" + bed.getId()
										+ ",this.value)' type='checkbox' checked><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					}
				} else {
					if (bed.getIsexecuted().equals("0")) {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' type='checkbox'><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					} else {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "'  type='checkbox' checked><i></i>"
										+ bed.getDis_checklistname() + "</label></li>");
					}
				}

			}
			builder.append("</ul>");

			builder.append("~");
			builder.append("<ul>");
			if (discharge.getDis_nursing_status().equals("0")) {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input onclick='selectAllChecklist(this.value,this.checked,"
								+ treatmentepisodeid
								+ ")' value='akashdclass' type='checkbox'><i></i>Select All</label></li>");
			} else {
				builder.append(
						"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input type='checkbox'><i></i>Select All</label></li>");
			}
			for (Bed bed : arrayList4) {
				if (discharge.getDis_nursing_status().equals("0")) {
					if (bed.getIsexecuted().equals("0")) {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' onclick='updateDischargeCheckListStatus(" + bed.getId()
										+ ",this.value)' type='checkbox'><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					} else {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' onclick='updateDischargeCheckListStatus(" + bed.getId()
										+ ",this.value)' type='checkbox' checked><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					}
				} else {
					if (bed.getIsexecuted().equals("0")) {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' type='checkbox'><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					} else {
						builder.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
										+ treatmentepisodeid + "' id='discklnameid" + bed.getId() + "' value='"
										+ bed.getId() + "' type='checkbox' checked><i></i>" + bed.getDis_checklistname()
										+ "</label></li>");
					}
				}
			}
			builder.append("</ul>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			// response.getWriter().write(""+str.toString()+"");
			response.getWriter().write("" + builder.toString() + "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public String update() throws Exception {
		// var url =
		// "updateInitialDischarge?column="+column+"&ipdtreatmentepisodeid="+ipdtreatmentepisodeid+"
		// ";
		String column = request.getParameter("column");
		String ipdtreatmentepisodeid = request.getParameter("ipdtreatmentepisodeid");
		String column2 = request.getParameter("column2");

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int valuetoupdate = ipdDAO.getValueToUpdate(column, ipdtreatmentepisodeid);

			int update = ipdDAO.updateInitialDischargeStatus(column, column2, valuetoupdate, datetime,
					ipdtreatmentepisodeid, loginInfo.getUserId());
			session.setAttribute("ipddash_forreload", "1");
			//ipdDAO.createAdmissionView();
			//ipdDAO.createDischargeView();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
		}

		return null;
	}

	public IpdForm getModel() {
		// TODO Auto-generated method stub
		return ipdForm;
	}

	public String changebed() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String wardid = ipdForm.getWardid();
			String bedid = ipdForm.getBedid();
			String treatmentepisodeid = ipdForm.getTreatmentepisodeid();
			String ipdid = ipdForm.getIpdid();

			int res = ipdDAO.updateDischargeBedandWard(bedid, wardid, ipdid);
			res = ipdDAO.updateDischargeDateandStatus(treatmentepisodeid, ipdForm.getAdmissiondetails());

			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> bedlist = bedDao.getAllBedList();
			ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
			ipdForm.setBedlist(bedlist);
			ipdForm.setWardlist(wardlist);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public void prepare() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			/*BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> bedlist = bedDao.getAllBedList();
			ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
			ipdForm.setBedlist(bedlist);
			ipdForm.setWardlist(wardlist);*/
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);

			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			ipdForm.setClinicName(clinic.getClinicName());
			ipdForm.setClinicOwner(clinic.getClinicOwner());
			ipdForm.setOwner_qualification(clinic.getOwner_qualification());
			ipdForm.setLocationAdressList(locationAdressList);
			ipdForm.setAddress(clinic.getAddress());
			ipdForm.setLandLine(clinic.getLandLine());
			ipdForm.setClinicemail(clinic.getEmail());
			ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
			ipdForm.setClinicLogo(clinic.getUserImageFileName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public String updatealldischargechecklist() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String treatmentid = request.getParameter("treatmentid");
			String ische = request.getParameter("val");
			String modelid = request.getParameter("modelll");
			String ipdclientid = request.getParameter("ipdclientid");
			String userid = loginInfo.getUserId();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String todate = dateFormat.format(calendar.getTime());
			String result = "0";

			ArrayList<Bed> arrayList1 = ipdDAO.getDischargeChecklistData(treatmentid, ipdclientid, modelid);
			for (Bed bed : arrayList1) {
				boolean flag = ipdDAO.isDisCheckListAlreadyPresent(String.valueOf(bed.getId()));
				if (flag) {
					if (ische.equals("true")) {
						int res = ipdDAO.updateCheckListStatusById(String.valueOf(bed.getId()), userid, todate, "1");
						result = "1";
					} else {
						int res = ipdDAO.updateCheckListStatusModicfy(String.valueOf(bed.getId()), userid, todate, "0",
								ische);
						result = "0";
					}
				} else {
					if (ische.equals("true")) {
						int res = ipdDAO.updateCheckListStatusModicfy(String.valueOf(bed.getId()), userid, todate, "1",
								ische);
						result = "1";
					} else {
						int res = ipdDAO.updateCheckListStatusModicfy(String.valueOf(bed.getId()), userid, todate, "0",
								ische);
						result = "0";
					}
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
	public String diagnosisdashboard(){
	

	 Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String patient_department=ipdForm.getPatient_department();
			String fromdate = ipdForm.getFromdate();
			String todate = ipdForm.getTodate();
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
//			ArrayList<TreatmentType> conditionlist=treatmentTypeDAO.getConditionList();
			ArrayList<TreatmentType> smstemplate=treatmentTypeDAO.getsmstemplateList();
			if(patient_department==null){
				patient_department="IPD";
			}
//			ipdForm.setConditionlist(conditionlist);
			ipdForm.setSmstemplate(smstemplate);
			String diagnose=ipdForm.getDiagnose();
			if(diagnose==null){
				diagnose="";
			}
			if(fromdate == null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				fromdate = dateFormat.format(cal.getTime());			
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				
			}
			else {
				
				if(fromdate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					//cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());			
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					
				} else {
					
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);		 
				}		
			}
			
			if(todate== null){
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance(); 
				todate = dateFormat.format(cal.getTime());
				todate = DateTimeUtils.getCommencingDate1(todate);
			} else {
				
				if(todate.equals("")){
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance(); 
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					todate = DateTimeUtils.getCommencingDate1(todate);
				}
				
			}
			String filter_sortby = ipdForm.getFilter_sortby();
			if (filter_sortby == null) {
				filter_sortby = "1";
			}
			
			ipdForm.setFilter_sortby(filter_sortby);
			ArrayList<Bed> ipddiagnosis=new ArrayList<Bed>();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		
			if(patient_department.equals("IPD")){
			ipddiagnosis = ipdDAO.getIPDfinalDiagnosisList(fromdate,todate,diagnose,filter_sortby);
			}else{
			ipddiagnosis = ipdDAO.getOPDfinalDiagnosisList(fromdate,todate,diagnose,filter_sortby,loginInfo);
			}
			
			ipdForm.setIpddiagnosis(ipddiagnosis);
			ipdForm.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
			ipdForm.setTodate(DateTimeUtils.getCommencingDate1(todate));
			//IpdDAO ipdDAO = new JDBCIpdDAO(connection);
//			int count = mrdDAO.getTotalMrdCount(fromdate,todate,searchtext,department,searchbydate);
//			pagination.setPreperties(count);
			
			//ArrayList<Mrd> arrayList =  mrdDAO.getmrdlist(fromdate,todate,searchtext,department,pagination,searchbydate,searchbyipdid,loginInfo.getIpd_abbr_access());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "diagnosisdashboard";
	}

	public String sendindividualsms() {
	Connection connection = null;
	try {
			connection = Connection_provider.getconnection();
				String clientid=request.getParameter("val");
				String msg=request.getParameter("sms");
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				  Client client = clientDAO.getClientDetails(clientid);
					String clientname = client.getTitle() + " " +client.getFirstName() + " " + client.getLastName();
				     SmsService service= new SmsService();
				     String templateid="";
				     service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
				} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
		}
	
	
	
	public String sendgroupsms() {
		Connection connection = null;
		try {
				connection = Connection_provider.getconnection();
					String clientid=request.getParameter("val");
					String msg=request.getParameter("sms");
					ClientDAO clientDAO=new JDBCClientDAO(connection);
					String sepclientid[]=clientid.split(",");
					for (String string : sepclientid) {
						
					
					  Client client = clientDAO.getClientDetails(clientid);
					     SmsService service= new SmsService();
					     String templateid="";
					     service.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
					}
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("");
					} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
			}
		public String savesmstemplate(){
			Connection connection = null;
			try {
					connection = Connection_provider.getconnection();
					AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
					String msg=request.getParameter("sms");
					String tempname=request.getParameter("tempname");
					int res=accountsDAO.savesmstemplate(msg,tempname);	
					response.setContentType("text/html");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write("");
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getsmstemplate(){
			Connection connection = null;
			try {
					connection = Connection_provider.getconnection();
					AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
					String id=request.getParameter("tempid");
					String res=accountsDAO.getsmstemplate(id);	
					response.setContentType("text/html");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(""+res+"");
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		public String getsmstemplatelist(){
			Connection connection = null;
			try {
					connection = Connection_provider.getconnection();
					TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
			ArrayList<TreatmentType> smstemplate=treatmentTypeDAO.getsmstemplateList();
			StringBuffer str = new StringBuffer();
			str.append("<select class='form-control' onchange='getsmstemplate(this.value)'>");
			str.append("<option value='0'>Select Template</option>");
			
			for (TreatmentType treatmentType : smstemplate) {
				
				str.append("<option value='"+treatmentType.getId()+"'>"+treatmentType.getSmstmp()+"</option>");
			}
			
			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+"");
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
		}
}
