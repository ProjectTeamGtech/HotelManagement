package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryCatalogueDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PrescriptionDetailsAction extends BaseAction implements
		ModelDriven<EmrForm>, Preparable {

	EmrForm emrForm = new EmrForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	String mastername;

	Pagination pagination = new Pagination(20, 1);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public EmrForm getModel() {
		return emrForm;
	}

	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(
					connection);
			String searchText = emrForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			int totalcount = prescriptionMasterDAO
					.getTotalPrescriptionDetailsCount(searchText);
			pagination.setPreperties(totalcount);
			ArrayList<Priscription> priscriptiondetails = prescriptionMasterDAO
					.getPrescriptionDetails(pagination,searchText);
			pagination.setPage_records(priscriptiondetails.size());
			emrForm.setTotalRecords(totalcount);
			emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			emrForm.setPriscriptiondetails(priscriptiondetails);

			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);

			} else {

				mastername = (String) session.getAttribute("mastername");
			}
			emrForm.setMastername(mastername);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String add() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(
					connection);
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			ArrayList<Priscription> prescriptioncategorylist = prescriptionMasterDAO
					.getPrescriptionCategoryList();
			emrForm.setPrescriptioncategorylist(prescriptioncategorylist);
			ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
			emrForm.setDosagenoteList(dosagenoteList);
			
			
			ArrayList<Master> prisctylist= new ArrayList<Master>();
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			prisctylist=emrDAO.getMedicineTypeList();
			emrForm.setPrisctypelist(prisctylist);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "add";
	}

	public String save() throws Exception {

		Connection connection = null;

		try {

			connection=Connection_provider.getconnection();
			PrescriptionMasterDAO masterDAO=new JDBCPrescriptionMasterDAO(connection);
			for(Priscription priscription:emrForm.getPrescription_details()) {
				String iswbd = priscription.getIswbd();
				if(iswbd!=null){
					if(iswbd.equals("on")){
						iswbd= "1";
					}else{
						iswbd ="0";
					}
				}else{
					iswbd ="0";
				}
				priscription.setIswbd(iswbd);
				String qty= request.getParameter("qty1");
				String noofdays= request.getParameter("noofdays");
				String remark=request.getParameter("remark");
				priscription.setDosefreq(emrForm.getDosefreq());
				priscription.setUom(emrForm.getUom());
				priscription.setDr_qty(qty);
				if(noofdays==null){
					noofdays="0";
				}
				if(noofdays.equals("")){
					noofdays="0";
				}
				priscription.setDays(Integer.parseInt(noofdays));
				priscription.setRemark(remark);
				priscription.setPrisctypename(emrForm.getPrisctypename());
				String type= priscription.getPrisctypename();
				String n= type;
				int result=masterDAO.addPrescriptionDetails(priscription);
			}
			
		   	
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return "save";
	}

	
	public String edit() throws Exception {
		
		Connection connection=null;
		
		try {
	        connection=Connection_provider.getconnection();		
			String selectedid=request.getParameter("selectedid");
	        PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection); 
	        MasterDAO masterDAO=new JDBCMasterDAO(connection);
	        TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
			
	        ArrayList<Master> listAllLocations= prescriptionMasterDAO.getAllMedicineLocation(selectedid);
	        ArrayList<Master> listSpecializations= prescriptionMasterDAO.getAllMedicineSpecialization(selectedid);
	        Priscription priscription=prescriptionMasterDAO.getPrescriptionDetails(selectedid);
	        emrForm.setPrisctypename(priscription.getPrisctypename());
	        emrForm.setId(priscription.getId());
	        emrForm.setDrug(priscription.getDrug());
	        emrForm.setStrength(priscription.getStrength());
			emrForm.setCategoryid(priscription.getCategoryid());
			emrForm.setMrp(priscription.getMrp());
			emrForm.setPurchase_price(priscription.getPurchase_price());
			emrForm.setSale_price(priscription.getSale_price());
			emrForm.setExpiry_date(priscription.getExpiry_date());
			emrForm.setPkg(priscription.getPkg());
			emrForm.setBatch_no(priscription.getBatch_no());
			emrForm.setMfg(priscription.getMfg());
			emrForm.setGenericname(priscription.getGenericname());
			emrForm.setRisk(priscription.getRisk());
			emrForm.setDosagenote(priscription.getDosagenote());
			emrForm.setFrequency(priscription.getFrequency());
			emrForm.setCaldose(priscription.getCaldose());
			emrForm.setIswbd(priscription.getIswbd());
			emrForm.setDays(priscription.getDays());
			emrForm.setDr_qty(priscription.getDr_qty());
			ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
			emrForm.setDosagenoteList(dosagenoteList);
			emrForm.setUom(priscription.getUom());
			emrForm.setDosefreq(priscription.getDosefreq());
			emrForm.setRemark(priscription.getRemark());
			emrForm.setComponent(DateTimeUtils.isNull(priscription.getComponent()));
			
			session.setAttribute("sessionrnote", priscription.getRemark());
			
			ArrayList<Master> medicineLocationList= masterDAO.getMedicineLocationList();
			ArrayList<TreatmentType> specializationList=treatmentTypeDAO.getDeptConditionList();
			ArrayList<Master> selectedLocations=new ArrayList<Master>();
			ArrayList<TreatmentType> selectedSpecializations=new ArrayList<TreatmentType>();
			ArrayList<Master> prisctylist= new ArrayList<Master>();
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			prisctylist=emrDAO.getMedicineTypeList();
			emrForm.setPrisctypelist(prisctylist);
			for(Master master:medicineLocationList){
				 
				   for(Master m1:listAllLocations){
					   
					     if(master.getId()==m1.getId()){
					    	  master.setStatus(1);
					    	  break;
					     }
				   }
				   selectedLocations.add(master);
			}
			
			for(TreatmentType treatmentType:specializationList){
				  for(Master master:listSpecializations){
					    
					   if(master.getId()==treatmentType.getId()){
						    treatmentType.setStatus(1);
						    break;
					   }
				  }
				  selectedSpecializations.add(treatmentType);
			}
			
			emrForm.setListAllLocations(selectedLocations);
			emrForm.setListSpecializations(selectedSpecializations);
			
			ArrayList<Priscription> prescriptioncategorylist = prescriptionMasterDAO
					.getPrescriptionCategoryList();
			emrForm.setPrescriptioncategorylist(prescriptioncategorylist);
	        
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
	
		return "edit";
	}
	
	
	
	public String update() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			
			Priscription priscription=new Priscription();
            priscription.setId(emrForm.getId());
            priscription.setDrug(emrForm.getDrug());
            priscription.setStrength(emrForm.getStrength());
            priscription.setCategoryid(emrForm.getCategoryid());		
            priscription.setMrp(emrForm.getMrp());
            priscription.setPurchase_price(emrForm.getPurchase_price());
            priscription.setSale_price(emrForm.getSale_price());
            priscription.setExpiry_date(emrForm.getExpiry_date());
            priscription.setPkg(emrForm.getPkg());
            priscription.setBatch_no(emrForm.getBatch_no());
            priscription.setMfg(emrForm.getMfg());
            priscription.setLocation(emrForm.getLocation());
            priscription.setSpecialization(emrForm.getSpecialization());
            priscription.setGenericname(emrForm.getGenericname());
            priscription.setRisk(emrForm.getRisk());
            priscription.setDosagenote(emrForm.getDosagenote());
			priscription.setPrisctypename(emrForm.getPrisctypename());
			priscription.setFrequency(request.getParameter("frequency"));
			priscription.setCaldose(request.getParameter("caldose"));
			priscription.setStrength(request.getParameter("strength"));
			
			priscription.setComponent(emrForm.getComponent());
			
			String dys=request.getParameter("noofdays");
			priscription.setDosefreq(emrForm.getDosefreq());
			
			String uom=request.getParameter("uom");
			String remark=request.getParameter("remark");
			
			priscription.setRemark(remark);
			priscription.setUom(uom);
			if(dys==null){
				dys="0";
			}
			if(dys.equals("")){
				dys="0";
			}
			priscription.setDays(Integer.parseInt(dys));
			priscription.setDr_qty(request.getParameter("qty1"));
			String iswtbs=request.getParameter("iswbd");
			if(iswtbs!=null){
				if(iswtbs.equals("1")){
					
				}else{
					iswtbs="0";
				}
			}else{
				iswtbs="0";
			}
			priscription.setIswbd(iswtbs);
            PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
            int result=prescriptionMasterDAO.updatePrescriptionDetails(priscription);
            
            
		} catch (Exception e) {

		   e.printStackTrace();
		}
	    finally{
	    	
	    	connection.close();
	    }
	
		return "save";
	}
	
	public String delete() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		try {
			
			String selectedid=request.getParameter("selectedid");
			connection=Connection_provider.getconnection();
		    PrescriptionMasterDAO dao=new JDBCPrescriptionMasterDAO(connection);
			int result=dao.deletePrescriptionDetails(selectedid);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		
		return "save";
	}
	
	
	public String back() {
		return "save";
	}
	
	
	
	
	
	public String addnewrow() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PrescriptionMasterDAO prescriptionMasterDAO = new JDBCPrescriptionMasterDAO(
					connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
			ArrayList<Priscription> prescriptioncategorylist = prescriptionMasterDAO
					.getPrescriptionCategoryList();
			ArrayList<Master> medicineLocationList= masterDAO.getMedicineLocationList();
			ArrayList<TreatmentType> specializationList=treatmentTypeDAO.getDeptConditionList();
			
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td class='hidden'><input type='checkbox' name='chkbox[]' title='Delete row' title='delete row' /></td>");
			buffer.append("<td>" + (rowcount) + "</td>");
			buffer.append("<td class='hidden'>");
			buffer.append("<select name='prescription_details["
					+ index
					+ "].categoryid' id='prescription_details' class='form-control showToolTip chosen' title = 'Select Prescription Category'>");
			buffer.append("<option value='0'>Select Prescription Category</option>");
			for (Priscription priscription : prescriptioncategorylist) {
				buffer.append("<option value='" + priscription.getId() + "'>"
						+ priscription.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].drug' id='drug' placeholder='Enter Medicine Name' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td><input type='text' name='prescription_details["+index+"].genericname' class='form-control' placeholder='Generic Name' /></td>");
			
			
			/*buffer.append("<td>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].strength' id='strength' placeholder='enter strength' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");*/
			buffer.append("<td class='hidden'>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].mrp' id='mrp' placeholder='enter mrp' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td class='hidden'> ");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].purchase_price' id='puchase_price' placeholder='enter purchase price' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td class='hidden'>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].sale_price' id='sale_price' placeholder='enter sale price' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			/*buffer.append("<td>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].expiry_date' id='expiry_date' placeholder='enter expiry date' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].pkg' id='pkg' placeholder='enter package' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].batch_no' id='batch_no' placeholder='enter batch no' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='prescription_details["
					+ index
					+ "].mfg' id='mfg' placeholder='enter mfg' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");*/
			
			buffer.append("<td>");
			   buffer.append("<div> <input type='hidden' name='prescription_details["+index+"].location' id='location"+index+"' />"); 
			     buffer.append("<input type='checkbox' onclick=selectAll('casem"+index+"',this) name='location"+index+"' /> Select All <br>");
			     for(Master master:medicineLocationList){
			    	   
			    	 buffer.append("<input type='checkbox' class=casem"+index+" value='"+master.getId()+"' /> "+master.getName()+" <br>");
			     }
			   buffer.append("</div>");
			buffer.append("</td>");
			buffer.append("<td>");
			   buffer.append("<div class='scroll'> <input type='hidden' name='prescription_details["+index+"].specialization' id='specialization"+index+"' />"); 
			     buffer.append("<input type='checkbox' name='specialise"+index+"' onclick=selectAll('caser"+index+"',this) /> Select All <br>");
			     for(TreatmentType treatmentType:specializationList){
			    	   
			    	 buffer.append("<input type='checkbox' class='caser"+index+"' value='"+treatmentType.getId()+"' /> "+treatmentType.getTreatmentName()+" <br>");
			     }
			   buffer.append("</div>");
			buffer.append("</td>");
			  buffer.append("<select class='form-control' name='prescription_details["+index+"].risk' > ");
			  
			    buffer.append("<option value='0'>Select Risk</option>");
			    buffer.append("<option value='1'>High</option>");
			    buffer.append("<option value='2'>Low</option>");
			  buffer.append("</select>");
			buffer.append("<td>");
			ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
			buffer.append("<select class='form-control' name='prescription_details["+index+"].dosagenote' > ");
			buffer.append("<option value='0'>Select Dosage Note</option>");
			 for(Master master: dosagenoteList){
				 buffer.append("<option value='"+master.getId()+"'> "+master.getName()+" </option>");
			 }
	        buffer.append("</select>");
			buffer.append("</td>");
			
			
			buffer.append("</tr>");
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
	
	public String getdosenote()  {
		 
		  Connection connection=null;
		  try {
		   
		   connection=Connection_provider.getconnection();
		   EmrDAO emrDAO = new JDBCEmrDAO(connection);
		   MasterDAO masterdao= new JDBCMasterDAO(connection);
		   PrescriptionMasterDAO masterDAO= new JDBCPrescriptionMasterDAO(connection);
		   String selectedid=request.getParameter("id");
		   Priscription priscription= masterDAO.getPrescriptionDetails(selectedid);
		   ArrayList<Master>dosageList = emrDAO.getDosageList();
		   ArrayList<Master>dosagenoteList = masterdao.getDosageNoteList();
		   ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
		   
		   
		   Priscription priscp=new Priscription();
		   priscp= masterDAO.getPrisc(selectedid);
		   String priscdose= priscp.getPriscdose();
		   String priscdays= priscp.getPriscdays();
		   String dosenote= priscp.getDosenotes();
		   String presc_time=priscription.getPriscriptiontime();
		   String unit= priscp.getUnit();
		   StringBuffer buffer = new StringBuffer();
		   buffer.append(priscdose);
		   
		   buffer.append("~"+priscdays+"~"+dosenote+"~"+unit);
		   buffer.append("~");
		   
		   //for Dose
		   buffer.append("<select  id='prisccode' onchange='setdosegiventiming(this.value)' class='form-control chosen'>");
		   buffer.append("<option value='0' >Frequency</option>");
		   for(Master dose:dosageList ){
		    if(priscdose!=null){
		     if(dose.getName().equals(priscdose)){
		      buffer.append("<option value='"+dose.getName()+"' selected>"+dose.getName()+"</option>");
		      
		     }else{
		      buffer.append("<option value='"+dose.getName()+"'>"+dose.getName()+"</option>");
		     }
		    }else{
		     buffer.append("<option value='"+dose.getName()+"'>"+dose.getName()+"</option>");
		    }
		   }buffer.append("</select>");
		   
		   buffer.append("~");
		   buffer.append("<select  id='priscdosenotes'  name='priscdosenotes' class='form-control chosen'>");
		   buffer.append("<option value='0' >Select Routes</option>");
		   
		   //for dose note
		   for(Master dosagenote:dosagenoteList ){
		    if(dosenote!=null){
		     if(dosagenote.getName().equals(dosenote)){
		      buffer.append("<option value='"+dosagenote.getName()+"' selected>"+dosagenote.getName()+"</option>");
		      
		     }else{
		      buffer.append("<option value='"+dosagenote.getName()+"'>"+dosagenote.getName()+"</option>");
		     }
		    }else{
		     buffer.append("<option value='"+dosagenote.getName()+"'>"+dosagenote.getName()+"</option>");
		    }
		   
		    
		   }
		   
		   buffer.append("</select>");
		   buffer.append("~");
		   
		   //presctime
		   buffer.append("<select  id='prisctime' class='form-control chosen'>");
		   buffer.append("<option value='0' >Frequency Note</option>");
		   
		   for(Priscription p: medicinetimelist){
		    if(presc_time!= null)
		    {
		     if(String.valueOf(p.getId()).equals(presc_time)){
		      buffer.append("<option value='"+p.getId()+"' selected>"+p.getPriscriptiontime()+"</option>");
		      
		     }else{
		      buffer.append("<option value='"+p.getId()+"'>"+p.getPriscriptiontime()+"</option>");
		     }
		    }else{
		     buffer.append("<option value='"+p.getId()+"'>"+p.getPriscriptiontime()+"</option>");
		    }
		    
		    
		   }
		   buffer.append("</select>");
		   
		   String dosenoteid= priscription.getDosagenote();
		   buffer.append("~");
		   
		   buffer.append(dosenoteid);
		   buffer.append("~");
		   String doselabletime=priscp.getDosegiventime();
		   String[] dosetime= new String[4];
			if(doselabletime!=null){
				if(!doselabletime.equals("")){
					dosetime = doselabletime.split("-");
					if(dosetime.length==3){
						if(dosetime[0]==null){
							dosetime[0]="";
						}
						if(dosetime[1]==null){
							dosetime[1]="";
						}
						if(dosetime[2]==null){
							dosetime[2]="";
						}
					}else{
						dosetime= new String[4];
						dosetime[0]="";
						dosetime[1]="";
						dosetime[2]="";
					}
				}else{
					dosetime[0]="";
					dosetime[1]="";
					dosetime[2]="";
				}
			}else{
				dosetime[0]="";
				dosetime[1]="";
				dosetime[2]="";
			}
			
			buffer.append(""+dosetime[0]+"");
			buffer.append("~");
			buffer.append(""+dosetime[1]+"");
			buffer.append("~");
			buffer.append(""+dosetime[2]+"");
			
			buffer.append("~");
			int qty=0;
			if(priscdose!=null){
				if(!priscdose.equals("") || (!priscdose.equals("0"))){
					
					boolean found = false;
				    for(char c : priscdose.toCharArray()){
				        if(Character.isDigit(c)){
				            found = true;
				        } else if(found){
				            // If we already found a digit before and this char is not a digit, stop looping
				            break;                
				        }
				    }
				    
				    String s = priscdays;
				    boolean checkalpha = s.matches(".*[a-zA-Z].*");
				    if(!checkalpha){
				    	if(found){
					    	if(priscdays!=null){
					    		if(!priscdays.equals("") || (!priscdays.equals("0"))){
					    			String[] priscdoselist = priscdose.split("-");
					    			for (String string : priscdoselist) {
										if(string!=null){
											if(!string.equals("")){
												boolean flag = false;
												if(string.matches("[0-9]+")){
													flag = true;
												}
												if(!flag){
													continue;
												}
												if(string.equals("0")){
													continue;
												}
												if(string.equals("1/2")){
													continue;
												}
												
												qty = qty + Integer.parseInt(priscdays) * Integer.parseInt(string);
											}
										}
									}
					    		}
					    	}
					    }
				    }
				    
				}
			}
			
			String patientId = request.getParameter("patientId");
//			System.out.println(patientId);
			
			  //Dose (DailyDose) = ((Wt x M_Dosage) / Frequency) / Strength
			  ClientDAO clientDAO = new JDBCClientDAO(connection);
			 
			  
			  Priscription pr= masterDAO.getPrescriptionDetails(selectedid);
			  
			 
			
			  double valueRounded = 0;
			  int days = 0;
			  double dddose = 0;
			  int resultqty = 0;
			
			if(loginInfo.getOutoprisc()==1){
				if(pr.getDr_qty()==null){
					pr.setDr_qty("0");
				}else{
					if(pr.getDr_qty().equals("")){
						pr.setDr_qty("0");
					}
				}
				 String weight = clientDAO.getClientWeight(patientId);
				   dddose = (Double.parseDouble(weight) * 
						  Double.parseDouble(pr.getCaldose())) / 
						  Double.parseDouble(pr.getFrequency()) /
						  Double.parseDouble(pr.getStrength());
				  
				  
				 double  tttqty = dddose *1.1 * Double.parseDouble(pr.getFrequency())
						  * priscription.getDays()/ Double.parseDouble(pr.getDr_qty());
				 
				  valueRounded = Math.ceil(tttqty);
				   resultqty = (int) valueRounded;
				  
				   String chdose = DateTimeUtils.changeFormat(dddose);
				   boolean chkdoseexixst = masterdao.chkdoseExsist(chdose);
				  
				   if(!chkdoseexixst){
					   dddose = masterdao.getnextGreaterDose(chdose);
				   }
						   
				priscription.setRemark("");
			}else{
				priscription.setRemark("");
				valueRounded=qty;
			}
			
			
			
			 
			
			buffer.append(""+qty+"");
			buffer.append("~"+priscription.getFrequency()+"~"
			+priscription.getIswbd()+"~"+priscription.getCaldose()+"~"
			+priscription.getStrength()+"~"+priscription.getDays()+"~"
			+priscription.getRemark()+"~"+resultqty+"~"+dddose+"~"
			+pr.getDosefreq()+"~"+pr.getUom()+"~"+pr.getRemark()+"~"+pr.getDosenotetxt());
			
			response.setCharacterEncoding("UTF-8");
			
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(buffer.toString());
		   //response.getWriter().write(priscdose);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return null;
		 }
	

	public void prepare() throws Exception {
		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			TreatmentTypeDAO treatmentEpisodeDAO=new JDBCTreatmentTypeDAO(con);
			EmrDAO emrDAO = new JDBCEmrDAO(con);
			
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			emrForm.setMasterlist(masterlist);
			
			ArrayList<Master> medicineLocationList= masterDAO.getMedicineLocationList();
			ArrayList<TreatmentType> specializationList=treatmentEpisodeDAO.getDeptConditionList();
			emrForm.setSpecializationList(specializationList);
			emrForm.setMedicineLocationList(medicineLocationList);
			
			ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
			emrForm.setMedicinetimelist(medicinetimelist);
			
			ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
			emrForm.setPriscUnitList(priscUnitList);
			
			ArrayList<Master>dosageList = emrDAO.getDosageList();
			emrForm.setDosageList(dosageList);
			
			ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();
			emrForm.setNimairemarklist(nimairemarklist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	
	public String searchbyajaxprisc(){
		Connection connection =null;
		String searchtxt=request.getParameter("searchtext");
		try {
			connection= Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Priscription> list= new ArrayList<Priscription>();
			list= masterDAO.getseachedpricofMaster(searchtxt);
			StringBuffer buffer= new StringBuffer();
			
			for(Priscription p:list){
				buffer.append("<tr>");
				buffer.append(" <td>"+p.getId()+"</td> ");
				buffer.append("<td>"+p.getGenericname()+"</td>");
				buffer.append("<td>"+p.getDrug()+"</td>");
				buffer.append("<td class='text-center'><a href='#' onclick=opencPopup('editPrescriptiondetails?selectedid="+p.getId()+"')><i class='fa fa-edit'></i></a></td>");
				buffer.append(" <td class='text-center'><a href=deletePrescriptiondetails?selectedid="+p.getId()+"   cssClass='text-danger'><i class='fa fa-trash-o'></i></a> </td>");				
				buffer.append("</tr>");
			}
			
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
		     response.getWriter().write(""+buffer.toString()+""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getmedondiagnosis() {
		Connection connection=null;
		try {
			connection= Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			String diagnosisid=request.getParameter("id");
			ArrayList<Master>medicinelist=masterDAO.getMedicineListbydiagnosis(diagnosisid);
			
			StringBuffer buffer =new StringBuffer();
			if(loginInfo.isAdd_medicine() || loginInfo.getUserType()==2){
				buffer.append("<a title='Add New Medicine' href='#' type='button' class='' onclick=openhiddendiv('hiddendiv') style='margin-right: 5px;'><i class='fa fa-pencil fa-2x cstm-icon' aria-hidden='true'></i></a>");
			} else{
				buffer.append("<a title='Add New Medicine' href='#' type='button' class=''  style='margin-right: 5px;'><i class='fa fa-pencil fa-2x cstm-icon' aria-hidden='true'></i></a>");
			} 
			buffer.append("<select class='form-control chosen' id='mdicinename' name='mdicinename' onchange='getDoseNote(this.value)' > ");
			buffer.append("<option value='0'>Select Medicine</option>");
			for(Master master:medicinelist){
				buffer.append("<option value='"+master.getId()+"'> "+master.getGenericname()+" </option>");
			}
			buffer.append("</select>");
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
		    response.getWriter().write(""+buffer.toString()+""); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
