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

public class Client extends JPanel{
    public Client(){
        JPanel Client = new JPanel(new BorderLayout());
		Label clientText = new Label("Clientes");
		Client.add(clientText, BorderLayout.NORTH);

		ArrayList<String> ClieArr = new ArrayList<String>();
		int countClie=0, lClie=0;
		/*try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE category = 'user'");
			
			while(res.next()){
				countClie++;
				ClieArr.add(res.getString("nome"));
				ClieArr.add(res.getString("apelido"));
				ClieArr.add(res.getString("email"));
				ClieArr.add(res.getString("contacto"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}*/
        // Sample data for the table
        Object[][] dataClie = new Object[countClie][4];
		for(int i=0; i<countClie; i++){
			for(int j=0; j<4; j++){
				dataClie[i][j] = ClieArr.get(lClie);
				lClie++;
			}
		}
        // Column names
        String[] columnNamesClie = {"Nome", "Apelido", "Email", "Contacto"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel modelClie = new DefaultTableModel(dataClie, columnNamesClie);
        // Create JTable using the model
        JTable tableClie = new JTable(modelClie);
        // Add the table to a scroll pane
        JScrollPane scrollPaneClie = new JScrollPane(tableClie);
        Client.add(scrollPaneClie, BorderLayout.CENTER);
    }
}
