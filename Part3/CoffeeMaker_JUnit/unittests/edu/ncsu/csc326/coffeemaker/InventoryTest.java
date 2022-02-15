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
        try {
            Inventory inventory = new Inventory();
            String chocAdd = "a";
            inventory.addChocolate(chocAdd);
            fail("Units of chocolate must be a integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testAddChocNeg(){
        try {
            Inventory inventory = new Inventory();
            String chocAdd = "-5";
            inventory.addChocolate(chocAdd);
            fail("Units of chocolate must be a positive integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testDefaultGetCoffee(){
        Inventory inventory = new Inventory();
        int coffeeDefault = 15;
        int actual = inventory.getCoffee();
        assertEquals(coffeeDefault,actual);
    }

    @Test
    public void testSetCoffee(){
        Inventory inventory = new Inventory();
        int coffeeSet = 50;
        inventory.setCoffee(coffeeSet);
        int actual = inventory.getCoffee();
        assertEquals(coffeeSet,actual);
    }

    @Test
    public void testSetCoffeeLess(){
        Inventory inventory = new Inventory();
        int coffeeSet = -5;
        inventory.setCoffee(coffeeSet);
        int actual = inventory.getCoffee();
        assertEquals(15,actual);
    }

    @Test
    public void testAddCoffee(){
        try {
            Inventory inventory = new Inventory();
            String coffeeAdd = "50";
            inventory.addCoffee(coffeeAdd);
            int actual = inventory.getCoffee();
            assertEquals(65,actual);
        } catch (InventoryException e) {
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testAddCoffeeException(){
        try {
            Inventory inventory = new Inventory();
            String coffeeAdd = "a";
            inventory.addCoffee(coffeeAdd);
            fail("Units of coffee must be a integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testAddCoffeeNeg(){
        try {
            Inventory inventory = new Inventory();
            String coffeeAdd = "-5";
            inventory.addCoffee(coffeeAdd);
            fail("Units of chocolate must be a positive integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testDefaultGetMilk(){
        Inventory inventory = new Inventory();
        int milkDefault = 15;
        int actual = inventory.getMilk();
        assertEquals(milkDefault,actual);
    }

    @Test
    public void testSetMilk(){
        Inventory inventory = new Inventory();
        int milkSet = 50;
        inventory.setMilk(milkSet);
        int actual = inventory.getMilk();
        assertEquals(milkSet,actual);
    }

    @Test
    public void testSetMilkLess(){
        Inventory inventory = new Inventory();
        int milkSet = -5;
        inventory.setMilk(milkSet);
        int actual = inventory.getMilk();
        assertEquals(15,actual);
    }

    @Test
    public void testAddMilk(){
        try {
            Inventory inventory = new Inventory();
            String milkAdd = "50";
            inventory.addMilk(milkAdd);
            int actual = inventory.getMilk();
            assertEquals(65,actual);
        } catch (InventoryException e) {
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testAddMilkException(){
        try {
            Inventory inventory = new Inventory();
            String milkAdd = "a";
            inventory.addMilk(milkAdd);
            fail("Units of coffee must be a integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testAddMilkNeg(){
        try {
            Inventory inventory = new Inventory();
            String milkAdd = "-5";
            inventory.addMilk(milkAdd);
            fail("Units of chocolate must be a positive integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testDefaultGetSugar(){
        Inventory inventory = new Inventory();
        int sugarDefault = 15;
        int actual = inventory.getSugar();
        assertEquals(sugarDefault,actual);
    }

    @Test
    public void testSetSugar(){
        Inventory inventory = new Inventory();
        int sugarSet = 50;
        inventory.setSugar(sugarSet);
        int actual = inventory.getSugar();
        assertEquals(sugarSet,actual);
    }

    @Test
    public void testSetSugarLess(){
        Inventory inventory = new Inventory();
        int sugarSet = -5;
        inventory.setSugar(sugarSet);
        int actual = inventory.getSugar();
        assertEquals(15,actual);
    }

    @Test
    public void testAddSugar(){
        try {
            Inventory inventory = new Inventory();
            String sugarAdd = "50";
            inventory.addSugar(sugarAdd);
            int actual = inventory.getSugar();
            assertEquals(65,actual);
        } catch (InventoryException e) {
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testAddSugarException(){
        try {
            Inventory inventory = new Inventory();
            String sugarAdd = "a";
            inventory.addSugar(sugarAdd);
            fail("Units of coffee must be a integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }

    @Test
    public void testAddSugarNeg(){
        try {
            Inventory inventory = new Inventory();
            String sugarAdd = "-5";
            inventory.addSugar(sugarAdd);
            fail("Units of chocolate must be a positive integer");
        } catch (InventoryException e) {
            //success if caught
        }
    }
}