package src.java.views.auth;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.resources.config.DBConnection;

public class DLogin extends JPanel implements ActionListener{
    GridBagConstraints constraints;

    JPanel loginPanel   ;
    JPanel userDiv      ;
    JPanel passwordDiv  ;

    JLabel authTitle    ;
    JLabel userLabel    ;
    JLabel ipLabel      ;
    JLabel passwordLabel;

    JTextField user ;
    JTextField ip   ;
    JPasswordField password;

    JButton lgButton;

    public DLogin(){
        /*setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
        */
        //setPreferredSize(new Dimension(400, 400));
		setBackground(new Color(0xfebd00));

        loginPanel  = new JPanel(new GridBagLayout());
        userDiv     = new JPanel();
        passwordDiv = new JPanel();

        authTitle       = new JLabel("  Entrar");
        userLabel       = new JLabel("  Usuário:");
        ipLabel         = new JLabel("            IP:");
        passwordLabel   = new JLabel("Password:");

        
        user        = new JTextField(15);
		ip          = new JTextField(15);
        password    = new JPasswordField(15);
        lgButton    = new JButton("Submeter");
        
        constraints         = new GridBagConstraints();
        constraints.gridx   = 0;
        constraints.gridy   = GridBagConstraints.RELATIVE;
        constraints.anchor  = GridBagConstraints.CENTER;
        constraints.insets  = new Insets(5, 7, 5, 7);

        loginPanel();
        add(loginPanel, BorderLayout.CENTER);

    }

    public void loginPanel(){
        //loginPanel.setBackground(new Color(0xfebd00));
        authTitle   .setFont(new Font("Arial", Font.BOLD, 23));
        authTitle   .setBorder(new EmptyBorder(0, 0, 25 ,0));
        user        .setPreferredSize(new Dimension(0, 30));
        ip          .setPreferredSize(new Dimension(0, 30));
        password    .setPreferredSize(new Dimension(0, 30));
        lgButton    .setFocusable(false);
        lgButton    .setBackground(new Color(0x1E90FF));
        lgButton    .setForeground(new Color(0xFFFFFF));
        lgButton    .addActionListener(this);
        /*userDiv   .setBackground(getForeground());
        passwordDiv .setBackground(getForeground());*/

        JPanel ipDiv = new JPanel();
        
        userDiv     .add(userLabel);
        userDiv     .add(user);
		ipDiv       .add(ipLabel);
		ipDiv       .add(ip);
        passwordDiv .add(passwordLabel);
        passwordDiv .add(password);
        
        loginPanel.add(authTitle, constraints);
        loginPanel.add(userDiv, constraints);
		loginPanel.add(ipDiv, constraints);
        loginPanel.add(passwordDiv, constraints);
        loginPanel.add(lgButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == lgButton) {
            System.out.println("login");
        }
    }

   /*public static void main(String[] args){
        new DLogin().setVisible(true);
    }*/

}
/*
 * 

            String name = lgUser.getText();
			String password = String.valueOf(lgPwd.getPassword());
			int count=0;
			String sql = "SELECT * FROM users WHERE username= ? AND password = ?";
			PreparedStatement ps = null;
        	ResultSet res = null;
			try{
				Connection conn = DBConnection.getConexao();
				//Statement stmt = conn.createStatement();
				//ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE username='"+name+"' AND password='"+password+"'");
				ps = conn.prepareStatement(sql);
        		ps.setString(1, name);
        		ps.setString(2, password);
        		res = ps.executeQuery();
				
				while(res.next()){
					count++;
					UserLvl.setText(res.getString("category"));
				}
			}
			catch(SQLException exp) {
				exp.printStackTrace();
			}
			if (count > 0 && count <2) {
				try {
					if(!(InetAddress.getByName(ipAdd.getText()).isReachable(2000))){
						JOptionPane.showMessageDialog(null, "IP inacessível ");
					}else{
						JOptionPane.showMessageDialog(null, "Bem vindo "+UserLvl.getText());
						if (UserLvl.getText().equals("admin")) {
							brandName.setText("Dashboard");
						}
						else{
							brandName.setText("Menu");
						}
						profile.setText(UserLvl.getText());
					
						if (UserLvl.getText().equals("user")) {
							aside.setBackground(Color.GREEN);
							dash.setVisible(false);
							pedido.setVisible(false);
							func.setVisible(false);
							cliente.setVisible(false);
							logs.setVisible(false);
							Home.remove(Dash);
							Home.add(Menu, BorderLayout.CENTER);
							System.out.println("Home");
						}
						else if (UserLvl.getText().equals("func")) {
							aside.setBackground(Color.ORANGE);
							func.setVisible(false);
							cliente.setVisible(false);
							logs.setVisible(false);
						}
						else{
							System.out.println("Default");
							Home.remove(Menu);
							Home.add(Dash, BorderLayout.CENTER);
						}
					
						AuthMainPanel.setVisible(false);
						add(Home);
						HomeHeader.add(logoLabel, BorderLayout.WEST);
        				HomeHeader.add(brandName, BorderLayout.CENTER);
        				HomeHeader.add(profile, BorderLayout.EAST);
						Home.setVisible(true);
					}
				}catch(IOException ex) {
                    System.out.println("Excepção");
                    ex.printStackTrace();
                }
			}
			else {
				JOptionPane.showMessageDialog(null, "Verifique as credenciais", "Erro de autenticacao", JOptionPane.ERROR_MESSAGE);
			}

 */