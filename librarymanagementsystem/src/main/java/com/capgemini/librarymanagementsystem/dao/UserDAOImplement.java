package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.db.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class UserDAOImplement implements UserDAO {

	@Override
	public boolean registerUser(UserInfo user) {
		for (UserInfo u : LibraryDB.USER) {
			if ((u.getEmail()).equals(user.getEmail())) {
				return false;
			}
		}
		LibraryDB.USER.add(user);
		return true;
	}
	
	@Override
	public UserInfo loginUser(String email, String password) {
		for (UserInfo student : LibraryDB.USER) {
			if ((student.getEmail().equals(email)) && (student.getPassword().equals(password))) {
				return student;
			}
		}
		throw new LMSException("Invalid Credentials");
	}
	
	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {
		ArrayList<BookInfo> searchList = new ArrayList<BookInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BookInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBname = retrievedBook.getBookName();
			if (bookName.equals(retrievedBname)) {
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException ("Book is not found");
		} else {
			return searchList;
		}
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {
		ArrayList<BookInfo> searchList = new ArrayList<BookInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BookInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBAuthor = retrievedBook.getAuthor();
			if (author.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException ("Book is not found");
		} else {
			return searchList;
		}	
	}
	
	@Override
	public ArrayList<BookInfo> searchBookByCategory(String category) {
		ArrayList<BookInfo> searchList = new ArrayList<BookInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BookInfo retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBookType=retrievedBook.getCategory();
			if (category.equals(retrievedBookType)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}	
	}
	
	@Override
	public ArrayList<BookInfo> getBooksInfo() {
			return LibraryDB.BOOKS;
	}

	@Override
	public RequestInfo bookRequest(UserInfo user, BookInfo book) {
		boolean flag = false, 
		isRequestExists = false;
		RequestInfo requestInfo = new RequestInfo();
		UserInfo StudentInfo2 = new UserInfo();
		BookInfo bookInfo2 = new BookInfo();
		for (RequestInfo requestInfo2 : LibraryDB.REQUEST) {
			if (book.getBookId() == requestInfo2.getBookInfo().getBookId()) {
				isRequestExists = true;
			}
		}
		if (!isRequestExists) {
			for (UserInfo studentBean : LibraryDB.USER) {
				if (user.getId() == studentBean.getId()) {
					for (BookInfo book1 : LibraryDB.BOOKS) {
						if (book1.getBookId() == book1.getBookId()) {
							StudentInfo2 = studentBean;
							bookInfo2 = book1;
							flag = true;
						}
					}
				}
			}
			if (flag == true) {
				requestInfo.setBookInfo(bookInfo2);
				requestInfo.setUserInfo(StudentInfo2);;
				LibraryDB.REQUEST.add(requestInfo);
				return requestInfo;
			}
		}
		throw new LMSException("Invalid request or you cannot request that book");
	}
	
	@Override
	public RequestInfo bookReturn(UserInfo student, BookInfo book) {
		for (RequestInfo requestInfo : LibraryDB.REQUEST) {
			if (requestInfo.getBookInfo().getBookId() == book.getBookId() &&
					requestInfo.getUserInfo().getId() == student.getId() &&
					requestInfo.isIssued() == true) {
				System.out.println("Returning Issued book only");
				requestInfo.setReturned(true);
				return requestInfo;
			}
		}
		throw new  LMSException("Invalid return ");
	}
}
	


