package com.capgemini.librarymanagementsystemjdbc1.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc1.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc1.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc1.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc1.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc1.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc1.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc1.factory.LibraryFactory;

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
	public boolean removeBook(int bId) {
		return dao.removeBook(bId);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bId,int uId) {
		return dao.issueBook(bId,uId);
	}

	@Override
	public boolean request(int uId, int bId) {
		return dao.request(uId, bId);
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
	public LinkedList<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bId,int uId,String status) {
		return dao.returnBook(bId,uId,status);
	}

	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int uId) {
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int uId) {
		return dao.borrowedBook(uId);
	}

	@Override
	public LinkedList<BookInfo> searchBookById(int bId) {
		return dao.searchBookById(bId);
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
