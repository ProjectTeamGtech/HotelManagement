package com.apm.Master.web.action;


import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.BookDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCBookDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCStateDAO;
import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Master.web.form.BookForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BookAction extends BaseAction implements ModelDriven<BookForm>,Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5908828765897245308L;
	BookForm bookForm = new BookForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(15,1);
    
    String mastername=null;
    
    public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	

	public void prepare() throws Exception {
		Connection connection= null;
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			bookForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			bookForm.setMastername(mastername);
			
			BookDAO bookdao= new JDBCBookDAO(connection);
			ArrayList<Book> booklist= bookdao.getallBooks(null, null);
			bookForm.setBooklist(booklist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}

	public BookForm getModel() {
		// TODO Auto-generated method stub
		return bookForm;
	}
	public String execute(){
		return "";
	}
	public String showallbook()throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			BookDAO bookDAO = new JDBCBookDAO(connection);
			int count=bookDAO.getBookCount();
			pagination.setPreperties(count);
			String searchText = bookForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Book> booklist = bookDAO.getallBooks(searchText, pagination);
			
			bookForm.setBooklist(booklist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(booklist.size());
			bookForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			bookForm.setTotalRecords(String.valueOf(count));
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			bookForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "showallbook";
	}
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			BookDAO bookDAO = new JDBCBookDAO(connection);
			Book book= new Book();
			book.setName(bookForm.getName());
			int result = bookDAO.addbookDB(book);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "savebook";
	}
	public String edit(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			BookDAO bookDAO = new JDBCBookDAO(connection);
			Book book= new Book();
			book.setId(bookForm.getId());
			Book book2 = bookDAO.getBookinfo(book);
			bookForm.setId(book2.getId());
			bookForm.setName(book2.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editbook";
	}
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			BookDAO bookDAO = new JDBCBookDAO(connection);
			Book book = new Book();
			book.setId(bookForm.getId());
			book.setName(bookForm.getName());
			int result =bookDAO.updateBookDB(book);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updatebook";
	}
	
	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			BookDAO bookDAO = new JDBCBookDAO(connection);
			Book book = new Book();
			book.setId(bookForm.getId());
			book.setName(bookForm.getName());
			int result = bookDAO.deleteBookDB(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedbook";
	}
	public String add(){
		return "addbook";	
	}

	
}
