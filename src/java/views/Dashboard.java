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

public class Dashboard extends JPanel{
	JPanel dashJPanel;
	JPanel dashHeaderJPanel;
	JPanel dashBodyJPanel;

	Label nrPizzas;
	Label nrFuncionarios;
	Label nrClientes;
	Label nrPeddPendente;
	Label nrPeddFeito;

	JButton actualizar;

    public Dashboard(){

		dashJPanel = new JPanel(new BorderLayout());
		dashHeaderJPanel = new JPanel();
		dashBodyJPanel = new JPanel();
		
		actualizar = new JButton("Actualizar");
		//actualizar.addActionListener(this);

		nrPizzas = new Label(" 200 Pizzas ");
		nrFuncionarios = new Label(" 4 Funcionarios ");
		nrClientes = new Label(" 20 Clientes ");
		nrPeddPendente = new Label(" 10 pedidos pendentes ");
		nrPeddFeito = new Label(" 7 pedidos atendidos ");

		dashPanel();

		dashBodyJPanel.add(nrPizzas);
		dashBodyJPanel.add(nrFuncionarios);
		dashBodyJPanel.add(nrClientes);
		dashBodyJPanel.add(nrPeddPendente);
		dashBodyJPanel.add(nrPeddFeito);

		dashHeaderJPanel.add(actualizar);
		dashHeaderJPanel.setBackground(new Color(0x123456));

		dashJPanel.add(dashHeaderJPanel, BorderLayout.NORTH);
		dashJPanel.add(dashBodyJPanel, BorderLayout.CENTER);
		add(dashJPanel);
		
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				try {
					Connection conn = DBConnection.getConexao();
					Statement stmt = conn.createStatement();
					ResultSet res = stmt.executeQuery("SELECT ( SELECT count(*) FROM pizza) AS p, (SELECT count(*) FROM pedidos) AS pdd, (SELECT count(*) FROM saidas) AS s, (SELECT count(*) FROM users WHERE category = 'funcionario') AS u");

					while(res.next()){
						nrPizzas.setText(" "+res.getInt("p")+" Pizzas");
						nrPeddPendente.setText(" "+res.getInt("pdd")+" Pendentes");
						nrPeddFeito.setText(" "+res.getInt("s")+" Feitos");
						nrFuncionarios.setText(" "+res.getInt("u")+" Funcionarios");
						System.out.println("updating");
					}
				}
				catch(SQLException exp) {
					exp.printStackTrace();
				}
			}
		});
        
    }
	public void dashPanel(){

		nrPizzas.setBackground(new Color(0x444444));
		nrPizzas.setForeground(new Color(0xFFFFFF));
		nrPizzas.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPizzas.setPreferredSize(new Dimension(110, 100));
		nrFuncionarios.setBackground(new Color(0x444444));
		nrFuncionarios.setForeground(new Color(0xFFFFFF));
		nrFuncionarios.setFont(new Font("Dialog", Font.BOLD, 18));
		nrFuncionarios.setPreferredSize(new Dimension(140, 100));
		nrClientes.setBackground(new Color(0x444444));
		nrClientes.setForeground(new Color(0xFFFFFF));
		nrClientes.setFont(new Font("Dialog", Font.BOLD, 18));
		nrClientes.setPreferredSize(new Dimension(130, 100));
		nrPeddPendente.setBackground(new Color(0x444444));
		nrPeddPendente.setForeground(new Color(0xFFFFFF));
		nrPeddPendente.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPeddPendente.setPreferredSize(new Dimension(210, 100));
		nrPeddFeito.setBackground(new Color(0x444444));
		nrPeddFeito.setForeground(new Color(0xFFFFFF));
		nrPeddFeito.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPeddFeito.setPreferredSize(new Dimension(200, 100));

	}
/* *
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			
			try {
				Connection conn = DBConnection.getConexao();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("SELECT ( SELECT count(*) FROM pizza) AS p, (SELECT count(*) FROM pedidos) AS pdd, (SELECT count(*) FROM saidas) AS s, (SELECT count(*) FROM users WHERE category = 'funcionario') AS u");

				while(res.next()){
					nrPizzas.setText(" "+res.getInt("p")+" Pizzas");
					nrPeddPendente.setText(" "+res.getInt("pdd")+" Pendentes");
					nrPeddFeito.setText(" "+res.getInt("s")+" Feitos");
					nrFuncionarios.setText(" "+res.getInt("u")+" Funcionarios");
					System.out.println("updating");
				}
			}
			catch(SQLException exp) {
				exp.printStackTrace();
			}
		}
	}
	*/
}
