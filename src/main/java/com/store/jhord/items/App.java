package com.store.jhord.items;
import java.util.List;
import operational.*;

public class App 
{
    public static void main( String[] args )
    {
        Operation execute = new AdminOperation();
        execute.menu();
        // List<Item> itemlist = ItemJDBC.select();
        // itemlist.forEach(item -> { System.out.println(item); });
    }
}
