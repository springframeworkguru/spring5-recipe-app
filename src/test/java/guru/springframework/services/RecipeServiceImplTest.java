package guru.springframework.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest extends TestCase {
  RecipeServiceImpl recipeService;
  @Mock
  RecipeRepository recipeRepository;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository);
  }
  public void testGetRecipes() {
    Recipe recipe = new Recipe();
    HashSet recipesData = new HashSet();
    recipesData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
  }
}
