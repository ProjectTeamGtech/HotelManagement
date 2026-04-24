package com.apm.Diagnosis.eu.bi;

import java.util.ArrayList;

import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.common.utils.Pagination;

public interface DiagnosisDAO {
	
   public ArrayList<Diagnosis> getAllDiagnosisList();

   public int getTotalDiagnosisNameCount();
   
   public ArrayList<Diagnosis> getAllDiagnosisNameList(Pagination pagination);
   
   public int addDiagnosisName(Diagnosis diagnosis);



   public int addProblemName(Diagnosis diagnosis);

   public int addInterventionName(Diagnosis diagnosis);	
   
   public int getTotalInterventionCount(String searchText);

   public ArrayList<Diagnosis> getAllDiagnosisList(Pagination pagination);

   public Diagnosis getDiagnosisName(String selectedid);

   public int updateDiagnosisName(Diagnosis diagnosis);

   public int deleteDiagnosisName(String selectedid);

   public int getTotalDiagnosisProblemCount(String searchText);

   public ArrayList<Diagnosis> getAllDiagnosisProblemList(Pagination pagination,String searchText);
   public ArrayList<Diagnosis> getAllDiagnosisProblemList();

   public Diagnosis getDiagnosisProblem(String selectedid);

   public ArrayList<Diagnosis> getProblemList(String id);

  public String getNameOfDiagnosisFromId(String id);

  public int updateDiagnosisProblem(Diagnosis diagnosis);

  public int deleteDiagnosisProblem(String selectedid);

  public ArrayList<Diagnosis> getAllDiagnosisInterventionList(Pagination pagination, String searchText);

  public Diagnosis getDiagnosisProblemIntervention(String selectedid);

  public int updateDiagnosisProblemIntervention(Diagnosis diagnosis);

  public int deleteDiagnosisProblemIntervention(String selectedid);

  public String getNameOfDiagnosisProblemNameFromId(String id);
  

public int addPatientIllness(String ipdid, String clientid);

public int addConditionsandProblems(String illnessid, String conditionid,
		String problemids);

public ArrayList<Diagnosis> getIpdIllnessCondition(int illnessid);

public ArrayList<Diagnosis> getIntervetionlist(String str);

public int addConsultationNote(Bed bed, String string, String currentdate);

public ArrayList<Diagnosis> getDiagnosisIntervention(String problem_id);

public String getDiagnosisNamefromProblemID(String problem_id);
public ArrayList<Diagnosis> getAllConditionList();

public ArrayList<Diagnosis> getAllDiagnosis();
    
public ArrayList<Diagnosis> getAllAutoDiagnosisList(ArrayList<Bed> ipdConditionids);

public ArrayList<Diagnosis> getAllDiagnosisList(String id);

public ArrayList<Diagnosis> getAllClinicalDiagnosisList();

public ArrayList<Diagnosis> getClinicalNotesProblemList(String id);

public String getClinicalNotesName(String id);

public ArrayList<Diagnosis> getClinicalNotesInterventionList(String id);

public String getClinicalNotesProblemName(String id);

public String getClinicalNotesIntervation(String id);

public ArrayList<Diagnosis> getAllClinicalNotes(String search);

public ArrayList<Diagnosis> getAllClinicalNotesProblemSearch(String search,String parentid);

public ArrayList<Diagnosis> getAllClinicalNotesIntervationSearch(String search,String parentid);

public int checkExixstDiagnosis(String name);
}
