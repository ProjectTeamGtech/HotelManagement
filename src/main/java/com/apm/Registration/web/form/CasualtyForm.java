package com.apm.Registration.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Registration.eu.entity.Casualty;

public class CasualtyForm {
	
	
	
	private int id;
	private String title;
	private String fname;
	private String lname;
	private String age;
	private String gender;
	private String address;
	private String mob;
	private String provdiag;
	private String recbelonging;
	private String drincharge;
	private String mlc;
	private String refby;
	private String typeofdis;
	private String note;
	private String mlcno;
	private int totalRecords;
	private String pagerecords;
	
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	ArrayList<Casualty>casualtyList;
	private ArrayList<Client> treatmentTypeList;
	
	ArrayList<String> initialList;
	ArrayList<DiaryManagement>userList;
	
	public ArrayList<String> getInitialList() {
		return initialList;
	}
	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getProvdiag() {
		return provdiag;
	}
	public void setProvdiag(String provdiag) {
		this.provdiag = provdiag;
	}
	public String getRecbelonging() {
		return recbelonging;
	}
	public void setRecbelonging(String recbelonging) {
		this.recbelonging = recbelonging;
	}
	public String getDrincharge() {
		return drincharge;
	}
	public void setDrincharge(String drincharge) {
		this.drincharge = drincharge;
	}
	public String getMlc() {
		return mlc;
	}
	public void setMlc(String mlc) {
		this.mlc = mlc;
	}
	public String getRefby() {
		return refby;
	}
	public void setRefby(String refby) {
		this.refby = refby;
	}
	public String getTypeofdis() {
		return typeofdis;
	}
	public void setTypeofdis(String typeofdis) {
		this.typeofdis = typeofdis;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	public ArrayList<Casualty> getCasualtyList() {
		return casualtyList;
	}
	public void setCasualtyList(ArrayList<Casualty> casualtyList) {
		this.casualtyList = casualtyList;
	}
	public ArrayList<Client> getTreatmentTypeList() {
		return treatmentTypeList;
	}
	public void setTreatmentTypeList(ArrayList<Client> treatmentTypeList) {
		this.treatmentTypeList = treatmentTypeList;
	}
	public String getMlcno() {
		return mlcno;
	}
	public void setMlcno(String mlcno) {
		this.mlcno = mlcno;
	}
	

}
