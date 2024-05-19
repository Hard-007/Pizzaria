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

public class Staff extends JPanel implements ActionListener{
	JPanel staffJPanel;
	JPanel menuHeaderJPanel;
	JPanel menuBodyJPanel;
	JPanel menuFooterJPanel;

	JPanel addPizzaJPanel;

	ImageIcon cardImgIcon;
	Image cardImgResize;
	ImageIcon cardImgResized;
	JLabel cardImgJLabel[];
	JPanel cardJPanel[];
	JTextArea cardJLabel[];
	JTextField cardQuantyJTextField[];
	JButton cardJButton[];
	JPanel cardBtnsJPanel[];

	ArrayList<String> pizzas;
	Object[][] data;
	int count, l=0;

	JButton actualizar;
	JButton verPizza;
	JButton addPizza;
	JButton editPizza;



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

	JScrollPane scrollPane;

    public Staff(String accessLevel){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel[100];
		cardJLabel = new JTextArea[100];
		cardImgJLabel= new JLabel[100];
		cardQuantyJTextField = new JTextField[100];
		cardJButton = new JButton[100];
		cardBtnsJPanel = new JPanel[100];


        cardImgIcon		= new ImageIcon("src/resources/assets/staff-icon.jpg");
        cardImgResize 	= cardImgIcon.getImage().getScaledInstance(240, 180, Image.SCALE_SMOOTH);
        cardImgResized	= new ImageIcon(cardImgResize);

        staffJPanel = new JPanel(new BorderLayout());
		menuHeaderJPanel = new JPanel();
		menuBodyJPanel = new JPanel(new FlowLayout());
		menuBodyJPanel.setPreferredSize(new Dimension(830, 3000));
		menuFooterJPanel = new JPanel();

		scrollPane = new JScrollPane(menuBodyJPanel);
        scrollPane.setPreferredSize(new Dimension(860, 470));

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
		editPizza = new JButton("Editar Pizza");
        //verPizza.addActionListener(this);
		//addPizza.addActionListener(this);
		//editPizza.addActionListener(this);

		if (accessLevel.equals("user")) {
			verPizza.setVisible(false);
			addPizza.setVisible(false);
			editPizza.setVisible(false);
		}
		
		menuDados();
		
		menuHeaderJPanel.setBackground(new Color(0x123456));
		menuHeaderJPanel.add(actualizar);
		menuHeaderJPanel.add(verPizza);
		menuHeaderJPanel.add(addPizza);
		menuHeaderJPanel.add(editPizza);
        //menuBodyJPanel.add(scrollPane);

		staffJPanel.add(menuHeaderJPanel, BorderLayout.NORTH);
		staffJPanel.add(scrollPane, BorderLayout.CENTER);
		staffJPanel.add(menuFooterJPanel, BorderLayout.SOUTH);

		add(staffJPanel);
    }
	public void menuDados(){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE category = 'staff'");
			count=0;
			
			while(res.next()){
				count++;

				cardJPanel[count] = new JPanel(new BorderLayout());
				cardImgJLabel[count] = new JLabel();
				cardJLabel[count] = new JTextArea();
				//cardJButton[count] = new JButton("Atender");
				//cardBtnsJPanel[count] = new JPanel();

				cardJLabel[count].setText("   ID : "+res.getString("id")+"\n   Nome : "+res.getString("nome")+"\n   Apelido : "+res.getString("apelido")+"\n   E-mail : "+res.getString("email")+"\n   Contacto: "+res.getString("contacto"));
				cardJLabel[count].setOpaque(true);
				//cardJLabel[count].setBackground(new Color(0x444444));
				//cardJLabel[count].setForeground(new Color(0xFFFFFF));
				cardJLabel[count].setFont(new Font("Dialog", Font.BOLD, 14));
				//cardJLabel[count].setHorizontalAlignment(SwingConstants.CENTER);
				
				cardJLabel[count].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
				cardJLabel[count].setEditable(false);
        		cardJLabel[count].setFocusable(false);  
        		cardJLabel[count].setWrapStyleWord(true); 
        		cardJLabel[count].setLineWrap(true);
        		cardJLabel[count].setBorder(BorderFactory.createEmptyBorder());

				//cardJButton[count].addActionListener(this);
				//cardBtnsJPanel[count].add(cardJButton[count]);
				//cardBtnsJPanel[count].setBackground(new Color(0xFFFFFF));

				cardJPanel[count].setBackground(new Color(0x444444));
				cardImgJLabel[count].setIcon(cardImgResized);

				cardJPanel[count].add(cardImgJLabel[count], BorderLayout.NORTH);
				cardJPanel[count].add(cardJLabel[count], BorderLayout.CENTER);
				//cardJPanel[count].add(cardBtnsJPanel[count], BorderLayout.SOUTH);
				menuBodyJPanel.add(cardJPanel[count]);
				revalidate();
				repaint();
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			menuDados();
		}
		else if(e.getSource() == verPizza){
			menuBodyJPanel.setVisible(true);
			scrollPane.setVisible(true);
			menuFooterJPanel.setVisible(true);
			revalidate();
			repaint();
			staffJPanel.add(menuBodyJPanel, BorderLayout.CENTER);
		}
		else if(e.getSource() == addPizza){
			menuBodyJPanel.setVisible(false);
			scrollPane.setVisible(false);
			menuFooterJPanel.setVisible(false);
			revalidate();
			repaint();
			staffJPanel.add(addPizzaJPanel, BorderLayout.CENTER);
		}
		else{
			for(int i = 0; i<100; i++){
				if (e.getSource() == cardJButton[i]) {
					String Pid = ""+i;
					String stts = "Atendido";
				
					String sql = "UPDATE pedido SET status = ? WHERE id = ?";

					PreparedStatement ps = null;
					try {
						ps = DBConnection.getConexao().prepareStatement(sql);
						ps.setString(1, stts);
						ps.setString(2, Pid);
						ps.execute();
						ps.close();
					}
					catch(SQLException ex) {
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(addPizzaJPanel, "Pedido atendido");
				}
			}
		}
	}

}