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

import src.resources.config.DBConnection;

import java.io.InputStream;
import javax.imageio.ImageIO;

public class Profile extends JPanel implements ActionListener{
	JPanel menuJPanel;
	JPanel menuHeaderJPanel;
	JPanel menuBodyJPanel;
	JPanel menuFooterJPanel;

	JPanel addPizzaJPanel;

	Image pddcardImgIcon;
	Image pddcardImgResize;
	ImageIcon pddcardImgResized;
	JLabel pddcardImgJLabel;
	JPanel cardJPanel;
	JTextArea cardJLabel;
	JButton cardJButton;
	SpinnerModel spinnerModel;
	JSpinner spinner;
	JPanel cardBtnsJPanel;
	JButton cardEditJButton;
	JButton cardDeleteJButton;
	JPanel cardTopBtnsJPanel;
	JPanel cardBottomBtnsJPanel;
	JPanel cardAllBtnsJPanel;

	ArrayList<String> pizzas;
	Object[][] data;
	int count;
	int getID;

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

    public Profile(){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel();
		cardJLabel = new JTextArea();
		pddcardImgJLabel= new JLabel();
		cardJButton = new JButton();
		cardEditJButton = new JButton();
		cardDeleteJButton = new JButton();
		cardTopBtnsJPanel = new JPanel();
		cardBottomBtnsJPanel = new JPanel();
		cardAllBtnsJPanel = new JPanel();

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

		try (InputStream is = Order.class.getResourceAsStream("/src/resources/assets/user.png")) {
			if (is != null) {
				pddcardImgIcon		= ImageIO.read(is);
        		pddcardImgResize 	= pddcardImgIcon.getScaledInstance(200, 160, Image.SCALE_SMOOTH);
        		pddcardImgResized	= new ImageIcon(pddcardImgResize);
			} else {
				System.out.println("Imagem não pôde ser encontrada.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a imagem.");
		}

        menuJPanel = new JPanel(new BorderLayout());
		menuHeaderJPanel = new JPanel();
		menuBodyJPanel = new JPanel(new FlowLayout());
		menuBodyJPanel.setPreferredSize(new Dimension(830, 3000));
		menuFooterJPanel = new JPanel();

		scrollPane = new JScrollPane(menuBodyJPanel);
		scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(895, 520));

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

		//addPizza = new JButton("Adicionar");
		//addPizza.addActionListener(this);

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
		
		menuDados();
		
		menuHeaderJPanel.setBackground(new Color(0x123456));
		menuHeaderJPanel.add(actualizar);
		//menuHeaderJPanel.add(addPizza);
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
					menuDados("admin");
					//System.out.println("Refreshing Menu");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		verificaDados.start();*/
    }
	public void menuDados(){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE id ='"+Home.getUser()+"' ");
			
			count=0;
			
			if(res.next()){
				count++;
				getID = Integer.parseInt(res.getString("id"));

				cardJPanel = new JPanel(new BorderLayout());
				pddcardImgJLabel = new JLabel();
				cardJLabel = new JTextArea();
				cardEditJButton = new JButton("Editar");
				cardDeleteJButton = new JButton("Excluir");
				cardTopBtnsJPanel = new JPanel();
				cardBottomBtnsJPanel = new JPanel();
				cardAllBtnsJPanel = new JPanel(new BorderLayout());

				cardJLabel.setText("   ID: "+res.getString("id")+"\n   Username: "+res.getString("username")+"\n   Nome: "+res.getString("nome")+"   Apelido: "+res.getString("apelido")+"\n   Email: "+res.getString("email")+"   Contacto: "+res.getString("contacto")+"\n   Password: "+res.getString("password")+"   Categoria: "+res.getString("category")+"\n   Crono: "+res.getString("create_date"));
				cardJLabel.setOpaque(true);
				//cardJLabel.setBackground(new Color(0x444444));
				//cardJLabel.setForeground(new Color(0xFFFFFF));
				cardJLabel.setFont(new Font("Dialog", Font.BOLD, 14));
				//cardJLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				cardJLabel.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
				cardJLabel.setEditable(false);
        		cardJLabel.setFocusable(false);  
        		cardJLabel.setWrapStyleWord(true); 
        		cardJLabel.setLineWrap(true);
        		cardJLabel.setBorder(BorderFactory.createEmptyBorder());

				cardEditJButton.addActionListener(this);
				cardDeleteJButton.addActionListener(this);

				cardJPanel.setBackground(new Color(0x444444));
				pddcardImgJLabel.setIcon(pddcardImgResized);

				cardBottomBtnsJPanel.add(cardEditJButton);
				cardBottomBtnsJPanel.add(cardDeleteJButton);
				cardAllBtnsJPanel.add(cardBottomBtnsJPanel, BorderLayout.SOUTH);
				cardBottomBtnsJPanel.setBackground(new Color(0xFFFFFF));

				cardJPanel.add(pddcardImgJLabel, BorderLayout.WEST);
				cardJPanel.add(cardJLabel, BorderLayout.CENTER);
				cardJPanel.add(cardAllBtnsJPanel, BorderLayout.SOUTH);
				menuBodyJPanel.add(cardJPanel);
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
				cardTopBtnsJPanel.remove(cardJButton);
				cardBottomBtnsJPanel.remove(cardEditJButton);
				cardBottomBtnsJPanel.remove(cardDeleteJButton);
				cardAllBtnsJPanel.remove(cardTopBtnsJPanel);
				cardAllBtnsJPanel.remove(cardBottomBtnsJPanel);

				cardJPanel.remove(pddcardImgJLabel);
				cardJPanel.remove(cardJLabel);
				cardJPanel.remove(cardAllBtnsJPanel);
				menuBodyJPanel.remove(cardJPanel);
			}catch(Exception e){
				System.out.println("bug");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			rmMenuDados();
			menuDados();
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

			String sql = "INSERT INTO users (username, nome, apelido, email, contacto, category) VALUES (?, ?, ?, ?, ?, ?)";
			
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
				if (e.getSource() == cardJButton) {
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
				else if (e.getSource() == cardEditJButton) {
					String Pid = ""+getID;
					String nomeP = null;
					String tamP = null;
					String precP = null;
					String catP = null;

					try{
					Connection conn = DBConnection.getConexao();
					Statement stmt = conn.createStatement();
					ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE id='"+Pid+"' ");
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

							String sql = "UPDATE users SET nome=?, tamanho=?, preco=?, categoria=?, updated_at=? WHERE id = ?";

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
				else if (e.getSource() == cardDeleteJButton) {
					String Pid = ""+getID;

					String sql = "DELETE FROM users WHERE id = ?";

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
