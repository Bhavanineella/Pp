package com.capgemini.librarymanagementsystemjdbc1.factory;

import com.capgemini.librarymanagementsystemjdbc1.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc1.dao.UsersDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc1.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc1.service.UsersServiceImplementation;

public class LibraryFactory {
	public static UsersDAO getUsersDao() {
		return new UsersDAOImplementation();
	}
	public static UsersService getUsersService() {
		return new UsersServiceImplementation();
	}
}
