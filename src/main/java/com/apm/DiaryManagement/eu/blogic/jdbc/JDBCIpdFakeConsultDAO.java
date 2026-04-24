package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.IpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.bi.OPDConsultDAO;
import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;


public class JDBCIpdFakeConsultDAO implements IpdFakeConsultDAO{

	Connection connection = null;
	
	public JDBCIpdFakeConsultDAO(Connection connection){
		this.connection = connection;
	}

	@Override
	public Vector<IpdFakeConsult> getDoctorwithsepecialitylist() {
		// TODO Auto-generated method stub
		
		PreparedStatement pst=null;
		Vector<IpdFakeConsult>list=new Vector<IpdFakeConsult>();
		try {
			String sql="select id,initial,firstname,lastname,discription from apm_user where usertype=4 and islogin=1  and (jobtitle='Practitioner' or hasdiary=1) order by firstname asc";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				IpdFakeConsult ipdConsult=new IpdFakeConsult();
				String speciality=getSpecialityName(rs.getString(5));
				ipdConsult.setId(rs.getInt(1));
				ipdConsult.setDoctor(rs.getString(3)+""+rs.getString(4));
				ipdConsult.setSpeciality(speciality);
				
				if(rs.getString(2)==null || rs.getString(2).equals(""))
				{
					ipdConsult.setDoctor(rs.getString(3) + " " +rs.getString(4)+"  ("+(speciality)+")");

				}
				else{
					ipdConsult.setDoctor(rs.getString(2) + " " + rs.getString(3) + " " +rs.getString(4)+"  ("+(speciality)+")");

				}
				list.add(ipdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getSpecialityName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discipline FROM apm_discipline where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}

	@Override
	public Vector<IpdFakeConsult> getIpdfakelist(String ipdpatientcount,LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		Vector<IpdFakeConsult>list=new Vector<IpdFakeConsult>();
		String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
		
		try {
			
			String sql="select id,concat(title,' ',firstname,' ',middlename,' ',surname),regdate from apm_patient where fake_status=1 and opd=0 and f_userid='"+loginInfo.getUserId()+"' and bdate like '%"+cdate+"%' limit 0,"+ipdpatientcount+" ";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()){
				
				IpdFakeConsult ipdConsult=new IpdFakeConsult();
				
				ipdConsult.setId(rs.getInt(1));
				ipdConsult.setPatientName(rs.getString(2));
				ipdConsult.setRegdate(rs.getString(3));
				
				list.add(ipdConsult);
				
				
			}
			
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public String getspecialityid(String docid) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discription from apm_user where  id = "+docid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}

	@Override
	public IpdFakeConsult getAuriusData(String docid, String specialityid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		IpdFakeConsult ipdConsult=new IpdFakeConsult();
		
		try {
			
			String sql="select ipd_addmission_form.id,clientid,ipd_addmission_form.admissiondsate from orises.ipd_addmission_form inner join orises.apm_user on apm_user.id=ipd_addmission_form.practitionerid where discription="+specialityid+" and taken!=1 limit 0,1; ";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			
            while(rs.next()){
				
				ipdConsult.setId(rs.getInt(1));
				ipdConsult.setClientid(rs.getString(2));
				ipdConsult.setAdmissiondate(rs.getString(3));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ipdConsult;
	}

	@Override
	public int updateIpdData(String admissiondate,IpdFakeConsult ipdFakeConsult,int aclientid,IpdFakeConsult auipdFake, String dbname) {
		// TODO Auto-generated method stub
		int res =0;
		try {
			
			//String sql="update "+dbname+".ipd_addmission_form set clientid="+ipdFakeConsult.getId()+", admissiondsate='"+admissiondate+"',taken=1,au_oldclient_id='"+auipdFake.getClientid()+"',au_admission_id='"+auipdFake.getId()+"' where id="+aclientid+" ";
			String sql="update "+dbname+".ipd_addmission_form set taken=1,au_oldclient_id='"+auipdFake.getClientid()+"',au_admission_id='"+auipdFake.getId()+"' where id="+aclientid+" ";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Bed geteditIpdData(int aclientid, String dbname,int id) {
		// TODO Auto-generated method stub
		

		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select clientid, practitionerid, conditionid, department, refferedby, wardid, bedid, tpid, status, ");
		sql.append("addmissionfor, reimbursment, secndryconsult, mlcno, admissiondetails, suggestedtrtment, hosp, package, ");
		sql.append("chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid, ");
		sql.append("suggestoper, systdepartment, fpcondition, fpnotest, nauseacondition, nauseanotes, fnucondition, ");
		sql.append("fnunotes, smcondition, smnotes, chestpaincond, chestpainnotes, dimvisioncond, dimvisionnotes, hgucondition, ");
		sql.append("hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, constipationcond, constpationnotes, ");
		sql.append("specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, hematurianotes, bmcondition, bmnotes, ");
		sql.append("oliguriacondi, oligurianotes, pstgucondi, pstgunotes, bmecondition, bmenotes, tnecondition, tnenotes, ");
		sql.append("weaknesscondi, weaknessnotes, ihcondition, ihnotes, admissiondsate,reason_admission,early_investigation,allergies, ");
		sql.append("alcohal, drugs, other_medication, tobacocon, tobaconotes, smoking, ");
		sql.append("menarche, lmp, llmp, pmc, cycle_length, duration_flow, amount_flow, ");
		sql.append("dysmenorrhea, dyspareunia, hopassingclots, white_disc_itching, intercourse_freq, ");
		sql.append("galactorea, hocontrarec, rubella_vaccine, menopause, nulligravida, ");
		sql.append("current_pregnent, iud, liveboys, stillbirths, abortions, death_child, ");
		sql.append("parity_abortion_notes, p, a, l, d,livegirls,mlcrefdoctor,addmited_by_userid,casualty,ipdseqno,surgicalnote,ismlc,speciality ,birthhist, diethist, developmenthist, emmunizationhist,head_circumference, mid_arm_circumference,length,wt_addmission, wt_discharge,mlcabrivationid,  ");
		sql.append("giddinesscondition,giddinessnotes,unconcondition,unconnotes,referenceid ,admissiondsate,kunal_final_diagnosis_text,kunal_manual_medicine_text ,gstureage,wtonbirth,maternal_history,perinatal_history,reasonlamadama,ipdabrivationid ");
		sql.append(" from "+dbname+".ipd_addmission_form where id='"+aclientid+"' ");
		
		try{
			
			ClientDAO clientDAO= new JDBCClientDAO(connection); 
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				bed.setIpdnewid(Integer.toString(aclientid));
				bed.setClientid(""+id);
	    		bed.setPractitionerid(rs.getString(2));
	    		bed.setConditionid(rs.getString(3));
	    		bed.setDepartment(rs.getString(4));
	    		bed.setRefferedby(rs.getString(5));
	    		bed.setWardid(rs.getString(6));
	    		bed.setBedid(rs.getString(7));
	    		bed.setTpid(rs.getString(8));
	    		bed.setStatus(rs.getString(9));
	    		bed.setAddmissionfor(rs.getString(10));
	    		bed.setReimbursment(rs.getString(11));
	    		bed.setSecndryconsult(rs.getString(12));
	    		bed.setMlcno(rs.getString(13));
	    		bed.setMlccase(rs.getString("ismlc"));
	    		bed.setAdmissiondetails(rs.getString(14));
	    		bed.setSuggestedtrtment(rs.getString(15));
	    		bed.setTreatmenthistory(rs.getString(15));
	    		
	    		bed.setHosp(rs.getString(16));
	    		bed.setPackagename(rs.getString(17));
	    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
	    		bed.setChiefcomplains(rs.getString(18));
	    		bed.setPresentillness(rs.getString(19));
	    		bed.setPasthistory(DateTimeUtils.isNull(rs.getString(20)));
	    		bed.setPersonalhist(DateTimeUtils.isNull(rs.getString(21)));
	    		bed.setFamilyhist(DateTimeUtils.isNull(rs.getString(22)));
	    		bed.setOnexamination(DateTimeUtils.isNull(rs.getString(23)));
	    		bed.setTreatmentepisodeid(DateTimeUtils.isNull(rs.getString(24)));
	    		
	    		bed.setSuggestoper(rs.getString(25));
	    		bed.setSystdepartment(rs.getString(26));
	    		bed.setFpcondition(rs.getString(27));
	    		bed.setFpnotest(rs.getString(28));
	    		bed.setNauseacondition(rs.getString(29));
	    		bed.setNauseanotes(rs.getString(30));
	    		bed.setFnucondition(rs.getString(31));
	    		bed.setFnunotes(rs.getString(32));
	    		bed.setSmcondition(rs.getString(33));
	    		bed.setSmnotes(rs.getString(34));
	    		bed.setChestpaincond(rs.getString(35));
	    		bed.setChestpainnotes(rs.getString(36));
	    		bed.setDimvisioncond(rs.getString(37));
	    		bed.setDimvisionnotes(rs.getString(38));
	    		
	    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
	    		bed.setHgucondition(rs.getString(39));
	    		bed.setHgunotes(rs.getString(40));
	    		bed.setHmcondition(rs.getString(41));
	    		bed.setHmnotes(rs.getString(42));
	    		bed.setJointpaincond(rs.getString(43));
	    		bed.setJointpainnotes(rs.getString(44));
	    		
	    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
	    		bed.setConstipationcond(rs.getString(45));
	    		bed.setConstpationnotes(rs.getString(46));
	    		bed.setSpecialnotes(rs.getString(47));
	    		bed.setEdemafeetcondi(rs.getString(48));
	    		bed.setEdemafeetnotes(rs.getString(49));
	    		bed.setHematuriacondi(rs.getString(50));
	    		bed.setHematurianotes(rs.getString(51));
	    		
	    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
	    		bed.setBmcondition(rs.getString(52));
	    		bed.setBmnotes(rs.getString(53));
	    		bed.setOliguriacondi(rs.getString(54));
	    		bed.setOligurianotes(rs.getString(55));
	    		bed.setPstgucondi(rs.getString(56));
	    		bed.setPstgunotes(rs.getString(57));
	    		
	    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
	    		bed.setBmecondition(rs.getString(58));
	    		bed.setBmenotes(rs.getString(59));
	    		bed.setTnecondition(rs.getString(60));
	    		bed.setTnenotes(rs.getString(61));
	    		bed.setWeaknesscondi(rs.getString(62));
	    		bed.setWeaknessnotes(rs.getString(63));
	    		bed.setIhcondition(rs.getString(64));
	    		bed.setIhnotes(rs.getString(65));
	    		
	    		/*if(rs.getString(66)!=null){
	    			//String dbDate = DateTimeUtils.getDBDate(rs.getString(66));
	    			bed.setAdmissiondate(rs.getString(66));
	    		}else{
	    			bed.setAdmissiondate("");
	    		}*/
	    		bed.setAdmissiondate(DateTimeUtils.isNull(rs.getString(66)));
	    		if(!DateTimeUtils.isNull(bed.getAdmissiondate()).equals("")){
	    			//Client client= new Client();
	    			//client= clientDAO.getClientDetails(bed.getClientid());
	    			String dob = clientDAO.getDOBOfPatient(bed.getClientid());
	    			String admndt[]= bed.getAdmissiondate().split(" ");
	    			String ageonadmn= DateTimeUtils.getAge1onAddmission(dob,admndt[0]);
	    			bed.setAgeonAdmn(ageonadmn);
	    		}else{
	    			bed.setAgeonAdmn("");
	    		}
	    		
	    		bed.setAdmission_reason(rs.getString(67));
	    		bed.setEarlierinvest(rs.getString(68));
	    		bed.setAlergies(rs.getString(69));
	    		
	    		// gynic details 
	    		//alcohal, drugs, other_medication, tobacocon, tobaconotes, smoking,
	    		bed.setAlcohal(rs.getString(70));
	    		bed.setDrugs(rs.getString(71));
	    		bed.setOther_medication(rs.getString(72));
	    		bed.setTobaco(rs.getString(73));
	    		bed.setTobaconotes(rs.getString(74));
	    		bed.setSmoking(rs.getString(75));
	    		
	    		//menarche, lmp, llmp, pmc, cycle_length, duration_flow, amount_flow,
	    		bed.setAge_menarche(rs.getString(76));
	    		bed.setLmp(rs.getString(77));
	    		bed.setLlmp(rs.getString(78));
	    		bed.setPmc(rs.getString(79));
	    		bed.setCycle_length(rs.getString(80));
	    		bed.setDuration_flow(rs.getString(81));
	    		bed.setAmount_flow(rs.getString(82));
	    		
	    		//dysmenorrhea, dyspareunia, hopassingclots, white_disc_itching, intercourse_freq,
	    		bed.setDysmenorrhea(rs.getString(83));
	    		bed.setDyspareunia(rs.getString(84));
	    		bed.setHopassing_clots(rs.getString(85));
	    		bed.setWhite_disc_itching(rs.getString(86));
	    		bed.setIntercourse_freq(rs.getString(87));
	    		
	    		//galactorea, hocontrarec, rubella_vaccine, menopause, nulligravida,
	    		bed.setGalactorrea(rs.getString(88));
	    		bed.setHo_contraception(rs.getString(89));
	    		bed.setRubella_vaccine(rs.getString(90));
	    		bed.setMenopause(rs.getString(91));
	    		bed.setNulligravida(rs.getString(92));
	    		
	    		//current_pregnent, iud, liveboys, stillbirths, abortions, death_child
	    		bed.setCurrent_pregnent(rs.getString(93));
	    		bed.setIud(rs.getString(94));
	    		bed.setLive_boys(rs.getString(95));
	    		bed.setStillbirths(rs.getString(96));
	    		bed.setAbortions(rs.getString(97));
	    		bed.setDeath_children(rs.getString(98));
	    		
	    		//parity_abortion_notes,p,a,l,d
	    		bed.setParity_abortion_notes(rs.getString(99));
	    		bed.setP(rs.getString(100));
	    		bed.setA(rs.getString(101));
	    		bed.setL(rs.getString(102));
	    		bed.setD(rs.getString(103));
	    		bed.setLive_girls(rs.getString(104));
	    		bed.setMlcrefdoctor(rs.getString(105));
	    		bed.setAddmitedbyuserid(rs.getString(106));
	    		bed.setAction(rs.getString(107));
	    		bed.setIpdseqno(rs.getString(108));
	    		bed.setSurgicalnotes(rs.getString(109));
	    		bed.setMlccase(rs.getString(110));
	    		bed.setSpeciality(rs.getString(111));
	    		bed.setBirthhist(rs.getString(112));
	    		bed.setDiethist(rs.getString(113));
	    		bed.setDevelopmenthist(rs.getString(114));
	    		bed.setEmmunizationhist(rs.getString(115));
	    		bed.setHeadcircumference(DateTimeUtils.isNull(rs.getString(116)));
	    		bed.setMidarmcircumference(DateTimeUtils.isNull(rs.getString(117)));
	    		bed.setLength(DateTimeUtils.isNull(rs.getString(118)));
	    		bed.setWtaddmission(DateTimeUtils.isNull(rs.getString(119)));
	    		bed.setWtdischarge(DateTimeUtils.isNull(rs.getString(120)));
	    		bed.setMlcabrivationid(rs.getString(121));
	    		
	    		//giddinesscondition,giddinessnotes,unconcondition,unconnotes
	    		bed.setGiddinesscondition(DateTimeUtils.isNull(rs.getString(122)));
	    		bed.setGiddinessnotes(DateTimeUtils.isNull(rs.getString(123)));
	    		bed.setUnconcondition(DateTimeUtils.isNull(rs.getString(124)));
	    		bed.setUnconnotes(DateTimeUtils.isNull(rs.getString(125)));
	    		bed.setReferenceid(DateTimeUtils.isNull(rs.getString(126)));
	    		bed.setNewadmndate(DateTimeUtils.isNull(rs.getString(127)));
	    		bed.setKunal_final_diagnosis_text(DateTimeUtils.isNull(rs.getString(128)));
	    		bed.setKunal_manual_medicine_text(DateTimeUtils.isNull(rs.getString(129)));
	    		bed.setGstureage(DateTimeUtils.isNull(rs.getString(130)));
	    		bed.setWtonbirth(DateTimeUtils.isNull(rs.getString(131)));
	    		bed.setMaternal_history(DateTimeUtils.isNull(rs.getString(132)));
	    		bed.setPerinatal_history(DateTimeUtils.isNull(rs.getString(133)));
	    		bed.setReasonlamadama(DateTimeUtils.isNull(rs.getString(134)));
	    		bed.setIpdabrivationid(DateTimeUtils.isNull(rs.getString(135)));
	    		/*if(bed.getHeadcircumference()==null||bed.getHeadcircumference().equals("")){
	    			bed.setHeadcircumference("");
	    		}
	    		if(bed.getMidarmcircumference()==null||bed.getMidarmcircumference().equals("")){
	    			bed.setMidarmcircumference("");
	    		}
	    		if(bed.getLength()==null||bed.getLength().equals("")){
	    			bed.setLength("");
	    		}
	    		if(bed.getWtaddmission()==null||bed.getWtaddmission().equals("")){
	    			bed.setWtaddmission("");
	    		}
	    		if(bed.getWtdischarge()==null||bed.getWtdischarge().equals("")){
	    			bed.setWtdischarge("");
	    		}*/
	    		
	    		if(bed.getEmmunizationhist()==null){
	    			bed.setEmmunizationhist("<br>");
	    		}
	    		
	    		if(bed.getBirthhist()==null){
	    			bed.setBirthhist("<br>");
	    		}
	    		
	    		if(bed.getDiethist()==null){
	    			bed.setDiethist("<br>");
	    		}
	    		
	    		if(bed.getDevelopmenthist()==null){
	    			bed.setDevelopmenthist("<br>");
	    		}
	    		if(bed.getSuggestedtrtment()==null){
	    			bed.setSuggestedtrtment("");
	    		}
	    		
	    		//lokesh
	    		if(!bed.getEmmunizationhist().equals("<br>")||!bed.getBirthhist().equals("<br>")||!bed.getDiethist().equals("<br>")||!bed.getDevelopmenthist().equals("<br>")){
	    			bed.setPeditric(true);
	    		}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return bed;
	
	}

	@Override
	public int addIpdFakedata(Bed bed,String admissiondate,int treatmentid,String docid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ipd_addmission_form ");
		sql.append("(clientid, practitionerid, conditionid, department, refferedby, wardid, bedid, tpid, status, ");
		sql.append("addmissionfor, reimbursment, secndryconsult, mlcno, admissiondetails, suggestedtrtment, hosp, package, ");
		sql.append("chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid, ");
		sql.append("suggestoper, systdepartment, fpcondition, fpnotest, nauseacondition, nauseanotes, fnucondition, ");
		sql.append("fnunotes, smcondition, smnotes, chestpaincond, chestpainnotes, dimvisioncond, dimvisionnotes, hgucondition, ");
		sql.append("hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, constipationcond, constpationnotes, ");
		sql.append("specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, hematurianotes, bmcondition, bmnotes, ");
		sql.append("oliguriacondi, oligurianotes, pstgucondi, pstgunotes, bmecondition, bmenotes, tnecondition, tnenotes, ");
		sql.append("weaknesscondi, weaknessnotes, ihcondition, ihnotes, admissiondsate, lastmodified,reason_admission,early_investigation,allergies,speciality,setstdcharge, ");
		sql.append("alcohal, drugs, other_medication, tobacocon, tobaconotes, smoking, ");
		sql.append("menarche, lmp, llmp, pmc, cycle_length, duration_flow, amount_flow, ");
		sql.append("dysmenorrhea, dyspareunia, hopassingclots, white_disc_itching, intercourse_freq, ");
		sql.append("galactorea, hocontrarec, rubella_vaccine, menopause, nulligravida, ");
		sql.append("current_pregnent, iud, liveboys, stillbirths, abortions, death_child, ");
		sql.append("parity_abortion_notes, p, a, l, d,livegirls,mlcrefdoctor,addmited_by_userid,casualty,ipdseqno,ismlc,referenceid,birthhist, diethist, developmenthist, emmunizationhist,surgicalnote,head_circumference, mid_arm_circumference,length,wt_addmission, wt_discharge,mlcabrivationid,ipdabrivationid,mbbsseqno ) ");
		
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,");
		sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		
		OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			
			preparedStatement.setString(1,bed.getClientid());
			preparedStatement.setString(2,docid);
			preparedStatement.setString(3,bed.getConditionid());
			preparedStatement.setString(4,bed.getDepartment());
			preparedStatement.setString(5,bed.getRefferedby());
			preparedStatement.setString(6,bed.getWardid());
			preparedStatement.setString(7,bed.getBedid());
			preparedStatement.setString(8,bed.getTpid());
			preparedStatement.setString(9,bed.getStatus());
			preparedStatement.setString(10,bed.getAddmissionfor());
			preparedStatement.setString(11,bed.getReimbursment());
			preparedStatement.setString(12,bed.getSecndryconsult());
			preparedStatement.setString(13,bed.getMlcno());
			preparedStatement.setString(14,bed.getAdmissiondetails());
			preparedStatement.setString(15,bed.getSuggestedtrtment());
			preparedStatement.setString(16,bed.getHosp());
			preparedStatement.setString(17,bed.getPackagename());
			preparedStatement.setString(18,bed.getChiefcomplains());
			preparedStatement.setString(19,bed.getPresentillness());
			preparedStatement.setString(20,bed.getPasthistory());
			preparedStatement.setString(21,bed.getPersonalhist());
			preparedStatement.setString(22,bed.getFamilyhist());
			preparedStatement.setString(23,bed.getOnexamination());
			preparedStatement.setString(24,Integer.toString(treatmentid));
			
			preparedStatement.setString(25,bed.getSuggestoper());
			preparedStatement.setString(26,bed.getSystdepartment());
			preparedStatement.setString(27,bed.getFpcondition());
			preparedStatement.setString(28,bed.getFpnotest());
			preparedStatement.setString(29,bed.getNauseacondition());
			preparedStatement.setString(30,bed.getNauseanotes());
			preparedStatement.setString(31,bed.getFnucondition());
			preparedStatement.setString(32,bed.getFnunotes());
			preparedStatement.setString(33,bed.getSmcondition());
			preparedStatement.setString(34,bed.getSmnotes());
			preparedStatement.setString(35,bed.getChestpaincond());
			preparedStatement.setString(36,bed.getChestpainnotes());
			preparedStatement.setString(37,bed.getDimvisioncond());
			preparedStatement.setString(38,bed.getDimvisionnotes());
			
			//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
			preparedStatement.setString(39,bed.getHgucondition());
			preparedStatement.setString(40,bed.getHgunotes());
			preparedStatement.setString(41,bed.getHmcondition());
			preparedStatement.setString(42,bed.getHmnotes());
			preparedStatement.setString(43,bed.getJointpaincond());
			preparedStatement.setString(44,bed.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
			preparedStatement.setString(45,bed.getConstipationcond());
			preparedStatement.setString(46,bed.getConstpationnotes());
			preparedStatement.setString(47,bed.getSpecialnotes());
			preparedStatement.setString(48,bed.getEdemafeetcondi());
			preparedStatement.setString(49,bed.getEdemafeetnotes());
			preparedStatement.setString(50,bed.getHematuriacondi());
			preparedStatement.setString(51,bed.getHematurianotes());
			
			//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
			preparedStatement.setString(52,bed.getBmcondition());
			preparedStatement.setString(53,bed.getBmnotes());
			preparedStatement.setString(54,bed.getOliguriacondi());
			preparedStatement.setString(55,bed.getOligurianotes());
			preparedStatement.setString(56,bed.getPstgucondi());
			preparedStatement.setString(57,bed.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
			preparedStatement.setString(58,bed.getBmecondition());
			preparedStatement.setString(59,bed.getBmenotes());
			preparedStatement.setString(60,bed.getTnecondition());
			preparedStatement.setString(61,bed.getTnenotes());
			preparedStatement.setString(62,bed.getWeaknesscondi());
			preparedStatement.setString(63,bed.getWeaknessnotes());
			preparedStatement.setString(64,bed.getIhcondition());
			preparedStatement.setString(65,bed.getIhnotes());
			
			preparedStatement.setString(66, admissiondate);  //date
			preparedStatement.setString(67, admissiondate);  //date
    		
			//early investi and admisiion_reason
			preparedStatement.setString(68, bed.getAdmission_reason());
			preparedStatement.setString(69, bed.getEarlierinvest());
			preparedStatement.setString(70, bed.getAlergies());
			preparedStatement.setString(71, bed.getSpeciality());
			preparedStatement.setString(72, bed.getStdchargesetup());
			
			
			preparedStatement.setString(73, bed.getAlcohal());
			preparedStatement.setString(74, bed.getDrugs());
			preparedStatement.setString(75, bed.getOther_medication());
			preparedStatement.setString(76, bed.getTobaco());
			preparedStatement.setString(77, bed.getTobaconotes());
			preparedStatement.setString(78, bed.getSmoking());
			preparedStatement.setString(79, bed.getAge_menarche());
			preparedStatement.setString(80, bed.getLmp());
			
			preparedStatement.setString(81, bed.getLlmp());
			preparedStatement.setString(82, bed.getPmc());
			preparedStatement.setString(83, bed.getCycle_length());
			preparedStatement.setString(84, bed.getDuration_flow());
			preparedStatement.setString(85, bed.getAmount_flow());
			preparedStatement.setString(86, bed.getDysmenorrhea());
			
			preparedStatement.setString(87, bed.getDysmenorrhea());
			preparedStatement.setString(88, bed.getHopassing_clots());
			preparedStatement.setString(89, bed.getWhite_disc_itching());
			preparedStatement.setString(90, bed.getIntercourse_freq());
			
			preparedStatement.setString(91, bed.getGalactorrea());
			preparedStatement.setString(92, bed.getHo_contraception());
			preparedStatement.setString(93, bed.getRubella_vaccine());
			preparedStatement.setString(94, bed.getMenopause());
			preparedStatement.setString(95, bed.getNulligravida());
			preparedStatement.setString(96, bed.getCurrent_pregnent());
			
			preparedStatement.setString(97, bed.getIud());
			preparedStatement.setString(98, bed.getLive_boys());
			preparedStatement.setString(99, bed.getStillbirths());
			preparedStatement.setString(100, bed.getAbortions());
			preparedStatement.setString(101, bed.getDeath_children());
			preparedStatement.setString(102, bed.getParity_abortion_notes());
			preparedStatement.setString(103, bed.getP());
			preparedStatement.setString(104, bed.getA());
			preparedStatement.setString(105, bed.getL());
			preparedStatement.setString(106, bed.getD());
			preparedStatement.setString(107, bed.getLive_girls());
			preparedStatement.setString(108, bed.getMlcrefdoctor());
			
			//  addmited by which userid
			preparedStatement.setString(109, bed.getAddmitedbyuserid());
			preparedStatement.setString(110, "0");
			//action
			
			//preparedStatement.setInt(111, Integer.parseInt(ipdSeqAbbr) );//maxid
			preparedStatement.setInt(111,(1) );
			preparedStatement.setString(112, bed.getMlccase());
			preparedStatement.setString(113, bed.getReferenceid());
			preparedStatement.setString(114, bed.getBirthhist());
			preparedStatement.setString(115, bed.getDiethist());
			preparedStatement.setString(116, bed.getDevelopmenthist());
			preparedStatement.setString(117, bed.getEmmunizationhist());
			
			preparedStatement.setString(118, bed.getSurgicalnotes());
			//pediatrics
			preparedStatement.setString(119, bed.getHeadcircumference());
			preparedStatement.setString(120, bed.getMidarmcircumference());
			preparedStatement.setString(121, bed.getLength());
			preparedStatement.setString(122, bed.getWtaddmission());
			preparedStatement.setString(123, bed.getWtdischarge());
			preparedStatement.setString(124, bed.getMlcabrivationid());
			preparedStatement.setString(125, bed.getIpdabrivationid());
			String newipdabbr=opdConsultDAO.generateOPDSequenceNewFormat();
			String ipdSeqAbbr = opdConsultDAO.generateLMHIPDSeqForMBBS(admissiondate.split(" ")[0]);
			preparedStatement.setString(126, ipdSeqAbbr);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
					saveAdmissionInParent(outoid);
					saveDischargeForm(bed.getTreatmentepisodeid());
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		
		return outoid;
		
	}
	
	
	private void saveDischargeForm(String treatmentepisodeid) {
		try {
			String sql="INSERT INTO discharge_form (id, clientid, practitionerid, condition_id, treatmentstatus, "
					+ "dschargestatus, dischargedate, lastmodified, dis_initiate_time, dis_initiate_status, "
					+ "dis_form_time, dis_form_status, dis_mdicine_time, dis_mdicine_status, dis_bill_time, dis_bill_status, "
					+ "dis_nursing_time, dis_nursing_status, iscancel) "
					+ "SELECT apm_treatment_episode.id, apm_treatment_episode.clientid, apm_treatment_episode.practitionerid, "
					+ "apm_treatment_episode.condition_id, treatmentstatus, dschargestatus, dischargedate, "
					+ "apm_treatment_episode.lastmodified, dis_initiate_time, dis_initiate_status, dis_form_time, "
					+ "dis_form_status, dis_mdicine_time, dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time, "
					+ "dis_nursing_status, iscancel "
					+ "FROM apm_treatment_episode "
					+ "WHERE id='"+treatmentepisodeid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveAdmissionInParent(int outoid) {
		try {
			String sql="INSERT INTO ipd_addmission_form_new (id, clientid, practitionerid, conditionid, "
					+ "admissiondsate, lastmodified, treatmentepisodeid, speciality, ismlc, ipdseqno, "
					+ "ipdabrivationid, wardid, bedid, department, tpid, mlcno, addmited_by_userid, "
					+ "casualty, mlcabrivationid) "
					+ "SELECT id, clientid, practitionerid, conditionid, admissiondsate, "
					+ "lastmodified, treatmentepisodeid, speciality, ismlc, ipdseqno, ipdabrivationid, "
					+ "wardid, bedid, department, tpid, mlcno, addmited_by_userid, casualty, mlcabrivationid "
					+ " FROM ipd_addmission_form WHERE id='"+outoid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int saveTreatmentEpisode(TreatmentEpisode treatmentEpisode) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_treatment_episode (clientid,clientname,practitionerid,name,ref_date,ref_source,ref_name,ref_contact,ref_letter,payby,spendlimit,sessions,discahrgeletter,startdate,authcode,invoicee,condition_id,urgent,lastmodified,ipdopd,refenddate,empname,approvedamt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		int t = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, treatmentEpisode.getClientId());
			preparedStatement.setString(2, treatmentEpisode.getClientName());
			preparedStatement.setString(3, treatmentEpisode.getDiaryUser());
			preparedStatement.setString(4, treatmentEpisode.getTreatmentEpisodeName());
			preparedStatement.setString(5, DateTimeUtils.getCommencingDate(treatmentEpisode.getReferalDate()));
			preparedStatement.setString(6, treatmentEpisode.getReferralSource());
			preparedStatement.setString(7, treatmentEpisode.getReferralName());
			preparedStatement.setString(8, treatmentEpisode.getReferralContact());
			preparedStatement.setString(9, treatmentEpisode.getReferralLetter());
			preparedStatement.setString(10, treatmentEpisode.getPayby());
			preparedStatement.setString(11, "0");
			preparedStatement.setString(12, "0");
			preparedStatement.setString(13, treatmentEpisode.getDischargeLetter());
			preparedStatement.setString(14, treatmentEpisode.getTreatmentStartDate());
			preparedStatement.setString(15, treatmentEpisode.getAuthorisationCode());
			preparedStatement.setString(16, treatmentEpisode.getInvoicee());
			preparedStatement.setString(17, treatmentEpisode.getCondition());
			preparedStatement.setBoolean(18, treatmentEpisode.isUrgent());
			preparedStatement.setString(19, "0");    //lastmodified
			preparedStatement.setString(20, treatmentEpisode.getIpdopd());
			preparedStatement.setString(21, null);
			preparedStatement.setString(22, treatmentEpisode.getEmpname());
			preparedStatement.setString(23, treatmentEpisode.getApprovedLimit());
			//preparedStatement.setString(17, "1");
			
			result = preparedStatement.executeUpdate();
			
			if(result>0){
				
				  ResultSet rs=preparedStatement.getGeneratedKeys();
				  while(rs.next()){
					    result=rs.getInt(1);
				  }
			  	
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Bed getDischrgedata(String treatmentepisodeid,String dbname) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		String sql = "SELECT treatmentstatus, outcomes, dschargestatus,dischargedate,hospcourse, disadvnotes,dis_extra_note,findingondischarge,treatmentgiven,investigation,otNotes,operative_procedure, anesthesia, surgeon, anesthesiologist,dischargebyid,death_note,emergencydetail,dis_extra_note,ot_manual_surg,ot_manual_anesthesio,endeduserid,dis_initiate_time,ondiet FROM "+dbname+".apm_treatment_episode where id = "
				+ treatmentepisodeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				bed.setChkDischarge(rs.getBoolean(1));
				bed.setDischrgeOutcomes(DateTimeUtils.isNull(rs.getString(2)));
				bed.setDischargeStatus(DateTimeUtils.isNull(rs.getString(3)));
				bed.setDischargedate(DateTimeUtils.isNull(rs.getString(4)));
				bed.setHospitalcourse(DateTimeUtils.isNull(rs.getString(5)));
				bed.setDiscadvnotes(DateTimeUtils.isNull(rs.getString(6)));
				bed.setExample(DateTimeUtils.isNull(rs.getString(7)));
				bed.setFindondischarge(DateTimeUtils.isNull(rs.getString(8)));
				bed.setTreatmentgiven(DateTimeUtils.isNull(rs.getString(9)));
				bed.setInvestigation(DateTimeUtils.isNull(rs.getString(10)));
				
				bed.setOtNotes(DateTimeUtils.isNull(rs.getString(11)));
				bed.setAppointmentText(DateTimeUtils.isNull(rs.getString(12)));
				bed.setAnesthesia(DateTimeUtils.isNull(rs.getString(13)));
				bed.setSurgeon(DateTimeUtils.isNull(rs.getString(14)));
				bed.setAnesthesiologist(DateTimeUtils.isNull(rs.getString(15)));
				bed.setDischargebyid(DateTimeUtils.isNull(rs.getString(16)));
				bed.setDeathnote(DateTimeUtils.isNull(rs.getString(17)));
				bed.setEmergencydetail(DateTimeUtils.isNull(rs.getString(18)));
				bed.setExample(rs.getString(19));
				bed.setOt_manual_surg(DateTimeUtils.isNull(rs.getString(20)));
				bed.setOt_manual_anesthesio(DateTimeUtils.isNull(rs.getString(21)));
				bed.setEnddischargename(rs.getString(22));
				bed.setDis_initiate_time(rs.getString(23));
				bed.setOndiet(rs.getString(24));
				
				if(bed.getExample()==null){
					bed.setExample("");
				}
				if(bed.getFindondischarge()==null){
					bed.setFindondischarge("");
				}
				if(bed.getInvestigation()==null){
					bed.setInvestigation("");
				}
				if(bed.getOtNotes()==null){
					bed.setOtNotes("");
				}
				if(bed.getHospitalcourse()==null){
					bed.setHospitalcourse("");
				}
				if(bed.getTreatmentgiven()==null){
					bed.setTreatmentgiven("");
				}
				if(bed.getEmergencydetail()==null){
					bed.setEmergencydetail("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bed;
	}

	@Override
	public int updateTreatmentEpisodeDischargeForm(String dischargedate, Bed bed1,int treatmentid) {
		// TODO Auto-generated method stub
		
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set treatmentstatus=?,outcomes=?,dschargestatus=?,dischargedate=?,hospcourse=?,disadvnotes=?,findingondischarge=?,treatmentgiven=?,investigation=?,otNotes=?,dis_extra_note=?,operative_procedure=?, anesthesia=?, surgeon=?, anesthesiologist=?,dischargebyid=?,death_note=?,emergencydetail=?,dis_initiate_time=?,dis_initiate_status=1,ondiet=?  where id = "+treatmentid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, bed1.getDischrgeOutcomes());
			preparedStatement.setString(3, bed1.getDischargeStatus());
			preparedStatement.setString(4, dischargedate);
			preparedStatement.setString(5, bed1.getHospitalcourse());
			preparedStatement.setString(6, bed1.getDiscadvnotes());
			preparedStatement.setString(7, bed1.getFindondischarge());
			preparedStatement.setString(8, bed1.getTreatmentgiven());
			preparedStatement.setString(9, bed1.getInvestigation());
			preparedStatement.setString(10,bed1.getOtNotes());
			preparedStatement.setString(11, bed1.getExample());
			preparedStatement.setString(12, bed1.getAppointmentText());
			preparedStatement.setString(13, bed1.getAnesthesia());
			preparedStatement.setString(14, bed1.getSurgeon());
			preparedStatement.setString(15, bed1.getAnesthesiologist());
			preparedStatement.setString(16, bed1.getDischargebyid());
			preparedStatement.setString(17, bed1.getDeathnote());
			preparedStatement.setString(18, bed1.getEmergencydetail());
			preparedStatement.setString(19, bed1.getDis_initiate_time());
			preparedStatement.setString(20, bed1.getOndiet());
			result = preparedStatement.executeUpdate();
			
			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(Integer.toString(treatmentid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	
	}

	@Override
	public int updatePatientIpdStatusbyId(LoginInfo loginInfo) {
		int result=0;
		try {
			String sql="update apm_patient set opd=1 where opd=0 and f_userid='"+loginInfo.getUserId()+"' ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			result=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public IpdFakeConsult getMaxAvailableDate(int id) {

		PreparedStatement preparedStatement = null;
		IpdFakeConsult ipd=new IpdFakeConsult();
		try {
			String sql="select max(id),commencing from apm_available_slot where clientId='"+id+"' group by id";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				ipd.setId(rs.getInt(1));
				ipd.setCommencing(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	@Override
	public String getnamebyDocid(String docid) {
		
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT concat(firstname,' ',lastname) from apm_user where  id = "+docid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int saveFinalDiagnosis(int ipdid,Bed bed,int treatmentid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_final_diagnosis (ipdid,treatmentepisodeid,conditionid) values(?,?,?)";
		
		int t = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ipdid);
			preparedStatement.setInt(2, treatmentid);
			preparedStatement.setString(3, bed.getConditionid());
			
			//preparedStatement.setString(17, "1");
			
			result = preparedStatement.executeUpdate();
			
			/*
			 * if(result>0){
			 * 
			 * ResultSet rs=preparedStatement.getGeneratedKeys(); while(rs.next()){
			 * result=rs.getInt(1); }
			 * 
			 * }
			 */
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateAdmissionid(String opdid, int ipdid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql="update apm_available_slot set admissionid="+ipdid+"  where id="+opdid+"";
			preparedStatement=connection.prepareStatement(sql);
			result=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int saveteporaryfakedata(String userid, String docid, String patientcount, String currdate) {
	       
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ipd_fake_temp (userid,docid,datetime,patientcount) values(?,?,?,?)";
		
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, docid);
			preparedStatement.setString(3, currdate);
			preparedStatement.setString(4, patientcount);
			
			
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public Vector<IpdFakeConsult> getfakeIpdTempData(String userid) {
		PreparedStatement pst=null;
		Vector<IpdFakeConsult>list=new Vector<IpdFakeConsult>();
		try {
			String sql="select id,docid,datetime,patientcount from ipd_fake_temp where userid='"+userid+"' ";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				IpdFakeConsult ipdConsult=new IpdFakeConsult();
				
				ipdConsult.setId(rs.getInt(1));
				ipdConsult.setDoctor(rs.getString(2));
				ipdConsult.setCommencing(rs.getString(3));
				ipdConsult.setPatientcount(rs.getString(4));
				
				
				
				list.add(ipdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int deleteIpdTempData(String userid) {
		int result = 0;
		try {

			String sql = "delete from ipd_fake_temp where userid='"+userid+"' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public int saveOpdfaketempdata(String docid, String patientcount, String userid, String commencing) {
		PreparedStatement preparedStatement=null;
		int result=0;
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("insert into opd_fake_temp(userid,docid,datetime,patientcount) value(?,?,?,?)");
			preparedStatement=connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, docid);
			preparedStatement.setString(3,commencing );
			preparedStatement.setString(4, patientcount);
			result=preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<OpdConsult> getfakeopdtempdata(String userid) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		try {
			String sql="select id,docid,datetime,patientcount from opd_fake_temp where userid='"+userid+"' ";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				
				opdConsult.setId(rs.getInt(1));
				opdConsult.setDoctor(rs.getString(2));
				opdConsult.setCommencing(rs.getString(3));
				opdConsult.setPatientcount(rs.getString(4));
				
				
				
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteOpdTempData(String userid) {
		int result = 0;
		try {

			String sql = "delete from opd_fake_temp where userid='"+userid+"' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

}

