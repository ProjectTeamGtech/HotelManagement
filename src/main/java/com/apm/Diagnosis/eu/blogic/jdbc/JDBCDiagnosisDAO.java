package com.apm.Diagnosis.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.a.a.a.a.a.c;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.common.utils.Pagination;

public class JDBCDiagnosisDAO implements DiagnosisDAO {

	private Connection connection;

	public JDBCDiagnosisDAO(Connection connection) {

		this.connection = connection;
	}

	public ArrayList<Diagnosis> getAllDiagnosisList() {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,name from apm_diagnosis";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setName(rs.getString(2));
				
				if(isProblemExists(diagnosis.getId())) {
					list.add(diagnosis);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Diagnosis> getAllConditionList() {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,name from apm_diagnosis";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public int addDiagnosisName(Diagnosis diagnosis) {

		int result = 0;
		try {

			result =isdiagosisExist(diagnosis.getName()); 
			if(result==0){
				String sql = "insert into apm_diagnosis (name,icdcode,department) values (?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, diagnosis.getName());
				ps.setString(2, diagnosis.getIcdcode());
				ps.setString(3, diagnosis.getDepartment());
				result = ps.executeUpdate();
				
				if(result>0){
					
					ResultSet rs=ps.getGeneratedKeys();
					while(rs.next()){
						 result =rs.getInt(1);
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}
	
	
	public int isdiagosisExist(String name) {
		
		int result=0;
		try {
			String sql="select id from apm_diagnosis where name=?  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 result =rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	

	public ArrayList<Diagnosis> getAllDiagnosisList(String diagnosis_id) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,problem_name,description from apm_dignosis_problem where dignosis_id='"
					+ diagnosis_id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setProblem_name(rs.getString(2));
				diagnosis.setDescription(rs.getString(3));
				diagnosis.setDiagnosis_id(diagnosis_id);
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int addProblemName(Diagnosis diagnosis) {

		int result = 0;

		try {

			String sql = "insert into apm_dignosis_problem (dignosis_id,problem_name,description) values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, diagnosis.getDiagnosis_id());
			ps.setString(2, diagnosis.getProblem_name());
			ps.setString(3, diagnosis.getDescription());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int addInterventionName(Diagnosis diagnosis) {

		int result = 0;

		try {

			String sql = "insert into apm_diagnosis_intervention (diagnosis_id,diag_problem_id,intervention,description) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, diagnosis.getDiagnosis_id());
			ps.setString(2, diagnosis.getDiag_problem_id());
			ps.setString(3, diagnosis.getIntervention());
			ps.setString(4, diagnosis.getDescription());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getTotalInterventionCount(String searchText) {

		int result = 0;

		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select count(*) from apm_diagnosis_intervention where intervention like ('"+searchText+"%')";
			} else {
				sql = "select count(*) from apm_diagnosis_intervention";
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

	public ArrayList<Diagnosis> getAllDiagnosisList(Pagination pagination) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id, diagnosis_id, diag_problem_id, intervention, description from apm_diagnosis_intervention";
			sql = pagination.getSQLQuery(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setIntervention_id(rs.getString(1));
				String diagnosis_id = rs.getString(2);
				String problem_id = rs.getString(3);
				diagnosis.setIntervention(rs.getString(4));
				diagnosis.setDescription(rs.getString(5));

				PreparedStatement ps1 = connection.prepareStatement("select name from apm_condition where id='"
						+ diagnosis_id + "'");
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {

					diagnosis.setName(rs1.getString(1));
				}
				ps1.close();

				PreparedStatement ps2 = connection
						.prepareStatement("select problem_name from apm_dignosis_problem where id='" + problem_id + "'");
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {

					diagnosis.setProblem_name(rs2.getString(1));
				}
				ps2.close();

				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int getTotalDiagnosisNameCount() {

		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement("select count(*) from apm_condition");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Diagnosis> getAllDiagnosisNameList(Pagination pagination) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,name from apm_diagnosis";
			sql = pagination.getSQLQuery(sql);

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public Diagnosis getDiagnosisName(String selectedid) {

		Diagnosis diagnosis = new Diagnosis();

		try {

			String sql = "select name from apm_diagnosis where id='" + selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				diagnosis.setName(rs.getString(1));
				diagnosis.setId(Integer.parseInt(selectedid));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return diagnosis;
	}

	public int updateDiagnosisName(Diagnosis diagnosis) {

		int result = 0;

		try {

			String sql = "update apm_diagnosis set name=? where id='" + diagnosis.getId() + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, diagnosis.getName());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteDiagnosisName(String selectedid) {

		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement("delete from apm_diagnosis where id='" + selectedid
					+ "'");
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getTotalDiagnosisProblemCount(String searchText) {

		int result = 0;
		try {
			String sql ="";
			if (searchText!=null) {
				sql="select count(*) from apm_dignosis_problem where problem_name like ('"+searchText+"%')";
			} else {
				sql="select count(*) from apm_dignosis_problem";
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

	public ArrayList<Diagnosis> getAllDiagnosisProblemList(Pagination pagination,String searchText) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select id,dignosis_id,problem_name,description from apm_dignosis_problem where problem_name like ('"+searchText+"%')";
			} else {
				sql = "select id,dignosis_id,problem_name,description from apm_dignosis_problem";
			}
			//sql = "select id,dignosis_id,problem_name,description from apm_dignosis_problem";
			sql = pagination.getSQLQuery(sql);

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setDiagnosis_id(rs.getString(2));
				String name = getNameOfDiagnosisFromId(diagnosis.getDiagnosis_id());
				diagnosis.setName(name);
				diagnosis.setProblem_name(rs.getString(3));
				diagnosis.setDescription(rs.getString(4));
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Diagnosis> getAllDiagnosisProblemList() {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,dignosis_id,problem_name,description from apm_dignosis_problem";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setDiagnosis_id(rs.getString(2));
				String name = getNameOfDiagnosisFromId(diagnosis.getDiagnosis_id());
				diagnosis.setName(name);
				diagnosis.setProblem_name(rs.getString(3));
				diagnosis.setDescription(rs.getString(4));
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Diagnosis getDiagnosisProblem(String selectedid) {

		Diagnosis diagnosis = new Diagnosis();

		try {

			String sql = "select dignosis_id,problem_name,description from apm_dignosis_problem where id='"
					+ selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				diagnosis.setId(Integer.parseInt(selectedid));
				diagnosis.setDiagnosis_id(rs.getString(1));
				diagnosis.setProblem_name(rs.getString(2));
				diagnosis.setDescription(rs.getString(3));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return diagnosis;
	}

	public ArrayList<Diagnosis> getProblemList(String id) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,problem_name from apm_dignosis_problem where dignosis_id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setProblem_name(rs.getString(2));
				diagnosis.setDiagnosis_id(id);
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public String getNameOfDiagnosisFromId(String id) {

		String name = "";
		try {

			String sql = "select name from apm_diagnosis where id='" + id + "'";
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

	public String getNameOfDiagnosisProblemNameFromId(String id) {

		String name = "";
		try {

			String sql = "select problem_name from apm_dignosis_problem where id='" + id + "'";
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

	public int updateDiagnosisProblem(Diagnosis diagnosis) {

		int result = 0;

		try {

			String sql = "update apm_dignosis_problem set dignosis_id=?,problem_name=? where id=" + diagnosis.getId()
					+ "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, diagnosis.getDiagnosis_id());
			ps.setString(2, diagnosis.getProblem_name());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return result;
	}

	public int deleteDiagnosisProblem(String selectedid) {

		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement("delete from apm_dignosis_problem where id='"
					+ selectedid + "'");
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Diagnosis> getAllDiagnosisInterventionList(Pagination pagination,String searchText) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {
			String sql="";
			if (searchText!=null) {
				sql = "select id, diagnosis_id, diag_problem_id, intervention, description from apm_diagnosis_intervention where intervention like ('"+searchText+"%')";
			} else {
				sql = "select id, diagnosis_id, diag_problem_id, intervention, description from apm_diagnosis_intervention";
			}
			sql = pagination.getSQLQuery(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setDiagnosis_id(rs.getString(2));
				diagnosis.setName(getNameOfDiagnosisFromId(diagnosis.getDiagnosis_id()));
				diagnosis.setDiag_problem_id(rs.getString(3));
				diagnosis.setProblem_name(getNameOfDiagnosisProblemNameFromId(diagnosis.getDiag_problem_id()));
				diagnosis.setIntervention(rs.getString(4));
				diagnosis.setDescription(rs.getString(5));
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Diagnosis getDiagnosisProblemIntervention(String selectedid) {

		Diagnosis diagnosis = new Diagnosis();

		try {
			String sql = "select diagnosis_id, diag_problem_id, intervention, description from apm_diagnosis_intervention where id='"
					+ selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				diagnosis.setDiagnosis_id(rs.getString(1));
				diagnosis.setDiag_problem_id(rs.getString(2));
				diagnosis.setIntervention(rs.getString(3));
				diagnosis.setDescription(rs.getString(4));
				diagnosis.setId(Integer.parseInt(selectedid));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diagnosis;
	}

	public int updateDiagnosisProblemIntervention(Diagnosis diagnosis) {

		int result = 0;

		try {

			String sql = "update apm_diagnosis_intervention set diagnosis_id=?, diag_problem_id=?, intervention=? where id="
					+ diagnosis.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, diagnosis.getDiagnosis_id());
			ps.setString(2, diagnosis.getDiag_problem_id());
			ps.setString(3, diagnosis.getIntervention());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteDiagnosisProblemIntervention(String selectedid) {

		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement("delete from apm_diagnosis_intervention where id='"
					+ selectedid + "'");
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int addPatientIllness(String ipdid, String clientid) {

		int result = 0;

		try {

			String sql = "insert into apm_patient_illness (ipdid,clientid) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ipdid);
			ps.setString(2, clientid);

			result = ps.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = ps.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int addConditionsandProblems(String illnessid, String conditionid, String problemids) {

		int result = 0;

		try {

			String sql = "insert into apm_illness_condition (illnessid, conditionid, problemid) values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, illnessid);
			ps.setString(2, conditionid);
			ps.setString(3, problemids);

			result = ps.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = ps.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Diagnosis> getIpdIllnessCondition(int illnessid) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id, conditionid, problemid from apm_illness_condition where illnessid=" + illnessid+ "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setConditionid(rs.getString(2));
				diagnosis.setProblemid(rs.getString(3));
				diagnosis.setIllnessid(String.valueOf(illnessid));
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Diagnosis> getIntervetionlist(String id) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,intervention from apm_diagnosis_intervention where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setIntervention(rs.getString(2));
				list.add(diagnosis);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int addConsultationNote(Bed bed, String history, String currentdate) {

		int result = 0;

		try {

			String sql = "insert into apm_consultation_note (patientid, appointmentid, history, practitionerid, lastmodified, condition_id) values (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bed.getClientid());
			ps.setString(2, "0");
			ps.setString(3, history);
			ps.setString(4, bed.getPractitionerid());
			ps.setString(5, currentdate);
			ps.setString(6, bed.getConditionid());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Diagnosis> getDiagnosisIntervention(String problem_id) {

		ArrayList<Diagnosis> list=new ArrayList<Diagnosis>();
		
		try {
			 String sql="select diagnosis_id,intervention from apm_diagnosis_intervention where diag_problem_id="+problem_id+"";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 
				     Diagnosis diagnosis=new Diagnosis();
				     diagnosis.setDiagnosis_id(getNameOfDiagnosisFromId(rs.getString(1)));
				     diagnosis.setProblem_name(getNameOfDiagnosisProblemNameFromId(problem_id));
				     diagnosis.setIntervention(rs.getString(2));
				     diagnosis.setProblemid(problem_id);
				     list.add(diagnosis);
				 
			 }
			 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}

	public String getDiagnosisNamefromProblemID(String problem_id) {

		String res="";
		try {
			
			String sql="select diagnosis_id from apm_dignosis_problem where id="+problem_id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				 res=getNameOfDiagnosisFromId(rs.getString(1)); 
				
			}
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
			
		return res;
	}
	
	
	public boolean isProblemExists(int condition_id) {
		
	    boolean flag= false;	
		try {
			
		   String sql="select id from apm_dignosis_problem where dignosis_id="+condition_id+"";	
           PreparedStatement ps=connection.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
		   if(rs.next()){
			    return true;
		   }else {
			   return false;
		   }
		} catch (Exception e) {

		   e.printStackTrace();
		}
	   
		return flag;
	}
	
	public ArrayList<Diagnosis> getAllDiagnosis() {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,name from apm_diagnosis";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setName(rs.getString(2));
				
				/*if(isProblemExists(diagnosis.getId())) {
					list.add(diagnosis);
				}*/
				
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	
	// Adarsh 
	

	public ArrayList<Diagnosis> getAllAutoDiagnosisList(ArrayList<Bed> ipdConditionids) {
		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		  try {

		   String sql = "select id,name from apm_diagnosis";
		   PreparedStatement ps = connection.prepareStatement(sql);
		   ResultSet rs = ps.executeQuery();
		   while (rs.next()) {

		    Diagnosis diagnosis = new Diagnosis();
		    diagnosis.setId(rs.getInt(1));
		    diagnosis.setName(rs.getString(2));
		    
		    if(isProblemExists(diagnosis.getId())) {
		     for(Bed bed: ipdConditionids){
		      if(bed.getConditionid().equals(String.valueOf(diagnosis.getId()))){
		       diagnosis.setAutodiagnosis(1);
		       break;
		      }
		     }
		     
		     
		     list.add(diagnosis);
		    }
		   }
		   

		  } catch (Exception e) {

		   e.printStackTrace();
		  }

		  return list;
		 }

	public ArrayList<Diagnosis> getAllClinicalDiagnosisList() {
		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();

		try {

			String sql = "select id,name from clinical_notes_master  order by name";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	private boolean isClinicalProblemExists(int id) {
		boolean flag= false;	
		try {
			
		   String sql="select id from clinical_notes_problem where clinicalnotesid="+id+"";	
           PreparedStatement ps=connection.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
		   if(rs.next()){
			    return true;
		   }else {
			   return false;
		   }
		} catch (Exception e) {

		   e.printStackTrace();
		}
	   
		return flag;
	}

	public ArrayList<Diagnosis> getClinicalNotesProblemList(String id) {
		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();
		try {
			String sql = "select id,problemname from clinical_notes_problem where clinicalnotesid='" + id + "' order by problemname";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setProblem_name(rs.getString(2));
				diagnosis.setDiagnosis_id(id);
				list.add(diagnosis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getClinicalNotesName(String id) {
		String name = "";
		try {

			String sql = "select name from clinical_notes_master where id='" + id + "'";
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

	public ArrayList<Diagnosis> getClinicalNotesInterventionList(String id) {
		ArrayList<Diagnosis> list=new ArrayList<Diagnosis>();
		
		try {
			 String sql="select id,interventioname from clinical_notes_intervention where problemid='"+id+"' order by interventioname";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				     Diagnosis diagnosis=new Diagnosis();
				     diagnosis.setId(rs.getInt(1));
				     diagnosis.setIntervention(rs.getString(2));
				     list.add(diagnosis);
			 }
			 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}

	public String getClinicalNotesProblemName(String id) {
		String name = "";
		try {

			String sql = "select problemname from clinical_notes_problem where id='" + id + "'";
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

	public String getClinicalNotesIntervation(String id) {
		String name = "";
		try {

			String sql = "select interventioname from clinical_notes_intervention where id='" + id + "'";
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

	public ArrayList<Diagnosis> getAllClinicalNotes(String search) {
		ArrayList<Diagnosis> list= new ArrayList<Diagnosis>();
		try {
			String sql = "select id,name from clinical_notes_master where name like '%"+search+"%' order by name";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
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

	public ArrayList<Diagnosis> getAllClinicalNotesProblemSearch(String search, String parentid) {
		
		ArrayList<Diagnosis> list= new ArrayList<Diagnosis>();
		try {

			String sql = "select id,problemname from clinical_notes_problem where clinicalnotesid='" + parentid + "' and problemname like '%"+search+"%' order by problemname";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setProblem_name(rs.getString(2));
				diagnosis.setDiagnosis_id(parentid);
				list.add(diagnosis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Diagnosis> getAllClinicalNotesIntervationSearch(String search, String parentid) {
		ArrayList<Diagnosis> list= new ArrayList<Diagnosis>();
		try {
			 String sql="select id,interventioname from clinical_notes_intervention where problemid='"+parentid+"' and interventioname like '%"+search+"%' order by interventioname";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				     Diagnosis diagnosis=new Diagnosis();
				     diagnosis.setId(rs.getInt(1));
				     diagnosis.setIntervention(rs.getString(2));
				     list.add(diagnosis);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		}

	public int checkExixstDiagnosis(String name) {
		int res=0;
		try {
			String sql="select id from apm_diagnosis where name='"+name+"'";
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
	

	
}
