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

public class MyOrder extends JPanel implements ActionListener{
	JPanel myOrderJPanel;
	JPanel menuHeaderJPanel;
	JPanel menuBodyJPanel;
	JPanel menuFooterJPanel;

	JPanel addPizzaJPanel;

	ImageIcon pdcardImgIcon;
	Image pdcardImgResize;
	ImageIcon pdcardImgResized;
	JLabel pdcardImgJLabel[];
	JPanel cardJPanel[];
	JTextArea cardJLabel[];
	//JButton cardJButton[];
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

    public MyOrder(String accessLevel){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel[100];
		cardJLabel = new JTextArea[100];
		pdcardImgJLabel= new JLabel[100];
		//cardJButton = new JButton[100];
		cardBtnsJPanel = new JPanel[100];


        pdcardImgIcon		= new ImageIcon("/src/resources/assets/myorder.png");
        pdcardImgResize 	= pdcardImgIcon.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH);
        pdcardImgResized	= new ImageIcon(pdcardImgResize);

        myOrderJPanel = new JPanel(new BorderLayout());
		menuHeaderJPanel = new JPanel();
		menuBodyJPanel = new JPanel(new FlowLayout());
		menuBodyJPanel.setPreferredSize(new Dimension(830, 3000));
		menuFooterJPanel = new JPanel();

		scrollPane = new JScrollPane(menuBodyJPanel);
        scrollPane.setPreferredSize(new Dimension(860, 470));

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

		myOrderJPanel.add(menuHeaderJPanel, BorderLayout.NORTH);
		myOrderJPanel.add(scrollPane, BorderLayout.CENTER);
		myOrderJPanel.add(menuFooterJPanel, BorderLayout.SOUTH);

		add(myOrderJPanel);

		/*Thread verificaDados = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(5000); 
					menuDados();
					System.out.println("Verificando Meus Pedidos");
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
			ResultSet res = stmt.executeQuery("SELECT * FROM pedido WHERE id_user='"+Home.getUser()+"' ");
			
			while(res.next()){
				count++;

				cardJPanel[count] = new JPanel(new BorderLayout());
				pdcardImgJLabel[count] = new JLabel();
				cardJLabel[count] = new JTextArea();
				cardBtnsJPanel[count] = new JPanel();

				cardJLabel[count].setText("   ID: "+res.getString("id")+"\n   Nome Pizza: "+res.getString("nome")+"\n   Tamanho: "+res.getString("tamanho")+"\n   Preco: "+res.getString("preco")+"\n   Quantidade: "+res.getString("quantidade")+"\n   Status: "+res.getString("status")+"\n   Crono: "+res.getString("created_at")+"\n   Total: "+(res.getInt("preco")*res.getInt("quantidade"))+",00 MZN");
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
				cardBtnsJPanel[count].setBackground(new Color(0xFFFFFF));

				cardJPanel[count].setPreferredSize(new Dimension(200, 200));
				cardJPanel[count].setBackground(new Color(0x444444));
				pdcardImgJLabel[count].setIcon(pdcardImgResized);

				cardJPanel[count].add(pdcardImgJLabel[count], BorderLayout.NORTH);
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
	}

}

/* *
ResultSet res = stmt.executeQuery("SELECT pedido.id AS id, pedido.nome AS nomeP, pedido.tamanho AS tam, pedido.preco AS preco, pedido.quantidade AS quant, pedido.status AS stt, pedido.created_at AS crea, users.id AS id_user, users.nome AS nomeU FROM pedido JOIN users ON pedido.id_user=users.id ");
			count=0;
			
			while(res.next()){
				count++;

				cardJPanel[count] = new JPanel(new BorderLayout());
				cardImgJLabel[count] = new JLabel();
				cardJLabel[count] = new JTextArea();
				cardJButton[count] = new JButton("Atender");
				cardBtnsJPanel[count] = new JPanel();

				cardJLabel[count].setText("   ID: "+res.getString("id")+"\n   ID_user: "+res.getString("id_user")+"\n   Nome Usuario: "+res.getString("nomeU")+"\n   Nome Pizza: "+res.getString("nomeP")+"\n   Tamanho: "+res.getString("tam")+"\n   Preco: "+res.getString("preco")+"\n   Quantidade: "+res.getString("quant")+"\n   Status: "+res.getString("stt")+"\n   Crono: "+res.getString("crea"));
				cardJLabel[count].setOpaque(true);
*/