package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.factory.Factory;

public class UserServiceImplementation implements UserService{
	
	private UserDAO dao=Factory.getUserDAO();

	@Override
	public UserInfo authUser(String uname, String upassword) {
		
		return dao.authUser(uname, upassword);
	}

	@Override
	public boolean registerUser(UserInfo ui) {
		
		return dao.registerUser(ui);
	}

	@Override
	public boolean requestBook(int bid, String bAuthor) {
		return dao.requestBook(bid, bAuthor);
	}

	@Override
	public BooksInfo borrow(int bookid) {
	
		return dao.borrow(bookid);
	}

	/*
	 * @Override public BooksInfo searchBooks(int bookid) {
	 * 
	 * return dao.searchBooks(bookid); }
	 */

	@Override
	public LinkedList<BooksInfo> searchBookByTitle(String bname) {
		
		return dao.searchBookByTitle(bname);
	}

	@Override
	public LinkedList<BooksInfo> searchBookByAuthor(String bAuthor) {
		
		return dao.searchBookByAuthor(bAuthor);
	}

	@Override
	public LinkedList<BooksInfo> getBooksInfo() {
		
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bid) {
		
		return dao.returnBook(bid);
	}
	

}
