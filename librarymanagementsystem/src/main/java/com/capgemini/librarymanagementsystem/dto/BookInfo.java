package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.lang.Math;

@SuppressWarnings("serial")
public class BookInfo implements Serializable {

	private int bookId = (int)Math.random();
	private String bookName;
	private String author;
	private String category;
	private String publishername;

	public BookInfo() {
		
	}
	
	public BookInfo(int bookId, String bookName, String author, String category, String publishername) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.category = category;
		this.publishername = publishername;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	@Override
	public String toString() {
		return String.format("%-10s %-10s %-13s %-15s %s", bookId, bookName, author, category, publishername);
	}

}