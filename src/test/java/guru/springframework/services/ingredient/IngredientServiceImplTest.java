package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.ingredients.IngredientCommandToIngredient;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.converters.unitofmeasure.UomCommandToUom;
import guru.springframework.converters.unitofmeasure.UomToUomCommand;
import guru.springframework.domains.Ingredient;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientToIngredientCommand toIngredientCommand;
    IngredientCommandToIngredient fromIngredientCommand;

    IngredientService service;

    public IngredientServiceImplTest() {
        toIngredientCommand = new IngredientToIngredientCommand(new UomToUomCommand());
        fromIngredientCommand = new IngredientCommandToIngredient(new UomCommandToUom());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new IngredientServiceImpl(ingredientRepository,
                recipeRepository, toIngredientCommand, fromIngredientCommand,
                unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId("asd");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("asd");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("asdf");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("asdfg");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = service.findByRecipeIdAndIngredientId("asd", "asdfg");

        assertEquals("asd" , ingredientCommand.getRecipeId());
        assertEquals("asdfg", ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());
    }

    @Test
    @Ignore
    public void saveIngredientCommand() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId("asdfg");
        ingredientCommand.setRecipeId("asdf");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("asdf");

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedCommand = service.saveIngredientCommand(ingredientCommand);

        assertEquals("asdfg", savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void deleteIngredientFromRecipe() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId("asd");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("asd");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("asdf");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));

        //when
        service.deleteIngredientFromRecipe("asd", "asd");

        //then
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        verify(ingredientRepository, times(1)).deleteById(anyString());
    }
}