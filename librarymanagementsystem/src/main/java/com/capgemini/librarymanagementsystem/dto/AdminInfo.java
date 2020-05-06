package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AdminInfo implements Serializable {
	
	private int id = (int)Math.random();
	private String name;
	private String email;
	private long mobileNo;
	private String password;
	
	public AdminInfo() {
	
	}
	
	public AdminInfo(int id, String name, String email, long mobileNo, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LibrarianInfo [id=" + id + ", name=" + name + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", password=" + password + "]";
	}
	
	
}
