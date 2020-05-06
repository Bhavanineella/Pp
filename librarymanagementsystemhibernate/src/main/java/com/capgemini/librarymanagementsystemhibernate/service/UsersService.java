package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;

public interface UsersService {
	boolean register(UserInfo user);

	UserInfo login(String email, String password);

	boolean addBook(BookInfo book);

	boolean removeBook(int bookId);

	boolean updateBook(BookInfo book);

	boolean issueBook(int bookId, int userId);

	boolean requestBook(int userId, int bookId);

	List<BorrowedBooksInfo> borrowedBook(int userId);

	List<BookInfo> searchBookById(int bookId);

	List<BookInfo> searchBookByTitle(String bookName);

	List<BookInfo> searchBookByAuthor(String author);

	List<BookInfo> getBooksInfo();

	boolean returnBook(int bookId, int userId, String status);

	List<Integer> bookHistoryDetails(int userId);

	List<RequestInfo> showRequests();

	List<BookIssueInfo> showIssuedBooks();

	List<UserInfo> showUsers();

	boolean updatePassword(String email, String password, String newPassword, String role);

}
