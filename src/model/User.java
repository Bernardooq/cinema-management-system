package model;

public class User {
    private int id;
    private String email;
    private String passwd;
    private String username;

    // Constructor sin id, ya que será generado automáticamente
    public User(String email, String passwd, String username) {
        this.email = email;
        this.passwd = passwd;
        this.username = username;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
