package guru.springframework.services;

import guru.springframework.Converters.RecipeCommandToRecipe;
import guru.springframework.Converters.RecipeToRecipeCommand;
import guru.springframework.models.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    // Inject the main class
    RecipeServiceImpl recipeService;

    // Inject the repository
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        // add the RecipeRepository
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() {
        // Create test set
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() {

        // Create test set
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet();
        recipeData.add(recipe);

        // tell mockito -> when 'recipeRepository.findAll()' is called -> Then -> return 'recipeData'
        when(recipeRepository.findAll()).thenReturn(recipeData);

        // call recipeService.getRecipes that will call behind to recipeRepository.findAll()
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        // make sure that the recipeRepository.findAll was called once and only once
        verify(recipeRepository, times(1)).findAll();
    }


}