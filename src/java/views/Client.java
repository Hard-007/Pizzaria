package src.java.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;


import src.java.controllers.UserController;
import src.java.models.User;

import java.io.InputStream;
import javax.imageio.ImageIO;

public class Client extends JPanel implements ActionListener{
	List<User> users = new UserController().findAll();

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
	int[] getID;

	JButton actualizar;

	JScrollPane scrollPane;

	JDialog modalDialog;
	JLabel modalDialogTitle;
	JPanel modalDialogHeader;
	JPanel modalDialogBody;
	JPanel modalDialogFooter;
	
	GridBagConstraints constraints;

    public Client(String accessLevel){
		pizzas = new ArrayList<String>();
		cardJPanel = new JPanel[users.size()];
		cardJLabel = new JTextArea[users.size()];
		pddcardImgJLabel= new JLabel[users.size()];
		cardJButton = new JButton[users.size()];
		spinnerModel = new SpinnerNumberModel[users.size()];
		spinner = new JSpinner[users.size()];
		cardEditJButton = new JButton[users.size()];
		cardDeleteJButton = new JButton[users.size()];
		cardTopBtnsJPanel = new JPanel[users.size()];
		cardBottomBtnsJPanel = new JPanel[users.size()];
		cardAllBtnsJPanel = new JPanel[users.size()];
		getID = new int[users.size()];

		constraints = new GridBagConstraints();
        constraints.gridx   = 0;
        constraints.gridy   = GridBagConstraints.RELATIVE;
        constraints.anchor  = GridBagConstraints.CENTER;
        constraints.insets  = new Insets(5, 7, 5, 7);

		try (InputStream is = Order.class.getResourceAsStream("/src/resources/assets/staff-icon.jpg")) {
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
		menuBodyJPanel.setPreferredSize(new Dimension(Setting.getPaneDimension()));
		menuFooterJPanel = new JPanel();

		scrollPane = new JScrollPane(menuBodyJPanel);
		scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(Setting.getScrollPaneDimension()));

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
		count=0;

		for (User user : users) {
			if (user.getCategoria().equals("Client")) {
				count++;

				getID[count] = user.getId();
				cardJPanel[getID[count]] = new JPanel(new BorderLayout());
				pddcardImgJLabel[getID[count]] = new JLabel();
				cardJLabel[getID[count]] = new JTextArea();
				cardEditJButton[getID[count]] = new JButton("Editar");
				cardDeleteJButton[getID[count]] = new JButton("Excluir");
				cardBottomBtnsJPanel[getID[count]] = new JPanel();
				cardAllBtnsJPanel[getID[count]] = new JPanel(new BorderLayout());
				
				cardJLabel[getID[count]].setText("   ID : "+user.getId()+"   Usuario : "+user.getUsuario()+"\n   Nome : "+user.getFirstName()+"\n   Apelido : "+user.getLastName()+"\n   E-mail : "+user.getEmail()+"\n   Contacto: "+user.getContacto());
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
				cardEditJButton[getID[count]].addActionListener(this);
				cardDeleteJButton[getID[count]].addActionListener(this);
				cardJPanel[getID[count]].setBackground(new Color(0x444444));
				pddcardImgJLabel[getID[count]].setIcon(pddcardImgResized);
				if (accessLevel.equals("Client")) {
					cardBottomBtnsJPanel[getID[count]].setVisible(false);
				}
				cardBottomBtnsJPanel[getID[count]].add(cardEditJButton[getID[count]]);
				cardBottomBtnsJPanel[getID[count]].add(cardDeleteJButton[getID[count]]);
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
	}
	public void rmMenuDados(){
		for(int i = 1; i<=count; i++){
			try{
				cardBottomBtnsJPanel[getID[count]].remove(cardEditJButton[getID[count]]);
				cardBottomBtnsJPanel[getID[count]].remove(cardDeleteJButton[getID[count]]);
				cardAllBtnsJPanel[getID[count]].remove(cardBottomBtnsJPanel[getID[count]]);
				cardBottomBtnsJPanel[getID[count]].setBackground(new Color(0xFFFFFF));
				cardJPanel[getID[count]].remove(pddcardImgJLabel[getID[count]]);
				cardJPanel[getID[count]].remove(cardJLabel[getID[count]]);
				cardJPanel[getID[count]].remove(cardAllBtnsJPanel[getID[count]]);
				menuBodyJPanel.remove(cardJPanel[getID[count]]);
			}catch(Exception e){
				System.out.println("bug");
			}
		}
	}
	public void modal(User u){
		JPanel idDiv = new JPanel();
		JPanel usuarioDiv = new JPanel();
		JPanel nomeDiv = new JPanel();
		JPanel apelidoDiv = new JPanel();
		JPanel emailDiv = new JPanel();
		JPanel contactoDiv = new JPanel();

		JLabel idLabel = new JLabel("ID: ");
		JLabel usuarioLabel = new JLabel("Usuario: ");
		JLabel nomeLabel = new JLabel("Nome: ");
		JLabel apelidoLabel = new JLabel("Apelido: ");
		JLabel emailLabel = new JLabel("Email: ");
		JLabel contactoLabel = new JLabel("Contacto: ");

		JTextField idTextField = new JTextField(10);
		JTextField usuarioTextField = new JTextField(10);
		JTextField nomeTextField = new JTextField(10);
		JTextField apelidoTextField = new JTextField(10);
		JTextField emailTextField = new JTextField(10);
		JTextField contactoTextField = new JTextField(10);

		idTextField.setEnabled(false);
		idTextField.setText(""+u.getId());
		usuarioTextField.setText(u.getUsuario());
		nomeTextField.setText(u.getFirstName());
		apelidoTextField.setText(u.getLastName());
		emailTextField.setText(u.getEmail());
		contactoTextField.setText(u.getContacto());

		modalDialog = new JDialog();
		modalDialog.setSize(300, 300);
		modalDialog.setModal(true);
		modalDialog.setUndecorated(true);
		modalDialog.setLocationRelativeTo(menuJPanel);
		modalDialog.setBackground(new Color(0xfff));
		modalDialog.setLayout(new BorderLayout());
		
		modalDialogTitle = new JLabel();
		modalDialogTitle.setText("Editar User");
		modalDialogTitle.setFont(new Font("Arial", Font.BOLD, 20));

		modalDialogHeader = new JPanel();
		modalDialogBody = new JPanel();
		modalDialogFooter = new JPanel();

		modalDialogHeader.add(modalDialogTitle);
		idDiv.add(idLabel);
		idDiv.add(idTextField);
		usuarioDiv.add(usuarioLabel);
		usuarioDiv.add(usuarioTextField);
		nomeDiv.add(nomeLabel);
		nomeDiv.add(nomeTextField);
		apelidoDiv.add(apelidoLabel);
		apelidoDiv.add(apelidoTextField);
		emailDiv.add(emailLabel);
		emailDiv.add(emailTextField);
		contactoDiv.add(contactoLabel);
		contactoDiv.add(contactoTextField);
		modalDialogBody.add(idDiv, constraints);
		modalDialogBody.add(usuarioDiv, constraints);
		modalDialogBody.add(nomeDiv, constraints);
		modalDialogBody.add(apelidoDiv, constraints);
		modalDialogBody.add(emailDiv, constraints);
		modalDialogBody.add(contactoDiv, constraints);
		// Add components to the dialog
		modalDialog.add(modalDialogHeader, BorderLayout.NORTH);
		modalDialog.add(modalDialogBody, BorderLayout.CENTER);
		modalDialog.add(modalDialogFooter, BorderLayout.SOUTH);

		JButton editButton = new JButton("Editar");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				u.setId(Integer.parseInt(idTextField.getText()));
				u.setUsuario(usuarioTextField.getText());
				u.setFirstName(nomeTextField.getText());
				u.setLastName(apelidoTextField.getText());
				u.setEmail(emailTextField.getText());
				u.setContacto(contactoTextField.getText());

				new UserController().update(u);
				JOptionPane.showMessageDialog(null, "User editado");
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
		modalDialogFooter.add(closeButton);
		modalDialogFooter.add(editButton);
		// Display the dialog
		modalDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			rmMenuDados();
			menuDados("Admin");
		}
		else{
			for(int i = 0; i<=count; i++){
				if (e.getSource() == cardEditJButton[getID[i]]) {
					User userID = new UserController().get(getID[i]);
					modal(userID);
				}
				else if (e.getSource() == cardDeleteJButton[getID[i]]) {
					new UserController().delete(getID[i]);
					
					JOptionPane.showMessageDialog(null, "User excluido");
				}
			}
		}
	}

}
