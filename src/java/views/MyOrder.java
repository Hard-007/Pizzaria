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

public class MyOrder extends JPanel implements ActionListener{
	JPanel myOrderJPanel;
	JPanel myOrderHeaderJPanel;
	JPanel myOrderBodyJPanel;
	JPanel myOrderFooterJPanel;

	ArrayList<String> PedidoArr;
	Object[][] dataPedido;
	int countPedido=0, lPedido=0;

	JButton actualizar;

    public MyOrder(){
        myOrderJPanel = new JPanel(new BorderLayout());
		myOrderHeaderJPanel = new JPanel();
		myOrderBodyJPanel = new JPanel();
		myOrderFooterJPanel = new JPanel();

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		PedidoArr = new ArrayList<String>();

		myOrderDados();

        // Column names
        String[] columnNamesPedido = {"Nr", "ID", "Nome", "Tamanho", "Preco", "Quantidade", "Status", "Data"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel modelPedido = new DefaultTableModel(dataPedido, columnNamesPedido);
        // Create JTable using the model
        JTable tablePedido = new JTable(modelPedido);
        // Add the table to a scroll pane
        JScrollPane scrollPanePedido = new JScrollPane(tablePedido);

		myOrderHeaderJPanel.setBackground(new Color(0x123456));
		myOrderHeaderJPanel.add(actualizar);
        myOrderBodyJPanel.add(scrollPanePedido);
		//myOrderFooterJPanel.add();

		myOrderJPanel.add(myOrderHeaderJPanel, BorderLayout.NORTH);
		myOrderJPanel.add(myOrderBodyJPanel, BorderLayout.CENTER);
		myOrderJPanel.add(myOrderFooterJPanel, BorderLayout.SOUTH);

		add(myOrderJPanel);
    }
	public void myOrderDados(){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM pedido WHERE id_user='"+Home.getUser()+"' ");

			while(res.next()){
				countPedido++;
				PedidoArr.add(" "+countPedido+1);
				PedidoArr.add(res.getString("id"));
				PedidoArr.add(res.getString("nome"));
				PedidoArr.add(res.getString("tamanho"));
				PedidoArr.add(res.getString("preco"));
				PedidoArr.add(res.getString("quantidade"));
				PedidoArr.add(res.getString("status"));
				PedidoArr.add(res.getString("created_at"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
		// Sample data for the table
        dataPedido = new Object[countPedido][8];
		for(int i=0; i<countPedido; i++){
			for(int j=0; j<8; j++){
				dataPedido[i][j] = PedidoArr.get(lPedido);
				lPedido++;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			myOrderDados();
		}
	}
}
