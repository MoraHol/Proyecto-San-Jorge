/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjorge.dao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class ConnectionSQL {
    private Connection jdbcConnection;
	private String jdbcURL;
	private String jdbcDataBase;
	private String jdbcUsername;
	private String jdbcPassword;

	public ConnectionSQL() {
		this.jdbcURL = "jdbc:mysql://localhost:3306/";
		this.jdbcDataBase = "SanJorge";
		this.jdbcUsername = "root";
		this.jdbcPassword = "";
	}

	public void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = (Connection) DriverManager.getConnection(jdbcURL + jdbcDataBase, jdbcUsername, jdbcPassword);
		}
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public Connection getJdbcConnection() {
		return jdbcConnection;
	}

}

