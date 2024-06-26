package src.java.views;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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

	ImageIcon cardImgIcon;
	Image cardImgResize;
	ImageIcon cardImgResized;
	JLabel cardImgJLabel[];
	JPanel cardJPanel[];
	JTextArea cardJLabel[];
	JButton cardJButton[];
	SpinnerModel spinnerModel[];
	JSpinner spinner[];
	JPanel cardBtnsJPanel[];
	JButton cardEditJButton[];
	JButton cardDeleteJButton[];
	JPanel cardTopBtnsJPanel[];
	JPanel cardBottomBtnsJPanel[];
	JPanel cardAllBtnsJPanel[];
	
	ArrayList<String> pizzas;
	Object[][] data;
	int count;
	public String code;
	int[] getID;
	
	JButton actualizar;
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

	JScrollPane scrollPane;

	JDialog modalDialog;

	JLabel modalDialogTitle;
	JPanel modalDialogHeader;
	JPanel modalDialogBody;
	JPanel modalDialogFooter;
	JPanel nomeDiv;
	JPanel tamDiv;
	JPanel precDiv;
	JPanel catDiv;
	
	GridBagConstraints constraints;

    public Menu(String accessLevel){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel[100];
		cardJLabel = new JTextArea[100];
		cardImgJLabel= new JLabel[100];
		cardJButton = new JButton[100];
		spinnerModel = new SpinnerNumberModel[100];
		spinner = new JSpinner[100];
		cardEditJButton = new JButton[100];
		cardDeleteJButton = new JButton[100];
		cardTopBtnsJPanel = new JPanel[100];
		cardBottomBtnsJPanel = new JPanel[100];
		cardAllBtnsJPanel = new JPanel[100];
		getID = new int[100];

		modalDialog = new JDialog();
		modalDialog.setSize(300, 220);
		modalDialog.setModal(true);
		modalDialog.setUndecorated(true);
		modalDialog.setLocationRelativeTo(menuJPanel);
		modalDialog.setBackground(new Color(0xfebd00));
		modalDialog.setLayout(new BorderLayout());
		
		modalDialogTitle = new JLabel();
		modalDialogTitle.setFont(new Font("Arial", Font.BOLD, 20));

		modalDialogHeader = new JPanel();
		modalDialogBody = new JPanel();
		modalDialogFooter = new JPanel();
		nomeDiv = new JPanel();
		tamDiv = new JPanel();
		precDiv = new JPanel();
		catDiv = new JPanel();

		constraints = new GridBagConstraints();
        constraints.gridx   = 0;
        constraints.gridy   = GridBagConstraints.RELATIVE;
        constraints.anchor  = GridBagConstraints.CENTER;
        constraints.insets  = new Insets(5, 7, 5, 7);


        cardImgIcon		= new ImageIcon("src/resources/assets/pizza-ico.png");
        cardImgResize 	= cardImgIcon.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH);
        cardImgResized	= new ImageIcon(cardImgResize);

        menuJPanel = new JPanel(new BorderLayout());
		menuHeaderJPanel = new JPanel();
		menuBodyJPanel = new JPanel(new FlowLayout());
		menuBodyJPanel.setPreferredSize(new Dimension(Setting.getPaneDimension()));
		menuFooterJPanel = new JPanel();

		scrollPane = new JScrollPane(menuBodyJPanel);
		scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(Setting.getScrollPaneDimension()));

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

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		addPizza = new JButton("Adicionar Pizza");
		addPizza.addActionListener(this);

		modalDialogHeader.add(modalDialogTitle);
		nomeDiv.add(nomJLabel);
		nomeDiv.add(nomJTextField);
		tamDiv.add(tamJLabel);
		tamDiv.add(tamJTextField);
		precDiv.add(precJLabel);
		precDiv.add(precJTextField);
		catDiv.add(catJLabel);
		catDiv.add(catJTextField);
		modalDialogBody.add(nomeDiv, constraints);
		modalDialogBody.add(tamDiv, constraints);
		modalDialogBody.add(precDiv, constraints);
		modalDialogBody.add(catDiv, constraints);

		// Add components to the dialog
		modalDialog.add(modalDialogHeader, BorderLayout.NORTH);
		modalDialog.add(modalDialogBody, BorderLayout.CENTER);
		modalDialog.add(modalDialogFooter, BorderLayout.SOUTH);
		
		menuDados(accessLevel);
		
		menuHeaderJPanel.setBackground(new Color(0x123456));
		menuHeaderJPanel.add(actualizar);
		menuHeaderJPanel.add(addPizza);
        //menuBodyJPanel.add(scrollPane);

		menuJPanel.add(menuHeaderJPanel, BorderLayout.NORTH);
		menuJPanel.add(scrollPane, BorderLayout.CENTER);
		menuJPanel.add(menuFooterJPanel, BorderLayout.SOUTH);

		add(menuJPanel);

		/*Thread verificaDados = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(5000); 
					rmMenuDados();
					menuDados("Admin");
					//System.out.println("Refreshing Menu");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		verificaDados.start();*/
    }
	public void menuDados(String accessLevel){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM menu");
			count=0;
			code = ((char) (new Random().nextInt(80)+65)+""+(char) (new Random().nextInt(80)+65)+""+(char) (new Random().nextInt(80)+65)+""+""+new Random().nextInt(100)+1).toUpperCase();

			while(res.next()){
				count++;
				getID[count] = Integer.parseInt(res.getString("id"));

				cardJPanel[getID[count]] = new JPanel(new BorderLayout());
				cardImgJLabel[getID[count]] = new JLabel();
				cardJLabel[getID[count]] = new JTextArea();
				spinnerModel[getID[count]] = new SpinnerNumberModel(0, 0, 100, 1);
				spinner[getID[count]] = new JSpinner(spinnerModel[getID[count]]);
				cardJButton[getID[count]] = new JButton("Pedir");
				cardEditJButton[getID[count]] = new JButton("Editar");
				cardDeleteJButton[getID[count]] = new JButton("Excluir");
				cardTopBtnsJPanel[getID[count]] = new JPanel();
				cardBottomBtnsJPanel[getID[count]] = new JPanel();
				cardAllBtnsJPanel[getID[count]] = new JPanel(new BorderLayout());

				cardJLabel[getID[count]].setText("   ID: "+res.getString("id")+"\n   Nome: "+res.getString("nome")+"\n   Tamanho: "+res.getString("tamanho")+"\n   Categoria: "+res.getString("categoria")+"\n   Preco: "+res.getString("preco"));
				cardJLabel[getID[count]].setOpaque(true);
				//cardJLabel[getID[count]].setBackground(new Color(0x444444));
				//cardJLabel[getID[count]].setForeground(new Color(0xFFFFFF));
				cardJLabel[getID[count]].setFont(new Font("Dialog", Font.BOLD, 14));
				//cardJLabel[getID[count]].setHorizontalAlignment(SwingConstants.CENTER);
				
				cardJLabel[getID[count]].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
				cardJLabel[getID[count]].setEditable(false);
        		cardJLabel[getID[count]].setFocusable(false);  
        		cardJLabel[getID[count]].setWrapStyleWord(true); 
        		cardJLabel[getID[count]].setLineWrap(true);
        		cardJLabel[getID[count]].setBorder(BorderFactory.createEmptyBorder());

				cardJButton[getID[count]].addActionListener(this);
				cardEditJButton[getID[count]].addActionListener(this);
				cardDeleteJButton[getID[count]].addActionListener(this);

				cardJPanel[getID[count]].setBackground(new Color(0x444444));
				cardImgJLabel[getID[count]].setIcon(cardImgResized);

				if (accessLevel.equals("Client")) {
					addPizza.setVisible(false);
					cardBottomBtnsJPanel[getID[count]].setVisible(false);
				}

				cardTopBtnsJPanel[getID[count]].add(spinner[getID[count]]);
				cardTopBtnsJPanel[getID[count]].add(cardJButton[getID[count]]);
				cardBottomBtnsJPanel[getID[count]].add(cardEditJButton[getID[count]]);
				cardBottomBtnsJPanel[getID[count]].add(cardDeleteJButton[getID[count]]);
				cardAllBtnsJPanel[getID[count]].add(cardTopBtnsJPanel[getID[count]], BorderLayout.NORTH);
				cardAllBtnsJPanel[getID[count]].add(cardBottomBtnsJPanel[getID[count]], BorderLayout.SOUTH);
				cardTopBtnsJPanel[getID[count]].setBackground(new Color(0xFFFFFF));
				cardBottomBtnsJPanel[getID[count]].setBackground(new Color(0xFFFFFF));

				cardJPanel[getID[count]].add(cardImgJLabel[getID[count]], BorderLayout.NORTH);
				cardJPanel[getID[count]].add(cardJLabel[getID[count]], BorderLayout.CENTER);
				cardJPanel[getID[count]].add(cardAllBtnsJPanel[getID[count]], BorderLayout.SOUTH);
				menuBodyJPanel.add(cardJPanel[getID[count]]);
				revalidate();
				repaint();
				
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
	}
	public void rmMenuDados(){
		for(int i = 1; i<=count; i++){
			try{
				cardTopBtnsJPanel[getID[i]].remove(spinner[getID[i]]);
			cardTopBtnsJPanel[getID[i]].remove(cardJButton[getID[i]]);
			cardBottomBtnsJPanel[getID[i]].remove(cardEditJButton[getID[i]]);
			cardBottomBtnsJPanel[getID[i]].remove(cardDeleteJButton[getID[i]]);
			cardAllBtnsJPanel[getID[i]].remove(cardTopBtnsJPanel[getID[i]]);
			cardAllBtnsJPanel[getID[i]].remove(cardBottomBtnsJPanel[getID[i]]);

			cardJPanel[getID[i]].remove(cardImgJLabel[getID[i]]);
			cardJPanel[getID[i]].remove(cardJLabel[getID[i]]);
			cardJPanel[getID[i]].remove(cardAllBtnsJPanel[getID[i]]);
			menuBodyJPanel.remove(cardJPanel[getID[i]]);
			}catch(Exception e){
				System.out.println("bug");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			rmMenuDados();
			menuDados("Admin");
		}
		else if(e.getSource() == addPizza){
			JButton closeButton = new JButton("Cancelar");
			closeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					modalDialogFooter.remove(closeButton);
					modalDialogFooter.remove(addPizzJButton);
					modalDialog.dispose();
					System.out.println("modal off");
				}
			});

			modalDialogTitle.setText("Adicionar Pizza");
			
			modalDialogFooter.add(closeButton);
			modalDialogFooter.add(addPizzJButton);

			// Display the dialog
			modalDialog.setVisible(true);
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
		else{
			for(int i = 0; i<=count; i++){
				if (e.getSource() == cardJButton[getID[i]]) {
					String Pid = ""+getID[i];
					String Pquant = ""+(Integer) spinner[getID[i]].getValue();
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

					String sql = "INSERT INTO pedido (id_user, codigo, nome, tamanho, preco, quantidade, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
					String code = (nomeP.substring(0, 3)+""+tamP.substring(0, 3)+""+preP.substring(0, 3)+""+new Random().nextInt(100)+1).toUpperCase();

					PreparedStatement ps = null;
					try {
						ps = DBConnection.getConexao().prepareStatement(sql);
						ps.setString(1, Home.getUser());
						ps.setString(2, code);
						ps.setString(3, nomeP);
						ps.setString(4, tamP);
						ps.setString(5, preP);
						ps.setString(6, Pquant);
						ps.setString(7, stts);
						ps.execute();
						ps.close();
					}
					catch(SQLException ex) {
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(addPizzaJPanel, "Pedido adicionado");

					/*
					String[] cartArr={Pid, Home.getUser(), code, nomeP, tamP, preP, Pquant, stts};
					Cart.setCart(cartArr);
					JOptionPane.showMessageDialog(addPizzaJPanel, "Pedido adicionado");
					Cart.getCart();
					*/
				}
				else if (e.getSource() == cardEditJButton[getID[i]]) {
					String Pid = ""+getID[i];
					String nomeP = null;
					String tamP = null;
					String precP = null;
					String catP = null;

					try{
					Connection conn = DBConnection.getConexao();
					Statement stmt = conn.createStatement();
					ResultSet res = stmt.executeQuery("SELECT * FROM menu WHERE id='"+Pid+"' ");
					while(res.next()){
						nomJTextField.setText(res.getString("nome"));
						tamJTextField.setText(res.getString("tamanho"));
						precJTextField.setText(res.getString("preco"));
						catJTextField.setText(res.getString("categoria"));
					}
					}
					catch(SQLException se){
					
					}

					JButton editButton = new JButton("Editar");
					editButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String editnomeP =	nomJTextField.getText();
							String edittamP = tamJTextField.getText();
							String editprecP = precJTextField.getText();
							String editcatP = catJTextField.getText();

							String sql = "UPDATE menu SET nome=?, tamanho=?, preco=?, categoria=?, updated_at=? WHERE id = ?";

							PreparedStatement ps = null;
							try {
								ps = DBConnection.getConexao().prepareStatement(sql);
								ps.setString(1, editnomeP);
								ps.setString(2, edittamP);
								ps.setString(3, editprecP);
								ps.setString(4, editcatP);
								ps.setString(5, ""+new Timestamp(System.currentTimeMillis()));
								ps.setString(6, Pid);
								ps.execute();
								ps.close();
							}
							catch(SQLException ex) {
								ex.printStackTrace();
							}
							JOptionPane.showMessageDialog(addPizzaJPanel, "Item menu editado");
						}
					});
					JButton closeButton = new JButton("Cancelar");
					closeButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							modalDialog.dispose();
							modalDialogFooter.remove(closeButton);
							modalDialogFooter.remove(editButton);
							System.out.println("modal off");
						}
					});

					modalDialogTitle.setText("Editar Pizza");
					modalDialogFooter.add(closeButton);
					modalDialogFooter.add(editButton);
					// Display the dialog
					modalDialog.setVisible(true);

				}
				else if (e.getSource() == cardDeleteJButton[getID[i]]) {
					String Pid = ""+getID[i];

					String sql = "DELETE FROM menu WHERE id = ?";

					PreparedStatement ps = null;
					try {
						ps = DBConnection.getConexao().prepareStatement(sql);
						ps.setString(1, Pid);
						ps.execute();
						ps.close();
					}
					catch(SQLException ex) {
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(addPizzaJPanel, "Item menu excluido");
				}
			}
		}
	}

}

/*
 * 
 * 
 JEditorPane editorPane = new JEditorPane();
editorPane.setContentType("text/html");
editorPane.setText("<html> <head><style>body{font-family: forte; color:red;}</style></head> <body> This is a <b>bold</b> word <br/> and <i>italic</i> word.</body></html>");
editorPane.setEditable(false); // Make non-editable if needed.
editorPane.setFont(new Font("Dialog", Font.BOLD, 14));
 *
 * 
 */