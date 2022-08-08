package domain;

public class Login {

    //Columns
    private int id;
    private String username;
    private String passw;
    private String privilege;

    public Login() {}
    public Login(int id) {
        this.id = id;
    }
    public Login(String username, String passw) {
        this.username = username;
        this.passw = passw;
    }
    public Login(int id, String username, String passw){
        this.id = id;
        this.username = username;
        this.passw = passw;
    }
    public Login(int id, String username, String passw, String privilege) {
        this.id = id;
        this.username = username;
        this.passw = passw;
        this.privilege = privilege;
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassw() {
        return passw;
    }
    public void setPassw(String passw) {
        this.passw = passw;
    }
    public String getPrivilege() {
        return privilege;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
    
    @Override
    public String toString() {
        return "Login [id=" + id + ", passw=" + passw + ", privilege=" + privilege + ", username=" + username + "]";
    }
    
}
