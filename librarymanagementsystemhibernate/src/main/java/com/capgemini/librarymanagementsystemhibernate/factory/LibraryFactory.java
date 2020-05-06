package com.capgemini.librarymanagementsystemhibernate.factory;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystemhibernate.service.UsersService;
import com.capgemini.librarymanagementsystemhibernate.service.UsersServiceImplement;

public class LibraryFactory {
	public static UserDAO getUsersDao() {
		return new UserDAOImplementation();
	}
	public static UsersService getUsersService() {
		return new UsersServiceImplement();
	}
}
