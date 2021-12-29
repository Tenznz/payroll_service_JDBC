package com.bridgelabz.payroll;

import java.sql.Date;

public class EmployeePayrollData {
	String id;
	String name;
	String phoneNumber;
	String gender;
	Date start;
	int companyID;
	String deptID;
	double salary;

	@Override
	public String toString() {
		return "EmployeePayrollData [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", gender="
				+ gender + ", start=" + start + ", companyID=" + companyID + ", deptID=" + deptID + ", salary=" + salary
				+ "]";
	}

	public EmployeePayrollData(String id, String name, String phoneNumber, String gender, Date start, int companyID,
			String deptID, double salary) {
		super();
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.gender = gender;
		this.start = start;
		this.phoneNumber = phoneNumber;
		this.companyID = companyID;
		this.deptID = deptID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

}
