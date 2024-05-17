package src.java.views.auth;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

import java.net.*;
import java.net.URL.*;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.resources.config.DBConnection;


public class Auth extends JPanel implements ActionListener{
    Login lg;
    Signin sg;

    JPanel AuthMainPanel;
    JPanel AuthPanel;
    JPanel AuthHeader;

    JLabel brandName;
    JLabel imgLabel;
    JLabel logoLabel;
    
    JButton authOptions;

    ImageIcon ico = new ImageIcon("src/resources/assets/pizza-ico.jpg");
    ImageIcon img;
    ImageIcon imgResized;
    ImageIcon logoImg;
    ImageIcon logoResized;

    JButton lgButton;
    
    Image imgResize;
    Image logoResize;

    static int offOn = 1;

    public void onOff(int onoff){
        offOn = onoff;
    }

    public Auth(){
        /*setTitle("Pizzaria");
        setIconImage(ico.getImage());
		setSize(1024, 642);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1024, 642));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));

        if (offOn == 0) {
            setVisible(false);
            System.out.println(offOn);
        }

        lg = new Login();
        sg = new Signin();

        AuthHeader      = new JPanel(new BorderLayout());
        AuthMainPanel   = new JPanel(new BorderLayout());
        AuthPanel       = new JPanel(new GridBagLayout());

        imgLabel    = new JLabel();
        img         = new ImageIcon("src/resources/assets/pizza.jpg");
        imgResize   = img.getImage().getScaledInstance(512, 642, Image.SCALE_SMOOTH);
        imgResized  = new ImageIcon(imgResize);
        imgLabel.setIcon(imgResized);

        logoLabel   = new JLabel();
        logoImg     = new ImageIcon("src/resources/assets/logo.jpg");
        logoResize  = logoImg.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logoResized = new ImageIcon(logoResize);
        logoLabel.setIcon(logoResized);

        brandName   = new JLabel("M GRILL");
        authOptions = new JButton("Cadastrar");

        AuthPanel.setBackground(new Color(0xFFFFFF));      

        authHeader();
        
        add(AuthMainPanel);
        AuthMainPanel.add(AuthHeader, BorderLayout.NORTH);
        AuthMainPanel.add(imgLabel, BorderLayout.WEST);
        AuthMainPanel.add(AuthPanel, BorderLayout.CENTER);

        sg.setVisible(false);
        AuthPanel.add(lg);
        AuthPanel.add(sg);

        lgButton = new JButton();
        lgButton.addActionListener(this);

        authOptions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(authOptions.getText().equals("Cadastrar")){
					authOptions.setText("Entrar");
					lg.setVisible(false);
                    sg.setVisible(true);
                    revalidate();
                    repaint();
					
				}else{
					authOptions.setText("Cadastrar");
					sg.setVisible(false);
					lg.setVisible(true);
                    revalidate();
                    repaint();
				}
			}
		});
        
    }

    public void authHeader(){
        AuthHeader.setBackground(new Color(0x241e20));

        brandName.setHorizontalAlignment(JLabel.CENTER);
        brandName.setFont(new Font("Arial", Font.BOLD, 27));
        brandName.setForeground(Color.WHITE);
        authOptions.setFont(new Font("Arial", Font.BOLD, 15));
        authOptions.setBackground(new Color(0x241e20));
        authOptions.setForeground(new Color(0xFFFFFF));
        authOptions.setFocusable(false);
        authOptions.setBorder(BorderFactory.createEtchedBorder());
        authOptions.setBorder(new EmptyBorder(7, 17 ,7, 30));

        AuthHeader.add(logoLabel, BorderLayout.WEST);
        AuthHeader.add(brandName, BorderLayout.CENTER);
        AuthHeader.add(authOptions, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    }

    /*public static void main(String[] args) {
        new Auth().setVisible(true);
    }*/
    
}
