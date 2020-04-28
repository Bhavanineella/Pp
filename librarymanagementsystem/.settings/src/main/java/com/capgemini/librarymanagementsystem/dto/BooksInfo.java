package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

public class BooksInfo {
	private int bid;
	private String bname;
	private String bauthor;
	private String bcategory;
	private String publishername;
	private String bookIssuedate;
	private String bookReturndate;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	
	public String getBcategory() {
		return bcategory;
	}
	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}
	public String getPublishername() {
		return publishername;
	}
	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}
	public String getBookIssuedate() {
		return bookIssuedate;
	}
	public void setBookIssuedate(String bookIssuedate) {
		this.bookIssuedate = bookIssuedate;
	}
	public String getBookReturndate() {
		return bookReturndate;
	}
	public void setBookReturndate(String bookReturndate) {
		this.bookReturndate = bookReturndate;
	}
	
}