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

public class Menu extends JPanel{
    public Menu(){
        JPanel Menu = new JPanel(new BorderLayout());
		Menu.setPreferredSize(new Dimension(500, 60));
        
        ArrayList<String> pizzas = new ArrayList<String>();
		int count=0, l=0;
		/*try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM pizza");
			
			while(res.next()){
				count++;
				pizzas.add(res.getString("nome"));
				pizzas.add(res.getString("tamanho"));
				pizzas.add(res.getString("preco"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}*/
        // Sample data for the table
        Object[][] data = new Object[count][3];
		for(int i=0; i<count; i++){
			for(int j=0; j<3; j++){
				data[i][j] = pizzas.get(l);
				l++;
			}
		}
        // Column names
        String[] columnNames = {"Nome", "Tamanho", "Preco"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        // Create JTable using the model
        JTable table = new JTable(model);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        Menu.add(scrollPane, BorderLayout.CENTER);


        JPanel AskPedidoBody = new JPanel();

		JLabel askPizzaNameL = new JLabel("Nome da pizza");
		JTextField askPizzaName = new JTextField(10);
		JLabel askPizzaTamL = new JLabel("Tamanho da pizza");
		JTextField askPizzaTam = new JTextField(10);
		JLabel askPizzaQuantL = new JLabel("Quantidade");
		JTextField askPizzaQuant = new JTextField(10);
		JButton submitAskPizza = new JButton("Adicionar Pedido");

		AskPedidoBody.add(askPizzaNameL);
		AskPedidoBody.add(askPizzaName);
		AskPedidoBody.add(askPizzaTamL);
		AskPedidoBody.add(askPizzaTam);
		AskPedidoBody.add(askPizzaQuantL);
		AskPedidoBody.add(askPizzaQuant);
		AskPedidoBody.add(submitAskPizza);

		Menu.add(AskPedidoBody, BorderLayout.SOUTH);
    }
}
