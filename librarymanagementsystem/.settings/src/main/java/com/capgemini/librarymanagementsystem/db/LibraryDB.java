package com.capgemini.librarymanagementsystem.db;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;

public class LibraryDB {

	public static final LinkedList<BooksInfo> BOOKS=new LinkedList<BooksInfo>();
	public static final LinkedList<UserInfo> USER=new LinkedList<UserInfo>();
	public static final LinkedList<AdminInfo> ADMIN=new LinkedList<AdminInfo>();
	
}
