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
            
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage()+e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getMessage()+e.getStackTrace());
        }
        
    }   

    private void viewItems() {
        List<Item> items = ItemJDBC.select();
        items.forEach(item -> {
            System.out.println(item);
        });
    }


    @Override
    public String goodBye() {
        // TODO Auto-generated method stub
        return null;    

    }
    

}
