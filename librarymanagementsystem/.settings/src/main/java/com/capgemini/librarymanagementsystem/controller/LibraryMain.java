package com.capgemini.librarymanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BooksInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.exception.CommonException;
import com.capgemini.librarymanagementsystem.factory.Factory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.Validation;

public class LibraryMain {
	public static void main(String[] args) {
		doReg();
	}

	public static void doReg() {
		
		boolean flag = false;

		int regId = 0; 
		String regName = null; 
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;

		int regId1 = 0; 
		String regName1 = null; 
		String regMobile1 = null;
		String regEmail1 = null;
		String regPassword1 = null;

		Validation validation = new Validation();

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Press 1 to Admin");
			System.out.println("Press 2 to User");

			int i = scanner.nextInt();
			switch(i) {
			case 1:
				
				do {
					try {
						System.out.println("Enter ID :");
						regId = scanner.nextInt();
						validation.validatedId(regId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (CommonException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Name :");
						regName = scanner.next();
						validation.validatedName(regName);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Name should contains only Alphabates");
					} catch (CommonException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Mobile :");
						regMobile = scanner.next();
						validation.validatedMobile(regMobile);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Mobile Number  should contains only numbers");
					} catch (CommonException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Email :");
						regEmail = scanner.next();
						validation.validatedEmail(regEmail);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Email should be proper ");
					} catch (CommonException e) {
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
					} catch (CommonException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				AdminService service1=Factory.getAdminService();
				do{
					System.out.println("Press 1 to Admin Register");
					System.out.println("Press 2 to Admin Login");
					System.out.println("Press 3 to exit");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						System.out.println("enter id");
						int aregId=scanner.nextInt();
						System.out.println("enter name");
						String aregName=scanner.next();
						System.out.println("enter password");
						String aregPassword=scanner.next();
						System.out.println("enter email");
						String aregEmail=scanner.next();
						System.out.println("enter mobileNo");
						long aregMobileNo=scanner.nextLong();
						AdminInfo ai=new AdminInfo();
						ai.setAid(aregId);
						ai.setAname(aregName);
						ai.setApassword(aregPassword);
						ai.setAemail(aregEmail);
						ai.setAmobileNo(aregMobileNo);
						boolean check=service1.registerAdmin(ai);
						if(check)
							System.out.println("Registered");
						else
							System.out.println("Already user is registered");
						break;

					case 2:
						
							System.out.println("enter id");
							int id=scanner.nextInt();
							System.out.println("enter email");
							String email=scanner.next();
							System.out.println("enter password");
							String password=scanner.next();

							try {
								AdminInfo authInfo=service1.auth(email, password);
								System.out.println("Logged In");
								do {
								System.out.println("Press 1 to add book");
								System.out.println("Press 2 to remove book");
								System.out.println("Press 3 to issue book");
								System.out.println("Press 4 to Search the Book by Author");
								System.out.println("Press 5 to Search the Book by Title");
								System.out.println("Press 6 to Get the Book Information");
								System.out.println("Press 7 to update the book");
								System.out.println("Press 8 to log out");

								int choice1 = scanner.nextInt();
								switch (choice1) {
								case 1:
									System.out.println("enter id");
									int addId=scanner.nextInt();
									System.out.println("enter bookname");
									String addName=scanner.next();
									System.out.println("enter authorname");
									String addAuth=scanner.next();
									System.out.println("enter publisher");
									String addPublisher=scanner.next();
									BooksInfo bi=new BooksInfo();
									bi.setBid(addId);
									bi.setBname(addName);
									bi.setBauthor(addAuth);
									bi.setPublishername(addPublisher);
									boolean check2=service1.addBook(bi);
									if(check2) 
										System.out.println("Added Book");
									else
										System.out.println("Book not added");
									break;	
								case 2:
									System.out.println("enter id");
									int removeId=scanner.nextInt();
									boolean check3=service1.removeBook(removeId);
									if(check3)
										System.out.println("Removed Book");
									else
										System.out.println("Book not removed");
									break;

								case 3:
									System.out.println("enter id");
									int issueId=scanner.nextInt();
									boolean check4=service1.issueBook(issueId);
									if(check4)
										System.out.println("Book Issued");
									else
										System.out.println("Book not issued");
									break;

								/*case 4:
									System.out.println("enter id");
									int searchId=scanner.nextInt();
									try {
										BooksInfo bi2=service1.searchBook(searchId);
										System.out.println("Book searched");
									}catch(Exception e) {
										System.out.println("Invalid credentials");	
									}
									break;*/
									
								case 4:
									System.out.println("Search the book by the Author Name:");
									String author = scanner.next();

									BooksInfo bean3 = new BooksInfo();
									bean3.setBauthor(author);
									List<BooksInfo> bookauthor = service1.searchBookByAuthor(author);
									for (BooksInfo bookBean : bookauthor) {

										if (bookBean != null) {
											System.out.println("Book_Id is-->"+bookBean.getBid());
											System.out.println("Book_Name is-->"+bookBean.getBname());
											System.out.println("Book_Author is-->"+bookBean.getBauthor());
											System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
										} else {
											System.out.println("No books are available written by this author");
										}
									}
									break;
								case 5:
									System.out.println("  Search the book by the Book_Title :");
									String btitle = scanner.next();

									BooksInfo bean4 = new BooksInfo();
									bean4.setBauthor(btitle);
									List<BooksInfo> booktitle = service1.searchBookByTitle(btitle);
									for (BooksInfo bookBean : booktitle) {	
										if (bookBean != null) {
											System.out.println("Book_Id is-->"+bookBean.getBid());
											System.out.println("Book_Name is-->"+bookBean.getBname());
											System.out.println("Book_Author is-->"+bookBean.getBauthor());
											System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
										} else {
											System.out.println("No books are available with this title.");
										}
									}
									break;
								
								case 6:
									LinkedList<BooksInfo> info = service1.getBooksInfo();
									for (BooksInfo bookBean : info) {

										if (bookBean != null) {
											System.out.println("Book_Id is-->"+bookBean.getBid());
											System.out.println("Book_Name is-->"+bookBean.getBname());
											System.out.println("Book_Author is-->"+bookBean.getBauthor());
											System.out.println("Book_PublisherName is-->"+bookBean.getPublishername());
										} else {
											System.out.println("Books info is not presernt");
										}
									}
									break;
								case 7:
									System.out.println("Enter the updated id :");
									int bid= scanner.nextInt();

									BooksInfo bean2 = new BooksInfo();
									bean2.setBid(bid);
									boolean updated = service1.updateBook(bid);
									if (updated) {
										System.out.println("Book is updated");
									} else {
										System.out.println("Book is not updated");
									}
									break;
								

								case 8:
									doReg();

								default:
									System.out.println("Invalid Choice");
									break;
								}}while(true);
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
				} while(true);

			case 2:
				UserService service2=Factory.getUserService();
				do {
					System.out.println("Press 1 to User Register");
					System.out.println("Press 2 to User Login");
					System.out.println("3 to main");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						do {
							try {
								System.out.println("Enter ID :");
								regId1 = scanner.nextInt();
								validation.validatedId(regId1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Id should contains only digits");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);




						do {
							try {
								System.out.println("Enter Name :");
								regName1 = scanner.next();
								validation.validatedName(regName1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Name should contains only Alphabates");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);


						do {
							try {
								System.out.println("Enter Mobile :");
								regMobile1 = scanner.next();
								validation.validatedMobile(regMobile1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Mobile Number  should contains only numbers");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);


						do {
							try {
								System.out.println("Enter Email :");
								regEmail1 = scanner.next();
								validation.validatedEmail(regEmail1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Email should be proper ");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						do {
							try {
								System.out.println("Enter Password :");
								regPassword1 = scanner.next();
								validation.validatedPassword(regPassword1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (CommonException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);


						
						System.out.println("enter id");
						int uregId=scanner.nextInt();
						System.out.println("enter name");
						String uregName=scanner.next();
						System.out.println("enter password");
						String uregPassword=scanner.next();
						System.out.println("enter department");
						String uregDepartment=scanner.next();
						System.out.println("enter email");
						String uregEmail=scanner.next();
						System.out.println("enter mobileNo");
						long uregMobileNo=scanner.nextLong();
						UserInfo ui=new UserInfo();
						ui.setUid(uregId);
						ui.setUname(uregName);
						ui.setUpassword(uregPassword);
						ui.setUdepartment(uregDepartment);
						ui.setUemail(uregEmail);
						ui.setUmobileNo(uregMobileNo);
						boolean check=service2.registerUser(ui);
						if(check)
							System.out.println("Registered");
						else
							System.out.println("Already user is registered");
						break;


					case 2:
						System.out.println("enter id");
						int id=scanner.nextInt();
						System.out.println("enter name");
						String name=scanner.next();
						System.out.println("enter password");
						String password=scanner.next();

						try {
							UserInfo authInfo=service2.authUser(name, password);
							System.out.println("Logged In");
							do {
							System.out.println("Press 1 to request book");
							System.out.println("Press 2 to borrow book");
							System.out.println("Press 3 to search book by author");
							System.out.println("Press 4 to search book by title");
							System.out.println("Press 5 to get books info");
							System.out.println("Press 6 to return book");
							System.out.println("Press 7 to main");


							int choice2 = scanner.nextInt();
							switch (choice2) {
							case 1:
								System.out.println("Enter the Book Requested_id :");
								int reqId= scanner.nextInt();
								System.out.println("Enter the Author Name :");
								String reqAuthor = scanner.next();

								BooksInfo beans = new BooksInfo();
								beans.setBid(reqId);
								beans.setBauthor(reqAuthor);
								boolean requested = service2.requestBook(reqId, reqAuthor);
								if (requested != false) {
									System.out.println("Book is Requested");
								} else {
									System.out.println("Book is not Requested");
								}	
								break;
							case 2:
								System.out.println("enter id");
								int borrowId=scanner.nextInt();
								try {
									BooksInfo bi2=service2.borrow(borrowId);
									System.out.println("Book borrowed");
								}catch(Exception e) {
									System.out.println("Invalid credentials");	
								}
								break;
							case 3:
								System.out.println("Search the book by the Author Name :");
								String author = scanner.next();

								BooksInfo bean2 = new BooksInfo();
								bean2.setBauthor(author);
								List<BooksInfo> bookauthor = service2.searchBookByAuthor(author);
								for (BooksInfo bookBean : bookauthor) {

									if (bookBean != null) {
										System.out.println("BookId is--"+bookBean.getBid());
										System.out.println("BookName is--"+bookBean.getBname());
										System.out.println("BookAuthor is--"+bookBean.getBauthor());
										System.out.println("BookPublisherName is--"+bookBean.getPublishername());
									} else {
										System.out.println("No books are available written by this author");
									}
								}
								break;
							case 4:
								System.out.println("Search the book by the Book Title :");
								String btitle = scanner.next();

								BooksInfo bean3 = new BooksInfo();
								bean3.setBauthor(btitle);
								List<BooksInfo> booktitle = service2.searchBookByAuthor(btitle);
								for (BooksInfo bookBean : booktitle) {	
									if (bookBean != null) {
										System.out.println("BookId is--"+bookBean.getBid());
										System.out.println("BookName is--"+bookBean.getBname());
										System.out.println("BookAuthor is--"+bookBean.getBauthor());
										System.out.println("BookPublisherName is--"+bookBean.getPublishername());
									} else {
										System.out.println("No books are available with this title.");
									}
								}
								break;
							case 5:
								LinkedList<BooksInfo> info = service2.getBooksInfo();
								for (BooksInfo bookBean : info) {

									if (bookBean != null) {
										System.out.println(bookBean);
									} else {
										System.out.println("Books info is not presernt");
									}
								}
								break;
							case 6:
								System.out.println("Enter the Book Requested_id :");
								int returnId= scanner.nextInt();

								BooksInfo bean6 = new BooksInfo();
								bean6.setBid(returnId);

								boolean returned = service2.returnBook(returnId);
								if (returned != false) {
									System.out.println("Book is Requested");
								} else {
									System.out.println("Book is not Requested");
								}	
								break;

							case 7:
								doReg();
							default:
								break;
							}}while(true);
						} catch (Exception e) {
							System.out.println("Invalid Credentials");
						}
						break;

					case 3:
						doReg();
					default:
						System.out.println("Invalid Choice");
						break;
					}
				} while(true);
			}
		}while(true);
	}
}
