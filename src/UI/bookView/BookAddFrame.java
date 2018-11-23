package UI.bookView;

import controller.Controller;
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

public class BookAddFrame {

	//variaveis global
	private JFrame bookAddFrame;
	private JComboBox bookPublisherBox;
    private ArrayList<JComboBox> bookAuthorBox;

	public void beginBookAddFrame(Connection conn) throws SQLException {

		//declaração dos objetos
		bookAddFrame = new JFrame();
        JLayeredPane bookAddLayered = new JLayeredPane();
        JPanel bookAddPanel = new JPanel();
        JPanel comboBoxPanel = new JPanel();
        JScrollPane panelScroll = new JScrollPane(comboBoxPanel);
        JTextField bookNameField = new JTextField();
        JLabel bookNameLabel = new JLabel("Nome :");
        JTextField bookIsbnField = new JTextField();
        JLabel bookIsbnLabel = new JLabel("ISBN :");
        JTextField bookPriceField = new JTextField();
        JLabel bookPriceLabel = new JLabel("Preço :");
        bookPublisherBox = new JComboBox();
        JLabel bookPublisherLabel = new JLabel("Editora :");
		bookAuthorBox = new ArrayList<>();
        JLabel bookAuthorLabel = new JLabel("Autor(es) :");
        JButton plusAuthorBox = new JButton("+");
        JButton minusAuthorBox = new JButton("-");
        JButton bookCancel = new JButton("Cancelar");
        JButton bookSave = new JButton("Salvar");

        //preenche os ComboBox com dados
        fillComboBox(conn);

        //adiciona os parametros para o comboBox
        GridBagConstraints comboBoxConstraints = new GridBagConstraints();
		comboBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
		comboBoxConstraints.weightx = 1;
		comboBoxConstraints.insets = new Insets(0,0,5,0);  //top padding
		comboBoxConstraints.gridx = 0;

		//Container principal	
		bookAddFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		bookAddFrame.setTitle("Livraria");
		bookAddFrame.setMinimumSize(new Dimension(750, 550));
		bookAddFrame.setMaximizedBounds(null);
		bookAddFrame.setResizable(false);
		Container bookAddFrameContentPane = bookAddFrame.getContentPane();

		//Layer do painel
		bookAddLayered.setBackground(Color.black);

		//Borda do painel
		bookAddPanel.setBorder(new CompoundBorder(new TitledBorder("Adicionar Livro"),	new EmptyBorder(5, 5, 5, 5)));
		bookAddPanel.setAutoscrolls(true);
		bookAddPanel.setMaximumSize(new Dimension(726, 494));
		bookAddPanel.setMinimumSize(new Dimension(726, 494));

		//Cria um painel para os comboBox
		comboBoxPanel.setLayout(new GridBagLayout());

		//Layout do painel
		panelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout bookAddPanelLayout = new GroupLayout(bookAddPanel);
		bookAddPanel.setLayout(bookAddPanelLayout);
		bookAddPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
			bookAddPanelLayout.createParallelGroup()
				.addGroup(bookAddPanelLayout.createSequentialGroup()
					.addGap(169, 169, 169)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(bookNameLabel)
						.addComponent(bookIsbnLabel)
						.addComponent(bookPriceLabel)
						.addComponent(bookPublisherLabel)
						.addComponent(bookAuthorLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(bookAddPanelLayout.createParallelGroup()
						.addGroup(bookAddPanelLayout.createSequentialGroup()
							.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(comboBoxPanel) // adiciona o painel dos comboBox no painel principal
								.addComponent(bookPublisherBox)
								.addComponent(bookPriceField)
								.addComponent(bookIsbnField)
								.addComponent(bookNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(plusAuthorBox)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(minusAuthorBox))
						.addGroup(bookAddPanelLayout.createSequentialGroup()
							.addComponent(bookCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addComponent(bookSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(206, Short.MAX_VALUE))
		);
		bookAddPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
			bookAddPanelLayout.createParallelGroup()
				.addGroup(bookAddPanelLayout.createSequentialGroup()
					.addGap(106, 106, 106)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(bookNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookNameLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(bookIsbnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookIsbnLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(bookPriceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookPriceLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(bookPublisherBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookPublisherLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(comboBoxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookAuthorLabel) // adiciona o painel dos comboBox no painel principal
						.addComponent(plusAuthorBox)
						.addComponent(minusAuthorBox))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
					.addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(bookCancel)
						.addComponent(bookSave))
					.addGap(67, 67, 67))
		);

		bookAddLayered.add(bookAddPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o bookAddPanel em bookAddLayered
		bookAddPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de bookAddPanel (x,y,w,h)


		GroupLayout bookAddFrameContentPaneLayout = new GroupLayout(bookAddFrameContentPane); // cria um layout para o container principal
		bookAddFrameContentPane.setLayout(bookAddFrameContentPaneLayout);
		bookAddFrameContentPaneLayout.setHorizontalGroup(
			bookAddFrameContentPaneLayout.createParallelGroup()
				.addGroup(bookAddFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bookAddLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		bookAddFrameContentPaneLayout.setVerticalGroup(
			bookAddFrameContentPaneLayout.createParallelGroup()
				.addGroup(bookAddFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bookAddLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		//criar um combo box no ArrayList
		comboBoxPanel.add(bookAuthorBox.get(0), comboBoxConstraints); // adiciona o combo box criado no painel com os parametros
		bookAddPanel.validate(); // atualiza o painel com as novas informações

        //Action Listener dos botões
		plusAuthorBox.addActionListener(new PlusAuthorButton(conn, this, comboBoxPanel, bookAddPanel, comboBoxConstraints));
        minusAuthorBox.addActionListener(new MinusAuthorButton(this, comboBoxPanel, bookAddPanel));
		bookSave.addActionListener(new AddBookButton(conn, this, bookNameField, bookIsbnField, bookPriceField, bookPublisherBox));
		bookCancel.addActionListener(new CancelAddBookButton(conn, this));

		bookAddFrame.setVisible(true); // liga a janela
		bookAddFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela 
		bookAddFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

	void closeFrame() {
	    this.bookAddFrame.dispose();
    }

    void setBookAuthorBox(JComboBox bookAuthorBox) {
        this.bookAuthorBox.add(bookAuthorBox);
    }

    ArrayList<JComboBox> getBookAuthorBox() {
        return this.bookAuthorBox;
    }

    private void fillComboBox(Connection conn) throws SQLException {
        this.bookPublisherBox = new Controller(conn).fillPublisherComboBox();
        this.bookAuthorBox.add((new Controller(conn).fillAuthorComboBox()));
    }
}

class PlusAuthorButton implements ActionListener {

    private BookAddFrame bookAddFrame;
    private JPanel comboBoxPanel, bookAddPanel;
    private GridBagConstraints comboBoxConstraints;
    private Connection conn;

    PlusAuthorButton(Connection conn, BookAddFrame bookAddFrame,
                     JPanel comboBoxPanel,
                     JPanel bookAddPanel,
                     GridBagConstraints comboBoxConstraints) {
        this.conn = conn;
        this.bookAddFrame = bookAddFrame;
        this.comboBoxPanel = comboBoxPanel;
        this.bookAddPanel = bookAddPanel;
        this.comboBoxConstraints = comboBoxConstraints;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int listIndex = bookAddFrame.getBookAuthorBox().size() - 1;
        if(listIndex < 6) {
            System.out.println(listIndex);
            try {
                listIndex++; // adiciona 1 no indice
                bookAddFrame.setBookAuthorBox((new Controller(conn).fillAuthorComboBox())); // adiciona um comboBox no ArrayList
                comboBoxPanel.add(bookAddFrame.getBookAuthorBox().get(listIndex), comboBoxConstraints);
            } catch (Exception s) {
                s.printStackTrace();
            }
        }
        bookAddPanel.validate(); // atualiza o painel com novas informações
    }
}

class MinusAuthorButton implements ActionListener {

    private BookAddFrame bookAddFrame;
    private JPanel comboBoxPanel, bookAddPanel;

    MinusAuthorButton(BookAddFrame bookAddFrame, JPanel comboBoxPanel, JPanel bookAddPanel) {
        this.bookAddFrame = bookAddFrame;
        this.comboBoxPanel = comboBoxPanel;
        this.bookAddPanel = bookAddPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int listIndex = bookAddFrame.getBookAuthorBox().size() - 1;
        if (listIndex > 0) {
            System.out.println(listIndex);
            comboBoxPanel.remove(bookAddFrame.getBookAuthorBox().get(listIndex)); // remove o comboBox do painel
            bookAddFrame.getBookAuthorBox().remove(listIndex);
        }
        bookAddPanel.validate(); // revalida os componentes do painel
        bookAddPanel.repaint(); // atualiza o painel
    }
}

class AddBookButton implements ActionListener {

    private Connection conn;
    private BookAddFrame bookAddFrame;
    private JTextField title, isbn, price;
    private JComboBox bookPublisherBox;

    AddBookButton(Connection conn, BookAddFrame bookAddFrame, JTextField title,
                    JTextField isbn, JTextField price, JComboBox bookPublisherBox) {
        this.conn = conn;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.bookPublisherBox = bookPublisherBox;
        this.bookAddFrame = bookAddFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((!title.getText().equals("")) || (!isbn.getText().equals("")) || (!price.getText().equals(""))) {
            int listIndex = bookAddFrame.getBookAuthorBox().size()-1;
            Controller con = new Controller(conn);
            try {
                con.insertBook(listIndex, (Publisher) bookPublisherBox.getSelectedItem(), title.getText(),
                        isbn.getText(), price.getText(), bookAddFrame.getBookAuthorBox());
                this.bookAddFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(1); // inicie a proxima janela
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar na tabela\n" +
                        s.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Erro ao salvar na tabela \n Campo vazio"
                    ,"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}

class CancelAddBookButton implements ActionListener {

    private Connection conn;
    private BookAddFrame bookAddFrame;

    CancelAddBookButton(Connection conn, BookAddFrame bookAddFrame) {
        this.conn = conn;
        this.bookAddFrame = bookAddFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.bookAddFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(1); // inicie a proxima janela
    }
}