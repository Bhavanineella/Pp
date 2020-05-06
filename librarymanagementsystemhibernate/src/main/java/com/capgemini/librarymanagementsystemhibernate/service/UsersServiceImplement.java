package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;
import com.capgemini.librarymanagementsystemhibernate.factory.LibraryFactory;

public class UsersServiceImplement implements UsersService {

	private UserDAO dao = LibraryFactory.getUsersDao();

	@Override
	public boolean register(UserInfo user) {
		return dao.register(user);
	}

	@Override
	public UserInfo login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public boolean addBook(BookInfo book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bookId) {
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		return dao.issueBook(bookId, userId);
	}

	@Override
	public boolean requestBook(int userId, int bookId) {
		return dao.request(userId, bookId);
	}

	@Override
	public List<BorrowedBooksInfo> borrowedBook(int userId) {
		return dao.borrowedBook(userId);
	}

	@Override
	public List<BookInfo> searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		return dao.returnBook(bookId, userId, status);
	}

	@Override
	public List<Integer> bookHistoryDetails(int userId) {
		return dao.bookHistoryDetails(userId);
	}

	@Override
	public List<RequestInfo> showRequests() {
		return dao.showRequests();
	}

	@Override
	public List<BookIssueInfo> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public List<UserInfo> showUsers() {
		return dao.showUsers();
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

}
