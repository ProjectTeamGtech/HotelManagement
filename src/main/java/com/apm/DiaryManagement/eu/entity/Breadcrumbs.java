package com.apm.DiaryManagement.eu.entity;

public class Breadcrumbs {
private boolean on;
private String urllink;
private String name;
private int sqno;
private boolean iscurrent;
private String showingname;

public String getShowingname() {
	return showingname;
}
public void setShowingname(String showingname) {
	this.showingname = showingname;
}
public boolean isIscurrent() {
	return iscurrent;
}
public void setIscurrent(boolean iscurrent) {
	this.iscurrent = iscurrent;
}
public boolean getOn() {
	return on;
}
public void setOn(boolean on) {
	this.on = on;
}
public String getUrllink() {
	return urllink;
}
public void setUrllink(String urllink) {
	this.urllink = urllink;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getSqno() {
	return sqno;
}
public void setSqno(int sqno) {
	this.sqno = sqno;
}

}
