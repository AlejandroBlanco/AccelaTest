package com.accela.test.dbconsole;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

	private static final String USER = "agenda";
	private static final String PASS = "Agenda654App";
	private static final String CONN = "jdbc:mysql://127.0.0.1:3306/agenda?useSSL=false&useUnicode=yes&"
			+ "characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	public Connection getDatabaseConnection() {
		Connection conn = null;
		try {
			return DriverManager.getConnection(CONN, USER,  PASS);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}