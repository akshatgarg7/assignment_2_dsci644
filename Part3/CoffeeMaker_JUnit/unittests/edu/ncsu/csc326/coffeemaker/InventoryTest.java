package edu.ncsu.csc326.coffeemaker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    public void testDefaultGetChoc(){
        Inventory inventory = new Inventory();
        int chocDefault = 15;
        int actual = inventory.getChocolate();
        assertEquals(chocDefault,actual);
    }

    @Test
    public void testDefaultSetChoc(){
        Inventory inventory = new Inventory();
        int chocSet = 50;
        inventory.setChocolate(chocSet);
        int actual = inventory.getChocolate();
        assertEquals(chocSet,actual);
    }

    @Test
    public void testDefaultSetChocLess(){
        Inventory inventory = new Inventory();
        int chocSet = -5;
        inventory.setChocolate(chocSet);
        int actual = inventory.getChocolate();
        assertEquals(15,actual);
    }

}