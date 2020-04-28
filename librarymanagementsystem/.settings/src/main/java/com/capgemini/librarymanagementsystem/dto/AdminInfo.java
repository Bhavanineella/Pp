package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

public class AdminInfo implements Serializable{
	private int aid;
	private String aname;
	private String aemail;
	private long amobileNo;
	private String apassword;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAemail() {
		return aemail;
	}
	public void setAemail(String aemail) {
		this.aemail = aemail;
	}
	public long getAmobileNo() {
		return amobileNo;
	}
	public void setAmobileNo(long amobileNo) {
		this.amobileNo = amobileNo;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}

}
