package dataobjects;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Item;

public class itemJDBC {

    private static List<Item> items;
    private static Connection databaseConnection;
    private static PreparedStatement statement;

    private static final String SQL_SELECT = "SELECT item_id, item_name, item_price FROM item";
    private static final String SQL_INSERT = "INSERT INTO item(item_name, item_price) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE item SET item_name = ?, item_price = ? WHERE item_id = ?";
    private static final String SQL_DELETE = "DELETE FROM item WHERE item_id = ?";

    public static List<Item> select() {
        items = new ArrayList<Item>();
        try {
            itemJDBC.databaseConnection = Database.connect();
            itemJDBC.statement = itemJDBC.databaseConnection.prepareStatement(SQL_SELECT);
            ResultSet dataResult = itemJDBC.statement.executeQuery();
            while (dataResult.next()) {
                int itemId = dataResult.getInt("item_id");
                String itemName = dataResult.getString("item_name");
                double itemPrice = dataResult.getDouble("item_price");
                itemJDBC.items.add(new Item(itemId, itemName, itemPrice));
            }
            Database.close(itemJDBC.databaseConnection, itemJDBC.statement, dataResult);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        return itemJDBC.items;
    }

    public static void insert(Item item) {
        
        try {
            itemJDBC.databaseConnection = Database.connect();
            itemJDBC.statement = itemJDBC.databaseConnection.prepareStatement(SQL_INSERT);
            
            itemJDBC.statement.setString(1, item.getItemName());
            itemJDBC.statement.setDouble(2, item.getItemPrice());

            itemJDBC.statement.executeUpdate();
            Database.close(itemJDBC.databaseConnection, itemJDBC.statement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }        
    }

    public static void update(Item item) {
        try {
            itemJDBC.databaseConnection = Database.connect();
            itemJDBC.statement = itemJDBC.databaseConnection.prepareStatement(SQL_UPDATE);
            
            itemJDBC.statement.setString(1, item.getItemName());
            itemJDBC.statement.setDouble(2, item.getItemPrice());
            itemJDBC.statement.setInt(3, item.getItemId());

            itemJDBC.statement.executeUpdate();
            Database.close(itemJDBC.databaseConnection, itemJDBC.statement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    public static void delete(Item item) {
        try {
            itemJDBC.databaseConnection = Database.connect();
            itemJDBC.statement = itemJDBC.databaseConnection.prepareStatement(SQL_DELETE);
            
            itemJDBC.statement.setInt(1, item.getItemId());

            itemJDBC.statement.executeUpdate();
            Database.close(itemJDBC.databaseConnection, itemJDBC.statement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
