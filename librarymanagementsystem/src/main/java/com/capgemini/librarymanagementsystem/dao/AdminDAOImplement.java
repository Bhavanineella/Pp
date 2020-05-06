package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.db.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.exception.LMSException;

public class AdminDAOImplement implements AdminDAO {
	
	@Override
	public boolean registerAdmin(AdminInfo admin) {
		for (AdminInfo ai1 : LibraryDB.ADMIN) {
		     if ((ai1.getEmail()) .equals(admin.getEmail())) {
		         return false;
	          }
		}
		LibraryDB.ADMIN.add(admin);
		return true;
	} 
	
	@Override
	public AdminInfo loginAdmin(String email, String password) {
		for (AdminInfo admin : LibraryDB.ADMIN) {
			if ((admin.getEmail().equals(email)) && (admin.getPassword().equals(password))) {
				return admin;
			}
		}
		throw new LMSException("Invalid Credentials");
	}
	
	@Override
	public boolean addBook(BookInfo book) {
		for (BookInfo b : LibraryDB.BOOKS) {
			if (b.getBookId() == book.getBookId()) {
				return false;
			}
		}
		LibraryDB.BOOKS.add(book);
		return true;
	}
	
	@Override
	public boolean removeBook(int id) {
		boolean removeStatus=false;
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
		    BookInfo retrievedBook = LibraryDB.BOOKS.get(i);
			int retrievedId = retrievedBook.getBookId();
			if (id == retrievedId) {
				removeStatus = true;
				LibraryDB.BOOKS.remove(i);
				break;
			}
		}
		return removeStatus;
    }
	
	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {
		ArrayList<BookInfo> searchList = new ArrayList<BookInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BookInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBname = retrievedBook.getBookName();
			if (bookName.equals(retrievedBname)) {
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
		    return searchList;
		}
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {
		ArrayList<BookInfo> searchList = new ArrayList<BookInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BookInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBAuthor = retrievedBook.getAuthor();
			if (author.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
				throw new LMSException("Book not found");
		} else {
			return searchList;
		}		
	}
	
	@Override
	public ArrayList<BookInfo> searchBookByCategory(String category) {
		ArrayList<BookInfo> searchList = new ArrayList<BookInfo>();
		for (int i = 0; i <= LibraryDB.BOOKS.size()-1; i++) {
			BookInfo retrievedBook = LibraryDB.BOOKS.get(i);
			String retrievedBookType = retrievedBook.getCategory();
			if(category.equals(retrievedBookType))
			{
				searchList.add(retrievedBook);	
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}
     }

	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		return LibraryDB.BOOKS;
	}

	@SuppressWarnings("unused")
	@Override
    public boolean updateBook(BookInfo book) {
		
		for(int i=0;i<=LibraryDB.BOOKS.size()-1;i++)
		{
			BookInfo retrievedBook=LibraryDB.BOOKS.get(i);
			if (retrievedBook.getBookId() == book.getBookId()) {
				retrievedBook.setBookName(book.getBookName());
				retrievedBook.setAuthor(book.getAuthor());
				retrievedBook.setCategory(book.getCategory());
				return true;
			}
			else {
				throw new LMSException("Invalid Book");
			}
		} 
		throw new LMSException("Book not updated");
	}
	
	@Override
	public List<UserInfo> showUsers() {
		List<UserInfo> studentsList = new ArrayList<UserInfo>();
		for (UserInfo studentBean : LibraryDB.USER) {
			studentBean.getId();
			studentBean.getName();
			studentBean.getEmail();
			studentBean.getBooksBorrowed();
			studentsList.add(studentBean);
		}
		return studentsList;
	}
	
	@Override
	public List<RequestInfo> showRequests() {
		List<RequestInfo> info = new ArrayList<RequestInfo>();
		for (RequestInfo requestInfo : LibraryDB.REQUEST) {
			requestInfo.getBookInfo();
			requestInfo.getUserInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			info.add(requestInfo);
		}
		return info;
	}
	
	@Override
	public boolean bookIssue(UserInfo user, BookInfo book) {
		boolean isValid = false;
		RequestInfo requestInfo = new RequestInfo();
		int noOfBooksBorrowed = user.getBooksBorrowed();
		for (RequestInfo info : LibraryDB.REQUEST) {
			if (info.getUserInfo().getId() == user.getId()) {
				if (info.getBookInfo().getBookId() == book.getBookId()) {
					requestInfo = info;
					isValid = true;
				}
			}
		}
		if (isValid) {
			for (BookInfo info2 : LibraryDB.BOOKS) {
				if (info2.getBookId() == book.getBookId()) {
					book = info2;
				}
			}
			for (UserInfo StudentInfo : LibraryDB.USER) {
				if (StudentInfo.getId() == user.getId()) {
					user = StudentInfo;
					noOfBooksBorrowed = user.getBooksBorrowed();
				}
			}
			if (noOfBooksBorrowed < 3) {
				boolean isRemoved = LibraryDB.BOOKS.remove(book);
				if (isRemoved) {
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					user.setBooksBorrowed(noOfBooksBorrowed);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new LMSException("Book can't be borrowed");
				}
			} else {
				throw new LMSException("Student Exceeds maximum limit");
			}
		} else {
			throw new LMSException("Book data or Student data is incorrect");
		}
	}
	
	@Override
	public boolean isBookReceived(UserInfo user, BookInfo book) {
		boolean isValid = false;
		RequestInfo requestInfo1 = new RequestInfo();
		for (RequestInfo requestInfo : LibraryDB.REQUEST) {
			if ((requestInfo.getBookInfo().getBookId()) == (book.getBookId())
					&& (requestInfo.getUserInfo().getId()) == (user.getId()) 
					&& (requestInfo.isReturned()) == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {
			book.setAuthor(requestInfo1.getBookInfo().getAuthor());
			book.setBookName(requestInfo1.getBookInfo().getBookName());
			LibraryDB.BOOKS.add(book);
			LibraryDB.REQUEST.remove(requestInfo1);
			for (UserInfo StudentInfo2 : LibraryDB.USER) {
				if (StudentInfo2.getId() == user.getId()) {
					user = StudentInfo2;
				}
			}
			int noOfBooksBorrowed = user.getBooksBorrowed();
			noOfBooksBorrowed--;
			user.setBooksBorrowed(noOfBooksBorrowed);
			return true;
		}
		return false;
	}
	}


