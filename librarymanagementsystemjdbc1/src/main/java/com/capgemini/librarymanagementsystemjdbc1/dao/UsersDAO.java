package com.capgemini.librarymanagementsystemjdbc1.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc1.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc1.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc1.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc1.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc1.dto.UsersInfo;

public interface UsersDAO {
	boolean register(UsersInfo user);
	UsersInfo login(String email,String password);
	boolean addBook(BookInfo book);
	boolean removeBook(int bId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bId,int uId);
	boolean request(int uId, int bId);
	List<BorrowedBooks> borrowedBook(int uId);
	LinkedList<BookInfo> searchBookById(int bId);
	LinkedList<BookInfo> searchBookByTitle(String bookName);
	LinkedList<BookInfo> searchBookByAuthor(String author);
	LinkedList<BookInfo> getBooksInfo();
	boolean returnBook(int bId,int uId,String status);
	LinkedList<BookIssueDetails> bookHistoryDetails(int uId);
	LinkedList<RequestDetails> showRequests();
	LinkedList<BookIssueDetails> showIssuedBooks();
	LinkedList<UsersInfo> showUsers();
	boolean updatePassword(String email,String password,String newPassword,String role);
}
