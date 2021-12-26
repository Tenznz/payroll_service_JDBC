package com.bridgelabz.payroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class PayrollServiceDB {

	public static Connection con = null;

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
			PayrollServiceDB.con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection successfull! " + PayrollServiceDB.con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		payrollDB.OperateDB();

	}

	private void OperateDB() throws SQLException {
		Scanner sc = new Scanner(System.in);
		int exit = 1;
		PayrollUI payrollUI = new PayrollUI();
		do {
			System.out.println("Database operation \n1.display\n2.insert\n3.update");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				payrollUI.display();
				break;
			case 2:
				payrollUI.input();
				break;
			case 3:
				payrollUI.update();
				break;
			default:
				System.out.println("Wrong input");
			}
			System.out.println("Enter 1 to continue");
			exit = sc.nextInt();

		} while (exit == 1);
		sc.close();

	}

	private void listDriver() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}
