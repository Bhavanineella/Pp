package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;

public interface UserDAO {
	
	boolean registerUser(UserInfo user);
	UserInfo loginUser(String email,String password);
	public RequestInfo bookRequest(UserInfo user, BookInfo book);
	public RequestInfo bookReturn(UserInfo user, BookInfo book);
	ArrayList<BookInfo> searchBookByTitle(String bookName);
	ArrayList<BookInfo> searchBookByAuthor(String author);
	ArrayList<BookInfo> searchBookByCategory(String category);
	ArrayList<BookInfo> getBooksInfo();
}
