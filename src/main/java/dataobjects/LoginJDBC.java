package dataobjects;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Login;

public class LoginJDBC {
    private static final String SQL_SELECT = "SELECT * FROM login";
    // private static final String SQL_INSERT = "INSERT INTO login(username, passw) VALUES(?, ?)";
    // private static final String SQL_UPDATE = "UPDATE login SET username = ?, passw = ? WHERE id = ?";
    // private static final String SQL_DELETE = "DELETE FROM login WHERE id = ?";

    //     //Menu
    //     public List<Login> menu(int option) {
    //         var input = new Scanner(System.in);
    //         List<Login> userList = new ArrayList<>();
    //         Login user = null;
    
    //         int id;
    //         String username;
    //         String password;
    
    //         switch (option) {
    //             case 1:
    //                 userList = select();
    //                 break;
    //             case 2:
    //                 System.out.print("New Username: "); username = input.next();
    //                 System.out.print("New Username's password: "); password = input.next();
    //                 user = new Login(username, password);
    //                 insert(user);
    //                 userList = select();
    //                 break;
    //             case 3:
    //                 System.out.print("Username: "); username = input.next();
    //                 System.out.print("Password: "); password = input.next();
    //                 System.out.println("User ID to be modified: "); id = input.nextInt();
    //                 update(user);
    //                 userList = select();
    //                 break;
    //             case 4:
    //                 System.out.print("ID number: "); id = input.nextInt();
    //                 user = new Login(id);
    //                 delete(user);
    //                 userList = select();
    //                 break;
    //             default:
    //                 System.out.println("Invalid option number. Try again.");
    //                 break;
    //         }
    //         input.close();
    //         return userList;
    //     }

    public List<Login> select() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Login> users = new ArrayList<>();

        try {
            con = Database.connect();
            statement = con.prepareStatement(SQL_SELECT);
            rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String usr = rs.getString("username");
                String pw = rs.getString("passw");
                String priv = rs.getString("privilege");
                Login user = new Login(id, usr, pw, priv);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        } finally {
            try {
                Database.close(con, statement, rs);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        return users;
    }

    // public int insert(Login user) {
    //     Connection con = null;
    //     PreparedStatement statement = null;
    //     int numRecords = 0;
    //     try {
    //         con = Database.connect();
    //         statement = con.prepareStatement(SQL_INSERT);
    //         statement.setString(1, user.getUsername());
    //         statement.setString(2, user.getPassw());

    //         numRecords = statement.executeUpdate();
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //         e.printStackTrace(System.out);
    //     } finally {
    //         try {
    //             Database.close(con, statement);
    //         } catch (SQLException e) {
    //             System.out.println(e.getMessage());
    //             e.printStackTrace(System.out);
    //         }
    //     }
    //     return numRecords;
    // }

    // public int update(Login user) {
    //     Connection con = null;
    //     PreparedStatement statement = null;
    //     int numRecords = 0;
    //     try {
    //         con = Database.connect();
    //         statement = con.prepareStatement(SQL_UPDATE);
    //         statement.setString(1, user.getUsername());
    //         statement.setString(2, user.getPassw());
    //         statement.setInt(3, user.getId());

    //         numRecords = statement.executeUpdate();
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //         e.printStackTrace(System.out);
    //     } finally {
    //         try {
    //             Database.close(con, statement);
    //         } catch (SQLException e) {
    //             System.out.println(e.getMessage());
    //             e.printStackTrace(System.out);
    //         }
    //     }
    //     return numRecords;
    // }

    // public int delete(Login user) {
    //     Connection con = null;
    //     PreparedStatement statement = null;
    //     int numRecords = 0;
    //     try {
    //         con = Database.connect();
    //         statement = con.prepareStatement(SQL_DELETE);
    //         statement.setInt(1, user.getId());

    //         numRecords = statement.executeUpdate();
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //         e.printStackTrace(System.out);
    //     } finally {
    //         try {
    //             Database.close(con, statement);
    //         } catch (SQLException e) {
    //             System.out.println(e.getMessage());
    //             e.printStackTrace(System.out);
    //         }
    //     }
    //     return numRecords;
    // }

    public boolean validate(Login user) {
        //Login usr = new Login();
        var input = new Scanner(System.in);
        List<Login> users = select();
        boolean valid = false;
        int attempts = 1;

        for (Login credential : users) {
            if ((user.getUsername().equals(credential.getUsername())) && (user.getPassw().equals(credential.getPassw()))) 
            {
                valid = true;
            }
        }
        
        while (!valid) {
            System.out.println("Login Access Denied: Wrong username or password.");
            System.out.print("Enter username: ");
            user.setUsername(input.next());
            System.out.print("Enter password: ");
            user.setPassw(input.next());

            //validate(user); //Decided to not use recursivity
            for (Login credential : users) {
                if ((user.getUsername().equals(credential.getUsername())) && (user.getPassw().equals(credential.getPassw()))) 
                {
                    //return true; //Possible solution 
                    valid = true;
                    
                }
            }
            if (valid == false) {
                ++attempts;
            }
            if (attempts>4) {
                valid = false; //Dead code as is supposed to be false inside this. Come on, just in case.
                input.close();
            }
        }

        if (valid) {
            return true;
        } else {
            return false;
        }
    }
}
