package com.apm.Ipd.eu.bi;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Ipd.eu.entity.Bed;

public interface IpdLogDAO {

	ArrayList<Bed> getAdmissionlogDAO(String clientId);

	ArrayList<Bed> getDischargeLog(String clientId);

	ArrayList<Bed> getBedChangeLogList(String clientId,String admissionid);

	String getDischargeBedId(String admissionid);

	boolean isBedChanged(String admissionid,String clientid);

	String getDischargeDate(int ipdid);

	ArrayList<Client> getPatientEditLog(String clientId);

	ArrayList<Bed> getBedChangeLogListNew(String clientid, String selectedid);

	Vector<Bed> getfakepatientBedLogList(String clientid, String string, String dbname);

	int updateBedlogdata(int fake_patientid, String newcommencing, String new_lastmodified,
			String new_selectedshiftdata, int id, int id2, int chrai_ipdid, String dbname);

	Bed getbedlodDataByid(int id, String dbname);

	int insertfakepatntBedlog(Bed bed1);

}
