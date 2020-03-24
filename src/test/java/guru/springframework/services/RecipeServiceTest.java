package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUP() throws Exception{
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeService(recipeRepository);
    }


    @Test
    public void getRecipies() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> serviceRecipies = recipeService.getRecipies();
        assertEquals(serviceRecipies.size(), 1);
        Mockito.verify(recipeRepository, times(1)).findAll();
    }
}