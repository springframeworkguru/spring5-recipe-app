package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    public static final Long RECIPE_ID = 1L;
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandConverter;

    @Mock
    RecipeToRecipeCommand recipeConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandConverter, recipeConverter);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = Recipe.builder().id(RECIPE_ID).build();

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe requestedRecipe = recipeService.findById(RECIPE_ID);

        assertNotNull(requestedRecipe);
        assertEquals(RECIPE_ID, requestedRecipe.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test(expected = RuntimeException.class)
    public void getRecipeByIdForNotFoundRecipe_ShouldThrowException() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Recipe requestedRecipe = recipeService.findById(RECIPE_ID);

        assertEquals(RECIPE_ID, requestedRecipe.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void deleteRecipeByIdForFoundRecipe_ShouldDelete() {
        recipeService.deleteById(RECIPE_ID);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}