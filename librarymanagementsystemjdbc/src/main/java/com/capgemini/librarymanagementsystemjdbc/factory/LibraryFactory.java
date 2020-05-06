package com.capgemini.librarymanagementsystemjdbc.factory;

import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc.service.UsersServiceImplementation;

public class LibraryFactory {
	public static UsersDAO getUsersDao() {
		return new UsersDAOImplementation();
	}
	public static UsersService getUsersService() {
		return new UsersServiceImplementation();
	}
}
