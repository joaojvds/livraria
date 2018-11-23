package controller;

import UI.View;
import dao.*;
import model.*;
import model.TableFill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    private Connection conn;

    public Controller() {
    }

    public Controller(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn(String user, String password) throws ClassNotFoundException{
        ConnectionFactory connection = new ConnectionFactory();
        connection.setUser(user);
        connection.setPassword(password);
        return connection.getConnection();
    }

    public DefaultTableModel fillAuthorTable() {
        return new TableFill().authorTable(this.conn, "", "");
    }

    public DefaultTableModel fillBookTable() {
        return new TableFill().bookTable(this.conn, "", "");
    }

    public DefaultTableModel fillPublisherTable() {
        return new TableFill().publisherTable(this.conn, "", "");
    }

    public DefaultTableModel searchAuthorTable(String collum, String filter) {
        return new TableFill().authorTable(this.conn, collum, "LOWER('%"+filter+"%')");
    }

    public DefaultTableModel searchBookTable(String collum, String filter) {
        return new TableFill().bookTable(this.conn, collum, "LOWER('%"+filter+"%')");
    }

    public DefaultTableModel searchPublisherTable(String collum, String filter) {
        return new TableFill().publisherTable(this.conn, collum, "LOWER('%"+filter+"%')");
    }

    public void insertAuthor(String fName, String lName) throws SQLException {
        SqlInsert sql = new SqlInsert();
        sql.insertAuthor(this.conn, new Author(fName, lName));
    }

    public void updateAuthor(String id, String fName, String lName) throws SQLException {
        SqlUpdate sql = new SqlUpdate();
        sql.updateAuthor(this.conn, new Author(id, fName, lName));
    }

    public void deleteAuthor(String id) throws SQLException {
        SqlDelete sql = new SqlDelete();
        sql.deleteAuthor(this.conn, id);
    }

    public void insertBook(int listIndex, Publisher publisher, String title,
                           String isbn, String price, ArrayList<JComboBox> author) throws SQLException {
        Books book = new Books(isbn, title, Double.parseDouble(price));
        book.setPublisher(publisher);
        for (JComboBox authors : author) {
            book.addAuthor((Author) authors.getSelectedItem());
        }
        new SqlInsert().insertBook(this.conn, listIndex, book);
    }

    public void updateBook(String isbn, String title, String price,
                           Publisher publisher, ArrayList<JComboBox> author) throws SQLException {

        SqlUpdate sql = new SqlUpdate();
        Books book = new Books(isbn, title, Double.parseDouble(price));
        book.setPublisher(publisher);
        for (JComboBox authors : author) {
            book.addAuthor((Author) authors.getSelectedItem());
        }
        sql.updateBook(this.conn, book);
    }

    public void deleteBook(String id) throws SQLException {
        SqlDelete sql = new SqlDelete();
        sql.deleteBook(this.conn, id);
    }

    public void insertPublisher(String name, String url) {
        SqlInsert sql = new SqlInsert();
        sql.insertPublisher(this.conn, new Publisher(name, url));
    }

    public void updatePublisher(String id, String name, String url) throws SQLException {
        SqlUpdate sql = new SqlUpdate();
        sql.updatePublisher(this.conn, new Publisher(id, name, url));
    }

    public void deletePublisher(String id) throws SQLException {
        SqlDelete sql = new SqlDelete();
        sql.deletePublisher(this.conn, id);
    }

    public JComboBox fillAuthorComboBox() throws SQLException {
        JComboBox author = new JComboBox();
        author.setModel(new DefaultComboBoxModel((new SqlSearch()).fillAuthorComboBox(this.conn).toArray()));
        return author;
    }

    public JComboBox fillPublisherComboBox() throws SQLException {
        return (new SqlSearch()).fillPublisherComboBox(this.conn);
    }

    public void beginLoginFrame() {
        View view = new View();
        view.beginLoginFrame();
    }

    public void beginMainFrame(int index) {
        View view = new View(this.conn);
        view.beginMainFrame(index);
    }

    public void beginAuthorAddFrame() {
        View view = new View(this.conn);
        view.beginAuthorAddFrame();
    }

    public void beginAuthorEditFrame(Author author) {
        View view = new View(this.conn);
        view.beginAuthorEditFrame(author);
    }

    public void beginAuthorDropFrame(Author author) {
        View view = new View(this.conn);
        view.beginAuthorDropFrame(author);
    }

    public void beginBookAddFrame() throws SQLException {
        View view = new View(this.conn);
        view.beginBookAddFrame();
    }

    public void beginBookEditFrame(Books book) {
        View view = new View(this.conn);
        view.beginBookEditFrame(book);
    }

    public void beginBookDropFrame(Books book) {
        View view = new View(this.conn);
        view.beginBookDropFrame(book);
    }

    public void beginPublisherAddFrame() {
        View view = new View(this.conn);
        view.beginPublisherAddFrame();
    }

    public void beginPublisherEditFrame(Publisher publisher) {
        View view = new View(this.conn);
        view.beginPublisherEditFrame(publisher);
    }

    public void beginPublisherDropFrame(Publisher publisher) {
        View view = new View(this.conn);
        view.beginPublisherDropFrame(publisher);
    }
}