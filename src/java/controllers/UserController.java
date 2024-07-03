package src.java.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import src.java.models.User;
import src.resources.config.DBConnection;

public class UserController {

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        try{
            Connection conn = DBConnection.getConexao();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM users");
            /*
             * PreparedStatement pstmt = conn.prepareStatement(sql);
             * ResultSet rs = pstmt.executeQuery();
             */
    
            while (res.next()) {
                User u = new User();

                u.setId(res.getInt("id"));
                u.setUsuario(res.getString("usuario"));
                u.setFirstName(res.getString("nome"));
                u.setLastName(res.getString("apelido"));
                u.setEmail(res.getString("email"));
                u.setCategoria(res.getString("category"));
                u.setContacto(res.getString("contacto"));
                users.add(u);
            }
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
    
        return users;
    }
     
    public void save(User user) {
        String sql = "INSERT INTO users (usuario, nome, apelido, email, contacto, password, category) VALUES (?, ?, ?, ?, ?, ?, 'user')";
		
		PreparedStatement ps = null;
		try {
			ps = DBConnection.getConexao().prepareStatement(sql);
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getContacto());
			ps.setString(6, user.getPassword());
			ps.execute();
			ps.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
    }
    public User get(int id) {
        User u = new User();
        try{
            Connection conn = DBConnection.getConexao();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE id='"+id+"' ");
    
            while (res.next()) {
                

                u.setId(res.getInt("id"));
                u.setUsuario(res.getString("usuario"));
                u.setFirstName(res.getString("nome"));
                u.setLastName(res.getString("apelido"));
                u.setEmail(res.getString("email"));
                u.setCategoria(res.getString("category"));
                u.setContacto(res.getString("contacto"));
            }
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return u;
    }
    public void update(User user){
        String sql = "UPDATE users SET usuario=?, nome=?, apelido=?, email=?, contacto=? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = DBConnection.getConexao().prepareStatement(sql);
			ps.setString(1, user.getUsuario());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
            ps.setString(5, user.getContacto());
			//ps.setString(6, ""+new Timestamp(System.currentTimeMillis()));
			ps.setString(6, ""+user.getId());
			ps.execute();
			ps.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
    }
    public void delete(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = DBConnection.getConexao().prepareStatement(sql);
			ps.setString(1, ""+id);
			ps.execute();
			ps.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
    }
    
    /*public List<User> search(String keyword) {
        if (keyword != null) {
            List<User> users = new ArrayList<>();
            try{
                Connection conn = DBConnection.getConexao();
                Statement stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("SELECT u FROM users u WHERE CONCAT(u.nome, ' ', u.apelido, ' ', u.category) LIKE %'"+keyword+"'1%");
            
                while (res.next()) {
                  int id = res.getInt("id");
                  String usr = res.getString("usuario");
                  String fN = res.getString("nome");
                  String lN = res.getString("apelido");
                  String eL = res.getString("email");
                  String ctg = res.getString("category");
                  String ctt = res.getString("contacto");
                  String cAt = res.getString("created_at");
                
                  users.add(new User(id, usr, fN, lN, eL, ctg, ctt, cAt));
                }
            }catch(SQLException sqe){
                sqe.printStackTrace();
            }
            return users;
        }
        return findAll();
    }
     
    */
    
}

