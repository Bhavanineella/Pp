package com.capgemini.librarymanagementsystemjdbc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.exception.LibraryException;

public class JdbcUtility {

	private static Connection connection = null;
	
	public static Connection getConnection() throws LibraryException {
		
		File file = null;
		FileInputStream inputStream = null;
		
		file = new File("db.properties");
		try {
			inputStream = new FileInputStream(file);
			
			Properties properties = new Properties();
			properties.load(inputStream);
			
			String path = properties.getProperty("path");
			String dburl = properties.getProperty("dburl");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			
			Class.forName(path);
			connection = DriverManager.getConnection(dburl, user, password);
			
		} catch (FileNotFoundException e) {
			throw new LibraryException("File not exists");
		} catch (IOException e) {
			throw new LibraryException("Unable to read the data from the file");
		} catch (ClassNotFoundException e) {
			throw new LibraryException("Class not loaded");
		} catch (SQLException e) {
			throw new LibraryException("Connection issue");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new LibraryException("Unable to close the file");
			}
			}
			return connection;
		}
	}

