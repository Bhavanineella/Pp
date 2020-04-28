package com.capgemini.librarymanagementsystemjdbc1.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagementsystemjdbc1.exception.LibraryException;


public class Validation {
	public boolean validatedId(int id) throws LibraryException {
		String idRegEx = "[0-9]{1}[0-9]{5}" ;
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LibraryException("Id should contains exactly 6 digits");
		}
		return result;
	}
	public boolean validatedName(String name) throws LibraryException {
		String nameRegEx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$" ;
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LibraryException("Name should  contains only Alphabates");
		}
		return result;
	}
	public boolean validatedMobile(long mobile) throws LibraryException {
		String mobileRegEx = "(0/91)?[6-9][0-9]{9}" ;
		boolean result = false;
		if (Pattern.matches(mobileRegEx, String.valueOf(mobile))) {
			result = true;
		} else {
			throw new LibraryException("Mobile Number  will start with  6 or 9 and It should contains 10 numbers");
		}
		return result;
	}
	public boolean validatedEmail(String email) throws LibraryException {
		String emailRegEx = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LibraryException("Enter proper email ");
		}
		return result;
	}
	public boolean validatedPassword(String password) throws LibraryException {
		String passwordRegEx = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})" ;
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) { 
			result = true;
		} else {
			throw new LibraryException("Password should contain atleast 8 characters ,one uppercase,one lowercase,one symbol"); 
		}
		return result;
	}
}
