package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;

public interface UsersService {
	boolean register(UsersInfo user);
	UsersInfo login(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int bookId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bookId,int userId);
	boolean requestBook(int userId, int bookId);
	List<BorrowedBooks> borrowedBook(int userId);
	LinkedList<BookInfo> searchBookById(int bookId);
	LinkedList<BookInfo> searchBookByTitle(String bookName);
	LinkedList<BookInfo> searchBookByAuthor(String author);
	ArrayList<BookInfo> getBooksInfo();
	LinkedList<BookIssueDetails> bookHistoryDetails(int userId);
	LinkedList<RequestDetails> showRequests();
	LinkedList<BookIssueDetails> showIssuedBooks();
	boolean returnBook(int bookId,int userId,String status);
	LinkedList<UsersInfo> showUsers();
	boolean updatePassword(String email,String password,String newPassword,String role);
}
