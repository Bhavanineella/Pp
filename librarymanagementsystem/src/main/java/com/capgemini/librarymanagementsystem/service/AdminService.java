package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.Book;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.User;

public interface AdminService {
	boolean registerAdmin(Admin admin);
	Admin loginAdmin(String email,String password);
	boolean addBook(Book book);
	boolean removeBook(int id);
	boolean updateBook(Book book);
	LinkedList<Book> searchBookByTitle(String bookName);
	LinkedList<Book> searchBookByAuthor(String author);
	LinkedList<Book> searchBookByCategory(String category);
	LinkedList<Book> getBooksInfo();

	List<User> showUsers();
	List<RequestBean> showRequests();
	boolean bookIssue(User user,Book book);
	boolean isBookReceived(User user,Book book);
}