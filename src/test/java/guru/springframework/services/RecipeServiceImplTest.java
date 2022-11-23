package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class RecipeServiceImplTest extends TestCase {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;


    @Override
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    public void testGetRecipe() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipeSet = recipeService.getRecipe();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}