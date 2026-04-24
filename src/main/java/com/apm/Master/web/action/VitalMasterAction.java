package com.apm.Master.web.action;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class VitalMasterAction extends BaseAction implements ModelDriven<MasterForm>, Preparable {

	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	private String mastername;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(20, 1);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public MasterForm getModel() {
		return masterForm;
	}

	public String execute() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count = masterDAO.getCountVitalMasterList();
			pagination.setPreperties(count);
			masterForm.setTotalRecords(count);
			ArrayList<Master> vitalMasterList = masterDAO.getAllVitalList(pagination);
			pagination.setTotal_records(vitalMasterList.size());
			masterForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			mastername = request.getParameter("selectedid");
			if (mastername != null) {
				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setVitalMasterList(vitalMasterList);
			masterForm.setMastername(mastername);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return super.execute();
	}

	public String add() {

		return "add";
	}

	public String save() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setName(masterForm.getName());
			master.setVital_type(masterForm.getVital_type());
			master.setMin_value_m(masterForm.getMin_value_m());
			master.setMax_value_m(masterForm.getMax_value_m());
			int res = masterDAO.saveVitalMaster(master);

			if (masterForm.getUserFileContentType() != null) {

				File file = masterForm.getUserFile();
				String filename = masterForm.getUserFileFileName();
				filename = loginInfo.getClinicUserid() + "_" + res + "_" + filename;
				String filePath = request.getRealPath("/liveData/vital/");
//				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, filename);
				FileUtils.copyFile(file, fileToCreate);

				int d = masterDAO.updateVitalImageName(res, filename);

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String edit() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");
			Master master = masterDAO.getVitalMasterData(id);
			masterForm.setName(master.getName());
			masterForm.setVital_type(master.getVital_type());
			masterForm.setId(master.getId());
			masterForm.setUnit(master.getUnit());
			masterForm.setMax_value_m(master.getMax_value_m());
			masterForm.setMin_value_m(master.getMin_value_m());
			String filePath = request.getRealPath("/liveData/vital/");

			File file = new File(filePath + "/" + master.getUserFileFileName());
			masterForm.setFileName(file);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "edit";
	}

	public String update() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setName(masterForm.getName());
			master.setId(masterForm.getId());
			master.setVital_type(masterForm.getVital_type());
			master.setUnit(masterForm.getUnit());
			master.setMin_value_m(masterForm.getMin_value_m());
			master.setMax_value_m(masterForm.getMax_value_m());
			int res = masterDAO.updateVitalMaster(master);

			if (masterForm.getUserFileContentType() != null) {

				File file = masterForm.getUserFile();
				String filename = masterForm.getUserFileFileName();
				filename = loginInfo.getClinicUserid() + "_" + res + "_" + filename;
				String filePath = request.getRealPath("/liveData/vital/");
//				System.out.println("Server path:" + filePath);
				File fileToCreate = new File(filePath, filename);
				FileUtils.copyFile(file, fileToCreate);

				int d = masterDAO.updateVitalImageName(res, filename);

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String delete() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String id = request.getParameter("id");

			int res = masterDAO.deleteVitalMaster(id);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	public void prepare() throws Exception {

		// TODO Auto-generated method stub
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			mastername = (String) session.getAttribute("mastername");
			masterForm.setMastername(mastername);

			ArrayList<Master> vitalTypeList = masterDAO.getAllVitalTypeList();
			masterForm.setVitalTypeList(vitalTypeList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

}
