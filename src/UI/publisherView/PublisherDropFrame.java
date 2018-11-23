package UI.publisherView;

import controller.Controller;
import model.Publisher;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

public class PublisherDropFrame {

	//variaveis global
	private JFrame publisherDropFrame;
    private Publisher publisher;
    private JTextField publisherIdField, publisherNameField, publisherUrlField;

    public PublisherDropFrame(Publisher publisher) {
        this.publisher = publisher;
        this.publisherIdField = new JTextField(publisher.getId());
        this.publisherNameField = new JTextField(publisher.getName());
        this.publisherUrlField = new JTextField(publisher.getUrl());
    }

    public void beginPublisherDropFrame(Connection conn) {

		//declaração dos objetos
		publisherDropFrame = new JFrame();
        JLayeredPane publisherDropLayered = new JLayeredPane();
        JPanel publisherDropPanel = new JPanel();
        JLabel publisherNameLabel = new JLabel("Nome da Editora :");
        JLabel publisherIdLabel = new JLabel("Id da Editora :");
        JLabel publisherUrlLabel = new JLabel("Site da Editora :");
        JButton publisherCancel = new JButton("Cancelar");
        JButton publisherErase = new JButton("Apagar");

		//Container principal
		publisherDropFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		publisherDropFrame.setTitle("Livraria");
		publisherDropFrame.setMinimumSize(new Dimension(750, 550));
		publisherDropFrame.setMaximizedBounds(null);
		publisherDropFrame.setResizable(false);
		Container publisherDropFrameContentPane = publisherDropFrame.getContentPane();

			//Layer do painel
			publisherDropLayered.setBackground(Color.black);

			//Borda do painel	
			publisherDropPanel.setBorder(new CompoundBorder(new TitledBorder("Apagar Editora"),	new EmptyBorder(5, 5, 5, 5)));

            //impedi o usuario de editar esses campos
			publisherIdField.setEditable(false);
			publisherNameField.setEditable(false);
			publisherUrlField.setEditable(false);

			//Layout do painel
			GroupLayout publisherDropPanelLayout = new GroupLayout(publisherDropPanel);
			publisherDropPanel.setLayout(publisherDropPanelLayout);
			publisherDropPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				publisherDropPanelLayout.createParallelGroup()
					.addGroup(publisherDropPanelLayout.createSequentialGroup()
						.addGroup(publisherDropPanelLayout.createParallelGroup()
							.addGroup(publisherDropPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(publisherCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(publisherErase, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(publisherDropPanelLayout.createSequentialGroup()
								.addGap(165, 165, 165)
								.addGroup(publisherDropPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(publisherNameLabel)
									.addComponent(publisherIdLabel)
									.addComponent(publisherUrlLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(publisherDropPanelLayout.createParallelGroup()
									.addGroup(publisherDropPanelLayout.createSequentialGroup()
										.addComponent(publisherIdField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
									.addGroup(publisherDropPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(publisherUrlField)
										.addComponent(publisherNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(141, Short.MAX_VALUE))
			);
			publisherDropPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				publisherDropPanelLayout.createParallelGroup()
					.addGroup(publisherDropPanelLayout.createSequentialGroup()
						.addGap(130, 130, 130)
						.addGroup(publisherDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherIdLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
						.addGroup(publisherDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(publisherDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherUrlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherUrlLabel))
						.addGap(137, 137, 137)
						.addGroup(publisherDropPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherCancel)
							.addComponent(publisherErase))
						.addGap(67, 67, 67))
			);
				
			publisherDropLayered.add(publisherDropPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o publisherDropPanel em publisherDropLayered
			publisherDropPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de publisherDropPanel (x,y,h,w)
		

		GroupLayout publisherDropFrameContentPaneLayout = new GroupLayout(publisherDropFrameContentPane); // cria um layout para o container principal
		publisherDropFrameContentPane.setLayout(publisherDropFrameContentPaneLayout);
		publisherDropFrameContentPaneLayout.setHorizontalGroup(
			publisherDropFrameContentPaneLayout.createParallelGroup()
				.addGroup(publisherDropFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(publisherDropLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		publisherDropFrameContentPaneLayout.setVerticalGroup(
			publisherDropFrameContentPaneLayout.createParallelGroup()
				.addGroup(publisherDropFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(publisherDropLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		publisherErase.addActionListener(new DeletePublsherButton(conn, this));
		publisherCancel.addActionListener(new CancelDeleteButton(conn, this));
		
		publisherDropFrame.setVisible(true); // liga a janela
		publisherDropFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela
		publisherDropFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

    public Publisher getPublisher() {
        return publisher;
    }

    void closeFrame() {
	    this.publisherDropFrame.dispose();
    }
}

/*class SearchPublsherButton implements ActionListener {

    private Connection conn;
    private JTextField id, name, url;

    SearchPublsherButton(Connection conn, JTextField publisherIdField, JTextField publisherNameField,JTextField publisherUrlField) {
        this.conn = conn;
        this.name = publisherNameField;
        this.url = publisherUrlField;

        this.id = publisherIdField;
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException {
        if (!id.getText().equals("")) {
            try {
                Publisher publisher = (new Controller(conn)).searchPublisher(this.id.getText());
                this.name.setText(publisher.getName());
                this.url.setText(publisher.getUrl());
            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null, "Erro \n" + s.getMessage()
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Erro \ncampo id em branco"
                    ,"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}*/

class DeletePublsherButton implements ActionListener {

    private Connection conn;
    private PublisherDropFrame publisherDropFrame;

    DeletePublsherButton(Connection conn, PublisherDropFrame publisherDropFrame) {
        this.conn = conn;
        this.publisherDropFrame = publisherDropFrame;
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
                con.deletePublisher(publisherDropFrame.getPublisher().getId());
                this.publisherDropFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(2); // inicie a proxima janela
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }
}

class CancelDeleteButton implements ActionListener {

    private Connection conn;
    private PublisherDropFrame publisherDropFrame;

    CancelDeleteButton(Connection conn, PublisherDropFrame publisherDropFrame) {
        this.conn = conn;
        this.publisherDropFrame = publisherDropFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.publisherDropFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(2); // inicie a proxima janela
    }
}