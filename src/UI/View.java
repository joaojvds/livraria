package UI;

import UI.authorView.*;
import UI.bookView.*;
import UI.publisherView.*;
import model.Author;
import model.Books;
import model.Publisher;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class View {

    private Connection conn;

    private LoginFrame loginFrame;

    public View() {
        loginFrame = new LoginFrame();
    }

    public View(Connection conn) {
        this.conn = conn;
    }

    public void beginLoginFrame() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                loginFrame.beginLogin();
            }
        });
    }

    public void beginMainFrame(int index) {
        MainFrame mainFrame = new MainFrame();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainFrame.beginMainFrame(conn, index);
            }
        });
    }

    public void beginAuthorAddFrame() {
        AuthorAddFrame authorAddFrame = new AuthorAddFrame();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                authorAddFrame.beginAuthorAddFrame(conn);
            }
        });
    }

    public void beginAuthorEditFrame(Author author) {
        AuthorEditFrame authorEditFrame = new AuthorEditFrame(author);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                authorEditFrame.beginAuthorEditFrame(conn);
            }
        });
    }

    public void beginAuthorDropFrame(Author author) {
        AuthorDropFrame authorDropFrame = new AuthorDropFrame(author);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                authorDropFrame.beginAuthorDropFrame(conn);
            }
        });
    }

    public void beginBookAddFrame() throws SQLException {
        BookAddFrame bookAddFrame = new BookAddFrame();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    bookAddFrame.beginBookAddFrame(conn);
                } catch (Exception s) {

                }
            }
        });
    }

    public void beginBookEditFrame(Books book) {
        BookEditFrame bookEditFrame = new BookEditFrame(book,conn);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                bookEditFrame.beginBookEditFrame(conn);
            }
        });
    }

    public void beginBookDropFrame(Books book) {
        BookDropFrame bookDropFrame = new BookDropFrame(book);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                bookDropFrame.beginBookDropFrame(conn);
            }
        });
    }

    public void beginPublisherAddFrame() {
        PublisherAddFrame publisherAddFrame = new PublisherAddFrame();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                publisherAddFrame.beginPublisherAddFrame(conn);
            }
        });
    }

    public void beginPublisherEditFrame(Publisher publisher) {
        PublisherEditFrame publisherEditFrame = new PublisherEditFrame(publisher);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                publisherEditFrame.beginPublisherEditFrame(conn);
            }
        });
    }

    public void beginPublisherDropFrame(Publisher publisher) {
        PublisherDropFrame publisherDropFrame = new PublisherDropFrame(publisher);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                publisherDropFrame.beginPublisherDropFrame(conn);
            }
        });
    }
}