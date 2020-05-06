package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;

public class UserServiceImplement implements UserService {
	
	private UserDAO dao=LibraryFactory.getUserDAO();

	@Override
	public boolean registerUser(UserInfo user) {
		return dao.registerUser(user);
	}

	@Override
	public UserInfo loginUser(String email, String password) {
		return dao.loginUser(email, password);
	}

	@Override
	public RequestInfo bookRequest(UserInfo user, BookInfo book) {
		return dao.bookRequest(user, book);
	}

	@Override
	public RequestInfo bookReturn(UserInfo user, BookInfo book) {
		return dao.bookReturn(user, book);
	}

	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BookInfo> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public ArrayList<BookInfo> getBookInfo() {
		return dao.getBooksInfo();
	}
}
