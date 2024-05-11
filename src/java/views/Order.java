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

public class Order extends JPanel implements ActionListener{
	JPanel orderJPanel;
	JPanel orderHeaderJPanel;
	JPanel orderBodyJPanel;
	JPanel orderFooterJPanel;

	ArrayList<String> PedidoArr;
	Object[][] dataPedido;
	int countPedido=0, lPedido=0;

	JButton actualizar;

    public Order(){
        orderJPanel = new JPanel(new BorderLayout());
		orderHeaderJPanel = new JPanel();
		orderBodyJPanel = new JPanel();
		orderFooterJPanel = new JPanel();

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		PedidoArr = new ArrayList<String>();

		orderDados();

        // Sample data for the table
        dataPedido = new Object[countPedido][9];
		for(int i=0; i<countPedido; i++){
			for(int j=0; j<9; j++){
				dataPedido[i][j] = PedidoArr.get(lPedido);
				lPedido++;
			}
		}
        // Column names
        String[] columnNamesPedido = {"Nr", "ID", "ID Cliente", "Nome", "Tamanho", "Preco", "Quantidade", "Status", "Data"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel modelPedido = new DefaultTableModel(dataPedido, columnNamesPedido);
        // Create JTable using the model
        JTable tablePedido = new JTable(modelPedido);
        // Add the table to a scroll pane
        JScrollPane scrollPanePedido = new JScrollPane(tablePedido);

		orderHeaderJPanel.setBackground(new Color(0x123456));
		orderHeaderJPanel.add(actualizar);
        orderBodyJPanel.add(scrollPanePedido);
		//orderFooterJPanel.add();

		orderJPanel.add(orderHeaderJPanel, BorderLayout.NORTH);
		orderJPanel.add(orderBodyJPanel, BorderLayout.CENTER);
		orderJPanel.add(orderFooterJPanel, BorderLayout.SOUTH);

		add(orderJPanel);
    }
	public void orderDados(){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT pedido.id AS id, pedido.nome AS nomeP, pedido.tamanho AS tam, pedido.preco AS preco, pedido.quantidade AS quant, pedido.status AS stt, pedido.created_at AS crea, users.id AS id_user, users.nome AS nomeU FROM pedido JOIN users ON pedido.id_user=users.id ");

			while(res.next()){
				countPedido++;
				PedidoArr.add(" "+countPedido+1);
				PedidoArr.add(res.getString("id"));
				PedidoArr.add(res.getString("id_user"));
				PedidoArr.add(res.getString("nomeU"));
				PedidoArr.add(res.getString("nomeP"));
				PedidoArr.add(res.getString("tam"));
				PedidoArr.add(res.getString("preco"));
				PedidoArr.add(res.getString("quant"));
				PedidoArr.add(res.getString("stt"));
				PedidoArr.add(res.getString("crea"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			orderDados();
		}
	}
}
