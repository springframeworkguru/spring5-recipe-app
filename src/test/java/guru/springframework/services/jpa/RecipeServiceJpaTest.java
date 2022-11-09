package guru.springframework.services.jpa;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceJpaTest {
    RecipeServiceJpa recipeServiceJpa;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeServiceJpa = new RecipeServiceJpa(recipeRepository);
    }

    @Test
    public void findAll() {
        Recipe recipeData = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipeData);

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeServiceJpa.findAll();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}