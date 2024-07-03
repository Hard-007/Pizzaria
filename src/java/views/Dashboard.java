package src.java.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.java.controllers.DashboardController;

public class Dashboard extends JPanel implements ActionListener{
	String dash[][] = new DashboardController().dash();

	JPanel dashJPanel;
	JPanel dashHeaderJPanel;
	JPanel dashBodyJPanel;

	JLabel nrLucro;
	JLabel nrPizzas;
	JLabel nrFuncionarios;
	JLabel nrClientes;
	JLabel nrPeddPendente;
	JLabel nrPeddFeito;

	JButton actualizar;

	JScrollPane scrollPane;

    public Dashboard(){

		dashJPanel = new JPanel(new BorderLayout());
		dashHeaderJPanel = new JPanel();
		dashBodyJPanel = new JPanel(new FlowLayout());
		dashBodyJPanel.setPreferredSize(new Dimension(830, 3000));
		
		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(this);

		nrLucro = new JLabel("Lucro");
		nrPizzas = new JLabel("Pizzas");
		nrFuncionarios = new JLabel("Funcionarios ");
		nrClientes = new JLabel("Clientes");
		nrPeddPendente = new JLabel("Pedidos pendentes");
		nrPeddFeito = new JLabel("Pedidos atendidos");

		dashPanel();
		dashDados();

		dashBodyJPanel.add(nrLucro);
		dashBodyJPanel.add(nrPizzas);
		dashBodyJPanel.add(nrFuncionarios);
		dashBodyJPanel.add(nrClientes);
		dashBodyJPanel.add(nrPeddPendente);
		dashBodyJPanel.add(nrPeddFeito);

		scrollPane = new JScrollPane(dashBodyJPanel);
		scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(Setting.getScrollPaneDimension()));

		dashHeaderJPanel.add(actualizar);
		dashHeaderJPanel.setBackground(new Color(0x123456));

		dashJPanel.add(dashHeaderJPanel, BorderLayout.NORTH);
		dashJPanel.add(scrollPane, BorderLayout.CENTER);
		add(dashJPanel);
        
		/*Thread verificaDados = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(5000); 
					dashDados();
					System.out.println("Verificando Dashboard");
					//notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		verificaDados.start();*/
    }
	public void dashPanel(){

		nrLucro.setOpaque(true);
		nrLucro.setBackground(new Color(0x444444));
		nrLucro.setForeground(new Color(0xFFFFFF));
		nrLucro.setFont(new Font("Dialog", Font.BOLD, 18));
		nrLucro.setPreferredSize(new Dimension(200, 100));
		nrLucro.setHorizontalAlignment(SwingConstants.CENTER);
		nrPizzas.setOpaque(true);
		nrPizzas.setBackground(new Color(0x444444));
		nrPizzas.setForeground(new Color(0xFFFFFF));
		nrPizzas.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPizzas.setPreferredSize(new Dimension(110, 100));
		nrPizzas.setHorizontalAlignment(SwingConstants.CENTER);
		nrFuncionarios.setOpaque(true);
		nrFuncionarios.setBackground(new Color(0x444444));
		nrFuncionarios.setForeground(new Color(0xFFFFFF));
		nrFuncionarios.setFont(new Font("Dialog", Font.BOLD, 18));
		nrFuncionarios.setPreferredSize(new Dimension(140, 100));
		nrFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		nrClientes.setOpaque(true);
		nrClientes.setBackground(new Color(0x444444));
		nrClientes.setForeground(new Color(0xFFFFFF));
		nrClientes.setFont(new Font("Dialog", Font.BOLD, 18));
		nrClientes.setPreferredSize(new Dimension(130, 100));
		nrClientes.setHorizontalAlignment(SwingConstants.CENTER);
		nrPeddPendente.setOpaque(true);
		nrPeddPendente.setBackground(new Color(0x444444));
		nrPeddPendente.setForeground(new Color(0xFFFFFF));
		nrPeddPendente.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPeddPendente.setPreferredSize(new Dimension(210, 100));
		nrPeddPendente.setHorizontalAlignment(SwingConstants.CENTER);
		nrPeddFeito.setOpaque(true);
		nrPeddFeito.setBackground(new Color(0x444444));
		nrPeddFeito.setForeground(new Color(0xFFFFFF));
		nrPeddFeito.setFont(new Font("Dialog", Font.BOLD, 18));
		nrPeddFeito.setPreferredSize(new Dimension(200, 100));
		nrPeddFeito.setHorizontalAlignment(SwingConstants.CENTER);

	}
	public void dashDados(){
			nrLucro.setText("Lucro\n "+dash[0][0]+",00 MZN");
			nrPizzas.setText(" "+4+" Pizzas");
			nrPeddPendente.setText(" "+2+" Pedido pendentes");
			nrPeddFeito.setText(" "+4+" Pedido atendidos ");
			nrFuncionarios.setText(" "+5+" Funcionarios");
			nrClientes.setText(" "+3+" Clientes");
	}


	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == actualizar) {
			dashDados();
		}
	}
	
}
