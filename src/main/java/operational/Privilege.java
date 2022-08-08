package operational;
import java.util.*;
import domain.Login;
import dataobjects.LoginJDBC;


public class Privilege {
    
    public void accountTypeValidation() {
        Scanner input = new Scanner(System.in);
        LoginJDBC userData = new LoginJDBC();
        Login usr = new Login();
        boolean isValid = false;

        System.out.print("Enter username: ");
        usr.setUsername(input.next());
        System.out.print("Enter password: ");
        usr.setPassw(input.next());

        isValid = userData.validate(usr);
        //Login validateUser = new Login(usr.getUsername(), usr.getPassw());
        List<Login> users = userData.select();
        users.forEach(u -> {
            //System.out.println(u);
            if (u.getUsername().equals(usr.getUsername())) {
                usr.setPrivilege(u.getPrivilege());
            }
        });


        if (isValid){
            
            System.out.println("Loading data:---");
            if (usr.getPrivilege().equals("admin")) {
                new AdminOperation().menu();
            }
            else if (usr.getPrivilege().equals("shop")) {
                new UserOperation().menu();
            }
            input.close();
    
        } else {
            System.out.println("Too much login attempts. The program will shut down...");
        }
    }
}
