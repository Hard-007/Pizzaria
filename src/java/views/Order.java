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

//import src.resources.config.DBConnection;

public class Order extends JPanel{
    public Order(){
        JPanel Pedido = new JPanel(new BorderLayout());
		Label askTextP = new Label("Pedidos");
		Pedido.add(askTextP, BorderLayout.NORTH);

		ArrayList<String> PedidoArr = new ArrayList<String>();
		int countPedido=0, lPedido=0;
		/*try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM pedidos ");
			
			while(res.next()){
				countPedido++;
				PedidoArr.add(res.getString("nomeP"));
				PedidoArr.add(res.getString("tamanhoP"));
				PedidoArr.add(res.getString("quantidadeP"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}*/
        // Sample data for the table
        Object[][] dataPedido = new Object[countPedido][3];
		for(int i=0; i<countPedido; i++){
			for(int j=0; j<3; j++){
				dataPedido[i][j] = PedidoArr.get(lPedido);
				lPedido++;
			}
		}
        // Column names
        String[] columnNamesPedido = {"Nome", "Tamanho", "Quantidade"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel modelPedido = new DefaultTableModel(dataPedido, columnNamesPedido);
        // Create JTable using the model
        JTable tablePedido = new JTable(modelPedido);
        // Add the table to a scroll pane
        JScrollPane scrollPanePedido = new JScrollPane(tablePedido);
        Pedido.add(scrollPanePedido, BorderLayout.CENTER);

    }
}
