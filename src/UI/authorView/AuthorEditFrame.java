package UI.authorView;

import controller.Controller;
import model.Author;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

public class AuthorEditFrame {

	//variaveis global
	private JFrame authorEditFrame;
	private Author author;
	private JTextField authorNameField, authorIdField, authorFnameField;

    public AuthorEditFrame(Author author) {
        this.author = author;
        this.authorIdField = new JTextField(author.getId());
        this.authorIdField.setEditable(false);
        this.authorNameField = new JTextField(author.getFirstName());
        this.authorFnameField = new JTextField(author.getLastName());
    }

    public void beginAuthorEditFrame(Connection conn) {

		//declaração dos objetos
		authorEditFrame = new JFrame();
        JLayeredPane authorEditLayered = new JLayeredPane();
        JPanel authorEditPanel = new JPanel();
        JLabel authorNameLabel = new JLabel("Nome :");
        JLabel authorIdLabel = new JLabel("Id do autor :");
        JLabel authorFnameLabel = new JLabel("Sobrenome :");
        JButton authorCancel = new JButton("Cancelar");
        JButton authorSave = new JButton("Salvar");

		//Container principal
		authorEditFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		authorEditFrame.setTitle("Livraria");
		authorEditFrame.setMinimumSize(new Dimension(750, 550));
		authorEditFrame.setMaximizedBounds(null);
		authorEditFrame.setResizable(false);
		Container authorEditFrameContentPane = authorEditFrame.getContentPane();

			//Layer do painel	
			authorEditLayered.setBackground(Color.black);

			//Borda do painel			
			authorEditPanel.setBorder(new CompoundBorder(new TitledBorder("Editar Autor"), new EmptyBorder(5, 5, 5, 5)));

			//Layout do painel
			GroupLayout authorEditPanelLayout = new GroupLayout(authorEditPanel);
			authorEditPanel.setLayout(authorEditPanelLayout);
			authorEditPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				authorEditPanelLayout.createParallelGroup()
					.addGroup(authorEditPanelLayout.createSequentialGroup()
						.addGroup(authorEditPanelLayout.createParallelGroup()
							.addGroup(authorEditPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(authorCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(authorSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(authorEditPanelLayout.createSequentialGroup()
								.addGap(190, 190, 190)
								.addGroup(authorEditPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(authorFnameLabel)
									.addComponent(authorNameLabel)
									.addComponent(authorIdLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(authorEditPanelLayout.createParallelGroup()
									.addGroup(authorEditPanelLayout.createSequentialGroup()
										.addComponent(authorIdField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
									.addGroup(authorEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(authorFnameField)
										.addComponent(authorNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(141, Short.MAX_VALUE))
			);
			authorEditPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				authorEditPanelLayout.createParallelGroup()
					.addGroup(authorEditPanelLayout.createSequentialGroup()
						.addGap(130, 130, 130)
						.addGroup(authorEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorIdLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
						.addGroup(authorEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(authorEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorFnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorFnameLabel))
						.addGap(137, 137, 137)
						.addGroup(authorEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorCancel)
							.addComponent(authorSave))
						.addGap(67, 67, 67))
			);
			
		authorEditLayered.add(authorEditPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o authorEditPanel em authorEditLayered
		authorEditPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de authorEditPanel (x,y,h,w)
		

		GroupLayout authorEditFrameContentPaneLayout = new GroupLayout(authorEditFrameContentPane); // cria um layout para o container principal
		authorEditFrameContentPane.setLayout(authorEditFrameContentPaneLayout);
		authorEditFrameContentPaneLayout.setHorizontalGroup(
			authorEditFrameContentPaneLayout.createParallelGroup()
				.addGroup(authorEditFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(authorEditLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		authorEditFrameContentPaneLayout.setVerticalGroup(
			authorEditFrameContentPaneLayout.createParallelGroup()
				.addGroup(authorEditFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(authorEditLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		authorSave.addActionListener(new EditAuthorButton(conn, this));
		authorCancel.addActionListener(new CancelEditButton(conn, this));

		authorEditFrame.setVisible(true); // liga a janela
		authorEditFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela 
		authorEditFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

    public Author getAuthor() {
        return author;
    }

    JTextField getAuthorNameField() {
        return authorNameField;
    }

    JTextField getAuthorFnameField() {
        return authorFnameField;
    }

    void closeFrame() {
	    this.authorEditFrame.dispose();
    }
}

class EditAuthorButton implements ActionListener {

    private Connection conn;
    private AuthorEditFrame authorEditFrame;

    EditAuthorButton(Connection conn, AuthorEditFrame authorEditFrame) {
        this.conn = conn;
        this.authorEditFrame = authorEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller con = new Controller(conn);
        if(!authorEditFrame.getAuthorNameField().getText().equals("") || !authorEditFrame.getAuthorFnameField().getText().equals("")) {
            try {
                con.updateAuthor(authorEditFrame.getAuthor().getId(), authorEditFrame.getAuthorNameField().getText(),
                        authorEditFrame.getAuthorFnameField().getText());
                this.authorEditFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(0); // inicie a proxima janela
            } catch (SQLException s) {
                s.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null,"Erro \nUm ou mais campos em branco"
                    ,"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}

class CancelEditButton implements ActionListener {

    private Connection conn;
    private AuthorEditFrame authorEditFrame;

    CancelEditButton(Connection conn, AuthorEditFrame authorEditFrame) {
        this.conn = conn;
        this.authorEditFrame = authorEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.authorEditFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(0); // inicie a proxima janela
    }
}