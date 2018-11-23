package UI.authorView;

import controller.Controller;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

public class AuthorAddFrame {

	//variaveis global
	private JFrame authorAddFrame;

	public void beginAuthorAddFrame(Connection conn) {

		//declaração dos objetos
		authorAddFrame = new JFrame();
        JLayeredPane authorAddLayered = new JLayeredPane();
        JPanel authorAddPanel = new JPanel();
        JTextField authorNameField = new JTextField();
        JLabel authorNameLabel = new JLabel("Nome :");
        JTextField authorFnameField = new JTextField();
        JLabel authorFnameLabel = new JLabel("Sobrenome :");
        JButton authorCancel = new JButton("Cancelar");
        JButton authorSave = new JButton("Salvar");

		//Container principal	
		authorAddFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		authorAddFrame.setTitle("Livraria");
		authorAddFrame.setMinimumSize(new Dimension(750, 550));
		authorAddFrame.setMaximizedBounds(null);
		authorAddFrame.setResizable(false);
		Container authorAddFrameContentPane = authorAddFrame.getContentPane();

			//Layer do painel
			authorAddLayered.setBackground(Color.black);

			//Borda do painel
			authorAddPanel.setBorder(new CompoundBorder(new TitledBorder("Adicionar Autor"),new EmptyBorder(5, 5, 5, 5)));

			//Layout do painel
			GroupLayout authorAddPanelLayout = new GroupLayout(authorAddPanel);
			authorAddPanel.setLayout(authorAddPanelLayout);
			authorAddPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				authorAddPanelLayout.createParallelGroup()
					.addGroup(authorAddPanelLayout.createSequentialGroup()
						.addGroup(authorAddPanelLayout.createParallelGroup()
							.addGroup(authorAddPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(authorCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(authorSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(authorAddPanelLayout.createSequentialGroup()
								.addGap(190, 190, 190)
								.addGroup(authorAddPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(authorFnameLabel)
									.addComponent(authorNameLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(authorAddPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
									.addComponent(authorFnameField)
									.addComponent(authorNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(224, Short.MAX_VALUE))
			);
			authorAddPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				authorAddPanelLayout.createParallelGroup()
					.addGroup(authorAddPanelLayout.createSequentialGroup()
						.addContainerGap(188, Short.MAX_VALUE)
						.addGroup(authorAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(authorAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorFnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(authorFnameLabel))
						.addGap(137, 137, 137)
						.addGroup(authorAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(authorCancel)
							.addComponent(authorSave))
						.addGap(67, 67, 67))
			);
				
			authorAddLayered.add(authorAddPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o authorAddPanel em authorAddLayered
			authorAddPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de authorAddPanel (x,y,h,w)
			

			GroupLayout authorAddFrameContentPaneLayout = new GroupLayout(authorAddFrameContentPane); // cria um layout para o container principal
			authorAddFrameContentPane.setLayout(authorAddFrameContentPaneLayout);
			authorAddFrameContentPaneLayout.setHorizontalGroup(
				authorAddFrameContentPaneLayout.createParallelGroup()
					.addGroup(authorAddFrameContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(authorAddLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			authorAddFrameContentPaneLayout.setVerticalGroup(
				authorAddFrameContentPaneLayout.createParallelGroup()
					.addGroup(authorAddFrameContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(authorAddLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);

			authorCancel.addActionListener(new CancelAddAuthorButton(conn, this));
			authorSave.addActionListener(new AddAuthorButton(conn, authorNameField, authorFnameField, this));

			authorAddFrame.setVisible(true); // liga a janela
			authorAddFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela 
			authorAddFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

	void closeFrame() {
	    this.authorAddFrame.dispose();
    }
}

class AddAuthorButton implements ActionListener {

    private Connection conn;
    private AuthorAddFrame authorAddFrame;
    private JTextField fName, lName;

    AddAuthorButton(Connection conn, JTextField fName, JTextField lName, AuthorAddFrame authorAddFrame) {
        this.conn = conn;
        this.fName = fName;
        this.lName = lName;
        this.authorAddFrame = authorAddFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((!fName.getText().equals("")) || (!lName.getText().equals(""))) {
            Controller con = new Controller(conn);
            try {
                con.insertAuthor(fName.getText(), lName.getText());
                this.authorAddFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(0); // inicie a proxima janela
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

class CancelAddAuthorButton implements ActionListener {

    private Connection conn;
    private AuthorAddFrame authorAddFrame;

    CancelAddAuthorButton(Connection conn, AuthorAddFrame authorAddFrame) {
        this.conn = conn;
        this.authorAddFrame = authorAddFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.authorAddFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(0); // inicie a proxima janela
    }
}