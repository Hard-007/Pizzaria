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

import src.resources.config.DBConnection;

public class Staff extends JPanel{
    public Staff(){
        JPanel Func = new JPanel(new BorderLayout());
		Label funcText = new Label("Funcionarios");
		Func.add(funcText, BorderLayout.NORTH);

		ArrayList<String> funcArr = new ArrayList<String>();
		int countFunc=0, lFunc=0;
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE category = 'funcionario'");
			
			while(res.next()){
				countFunc++;
				funcArr.add(res.getString("nome"));
				funcArr.add(res.getString("apelido"));
				funcArr.add(res.getString("email"));
				funcArr.add(res.getString("contacto"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
        // Sample data for the table
        Object[][] dataFunc = new Object[countFunc][4];
		for(int i=0; i<countFunc; i++){
			for(int j=0; j<4; j++){
				dataFunc[i][j] = funcArr.get(lFunc);
				lFunc++;
			}
		}
        // Column names
        String[] columnNamesFunc = {"Nome", "Apelido", "Email", "Contacto"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel modelFunc = new DefaultTableModel(dataFunc, columnNamesFunc);
        // Create JTable using the model
        JTable tableFunc = new JTable(modelFunc);
        // Add the table to a scroll pane
        JScrollPane scrollPaneFunc = new JScrollPane(tableFunc);
        Func.add(scrollPaneFunc, BorderLayout.CENTER);
    }
}
