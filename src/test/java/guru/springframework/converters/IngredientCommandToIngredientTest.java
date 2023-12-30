package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    public static final Recipe recipe = new Recipe();
    public static final BigDecimal amount = new BigDecimal("1");
    public static final String description = "Cheeseburguer";
    public static final Long idValue = 1L;
    public static final Long uomId = 2L;
    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(idValue);
        command.setAmount(amount);
        command.setDescription(description);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(uomId);
        command.setUom(unitOfMeasureCommand);

        Ingredient ingredient = converter.convert(command);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(idValue,ingredient.getId());
        assertEquals(amount,ingredient.getAmount());
        assertEquals(description,ingredient.getDescription());
        assertEquals(uomId,ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(idValue);
        command.setAmount(amount);
        command.setDescription(description);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();

        Ingredient ingredient = converter.convert(command);

        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(idValue,ingredient.getId());
        assertEquals(amount,ingredient.getAmount());
        assertEquals(description,ingredient.getDescription());
    }
}