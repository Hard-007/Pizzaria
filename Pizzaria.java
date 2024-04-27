//javac -cp C:\Users\Filipe\Desktop\Java\GUI\Pizzaria\ Pizzaria.java

import java.awt.*;
import javax.swing.*;

import src.main.java.view.*;

public class Pizzaria extends JFrame{
    /*public Pizzaria(){
        setTitle("Pizzaria");
		setSize(1024, 642);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(0xFFFFFF));
        
        add(new Auth(), BorderLayout.CENTER);
    }*/
    
    public static void main(String[] args) {
        new Auth().setVisible(true);
    }
}
