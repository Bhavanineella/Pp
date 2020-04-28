package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;

public interface AdminService {
	AdminInfo auth(String aemail,String apassword);
	boolean registerAdmin(AdminInfo ai);
	boolean addBook(BooksInfo bi);
	boolean removeBook(int bid);
	boolean issueBook(int bid);
	LinkedList<BooksInfo> searchBookByTitle(String bname);
	LinkedList<BooksInfo> searchBookByAuthor(String bauthor);
	LinkedList<BooksInfo> getBooksInfo();
	boolean updateBook(int bid);

}
