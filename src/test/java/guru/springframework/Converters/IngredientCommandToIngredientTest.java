package guru.springframework.Converters;

import guru.springframework.command.IngredientCommand;
import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.models.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static String DESCRIPTION = "description";
    private static Long ID = 1L;
    private static BigDecimal AMOUNT = new BigDecimal(4);
    private static Long UOM_ID = 2L;
    private static String UOM_DESCRIPTION = "uom_description";


    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParam(){
        assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    public void testWithObject(){
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);
        ingredientCommand.setUom(uomCommand);

        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNotNull(ingredient.getUom());
        assertEquals(UOM_ID, ingredient.getUom().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUom().getDescription());

    }

    @Test
    public void convertWithNullUOM(){
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNull(ingredient.getUom());
    }
}