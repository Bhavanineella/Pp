package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryException;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryFactory;
import com.capgemini.librarymanagementsystemjdbc.service.UsersService;
import com.capgemini.librarymanagementsystemjdbc.validation.Validation;

public class LMSMain {
    
	public static void doReg() {
		
		boolean flag = false;
		int regId = 0; 
		String regFirstName = null; 
		String regLastName = null; 
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;
		String regRole = null;
		boolean loginStatus = true;
		
		Validation validation = new Validation();
		do {
			try (Scanner scanner = new Scanner(System.in);) {
				System.out.println("<----------------------->");
				System.out.println("PRESS-1 TO REGISTER");
				System.out.println("PRESS-2 TO LOGIN");
				System.out.println("PRESS-3 TO EXIT");
				System.out.println("<----------------------->");
				do {
					try {
						UsersService service1 = LibraryFactory.getUsersService();
						
						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							do {
								try {
									System.out.println("Enter ID to register : ");
									regId = scanner.nextInt();
									validation.validatedId(regId);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Id should contains only digits");
								} catch (LibraryException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							do {
								try {
									System.out.println("Enter First Name : ");
									regFirstName = scanner.next();
									validation.validatedName(regFirstName);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Name should contains only Alphabates");
								} catch (LibraryException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);
							
							do {
								try {
									System.out.println("Enter Last Name : ");
									regLastName = scanner.next();
									validation.validatedName(regLastName);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Name should contains only Alphabates");
								} catch (LibraryException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							do {
								try {
									System.out.println("Enter Email  : ");
									regEmail = scanner.next();
									validation.validatedEmail(regEmail);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Email should be proper ");
								} catch (LibraryException e) {
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
								} catch (LibraryException e) {
									flag = false;
									System.err.println(e.getMessage());
								} //catch (javax.xml.bind.LMSException e) {
									// TODO Auto-generated catch block
								//	e.printStackTrace();
								//}
							} while (!flag);

							do {
								try {
									System.out.println("Enter MobileNumber : ");
									regMobile = scanner.nextLong();
									validation.validatedMobile(regMobile);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Mobile Number  should contains only numbers");
								} catch (LibraryException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);
							
							do {
								try {
									System.out.println("Enter Role :");
									regRole = scanner.next();
									validation.validatedRole(regRole);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Choose either librarian or student as role");
								} catch (LibraryException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);
							
							UsersInfo ai = new UsersInfo();
							ai.setUserId(regId);
							ai.setFirstName(regFirstName);
							ai.setLastName(regLastName);
							ai.setEmail(regEmail);
							ai.setPassword(regPassword);
							ai.setMobile(regMobile);
							ai.setRole(regRole);
							try {
								boolean check=service1.register(ai);
								if(check) {
									System.out.println("Registered");
								}else {
									System.out.println("Already user is registered");
								}
							}catch (LibraryException e) {
								System.err.println(e.getMessage());
							}
							break;
							
						case 2:
							System.out.println("Enter registered email to login : ");
							String email = scanner.next();
							System.out.println("Enter registered password to login : ");
							String password = scanner.next();
							try {
								UsersInfo loginInfo = service1.login(email, password);
								if ((loginInfo.getEmail().equals(email)) && (loginInfo.getPassword().equals(password))) {
									System.out.println("LOGGED IN SUCCESSFULLY");
								}
								if(loginInfo.getRole().equals("librarian")) {
									do {
										try {
											System.out.println("-----------------------------------------------");
											System.out.println("PRESS-1 TO ADD BOOKS");
											System.out.println("PRESS-2 TO REMOVE THE BOOK");
											System.out.println("PRESS-3 TO ISSUE BOOK");
											System.out.println("PRESS-4 TO SEARCH BOOK BY AUTHOR");
											System.out.println("PRESS-5 TO SEARCH BOOK BY TITLE");
											System.out.println("PRESS-6 TO GET INFO ABOUT ALL BOOKS");
											System.out.println("PRESS-7 TO SEARCH BOOK BY ID");
											System.out.println("PRESS-8 TO UPDATE THE BOOK");
											System.out.println("PRESS-9 TO CHECK BOOK HISTROY OF STUDENT");
											System.out.println("PRESS-10 TO VIEW REQUESTS");
											System.out.println("PRESS-11 TO CHECK THE ISSUED BOOKS");
											System.out.println("PRESS-12 TO VIEW STUDENTS");
											System.out.println("PRESS-13 TO UPDATE THE PASSWORD");
											System.out.println("PRESS-14 TO LOGOUT");

											int choice1 = scanner.nextInt();
											switch (choice1) {
											case 1:
												System.out.println("Enter book id to add : ");
												int addId = scanner.nextInt();
												System.out.println("Enter bookname : ");
												String addName = scanner.next();
												System.out.println("Enter authorname : ");
												String addAuth = scanner.next();
												System.out.println("Enter category : ");
												String addCategory = scanner.next();
												System.out.println("Enter publisher : ");
												String addPublisher = scanner.next();
											
												BookInfo bi = new BookInfo();
												bi.setBookId(addId);
												bi.setBookName(addName);
												bi.setAuthor(addAuth);
												bi.setCategory(addCategory);
												bi.setPublisher(addPublisher);
						
												try {
													boolean check2 = service1.addBook(bi);
													if (check2) {
														System.out.println("<------------------------------------->");
														System.out.println("Book is added of id = "+addId);
													} else {
														System.out.println("<-------------------------------------->");
														System.out.println("Book not added of id = "+addId);
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
                                                break;	
												
											case 2:
												System.out.println("Enter book id to remove : ");
												int removeId = scanner.nextInt();
												try {
													boolean check3 = service1.removeBook(removeId);
													if (check3) {
														System.out.println("<------------------------------------------->");
														System.out.println("Book is removed of id = "+removeId);
													} else {
														System.out.println("<-------------------------------------------->");
														System.out.println("Book is not removed of id = "+removeId);
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 3:
												System.out.println("Enter Book Id to issue : ");
												int issueId = scanner.nextInt();
												System.out.println("Enter Student Id : ");
												int studentId = scanner.nextInt();
												try {
													boolean check4 = service1.issueBook(issueId,studentId);
													if (check4) {
														System.out.println("<------------------------------>");
														System.out.println("Book Issued of id = "+issueId);
													} else {
														System.out.println("<-------------------------------->");
														System.out.println("Book is not issued of id = "+issueId);
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 4:
												System.out.println("Search book by AuthorName : ");
												String author = scanner.next();
												try {
													List<BookInfo> bookauthor = service1.searchBookByAuthor(author);
													System.out.println(
															"<----------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
													for (BookInfo BookInfo : bookauthor) {
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out
																	.println("No books are available with this authorname");
														}
													}
												}catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
											case 5:
												System.out.println("Search book by Title : ");
												String btitle = scanner.next();
												try {
													List<BookInfo> booktitle = service1.searchBookByTitle(btitle);
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
													for (BookInfo BookInfo : booktitle) {	
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out.println("No books are available with this title");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 6:
												try {
													ArrayList<BookInfo> info = service1.getBooksInfo();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
											        for (BookInfo BookInfo : info) {
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out.println("No books are available in the library");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 7:
												System.out.println("Search book by the Book Id : ");
												int book_Id = scanner.nextInt();
												try {
													List<BookInfo> bId = service1.searchBookById(book_Id);
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
													for (BookInfo BookInfo : bId) {	
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out.println("No books are available with this Id");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 8:
												System.out.println("Enter the bookId to be updated : ");
												int bid = scanner.nextInt();
												System.out.println("Enter bookName to be updated : ");
												String updatedBookName = scanner.next();
												BookInfo bean2 = new BookInfo();
												bean2.setBookId(bid);
												bean2.setBookName(updatedBookName);
												try {
													boolean updated = service1.updateBook(bean2);
													if (updated) {
														System.out.println("<--------------------------------->");
														System.out.println("Book is updated of id = "+bid);
													} else {
														System.out.println("<----------------------------------->");
														System.out.println("Book is not updated of id = "+bid);
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 9:
												System.out.println("Enter the Student Id : ");
												int student_Id = scanner.nextInt();
												try {
													List<BookIssueDetails> sId = service1.bookHistoryDetails(student_Id);
													for (BookIssueDetails issueDetails : sId) {
														if(issueDetails != null) {
															System.out.println("<---------------------------------------------->");
															System.out.println("No of books Borrowed :"+issueDetails.getUserId());
														} else {
															System.out.println("<---------------------------------------------->");
															System.out.println("This user hasn't borrowed any books");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 10:
												System.out.println(" Requests received are : ");
												try {
													List<RequestDetails> requests = service1.showRequests();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-10s %s", "UserId",
															"UserName", "BookId", "BookName"));
													
													for (RequestDetails requestBean : requests) {	
														if (requestBean != null) {
															System.out.println(requestBean.toString());
														} else {
															System.out.println("No Requests are received");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 11:
												System.out.println("Issued Books are : ");
												try {
													List<BookIssueDetails> issuedBooks = service1.showIssuedBooks();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-10s %s", "BookId",
															"UserId", "IssueDate", "ReturnDate"));
													for (BookIssueDetails issueBean : issuedBooks) {	
														if (issueBean != null) {
															System.out.println(issueBean.toString());
														} else {
															System.out.println("No book has been issued");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 12:
												System.out.println("Users are : ");
												try {
													List<UsersInfo> users = service1.showUsers();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-10s %-15s %-10s %-13s %s", "UserId",
															"FirstName", "LastName", "EmailId", "Password", "MobileNumber", "Role"));
													for (UsersInfo bean : users) {	
														if (bean != null) {
															System.out.println(bean.toString());
														} else {
											                System.out.println("No Users are present");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 13:
												System.out.println("Enter the email : ");
												String email_Id = scanner.next();
												System.out.println("Enter the old password : ");
												String old_Password = scanner.next();
												System.out.println("Enter the new password : ");
												String new_Password = scanner.next();
												String student_Role = loginInfo.getRole();
												try {
													boolean updated = service1.updatePassword(email_Id, old_Password, new_Password, student_Role);
													if (updated) {
														System.out.println("<------------------------------>");
														System.out.println("Password is updated of email id = "+email_Id);
													} else {
														System.out.println("<------------------------------->");
														System.out.println("Password is not updated of email id = "+email_Id);
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 14:
												doReg();

											default:
												System.out.println("<----------------------------------->");
												System.out.println("Invalid Choice");
												break;
											}
										} catch (InputMismatchException ex) {
											System.out.println("Incorrect entry. Please input only a positive integer.");
											scanner.nextLine();
										}
									} while (true);
								}
								else if(loginInfo.getRole().equals("student")) {
									do {
										try {
											System.out.println("<------------------------------------------>");
											System.out.println("PRESS-1 TO REQUEST THE BOOK");
											System.out.println("PRESS-2 TO VIEW THE BOOKS BORROWED");
											System.out.println("PRESS-3 TO SEARCH BOOK BY AUTHOR");
											System.out.println("PRESS-4 TO SEARCH BOOK BY TITLE");
											System.out.println("PRESS-5 TO SEARCH BOOK BY ID");
											System.out.println("PRESS-6 TO GET INFO ABOUT ALL BOOKS");
											System.out.println("PRESS-7 TO RETURN THE BOOK");
											System.out.println("PRESS-8 TO UPDATE THE PASSWORD");
											System.out.println("PRESS-9 TO RETURN BACK TO MAIN");

											int choice2 = scanner.nextInt();
											switch (choice2) {
											case 1:
												System.out.println("Enter the Book Id : ");
												int reqBookId = scanner.nextInt();
												System.out.println("Enter the Student Id : ");
												int reqstudentId = scanner.nextInt();
												try {
													if (loginInfo.getUserId() == reqstudentId) {
														boolean requested = service1.requestBook(reqstudentId,reqBookId);
														if (requested != false) {
															System.out.println("<------------------------------------->");
															System.out.println("Book is Requested of id = "+reqBookId);
														} else {
															System.out.println("<------------------------------------->");
															System.out.println("Book is not Requested of id = "+reqBookId);
														}	
													} else {
														System.out.println("Enter the correct studentId");
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 2:
												System.out.println("Enter the user Id : ");
												int student_Id = scanner.nextInt();
												try {
													if(loginInfo.getUserId() == student_Id) {
														List<BorrowedBooks> borrowedBookList = service1.borrowedBook(student_Id);
														System.out.println(
																"<--------------------------------------------------------------------->");
														System.out.println(String.format("%-10s %-10s %s", "UserId",
																"BookId", "EmailId"));
														for (BorrowedBooks BookInfo : borrowedBookList) {
															if (BookInfo != null) {
																System.out.println(BookInfo.toString());
															} else {
																System.out.println("No books are borrowed by the student");
															}
														}
													} else {
														System.out.println("Incorrect user Id");
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 3:
												System.out.println("Search book by Author Name : ");
												String author = scanner.next();
												try {
													List<BookInfo> bookauthor = service1.searchBookByAuthor(author);
													System.out.println(
															"<----------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
													for (BookInfo BookInfo : bookauthor) {
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out
																	.println("No books are available with this authorname");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 4:
												System.out.println("Search book by Book Title : ");
												String btitle = scanner.next();
												try {
													List<BookInfo> booktitle = service1.searchBookByTitle(btitle);
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
													for (BookInfo BookInfo : booktitle) {	
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out.println("No books are available with this title");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 5:
												System.out.println("Search the book by id : ");
												int book_Id = scanner.nextInt();

												try {
													List<BookInfo> bId = service1.searchBookById(book_Id);
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
													for (BookInfo BookInfo : bId) {	
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out.println("No books are available with this Id");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 6:
												try {
													ArrayList<BookInfo> info = service1.getBooksInfo();
													System.out.println(
															"<--------------------------------------------------------------------->");
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookAuthor", "BookCategory", "BookPublisherName"));
											        for (BookInfo BookInfo : info) {
														if (BookInfo != null) {
															System.out.println(BookInfo.toString());
														} else {
															System.out.println("No books are available in the library");
														}
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;
												
											case 7:
												System.out.println("Enter the Book id to return : ");
												int returnId = scanner.nextInt();
												System.out.println("Enter studentId : ");
												int studentId = scanner.nextInt();	
												System.out.println("Enter the status(yes/no) of the book : ");
												String status = scanner.next();
												try {
													if(loginInfo.getUserId() == studentId) {
														boolean returned = service1.returnBook(returnId,studentId,status);
														if (returned != false) {
															System.out.println("<------------------------------->");
															System.out.println("Book is Returned of id = "+returnId);
														} else {
															System.out.println("<------------------------------->");
															System.out.println("Book is not Returned of id = "+returnId);
														}	
													} else {
														System.out.println("Invalid studentId");
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 8:
												System.out.println("Enter the email : ");
												String email_Id = scanner.next();
												System.out.println("Enter the old password : ");
												String old_Password = scanner.next();
												System.out.println("Enter the new password : ");
												String new_Password = scanner.next();
												String student_Role = loginInfo.getRole();
												try {
													boolean updated = service1.updatePassword(email_Id, old_Password, new_Password, student_Role);
													if (updated) {
														System.out.println("<------------------------------------>");
														System.out.println("Password is updated of email id = "+email_Id);
													} else {
														System.out.println("<------------------------------------->");
														System.out.println("Password is not updated of email id = "+email_Id);
													}
												} catch (LibraryException e) {
													System.err.println(e.getMessage());
												}
												break;

											case 9:
												doReg();
												
											default:
												break;
											}
										} catch (InputMismatchException ex) {
											System.out.println("Incorrect entry. Please input only a positive integer.");
											scanner.nextLine();
										}
									} while (true);
								}
							} catch (Exception e) {
								System.out.println("Invalid Credentials");
								System.out.println("Try logging in again,Press 2 to login");
							}
							break;
							
						case 3: System.out.println("EXIT");
						        System.exit(0);
						
						default:
							break;
						}
					} catch (InputMismatchException ex) {
						System.out.println("Incorrect entry. Please input only a positive integer.");
						scanner.nextLine();
					}
				} while (true);
			}
		} while (loginStatus);
	}
}