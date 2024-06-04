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

public class Dashboard extends JPanel implements ActionListener{
	JPanel dashJPanel;
	JPanel dashHeaderJPanel;
	JPanel dashBodyJPanel;

	JLabel nrLucro;
	JLabel nrPizzas;
	JLabel nrFuncionarios;
	JLabel nrClientes;
	JLabel nrPeddPendente;
	JLabel nrPeddFeito;

	JButton actualizar;

	JScrollPane scrollPane;

    public Dashboard(){

		dashJPanel = new JPanel(new BorderLayout());
		dashHeaderJPanel = new JPanel();
		dashBodyJPanel = new JPanel(new FlowLayout());
		dashBodyJPanel.setPreferredSize(new Dimension(830, 3000));
		
		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		nrLucro = new JLabel("Lucro");
		nrPizzas = new JLabel("Pizzas");
		nrFuncionarios = new JLabel("Funcionarios ");
		nrClientes = new JLabel("Clientes");
		nrPeddPendente = new JLabel("Pedidos pendentes");
		nrPeddFeito = new JLabel("Pedidos atendidos");

		dashPanel();
		dashDados();

		dashBodyJPanel.add(nrLucro);
		dashBodyJPanel.add(nrPizzas);
		dashBodyJPanel.add(nrFuncionarios);
		dashBodyJPanel.add(nrClientes);
		dashBodyJPanel.add(nrPeddPendente);
		dashBodyJPanel.add(nrPeddFeito);

		scrollPane = new JScrollPane(dashBodyJPanel);
		scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(Setting.getScrollPaneWidth(), Setting.getScrollPaneHeight()));

		dashHeaderJPanel.add(actualizar);
		dashHeaderJPanel.setBackground(new Color(0x123456));

		dashJPanel.add(dashHeaderJPanel, BorderLayout.NORTH);
		dashJPanel.add(scrollPane, BorderLayout.CENTER);
		add(dashJPanel);
        
		/*Thread verificaDados = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(5000); 
					dashDados();
					System.out.println("Verificando Dashboard");
					//notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		verificaDados.start();*/
    }
	public void dashPanel(){

		nrLucro.setOpaque(true);
		nrLucro.setBackground(new Color(0x444444));
		nrLucro.setForeground(new Color(0xFFFFFF));
		nrLucro.setFont(new Font("Dialog", Font.BOLD, 18));
		nrLucro.setPreferredSize(new Dimension(200, 100));
		nrLucro.setHorizontalAlignment(SwingConstants.CENTER);
		nrPizzas.setOpaque(true);
		nrPizzas.setBackground(new Color(0x444444));
		nrPizzas.setForeground(new Color(0xFFFFFF));
		nrPizzas.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPizzas.setPreferredSize(new Dimension(110, 100));
		nrPizzas.setHorizontalAlignment(SwingConstants.CENTER);
		nrFuncionarios.setOpaque(true);
		nrFuncionarios.setBackground(new Color(0x444444));
		nrFuncionarios.setForeground(new Color(0xFFFFFF));
		nrFuncionarios.setFont(new Font("Dialog", Font.BOLD, 18));
		nrFuncionarios.setPreferredSize(new Dimension(140, 100));
		nrFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		nrClientes.setOpaque(true);
		nrClientes.setBackground(new Color(0x444444));
		nrClientes.setForeground(new Color(0xFFFFFF));
		nrClientes.setFont(new Font("Dialog", Font.BOLD, 18));
		nrClientes.setPreferredSize(new Dimension(130, 100));
		nrClientes.setHorizontalAlignment(SwingConstants.CENTER);
		nrPeddPendente.setOpaque(true);
		nrPeddPendente.setBackground(new Color(0x444444));
		nrPeddPendente.setForeground(new Color(0xFFFFFF));
		nrPeddPendente.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPeddPendente.setPreferredSize(new Dimension(210, 100));
		nrPeddPendente.setHorizontalAlignment(SwingConstants.CENTER);
		nrPeddFeito.setOpaque(true);
		nrPeddFeito.setBackground(new Color(0x444444));
		nrPeddFeito.setForeground(new Color(0xFFFFFF));
		nrPeddFeito.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPeddFeito.setPreferredSize(new Dimension(200, 100));
		nrPeddFeito.setHorizontalAlignment(SwingConstants.CENTER);

	}
	public void dashDados(){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT (SELECT sum(preco * quantidade) FROM pedido WHERE status='atendido') AS lucro, ( SELECT count(*) FROM menu) AS p, (SELECT count(*) FROM pedido WHERE status='pendente') AS pdp, (SELECT count(*) FROM pedido WHERE status='atendido') AS pda, (SELECT count(*) FROM users WHERE category = 'staff') AS uf, (SELECT count(*) FROM users WHERE category = 'user') AS uc");

			while(res.next()){
				nrLucro.setText("Lucro\n "+res.getInt("lucro")+",00 MZN");
				nrPizzas.setText(" "+res.getInt("p")+" Pizzas");
				nrPeddPendente.setText(" "+res.getInt("pdp")+" Pedido pendentes");
				nrPeddFeito.setText(" "+res.getInt("pda")+" Pedido atendidos ");
				nrFuncionarios.setText(" "+res.getInt("uf")+" Funcionarios");
				nrClientes.setText(" "+res.getInt("uc")+" Clientes");
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			dashDados();
		}
	}
	
}
