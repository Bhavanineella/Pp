package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;

public interface AdminService {
	
	boolean registerAdmin(AdminInfo admin);
	AdminInfo loginAdmin(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int id);
	boolean updateBook(BookInfo book);
	ArrayList<BookInfo> searchBookByTitle(String bookname);
	ArrayList<BookInfo> searchBookByAuthor(String author);
	ArrayList<BookInfo> searchBookByCategory(String category);
	ArrayList<BookInfo> getBooksInfo();
	List<UserInfo> showUsers();
	List<RequestInfo> showRequests();
	boolean bookIssue(UserInfo user,BookInfo book);
	boolean isBookReceived(UserInfo user,BookInfo book);
}
