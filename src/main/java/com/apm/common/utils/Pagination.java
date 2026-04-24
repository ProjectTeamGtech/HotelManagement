package com.apm.common.utils;



public class Pagination {
	
	public int page_size = 0; 
	public int page_number = 0;
	public String sortColumn =  null;
	public String sortOrder =  "ASC";
	public int total_records = 0;
	public int page_records = 0;
	public int start = 0;
	public int end = 0;
	public int total_pages = 0;
	public int showPage = 0;
	public int showtoPage = 0;
	public String sortClass = null;
	
	public void setPreperties(int count){
		total_records = count;
		total_pages = (int) Math.ceil(((double)count / (double)page_size));
		start = ((page_size * page_number) - page_size);
		end = page_size;
		showPage = start+1;
		showtoPage = page_size * page_number;
		
		if(page_size==0){
			start = 0;
			end = count;
			total_pages = 1;
		}
		if(sortOrder.equals("ASC"))	sortClass = "order1";
		else sortClass = "order2";
	}
	
	public Pagination(int page_size, int page_number) {
		this.page_number = page_number;
		this.page_size = page_size;
	}
	
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getPage_number() {
		return page_number;
	}
	public void setPage_number(int page_number) {
		this.page_number = page_number;
	}
	public int getTotal_records() {
		return total_records;
	}
	public void setTotal_records(int total_records) {
		this.total_records = total_records;
	}
	public int getPage_records() {
		return page_records;
	}
	public void setPage_records(int page_records) {
		this.page_records = page_records;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public String getSortOrder() {
		return (sortOrder==null)?"":sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSortColumn() {
		return (sortColumn==null)?"":sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortClass() {
		return sortClass;
	}

	public void setSortClass(String sortClass) {
		this.sortClass = sortClass;
	}

	/**
	 * @param query
	 * @return
	 */
	public String getSQLQuery(String query) {
		if(sortColumn != null && sortColumn.length() > 0) 
			query += " ORDER BY " + sortColumn + " " + sortOrder;
		query += " LIMIT " + start + ", " + end;
		return query;
	}

	public int getShowPage() {
		return showPage;
	}

	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}

	public int getShowtoPage() {
		return showtoPage;
	}

	public void setShowtoPage(int showtoPage) {
		this.showtoPage = showtoPage;
	}
	public String getManualIpdId() {
		return manualIpdId;
	}

	public void setManualIpdId(String manualIpdId) {
		this.manualIpdId = manualIpdId;
	}
	private String manualIpdId;
}
