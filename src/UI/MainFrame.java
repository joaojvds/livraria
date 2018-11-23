package UI;

import controller.Controller;
import dao.SqlSearch;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.sql.*;

public class MainFrame {

	//variaveis global
	private JFrame mainFrame;
	private JTable authorTable, bookTable, publisherTable;
	private JButton authorEdit, bookEdit, publisherEdit;
	private JButton authorDrop, bookDrop, publisherDrop;
	private JComboBox<SqlFilter> authorSearchBox;
    private JComboBox<SqlFilter> bookSearchBox;
    private JComboBox<SqlFilter> publisherSearchBox;
    private JTextField authorSearchField, bookSearchField, publisherSearchField;
	private Author author;
	private Books book;
	private Publisher publisher;

	public void beginMainFrame(Connection conn, int listIndex) {

		//declaração dos objetos
		mainFrame = new JFrame();
		JTabbedPane bookStoreTabbed = new JTabbedPane();
		JPanel authorPanel = new JPanel();
		JScrollPane authorScrollPane = new JScrollPane();
		JButton authorAdd = new JButton("Adicionar");
		authorEdit = new JButton("Editar");
		authorDrop = new JButton("excluir");
		JLabel authorLabel = new JLabel("Autores");
		JPanel bookPanel = new JPanel();
		JScrollPane bookScrollPane = new JScrollPane();
		JButton bookAdd = new JButton("Adicionar");
		bookEdit = new JButton("Editar");
		bookDrop = new JButton("excluir");
		JLabel bookLabel = new JLabel("Livros");
		JPanel publisherPanel = new JPanel();
		JScrollPane publisherScrollPane = new JScrollPane();
		JButton publisherAdd = new JButton("Adicionar");
		publisherEdit = new JButton("Editar");
		publisherDrop = new JButton("excluir");
		JLabel publisherLabel = new JLabel("Editoras");

		authorSearchBox = new JComboBox<>();
		authorSearchField = new JTextField();

		bookSearchBox = new JComboBox<>();
		bookSearchField = new JTextField();

		publisherSearchBox = new JComboBox<>();
		publisherSearchField = new JTextField();


		authorEdit.setEnabled(false);
		authorDrop.setEnabled(false);
		bookEdit.setEnabled(false);
		bookDrop.setEnabled(false);
		publisherEdit.setEnabled(false);
		publisherDrop.setEnabled(false);

		authorTable = new JTable(new Controller(conn).fillAuthorTable());
		authorTable.addMouseListener(new AuthorTableBehavior(conn, this));
		bookTable = new JTable(new Controller(conn).fillBookTable());
		bookTable.addMouseListener(new BookTableBehavior(conn, this));
		publisherTable = new JTable(new Controller(conn).fillPublisherTable());
		publisherTable.addMouseListener(new PublisherTableBehavior(conn, this));

		//Frame principal
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setTitle("Livraria");
		mainFrame.setMinimumSize(new Dimension(750, 550));
		mainFrame.setMaximizedBounds(null);
		mainFrame.setResizable(false);
		Container mainFrameContentPane = mainFrame.getContentPane();

		//cria uma aba e adiciona o painel authorPanel a ela
		bookStoreTabbed.addTab("Autores", authorPanel);

			//adiciona a tabela e um scroll a ela
			authorScrollPane.setViewportView(authorTable);

			//adiciona fonte aos botões
			authorAdd.setFont(authorAdd.getFont().deriveFont(authorAdd.getFont().getSize() + 1f));
			authorEdit.setFont(authorEdit.getFont().deriveFont(authorEdit.getFont().getSize() + 1f));
			authorDrop.setFont(authorDrop.getFont().deriveFont(authorDrop.getFont().getSize() + 1f));

			//adiciona fonte ao titulo
			authorLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
			authorLabel.setHorizontalAlignment(SwingConstants.CENTER);

			authorSearchBox.addItem(new SqlFilter("Author_Id", "ID",1));
            authorSearchBox.addItem(new SqlFilter("Name", "Nome",1));
            authorSearchBox.addItem(new SqlFilter("Fname", "Sobrenome",1));

            bookSearchBox.addItem(new SqlFilter("Books.ISBN", "ISBN",2));
            bookSearchBox.addItem(new SqlFilter("Books.Title", "Titulo",2));
            bookSearchBox.addItem(new SqlFilter("Authors.Name", "Autor",2));
            bookSearchBox.addItem(new SqlFilter("Publishers.Name", "Editora",2));

            publisherSearchBox.addItem(new SqlFilter("Publisher_Id", "ID",1));
            publisherSearchBox.addItem(new SqlFilter("Name", "Nome",1));
            publisherSearchBox.addItem(new SqlFilter("URL", "Site",1));

			GroupLayout authorPanelLayout = new GroupLayout(authorPanel); // cria um layout para authorPanel
			authorPanel.setLayout(authorPanelLayout);

			authorPanelLayout.setHorizontalGroup(
				authorPanelLayout.createParallelGroup()
					.addGroup(authorPanelLayout.createSequentialGroup()
						.addGap(265, 265, 265)
						.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(363, Short.MAX_VALUE))
					.addGroup(authorPanelLayout.createSequentialGroup()
						.addGap(42, 42, 42)
						.addGroup(authorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(authorPanelLayout.createSequentialGroup()
								.addComponent(authorSearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(authorSearchField))
							.addComponent(authorScrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(authorPanelLayout.createParallelGroup()
							.addComponent(authorAdd, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addComponent(authorDrop, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addComponent(authorEdit, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
						.addGap(28, 28, 28))
			);
			authorPanelLayout.setVerticalGroup(
				authorPanelLayout.createParallelGroup()
					.addGroup(authorPanelLayout.createSequentialGroup()
						.addGap(65, 65, 65)
						.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(authorPanelLayout.createParallelGroup()
							.addComponent(authorScrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
							.addGroup(authorPanelLayout.createSequentialGroup()
								.addGap(38, 38, 38)
								.addComponent(authorAdd)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(authorEdit)
								.addGap(13, 13, 13)
								.addComponent(authorDrop)))
						.addGap(19, 19, 19)
						.addGroup(authorPanelLayout.createParallelGroup()
							.addComponent(authorSearchBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorSearchField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(56, 56, 56))
			);

			//Action Listener dos botões
			authorAdd.addActionListener(new ButtonAddAuthor(conn, this));
			authorEdit.addActionListener(new ButtonEditAuthor(conn, this));
			authorDrop.addActionListener(new ButtonDropAuthor(conn, this));
            authorSearchField.addKeyListener(new SearchAuthor(conn, this));

		// A patir daqui o codigo se repete, só alterando os nomes das variaveis


		bookStoreTabbed.addTab("Livros", bookPanel);

			bookScrollPane.setViewportView(bookTable);

			bookAdd.setFont(bookAdd.getFont().deriveFont(bookAdd.getFont().getSize() + 1f));
			bookEdit.setFont(bookEdit.getFont().deriveFont(bookEdit.getFont().getSize() + 1f));
			bookDrop.setFont(bookDrop.getFont().deriveFont(bookDrop.getFont().getSize() + 1f));

			bookLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
			bookLabel.setHorizontalAlignment(SwingConstants.CENTER);

			GroupLayout bookPanelLayout = new GroupLayout(bookPanel);
			bookPanel.setLayout(bookPanelLayout);

			bookPanelLayout.setHorizontalGroup(
				bookPanelLayout.createParallelGroup()
					.addGroup(bookPanelLayout.createSequentialGroup()
						.addGap(265, 265, 265)
						.addComponent(bookLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(363, Short.MAX_VALUE))
					.addGroup(bookPanelLayout.createSequentialGroup()
						.addGap(42, 42, 42)
						.addGroup(bookPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(bookPanelLayout.createSequentialGroup()
								.addComponent(bookSearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(bookSearchField))
							.addComponent(bookScrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(bookPanelLayout.createParallelGroup()
							.addComponent(bookAdd, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addComponent(bookDrop, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addComponent(bookEdit, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
						.addGap(28, 28, 28))
			);
			bookPanelLayout.setVerticalGroup(
				bookPanelLayout.createParallelGroup()
					.addGroup(bookPanelLayout.createSequentialGroup()
						.addGap(65, 65, 65)
						.addComponent(bookLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(bookPanelLayout.createParallelGroup()
							.addComponent(bookScrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
							.addGroup(bookPanelLayout.createSequentialGroup()
								.addGap(38, 38, 38)
								.addComponent(bookAdd)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(bookEdit)
								.addGap(13, 13, 13)
								.addComponent(bookDrop)))
						.addGap(19, 19, 19)
						.addGroup(bookPanelLayout.createParallelGroup()
							.addComponent(bookSearchBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookSearchField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(56, 56, 56))
			);

			bookAdd.addActionListener(new ButtonAddBook(conn, this));
			bookEdit.addActionListener(new ButtonEditBook(conn, this));
			bookDrop.addActionListener(new ButtonDropBook(conn, this));
            bookSearchField.addKeyListener(new SearchBook(conn, this));

		bookStoreTabbed.addTab("Editoras", publisherPanel);

			publisherScrollPane.setViewportView(publisherTable);

			publisherAdd.setFont(publisherAdd.getFont().deriveFont(publisherAdd.getFont().getSize() + 1f));
			publisherEdit.setFont(publisherEdit.getFont().deriveFont(publisherEdit.getFont().getSize() + 1f));
			publisherDrop.setFont(publisherDrop.getFont().deriveFont(publisherDrop.getFont().getSize() + 1f));

			publisherLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
			publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);

			GroupLayout publisherPanelLayout = new GroupLayout(publisherPanel);
			publisherPanel.setLayout(publisherPanelLayout);

			publisherPanelLayout.setHorizontalGroup(
				publisherPanelLayout.createParallelGroup()
					.addGroup(publisherPanelLayout.createSequentialGroup()
						.addGap(265, 265, 265)
						.addComponent(publisherLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(363, Short.MAX_VALUE))
					.addGroup(publisherPanelLayout.createSequentialGroup()
						.addGap(42, 42, 42)
						.addGroup(publisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(publisherPanelLayout.createSequentialGroup()
								.addComponent(publisherSearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(publisherSearchField))
							.addComponent(publisherScrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(publisherPanelLayout.createParallelGroup()
							.addComponent(publisherAdd, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addComponent(publisherDrop, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addComponent(publisherEdit, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
						.addGap(28, 28, 28))
			);
			publisherPanelLayout.setVerticalGroup(
				publisherPanelLayout.createParallelGroup()
					.addGroup(publisherPanelLayout.createSequentialGroup()
						.addGap(65, 65, 65)
						.addComponent(publisherLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(publisherPanelLayout.createParallelGroup()
							.addComponent(publisherScrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
							.addGroup(publisherPanelLayout.createSequentialGroup()
								.addGap(38, 38, 38)
								.addComponent(publisherAdd)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(publisherEdit)
								.addGap(13, 13, 13)
								.addComponent(publisherDrop)))
						.addGap(19, 19, 19)
						.addGroup(publisherPanelLayout.createParallelGroup()
							.addComponent(publisherSearchBox, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherSearchField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(56, 56, 56))
			);

			publisherAdd.addActionListener(new ButtonAddPublisher(conn, this));
			publisherEdit.addActionListener(new ButtonEditPublisher(conn, this));
			publisherDrop.addActionListener(new ButtonDropPublisher(conn, this));
            publisherSearchField.addKeyListener(new SearchPublisher(conn, this));

		bookStoreTabbed.setSelectedIndex(listIndex);

		GroupLayout mainFrameContentPaneLayout = new GroupLayout(mainFrameContentPane); // cria um layout para o container principal
		mainFrameContentPane.setLayout(mainFrameContentPaneLayout);
		mainFrameContentPaneLayout.setHorizontalGroup(
			mainFrameContentPaneLayout.createParallelGroup()
				.addComponent(bookStoreTabbed, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		mainFrameContentPaneLayout.setVerticalGroup(
			mainFrameContentPaneLayout.createParallelGroup()
				.addComponent(bookStoreTabbed, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);

		mainFrame.setVisible(true); // liga a janela
		mainFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela
		mainFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

	void enableAuthorButton() {
		authorEdit.setEnabled(true);
		authorDrop.setEnabled(true);
	}

	void enableBookButton() {
		bookEdit.setEnabled(true);
		bookDrop.setEnabled(true);
	}

	void enablePublisherButton() {
		publisherEdit.setEnabled(true);
		publisherDrop.setEnabled(true);
	}

    JComboBox<SqlFilter> getAuthorSearchBox() {
        return authorSearchBox;
    }

    JComboBox<SqlFilter> getBookSearchBox() {
        return bookSearchBox;
    }

    JComboBox<SqlFilter> getPublisherSearchBox() {
        return publisherSearchBox;
    }

    JTextField getAuthorSearchField() {
        return authorSearchField;
    }

    JTextField getBookSearchField() {
        return bookSearchField;
    }

    JTextField getPublisherSearchField() {
        return publisherSearchField;
    }

    public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	Books getBook() {
		return book;
	}

	void setBook(Books book) {
		this.book = book;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	JTable getAuthorTable() {
		return authorTable;
	}

    JTable getBookTable() {
		return bookTable;
	}

    JTable getPublisherTable() {
		return publisherTable;
	}

    void closeFrame() {
		this.mainFrame.dispose();
	}
}

class SqlFilter {

    private String collum, collumLabel;

    SqlFilter(String collum, String collumLabel,int i) {
        if(i == 1) {
            this.collum = "WHERE " + collum + " LIKE";
            this.collumLabel = collumLabel;
        } else {
            this.collum = "AND " + collum + " LIKE";
            this.collumLabel = collumLabel;
        }
    }

    String getCollum() {
        return this.collum;
    }

    @Override
    public String toString() {
        return this.collumLabel;
    }
}

class AuthorTableBehavior extends MouseAdapter {

	private Connection conn;
	private MainFrame mainFrame;

	AuthorTableBehavior(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1) {
			AuthorList authorList = SqlSearch.getAuthorList(conn, "", "");
			mainFrame.setAuthor(authorList.get(mainFrame.getAuthorTable().getSelectedRow()));
			mainFrame.enableAuthorButton();
			System.out.println("livro :" + mainFrame.getAuthorTable().getSelectedRow());
			System.out.println("author :" + mainFrame.getAuthor().getId());
		}
	}
}

class BookTableBehavior extends MouseAdapter {

	private Connection conn;
	private MainFrame mainFrame;

	BookTableBehavior(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1) {
			BookList bookList = SqlSearch.getBookList(conn, "", "");
			mainFrame.setBook(bookList.get(mainFrame.getBookTable().getSelectedRow()));
			mainFrame.enableBookButton();
			System.out.println("livro :" + mainFrame.getBookTable().getSelectedRow());
			System.out.println("author :" + mainFrame.getBook().getIsbn());
		}
	}
}

class PublisherTableBehavior extends MouseAdapter {

	private Connection conn;
	private MainFrame mainFrame;

	PublisherTableBehavior(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1) {
			PublisherList publisherList = SqlSearch.getPublisherList(conn, "", "");
			mainFrame.setPublisher(publisherList.get(mainFrame.getPublisherTable().getSelectedRow()));
			mainFrame.enablePublisherButton();
			System.out.println("livro :" + mainFrame.getPublisherTable().getSelectedRow());
			System.out.println("author :" + mainFrame.getPublisher().getId());
		}
	}
}

class SearchAuthor extends KeyAdapter {

    private Connection conn;
    private MainFrame mainFrame;

    SearchAuthor(Connection conn, MainFrame mainFrame) {
        this.conn = conn;
        this.mainFrame = mainFrame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        SqlFilter collun = (SqlFilter) mainFrame.getAuthorSearchBox().getSelectedItem();
        assert collun != null;
        mainFrame.getAuthorTable().setModel(new Controller(conn).searchAuthorTable(
                collun.getCollum(), mainFrame.getAuthorSearchField().getText()));
    }

}

class SearchBook extends KeyAdapter {

    private Connection conn;
    private MainFrame mainFrame;

    SearchBook(Connection conn, MainFrame mainFrame) {
        this.conn = conn;
        this.mainFrame = mainFrame;    }

    @Override
    public void keyTyped(KeyEvent e) {
        SqlFilter collun = (SqlFilter) mainFrame.getBookSearchBox().getSelectedItem();
        assert collun != null;
        mainFrame.getBookTable().setModel(new Controller(conn).searchBookTable(
                collun.getCollum(), mainFrame.getBookSearchField().getText()));
    }

}

class SearchPublisher extends KeyAdapter {

    private Connection conn;
    private MainFrame mainFrame;

    SearchPublisher(Connection conn, MainFrame mainFrame) {
        this.conn = conn;
        this.mainFrame = mainFrame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        SqlFilter collun = (SqlFilter) mainFrame.getPublisherSearchBox().getSelectedItem();
        assert collun != null;
        mainFrame.getPublisherTable().setModel(new Controller(conn).searchPublisherTable(
                collun.getCollum(), mainFrame.getPublisherSearchField().getText()));
    }

}

class ButtonAddAuthor implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonAddAuthor(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginAuthorAddFrame(); // inicie a proxima janela
	}
}

class ButtonEditAuthor implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonEditAuthor(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginAuthorEditFrame(mainFrame.getAuthor()); // inicie a proxima janela
	}
}

class ButtonDropAuthor implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonDropAuthor(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginAuthorDropFrame(mainFrame.getAuthor()); // inicie a proxima janela
	}
}

class ButtonAddBook implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonAddBook(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		try {
			(new Controller(conn)).beginBookAddFrame(); // inicie a proxima janela
		} catch (Exception s) {
			s.printStackTrace();
		}
	}
}

class ButtonEditBook implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonEditBook(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginBookEditFrame(mainFrame.getBook()); // inicie a proxima janela
	}
}

class ButtonDropBook implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonDropBook(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginBookDropFrame(mainFrame.getBook()); // inicie a proxima janela
	}
}

class ButtonAddPublisher implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonAddPublisher(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginPublisherAddFrame(); // inicie a proxima janela
	}
}

class ButtonEditPublisher implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonEditPublisher(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginPublisherEditFrame(mainFrame.getPublisher()); // inicie a proxima janela
	}
}

class ButtonDropPublisher implements ActionListener{

	private Connection conn;
	private MainFrame mainFrame;

	ButtonDropPublisher(Connection conn, MainFrame mainFrame) {
		this.conn = conn;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.closeFrame(); // fecha a janela atual
		(new Controller(conn)).beginPublisherDropFrame(mainFrame.getPublisher()); // inicie a proxima janela
	}
}