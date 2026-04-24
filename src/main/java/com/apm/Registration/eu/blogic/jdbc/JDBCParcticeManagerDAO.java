package com.apm.Registration.eu.blogic.jdbc;

import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Registration.eu.bi.PracticeManagerDAO;
import com.apm.Registration.eu.entity.PracticeManager;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;

public class JDBCParcticeManagerDAO extends JDBCBaseDAO implements PracticeManagerDAO{
	
	public JDBCParcticeManagerDAO(Connection connection){
		
		this.connection = connection;
	}

	public ArrayList<PracticeManager> getPracticeManagerList() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
