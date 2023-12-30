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
    public static final Recipe recipe = new Recipe();
    public static final BigDecimal amount = new BigDecimal("1");
    public static final String description = "description";
    public static final Long uomID = 2L;
    public static final Long idValue = 1L;
    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertWithUOM() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(idValue);
        ingredient.setAmount(amount);
        ingredient.setDescription(description);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(uomID);
        ingredient.setUnitOfMeasure(uom);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(idValue,ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUom());
        assertEquals(uomID,ingredientCommand.getUom().getId());
        assertEquals(amount,ingredientCommand.getAmount());
        assertEquals(description,ingredientCommand.getDescription());
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(idValue);
        ingredient.setAmount(amount);
        ingredient.setDescription(description);
        ingredient.setUnitOfMeasure(null);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(idValue,ingredientCommand.getId());
        assertNull(ingredientCommand.getUom());
        assertEquals(amount,ingredientCommand.getAmount());
        assertEquals(description,ingredientCommand.getDescription());
    }
}