package com.capgemini.librarymanagementsystemjdbc.dto;

public class BookInfo {
	private int bookId;
	private String bookName;
	private String author;
	private String category;
	private String publisher;

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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/*
	 * public int getCopies() { return copies; } public void setCopies(int copies) {
	 * this.copies = copies; }
	 */
}
