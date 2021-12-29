package com.bridgelabz.payroll;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PayrollOperation {
	Scanner sc = new Scanner(System.in);
	private int rowCount;
	List<EmployeePayrollData> emp = new ArrayList<>();

	public int insert() throws SQLException {
		EmployeePayrollConnection.con.setAutoCommit(false);;
		String insertQuery = "insert into employee values(?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = null;
		int res = 0;
		pstmt = EmployeePayrollConnection.con.prepareStatement(insertQuery);
		System.out.print("Enter empID: ");
		String id = sc.next();
		System.out.print("Employee Name:");
		String name = sc.next();
		System.out.print("Employee phoneNumber: ");
		String number = sc.next();
		System.out.print("Gender M/F: ");
		String gender = sc.next();
		System.out.print("Enter Date in yyyy-mm-dd: ");
		String date = sc.next();
		System.out.print("Company ID");
		int company_id = sc.nextInt();
		System.out.print("Department ID: ");
		String dept_id = sc.next();
		System.out.println("Salary");
		double salary = sc.nextDouble();
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, number);
		pstmt.setString(4, gender);
		pstmt.setString(5, date);
		pstmt.setInt(6, company_id);
		pstmt.setString(7, dept_id);
		pstmt.setDouble(8, salary);
		res = pstmt.executeUpdate();
		insertPayrollTable(id, salary);
		try {
			EmployeePayrollConnection.con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res > 0) {
			System.out.println("Data got inserted");
		}
		return res;
	}

	@SuppressWarnings("static-access")
	void insertPayrollTable(String id, double basicPay) throws SQLException {
		try (Statement pstmt = EmployeePayrollConnection.con.createStatement()) {
			double deductions = basicPay * 0.2;
			double taxablePay = basicPay - deductions;
			double tax = taxablePay * 0.1;
			double netPay = basicPay - tax;
			String insertQuery = String.format("insert into payroll values('%s',%s,%s,%s,%s,%s);", id, basicPay,
					deductions, taxablePay, tax, netPay);
			int rowAffected = pstmt.executeUpdate(insertQuery);
			if (rowAffected == 1) {
				System.out.println(insertQuery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insertion got rollback");
			EmployeePayrollConnection.con.rollback();
		}

		PayrollServiceDBMain payrollDB = new PayrollServiceDBMain();
		payrollDB.OperateDB();

	}

	public int update() throws SQLException {
		String updateQuery = "update employee set salary=? where emp_id=?";
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			pstmt = EmployeePayrollConnection.con.prepareStatement(updateQuery);
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
		return res;

	}

	public List<EmployeePayrollData> display() throws SQLException {
		String query = "Select * from Employee;";
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = EmployeePayrollConnection.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		res = stmt.executeQuery(query);
		return getEmployeeTable(res);
	}

	public void search() throws SQLException {

		String printQuery = "select * from employee where name=?;";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		pstmt = EmployeePayrollConnection.con.prepareStatement(printQuery);
		System.out.println("Enter name you want to search from table");
		String data = sc.next();
		pstmt.setString(1, data);
		res = pstmt.executeQuery();
		String executedQuery = res.getStatement().toString();
		System.out.println(executedQuery);
		getEmployeeTable(res);
	}

	public void searchRange() throws SQLException {
		String printQuery = "SELECT * FROM Employee WHERE salary BETWEEN ? AND ?;";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		pstmt = EmployeePayrollConnection.con.prepareStatement(printQuery);
		System.out.print("salary range from :");
		double salary1 = sc.nextDouble();
		pstmt.setDouble(1, salary1);
		System.out.print(" to ");
		double salary2 = sc.nextDouble();
		pstmt.setDouble(2, salary2);
		res = pstmt.executeQuery();
		String executedQuery = res.getStatement().toString();
		System.out.println(executedQuery);
		getEmployeeTable(res);
	}

	private List<EmployeePayrollData> getEmployeeTable(ResultSet res) {
		List<EmployeePayrollData> empList = new ArrayList<>();
		try {
			while (res.next()) {
				rowCount++;
				String empID = res.getString(1);
				String name = res.getString(2);
				String phoneNumber = res.getString(3);
				String gender = res.getString(4);
				Date start = res.getDate(5);
				int companyID = res.getInt(6);
				String dept_ID = res.getString(7);
				double salary = res.getDouble(8);
				empList.add(
						new EmployeePayrollData(empID, name, phoneNumber, gender, start, companyID, dept_ID, salary));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		empList.forEach(System.out::println);
		return empList;
	}

	public int getRowCount() {
		return rowCount;
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
			stmt = EmployeePayrollConnection.con.createStatement();
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
			stmt = EmployeePayrollConnection.con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		res = stmt.executeQuery(printQuery);

		while (res.next()) {
			System.out.println(res.getString(1) + " " + res.getString(2));
		}
	}
}
