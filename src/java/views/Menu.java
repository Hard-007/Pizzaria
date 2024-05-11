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

public class Menu extends JPanel implements ActionListener{
	JPanel menuJPanel;
	JPanel menuHeaderJPanel;
	JPanel menuBodyJPanel;
	JPanel menuFooterJPanel;

	JPanel addPizzaJPanel;

	ArrayList<String> pizzas;
	Object[][] data;
	int count=0, l=0;

	JButton actualizar;
	JButton verPizza;
	JButton addPizza;



	JLabel nomJLabel;
	JTextField nomJTextField ;
	JLabel tamJLabel ;
	JTextField tamJTextField ;
	JLabel precJLabel ;
	JTextField precJTextField ;
	JLabel catJLabel;
	JTextField catJTextField;
	JButton addPizzJButton;


	JPanel AskPedidoBody ;
	JLabel askPizzaIdL ;
	JTextField askPizzaId ;
	JLabel askPizzaQuantL ;
	JTextField askPizzaQuant ;
	JButton submitAskPizza;

    public Menu(String accessLevel){
        menuJPanel = new JPanel(new BorderLayout());
		menuHeaderJPanel = new JPanel();
		menuBodyJPanel = new JPanel();
		menuFooterJPanel = new JPanel();

		addPizzaJPanel = new JPanel();

		nomJLabel = new JLabel("Nome");
		nomJTextField = new JTextField(10);
		tamJLabel = new JLabel("Tamanho");
		tamJTextField = new JTextField(10);
		precJLabel = new JLabel("Preco");
		precJTextField = new JTextField(10);
		catJLabel = new JLabel("Categoria");
		catJTextField = new JTextField(10);
		addPizzJButton = new JButton("Adicionar");
		addPizzJButton.addActionListener(this);

		addPizzaJPanel.add(nomJLabel);
		addPizzaJPanel.add(nomJTextField);
		addPizzaJPanel.add(tamJLabel);
		addPizzaJPanel.add(tamJTextField);
		addPizzaJPanel.add(precJLabel);
		addPizzaJPanel.add(precJTextField);
		addPizzaJPanel.add(catJLabel);
		addPizzaJPanel.add(catJTextField);
		addPizzaJPanel.add(addPizzJButton);

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		verPizza = new JButton("Ver pizza");
		addPizza = new JButton("Adicionar Pizza");
        verPizza.addActionListener(this);
		addPizza.addActionListener(this);

		if (accessLevel.equals("user")) {
			verPizza.setVisible(false);
			addPizza.setVisible(false);
		}

        pizzas = new ArrayList<String>();
		
		menuDados();
        
        // Column names id	nome	tamanhoP	preco	categoria	
        String[] columnNames = {"Nr", "ID", "Nome", "Tamanho", "Preco", "Categoria"};
        // Create a DefaultTableModel and set the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        // Create JTable using the model
        JTable table = new JTable(model);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
		
		
        AskPedidoBody = new JPanel();
		
		askPizzaIdL = new JLabel("ID");
		askPizzaId = new JTextField(10);
		askPizzaQuantL = new JLabel("Quantidade");
		askPizzaQuant = new JTextField(10);
		submitAskPizza = new JButton("Adicionar Pedido");
		submitAskPizza.addActionListener(this);

		AskPedidoBody.add(askPizzaIdL);
		AskPedidoBody.add(askPizzaId);
		AskPedidoBody.add(askPizzaQuantL);
		AskPedidoBody.add(askPizzaQuant);
		AskPedidoBody.add(submitAskPizza);
		
		menuHeaderJPanel.setBackground(new Color(0x123456));
		menuHeaderJPanel.add(actualizar);
		menuHeaderJPanel.add(verPizza);
		menuHeaderJPanel.add(addPizza);
        menuBodyJPanel.add(scrollPane);
		menuFooterJPanel.add(AskPedidoBody);

		menuJPanel.add(menuHeaderJPanel, BorderLayout.NORTH);
		menuJPanel.add(menuBodyJPanel, BorderLayout.CENTER);
		menuJPanel.add(menuFooterJPanel, BorderLayout.SOUTH);

		add(menuJPanel);
    }
	public void menuDados(){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM menu");
			
			while(res.next()){
				count++;
				pizzas.add(" "+count+1);
				pizzas.add(res.getString("id"));
				pizzas.add(res.getString("nome"));
				pizzas.add(res.getString("tamanho"));
				pizzas.add(res.getString("preco"));
				pizzas.add(res.getString("categoria"));
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
		// Sample data for the table
        data = new Object[count][6];
		for(int i=0; i<count; i++){
			for(int j=0; j<6; j++){
				data[i][j] = pizzas.get(l);
				l++;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			menuDados();
		}
		else if(e.getSource() == submitAskPizza){
			String Pid = askPizzaId.getText();
			String Pquant = askPizzaQuant.getText();
			String stts = "Pendente";
			String nomeP = null;
			String tamP = null;
			String preP = null;

			try{
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM menu WHERE id='"+Pid+"' ");
			while(res.next()){
				nomeP = res.getString("nome");
				tamP = res.getString("tamanho");
				preP = res.getString("preco");
			}
			}
			catch(SQLException se){

			}

			String sql = "INSERT INTO pedido (id_user, nome, tamanho, preco, quantidade, status) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = null;
			try {
				ps = DBConnection.getConexao().prepareStatement(sql);
				ps.setString(1, Home.getUser());
				ps.setString(2, nomeP);
				ps.setString(3, tamP);
				ps.setString(4, preP);
				ps.setString(5, Pquant);
				ps.setString(6, stts);
				ps.execute();
				ps.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(addPizzaJPanel, "Pedido adicionada");
		}
		else if(e.getSource() == verPizza){
			menuBodyJPanel.setVisible(true);
			menuFooterJPanel.setVisible(true);
			menuJPanel.add(menuBodyJPanel, BorderLayout.CENTER);
		}
		else if(e.getSource() == addPizza){
			menuBodyJPanel.setVisible(false);
			menuFooterJPanel.setVisible(false);
			menuJPanel.add(addPizzaJPanel, BorderLayout.CENTER);
		}
		else if(e.getSource() == addPizzJButton){
			System.out.println("pressed");
			String no = nomJTextField.getText();
			String ta = tamJTextField.getText();
			String pre = precJTextField.getText();
			String ca = catJTextField.getText();

			String sql = "INSERT INTO menu (nome, tamanho, preco, categoria) VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = null;
			try {
				ps = DBConnection.getConexao().prepareStatement(sql);
				ps.setString(1, no);
				ps.setString(2, ta);
				ps.setString(3, pre);
				ps.setString(4, ca);
				ps.execute();
				ps.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(addPizzaJPanel, "Pizza adicionada");
		}
	}

}
