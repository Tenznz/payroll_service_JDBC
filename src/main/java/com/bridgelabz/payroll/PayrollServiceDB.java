package com.bridgelabz.payroll;

import java.awt.DisplayMode;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;

public class PayrollServiceDB {

	Connection con = null;

	public static void main(String[] args) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "root";

		PayrollServiceDB payrollDB = new PayrollServiceDB();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("cannot find the driver in the path!", e);
		}
		payrollDB.listDriver();

		try {
			System.out.println("Connecting to database:" + jdbcURL);
			payrollDB.con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection successfull! " + payrollDB.con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		payrollDB.OperateDB();

	}

	private void OperateDB() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 to continue");
		int exit = 1;

		while (exit == 1) {
			int input = sc.nextInt();
			switch (input) {
			case 1:
				display();
				break;
			case 2:
				update();
				break;
			}
		}

	}

	private void update() throws SQLException {
		String updateQuery = "update employee set salary=3000000.00 where emp_id='emp1'";

		Statement stmt = null;
		int res = 0;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		res = stmt.executeUpdate(updateQuery);
		if (res > 0) {
			System.out.println("Data got inseted");
		}

	}

	private void display() throws SQLException {
		String query = "Select * from Employee;";
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		res = stmt.executeQuery(query);

		while (res.next()) {
			System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
					+ res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7));
		}
	}

	private void listDriver() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}

	}

}
