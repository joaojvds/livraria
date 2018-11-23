package dao;

import dao.connection.SqlConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDelete {
    public void deleteAuthor(Connection conn, String id) throws SQLException {
        String deleteAuthor = "DELETE FROM Authors WHERE Author_Id = ?";
        PreparedStatement stmt = conn.prepareStatement(deleteAuthor);
        stmt.setString(1, id);
        stmt.executeUpdate();
        SqlConnection.closeConnect(stmt);
    }

    public void deleteBook(Connection conn, String id) throws SQLException {
        String deleteAuthor = "DELETE FROM BooksAuthors WHERE ISBN = ?";
        String deleteBook = "DELETE FROM Books WHERE ISBN = ?";
        PreparedStatement stmt = conn.prepareStatement(deleteAuthor);
        stmt.setString(1, id);
        stmt.executeUpdate();
        stmt = conn.prepareStatement(deleteBook);
        stmt.setString(1, id);
        stmt.executeUpdate();
        SqlConnection.closeConnect(stmt);
    }

    public void deletePublisher(Connection conn, String id) throws SQLException {
        String deletePublisher = "DELETE FROM Publishers WHERE Publisher_Id = ?";
        PreparedStatement stmt = conn.prepareStatement(deletePublisher);
        stmt.setString(1, id);
        stmt.executeUpdate();
        SqlConnection.closeConnect(stmt);
    }
}
