package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;



public class UserInfo implements Serializable {

	private int uid;
	private String uname;
	private String upassword;
    private String udepartment;
	private String uemail;
	private long umobileNo;
	private int noOfBooks =0;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUdepartment() {
		return udepartment;
	}
	public void setUdepartment(String udepartment) {
		this.udepartment = udepartment;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public long getUmobileNo() {
		return umobileNo;
	}
	public void setUmobileNo(long umobileNo) {
		this.umobileNo = umobileNo;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	
	}
