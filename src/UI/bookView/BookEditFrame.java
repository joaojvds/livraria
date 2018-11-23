package UI.bookView;

//import UI.MainFrame;
import controller.Controller;
import model.Author;
import model.Books;
import model.Publisher;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

public class BookEditFrame /*implements ActionListener*/ {

	//variaveis global
	private JFrame bookEditFrame;
	private Books book;
    private JPanel bookEditPanel, comboBoxPanel;
	private JTextField bookNameField, bookIsbnField, bookPriceField;
    private ArrayList<JComboBox> bookAuthorBox;
	private JComboBox bookPublisherBox;
    private GridBagConstraints comboBoxConstraints;
	//private Connection conn;

	public BookEditFrame(Books book, Connection conn) {
        this.book = book;
        this.bookIsbnField = new JTextField(book.getIsbn());
        this.bookIsbnField.setEditable(false);
        this.bookNameField = new JTextField(book.getTitle());
        this.bookPriceField = new JTextField(String.valueOf(book.getPrice()));
        this.bookPublisherBox = new JComboBox();
        try {
            this.bookPublisherBox = new Controller(conn).fillPublisherComboBox();
            this.bookPublisherBox.getModel().setSelectedItem(book.getPublisher());
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

	public void beginBookEditFrame(Connection conn) {

		//declaração dos objetos
		bookEditFrame = new JFrame();
        JLayeredPane bookEditLayered = new JLayeredPane();
		bookEditPanel = new JPanel();
		comboBoxPanel = new JPanel();
        JLabel bookNameLabel = new JLabel("Nome :");
        JLabel bookIsbnLabel = new JLabel("ISBN :");
        JLabel bookPriceLabel = new JLabel("Preço :");
        JLabel bookPublisherLabel = new JLabel("Editora :");
		bookAuthorBox = new ArrayList<>();
        JLabel bookAuthorLabel = new JLabel("Autor(es) :");
        JButton plusAuthorBox = new JButton("+");
        JButton minusAuthorBox = new JButton("-");
        JButton bookCancel = new JButton("Cancelar");
        JButton bookSave = new JButton("Salvar");

        //adiciona os parametros para o comboBox
		comboBoxConstraints = new GridBagConstraints();
		comboBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
		comboBoxConstraints.weightx = 1;
		comboBoxConstraints.insets = new Insets(0,0,5,0);  //top padding
		comboBoxConstraints.gridx = 0;

		//Container principal
		bookEditFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		bookEditFrame.setTitle("Livraria");
		bookEditFrame.setMinimumSize(new Dimension(750, 550));
		bookEditFrame.setMaximizedBounds(null);
		bookEditFrame.setResizable(false);
		Container bookEditFrameContentPane = bookEditFrame.getContentPane();

			//Layer do painel
			bookEditLayered.setBackground(Color.black);

			//Borda do painel
			bookEditPanel.setBorder(new CompoundBorder(new TitledBorder("Editar Livro"), new EmptyBorder(5, 5, 5, 5)));

            //Cria um painel para os comboBox
			comboBoxPanel.setLayout(new GridBagLayout());

			//Layout do painel
			GroupLayout bookEditPanelLayout = new GroupLayout(bookEditPanel);
			bookEditPanel.setLayout(bookEditPanelLayout);
			bookEditPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				bookEditPanelLayout.createParallelGroup()
					.addGroup(bookEditPanelLayout.createSequentialGroup()
						.addGroup(bookEditPanelLayout.createParallelGroup()
							.addGroup(bookEditPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(bookCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(bookSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(bookEditPanelLayout.createSequentialGroup()
								.addGap(171, 171, 171)
								.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(bookPriceLabel)
									.addComponent(bookPublisherLabel)
									.addComponent(bookAuthorLabel)
									.addComponent(bookNameLabel)
									.addComponent(bookIsbnLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(bookEditPanelLayout.createParallelGroup()
									.addGroup(bookEditPanelLayout.createSequentialGroup()
										.addComponent(bookIsbnField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
									.addGroup(bookEditPanelLayout.createSequentialGroup()
										.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
											.addComponent(comboBoxPanel) // adiciona o painel dos comboBox no painel principal
											.addComponent(bookPublisherBox)
											.addComponent(bookPriceField)
											.addComponent(bookNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(plusAuthorBox)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(minusAuthorBox))
									.addGroup(bookEditPanelLayout.createSequentialGroup()
										.addComponent(bookCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(bookSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(206, Short.MAX_VALUE)))
			));
			bookEditPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				bookEditPanelLayout.createParallelGroup()
					.addGroup(bookEditPanelLayout.createSequentialGroup()
						.addGap(130, 130, 130)
						.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookIsbnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookIsbnLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
						.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookPriceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookPriceLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookPublisherBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookPublisherLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(comboBoxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(bookAuthorLabel) // adiciona o painel dos comboBox no painel principal
							.addComponent(plusAuthorBox)
							.addComponent(minusAuthorBox))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
						.addGroup(bookEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(bookCancel)
							.addComponent(bookSave))
						.addGap(67, 67, 67))
			);
				
			bookEditLayered.add(bookEditPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o bookEditPanel em bookEditLayered
			bookEditPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de bookEditPanel (x,y,h,w)
			

		GroupLayout bookEditFrameContentPaneLayout = new GroupLayout(bookEditFrameContentPane); // cria um layout para o container principal
		bookEditFrameContentPane.setLayout(bookEditFrameContentPaneLayout);
		bookEditFrameContentPaneLayout.setHorizontalGroup(
			bookEditFrameContentPaneLayout.createParallelGroup()
				.addGroup(bookEditFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bookEditLayered, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
					.addContainerGap())
		);
		bookEditFrameContentPaneLayout.setVerticalGroup(
			bookEditFrameContentPaneLayout.createParallelGroup()
				.addGroup(bookEditFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bookEditLayered, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
					.addContainerGap())
		);

        for (Author author : book.getAuthor()) {
            try {
                bookAuthorBox.add((new Controller(conn).fillAuthorComboBox()));
                bookAuthorBox.get(bookAuthorBox.size()-1).getModel().setSelectedItem(author);
            } catch (SQLException s) {
                s.printStackTrace();
            }
            comboBoxPanel.add(bookAuthorBox.get(bookAuthorBox.size()-1), comboBoxConstraints);
            bookEditPanel.validate();
        }

        //Action Listener dos botões
		minusAuthorBox.addActionListener(new EditMinusAuthorButton(this));
		plusAuthorBox.addActionListener(new EditPlusAuthorButton(conn, this));
		bookSave.addActionListener(new EditBookButton(conn, this)); //new EditBookButton(conn, this) precisa consertar
		bookCancel.addActionListener(new CancelEditAuthorButton(conn, this));

		bookEditFrame.setVisible(true); // liga a janela
		bookEditFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela
		bookEditFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

    public Books getBook() {
        return book;
    }

    GridBagConstraints getComboBoxConstraints() {
        return comboBoxConstraints;
    }

    JPanel getBookEditPanel() {
        return bookEditPanel;
    }

    JPanel getComboBoxPanel() {
        return comboBoxPanel;
    }

    JTextField getBookNameField() {
        return bookNameField;
    }

    JTextField getBookPriceField() {
        return bookPriceField;
    }

    ArrayList<JComboBox> getBookAuthorBox() {
        return bookAuthorBox;
    }

    void setBookAuthorBox(JComboBox bookAuthorBox) {
        this.bookAuthorBox.add(bookAuthorBox);
    }

    JComboBox getBookPublisherBox() {
        return bookPublisherBox;
    }

    void closeFrame() {
        this.bookEditFrame.dispose();
    }
}

class EditPlusAuthorButton implements ActionListener {

    private BookEditFrame bookEditFrame;
    private Connection conn;

    EditPlusAuthorButton(Connection conn, BookEditFrame bookEditFrame) {
        this.conn = conn;
        this.bookEditFrame = bookEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int listIndex = bookEditFrame.getBookAuthorBox().size() - 1;
        if(listIndex < 6) {
            System.out.println(listIndex);
            try {
                listIndex++; // adiciona 1 no indice
                bookEditFrame.setBookAuthorBox((new Controller(conn).fillAuthorComboBox())); // adiciona um comboBox no ArrayList
                bookEditFrame.getComboBoxPanel().add(bookEditFrame.getBookAuthorBox().get(listIndex),
                        bookEditFrame.getComboBoxConstraints());
            } catch (Exception s) {
                s.printStackTrace();
            }
        }
        bookEditFrame.getBookEditPanel().validate(); // atualiza o painel com novas informações
    }
}

class EditMinusAuthorButton implements ActionListener {

    private BookEditFrame bookEditFrame;

    EditMinusAuthorButton(BookEditFrame bookEditFrame) {
        this.bookEditFrame = bookEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int listIndex = bookEditFrame.getBookAuthorBox().size() - 1;
        if (listIndex > 0) {
            System.out.println(listIndex);
            bookEditFrame.getComboBoxPanel().remove(bookEditFrame.getBookAuthorBox().get(listIndex)); // remove o comboBox do painel
            bookEditFrame.getBookAuthorBox().remove(listIndex);
        }
        bookEditFrame.getBookEditPanel().validate(); // revalida os componentes do painel
        bookEditFrame.getBookEditPanel().repaint(); // atualiza o painel
    }
}

class EditBookButton implements ActionListener {

    private Connection conn;
    private BookEditFrame bookEditFrame;

    EditBookButton(Connection conn, BookEditFrame bookEditFrame) {
        this.conn = conn;
        this.bookEditFrame = bookEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((!bookEditFrame.getBookNameField().getText().equals("")) ||
                (!bookEditFrame.getBookPriceField().getText().equals(""))) {
            Controller con = new Controller(conn);
            try {
                con.updateBook(bookEditFrame.getBook().getIsbn(), bookEditFrame.getBookNameField().getText(),
                        bookEditFrame.getBookPriceField().getText(), (Publisher) bookEditFrame.getBookPublisherBox().getSelectedItem(),
                        bookEditFrame.getBookAuthorBox());
                this.bookEditFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(1); // inicie a proxima janela
            } catch (Exception s) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar na tabela\n" +
                        s.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Erro ao salvar na tabela \n Campo vazio"
                    ,"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}

class CancelEditAuthorButton implements ActionListener {

    private Connection conn;
    private BookEditFrame bookEditFrame;

    CancelEditAuthorButton(Connection conn, BookEditFrame bookEditFrame) {
        this.conn = conn;
        this.bookEditFrame = bookEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.bookEditFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(1); // inicie a proxima janela
    }
}