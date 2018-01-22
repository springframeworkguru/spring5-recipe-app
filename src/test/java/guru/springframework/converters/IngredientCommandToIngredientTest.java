package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

  private final Long ID = 1L;
  private final String TEST_DESC = "Test desc";
  private final BigDecimal AMOUNT = new BigDecimal("5");
  private final String UOM_DESC = "Spoon";
  private final Long UOM_ID = 2L;
  private IngredientCommandToIngredient ingredientCommandToIngredient;

  @Before
  public void setUp() throws Exception {
    UnitOfMeasureCommandToUnitOfMeasure uomCommandConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    ingredientCommandToIngredient = new IngredientCommandToIngredient(uomCommandConverter);
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    Ingredient ingredient = ingredientCommandToIngredient.convert(null);

    // then
    assertNull(ingredient);
  }

  @Test
  public void shouldConvertEmptySource() {
    // when
    Ingredient ingredient = ingredientCommandToIngredient.convert(new IngredientCommand());

    // then
    assertNotNull(ingredient);
  }

  @Test
  public void shouldConvertSourceWithoutUOM() {
    // given
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ID);
    ingredientCommand.setDescription(TEST_DESC);
    ingredientCommand.setAmount(AMOUNT);

    // when
    Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

    // then
    assertNotNull(ingredient);
    assertEquals(ID, ingredient.getId());
    assertEquals(TEST_DESC, ingredient.getDescription());
    assertEquals(AMOUNT, ingredient.getAmount());
  }

  @Test
  public void shouldConvertSource() {
    // given
    UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
    uomc.setId(UOM_ID);
    uomc.setDescription(UOM_DESC);

    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(ID);
    ingredientCommand.setDescription(TEST_DESC);
    ingredientCommand.setAmount(AMOUNT);
    ingredientCommand.setUnitOfMeasure(uomc);

    // when
    Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

    // then
    assertNotNull(ingredient);
    assertEquals(ID, ingredient.getId());
    assertEquals(TEST_DESC, ingredient.getDescription());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(UOM_ID, ingredient.getUom().getId());
    assertEquals(UOM_DESC, ingredient.getUom().getDescription());
  }
}