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

  private static final Long ID = 1L;
  private static final String TEST_DESC = "Test desc";
  private static final Integer PREP_TIME = 10;
  private static final Integer COOK_TIME = 15;
  private static final Integer SERVINGS = 4;
  private static final String SOURCE = "source";
  private static final String URL = "url";
  private static final String DIRECTIONS = "Directions";
  private static final Difficulty DIFFICULTY = Difficulty.KIND_OF_HARD;
  private static final Long CAT_ID_1 = 1L;
  private static final Long CAT_ID2 = 2L;
  private static final Long INGRED_ID_1 = 3L;
  private static final Long INGRED_ID_2 = 4L;
  private static final Long NOTES_ID = 9L;
  private RecipeCommandToRecipe recipeCommandToRecipe;

  @Before
  public void setUp() throws Exception {
    CategoryCommandToCategory categoryCommandToCategory = new CategoryCommandToCategory();
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    NotesCommandToNotes notesCommandToNotes = new NotesCommandToNotes();
    recipeCommandToRecipe = new RecipeCommandToRecipe(categoryCommandToCategory, ingredientCommandToIngredient, notesCommandToNotes);
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    Recipe recipe = recipeCommandToRecipe.convert(null);

    // then
    assertNull(recipe);
  }

  @Test
  public void shouldReturnConvertEmptySource() {
    // when
    Recipe recipe = recipeCommandToRecipe.convert(new RecipeCommand());

    // then
    assertNotNull(recipe);
  }

  @Test
  public void shouldConvertRecipeCommand() {
    // given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(ID);
    recipeCommand.setDescription(TEST_DESC);
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

    CategoryCommand category = new CategoryCommand();
    category.setId(CAT_ID_1);

    CategoryCommand category2 = new CategoryCommand();
    category2.setId(CAT_ID2);

    recipeCommand.getCategories().add(category);
    recipeCommand.getCategories().add(category2);

    IngredientCommand ingredient = new IngredientCommand();
    ingredient.setId(INGRED_ID_1);

    IngredientCommand ingredient2 = new IngredientCommand();
    ingredient2.setId(INGRED_ID_2);

    recipeCommand.getIngredients().add(ingredient);
    recipeCommand.getIngredients().add(ingredient2);

    //when
    Recipe recipe  = recipeCommandToRecipe.convert(recipeCommand);

    assertNotNull(recipe);
    assertEquals(ID, recipe.getId());
    assertEquals(COOK_TIME, recipe.getCookTime());
    assertEquals(PREP_TIME, recipe.getPrepTime());
    assertEquals(TEST_DESC, recipe.getDescription());
    assertEquals(DIFFICULTY, recipe.getDifficulty());
    assertEquals(DIRECTIONS, recipe.getDirections());
    assertEquals(SERVINGS, recipe.getServings());
    assertEquals(SOURCE, recipe.getSource());
    assertEquals(URL, recipe.getUrl());
    assertEquals(NOTES_ID, recipe.getNotes().getId());
    assertEquals(2, recipe.getCategories().size());
    assertEquals(2, recipe.getIngredients().size());
  }
}