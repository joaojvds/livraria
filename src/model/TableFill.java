package model;

import dao.SqlSearch;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

public class TableFill {

    public DefaultTableModel authorTable(Connection conn, String collum, String filter) {
        AuthorList authorList = SqlSearch.getAuthorList(conn, collum, filter);
        DefaultTableModel authorTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        authorTableModel.addColumn("id");
        authorTableModel.addColumn("nome");
        authorTableModel.addColumn("sobrenome");

        authorTableModel.setRowCount(0);
        for (Author author : authorList)
            authorTableModel.addRow(new Object[]{
                    author.getId(),
                    author.getFirstName(),
                    author.getLastName()
            });
        return authorTableModel;
    }

    public DefaultTableModel bookTable(Connection conn, String collum, String filter) {
        BookList bookList = SqlSearch.getBookList(conn, collum, filter);
        DefaultTableModel bookTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookTableModel.addColumn("isbn");
        bookTableModel.addColumn("nome");
        bookTableModel.addColumn("preço");
        bookTableModel.addColumn("autores");
        bookTableModel.addColumn("editora");

        bookTableModel.setRowCount(0);
        for (Books book : bookList)
            bookTableModel.addRow(new Object[]{
                    book.getIsbn(),
                    book.getTitle(),
                    book.getPrice(),
                    book.getAuthor(),
                    book.getPublisher()
            });
        return bookTableModel;
    }

    public DefaultTableModel publisherTable(Connection conn, String collum, String filter) {
        PublisherList publisherList = SqlSearch.getPublisherList(conn, collum, filter);
        DefaultTableModel publisherTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        publisherTableModel.addColumn("id");
        publisherTableModel.addColumn("nome");
        publisherTableModel.addColumn("site");

        publisherTableModel.setRowCount(0);
        for (Publisher publisher : publisherList)
            publisherTableModel.addRow(new Object[]{
                    publisher.getId(),
                    publisher.getName(),
                    publisher.getUrl()
            });
        return publisherTableModel;
    }
}