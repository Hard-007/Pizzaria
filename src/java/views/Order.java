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

public class Order extends JPanel implements ActionListener{
	JPanel menuJPanel;
	JPanel menuHeaderJPanel;
	JPanel menuBodyJPanel;
	JPanel menuFooterJPanel;


	Image pddcardImgIcon;
	Image pddcardImgResize;
	ImageIcon pddcardImgResized;
	JLabel pddcardImgJLabel[];
	JPanel cardJPanel[];
	JTextArea cardJLabel[];
	JButton cardJButton[];
	JPanel cardBtnsJPanel[];
	JButton cardDeleteJButton[];
	JPanel cardTopBtnsJPanel[];
	JPanel cardBottomBtnsJPanel[];
	JPanel cardAllBtnsJPanel[];

	ArrayList<String> pizzas;
	Object[][] data;
	int count;
	int[] getID;

	JButton actualizar;

	JScrollPane scrollPane;


    public Order(String accessLevel){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel[100];
		cardJLabel = new JTextArea[100];
		pddcardImgJLabel= new JLabel[100];
		cardJButton = new JButton[100];
		cardDeleteJButton = new JButton[100];
		cardTopBtnsJPanel = new JPanel[100];
		cardBottomBtnsJPanel = new JPanel[100];
		cardAllBtnsJPanel = new JPanel[100];
		getID = new int[100];


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
		scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(995, 600));

		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		
		menuDados(accessLevel);
		
		menuHeaderJPanel.setBackground(new Color(0x123456));
		menuHeaderJPanel.add(actualizar);
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
	public void menuDados(String accessLevel){
		try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM pedido AS pdd JOIN users AS usr ON pdd.id_user=usr.id ");
			
			count=0;
			
			while(res.next()){
				count++;
				getID[count] = Integer.parseInt(res.getString("id"));

				cardJPanel[getID[count]] = new JPanel(new BorderLayout());
				pddcardImgJLabel[getID[count]] = new JLabel();
				cardJLabel[getID[count]] = new JTextArea();
				cardJButton[getID[count]] = new JButton("Atender");
				//cardEditJButton[getID[count]] = new JButton("Editar");
				cardDeleteJButton[getID[count]] = new JButton("Excluir");
				cardTopBtnsJPanel[getID[count]] = new JPanel();
				cardBottomBtnsJPanel[getID[count]] = new JPanel();
				cardAllBtnsJPanel[getID[count]] = new JPanel(new BorderLayout());

				cardJLabel[getID[count]].setText("   ID: "+res.getString("pdd.id")+"\n   ID_user: "+res.getString("pdd.id_user")+"\n   Codigo: "+res.getString("pdd.codigo")+"\n   Nome Usuario: "+res.getString("usr.nome")+"\n   Nome Pizza: "+res.getString("pdd.nome")+"\n   Tamanho: "+res.getString("pdd.tamanho")+"\n   Preco: "+res.getString("pdd.preco")+"00, MZN\n   Quantidade: "+res.getString("pdd.quantidade")+"\n   Status: "+res.getString("pdd.status")+"\n   Crono: "+res.getString("pdd.created_at")+"\n   Total: "+(res.getInt("pdd.preco")*res.getInt("pdd.quantidade"))+",00 MZN");
				cardJLabel[getID[count]].setOpaque(true);
				//cardJLabel[getID[count]].setBackground(new Color(0x444444));
				//cardJLabel[getID[count]].setForeground(new Color(0xFFFFFF));
				cardJLabel[getID[count]].setFont(new Font("Dialog", Font.BOLD, 13));
				//cardJLabel[getID[count]].setHorizontalAlignment(SwingConstants.CENTER);
				
				cardJLabel[getID[count]].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
				cardJLabel[getID[count]].setEditable(false);
        		cardJLabel[getID[count]].setFocusable(false);  
        		cardJLabel[getID[count]].setWrapStyleWord(true); 
        		cardJLabel[getID[count]].setLineWrap(true);
        		cardJLabel[getID[count]].setBorder(BorderFactory.createEmptyBorder());

				cardJButton[getID[count]].addActionListener(this);
				//cardEditJButton[getID[count]].addActionListener(this);
				cardDeleteJButton[getID[count]].addActionListener(this);

				cardJPanel[getID[count]].setBackground(new Color(0x444444));
				pddcardImgJLabel[getID[count]].setIcon(pddcardImgResized);

				if (accessLevel.equals("user")) {
					cardBottomBtnsJPanel[getID[count]].setVisible(false);
				}

				cardTopBtnsJPanel[getID[count]].add(cardJButton[getID[count]]);
				//cardBottomBtnsJPanel[getID[count]].add(cardEditJButton[getID[count]]);
				cardBottomBtnsJPanel[getID[count]].add(cardDeleteJButton[getID[count]]);
				cardAllBtnsJPanel[getID[count]].add(cardTopBtnsJPanel[getID[count]], BorderLayout.NORTH);
				cardAllBtnsJPanel[getID[count]].add(cardBottomBtnsJPanel[getID[count]], BorderLayout.SOUTH);
				cardBottomBtnsJPanel[getID[count]].setBackground(new Color(0xFFFFFF));

				cardJPanel[getID[count]].add(pddcardImgJLabel[getID[count]], BorderLayout.NORTH);
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
				cardTopBtnsJPanel[getID[i]].remove(cardJButton[getID[i]]);
				//cardBottomBtnsJPanel[getID[i]].remove(cardEditJButton[getID[i]]);
				cardBottomBtnsJPanel[getID[i]].remove(cardDeleteJButton[getID[i]]);
				cardAllBtnsJPanel[getID[i]].remove(cardTopBtnsJPanel[getID[i]]);
				cardAllBtnsJPanel[getID[i]].remove(cardBottomBtnsJPanel[getID[i]]);

				cardJPanel[getID[i]].remove(pddcardImgJLabel[getID[i]]);
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
			menuDados("admin");
		}
		else{
			for(int i = 0; i<=count; i++){
				if (e.getSource() == cardDeleteJButton[getID[i]]) {
					String Pid = ""+getID[i];

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
					JOptionPane.showMessageDialog(null, "Item menu excluido");
				}
			}
		}
	}

}
