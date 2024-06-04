//javac -cp C:\Users\Filipe\Desktop\Java\GUI\Pizzaria;. Pizzaria.java
//javac -cp C:\Users\Filipe\Desktop\Java\GUI\Pizzaria\src\resources\config\mysql-connector-j-8.3.0.jar;. Pizzaria.java
import src.java.views.*;
import src.java.views.auth.*;


import javax.swing.*;


public class Pizzaria extends JFrame{
    /*public Pizzaria(){
        setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));
        
        //add(new Auth());
    }*/
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Home("admin", "1").setVisible(true);
            //new Auth().setVisible(true);
        });
    }
}
