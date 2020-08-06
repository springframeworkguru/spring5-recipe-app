package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepositoryMock;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepositoryMock,
                recipeCommandToRecipe,recipeToRecipeCommand);

    }

    @Test
    public void getRecipes() {
        Recipe  recipe = new Recipe();
        Set recipeData= new HashSet();
        recipeData.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipeData);
        Set<Recipe> recipes= recipeService.getRecipes();
        assertEquals(recipes.size(),1);
        
    }
}
