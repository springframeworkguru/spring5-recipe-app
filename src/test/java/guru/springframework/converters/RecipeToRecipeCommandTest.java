package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

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
  private RecipeToRecipeCommand recipeToRecipeCommand;

  @Before
  public void setUp() throws Exception {
    recipeToRecipeCommand = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new NotesToNotesCommand(),
        new CategoryToCategoryCommand());
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    RecipeCommand command = recipeToRecipeCommand.convert(null);

    // then
    assertNull(command);
  }

  @Test
  public void shouldReturnConvertEmptySource() {
    // when
    RecipeCommand command = recipeToRecipeCommand.convert(new Recipe());

    // then
    assertNotNull(command);
  }

  @Test
  public void shouldConvertRecipeCommand() {
    // given
    Recipe recipe = new Recipe();
    recipe.setId(ID);
    recipe.setDescription(TEST_DESC);
    recipe.setPrepTime(PREP_TIME);
    recipe.setCookTime(COOK_TIME);
    recipe.setServings(SERVINGS);
    recipe.setSource(SOURCE);
    recipe.setUrl(URL);
    recipe.setDirections(DIRECTIONS);
    recipe.setDifficulty(DIFFICULTY);

    Notes notes = new Notes();
    notes.setId(NOTES_ID);

    recipe.setNotes(notes);

    Category category = new Category();
    category.setId(CAT_ID_1);

    Category category2 = new Category();
    category2.setId(CAT_ID2);

    recipe.getCategories().add(category);
    recipe.getCategories().add(category2);

    Ingredient ingredient = new Ingredient();
    ingredient.setId(INGRED_ID_1);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(INGRED_ID_2);

    recipe.getIngredients().add(ingredient);
    recipe.getIngredients().add(ingredient2);

    //when
    RecipeCommand command  = recipeToRecipeCommand.convert(recipe);

    assertNotNull(command);
    assertEquals(ID, command.getId());
    assertEquals(COOK_TIME, command.getCookTime());
    assertEquals(PREP_TIME, command.getPrepTime());
    assertEquals(TEST_DESC, command.getDescription());
    assertEquals(DIFFICULTY, command.getDifficulty());
    assertEquals(DIRECTIONS, command.getDirections());
    assertEquals(SERVINGS, command.getServings());
    assertEquals(SOURCE, command.getSource());
    assertEquals(URL, command.getUrl());
    assertEquals(NOTES_ID, command.getNotes().getId());
    assertEquals(2, command.getCategories().size());
    assertEquals(2, command.getIngredients().size());

  }
}