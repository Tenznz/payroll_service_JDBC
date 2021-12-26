package com.bridgelabz.payroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class PayrollServiceDB {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "root";
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("cannot find the driver in the path!", e);
		}
		listDriver();

		try {
			System.out.println("Connecting to database:" + jdbcURL);
			con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection successfull! " + con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "Select * from Employee;";

		try {
			res = stmt.executeQuery(query);

			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
						+ res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void listDriver() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}

	}
}
