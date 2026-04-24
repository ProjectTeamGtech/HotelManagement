package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.Master;

public class BookForm {

private	ArrayList<Master> masterlist;
private String mastername;
private ArrayList<Book> booklist;
private String searchText;
private String pagerecords;
private String totalRecords;
private String name;
private int id;
public ArrayList<Master> getMasterlist() {
	return masterlist;
}

public void setMasterlist(ArrayList<Master> masterlist) {
	this.masterlist = masterlist;
}

public String getMastername() {
	return mastername;
}

public void setMastername(String mastername) {
	this.mastername = mastername;
}

public ArrayList<Book> getBooklist() {
	return booklist;
}

public void setBooklist(ArrayList<Book> booklist) {
	this.booklist = booklist;
}



public String getPagerecords() {
	return pagerecords;
}

public void setPagerecords(String pagerecords) {
	this.pagerecords = pagerecords;
}



public String getTotalRecords() {
	return totalRecords;
}

public void setTotalRecords(String totalRecords) {
	this.totalRecords = totalRecords;
}

public String getSearchText() {
	return searchText;
}

public void setSearchText(String searchText) {
	this.searchText = searchText;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

}
