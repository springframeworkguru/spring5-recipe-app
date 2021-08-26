package guru.springframework.services;

import guru.springframework.converters.recipe.RecipeCommandToRecipe;
import guru.springframework.converters.recipe.RecipeToRecipeCommand;
import guru.springframework.domains.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.recipe.RecipeServiceImpl;
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
        recipe1.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe1);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipe = recipeRepository.findById(1L).orElse(null);

        assertNotNull("Null recipe returned", recipe);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getAll() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getAll();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        recipeService.deleteById(2L);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}