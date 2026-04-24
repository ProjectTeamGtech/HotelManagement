package com.apm.Master.eu.entity;

public class Declaration {
	private int id;
	private String declarationNotes;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeclarationNotes() {
		return declarationNotes;
	}
	public void setDeclarationNotes(String declarationNotes) {
		this.declarationNotes = declarationNotes;
	}
}
