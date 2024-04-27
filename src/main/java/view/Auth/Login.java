package Pizzaria.src.main.java.view.Auth;

import java.awt.*;
import javax.swing.*;

public class Login extends {
    JPanel loginPanel;
    JLabel authTitle;
    JLabel userLabel;
    TextField lgUser;
    JLabel ip;
    JTextField ipAdd;
    JLabel passwordLabel;
    JPasswordField lgPwd;
    JButton lgButton;
    GridBagConstraints constraints;

    public Login(){
        loginPanel      = new JPanel(new GridBagLayout());
        authTitle       = new JLabel("Entrar");
        userLabel       = new JLabel("Usuário:");
        lgUser          = new JTextField(15);
		ip              = new JLabel("IP: ");
		ipAdd           = new JTextField(15);
        passwordLabel   = new JLabel("Senha:   ");
        lgPwd           = new JPasswordField(15);
        lgButton        = new JButton("Submeter"); 
        constraints     = new GridBagConstraints();

        setTitle("Pizzaria - Login");
		setSize(1024, 642);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));
        setVisible(true);

        //style
        loginPanel.setBackground(new Color(0xfebd00));
        authTitle.setFont(new Font("Arial", Font.BOLD, 23));
        authTitle.setBorder(new EmptyBorder(0, 0, 25 ,0));
        lgUser.setPreferredSize(new Dimension(0, 27));
        lgPwd.setPreferredSize(new Dimension(0, 27));
        lgButton.setFocusable(false);
        lgButton.setBackground(new Color(0x1E90FF));
        lgButton.setForeground(new Color(0xFFFFFF));
        
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 7, 5, 7);

        loginPanel.add(authTitle, constraints);
        JPanel userDiv = new JPanel();
        userDiv.add(userLabel);
        userDiv.add(lgUser);
        loginPanel.add(userDiv, constraints);
		JPanel ipDiv = new JPanel();
		ipDiv.add(ip);
		ipDiv.add(ipAdd);
		loginPanel.add(ipDiv, constraints);
        JPanel passwordDiv = new JPanel();
        passwordDiv.add(passwordLabel);
        passwordDiv.add(lgPwd);
        loginPanel.add(passwordDiv, constraints);
        loginPanel.add(lgButton, constraints);
        userDiv.setBackground(getForeground());
		ipDiv.setBackground(getForeground());
        passwordDiv.setBackground(getForeground());

    }

}
