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

public class Client extends JPanel implements ActionListener{
	JPanel clientJPanel;
	JPanel clientHeaderJPanel;
	JPanel clientBodyJPanel;
	JPanel clientFooterJPanel;

	ArrayList<String> ClieArr;
	int countClie=0, lClie=0;
	Object[][] dataClie;

	JButton actualizar;

    public Client(){
        clientJPanel = new JPanel(new BorderLayout());
		clientHeaderJPanel = new JPanel();
		clientBodyJPanel = new JPanel();
		clientFooterJPanel = new JPanel();

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		ClieArr = new ArrayList<String>();
		
		clientDados();
        // Sample data for the table
        dataClie = new Object[countClie][4];

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

		clientHeaderJPanel.setBackground(new Color(0x123456));
		clientHeaderJPanel.add(actualizar);
        clientBodyJPanel.add(scrollPaneClie);
		//clientFooterJPanel.add();

		clientJPanel.add(clientHeaderJPanel, BorderLayout.NORTH);
		clientJPanel.add(clientBodyJPanel, BorderLayout.CENTER);
		clientJPanel.add(clientFooterJPanel, BorderLayout.NORTH);

		add(clientJPanel);
    }

	public void clientDados(){
		try {
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
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			clientDados();
		}
	}
}
