package operational;

import java.io.*;
import java.util.*;

import dataobjects.ItemJDBC;
import domain.Item;

public class AdminOperation implements Operation{

    BufferedReader reader;

    public AdminOperation() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    @Override
    public void menu() {
        System.out.println("Type the option number to select the operation to do in the DataBase.");
        StringBuilder menuOptions = new StringBuilder();
        menuOptions.append("1)View Items.\n")
                   .append("2)Add Item.\n")
                   .append("3)Modify Item.\n")
                   .append("4)Delete Item.");
        System.out.println(menuOptions);
        try {
            int option = Integer.parseInt(reader.readLine());

            switch (option) {
                case 1: viewItems(); 
                    break;
                
                case 2: addItem();
                    break;

                case 3: modifyItem();
                    break;

                case 4: deleteItem();
                    break;

                default: System.out.println("Option incorrect. Try again."); menu();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage()+e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getMessage()+e.getStackTrace());
        }
        
    }   

    private void viewItems() {
        try {
            List<Item> items = ItemJDBC.select();
            items.forEach(item -> {
            System.out.println(item);
            });

            String menu;
            boolean equals_Y;
            boolean equals_N;
            
            do {
                System.out.println("Return to menu? Type 'Y' for Yes or 'N' to exit.");
                menu = reader.readLine();
                equals_Y = menu.equalsIgnoreCase("Y");
                equals_N = menu.equalsIgnoreCase("N");
                if (menu.equalsIgnoreCase("Y") || menu.equalsIgnoreCase("N")) {
                    if (menu.equalsIgnoreCase("Y")) {
                        menu();
                    } else {
                        goodBye();  
                    }    
                } 
            } while (!equals_Y && !equals_N);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        
    }

    private void addItem() {
        try {
            System.out.print("Item Name: ");
            String itemName = reader.readLine();

            System.out.print("Item Price: ");
            double itemPrice = Double.parseDouble(reader.readLine());

            Item item = new Item(itemName, itemPrice);
            ItemJDBC.insert(item);
            viewItems();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void modifyItem() {
        try {
            System.out.println("Item id to be modified:");
            int itemId = Integer.parseInt(reader.readLine());

            System.out.print("New Item Name: ");
            String itemName = reader.readLine();

            System.out.print("New Item Price: ");
            double itemPrice = Double.parseDouble(reader.readLine());

            Item item = new Item(itemId, itemName, itemPrice);
            ItemJDBC.update(item);
            viewItems();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void deleteItem() {
        try {
            System.out.println("Item id to be deleted:");
            int itemId = Integer.parseInt(reader.readLine());

            Item item = new Item(itemId);
            ItemJDBC.delete(item);
            viewItems();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void goodBye() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Admin User Logging Out...");    
    }
    

}
