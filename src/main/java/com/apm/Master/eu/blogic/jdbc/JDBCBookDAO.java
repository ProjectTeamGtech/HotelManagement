package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import com.apm.Master.eu.bi.BookDAO;
import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.Book;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;


public class JDBCBookDAO extends JDBCBaseDAO implements BookDAO {
	
	public JDBCBookDAO(Connection connection){
		this.connection = connection;
		}


	public ArrayList<Book> getallBooks(String searchText,Pagination pagination) {
		ArrayList<Book> users = new ArrayList<Book>();
		try {
			Statement stmt = connection.createStatement();
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name from book_master where name like ('"+searchText+"%')";
			} else {
				sql = "select id,name from book_master";
			}
			if(pagination!=null)
			{
				sql=pagination.getSQLQuery(sql);
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setName(rs.getString(2));
				users.add(book);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public int getBookCount(){
		int count=0;
		try {
		     
			String sql="select count(*) from book_master";
			PreparedStatement stmt=connection.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	
	public int addbookDB(Book book) {
		int result = 0;
		try {
			String query = "insert into book_master(name) values(?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, book.getName());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteBookDB(Book book) {
		int result = 0;
		try {
			
			int j =book.getId();
			String query = "delete from book_master where id="+book.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
		
		
		
		
		
		
	}

	public Book getBookinfo(Book book) {
		Book Book2 =null;
		try {
			String query= "select id,name from book_master where id="+book.getId();
			PreparedStatement PreparedStatement = connection.prepareStatement(query);
			ResultSet rs = PreparedStatement.executeQuery();
			if(rs.next()){
				Book2 = new Book();
				Book2.setId(rs.getInt(1));
				Book2.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Book2;
	}


	public int updateBookDB(Book Book) {
		int result = 0;
		try {
			String query = "update book_master set name=? where id="+Book.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, Book.getName());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}


