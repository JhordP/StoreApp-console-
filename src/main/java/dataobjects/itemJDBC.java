package dataobjects;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Item;

public class ItemJDBC {

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
            ItemJDBC.databaseConnection = Database.connect();
            ItemJDBC.statement = ItemJDBC.databaseConnection.prepareStatement(SQL_SELECT);
            ResultSet dataResult = ItemJDBC.statement.executeQuery();
            while (dataResult.next()) {
                int itemId = dataResult.getInt("item_id");
                String itemName = dataResult.getString("item_name");
                double itemPrice = dataResult.getDouble("item_price");
                ItemJDBC.items.add(new Item(itemId, itemName, itemPrice));
            }
            Database.close(ItemJDBC.databaseConnection, ItemJDBC.statement, dataResult);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        return ItemJDBC.items;
    }

    public static void insert(Item item) {
        
        try {
            ItemJDBC.databaseConnection = Database.connect();
            ItemJDBC.statement = ItemJDBC.databaseConnection.prepareStatement(SQL_INSERT);
            
            ItemJDBC.statement.setString(1, item.getItemName());
            ItemJDBC.statement.setDouble(2, item.getItemPrice());

            ItemJDBC.statement.executeUpdate();
            Database.close(ItemJDBC.databaseConnection, ItemJDBC.statement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }        
    }

    public static void update(Item item) {
        try {
            ItemJDBC.databaseConnection = Database.connect();
            ItemJDBC.statement = ItemJDBC.databaseConnection.prepareStatement(SQL_UPDATE);
            
            ItemJDBC.statement.setString(1, item.getItemName());
            ItemJDBC.statement.setDouble(2, item.getItemPrice());
            ItemJDBC.statement.setInt(3, item.getItemId());

            ItemJDBC.statement.executeUpdate();
            Database.close(ItemJDBC.databaseConnection, ItemJDBC.statement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    public static void delete(Item item) {
        try {
            ItemJDBC.databaseConnection = Database.connect();
            ItemJDBC.statement = ItemJDBC.databaseConnection.prepareStatement(SQL_DELETE);
            
            ItemJDBC.statement.setInt(1, item.getItemId());

            ItemJDBC.statement.executeUpdate();
            Database.close(ItemJDBC.databaseConnection, ItemJDBC.statement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
