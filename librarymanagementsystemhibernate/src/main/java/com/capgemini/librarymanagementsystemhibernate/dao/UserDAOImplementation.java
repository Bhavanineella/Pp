package com.capgemini.librarymanagementsystemhibernate.dao;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;
import com.capgemini.librarymanagementsystemhibernate.exception.LibraryException;

public class UserDAOImplementation implements UserDAO{
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	int noOfBooks;


	@Override
	public boolean register(UserInfo user) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public UserInfo login(String email, String password) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql="select u from UserInfo u where u.email=:email and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserInfo bean = query.getSingleResult();
			return bean;
		}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean addBook(BookInfo book) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(book);
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean removeBook(int bookId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class,bookId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean updateBook(BookInfo book) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookInfo record = manager.find(BookInfo.class, book.getBookId());
			record.setBookName(book.getBookName());
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override 
	public boolean issueBook(int bookId, int userId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookId", bookId);
			BookInfo rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select r from RequestInfo r where r.userId=:userId and r.bookId=:bookId";
				TypedQuery<RequestInfo> query1 = manager.createQuery(jpql1,RequestInfo.class);
				query1.setParameter("userId", userId);
				query1.setParameter("bookId", bookId);
				List<RequestInfo> rs1 = query1.getResultList();
				if(!rs1.isEmpty() && rs1 != null) {
					transaction.begin();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					BookIssueInfo issueBook = new BookIssueInfo();
					issueBook.setUserId(userId);
					issueBook.setBookId(bookId);
					issueBook.setIssueDate(java.sql.Date.valueOf(issueDate));
					issueBook.setReturnDate(java.sql.Date.valueOf(returnDate));
					manager.persist(issueBook);
					transaction.commit();
					if(!rs1.isEmpty() && rs1 != null) {
						transaction.begin();
						Query bookName = manager.createQuery("select b.bookName from BookInfo b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						BorrowedBooksInfo borrowedBooks = new BorrowedBooksInfo();
						borrowedBooks.setUserId(userId);
						borrowedBooks.setBookId(bookId);
						borrowedBooks.setBookName(book.get(0).toString());
						manager.persist(borrowedBooks);
						transaction.commit();
						return true;
					}else {
						throw new LibraryException("Book Not issued");
					}
				}else {
					throw new LibraryException("The respective user have not placed any request");
				}
			}else {
				throw new LibraryException("There is no book exist with bookId"+bookId);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public boolean request(int userId, int bookId) {
		int count=0;
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookId", bookId);
			List rs = query.getResultList();
			if(rs != null) {
				String jpql1 = "select b from BorrowedBooksInfo b where b.userId=:userId and b.bookId=:bookId";
				TypedQuery<BorrowedBooksInfo> query1 = (TypedQuery<BorrowedBooksInfo>) manager.createQuery(jpql1,BorrowedBooksInfo.class);
				query1.setParameter("userId", userId);
				query1.setParameter("bookId", bookId);
				List rs1 = query1.getResultList();
				if( rs1.isEmpty() || rs1==null ) {
					String jpql2 = "select b from BookIssueInfo b where b.userId=:userId";
					TypedQuery<BookIssueInfo> query2 = (TypedQuery<BookIssueInfo>) manager.createQuery(jpql2,BookIssueInfo.class);
					query2.setParameter("userId", userId);
					List<BookIssueInfo> rs2 = query2.getResultList();
					for(BookIssueInfo p : rs2) {
						noOfBooks = count++;
					}
					if(noOfBooks<3) {
						Query bookName = manager.createQuery("select b.bookName from BookInfo b where b.bookId=:bookId");
						bookName.setParameter("bookId", bookId);
						List book = bookName.getResultList();
						Query email = manager.createQuery("select u.email from UserInfo u where u.userId=:user_Id");
						email.setParameter("user_Id", userId);
						List userEmail = email.getResultList();
						transaction.begin();
						RequestInfo request = new RequestInfo();
						request.setUserId(userId);
						request.setBookId(bookId);
						request.setEmail(userEmail.get(0).toString());
						request.setBookName(book.get(0).toString());
						manager.persist(request);
						transaction.commit();
						return true;

					}else {
						throw new LibraryException("You have crossed the book limit");
					}
				}else {
					throw new LibraryException("You have already borrowed the requested book");
				}
			}else {
				throw new LibraryException("The book with requested id is not present");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BorrowedBooksInfo> borrowedBook(int userId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BorrowedBooksInfo b where b.userId=:userId";
			TypedQuery<BorrowedBooksInfo> query = manager.createQuery(jpql,BorrowedBooksInfo.class);
			query.setParameter("userId", userId);
			List<BorrowedBooksInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookById(int bookId) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookId", bookId);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.bookName=:bookName";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookName", bookName);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			String jpql = "select b from BookInfo b where b.author=:author";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("author", author);
			List<BookInfo> recordList = query.getResultList();
			return recordList; 
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookInfo b";
		TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
		List<BookInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			String jpql = "select b from BookInfo b where b.bookId=:bookId";
			TypedQuery<BookInfo> query = manager.createQuery(jpql,BookInfo.class);
			query.setParameter("bookId", bookId);
			BookInfo rs = query.getSingleResult();
			if(rs != null) {
				String jpql1 = "select b from BookIssueInfo b where b.bookId=:bookId and b.userId=:userId ";
				TypedQuery<BookIssueInfo> query1 = manager.createQuery(jpql1,BookIssueInfo.class);
				query1.setParameter("bookId", bookId);
				query1.setParameter("userId", userId);
				BookIssueInfo rs1 = query1.getSingleResult();
				if(rs1 != null) {
					Date issueDate = rs1.getIssueDate();
					Calendar cal = Calendar.getInstance();
					Date returnDate = cal.getTime();
					long difference = issueDate.getTime() - returnDate.getTime();
					float daysBetween = (difference / (1000*60*60*24));
					if(daysBetween>7.0) {
						//transaction.begin();
						float fine = daysBetween*5;
						System.out.println("The user has to pay the fine of the respective book of Rs:"+fine);
						if(status=="yes") {
							transaction.begin();
							/*
							String jpql2 = "select b from BookIssueBean b where b.bId=:bId and b.uId=:uId";
							Query query2 = manager.createQuery(jpql2);
							query2.setParameter("bId", bId);
							query2.setParameter("uId", uId);
							BookIssueBean bib = (BookIssueBean) query2.getSingleResult();
							 */
							//int bib_Id = rs1.getId();
							manager.remove(rs1);
							transaction.commit();


							transaction.begin();
							String jpql3 = "select b from BorrowedBooksInfo b  where b.bookId=:bookId and b.userId=:userId";
							Query query3 = manager.createQuery(jpql3);
							query3.setParameter("bId", bookId);
							query3.setParameter("uId", userId);
							BorrowedBooksInfo bbb = (BorrowedBooksInfo) query3.getSingleResult();
							//int bbb_Id = bbb.getId();
							manager.remove(bbb);
							transaction.commit();

							transaction.begin();
							String jpql4 = "select r from RequestInfo r where r.bookId=:bookId and r.userId=:userId";
							Query query4 = manager.createQuery(jpql4);
							query4.setParameter("bookId", bookId);
							query4.setParameter("userId", userId);
							RequestInfo rdb = (RequestInfo) query4.getSingleResult();
							//int rdb_Id = rdb.getId();
							manager.remove(rdb);
							transaction.commit();
							return true;
						}else {
							throw new LibraryException("The User has to pay fine for delaying book return");
						}
					}else {
						transaction.begin();
						/*
						String jpql2 = "select b from BookIssueBean b where b.bId=:bId and b.uId=:uId";
						Query query2 = manager.createQuery(jpql2);
						query2.setParameter("bId", bId);
						query2.setParameter("uId", uId);
						BookIssueBean bib = (BookIssueBean) query2.getSingleResult();
						 */
						//int bib_Id = rs1.getId();
						manager.remove(rs1);
						transaction.commit();


						transaction.begin();
						String jpql3 = "select b from BorrowedBooksInfo b  where b.bookId=:bookId and b.userId=:userId";
						Query query3 = manager.createQuery(jpql3);
						query3.setParameter("bookId", bookId);
						query3.setParameter("userId", userId);
						BorrowedBooksInfo bbb = (BorrowedBooksInfo) query3.getSingleResult();
						//int bbb_Id = bbb.getId();
						manager.remove(bbb);
						transaction.commit();

						transaction.begin();
						String jpql4 = "select r from RequestInfo r where r.bookId=:bookId and r.userId=:userId";
						Query query4 = manager.createQuery(jpql4);
						query4.setParameter("bookId", bookId);
						query4.setParameter("userId", userId);
						RequestInfo rdb = (RequestInfo) query4.getSingleResult();
						//int rdb_Id = rdb.getId();
						manager.remove(rdb);
						transaction.commit();
						return true;
					}

				}else {
					throw new LibraryException("This respective user hasn't borrowed any book");
				}
			}else {
				throw new LibraryException("book doesnt exist");
			}

		}catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		}finally {
			manager.close();
			factory.close();
		}
	}

	@Override
	public List<Integer> bookHistoryDetails(int uId) {
		int count=0;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueInfo b";
		TypedQuery<BookIssueInfo> query = manager.createQuery(jpql,BookIssueInfo.class);
		List<BookIssueInfo> recordList = query.getResultList();
		for(BookIssueInfo p : recordList) {
			noOfBooks = count++;
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(noOfBooks);
		manager.close();
		factory.close();
		return list;
	}

	@Override
	public List<RequestInfo> showRequests() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select r from RequestInfo r";
		TypedQuery<RequestInfo> query = manager.createQuery(jpql,RequestInfo.class);
		List<RequestInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<BookIssueInfo> showIssuedBooks() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select b from BookIssueBean b";
		TypedQuery<BookIssueInfo> query = manager.createQuery(jpql,BookIssueInfo.class);
		List<BookIssueInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public List<UserInfo> showUsers() {
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String jpql = "select u from UsersBean u";
		TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
		List<UserInfo> recordList = query.getResultList();
		manager.close();
		factory.close();
		return recordList;
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		try(FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select u from UsersBean u where u.email=:email and u.role=:role and u.password=:password";
			TypedQuery<UserInfo> query = manager.createQuery(jpql,UserInfo.class);
			query.setParameter("email", email);
			query.setParameter("role", role);
			query.setParameter("password", password);
			UserInfo rs = query.getSingleResult();
			if(rs != null) {
				UserInfo record = manager.find(UserInfo.class,email);
				record.setPassword(newPassword);
				transaction.commit();
				return true;			
			}else {
				throw new LibraryException("User doesnt exist");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			transaction.rollback();
			return false;
		} finally {
			manager.close();
			factory.close();
		}
	}


}
