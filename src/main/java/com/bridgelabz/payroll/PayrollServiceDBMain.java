package com.bridgelabz.payroll;

import java.sql.SQLException;
import java.util.Scanner;

public class PayrollServiceDBMain {

	public static void main(String[] args) throws SQLException {
		PayrollServiceDBMain payrollDB = new PayrollServiceDBMain();
		EmployeePayrollConnection connection=new EmployeePayrollConnection();
		connection.getConnection();
		payrollDB.OperateDB(); 

	}

	private void OperateDB() throws SQLException {
		Scanner sc = new Scanner(System.in);
		int exit = 1;
		PayrollOperation payrollUI = new PayrollOperation();
		do {
			System.out.println(
					"Database operation \n1.display\n2.insert\n3.update\n4.search\n5.salary range\n6.total salary\n7.group by gender");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				payrollUI.display();
				break;
			case 2:
				payrollUI.insert();
				break;
			case 3:
				payrollUI.update();
				break;
			case 4:
				payrollUI.search();
				break;
			case 5:
				payrollUI.searchRange();
				break;
			case 6:
				payrollUI.totalSalary();
				break;
			case 7:
				payrollUI.groupBy();
				break;

			default:
				System.out.println("Wrong input");
			}
			System.out.println("Enter 1 to continue");
			exit = sc.nextInt();

		} while (exit == 1);
		sc.close();
	}
}
