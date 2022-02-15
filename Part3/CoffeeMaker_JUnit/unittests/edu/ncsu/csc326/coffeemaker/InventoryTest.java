package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
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
    public void testSetChoc(){
        Inventory inventory = new Inventory();
        int chocSet = 50;
        inventory.setChocolate(chocSet);
        int actual = inventory.getChocolate();
        assertEquals(chocSet,actual);
    }

    @Test
    public void testSetChocLess(){
        Inventory inventory = new Inventory();
        int chocSet = -5;
        inventory.setChocolate(chocSet);
        int actual = inventory.getChocolate();
        assertEquals(15,actual);
    }

    @Test
    public void testAddChoc(){
        try {
            Inventory inventory = new Inventory();
            String chocAdd = "50";
            inventory.addChocolate(chocAdd);
            int actual = inventory.getChocolate();
            assertEquals(65,actual);
        } catch (InventoryException e) {
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testAddChocException(){
        Inventory inventory = new Inventory();
        String chocAdd = "a";
        try {
            inventory.addChocolate(chocAdd);
            fail("Units of chocolate must be a integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }
}