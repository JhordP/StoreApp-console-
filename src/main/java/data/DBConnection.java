package data;
import java.sql.*;


public class DBConnection {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PW = "admin";

    protected static Connection initiateConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PW);
    }
}
