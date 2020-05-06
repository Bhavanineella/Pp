package com.capgemini.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;

public class AdminServiceImplementation implements AdminService {
 
	private AdminDAO dao=LibraryFactory.getAdminDAO();

	@Override
	public boolean registerAdmin(AdminInfo admin) {
		return dao.registerAdmin(admin);
	}

	@Override
	public AdminInfo loginAdmin(String email, String password) {
		return dao.loginAdmin(email, password);
	}

	@Override
	public boolean addBook(BookInfo book) {
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int id) {
		return dao.removeBook(id);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookname) {
		return dao.searchBookByTitle(bookname);
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BookInfo> searchBookByCategory(String category) {
		return dao.searchBookByCategory(category);
	}

	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public List<UserInfo> showUsers() {
		return dao.showUsers();
	}

	@Override
	public List<RequestInfo> showRequests() {
		return dao.showRequests();
	}

	@Override
	public boolean bookIssue(UserInfo user, BookInfo book) {
		return dao.bookIssue(user, book);
	}

	@Override
	public boolean isBookReceived(UserInfo user, BookInfo book) {
		return dao.isBookReceived(user, book);
	}  	
}
