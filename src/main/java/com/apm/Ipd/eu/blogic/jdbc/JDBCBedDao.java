package com.apm.Ipd.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCBedDao implements BedDao {

	Connection connection = null;

	public JDBCBedDao(Connection connection) {
		this.connection = connection;
	}

	public int addWard(String wardname,String plusminus,String procedure) {
		// TODO Auto-generated method stub

		if(!"1".equals(plusminus)){
			plusminus="0";
		}
		if("1".equals(procedure)){
			procedure="0";
		}
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_ipd_ward (wardname,increment,procedures) values (?,?,?)");
			ps.setString(1, wardname);
			ps.setString(2, plusminus);
			ps.setString(3, procedure);
			result = ps.executeUpdate();
			
			if(result>0){
				 
				  ResultSet set=ps.getGeneratedKeys();
				  while(set.next()){
					    result=set.getInt(1);
				  }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Bed> getAllWardList(String action) {
		ArrayList<Bed> beds = new ArrayList<Bed>();
		
		String sql="select apm_ipd_ward.id,wardname,increment,procedures  from apm_ipd_ward inner join apm_ipd_bed on  "
				+ " apm_ipd_bed.wardid = apm_ipd_ward.id where casualty = "+action+" group by apm_ipd_bed.wardid order by apm_ipd_ward.id desc  ";
		try {

			PreparedStatement ps = connection
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Bed bed = new Bed();
				int id = rs.getInt(1);
				String wardname = rs.getString(2);
				int increment=rs.getInt(3);
				int procedure=rs.getInt(4);
				bed.setId(id);
				bed.setWardname(wardname);
				bed.setIncrement(increment);
				bed.setProcedure(procedure);
				beds.add(bed);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return beds;
	}

	private boolean checkWardExist(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM ipd_addmission_form_new where wardid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int addSection(String sectionname, String wardid) {
		// TODO Auto-generated method stub

		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_ipd_section (wardid,sectionname) values (?,?) ");
			ps.setString(1, wardid);
			ps.setString(2, sectionname);
			result = ps.executeUpdate();
			
			
			if(result>0){
				 
				  ResultSet set=ps.getGeneratedKeys();
				  while(set.next()){
					    result=set.getInt(1);
				  }
			}
			

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Bed> getAllSectionList() {
		// TODO Auto-generated method stub
		ArrayList<Bed> list = new ArrayList<Bed>();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_ipd_section");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Bed bed = new Bed();
				int id = rs.getInt(1);
				String wardid = rs.getString(2);
				String sectionname = rs.getString(3);
				bed.setId(id);
				bed.setWardid(wardid);
				bed.setSectionname(sectionname);
				list.add(bed);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int addBed(Bed bed) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_ipd_bed (wardid,sectionid,bedname,casualty) values (?,?,?,?)");
			ps.setString(1, bed.getWardid());
			ps.setString(2, bed.getSectionid());
			ps.setString(3, bed.getBedname());
			ps.setString(4, bed.getCasualty());
			result = ps.executeUpdate();
			
			if(result>0){
				 
				  ResultSet set=ps.getGeneratedKeys();
				  while(set.next()){
					    result=set.getInt(1);
				  }
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Bed> getAllBedList() {
		// TODO Auto-generated method stub
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ArrayList<Bed> beds = new ArrayList<Bed>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_ipd_bed "
							+ " inner join  apm_ipd_ward on apm_ipd_bed.wardid=apm_ipd_ward.id "
							+ " inner join  apm_ipd_section on apm_ipd_bed.sectionid=apm_ipd_section.id ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String wardid = rs.getString(2);
				String sectionid = rs.getString(3);
				String wardname=null;
				String sectionname=null;
				
				 wardname=rs.getString("wardname");	
				 sectionname=rs.getString("sectionname");
				 
				/*PreparedStatement ps1=connection.prepareStatement("select wardname from apm_ipd_ward where id="+wardid+"");
				ResultSet rs2=ps1.executeQuery();
				while(rs2.next()){
					
			           wardname=rs2.getString(1);		
				}
				PreparedStatement ps2=connection.prepareStatement("select sectionname from apm_ipd_section where id="+sectionid+"");
			    ResultSet rs3=ps2.executeQuery();
				
			    while(rs3.next()){
			    	sectionname=rs3.getString(1);
			    }*/
				String bedname = rs.getString(4);
				String bedstatus = rs.getString(5);
				Bed bed = new Bed();
				bed.setId(id);
				bed.setWardid(wardid);
				bed.setSectionid(sectionid);
				bed.setBedname(bedname);
				bed.setWardname(wardname);
				bed.setSectionname(sectionname);
				bed.setBedstatus(bedstatus);
				beds.add(bed);
				
				
				boolean checkbedstatus  = ipdDAO.checkBedStatus(id);
				if(checkbedstatus){
					
					String clientid = getbedClientid(id);
					/*Client client = clientDAO.getClientDetails(clientid);
					String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();*/
					bed.setClientname(clientDAO.getClientFullName(clientid));
					
					bed.setStatus("1");
				}else{
					bed.setStatus("0");
				}
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return beds;

	}

	private String getbedClientid(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clientid FROM ipd_addmission_form where bedid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int addEquipment(Bed bed) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_ipd_equipment (wardid,sectionid,bedid,equipmentname) values (?,?,?,?)");
			ps.setString(1, bed.getWardid());
			ps.setString(2, bed.getSectionid());
			ps.setString(3, bed.getBedid());
			ps.setString(4, bed.getEquipmentname());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Bed getBed(int id) {
		// TODO Auto-generated method stub
		Bed bed = new Bed();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_ipd_bed where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int iid = rs.getInt(1);
				String wardid = rs.getString(2);
				String sectionid = rs.getString(3);
				String bedname = rs.getString(4);
				bed.setId(iid);
				bed.setWardid(wardid);
				bed.setSectionid(sectionid);
				bed.setBedname(bedname);
				bed.setCasualty(rs.getString(6));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bed;
	}

	public int addIpdAdmissionForm(Bed bed,String date,String action,int maxid) {
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
		sql.append("parity_abortion_notes, p, a, l, d,livegirls,mlcrefdoctor,addmited_by_userid,casualty,ipdseqno,ismlc,referenceid,birthhist, diethist, developmenthist, emmunizationhist,surgicalnote,head_circumference, mid_arm_circumference,length,wt_addmission, wt_discharge,mlcabrivationid,ipdabrivationid,refered_doc_id ) ");
		
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,");
		sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			
			preparedStatement.setString(1,bed.getClientid());
			preparedStatement.setString(2,bed.getPractitionerid());
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
			preparedStatement.setString(18,DateTimeUtils.isNull(bed.getChiefcomplains()));
			preparedStatement.setString(19,DateTimeUtils.isNull(bed.getPresentillness()));
			preparedStatement.setString(20,DateTimeUtils.isNull(bed.getPasthistory()));
			preparedStatement.setString(21,DateTimeUtils.isNull(bed.getPersonalhist()));
			preparedStatement.setString(22,DateTimeUtils.isNull(bed.getFamilyhist()));
			preparedStatement.setString(23,DateTimeUtils.isNull(bed.getOnexamination()));
			preparedStatement.setString(24,bed.getTreatmentepisodeid());
			
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
			
			preparedStatement.setString(66, date);
			preparedStatement.setString(67, date);
    		
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
			preparedStatement.setString(110, action);
			preparedStatement.setInt(111, maxid);
			preparedStatement.setString(112, bed.getMlccase());
			preparedStatement.setString(113, bed.getReferenceid());
			preparedStatement.setString(114, DateTimeUtils.isNull(bed.getBirthhist()));
			preparedStatement.setString(115, DateTimeUtils.isNull(bed.getDiethist()));
			preparedStatement.setString(116, DateTimeUtils.isNull(bed.getDevelopmenthist()));
			preparedStatement.setString(117, DateTimeUtils.isNull(bed.getEmmunizationhist()));
			
			preparedStatement.setString(118, bed.getSurgicalnotes());
			//pediatrics
			preparedStatement.setString(119, bed.getHeadcircumference());
			preparedStatement.setString(120, bed.getMidarmcircumference());
			preparedStatement.setString(121, bed.getLength());
			preparedStatement.setString(122, bed.getWtaddmission());
			preparedStatement.setString(123, bed.getWtdischarge());
			preparedStatement.setString(124, bed.getMlcabrivationid());
			preparedStatement.setString(125, bed.getIpdabrivationid());
			preparedStatement.setString(126, bed.getRefdocotor());
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

	public Bed getEditIpdData(String selectedid) {
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
		sql.append("giddinesscondition,giddinessnotes,unconcondition,unconnotes,referenceid ,admissiondsate,kunal_final_diagnosis_text,kunal_manual_medicine_text ,gstureage,wtonbirth,maternal_history,perinatal_history,reasonlamadama,ipdabrivationid,ondiet,lastopdno,mbbsseqno,refered_doc_id ");
		sql.append(" from ipd_addmission_form where id='"+selectedid+"' ");
		
		try{
			
			ClientDAO clientDAO= new JDBCClientDAO(connection); 
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				bed.setIpdnewid(selectedid);
				bed.setClientid(rs.getString(1));
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
	    		bed.setPresentillness((DateTimeUtils.isNull(rs.getString(19))).trim());
	    		bed.setPasthistory((DateTimeUtils.isNull(rs.getString(20))).trim());
	    		bed.setPersonalhist((DateTimeUtils.isNull(rs.getString(21))).trim());
	    		bed.setFamilyhist((DateTimeUtils.isNull(rs.getString(22))).trim());
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
	    		bed.setEarlierinvest((DateTimeUtils.isNull(rs.getString(68))).trim());
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
	    		bed.setBirthhist((DateTimeUtils.isNull(rs.getString(112))).trim());
	    		bed.setDiethist((DateTimeUtils.isNull(rs.getString(113))).trim());
	    		bed.setDevelopmenthist((DateTimeUtils.isNull(rs.getString(114))).trim());
	    		bed.setEmmunizationhist((DateTimeUtils.isNull(rs.getString(115))).trim());
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
	    		bed.setOndiet(DateTimeUtils.isNull(rs.getString(136)));
	    		bed.setOpdabbrivationid(DateTimeUtils.isNull(rs.getString(137)));
	    		bed.setMbbsipdsequence(DateTimeUtils.isNull(rs.getString(138)));
	            bed.setRefdocotor(DateTimeUtils.isNull(rs.getString(139)));
	    		
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

	
	
	public Bed getIpdDetails(String clientid) {
		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select ");
		sql.append("id, practitionerid, conditionid, department, refferedby, wardid, bedid, tpid, status, ");
		sql.append("addmissionfor, reimbursment, secndryconsult, mlcno, admissiondetails, suggestedtrtment, hosp, package, ");
		sql.append("chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid, ");
		sql.append("suggestoper, systdepartment, fpcondition, fpnotest, nauseacondition, nauseanotes, fnucondition, ");
		sql.append("fnunotes, smcondition, smnotes, chestpaincond, chestpainnotes, dimvisioncond, dimvisionnotes, hgucondition, ");
		sql.append("hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, constipationcond, constpationnotes, ");
		sql.append("specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, hematurianotes, bmcondition, bmnotes, ");
		sql.append("oliguriacondi, oligurianotes, pstgucondi, pstgunotes, bmecondition, bmenotes, tnecondition, tnenotes, ");
		sql.append("weaknesscondi, weaknessnotes, ihcondition, ihnotes, admissiondsate from ipd_addmission_form where clientid="+clientid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				bed.setId(rs.getInt(1));
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
	    		bed.setAdmissiondetails(rs.getString(14));
	    		bed.setSuggestedtrtment(rs.getString(15));
	    		bed.setHosp(rs.getString(16));
	    		bed.setPackagename(rs.getString(17));
	    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
	    		bed.setChiefcomplains(rs.getString(18));
	    		bed.setPresentillness(rs.getString(19));
	    		bed.setPasthistory(rs.getString(20));
	    		bed.setPersonalhist(rs.getString(21));
	    		bed.setFamilyhist(rs.getString(22));
	    		bed.setOnexamination(rs.getString(23));
	    		bed.setTreatmentepisodeid(rs.getString(24));
	    		bed.setClientid(clientid);
	    	
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
	    		
	    		if(rs.getString(66)!=null){
	    			//String dbDate = DateTimeUtils.getDBDate(rs.getString(66));
	    			bed.setAdmissiondate(rs.getString(66));
	    		}else{
	    			bed.setAdmissiondate("");
	    		}
	    		
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return bed;
	}

	
	
	
	public int updateIpdDetails(Bed bed, int id,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		StringBuffer sql = new StringBuffer();
		  sql.append("update ipd_addmission_form set ");  
		  sql.append("clientid=?, practitionerid=?, conditionid=?, department=?, refferedby=?, wardid=?, bedid=?, tpid=?, status=?, ");
		  sql.append("addmissionfor=?, reimbursment=?, secndryconsult=?, mlcno=?, admissiondetails=?, suggestedtrtment=?, hosp=?, package=?, ");
		  sql.append("chiefcomplains=?, presentillness=?, pasthistory=?, personalhist=?, familyhist=?, onexamination=?, treatmentepisodeid=?, ");
		  sql.append("suggestoper=?, systdepartment=?, fpcondition=?, fpnotest=?, nauseacondition=?, nauseanotes=?, fnucondition=?, ");
		  sql.append("fnunotes=?, smcondition=?, smnotes=?, chestpaincond=?, chestpainnotes=?, dimvisioncond=?, dimvisionnotes=?, hgucondition=?, ");
		  sql.append("hgunotes=?, hmcondition=?, hmnotes=?, jointpaincond=?, jointpainnotes=?, constipationcond=?, constpationnotes=?, ");
		  sql.append("specialnotes=?, edemafeetcondi=?, edemafeetnotes=?, hematuriacondi=?, hematurianotes=?, bmcondition=?, bmnotes=?, ");
		  sql.append("oliguriacondi=?, oligurianotes=?, pstgucondi=?, pstgunotes=?, bmecondition=?, bmenotes=?, tnecondition=?, tnenotes=?, ");
		  sql.append("weaknesscondi=?, weaknessnotes=?, ihcondition=?, ihnotes=?, lastmodified=?, reason_admission=?, early_investigation=?,allergies=?,speciality=?, ");
		  sql.append("alcohal=?, drugs=?, other_medication=?, tobacocon=?, tobaconotes=?, smoking=?, ");
		  sql.append("menarche=?, lmp=?, llmp=?, pmc=?, cycle_length=?, duration_flow=?, amount_flow=?, ");
		  sql.append("dysmenorrhea=?, dyspareunia=?, hopassingclots=?, white_disc_itching=?, intercourse_freq=?, ");
		  sql.append("galactorea=?, hocontrarec=?, rubella_vaccine=?, menopause=?, nulligravida=?, ");
		  sql.append("current_pregnent=?, iud=?, liveboys=?, stillbirths=?, abortions=?, death_child=?, ");
		  sql.append("parity_abortion_notes=?, p=?, a=?, l=?, d=?,livegirls=?,mlcrefdoctor=?, surgicalnote=?,ismlc=?,referenceid=?, birthhist=?, diethist=?, developmenthist=?, emmunizationhist=?,head_circumference=?, mid_arm_circumference=?,length=?,wt_addmission=?, wt_discharge=?,  ");
		  sql.append("giddinesscondition=?, giddinessnotes=?, unconcondition=?, unconnotes=?,mlcabrivationid=?,gstureage=?,wtonbirth=? ,maternal_history=?,perinatal_history=?,ondiet=?,admissiondsate=?,refered_doc_id=? ");
		  sql.append("where id="+id+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			
			preparedStatement.setString(1,bed.getClientid());
			preparedStatement.setString(2,bed.getPractitionerid());
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
			preparedStatement.setString(24,bed.getTreatmentepisodeid());
			
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
			preparedStatement.setString(66, date);
			preparedStatement.setString(67, bed.getAdmission_reason());
			preparedStatement.setString(68, bed.getEarlierinvest());
    		preparedStatement.setString(69, bed.getAlergies());
    		preparedStatement.setString(70, bed.getSpeciality());
		
    		preparedStatement.setString(71, bed.getAlcohal());
			preparedStatement.setString(72, bed.getDrugs());
			preparedStatement.setString(73, bed.getOther_medication());
			preparedStatement.setString(74, bed.getTobaco());
			preparedStatement.setString(75, bed.getTobaconotes());
			preparedStatement.setString(76, bed.getSmoking());
			preparedStatement.setString(77, bed.getAge_menarche());
			preparedStatement.setString(78, bed.getLmp());
			
			preparedStatement.setString(79, bed.getLlmp());
			preparedStatement.setString(80, bed.getPmc());
			preparedStatement.setString(81, bed.getCycle_length());
			preparedStatement.setString(82, bed.getDuration_flow());
			preparedStatement.setString(83, bed.getAmount_flow());
			preparedStatement.setString(84, bed.getDysmenorrhea());
			
			preparedStatement.setString(85, bed.getDysmenorrhea());
			preparedStatement.setString(86, bed.getHopassing_clots());
			preparedStatement.setString(87, bed.getWhite_disc_itching());
			preparedStatement.setString(88, bed.getIntercourse_freq());
			
			preparedStatement.setString(89, bed.getGalactorrea());
			preparedStatement.setString(90, bed.getHo_contraception());
			preparedStatement.setString(91, bed.getRubella_vaccine());
			preparedStatement.setString(92, bed.getMenopause());
			preparedStatement.setString(93, bed.getNulligravida());
			preparedStatement.setString(94, bed.getCurrent_pregnent());
			
			preparedStatement.setString(95, bed.getIud());
			preparedStatement.setString(96, bed.getLive_boys());
			preparedStatement.setString(97, bed.getStillbirths());
			preparedStatement.setString(98, bed.getAbortions());
			preparedStatement.setString(99, bed.getDeath_children());
			preparedStatement.setString(100, bed.getParity_abortion_notes());
			preparedStatement.setString(101, bed.getP());
			preparedStatement.setString(102, bed.getA());
			preparedStatement.setString(103, bed.getL());
			preparedStatement.setString(104, bed.getD());
			preparedStatement.setString(105, bed.getLive_girls());
			preparedStatement.setString(106, bed.getMlcrefdoctor());
			preparedStatement.setString(107, bed.getSurgicalnotes());
			preparedStatement.setString(108, bed.getMlccase());
			preparedStatement.setString(109, bed.getReferenceid());
			preparedStatement.setString(110, bed.getBirthhist());
			preparedStatement.setString(111, bed.getDiethist());
			preparedStatement.setString(112, bed.getDevelopmenthist());
			preparedStatement.setString(113, bed.getEmmunizationhist());
			preparedStatement.setString(114, bed.getHeadcircumference());
			preparedStatement.setString(115, bed.getMidarmcircumference());
			preparedStatement.setString(116, bed.getLength());
			preparedStatement.setString(117, bed.getWtaddmission());
			preparedStatement.setString(118, bed.getWtdischarge());
			
			//  27 july 2018
			preparedStatement.setString(119, bed.getGiddinesscondition());
			preparedStatement.setString(120, bed.getGiddinessnotes());
			preparedStatement.setString(121, bed.getUnconcondition());
			preparedStatement.setString(122, bed.getUnconnotes());
			//  08/01/19
			preparedStatement.setString(123, bed.getMlcabrivationid());
			preparedStatement.setString(124, DateTimeUtils.isNull(bed.getGstureage()));
			preparedStatement.setString(125, DateTimeUtils.isNull(bed.getWtonbirth()));
			preparedStatement.setString(126, DateTimeUtils.isNull(bed.getMaternal_history()));
			preparedStatement.setString(127, DateTimeUtils.isNull(bed.getPerinatal_history()));
			preparedStatement.setString(128, DateTimeUtils.isNull(bed.getOndiet()));
			preparedStatement.setString(129, DateTimeUtils.isNull(bed.getAdmissiondate()));
			preparedStatement.setString(130, DateTimeUtils.isNull(bed.getRefdocotor()));
			result = preparedStatement.executeUpdate();
			
			updateAdmissionFormInParent(id);
			updateDischargeForm(bed.getTreatmentepisodeid());
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return result;
	}

	
	public void updateDischargeForm(String treatmentepisodeid) {
		try {
			String sql="UPDATE discharge_form,apm_treatment_episode "
					+ "SET discharge_form.clientid = apm_treatment_episode.clientid,"
					+ "discharge_form.practitionerid = apm_treatment_episode.practitionerid,"
					+ "discharge_form.condition_id = apm_treatment_episode.condition_id,"
					+ "discharge_form.treatmentstatus = apm_treatment_episode.treatmentstatus,"
					+ "discharge_form.dschargestatus = apm_treatment_episode.dschargestatus,"
					+ "discharge_form.dischargedate = apm_treatment_episode.dischargedate,"
					+ "discharge_form.lastmodified = apm_treatment_episode.lastmodified,"
					+ "discharge_form.dis_initiate_time = apm_treatment_episode.dis_initiate_time,"
					+ "discharge_form.dis_initiate_status = apm_treatment_episode.dis_initiate_status,"
					+ "discharge_form.dis_form_time = apm_treatment_episode.dis_form_time,"
					+ "discharge_form.dis_form_status = apm_treatment_episode.dis_form_status,"
					+ "discharge_form.dis_mdicine_time = apm_treatment_episode.dis_mdicine_time,"
					+ "discharge_form.dis_mdicine_status = apm_treatment_episode.dis_mdicine_status,"
					+ "discharge_form.dis_bill_time = apm_treatment_episode.dis_bill_time,"
					+ "discharge_form.dis_bill_status = apm_treatment_episode.dis_bill_status,"
					+ "discharge_form.dis_nursing_time = apm_treatment_episode.dis_nursing_time,"
					+ "discharge_form.dis_nursing_status = apm_treatment_episode.dis_nursing_status,"
					+ "discharge_form.iscancel = apm_treatment_episode.iscancel "
					+ "WHERE discharge_form.id='"+treatmentepisodeid+"' and apm_treatment_episode.id='"+treatmentepisodeid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void updateAdmissionFormInParent(int id) {
		try {
			String sql="UPDATE ipd_addmission_form_new,ipd_addmission_form "
					+ "SET ipd_addmission_form_new.clientid = ipd_addmission_form.clientid,"
					+ "ipd_addmission_form_new.practitionerid = ipd_addmission_form.practitionerid,"
					+ "ipd_addmission_form_new.conditionid = ipd_addmission_form.conditionid,"
					+ "ipd_addmission_form_new.admissiondsate = ipd_addmission_form.admissiondsate,"
					+ "ipd_addmission_form_new.lastmodified = ipd_addmission_form.lastmodified,"
					+ "ipd_addmission_form_new.treatmentepisodeid = ipd_addmission_form.treatmentepisodeid,"
					+ "ipd_addmission_form_new.speciality = ipd_addmission_form.speciality,"
					+ "ipd_addmission_form_new.ismlc = ipd_addmission_form.ismlc,"
					+ "ipd_addmission_form_new.ipdseqno = ipd_addmission_form.ipdseqno,"
					+ "ipd_addmission_form_new.ipdabrivationid = ipd_addmission_form.ipdabrivationid,"
					+ "ipd_addmission_form_new.wardid = ipd_addmission_form.wardid,"
					+ "ipd_addmission_form_new.bedid = ipd_addmission_form.bedid,"
					+ "ipd_addmission_form_new.department = ipd_addmission_form.department,"
					+ "ipd_addmission_form_new.tpid = ipd_addmission_form.tpid,"
					+ "ipd_addmission_form_new.mlcno = ipd_addmission_form.mlcno,"
					+ "ipd_addmission_form_new.addmited_by_userid = ipd_addmission_form.addmited_by_userid,"
					+ "ipd_addmission_form_new.casualty = ipd_addmission_form.casualty,"
					+ "ipd_addmission_form_new.mlcabrivationid = ipd_addmission_form.mlcabrivationid, "
					+ "ipd_addmission_form_new.admissiondsate = ipd_addmission_form.admissiondsate "
					+ "WHERE ipd_addmission_form.id='"+id+"' and ipd_addmission_form_new.id='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public NotAvailableSlot getClientLastOpdRecord(String clientid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT diaryuserid,condition_id,treatmentepisodeid FROM apm_available_slot where clientid = 6242 order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				notAvailableSlot.setDiaryUser(rs.getString(1));
				notAvailableSlot.setCondition(rs.getString(2));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public int updateBedStatus(String sessionadmissionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_addmission_form set bedid=? where id = "+sessionadmissionid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "0");
			result = preparedStatement.executeUpdate();
			
			updateAdmissionFormInParent(DateTimeUtils.convertToInteger(sessionadmissionid));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	

	public ArrayList<Bed> getAllWardList(Pagination pagination) {
		ArrayList<Bed> beds = new ArrayList<Bed>();
		try {

			String sql="select * from apm_ipd_ward order by id desc";
			
			
			if(pagination!=null){
				sql=pagination.getSQLQuery(sql);
			}
			PreparedStatement ps = connection
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Bed bed = new Bed();
				int id = rs.getInt(1);
				String wardname = rs.getString(2);
				bed.setId(id);
				bed.setWardname(wardname);
				
				boolean checkWardExist = checkWardExist(id);
				if(checkWardExist){
					bed.setStatus("1");
				}else{
					bed.setStatus("0");
				}
				
				beds.add(bed);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return beds;
	}
	
	
	
	
	
	public int deleteBed(String selectedid) {

		int result=0;
		String wardid=null;
		String sectionid=null;
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("select wardid,sectionid from apm_ipd_bed where id=?");
			ps.setString(1, selectedid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				 wardid=rs.getString(1);
				 sectionid=rs.getString(2);
			}
			
			PreparedStatement ps2=connection.prepareStatement("delete from apm_ipd_ward where id=?");
			ps2.setString(1, wardid);
			result=ps2.executeUpdate();

			PreparedStatement ps3=connection.prepareStatement("delete from apm_ipd_section where id=?");
			ps3.setString(1, sectionid);
			result=ps3.executeUpdate();
			
			PreparedStatement ps4=connection.prepareStatement("delete from apm_ipd_bed where id=?");
			ps4.setString(1, selectedid);
			result=ps4.executeUpdate();
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int updateBedEntries(Bed bed) {
		// TODO Auto-generated method stub
		int result = 0;
		String wardid = null;
		String sectionid = null;
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_ipd_bed where id=?");
			ps.setInt(1, bed.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				wardid = rs.getString(2);
				sectionid = rs.getString(3);
			}

			PreparedStatement ps1 = connection
					.prepareStatement("update apm_ipd_ward set wardname=? where id=?");
			ps1.setString(1, bed.getWardname());
			ps1.setString(2, wardid);
			ps1.executeUpdate();

			PreparedStatement ps2 = connection
					.prepareStatement("update apm_ipd_section set sectionname=? where id=?");
			ps2.setString(1, bed.getSectionname());
			ps2.setString(2, sectionid);

			PreparedStatement ps3 = connection
					.prepareStatement("update apm_ipd_bed set bedname=? where id=?");
			ps3.setString(1, bed.getBedname());
			ps3.setString(2, sectionid);

			result = ps3.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Bed> getEquipmentList() {

		ArrayList<Bed> equipments=new ArrayList<Bed>();
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("select id,equipmentname from apm_ipd_equipment");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				Bed bed=new Bed();
				int id=rs.getInt(1);
				String equipmentname=rs.getString(2);
				bed.setId(id);
				bed.setEquipmentname(equipmentname);
				equipments.add(bed);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return equipments;
	}

	public ArrayList<Bed> getSectionList(String wardid) {

		ArrayList<Bed> sections=new ArrayList<Bed>();
		
		try {
			
			String sql="select id,sectionname from apm_ipd_section where wardid="+wardid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				  Bed bed=new Bed();
				  bed.setId(rs.getInt(1));
				  bed.setSectionname(rs.getString(2));
				  sections.add(bed);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
	
        return sections; 
	}

	public ArrayList<Bed> getBedList(String sectionid) {

		 ArrayList<Bed> beds=new ArrayList<Bed>();
		 
		 try {
		
			 String sql="select id,bedname from apm_ipd_bed where sectionid="+sectionid+"";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 while(rs.next()) {
				 
				 Bed bed=new Bed();
				 bed.setId(rs.getInt(1));
				 bed.setBedname(rs.getString(2));
				 beds.add(bed);
			 }
			
			 
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return beds;
	}

	public ArrayList<Bed> getEquipmentList(String bedid) {

		ArrayList<Bed> equipments=new ArrayList<Bed>();
		
		try {
		
			String sql="select id,equipmentname from apm_ipd_equipment where bedid="+bedid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			
				Bed bed=new Bed();
			    bed.setId(rs.getInt(1));
			    bed.setEquipmentname(rs.getString(2));
			    equipments.add(bed);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		} 
      		
		return equipments;
	}

	public int getTotalWardCount() {

		int result=0;
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("select count(*) from apm_ipd_ward");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				result=rs.getInt(1);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public Bed getWard(String selectedid) {

		Bed  bed=new Bed();
		
		try {
			
			String sql="select wardname,increment,procedures from apm_ipd_ward where id='"+selectedid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				bed.setId(Integer.parseInt(selectedid));
				bed.setWardname(rs.getString(1));
				bed.setIncrement(rs.getInt(2));
				bed.setProcedure(rs.getInt(3));
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return bed;
	}

	public int updateWardMaster(Bed bed)  {

		int result=0;
	    try {
	    	
	    	String sql="update apm_ipd_ward set wardname=?,increment=?,procedures=? where id="+bed.getId()+"";
	    	PreparedStatement ps=connection.prepareStatement(sql);
	    	ps.setString(1, bed.getWardname());
	    	ps.setInt(2, bed.getIncrement());
	    	ps.setInt(3, bed.getProcedure());
	    	result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	    
		
		return result;
	}

	public int deleteWardMaster(String selectedid) {

		int result=0;
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("delete from apm_ipd_ward where id=?");
			ps.setString(1, selectedid);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Bed> getAllSectionList(Pagination pagination) {

				ArrayList<Bed> list = new ArrayList<Bed>();
				try {

					String sql="select * from apm_ipd_section";
					sql=pagination.getSQLQuery(sql);
					PreparedStatement ps = connection
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						Bed bed = new Bed();
						int id = rs.getInt(1);
						String wardid = rs.getString(2);
						String sectionname = rs.getString(3);
						bed.setId(id);
						bed.setWardid(wardid);
						bed.setSectionname(sectionname);
						
						boolean checksesctionexist = checkSectionExist(id);
						if(checksesctionexist){
							bed.setStatus("1");
						}else{
							bed.setStatus("0");
						}
						
						list.add(bed);
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				return list;
	}

	private boolean checkSectionExist(int id) {
		PreparedStatement preparedStatement = null;
		boolean resiult = false;
		String sql = "SELECT * FROM apm_ipd_bed where sectionid = "+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				resiult = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resiult;
	}

	public int getTotalSectionCount() {

		int result=0;
		try {
			
			PreparedStatement ps=connection.prepareStatement("select count(*) from apm_ipd_section");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				result=rs.getInt(1);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public Bed getSection(String selectedid) {
		

		Bed bed=new Bed();
		try {
	
			  String sql="select wardid,sectionname from apm_ipd_section where id='"+selectedid+"'";
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next()) {
				  
				  bed.setWardid(rs.getString(1));
				  bed.setSectionname(rs.getString(2));
				  bed.setId(Integer.parseInt(selectedid));
			  }
			  
		} catch (Exception e) {
             e.printStackTrace();
		}
	
		return bed;
	}

	public int updateSectionMaster(Bed bed) {

		int result=0;
		
		try {
			/*String sql="update apm_ipd_section set wardid=?,sectionname=? where id="+bed.getId()+"";*/
			String sql="update apm_ipd_section set sectionname=? where id="+bed.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			/*ps.setString(1, bed.getWardid());*/
			ps.setString(1, bed.getSectionname());
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		
		return result;
	}


	public int deleteSectionMaster(String selectedid) {

		 int result=0;
		 
		 try {
			
			 String sql="delete from apm_ipd_section where id='"+selectedid+"'";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 result=ps.executeUpdate();
			 
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		
		return result;
	}

	public ArrayList<Bed> getAllBedList(Pagination pagination,String searchText) {

        ArrayList<Bed> beds=new ArrayList<Bed>(); 
		 
        try {
			String sql = "";
			if (searchText!=null) {
				sql="select id,wardid,sectionid,bedname from apm_ipd_bed where bedname like ('"+searchText+"%')";
	        } else {
	        	sql="select id,wardid,sectionid,bedname from apm_ipd_bed";
	        }
        	sql=pagination.getSQLQuery(sql);
        	PreparedStatement ps=connection.prepareStatement(sql);
        	ResultSet rs=ps.executeQuery();
        	while(rs.next()) {
        		
        		  Bed bed=new Bed();
        		  bed.setId(rs.getInt(1));
        		  bed.setWardid(rs.getString(2));
        		  bed.setSectionid(rs.getString(3));
        		  bed.setBedname(rs.getString(4));
        		  
        		  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
        		  boolean checkbedstatus  = ipdDAO.checkBedStatus(bed.getId());
  					if(checkbedstatus){
  						bed.setStatus("1");
  					}else{
  						bed.setStatus("0");
  					}
        		  
        		  beds.add(bed);
        		
        	}
        	    	
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return beds;
	}

	public int getTotalBedCount(String searchText) {

		int result=0;
		
		try {
			String sql ="";
			if (searchText!=null) {
				sql ="select count(*) from apm_ipd_bed where bedname like ('"+searchText+"%')";
			} else {
				sql ="select count(*) from apm_ipd_bed";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				result=rs.getInt(1);
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}



	public int updateBedMaster(Bed bed) {

		int result=0;
		
		try {
			
			/*String sql="update apm_ipd_bed set wardid=?,sectionid=?,bedname=? where id="+bed.getId()+"";*/
			String sql="update apm_ipd_bed set bedname=?,casualty=? where id="+bed.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			/*ps.setString(1, bed.getWardid());
			ps.setString(2, bed.getSectionid());*/
			ps.setString(1, bed.getBedname());
			ps.setString(2, bed.getCasualty());
            result=ps.executeUpdate();
			
		} catch (Exception e) {
             
		   e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteBedMaster(String selectedid) {

		int result=0;		
		try {
			
			PreparedStatement ps=connection.prepareStatement("delete from apm_ipd_bed where id='"+selectedid+"'");
			result=ps.executeUpdate();		
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Bed> getEquipmentList(Pagination pagination) {

	ArrayList<Bed> equipments=new ArrayList<Bed>();
		
		try {
			
			String sql="select id,wardid,sectionid,bedid,equipmentname from apm_ipd_equipment";
			sql=pagination.getSQLQuery(sql);
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				Bed bed=new Bed();
				bed.setId(rs.getInt(1));
				bed.setWardid(rs.getString(2));
				bed.setSectionid(rs.getString(3));
				bed.setBedid(rs.getString(4));
				bed.setEquipmentname(rs.getString(5));
				equipments.add(bed);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return equipments;
			
	}

	public int getTotalEquipmentCount() {
		
		int result=0;
		try {
			
			PreparedStatement ps=connection.prepareStatement("select count(*) from apm_ipd_equipment");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				result=rs.getInt(1);
			}
		} catch (Exception e) {

		  e.printStackTrace();
		}
		return result;
	}

	public Bed getEquipment(String selectedid) {

		 Bed bed=new Bed();
		 
		 try {
			
			String sql="select wardid,sectionid,bedid,equipmentname from apm_ipd_equipment where id='"+selectedid+"'"; 
			 
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
			      bed.setWardid(rs.getString(1));
			      bed.setSectionid(rs.getString(2));
			      bed.setBedid(rs.getString(3));
			      bed.setEquipmentname(rs.getString(4));
			      bed.setId(Integer.parseInt(selectedid));
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return bed;
	}

	public int updateEquipment(Bed bed) {

		int result=0;
        try {
			
        	/*String sql="update apm_ipd_equipment set wardid=?,sectionid=?,bedid=?,equipmentname=? where id="+bed.getId()+"";*/
        	String sql="update apm_ipd_equipment set equipmentname=? where id="+bed.getId()+"";
        	PreparedStatement ps=connection.prepareStatement(sql);
        	/*ps.setString(1, bed.getWardid());
        	ps.setString(2, bed.getSectionid());
        	ps.setString(3, bed.getBedid());*/
        	ps.setString(1, bed.getEquipmentname());
        	
        	result=ps.executeUpdate();
        	
		} catch (Exception e) {

		   e.printStackTrace();
		}		
		
		return result;
	}

	public int deleteEquipment(String selectedid) {

		int result=0;
		
		try {
			
			PreparedStatement ps=connection.prepareStatement("delete from apm_ipd_equipment where id='"+selectedid+"'");
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public int addCondition(int admissionid, Bed bed) {
		int result=0;
		
		try {
			
			String sql="insert into apm_ipd_condition (admissionid,conditionid,conditionname) values (?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(admissionid));
			ps.setString(2, bed.getConditionid());
			ps.setString(3, bed.getConditionname());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return result;
	}

	public int updateIpdCondition(int admissionid, String conditionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_addmission_form set conditionid=? where id="+admissionid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, conditionid);
			
			result = preparedStatement.executeUpdate();
			updateAdmissionFormInParent(admissionid);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bed> getIpdConditionList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed>list = new ArrayList<Bed>();
		String sql = "SELECT conditionid FROM apm_ipd_condition where admissionid = "+selectedid+" order by id ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				if(rs.getString(1)==null){
					continue;
				}
				
				Bed bed = new Bed();
				bed.setConditionid(rs.getString(1));
				String conditionname = getIpdConditionName(bed.getConditionid());
				bed.setConditionname(conditionname);
				
				list.add(bed);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public String getIpdConditionName(String conditionid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select id,name,diseasecode,icdcode from apm_diagnosis where id = '"+conditionid+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				String condition = rs.getString(2);
				
				if(rs.getString(3)!=null && rs.getString(4)!=null){
					condition =  condition  + " " + rs.getString(3) + " / " + rs.getString(4);
				}
				
				else if(rs.getString(4)!=null){
					if(rs.getString(4).equals("")){
						//condition = condition;
					}else if(rs.getString(4).equals("0")){
						//condition = condition;
					}else{
						condition = condition + " / " + rs.getString(4);
					}
				}
				result = condition;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return result;
	}

	public int deleteIpdCondition(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_ipd_condition where admissionid="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int saveAdmissionLog(Bed bed, String date, int admissionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ipd_admission_log(admissionid, lastmodified, clientid) values(?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, admissionid);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, bed.getClientid());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveDischargeLog(String sessionadmissionid, String patientid,
			String ukCurrentDataTime,String bedid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ipd_discharge_log(admissionid, lastmodified, clientid,bedid,discharge_end_by) values(?,?,?,?,?) ";
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sessionadmissionid);
			preparedStatement.setString(2, ukCurrentDataTime);
			preparedStatement.setString(3, patientid);
			preparedStatement.setString(4, bedid);
			preparedStatement.setString(5, loginInfo.getUserId());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getTreatmentName(String treatmentepisodeid) {
        
        String treatmentType=null;
		
		try {
			
			String sql="select name from apm_treatment_episode where id='"+treatmentepisodeid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);

			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				treatmentType=rs.getString(1);	
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}  
		
		return treatmentType;
	 
	}

	public Collection<Bed> getConditionList(String selectedid) {
		PreparedStatement preparedStatement = null;
		Collection<Bed>list  = new ArrayList<Bed>();
		String sql = "select id,name,diseasecode,icdcode from apm_condition where id = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed client = new Bed();
				client.setId(rs.getInt(1));
				String condition = rs.getString(2);
				
				if(rs.getString(3)!=null && rs.getString(4)!=null){
					condition =  condition  + " " + rs.getString(3) + " / " + rs.getString(4);
				}
				
				else if(rs.getString(4)!=null){
					condition = condition + " / " + rs.getString(4);
				}
				
				
				
				client.setConditionname(condition);
				list.add(client);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveBedChangeLog(Bed bed, String date, int admissionid,String logcommencing,String selectedshiftdata,int autosetcharge) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		logcommencing = date.split(" ")[0];
		String sql = "insert into ipd_bed_change_log(admissionid, wardid, bedid, lastmodified, clientid,commencing,selectedshiftdata,autosetcharge) values(?,?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, admissionid);
			preparedStatement.setString(2, bed.getWardid());
			preparedStatement.setString(3, bed.getBedid());
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, bed.getClientid());
			preparedStatement.setString(6, logcommencing);
			preparedStatement.setString(7, selectedshiftdata);
			preparedStatement.setInt(8, autosetcharge);
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int changeBedStatus(String selectedid, String status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_ipd_bed set active="+status+" where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<String> getConditionNameList(ArrayList<Bed> ipdConditionids) {

		  ArrayList<String> list=new ArrayList<String>();
		  
		  try {
		   
		   for(Bed bed:ipdConditionids) {
		    
		     String name=getIpdConditionName(bed.getConditionid());
		     list.add(name);
		   }
		   
		   
		  } catch (Exception e) {

		    e.printStackTrace();
		  }
        return list;
	}

	public ArrayList<Bed> getAllIpdActivePatients() {

		ArrayList<Bed> list=new ArrayList<Bed>();
		
		try {
			
			String sql="SELECT id,clientid,bedid,conditionid,wardid,admissiondsate from ipd_addmission_form where bedid!=0 order by id desc";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   Bed bed=new Bed();
				   bed.setId(rs.getInt(1));
				   bed.setClientid(rs.getString(2));
				   bed.setBedid(rs.getString(3));
				   bed.setConditionid(rs.getString(4));
				   bed.setWardid(rs.getString(5));
				   bed.setAdmissiondate(rs.getString(6));
				   list.add(bed);
			}
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public boolean getIsWardChange(int id, String wardid) {

		boolean isFlag=false;
		String tempwardid="";
		try {
			
			String sql="select wardid from ipd_addmission_form where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				tempwardid=rs.getString(1);
			}
			
			if(tempwardid.equals(wardid)){
				
				isFlag=false;
			}
			else {
				isFlag=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isFlag;
	}

	
	public int deleteIpdConditionifExist(int admissionid,
			String treatmentepisodeid) {

		int result=0;
		
		try {
			String sql="delete from ipd_condition_report where ipdid="+admissionid+" and treatmentepisodeid="+treatmentepisodeid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	
	public int addIpdConditionReport(int admissionid, Bed bed) {

		int result=0;
		try {
			String sql="insert into ipd_condition_report (ipdid, treatmentepisodeid, conditionid, lastmodified) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1, admissionid);
			ps.setString(2, bed.getTreatmentepisodeid());
			ps.setString(3, bed.getConditionid());
			ps.setString(4, bed.getLastmodified());
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public boolean isIpdExistCondition(String sessionadmissionid,
			String treatmentEpisodeid, String conditionid) {

		try {
			
			String sql="select id from ipd_condition_report where ipdid="+sessionadmissionid+" and treatmentepisodeid="+sessionadmissionid+" and conditionid="+conditionid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public int updateCasualtyid(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set casualtyid=0 where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Bed> getAllIpdStdPatientList() {

		ArrayList<Bed> list= new ArrayList<Bed>();
		try {
			String sql="select id,clientid,practitionerid,wardid,admissiondsate,tpid from ipd_addmission_form where bedid!=0 and setstdcharge=1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Bed bed=new Bed();
				bed.setId(rs.getInt(1));
				bed.setIpdid(rs.getString(1));
				bed.setClientid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				bed.setWardid(rs.getString(4));
				bed.setAdmissiondate(rs.getString(5));
				bed.setTpid(rs.getString(6));
				list.add(bed);
				
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int updateStdChargeSetup(String ipdid) {

		int result=0;
		try {
			
			String sql="update ipd_addmission_form set setstdcharge=0 where id="+ipdid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}


	
	public int saveShiftBedLog(String admissiondate, int id, String fromward,
			String toward) {
		
		int result=0;
	    try {
			
	    	String sql="insert into ipd_shift_bed_log (ipdid, fromward, toward, datetime) values (?,?,?,?) ";
	    	PreparedStatement ps=connection.prepareStatement(sql);
	    	ps.setInt(1, id);
	    	ps.setString(2, fromward);
	    	ps.setString(3, toward);
	    	ps.setString(4, admissiondate);
	    	
	    	result =ps.executeUpdate();
	    	
		} catch (Exception e) {

			 e.printStackTrace();
		}	
		
		return result;
	}

	public int saveGynicObsData(int admissionid, Bed bed) {

		int result =0;
		try {
			
			String sql="insert into ipd_gynic_obs_history (ipdid, year, obs_type, type_delivery, indications, coamplications, institution, notes, gynicid,newgynicid) " +
					" values (?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, admissionid);
			ps.setString(2, bed.getYear());
			ps.setString(3, bed.getType());
			ps.setString(4, bed.getType_delivery());
			ps.setString(5, bed.getIndications());
			ps.setString(6, bed.getCoamplications());
			ps.setString(7, bed.getInstitution());
			ps.setString(8, "");
			ps.setString(9, bed.getGynicid());
			ps.setString(10, ""+bed.getNewgynicid());
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bed> getGynicObsList(String ipdid)  {
		
	     ArrayList<Bed> list= new ArrayList<Bed>();	
		
	     try {
	    	 
	    	 String sql="select id, year, obs_type, type_delivery, indications, coamplications, institution, notes from ipd_gynic_obs_history where ipdid="+ipdid+"";
	    	 PreparedStatement ps=connection.prepareStatement(sql);
	    	 ResultSet rs= ps.executeQuery();
	    	 while(rs.next() ){
	    		 
	    		    Bed bed=new Bed();
	    		    bed.setId(rs.getInt(1));
	    		    bed.setYear(rs.getString(2));
	    		    bed.setType(rs.getString(3));
	    		    bed.setType_delivery(rs.getString(4));
	    		    bed.setIndications(rs.getString(5));
	    		    bed.setCoamplications(rs.getString(6));
	    		    bed.setInstitution(rs.getString(7));
	    		    list.add(bed);
	    	 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		 
		return list;
	}
	public ArrayList<Bed> getGynicObsListByGynicId(String gynicid)  {
		
	     ArrayList<Bed> list= new ArrayList<Bed>();	
		
	     try {
	    	 
	    	 String sql="select id, year, obs_type, type_delivery, indications, coamplications, institution, notes from ipd_gynic_obs_history where gynicid="+gynicid+"";
	    	 PreparedStatement ps=connection.prepareStatement(sql);
	    	 ResultSet rs= ps.executeQuery();
	    	 while(rs.next() ){
	    		 
	    		    Bed bed=new Bed();
	    		    bed.setId(rs.getInt(1));
	    		    bed.setYear(rs.getString(2));
	    		    bed.setType(rs.getString(3));
	    		    bed.setType_delivery(rs.getString(4));
	    		    bed.setIndications(rs.getString(5));
	    		    bed.setCoamplications(rs.getString(6));
	    		    bed.setInstitution(rs.getString(7));
	    		    list.add(bed);
	    	 }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		 
		return list;
	}

	public int updateGynicObsData(Bed bed) {
		int result =0;
		try {
			
			String sql="update ipd_gynic_obs_history set year=?, obs_type=?, type_delivery=?, indications=?, coamplications=?, institution=?, notes=? where id="+bed.getId()+" ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, bed.getYear());
			ps.setString(2, bed.getType());
			ps.setString(3, bed.getType_delivery());
			ps.setString(4, bed.getIndications());
			ps.setString(5, bed.getCoamplications());
			ps.setString(6, bed.getInstitution());
			ps.setString(7, "");
			
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	//@ruchi ward name
	
	public String getWardName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT wardname FROM apm_ipd_ward where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//@ruchi ward Bed
	
		public String getBedName(String id) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT bedname FROM apm_ipd_bed where id = "+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
	
		//@ruchi thirdparty name
		
			public String getThirdPartyName(String id) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT company_name FROM apm_third_party_details where id = "+id+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getString(1);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return result;
			}

			public int updateCasualtybedEmpty(int id) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "update ipd_addmission_form set bedid=0 where id="+id+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					result = preparedStatement.executeUpdate();
					updateAdmissionFormInParent(id);
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public int getMaxIpdId() {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "SELECT max(ipdseqno) FROM ipd_addmission_form where casualty=0 ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getInt(1);
					}
					
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public int getMaxCasualtyID() {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "SELECT max(ipdseqno) FROM ipd_addmission_form where casualty = 1 ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getInt(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}


			public int getMaxDayCareId() {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "SELECT max(ipdseqno) FROM ipd_addmission_form where casualty = 2 ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getInt(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			
			
			
			
			public int leftCasualtyPatient(String selectedid) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "update ipd_addmission_form set bedid=0 where id = "+selectedid+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					result = preparedStatement.executeUpdate();
					
					updateAdmissionFormInParent(DateTimeUtils.convertToInteger(selectedid));
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public ArrayList<Bed> getAllMasterWardList() {
				
				ArrayList<Bed> beds= new ArrayList<Bed>();
				try {
					String sql="select id,wardname,increment,procedures from apm_ipd_ward order by id desc ";
							
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						Bed bed = new Bed();
						int id = rs.getInt(1);
						String wardname = rs.getString(2);
						int increment=rs.getInt(3);
						int procedure=rs.getInt(4);
						bed.setId(id);
						bed.setWardname(wardname);
						bed.setIncrement(increment);
						bed.setProcedure(procedure);
						beds.add(bed);
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				return beds;
			}

			public boolean checkBedidExixtst(String bedid) {
				PreparedStatement preparedStatement = null;
				boolean result = false;
				String sql = "SELECT * FROM ipd_addmission_form where bedid = "+bedid+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = true;
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public String getHospitalCutoffTime(int clinicid) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "select auto_charge_time from apm_user where id="+clinicid+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getString(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public ArrayList<Priscription>  gettreatmentlist(String selectedid) {
			
			 ArrayList<Priscription> treatmentlist= new ArrayList<Priscription>();
			PreparedStatement ps =null;
			StringBuffer buffer = new StringBuffer();
			String sql= "";
			try{
			buffer.append(" select  a.mdicinename, a.dose,a.days, a.id from apm_client_priscription a ");
			buffer.append(" inner join apm_client_parent_priscription b on b.id= a.parentid ");
			buffer.append("  inner join ipd_addmission_form c on c.id= b.ipdid ");
			buffer.append(" where  c.id= '"+selectedid+"'  and b.discharge='0' ");
			sql=buffer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
			Priscription prisc = new Priscription();
			prisc.setMdicinenametxt(rs.getString(1));
			prisc.setDosage(rs.getString(2));
			prisc.setDays(rs.getInt(3));
			prisc.setPrischildid(rs.getString(4));
			treatmentlist.add(prisc);
			}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
				return treatmentlist;
			}

			public ArrayList<Investigation> getinvestigationlist(String selectedid) {
				 ArrayList<Investigation> investigationlist = new ArrayList<Investigation>();
					PreparedStatement ps =null;
					StringBuffer buffer = new StringBuffer();
					String sql= "";
					try{
						buffer.append(" select a.invsttype,a.id,a.parentid,a.lastmodified from apm_client_investigation a ");
						buffer.append(" inner join apm_client_parent_investigation b on b.id= a.parentid ");
						buffer.append(" inner join ipd_addmission_form c on c.id= b.ipdid ");
						buffer.append(" where  c.id='"+selectedid+"' and ininvestigation='0' group by invsttype ");
						sql= buffer.toString();
						ps= connection.prepareStatement(sql);
						ResultSet rs= ps.executeQuery();
						while(rs.next()){
							Investigation invst= new Investigation();
							invst.setInvsttype(rs.getString(1));
							invst.setId(rs.getInt(2));
							invst.setParentid(rs.getInt(3));
							investigationlist.add(invst);
							String date=rs.getString(4);
							String date1[]= date.split("-");
					String s[]=date1[2].split(" ");
					date= s[0]+"-"+date1[1]+"-"+date1[0]+"--"+s[1];
					invst.setDate(date);
						}
						
					}catch(Exception e){
						e.printStackTrace();
						
					}
					
				return investigationlist;
			}

			public Bed getRecentWardid(int id, String wardid) {
				PreparedStatement preparedStatement = null;
				String sql = "select wardid,commencing from apm_invoice_assesments where ipdid='"+id+"' and apmttype='bed charges' group by wardid order by commencing desc limit 0,1 ";
				Bed bed = new Bed();
				try{
					
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						
						bed.setId(rs.getInt(1));
						bed.setCommencing(rs.getString(2));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return bed;
			}

			
			public int updateCasualtybedEmptyByClient(String  clientidid) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "update ipd_addmission_form set bedid=0 where clientid="+clientidid+" and casualty!='0' ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					result = preparedStatement.executeUpdate();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public boolean isDayCare(String ipdid) {
				boolean isdaycare=false;
				try {
					PreparedStatement ps=connection.prepareStatement("select casualty from ipd_addmission_form where id='"+ipdid+"' ");
					ResultSet rs= ps.executeQuery();
					while (rs.next()) {
						if(rs.getInt(1)==2){
							isdaycare=true;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isdaycare;
			}

			public boolean checkAlreadyAutoChargeApplied(String dt, String clientid, int id, String chargename) {
				boolean flag =false;
				try {
					 String sql = "select id from apm_invoice_assesments where commencing='"+dt+"' and  clientid='"+clientid+"' and std_charge_id>0 and ipdid='"+id+"' and apmttype='"+chargename+"' limit 1";
					   PreparedStatement preparedStatement = connection.prepareStatement(sql);
					   ResultSet rs = preparedStatement.executeQuery();
					   if(rs.next()){
						   flag = true; 
					   }
				} catch (Exception e) {
					e.printStackTrace();
				}
				return flag;
			}

			public int updateAdmissionDate(int id, String admissiondate) {
				int res =0;
				try {
					String sql ="update ipd_addmission_form set admissiondsate='"+admissiondate+"' where id='"+id+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					res = preparedStatement.executeUpdate();
					
					updateAdmissionFormInParent(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public ArrayList<Bed> getAllWardList(String action, String wardid, int usertype) {
				ArrayList<Bed> beds = new ArrayList<Bed>();
				
				
				try {
					
					StringBuffer buffer = new StringBuffer();
					buffer.append("select apm_ipd_ward.id,wardname,increment,procedures  from apm_ipd_ward ");
					buffer.append("inner join apm_ipd_bed on apm_ipd_bed.wardid = apm_ipd_ward.id ");
					buffer.append("where casualty = "+action+" ");
					if(usertype==2){
						
					}else{
						buffer.append("and apm_ipd_ward.id in ("+wardid+") ");
					}
					buffer.append("group by apm_ipd_bed.wardid order by apm_ipd_ward.id desc ");
					PreparedStatement ps = connection
							.prepareStatement(buffer.toString());
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						Bed bed = new Bed();
						bed.setId(rs.getInt(1));
						bed.setWardname(rs.getString(2));
						bed.setIncrement(rs.getInt(3));
						bed.setProcedure(rs.getInt(4));
						beds.add(bed);
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				return beds;
			}

			public ArrayList<Master> getAllWardListNew(String action, String wardid, int usertype) {
				ArrayList<Master> beds = new ArrayList<Master>();
				
				try {
					
					StringBuffer buffer = new StringBuffer();
					buffer.append("select apm_ipd_ward.id,wardname,increment,procedures  from apm_ipd_ward ");
					buffer.append("inner join apm_ipd_bed on apm_ipd_bed.wardid = apm_ipd_ward.id ");
					buffer.append("where casualty = "+action+" ");
					if(usertype==2){
						
					}else{
						buffer.append("and apm_ipd_ward.id in ("+wardid+") ");
					}
					buffer.append("group by apm_ipd_bed.wardid order by apm_ipd_ward.id desc ");
					PreparedStatement ps = connection
							.prepareStatement(buffer.toString());
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						Master bed = new Master();
						int id = rs.getInt(1);
						String wardname = rs.getString(2);
						
						bed.setId(id);
						bed.setName(wardname);
						beds.add(bed);
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				return beds;
			}

			public int updateUseridInTable(String userid, String treatmentepisodeId, String colname) {
				int res=0;
				try {
					PreparedStatement ps= connection.prepareStatement(" update apm_treatment_episode set "+colname+"='"+userid+"' where id='"+treatmentepisodeId+"'  ");
					res=ps.executeUpdate();
					
					updateDischargeForm(treatmentepisodeId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public String getLastDischargeUserId(String treatmentepisodeId, String colname) {
				String result="",datecol=""; 
				try {
					if(colname.equals("endeduserid")){
						datecol="discharge_end_date";
					}else{
						datecol="finalmodifydate";
					}
					PreparedStatement ps= connection.prepareStatement(" select "+colname+","+datecol+" from apm_treatment_episode where id='"+treatmentepisodeId+"'");
					ResultSet rs=ps.executeQuery();
					while (rs.next()) {
						result=DateTimeUtils.isNull(rs.getString(1));
						String date=DateTimeUtils.isNull(rs.getString(2));
						if(date.contains(":")){
							date= DateTimeUtils.getCommencingDate1(date.split(" ")[0])+" "+ date.split(" ")[1];
						}
						result=result+" [ "+date+" ]";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public Bed newDischargeCardDetails(String ipdid) {
				StringBuffer buffer=  new StringBuffer();
				buffer.append(" past_hist_HTN,past_hist_DM,past_hist_IHD,past_hist_Other,past_hist_CVE,past_hist_br_asthama,past_hist_COAD,past_hist_Thyroid");
				buffer.append(",past_hist_HTN_text,past_hist_DM_text,past_hist_IHD_text,past_hist_Other_text,past_hist_CVE_text,past_hist_br_asthama_text,past_hist_COAD_text,past_hist_Thyroid_text");
				buffer.append(",person_hist_Smoking,person_hist_Alchohol,person_hist_OtherAddt,person_hist_Bowel_Bladder,person_hist_Sleep,person_hist_Tobacco");
				buffer.append(",person_hist_Smoking_text,person_hist_Alchohol_text,person_hist_OtherAddt_text,person_hist_Bowel_Bladder_text,person_hist_Sleep_text,person_hist_Tobacco_text");
				buffer.append(",obng_menstrual_hist,obng_gpla,obng_tubesctomy,obng_lmp");
				buffer.append(",apearnace_Pallor,apearnace_Cynosis,apearnace_Clubbing,apearnace_Icterus,apearnace_ln,sys_exa_CVS,sys_exa_RS,sys_exa_CNS,sys_exa_PA,sys_exa_PVPR,sys_exa_Others");
				buffer.append(",gen_cond_Temp,gen_cond_Pulse,gen_cond_BP,gen_cond_CVS,gen_cond_PS,gen_cond_CNS,physio_th_adv_Mobilization,physio_th_adv_fallRisk,physio_th_adv_Driving,physio_th_adv_sexual_Activity,dietary_advice,local_relevant_area,tubes_and_training,line_tube_drains,when_to_get_help");
				buffer.append(",fm_hist_hypertension,fm_hist_asthma,fm_hist_heart_disease,fm_hist_stroke,fm_hist_diabetes,fm_hist_arthritis_gout,fm_hist_tuberculosis,fm_hist_cancer,fm_hist_epilepsy,fm_hist_other_chronic");					
				buffer.append(",call_for_appointmant,consent_sign");						
				Bed bed= new Bed();
				try {
					String sql="  select * from his_new_discharge_fields where ipdid='"+ipdid+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					

										
					while (rs.next()) {
						bed.setIpdid(ipdid);
						bed.setTreatmentepisodeid(DateTimeUtils.isNull(rs.getString("treat_id")));
						bed.setPast_hist_HTN(DateTimeUtils.isNull(rs.getString("past_hist_HTN")));
						bed.setPast_hist_DM(DateTimeUtils.isNull(rs.getString("past_hist_DM")));
						bed.setPast_hist_IHD(DateTimeUtils.isNull(rs.getString("past_hist_IHD")));
						bed.setPast_hist_Other(DateTimeUtils.isNull(rs.getString("past_hist_Other")));
						bed.setPast_hist_CVE(DateTimeUtils.isNull(rs.getString("past_hist_CVE")));
						bed.setPast_hist_br_asthama(DateTimeUtils.isNull(rs.getString("past_hist_br_asthama")));
						bed.setPast_hist_COAD(DateTimeUtils.isNull(rs.getString("past_hist_COAD")));
						bed.setPast_hist_Thyroid(DateTimeUtils.isNull(rs.getString("past_hist_Thyroid")));
						bed.setPast_hist_HTN_text(DateTimeUtils.isNull(rs.getString("past_hist_HTN_text")));
						bed.setPast_hist_DM_text(DateTimeUtils.isNull(rs.getString("past_hist_DM_text")));
						bed.setPast_hist_IHD_text(DateTimeUtils.isNull(rs.getString("past_hist_IHD_text")));
						bed.setPast_hist_Other_text(DateTimeUtils.isNull(rs.getString("past_hist_Other_text")));
						bed.setPast_hist_CVE_text(DateTimeUtils.isNull(rs.getString("past_hist_CVE_text")));
						bed.setPast_hist_br_asthama_text(DateTimeUtils.isNull(rs.getString("past_hist_br_asthama_text")));
						bed.setPast_hist_COAD_text(DateTimeUtils.isNull(rs.getString("past_hist_COAD_text")));
						bed.setPast_hist_Thyroid_text(DateTimeUtils.isNull(rs.getString("past_hist_Thyroid_text")));

						bed.setPerson_hist_Smoking(DateTimeUtils.isNull(rs.getString("person_hist_Smoking")));
						bed.setPerson_hist_Alchohol(DateTimeUtils.isNull(rs.getString("person_hist_Alchohol")));
						bed.setPerson_hist_OtherAddt(DateTimeUtils.isNull(rs.getString("person_hist_OtherAddt")));
						bed.setPerson_hist_Bowel_Bladder(DateTimeUtils.isNull(rs.getString("person_hist_Bowel_Bladder")));
						bed.setPerson_hist_Sleep(DateTimeUtils.isNull(rs.getString("person_hist_Sleep")));
						bed.setPerson_hist_Tobacco(DateTimeUtils.isNull(rs.getString("person_hist_Tobacco")));
						bed.setPerson_hist_Smoking_text(DateTimeUtils.isNull(rs.getString("person_hist_Smoking_text")));
						bed.setPerson_hist_Alchohol_text(DateTimeUtils.isNull(rs.getString("person_hist_Alchohol_text")));
						bed.setPerson_hist_OtherAddt_text(DateTimeUtils.isNull(rs.getString("person_hist_OtherAddt_text")));
						bed.setPerson_hist_Bowel_Bladder_text(DateTimeUtils.isNull(rs.getString("person_hist_Bowel_Bladder_text")));
						bed.setPerson_hist_Sleep_text(DateTimeUtils.isNull(rs.getString("person_hist_Sleep_text")));
						bed.setPerson_hist_Tobacco_text(DateTimeUtils.isNull(rs.getString("person_hist_Tobacco_text")));
						bed.setObng_menstrual_hist(DateTimeUtils.isNull(rs.getString("obng_menstrual_hist")));
						bed.setObng_gpla(DateTimeUtils.isNull(rs.getString("obng_gpla")));
						bed.setObng_lmp(DateTimeUtils.isNull(rs.getString("obng_lmp")));
						bed.setObng_tubesctomy(DateTimeUtils.isNull(rs.getString("obng_tubesctomy")));
						bed.setApearnace_Pallor(DateTimeUtils.isNull(rs.getString("apearnace_Pallor")));
						bed.setApearnace_Cynosis(DateTimeUtils.isNull(rs.getString("apearnace_Cynosis")));
						bed.setApearnace_Clubbing(DateTimeUtils.isNull(rs.getString("apearnace_Clubbing")));
						bed.setApearnace_Icterus(DateTimeUtils.isNull(rs.getString("apearnace_Icterus")));
						bed.setApearnace_ln(DateTimeUtils.isNull(rs.getString("apearnace_ln")));
						
						bed.setSys_exa_CVS(DateTimeUtils.isNull(rs.getString("sys_exa_CVS")));
						bed.setSys_exa_RS(DateTimeUtils.isNull(rs.getString("sys_exa_RS")));
						bed.setSys_exa_CNS(DateTimeUtils.isNull(rs.getString("sys_exa_CNS")));
						bed.setSys_exa_PA(DateTimeUtils.isNull(rs.getString("sys_exa_PA")));
						bed.setSys_exa_PVPR(DateTimeUtils.isNull(rs.getString("sys_exa_PVPR")));
						bed.setSys_exa_Others(DateTimeUtils.isNull(rs.getString("sys_exa_Others")));
						
						bed.setGen_cond_Temp(DateTimeUtils.isNull(rs.getString("gen_cond_Temp")));
						bed.setGen_cond_Pulse(DateTimeUtils.isNull(rs.getString("gen_cond_Pulse")));
						bed.setGen_cond_BP(DateTimeUtils.isNull(rs.getString("gen_cond_BP")));
						bed.setGen_cond_CVS(DateTimeUtils.isNull(rs.getString("gen_cond_CVS")));
						bed.setGen_cond_PS(DateTimeUtils.isNull(rs.getString("gen_cond_PS")));
						bed.setGen_cond_CNS(DateTimeUtils.isNull(rs.getString("gen_cond_CNS")));
						bed.setPhysio_th_adv_Mobilization(DateTimeUtils.isNull(rs.getString("physio_th_adv_Mobilization")));
						bed.setPhysio_th_adv_fallRisk(DateTimeUtils.isNull(rs.getString("physio_th_adv_fallRisk")));
						bed.setPhysio_th_adv_Driving(DateTimeUtils.isNull(rs.getString("physio_th_adv_Driving")));
						bed.setPhysio_th_adv_sexual_Activity(DateTimeUtils.isNull(rs.getString("physio_th_adv_sexual_Activity")));
						bed.setDietary_advice(DateTimeUtils.isNull(rs.getString("dietary_advice")));
						bed.setLocal_relevant_area(DateTimeUtils.isNull(rs.getString("local_relevant_area")));
						bed.setTubes_and_training(DateTimeUtils.isNull(rs.getString("tubes_and_training")));
						bed.setLine_tube_drains(DateTimeUtils.isNull(rs.getString("line_tube_drains")));
						bed.setWhen_to_get_help(DateTimeUtils.isNull(rs.getString("when_to_get_help")));
						bed.setApearnace_Oedema(DateTimeUtils.isNull(rs.getString("apearnace_Oedema")));
						bed.setGeneral_other(DateTimeUtils.isNull(rs.getString("general_other")));
						bed.setFm_hist_hypertension(DateTimeUtils.isNull(rs.getString("fm_hist_hypertension")));
						bed.setFm_hist_asthma(DateTimeUtils.isNull(rs.getString("fm_hist_asthma")));
						bed.setFm_hist_heart_disease(DateTimeUtils.isNull(rs.getString("fm_hist_heart_disease")));
						bed.setFm_hist_stroke(DateTimeUtils.isNull(rs.getString("fm_hist_stroke")));
						bed.setFm_hist_diabetes(DateTimeUtils.isNull(rs.getString("fm_hist_diabetes")));
						bed.setFm_hist_arthritis_gout(DateTimeUtils.isNull(rs.getString("fm_hist_arthritis_gout")));
						bed.setFm_hist_tuberculosis(DateTimeUtils.isNull(rs.getString("fm_hist_tuberculosis")));
						bed.setFm_hist_cancer(DateTimeUtils.isNull(rs.getString("fm_hist_cancer")));
						bed.setFm_hist_epilepsy(DateTimeUtils.isNull(rs.getString("fm_hist_epilepsy")));
						bed.setFm_hist_other_chronic(DateTimeUtils.isNull(rs.getString("fm_hist_other_chronic")));
						bed.setCall_for_appointmant(DateTimeUtils.isNull(rs.getString("call_for_appointmant")));
						bed.setConsent_sign(DateTimeUtils.isNull(rs.getString("consent_sign")));


						bed.setFm_hist_hypertension_text(DateTimeUtils.isNull(rs.getString("fm_hist_hypertension_text")));
						bed.setFm_hist_asthma_text(DateTimeUtils.isNull(rs.getString("fm_hist_asthma_text")));
						bed.setFm_hist_heart_disease_text(DateTimeUtils.isNull(rs.getString("fm_hist_heart_disease_text")));
						bed.setFm_hist_stroke_text(DateTimeUtils.isNull(rs.getString("fm_hist_stroke_text")));
						bed.setFm_hist_arthritis_gout_text(DateTimeUtils.isNull(rs.getString("fm_hist_arthritis_gout_text")));
						bed.setFm_hist_tuberculosis_text(DateTimeUtils.isNull(rs.getString("fm_hist_tuberculosis_text")));
						bed.setFm_hist_cancer_text(DateTimeUtils.isNull(rs.getString("fm_hist_cancer_text")));
						bed.setFm_hist_epilepsy_text(DateTimeUtils.isNull(rs.getString("fm_hist_epilepsy_text")));
						bed.setFm_hist_diabetes_text(DateTimeUtils.isNull(rs.getString("fm_hist_diabetes_text")));
						bed.setFm_hist_other_chronic_text(DateTimeUtils.isNull(rs.getString("fm_hist_other_chronic_text")));
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
				return bed;
			}

			public int makeEntryToNewDischargeFields(String clientId, String ipdid, String treatmentEpiId) {
				int res=0;
				try {
					String sql="  insert into his_new_discharge_fields(client_Id,ipdid,treat_id) values(?,?,?) ";
					PreparedStatement ps=connection.prepareStatement(sql);
					ps.setString(1, clientId);
					ps.setString(2, ipdid);
					ps.setString(3, treatmentEpiId);
					res=ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public boolean entryExistsInNewDischargeFields(String ipdid) {
				boolean flag=false;
				try {
					String sql="  select * from his_new_discharge_fields where ipdid='"+ipdid+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while (rs.next()) {
						flag=true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return flag;
			}

			public int saveNewFieldsData(String ipdid, String column, String value) {
				int res=0;
				try {
					String sql=" update his_new_discharge_fields set "+column+"=? where ipdid='"+ipdid+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ps.setString(1, value);
					res=ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public int saveTreatmentEpisodeFieldsData(String treatmentId, String column, String value) {
				int res=0;
				try {
					String sql=" update apm_treatment_episode set "+column+"=? where id='"+treatmentId+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ps.setString(1, value);
					res=ps.executeUpdate();
					
					updateDischargeForm(treatmentId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}
			
			
			public int bedCountOfWard(String wardid) {
				int res=0;
				try {
					String sql="select count(*) from apm_ipd_bed where active ='1' and wardid='"+wardid+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while (rs.next()) {
						res=rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public String dischargeEnDateOfPatient(String treatmentepisode) {
				String date="";
				String sql="select dischargedate from apm_treatment_episode where id='"+treatmentepisode+"'";
				try {
					PreparedStatement ps= connection.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while (rs.next()) {
						date=DateTimeUtils.isNull(rs.getString(1));
						if(!date.contains(" ")){
							date="";
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				return date;
			}

			public ArrayList<Bed> getNewGynicObsListByGynicId(String id) {

				
			     ArrayList<Bed> list= new ArrayList<Bed>();	
				
			     try {
			    	 
			    	 String sql="select id, year, obs_type, type_delivery, indications, coamplications, institution, notes from ipd_gynic_obs_history where newgynicid="+id+"";
			    	 PreparedStatement ps=connection.prepareStatement(sql);
			    	 ResultSet rs= ps.executeQuery();
			    	 int check_indications=0;
			    	 int check_coamplications=0;
			    	 int check_institution=0;
			    	 while(rs.next() ){
			    		 
			    		    Bed bed=new Bed();
			    		    bed.setId(rs.getInt(1));
			    		    bed.setYear(rs.getString(2));
			    		    bed.setType(rs.getString(3));
			    		    bed.setType_delivery(rs.getString(4));
			    		    bed.setIndications(rs.getString(5));
			    		    bed.setCoamplications(rs.getString(6));
			    		    bed.setInstitution(rs.getString(7));
			    		    if(!DateTimeUtils.isNull(rs.getString(5)).equals("")){
			    		    	check_indications =1;
			    		    }
			    		    if(!DateTimeUtils.isNull(rs.getString(6)).equals("")){
			    		    	check_coamplications =1;
			    		    }
			    		    if(!DateTimeUtils.isNull(rs.getString(7)).equals("")){
			    		    	check_institution =1;
			    		    }
			    		    bed.setCheck_indications(check_indications);
			    		    bed.setCheck_coamplications(check_coamplications);
			    		    bed.setCheck_institution(check_institution);
			    		    list.add(bed);
			    	 }
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				 
				return list;
			
			}

			public int deletePreviousGynicObsData(int id) {
				int res =0;
				try {
					String sql="delete from ipd_gynic_obs_history where newgynicid='"+id+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					res = preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public String getAllWardIds() {
				String wardids="0";
				try {
					String sql ="select id from apm_ipd_ward";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						wardids = wardids+","+rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return wardids;
			}

			public int updateWardAccessforAdmin(String wardids) {
				int res =0;
				try {
					String sql="update apm_user set wardid=? where jobtitle='Admin'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, wardids);
					res = preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
						
				return res;
			}

			public String getLastVitalLmpDate(int lastappointmentid) {
				String date="";
				try {
					String sql ="select lmpd from his_bmi where apmtid='"+lastappointmentid+"' and lmpd is not null and lmpd!=''";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						date = rs.getString(1);
						String[] datetime = rs.getString(1).split("/");
						date = datetime[0]+"-"+datetime[1]+"-"+datetime[2];
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}

			@Override
			public boolean getActiveBedStaus(String selectedid) {
				boolean flag = false;
				try {
					String sql="select * from ipd_addmission_form where bedid='"+selectedid+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						flag = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return flag;
			}

			public Bed getTreatmentNAdmissiondate(String string) {
				Bed bed=new Bed();
				try {
					
					String sql="select treatmentepisodeid,admissiondsate from ipd_addmission_form where id="+string+"";
					PreparedStatement preparedStatement=connection.prepareStatement(sql);
					ResultSet rs=preparedStatement.executeQuery();
					while (rs.next()) {
						bed.setTreatmentepisodeid(rs.getString(1));
						bed.setAdmissiondate(rs.getString(2));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				return bed;
			}

			public Bed getWardIdFromIPDId(String ipdaddmissionid) {
				Bed bed = new Bed();
				try {
					String sql="select id, wardid, bedid from ipd_addmission_form where id='"+ipdaddmissionid+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						bed.setId(rs.getInt(1));
						bed.setWardid(rs.getString(2));
						bed.setBedid(rs.getString(3));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return bed;
				
			}

			@Override
			public int saveFakeIPDPatient(Client client, int clientid) {
				int res =0;
				try {
					String sql="insert into ipd_fake_data(clientid,diagnosis,doctorid,dateOfAdmission,"
							+ "dateOfDischarge,ipdabrivationid,admissionid) values(?,?,?,?,?,?,?) ";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, ""+clientid);
					preparedStatement.setString(2, client.getDiagnosis());
					preparedStatement.setString(3, client.getDrnameId());
					preparedStatement.setString(4, client.getAdmissiondate());
					preparedStatement.setString(5, client.getDischargedate());
					preparedStatement.setString(6, client.getAbrivationid());
					preparedStatement.setString(7, client.getIpdid());
					res = preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			@Override
			public String generateIPDKalmeghAbrivation(String cdate,String actionType) {
				String abrivationid = "";
				try {
					int seqno = checkifIPDSequenceExistKalmegh(cdate,actionType);
					String clinicabrivation="IP";
					if(DateTimeUtils.isNull(actionType).equals("2")){
						clinicabrivation="DC";
					}else if(DateTimeUtils.isNull(actionType).equals("1")){
						clinicabrivation="CS";
					}
					String tempd[] = cdate.split("-");
					String y = tempd[0];
					String m = tempd[1];
					String d = tempd[2];
					String newseq="";
					int yr = Integer.parseInt(y)%1000;
					yr=Integer.parseInt(y);
					if(String.valueOf(seqno).length()==1){
						 newseq="000"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==3){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
					abrivationid = clinicabrivation + yr + m +d + "" + newseq;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return abrivationid;
			}

			private int checkifIPDSequenceExistKalmegh(String cdate, String actionType) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "select seqno from ipd_sequence where commencing = '"+cdate+"' and actionType='"+actionType+"' ";
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getInt(1);
						result++;
						String querry ="update ipd_sequence set seqno='"+result+"' where commencing='"+cdate+"' and actionType='"+actionType+"' ";
						PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
						preparedStatement2.executeUpdate();
					}else{
						result =1;
						String querry ="insert into ipd_sequence(commencing,seqno,actionType) values(?,?,?)";
						PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
						preparedStatement2.setString(1, cdate);
						preparedStatement2.setString(2, ""+result);
						preparedStatement2.setString(3, actionType);
						preparedStatement2.executeUpdate();
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			@Override
			public int updateKalmeghIpdData(String diagnosis, String originalDischargeDate, String sessionadmissionid) {
				int res =0;
				try {
					String sql="update ipd_fake_data set diagnosis=?, dateOfDischarge=? ,ischeck=? where admissionid=?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, diagnosis);
					preparedStatement.setString(2, originalDischargeDate);
					preparedStatement.setInt(3, 1);
					preparedStatement.setString(4, sessionadmissionid);
					res = preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			@Override
			public int saveCFMedicineFieldsData(String id, String column, String val,String priscqtys) {
				int res=0;
				try {
					String sql=" update apm_client_priscription set "+column+"=?,dr_qty="+priscqtys+"  where id='"+id+"'";
					PreparedStatement ps= connection.prepareStatement(sql);
					ps.setString(1, val);
					res=ps.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			@Override
			public ArrayList<Bed> getbedOccupancylist(String fromDate, String toDate) {
				// TODO Auto-generated method stub
				
				PreparedStatement preparedStatement = null;
				int difference=0;
				int totaldifference=0;
				
				ArrayList<Bed> beds = new ArrayList<Bed>();
				
				String sql="select date,ipd_inhouse from maindashboard_data where date between '"+fromDate+"' and '"+toDate+"' group by date  ";
				try {

					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						Bed bed = new Bed();
						
						bed.setCommencing(rs.getString(1));
						bed.setCount(rs.getInt(2));
						
						if(bed.getCount()>218) {
							
							difference=(bed.getCount()-218);
							bed.setDifference(difference);
							totaldifference=totaldifference+difference;
							//bed.setTotaldifference(totaldifference);
						}
						
						bed.setTotaldifference(totaldifference);
				
						beds.add(bed);
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				return beds;
			}

			@Override
			public int saveNote(Bed bed) {
				
				int res=0;
				
				try {
					
					String sql="insert into request_note(date_time,note,userid,admissionid,isseen,ispunchkarma,procedureid,opdid,clientid)values(?,?,?,?,?,?,?,?,?) ";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, bed.getDatetime());
					preparedStatement.setString(2, bed.getNotes());
					preparedStatement.setString(3, bed.getUserid());
					preparedStatement.setString(4, bed.getIpdid());
					preparedStatement.setString(5, "1");
					preparedStatement.setString(6, bed.getPunchkarma());
					preparedStatement.setInt(7, bed.getProcedure());
					preparedStatement.setString(8, bed.getOpdid());
					preparedStatement.setString(9, bed.getClientid());
					res = preparedStatement.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return res;
			}

			@Override
			public int updateRequestnote(Bed bed) {
				int result=0;
				PreparedStatement pmt=null;
				
				try {
					
					String sql="update request_note set note='"+bed.getNotes()+"',procedureid='"+bed.getProcedure()+"' where id="+bed.getReqid()+"";
					
		            pmt=connection.prepareStatement(sql);
					result=pmt.executeUpdate();
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return result;
			}

			@Override
			public int isSeenOrNot(String reqid) {
				int result=0;
				PreparedStatement pmt=null;
				try {
					
                    String sql="update request_note set isseen=1 where id="+reqid+" ";
					
		            pmt=connection.prepareStatement(sql);
					result=pmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}

			@Override
			public int saveIpdPhsioDetails(Bed bed4) {
              int res=0;
				
				try {
					
					String sql="insert into bmsphysio(date,opdid,clientid,ipdid,physio)values(?,?,?,?,?) ";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, DateTimeUtils.getCommencingDate1(bed4.getFakebackdate()));
					preparedStatement.setString(2, bed4.getOpdid());
					preparedStatement.setString(3, bed4.getClientid());
					preparedStatement.setString(4, bed4.getIpdid());
					preparedStatement.setString(5, bed4.getPhysio());
					
					res = preparedStatement.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return res;
				
			}

			@Override
			public int saveOpdPhsioDetails(Bed bed1) {
				 int res=0;
					
					try {
						
						String sql="insert into bmsphysio(date,opdid,clientid,physio)values(?,?,?,?) ";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, DateTimeUtils.getCommencingDate1(bed1.getFakebackdate()));
						preparedStatement.setString(2, bed1.getOpdid());
						preparedStatement.setString(3, bed1.getClientid());
						preparedStatement.setString(4, bed1.getPhysio());
						
						res = preparedStatement.executeUpdate();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return res;
				
			}

			@Override
			public ArrayList<Bed> getPhysioList(String maindate, int ipdid,String opdid) {
				ArrayList<Bed> list=new ArrayList<>();
				PreparedStatement preparedStatement=null;
				try {
				if(ipdid!=0) {
				preparedStatement = connection.prepareStatement("select physio from bmsphysio where ipdid="+ipdid+" and date ='"+maindate+" 00:00:00'");
	
				}else {
				preparedStatement = connection.prepareStatement("select physio from bmsphysio where opdid="+opdid+" and date ='"+maindate+" 00:00:00'");
	            }
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Bed bed = new Bed();
					bed.setPhysio(rs.getString(1));
					list.add(bed);
				}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			@Override
			public String getdatebyClientid(String clientid) {
				
				String date="";
				
				try {
					String sql="select commencing from apm_available_slot where clientId='"+clientid+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						date=rs.getString(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
				
			}

			@Override
			public String getlastOpdNo(String clientid) {
              String lastopdno="";
				
				try {
					String sql="select max(id),opdabbrevationid from apm_available_slot where clientId='"+clientid+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						lastopdno=rs.getString(2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return lastopdno;
				
			}

			@Override
			public int updateOpdNo(int admissionid,String lastopdno) {
				int result=0;
				PreparedStatement pmt=null;
				try {
					
                    String sql="update ipd_addmission_form set lastopdno='"+lastopdno+"' where id="+admissionid+" ";
					
		            pmt=connection.prepareStatement(sql);
					result=pmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
				
			}

			@Override
			public String getAppointmentId(String opd_abbrivation_id) {
              String apmtid="";
				
				try {
					String sql="select id from apm_available_slot where opdabbrevationid='"+opd_abbrivation_id+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						apmtid=rs.getString(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return apmtid;
			}

			@Override
			public Vector<Bed> getBamsPhysioData(OpdConsult opdConsult2, String dbname) {
				Vector<Bed> list=new Vector<>();
				PreparedStatement preparedStatement=null;
				try {
				if(!opdConsult2.getFlag_opd().equals("0")) {
					
				preparedStatement = connection.prepareStatement("select physio,date from  "+dbname+".bmsphysio where ipdid="+opdConsult2.getOldipdid()+" ");
	
				}else {
				preparedStatement = connection.prepareStatement("select physio,date from "+dbname+".bmsphysio where opdid="+opdConsult2.getAppointmentid()+" ");
	            }
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Bed bed = new Bed();
					bed.setPhysio(rs.getString(1));
					bed.setDate(rs.getString(2));
					list.add(bed);
				}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return list;
				
			}

			@Override
			public int insertBamsPhysioData(Bed bed) {
				 int res=0;
					
					try {
						
						String sql="insert into bmsphysio(date,opdid,clientid,physio,ipdid)values(?,?,?,?,?) ";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, bed.getDate());
						preparedStatement.setString(2, bed.getOpdid());
						preparedStatement.setString(3, bed.getClientid());
						preparedStatement.setString(4, bed.getPhysio());
						preparedStatement.setString(5, bed.getIpdid());
						
						res = preparedStatement.executeUpdate();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return res;
				
			}

			@Override
			public ArrayList<Master> getAllWardListNewAyushman() {
            ArrayList<Master> beds = new ArrayList<Master>();
				
				try {
					
					StringBuffer buffer = new StringBuffer();
					buffer.append("select apm_ipd_ward.id,wardname,increment,procedures  from apm_ipd_ward ");
					buffer.append("inner join apm_ipd_bed on apm_ipd_bed.wardid = apm_ipd_ward.id ");
					buffer.append("where casualty =0 ");
					buffer.append("group by apm_ipd_bed.wardid order by apm_ipd_ward.id desc ");
					PreparedStatement ps = connection
							.prepareStatement(buffer.toString());
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						Master bed = new Master();
						int id = rs.getInt(1);
						String wardname = rs.getString(2);
						
						bed.setId(id);
						bed.setName(wardname);
						beds.add(bed);
					}

				} catch (Exception e) {

					e.printStackTrace();
				}

				return beds;
				
			}

}
