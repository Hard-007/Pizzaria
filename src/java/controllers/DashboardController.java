package src.java.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import src.java.views.Menu;
import src.resources.config.DBConnection;

public class DashboardController {
    
    public String[][] dash(){
        int i=0;
        String results[][] = new String[8][6];
        
        try {
			Connection conn = DBConnection.getConexao();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT (SELECT sum(preco * quantidade) FROM pedido WHERE status='atendido') AS lucro, (SELECT count(*) FROM menu) AS p, (SELECT count(*) FROM pedido WHERE status='pendente') AS pdp, (SELECT count(*) FROM pedido WHERE status='atendido') AS pda, (SELECT count(*) FROM users WHERE category = 'staff') AS uf, (SELECT count(*) FROM users WHERE category = 'user') AS uc");

			while(res.next()){
				
				results[i][0] = ""+ res.getInt("lucro");
				results[i][1] = ""+ res.getInt("p");
				results[i][2] = ""+ res.getInt("pdp");
			    results[i][3] = ""+ res.getInt("pda");
				results[i][4] = ""+ res.getInt("uf");
				results[i][5] = ""+ res.getInt("uc");

                i++;
			}
		}
		catch(SQLException exp) {
			exp.printStackTrace();
		}
        return results;
    }
}
