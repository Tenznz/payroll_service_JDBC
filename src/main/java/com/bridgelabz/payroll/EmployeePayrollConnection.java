package com.bridgelabz.payroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class EmployeePayrollConnection {
	public static EmployeePayrollConnection instance;

	private EmployeePayrollConnection() {

	}

	public static EmployeePayrollConnection getInstance() {
		if (instance == null) {
			instance = new EmployeePayrollConnection();
		}
		return instance;

	}

	public static Connection con = null;

	public void getConnection() {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "root";

		// load class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("cannot find the driver in the path!", e);
		}
		listDriver();
		// connection
		try {
			System.out.println("Connecting to database:" + jdbcURL);
			EmployeePayrollConnection.con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection successfull! " + EmployeePayrollConnection.con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listDriver() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}

	public void closeHeavyClass() throws SQLException {
		con.close();
	}

	public void setAutoCommit(boolean falseCommit) throws SQLException {
		con.setAutoCommit(false);

	}

}
