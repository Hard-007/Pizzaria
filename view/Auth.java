package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.net.*;
import java.net.URL.*;

public class Auth extends JFrame{
    GridBagConstraints constraints;
    JTextField user;
    JPasswordField password;
    JButton authOptions, lgButton, sgButton;
    JLabel imgLabel, logoLabel, brandName, authTitle, userLabel, passwordLabel;
    JPanel AuthMainPanel, AuthPanel, AuthHeader, loginPanel, signinPanel;
    ImageIcon img, imgResized, logoImg, logoResized;
    Image imgResize, logoResize;

    public Auth(){
        setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));

        imgLabel = new JLabel();
        img = new ImageIcon("../assets/pizza.jpg");
        imgResize = img.getImage().getScaledInstance(512, 642, Image.SCALE_SMOOTH);
        imgResized = new ImageIcon(imgResize);
        imgLabel.setIcon(imgResized);

        logoLabel = new JLabel();
        logoImg = new ImageIcon("../assets/logo.jpg");
        logoResize = logoImg.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logoResized = new ImageIcon(logoResize);
        logoLabel.setIcon(logoResized);

        AuthMainPanel = new JPanel(new BorderLayout());
        AuthPanel = new JPanel(new GridBagLayout());
        AuthPanel.setBackground(new Color(0xFFFFFF));
        
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(0xfebd00));
        authTitle = new JLabel("Entrar");
        authTitle.setFont(new Font("Arial", Font.BOLD, 23));
        authTitle.setBorder(new EmptyBorder(0, 0, 25 ,0));
        userLabel = new JLabel("Usuário:");
        user = new JTextField(15);
        user.setPreferredSize(new Dimension(0, 27));
        passwordLabel = new JLabel("Password:");
        password = new JPasswordField(15);
        password.setPreferredSize(new Dimension(0, 27));
        lgButton = new JButton("Submeter");
        lgButton.setFocusable(false);
        lgButton.setBackground(new Color(0x1E90FF));
        lgButton.setForeground(new Color(0xFFFFFF));
        
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 7, 5, 7);

        loginPanel.add(authTitle, constraints);
        JPanel userDiv = new JPanel();
        userDiv.add(userLabel);
        userDiv.add(user);
        loginPanel.add(userDiv, constraints);
        JPanel passwordDiv = new JPanel();
        passwordDiv.add(passwordLabel);
        passwordDiv.add(password);
        loginPanel.add(passwordDiv, constraints);
        loginPanel.add(lgButton, constraints);
        userDiv.setBackground(getForeground());
        passwordDiv.setBackground(getForeground());        

        AuthHeader = new JPanel(new BorderLayout());
        AuthHeader.setBackground(new Color(0x241e20));
        brandName = new JLabel("M GRILL");
        brandName.setHorizontalAlignment(JLabel.CENTER);
        brandName.setFont(new Font("Arial", Font.BOLD, 27));
        brandName.setForeground(Color.WHITE);
        authOptions = new JButton("Cadastrar");
        authOptions.setFont(new Font("Arial", Font.BOLD, 15));
        authOptions.setBackground(new Color(0x241e20));
        authOptions.setForeground(new Color(0xFFFFFF));
        authOptions.setFocusable(false);
        authOptions.setBorder(BorderFactory.createEtchedBorder());
        authOptions.setBorder(new EmptyBorder(7, 17 ,7, 30));
        
        add(AuthMainPanel);
        AuthMainPanel.add(AuthHeader, BorderLayout.NORTH);
        AuthHeader.add(logoLabel, BorderLayout.WEST);
        AuthHeader.add(brandName, BorderLayout.CENTER);
        AuthHeader.add(authOptions, BorderLayout.EAST);
        AuthMainPanel.add(imgLabel, BorderLayout.WEST);
        AuthMainPanel.add(AuthPanel, BorderLayout.CENTER);
        AuthPanel.add(loginPanel);
        
    }

    /*public static void main(String[] args) {
        new Auth().setVisible(true);
    }*/
    
}
