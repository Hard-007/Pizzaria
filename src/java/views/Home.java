package src.java.views;

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

import src.java.views.auth.Auth;
import src.resources.config.DBConnection;

public class Home extends JFrame implements ActionListener{
    Dashboard dashboardObj;
    Menu menuObj;
    Order orderObj;
    Staff staffObj;
    Client clientObj;
    Account accountObj;
    Setting settingObj;
    Logs logsObj;

    JPanel homeJPanel       ;
    JPanel homeHeaderJPanel ;
    JPanel homeBodyJPanel   ;
    JPanel homeLeftJPanel   ;

    JLabel brandName    ;
    JLabel imgLabel     ;
    JLabel logoLabel    ;
    
    ImageIcon logoImg    ;
    ImageIcon logoResized;
    
    Image logoResize;
    
    JButton profile ;
    JButton dashJButton     ;
    JButton menuJButton     ;
    JButton orderJButton    ;
    JButton staffJButton    ;
    JButton clientJButton   ;
    JButton accountJButton  ; 
    JButton settingJButton  ; 
    JButton logsJButton     ;
    JButton exitJButton     ;
    
	JButton addPizzaBtn = new JButton("Add Pizza");
    JButton askPedido = new JButton("Ver Pedido");

    public Home(){
        setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));

        dashboardObj = new Dashboard();
        menuObj = new Menu();
        orderObj = new Order();
        staffObj = new Staff();
        clientObj = new Client();
        accountObj = new Account();
        settingObj = new Setting();
        logsObj = new Logs();

        homeJPanel        = new JPanel(new BorderLayout());
        homeHeaderJPanel  = new JPanel(new BorderLayout());
        homeBodyJPanel    = new JPanel(new BorderLayout());
        homeLeftJPanel    = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        logoLabel   = new JLabel();
        logoImg     = new ImageIcon("../assets/logo.jpg");
        logoResize  = logoImg.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logoResized = new ImageIcon(logoResize);
        logoLabel.setIcon(logoResized);      

        brandName   = new JLabel("Home");
        profile     = new JButton("User");

        dashJButton     = new JButton("Dashboard");
        menuJButton     = new JButton("Menu");
        orderJButton    = new JButton("Pedidos");
        staffJButton    = new JButton("Funcionarios");
        clientJButton   = new JButton("Clientes");
        accountJButton  = new JButton("Conta");
        settingJButton  = new JButton("Definicoes");
        logsJButton     = new JButton("Logs");
        exitJButton     = new JButton("Logout");
        dashJButton     .addActionListener(this);  
        menuJButton     .addActionListener(this);
        orderJButton    .addActionListener(this);
        staffJButton    .addActionListener(this);
        clientJButton   .addActionListener(this);
        accountJButton  .addActionListener(this);
        settingJButton  .addActionListener(this);
        logsJButton     .addActionListener(this);
        exitJButton     .addActionListener(this);
        homeLeftJPanel.add(dashJButton);
        homeLeftJPanel.add(menuJButton);
        homeLeftJPanel.add(orderJButton);
        homeLeftJPanel.add(staffJButton);
        homeLeftJPanel.add(clientJButton);
        homeLeftJPanel.add(accountJButton);
        homeLeftJPanel.add(settingJButton);
        homeLeftJPanel.add(logsJButton);
        homeLeftJPanel.add(exitJButton);
        homeLeftJPanel.setPreferredSize(new Dimension(120, MAXIMIZED_VERT));
        homeLeftJPanel.setBackground(new Color(0x241e20));

        homeHeader();
        JPanel aside = new JPanel();
        aside.setBackground(Color.RED);
        add(aside, BorderLayout.WEST);

        add(homeJPanel);
        homeJPanel.add(homeHeaderJPanel, BorderLayout.NORTH);
        homeJPanel.add(homeBodyJPanel, BorderLayout.CENTER);
        homeJPanel.add(homeLeftJPanel, BorderLayout.WEST);

        /*profile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(profile.getText().equals("Cadastrar")){
                    revalidate();
                    repaint();
					
				}else{

                    revalidate();
                    repaint();
				}
			}
		});*/
        
    }

    public void homeHeader(){
        homeHeaderJPanel.setBackground(new Color(0x241e20));

        brandName.setHorizontalAlignment(JLabel.CENTER);
        brandName.setFont(new Font("Arial", Font.BOLD, 27));
        brandName.setForeground(Color.WHITE);
        profile.setFont(new Font("Arial", Font.BOLD, 15));
        profile.setBackground(new Color(0x241e20));
        profile.setForeground(new Color(0xFFFFFF));
        profile.setFocusable(false);
        profile.setBorder(BorderFactory.createEtchedBorder());
        profile.setBorder(new EmptyBorder(7, 17 ,7, 30));

        homeHeaderJPanel.add(logoLabel, BorderLayout.WEST);
        homeHeaderJPanel.add(brandName, BorderLayout.CENTER);
        homeHeaderJPanel.add(profile, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e){  

        if (e.getSource() == dashJButton) {
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);
            
            brandName.setText("Dashboard");
            homeBodyJPanel.add(dashboardObj);
            dashboardObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == menuJButton){
            dashboardObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);
            
            brandName.setText("Menu");
            homeBodyJPanel.add(menuObj);
            menuObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == orderJButton){
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);
            
            brandName.setText("Order");
            homeBodyJPanel.add(orderObj);
            orderObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == staffJButton){
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);
            
            brandName.setText("Staff");
            homeBodyJPanel.add(staffObj);
            staffObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == clientJButton){
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);
            
            brandName.setText("Client");
            homeBodyJPanel.add(clientObj);
            clientObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == accountJButton){
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);
            
            brandName.setText("Account");
            homeBodyJPanel.add(accountObj);
            accountObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == settingJButton){
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            logsObj.setVisible(false);

            brandName.setText("Setting");
            homeBodyJPanel.add(settingObj);
            settingObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == logsJButton){
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            
            brandName.setText("Logs");
            homeBodyJPanel.add(logsObj);
            logsObj.setVisible(true);
            revalidate();
            repaint();
        }
        else if(e.getSource() == exitJButton ){
            JOptionPane.showMessageDialog(homeJPanel, "Encerrando sessão...");
            dashboardObj.setVisible(false);
            menuObj.setVisible(false);
            orderObj.setVisible(false);
            staffObj.setVisible(false);
            clientObj.setVisible(false);
            accountObj.setVisible(false);
            settingObj.setVisible(false);
            logsObj.setVisible(false);

			setVisible(false);
            new Auth().setVisible(true);
            //System.exit(0);
        }
    }

    /*public static void main(String[] args) {
        new Home().setVisible(true);
    }*/
    
}
