package com.apm.InstantMessage.web.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;


import com.apm.InstantMessage.eu.bi.InstantMsgDAO;
import com.apm.InstantMessage.eu.blogic.jdbc.JDBCInstantMsgDAO;
import com.apm.InstantMessage.eu.entity.InstantMsg;
import com.apm.common.eu.blogic.jdbc.Connection_provider;


public class JQGridAllContacts {
	@SuppressWarnings("unchecked")
	public JSONObject getJSONData() throws JSONException {
		
	
		JSONObject jsonOutResult = new JSONObject();
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			InstantMsgDAO instantMsgDAO = new JDBCInstantMsgDAO(connection);
			ArrayList<InstantMsg> allContactList = new ArrayList<InstantMsg>(); 
			allContactList = instantMsgDAO.getAllContacts();
			
			JSONArray array = new JSONArray();
			
			
			for(InstantMsg instantMsg : allContactList){
				
				JSONObject jsonRecordsList = new JSONObject();
					jsonRecordsList.put("id", instantMsg.getId());
					jsonRecordsList.put("name", instantMsg.getName());
					jsonRecordsList.put("designation", instantMsg.getDesignation());
					jsonRecordsList.put("emailId", instantMsg.getEmailId());
					
				
				
				
				
				array.add(jsonRecordsList);
			}
		
			jsonOutResult.put("jsonOutResult", array);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		
		return jsonOutResult;
		
		
	}
}
