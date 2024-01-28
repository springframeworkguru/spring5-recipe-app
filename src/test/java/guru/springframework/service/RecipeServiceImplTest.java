package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class RecipeServiceImplTest extends TestCase {
RecipeServiceImpl recipeService;
@Mock
RecipeRepository recipeRepository;
@Before
public void setUp() throws Exception{
    MockitoAnnotations.initMocks(this);
    recipeService= new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void testGetRecipes() {
    Recipe recipe = new Recipe();
    HashSet recipesData = new HashSet();
    recipesData.add(recipe);

    when(recipeService.getRecipes()).thenReturn(recipesData);
    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(recipes.size(),1);
    verify(recipeRepository,times(1)).findAll();

    }
}