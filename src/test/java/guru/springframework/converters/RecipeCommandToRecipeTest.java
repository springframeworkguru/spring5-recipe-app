package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.PanelUI;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 10;
    public static final Integer COOK_TIME = 10;
    public static final Integer SERVINGS = 4;
    public static final String SOURCE = "source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long NOTES_ID = 1L;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 1L;
    public static final Long INGRED_ID_2 = 2L;

    RecipeCommandToRecipe converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(
                        new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes()
        );
    }
    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }


    @Test
    public void convert() {
        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        recipeCommand.setNotes(notes);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_2);
        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        //When
        Recipe recipe = converter.convert(recipeCommand);

        //Then
        assertNotNull(recipe);
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

    }
}