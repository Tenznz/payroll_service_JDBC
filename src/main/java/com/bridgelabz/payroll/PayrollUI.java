package com.bridgelabz.payroll;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PayrollUI {

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
		String updateQuery = "update employee set salary=3000000.00 where emp_id='emp1'";

		Statement stmt = null;
		int res = 0;
		try {
			stmt = PayrollServiceDB.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		res = stmt.executeUpdate(updateQuery);
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
					+ res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7));
		}
	}
}
