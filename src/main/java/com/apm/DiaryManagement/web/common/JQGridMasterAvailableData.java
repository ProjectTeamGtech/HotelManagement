package com.apm.DiaryManagement.web.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

public class JQGridMasterAvailableData {
	
	@SuppressWarnings("unchecked")
	public JSONObject getJSONData(String diaryuserid,String practitionerid,String rdddval) throws JSONException, SQLException {
		
		
//		System.out.println(">>>>>"+diaryuserid);
		JSONObject jsonOutResult = new JSONObject();
		
		ArrayList<Object> list = new ArrayList<Object>(); 
		HashMap<String, Object> hashMap;
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			
			//System.out.println(loginInfo.getUserId());
			//int year = (Integer) session.getAttribute("year");
			String opendb = (String)session.getAttribute("openedb");
			rdddval = (String)session.getAttribute("sessionrddval");
			
			ArrayList<NotAvailableSlot>apmtList = diaryManagementDAO.getApmtList(diaryuserid,practitionerid,opendb,rdddval);
			
			
			
			JSONArray array = new JSONArray();
			
			
			for(NotAvailableSlot notAvailableSlot : apmtList){
				
				double balance = 0;
				
				/*if(notAvailableSlot.getStatus().equals("0")){
					if(notAvailableSlot.getWhopay().equals(Constants.PAY_BY_CLIENT)){
						double debit = diaryManagementDAO.getClientDebitTotal(notAvailableSlot.getClientId());
						double payment = diaryManagementDAO.getClientPayment(notAvailableSlot.getClientId());
						
						balance = debit - payment;
						
						System.out.println(balance);
					}
				}*/
				
				JSONObject jsonRecordsList = new JSONObject();
				jsonRecordsList.put("id", notAvailableSlot.getId());
				jsonRecordsList.put("starttime", notAvailableSlot.getSTime());
				jsonRecordsList.put("endtime", notAvailableSlot.getEndTime());
				jsonRecordsList.put("apmtduration", notAvailableSlot.getApmtDuration());
				jsonRecordsList.put("clientname", notAvailableSlot.getClientName());
				jsonRecordsList.put("apmttype", notAvailableSlot.getApmtType());
				jsonRecordsList.put("notes", notAvailableSlot.getNotes());
				jsonRecordsList.put("duration", notAvailableSlot.getDuration());
				jsonRecordsList.put("status", notAvailableSlot.getStatus());
				jsonRecordsList.put("arrivedstatus", notAvailableSlot.getArrivedStatus());
				jsonRecordsList.put("dna", notAvailableSlot.isDna());
				jsonRecordsList.put("clientId", notAvailableSlot.getClientId());
				jsonRecordsList.put("commencing", notAvailableSlot.getCommencing());
				jsonRecordsList.put("practitionerEmail", notAvailableSlot.getPractitionerEmail());
				jsonRecordsList.put("clientEmail", notAvailableSlot.getClientEmail());
				jsonRecordsList.put("charge", DateTimeUtils.changeFormat(notAvailableSlot.getCharge()));
				jsonRecordsList.put("reasonforblock", notAvailableSlot.getReasonforblock());
				jsonRecordsList.put("shortnotes", notAvailableSlot.getNotes());
				if(notAvailableSlot.getNotes().length() > 50){
					jsonRecordsList.put("shortnotes", notAvailableSlot.getNotes().substring(0, 30));
				}
				jsonRecordsList.put("treatmentepisodeid", notAvailableSlot.getTreatmentEpisodeId());
				jsonRecordsList.put("iscompleted", notAvailableSlot.isAppointmentCompleted());
				jsonRecordsList.put("chargecompleted", notAvailableSlot.isChargeCompleted());
				jsonRecordsList.put("usedsession", notAvailableSlot.getUsedsession());
				jsonRecordsList.put("tempusedsession", notAvailableSlot.getTempusedSession());
				jsonRecordsList.put("apmttypetext", notAvailableSlot.getApmttypetext());
				jsonRecordsList.put("location", notAvailableSlot.getLocation());
				

				jsonRecordsList.put("firstapmt", notAvailableSlot.getFirstApmt());
				jsonRecordsList.put("lastapmt", notAvailableSlot.getLastApmt());
				jsonRecordsList.put("payby", notAvailableSlot.getPayBy());
				jsonRecordsList.put("conditionid", notAvailableSlot.getCondition());
				
				//set tp details
				jsonRecordsList.put("tptypeid", notAvailableSlot.getTptypeid());
				jsonRecordsList.put("tpnameid", notAvailableSlot.getTpnameid());
				jsonRecordsList.put("whopay", notAvailableSlot.getWhopay());
				jsonRecordsList.put("locid", notAvailableSlot.getLocid());
				jsonRecordsList.put("tpname", notAvailableSlot.getTpName());
				jsonRecordsList.put("dnanotes", notAvailableSlot.getDnaNotes());
				jsonRecordsList.put("isreportsent", notAvailableSlot.getIsReportsent());
				jsonRecordsList.put("loggedemail", loginInfo.getEmail());
				jsonRecordsList.put("bal", DateTimeUtils.changeFormat(balance));
				jsonRecordsList.put("usrtype", loginInfo.getUserType());
				jsonRecordsList.put("otid", notAvailableSlot.getOtid());
				jsonRecordsList.put("otname", notAvailableSlot.getOtname());
				jsonRecordsList.put("work", notAvailableSlot.getWork());
				
				//ot values
				jsonRecordsList.put("planed", notAvailableSlot.getOtplan());
				jsonRecordsList.put("procedure", notAvailableSlot.getProcedure());
				jsonRecordsList.put("surgeon", notAvailableSlot.getSurgeon());
				jsonRecordsList.put("anesthesia", notAvailableSlot.getAnesthesia());
				jsonRecordsList.put("ipdno", notAvailableSlot.getIpdno());
				jsonRecordsList.put("wardid", notAvailableSlot.getWardid());
				jsonRecordsList.put("asisdoclist", notAvailableSlot.getAsistantdoclist());
				jsonRecordsList.put("token", notAvailableSlot.getToken());
				jsonRecordsList.put("otmsg", notAvailableSlot.getOtmsg());
				jsonRecordsList.put("otaccname", notAvailableSlot.getOtaccname());
				
				
				
				if(notAvailableSlot.getImagename()==null){
					notAvailableSlot.setImagename("");
				} 				
				jsonRecordsList.put("imagename", notAvailableSlot.getImagename());
				
				Client client=clientDAO.getPatientBMIData(notAvailableSlot.getClientId(),notAvailableSlot.getId());
//				System.out.println(notAvailableSlot.getClientId());
				jsonRecordsList.put("height", client.getHeight());
				jsonRecordsList.put("weight", client.getWeight());
				jsonRecordsList.put("bmi",client.getBmi());
				jsonRecordsList.put("pulse", client.getPulse());
				jsonRecordsList.put("sysbp", client.getSysbp());
				jsonRecordsList.put("diabp", client.getDiabp());
				jsonRecordsList.put("sugarfasting", client.getSugarfasting());
				jsonRecordsList.put("postmeal", client.getPostmeal());
				jsonRecordsList.put("temprature", client.getTemprature());
				jsonRecordsList.put("spo", client.getSpo());
				jsonRecordsList.put("bsa", client.getHead_cir());
				jsonRecordsList.put("luhid", loginInfo.getUhid());
				jsonRecordsList.put("uhid", notAvailableSlot.getUhid());
				NotAvailableSlot notAvailableSlot1=notAvailableSlotDAO.getAvailableSlotdata(notAvailableSlot.getId());
				jsonRecordsList.put("isotoropd", notAvailableSlot1.getProcedure());
				array.add(jsonRecordsList);
			}
		
			jsonOutResult.put("jsonOutResult", array);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
			
		
		return jsonOutResult;
		
		
	}

}
