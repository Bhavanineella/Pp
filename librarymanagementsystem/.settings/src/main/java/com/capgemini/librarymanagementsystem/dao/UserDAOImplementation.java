package com.capgemini.librarymanagementsystem.dao;




import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.db.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.exception.CommonException;

public class UserDAOImplementation implements UserDAO{

	@Override
	public UserInfo authUser(String uname, String upassword) {
		for(UserInfo ui : LibraryDB.USER) {
			if(ui.getUname().equals(uname) && ui.getUpassword().equals(upassword));
			return ui;
		}
		throw new CommonException("Invalid Credentials");
		
	}

	@Override
	public boolean registerUser(UserInfo ui) {
		for(UserInfo ui1 : LibraryDB.USER) {
			if(ui1.getUname().equals(ui.getUname()));
			return false;
		}
		LibraryDB.USER.add(ui);
		return true;
	}

	/*
	 * @Override public BooksInfo searchBooks(int bookid) { for(BooksInfo bi :
	 * LibraryDB.BOOKS) { if(bi.getBid()==bookid) { return bi; } } throw new
	 * CommonException("Invalid Credentials");
	 * 
	 * }
	 */

	@Override
	public boolean requestBook(int bid, String bAuthor) {
		for (BooksInfo bookbean : LibraryDB.BOOKS) {
			if (bookbean.getBid() == bid && bookbean.getBauthor() == bAuthor) {

				return true;
			} else {
				return false;
			}

		}
		return true;
	}
	@Override
	public BooksInfo borrow(int bookid) {
		UserInfo ui=new UserInfo();
		int count=ui.getNoOfBooks();
		BooksInfo bi=new BooksInfo();
		if(bi.getBid()==bookid) {
			return bi;
		}
		count++;
		throw new CommonException("Invlaid id");
		
	}

	@Override
	public LinkedList<BooksInfo> searchBookByTitle(String bname) {
		LinkedList<BooksInfo> searchList=new LinkedList<BooksInfo>();
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBname=retrievedBook.getBname();
			if(bname.equals(retrievedBname))
			{
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if(searchList.size()==0)
		{
			throw new CommonException ("Book is not found");
		}
		else
		{
			return searchList;
		}

	}

	@Override
	public LinkedList<BooksInfo> searchBookByAuthor(String bAuthor) {
		LinkedList<BooksInfo> searchList=new LinkedList<BooksInfo>();
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBAuthor=retrievedBook.getBauthor();
			if(bAuthor.equals(retrievedBAuthor))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new CommonException ("Book is not found");
		}
		else
		{
			return searchList;
		}	
	}

	@Override
	public LinkedList<BooksInfo> getBooksInfo() {
		
		return LibraryDB.BOOKS;
	}

	@Override
	public boolean returnBook(int bid) {
		for (BooksInfo bookbean : LibraryDB.BOOKS) {
			if (bookbean.getBid() == bid ) {
				LibraryDB.BOOKS.add(bid, bookbean);
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	}
