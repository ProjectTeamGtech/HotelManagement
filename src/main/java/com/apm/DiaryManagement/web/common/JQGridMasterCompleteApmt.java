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
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;

public class JQGridMasterCompleteApmt {
	@SuppressWarnings("unchecked")
	public JSONObject getJSONData(String id) throws JSONException, SQLException {
		
	
		JSONObject jsonOutResult = new JSONObject();
		
		ArrayList<Object> list = new ArrayList<Object>(); 
		HashMap<String, Object> hashMap;
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			ArrayList<CompleteAppointment> detailsList = new ArrayList<CompleteAppointment>();
			detailsList = completeAptmDAO.getCompleteApmtData(id);
			JSONObject jsonRecords = new JSONObject();
			
			JSONArray array = new JSONArray();
			
			
			for(CompleteAppointment completeAppointment : detailsList){
				
				JSONObject jsonRecordsList = new JSONObject();
			
				jsonRecordsList.put("date", completeAppointment.getCommencing());
				jsonRecordsList.put("starttime",completeAppointment.getStartTime());
				jsonRecordsList.put("practitionerId",completeAppointment.getPractitionerId());
				jsonRecordsList.put("practitionerName",completeAppointment.getPractitionerName());
				jsonRecordsList.put("location",completeAppointment.getLocationid());
				jsonRecordsList.put("clientId",completeAppointment.getClientId());
				jsonRecordsList.put("client",completeAppointment.getClient());
				jsonRecordsList.put("apmtTypeId",completeAppointment.getApmtType());
				jsonRecordsList.put("duration",completeAppointment.getDuration());
				jsonRecordsList.put("apmTypeText",completeAppointment.getApmtypeText());
				jsonRecordsList.put("treatmentEpisodeId", completeAppointment.getTreatmentEpisodeId());
				jsonRecordsList.put("charges", completeAppointment.getCharges());
				jsonRecordsList.put("usedSession", completeAppointment.getUsedSession());
				jsonRecordsList.put("totalSession", completeAppointment.getTotalSession());
				jsonRecordsList.put("payBuy", completeAppointment.getPayBuy());
				jsonRecordsList.put("invoicee", completeAppointment.getInvoicee());
				jsonRecordsList.put("tpnotes", completeAppointment.getTpNotes());
			    
				Client client=clientDAO.getClientDetails(completeAppointment.getClientId());
				if(client.getImageName()==null){
					client.setImageName("");
				} 
				jsonRecordsList.put("imagename", client.getImageName());
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
