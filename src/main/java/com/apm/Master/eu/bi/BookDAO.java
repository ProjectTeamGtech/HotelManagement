package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Book;
import com.apm.common.utils.Pagination;

public interface BookDAO {
	public ArrayList<Book> getallBooks(String searchText,Pagination pagination);
	
	public int getBookCount();
	public int updateBookDB(Book Book);
	public Book getBookinfo(Book book);
	public int deleteBookDB(Book book);
	public int addbookDB(Book book);
	
}
