package com.store.jhord.items;
import java.util.List;

import dataobjects.itemJDBC;
import domain.Item;

public class App 
{
    public static void main( String[] args )
    {
        // Item prod = new Item("Coca-Cola(500ml)", 0.75);
        // itemJDBC.insert(prod);
        //Update
        Item prod = new Item(3, "Water Bottle(250ml)", 0.25);
        itemJDBC.update(prod);

        //Isert
        prod = new Item("Coca-Cola(500ml)", 0.75);
        itemJDBC.insert(prod);

        //Delete
        prod = new Item(5);
        itemJDBC.delete(prod);
        List<Item> itemlist = itemJDBC.select();
        itemlist.forEach(item -> { System.out.println(item); });
    }
}
