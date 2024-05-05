package src.java.views.auth;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.resources.config.DBConnection;

public class Signin extends JPanel implements ActionListener{
    GridBagConstraints constraints;

    JPanel signinPanel  ;
    JPanel userDiv      ;
    JPanel nomeDiv      ;
    JPanel apelidoDiv   ;
    JPanel emailDiv     ;
    JPanel contactoDiv  ;
    JPanel passwordDiv  ;

    JLabel authTitle;
    JLabel userLabel    ;
    JLabel nomeLabel    ;
    JLabel apelidoLabel ;
    JLabel emailLabel   ; 
    JLabel contactoLabel;
    JLabel passwordLabel;

    JTextField user     ;
    JTextField nome     ;
    JTextField apelido  ;
    JTextField email    ;
    JTextField contacto ;
    JPasswordField password ;

    JButton sgButton;

    public Signin(){
        /*setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
        */
        //setPreferredSize(new Dimension(400, 400));
		setBackground(new Color(0xfebd00));

        signinPanel     = new JPanel(new GridBagLayout());
        passwordDiv     = new JPanel();
        userDiv         = new JPanel();
        nomeDiv         = new JPanel();
        apelidoDiv      = new JPanel();
        emailDiv        = new JPanel();
        contactoDiv     = new JPanel();
        
        authTitle       = new JLabel("Cadastrar");
        userLabel       = new JLabel("  Usuário:");
        nomeLabel       = new JLabel("      Nome:");
        apelidoLabel    = new JLabel("   Apelido:");
        emailLabel      = new JLabel("      E-mail:");
        contactoLabel   = new JLabel("   Contacto:");
        passwordLabel   = new JLabel(" Password:");
        user            = new JTextField(15);
        nome            = new JTextField(15);
        apelido         = new JTextField(15);
        email           = new JTextField(15);
        contacto        = new JTextField(15);
        password        = new JPasswordField(15);
        sgButton        = new JButton("Submeter");

        constraints         = new GridBagConstraints();
        constraints.gridx   = 0;
        constraints.gridy   = GridBagConstraints.RELATIVE;
        constraints.anchor  = GridBagConstraints.CENTER;
        constraints.insets  = new Insets(5, 7, 5, 7);
        
        signinPanel();
        add(signinPanel, BorderLayout.CENTER);

    }

    public void signinPanel(){
        //signinPanel.setBackground(new Color(0xfebd00));
        authTitle   .setFont(new Font("Arial", Font.BOLD, 23));
        authTitle   .setBorder(new EmptyBorder(0, 0, 25 ,0));
        user        .setPreferredSize(new Dimension(0, 30));
        nome        .setPreferredSize(new Dimension(0, 30));
        apelido     .setPreferredSize(new Dimension(0, 30));
        email       .setPreferredSize(new Dimension(0, 30));
        contacto    .setPreferredSize(new Dimension(0, 30));
        password    .setPreferredSize(new Dimension(0, 30));
        sgButton    .setFocusable(false);
        sgButton    .setBackground(new Color(0x1E90FF));
        sgButton    .setForeground(new Color(0xFFFFFF));
        sgButton    .addActionListener(this);
        /*userDiv   .setBackground(getForeground());
		nomeDiv     .setBackground(getForeground());
		apelidoDiv  .setBackground(getForeground());
		emailDiv    .setBackground(getForeground());
		contactoDiv .setBackground(getForeground());
        passwordDiv .setBackground(getForeground());*/

        userDiv     .add(userLabel);
        userDiv     .add(user);
        nomeDiv     .add(nomeLabel);
        nomeDiv     .add(nome);
        apelidoDiv  .add(apelidoLabel);
        apelidoDiv  .add(apelido);
        emailDiv    .add(emailLabel);
        emailDiv    .add(email);
        contactoDiv .add(contactoLabel);
        contactoDiv .add(contacto);
        passwordDiv .add(passwordLabel);
        passwordDiv .add(password);

        signinPanel.add(authTitle, constraints);
        signinPanel.add(userDiv, constraints);
        signinPanel.add(nomeDiv, constraints);
        signinPanel.add(apelidoDiv, constraints);
        signinPanel.add(emailDiv, constraints);
        signinPanel.add(contactoDiv, constraints);
        signinPanel.add(passwordDiv, constraints);
        signinPanel.add(sgButton, constraints);
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == sgButton){
            System.out.println("sign");
        }
    }

   /*public static void main(String[] args){
        new Signin().setVisible(true);
    }*/

}

/*
 * 
 * 
 *          String username = sgUser.getText();
			String name = sgNome.getText();
			String surname = sgApelido.getText();
			String email = sgEmail.getText();
			String contacto = sgContacto.getText();
			String password = String.valueOf(sgPwd.getPassword());
			String sql = "INSERT INTO users (username, nome, apelido, email, contacto, password, category) VALUES (?, ?, ?, ?, ?, ?, 'user')";
			
			PreparedStatement ps = null;
			try {
				ps = DBConnection.getConexao().prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, name);
				ps.setString(3, surname);
				ps.setString(4, email);
				ps.setString(5, contacto);
				ps.setString(6, password);
				ps.execute();
				ps.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(signinPanel, "Cadastro sucedido, faça login");
			signinPanel.setVisible(false);
			loginPanel.setVisible(true);
			////loginpanel2.setVisible(true);
 * 
 * 
 */