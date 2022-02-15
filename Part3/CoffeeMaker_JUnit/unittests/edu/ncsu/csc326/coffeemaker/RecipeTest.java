package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    public void testDefaultChocolate(){
        Recipe recipe = new Recipe();
        int chocDefault = 0;
        int actual = recipe.getAmtChocolate();
        assertEquals(chocDefault,actual);
    }

    @Test
    public void testSetChocolate(){
        try {
            Recipe recipe = new Recipe();
            String chocSet = "20";
            recipe.setAmtChocolate(chocSet);
            assertEquals(20, recipe.getAmtChocolate());
        } catch (RecipeException e){
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testSetChocolateFail(){
        try {
            Recipe recipe = new Recipe();
            String chocSet = "a";
            recipe.setAmtChocolate(chocSet);
            fail("Units of chocolate must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testSetChocolateNeg(){
        try {
            Recipe recipe = new Recipe();
            String chocSet = "-5";
            recipe.setAmtChocolate(chocSet);
            fail("Units of chocolate must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testDefaultCoffee(){
        Recipe recipe = new Recipe();
        int coffeeDefault = 0;
        int actual = recipe.getAmtCoffee();
        assertEquals(coffeeDefault,actual);
    }

    @Test
    public void testSetCoffee(){
        try {
            Recipe recipe = new Recipe();
            String coffeeSet = "20";
            recipe.setAmtCoffee(coffeeSet);
            assertEquals(20, recipe.getAmtCoffee());
        } catch (RecipeException e){
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testSetCoffeeFail(){
        try {
            Recipe recipe = new Recipe();
            String coffeeSet = "a";
            recipe.setAmtCoffee(coffeeSet);
            fail("Units of coffee must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testSetCoffeeNeg(){
        try {
            Recipe recipe = new Recipe();
            String coffeeSet = "-5";
            recipe.setAmtCoffee(coffeeSet);
            fail("Units of coffee must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testDefaultMilk(){
        Recipe recipe = new Recipe();
        int milkDefault = 0;
        int actual = recipe.getAmtMilk();
        assertEquals(milkDefault,actual);
    }

    @Test
    public void testSetMilk(){
        try {
            Recipe recipe = new Recipe();
            String milkSet = "20";
            recipe.setAmtMilk(milkSet);
            assertEquals(20, recipe.getAmtMilk());
        } catch (RecipeException e){
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testSetMilkFail(){
        try {
            Recipe recipe = new Recipe();
            String milkSet = "a";
            recipe.setAmtMilk(milkSet);
            fail("Units of milk must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testSetMilkNeg(){
        try {
            Recipe recipe = new Recipe();
            String milkSet = "-5";
            recipe.setAmtMilk(milkSet);
            fail("Units of milk must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testDefaultSugar(){
        Recipe recipe = new Recipe();
        int sugarDefault = 0;
        int actual = recipe.getAmtSugar();
        assertEquals(sugarDefault,actual);
    }

    @Test
    public void testSetSugar(){
        try {
            Recipe recipe = new Recipe();
            String sugarSet = "20";
            recipe.setAmtSugar(sugarSet);
            assertEquals(20, recipe.getAmtSugar());
        } catch (RecipeException e){
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testSetSugarFail(){
        try {
            Recipe recipe = new Recipe();
            String sugarSet = "a";
            recipe.setAmtSugar(sugarSet);
            fail("Units of sugar must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testSetSugarNeg(){
        try {
            Recipe recipe = new Recipe();
            String sugarSet = "-5";
            recipe.setAmtSugar(sugarSet);
            fail("Units of sugar must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testDefaultName(){
        Recipe recipe = new Recipe();
        String nameDefault = "";
        String actual = recipe.getName();
        assertEquals(nameDefault,actual);
    }

    @Test
    public void testSetName(){
        Recipe recipe = new Recipe();
        String nameDefault = "mocha";
        recipe.setName(nameDefault);
        String actual = recipe.getName();
        assertEquals(nameDefault,actual);
    }

    @Test
    public void testSetNameNull(){
        Recipe recipe = new Recipe();
        recipe.setName(null);
        String actual = recipe.getName();
        assertEquals("",actual);
    }

    @Test
    public void testDefaultPrice(){
        Recipe recipe = new Recipe();
        int priceDefault = 0;
        int actual = recipe.getPrice();
        assertEquals(priceDefault,actual);
    }

    @Test
    public void testSetPrice(){
        try {
            Recipe recipe = new Recipe();
            String priceSet = "20";
            recipe.setPrice(priceSet);
            assertEquals(20, recipe.getPrice());
        } catch (RecipeException e){
            fail("Something very wrong happened with code!");
        }
    }

    @Test
    public void testSetPriceFail(){
        try {
            Recipe recipe = new Recipe();
            String priceSet = "a";
            recipe.setPrice(priceSet);
            fail("Price must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testSetPriceNeg(){
        try {
            Recipe recipe = new Recipe();
            String priceSet = "-5";
            recipe.setPrice(priceSet);
            fail("Price must be a positive integer");
        } catch (RecipeException e){
            //success
        }
    }

    @Test
    public void testToString() {
        Recipe recipe = new Recipe();
        assertEquals("", recipe.toString());
    }

    @Test
    public void testHashcode(){
        Recipe recipe = new Recipe();
        assertEquals(31,recipe.hashCode());
    }

    @Test
    public void testHashcodeName(){
        Recipe recipe = new Recipe();
        recipe.setName("a");
        assertEquals(128,recipe.hashCode());
    }

    @Test
    public void testHashcodeNull(){
        Recipe recipe = new Recipe();
        recipe.setName(null);
        assertEquals(31,recipe.hashCode());
    }

    @Test
    public void testEqualObject(){
        Recipe recipe = new Recipe();
        Object obj = recipe;
        assertTrue(recipe.equals(obj));
    }

    @Test
    public void testEqualObjectFail1(){
        Recipe recipe = new Recipe();
        Recipe obj = null;
        assertFalse(recipe.equals(obj));
    }

    @Test
    public void testEqualObjectFail2(){
        Recipe recipe = new Recipe();
        String obj = "a";
        assertFalse(recipe.equals(obj));
    }

    @Test
    public void testEqualObjectPass(){
        Recipe recipe = new Recipe();
        Recipe obj = new Recipe();
        assertTrue(recipe.equals(obj));
    }

    @Test
    public void testEqualObjectFail3(){
        Recipe recipe = new Recipe();
        recipe.setName("a");
        Recipe obj = new Recipe();
        obj.setName("b");
        assertFalse(recipe.equals(obj));
    }

    @Test
    public void testEqualObjectFail4(){
        Recipe recipe = new Recipe();
        Recipe obj = new Recipe();
        obj.setName("b");
        assertFalse(recipe.equals(obj));
    }

    @Test
    public void testEqualObjectNullNames(){
        Recipe recipe = new Recipe();
        recipe.setName(null);
        Recipe obj = new Recipe();
        obj.setName(null);
        assertTrue(recipe.equals(obj));
    }


    @Test
    public void testHashCode(){
        try {
            int expectedValue=31;
            Recipe recipe = new Recipe();
            int actualValue=recipe.hashCode();
            assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            assertFalse(false);
        }
    }
}