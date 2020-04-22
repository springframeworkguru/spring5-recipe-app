package guru.springframework.services;

import guru.springframework.models.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    // Inject the main class
    RecipeServiceImpl recipeService;

    // Inject the repository
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        // add the RecipeRepository
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {

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