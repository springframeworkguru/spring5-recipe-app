package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.converters.unitofmeasure.UomToUomCommand;
import guru.springframework.domains.Ingredient;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    RecipeRepository recipeRepository;

    IngredientToIngredientCommand toIngredientCommand;

    IngredientService service;

    public IngredientServiceImplTest() {
        toIngredientCommand = new IngredientToIngredientCommand(new UomToUomCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new IngredientServiceImpl(ingredientRepository, recipeRepository, toIngredientCommand);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = service.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals((Long)1L , ingredientCommand.getRecipeId());
        assertEquals((Long)3L, ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());

    }
}