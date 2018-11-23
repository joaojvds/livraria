package UI.publisherView;

import controller.Controller;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

public class PublisherAddFrame {

	//variaveis global
	private JFrame publisherAddFrame;

    public void beginPublisherAddFrame(Connection conn) {

		//declaração dos objetos
		publisherAddFrame = new JFrame();
        JLayeredPane publisherAddLayered = new JLayeredPane();
        JPanel publisherAddPanel = new JPanel();
        JTextField publisherNameField = new JTextField();
        JLabel publisherNameLabel = new JLabel("Nome da Editora :");
        JTextField publisherUrlField = new JTextField();
        JLabel publisherUrlLabel = new JLabel("Site da Editora :");
        JButton publisherCancel = new JButton("Cancelar");
        JButton publisherSave = new JButton("Salvar");

		//Container principal
		publisherAddFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		publisherAddFrame.setTitle("Livraria");
		publisherAddFrame.setMinimumSize(new Dimension(750, 550));
		publisherAddFrame.setMaximizedBounds(null);
		publisherAddFrame.setResizable(false);
		Container publisherAddFrameContentPane = publisherAddFrame.getContentPane();

			//Layer do painel
			publisherAddLayered.setBackground(Color.black);

			//Borda do painel
			publisherAddPanel.setBorder(new CompoundBorder(new TitledBorder("Adicionar Editora"), new EmptyBorder(5, 5, 5, 5)));

			//Layout do painel
			GroupLayout publisherAddPanelLayout = new GroupLayout(publisherAddPanel);
			publisherAddPanel.setLayout(publisherAddPanelLayout);
			publisherAddPanelLayout.setHorizontalGroup( // adiciona os parametros do eixo x (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				publisherAddPanelLayout.createParallelGroup()
					.addGroup(publisherAddPanelLayout.createSequentialGroup()
						.addGroup(publisherAddPanelLayout.createParallelGroup()
							.addGroup(publisherAddPanelLayout.createSequentialGroup()
								.addGap(232, 232, 232)
								.addComponent(publisherCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(publisherSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(publisherAddPanelLayout.createSequentialGroup()
								.addGap(165, 165, 165)
								.addGroup(publisherAddPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(publisherNameLabel)
									.addComponent(publisherUrlLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(publisherAddPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
									.addComponent(publisherUrlField)
									.addComponent(publisherNameField, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(224, Short.MAX_VALUE))
			);
			publisherAddPanelLayout.setVerticalGroup( // adiciona os parametros do eixo y (para ver a descrição dos parametros veja o arquivo LoginFrame.java)
				publisherAddPanelLayout.createParallelGroup()
					.addGroup(publisherAddPanelLayout.createSequentialGroup()
						.addContainerGap(188, Short.MAX_VALUE)
						.addGroup(publisherAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherNameLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(publisherAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherUrlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(publisherUrlLabel))
						.addGap(137, 137, 137)
						.addGroup(publisherAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(publisherCancel)
							.addComponent(publisherSave))
						.addGap(67, 67, 67))
			);
				
			publisherAddLayered.add(publisherAddPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o publisherAddPanel em publisherAddLayered
			publisherAddPanel.setBounds(1, 1, 726, 494); // seta o tamanho e a posição de publisherAddPanel (x,y,h,w)
			

		GroupLayout publisherAddFrameContentPaneLayout = new GroupLayout(publisherAddFrameContentPane); // cria um layout para o container principal
		publisherAddFrameContentPane.setLayout(publisherAddFrameContentPaneLayout);
		publisherAddFrameContentPaneLayout.setHorizontalGroup(
			publisherAddFrameContentPaneLayout.createParallelGroup()
				.addGroup(publisherAddFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(publisherAddLayered, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		publisherAddFrameContentPaneLayout.setVerticalGroup(
			publisherAddFrameContentPaneLayout.createParallelGroup()
				.addGroup(publisherAddFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(publisherAddLayered, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

        //Action Listener dos botões
		publisherSave.addActionListener(new AddPublisherButton(conn, this, publisherNameField, publisherUrlField));
		publisherCancel.addActionListener(new CancelAddPublisherButton(conn, this));
		
		publisherAddFrame.setVisible(true); // liga a janela
		publisherAddFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela
		publisherAddFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

	void closeFrame() {
	    this.publisherAddFrame.dispose();
    }
}

class AddPublisherButton implements ActionListener{

    private Connection conn;
    private JTextField name, url;
    private PublisherAddFrame publisherAddFrame;

    AddPublisherButton(Connection conn, PublisherAddFrame publisherAddFrame, JTextField publisherNameField, JTextField publisherUrlField) {
        this.conn = conn;
        this.name = publisherNameField;
        this.url = publisherUrlField;
        this.publisherAddFrame = publisherAddFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller con = new Controller(conn);
        try {
            con.insertPublisher(name.getText(), url.getText());
            publisherAddFrame.closeFrame();
            con.beginMainFrame(2);
        } catch (Exception s) {
            s.printStackTrace();
        }
    }
}

class CancelAddPublisherButton implements ActionListener{

    private Connection conn;
    private PublisherAddFrame publisherAddFrame;

    CancelAddPublisherButton(Connection conn, PublisherAddFrame publisherAddFrame) {
        this.conn = conn;
        this.publisherAddFrame = publisherAddFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.publisherAddFrame.closeFrame(); // fecha a janela atual
        (new Controller(conn)).beginMainFrame(2); // inicie a proxima janela
    }
}