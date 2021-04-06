package guru.springframework.services;


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

/**
 * Created by jt on 6/17/17.
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional <Recipe> rOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(rOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Retornou vazio", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

        @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

}
