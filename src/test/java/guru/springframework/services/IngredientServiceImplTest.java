package guru.springframework.services;

import guru.springframework.Converters.IngredientCommandToIngredient;
import guru.springframework.Converters.IngredientToIngredientCommand;
import guru.springframework.Converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.models.Ingredient;
import guru.springframework.models.Recipe;
import guru.springframework.models.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        // add the RecipeRepository
        MockitoAnnotations.initMocks(this);

        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {

        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setDescription("aaaa");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setDescription("bbbb");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setDescription("cccc");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 2L);

        // then
        assertNotNull(ingredientCommand);
        assertEquals(Long.valueOf(2L) , ingredientCommand.getId());
        assertEquals(Long.valueOf(1L) , ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveRecipeCommand(){

        // IngredientCommand
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(2L);

        // Recipe
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        // Ingredient
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);

        // UnitOfMeasure
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        ingredient.setUom(unitOfMeasure);

        recipe.getIngredients().add(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(2L);
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(unitOfMeasure));
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        assertNotNull(savedIngredientCommand);
        assertEquals(Long.valueOf(3L), savedIngredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());

    }
}