package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

  private final Long ID = 1L;
  private final String TEST_DESC = "Test desc";
  private final BigDecimal AMOUNT = new BigDecimal("5");
  private final Recipe RECIPE = new Recipe();
  private final Long UOM_ID = 4L;
  private final String UOM_DESC = "Spoon";
  private IngredientToIngredientCommand ingredientToIngredientCommand;

  @Before
  public void setUp() throws Exception {
    UnitOfMeasureToUnitOfMeasureCommand uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    ingredientToIngredientCommand = new IngredientToIngredientCommand(uomConverter);
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    IngredientCommand command = ingredientToIngredientCommand.convert(null);

    // then
    assertNull(command);
  }

  @Test
  public void shouldConvertEmptySource() {
    // when
    IngredientCommand command = ingredientToIngredientCommand.convert(new Ingredient());

    // then
    assertNotNull(command);
  }

  @Test
  public void shouldConvertSourceWithoutUOM() {
    // given
    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID);
    ingredient.setDescription(TEST_DESC);
    ingredient.setAmount(AMOUNT);
    ingredient.setRecipe(RECIPE);
    ingredient.setUnitOfMeasure(null);

    // when
    IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);

    // then
    assertNotNull(command);
    assertNull(command.getUnitOfMeasure());
    assertEquals(ID, command.getId());
    assertEquals(TEST_DESC, command.getDescription());
    assertEquals(AMOUNT, command.getAmount());
    assertNull(command.getUnitOfMeasure());
  }

  @Test
  public void shouldConvertSourceWithUOM() {
    // given
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(UOM_ID);
    uom.setDescription(UOM_DESC);

    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID);
    ingredient.setDescription(TEST_DESC);
    ingredient.setAmount(AMOUNT);
    ingredient.setRecipe(RECIPE);
    ingredient.setUnitOfMeasure(uom);

    // when
    IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);

    // then
    assertNotNull(command);
    assertNotNull(command.getUnitOfMeasure());
    assertEquals(ID, command.getId());
    assertEquals(TEST_DESC, command.getDescription());
    assertEquals(AMOUNT, command.getAmount());
    assertEquals(UOM_ID, command.getUnitOfMeasure().getId());
  }
}