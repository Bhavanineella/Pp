package com.capgemini.librarymanagementsystem.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystem.db.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.exception.LMSException;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.Validation;

public class LibraryController {
	
	public static void doReg() {
		
		LibraryDB.addToDB();

		boolean flag = false;
		int regId = 0;
		String regName = null;
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;
		int regId1 = 0;
		String regName1 = null;
		long regMobile1 = 0;
		String regEmail1 = null;
		String regPassword1 = null;
		int bookId = 0;
		String bookAuthor = null;
		String bookName = null;
		String bookCategory = null;
		String bookPublisherName = null;

		Validation validation = new Validation();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			try {
				System.out.println("<----------------------<<< WELCOME TO LIBRARY >>>--------------------->");
				System.out.println("PRESS-1 TO ADMIN PAGE");
				System.out.println("PRESS-2 TO USER PAGE");
				System.out.println("<--------------------------------------------------------------------->");
				int i = scanner.nextInt();
				switch (i) {
				case 1:
					AdminService service = LibraryFactory.getAdminService();
					do {
						try {
							System.out
									.println("<--------------------------------------------------------------------->");
							System.out.println("PRESS-1 TO ADMIN REGISTER");
							System.out.println("PRESS-2 TO ADMIN LOGIN");
							System.out.println("PRESS-3 TO EXIT");
							System.out
									.println("<--------------------------------------------------------------------->");
							int choice = scanner.nextInt();
							switch (choice) {
							case 1:
								do {
									try {
										System.out.println("Enter ID to Register as Admin : ");
										regId = scanner.nextInt();
										validation.validatedId(regId);
										flag = true;
									} catch (InputMismatchException e) {
										System.err.println("ID should consist of only digits");
										flag = false;
                                        scanner.next();
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Name to Register : ");
										regName = scanner.next();
										validation.validatedName(regName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should consists of only Alphabets");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter MobileNumber to Register : ");
										regMobile = scanner.nextLong();
										validation.validatedMobile(regMobile);
										flag = true;
									} catch (InputMismatchException e) {
                                         System.err.println("Mobile Number  should consists of only numbers");
                                         flag = false;
                                         scanner.next();
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Email to Register : ");
										regEmail = scanner.next();
										validation.validatedEmail(regEmail);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println(
												"Enter proper email such that it should consist of numbers and alphabets");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Password :");
										regPassword = scanner.next();
										validation.validatedPassword(regPassword);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Enter correct Password ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									} 
								} while (!flag);

								AdminInfo bean = new AdminInfo();
								bean.setId(regId);
								bean.setName(regName);
								bean.setMobileNo(regMobile);
								bean.setEmail(regEmail);
								bean.setPassword(regPassword);

								boolean check = service.registerAdmin(bean);
								if (check) {
									System.out.println("REGISTERED SUCCESSFULLY");
								} else {
									System.out.println("Email already exist");
								}
								break;

							case 2:
								System.out.println("Enter registered email to login : ");
								String email = scanner.next();
								System.out.println("Enter registered Password to login : ");
								String password = scanner.next();
								try {
									@SuppressWarnings("unused")
									AdminInfo authBean = service.loginAdmin(email, password);
									System.out.println("LOGGED IN SUCCESSFULLY");

									do {
										try {
											System.out.println(
													"<--------------------------------------------------------------------->");
											System.out.println("PRESS-1 TO ADD BOOKS");
											System.out.println("PRESS-2 TO UPDATE THE BOOK");
											System.out.println("PRESS-3 TO SEARCH BOOK BY AUTHOR");
											System.out.println("PRESS-4 TO SEARCH BOOK BY TITLE");
											System.out.println("PRESS-5 TO SEARCH BOOK BY CATEGORY");
											System.out.println("PRESS-6 TO REMOVE THE BOOK");
											System.out.println("PRESS-7 TO GET INFO ABOUT ALL BOOKS");
											System.out.println("PRESS-8 TO ISSUE THE BOOK");
											System.out.println("PRESS-9 TO VIEW STUDENTS");
											System.out.println("PRESS-10 TO VIEW REQUESTS");
											System.out.println("PRESS-11 TO RECEIVE RETURNED BOOK");
											System.out.println("PRESS-12 TO  RETURN BACK TO MAIN");
											System.out.println(
													"<--------------------------------------------------------------------->");
											int choice1 = scanner.nextInt();
											switch (choice1) {
											case 1:

												do {
													try {
														System.out.println("Enter BookID to add : ");
														bookId = scanner.nextInt();
														validation.validatedId(bookId);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Id should contains only digits");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter BookName : ");
														bookName = scanner.next();
														validation.validatedName(bookName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("BookName should contains only Alphabets");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter AuthorName : ");
														bookAuthor = scanner.next();
														validation.validatedName(bookAuthor);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err
																.println("AuthorName should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter Category : ");
														bookCategory = scanner.next();
														validation.validatedName(bookCategory);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"Book-Category should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter PublisherName : ");
														bookPublisherName = scanner.next();
														validation.validatedName(bookPublisherName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"BookPublisherName should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												BookInfo bean1 = new BookInfo();
												bean1.setBookId(bookId);
												bean1.setBookName(bookName);
												bean1.setAuthor(bookAuthor);
												bean1.setPublishername(bookPublisherName);
												bean1.setCategory(bookCategory);
												boolean check2 = service.addBook(bean1);
												if (check2) {
													System.out.println("Book Added of id = " + bookId);
												} else {
													System.out.println("Book already exist of id = " + bookId);
												}
												break;

											case 2:
												System.out.println("Enter the updated id :");
												int bid = scanner.nextInt();
												System.out.println("enter bookname");
												String title = scanner.next();
												System.out.println("enter author");
												String bauthor = scanner.next();
												System.out.println("enter category");
												String category1 = scanner.next();
												BookInfo bean2 = new BookInfo();
												bean2.setBookId(bid);
												bean2.setBookName(title);
												bean2.setAuthor(bauthor);
												bean2.setCategory(category1);
												boolean updated = service.updateBook(bean2);
												if (updated) {
													System.out.println("Book is updated");
												} else {
													System.out.println("Book is not updated");
												}
												break;

											case 3:
												System.out.println("Search book by AuthorName : ");
												String author = scanner.next();

												BookInfo bean3 = new BookInfo();
												bean3.setAuthor(author);
												List<BookInfo> bookauthor = service.searchBookByAuthor(author);
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : bookauthor) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out
																.println("No books are available with this authorname");
													}
												}
												break;

											case 4:
												System.out.println("Search book by Title : ");
												String bookTitle = scanner.next();

												BookInfo bean4 = new BookInfo();
												bean4.setBookName(bookTitle);
												List<BookInfo> booktitle = service.searchBookByTitle(bookTitle);
												System.out.println(
														"<<--------------------------------------------------------------------->>");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : booktitle) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this title");
													}
												}
												break;

											case 5:
												System.out.println("Search book by Book Category : ");
												String category = scanner.next();

												BookInfo bean5 = new BookInfo();
												bean5.setCategory(category);
												List<BookInfo> bookIds = service.searchBookByCategory(category);
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this Category");
													}
												}
												break;

											case 6:
												System.out.println("Enter the BookId to delete : ");
												int bookId3 = scanner.nextInt();
												if (bookId3 == 0) {
													System.out.println("Please Enter the Valid BookId");
												} else {
													BookInfo bean6 = new BookInfo();
													bean6.setBookId(bookId3);
													boolean remove = service.removeBook(bookId3);
													if (remove) {
														System.out.println("The Book is removed of Id = " + bookId3);
													} else {
														System.out
																.println("The Book is not removed of Id = " + bookId3);
													}
												}
												break;

											case 7:
												ArrayList<BookInfo> info = service.getBooksInfo();
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : info) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available in the library");
													}
												}
												break;

											case 8:
												UserInfo StudentBean2 = new UserInfo();
												BookInfo bookBean2 = new BookInfo();
												System.out.println("enter Book Id");
												int bookId4 = scanner.nextInt();
												System.out.println("enter Student Id");
												int uId = scanner.nextInt();
												bookBean2.setBookId(bookId4);
												StudentBean2.setId(uId);
												try {
													boolean isIssued = service.bookIssue(StudentBean2, bookBean2);
													if (isIssued) {
														System.out.println("Book Issued of Id = " + bookId4);
													} else {
														System.out.println("Book cannot be issued of Id = " + bookId4);
													}
												} catch (Exception e) {
													System.out.println("Invalid data! Request book cannot be issued");
												}
												break;

											case 9:
												try {
													System.out.println("Students of Library are : ");
													System.out.println(
															"<--------------------------------------------------------------------->");
													List<UserInfo> UserInfos = service.showUsers();
													System.out.println(String.format("%-10s %-10s %s", "STUDENT-ID",
															"STUDENT-NAME", "STUDENT-EMAIL"));
													for (UserInfo infos : UserInfos) {
														System.out.println(String.format("%-10s %-10s %s",
																infos.getId(), infos.getName(), infos.getEmail()));
													}
												} catch (Exception e) {
													System.out.println(
															"None of the students are registered in the library");
												}
												break;

											case 10:
												try {
													System.out.println("Requests for Books are : ");
													System.out.println(
															"<--------------------------------------------------------------------->");
													List<RequestInfo> requestInfos = service.showRequests();
													System.out.println(String.format("%-10s %-10s %-10s %-10s %-10s %s",
															"BOOK-ID", "BOOK-NAME", "STUDENT-ID", "STUDENT-NAME",
															"BOOK-ISSUEDATE", "BOOK-RETURNDATE"));
													for (RequestInfo info1 : requestInfos) {
														System.out.println(String.format(
																"%-10s %-10s %-10s %-10s %-10s %s",
																info1.getBookInfo().getBookId(),
																info1.getBookInfo().getBookName(),
																info1.getUserInfo().getId(),
																info1.getUserInfo().getName(), info1.getIssuedDate(),
																info1.getReturnedDate()));
													}
												} catch (Exception e) {
													System.out.println("None of the book is requested");
												}
												break;

											case 11:
												System.out.println("Receive Returned Books are : ");
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println("Enter Student Id");
												int Student_Id = scanner.nextInt();
												System.out.println("Enter Book Id");
												int bookId5 = scanner.nextInt();

												UserInfo Student = new UserInfo();
												BookInfo book = new BookInfo();
												Student.setId(Student_Id);
												book.setBookId(bookId5);
												boolean isReceive = service.isBookReceived(Student, book);
												if (isReceive) {
													System.out.println("Received Returned book of Id = " + bookId5);
												} else {
													System.out.println("Invalid Data! Admin unable to receive");
												}
												break;

											case 12:
												doReg();

											default:
												System.out.println("Invalid Choice");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("Invalid entry please provide only positive integer");
											scanner.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;

							case 3:
								doReg();
								break;

							default:
								System.out.println("Invalid Choice");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid entry please provide only positive integer");
							scanner.nextLine();
						}
					} while (true);

				case 2:
					UserService service1 = LibraryFactory.getUserService();
					do {
						try {
							System.out
									.println("<--------------------------------------------------------------------->");
							System.out.println("PRESS-1 TO USER REGISTER");
							System.out.println("PRESS-2 TO USER LOGIN");
							System.out.println("PRESS-3 TO RETURN BACK TO MAIN");
							System.out
									.println("<--------------------------------------------------------------------->");
							int choice = scanner.nextInt();
							switch (choice) {
							case 1:
								do {
									try {
										System.out.println("Enter ID to register as Student : ");
										regId1 = scanner.nextInt();
										validation.validatedId(regId1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Id should contains only digits");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Name to register : ");
										regName1 = scanner.next();
										validation.validatedName(regName1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter MobileNumber to register : ");
										regMobile = scanner.nextLong();
										validation.validatedMobile(regMobile);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Mobile Number  should contains only numbers");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Email to register : ");
										regEmail1 = scanner.next();
										validation.validatedEmail(regEmail1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Email should be proper ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Password to register : ");
										regPassword1 = scanner.next();
										validation.validatedPassword(regPassword1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Enter correct Password ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								UserInfo bean1 = new UserInfo();
								bean1.setId(regId1);
								bean1.setName(regName1);
								bean1.setMobileNo(regMobile1);
								bean1.setEmail(regEmail1);
								bean1.setPassword(regPassword1);

								boolean check = service1.registerUser(bean1);
								if (check) {
									System.out.println("REGISTERED SUCCESSFULLY");
								} else {
									System.out.println("Email already exist");
								}
								break;

							case 2:
								System.out.println("Enter registered email to login : ");
								String email = scanner.next();
								System.out.println("Enter registered Password to login : ");
								String password = scanner.next();
								try {
									@SuppressWarnings("unused")
									UserInfo StudentBean = service1.loginUser(email, password);
									System.out.println("LOGGED IN SUCCESSFULLY");
									do {
										try {
											System.out.println(
													"<--------------------------------------------------------------------->");
											System.out.println("PRESS-1 TO SEARCH BOOK BY AUTHOR");
											System.out.println("PRESS-2 TO SEARCH BOOK BY TITLE");
											System.out.println("PRESS-3 TO SEARCH BOOK BY CATEGORY");
											System.out.println("PRESS-4 TO GET INFO ABOUT ALL BOOKS");
											System.out.println("PRESS-5 TO REQUEST THE BOOK FROM LIBRARY");
											System.out.println("PRESS-6 TO RETURN THE BOOK TO LIBRARY");
											System.out.println("PRESS-7 TO RETURN BACK TO MAIN");
											System.out.println(
													"<--------------------------------------------------------------------->");
											int choice2 = scanner.nextInt();
											switch (choice2) {
											case 1:
												System.out.println("Search book by AuthorName : ");
												String author = scanner.next();

												BookInfo bean2 = new BookInfo();
												bean2.setAuthor(author);
												List<BookInfo> bookauthor = service1.searchBookByAuthor(author);
												System.out.println(
														"<----------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : bookauthor) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out
																.println("No books are available with this authorname");
													}
												}
												break;

											case 2:
												System.out.println("Search book by BookTitle : ");
												String book_Name = scanner.next();

												BookInfo bean3 = new BookInfo();
												bean3.setBookName(book_Name);
												List<BookInfo> booktitle = service1.searchBookByTitle(book_Name);
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : booktitle) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this title");
													}
												}
												break;

											case 3:
												System.out.println("Search book by BookCategory : ");
												String book_Category = scanner.next();

												BookInfo bean4 = new BookInfo();
												bean4.setCategory(book_Category);
												List<BookInfo> bookIds = service1.searchBookByCategory(book_Category);
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this Category");
													}
												}
												break;

											case 4:
												ArrayList<BookInfo> info = service1.getBookInfo();
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
												for (BookInfo bookBean : info) {
													if (bookBean != null) {
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available in the library");
													}
												}
												break;

											case 5:
												System.out.println("Enter book Id : ");
												int bookId2 = scanner.nextInt();

												BookInfo bookBean = new BookInfo();
												bookBean.setBookId(bookId2);
												System.out.println("Enter Student Id : ");
												int StudentId = scanner.nextInt();

												UserInfo Student = new UserInfo();
												Student.setId(StudentId);
												try {
													RequestInfo request = service1.bookRequest(Student, bookBean);
													System.out.println("Request placed to admin");
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-10s %s",
															"STUDENT-ID", "STUDENT-NAME", "BOOK-ID", "BOOK-NAME"));
													System.out.println(String.format("%-10s %-10s %-10s %s",
															request.getUserInfo().getId(),
															request.getUserInfo().getName(),
															request.getBookInfo().getBookId(),
															request.getBookInfo().getBookName()));
												} catch (Exception e) {
													System.out.println("Invalid Request of book");
												}
												break;

											case 6:
												System.out.println("Returning a book:");
												System.out.println(
														"<--------------------------------------------------------------------->");
												System.out.println("Enter Student Id : ");
												int Student2 = scanner.nextInt();
												System.out.println("Enter Book Id : ");
												int book = scanner.nextInt();

												UserInfo StudentBean7 = new UserInfo();
												StudentBean7.setId(Student2);
												BookInfo bookBean7 = new BookInfo();
												bookBean7.setBookId(book);
												try {
													RequestInfo requestInfo = service1.bookReturn(StudentBean7,
															bookBean7);
													System.out.println("Book is Returning to Admin");
													System.out.println(
															"<------------------------------------------------------------------>");
													System.out.println(String.format("%-10s %-10s %s", "STUDENT-ID",
															"BOOK-ID", "IS BOOK RETURNED"));
													System.out.println(String.format("%-10s %-10s %-10s %s",
															requestInfo.getUserInfo().getId(),
															requestInfo.getBookInfo().getBookId(),
															requestInfo.isReturned()));
												} catch (Exception e) {
													System.out.println("Invalid Returning of a book");
												}
												break;

											case 7:
												doReg();

											default:
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("Invalid entry please provide only positive integer");
											scanner.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;
							case 3:
								doReg();
								break;

							default:
								System.out.println("Invalid Choice");
								System.err.println("Choice must be 1 or 2");
								break;
							}
						} catch (InputMismatchException e) { //if we give string in 1 n 2 n 3
							System.out.println("Invalid entry please provide only positive integer");
							scanner.nextLine();
						}
					} while (true);
				}
			} catch (InputMismatchException e) {  ////if we give string in 1 n 2
				System.out.println("Invalid entry please provide only positive integer");
				scanner.nextLine();
			}
		} while (true);
	}
}


