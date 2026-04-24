package com.apm.Ipd.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.web.form.BedForm;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BedMasterAction extends ActionSupport implements
		ModelDriven<BedForm>, Preparable {

	BedForm bedForm = new BedForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(10, 1);

    private String mastername;
   
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> wardlist = bedDao.getAllMasterWardList();
			bedForm.setWardlist(wardlist);
			//session.setAttribute("ipddash_forreload", "1");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return SUCCESS;
	}

	public String add() throws Exception {
		Connection connection = null;
		try {

			String wardname = request.getParameter("wardname");
			String plusminus=request.getParameter("plusminus");
			String procedure=request.getParameter("procedure");
			
	        int wardid=0;
			
			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			
			if(!wardname.equals("")){
				wardid=bedDao.addWard(wardname,plusminus,procedure);
			}
			if(wardid!=0){
				session.setAttribute("selectedward", wardid);
				String wardids = bedDao.getAllWardIds();
				int res = bedDao.updateWardAccessforAdmin(wardids);
			}

			ArrayList<Bed> wardlist = bedDao.getAllMasterWardList();

			StringBuffer str = new StringBuffer();
			str.append(" <label for='exampleInputEmail1'>Existing Ward</label>");
			str.append("<select class='form-control showToolTip chosen' name='wardlist' id='wardlist' onchange='setWardId(this.value)' >");
			str.append("<option value='0'>Select Ward</option>");
			for (Bed bed : wardlist) {
				
				if(bed.getId()==wardid) {
				str.append("<option value='" + bed.getId() + "' selected='selected'>"
						+ bed.getWardname() + "</option>");
				} else {
					str.append("<option value='" + bed.getId() + "'>"
							+ bed.getWardname() + "</option>");	
				}
			}
			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String addSection() throws Exception {

		Connection connection = null;
 
		int sectionid=0;
		try {
			String sectionname = request.getParameter("sectionname");
			String wardid = request.getParameter("wardid");
			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			
			
			if(!sectionname.equals("")){
				sectionid=bedDao.addSection(sectionname, wardid);
			
			}
			if(sectionid!=0){
				 session.setAttribute("selectedsection", sectionid);
			}
			
			
			ArrayList<Bed> sectionlist = bedDao.getAllSectionList();
			StringBuffer str = new StringBuffer();
			str.append(" <label for='exampleInputEmail1'>Existing Section</label>");
			str.append("<select class='form-control showToolTip chosen' name='sectionlist' id='fsection'  onchange='setSectionId(this.value)' >");
			str.append("<option value='0'>Select Section</option>");

			for (Bed bed : sectionlist) {
				if(bed.getId()==sectionid) {
				  str.append("<option value='" + bed.getId() + "' selected='selected'>"
						+ bed.getSectionname() + "</option>");
				} else {
					str.append("<option value='" + bed.getId() + "'>"
							+ bed.getSectionname() + "</option>");
				}
			}

			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return null;
	}

	public String addbed() throws Exception {

		Connection connection = null;
        int bedid=0;
		try {

			connection = Connection_provider.getconnection();
			String bedname = request.getParameter("bedname");
			String wardid = request.getParameter("wardid");
			String sectionid = request.getParameter("sectionid");
			String casualty=request.getParameter("casualty");
			Bed bed = new Bed();
			bed.setBedname(bedname);
			bed.setWardid(wardid);
			bed.setSectionid(sectionid);
			bed.setCasualty(casualty);
			BedDao bedDao = new JDBCBedDao(connection);
			
			if(!bedname.equals("")){
				bedid=bedDao.addBed(bed);
			}
			if(bedid!=0){
				 session.setAttribute("selectedbed", bedid);
			}
			
			ArrayList<Bed> bedlist = bedDao.getAllBedList();

			StringBuffer str = new StringBuffer();
			str.append(" <label for='exampleInputEmail1'>Existing Bed</label>");
			str.append("<select class='form-control showToolTip chosen' name='bedlist' id='fbed'  onchange='setBedId(this.value)' >");
			str.append("<option value='0'>Select Bed</option>");

			for (Bed bed2 : bedlist) {
				if(bed2.getId()==bedid) {
				 str.append("<option value='" + bed2.getId() + "' selected='selected'>"
						+ bed2.getBedname() + "</option>");
				} else {
					str.append("<option value='" + bed2.getId() + "'>"
							+ bed2.getBedname() + "</option>");
				}
			}
			str.append("</select>");
			
			session.setAttribute("ipddash_forreload", "1");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String setWardList() throws Exception {

		Connection connection = null;
		try {

			int wardid=(Integer)session.getAttribute("selectedward");
			

			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> wardlist =bedDao.getAllMasterWardList();
					// bedDao.getAllWardList("0");
			StringBuffer str = new StringBuffer();
			str.append(" <label for='exampleInputEmail1'>Existing Ward</label>");
			str.append("<select class='form-control showToolTip chosen' name='wardlist' id='fward' onchange='setWardId(this.value)' >");
			str.append("<option value='0'>Select Ward</option>");

			for (Bed bed : wardlist) {
				
				if(bed.getId()==wardid) {
					str.append("<option value='" + bed.getId() + "' selected='selected'>"
							+ bed.getWardname() + "</option>");
				} else {
					str.append("<option value='" + bed.getId() + "'>"
							+ bed.getWardname() + "</option>");
				}
					
				
			}
			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String setSectionList() throws Exception {
		Connection connection = null;
		try {
			
			int sectionid=(Integer)session.getAttribute("selectedsection");
            
			
			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> sectionlist = bedDao.getAllSectionList();
			StringBuffer str = new StringBuffer();
			str.append(" <label for='exampleInputEmail1'>Existing Section</label>");
			str.append("<select class='form-control showToolTip chosen' name='sectionlist' id='fsection' onchange='setSectionId(this.value)' >");
			str.append("<option value='0'>Select Section</option>");

			for (Bed bed : sectionlist) {
				if(bed.getId()==sectionid) {
				
				str.append("<option value='" + bed.getId() + "' selected='selected'>"
						+ bed.getSectionname() + "</option>");
				} else {
					str.append("<option value='" + bed.getId() + "'>"
							+ bed.getSectionname() + "</option>");
				}
			}
			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String saveequipment() throws Exception {

		String equipment = request.getParameter("equipment");
		String wardid = request.getParameter("wardid");
		String sectionid = request.getParameter("sectionid");
		String bedid = request.getParameter("bedid");

		Bed bed = new Bed();

		Connection connection = null;
		try {

			bed.setEquipmentname(equipment);
			bed.setWardid(wardid);
			bed.setSectionid(sectionid);
			bed.setBedid(bedid);
			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			
			if(!equipment.equals("")){
				bedDao.addEquipment(bed);
			}
			

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;

	}

	public String edit() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
            int id=Integer.parseInt(request.getParameter("selectedid"));
			BedDao bedDao=new JDBCBedDao(connection);
            Bed bed=bedDao.getBed(id);
            bedForm.setId(bed.getId());
            bedForm.setWardid(bed.getWardid());
			bedForm.setSectionid(bed.getSectionid());
			bedForm.setBedname(bed.getBedname());
			ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
			bedForm.setWardlist(wardlist);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return SUCCESS;
	}

	public String redirectbookbed() throws Exception {
		Connection connection = null;
		try {

			/*connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> wardlist = bedDao.getAllMasterWardList();
			bedForm.setWardlist(wardlist);
			ArrayList<Bed> bedlist = bedDao.getAllBedList();
			bedForm.setBedlist(bedlist);*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*connection.close();*/
		}

		return "bedStatus";
	}

	public String admissionform() {
		
		return "admission";
	}
	

	public BedForm getModel() {
		// TODO Auto-generated method stub
		return bedForm;
	}

	public String update() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			String wardname = request.getParameter("wardname");
			String sectionname = request.getParameter("sectionname");
			String bedname = request.getParameter("bedname");

			Bed bed = new Bed();
			bed.setId(Integer.parseInt(id));
			bed.setWardname(wardname);
			bed.setSectionname(sectionname);
			bed.setBedname(bedname);

			BedDao bedDao = new JDBCBedDao(connection);
			int result = bedDao.updateBedEntries(bed);

			StringBuffer str = new StringBuffer();
			str.append("<");
			str.append(".innerHTML=<i class='fa fa-pencil-square-o'></i>");
			str.append("<>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
	
	
	public String setajaxsection() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			String wardid=request.getParameter("wardid");
			BedDao bedDao=new JDBCBedDao(connection);
			
			ArrayList<Bed> sectionlist=bedDao.getSectionList(wardid);
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<select class='form-control showToolTip chosen' name='sectionlist' id='sectionlist' onchange='setBedList(this.value)' >");
			buffer.append("<option value='0'>Select Section</option>");

			for (Bed bed : sectionlist) {
				buffer.append("<option value='" + bed.getId() + "'>"
						+ bed.getSectionname() + "</option>");
			}
			buffer.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		} catch (Exception e) {

		 e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	
		return null;
	}
	
	
public String setajaxbed() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			String sectionid=request.getParameter("sectionid");
			BedDao bedDao=new JDBCBedDao(connection);
			
			ArrayList<Bed> bedlist=bedDao.getBedList(sectionid);
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<select class='form-control showToolTip chosen' name='bedlist' id='bedlist' onchange='setEquipmentList(this.value)' >");
			buffer.append("<option value='0'>Select Bed</option>");

			for (Bed bed : bedlist) {
				buffer.append("<option value='" + bed.getId() + "'>"
						+ bed.getBedname() + "</option>");
			}
			buffer.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		} catch (Exception e) {

		 e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	
		return null;
	}
	
	
   public String setajaxequipment()throws Exception {
	   
	   Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			String bedid=request.getParameter("bedid");
			BedDao bedDao=new JDBCBedDao(connection);
			
			ArrayList<Bed> equipmentlist=bedDao.getEquipmentList(bedid);
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<select class='form-control showToolTip chosen' name='equipmentlist' id='equipmentlist' >");
			buffer.append("<option value='0'>Select Equipment</option>");

			for (Bed bed : equipmentlist) {
				buffer.append("<option value='" + bed.getId() + "'>"
						+ bed.getEquipmentname() + "</option>");
			}
			buffer.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		} catch (Exception e) {

		 e.printStackTrace();
		}
		finally{
			connection.close();
		} 
	   return null;
   }
   

	

	public String delete() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String selectedid = request.getParameter("selectedid");
			BedDao bedDao = new JDBCBedDao(connection);
			int result = bedDao.deleteBed(selectedid);
			ArrayList<Bed> bedlist = bedDao.getAllBedList();
			bedForm.setBedlist(bedlist);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "bedStatus";
	}
	
	public String updatebedmaster() throws Exception{
		
		Connection connection=null;
		
		try {

			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=new Bed();
			bed.setBedid(bedForm.getBedid());
			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "bedStatus";
	}
	
	
	public String wardlistmaster() throws Exception {
		
		Connection connection = null;
		try {

			mastername=request.getParameter("selectedid");
			if(mastername!=null) {
				
				session.setAttribute("mastername",mastername);
			}
			else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			connection = Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			int total=bedDao.getTotalWardCount();
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			
			ArrayList<Bed> wardlist = bedDao.getAllWardList(pagination);
			
			pagination.setPage_records(wardlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bedForm.setWardlist(wardlist);
			bedForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		
		return "wardmaster";
	}
	
	public String editwardmaster() throws Exception {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			String selectedid=request.getParameter("selectedid");
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=bedDao.getWard(selectedid);
			bedForm.setId(bed.getId());
			bedForm.setWardname(bed.getWardname());
			bedForm.setIncrement(bed.getIncrement());
			bedForm.setProcedure(bed.getProcedure());
			
		} catch (Exception e) {
          e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "editwardmaster";
	}
	
	public String updatewardmaster() throws Exception {
		
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=new Bed();
			bed.setId(bedForm.getId());
			bed.setWardname(bedForm.getWardname());
			bed.setIncrement(bedForm.getIncrement());
			bed.setProcedure(bedForm.getProcedure());
			int result=bedDao.updateWardMaster(bed);		
			
			int total=bedDao.getTotalWardCount();
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			ArrayList<Bed> wardlist = bedDao.getAllWardList(pagination);
			pagination.setPage_records(wardlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bedForm.setWardlist(wardlist);
			bedForm.setMastername(mastername);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "wardmaster";
	}
	
	
	public String deletewardmaster() throws Exception {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			String selectedid=request.getParameter("selectedid");
			BedDao bedDao=new JDBCBedDao(connection);
			int result=bedDao.deleteWardMaster(selectedid);
			
			
			int total=bedDao.getTotalWardCount();
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			ArrayList<Bed> wardlist = bedDao.getAllWardList(pagination);
			pagination.setPage_records(wardlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bedForm.setWardlist(wardlist);
			bedForm.setMastername(mastername);
		} catch (Exception e) {

		  e.printStackTrace();
		  
		}
		finally{
			connection.close();
		}
		
		return "wardmaster";
	}
	
	
	public String sectionlistmaster() throws Exception {
		
		Connection connection=null;
		
		try {
			
			 mastername=request.getParameter("selectedid");
			if(mastername!=null) {
				
				session.setAttribute("mastername",mastername);
			}
			else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
		    int total=bedDao.getTotalSectionCount();
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			
			ArrayList<Bed> sectionlist=bedDao.getAllSectionList(pagination); 
			
			pagination.setPage_records(sectionlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bedForm.setSectionlist(sectionlist);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			bedForm.setWardlist(wardlist);
		    bedForm.setMastername(mastername);
		} catch (Exception e) {

		   e.printStackTrace();
		} 
		finally {
			connection.close();
		}
		
		
		return "sectionmaster";
	}
	
	
	public String editsectionmaster() throws Exception{
	
		Connection connection=null;
		
		try {
			 connection=Connection_provider.getconnection();
			 String selectedid=request.getParameter("selectedid");
			 BedDao bedDao=new JDBCBedDao(connection);
			 Bed bed=bedDao.getSection(selectedid);
			 bedForm.setId(bed.getId());
			 bedForm.setSectionname(bed.getSectionname());
			 bedForm.setWardid(bed.getWardid()); 
			
			 ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			 bedForm.setWardlist(wardlist);
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		finally {
			connection.close();
		}
	
		return "editsectionmaster";
	}
	
	
	public String updatesectionmaster() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=new Bed();
			bed.setId(bedForm.getId());
			bed.setWardid(bedForm.getWardid());
			bed.setSectionname(bedForm.getSectionname());
			
			int result=bedDao.updateSectionMaster(bed);
			
			int total=bedDao.getTotalSectionCount();
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			ArrayList<Bed> sectionlist=bedDao.getAllSectionList(pagination); 
			pagination.setPage_records(sectionlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bedForm.setSectionlist(sectionlist);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			bedForm.setWardlist(wardlist);
		    bedForm.setMastername(mastername);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		finally{
			connection.close();
		}
		return "sectionmaster";
	}
	
	public String deletesectionmaster() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			String selectedid=request.getParameter("selectedid");
			int result=bedDao.deleteSectionMaster(selectedid);
            
			int total=bedDao.getTotalSectionCount();
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			ArrayList<Bed> sectionlist=bedDao.getAllSectionList(pagination); 
			pagination.setPage_records(sectionlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bedForm.setSectionlist(sectionlist);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			bedForm.setWardlist(wardlist);
		    bedForm.setMastername(mastername);
			
			
		} catch (Exception e) {
           e.printStackTrace();
           
		}
		finally {
			connection.close();
		}
		return "sectionmaster";
	}
	
	public String bedlistmaster() throws Exception{
		
	     Connection connection=null;	
	
		try {
	
			 mastername=request.getParameter("selectedid");
			if(mastername!=null) {
				
				session.setAttribute("mastername",mastername);
			}
			else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			
			String searchText = bedForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			int total=bedDao.getTotalBedCount(searchText);
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			
			ArrayList<Bed> bedlist=bedDao.getAllBedList(pagination,searchText);
			
			bedForm.setBedlist(bedlist);
			pagination.setPage_records(bedlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
	        ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
	        bedForm.setWardlist(wardlist);
	        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
	        bedForm.setSectionlist(sectionlist);
			bedForm.setMastername(mastername);
			session.setAttribute("isthissuperadmin", loginInfo.getSuperadminid());
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "bedmaster";
	}
	
	
	public String editbedmaster() throws Exception {
		
        Connection connection=null;
        
        try {
			
        	connection=Connection_provider.getconnection();
        	
        	String selectedid=request.getParameter("selectedid");
        	BedDao bedDao=new JDBCBedDao(connection);
        	Bed bed=bedDao.getBed(Integer.parseInt(selectedid));
        	bedForm.setId(bed.getId());
        	bedForm.setWardid(bed.getWardid());
        	bedForm.setSectionid(bed.getSectionid());
        	bedForm.setBedname(bed.getBedname());
        	bedForm.setCasualty(bed.getCasualty());
        	ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
        	bedForm.setWardlist(wardlist);
        	
        	ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
        	bedForm.setSectionlist(sectionlist);
        	
        	
        	
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}	
        finally {
        	
        	connection.close();
        }
		
		return "editbedmaster";
	}
	
	
	public String updatebed() throws Exception {
		
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection); 
			Bed bed=new Bed();
			bed.setId(bedForm.getId());
			bed.setWardid(bedForm.getWardid());
			bed.setSectionid(bedForm.getSectionid());
			bed.setBedname(bedForm.getBedname());
			bed.setCasualty(bedForm.getCasualty());
			int result=bedDao.updateBedMaster(bed);
			
			int total=bedDao.getTotalBedCount(null);
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			ArrayList<Bed> bedlist=bedDao.getAllBedList(pagination,null);
			bedForm.setBedlist(bedlist);
			pagination.setPage_records(bedlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
	        ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
	        bedForm.setWardlist(wardlist);
	        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
	        bedForm.setSectionlist(sectionlist);
		    bedForm.setMastername(mastername);			
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		finally{
			connection.close();
		}
		
		
		return "bedmaster";
	}
	

	public String deletebedmaster() throws Exception{
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			
			BedDao bedDao=new JDBCBedDao(connection);
		    String selectedid=request.getParameter("selectedid");
		    int result=bedDao.deleteBedMaster(selectedid);
		    
			
		    int total=bedDao.getTotalBedCount(null);
			pagination.setPreperties(total);
			bedForm.setTotalRecords(total);
			ArrayList<Bed> bedlist=bedDao.getAllBedList(pagination,null);
			bedForm.setBedlist(bedlist);
			pagination.setPage_records(bedlist.size());
			bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
	        ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
	        bedForm.setWardlist(wardlist);
	        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
	        bedForm.setSectionlist(sectionlist);
		    bedForm.setMastername(mastername);			
		    
		    
		} catch (Exception e) {
		
		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		return "bedmaster";
	}
	
	public String equipmentlistmaster() throws Exception {
		
	    Connection connection=null;
	    
	    try {
	    	
	    	String mastername=request.getParameter("selectedid");
			if(mastername!=null) {
				
				session.setAttribute("mastername",mastername);
			}
			else {
				
				mastername=(String)session.getAttribute("mastername");
			}
	    	connection=Connection_provider.getconnection();
	    	BedDao bedDao=new JDBCBedDao(connection);
	        int total=bedDao.getTotalEquipmentCount();
	        pagination.setPreperties(total);
	        bedForm.setTotalRecords(total);   	
	        ArrayList<Bed> equipmentlist=bedDao.getEquipmentList(pagination);
	        pagination.setPage_records(equipmentlist.size()); 
	        bedForm.setEquipmentlist(equipmentlist);
	        bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
	        
	        ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
	        bedForm.setWardlist(wardlist);
	        
	        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
	        bedForm.setSectionlist(sectionlist);
	        
	        ArrayList<Bed> bedlist=bedDao.getAllBedList();
	        bedForm.setBedlist(bedlist);
	        
	        bedForm.setMastername(mastername);
	     
	    	
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			
			connection.close();
		}
		
		return "equipmentmaster";
	}
	
	
	public String editequipmentmaster() throws Exception {
		
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String selectedid=request.getParameter("selectedid");
			
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=bedDao.getEquipment(selectedid); 
			bedForm.setId(bed.getId());
			bedForm.setWardid(bed.getWardid());
			bedForm.setSectionid(bed.getSectionid());
			bedForm.setBedid(bed.getBedid());
			bedForm.setEquipmentname(bed.getEquipmentname());
			
			    ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
		        bedForm.setWardlist(wardlist);
		        
		        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
		        bedForm.setSectionlist(sectionlist);
		        
		        ArrayList<Bed> bedlist=bedDao.getAllBedList();
		        bedForm.setBedlist(bedlist);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	    finally {
	    	connection.close();
	    }
   
		
		return "editequipment";
	}
	
	
	public String updateequipmentmaster() throws Exception {
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=new Bed();
			bed.setId(bedForm.getId());
			bed.setWardid(bedForm.getWardid());
			bed.setSectionid(bedForm.getSectionid());
			bed.setBedid(bedForm.getBedid());
			bed.setEquipmentname(bedForm.getEquipmentname());
			int result=bedDao.updateEquipment(bed); 
			
			
			int total=bedDao.getTotalEquipmentCount();
	        pagination.setPreperties(total);
	        bedForm.setTotalRecords(total);   	
	        ArrayList<Bed> equipmentlist=bedDao.getEquipmentList(pagination);
	        pagination.setPage_records(equipmentlist.size()); 
	        bedForm.setEquipmentlist(equipmentlist);
	        bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
	        
	        ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
	        bedForm.setWardlist(wardlist);
	        
	        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
	        bedForm.setSectionlist(sectionlist);
	        
	        ArrayList<Bed> bedlist=bedDao.getAllBedList();
	        bedForm.setBedlist(bedlist);
	        
	        bedForm.setMastername(mastername);
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		finally {
			connection.close();
		}
		
		
		return "equipmentmaster";
	}
	
	
	public String deleteequipmentmaster() throws Exception{
		
	  Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			String selectedid=request.getParameter("selectedid");
			int result=bedDao.deleteEquipment(selectedid);
			
			
			
			int total=bedDao.getTotalEquipmentCount();
	        pagination.setPreperties(total);
	        bedForm.setTotalRecords(total);   	
	        ArrayList<Bed> equipmentlist=bedDao.getEquipmentList(pagination);
	        pagination.setPage_records(equipmentlist.size()); 
	        bedForm.setEquipmentlist(equipmentlist);
	        bedForm.setPagerecords(String.valueOf(pagination.getPage_records()));
	        
	        ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
	        bedForm.setWardlist(wardlist);
	        
	        ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
	        bedForm.setSectionlist(sectionlist);
	        
	        ArrayList<Bed> bedlist=bedDao.getAllBedList();
	        bedForm.setBedlist(bedlist);
	        
	        bedForm.setMastername(mastername);
			
			
		} catch (Exception e) {
              e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "equipmentmaster";
	}
	
	
	public String status()throws Exception{
		String selectedid = request.getParameter("selectedid");
		String status = request.getParameter("status");
		String bedstatus = "";
		
		 Connection connection=null;
		 
			try {
				
				connection=Connection_provider.getconnection();
				BedDao bedDao=new JDBCBedDao(connection);
				
				if(status.equals("0")){
					bedstatus = "1";
				}
				
				if(status.equals("1")){
					bedstatus = "0";
				}
				
				boolean flag = bedDao.getActiveBedStaus(selectedid);
				int upd = 0;
				if(!flag){
					upd = bedDao.changeBedStatus(selectedid,bedstatus);
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+upd+"");
			
		}catch(Exception e){
			e.printStackTrace();
		}
			finally{
				connection.close();
			}
		return null;
	}
	
public String getallnotoccupiedbeds(){
	Connection connection= null;
	String wardid=request.getParameter("wardid");
	try {
		connection= Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ArrayList<Bed> bedList = ipdDAO.getWardWiseBedList(wardid);

		StringBuffer str = new StringBuffer();
		str.append("<select  name='bedid' id='bedname' class='form-control chosen-select'>");
		str.append("<option value='0'>Select Bed</option>");

		for (Bed bed : bedList) {
			str.append("<option value='" + bed.getId() + "'>" + bed.getBedname() + "</option>");
		}

		str.append("</select>");
		str.append("&nbsp; &nbsp; &nbsp;<p><button class='btn btn-primary' onclick='savepatienttoipd()' >save to IPD</button></p>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
	

	public void prepare() throws Exception {
		
		Connection con = null;
		try {
			con = Connection_provider.getconnection();
			DischargeOutcomeDAO outcomeDAO = new JDBCDischargeOutcomeDAO(con);
			BedDao bedDao=new JDBCBedDao(con);
			ArrayList<Bed> bedlist=bedDao.getAllBedList();
			ArrayList<Bed> wardlist=bedDao.getAllMasterWardList();
			ArrayList<Bed> sectionlist=bedDao.getAllSectionList();
			
			bedForm.setBedlist(bedlist);
			bedForm.setWardlist(wardlist);
			bedForm.setSectionlist(sectionlist);
			
			ArrayList<Master> masterlist = outcomeDAO.getMasterList(loginInfo);
			bedForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			//con.close();
		}
	}

	public String getallnotoccupiedbeds1(){
		Connection connection= null;
		String wardid=request.getParameter("wardid");
		try {
			connection= Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ArrayList<Bed> bedList = ipdDAO.getWardWiseBedList(wardid);

			StringBuffer str = new StringBuffer();
			str.append("<select  name='bedid' id='bedname' class='form-control chosen-select'>");
			str.append("<option value='0'>Select Bed</option>");

			for (Bed bed : bedList) {
				str.append("<option value='" + bed.getId() + "'>" + bed.getBedname() + "</option>");
			}

			str.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str.toString() + "");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
