package com.capgemini.librarymanagementsystem.service;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.factory.Factory;

public class AdminServiceImplementation implements AdminService{
 
	private AdminDAO dao=Factory.getAdminDAO();
	
	@Override
	public AdminInfo auth(String aemail, String apassword) {
	
		return dao.auth(aemail, apassword);
	}

	@Override
	public boolean registerAdmin(AdminInfo ai) {

		return dao.registerAdmin(ai);
	}

	@Override
	public boolean addBook(BooksInfo bi) {
	
		return dao.addBook(bi);
	}

	@Override
	public boolean removeBook(int bid) {
	
		return dao.removeBook(bid);
	}

	@Override
	public boolean issueBook(int bid) {
	
		return dao.issueBook(bid);
	}

	@Override
	public LinkedList<BooksInfo> searchBookByTitle(String bname) {
		
		return dao.searchBookByTitle(bname);
	}

	@Override
	public LinkedList<BooksInfo> searchBookByAuthor(String bauthor) {
		
		return dao.searchBookByAuthor(bauthor);
	}

	@Override
	public LinkedList<BooksInfo> getBooksInfo() {
		
		return dao.getBooksInfo();
	}

	@Override
	public boolean updateBook(int bid) {
		
		return dao.updateBook(bid);
	}
}
