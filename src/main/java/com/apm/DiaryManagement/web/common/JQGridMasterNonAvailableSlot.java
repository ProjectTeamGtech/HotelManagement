package com.apm.DiaryManagement.web.common;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

public class JQGridMasterNonAvailableSlot extends ActionSupport {
	
	public static void main(String[] args) throws JSONException {
		JQGridMasterNonAvailableSlot master = new JQGridMasterNonAvailableSlot();
		//System.out.println(master.getJSONData(""));
	}
	
	
	

	
public JSONObject getJSONData(String diaryuserid,String date,String locationid) throws JSONException {
		
		String dateTemp[] = date.split("-");
		date = dateTemp[2] + "-" + dateTemp[1] + "-"  + dateTemp[0];
		
		
		String year = dateTemp[2];
		
		
//		System.out.println(diaryuserid);;
		JSONObject jsonOutResult = new JSONObject();
		
		ArrayList<Object> list = new ArrayList<Object>(); 
		HashMap<String, Object> hashMap;
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			ArrayList<DiaryManagement>diaryUserList = new ArrayList<DiaryManagement>();
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
//			System.out.println(loginInfo.getUserId());
			
			diaryUserList = diaryManagementDAO.getAppointmentSlotData2(diaryuserid,loginInfo.getId(),date,year,locationid);
		
			
			JSONArray array = new JSONArray();
			
			
				for(DiaryManagement diaryManagement : diaryUserList){
					
					JSONObject jsonRecordsList = new JSONObject();
					jsonRecordsList.put("id", diaryManagement.getId());
					jsonRecordsList.put("starttime", diaryManagement.getSTime());
					jsonRecordsList.put("endtime", diaryManagement.getEndTime());
					jsonRecordsList.put("apmtduration", diaryManagement.getApmtDuration());
					jsonRecordsList.put("location", diaryManagement.getLocation());
					jsonRecordsList.put("locationName", diaryManagement.getLocationName());
					jsonRecordsList.put("color", diaryManagement.getLocationColor());
					jsonRecordsList.put("tdcode", diaryManagement.getTdCode());
					jsonRecordsList.put("weekname", diaryManagement.getWeekName());
					jsonRecordsList.put("commencing", diaryManagement.getCommencing());
					jsonRecordsList.put("weekfullname", diaryManagement.getWeekFullName());
					jsonRecordsList.put("onlinebooking", diaryManagement.isOnlineBooking());
					jsonRecordsList.put("diaryUser", diaryManagement.getDiaryUser());
					jsonRecordsList.put("practitionerid", diaryManagement.getDiarUserid());
					jsonRecordsList.put("discipline", diaryManagement.getDisciplineid());
					jsonRecordsList.put("clinicstime", loginInfo.getClinicStartTime());

					
					array.add(jsonRecordsList);
				}
			
				jsonOutResult.put("jsonOutResult", array);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	/*	for(int i=1; i <11;i++){
		    hashMap = new HashMap<String, Object>();
			hashMap.put("id",i);
			hashMap.put("name","Name " + i);
			hashMap.put("address","Address " + i);
			hashMap.put("age","Age " + i);
			
			list.add(hashMap);
		}
		
		Iterator<Object> iterator = list.iterator();
		JSONArray array = new JSONArray();
		while(iterator.hasNext()){
			HashMap<String, Object> hashMap1 = (HashMap<String, Object>) iterator.next();
			
			JSONObject jsonRecordsList = new JSONObject();
			jsonRecordsList.put("id", hashMap1.get("id"));
			jsonRecordsList.put("name", hashMap1.get("name"));
			jsonRecordsList.put("address", hashMap1.get("address"));
			jsonRecordsList.put("age", hashMap1.get("age"));
			
			
			array.add(jsonRecordsList);
			
			
		}
		jsonOutResult.put("jsonOutResult", array);*/
		return jsonOutResult;

	}
	
public JSONObject getJSONData1(String date) throws JSONException {
		
		
		String temp[] = date.split("/");
		String year1 = temp[2];
//		System.out.println(year1);
		
		//System.out.println(diaryuserid);;
		JSONObject jsonOutResult = new JSONObject();
		
		ArrayList<Object> list = new ArrayList<Object>(); 
		HashMap<String, Object> hashMap;
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
//			System.out.println(loginInfo.getUserId());
			
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			//start coding
			
			
			int year = Integer.parseInt(year1);
			
			ArrayList<DiaryManagement>diaryUserList = diaryManagementDAO.getAppointmentSlotData3(loginInfo.getId(),date,year1);
			JSONArray array = new JSONArray();
			
			
				for(DiaryManagement diaryManagement : diaryUserList){
					
					JSONObject jsonRecordsList = new JSONObject();
					jsonRecordsList.put("dairyuserid", diaryManagement.getDiarUserid());
					jsonRecordsList.put("id", diaryManagement.getId());
					jsonRecordsList.put("starttime", diaryManagement.getSTime());
					jsonRecordsList.put("endtime", diaryManagement.getEndTime());
					jsonRecordsList.put("apmtduration", diaryManagement.getApmtDuration());
					jsonRecordsList.put("location", diaryManagement.getLocation());
					jsonRecordsList.put("color", diaryManagement.getLocationColor());
					jsonRecordsList.put("tdcode", diaryManagement.getTdCode());
					jsonRecordsList.put("weekname", diaryManagement.getWeekName());
					jsonRecordsList.put("commencing", diaryManagement.getCommencing());
					jsonRecordsList.put("weekfullname", diaryManagement.getWeekFullName());
					jsonRecordsList.put("onlinebooking", diaryManagement.isOnlineBooking());
					
					
					array.add(jsonRecordsList);
				}
			
				jsonOutResult.put("jsonOutResult", array);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	
		return jsonOutResult;

	}

public JSONObject getJSONAllUser() throws JSONException {
	
	JSONObject jsonOutResult = new JSONObject();
	ArrayList<Object> list = new ArrayList<Object>(); 
	HashMap<String, Object> hashMap;
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpSession session = request.getSession(true);
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
//		System.out.println(loginInfo.getUserId());
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		
		JSONArray array = new JSONArray();
		
				
			
		
			jsonOutResult.put("jsonOutResult", array);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	


	return jsonOutResult;

}

public JSONObject getipddetailsData(String clientid,String admissionid,String treatmentid) throws JSONException {
	
	JSONObject jsonOutResult = new JSONObject();
	
	
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpSession session = request.getSession(true);
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		ArrayList<DiaryManagement>diaryUserList = new ArrayList<DiaryManagement>();
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
//		System.out.println(loginInfo.getUserId());
		
		//diaryUserList = diaryManagementDAO.getAppointmentSlotData2(clientid,loginInfo.getId(),admissionid,year,treatmentid);
		String stdChargeID = null;
		boolean checkStandardChargeExist=ipdDAO.checkStandardChargeExist(Integer.parseInt(admissionid));
		if(checkStandardChargeExist) {
		  stdChargeID=ipdDAO.getAllActiveStdCharges(Integer.parseInt(admissionid));
		}
		
		JSONArray array = new JSONArray();
		JSONObject jsonRecordsList = new JSONObject();
		jsonRecordsList.put("checkStandardChargeExist", checkStandardChargeExist);
		jsonRecordsList.put("stdChargeID", stdChargeID);
		array.add(jsonRecordsList);
		jsonOutResult.put("jsonOutResult", array);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return jsonOutResult;

}

}
