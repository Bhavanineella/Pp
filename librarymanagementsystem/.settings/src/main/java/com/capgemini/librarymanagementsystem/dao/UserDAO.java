package com.capgemini.librarymanagementsystem.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;

public interface UserDAO {

	UserInfo authUser(String uname,String upassword);
	boolean registerUser(UserInfo ui);
	boolean requestBook(int bid, String bAuthor);
	BooksInfo borrow(int bookid);
	
	LinkedList<BooksInfo> searchBookByTitle(String bname);
	LinkedList<BooksInfo> searchBookByAuthor(String bAuthor);
	LinkedList<BooksInfo> getBooksInfo();
	boolean returnBook(int bid);
	
}
