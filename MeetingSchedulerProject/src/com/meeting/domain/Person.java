package com.meeting.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	private String name;
	private int employeeId;
	private String designation;
	private String emailId; //unique

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + "\nEmployee ID: " + this.employeeId
				+ " \nDesignation: " + this.designation + "\nEmailID: "
				+ this.emailId;
	}

}
