package guru.springframework.services.jpa;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class RecipeServiceJpaTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.recipeService = new RecipeServiceJpa(this.recipeRepository);
    }

    @Test
    public void getAll() {

        Recipe recipe = Recipe.builder()
                .id(2l)
                .description("Ñoquis")
                .build();

        Set<Recipe> mockRecipes = new HashSet<>();
        mockRecipes.add(recipe);

        when(this.recipeRepository.findAll()).thenReturn(mockRecipes);

        final Set<Recipe> recipes = this.recipeService.getAll();

        Assert.assertEquals(1, recipes.size());
        verify(this.recipeRepository, times(1)).findAll();

    }
}
