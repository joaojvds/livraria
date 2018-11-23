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

public class AuthorDropFrame {

	//variaveis global
	private JFrame authorDropFrame;
    private Author author;
    private JTextField authorNameField, authorIdField, authorFnameField;

	public AuthorDropFrame(Author author) {
	    this.author = author;
        this.authorIdField = new JTextField(author.getId());
        this.authorNameField = new JTextField(author.getFirstName());
        this.authorFnameField = new JTextField(author.getLastName());
    }

    public void beginAuthorDropFrame(Connection conn) {

		//declaração dos objetos
		authorDropFrame = new JFrame();
        JLayeredPane authorDropLayered = new JLayeredPane();
        JPanel authorDropPanel = new JPanel();
        JLabel authorNameLabel = new JLabel("Nome :");
        JLabel authorIdLabel = new JLabel("Id do autor :");
        JLabel authorFnameLabel = new JLabel("Sobrenome :");
        JButton authorCancel = new JButton("Cancelar");
        JButton authorErase = new JButton("Apagar");

		//Container principal
		authorDropFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		authorDropFrame.setTitle("Livraria");
		authorDropFrame.setMinimumSize(new Dimension(750, 550));
		authorDropFrame.setMaximizedBounds(null);
		authorDropFrame.setResizable(false);
		Container authorDropFrameContentPane = authorDropFrame.getContentPane();

			//Layer do painel			
			authorDropLayered.setBackground(Color.black);

			//Borda do painel		
			authorDropPanel.setBorder(new CompoundBorder(new TitledBorder("Apagar Autor"), new EmptyBorder(5, 5, 5, 5)));

			//impedi o usuario de editar esses campos
            authorIdField.setEditable(false);
			authorNameField.setEditable(false);
			authorFnameField.setEditable(false);

			//Layout do painel
			GroupLayout authorDropPanelLayout = new GroupLayout(authorDropPanel);
			authorDropPanel.setLayout(authorDropPanelLayout);
			authorDropPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				authorDropPanelLayout.createParallelGroup()
					.addGroup(authorDropPanelLayout.createSequentialGroup()
						.addGroup(authorDropPanelLayout.createParallelGroup()
							.addGroup(authorDropPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(authorCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(authorErase, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(authorDropPanelLayout.createSequentialGroup()
								.addGap(190, 190, 190)
								.addGroup(authorDropPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(authorFnameLabel)
									.addComponent(authorNameLabel)
									.addComponent(authorIdLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(authorDropPanelLayout.createParallelGroup()
									.addGroup(authorDropPanelLayout.createSequentialGroup()
										.addComponent(authorIdField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
									.addGroup(authorDropPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(authorFnameField)
										.addComponent(authorNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(141, Short.MAX_VALUE))
			);
			authorDropPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				authorDropPanelLayout.createParallelGroup()
					.addGroup(authorDropPanelLayout.createSequentialGroup()
						.addGap(130, 130, 130)
						.addGroup(authorDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorIdLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
						.addGroup(authorDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(authorDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorFnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorFnameLabel))
						.addGap(137, 137, 137)
						.addGroup(authorDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorCancel)
							.addComponent(authorErase))
						.addGap(67, 67, 67))
			);
				
			authorDropLayered.add(authorDropPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o authorDropPanel em authorDropLayered
			authorDropPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de authorDropPanel (x,y,h,w)
			

		GroupLayout authorDropFrameContentPaneLayout = new GroupLayout(authorDropFrameContentPane); // cria um layout para o container principal
		authorDropFrameContentPane.setLayout(authorDropFrameContentPaneLayout);
		authorDropFrameContentPaneLayout.setHorizontalGroup(
			authorDropFrameContentPaneLayout.createParallelGroup()
				.addGroup(authorDropFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(authorDropLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		authorDropFrameContentPaneLayout.setVerticalGroup(
			authorDropFrameContentPaneLayout.createParallelGroup()
				.addGroup(authorDropFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(authorDropLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		authorErase.addActionListener(new DeleteAuthorButton(conn, this));
		authorCancel.addActionListener(new CancelDeleteAuthorButton(conn, this));
		
		authorDropFrame.setVisible(true); // liga a janela
		authorDropFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela 
		authorDropFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela		
	}

    public Author getAuthor() {
        return author;
    }

    void closeFrame() {
        this.authorDropFrame.dispose();
    }
}

class DeleteAuthorButton implements ActionListener {

    private Connection conn;
    private AuthorDropFrame authorDropFrame;

    DeleteAuthorButton(Connection conn, AuthorDropFrame authorDropFrame) {
        this.conn = conn;
        this.authorDropFrame = authorDropFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel header = new JLabel("<HTML><B>Você tem certeza que quer apagar esse autor?</B>" +
                "<br><br><FONT style = 'font-weight: normal;'>Apagar esse autor pode apagar todos os dados ligados a ele<br>" +
                "Você deseja continuar?</FONT></html>");

        if (JOptionPane.showConfirmDialog(null, header, "AVISO!!",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            Controller con = new Controller(conn);
            try {
                con.deleteAuthor(authorDropFrame.getAuthor().getId());
                this.authorDropFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(0); // inicie a proxima janela
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }
}

class CancelDeleteAuthorButton implements ActionListener {

    private Connection conn;
    private AuthorDropFrame authorDropFrame;

    CancelDeleteAuthorButton(Connection conn, AuthorDropFrame authorDropFrame) {
        this.conn = conn;
        this.authorDropFrame = authorDropFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.authorDropFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(0); // inicie a proxima janela
    }
}