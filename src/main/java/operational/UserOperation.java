package operational;

import java.io.*;
import java.util.*;

import dataobjects.ItemJDBC;
import domain.Item;

public class UserOperation implements Operation{
    BufferedReader reader;
    Item product;
    private static List<Item> shoppingCart;
    double total;

    public UserOperation() {
        UserOperation.shoppingCart = new ArrayList<>();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    @Override
    public void menu() {
        System.out.println("Type the option number to select the operation to do in the Shopping Cart.");
        StringBuilder menuOptions = new StringBuilder();
        menuOptions.append("1)View Items.\n")
                   .append("2)Add Item to the Shopping Cart.\n")
                   .append("3)Delete Item in the Shopping Cart.");
        System.out.println(menuOptions);
        try {
            int option = Integer.parseInt(reader.readLine());

            switch (option) {
                case 1: viewItems(); 
                    break;
                
                case 2: addItemShopping(askId(option));
                    break;

                case 3: deleteItemShopping(askId(option));
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

    public void backToMenu() {
        try {
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

    public void viewItems() {
        
        List<Item> items = ItemJDBC.select();
        items.forEach(item -> {
        System.out.println(item);
        });
        backToMenu();
    }

    //Ask for the item ID to add/delete.
    private Item askId(int option) {

        try {

            if (option == 2) {
                List<Item> items = ItemJDBC.select();
                for (Item item : items) { //Show items
                    System.out.println(item);
                }

                System.out.println("Type the item ID you want to add to the cart.");
                int idItem = Integer.parseInt(reader.readLine());

                if ((idItem < 0) /*|| (idItem > items.size())*/) { //Validates if the digit is not out of index.
                    System.out.println("Incorrect ID. Try again...");
                    askId(option);
                } else {
                    items.forEach(item -> { 
                        if(item.getItemId() == idItem) {
                            this.product = new Item(item.getItemId(), item.getItemName(), item.getItemPrice());
                        }
                    });
                }

            } else if(option == 3) {

                System.out.println("Shopping Cart Items: ");

                for (Item product : UserOperation.shoppingCart) {
                    System.out.println(product);
                }

                System.out.println("Type the item ID you want to remove from the cart.");
                int idItem = Integer.parseInt(reader.readLine());

                if ((idItem < 0) /*|| (idItem > UserOperation.shoppingCart.size())*/) { //Validates if the digit is not out of index.
                    System.out.println("Incorrect ID. Try again...");
                    askId(option);
                } else {
                    UserOperation.shoppingCart.forEach(item -> { 
                        if(item.getItemId() == idItem) {
                            this.product = new Item(item.getItemId(), item.getItemName(), item.getItemPrice());
                        }
                    });
                }
            }
            
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return this.product;
    }

    private void addItemShopping(Item product) {
        this.total = 0;

        UserOperation.shoppingCart.add(product);
        System.out.println("Shopping Cart Items: ");
        UserOperation.shoppingCart.forEach(item -> { System.out.println(item.getItemName() + "\t" + item.getItemPrice()); });

        UserOperation.shoppingCart.forEach(prod -> { this.total += prod.getItemPrice(); });
        System.out.println("\nTotal Price: "+this.total);
        backToMenu();
    }

    private void deleteItemShopping(Item product) {

        UserOperation.shoppingCart.remove(product);
        System.out.println("Shopping Cart Items: ");
        UserOperation.shoppingCart.forEach(item -> { System.out.println(item.getItemName() + "\t" + item.getItemPrice()); });

        this.total -= product.getItemPrice();
        System.out.println("\nTotal Price: "+this.total);
        backToMenu();
    }

    @Override
    public void goodBye() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Shop User Logging Out..."); 
    }
}
