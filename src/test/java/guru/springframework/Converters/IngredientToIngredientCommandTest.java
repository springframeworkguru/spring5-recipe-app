package guru.springframework.Converters;

import guru.springframework.command.IngredientCommand;
import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.models.Ingredient;
import guru.springframework.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static String DESCRIPTION = "description";
    private static Long ID = 1L;
    private static BigDecimal AMOUNT = new BigDecimal(4);
    private static Long UOM_ID = 2L;
    private static String UOM_DESCRIPTION = "uom_description";

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void convert() {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure uomCommand = new UnitOfMeasure();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);
        ingredient.setUom(uomCommand);

        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());

        assertNotNull(ingredientCommand.getUom());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, ingredientCommand.getUom().getDescription());

    }
}