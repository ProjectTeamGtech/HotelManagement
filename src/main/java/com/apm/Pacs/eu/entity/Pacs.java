package com.apm.Pacs.eu.entity;


public class Pacs {
	
	private int id;
	private String modality;
	private String pid;
	private String pname;
	private String studydate;
	private String recievedon;
	private String filename;
	private String dir;
	private String multipacsid;
	
	public String getMultipacsid() {
		return multipacsid;
	}
	public void setMultipacsid(String multipacsid) {
		this.multipacsid = multipacsid;
	}
	private String clientid;
	private String  practid;
	private String condition;
	
	private String date;
	private String doctype;
	private String findingtxt;
	private String finding;
	private int fstatus;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFinding() {
		return finding;
	}
	public void setFinding(String finding) {
		this.finding = finding;
	}
	public String getFindingtxt() {
		return findingtxt;
	}
	public void setFindingtxt(String findingtxt) {
		this.findingtxt = findingtxt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getPractid() {
		return practid;
	}
	public void setPractid(String practid) {
		this.practid = practid;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getStudydate() {
		return studydate;
	}
	public void setStudydate(String studydate) {
		this.studydate = studydate;
	}
	public String getRecievedon() {
		return recievedon;
	}
	public void setRecievedon(String recievedon) {
		this.recievedon = recievedon;
	}
	public int getFstatus() {
		return fstatus;
	}
	public void setFstatus(int fstatus) {
		this.fstatus = fstatus;
	}
private String abrivationid;

public String getAbrivationid() {
	return abrivationid;
}
public void setAbrivationid(String abrivationid) {
	this.abrivationid = abrivationid;
}
private String fullname;

public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
private String findinguser;

public String getFindinguser() {
	return findinguser;
}
public void setFindinguser(String findinguser) {
	this.findinguser = findinguser;
}
private String dicid;

public String getDicid() {
	return dicid;
}
public void setDicid(String dicid) {
	this.dicid = dicid;
}
}
