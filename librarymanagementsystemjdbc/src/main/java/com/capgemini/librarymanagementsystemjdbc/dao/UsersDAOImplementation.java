package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryException;

public class UsersDAOImplementation implements UsersDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean register(UsersInfo user) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("registerQuery"))) {
				pstmt.setInt(1, user.getUserId());
				pstmt.setString(2, user.getFirstName());
				pstmt.setString(3, user.getLastName());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getPassword());
				pstmt.setLong(6, user.getMobile());
				pstmt.setString(7, user.getRole());
				int count = pstmt.executeUpdate();
				if (user.getEmail().isEmpty() && count == 0) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public UsersInfo login(String email, String password) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("loginQuery"));) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					UsersInfo bean = new UsersInfo();
					bean.setUserId(rs.getInt("userId"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobile(rs.getLong("mobile"));
					bean.setRole(rs.getString("role"));
					return bean;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean addBook(BookInfo book) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("addBookQuery"));) {
				pstmt.setInt(1, book.getBookId());
				pstmt.setString(2, book.getBookName());
				pstmt.setString(3, book.getAuthor());
				pstmt.setString(4, book.getCategory());
				pstmt.setString(5, book.getPublisher());
				// pstmt.setInt(6, book.getCopies());
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean removeBook(int bookId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("removeBookQuery"));) {
				pstmt.setInt(1, bookId);
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBook(BookInfo book) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("updateBookQuery"));) {
				pstmt.setString(1, book.getBookName());
				pstmt.setInt(2, book.getBookId());
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pst = conn.prepareStatement(pro.getProperty("issueBookQuery1"));) {
				pst.setInt(1, bookId);
				rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("issueBookQuery2"))) {
						pstmt.setInt(1, userId);
						pstmt.setInt(2, bookId);
						pstmt.setInt(3, userId);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							try (PreparedStatement pstmt1 = conn
									.prepareStatement(pro.getProperty("issueBookQuery3"));) {
								pstmt1.setInt(1, bookId);
								pstmt1.setInt(2, userId);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Calendar cal = Calendar.getInstance();
								String issueDate = sdf.format(cal.getTime());
								pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
								cal.add(Calendar.DAY_OF_MONTH, 7);
								String returnDate = sdf.format(cal.getTime());
								pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
								int count = pstmt1.executeUpdate();
								if (count != 0) {
									try (PreparedStatement pstmt2 = conn
											.prepareStatement(pro.getProperty("issueBookQuery4"))) {
										pstmt2.setInt(1, userId);
										pstmt2.setInt(2, bookId);
										pstmt2.setInt(3, userId);
										int isBorrowed = pstmt2.executeUpdate();
										if (isBorrowed != 0) {
											return true;
										} else {
											return false;
										}
									}
								} else {
									throw new LibraryException("Book Not issued");
								}
							}
						} else {
							throw new LibraryException("The respective user have not placed any request");
						}
					}
				} else {
					throw new LibraryException("There is no book exist with bookId" + bookId);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean requestBook(int userId, int bookId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pst = conn.prepareStatement(pro.getProperty("requestBookQuery1"));) {
				pst.setInt(1, bookId);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("requestBookQuery2"));) {
						pstmt.setInt(1, userId);
						pstmt.setInt(2, bookId);
						pstmt.setInt(3, userId);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							int isBookExists = rs.getInt("userId");
							if (isBookExists == 0) {
								try (PreparedStatement pstmt1 = conn
										.prepareStatement(pro.getProperty("requestBookQuery3"));) {
									pstmt1.setInt(1, userId);
									rs = pstmt1.executeQuery();
									if (rs.next()) {
										int noOfBooksBorrowed = rs.getInt("userId");
										if (noOfBooksBorrowed < 3) {
											try (PreparedStatement pstmt2 = conn
													.prepareStatement(pro.getProperty("requestBookQuery4"));) {
												pstmt2.setInt(1, userId);
												pstmt2.setInt(2, userId);
												pstmt2.setInt(3, bookId);
												pstmt2.setInt(4, bookId);
												pstmt2.setInt(5, userId);
												int count = pstmt2.executeUpdate();
												if (count != 0) {
													return true;
												} else {
													return false;
												}
											}
										} else {
											throw new LibraryException("no Of books limit has crossed");
										}
									} else {
										throw new LibraryException("no of books limit has crossed");
									}
								}
							} else {
								throw new LibraryException("You have already borrowed the requested book");
							}
						} else {
							throw new LibraryException("You have already borrowed the requested book");
						}
					}

				} else {
					throw new LibraryException("The book with requested id is not present");
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public LinkedList<BookInfo> searchBookByTitle(String bookName) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("searchBookByTitle"));) {
				pstmt.setString(1, bookName);
				rs = pstmt.executeQuery();
				LinkedList<BookInfo> beans = new LinkedList<BookInfo>();
				while (rs.next()) {
					BookInfo bean = new BookInfo();
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					// bean.setCopies(rs.getInt("copies"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<BookInfo> searchBookByAuthor(String author) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("searchBookByAuthor"));) {
				pstmt.setString(1, author);
				rs = pstmt.executeQuery();
				LinkedList<BookInfo> beans = new LinkedList<BookInfo>();
				while (rs.next()) {
					BookInfo bean = new BookInfo();
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();) {
				rs = stmt.executeQuery(pro.getProperty("getBooksInfoQuery"));
				ArrayList<BookInfo> beans = new ArrayList<BookInfo>();
				while (rs.next()) {
					BookInfo bean = new BookInfo();
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pst = conn.prepareStatement(pro.getProperty("returnBookQuery1"));) {
				pst.setInt(1, bookId);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("returnBookQuery2"));) {
						pstmt.setInt(1, bookId);
						pstmt.setInt(2, userId);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							Date issueDate = rs.getDate("issueDate");
							Calendar cal = Calendar.getInstance();
							Date returnDate = cal.getTime();
							long difference = issueDate.getTime() - returnDate.getTime();
							float daysBetween = (difference / (1000 * 60 * 60 * 24));
							if (daysBetween > 7) {
								float fine = daysBetween * 5;
								System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
								if (status == "yes") {
									try (PreparedStatement pstmt1 = conn
											.prepareStatement(pro.getProperty("returnBookQuery3"));) {
										pstmt1.setInt(1, bookId);
										pstmt1.setInt(2, userId);
										int count = pstmt1.executeUpdate();
										if (count != 0) {
											try (PreparedStatement pstmt2 = conn
													.prepareStatement(pro.getProperty("returnBookQuery4"));) {
												pstmt2.setInt(1, bookId);
												pstmt2.setInt(2, userId);
												int isReturned = pstmt2.executeUpdate();
												if (isReturned != 0) {
													try (PreparedStatement pstmt3 = conn
															.prepareStatement(pro.getProperty("returnBookQuery5"));) {
														pstmt3.setInt(1, bookId);
														pstmt3.setInt(2, userId);
														int isRequestDeleted = pstmt3.executeUpdate();
														if (isRequestDeleted != 0) {
															return true;
														} else {
															return false;
														}
													}
												} else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								} else {
									throw new LibraryException("The User has to pay fine for delaying book return");
								}
							} else {
								try (PreparedStatement pstmt1 = conn
										.prepareStatement(pro.getProperty("returnBookQuery3"));) {
									pstmt1.setInt(1, bookId);
									pstmt1.setInt(2, userId);
									int count = pstmt1.executeUpdate();
									if (count != 0) {
										try (PreparedStatement pstmt2 = conn
												.prepareStatement(pro.getProperty("returnBookQuery4"));) {
											pstmt2.setInt(1, bookId);
											pstmt2.setInt(2, userId);
											int isReturned = pstmt2.executeUpdate();
											if (isReturned != 0) {
												try (PreparedStatement pstmt3 = conn
														.prepareStatement(pro.getProperty("returnBookQuery5"));) {
													pstmt3.setInt(1, bookId);
													pstmt3.setInt(2, userId);
													int isRequestDeleted = pstmt3.executeUpdate();
													if (isRequestDeleted != 0) {
														return true;
													} else {
														return false;
													}
												}
											} else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							}
						} else {
							throw new LibraryException("This respective user hasn't borrowed any book");
						}
					}

				} else {
					throw new LibraryException("No book exist with bookId" + bookId);
				}

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int userId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("bookHistoryDetailsQuery"));) {
				pstmt.setInt(1, userId);
				rs = pstmt.executeQuery();
				LinkedList<BookIssueDetails> beans = new LinkedList<BookIssueDetails>();
				while (rs.next()) {
					BookIssueDetails issueDetails = new BookIssueDetails();
					issueDetails.setuId(rs.getInt("userId"));
					beans.add(issueDetails);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int userId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("borrowedBookQuery"));) {
				pstmt.setInt(1, userId);
				rs = pstmt.executeQuery();
				LinkedList<BorrowedBooks> beans = new LinkedList<BorrowedBooks>();
				while (rs.next()) {
					BorrowedBooks listOfbooksBorrowed = new BorrowedBooks();
					listOfbooksBorrowed.setuId(rs.getInt("userId"));
					listOfbooksBorrowed.setbId(rs.getInt("bookId"));
					listOfbooksBorrowed.setEmail(rs.getString("email"));
					beans.add(listOfbooksBorrowed);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<BookInfo> searchBookById(int bookId) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("searchBookByIdQuery"));) {
				pstmt.setInt(1, bookId);
				rs = pstmt.executeQuery();
				LinkedList<BookInfo> beans = new LinkedList<BookInfo>();
				while (rs.next()) {
					BookInfo bean = new BookInfo();
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthor(rs.getString("author"));
					bean.setCategory(rs.getString("category"));
					bean.setPublisher(rs.getString("publisher"));
					// bean.setCopies(rs.getInt("copies"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<RequestDetails> showRequests() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(pro.getProperty("showRequestsQuery"));) {
				LinkedList<RequestDetails> beans = new LinkedList<RequestDetails>();
				while (rs.next()) {
					RequestDetails bean = new RequestDetails();
					bean.setuId(rs.getInt("userId"));
					bean.setFullName(rs.getString("fullName"));
					bean.setbId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<BookIssueDetails> showIssuedBooks() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(pro.getProperty("showIssuedBooksQuery"));) {
				LinkedList<BookIssueDetails> beans = new LinkedList<BookIssueDetails>();
				while (rs.next()) {
					BookIssueDetails bean = new BookIssueDetails();
					bean.setBookId(rs.getInt("bookId"));
					bean.setuId(rs.getInt("userId"));
					bean.setIssueDate(rs.getDate("issueDate"));
					bean.setReturnDate(rs.getDate("returnDate"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public LinkedList<UsersInfo> showUsers() {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery(pro.getProperty("showUsersQuery"));) {
				LinkedList<UsersInfo> beans = new LinkedList<UsersInfo>();
				while (rs.next()) {
					UsersInfo bean = new UsersInfo();
					bean.setUserId(rs.getInt("userId"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobile(rs.getLong("mobile"));
					bean.setRole(rs.getString("role"));
					beans.add(bean);
				}
				return beans;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro);
					PreparedStatement pst = conn.prepareStatement(pro.getProperty("updatePasswordQuery1"))) {
				pst.setString(1, email);
				pst.setString(2, role);
				rs = pst.executeQuery();
				if (rs.next()) {
					try (PreparedStatement pstmt = conn.prepareStatement(pro.getProperty("updatePasswordQuery2"));) {
						pstmt.setString(1, newPassword);
						pstmt.setString(2, email);
						pstmt.setString(3, password);
						int count = pstmt.executeUpdate();
						if (count != 0) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					throw new LibraryException("User doesnt exist");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
