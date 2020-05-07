package guru.springframework.Converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.command.NotesCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final String DESCRIPTION = "description";
    private static final Integer PREP_TIME = 5;
    private static final Integer COOK_TIME = 10;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "source";
    private static final String GOOGLE_COM = "google.com";
    private static final String DIRECTIONS = "directions";
    private static final Difficulty MODERATE = Difficulty.MODERATE;
    private static final Long ID_NOTE = 1L;
    private static final String RECIPE_NOTES = "recipe_notes";
    private static final Long ID = 1L;

    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {

        recipeCommandToRecipe = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testWithNull(){
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testWithObject(){
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(GOOGLE_COM);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(MODERATE);

        Set<CategoryCommand> categoryCommandSet = new HashSet<>();
        CategoryCommand cc1 = new CategoryCommand();
        cc1.setId(1L);
        cc1.setDescription("aaaa");
        categoryCommandSet.add(cc1);

        CategoryCommand cc2 = new CategoryCommand();
        cc2.setId(2L);
        cc2.setDescription("bbbb");
        categoryCommandSet.add(cc2);

        recipeCommand.setCategories(categoryCommandSet);

        Set<IngredientCommand> ingredientCommandSet = new HashSet<>();
        ingredientCommandSet.add(new IngredientCommand());
        recipeCommand.setIngredients(ingredientCommandSet);

        recipeCommand.setImage(null);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_NOTE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);
        recipeCommand.setNotes(notesCommand);

        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(GOOGLE_COM, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(MODERATE, recipe.getDifficulty());

        assertEquals(2, recipe.getCategories().size());
        assertEquals(1, recipe.getIngredients().size());

        assertNotNull(recipe.getNotes());
        assertEquals(ID_NOTE, recipe.getNotes().getId());

    }
}