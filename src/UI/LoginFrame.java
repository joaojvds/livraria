package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

import controller.Controller;

public class LoginFrame {

	//variaveis globais
	private JFrame loginFrame;

    void beginLogin() {

		//declaração dos objetos
		loginFrame = new JFrame();
        JLayeredPane loginLayer = new JLayeredPane();
        JPanel loginPanel = new JPanel();
        JTextField userTextField = new JTextField();
        JLabel userLabel = new JLabel("Usuario :");
        JPasswordField passwordField = new JPasswordField();
        JLabel passwordLabel = new JLabel("Senha :");
        JLabel titleLabel = new JLabel("Livraria");
        JButton loginButton = new JButton("Log in");

		//Frame principal		
		loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loginFrame.setTitle("Livraria");
		loginFrame.setMinimumSize(new Dimension(750, 550));
		loginFrame.setMaximizedBounds(null);
		loginFrame.setResizable(false);
		Container loginFrameContentPane = loginFrame.getContentPane(); //container principal

			//Layer do bloco de login, adicionado somente para ter as bordas no painel de login			
			loginLayer.setBorder(new EtchedBorder());
			loginLayer.setAlignmentX(0.0F);

				//seta a cor do painel
				loginPanel.setBackground(Color.white);
				loginPanel.setBorder(LineBorder.createBlackLineBorder());

				//seta a fonte do titulo
				titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | Font.BOLD, titleLabel.getFont().getSize() + 6f));

				//atribuição do tipo de layout ao painel de login ""Nintendo Boy N�O MEXA NOS PARAMETROS!!!""
				GroupLayout loginPanelLayout = new GroupLayout(loginPanel);
				loginPanel.setLayout(loginPanelLayout); // seta a o layout em loginPanel

				loginPanelLayout.setHorizontalGroup( //adiciona paineis horizontal 
					loginPanelLayout.createParallelGroup() //adiciona painel com arranjos em paralelo de mesmo espa�amento 
						.addGroup(GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()	//adiciona um painel com arranjos em sequencia
							.addGroup(loginPanelLayout.createParallelGroup() // adiciona um painel paralelo dentro painel sequencial
								.addGroup(loginPanelLayout.createSequentialGroup() // painel dos text fields 
									.addGap(39, 39, 39) // define o espa�amento dos itens do painel (minimo, preferencial, maximo) espa�amento definido em pixel
									.addGroup(loginPanelLayout.createParallelGroup() // painel do usuario e senha
										.addGroup(loginPanelLayout.createSequentialGroup() // painel da label user e seu text field
											.addComponent(userLabel) //adiciona a label "nome :" no painel
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // relaciona a label com um componente proximo 
											.addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)) // adiciona o text field 
																																	   //e seta o tamanho padr�o
										.addGroup(loginPanelLayout.createSequentialGroup() // painel da label senha e do password field
											.addGap(6, 6, 6) // espa�amentos do painel horizontal
											.addComponent(passwordLabel) 
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // faz mesma coisa do painel acima 
											.addGroup(loginPanelLayout.createParallelGroup()
												.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(loginPanelLayout.createSequentialGroup() // painel do titulo
									.addGap(113, 113, 113) // espa�amentos horizontais da label do titulo
									.addComponent(titleLabel))) // adiciona a label do titulo no painel
							.addGap(64, 64, 64))
				); // fim dos parametros horizontais

				loginPanelLayout.setVerticalGroup( // adiciona paineis verticais
					loginPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
							.addGap(25, 25, 25) 
							.addComponent(titleLabel) // pode parecer que eu estou adicionando a variavel duas vezes, mas esta certo 
													  // no trecho acima eu estava adicionando a variavel na posição do eixo y agora estou adicionando no eixo x
							.addGap(18, 18, 18)
							.addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(userTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordLabel))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(loginButton)
							.addGap(32, 32, 32))
				); // fim dos parametros verticais
				
			loginLayer.add(loginPanel, JLayeredPane.DEFAULT_LAYER); // adiciona o loginPanel em loginLayer
			loginPanel.setBounds(0, 0, 300, 196); // seta o tamanho e a posição de loginPanel (x,y,h,w)
			

		GroupLayout loginFrameContentPaneLayout = new GroupLayout(loginFrameContentPane); // adiciona o layout ao container principal
		loginFrameContentPane.setLayout(loginFrameContentPaneLayout);
		loginFrameContentPaneLayout.setHorizontalGroup(
			loginFrameContentPaneLayout.createParallelGroup()
				.addGroup(loginFrameContentPaneLayout.createSequentialGroup()
					.addContainerGap(223, Short.MAX_VALUE)
					.addComponent(loginLayer, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addGap(224, 224, 224))
		);
		loginFrameContentPaneLayout.setVerticalGroup(
			loginFrameContentPaneLayout.createParallelGroup()
				.addGroup(loginFrameContentPaneLayout.createSequentialGroup()
					.addGap(172, 172, 172)
					.addComponent(loginLayer, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(149, Short.MAX_VALUE))
		);

		loginButton.addActionListener(new LoginButton(userTextField, passwordField,this));
//
		loginFrame.setVisible(true); // liga a janela
		loginFrame.pack(); // faz os tamanhos dos componentes se adaptarem a tela 
		loginFrame.setLocationRelativeTo(null); // faz a janela ser iniciada no centro da tela
	}

	void closeFrame() {
        this.loginFrame.dispose();
    }
}

class LoginButton implements ActionListener {

    private JTextField userTextField;
    private JPasswordField passwordField;
    private LoginFrame login;

    LoginButton(JTextField userTextField, JPasswordField passwordField, LoginFrame login) {
        this.userTextField = userTextField;
        this.passwordField = passwordField;
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try { //String.valueOf converte um vetor de char para uma string
            login.closeFrame();
            (new Controller(new Controller().getConn(
                    userTextField.getText(),String.valueOf(passwordField.getPassword())))).beginMainFrame(0);
        } catch (ClassNotFoundException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}