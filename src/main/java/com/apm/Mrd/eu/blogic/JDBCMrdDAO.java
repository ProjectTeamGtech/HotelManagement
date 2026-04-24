package com.apm.Mrd.eu.blogic;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdLogDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.ShelfMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCShelfMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.ShelfMaster;
import com.apm.Mrd.eu.bi.MrdDAO;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;

public class JDBCMrdDAO extends BaseAction implements MrdDAO {

	Connection connection;

	public JDBCMrdDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Mrd> getmrddetails(String fromdate,String todate,String searchText,String wardnameid) {
		ArrayList<Mrd> arrayList = new ArrayList<Mrd>();
		try {
			
			StringBuffer buffer = new StringBuffer();
			boolean flag=false;
			
				
				buffer.append("select ipd_addmission_form.id, ipd_addmission_form.clientid, ipd_addmission_form.bedid, ipd_addmission_form.wardid, ipd_addmission_form.admissiondsate ");
				buffer.append("from ipd_addmission_form ");
				
				if(searchText!=null){
					flag=true;
					buffer.append("INNER JOIN apm_patient ON  ipd_addmission_form.clientid = apm_patient.id  ");
					buffer.append("where apm_patient.firstname like ('"+searchText+"%') or surname like ('%"+searchText+"')  ");
				}
				if(flag){
					buffer.append("and  admissiondsate between '"+fromdate+"' and '"+todate+"'  ");
				}
				else {
					buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"'  ");
				}
				
				if(wardnameid!=null){
					buffer.append("and ipd_addmission_form.wardid="+wardnameid+" ");
				}
				String query = buffer.toString();
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Mrd mrd = new Mrd();
				mrd.setIpdid(rs.getInt(1));
				mrd.setClientid(rs.getString(2));
				mrd.setBedid(rs.getString(3));
				mrd.setWardid(rs.getString(4));
				String admissiondatetime = rs.getString(5);
				//mrd.setAdmissiondsate(rs.getString(5));
				//String firstname = rs.getString(6);
				//String surname = rs.getString(7);
				
				String[] splited = admissiondatetime.split(" ");
				String admissiondate = splited[0];
				String admissiondate1 = DateTimeUtils.getCommencingDate2(admissiondate);
				mrd.setAdmissiondsate(admissiondate1);
				
				String wardid = mrd.getWardid();
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(wardid);
				mrd.setWardname(wardname);
				
				String bedname = ipdDAO.getIpdBedName(mrd.getBedid());
				mrd.setBedname(bedname);
				
				mrd.setFromdate(fromdate);
				mrd.setTodate(todate);
				
				IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
				int ipdid = mrd.getIpdid();
				
				String dischargedatetime = ipdLogDAO.getDischargeDate(ipdid);			
				if(dischargedatetime=="")
					dischargedatetime = null;
				
				if(dischargedatetime==null || dischargedatetime.equals(null)){
					mrd.setDischargedate(dischargedatetime);
				}else{
					String[] split1 = dischargedatetime.split(" ");
					String dischargedate = split1[0];	
					String[] temp = dischargedate.split("-");
					dischargedate = temp[2]+"/"+temp[1]+"/"+ temp[0];
					mrd.setDischargedate(dischargedate);
				}
				
				if(bedname.equals("")){
					String bedid = ipdLogDAO.getDischargeBedId(""+ipdid);	
					String newbedname = ipdDAO.getIpdBedName(bedid);
					mrd.setBedname(newbedname);
				}
				
				Mrd mrd1 = getmrdinfo(ipdid);
				String shelf_no = mrd1.getShelf_no();
				String place = mrd1.getPlace();
				String remark = mrd1.getRemark();
				String mlc = mrd1.getMlc();
				mrd.setShelf_no(shelf_no);
				mrd.setPlace(place);
				mrd.setRemark(remark);
				mrd.setMlc(mlc);
				String status = mrd1.getStatus();
				mrd.setStatus(status);
				mrd.setId(mrd1.getId());
			
				String checklist = mrd1.getChecklist();
				mrd.setChecklist(mrd1.getChecklist());
				
				if(mrd1.getId()>0){
					String sql ="select id from apm_assessment_client_details where mrdid="+mrd1.getId()+"";
					PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
					ResultSet rs1 = preparedStatement2.executeQuery();
					while (rs1.next()) {
						String assessmentid = ""+rs1.getInt(1);
						mrd.setAssessmenetid(""+rs1.getInt(1));
					}
				}
				//mrd.setIpdid(mrd1.getId());
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(mrd.getClientid());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				mrd.setClientname(clientname);
				mrd.setWhopay(client.getWhopay());
				//bed.setTpid(client.getTypeName());
				if(mrd.getWhopay()!=null){
					
					 if(mrd.getWhopay().equalsIgnoreCase("Client") || mrd.getWhopay().equalsIgnoreCase("Self")){
						 mrd.setPayby("SELF");
					 } else {
						 mrd.setPayby("TP");
//						 ThirdParty thirdParty=client.getTpDetails();
//						 mrd.setPayby(thirdParty.getShortname());
						 	ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
							mrd.setPayby(thirdParty.getCompanyName());
						 
					 }
//					 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
//						ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
//						mrd.setTpname(thirdParty.getCompanyName());
//				
				}		
				arrayList.add(mrd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	
	public Mrd getmrdinfo(int ipdid) {
		Mrd mrd1 = new Mrd();
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT ipdid,clientid, shelf_no, place, remark, mlc, status,id,checklist  FROM apm_mrd where ipdid  = " + ipdid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				mrd1 = new Mrd();
				mrd1.setIpdid(rs.getInt(1));
				mrd1.setClientid(rs.getString(2));
				mrd1.setShelf_no(rs.getString(3));
				mrd1.setPlace(rs.getString(4));
				mrd1.setRemark(rs.getString(5));
				mrd1.setMlc(rs.getString(6));
				mrd1.setStatus(rs.getString(7));
				mrd1.setId(rs.getInt(8));
				mrd1.setChecklist(rs.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mrd1;
	}

	public int updatemrdetails(Mrd mrd) {
		int result = 0;
		Boolean flag= false;
		int ipdid1 = 0;
		String clientid1 = "";
		try {
			String query = "select ipdid,clientid from apm_mrd where ipdid="+mrd.getIpdid();
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				ipdid1 = rs.getInt(1);
				clientid1 = rs.getString(2);
				flag =true;
			}
			
			if(flag == false){
				String query1 = "insert into apm_mrd (ipdid,clientid, shelf_no, place, remark, mlc,status) values(?,?,?,?,?,?,?)";
				PreparedStatement stmt1 = connection.prepareStatement(query1);
				stmt1.setInt(1, mrd.getIpdid());
				stmt1.setString(2, mrd.getClientid());
				stmt1.setString(3, mrd.getShelf_no());
				stmt1.setString(4, mrd.getPlace());
				stmt1.setString(5, mrd.getRemark());
				stmt1.setString(6, mrd.getMlc());
				stmt1.setString(7, mrd.getStatus());
				result = stmt1.executeUpdate();
			}
			else{
				int id = ipdid1;
				String query2 = "update apm_mrd set shelf_no=?, place=?, remark=?, mlc=?, status=? where ipdid="+ipdid1;
				PreparedStatement stmt2 = connection.prepareStatement(query2);
				stmt2.setString(1, mrd.getShelf_no());
				stmt2.setString(2, mrd.getPlace());
				stmt2.setString(3, mrd.getRemark());
				stmt2.setString(4, mrd.getMlc());
				stmt2.setString(5, mrd.getStatus());
				result = stmt2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int changeMrdStatus(String id) {	
		int result =0;
		try {
				int ipdid = Integer.parseInt(id);
				String query2 = "update apm_mrd set status=? where ipdid="+ipdid;
				PreparedStatement stmt2 = connection.prepareStatement(query2);
				stmt2.setString(1, "0");
				result = stmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int setMrdCheckList(String mrd_clientid, String mrd_ipdid,String templateId) {
		int result =0;
		try {
			String query = "insert into apm_mrd(clientid, ipdid,checklist,templateId,status) values(?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, mrd_clientid);
			preparedStatement.setInt(2, Integer.parseInt(mrd_ipdid));
			preparedStatement.setString(3, "1");
			preparedStatement.setString(4, templateId);
			preparedStatement.setString(5, "1");
			
			result = preparedStatement.executeUpdate();
			if(result>0)
			{
				ResultSet resultSet=preparedStatement.getGeneratedKeys();
				while(resultSet.next()){
				     result= resultSet.getInt(1);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String gettemplateid() {
		String templateid = "";
		try {
			String sql = "select id from apm_assment_template where templateName='MRD CheckList'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				templateid = ""+resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateid;
	}

	public int updateMrdChecklist(String templateId,String mrdid) {
		int result =0;
		try {
			String sql ="update apm_mrd set checklist=1, templateId="+templateId+" where id="+mrdid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Mrd> getbedlistFromWardId(String id) {
		ArrayList<Mrd> arrayList = new ArrayList<Mrd>();
		try {
			//String sql = "select id,bedname from apm_ipd_bed where wardid='"+id+"'";
			StringBuilder builder = new StringBuilder();
			builder.append("select id,bedname from apm_ipd_bed ");
			if(id!=null){
				builder.append("where wardid='"+id+"'");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Mrd mrd = new Mrd();
				mrd.setId(rs.getInt(1));
				mrd.setBedname(rs.getString(2));
				arrayList.add(mrd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getIpdidFromClientId(String clientid) {
		String ipdid ="";
		try {
			String sql ="select id from ipd_addmission_form where clientid="+clientid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ipdid = ""+resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipdid;
	}

	
	public int saveMrdDetails(Mrd mrd) {
		int result =0;
		try {
			String sql ="insert into apm_mrd(clientid, shelf_no, place, remark, mlc, ipdid, patientFrom, admissiondate, dischargedate, wardid, bedid, practitionerid, speciality, whopay, type, typename, policyno, expirydate, policyexcess,mlcno,lastmodfied) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mrd.getClientid());
			preparedStatement.setString(2, mrd.getShelf_no());
			preparedStatement.setString(3, mrd.getPlace());
			preparedStatement.setString(4, mrd.getRemark());
			preparedStatement.setString(5, mrd.getMlc());
			preparedStatement.setInt(6, mrd.getIpdid());
			preparedStatement.setString(7, mrd.getPatientFrom());
			preparedStatement.setString(8, mrd.getAdmissiondsate());
			preparedStatement.setString(9, mrd.getDischargedate());
			preparedStatement.setString(10, mrd.getWardid());
			preparedStatement.setString(11, mrd.getBedid());
			preparedStatement.setString(12, mrd.getPractitionerid());
			preparedStatement.setString(13, mrd.getSpeciality());
			preparedStatement.setString(14, mrd.getWhopay());
			preparedStatement.setString(15, mrd.getType());
			preparedStatement.setString(16, mrd.getTypeName());
			preparedStatement.setString(17, mrd.getPolicyNo());
			preparedStatement.setString(18, mrd.getExpiryDate());
			preparedStatement.setString(19, mrd.getPolicyExcess());
			preparedStatement.setString(20, mrd.getNew_mlcno());
			preparedStatement.setString(21, mrd.getLastmodified());
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public ArrayList<Mrd> getmrdlist(String fromdate,String todate,String searchText,String department, Pagination pagination, String searchbydate,String searchbyipdid,int access) {
		ArrayList<Mrd> arrayList = new ArrayList<Mrd>();
		try {
			StringBuffer buffer = new StringBuffer();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			boolean flag=false;
				todate = todate+" "+ "59:59:59";
				
				buffer.append("select apm_mrd.id, apm_mrd.clientid, apm_mrd.shelf_no, apm_mrd.place, apm_mrd.remark, apm_mrd.mlc, apm_mrd.status, apm_mrd.ipdid, checklist, templateId, patientFrom, apm_mrd.admissiondate, apm_mrd.dischargedate, apm_mrd.wardid, apm_mrd.bedid, apm_mrd.practitionerid, apm_mrd.speciality, apm_mrd.whopay, apm_mrd.type, apm_mrd.typename, apm_mrd.policyno, apm_mrd.expirydate, apm_mrd.policyexcess,apm_patient.abrivationid,apm_mrd.lastmodfied,apm_mrd.shiftstatus ");
				buffer.append("from apm_mrd ");
				buffer.append("INNER JOIN apm_patient ON  apm_mrd.clientid = apm_patient.id  ");
				if(searchText!=null){
					flag=true;
					buffer.append("where apm_patient.firstname like ('"+searchText+"%') or apm_patient.surname like ('%"+searchText+"') or apm_patient.abrivationid like ('%"+searchText+"%') or apm_mrd.ipdid='"+searchText+"' ");
				}
				/*if(flag){
					buffer.append("and  apm_mrd.admissiondate between '"+fromdate+"' and '"+todate+"'  ");
				}
				else {
					buffer.append("where apm_mrd.admissiondate between '"+fromdate+"' and '"+todate+"'  ");
				}*/
				
				if(searchbydate.equals("1")){
					if(flag){
						buffer.append("and  apm_mrd.admissiondate between '"+fromdate+"' and '"+todate+"'  ");
					}
					else {
						buffer.append(" where apm_mrd.admissiondate between '"+fromdate+"' and '"+todate+"'  ");
					}
				}else{
					if(flag){
						buffer.append("and  apm_mrd.lastmodfied between '"+fromdate+"' and '"+todate+"'  ");
					}
					else {
						buffer.append("where apm_mrd.lastmodfied between '"+fromdate+"' and '"+todate+"'  ");
					}
				}
				
				if(department!=null){
					buffer.append("and patientFrom='"+department+"' ");
				}
				 buffer.append(" and isdelete=0 ");
				 /*if(searchbyipdid!=null){
					 buffer.append("and ipdid="+searchbyipdid+" ");
				 }*/
				 buffer.append(" order by ipdid desc ");
			String query = buffer.toString();
		
			if(pagination!=null)
			{
				//query=pagination.getSQLQuery(buffer.toString());
				query= pagination.getSQLQuery(query);
			}
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Mrd mrd = new Mrd();
				mrd.setId(rs.getInt(1));
				mrd.setClientid(rs.getString(2));
				ShelfMaster master = new ShelfMaster();
				master.setId(rs.getInt(3));
				ShelfMaster shelfMaster = shelfMasterDAO.getshelfinfo(master);
				mrd.setShelf_no(shelfMaster.getName());
				mrd.setPlace(rs.getString(4));
				mrd.setRemark(rs.getString(5));
				mrd.setMlc(rs.getString(6));
				mrd.setStatus(rs.getString(7));
				if(access==1){
					String newipdabbr=ipdDAO.getIpdAbrivationIds(rs.getInt(8)); 
					mrd.setNewipdabbr(newipdabbr);
					mrd.setIpdid(Integer.parseInt(rs.getString(8)));
				}else{
				mrd.setIpdid(Integer.parseInt(rs.getString(8)));
				mrd.setNewipdabbr(rs.getString(8));
				}
				mrd.setChecklist(rs.getString(9));
				mrd.setTemplateId(rs.getString(10));
				mrd.setPatientFrom(rs.getString(11));
				
				
				if(rs.getString(12)!=null){
					if(!rs.getString(12).equals("")){
						String admissiondate = DateTimeUtils.getCommencingDate2(rs.getString(12));
						mrd.setAdmissiondsate(admissiondate);
					}
				}
				
				if(rs.getString(13)!=null){
					if(!rs.getString(13).equals("")){
						String dischargedate = DateTimeUtils.getCommencingDate2(rs.getString(13));
						mrd.setDischargedate(dischargedate);
					}
				}
				
				
				
				
				mrd.setWardid(rs.getString(14));
				mrd.setBedid(rs.getString(15));
				
				String wardid = mrd.getWardid();
				
				String wardname = ipdDAO.getIpdWardName(wardid);
				mrd.setWardname(wardname);
				
				String bedname = ipdDAO.getIpdBedName(mrd.getBedid());
				mrd.setBedname(bedname);
				
				mrd.setPractitionerid(rs.getString(16));
				mrd.setSpeciality(rs.getString(17));
				
				mrd.setWhopay(rs.getString(18));
				
				mrd.setType(rs.getString(19));
				mrd.setTypeName(rs.getString(20));
				mrd.setPolicyNo(rs.getString(21));
				mrd.setExpiryDate(rs.getString(22));
				mrd.setPolicyExcess(rs.getString(23));
				
				mrd.setAbrivationid(rs.getString(24));
				//mrd.setLastmodfied(rs.getString(25));
				String datetime = rs.getString(25);
				String[] datetime1 = datetime.split(" ");
				String[] date = datetime1[0].split("-");
				/*String finaldte = date[2]+"-"+ date[1]+"-"+ date[0] +" "+ datetime1[1];*/
				String finaldte = date[2]+"-"+ date[1]+"-"+ date[0] ;
				mrd.setLastmodfied(finaldte);
				
				//  22 dec 2017 mrd set uid
				if(rs.getString(24)!=null){
					if(rs.getString(24).equals("0") || rs.getString(24).equals("")){
						mrd.setAbrivationid(rs.getString(2));
					}else{
						mrd.setAbrivationid(rs.getString(24));
					}
				}else{
					mrd.setAbrivationid(rs.getString(2));
				}
				mrd.setShiftstatus(rs.getString(26));
			
				mrd.setFromdate(fromdate);
				mrd.setTodate(todate);
				if(department!=null){
					mrd.setPatient_department(department);
				}
				
				Client client = clientDAO.getClientDetails(mrd.getClientid());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				mrd.setClientname(clientname);
				
				if(mrd.getId()>0){
					String sql ="select id from apm_assessment_client_details where mrdid="+mrd.getId()+"";
					PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
					ResultSet rs1 = preparedStatement2.executeQuery();
					while (rs1.next()) {
						String assessmentid = ""+rs1.getInt(1);
						mrd.setAssessmenetid(""+rs1.getInt(1));
					}
				}
				
				if(mrd.getWhopay()!=null){
					 if(mrd.getWhopay().equalsIgnoreCase("Client") || mrd.getWhopay().equalsIgnoreCase("Self")){
						 mrd.setPayby("SELF");
					 } else {
						 mrd.setPayby("TP");
						 	ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(mrd.getTypeName());
							mrd.setPayby(thirdParty.getCompanyName());
					}
				}		
				arrayList.add(mrd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Mrd getPatientForEdit(String id) {
		Mrd mrd = new Mrd();
		try {
			String sql ="select id, clientid, shelf_no, place, remark, mlc, ipdid, patientFrom, admissiondate, dischargedate, wardid, bedid, practitionerid, speciality, whopay, type, typename, policyno, expirydate, policyexcess,mlcno,shiftstatus from apm_mrd where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				mrd.setId(rs.getInt(1));
				mrd.setClientid(rs.getString(2));
				mrd.setShelf_no(rs.getString(3));
				mrd.setPlace(rs.getString(4));
				mrd.setRemark(rs.getString(5));
				mrd.setMlc(rs.getString(6));
				mrd.setIpdid(Integer.parseInt(rs.getString(7)));
				mrd.setPatientFrom(rs.getString(8));
				mrd.setAdmissiondsate(rs.getString(9));
				mrd.setDischargedate(rs.getString(10));
				mrd.setWardid(rs.getString(11));
				mrd.setBedid(rs.getString(12));
				mrd.setPractitionerid(rs.getString(13));
				mrd.setSpeciality(rs.getString(14));
				mrd.setWhopay(rs.getString(15));
				mrd.setType(rs.getString(16));
				mrd.setTypeName(rs.getString(17));
				mrd.setPolicyNo(rs.getString(18));
				mrd.setExpiryDate(rs.getString(19));
				mrd.setPolicyExcess(rs.getString(20));
				mrd.setNew_mlcno(rs.getString(21));
				mrd.setMrdstatus(rs.getString(22));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mrd;
	}

	public int updateMrdInformation(Mrd mrd) {
		int result = 0;
		try {
			String query2 = "update apm_mrd set shelf_no=?, place=?, remark=?, mlc=?,mlcno=? where id="+mrd.getId();
			PreparedStatement stmt2 = connection.prepareStatement(query2);
			stmt2.setString(1, mrd.getShelf_no());
			stmt2.setString(2, mrd.getPlace());
			stmt2.setString(3, mrd.getRemark());
			stmt2.setString(4, mrd.getMlc());
			stmt2.setString(5, mrd.getNew_mlcno());
			result = stmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public String getSpecialityId(String condition) {
		String specialityid ="";
		try {
			String sql ="select id from apm_condition where name='"+condition+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specialityid = ""+resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return specialityid;
	}

	public int getTotalMrdCount(String fromdate,String todate,String searchtext,String department, String searchbydate) {
		int result=0;
		try {
			StringBuffer buffer = new StringBuffer();
			boolean flag=false;
			todate ="59:59:59";
			//lastmodfied
			buffer.append("select count(*) ");
			buffer.append("from apm_mrd ");
			buffer.append("INNER JOIN apm_patient ON  apm_mrd.clientid = apm_patient.id  ");
			if(searchtext!=null){
				flag=true;
				buffer.append("where apm_patient.firstname like ('"+searchtext+"%') or apm_patient.surname like ('%"+searchtext+"')  ");
			}
			
			if(searchbydate.equals("1")){
				if(flag){
					buffer.append("and  apm_mrd.admissiondate between '"+fromdate+"' and '"+todate+"'  ");
				}
				else {
					buffer.append("where apm_mrd.admissiondate between '"+fromdate+"' and '"+todate+"'  ");
				}
			}else{
				if(flag){
					buffer.append("and  apm_mrd.lastmodfied between '"+fromdate+"' and '"+todate+"'  ");
				}
				else {
					buffer.append("where apm_mrd.lastmodfied between '"+fromdate+"' and '"+todate+"'  ");
				}
			}
			
			 buffer.append(" and isdelete=0 ");
			
			if(department!=null){
				buffer.append("and patientFrom='"+department+"' ");
			}
			String query = buffer.toString();
			PreparedStatement stmt=connection.prepareStatement(query);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Mrd>getallshiftmrdlist(String mrdid){
		ArrayList<Mrd>arrayList = new ArrayList<Mrd>();
		try {
			
			String query = "select id, mrdid, clientid, givento, givendate, receivedfrom, receiveddate, givenuserid, receiveduserid, mrdstatus,remark from mrd_shift_record where mrdid='"+mrdid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
		    while(rs.next()){
		    	Mrd mrd = new Mrd();
		    	mrd.setId(rs.getInt(1));
		    	mrd.setMrdid(rs.getString(2));
		    	mrd.setClientid(rs.getString(3));
		    	mrd.setGivento(rs.getString(4));
		    	mrd.setGivendate(rs.getString(5));
		    	mrd.setReceivedfrom(rs.getString(6));
		    	mrd.setReceiveddate(rs.getString(7));
		    	mrd.setGivenuserid(rs.getString(8));
		    	mrd.setReceiveduserid(rs.getString(9));
		    	mrd.setMrdstatus(rs.getString(10));
		    	mrd.setRemark(rs.getString(11));
		    	arrayList.add(mrd);
		    }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayList;
		
	}

	public int saveshiftMrdDetails(Mrd mrd) {
		int result =0;
		try {
			String sql ="insert into mrd_shift_record(mrdid, clientid, givento, givendate, receivedfrom, receiveddate, givenuserid, receiveduserid, mrdstatus,remark) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1,mrd.getShiftpatientmrdid());
	        ps.setString(2,mrd.getShiftpatientid());
	        ps.setString(3,mrd.getShiftmrdgivento());
	        ps.setString(4, mrd.getShiftmrdgivendate());
	        ps.setString(5,mrd.getShiftmrdreceivedfrom());
	        ps.setString(6, mrd.getShiftmrdreceiveddate());
	        ps.setString(7,mrd.getGivenuserid());
	        ps.setString(8, mrd.getReceiveduserid());
	        ps.setString(9, "0");
	        ps.setString(10,mrd.getRemark());
	        result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
		public Mrd editshiftMrdDetails(String id){
			Mrd mrd = new Mrd();
			try {
				String sql ="select id, mrdid, clientid, givento, givendate, receivedfrom, receiveddate, givenuserid, receiveduserid, mrdstatus,remark from mrd_shift_record where id="+id+"";
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					mrd.setId(rs.getInt(1));
					mrd.setShiftpatientmrdid(rs.getString(2));
					mrd.setShiftpatientid(rs.getString(3));
					mrd.setShiftmrdgivento(rs.getString(4));
					mrd.setShiftmrdgivendate(rs.getString(5));
					mrd.setShiftmrdreceivedfrom(rs.getString(6));
					mrd.setShiftmrdreceiveddate(rs.getString(7));
					mrd.setGivenuserid(rs.getString(8));
					mrd.setReceiveduserid(rs.getString(9));
					mrd.setMrdstatus(rs.getString(10));
					mrd.setRemark(rs.getString(11));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mrd;	
	}
		public int updateshiftMrdDetails(Mrd mrd) {
			int result = 0;
			try {
				String query = "update mrd_shift_record set givento=?, givendate=?, receivedfrom=?, receiveddate=?, remark=? where id="+mrd.getId()+"";
				PreparedStatement ps = connection.prepareStatement(query);
				
				  ps.setString(1,mrd.getShiftmrdgivento());
			        ps.setString(2, mrd.getShiftmrdgivendate());
			        ps.setString(3,mrd.getShiftmrdreceivedfrom());
			        ps.setString(4, mrd.getShiftmrdreceiveddate());
			        ps.setString(5, mrd.getRemark());
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int updateShiftStatus(String shiftpatientmrdid) {
			int res=0;
			try {
				String query = "update apm_mrd set shiftstatus=?, shelf_no=? where id="+shiftpatientmrdid+"";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, "1");
				ps.setString(2, "0");
				res = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int saveeditShiftMrdDetails(String shiftmrdid) {
			int res=0;
			try {
				String query = "update apm_mrd set shiftstatus=? where id="+shiftmrdid+"";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, "0");
				res = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int cancelMrd(String id) {
			 
			  int result = 0;
			  try {
			   String sql = "update apm_mrd set isdelete=1 where id="+id+"";
			   PreparedStatement ps = connection.prepareStatement(sql);
			   result = ps.executeUpdate();
			  } catch (Exception e) {

			   e.printStackTrace();
			  }

			  return result;
			 }
		


	}
	
	
	
	

