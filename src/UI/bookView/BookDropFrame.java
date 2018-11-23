package UI.bookView;

import controller.Controller;
import model.Author;
import model.Books;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

public class BookDropFrame {

	//variaveis global
	private JFrame bookDropFrame;
	private Books book;
    private JTextField bookNameField, bookIsbnField, bookPriceField, bookPublisherField;

	public BookDropFrame(Books book) {
	    this.book = book;
        this.bookIsbnField = new JTextField(book.getIsbn());
        this.bookNameField = new JTextField(book.getTitle());
        this.bookPriceField = new JTextField(String.valueOf(book.getPrice()));
        this.bookPublisherField = new JTextField(book.getPublisher().getName());
    }

	public void beginBookDropFrame(Connection conn) {

		//declaração dos objetos
		bookDropFrame = new JFrame();
        JLayeredPane bookDropLayered = new JLayeredPane();
        JPanel bookDropPanel = new JPanel();
        JPanel textFieldPanel = new JPanel();
        JLabel bookNameLabel = new JLabel("Nome :");
        JLabel bookIsbnLabel = new JLabel("ISBN :");
        JLabel bookPriceLabel = new JLabel("Preço :");
        JLabel bookPublisherLabel = new JLabel("Editora :");
        ArrayList<JTextField> bookAuthorField = new ArrayList<>();
        JLabel bookAuthorLabel = new JLabel("Autor(es) :");
        JButton bookCancel = new JButton("Cancelar");
        JButton bookErase = new JButton("Apagar");

        GridBagConstraints textFieldConstraints = new GridBagConstraints();
		textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		textFieldConstraints.weightx = 1;
		textFieldConstraints.insets = new Insets(0,0,5,0);  //top padding
		textFieldConstraints.gridx = 0;

		//Container principal
		bookDropFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		bookDropFrame.setTitle("Livraria");
		bookDropFrame.setMinimumSize(new Dimension(750, 550));
		bookDropFrame.setMaximizedBounds(null);
		bookDropFrame.setResizable(false);
		Container bookDropFrameContentPane = bookDropFrame.getContentPane();

			//Layer do painel
			bookDropLayered.setBackground(Color.black);

			//Borda do painel
			bookDropPanel.setBorder(new CompoundBorder(new TitledBorder("Apagar Livro"), new EmptyBorder(5, 5, 5, 5)));

			textFieldPanel.setLayout(new GridBagLayout());

			//Layout do painel
			GroupLayout bookDropPanelLayout = new GroupLayout(bookDropPanel);
			bookDropPanel.setLayout(bookDropPanelLayout);
			bookDropPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				bookDropPanelLayout.createParallelGroup()
					.addGroup(bookDropPanelLayout.createSequentialGroup()
						.addGroup(bookDropPanelLayout.createParallelGroup()
							.addGroup(bookDropPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(bookCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(bookErase, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(bookDropPanelLayout.createSequentialGroup()
								.addGap(190, 190, 190)
								.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(bookPriceLabel)
									.addComponent(bookNameLabel)
									.addComponent(bookIsbnLabel)
									.addComponent(bookPublisherLabel)
									.addComponent(bookAuthorLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(bookDropPanelLayout.createParallelGroup()
									.addGroup(bookDropPanelLayout.createSequentialGroup()
										.addComponent(bookIsbnField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
									.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(textFieldPanel)
										.addComponent(bookPublisherField)
										.addComponent(bookPriceField)
										.addComponent(bookNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(168, Short.MAX_VALUE))
			);
			bookDropPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				bookDropPanelLayout.createParallelGroup()
					.addGroup(bookDropPanelLayout.createSequentialGroup()
						.addGap(130, 130, 130)
						.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookIsbnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookIsbnLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
						.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookPriceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookPriceLabel))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookPublisherField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookPublisherLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(textFieldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookAuthorLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
						.addGroup(bookDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookCancel)
							.addComponent(bookErase))
						.addGap(67, 67, 67))
			);
			
			bookDropLayered.add(bookDropPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o bookDropPanel em bookDropLayered
			bookDropPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de bookDropPanel (x,y,h,w)
			

		GroupLayout bookDropFrameContentPaneLayout = new GroupLayout(bookDropFrameContentPane); // cria um layout para o container principal
		bookDropFrameContentPane.setLayout(bookDropFrameContentPaneLayout);
		bookDropFrameContentPaneLayout.setHorizontalGroup(
			bookDropFrameContentPaneLayout.createParallelGroup()
				.addGroup(bookDropFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bookDropLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		bookDropFrameContentPaneLayout.setVerticalGroup(
			bookDropFrameContentPaneLayout.createParallelGroup()
				.addGroup(bookDropFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bookDropLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

        for (Author author : book.getAuthor()) {
            bookAuthorField.add(new JTextField(author.getName()));
            textFieldPanel.add(bookAuthorField.get(bookAuthorField.size()-1), textFieldConstraints);
            bookAuthorField.get(bookAuthorField.size()-1).setEditable(false);
            bookDropPanel.validate();
        }

		bookErase.addActionListener(new DeleteBookButton(conn, this));
		bookCancel.addActionListener(new CancelDeleteButton(conn, this));

        //impedi o usuario de editar esses campos
		bookIsbnField.setEditable(false);
		bookNameField.setEditable(false);
		bookPriceField.setEditable(false);
		bookPublisherField.setEditable(false);

		bookDropFrame.setVisible(true); // liga a janela
		bookDropFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela
		bookDropFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

    Books getBook() {
        return book;
    }

    void closeFrame() {
	    this.bookDropFrame.dispose();
    }
}

class DeleteBookButton implements ActionListener {

    private Connection conn;
    private BookDropFrame bookDropFrame;

    DeleteBookButton(Connection conn, BookDropFrame bookDropFrame) {
        this.conn = conn;
        this.bookDropFrame = bookDropFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel header = new JLabel("<HTML><B>Você tem certeza que quer apagar esse livro?</B>" +
                "<br><br><FONT style = 'font-weight: normal;'>Não sera possivel recupera-lo após isso<br>" +
                "Você deseja continuar?</FONT></html>");

        if (JOptionPane.showConfirmDialog(null, header, "AVISO!!",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            Controller con = new Controller(conn);
            try {
                con.deleteBook(bookDropFrame.getBook().getIsbn());
                this.bookDropFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(1); // inicie a proxima janela
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }
}

class CancelDeleteButton implements ActionListener {

    private Connection conn;
    private BookDropFrame bookDropFrame;

    CancelDeleteButton(Connection conn, BookDropFrame bookDropFrame) {
        this.conn = conn;
        this.bookDropFrame = bookDropFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.bookDropFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(1); // inicie a proxima janela
    }
}