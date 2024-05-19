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


        cardImgIcon		= new ImageIcon("src/resources/assets/pizza-ico.png");
        cardImgResize 	= cardImgIcon.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH);
        cardImgResized	= new ImageIcon(cardImgResize);

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

		verPizza = new JButton("Ver pizza");
		addPizza = new JButton("Adicionar Pizza");
		editPizza = new JButton("Editar Pizza");
        verPizza.addActionListener(this);
		addPizza.addActionListener(this);
		editPizza.addActionListener(this);

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

		menuJPanel.add(menuHeaderJPanel, BorderLayout.NORTH);
		menuJPanel.add(scrollPane, BorderLayout.CENTER);
		menuJPanel.add(menuFooterJPanel, BorderLayout.SOUTH);

		add(menuJPanel);

		/*Thread verificaDados = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(5000); 
					menuDados();
					System.out.println("Verificando Menu");
					//notify();
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
			ResultSet res = stmt.executeQuery("SELECT * FROM menu");
			count=0;
			
			while(res.next()){
				count++;

				cardJPanel[count] = new JPanel(new BorderLayout());
				cardImgJLabel[count] = new JLabel();
				cardJLabel[count] = new JTextArea();
				spinnerModel[count] = new SpinnerNumberModel(0, 0, 100, 1);
				spinner[count] = new JSpinner(spinnerModel[count]);
				cardJButton[count] = new JButton("Pedir");
				cardEditJButton[count] = new JButton("Editar");
				cardDeleteJButton[count] = new JButton("Excluir");
				cardTopBtnsJPanel[count] = new JPanel();
				cardBottomBtnsJPanel[count] = new JPanel();
				cardAllBtnsJPanel[count] = new JPanel(new BorderLayout());

				cardJLabel[count].setText("   ID: "+res.getString("id")+"\n   Nome: "+res.getString("nome")+"\n   Tamanho: "+res.getString("tamanho")+"\n   Categoria: "+res.getString("categoria")+"\n   Preco: "+res.getString("preco"));
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

				cardJButton[count].addActionListener(this);
				cardTopBtnsJPanel[count].add(spinner[count]);
				cardTopBtnsJPanel[count].add(cardJButton[count]);
				cardBottomBtnsJPanel[count].add(cardEditJButton[count]);
				cardBottomBtnsJPanel[count].add(cardDeleteJButton[count]);
				cardAllBtnsJPanel[count].add(cardTopBtnsJPanel[count], BorderLayout.NORTH);
				cardAllBtnsJPanel[count].add(cardBottomBtnsJPanel[count], BorderLayout.SOUTH);
				cardTopBtnsJPanel[count].setBackground(new Color(0xFFFFFF));
				cardBottomBtnsJPanel[count].setBackground(new Color(0xFFFFFF));

				cardJPanel[count].setBackground(new Color(0x444444));
				cardImgJLabel[count].setIcon(cardImgResized);

				cardJPanel[count].add(cardImgJLabel[count], BorderLayout.NORTH);
				cardJPanel[count].add(cardJLabel[count], BorderLayout.CENTER);
				cardJPanel[count].add(cardAllBtnsJPanel[count], BorderLayout.SOUTH);
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
			menuHeaderJPanel.setVisible(false);
			menuBodyJPanel.setVisible(true);
			scrollPane.setVisible(true);
			menuFooterJPanel.setVisible(true);
			revalidate();
			repaint();
			menuJPanel.add(scrollPane, BorderLayout.CENTER);
		}
		else if(e.getSource() == addPizza){
			/*menuBodyJPanel.setVisible(false);
			scrollPane.setVisible(false);
			menuFooterJPanel.setVisible(false);
			revalidate();
			repaint();
			menuJPanel.add(addPizzaJPanel, BorderLayout.CENTER);*/
			 // Create the modal dialog
			JDialog modalDialog = new JDialog();
			modalDialog.setTitle("Modal Dialog");
			modalDialog.setModal(true);
			modalDialog.setSize(300, 220);
			modalDialog.setUndecorated(true);
			modalDialog.setLocationRelativeTo(menuJPanel);
			modalDialog.setBackground(new Color(0xfebd00));
			modalDialog.setLayout(new BorderLayout());
			
			JPanel modalDialogHeader = new JPanel();
			JPanel modalDialogBody = new JPanel();
			JPanel modalDialogFooter = new JPanel();
			JPanel nomeDiv = new JPanel();
			JPanel tamDiv = new JPanel();
			JPanel precDiv = new JPanel();
			JPanel catDiv = new JPanel();

			JLabel modalDialogTitle = new JLabel("Adicionar Pizza");
			modalDialogTitle.setFont(new Font("Arial", Font.BOLD, 20));
			JButton closeButton = new JButton("Cancelar");
			closeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					modalDialog.dispose();
					System.out.println("modal off");
				}
			});

			GridBagConstraints constraints = new GridBagConstraints();
        	constraints.gridx   = 0;
        	constraints.gridy   = GridBagConstraints.RELATIVE;
        	constraints.anchor  = GridBagConstraints.CENTER;
        	constraints.insets  = new Insets(5, 7, 5, 7);

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
			modalDialogFooter.add(closeButton);
			modalDialogFooter.add(addPizzJButton);
			
			// Add components to the dialog
			modalDialog.add(modalDialogHeader, BorderLayout.NORTH);
			modalDialog.add(modalDialogBody, BorderLayout.CENTER);
			modalDialog.add(modalDialogFooter, BorderLayout.SOUTH);

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
			for(int i = 0; i<100; i++){
				if (e.getSource() == cardJButton[i]) {
					String Pid = ""+i;
					String Pquant = ""+(Integer) spinner[i].getValue();
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
					JOptionPane.showMessageDialog(addPizzaJPanel, "Pedido adicionado");
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