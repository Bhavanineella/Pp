package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryFactory;

public class UsersServiceImplementation implements UsersService{

	private UsersDAO dao = LibraryFactory.getUsersDao();
	@Override
	public boolean register(UsersInfo user) {
		return dao.register(user);
	}

	@Override
	public UsersInfo login(String email, String password) {
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
	public boolean issueBook(int bookId,int userId) {
		return dao.issueBook(bookId,userId);
	}

	@Override
	public boolean requestBook(int userId, int bookId) {
		return dao.requestBook(userId, bookId);
	}

	@Override
	public LinkedList<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bookId,int userId,String status) {
		return dao.returnBook(bookId,userId,status);
	}

	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int userId) {
		return dao.bookHistoryDetails(userId);
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int userId) {
		return dao.borrowedBook(userId);
	}

	@Override
	public LinkedList<BookInfo> searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}

	@Override
	public LinkedList<RequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public LinkedList<BookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public LinkedList<UsersInfo> showUsers() {
		return dao.showUsers();
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

}
