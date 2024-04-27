package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DLogin extends JPanel{
    GridBagConstraints constraints;
    JTextField user;
    JPasswordField password;
    JButton lgButton;
    JLabel authTitle, userLabel, passwordLabel;
    JPanel loginPanel;

    public DLogin(){
        /*setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));*/

        
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(0xfebd00));
        authTitle = new JLabel("Entrar");
        authTitle.setFont(new Font("Arial", Font.BOLD, 23));
        authTitle.setBorder(new EmptyBorder(0, 0, 25 ,0));
        userLabel = new JLabel("Usuário:");
        user = new JTextField(15);
        user.setPreferredSize(new Dimension(0, 27));
        passwordLabel = new JLabel("Senha:  ");
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
        //userDiv.setBackground(getForeground());
        //passwordDiv.setBackground(getForeground());
        
        add(loginPanel, BorderLayout.CENTER);

    }

   /*public static void main(String[] args){
        new DLogin().setVisible(true);
    }*/

}
