package com.capgemini.librarymanagementsystem.dao;



import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.db.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.exception.CommonException;

public class AdminDAOImplementation implements AdminDAO{

	@Override
	public AdminInfo auth(String aemail, String apassword) {
		for(AdminInfo ai: LibraryDB.ADMIN) {
		if(ai.getAemail().equals(aemail) && ai.getApassword().equals(apassword)) {
		return ai;	
		}
		}
		throw new CommonException("Invalid Credentials");
	}

	@Override
	public boolean registerAdmin(AdminInfo ai) {
		for(AdminInfo ai1 : LibraryDB.ADMIN) {
		if(ai1.getAname().equals(ai.getAname())) {
		return false;
	}
		}
		LibraryDB.ADMIN.add(ai);
		return true;
	}

	@Override
	public boolean addBook(BooksInfo bi) {
		for(BooksInfo bi1 : LibraryDB.BOOKS) {
			if(bi1.getBid()==bi.getBid()) {
				return false;
			}
		}
		LibraryDB.BOOKS.add(bi);
		return true;
	}

	@Override
	public boolean removeBook(int bid) {
		boolean removeStatus=false;
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			int retrievedId=retrievedBook.getBid();
			if(bid==retrievedId)
			{
				removeStatus=true;
				LibraryDB.BOOKS.remove(i);
				break;
			}
		}
		return removeStatus;
}

	@Override
	public boolean issueBook(int bid) {
		
		boolean bookPresent=LibraryDB.BOOKS.contains(bid);
		UserInfo ui=new UserInfo();
		int booksBorrowed = ui.getNoOfBooks();
		if(bookPresent) {
			if(booksBorrowed<=3) {
				
				for(BooksInfo bi : LibraryDB.BOOKS) {
					if(bi.getBid()==bid) {
						booksBorrowed++;
						ui.setNoOfBooks(booksBorrowed);
					}	
					return true;
			}
				}
		}
		return false;
	}

	/*
	 * @Override public BooksInfo searchBook(int bid) { for(BooksInfo bi :
	 * LibraryDB.BOOKS) { if(bi.getBid()==bid) { return bi; } } throw new
	 * CommonException("Book Not Found"); }
	 */

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
			throw new CommonException("Book not found");
		}
		else
		{
			return searchList;
		}

	}

	@Override
	public LinkedList<BooksInfo> searchBookByAuthor(String bauthor) {
		LinkedList<BooksInfo> searchList=new LinkedList<BooksInfo>();
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			String retrievedBAuthor=retrievedBook.getBauthor();
			if(bauthor.equals(retrievedBAuthor))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new CommonException("Book not found");
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
	public boolean updateBook(int bid) {
		int updateStatus=0;
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BooksInfo retrievedBook=LibraryDB.BOOKS.get(i);
			int retrievedId=retrievedBook.getBid();
			if(bid==retrievedId)
			{
				updateStatus++;
				break;
			}
		}
		throw new CommonException("Book not updated");
	}

}
