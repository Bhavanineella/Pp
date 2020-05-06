package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "borrowed_books")
@SequenceGenerator(name = "seq2", initialValue = 1, allocationSize = 100)
public class BorrowedBooksInfo implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
	private int id;
	@Column
	private int userId;
	@Column
	private int bookId;
	@Column
	private String bookName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

}
