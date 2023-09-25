package guru.springframework.converters;

import guru.springframework.command.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IngredientToIngredientCommandTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Ingredient";
    public static final BigDecimal AMOUNT = new BigDecimal("1");

    @Mock
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    @InjectMocks
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @BeforeEach
    void setUp() {
        ingredientToIngredientCommand =
                new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void testNull(){
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(null);
        assertNull(ingredientCommand);
    }

    @Test
    void testNotNull(){
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(new Ingredient());
        assertNotNull(ingredientCommand);
    }

    @Test
    void testConvert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
        assertNotNull(ingredientCommand);
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
    }
}