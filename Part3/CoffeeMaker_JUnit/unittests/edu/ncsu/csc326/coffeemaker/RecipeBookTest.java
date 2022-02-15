package edu.ncsu.csc326.coffeemaker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeBookTest {
    @Test
    public void testDefault(){
        RecipeBook recipeBook = new RecipeBook();
        assertArrayEquals(new Recipe[]{null,null,null,null},recipeBook.getRecipes());
    }

    @Test
    public void testAdd(){
        RecipeBook recipeBook = new RecipeBook();
        Recipe r1 = new Recipe();
        r1.setName("a");
        boolean actualBoolean = recipeBook.addRecipe(r1);
        assertTrue(actualBoolean);
    }

    @Test
    public void testAddExist(){
        RecipeBook recipeBook = new RecipeBook();
        Recipe r1 = new Recipe();
        r1.setName("a");
        Recipe r2 = new Recipe();
        r2.setName("b");
        Recipe r3 = new Recipe();
        r3.setName("c");
        Recipe r4 = new Recipe();
        r4.setName("d");
        Recipe r5 = new Recipe();
        r5.setName("a");
        recipeBook.addRecipe(r1);
        recipeBook.addRecipe(r2);
        recipeBook.addRecipe(r3);
        recipeBook.addRecipe(r4);
        assertFalse(recipeBook.addRecipe(r5));
    }

    @Test
    public void testAddOutofBound(){
        RecipeBook recipeBook = new RecipeBook();
        Recipe r1 = new Recipe();
        r1.setName("a");
        Recipe r2 = new Recipe();
        r2.setName("b");
        Recipe r3 = new Recipe();
        r3.setName("c");
        Recipe r4 = new Recipe();
        r4.setName("d");
        Recipe r5 = new Recipe();
        r5.setName("e");
        recipeBook.addRecipe(r1);
        recipeBook.addRecipe(r2);
        recipeBook.addRecipe(r3);
        recipeBook.addRecipe(r4);
        assertFalse(recipeBook.addRecipe(r5));
    }

    @Test
    public void testDeleteNull(){
        RecipeBook recipeBook = new RecipeBook();
        assertNull(recipeBook.deleteRecipe(1));
    }

    @Test
    public void testDelete(){
        RecipeBook recipeBook = new RecipeBook();
        Recipe r1 = new Recipe();
        r1.setName("a");
        Recipe r2 = new Recipe();
        r2.setName("b");
        Recipe r3 = new Recipe();
        r3.setName("c");
        Recipe r4 = new Recipe();
        r4.setName("d");
        recipeBook.addRecipe(r1);
        recipeBook.addRecipe(r2);
        recipeBook.addRecipe(r3);
        recipeBook.addRecipe(r4);
        assertEquals("b",recipeBook.deleteRecipe(1));
    }

    @Test
    public void testDeleteArrayOutofBound(){
        try {
            RecipeBook recipeBook = new RecipeBook();
            Recipe r1 = new Recipe();
            r1.setName("a");
            Recipe r2 = new Recipe();
            r2.setName("b");
            Recipe r3 = new Recipe();
            r3.setName("c");
            Recipe r4 = new Recipe();
            r4.setName("d");
            recipeBook.addRecipe(r1);
            recipeBook.addRecipe(r2);
            recipeBook.addRecipe(r3);
            recipeBook.addRecipe(r4);
            recipeBook.deleteRecipe(5);
        } catch (Throwable e){
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void testDeleteArrayOutofBoundNeg(){
        try {
            RecipeBook recipeBook = new RecipeBook();
            Recipe r1 = new Recipe();
            r1.setName("a");
            Recipe r2 = new Recipe();
            r2.setName("b");
            Recipe r3 = new Recipe();
            r3.setName("c");
            Recipe r4 = new Recipe();
            r4.setName("d");
            recipeBook.addRecipe(r1);
            recipeBook.addRecipe(r2);
            recipeBook.addRecipe(r3);
            recipeBook.addRecipe(r4);
            recipeBook.deleteRecipe(-5);
        } catch (Throwable e){
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void testDeleteArrayCheck(){
        RecipeBook recipeBook = new RecipeBook();
        Recipe r1 = new Recipe();
        r1.setName("a");
        Recipe r2 = new Recipe();
        r2.setName("b");
        Recipe r3 = new Recipe();
        r3.setName("c");
        Recipe r4 = new Recipe();
        r4.setName("d");
        recipeBook.addRecipe(r1);
        recipeBook.addRecipe(r2);
        recipeBook.addRecipe(r3);
        recipeBook.addRecipe(r4);
        recipeBook.deleteRecipe(1);
        assertNull(recipeBook.getRecipes()[1]);
    }

    @Test
    public void testEditNull(){
        RecipeBook recipeBook = new RecipeBook();
        assertNull(recipeBook.editRecipe(1,new Recipe()));
    }

    @Test
    public void testEdit(){
        RecipeBook recipeBook = new RecipeBook();
        Recipe r1 = new Recipe();
        r1.setName("a");
        Recipe r2 = new Recipe();
        r2.setName("b");
        Recipe r3 = new Recipe();
        r3.setName("c");
        Recipe r4 = new Recipe();
        r4.setName("d");
        recipeBook.addRecipe(r1);
        recipeBook.addRecipe(r2);
        recipeBook.addRecipe(r3);
        recipeBook.addRecipe(r4);
        assertEquals("b",recipeBook.editRecipe(1,new Recipe()));
    }

    @Test
    public void testEditArrayOutofBound(){
        try {
            RecipeBook recipeBook = new RecipeBook();
            Recipe r1 = new Recipe();
            r1.setName("a");
            Recipe r2 = new Recipe();
            r2.setName("b");
            Recipe r3 = new Recipe();
            r3.setName("c");
            Recipe r4 = new Recipe();
            r4.setName("d");
            recipeBook.addRecipe(r1);
            recipeBook.addRecipe(r2);
            recipeBook.addRecipe(r3);
            recipeBook.addRecipe(r4);
            recipeBook.editRecipe(5,new Recipe());
        } catch (Throwable e){
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    public void testEditArrayOutofBoundNeg(){
        try {
            RecipeBook recipeBook = new RecipeBook();
            Recipe r1 = new Recipe();
            r1.setName("a");
            Recipe r2 = new Recipe();
            r2.setName("b");
            Recipe r3 = new Recipe();
            r3.setName("c");
            Recipe r4 = new Recipe();
            r4.setName("d");
            recipeBook.addRecipe(r1);
            recipeBook.addRecipe(r2);
            recipeBook.addRecipe(r3);
            recipeBook.addRecipe(r4);
            recipeBook.editRecipe(-5,new Recipe());
        } catch (Throwable e){
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

}