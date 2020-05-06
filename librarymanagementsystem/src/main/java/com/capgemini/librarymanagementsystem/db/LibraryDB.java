package com.capgemini.librarymanagementsystem.db;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;

public class LibraryDB {

	public static final ArrayList<BookInfo> BOOKS = new ArrayList<BookInfo>();
	public static final ArrayList<UserInfo> USER = new ArrayList<UserInfo>();
	public static final ArrayList<AdminInfo> ADMIN = new ArrayList<AdminInfo>();
	public static final ArrayList<RequestInfo> REQUEST = new ArrayList<RequestInfo>();

	public static void addToDB() {

		ADMIN.add(new AdminInfo(123123, "bhavani", "bhavani@gmail.com", 994851751, "Bhav@1234"));
		ADMIN.add(new AdminInfo(345345, "shivani", "shivani@gmail.com", 991216571, "Shiva@7285"));


		BOOKS.add(new BookInfo(123456, "jdbc", "Jon", "Api", "SunMicroSystems"));
		BOOKS.add(new BookInfo(654321, "Java", "JamesGosling", "Programming", "SunMicroSystems"));
		BOOKS.add(new BookInfo(147896, "Angular", "Sathwik", "Programming", "SunMicroSystems"));


	}
}
