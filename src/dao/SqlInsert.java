package dao;

import dao.connection.SqlConnection;
import model.Author;
import model.Books;
import model.Publisher;

import javax.swing.*;
import java.sql.*;

public class SqlInsert {
    public void insertAuthor(Connection conn, Author authors) throws SQLException {
        try {
            String query = "INSERT INTO Authors (Name, Fname) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, authors.getFirstName());
            stmt.setString(2, authors.getLastName());
            stmt.executeUpdate();
            SqlConnection.closeConnect(stmt);
        } catch (StringIndexOutOfBoundsException st) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar na tabela \n" +
                    st.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertBook(Connection conn, int listIndex, Books book) throws SQLException {
        try {
            String insertBook = "INSERT INTO Books VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertBook);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setString(3, book.getPublisher().getId());
            stmt.setDouble(4, book.getPrice());
            stmt.executeUpdate();

            String insertAuthors = "INSERT INTO BooksAuthors VALUES ( ? , ?, ?)";
            stmt = conn.prepareStatement(insertAuthors);

            for (int i = 0; i <= listIndex;i++){
                stmt.setString(1, book.getIsbn());
                stmt.setString(2, book.getAuthor().get(i).getId());
                stmt.setInt(3, i+1);
                stmt.executeUpdate();
            }
            SqlConnection.closeConnect(stmt);
        } catch (StringIndexOutOfBoundsException st) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar na tabela \n" +
                    st.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertPublisher(Connection conn, Publisher publisher) {
        try {
            String query = "INSERT INTO Publishers (Name, URL) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getUrl());
            stmt.executeUpdate();
            SqlConnection.closeConnect(stmt);
        } catch (Exception s) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar na tabela \n" +
                    s.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}
