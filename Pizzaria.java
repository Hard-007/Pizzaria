//javac -cp C:\Users\Filipe\Desktop\Java\GUI\Pizzaria;. Pizzaria.java
//javac -cp C:\Users\Filipe\Desktop\Java\GUI\Pizzaria\src\resources\config\mysql-connector-j-8.3.0.jar;. Pizzaria.java
import src.java.views.*;
import src.java.views.auth.*;

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


public class Pizzaria extends JFrame{
    /*public Pizzaria(){
        setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));
        
        //add(new Auth());
    }*/
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Home("admin", "1").setVisible(true);
            //new Auth().setVisible(true);
        });
    }
}
