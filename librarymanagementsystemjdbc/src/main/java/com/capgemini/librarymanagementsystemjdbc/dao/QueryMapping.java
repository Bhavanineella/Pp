package com.capgemini.librarymanagementsystemjdbc.dao;

public interface QueryMapping {

	String registerQuery="insert into users values(?,?,?,?,?,?,?)";
	String loginQuery="select * from users where email=? and password=?";
	String addBookQuery="insert into bookinfo values(?,?,?,?,?)";
	String removeBookQuery="delete from bookinfo where bookid=?";
	String updateBookQuery="update bookinfo set bookname=? where bookid=?";

	String issueBookQuery1="select * from bookinfo where bookid=?";
	String issueBookQuery2="select * from request_details where userid=? and bookid=? and email=(select email from users where userid=?) ";
	String issueBookQuery3="insert into book_issue_details values(?,?,?,?)";
	String issueBookQuery4="insert into borrowed_books values(?,?,(select email from users where userid=?))";

	String requestBookQuery1="select * from bookinfo where bookid=?";
	String requestBookQuery2="select count(*) as userid from borrowed_books where userid=? and bookid=? and email=(select email from users where userid=?)";
	String requestBookQuery3="select count(*) as userid from book_issue_details where userid=?";
	String requestBookQuery4="insert into request_details values(?,(select concat(firstname,'_',lastname) from users where userid=?),?,(select bookname from bookinfo where bookid=?),(select email from users where userid=?))";

	String searchBookByTitle="select * from bookinfo where bookname=?";
	String searchBookByAuthor="select * from bookinfo where author=?";
	String getBooksInfoQuery="select * from bookinfo";

	String returnBookQuery1="select * from bookinfo where bookid=?";
	String returnBookQuery2="select * from book_issue_details where bookid=? and userid=?";
	String returnBookQuery3="delete from book_issue_details where bookid=? and userid=?";
	String returnBookQuery4="delete from borrowed_books where bookid=? and userid=?";
	String returnBookQuery5="delete from request_details where bookid=? and userid=?";

	String bookHistoryDetailsQuery="select count(*) as userid from book_issue_details where userid=?";
	String borrowedBookQuery="select * from borrowed_books where userid=?";

	String searchBookByIdQuery="select * from bookinfo where bookid=?";
	String showRequestsQuery="select * from request_details";
	String showIssuedBooksQuery="select * from book_issue_details";
	String showUsersQuery="select * from users";

	String updatePasswordQuery1="select * from users where email=? and role=?";
	String updatePasswordQuery2="update users set password=? where email=? and password=?";

}

