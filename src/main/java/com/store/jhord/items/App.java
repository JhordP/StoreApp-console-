package com.store.jhord.items;
import operational.*;

public class App 
{
    public static void main( String[] args )
    {
        Operation execute = new UserOperation();
        execute.menu();
    }
}
