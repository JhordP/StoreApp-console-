package dataobjects;
import java.sql.*;
import data.DBConnection;

public class Database extends DBConnection {
    
    public static Connection connect() throws SQLException { 
        return initiateConnection(); 
    }

    public static void close(Connection con, PreparedStatement stm) throws SQLException {
        stm.close();
        con.close();
    }
    public static void close(Connection con, PreparedStatement stm, ResultSet res) throws SQLException {
        res.close();
        stm.close();
        con.close();
    }
}
