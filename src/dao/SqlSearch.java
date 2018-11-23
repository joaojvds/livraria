package dao;

import java.sql.*;
import java.util.ArrayList;

import dao.connection.SqlConnection;
import model.*;

import javax.swing.*;

public class SqlSearch {

    public static BookList getBookList(Connection conn, String collum, String filter) {
        BookList bookList = new BookList();
        try {
            String query = "SELECT Books.Title ," +
                    " Books.ISBN ," +
                    " Books.price ," +
                    " Publishers.Publisher_Id ," +
                    " Publishers.Name AS pname," +
                    " Publishers.URL," +
                    " Authors.Author_Id," +
                    " Authors.Name AS aname," +
                    " Authors.Fname " +
                    "FROM Books, Publishers, Authors, BooksAuthors " +
                    "WHERE Books.Publisher_Id = Publishers.Publisher_Id " +
                    "AND Books.ISBN = BooksAuthors.ISBN " +
                    "AND Authors.Author_Id = BooksAuthors.Author_Id " +
                    collum+" "+filter+
                    "ORDER BY Books.ISBN ";
            PreparedStatement stmt = conn.prepareStatement(query);
            //stmt.setString(1, collum);
            //stmt.setString(2, filter);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if ((bookList.size() > 0) && bookList.getLast().getIsbn().equals(rs.getString("isbn"))) {
                    bookList.getLast().addAuthor(
                            new Author(
                                    rs.getString("Author_Id"),
                                    rs.getString("Fname"),
                                    rs.getString("aname")));
                } else {
                    Books book = new Books(
                            rs.getString("isbn"),
                            rs.getString("title"),
                            rs.getDouble("price"));
                    book.setPublisher(
                            new Publisher(
                                    rs.getString("Publisher_Id"),
                                    rs.getString("pname"),
                                    rs.getString("url")));
                    book.addAuthor(
                            new Author(
                                    rs.getString("Author_Id"),
                                    rs.getString("Fname"),
                                    rs.getString("aname")));
                    bookList.addBook(book);
                }
            }
            SqlConnection.closeConnect(stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static AuthorList getAuthorList(Connection conn, String collum, String filter) {
        AuthorList authorList = new AuthorList();
        try {
            String query = "SELECT DISTINCT Authors.Author_Id , Authors.Name , Authors.Fname \n" +
                    "FROM Authors \n" +
                    collum+" "+filter+
                    " ORDER BY Authors.Author_Id ;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Author author = new Author(
                        rs.getString("Author_Id"),
                        rs.getString("Name"),
                        rs.getString("Fname"));
                authorList.addAuthor(author);
            }
            SqlConnection.closeConnect(stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public static PublisherList getPublisherList(Connection conn, String collum, String filter) {
        PublisherList publisherList = new PublisherList();
        try {
            String query = "SELECT Publisher_Id , Name , URL  FROM Publishers "+collum+" "+filter;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Publisher publisher = new Publisher(
                        rs.getString("Publisher_Id"),
                        rs.getString("Name"),
                        rs.getString("URL"));
                publisherList.addPublisher(publisher);
            }
            SqlConnection.closeConnect(stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publisherList;
    }

    public ArrayList fillAuthorComboBox(Connection conn) throws SQLException {
        Statement stm = conn.createStatement();
        ArrayList author = new ArrayList<Author>();
        ResultSet rs = stm.executeQuery("SELECT * FROM Authors");
        while (rs.next()) {
            author.add(new Author(
                    rs.getString("Author_Id"),
                    rs.getString("Name"),
                    rs.getString("Fname")));
        }

        stm.close();
        rs.close();
        return author;
    }

    public JComboBox fillPublisherComboBox(Connection conn) throws SQLException {
        Statement stm = conn.createStatement();
        JComboBox comboBox = new JComboBox();
        ResultSet rs = stm.executeQuery("SELECT * FROM Publishers");
        while (rs.next()) {
            comboBox.addItem(new Publisher(
                    rs.getString("Publisher_Id"),
                    rs.getString("Name"),
                    rs.getString("URL")));
        }
        stm.close();
        rs.close();
        return comboBox;
    }
}