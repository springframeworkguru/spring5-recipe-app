package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientServiceImpl service;
        final Long ingredientId = 345L;
        final Long recipeId = 123L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath() {
        //given
        final Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredientId);
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);
        recipe.addIngredient(ingredient);

        when(recipeRepository.findById(eq(recipeId))).thenReturn(Optional.of(recipe));
        when(ingredientToIngredientCommand.convert(eq(ingredient))).thenReturn(ingredientCommand);

        //when
        final IngredientCommand foundIngredientCommand = service.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        //then
        assertEquals(ingredient.getDescription(), foundIngredientCommand.getDescription());
    }
}
