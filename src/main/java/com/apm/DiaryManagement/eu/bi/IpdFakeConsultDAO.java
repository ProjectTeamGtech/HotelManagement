package com.apm.DiaryManagement.eu.bi;

import java.util.Vector;

import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.web.common.helper.LoginInfo;

public interface IpdFakeConsultDAO {

	Vector<IpdFakeConsult> getDoctorwithsepecialitylist();

	Vector<IpdFakeConsult> getIpdfakelist(String ipdpatientcount, LoginInfo loginInfo);

	String getspecialityid(String docid);

	IpdFakeConsult getAuriusData(String docid, String specialityid);

	int updateIpdData(String newadmissiondate, IpdFakeConsult ipdFakeConsult, int aclientid, IpdFakeConsult auipdFake, String dbname);

	Bed geteditIpdData(int aclientid, String dbname, int i);

	int addIpdFakedata(Bed bed, String newadmissiondate, int treatmentid, String docid);

	int saveTreatmentEpisode(TreatmentEpisode treatmentEpisode);

	Bed getDischrgedata(String treatmentepisodeid, String dbname);

	int updateTreatmentEpisodeDischargeForm(String dischargedate, Bed bed, int treatmentid);

	int updatePatientIpdStatusbyId(LoginInfo loginInfo);

	IpdFakeConsult getMaxAvailableDate(int id);

	String getnamebyDocid(String docid);

	int saveFinalDiagnosis(int ipdid, Bed bed, int treatmentid);

	int updateAdmissionid(String opdid, int ipdid);

	int saveteporaryfakedata(String userid, String docid, String patientcount, String currdate);

	Vector<IpdFakeConsult> getfakeIpdTempData(String userid);

	int deleteIpdTempData(String userid);

	int saveOpdfaketempdata(String docid, String patientcount, String userid, String commencing);

	Vector<OpdConsult> getfakeopdtempdata(String userid);

	int deleteOpdTempData(String userid);

	

	

}
