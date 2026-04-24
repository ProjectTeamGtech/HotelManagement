package com.apm.DiaryManagement.web.common;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

public class JQGridMaster extends  ActionSupport {

	public static void main(String[] args) throws JSONException {
		JQGridMaster master = new JQGridMaster();
		//System.out.println(master.getJSONData(""));
	}
	
	
	

	@SuppressWarnings("unchecked")
	public JSONObject getJSONData(String diaryuserid,String tdcode) throws JSONException, SQLException {
		
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
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
//			System.out.println(loginInfo.getUserId());
			int year = (Integer) session.getAttribute("year");
			
			ArrayList<DiaryManagement>diaryUserList = diaryManagementDAO.getAppointmentSlotData(diaryuserid,loginInfo.getId(),tdcode,Integer.toString(year));
			JSONArray array = new JSONArray();
			
			
				for(DiaryManagement diaryManagement : diaryUserList){
					
					JSONObject jsonRecordsList = new JSONObject();
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
					jsonRecordsList.put("clinicstime", 1);
					
					
					
					array.add(jsonRecordsList);
				}
			
				jsonOutResult.put("jsonOutResult", array);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
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

}
