package dao;

import dao.connection.SqlConnection;
import model.Author;
import model.Books;
import model.Publisher;

import java.sql.*;

public class SqlUpdate {
    public void updateAuthor(Connection conn, Author author) throws SQLException {
        String updateAuthor = "UPDATE Authors SET Name = ?, Fname = ? WHERE Author_Id = ?";
        PreparedStatement stmt = conn.prepareStatement(updateAuthor);
        stmt.setString(1, author.getFirstName());
        stmt.setString(2, author.getLastName());
        stmt.setString(3, author.getId());
        stmt.executeUpdate();
        SqlConnection.closeConnect(stmt);
    }

    public void updateBook(Connection conn, Books book) throws SQLException {
        String updateBook = "UPDATE Books SET Title = ?, Publisher_Id = " +
                "(SELECT Publisher_Id FROM Publishers WHERE Name = ?) ,Price = ? WHERE ISBN = ?";

        String insertBookAuthor = "INSERT INTO BooksAuthors VALUES(?, ?, ?)";
        String deleteBookAuthor = "DELETE FROM BooksAuthors  WHERE ISBN = ? AND Seq_No = ?";
        String sizeBookAuthor = "SELECT * FROM BooksAuthors WHERE ISBN = ?";


        PreparedStatement stmt = conn.prepareStatement(updateBook);
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getPublisher().getName());
        stmt.setDouble(3, book.getPrice());
        stmt.setString(4, book.getIsbn());
        stmt.executeUpdate();

        stmt = conn.prepareStatement(sizeBookAuthor);
        stmt.setString(1, book.getIsbn());
        ResultSet rs = stmt.executeQuery();

        int rsSize = 0;
        while (rs.next()) {
            rsSize++;
        }

        stmt = conn.prepareStatement(deleteBookAuthor);
        for (int i = rsSize; i > 0; i--) {
            stmt.setString(1, book.getIsbn());
            stmt.setInt(2, i);
            stmt.executeUpdate();
        }

        stmt = conn.prepareStatement(insertBookAuthor);
        for (int i = 1; i <= book.getAuthor().size(); i++) {
            System.out.println(book.getIsbn());
            stmt.setString(1, book.getIsbn());
            System.out.println(book.getAuthor().get(i-1).getId());
            stmt.setString(2, book.getAuthor().get(i-1).getId());
            stmt.setInt(3, i);
            stmt.executeUpdate();
        }

        SqlConnection.closeConnect(stmt, rs);
    }

    public void updatePublisher(Connection conn, Publisher publisher) throws SQLException {
        String updatePublisher = "UPDATE Publishers SET Name = ?, URL = ? WHERE Publisher_Id = ?";
        PreparedStatement stmt = conn.prepareStatement(updatePublisher);
        stmt.setString(1, publisher.getName());
        stmt.setString(2, publisher.getUrl());
        stmt.setString(3, publisher.getId());
        stmt.executeUpdate();
        SqlConnection.closeConnect(stmt);
    }
}
