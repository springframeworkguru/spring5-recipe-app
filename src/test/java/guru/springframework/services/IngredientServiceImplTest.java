package guru.springframework.services;

import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.IngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IngredientServiceImplTest {

    public static final long INGREDIENT_ID = 1L;
    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    RecipeService recipeService;

    IngredientToIngredientCommand converter;

    UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @InjectMocks
    IngredientServiceImpl ingredientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();

        ingredientService = new IngredientServiceImpl(ingredientRepository, recipeService, converter, uomConverter);
    }

    @Test
    public void findByIngredientId() {

    }

    @Test
    public void deleteById() {
        ingredientService.deleteById(INGREDIENT_ID, INGREDIENT_ID);

        verify(ingredientRepository, times(1)).deleteByIdAndRecipeId(anyLong(), anyLong());
    }
}