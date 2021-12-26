package com.bridgelabz.payroll;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PayrollOperation {
	Scanner sc = new Scanner(System.in);

	public void input() throws SQLException {
		String insertQuery = "insert into employee values(?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = null;
		int res = 0;
		pstmt = PayrollServiceDB.con.prepareStatement(insertQuery);
		pstmt.setString(1, "emp7");
		pstmt.setString(2, "sham");
		pstmt.setString(3, "4564564655");
		pstmt.setString(4, "M");
		pstmt.setString(5, "2002-12-01");
		pstmt.setInt(6, 1);
		pstmt.setString(7, "ECE");
		pstmt.setDouble(8, 100000.00);

		res = pstmt.executeUpdate();
		if (res > 0) {
			System.out.println("Data got inserted");
		}
	}

	public void update() throws SQLException {
		String updateQuery = "update employee set salary=? where emp_id=?";
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			pstmt = PayrollServiceDB.con.prepareStatement(updateQuery);
			System.out.println("Enter emp_id");
			String emp_ID = sc.next();
			pstmt.setString(2, emp_ID);
			System.out.println("Update salary");
			double salary = sc.nextDouble();
			pstmt.setDouble(1, salary);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		res = pstmt.executeUpdate();
		if (res > 0) {
			System.out.println("Data got updated");
		}

	}

	public void display() throws SQLException {
		String query = "Select * from Employee;";
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = PayrollServiceDB.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		res = stmt.executeQuery(query);

		while (res.next()) {
			System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
					+ res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " "
					+ res.getDouble(8));
		}
	}

	public void search() throws SQLException {

		String printQuery = "select * from employee where name=?;";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		pstmt = PayrollServiceDB.con.prepareStatement(printQuery);
		System.out.println("Enter name you want to search from table");
		String data = sc.next();
		pstmt.setString(1, data);
		res = pstmt.executeQuery();
		String executedQuery = res.getStatement().toString();
		System.out.println(executedQuery);
		while (res.next()) {
			System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
					+ res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " "
					+ res.getDouble(8));
		}
	}

	public void searchRange() throws SQLException {
		String printQuery = "SELECT * FROM Employee WHERE salary BETWEEN ? AND ?;";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		pstmt = PayrollServiceDB.con.prepareStatement(printQuery);
		System.out.print("salary range from :");
		double salary1 = sc.nextDouble();
		pstmt.setDouble(1, salary1);
		System.out.print(" to ");
		double salary2 = sc.nextDouble();
		pstmt.setDouble(2, salary2);
		res = pstmt.executeQuery();
		String executedQuery = res.getStatement().toString();
		System.out.println(executedQuery);
		while (res.next()) {
			System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " "
					+ res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " "
					+ res.getDouble(8));
		}
	}

	public void totalSalary() throws SQLException {
		System.out.println("1.sum\n2.avg\n3.count\n4.min\n5.max");
		;
		String query = "";
		int input = sc.nextInt();
		switch (input) {
		case 1:
			query = "SELECT sum(salary)";
			break;
		case 2:
			query = "SELECT avg(salary)";
			break;
		case 3:
			query = "SELECT count(*)";
			break;
		case 4:
			query = "SELECT min(salary)";
			break;
		case 5:
			query = "SELECT max(salary)";
			break;

		}
		String mysqlQuery = query + " FROM Employee;";
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = PayrollServiceDB.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		res = stmt.executeQuery(mysqlQuery);
		String executedQuery = res.getStatement().toString();
		System.out.println(executedQuery);
		while (res.next()) {
			System.out.println(res.getDouble(1));
		}
	}

	public void groupBy() throws SQLException {
		String printQuery = "SELECT gender,count(*) FROM Employee group by gender;";
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = PayrollServiceDB.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		res = stmt.executeQuery(printQuery);

		while (res.next()) {
			System.out.println(res.getString(1) + " " + res.getString(2));
		}
	}
}
