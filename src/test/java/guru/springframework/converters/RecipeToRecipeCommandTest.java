package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
    public static final Long recipeid = 1L;
    public static final Integer cookTime = Integer.valueOf("5");
    public static final Integer prepTime = Integer.valueOf("7");
    public static final String description = "My Recipe";
    public static final String directions = "Directions";
    public static final Difficulty difficulty = Difficulty.EASY;
    public static final Integer servings = Integer.valueOf("3");
    public static final String source = "Source";
    public static final String url = "Some url";
    public static final Long catId1 = 1L;
    public static final Long catId2 = 2L;
    public static final Long ingred1 = 3L;
    public static final Long ingred2 = 4L;
    public static final Long notesId = 9L;
    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(recipeid);
        recipe.setCookTime(cookTime);
        recipe.setPrepTime(prepTime);
        recipe.setDescription(description);
        recipe.setDirections(directions);
        recipe.setDifficulty(difficulty);
        recipe.setServings(servings);
        recipe.setSource(source);
        recipe.setUrl(url);

        Notes notes = new Notes();
        notes.setId(notesId);
        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(catId1);

        Category category2 = new Category();
        category2.setId(catId2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingred1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ingred2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand command = converter.convert(recipe);

        assertNotNull(command);
        assertEquals(recipeid,command.getId());
        assertEquals(cookTime,command.getCookTime());
        assertEquals(prepTime, command.getPrepTime());
        assertEquals(description,command.getDescription());
        assertEquals(difficulty,command.getDifficulty());
        assertEquals(directions,command.getDirections());
        assertEquals(servings,command.getServings());
        assertEquals(source,command.getSource());
        assertEquals(url,command.getUrl());
        assertEquals(notesId,command.getNotes().getId());
        assertEquals(2,command.getCategories().size());
        assertEquals(2,command.getIngredients().size());
    }
}