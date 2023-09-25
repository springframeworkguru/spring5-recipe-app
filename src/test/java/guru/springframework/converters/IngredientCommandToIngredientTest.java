package guru.springframework.converters;

import guru.springframework.command.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class IngredientCommandToIngredientTest {

    public static final long ID = 1L;
    public static final String DESCRIPTION = "Ingredient";
    public static final BigDecimal AMOUNT = new BigDecimal("1");

    @Mock
    private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    @InjectMocks
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @BeforeEach
    void setUp() {
        ingredientCommandToIngredient =
                new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    }

    @Test
    void testNull(){
        Ingredient ingredient = ingredientCommandToIngredient.convert(null);
        assertNull(ingredient);
    }

    @Test
    void testNotNull(){
        Ingredient ingredient = ingredientCommandToIngredient.convert(new IngredientCommand());
        assertNotNull(ingredient);
    }

    @Test
    void testConvert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}