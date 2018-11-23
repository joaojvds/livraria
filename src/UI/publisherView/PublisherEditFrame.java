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

public class PublisherEditFrame {

	//variaveis global
	private JFrame publisherEditFrame;
	private Publisher publisher;
	private JTextField publisherIdField, publisherNameField, publisherUrlField;

	public PublisherEditFrame(Publisher publisher) {
	    this.publisher = publisher;
	    this.publisherIdField = new JTextField(publisher.getId());
        this.publisherIdField.setEditable(false);
	    this.publisherNameField = new JTextField(publisher.getName());
	    this.publisherUrlField = new JTextField(publisher.getUrl());
    }

	public void beginPublisherEditFrame(Connection conn) {

		//declaração dos objetos
		publisherEditFrame = new JFrame();
        JLayeredPane publisherEditLayered = new JLayeredPane();
        JPanel publisherEditPanel = new JPanel();
        JLabel publisherNameLabel = new JLabel("Nome da Editora :");
        JLabel publisherIdLabel = new JLabel("Id da Editora :");
        JLabel publisherUrlLabel = new JLabel("Site da Editora :");
        JButton publisherCancel = new JButton("Cancelar");
        JButton publisherSave = new JButton("Salvar");

		//Container principal	
		publisherEditFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		publisherEditFrame.setTitle("Livraria");
		publisherEditFrame.setMinimumSize(new Dimension(750, 550));
		publisherEditFrame.setMaximizedBounds(null);
		publisherEditFrame.setResizable(false);
		Container publisherEditFrameContentPane = publisherEditFrame.getContentPane();

			//Layer do painel
			publisherEditLayered.setBackground(Color.black);

			//Borda do painel
			publisherEditPanel.setBorder(new CompoundBorder(new TitledBorder("Editar Editora"),	new EmptyBorder(5, 5, 5, 5)));

			//Layout do painel
			GroupLayout publisherEditPanelLayout = new GroupLayout(publisherEditPanel);
			publisherEditPanel.setLayout(publisherEditPanelLayout);
			publisherEditPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				publisherEditPanelLayout.createParallelGroup()
					.addGroup(publisherEditPanelLayout.createSequentialGroup()
						.addGroup(publisherEditPanelLayout.createParallelGroup()
							.addGroup(publisherEditPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(publisherCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(publisherSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(publisherEditPanelLayout.createSequentialGroup()
								.addGap(165, 165, 165)
								.addGroup(publisherEditPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(publisherNameLabel)
									.addComponent(publisherIdLabel)
									.addComponent(publisherUrlLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(publisherEditPanelLayout.createParallelGroup()
									.addGroup(publisherEditPanelLayout.createSequentialGroup()
										.addComponent(publisherIdField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
									.addGroup(publisherEditPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(publisherUrlField)
										.addComponent(publisherNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(141, Short.MAX_VALUE))
			);
			publisherEditPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				publisherEditPanelLayout.createParallelGroup()
					.addGroup(publisherEditPanelLayout.createSequentialGroup()
						.addGap(130, 130, 130)
						.addGroup(publisherEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherIdLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
						.addGroup(publisherEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(publisherEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherUrlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherUrlLabel))
						.addGap(137, 137, 137)
						.addGroup(publisherEditPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherCancel)
							.addComponent(publisherSave))
						.addGap(67, 67, 67))
			);

			publisherEditLayered.add(publisherEditPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o publisherEditPanel em publisherEditLayered
			publisherEditPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de publisherEditPanel (x,y,h,w)
		

		GroupLayout publisherEditFrameContentPaneLayout = new GroupLayout(publisherEditFrameContentPane); // cria um layout para o container principal
		publisherEditFrameContentPane.setLayout(publisherEditFrameContentPaneLayout);
		publisherEditFrameContentPaneLayout.setHorizontalGroup(
			publisherEditFrameContentPaneLayout.createParallelGroup()
				.addGroup(publisherEditFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(publisherEditLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		publisherEditFrameContentPaneLayout.setVerticalGroup(
			publisherEditFrameContentPaneLayout.createParallelGroup()
				.addGroup(publisherEditFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(publisherEditLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		publisherSave.addActionListener(new EditPublisherButton(conn, this));
		publisherCancel.addActionListener(new CancelEditButton(conn, this));
		
		publisherEditFrame.setVisible(true); // liga a janela
		publisherEditFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela
		publisherEditFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

    public Publisher getPublisher() {
        return publisher;
    }

    JTextField getPublisherNameField() {
        return publisherNameField;
    }

    JTextField getPublisherUrlField() {
        return publisherUrlField;
    }

    void closeFrame() {
	    this.publisherEditFrame.dispose();
    }
}

class EditPublisherButton implements ActionListener {

    private Connection conn;
    private PublisherEditFrame publisherEditFrame;

    EditPublisherButton(Connection conn, PublisherEditFrame publisherEditFrame) {
        this.conn = conn;
        this.publisherEditFrame = publisherEditFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller con = new Controller(conn);
        if(!publisherEditFrame.getPublisherNameField().getText().equals("") ||
                !publisherEditFrame.getPublisherUrlField().getText().equals("")) {
            try {
                con.updatePublisher(publisherEditFrame.getPublisher().getId(),
                        publisherEditFrame.getPublisherNameField().getText(),
                        publisherEditFrame.getPublisherUrlField().getText());
                this.publisherEditFrame.closeFrame(); // fecha a janela atual
                con.beginMainFrame(2); // inicie a proxima janela
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
    private PublisherEditFrame publisherEditFrame;

    CancelEditButton(Connection conn, PublisherEditFrame publisherDropFrame) {
        this.conn = conn;
        this.publisherEditFrame = publisherDropFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.publisherEditFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(2); // inicie a proxima janela
    }
}