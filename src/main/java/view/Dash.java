package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Dash {
    public Dash(){
        /********Dashboard */

		Dash = new JPanel(new BorderLayout());
		
		DashHeader = new JPanel();
		DashHeader.setBackground(new Color(0xFFEE00));
		dsHeaderText = new JLabel("Dashboard");
		dsHeaderText.setFont(new Font("Serif", Font.BOLD, 30));
		
		
		DashBody = new JPanel();
		
		DashFooter = new JPanel();
		dsBtn[0] = new JButton("Adicionar");
		dsBtn[1] = new JButton("Logout");
		
		Dash.add(DashHeader, BorderLayout.NORTH);
		DashHeader.add(dsHeaderText);
		
		Dash.add(DashBody, BorderLayout.CENTER);
		
		ArrayList<String> pizzas = new ArrayList<String>();
		int count=0, l=0;

		try {
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
		}

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
        DashBody.add(scrollPane);
		
		
		Dash.add(DashFooter, BorderLayout.SOUTH);
		DashFooter.add(dsBtn[0]);
		DashFooter.add(dsBtn[1]);

		dsBtn[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Dash.setVisible(false);
				add(AddPizza);
				AddPizza.setVisible(true);
			}
		});
		dsBtn[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Dash.setVisible(false);
				add(Home);
				Home.setVisible(true);
			}
		});
    }
}
