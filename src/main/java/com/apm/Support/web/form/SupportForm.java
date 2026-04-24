package com.apm.Support.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Support.eu.entity.Support;

public class SupportForm {

	private ArrayList<Clinic> cliniclist;
	private String assignedTo;
	private String side;
	public ArrayList<Clinic> getCliniclist() {
		return cliniclist;
	}

	public void setCliniclist(ArrayList<Clinic> cliniclist) {
		this.cliniclist = cliniclist;
	}
	public ArrayList<UserProfile> getSupportrequestlist() {
		return supportrequestlist;
	}

	public void setSupportrequestlist(ArrayList<UserProfile> supportrequestlist) {
		this.supportrequestlist = supportrequestlist;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getClinicid() {
		return clinicid;
	}

	public void setClinicid(String clinicid) {
		this.clinicid = clinicid;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public ArrayList<Support> getExecutivelist() {
		return executivelist;
	}

	public void setExecutivelist(ArrayList<Support> executivelist) {
		this.executivelist = executivelist;
	}
	public ArrayList<Support> getMessagelist() {
		return messagelist;
	}

	public void setMessagelist(ArrayList<Support> messagelist) {
		this.messagelist = messagelist;
	}
	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	public String getExecutive() {
		return executive;
	}

	public void setExecutive(String executive) {
		this.executive = executive;
	}
	private ArrayList<UserProfile> supportrequestlist;
	private String username;
	private String clinicid;
	private String status;
	private String todate ;
	private String fromdate;
	
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	private ArrayList<Support> executivelist;
	private ArrayList<Support> messagelist;
	private String ticketid;
	private String executive; 
	private String query,querytype,userid,datetime;
	private int totalcount;
	private String priority;
	private int fixed=0,requseted=0,notPossible=0,wip=0;

	public int getFixed() {
		return fixed;
	}

	public void setFixed(int fixed) {
		this.fixed = fixed;
	}

	public int getRequseted() {
		return requseted;
	}

	public void setRequseted(int requseted) {
		this.requseted = requseted;
	}

	public int getNotPossible() {
		return notPossible;
	}

	public void setNotPossible(int notPossible) {
		this.notPossible = notPossible;
	}

	public int getWip() {
		return wip;
	}

	public void setWip(int wip) {
		this.wip = wip;
	}
public ArrayList<Master> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Master> moduleList) {
		this.moduleList = moduleList;
	}
public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
private ArrayList<Master> moduleList;	
private String modulename;
private String subuploadfilesContentType;
private String subuploadfilesFileName;
private File subuploadfiles;

public String getSubuploadfilesContentType() {
	return subuploadfilesContentType;
}

public void setSubuploadfilesContentType(String subuploadfilesContentType) {
	this.subuploadfilesContentType = subuploadfilesContentType;
}

public String getSubuploadfilesFileName() {
	return subuploadfilesFileName;
}

public void setSubuploadfilesFileName(String subuploadfilesFileName) {
	this.subuploadfilesFileName = subuploadfilesFileName;
}

public File getSubuploadfiles() {
	return subuploadfiles;
}

public void setSubuploadfiles(File subuploadfiles) {
	this.subuploadfiles = subuploadfiles;
}

public String getAssignedTo() {
	return assignedTo;
}

public void setAssignedTo(String assignedTo) {
	this.assignedTo = assignedTo;
}

public String getSide() {
	return side;
}

public void setSide(String side) {
	this.side = side;
}
}
