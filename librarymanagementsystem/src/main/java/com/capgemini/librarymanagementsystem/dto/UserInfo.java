package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserInfo implements Serializable {
	
	private int id = (int)Math.random();
	private String name;
	private String password;
    private String department;
	private String email;
	private long mobileNo;
	private Date bookIssueDate;
	private Date bookReturnDate;
	private int booksBorrowed;
	
	public UserInfo() {
		
	}
	
	public UserInfo(int id, String name, String password, String department, String email, long mobileNo,
			Date bookIssueDate, Date bookReturnDate, int booksBorrowed) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.department = department;
		this.email = email;
		this.mobileNo = mobileNo;
		this.bookIssueDate = bookIssueDate;
		this.bookReturnDate = bookReturnDate;
		this.booksBorrowed = booksBorrowed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Date getBookIssueDate() {
		return bookIssueDate;
	}
	public void setBookIssueDate(Date bookIssueDate) {
		this.bookIssueDate = bookIssueDate;
	}
	public Date getBookReturnDate() {
		return bookReturnDate;
	}
	public void setBookReturnDate(Date bookReturnDate) {
		this.bookReturnDate = bookReturnDate;
	}
	public int getBooksBorrowed() {
		return booksBorrowed;
	}
	public void setBooksBorrowed(int booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}

	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", name=" + name + ", password=" + password + ", department=" + department
				+ ", email=" + email + ", mobileNo=" + mobileNo + ", bookIssueDate=" + bookIssueDate
				+ ", bookReturnDate=" + bookReturnDate + ", booksBorrowed=" + booksBorrowed + "]";
	}
	
	
}
