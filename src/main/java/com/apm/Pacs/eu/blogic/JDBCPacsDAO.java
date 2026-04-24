package com.apm.Pacs.eu.blogic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;




import com.a.a.a.g.m.p;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Master.eu.entity.Master;
import com.apm.Pacs.eu.bi.PacsDAO;
import com.apm.Pacs.eu.entity.Pacs;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.main.common.constants.Constants;




public class JDBCPacsDAO extends JDBCBaseDAO implements PacsDAO{
	
	public JDBCPacsDAO(){
		
	}
	public JDBCPacsDAO(Connection connection){
		this.connection = connection;
	}
	
	public int saveData(String modality, String string) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "";
		return 0;
	}
	public int saveFileName(String fileName,Connection connection,String dir,String invstid,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		String sql = "insert into dicom_list(filename,recievedon,dir,invstid) values(?,?,?,?) ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, "c:/dicom/");
			preparedStatement.setString(4, invstid);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return outoid;
	}
	public int updateData(String elements, String column,int id,Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update dicom_list set "+column+"=? where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, elements);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public ArrayList<Pacs> getPacsList(Connection connection) {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		return null;
	}
	public ArrayList<Pacs> getPacsDataList(Connection connection) {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		String sql = "SELECT id, modality, pid, pname, studydate, recievedon, filename, dir FROM dicom_list";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs pacs = new Pacs();
				pacs.setId(rs.getInt(1));
				pacs.setModality(rs.getString(2));
				pacs.setPid(rs.getString(3));
				pacs.setPname(rs.getString(4));
				pacs.setStudydate(rs.getString(5));
				pacs.setRecievedon(rs.getString(6));
				pacs.setFilename(rs.getString(7));
				pacs.setDir(rs.getString(8));
				
				list.add(pacs);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public Pacs getPacsData(Connection connection, String id) {
		PreparedStatement preparedStatement = null;
		Pacs pacs = new Pacs();
		String sql = "SELECT id, modality, pid, pname, studydate, recievedon, filename, dir, finding FROM dicom_list where id="+id+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				pacs.setId(rs.getInt(1));
				pacs.setModality(rs.getString(2));
				pacs.setPid(rs.getString(3));
				pacs.setPname(rs.getString(4));
				pacs.setStudydate(rs.getString(5));
				pacs.setRecievedon(rs.getString(6));
				pacs.setFilename(rs.getString(7));
				pacs.setDir(rs.getString(8));
				pacs.setFindingtxt(rs.getString(9));
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return pacs;
	}
	public int getModid(String modality,Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		modality = modality.trim();
		
		String sql = "SELECT id FROM modality where name = '"+modality+"' ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int updateModid(int modid, int insertid, Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update dicom_list set modid="+modid+" where id = "+insertid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
	}
	public ArrayList<Pacs> getSearchedPacsList(Connection connection,
			String fromdate, String todate,ArrayList<String>modlist,int status,String pid,String pname) {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		StringBuffer sql = new StringBuffer();
		
		todate = todate + " 23:59:59";
		
		StringBuffer modid = new StringBuffer();
		String mid = "";
		if(status==1){
			for(String s : modlist){
				modid.append(s + ",");
			}
			
			mid = modid.substring(0,modid.length()-1);
		}
		
		sql.append("SELECT id, modality, pid, pname, studydate, recievedon, filename, dir FROM dicom_list ");
		
		//4
		if(!fromdate.equals(" ") && status==1 && !pid.equals("") && !pname.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and modid in("+mid+") and pid="+pid+" and pname like('%"+pname+"%') ");
		}
		
		//3
		else if(!fromdate.equals(" ") && !pid.equals("") && !pname.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and pid="+pid+" and pname like('%"+pname+"%') ");
		}else if(status==1 && !pid.equals("") && !pname.equals("")){
			sql.append("where modid in("+mid+") and pid="+pid+" and pname like('%"+pname+"%') ");
		}
		
		//2
		else if(!fromdate.equals(" ") && !pid.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and pid="+pid+" ");
		}
		else if(status==1 && !pid.equals("")){
			sql.append("where modid in("+mid+") and pid="+pid+" ");
		}
		
		else if(!fromdate.equals(" ") && !pname.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and pname like('%"+pname+"%') ");
		}
		else if(status==1 && !pname.equals("")){
			sql.append("where modid in("+mid+") and pname like('%"+pname+"%') ");
		}
		
		//1
		else if(!fromdate.equals(" ")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' ");
		}
		else if(status==1){
			sql.append("where modid in("+mid+") ");
		}
		else if(!pid.equals("")){
			sql.append("where pid="+pid+" ");
		}
		else if(!pname.equals("")){
			sql.append("where pname like('%"+pname+"%')");
		}
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs pacs = new Pacs();
				pacs.setId(rs.getInt(1));
				pacs.setModality(rs.getString(2));
				pacs.setPid(rs.getString(3));
				pacs.setPname(rs.getString(4));
				pacs.setStudydate(rs.getString(5));
				pacs.setRecievedon(rs.getString(6));
				pacs.setFilename(rs.getString(7));
				pacs.setDir(rs.getString(8));
				
				list.add(pacs);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public int saveSelectedImgid(String label,Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into editimg(selectedid) values(?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, label);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public boolean checkInvst(Connection connection,String invstid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_client_parent_investigation where id = "+invstid+" ";
		
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
	public Pacs getInvstigationDetails(String invstid,Connection connection) {
		PreparedStatement preparedStatement = null;
		Pacs pacs = new Pacs();
		String sql = "SELECT clientid,practitionerid,conditionid FROM apm_client_parent_investigation where id = "+invstid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				pacs.setClientid(rs.getString(1));
				pacs.setPractid(rs.getString(2));
				pacs.setCondition(rs.getString(3));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return pacs;
	}
	
	public int saveInvstID(String invstid,Pacs pacs,Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		String sql = "insert into apm_document(patientid,filename,practitionerid,doct_type,condition_id,lnvstid,packs_imgid,lastmodified) values(?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pacs.getClientid());
			preparedStatement.setString(2, pacs.getFilename());
			preparedStatement.setString(3, pacs.getPractid());
			preparedStatement.setString(4, pacs.getDoctype());
			preparedStatement.setString(5, pacs.getCondition());
			preparedStatement.setString(6, invstid);
			preparedStatement.setString(7, invstid);
			preparedStatement.setString(8, pacs.getDate());
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return outoid;
		
		
	}
	public boolean checkPacsImgId(String invstid,Connection connection) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_document where packs_imgid = "+invstid+" ";
		
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
	public int getPacsId(String invstid,Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_document where packs_imgid = "+invstid+" ";
		
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
	public int saveFindings(String finding, String findingtxt,Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update dicom_list set finding='"+findingtxt+"' where id = "+finding+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int getSelectedImgID(Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT selectedid FROM editimg order by id desc limit 0,1 ";
		
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
	public int saveServerFindings(String finding, String findingtxt,
			Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update his_pacs_data set finding='"+findingtxt+"' where imgid = "+finding+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public int getDicomSelectedId(Connection connection) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT selectedid FROM his_remote_dicom order by id desc limit 0,1 ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getSelectedDicomInvstid(int selectedid,Connection connection) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT lnvstid FROM apm_document where id = "+selectedid+" ";
		
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
	public ArrayList<Master> getInvstList(String curdate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		
		String todate = curdate + " 23:59:59";
		
		String sql = "SELECT id,clientid  FROM apm_client_parent_investigation where lastmodified between '"+curdate+"' and '"+todate+"' order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Master master = new Master();
				master.setId(rs.getInt(1));
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(rs.getString(2));
				
				String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				master .setName(master.getId() + " ("+fullname+")");
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	public ArrayList<Master> getModalityList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		
		
		String sql = "select id,name from modality ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	public ArrayList<Pacs> getwebpacsList(Connection connection,
			String searchText, String fromdate, String todate,
			String selectedmodality,String searchInvstid,String clientid,String pacsip) {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		StringBuffer sql = new StringBuffer();
		String uhid=getabrivationidbyclientid(clientid);
		
		todate = todate + " 23:59:59";
		
		StringBuffer modid = new StringBuffer();
		String mid = "";
		int status = 0;
		if(selectedmodality!=null){
			String temp[] = selectedmodality.split(",");
			
			if(temp.length>1){
				status = 1;
				mid = selectedmodality;
			}
		}
		
		//sql.append("SELECT di                                                                                                                                                                                                com_list.id, modality, invstid, pname, studydate, recievedon, filename, dir, multpacsid FROM dicom_list  ");
		//sql.append("FROM dicom_list inner join apm_client_parent_investigation ");
		//sql.append("on apm_client_parent_investigation.id = dicom_list.invstid ");
		
		
		/*sql.append("SELECT id, modality, invstid, pname, studydate, recievedon, filename, dir, multpacsid FROM his_dicom_list  ");*/
		
		sql.append("SELECT multipacs.id, dicom_list.filename, multipacs.ldate, multpacsid,multipacs.dirname,dicom_list.id,dicom_list.dirname FROM multipacs inner join dicom_list on dicom_list.multpacsid = multipacs.id");
		
		//4
		if(!fromdate.equals(" ") && status==1 && !searchInvstid.equals("") && !searchText.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and modid in("+mid+") and invstid="+searchInvstid+" and pname like('%"+searchText+"%') ");
		}
		
		//3
		else if(!fromdate.equals(" ") && !searchInvstid.equals("") && !searchText.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and invstid="+searchInvstid+" and pname like('%"+searchText+"%') ");
		}else if(status==1 && !searchInvstid.equals("") && !searchText.equals("")){
			sql.append("where modid in("+mid+") and invstid="+searchInvstid+" and pname like('%"+searchText+"%') ");
		}
		
		//2
		else if(!fromdate.equals(" ") && !searchInvstid.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and invstid="+searchInvstid+" ");
		}
		else if(status==1 && !searchInvstid.equals("")){
			sql.append("where modid in("+mid+") and invstid="+searchInvstid+" ");
		}
		
		else if(!fromdate.equals(" ") && !searchText.equals("")){
			sql.append("where recievedon between '"+fromdate+"' and '"+todate+"' and pname like('%"+searchText+"%') ");
		}
		else if(status==1 && !searchText.equals("")){
			sql.append("where modid in("+mid+") and pname like('%"+searchText+"%') ");
		}
		
		
		else if(status==1){
			sql.append("where modid in("+mid+") ");
		}
		else if(!searchInvstid.equals("")){
			sql.append("where invstid="+searchInvstid+" ");
		}
		else if(!searchText.equals("")){
			sql.append("where pname like('%"+searchText+"%') ");
		}
		
		if(!clientid.equals("0")){
			//sql.append(" and dirname="+uhid+" ");
			sql.append("  where multipacs.dirname='"+uhid+"' order by multipacs.id desc ");
		}
		if(clientid.equals("0")){
		sql.append(" order by multipacs.id desc ");
		}
		try{
			
			 connection=DriverManager.getConnection("jdbc:mysql://"+pacsip+":3306/webpacs",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			 
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs pacs = new Pacs();
				pacs.setId(rs.getInt(1));
				//pacs.setModality(rs.getString(2));
				/*pacs.setPid(rs.getString(3));
				pacs.setPname(rs.getString(4));
				pacs.setStudydate(rs.getString(5));*/
				pacs.setFilename(rs.getString(2));
				pacs.setRecievedon(rs.getString(3));
				/*pacs.setDir(rs.getString(8));*/
				pacs.setMultipacsid(rs.getString(4));
				//pacs.setAbrivationid(rs.getString(5));
				pacs.setDicid(rs.getString(6));
				pacs.setAbrivationid(rs.getString(5));
				//pacs.setAbrivationid(client.getAbrivationid());
				Client client=getAllclientsPacsdetails(rs.getString(5));
				pacs.setFullname(client.getFullname());
				boolean findingstatus = getFindingStatus(pacs.getId());
				if(findingstatus){
					pacs.setFstatus(1);
				}else{
					pacs.setFstatus(0);
				}
				
				
				list.add(pacs);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	public Client getAllclientsPacsdetails(String uhid) {
		PreparedStatement preparedStatement = null;
		Client client=new Client();
		String sql = "SELECT fullname,abrivationid FROM apm_patient where abrivationid='"+uhid+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				client.setFullname(rs.getString(1));
				client.setAbrivationid(rs.getString(2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}
	private String getabrivationidbyclientid(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT abrivationid FROM apm_patient where id = "+clientid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result=rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	private boolean getFindingStatus(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM his_pacs_data where imgid = "+id+" ";
		
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
	public int updatefinding(int selectedid, String txt) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		//int selectedimgid = getSelectedImgID(selectedid);
		
		String sql = "update his_pacs_data set finding=? where id="+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, txt);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	
	private int getSelectedImgID(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id from  his_pacs_data where imgid="+selectedid+" order by id desc limit 0,1 ";
		
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
	public String getSelectedFinding(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT finding FROM his_pacs_data where id = "+id+" ";
		
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
	public String getSelectedInvstid(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invstid FROM his_pacs_data where id = "+selectedid+" ";
		
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
	public ArrayList<Pacs> getFolderList(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		String sql = "select id, ldate, dir, wfilename from multipacs ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs pacs = new Pacs();
				pacs.setId(rs.getInt(1));
				pacs.setDate(rs.getString(2));
				pacs.setDir(rs.getString(3));
				pacs.setFilename(rs.getString(4));
				
				list.add(pacs);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int getPacsCount(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from dicom_list where multpacsid = "+id+" ";
		
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
	public ArrayList<Pacs> getPacsImgList(String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		String sql = "select filename,id from dicom_list where multpacsid = "+id+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs pacs = new Pacs();
				pacs.setFilename(rs.getString(1));
				pacs.setId(rs.getInt(2));
				
				list.add(pacs);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public int insertDicomid(int dicomid,String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_pacs_viewer(dicomid,userid) values(?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dicomid);
			preparedStatement.setString(2, userid);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int truncatemultipacs(String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from his_pacs_viewer where userid='"+userid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int updatewordfilename(String selectedinvstid, String fileName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update multipacs set wfilename=? where id="+selectedinvstid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fileName);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	@Override
	public ArrayList<Pacs> getnewwebpacsList(Connection connection, String clientid, String pacsip, String abrivationid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("SELECT multipacs.id, dicom_list.filename, multipacs.ldate, multpacsid FROM multipacs inner join dicom_list on dicom_list.multpacsid = multipacs.id where multipacs.dirname='"+abrivationid+"'");
//		sql.append(" and clientid="+clientid+" ");
		sql.append(" order by id desc ");
		try{
			
			 connection=DriverManager.getConnection("jdbc:mysql://"+pacsip+":3306/webpacs",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			 
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs pacs = new Pacs();
				pacs.setId(rs.getInt(1));
				pacs.setModality(rs.getString(2));
				pacs.setPid(rs.getString(3));
				pacs.setPname(rs.getString(4));
				pacs.setStudydate(rs.getString(5));
				pacs.setRecievedon(rs.getString(6));
				pacs.setFilename(rs.getString(7));
				pacs.setDir(rs.getString(8));
				pacs.setMultipacsid(rs.getString(9));
				
				boolean findingstatus = getFindingStatus(pacs.getId());
				if(findingstatus){
					pacs.setFstatus(1);
				}else{
					pacs.setFstatus(0);
				}
				
				list.add(pacs);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int updatepatientuhid(Pacs pacs, String id, String uhid,String pacsip,String pacsid) {
		int result=0;
		try {
			String sql="update webpacs.multipacs set dirname=?  where id='"+pacsid+"'";
			connection=DriverManager.getConnection("jdbc:mysql://"+pacsip+":3306/webpacs",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, uhid);
			
			result=pst.executeUpdate();
			
		} catch (Exception e) {
	   e.printStackTrace();	
	   }
		return result;
	}
	@Override
	public Pacs getPatientUhidbyid(String id) {
		Pacs pacs=new Pacs();
		try {
			
			String sql="SELECT dirname FROM webpacs.multipacs where id='"+id+"'";
		   
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
			
			   pacs.setAbrivationid(rs.getString(1));
			}
			
		} catch (Exception e) {
	          e.printStackTrace();
		}
		return pacs;
	}
	@Override
	public Pacs getpacsFindingbypacsid(String selectedid) {
		Pacs pacs=new Pacs();
      try {
			
			String sql="SELECT finding,findinguser,dirname FROM webpacs.dicom_list where id='"+selectedid+"'";
		   
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
			
			   pacs.setFinding(rs.getString(1));
			   pacs.setFindinguser(rs.getString(2));
			   pacs.setAbrivationid(rs.getString(3));
			}
			
		} catch (Exception e) {
	          e.printStackTrace();
		}
		return pacs;
	}
	@Override
	public Pacs getusernamebyuhid(String abrivationid) {
		Pacs pacs=new Pacs();
		  try {
				
				String sql="SELECT id,findinguser FROM webpacs.dicom_list where dirname='"+abrivationid+"'";
			   
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
				  pacs.setId(rs.getInt(1));
				  pacs.setFindinguser(rs.getString(2));
				 
				}
				
			} catch (Exception e) {
		          e.printStackTrace();
			}
		return pacs;
	}
	@Override
	public int updateWriteupData(int selectedid, String finding, String string, String string2) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update webpacs.dicom_list  set finding=? where multpacsid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, finding);
			//preparedStatement.setString(2, "");
			//preparedStatement.setString(3, "");

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	@Override
	public ArrayList<Pacs> getTemplateList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Pacs>list = new ArrayList<Pacs>();
		String sql = "SELECT id,name FROM apm_investigation_template ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Pacs dicom = new Pacs();
				dicom.setId(rs.getInt(1));
				dicom.setName(rs.getString(2));
				
				list.add(dicom);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	@Override
	public String getTemplateData(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT template FROM apm_investigation_template where id = "+id+" ";
		
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

}
