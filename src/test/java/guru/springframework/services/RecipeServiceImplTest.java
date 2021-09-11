package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.recipe.RecipeCommandToRecipe;
import guru.springframework.converters.recipe.RecipeToRecipeCommand;
import guru.springframework.domains.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.recipe.RecipeService;
import guru.springframework.services.recipe.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeById() throws Exception{
        Recipe recipe1 = new Recipe();
        recipe1.setId("asd");
        Optional<Recipe> recipeOptional = Optional.of(recipe1);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipe = recipeRepository.findById("asd").orElse(null);

        assertNotNull("Null recipe returned", recipe);

        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getAll() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn((List<Recipe>) recipesData);

        Set<Recipe> recipes = recipeService.getAll();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        recipeService.deleteById("asd");

        verify(recipeRepository, times(1)).deleteById(anyString());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception{
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.getById("asd");

        //We are expecting an error.
    }

    @Test
    public void getRecipeCommandByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId("asd");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("asd");

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById("asd");

        assertNotNull("Null recipe returned.", commandById);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn((List<Recipe>) recipes);

        Set<Recipe> fetchedRecipes = recipeService.getAll();

        assertEquals(1, fetchedRecipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test(expected = NumberFormatException.class)
    public void testNumberFormatException() {
        when(recipeRepository.findById(any())).thenThrow(new NumberFormatException());

        Recipe recipe = recipeRepository.findById("asd").get();
    }
}