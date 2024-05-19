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

import java.io.InputStream;
import javax.imageio.ImageIO;

import src.resources.config.DBConnection;

public class Order extends JPanel implements ActionListener{
	JPanel menuJPanel;
	JPanel menuHeaderJPanel;
	JPanel menuBodyJPanel;
	JPanel menuFooterJPanel;

	JPanel addPizzaJPanel;

	Image pddcardImgIcon;
	Image pddcardImgResize;
	ImageIcon pddcardImgResized;
	JLabel pddcardImgJLabel[];
	JPanel cardJPanel[];
	JTextArea cardJLabel[];
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

    public Order(String accessLevel){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel[100];
		cardJLabel = new JTextArea[100];
		pddcardImgJLabel= new JLabel[100];
		cardJButton = new JButton[100];
		cardBtnsJPanel = new JPanel[100];

		try (InputStream is = Order.class.getResourceAsStream("/src/resources/assets/order.png")) {
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
        scrollPane.setPreferredSize(new Dimension(860, 475));

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
		//menuHeaderJPanel.add(verPizza);
		//menuHeaderJPanel.add(addPizza);
		//menuHeaderJPanel.add(editPizza);
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
					System.out.println("Verificando Pedidos");
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
			ResultSet res = stmt.executeQuery("SELECT pedido.id AS id, pedido.nome AS nomeP, pedido.tamanho AS tam, pedido.preco AS preco, pedido.quantidade AS quant, pedido.status AS stt, pedido.created_at AS crea, users.id AS id_user, users.nome AS nomeU FROM pedido JOIN users ON pedido.id_user=users.id ");
			count=0;
			
			while(res.next()){
				count++;

				cardJPanel[count] = new JPanel(new BorderLayout());
				pddcardImgJLabel[count] = new JLabel();
				cardJLabel[count] = new JTextArea();
				cardJButton[count] = new JButton("Atender");
				cardBtnsJPanel[count] = new JPanel();

				cardJLabel[count].setText("   ID: "+res.getString("id")+"\n   ID_user: "+res.getString("id_user")+"\n   Nome Usuario: "+res.getString("nomeU")+"\n   Nome Pizza: "+res.getString("nomeP")+"\n   Tamanho: "+res.getString("tam")+"\n   Preco: "+res.getString("preco")+"\n   Quantidade: "+res.getString("quant")+"\n   Status: "+res.getString("stt")+"\n   Crono: "+res.getString("crea")+"\n   Total: "+(res.getInt("preco")*res.getInt("quant"))+",00 MZN");
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
				cardBtnsJPanel[count].add(cardJButton[count]);
				cardBtnsJPanel[count].setBackground(new Color(0xFFFFFF));

				cardJPanel[count].setPreferredSize(new Dimension(200, 390));
				cardJPanel[count].setBackground(new Color(0x444444));
				pddcardImgJLabel[count].setIcon(pddcardImgResized);

				cardJPanel[count].add(pddcardImgJLabel[count], BorderLayout.NORTH);
				cardJPanel[count].add(cardJLabel[count], BorderLayout.CENTER);
				cardJPanel[count].add(cardBtnsJPanel[count], BorderLayout.SOUTH);
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
			menuJPanel.add(menuBodyJPanel, BorderLayout.CENTER);
		}
		else if(e.getSource() == addPizza){
			menuBodyJPanel.setVisible(false);
			scrollPane.setVisible(false);
			menuFooterJPanel.setVisible(false);
			revalidate();
			repaint();
			menuJPanel.add(addPizzaJPanel, BorderLayout.CENTER);
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
