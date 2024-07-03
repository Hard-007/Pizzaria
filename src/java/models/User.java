package src.java.models;

public class User {
    private Integer id;
    private String usuario;
    private String firstName;
    private String lastName;
    private String email;
    private String categoria;
    private String contacto;
    private String password;
    private String createdAt;

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
    	return id;
    }
    public String getUsuario() {
    	return this.usuario;
    }
    public void setUsuario(String usuario) {
    	this.usuario = usuario;
    }
    public String getFirstName() {
    	return this.firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    public String getLastName() {
    	return this.lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getCategoria() {
    	return this.categoria;
    }
    public void setCategoria(String categoria) {
    	this.categoria = categoria;
    }
    public String getContacto() {
    	return this.contacto;
    }
    public void setContacto(String contacto) {
    	this.contacto = contacto;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public String getCreatedAt() {
    	return this.createdAt;
    }

}
