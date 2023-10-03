package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long recipeid = 1L;
    public static final Integer cookTime = Integer.valueOf("5");
    public static final Integer prepTime = Integer.valueOf("7");
    public static final String description = "My Recipe";
    public static final String directions = "Directions";
    public static final Difficulty difficulty = Difficulty.EASY;
    public static final Integer servings = Integer.valueOf("3");
    public static final String source = "Source";
    public static final String url = "Some url";
    public static final Long catid1 = 1L;
    public static final Long catid2 = 2L;
    public static final Long ingred1 = 3L;
    public static final Long ingred2 = 4L;
    public static final Long notesId = 9L;
    RecipeCommandToRecipe converter;
    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeid);
        recipeCommand.setCookTime(cookTime);
        recipeCommand.setPrepTime(prepTime);
        recipeCommand.setDescription(description);
        recipeCommand.setDirections(directions);
        recipeCommand.setDifficulty(difficulty);
        recipeCommand.setServings(servings);
        recipeCommand.setSource(source);
        recipeCommand.setUrl(url);

        NotesCommand notes = new NotesCommand();
        notes.setId(notesId);
        recipeCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(catid1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(catid2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(ingred1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(ingred2);

        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(recipeid,recipe.getId());
        assertEquals(cookTime,recipe.getCookTime());
        assertEquals(prepTime,recipe.getPrepTime());
        assertEquals(description,recipe.getDescription());
        assertEquals(directions,recipe.getDirections());
        assertEquals(difficulty,recipe.getDifficulty());
        assertEquals(servings,recipe.getServings());
        assertEquals(source,recipe.getSource());
        assertEquals(url,recipe.getUrl());
        assertEquals(notesId,recipe.getNotes().getId());
        assertEquals(2,recipe.getCategories().size());
        assertEquals(2,recipe.getIngredients().size());

    }
}