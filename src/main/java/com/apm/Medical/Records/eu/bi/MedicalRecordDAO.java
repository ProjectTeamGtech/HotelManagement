package com.apm.Medical.Records.eu.bi;

import java.util.ArrayList;

import com.apm.Medical.Records.eu.entity.MedicalRecord;
import com.apm.common.utils.Pagination;

public interface MedicalRecordDAO {

	int saveMedicalRecord(MedicalRecord medicalRecord);

	int getTotalMedicakRecordsCount(String searchText, String string);

	ArrayList<MedicalRecord> getMedicalRecordList(Pagination pagination, String searchText, String string);

	MedicalRecord getMedicalDetails(int id, MedicalRecord medicalRecord);

	int updateMedicalRecord(MedicalRecord medicalRecord, int idSelected);

	int deleteMedicalRecord(int id, MedicalRecord medicalRecord);

}
