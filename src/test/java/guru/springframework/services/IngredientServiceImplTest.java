package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    final Long ingredientId = 345L;
    final Long recipeId = 123L;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientServiceImpl service;
    private final Long unitOfMeasureId = 77777L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand, ingredientCommandToIngredient, unitOfMeasureRepository);
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

    @Test
    public void save() {
        //given
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredientId);
        ingredientCommand.setRecipeId(recipeId);

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);

        final Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient);
        recipe.setId(recipeId);
        ingredient.setRecipe(recipe);

        final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(unitOfMeasureId);

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        ingredientCommand.setUom(unitOfMeasureCommand);

        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommand);
        when(ingredientCommandToIngredient.convert(any())).thenReturn(ingredient);
        when(unitOfMeasureRepository.findById(eq(unitOfMeasureId))).thenReturn(Optional.of(unitOfMeasure));

        //when
        final IngredientCommand savedIngredientCommand = service.save(ingredientCommand);

        //then
        assertNotNull(savedIngredientCommand);
        assertEquals(recipeId, savedIngredientCommand.getRecipeId());
    }
}
