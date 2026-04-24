package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.hssf.record.FeatRecord;
import org.apache.struts2.convention.annotation.Results;

import com.a.a.a.a.a.c;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCMasterDAO extends JDBCBaseDAO implements MasterDAO {

	public JDBCMasterDAO(Connection connection) {
		this.connection = connection;

	}

	public int getTotalNewchargeCount() {
		int result = 0;
		try {
			String sql = "select count(*) from apm_newchargetype";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getProductTypeMasterCount() {
		int result = 0;
		try {
			String sql = "select count(*) from apm_productType";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getOtherTemplateCount() {

		int result = 0;
		try {
			String sql = "select count(*) from apm_other_template where payroll=0";
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

	public int getTotalapmEventsCount() {

		int result = 0;
		try {
			String sql = "select count(*) from apm_no_avail_reason";
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

	public int getTotalLocationCount() {

		int result = 0;
		try {
			String sql = "select count(*) from apm_condition";
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

	public ArrayList<Master> getOccupationList(Pagination pagination, String searchText) {

		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		if (pagination.sortColumn == null) {
			if (searchText != null) {
				sql = "SELECT id,occupation FROM occupation where occupation like ('" + searchText
						+ "%') order by id desc";
			} else {
				sql = "SELECT id,occupation FROM occupation order by id desc";
			}
		} else {
			if (searchText != null) {
				sql = "SELECT id,occupation FROM occupation where occupation like ('" + searchText + "%')";
			} else {
				sql = "SELECT id,occupation FROM occupation";
			}
		}
		sql = pagination.getSQLQuery(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setOccupation(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalOccupationCount(String searchText) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "";
		if (searchText != null) {
			sql = "select count(*) from occupation where occupation like ('" + searchText + "%')";
		} else {
			sql = "select count(*) from occupation";
		}
		// String sql = "select count(*) from occupation";
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

	public int saveOccupation(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into occupation(occupation) values(?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getOccupation());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Master getOccupation(int id, Master master) {
		PreparedStatement preparedStatement = null;

		String sql = "SELECT id,occupation FROM occupation where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setOccupation(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateOccupation(Master master, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update occupation set occupation = ? where id = " + id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getOccupation());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteOccupation(int id, Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from occupation where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getJobTitleList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		if (pagination.sortColumn == null) {
			sql = "SELECT id,jobtitle,jobgroup_id FROM job_title order by id desc";
		} else {
			sql = "SELECT id,jobtitle,jobgroup_id FROM job_title";
		}
		sql = pagination.getSQLQuery(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setJobTitle(rs.getString(2));
				master.setJobtitlegroup_id(rs.getString(3));
				String jobgroup = getJobTitleGroup(master.getJobtitlegroup_id());
				master.setJobtitlegroup(jobgroup);

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalJobTitleCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;

		String sql = "select count(*) from job_title";
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

	public int saveJobTitle(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into job_title(jobtitle,jobgroup_id) values(?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getJobTitle());
			preparedStatement.setString(2, master.getJobtitlegroup_id());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Master getJobTitle(int id, Master master) {
		PreparedStatement preparedStatement = null;

		String sql = "SELECT id,jobtitle,jobgroup_id FROM job_title where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setJobTitle(rs.getString(2));
				master.setJobtitlegroup_id(rs.getString(3));

				String jobtitlegroup = getJobTitleGroup(master.getJobtitlegroup_id());
				master.setJobTitle(jobtitlegroup);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateJobTitle(Master master, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update job_title set jobtitle = ?,jobgroup_id=? where id = " + id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getJobTitle());
			preparedStatement.setString(2, master.getJobtitlegroup_id());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteJobTitle(int id, Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from job_title where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getReferenceList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		if (pagination.sortColumn == null) {
			sql = "SELECT id, name, dateTime, speciality, mobno, email, isvisitingconsultant, issurgeon, isanesthesiologist, fees, isrefered, ismlc, mlcqualification FROM reference order by id desc";
		} else {
			sql = "SELECT id, name, dateTime, speciality, mobno, email, isvisitingconsultant, issurgeon, isanesthesiologist, fees, isrefered, ismlc, mlcqualification FROM reference";

		}
		sql = pagination.getSQLQuery(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setReference(rs.getString(2));
				master.setDatetime(rs.getString(3));

				master.setMobileNo(rs.getString(5));
				master.setEmail(rs.getString(6));
				master.setVisitingConsultant(rs.getString(7));
				master.setSurgeon(rs.getString(8));
				master.setAnesthesiologist(rs.getString(9));
				master.setFees(rs.getString(10));
				master.setReferred(rs.getString(11));
				master.setMlc(rs.getString(12));
				master.setQualification(rs.getString(13));

				Master master2 = getDisciplineData(rs.getString(4));

				master.setSpeciality(master2.getDiscipline());

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalReferenceCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;

		String sql = "select count(*) from reference";
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

	public int saveReference(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into reference(name,dateTime) values(?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getReference());

			preparedStatement.setString(2, DateTimeUtils.getCurrentDateInDDMMYYYYCasting());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Master getReference(int id, Master master) {
		PreparedStatement preparedStatement = null;

		String sql = "SELECT id,name,dateTime FROM reference where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setReference(rs.getString(2));
				master.setDatetime(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateReference(Master master, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update reference set name = ? where id = " + id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getReference());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteReference(int id, Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from reference where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalDisciplineCount(String searchText) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "";
		if (searchText != null)
			sql = "SELECT count(*) FROM apm_discipline where discipline like '" + searchText + "%'";
		else
			sql = "SELECT count(*) FROM apm_discipline ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getDisciplineList(Pagination pagination, String searchText) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		if (searchText != null)
			sql = "SELECT id,discipline,discription,area,floor,room_no,showdeptlist FROM apm_discipline where discipline like '"
					+ searchText + "%'";
		else
			sql = "SELECT id,discipline,discription,area,floor,room_no,showdeptlist FROM apm_discipline";
		sql = pagination.getSQLQuery(sql);
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));
				master.setDescription(rs.getString(3));
				master.setArea(rs.getString(4));
				master.setFloor(rs.getString(5));
				master.setRoom_no(rs.getString(6));
				master.setShowdeptlist(rs.getString(7));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int saveDiscipline(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_discipline (discipline,discription,area,floor,room_no,showdeptlist) values(?,?,?,?,?,?) ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getDiscipline());
			preparedStatement.setString(2, master.getDescription());
			preparedStatement.setString(3, master.getArea());
			preparedStatement.setString(4, master.getFloor());
			preparedStatement.setString(5, master.getRoom_no());
			preparedStatement.setString(6, master.getShowdeptlist());
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getDisciplineData(String selectedid) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "select discipline,discription,area,floor,room_no,id,showdeptlist FROM apm_discipline where id = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				master.setDiscipline(rs.getString(1));
				master.setDescription(rs.getString(2));
				master.setArea(rs.getString(3));
				master.setFloor(rs.getString(4));
				master.setRoom_no(rs.getString(5));
				master.setId(rs.getInt(6));
				if (rs.getString(7).equals("1")) {
					master.setShowdeptlist("true");
				} else {
					master.setShowdeptlist("false");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateDiscipline(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_discipline set discipline=?,discription=?,area=?,floor=?,room_no=?,showdeptlist=?  where id=? ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getDiscipline());
			preparedStatement.setString(2, master.getDescription());
			preparedStatement.setString(3, master.getArea());
			preparedStatement.setString(4, master.getFloor());
			preparedStatement.setString(5, master.getRoom_no());
			preparedStatement.setString(6, master.getShowdeptlist());
			preparedStatement.setInt(7, master.getId());
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteDiscipline(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_discipline where id=" + selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getDisciplineDataList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getPractitonerTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_practitoner_type ";

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

	public int addNotAvailableReason(Master master) {
		// TODO Auto-generated method stub
		int i = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_no_avail_reason (name,discription) values (?,?)");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			i = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return i;
	}

	public ArrayList<Master> getNotAvaiableReasonList(Pagination pagination) {
		// TODO Auto-generated method stub

		ArrayList<Master> masters = new ArrayList<Master>();

		try {
			PreparedStatement ps = connection.prepareStatement("select * from apm_no_avail_reason");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String discription = rs.getString(3);
				master.setId(id);
				master.setName(name);
				master.setDescription(discription);
				masters.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return masters;
	}

	public Master getNotAvailableReason(int id, Master master) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement ps = connection
					.prepareStatement("select name,discription from apm_no_avail_reason where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString(1);
				String description = rs.getString(2);
				master.setName(name);
				master.setId(id);
				master.setDescription(description);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateNotAvilableReason(Master master) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("update apm_no_avail_reason set name=?,discription=? where id=?");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			ps.setInt(3, master.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteNotAvilableReason(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection.prepareStatement("delete from apm_no_avail_reason where id=?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getMasterList(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		ArrayList<Master> masters = new ArrayList<Master>();
         PreparedStatement ps=null;
		try {
			 if(loginInfo.getId()==1){
			       ps=connection.prepareStatement("select * from apm_select_master order by mastername");
			  }
			  else{
			       ps=connection.prepareStatement("select * from apm_select_master where id!=22 order by mastername");
			  }
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String mastername = rs.getString(2);
				master.setId(id);
				master.setName(mastername);
				masters.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return masters;
	}

	public ArrayList<Master> getCountryCodeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT countryid,country,code FROM country_code ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setCountryid(rs.getString(1));
				master.setCountryName(rs.getString(2) + " (+" + rs.getString(3) + ")");

				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Master> getNewChargeTypeList(String searchText, Pagination pagination) {
		ArrayList<Master> masters = new ArrayList<Master>();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select * from apm_newchargetype where name like ('" + searchText + "%')";
			} else {
				sql = "select * from apm_newchargetype";
			}

			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String discription = rs.getString(3);
				boolean procedure = rs.getBoolean(4);
				int breakage = rs.getInt("breakage");
				master.setId(id);
				master.setName(name);
				master.setDescription(discription);
				master.setProcedure(procedure);
				master.setBreakage(breakage);
				Master master2 = getDisciplineData(discription);
				master.setDescription(master2.getDiscipline());
				master.setConsultant_compulsay(rs.getBoolean("compulsay_consultant"));
				/*
				 * String wardname = ipdDAO.getIpdWardName(rs.getString(5));
				 * master.setWardid(wardname);
				 */
				masters.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return masters;
	}

	public ArrayList<Master> getchargetypelist(String sid) {

		ArrayList<Master> masters = new ArrayList<Master>();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);

		try {

			String sql = "select * from apm_newchargetype where procedures=1 and sittingid='" + sid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String discription = rs.getString(3);
				boolean procedure = rs.getBoolean(4);
				int breakage = rs.getInt("breakage");
				master.setId(id);
				master.setName(name);
				master.setDescription(discription);
				master.setProcedure(procedure);
				master.setBreakage(breakage);
				Master master2 = getDisciplineData(discription);
				master.setDescription(master2.getDiscipline());
				master.setConsultant_compulsay(rs.getBoolean("compulsay_consultant"));
				/*
				 * String wardname = ipdDAO.getIpdWardName(rs.getString(5));
				 * master.setWardid(wardname);
				 */
				masters.add(master);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return masters;

	}

	public ArrayList<Master> getchargetype() {

		ArrayList<Master> masters = new ArrayList<Master>();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);

		try {

			String sql = "select * from apm_newchargetype where procedures=1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String discription = rs.getString(3);
				boolean procedure = rs.getBoolean(4);
				int breakage = rs.getInt("breakage");
				master.setId(id);
				master.setName(name);
				master.setDescription(discription);
				master.setProcedure(procedure);
				master.setBreakage(breakage);
				Master master2 = getDisciplineData(discription);
				master.setDescription(master2.getDiscipline());
				master.setConsultant_compulsay(rs.getBoolean("compulsay_consultant"));
				/*
				 * String wardname = ipdDAO.getIpdWardName(rs.getString(5));
				 * master.setWardid(wardname);
				 */
				masters.add(master);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return masters;

	}

	public int addNewChargeType(Master master) {
		int result = 0;
		try {

			PreparedStatement ps = connection.prepareStatement(
					"insert into apm_newchargetype (name,description,procedures,compulsay_consultant,sittingid) values (?,?,?,?,?)");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			ps.setBoolean(3, master.isProcedure());
			ps.setBoolean(4, master.isConsultant_compulsay());
			ps.setString(5, master.getSitting());
			/* ps.setString(4, master.getWardid()); */
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getNewChargeType(int id, Master master) {
		try {

			PreparedStatement ps = connection.prepareStatement("select * from apm_newchargetype where id=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int iid = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				boolean procedure = rs.getBoolean(4);
				master.setId(iid);
				master.setName(name);
				master.setDescription(description);
				master.setProcedure(procedure);
				master.setConsultant_compulsay(rs.getBoolean(9));
				/* master.setWardid(rs.getString(5)); */
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateNewChargetype(Master master) {
		int result = 0;
		try {

			PreparedStatement ps = connection.prepareStatement(
					"update apm_newchargetype set name=?,description=?,procedures=?,compulsay_consultant=? where id="
							+ master.getId() + "");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			ps.setBoolean(3, master.isProcedure());
			ps.setBoolean(4, master.isConsultant_compulsay());
			/* ps.setString(4, master.getWardid()); */
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteNewChargeType(int id) {
		int result = 0;
		try {

			PreparedStatement ps = connection.prepareStatement("delete from apm_newchargetype where id=?");
			ps.setInt(1, id);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Master> getAllOtherTemplateList(String searchText, Pagination pagination) {
		// TODO Auto-generated method stub
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id,othertemplate_text,title,discipline_id from apm_other_template where title like ('"
						+ searchText + "%') and payroll=0";
			} else {
				sql = "select id,othertemplate_text,title,discipline_id from apm_other_template where payroll=0";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String other_template_text = rs.getString(2);
				String title = rs.getString(3);
				master.setId(id);
				master.setOther_template_text(other_template_text);
				master.setTitle(title);
				master.setDiscipline_id(rs.getString(4));

				Master master2 = getDisciplineData(master.getDiscipline_id());
				master.setDiscipline(master2.getDiscipline());

				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int addOtherTemplate(Master master) {

		int result = 0;
		try {

			PreparedStatement ps = connection.prepareStatement(
					"insert into apm_other_template (othertemplate_text,title,discipline_id,payroll) values (?,?,?,?)");
			ps.setString(1, master.getOther_template_text());
			ps.setString(2, master.getTitle());
			ps.setString(3, master.getDiscipline_id());
			ps.setString(4, master.getPayroll());
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getOtherTemplate(String selectedid) {
		// TODO Auto-generated method stub

		Master master = new Master();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select othertemplate_text,title,discipline_id from apm_other_template where id=?");
			ps.setString(1, selectedid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String other_template_text = rs.getString(1);
				String title = rs.getString(2);
				int id = Integer.parseInt(selectedid);
				master.setId(id);
				master.setOther_template_text(other_template_text);
				master.setTitle(title);
				master.setDiscipline_id(rs.getString(3));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateOtherTemplate(Master master) {
		int result = 0;
		try {

			String sql = "update apm_other_template set othertemplate_text=?,title=?,discipline_id=? where id="
					+ master.getId() + "";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getOther_template_text());
			ps.setString(2, master.getTitle());
			ps.setString(3, master.getDiscipline_id());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteOtherTemplate(String selectedid) {

		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement("delete from apm_other_template where id=?");
			ps.setString(1, selectedid);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getEmrTemplateList(String disciplineid) {
		ArrayList<Master> list = new ArrayList<Master>();

		String sql = "";

		try {

			if (disciplineid == null) {
				sql = "select id,othertemplate_text,title,discipline_id from apm_other_template";
			} else {
				sql = "select id,othertemplate_text,title,discipline_id from apm_other_template where discipline_id="
						+ disciplineid + "";
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String other_template_text = rs.getString(2);
				String title = rs.getString(3);
				master.setId(id);
				master.setOther_template_text(other_template_text);
				master.setTitle(title);
				master.setDiscipline_id(rs.getString(4));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Master> getMedicineList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,drug,genericname,catalogueid FROM apm_medicine_details where istempmed='0' and drug is not null and drug!='' order by drug ";
		// InventoryProductDAO inventoryProductDAO= new
		// JDBCInventoryProductDAO(connection);
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				// String catalogueid= rs.getString(4);
				// int stock
				// =inventoryProductDAO.getStockFromProduct(catalogueid);
				/*
				 * master.setGenericname(rs.getString(3)+" - "+master.getName()+
				 * "");
				 */
				if (rs.getString(3) != null) {
					if (!rs.getString(3).equals("")) {
						master.setGenericname(rs.getString(3) + " - " + master.getName() + "");
					} else {
						master.setGenericname("" + master.getName() + "");
					}
				} else {
					master.setGenericname("" + master.getName() + "");
				}
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getWardList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,wardname FROM apm_ipd_ward ";

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

	public int getTotalExpenceCategoryCount() {

		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement("select count(*) from apm_expence_category");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getExpenceCategoryList(Pagination pagination) {

		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "select id,name,description from apm_expence_category";
			sql = pagination.getSQLQuery(sql);

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setDescription(rs.getString(3));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return list;
	}

	public int addExpenceCategory(Master master) {

		int result = 0;

		try {

			String sql = "insert into apm_expence_category (name,description) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getExpenceCategory(String selectedid) {

		Master master = new Master();

		try {

			String sql = "select name,description from apm_expence_category where id='" + selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setName(rs.getString(1));
				master.setDescription(rs.getString(2));
				master.setId(Integer.parseInt(selectedid));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateExpenceCategory(Master master) {

		int result = 0;

		try {

			String sql = "update apm_expence_category set name=?,description=? where id=" + master.getId() + "";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteExpenceCategory(String selectedid) {

		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("delete from apm_expence_category where id='" + selectedid + "'");

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getDosageNoteList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id,note from apm_dosage_note ";

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

	public ArrayList<Master> getStandardChargesList(Pagination pagination, String SearchText) {

		ArrayList<Master> list = new ArrayList<Master>();
		BedDao bedDao = new JDBCBedDao(connection);
		try {
			String sql = "";

			if (SearchText != null) {
				sql = "select id, wardid, name, charges,payby from apm_standard_charges where name like ('" + SearchText
						+ "%') ";
			} else {
				sql = "select id, wardid, name, charges,payby from apm_standard_charges";
			}

			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				Bed bed = bedDao.getWard(rs.getString(2));
				master.setWardid(bed.getWardname());
				master.setName(rs.getString(3));
				master.setCharge(rs.getString(4));
				master.setPayby(rs.getString(5));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public String getMasterChargeType(String chargeTypeID) {

		String chargeType = "0";
		try {

			String sql = "select name from apm_newchargetype where id='" + chargeTypeID + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				chargeType = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return chargeType;
	}

	public int getTotalStandardChargesCount() {

		int result = 0;

		try {

			String sql = "select count(*) from apm_standard_charges";
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

	public int addStandardCharge(Master master) {

		int result = 0;

		try {

			String sql = "insert into apm_standard_charges (wardid, name, charges,payby) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getWardid());
			ps.setString(2, master.getName());
			ps.setString(3, master.getCharge());
			ps.setString(4, master.getPayby());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getStandardCharge(String id) {

		Master master = new Master();

		try {

			String sql = "select wardid,name,charges,payby from apm_standard_charges where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setWardid(rs.getString(1));
				master.setName(rs.getString(2));
				master.setCharge(rs.getString(3));
				master.setPayby(rs.getString(4));
				master.setId(Integer.parseInt(id));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateStandardCharge(Master master) {

		int result = 0;

		try {

			String sql = "update apm_standard_charges set wardid=?,name=?,charges=?,payby=? where id=" + master.getId()
					+ "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getWardid());
			ps.setString(2, master.getName());
			ps.setString(3, master.getCharge());
			ps.setString(4, master.getPayby());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteStandardCharge(String id) {

		int result = 0;

		try {

			String sql = "delete from apm_standard_charges where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getAllNursingCategory(Pagination pagination) {

		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id,name,description from apm_nursing_category";
		try {
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setDescription(rs.getString(3));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int getNursingCategoryCount() {

		int result = 0;

		try {

			String sql = "select count(*) from apm_nursing_category";
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

	public int addNursingCategory(Master master) {

		int result = 0;
		try {

			String sql = "insert into apm_nursing_category (name,description) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public Master getNursingCategory(String selectedid) {

		Master master = new Master();
		try {

			String sql = "select name,description from apm_nursing_category where id='" + selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				master.setName(resultSet.getString(1));
				master.setDescription(resultSet.getString(2));
				master.setId(Integer.parseInt(selectedid));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateNursingCategory(Master master) {

		int result = 0;

		try {
			String sql = "update apm_nursing_category set name=?,description=? where id=" + master.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteNursingCategory(String id) {

		int result = 0;

		try {
			String sql = "delete from apm_nursing_category where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getAllNursingDetails(Pagination pagination, String searchText) {

		ArrayList<Master> list = new ArrayList<Master>();

		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id, nursingtype_id, taskname, frequency, notes from apm_nursing_details where taskname like ('"
						+ searchText + "%')";
			} else {
				sql = "select id, nursingtype_id, taskname, frequency, notes from apm_nursing_details";
			}
			// String sql="select id, nursingtype_id, taskname, frequency, notes
			// from apm_nursing_details";

			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setNursingtype_id(rs.getString(2));
				Master master2 = getNursingCategory(master.getNursingtype_id());
				master.setNursingtype(master2.getName());
				master.setTaskname(rs.getString(3));
				master.setFrequency(rs.getString(4));
				master.setNotes(rs.getString(5));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int getTotalNursingDetailsCount(String searchText) {

		int result = 0;
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select count(*) from apm_nursing_details where taskname like ('" + searchText + "%')";
			} else {
				sql = "select count(*) from apm_nursing_details";
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

	public int addNursingDetails(Master master) {

		int result = 0;
		try {

			String sql = "insert into apm_nursing_details (nursingtype_id, taskname, frequency, notes) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getNursingtype_id());
			ps.setString(2, master.getTaskname());
			ps.setString(3, master.getFrequency());
			ps.setString(4, master.getNotes());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getNursingDetails(String selectedid) {

		Master master = new Master();

		try {
			String sql = "select nursingtype_id, taskname, frequency, notes from apm_nursing_details where id='"
					+ selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setNursingtype_id(rs.getString(1));
				master.setTaskname(rs.getString(2));
				master.setFrequency(rs.getString(3));
				master.setNotes(rs.getString(4));
				master.setId(Integer.parseInt(selectedid));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int deleteNursingDetails(String selectedid) {

		int result = 0;
		try {
			String sql = "delete from apm_nursing_details where id='" + selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateNursingDetails(Master master) {

		int result = 0;
		try {

			String sql = "update apm_nursing_details set nursingtype_id=?, taskname=?, frequency=?, notes=? where id="
					+ master.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getNursingtype_id());
			ps.setString(2, master.getTaskname());
			ps.setString(3, master.getFrequency());
			ps.setString(4, master.getNotes());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getJobgroupList() {

		ArrayList<Master> list = new ArrayList<Master>();

		try {
			String sql = "select id,name from jobtitle_group";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public String getJobTitleGroup(String jobgroup_id) {

		String name = "";
		try {
			String sql = "SELECT name from jobtitle_group where id='" + jobgroup_id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				name = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return name;
	}

	public ArrayList<Master> getAllGodownlist(Pagination pagination) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, name, description from apm_inventory_godown";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setDescription(rs.getString(3));
				list.add(master);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int getAllGodownCount() {

		int result = 0;
		try {

			String sql = "select count(*) from apm_inventory_godown";
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

	public int updateSMSCounter(int smscounter, String sms) {

		int result = 0;
		try {

			smscounter = smscounter + getSMSCount();
			String sql = "update apm_sms_counter set count=?,sms=? where id=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, smscounter);
			ps.setString(2, sms);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int getSMSCount() {

		int count = 0;

		try {

			String sql = "SELECT count from apm_sms_counter where id=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	public int addGodown(Master master) {

		int result = 0;
		try {

			String sql = "insert into apm_inventory_godown (name,description) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public Master getGodown(String id) {

		Master master = new Master();
		try {

			String sql = "SELECT name,description from apm_inventory_godown where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setName(rs.getString(1));
				master.setDescription(rs.getString(2));
				master.setId(Integer.parseInt(id));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateGowdown(Master master) {

		int result = 0;

		try {

			String sql = "update apm_inventory_godown set name=?,description=? where id=" + master.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteGodown(String id) {

		int result = 0;
		try {
			String sql = "DELETE from apm_inventory_godown where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getIpdTemplateList(Pagination pagination, String SearchText) {

		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		try {
			if (SearchText != null) {
				sql = "SELECT id, template_nameid, text, name, department from ipd_template where name like ('"
						+ SearchText + "%')";

			} else {
				sql = "SELECT id, template_nameid, text, name, department from ipd_template order by id DESC";
			}

			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTemplate_nameid(rs.getString(2));
				master.setTemplate_text(getIpdTemplatenamefromId(rs.getString(2)));
				master.setText(rs.getString(3));
				master.setName(rs.getString(4));
				master.setDepartment(rs.getString(5));
				list.add(master);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int getIpdTemplateCount() {

		int result = 0;
		try {

			String sql = "select count(*) from ipd_template";
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

	public ArrayList<Master> getIpdTemplateNameList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, name from ipd_templatename";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public int saveIpdTemplate(Master master) {

		int result = 0;
		try {

			String sql = "insert into ipd_template(template_nameid, text, name, department,speciality,showall) values (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getTemplate_nameid());
			ps.setString(2, master.getTemplate_text());
			ps.setString(3, master.getTitle());
			ps.setString(4, "1");
			ps.setString(5, master.getDepartment());
			ps.setString(6, master.getShowall());
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String getIpdTemplatenamefromId(String id) {

		String s = "";
		try {

			String sql = "SELECT name from ipd_templatename where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				s = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return s;
	}

	public String getIpdTemplateId(String templateName) {

		String id = "";
		try {

			String sql = "SELECT id from ipd_templatename where name='" + templateName + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return id;
	}

	public ArrayList<Master> getIpdTemplateListNames(String templateNameId) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, template_nameid, text, name, department from ipd_template where showall='1' or template_nameid='"
					+ templateNameId + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTemplate_nameid(rs.getString(2));
				master.setTemplate_text(getIpdTemplatenamefromId(rs.getString(2)));
				master.setText(rs.getString(3));
				master.setName(rs.getString(4));
				master.setDepartment(rs.getString(5));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Master getIpdTemplate(String id) {

		Master master = new Master();
		try {

			String sql = "SELECT id, template_nameid, text, name, department,speciality,showall from ipd_template where id="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setTemplate_nameid(rs.getString(2));
				master.setTemplate_text(getIpdTemplatenamefromId(rs.getString(2)));
				master.setText(rs.getString(3));
				master.setTitle(rs.getString(4));
				master.setDepartment(rs.getString(5));
				master.setSpeciality(rs.getString(6));
				master.setShowall(rs.getString(7));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateIpdTemplate(Master master) {

		int result = 0;
		try {

			String sql = "update ipd_template set template_nameid=?,text=?,name=?,speciality=?,showall=? where id="
					+ master.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getTemplate_nameid());
			ps.setString(2, master.getText());
			ps.setString(3, master.getTitle());
			ps.setString(4, master.getSpeciality());
			ps.setString(5, master.getShowall());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteIpdTemplate(String id) {

		int result = 0;
		try {
			String sql = "delete from ipd_template where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public String getSharedChargeUser(String chargeType) {

		String result = "";
		try {

			String sql = "select userid from apm_shared_charges where chargetype='" + chargeType + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int saveShareCharge(String str, String chargeType) {

		int result = 0;
		try {

			String sql = "insert into apm_shared_charges (chargetype, userid) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, chargeType);
			ps.setString(2, str);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getShareIdIfExists(String chargeType) {

		int result = 0;

		try {
			String sql = "select id from apm_shared_charges where chargetype='" + chargeType + "'";
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

	public int updateShareCharge(String userids, String chargeType, int id) {

		int result = 0;
		try {

			String sql = "update apm_shared_charges set userid='" + userids + "' where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getMedicineLocationList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id,name from apm_medicine_location";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

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

	public ArrayList<Master> getIpdFormFiledList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id,name from apm_ipd_form_fields";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public ArrayList<String> getAllSelectedFormFiledList() {

		ArrayList<String> list = new ArrayList<String>();
		try {

			String sql = "SELECT field from apm_ipd_form_setting";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (rs.getString(1) != null) {
					list.add(rs.getString(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getIpdFormFieldName(String id) {

		String name = "";
		try {

			String sql = "SELECT name from apm_ipd_form_fields where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				name = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}

	public boolean isIpdFormFieldActive(String sepcializationid, String field) {

		try {
			String sql = "select id from apm_ipd_form_setting where dept_id=" + sepcializationid + " and field='"
					+ field + "' ";
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

	public ArrayList<Master> getSpecializationList() {

		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "select id,discipline from apm_discipline";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public ArrayList<Master> getAllIpdFormSettingList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "SELECT dept_id FROM apm_ipd_form_setting group by dept_id asc ; ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				String dept_id = rs.getString(1);
				String fields = "";

				String sql1 = "select id,field from apm_ipd_form_setting where dept_id=" + dept_id + " ";
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ResultSet rs1 = ps1.executeQuery();

				if (rs1.next()) {
					fields = rs1.getString(2);
				}
				while (rs1.next()) {

					fields = fields + "," + rs1.getString(2);
				}

				master.setFields(fields);
				master.setDept_id(dept_id);

				Master discipline = getDisciplineData(dept_id);
				master.setSpecialization(discipline.getDiscipline());
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int saveIpdFormData(String speci, String f) {

		int result = 0;
		try {

			String sql = "insert into apm_ipd_form_setting (dept_id, field) values (?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, speci);
			ps.setString(2, f);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<String> getIpdFormSettingFields(String dept_id) {

		ArrayList<String> list = new ArrayList<String>();
		try {

			String sql = "select field from apm_ipd_form_setting where dept_id=" + dept_id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				list.add(rs.getString(1));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int deletePreviousDeptFields(String specialization) {

		int result = 0;

		try {

			String sql = "delete from apm_ipd_form_setting where dept_id=" + specialization + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getAllLocation(String searchText, Pagination pagination) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				// sql = "select id,name from apm_pharmacy_location where name
				// like ('"+searchText+"%')";
				sql = "select id,name,speciality from apm_condition where name like ('" + searchText + "%')";
			} else {
				// sql = "select id,name from apm_pharmacy_location";
				sql = "select id,name,speciality from apm_condition";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name=departmentid(rs.getString(3));
				Master locationMaster = new Master();
				locationMaster.setId(rs.getInt(1));
				locationMaster.setName(rs.getString(2));
				locationMaster.setDiscipline(name);
				users.add(locationMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public String departmentid(String string) {
		// TODO Auto-generated method stub
		  PreparedStatement pmt=null;
		  String name="";
		         
		   try {
			   
				String sql="select discipline from apm_discipline where id='"+string+"'";
				pmt=connection.prepareStatement(sql);
				ResultSet rs=pmt.executeQuery();
					
			    while(rs.next()){
				 name=rs.getString(1);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
				
				  return name ;
				  
      }

	public ArrayList<Master> getAllLocation(String searchText) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id,name from apm_condition where name like ('" + searchText + "%') or speciality='"+searchText+"' ";
			} else {
				sql = "select id,name from apm_condition";
			}

			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master locationMaster = new Master();
				locationMaster.setId(rs.getInt(1));
				locationMaster.setName(rs.getString(2));
				users.add(locationMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int addLocation(Master locationMaster) {
		int result = 0;
		try {
			String query = "insert into apm_condition(name,pharmacy,speciality) values(?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, locationMaster.getName());
			stmt.setString(2, locationMaster.getPharmacycheck());
			stmt.setString(3, locationMaster.getDiscipline());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getlocationinfo(Master master) {
		Master master2 = new Master();
		try {
			String query = "select id,name,pharmacy,speciality from apm_condition where id=" + master.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				master2.setId(rs.getInt(1));
				master2.setName(rs.getString(2));
				master2.setPharmacycheck(rs.getString(3));
				master2.setDiscipline(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateLocation(Master master) {
		int result = 0;
		try {
			String query = "update apm_condition set name=?,pharmacy=? where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getName());
			stmt.setString(2, master.getPharmacycheck());
			//stmt.setString(3, master.getDiscipline());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deletelocation(Master master) {
		int result = 0;
		try {
			int j = master.getId();
			String query = "delete from apm_condition where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getAllproductypelist(String searchText) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id,name from apm_productType where name like ('" + searchText + "%')";
			} else {
				sql = "select id,name from apm_productType";
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				users.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int addproductypelist(Master master) {
		int result = 0;
		try {
			String query = "insert into apm_productType(name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getName());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getproducttypemasterinfo(Master master) {
		Master master2 = new Master();
		try {
			String query = "select id,name from apm_productType where id=" + master.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				master2.setId(rs.getInt(1));
				master2.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateproducttypemaster(Master master) {
		int result = 0;
		try {
			String query = "update apm_productType set name=? where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getName());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteproducttypemaster(Master master) {
		int result = 0;
		try {
			int j = master.getId();
			String query = "delete from apm_productType where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalTermsCondition() {
		int result = 0;
		try {
			String sql = "select count(*) from terms_and_condition";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return result;
	}

	public int addTermsCondition(Master master) {
		int result = 0;
		try {
			String sql = "insert into terms_and_condition(term,workorderterms) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, master.getName());
			stmt.setString(2, master.getWorkorderterms());
			result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteTermsCondition(Master master) {
		int result = 0;
		try {
			String sql = "delete from terms_and_condition where id=" + master.getId() + "";
			PreparedStatement stmt = connection.prepareStatement(sql);
			result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getTermsConditionListById(Master termCond) {
		Master list = new Master();
		try {
			String sql = "select id,term,workorderterms from terms_and_condition where id=" + termCond.getId() + "";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				list.setId(rs.getInt(1));
				list.setName(rs.getString(2));
				list.setWorkorderterms(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Master> getTermsConditionList(Pagination pagination, String SearchText) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "";
			if (SearchText != null) {
				sql = "SELECT id,term,workorderterms FROM terms_and_condition where term like ('" + SearchText + "%')";
			} else {
				sql = "SELECT id,term,workorderterms FROM terms_and_condition";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master termCond = new Master();
				termCond.setId(rs.getInt(1));
				termCond.setName(rs.getString(2));
				termCond.setWorkorderterms(rs.getString(3));
				list.add(termCond);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int updateTermsConditionlist(Master master) {
		int result = 0;
		try {
			String sql = "update terms_and_condition set term=?,workorderterms=? where id=" + master.getId() + "";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, master.getName());
			stmt.setString(2, master.getWorkorderterms());
			result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Master> getIpdTemplateList(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Master> getStandardChargesList(Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Master> getNotAvaiableReasonList() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Master> getAllproductypelist(String searchText, Pagination pagination) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCombineFormDetails(String assementtemplateId, String combineformclientid,
			String combineformdoctorid, String combineformconditionid, int fieldid, String columname) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select " + columname + " from apm_combine_assessment_client_details where templateId="
				+ assementtemplateId + " and clientId = " + combineformclientid + " " + " and practitionerid = "
				+ combineformdoctorid + " and conditionid = " + combineformconditionid + " and leftnameid = " + fieldid
				+ " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

			if (result == null) {
				result = "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getNursingDetailsByNursingTypeid(String nursingtypeid) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "select id, taskname, frequency, notes from apm_nursing_details where nursingtype_id='"
					+ nursingtypeid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTaskname(rs.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Master> getJobTitleList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		sql = "SELECT id,jobtitle,jobgroup_id FROM job_title order by id desc";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setJobTitle(rs.getString(2));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getInvestigationNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_name ";

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

	public ArrayList<Master> getAllVitalList(Pagination pagination) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "select id,name,vital_type from his_vital_master ";

			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setVital_type(rs.getString(3));
				String vital_type = getVitalTypeName(master.getVital_type());
				master.setVital_type_name(vital_type);
				list.add(master);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private String getVitalTypeName(String type) {

		String res = "";
		try {
			String sql = "SELECT name from his_vital_type where id=" + type + " ";
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

	public int getCountVitalMasterList() {

		int res = 0;
		try {
			String sql = "select count(*) from his_vital_master ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Master> getAllVitalTypeList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id,name from his_vital_type";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public int saveVitalMaster(Master master) {

		int res = 0;
		try {

			String sql = "insert into his_vital_master (name,vital_type,min_value_m, max_value_m) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getVital_type());
			ps.setString(3, master.getMin_value_m());
			ps.setString(4, master.getMax_value_m());
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

	public int updateVitalImageName(int id, String filename) {

		int res = 0;
		try {
			String sql = "update his_vital_master set imagename='" + filename + "' where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public Master getVitalMasterData(String id) {

		Master master = new Master();
		try {

			String sql = "SELECT id,name,vital_type,imagename,unit,min_value_m, max_value_m from his_vital_master where id="
					+ id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setVital_type(rs.getString(3));
				master.setUserFileFileName(rs.getString(4));
				String unit = DateTimeUtils.isNull(rs.getString(5));
				if (unit.equals("0")) {
					unit = "";
				}
				master.setMin_value_m(DateTimeUtils.isNull(rs.getString(6)));
				master.setMax_value_m(DateTimeUtils.isNull(rs.getString(7)));
				master.setUnit(unit);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return master;
	}

	public int updateVitalMaster(Master master) {

		int res = 0;
		try {
			if (master.getVital_type() == null) {
				master.setVital_type("1");
			}
			String sql = "update his_vital_master set name=?,vital_type=?,unit=?,min_value_m=?, max_value_m=? where id="
					+ master.getId() + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getVital_type());
			ps.setString(3, master.getUnit());
			ps.setString(4, master.getMin_value_m());
			ps.setString(5, master.getMax_value_m());
			res = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int deleteVitalMaster(String id) {

		int res = 0;
		try {
			String sql = "delete from his_vital_master where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int getTotalNABHCatagoryCount(String searchText) {
		int result = 0;
		try {
			// String sql="select count(*) from nabh_Catagory";
			StringBuilder builder = new StringBuilder();
			builder.append("select count(*) from nabh_Catagory ");
			if (searchText != null) {
				builder.append("where name like ('" + searchText + "%') ");
			}
			PreparedStatement stmt = connection.prepareStatement(builder.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getNABHCatagoryList(String searchText, Pagination pagination) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id,name from nabh_Catagory where name like ('" + searchText + "%')";
			} else {
				sql = "select id,name from nabh_Catagory";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				arrayList.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int addNABHCatagory(String name) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "insert into nabh_Catagory(name) values(?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteNABHCatagory(String id) {
		int res = 0;
		try {
			String sql = "delete from nabh_Catagory where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public Master getNABHCatagory(String id) {
		Master master = new Master();
		try {
			String sql = "select id,name from nabh_Catagory where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateNABHCatagory(String id, String name) {
		int res = 0;
		try {
			String sql = "update nabh_Catagory set name=? where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getNABHSubCatagoryList(String searchText, Pagination pagination) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id, catagoryid, name from nabh_subcatagory where name like ('" + searchText + "%')";
			} else {
				sql = "select id, catagoryid, name from nabh_subcatagory";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(3));
				master.setCatagoryid(rs.getString(2));
				Master master2 = getNABHCatagory(rs.getString(2));
				master.setCategoryname(master2.getName());
				arrayList.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Master> getNabhSubCatList(String id) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "";
			sql = "select id, catagoryid, name from nabh_subcatagory where catagoryid='" + id + "'";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(3));
				master.setCatagoryid(rs.getString(2));
				arrayList.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int getTotalNABHSubCatagoryCount(String searchText) {
		int result = 0;
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select count(*) from nabh_subcatagory ");
			if (searchText != null) {
				builder.append("where name like ('" + searchText + "%') ");
			}
			PreparedStatement stmt = connection.prepareStatement(builder.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addNABHSubCatagory(String name, String catagoryid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "insert into nabh_subcatagory(catagoryid, name) values(?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, catagoryid);
			preparedStatement.setString(2, name);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getNABHSubCatagory(String id) {
		Master master = new Master();
		try {
			String sql = "select id, catagoryid, name from nabh_subcatagory where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(3));
				master.setCatagoryid(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateNABHSubCatagory(int id, String name, String catagoryid) {
		int res = 0;
		try {
			String sql = "update nabh_subcatagory set name=?,catagoryid=? where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, catagoryid);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteNABHSubCatagory(String id) {
		int res = 0;
		try {
			String sql = "delete from nabh_subcatagory where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int getTotalNABHAreaCount(String searchText) {
		int result = 0;
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select count(*) from kpi_area ");
			if (searchText != null) {
				builder.append("where area like ('" + searchText + "%') ");
			}
			PreparedStatement stmt = connection.prepareStatement(builder.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getNABHAreaList(String searchText, Pagination pagination) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id, area, description, subcatagoryid from kpi_area where area like ('" + searchText
						+ "%')";
			} else {
				sql = "select id, area, description, subcatagoryid from kpi_area";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setSubcategory(rs.getString(4));
				Master master2 = getNABHSubCatagory(rs.getString(4));
				master.setSubcategoryname(master2.getName());
				arrayList.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int addNABHArea(String name, String subcatagoryid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "insert into kpi_area(subcatagoryid, area) values(?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subcatagoryid);
			preparedStatement.setString(2, name);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getNABHArea(String id) {
		Master master = new Master();
		try {
			String sql = "select id, area, description, subcatagoryid from kpi_area where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setSubcategory(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateNABHArea(int id, String name, String subcatagoryid) {
		int res = 0;
		try {
			String sql = "update kpi_area set area=?,subcatagoryid=? where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, subcatagoryid);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteNABHArea(String id) {
		int res = 0;
		try {
			String sql = "delete from kpi_area where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int getPriseTemplateCount(String searchText) {
		int res = 0;
		try {
			String sql = "select count(*) from apm_client_parent_priscription_template ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Master> getPriscTemplateList(String searchText, Pagination pagination) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select id,templatename from apm_client_parent_priscription_template where templatename like ('"
						+ searchText + "%')";
			} else {
				sql = "select id,templatename from apm_client_parent_priscription_template";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				arrayList.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Master> getMasterSpecializationList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "select id,name from  apm_condition where department>0";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public String getApmtidFromClientid(String clientid) {

		String res = "";
		try {
			String sql = "select id from apm_available_slot where clientId=" + clientid + " order by id desc limit 0,1";
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

	public int getTotalSchedulerTaskCount() {
		int res = 0;
		try {

			String sql = "select count(*) from schedular_task";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;

	}

	public ArrayList<Master> getAllSchedulerTask(String searchText, Pagination pagination) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id,taskname,jobtitle from schedular_task where taskname like ('" + searchText + "%')";
			} else {
				sql = "select id,taskname,jobtitle from schedular_task";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setTaskname(rs.getString(2));
				master.setJobTitle(rs.getString(3));
				list.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addSchedulerTask(Master master) {
		int result = 0;
		try {
			String query = "insert into schedular_task( taskname, jobtitle) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getTaskname());
			stmt.setString(2, master.getJobTitle());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteSchedulerTask(Master master) {
		int result = 0;
		try {

			int j = master.getId();
			String query = "delete from schedular_task where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getSchedulerTaskinfo(Master master) {
		Master master2 = null;
		try {
			String query = "select id,taskname,jobtitle from schedular_task where id=" + master.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				master2 = new Master();
				master2.setId(rs.getInt(1));
				master2.setTaskname(rs.getString(2));
				master2.setJobTitle(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateSchedulerTask(Master master) {
		int result = 0;
		try {
			String query = "update schedular_task set taskname=?,jobtitle=? where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getTaskname());
			stmt.setString(2, master.getJobTitle());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalSchedulerSubtaskCount() {
		int res = 0;
		try {

			String sql = "select count(*) from schedular_subtask";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;

	}

	public ArrayList<Master> getAllSchedulerSubtask(String searchText, Pagination pagination) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id,parentid,subtask from schedular_subtask where subtask like ('" + searchText + "%')";
			} else {
				sql = "select id,parentid,subtask from schedular_subtask";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			/*
			 * CityMaster city = new CityMaster(); city.setId(rs.getInt(1));
			 * city.setStateid(rs.getString(2)); city.setCity(rs.getString(3));
			 * 
			 * StateDAO stateDAO = new JDBCStateDAO(connection); State state1 =
			 * new State(); state1.setId(Integer.parseInt(rs.getString(2)));
			 * State state = stateDAO.getstateinfo(state1);
			 * city.setStatename(state.getName());
			 * 
			 * users.add(city);
			 */

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setParentid(rs.getString(2));
				master.setSubtask(rs.getString(3));

				String taskName = getTaskNameParent(master.getParentid());
				master.setTaskname(taskName);

				list.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getTaskNameParent(String parentid) {
		String res = "";
		try {

			String sql = "select taskname from schedular_task where id =" + parentid + " ";
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

	public int addSchedulerSubtask(Master master) {
		int result = 0;
		try {
			String query = "insert into schedular_subtask(parentid,subtask) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getTaskname());
			stmt.setString(2, master.getSubtask());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getSchedulerSubtaskinfo(Master master) {
		Master master2 = null;
		try {
			// String query= "select id, subtask from schedular_subtask where
			// id="+master.getId();
			String query = "select id,parentid, subtask from schedular_subtask where id=" + master.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				master2 = new Master();
				master2.setId(rs.getInt(1));
				master2.setParentid(rs.getString(2));
				master2.setSubtask(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateSchedulerSubtask(Master master) {
		int result = 0;
		try {
			String query = "update schedular_subtask set parentid=?, subtask=? where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getParentid());
			stmt.setString(2, master.getSubtask());

			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteSchedulerSubtask(Master master) {
		int result = 0;
		try {

			int j = master.getId();
			String query = "delete from schedular_subtask where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getSubTasklist(String id) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String query = "select id,subtask from schedular_subtask where parentid=" + id + "";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setSubtask(rs.getString(2));
				list.add(master);
			}

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public String getNotesList(Master master) {
		String notes = "";
		try {
			String query = "select subtask from schedular_subtask where id =" + master.getId() + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				notes = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return notes;

	}

	public ArrayList<Master> getIpdTemplateBySpeciality(String surgical_template, String name) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, template_nameid, text, name, department from ipd_template where template_nameid='"
					+ surgical_template + "' and speciality='" + name + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTemplate_nameid(rs.getString(2));
				master.setTemplate_text(getIpdTemplatenamefromId(rs.getString(2)));
				master.setText(rs.getString(3));
				master.setName(rs.getString(4));
				master.setDepartment(rs.getString(5));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Master> getPriscUnitList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "select id,name from apm_prisc_unit";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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
	// Adarsh

	public int getTotalNursingDiagnosisCount() {
		int res = 0;
		try {
			String query = "select count(*) from nursing_care_diagnosis";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * public ArrayList<Master> getAllNursingDiagnosis(String searchText,
	 * Pagination pagination) { ArrayList<Master> list = new
	 * ArrayList<Master>(); try { Statement stmt = connection.createStatement();
	 * String sql =""; if (searchText!=null) { sql =
	 * "select id, name from nursing_care_diagnosis where name like ('"
	 * +searchText+"%')"; } else { sql =
	 * "select id, name from nursing_care_diagnosis"; } if(pagination!=null) {
	 * sql=pagination.getSQLQuery(sql); }
	 * 
	 * ResultSet rs = stmt.executeQuery(sql); while (rs.next()) { Master master
	 * = new Master();
	 * 
	 * master.setId(rs.getInt(1)); master.setNursing_diagnosis(rs.getString(2));
	 * 
	 * list.add(master); } stmt.close(); } catch (Exception e) {
	 * e.printStackTrace(); } return list; }
	 */
	/*
	 * public int addNursingDiagnosis(Master master) { int result = 0; try {
	 * String query = "insert into nursing_care_diagnosis(name) values(?)";
	 * PreparedStatement stmt = connection.prepareStatement(query);
	 * stmt.setString(1, master.getNursing_diagnosis());
	 * 
	 * result = stmt.executeUpdate(); } catch (Exception e) {
	 * e.printStackTrace(); } return result; }
	 */
	/*
	 * public Master getNursingDiagnosis(Master master) { Master master2 =null;
	 * try { //String query=
	 * "select id, subtask from schedular_subtask where id="+master.getId();
	 * String query=
	 * "select id,name from nursing_care_diagnosis where id="+master.getId();
	 * PreparedStatement preparedStatement = connection.prepareStatement(query);
	 * ResultSet rs = preparedStatement.executeQuery(); if(rs.next()){ master2 =
	 * new Master(); master2.setId(rs.getInt(1));
	 * master2.setNursing_diagnosis(rs.getString(2)); } } catch (Exception e) {
	 * e.printStackTrace(); } return master2; } public int
	 * updateNursingDiagnosis(Master master) { int result = 0; try { String
	 * query =
	 * "update nursing_care_diagnosis set name? where id="+master.getId();
	 * PreparedStatement stmt = connection.prepareStatement(query);
	 * stmt.setString(1, master.getNursing_diagnosis());
	 * 
	 * 
	 * result = stmt.executeUpdate(); } catch (Exception e) {
	 * e.printStackTrace(); } return result; } public int
	 * deleteNursingDiagnosis(Master master) { int result = 0; try {
	 * 
	 * int j =master.getId(); String query =
	 * "delete from nursing_care_diagnosis where id="+master.getId();
	 * PreparedStatement stmt = connection.prepareStatement(query); result =
	 * stmt.executeUpdate(); } catch (Exception e) { e.printStackTrace(); }
	 * return result; } public int getTotalNursingPlanningCount() { int res=0;
	 * try { String query="select count(*) from nursing_care_planning";
	 * PreparedStatement ps=connection.prepareStatement(query); ResultSet rs=
	 * ps.executeQuery(); while(rs.next()) { res=rs.getInt(1);
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); } return res; }
	 */
	public ArrayList<Master> getAllNursingDiagnosis(String searchText, Pagination pagination) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id, name from nursing_care_diagnosis where name like ('" + searchText + "%')";
			} else {
				sql = "select id, name from nursing_care_diagnosis";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setNursing_diagnosis(rs.getString(2));

				list.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addNursingDiagnosis(Master master) {
		int result = 0;
		try {
			String query = "insert into nursing_care_diagnosis(name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getNursing_diagnosis());

			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getNursingDiagnosis(Master master) {
		Master master2 = null;
		try {
			// String query= "select id, subtask from schedular_subtask where
			// id="+master.getId();
			String query = "select id,name from nursing_care_diagnosis where id=" + master.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				master2 = new Master();
				master2.setId(rs.getInt(1));
				master2.setNursing_diagnosis(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateNursingDiagnosis(Master master) {
		int result = 0;
		try {
			String query = "update nursing_care_diagnosis set name? where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getNursing_diagnosis());

			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteNursingDiagnosis(Master master) {
		int result = 0;
		try {

			int j = master.getId();
			String query = "delete from nursing_care_diagnosis where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalNursingPlanningCount() {
		int res = 0;
		try {
			String query = "select count(*) from nursing_care_planning";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getAllNursingPlanning(String searchText, Pagination pagination) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id, diagnosisname, planningname from nursing_care_planning where planningname like ('"
						+ searchText + "%')";
			} else {
				sql = "select id, diagnosisname, planningname from nursing_care_planning";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setDiagnosisname(rs.getString(2));
				master.setNursing_planning(rs.getString(3));

				String nursing_diagnosis = getDiagnosisName(master.getDiagnosisname());
				master.setNursing_diagnosis(nursing_diagnosis);

				list.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getDiagnosisName(String diagnosisname) {
		String res = "";
		try {

			String sql = "select name from nursing_care_diagnosis where id =" + diagnosisname + " ";
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

	public int addNursingPlanning(Master master) {
		int result = 0;
		try {

			String query = "insert into nursing_care_planning(diagnosisname, planningname) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getNursing_diagnosis());
			stmt.setString(2, master.getNursing_planning());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteNursingPlanning(Master master) {
		int result = 0;
		try {

			int j = master.getId();
			String query = "delete from nursing_care_planning where id=" + master.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int getTotalNursingInterventionCount() {
		int res = 0;
		try {
			String query = "select count(*) from nursing_care_intervention";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getAllNursingIntervention(String searchText, Pagination pagination) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id, planningid, intervention_name from nursing_care_intervention where planningname like ('"
						+ searchText + "%')";
			} else {
				sql = "select id, planningid, intervention_name from nursing_care_intervention";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setPlanningid(rs.getString(2));
				master.setIntervention_name(rs.getString(3));

				/*
				 * String nursing_planning =
				 * getDiagnosisName(master.getDiagnosisname());
				 */
				String nursing_planning = getPlanningName(master.getPlanningid());
				master.setNursing_planning(nursing_planning);

				list.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getPlanningName(String planningid) {
		String res = "";
		try {

			String sql = "select planningname from nursing_care_planning where id =" + planningid + " ";
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

	public int addNursingIntervention(Master master) {
		int result = 0;
		try {

			String query = "insert into nursing_care_intervention(planningid, intervention_name) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getNursing_planning());
			stmt.setString(2, master.getNursing_intervention());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getBankNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM bankname_list ";

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

	// bookchaptermster

	public ArrayList<Master> getallBookChapter(String searchText, Pagination pagination) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id,chapter_name,chapter_link, discription from book_chapter_master where chapter_name  like ('"
						+ searchText + "%')";
			} else {
				sql = "select id,chapter_name ,chapter_link, discription  from book_chapter_master";
			}
			if (pagination == null) {
				sql = pagination.getSQLQuery(sql);
			}

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();
				master.setBook_chapter_id(rs.getInt(1));
				master.setBook_chapter_name(rs.getString(2));
				master.setBook_chapter_link(rs.getString(3));
				master.setBook_chapter_discription(rs.getString(4));
				users.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int getBookChapterCount() {
		int count = 0;
		try {

			String sql = "select count(*) from book_chapter_master";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int addbookChapterDB(Master master) {
		int result = 0;
		try {
			String query = "insert into book_chapter_master(chapter_name,parent_id,chapter_link, discription  ) values(?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getBook_chapter_name());
			stmt.setInt(2, master.getBook_chapter_parentid());
			stmt.setString(3, master.getBook_chapter_link());
			stmt.setString(4, master.getBook_chapter_discription());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteBookChapterDB(Master master) {
		int result = 0;
		try {

			int j = master.getId();
			String query = "delete from book_chapter_master where id=" + master.getBook_chapter_id();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getBookChapterinfo(Master master) {
		Master master2 = null;
		try {
			String query = "select id,chapter_name,chapter_link, discription    from book_chapter_master where id="
					+ master.getBook_chapter_id();
			PreparedStatement PreparedStatement = connection.prepareStatement(query);
			ResultSet rs = PreparedStatement.executeQuery();
			if (rs.next()) {
				master2 = new Master();
				master2.setBook_chapter_id(rs.getInt(1));
				master2.setBook_chapter_name(rs.getString(2));
				master2.setBook_chapter_link(rs.getString(3));
				master2.setBook_chapter_discription(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateBookChapterDB(Master master) {
		int result = 0;
		try {
			String query = "update book_chapter_master set chapter_name =?,parent_id =? ,chapter_link =?, discription =?  where id="
					+ master.getBook_chapter_id();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getBook_chapter_name());
			stmt.setInt(2, master.getBook_chapter_parentid());
			stmt.setString(3, master.getBook_chapter_link());
			stmt.setString(4, master.getBook_chapter_discription());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getallOutsource(String searchText, Pagination pagination) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id,name from apm_outsource where name like ('" + searchText + "%')";
			} else {
				sql = "select id,name from apm_outsource";
			}
			/*
			 * if(pagination!=null) { sql=pagination.getSQLQuery(sql); }
			 */
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();
				master.setOutsource_id((rs.getInt(1)));
				master.setOutsource_name(rs.getString(2));
				users.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int getOutsourceCount() {
		int count = 0;
		try {

			String sql = "select count(*) from apm_outsource";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int addOutsourceDB(Master master) {
		int result = 0;
		try {
			String query = "insert into apm_outsource(name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getOutsource_name());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteOutsourceDB(Master master) {
		int result = 0;
		try {

			int j = master.getOutsource_data_id();
			String query = "delete from apm_outsource where id=" + master.getOutsource_id();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getOutsourceinfo(Master master) {
		Master master2 = null;
		try {
			String query = "select id,name from apm_outsource where id=" + master.getOutsource_id();
			PreparedStatement PreparedStatement = connection.prepareStatement(query);
			ResultSet rs = PreparedStatement.executeQuery();
			if (rs.next()) {
				master2 = new Master();
				master2.setOutsource_id(rs.getInt(1));
				master2.setOutsource_name(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateOutsourceDB(Master master) {
		int result = 0;
		try {
			String query = "update apm_outsource set name=? where id=" + master.getOutsource_id();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getOutsource_name());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// outsourcedb

	public ArrayList<Master> getallOutsourceData(String searchText, Pagination pagination) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";

			sql = "SELECT a.id,ammount,sharing_type, b.name ,c.name  FROM apm_outsource_data a inner join apm_investigation_type b on b.id=a.investigation_type_id inner join apm_outsource c on c.id= a.outsource_id";

			/*
			 * if(pagination!=null) { sql=pagination.getSQLQuery(sql); }
			 */
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master master = new Master();
				master.setOutsource_data_id(rs.getInt(1));
				master.setInvestigation_name(rs.getString(4));
				master.setOutsource_name(rs.getString(5));
				master.setAmmount(rs.getString(2));
				master.setSharing_type(rs.getString(3));
				users.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int getOutsourceDataCount() {
		int count = 0;
		try {

			String sql = "select count(*) from apm_outsource_data";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int addOutsourceDataDB(Master master) {
		int result = 0;
		try {
			String query = "insert into apm_outsource_data(outsource_id,investigation_type_id,ammount,sharing_type) values(?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, String.valueOf(master.getOutsource_id()));
			stmt.setString(2, master.getInvestigstion_id());
			stmt.setString(3, master.getAmmount());
			stmt.setString(4, master.getSharing_type());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteOutsourceDataDB(Master master) {
		int result = 0;
		try {

			int j = master.getOutsource_data_id();
			String query = "delete from apm_outsource_data where id=" + master.getOutsource_data_id();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getOutsourceDatainfo(Master master) {
		Master master2 = null;
		try {
			String query = "select outsource_id,investigation_type_id,ammount,sharing_type , id from apm_outsource_data where id="
					+ master.getOutsource_data_id();
			PreparedStatement PreparedStatement = connection.prepareStatement(query);
			ResultSet rs = PreparedStatement.executeQuery();
			if (rs.next()) {
				master2 = new Master();
				master2.setOutsource_id(rs.getInt(1));
				master2.setInvestigation_type_id(rs.getString(2));
				master2.setAmmount(rs.getString(3));
				master2.setSharing_type(rs.getString(4));
				master2.setOutsource_data_id(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master2;
	}

	public int updateOutsourceDataDB(Master master) {
		int result = 0;
		try {
			String query = "update apm_outsource_data set outsource_id =?,investigation_type_id=?,ammount=?,sharing_type=?  where id="
					+ master.getOutsource_data_id();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, String.valueOf(master.getOutsource_id()));
			stmt.setString(2, master.getInvestigation_type_id());
			stmt.setString(3, master.getAmmount());
			stmt.setString(4, master.getSharing_type());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getallInvestigationtype() {

		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;

		try {

			String query = "SELECT id, name FROM apm_investigation_type ";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master ms = new Master();
				ms.setName(rs.getString(2));
				ms.setId(rs.getInt(1));
				list.add(ms);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getAllCityList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id,city from apm_city";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setCity(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int deleteMedicineTemplateDB(String id) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = "";
		try {
			sql = "delete from apm_client_parent_priscription_template where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int saveFeedBack(String name, String isopd, String isipd) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "insert into feedback_child_master(name, isopd, isipd) values(?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, isopd);
			ps.setString(3, isipd);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteFeedBack(String id) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "delete from feedback_child_master where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateFeedBack(String name, String isopd, String isipd, String id) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update feedback_child_master set name='" + name + "', isopd='" + isopd + "', isipd='" + isipd
					+ "' where id='" + id + "' ";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getFeedBackInfo(String id) {
		Master master = new Master();
		PreparedStatement ps = null;
		try {
			String sql = "select id, name, isopd, isipd from feedback_child_master where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setIsopd(rs.getString(3));
				master.setIsipd(rs.getString(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int deleteNursingcareIntervationMaster(String id) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "delete from nursing_care_intervention where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Master getNusringCareMasterInfo(String id) {
		Master master = new Master();
		PreparedStatement ps = null;
		try {
			String sql = "select id, planningid, intervention_name from nursing_care_intervention where id='" + id
					+ "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setPlanningid(rs.getString(2));
				master.setIntervention_name(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateNursingcareIntervationMaster(String id, String nursing_planning, String nursingIntervention) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			String sql = "update nursing_care_intervention set planningid ='" + nursing_planning
					+ "' , intervention_name='" + nursingIntervention + "' where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getInvestigationNameList1() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_name group by name ";

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

	public ArrayList<Master> getAllSharedChargeList() {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT id,name FROM apm_appointment_type where shareablecharge='1'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int checkIssharedchargewithuser(String sharechargeid, String shareuserid) {
		int res = 0;
		try {
			String sql = "select id from usersharecharge where userid='" + shareuserid + "' and '" + sharechargeid
					+ "'";
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

	// lokesh
	public int savevaccinationmaster(String vacinname, String vacine_dependson, String vacine_iscompulsary,
			String vacine_excludes, String vacine_parent, String vacine_info, String scheduled, String duration,
			int type, String scheduledependency, String genderspecified) {
		int result = 0;
		try {
			String sql = "insert into apm_vacination_master(name,dependancy,is_compulsary,excludes, parent,info,scheduled_on,duration,type,totaldays,genderspecified) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = null;

			if (vacine_dependson != null) {
				if (vacine_dependson.equals("")) {

					vacine_dependson = null;
				}
			}

			ps = connection.prepareStatement(sql);
			ps.setString(1, vacinname);
			ps.setString(2, vacine_dependson);
			ps.setInt(3, Integer.parseInt(vacine_iscompulsary));
			ps.setString(4, vacine_excludes);
			ps.setString(5, vacine_parent);
			ps.setString(6, vacine_info);
			ps.setString(7, scheduled);
			ps.setString(8, duration);
			ps.setInt(9, type);
			ps.setString(10, scheduledependency);
			ps.setString(11, genderspecified);
			result = ps.executeUpdate();
			if (result != 0) {
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

	public int updatevaccinationmaster(String vacinname, String vacine_dependson, String vacine_iscompulsary,
			String vacine_excludes, String vacine_parent, String vacine_info, String id, String scheduled,
			String duration, int type, String scheduledependency, String genderspecified) {
		int result = 0;
		try {
			String sql = "update apm_vacination_master set name=?,dependancy=?,is_compulsary=?,excludes=?, parent=?,info=?,scheduled_on=?,duration=?,type=?,totaldays=?,genderspecified=? where id="
					+ id + "";
			PreparedStatement ps = null;
			if (vacine_dependson != null) {
				if (vacine_dependson.equals("")) {

					vacine_dependson = null;
				}
			}
			ps = connection.prepareStatement(sql);
			ps.setString(1, vacinname);
			ps.setString(2, vacine_dependson);
			ps.setInt(3, Integer.parseInt(vacine_iscompulsary));
			ps.setString(4, vacine_excludes);
			ps.setString(5, vacine_parent);
			ps.setString(6, vacine_info);
			ps.setString(7, scheduled);
			ps.setString(8, duration);
			ps.setInt(9, type);
			ps.setString(10, scheduledependency);
			ps.setString(11, genderspecified);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deletevaccinationmaster(String id) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "delete from apm_vacination_master where id= " + id + " ";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getallvaccinations() {
		PreparedStatement ps = null;
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "select id, name from apm_vacination_master ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setVacinname(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Master> getallveterinarymedicine() {
		PreparedStatement ps = null;
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "select id, name from veterinary_medicine_master ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setVacinname(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getallparentvaccinations() {
		PreparedStatement ps = null;
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "select id, name from apm_vacination_parent_master ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setParentid(String.valueOf(rs.getInt(1)));
				master.setVacine_parent(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Master getvaccinationMasterInfo(String id) {
		Master master = new Master();
		PreparedStatement ps = null;
		try {
			String sql = " select id,name,dependancy,scheduled_on,is_compulsary,excludes, parent,info,duration,type,totaldays,genderspecified from apm_vacination_master where id="
					+ id + "";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setVacinname(rs.getString(2));
				master.setVacine_dependson(rs.getString(3));
				master.setVacine_scheduledon(rs.getString(4));
				master.setVacine_iscompulsary(rs.getString(5));
				master.setVacine_excludes(rs.getString(6));
				master.setParentid(rs.getString(7));
				if (master.getParentid() == null) {
					master.setParentid("");
				}
				master.setVacine_info(rs.getString(8));
				master.setDuration(rs.getString(9));
				master.setType(rs.getInt(10));
				master.setDependsonscheedule("" + rs.getInt(11));
				master.setGendervaccine(rs.getInt(12));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updatetotalvacindays(Master master, Master master2) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update apm_vacination_master set totaldays=''";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int saveclinicalnotesmstr(String name) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "insert into clinical_notes_master(name) values (?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveclinicalproblemmster(String name, String parentid) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "insert into clinical_notes_problem (problemname,clinicalnotesid) values(?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, parentid);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveclinicalintervation(String name, String parentid) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "insert into clinical_notes_intervention (interventioname,problemid) values(?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, parentid);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getclinicalnoteslist() {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			String sql = "select id, name from clinical_notes_master ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public ArrayList<Master> getclinicalproblemlist() {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			String sql = "select id, problemname, clinicalnotesid from clinical_notes_problem ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setParentid(rs.getString(3));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Master> getclinicalintervationlist() {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			String sql = "select id, interventioname,problemid from clinical_notes_intervention ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setParentid(rs.getString(3));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int deleteclinicalmster(String id) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "delete from clinical_notes_master where id='" + id + "'";
			ps = connection.prepareStatement(sql);

			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteclinicalproblem(String id) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "delete from clinical_notes_problem where id='" + id + "'";
			ps = connection.prepareStatement(sql);

			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteclinicalintervation(String id) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "delete from clinical_notes_intervention where id='" + id + "'";
			ps = connection.prepareStatement(sql);

			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Master getclinicalnotesInfo(String id) {
		PreparedStatement ps = null;
		Master master = new Master();
		try {
			String sql = "select id, name from clinical_notes_master where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public Master getclinicalproblemInfo(String id) {
		PreparedStatement ps = null;
		Master master = new Master();
		try {
			String sql = "select id, problemname, clinicalnotesid from clinical_notes_problem where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setParentid(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public Master getclinicalintervationInfo(String id) {
		PreparedStatement ps = null;
		Master master = new Master();
		try {
			String sql = "select id, interventioname,problemid from clinical_notes_intervention where id='" + id + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setParentid(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateclinicalNote(Master master) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "update clinical_notes_master set name='" + master.getName() + "' where id='" + master.getId()
					+ "'";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateclinicalProblem(Master master) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "update clinical_notes_problem set problemname='" + master.getName() + "',clinicalnotesid='"
					+ master.getParentid() + "'  where id='" + master.getId() + "'";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateclinicalIntervation(Master master) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "update clinical_notes_intervention set interventioname='" + master.getName()
					+ "', problemid= '" + master.getParentid() + "'  where id='" + master.getId() + "'";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Master getMedicineDetails(int result) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "SELECT id,drug,genericname,catalogueid FROM apm_medicine_details where id='" + result + "'  ";
		// InventoryProductDAO inventoryProductDAO= new
		// JDBCInventoryProductDAO(connection);
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				// String catalogueid= rs.getString(4);
				// int stock
				// =inventoryProductDAO.getStockFromProduct(catalogueid);
				/*
				 * master.setGenericname(rs.getString(3)+" - "+master.getName()+
				 * "");
				 */
				if (rs.getString(3) != null) {
					if (!rs.getString(3).equals("")) {
						master.setGenericname(rs.getString(3) + " - " + master.getName() + "");
					} else {
						master.setGenericname("" + master.getName() + "");
					}
				} else {
					master.setGenericname("" + master.getName() + "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public ArrayList<Master> getAllLocationNew(String location) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			String sql = "";
			if (location != null) {
				sql = "select id,name from apm_condition where id='" + location + "'";
			} else {
				sql = "select id,name from apm_condition";
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Master locationMaster = new Master();
				locationMaster.setId(rs.getInt(1));
				locationMaster.setName(rs.getString(2));
				users.add(locationMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<Priscription> getseachedpricofMaster(String searchtxt) {
		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();

		try {
			String sql = "";

			sql = "select id,categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk from apm_medicine_details where drug like ('%"
					+ searchtxt + "%') order by id desc limit 20 ";

			// sql = "select
			// id,categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk
			// from apm_medicine_details order by id desc";

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));

				PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(connection);
				String selectedid = rs.getString(2);
				Priscription priscription1 = prescriptionMasterDAO.getPrescriptionCategory(selectedid);
				priscription.setCategoryid(priscription1.getName());

				// priscription.setCategoryid(rs.getString(2));
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

	public ArrayList<Master> getnimaidoselistt() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id,name from his_nimai_dose ";

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
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Master> getnimaiqtylist() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id,name from his_nimai_qty ";

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
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Master> getnimairemarlist() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id,eng from his_nimai_remark order by id desc ";

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

	public int saveremarkMaster(Master master) {
		PreparedStatement ps = null;
		int x = 0;
		try {
			String sql = "insert into his_nimai_remark(eng,hindi,marathi) values(?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, master.getRemark());
			ps.setString(2, master.getRemarkhindi());
			ps.setString(3, master.getRemarkmarathi());

			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public ArrayList<Master> getallRemarks() {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			String sql = "select id,eng from his_nimai_remark ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public int updateRemarkMaster(Master master) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Master getMasterInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean chkdoseExsist(String chdose) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from his_nimai_dose where name = " + chdose + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public double getnextGreaterDose(String chdose) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select name from his_nimai_dose where name > " + chdose + " limit 0,1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int setbreakage(String id, String checked) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_newchargetype set breakage =" + checked + " where id = " + id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int setAllbreakage(String selecteditem) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int breakage = 0;
		String sql = "";
		if (selecteditem.equals("")) {
			breakage = 0;
		} else if (selecteditem == null) {
			breakage = 0;
		} else if (selecteditem.equals("0")) {
			breakage = 0;
		} else {
			breakage = 1;
		}

		if (!selecteditem.equals("0")) {
			sql = "update apm_newchargetype set breakage =" + breakage + " where id = " + selecteditem + "";
		} else {
			sql = "update apm_newchargetype set breakage =" + breakage + "";
		}
		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public int setnoneditable(String id, String checked) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_appointment_type set noneditamt =" + checked + " where id = " + id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getMasterList(String tablename) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection.prepareStatement("select id,name,per,type from " + tablename + " ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setPercent("" + rs.getDouble(3));
				master.setType(rs.getInt(4));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Master getMasterInfo(String tablename, String id) {
		Master master = new Master();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select id,name,per from " + tablename + " where id='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setPercent("" + rs.getDouble(3));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int deleteMaster(String tablename, String id) {
		int result = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("delete from " + tablename + " where id='" + id + "'");
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateMaster(String tablename, Master master) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" update " + tablename + " set name=?,per=? where id='" + master.getId() + "' ");
			ps.setString(1, master.getName());
			ps.setString(2, master.getPercent());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addToTaxMaster(Master master) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" insert into his_tax_master(name,per,type) values(?,?,?) ");
			ps.setString(1, master.getName());
			ps.setString(2, master.getPercent());
			ps.setString(3, "" + master.getType());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getTaxnamebyType(String type) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select name,per from his_tax_master where type='" + type + "' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1) + " [" + rs.getInt(2) + " %]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getTaxTypeExist(String type) {
		boolean result = false;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select name,per from his_tax_master where type='" + type + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AppointmentType> getseachedCharge(String searchtxt) {
		ArrayList<AppointmentType> appointmentTypes = new ArrayList<AppointmentType>();
		AppointmentTypeDAO appointmentDAO = new JDBCAppointmentTypeDAO(connection);

		try {
			StringBuffer sql = new StringBuffer();

			sql.append(
					"SELECT apm_appointment_type.id,name,description,category,duration,charges,location,chargeType,reportField,tpid,code,basecharge,apm_ipd_ward.wardname,noneditamt FROM apm_appointment_type ");
			sql.append("left join apm_ipd_ward on apm_ipd_ward.id=apm_appointment_type.wardid where name like ('%"
					+ searchtxt + "%') order by id desc ");
			PreparedStatement ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AppointmentType appointmentType = new AppointmentType();
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				if (rs.getString(5) == null) {
					appointmentType.setDuration("");
				} else {
					appointmentType.setDuration(rs.getString(5));
				}
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));

				if (rs.getInt(10) != 0) {
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(rs.getString(10));
					appointmentType.setTpName(thirdParty.getCompanyName());
					if (thirdParty.getChargecolumnname() == null) {
						thirdParty.setChargecolumnname("charges");
					}
					if (thirdParty.getChargecolumnname().equals("")) {
						thirdParty.setChargecolumnname("charges");
					}
					String charges = appointmentDAO.getChargebyid(rs.getInt(1), thirdParty.getChargecolumnname());
					appointmentType.setCharges(charges);
				} else {
					appointmentType.setTpName("Self");
				}
				appointmentType.setTpid("" + rs.getInt(10));
				if (rs.getString(11) == null) {
					appointmentType.setCode("");
				} else {
					appointmentType.setCode(rs.getString(11));
				}
				appointmentType.setBasecharge(rs.getString(12));
				String wardname = rs.getString(13);
				if (rs.getString(13) == null) {
					wardname = "";
				}
				appointmentType.setWardname(wardname);
				if (rs.getInt(14) == 1) {
					appointmentType.setNoneditamt(1);
				} else {
					appointmentType.setNoneditamt(0);
				}
				appointmentTypes.add(appointmentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentTypes;
	}

	public int updatemfgMaster(String tablename, Master master) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" update " + tablename + " set mfg_name=? where id='" + master.getId() + "' ");
			ps.setString(1, master.getName());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void addToMfgMaster(Master master) {
		int result = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(" insert into mfg_details(mfg_name) values(?) ");
			ps.setString(1, master.getName());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getmfgExist(String name) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select mfg_name from mfg_details where mfg_name='" + name + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getMfgList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection.prepareStatement("select id,mfg_name from mfg_details ");
			ResultSet rs = ps.executeQuery();
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

	public Master getMasterInfoMfg(String id) {
		Master master = new Master();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select id,mfg_name from mfg_details where id='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int getTotalGenericNameCount(String searchText) {
		int result = 0;
		try {
			String sql = "";
			if (searchText != null) {
				sql = "select count(*) from generic_name where name like ('" + searchText + "%')";
			} else {
				sql = "select count(*) from generic_name";
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getAllGenericName(String searchText, Pagination pagination) {
		ArrayList<Master> users = new ArrayList<Master>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "";
			if (searchText != null) {
				sql = "select id,name from generic_name where name like ('" + searchText + "%')";
			} else {
				sql = "select id,name from generic_name";
			}
			if (pagination != null) {
				sql = pagination.getSQLQuery(sql);
			}

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Master state = new Master();
				state.setId(rs.getInt(1));
				state.setName(rs.getString(2));
				users.add(state);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public int addGenericName(String name) {
		int result = 0;
		try {
			String query = "insert into generic_name(name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, name);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkGenericName(String genericname) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select name from generic_name where name='" + genericname + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> listDosages() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection.prepareStatement("  select id,name, regional from apm_medicine_dosage");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master mst = new Master();
				mst.setName(rs.getString(2));
				mst.setId(rs.getInt(1));
				mst.setRegional(rs.getString(3));
				list.add(mst);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addDosages(Master master) {
		int res = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" insert into apm_medicine_dosage(name,regional) values(?,?)");
			ps.setString(1, master.getRemark());
			ps.setString(2, master.getRemarkhindi());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getHISDocumentList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection.prepareStatement(" select id, name from his_document_names ");
			ResultSet rs = ps.executeQuery();
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

	public ArrayList<Master> listsms() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("  select id,sms, sms_type,sms_itype from sms_template_master");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setSms(rs.getString(2));
				master.setSms_type(getsmstype(rs.getString(3), "sms_type", "sms_type"));
				master.setSms_itype(getsmstype(rs.getString(4), "sms_invoice_type", "type"));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getsmstype(String id, String table, String column) {
		String res = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select " + column + " from " + table + " where id=" + id + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getsmstypeList(String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id, sms_type FROM sms_type ");
		if (id != null) {
			if (!id.equals("")) {
				buffer.append(" where id=" + id + " ");
			}
		}
		try {
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setSms_type(rs.getString(2));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getsmsitypelist() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id, type FROM sms_invoice_type ");
		try {
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setSms_itype(rs.getString(2));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getsmstempExist(String id) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from sms_template_master where sms_type='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addsmstmp(Master master) {
		int res = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" insert into sms_template_master(sms, sms_type, sms_itype) values(?,?,?)");
			ps.setString(1, master.getSms());
			ps.setString(2, master.getSms_type());
			ps.setString(3, master.getSms_itype());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Master getMasterSMSTemp(String id) {
		Master master = new Master();
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select id,sms, sms_type,sms_itype from sms_template_master where id='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setSms(rs.getString(2));
				master.setSms_type(rs.getString(3));
				master.setSms_itype(rs.getString(4));
				list.add(master);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updatSMSMaster(Master master) {
		int result = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" update sms_template_master set sms=?, sms_type=?, sms_itype=? where id='"
							+ master.getId() + "' ");
			ps.setString(1, master.getSms());
			ps.setString(2, master.getSms_type());
			ps.setString(3, master.getSms_itype());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateSMSMonthCount(String month, String year, int i) {
		int res = 0;
		try {
			String sql = "select count,id,msgsents from sms_month_count where year='" + year + "' and month='" + month
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1) + i;
				int msgsents = rs.getInt(3) + 1;
				PreparedStatement ps = connection.prepareStatement(
						" update sms_month_count set count=?,msgsents=? where id='" + rs.getInt(2) + "' ");
				ps.setString(1, "" + count);
				ps.setString(2, "" + msgsents);
				res = ps.executeUpdate();
			} else {
				PreparedStatement ps = connection
						.prepareStatement("insert into sms_month_count(count, month, year,msgsents) values(?,?,?,?)");
				ps.setString(1, "" + i);
				ps.setString(2, month);
				ps.setString(3, year);
				ps.setString(4, "1");
				res = ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> listoutsourcerate() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select id,invstypename, charge,vendor from outsource_investigation_charges");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setInvestigation_name(rs.getString(2));
				master.setCharge(rs.getString(3));
				master.setOutsource_id(rs.getInt(4));
				Master master1 = getOutsourceinfo(master);
				master.setOutsource_name(master1.getOutsource_name());
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addoutsourceratemaster(Master master1) {
		int result = 0;
		InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
		String sql = "insert into outsource_investigation_charges(invstypeid,invstypename,charge,vendor) values(?,?,?,?)";

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master1.getInvsttype());
			Master master = investigationMasterDAO.getInvestigationType(master1.getInvsttype());
			ps.setString(2, master.getName());
			ps.setString(3, master1.getCharge());
			ps.setString(4, master1.getVendor());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getoutsourcerateList(String id) {
		Master master = new Master();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select id,invstypeid, charge,vendor from outsource_investigation_charges where id=" + id + "");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setInvestigstion_id(rs.getString(2));
				master.setCharge(rs.getString(3));
				master.setOutsource_id(rs.getInt(4));
				Master master1 = getOutsourceinfo(master);
				master.setOutsource_name(master1.getOutsource_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public int updateoutsourceratemaster(Master master) {
		int result = 0;
		InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
		try {
			PreparedStatement ps = connection.prepareStatement(
					" update outsource_investigation_charges set invstypeid=?, invstypename=?, charge=?, vendor=? where id='"
							+ master.getId() + "' ");
			ps.setString(1, master.getInvsttype());
			Master master1 = investigationMasterDAO.getInvestigationType(master.getInvsttype());
			ps.setString(2, master1.getName());
			ps.setString(3, master.getCharge());
			ps.setString(4, master.getVendor());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getallVitalMasterdata(String type) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, name, department, min_value_m, max_value_m, min_value_f, max_value_f,imagename,unit from his_vital_master where vital_type="
					+ type + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				String vital_master_id = rs.getString(1);
				master.setDepartment(rs.getString(3));
				master.setMin_value_m(rs.getString(4));
				master.setMax_value_m(rs.getString(5));
				master.setMin_value_f(rs.getString(6));
				master.setMax_value_f(rs.getString(7));
				master.setImagename(rs.getString(8));
				String unit = DateTimeUtils.isNull(rs.getString(9));
				if (unit.equals("0")) {
					unit = "";
				}
				master.setUnit(unit);
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getdischargeVitalVal(String masterid, String ipdid) {
		String res = "";
		try {
			String sql = "  select finding from his_vital where vital_master_id='" + masterid + "' and ipdid='" + ipdid
					+ "' and isfromdcard='1' order by id desc limit 1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<UserProfile> userSignaturesList() {
		ArrayList<UserProfile> list = new ArrayList<UserProfile>();
		try {
			String sql = " select * from users_sign_docs  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserProfile user = new UserProfile();
				user = userProfileDAO.getUserprofileDetails(rs.getInt("userid"));
				user.setId(rs.getInt("userid"));
				user.setFilename(rs.getString("filename"));

				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addUserSignature(UserProfile userProfile) {
		try {
			PreparedStatement ps = connection
					.prepareStatement(" insert into users_sign_docs(userid,filename) values(?,?) ");
			ps.setString(1, "" + userProfile.getId());
			ps.setString(2, userProfile.getFilename());
			int i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUserSignature(UserProfile userProfile) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					" update users_sign_docs set filename=? where userid=" + userProfile.getId() + " ");
			ps.setString(1, userProfile.getFilename());
			int i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean userSignExist(String userid) {
		boolean flag = false;
		try {
			String sql = " select * from users_sign_docs where userid='" + userid + "'  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public UserProfile userSignature(String userId) {
		UserProfile user = new UserProfile();
		try {
			String sql = " select * from users_sign_docs where userid='" + userId + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				user = userProfileDAO.getUserprofileDetails(rs.getInt("userid"));
				user.setId(rs.getInt("userid"));
				user.setFilename(rs.getString("filename"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<Master> getBirthPlaceList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = " select * from birth_place   ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master m = new Master();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addBirthPlcae(String name) {
		int i = 0;
		try {
			String sql = " insert into birth_place(name) value(?)    ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int getbirthplaceExist(String name) {

		int result = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("select name from birth_place where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public Master getMasterInfoBirthplace(String id) {

		Master master = new Master();
		try {
			PreparedStatement ps = connection.prepareStatement("select id,name from birth_place where id='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;

	}

	public int updateBirthPlaceMaster(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update birth_place set name = ? where id = " + master.getId() + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getName());
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void saveChargeIdOfVaccine(int chargeid, String vaccineid) {
		try {
			String sql = "update apm_vacination_data set charge_created_id='" + chargeid + "' where id='" + vaccineid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Master> getDisciplineDataListWithChecked() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline where showdeptlist='1'";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getMasterChargeList() {
		ArrayList<Master> arrayList = new ArrayList<>();
		try {
			String sql = "select id,name from apm_newchargetype where procedures=0";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public ArrayList<Master> getDepartmentWisePatientCount(String date, String nextDate,String deptid,LoginInfo loginInfo) {
		// date = DateTimeUtils.getCommencingDate1(date);
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		StringBuffer buffer =new StringBuffer();
		buffer.append("SELECT id,discipline FROM apm_discipline where showdeptlist='1' ");
		if(!deptid.equals("")) {
			buffer.append(" and id="+deptid+"");
		}
	
		try {
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int finalNewPatientCount = 0;
			int finalOldPatientCount = 0;
			int finalTotalPatientCount = 0;
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				int newPatientCount = getDeptPatientCount(1, rs.getInt(1), date, nextDate);
				int oldPatientCound = getDeptPatientCount(0, rs.getInt(1), date, nextDate);
				int totalPatientCount = newPatientCount + oldPatientCound;
           
				finalNewPatientCount = finalNewPatientCount + newPatientCount;
				finalOldPatientCount = finalOldPatientCount + oldPatientCound;
				finalTotalPatientCount = finalTotalPatientCount + totalPatientCount;
				master.setFinalNewPatientCount(finalNewPatientCount);
				master.setFinalOldPatientCount(finalOldPatientCount);
				master.setFinalTotalPatientCount(finalTotalPatientCount);
				master.setNewPatientCount(newPatientCount);
				master.setOldPatientCound(oldPatientCound);
				master.setTotalPatientCount(totalPatientCount);
				double charge=0;
				if(loginInfo.isPhysio()) {
					ArrayList<Accounts> clientidsList = new ArrayList<Accounts>();
					ArrayList<Accounts> chargelist = new ArrayList<Accounts>();
					ArrayList<Accounts> paymentlist = new ArrayList<Accounts>();
					ChargesReportDAO chargesReportDAO =new JDBCChargesReportDAO(connection);
					MasterDAO masterDAO=new  JDBCMasterDAO(connection);
					clientidsList=chargesReportDAO.getDeptlist(date,nextDate,""+master.getId());
					
					String cliidss="";
					if(clientidsList.size()!=0){
						Accounts clientids=clientidsList.get(clientidsList.size()-1);
						String clientid=clientids.getClientids();
						cliidss=clientid.substring(0,clientid.length()-1);
					}
					chargelist=masterDAO.invoicelistfornkp(date,nextDate,cliidss);
					String inv1="";
					if(chargelist.size()!=0){
					Accounts invids=chargelist.get(chargelist.size()-1);
					String inv=invids.getInvoiceids();
					inv1=inv.substring(0, inv.length()-1);
					}
					paymentlist=masterDAO.getChargePaymentlist(inv1,date,nextDate);
					double totalAmt = 0;
					
					 for(Accounts accounts : paymentlist){ 
						 totalAmt = totalAmt+accounts.getPayAmount();
						 
					 }
					 master.setCharge(""+totalAmt);
                }else {
			    charge=getChargessum(master.getDiscipline(),date,nextDate,loginInfo,"");
				master.setCharge(""+charge);
			}
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private double getChargessum(String discipline, String date, String nextDate,LoginInfo loginInfo, String cliidss) {
	  double charge=0.0;
	  try {
		  if(discipline.equals("Oral Diagnosis Medicine & Radiology (ODMR)")) {
			  discipline="ORAL DIAGNOCIS";
		  }if(discipline.equals("Conservative (CNV)")) {
			  discipline="CONSERVATIVE";
		  }if(discipline.equals("Public Health Dentistry (PHD)")) {
			  discipline="PUBLIC HEALTH DENTISTRY";
		  }
		  String sql="";
		  if(loginInfo.isPhysio()) {
			 sql="select sum(charge) FROM apm_invoice_assesments  where apm_invoice_assesments.clientId in('"+cliidss+"') and commencing between '"+date+"' and '"+nextDate+"' ";

		  }else {
		    sql="select sum(charge) from apm_invoice_assesments where masterchargetype='"+discipline+"' and commencing between '"+date+"' and '"+nextDate+"'";
		  }
		  PreparedStatement preparedStatement = connection.prepareStatement(sql);
		  ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				charge=rs.getDouble(1);
			}

	  } catch (Exception e) {
		e.printStackTrace();
	}
		return charge;
	}

	public int getDeptPatientCount(int patientType, int id, String date, String nextDate) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from department_opd where dept='" + id + "' and commencing between '" + date
					+ "' and '" + nextDate + "' ");
			if (patientType == 1) {
				buffer.append("and newpatient=1 ");
			} else {
				buffer.append("and newpatient!=1 ");
			}
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

	@Override
	public ArrayList<Master> getDisciplineWithChecked(String dicipline) {

		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();

		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id,discipline FROM apm_discipline where showdeptlist='1' ");
		buffer.append("and id not in (" + dicipline + ") ");

		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public ArrayList<Master> getprocedurelist() {
		// TODO Auto-generated method stub

		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "select id,name from apm_newchargetype where procedures=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public ArrayList<Master> getIpdTemplateListNamesList(String templateName) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			// String sql="SELECT id, template_nameid, text, name, department
			// from ipd_template where showall='1' or
			// template_nameid='"+templateName+"'";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"SELECT ipd_template.id, template_nameid, text, ipd_template.name, department from ipd_template ");
			buffer.append("inner join ipd_templatename on ipd_templatename.id= ipd_template.template_nameid ");
			buffer.append("where showall=1 or ipd_templatename.name=? ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ps.setString(1, templateName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTemplate_nameid(rs.getString(2));
				master.setTemplate_text(getIpdTemplatenamefromId(rs.getString(2)));
				master.setText(rs.getString(3));
				master.setName(rs.getString(4));
				master.setDepartment(rs.getString(5));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Override
	public boolean checkTermsandCondtion(String string) {
		// TODO Auto-generated method stub

		boolean flag = false;

		try {
			String sql = "SELECT term FROM terms_and_condition where term=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, string);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	
	public ArrayList<Master>getallhospitallist(String pro_userid_id,String clinic_userid) {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = "select id,concat(hosp_name,' - ', city),commercial from admin.ipd_hospital where user_id='"+clinic_userid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				String commercial = getCommericalHospital(pro_userid_id,rs.getInt(1));
				if(DateTimeUtils.numberCheck(commercial).equals("0")){
					commercial = rs.getString(3);
				}
				master.setHosp_name(rs.getString(2)+" - "+commercial);
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getCommericalHospital(String pro_userid_id, int hospid) {
		String commercial ="0";
		try {
			String sql="select commercial_charge from admin.clinic_hospital_commercial where clinic_id=? and hospital_id='"+hospid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pro_userid_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				commercial = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commercial;
	}

	@Override
	public ArrayList<Master> getDepartmenDataList(String selecteddept_id) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline where id="+selecteddept_id+" ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ArrayList<Master> editDisciplineDataList(String selecteddeptid) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline where id="+selecteddeptid+" ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public String getCommericalHospitalPer(String refclinicid) {
		String str ="0";
		try {
			String sql="select commercial from admin.ipd_hospital where id='"+refclinicid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public ArrayList<Master> getSpecializalist() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	
	}

	@Override
	public int chklocationexist(String speciality) {
		// TODO Auto-generated method stub
		   int res=0;
		try {
			String sql="select id from apm_condition where speciality='"+speciality+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Master> getdepartment() {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_condition ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));;

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Master> getConsultantNotesTemplateList(String templateName) {


		ArrayList<Master> list = new ArrayList<Master>();
		try {

			// String sql="SELECT id, template_nameid, text, name, department
			// from ipd_template where showall='1' or
			// template_nameid='"+templateName+"'";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"SELECT ipd_template.id, template_nameid, text, ipd_template.name, department from ipd_template ");
			buffer.append("inner join ipd_templatename on ipd_templatename.id= ipd_template.template_nameid ");
			buffer.append("where showall=1 or ipd_templatename.name=? ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ps.setString(1, templateName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTemplate_nameid(rs.getString(2));
				master.setTemplate_text(getIpdTemplatenamefromId(rs.getString(2)));
				master.setText(rs.getString(3));
				master.setName(rs.getString(4));
				master.setDepartment(rs.getString(5));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updatewhatsappcount(int wsms, String message) {
		int result = 0;
		try {

			wsms = wsms + getWSMSCount();
			String sql = "update apm_whatsms_counter set count=?,whtsms=? where id=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, wsms);
			ps.setString(2, message);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	

	@Override
	public int getWSMSCount() {
		int count = 0;

		try {

			String sql = "SELECT count from apm_whatsms_counter where id=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	public void logj(String messege) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into logj(message) values(?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, (messege));

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		//return result;
		
		
		
	}

	@Override
	public ArrayList<Master> getDepartmentWisePatientCount1(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline where showdeptlist='1'";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int finalNewPatientCount = 0;
			int finalOldPatientCount = 0;
			int finalTotalPatientCount = 0;
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDiscipline(rs.getString(2));

				int newPatientCount = getDeptPatientCount1(1, rs.getInt(1), fromDate, toDate);
				int oldPatientCound = getDeptPatientCount1(0, rs.getInt(1), fromDate, toDate);
				int totalPatientCount = newPatientCount + oldPatientCound;

				finalNewPatientCount = finalNewPatientCount + newPatientCount;
				finalOldPatientCount = finalOldPatientCount + oldPatientCound;
				finalTotalPatientCount = finalTotalPatientCount + totalPatientCount;
				master.setFinalNewPatientCount(finalNewPatientCount);
				master.setFinalOldPatientCount(finalOldPatientCount);
				master.setFinalTotalPatientCount(finalTotalPatientCount);
				master.setNewPatientCount(newPatientCount);
				master.setOldPatientCound(oldPatientCound);
				master.setTotalPatientCount(totalPatientCount);

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private int getDeptPatientCount1(int patientType, int id, String fromDate, String toDate) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from department_opd where dept='" + id + "' and commencing between '" + fromDate
					+ "' and '" + toDate + "' and aptmtype!=1 ");
			if (patientType == 1) {
				buffer.append("and newpatient=1 ");
			} else {
				buffer.append("and newpatient!=1 ");
			}
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

	@Override
	public ArrayList<Master> getPayrollTemplateList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			
			String	sql = "select id,othertemplate_text,title,discipline_id from apm_other_template where payroll=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Master master = new Master();
				int id = rs.getInt(1);
				String other_template_text = rs.getString(2);
				String title = rs.getString(3);
				master.setId(id);
				master.setOther_template_text(other_template_text);
				master.setTitle(title);
				master.setDiscipline_id(rs.getString(4));

				Master master2 = getDisciplineData(master.getDiscipline_id());
				master.setDiscipline(master2.getDiscipline());

				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ArrayList<Master> getAllpayrolldeclarationList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,title from apm_other_template where payroll=1 order by title ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;	}

	@Override
	public String getpayrollTitleOfDeclaration(int id) {
		PreparedStatement preparedStatement = null;
		String title = " ";
		String sql = "select title from apm_other_template where selected = 1 ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				title = rs.getString(1);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return title;
	}

	@Override
	public String getpayrollDeclaration(int id) {
		PreparedStatement preparedStatement = null;
		String notes = " ";
		String sql = "select othertemplate_text from apm_other_template where selected = 1 ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				notes = rs.getString(1);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return notes;
	}

	@Override
	public Master getpayrollPrint(String empid) {
	PreparedStatement pst=null;
	Master master=new Master();
	try {
		String sql = "select id,declaration_data,declration_title from apm_document where empid = '"+empid+"' ";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			master.setId(rs.getInt(1));
			master.setOther_template_text(rs.getString(2));
			master.setTitle(rs.getString(3));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return master;
	}

	@Override
	public int insertSmsdata(LoginInfo loginInfo, String smsUrl, String date_time, String mobileNo, int status,
			String error) {
		PreparedStatement preparedStatement=null;
		int res=0;
		try {
			
			String sql="insert into sms_log (date_time, clinicid, sms_link, patient_name, mobno, error, status,smstype) values(?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date_time);
			preparedStatement.setString(2, loginInfo.getClinicid1());
			preparedStatement.setString(3, smsUrl);
			preparedStatement.setString(4, loginInfo.getPatient_name());
			preparedStatement.setString(5, mobileNo);
			preparedStatement.setString(6, error);
			preparedStatement.setInt(7, status);
			preparedStatement.setString(8, loginInfo.getSms_type());
			res=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public String getApikey() {
		PreparedStatement preparedStatement = null;
		String key = " ";
		String sql = "select api_key from apm_clinic_location where id = 1 ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				key = rs.getString(1);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	@Override
	public ArrayList<Master> getPhysioIpdhospitalList() {
		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "select id,hospital_name from physio_ipd_hospital ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setHosp_name(rs.getString(2));
				list.add(master);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
		
	}

	@Override

		public void saveChargeIdOfMedicine(int chargeid, String vaccineid) {
			try {
				String sql = "update veterinary_medicin_data set charge_created_id='" + chargeid + "' where id='" + vaccineid
						+ "'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	@Override
	public Master getProcedureTemplate(String id) {
		Master master = new Master();
		try {

			String sql = "SELECT name FROM apm_newchargetype  where id="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				master.setTemplate_nameid(rs.getString(1));
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}
	
	@Override
	public ArrayList<Master> getReferDepartmentwisePatientCount(String currentDate, String toDate, String dicipline,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql="";
        if(loginInfo.isAmravati()) {
	         sql = "SELECT id,dept FROM department_opd where commencing between '"+currentDate+"'  and '"+toDate+"' and dept!='113' group by dept ";
        }else {
	         sql = "SELECT id,dept FROM department_opd where commencing between '"+currentDate+"'  and '"+toDate+"' group by dept ";
       }
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				String deptname=getdeptnameByDeptId(rs.getString(2));
				master.setDiscipline(deptname);
                ArrayList<Master>refercountlist=getrefercount(rs.getString(2),currentDate,toDate,dicipline,loginInfo);
                if(refercountlist.size()>0) {
                	master.setReferdeptlist(refercountlist);
                }
				//master.setReferdeptlist(refercountlist);
				
				  list.add(master);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	private ArrayList<Master> getrefercount(String sourcedept, String currentDate, String toDate, String dicipline,LoginInfo loginInfo) {
		ArrayList<Master> list = new ArrayList<Master>();

		try {
			String sql="";
            if(loginInfo.isAmravati()) {
           	     sql = "select count(*),refertodept,dept from department_opd where refertodept='"+sourcedept+"' and commencing between '"+currentDate+"' and '"+toDate+"' and dept=113 group by refertodept";

            }else {
            	 sql = "select count(*),refertodept,dept from department_opd where dept='"+sourcedept+"' and commencing between '"+currentDate+"' and '"+toDate+"'  group by refertodept";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Master master = new Master();

				master.setCount(rs.getInt(1));
				String refertodept=getdeptnameByDeptId(rs.getString(2));
				master.setReferred(refertodept);
				String dept=getdeptnameByDeptId(rs.getString(3));
				master.setDepartment(dept);
				if(!rs.getString(2).equals("0")) {
				   list.add(master);
			    }
				//list.add(master);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
		
	}

	public String getdeptnameByDeptId(String deptid) {
		String deptname = "";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT discipline FROM apm_discipline where id='"+deptid+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				deptname = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptname;
		
	}

	@Override
	public String getDiscriptionidbyuserid(String userId) {
		String description = "";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT discription FROM apm_user where userid='"+userId+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				description = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return description;
	}

	@Override
	public int getArvpatientcount(String deptid, String fromDate, String toDate, String newpatient,String patinetcategory) {
		int arvcount=0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from department_opd inner join apm_patient on apm_patient.id=department_opd.clientId  ");
			buffer.append(" where dept='"+deptid+"'and commencing between '"+fromDate+"' and '"+toDate+"' and");
		 if(patinetcategory.equals("ARV Charity")) {
			
			 buffer.append(" patientcategory='ARV Charity' ");
		  }else {
			 buffer.append(" patientcategory !='ARV Charity' ");
		  }

		  if(newpatient.equals("1")) {
				buffer.append(" and newpatient=1 ");
		  }else {
				buffer.append(" and newpatient=2 ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				arvcount=rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arvcount;
	}

	@Override
	public ArrayList<Master> getDepartmentWisePatientCount2(String fromDate, String toDate, LoginInfo loginInfo, String deptid) {
		// date = DateTimeUtils.getCommencingDate1(date);
				PreparedStatement preparedStatement = null;
				ArrayList<Master> list = new ArrayList<Master>();
				StringBuffer buffer=new StringBuffer();
				
				buffer.append("SELECT id,discipline FROM apm_discipline where showdeptlist='1' ");
				if(!deptid.equals("")) {
				buffer.append(" and id="+deptid+"");
				}
				try {
					preparedStatement = connection.prepareStatement(buffer.toString());
					ResultSet rs = preparedStatement.executeQuery();
				
					while (rs.next()) {
						Master master = new Master();
						master.setId(rs.getInt(1));
						master.setDiscipline(rs.getString(2));

						int arvnew=getArvpatientcount(""+rs.getInt(1),fromDate,toDate,"1","ARV Charity");
						int arvold=getArvpatientcount(""+rs.getInt(1),fromDate,toDate,"2","ARV Charity");
					    int newpatient=getArvpatientcount(""+rs.getInt(1),fromDate,toDate,"1","");
					    int oldpatient=getArvpatientcount(""+rs.getInt(1),fromDate,toDate,"2","");
					    int total=arvnew+arvold+newpatient+oldpatient;
					    master.setArvnew(arvnew);
					    master.setArvold(arvold);
					    master.setNewpatient(newpatient);
					    master.setOldpatient(oldpatient);
					    master.setTotalcount(total);
						list.add(master);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;

	}

	@Override
	public Master getUserdatabyid(String empid) {
		PreparedStatement preparedStatement=null;
		Master master=new Master();
		try {
			String sql="select clinicname,concat(firstname,' ',lastname),jobtitle from apm_user inner join apm_payroll_employee on apm_user.userid=apm_payroll_employee.userid where apm_payroll_employee.empid='"+empid+"'";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				master.setHosp_name(rs.getString(1));
				master.setFullname(rs.getString(2));
				master.setJobtitle(rs.getString(3));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public int saveAppointmentdocs(String empid, String declarationNotes, String declarationTitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String cdate = DateTimeUtils.getUKCurrentDataTime("India");
		String sql = "insert into apm_document (lastmodified,declaration_data,declration_title,empid) values(?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cdate);
			preparedStatement.setString(2, declarationNotes);
			preparedStatement.setString(3, declarationTitle);
			preparedStatement.setString(4, empid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Master getUserApmtdocsbyid(int id) {
		PreparedStatement preparedStatement=null;
		Master master=new Master();
		try {
			String sql="select declaration_data,declration_title from apm_document inner join apm_payroll_employee on apm_user.userid=apm_payroll_employee.userid where apm_payroll_employee.empid";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				master.setHosp_name(rs.getString(1));
				master.setFullname(rs.getString(2));
				master.setJobtitle(rs.getString(3));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public String getEmpidbyloginid(int id) {
		PreparedStatement preparedStatement=null;
		String empid="";
		try {
			
			String sql="select empid from apm_payroll_employee inner join apm_user on apm_user.userid=apm_payroll_employee.userid where id="+id+"";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				empid=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empid;
	}

	@Override
	public int addNewDoctor(Master master) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String cdate = DateTimeUtils.getUKCurrentDataTime("India");
		String sql = "insert into ipd_refered_data (doctor,share) values(?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, master.getDoctorName());
			preparedStatement.setString(2, master.getShare());


			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
	}

	@Override
	public ArrayList<Master> getAllCampLocation() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		
		sql = "SELECT id, location_name FROM camp_location";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setCamp_location(rs.getString(2));
				

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int addCampLocation(Master master) {
		int result = 0;
		try {
			String query = "insert into camp_location(location_name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getCamp_location());
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	@Override
	public NotAvailableSlot getAppointmentchargedetails(String chargetype) {
		PreparedStatement pst=null;
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		try {
			String sql="SELECT id,name,chargeType,charges FROM apm_appointment_type where chargeType='"+chargetype+"' and tpid = 0 and wardid=0 and otchargetype = 1 ";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setName(rs.getString(2));
				notAvailableSlot.setChargeType(rs.getString(3));
				notAvailableSlot.setCharge(rs.getDouble(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	@Override
	public ArrayList<Diagnosis> getDiagnosisList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();
		String sql = "SELECT id,name FROM apm_diagnosis ";
		// InventoryProductDAO inventoryProductDAO= new
		// JDBCInventoryProductDAO(connection);
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setName(rs.getString(2));
			
				list.add(diagnosis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Master> getMedicineListbydiagnosis(String diagnosisid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,drug,genericname,catalogueid FROM apm_medicine_details where diagnosisid='"+diagnosisid+"' and drug is not null and drug!='' order by drug ";
		
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				// String catalogueid= rs.getString(4);
				// int stock
				// =inventoryProductDAO.getStockFromProduct(catalogueid);
				/*
				 * master.setGenericname(rs.getString(3)+" - "+master.getName()+
				 * "");
				 */
				if (rs.getString(3) != null) {
					if (!rs.getString(3).equals("")) {
						master.setGenericname(rs.getString(3) + " - " + master.getName() + "");
					} else {
						master.setGenericname("" + master.getName() + "");
					}
				} else {
					master.setGenericname("" + master.getName() + "");
				}
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int saveclientType(Master master) {
		int result = 0;
		try {
			String query = "insert into client_type(clientType) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, master.getClientType());
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
		
	}

	@Override
	public ArrayList<Master> getAllClientTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "";
		
		sql = "SELECT id, clientType FROM client_type";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Master master = new Master();

				master.setId(rs.getInt(1));
				master.setClientType(rs.getString(2));
				

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Accounts> invoicelistfornkp(String fromDate, String toDate, String cliidss) {
		String tp = "Third Party";String self = "Client";
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		
		try {
			sql.append("SELECT apm_charges_invoice.id FROM apm_charges_invoice");
			//sql.append(" inner join apm_patient on apm_patient.id=apm_charges_invoice.clientid ");
			sql.append(" inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id where apm_invoice_assesments.clientId in("+cliidss+") and apm_invoice_assesments.commencing between '"+fromDate+"' AND '"+toDate+" 23:59:59' ");
			sql.append(" group by invoiced order by apm_charges_invoice.id desc");
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int res =0;
			
			while(rs.next()){
				
				buffer=buffer.append(rs.getString(1)+",");
				Accounts accounts=new Accounts();
				
			    accounts.setInvoiceids(""+buffer);
				
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Accounts> getChargePaymentlist(String inv1, String fromDate, String toDate) {
		PreparedStatement preparedStatement=null;
		
		ArrayList<Accounts> list=new ArrayList<Accounts>();
		StringBuffer buffer=new StringBuffer();
		String tp = "Third Party";
		try {
			
			 buffer.append("select sum(payment) from apm_charges_payment ");
			 buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid ");
			 //buffer.append("inner join apm_patient on apm_patient.id=apm_charges_payment.clientid ");
			 //buffer.append("inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_charges_payment.clientid ");
			 buffer.append("where apm_charges_payment.chargeinvoiceid in("+inv1+") and apm_charges_invoice.date between '"+fromDate+"' AND '"+toDate+" 23:59:59' ");
			 buffer.append("and paymode!='prepayment' group by  apm_charges_payment.chargeinvoiceid order by apm_charges_payment.id desc");
			 preparedStatement=connection.prepareStatement(buffer.toString());
			 ResultSet rs=preparedStatement.executeQuery();
			 while(rs.next()) {
				 Accounts accounts=new Accounts();
				 accounts.setPayAmount(rs.getDouble(1));
				 list.add(accounts);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public Master getChiefComplainsname(String s) {
		Master master = new Master();

		try {

			String sql = "select text from ipd_template where id='" + s + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setChiefcomplains(rs.getString(1));
				master.setId(Integer.parseInt(s));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	@Override
	public int checkExixstChiefComplains(String chiefcomplains, int id) {
		int res=0;
		try {
			String sql="select id from ipd_template where text='"+chiefcomplains+"' and template_nameid='"+id+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int addChiefComplainName(Master master) {
		int result = 0;
		try {

			
				String sql = "insert into ipd_template (template_nameid,text,department) values (?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1,master.getTemplateid());
				ps.setString(2, master.getChiefcomplains());
				ps.setString(3,"1");
				result = ps.executeUpdate();
				
				if(result>0){
					
					ResultSet rs=ps.getGeneratedKeys();
					while(rs.next()){
						 result =rs.getInt(1);
					}
				}
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Master getHistoryname(String s) {
		Master master = new Master();

		try {

			String sql = "select text from ipd_template where id='" + s + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setPasthistory(rs.getString(1));
				master.setHistoryid(s);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	@Override
	public Master getInvestigationName(String s) {
		Master master = new Master();

		try {

			String sql = "select name from apm_investigation_type where id='" + s + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setInvestigation_name(rs.getString(1));
				master.setId(Integer.parseInt(s));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	@Override
	public int checkExixstinvestigation(String investigation_name) {
		int res=0;
		try {
			String sql="select id from apm_investigation_type where name='"+investigation_name+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int addInvestigationName(Master master) {
		int result = 0;
		try {

			
				String sql = "insert into apm_investigation_type (name) values (?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				//ps.setInt(1, master.setInvestigation_name());
				ps.setString(1, master.getInvestigation_name());
				
				result = ps.executeUpdate();
				
				if(result>0){
					
					ResultSet rs=ps.getGeneratedKeys();
					while(rs.next()){
						 result =rs.getInt(1);
					}
				}
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Master getNotesName(String s) {
		Master master = new Master();

		try {

			String sql = "select text from ipd_template where id='" + s + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setNotes(rs.getString(1));
				master.setId(Integer.parseInt(s));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	@Override
	public Master getChragesName(String s) {
		Master master = new Master();

		try {

			String sql = "select name,charges,chargeType from apm_appointment_type where id='" + s + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setChargename(rs.getString(1));
				master.setId(Integer.parseInt(s));
				master.setCharge(rs.getString(2));
				master.setChargetype(rs.getString(3));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	@Override
	public int insertCharges(String charge, String chargename) {
		int result=0;
		PreparedStatement pst=null;
		try {
			
			String sql="insert into apm_appointment_type(name,charges,chargeType,hours,discountAmt,originalAmt) values(?,?,?,?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setString(1, chargename);
			pst.setString(2, charge);
			pst.setString(3, "Additional Charge");
			pst.setString(4, "24");
			pst.setString(5, charge);
			pst.setString(6, charge);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}