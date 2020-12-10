package cc.paukner.services;

import cc.paukner.domain.Recipe;
import cc.paukner.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DefaultRecipeServiceTest {

    DefaultRecipeService recipeService;

    @Mock // creates a minimal hull _pretending_ to be a full repo
            // in reality, you have to tell it what to do or return on certain requests
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new DefaultRecipeService(recipeRepository); // dep injection
    }

    @Test
    public void getAllRecipes() {
        // tell the mock what to do
        when(recipeRepository.findAll()).thenReturn(Set.of(new Recipe()));

        Set<Recipe> recipes = recipeService.getAllRecipes();
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}
