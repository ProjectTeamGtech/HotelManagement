package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.entity.Declaration;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCTreatmentTypeDAO extends JDBCBaseDAO implements
		TreatmentTypeDAO {

	public JDBCTreatmentTypeDAO(Connection connection) {
		this.connection = connection;

	}

	public int getTotalTreatmentTypeCount(String searchText) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "";
		if (searchText != null) {
			sql = "select count(*) from apm_diagnosis where name like '"
					+ searchText + "%'";
		} else {
			sql = "select count(*) from apm_diagnosis";
		}
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

	public ArrayList<TreatmentType> getTreatmentTypeList(Pagination pagination,
			String searchText) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentType> list = new ArrayList<TreatmentType>();
		String sql = "";
		if (pagination.sortColumn == null) {
			if (searchText != null) {
				sql = "SELECT id,name,diseasecode,icdcode,dateTime,department,isdeleted FROM apm_diagnosis where name like '%"
						 + searchText + "%' order by id desc";
			} else {
				sql = "SELECT id,name,diseasecode,icdcode,dateTime,department,isdeleted FROM apm_diagnosis order by id desc";
			}
		} else {
			if (searchText != null) {
				sql = "SELECT id,name,diseasecode,icdcode,dateTime,department,isdeleted FROM apm_diagnosis where name like '%"
						+ searchText + "%'";
			} else {
				sql = "SELECT id,name,diseasecode,icdcode,dateTime,department,isdeleted FROM apm_diagnosis";
			}
		}
		sql = pagination.getSQLQuery(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				TreatmentType treatmentType = new TreatmentType();
				treatmentType.setId(rs.getInt(1));

				// treatmentType.setTreatmentName(rs.getString(2));

				String condition = rs.getString(2);

				if (rs.getString(3) != null && rs.getString(4) != null) {
					condition = condition + " " + rs.getString(3) + " / "
							+ rs.getString(4);
				}

				else if (rs.getString(4) != null) {
					condition = condition + " / " + rs.getString(4);
				}

				treatmentType.setTreatmentName(condition);

				treatmentType.setDateTime(rs.getString(5));
				String department = getDepartmentName(rs.getString(6));
				treatmentType.setLocation(department);
				treatmentType.setIsdeleted(rs.getString(7));
				treatmentType.setIcd_code(rs.getString(4));
				list.add(treatmentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private String getPractitionerName(String practitionerid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname FROM apm_user where id= '"
				+ practitionerid + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					result = rs.getString(2) + " " + rs.getString(3);

				} else {
					result = rs.getString(1) + " " + rs.getString(2) + " "
							+ rs.getString(3);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveTreatmentType(TreatmentType treatmentType) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_diagnosis(name,dateTime,department,icdcode) values(?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, treatmentType.getTreatmentName());
			// preparedStatement.setString(2,
			// treatmentType.getTreatmentNotes());
			preparedStatement.setString(2, DateTimeUtils
					.getCurrentDateInDDMMYYYYCasting());
			preparedStatement.setString(3, treatmentType.getLocation());
			// preparedStatement.setString(4, treatmentType.getDiaryUser());
			preparedStatement.setString(4, treatmentType.getIcd_code());
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public TreatmentType getTreatmentType(int id, TreatmentType treatmentType) {

		PreparedStatement preparedStatement = null;

		String sql = "SELECT id,name,dateTime,department,icdcode FROM apm_diagnosis where id = "
				+ id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				treatmentType.setId(rs.getInt(1));
				treatmentType.setTreatmentName(rs.getString(2));
				// treatmentType.setTreatmentNotes(rs.getString(3));
				treatmentType.setDateTime(rs.getString(3));
				String department = getDepartmentName(rs.getString(4));
				treatmentType.setLocation(rs.getString(4));
				treatmentType.setIcd_code(rs.getString(5));
				// treatmentType.setDiaryUser(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return treatmentType;
	}

	private String getDepartmentName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select location from apm_clinic_location where id = "
				+ id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateTreatmentType(TreatmentType treatmentType, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_diagnosis set name = ?,department=?, icdcode=? where id = "
				+ id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, treatmentType.getTreatmentName());
			preparedStatement.setString(2, treatmentType.getLocation());
			preparedStatement.setString(3, treatmentType.getIcd_code());
			// preparedStatement.setString(2,
			// treatmentType.getTreatmentNotes());
			// preparedStatement.setTimestamp(3,
			// DateTimeUtils.getCurrentDateInSQLCasting());
			// preparedStatement.setString(3, treatmentType.getDiaryUser());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteTreatmentType(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_diagnosis where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TreatmentType> getConditionList() {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentType> list = new ArrayList<TreatmentType>();
		String sql = "";

		sql = "SELECT id,name FROM apm_diagnosis order by name";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				TreatmentType treatmentType = new TreatmentType();
				treatmentType.setId(rs.getInt(1));
				treatmentType.setTreatmentName(rs.getString(2));

				list.add(treatmentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getMasterList() {
		// TODO Auto-generated method stub
		ArrayList<Master> masters = new ArrayList<Master>();

		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_select_master order by mastername");
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

	public TreatmentType getDepartmentCondition(int id) {

		TreatmentType treatmentType = new TreatmentType();
		String sql = "SELECT id,name,diseasecode,icdcode,dateTime,department FROM apm_condition where id="
				+ id + " ";
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				treatmentType.setId(rs.getInt(1));
				treatmentType.setTreatmentName(rs.getString(2));

				treatmentType.setDateTime(rs.getString(5));
				String department = getDepartmentName(rs.getString(6));
				treatmentType.setLocation(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return treatmentType;
	}

	public ArrayList<TreatmentType> getDeptConditionList() {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentType> list = new ArrayList<TreatmentType>();
		String sql = "SELECT id,name,diseasecode,icdcode,dateTime,department FROM apm_condition ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				TreatmentType treatmentType = new TreatmentType();
				treatmentType.setId(rs.getInt(1));
				treatmentType.setTreatmentName(rs.getString(2));

				treatmentType.setDateTime(rs.getString(5));
				String department = getDepartmentName(rs.getString(6));
				treatmentType.setLocation(department);
				list.add(treatmentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public int updateDeleteDiagnosis(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_diagnosis set isdeleted = 1 where id = "
				+ id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<TreatmentType> getsmstemplateList() {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentType> list = new ArrayList<TreatmentType>();
		String sql = "SELECT id, template_name FROM sms_template ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				TreatmentType treatmentType = new TreatmentType();
				treatmentType.setId(rs.getInt(1));
				treatmentType.setSmstmp(rs.getString(2));
				list.add(treatmentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
