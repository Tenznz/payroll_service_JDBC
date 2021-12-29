package com.bridgelabz.payroll;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PayrollOperationTest {
	List<EmployeePayrollData> empList = new ArrayList<>();

	void init() {

	}

	@Test
	public void from_employee_table_sync_with_database_get_row_count() {
		EmployeePayrollConnection connection = EmployeePayrollConnection.getInstance();
		PayrollOperation payrollOperation = new PayrollOperation();
		connection.getConnection();
		try {
			empList = payrollOperation.display();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(8, empList.size());
	}

	@Test
	public void given_employee_ID_and_Salary_should_return_row_effected_count() {
		EmployeePayrollConnection connection = EmployeePayrollConnection.getInstance();
		PayrollOperation payrollOperation = new PayrollOperation();
		connection.getConnection();
		int actual = 0;
		try {
			actual = payrollOperation.update();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1, actual);
	}

	@Test
	public void given_records_should_return_row_effected_count() {
		EmployeePayrollConnection connection = EmployeePayrollConnection.getInstance();
		PayrollOperation payrollOperation = new PayrollOperation();
		connection.getConnection();
		int actual = 0;
		try {
			actual = payrollOperation.insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1, actual);
	}

}
