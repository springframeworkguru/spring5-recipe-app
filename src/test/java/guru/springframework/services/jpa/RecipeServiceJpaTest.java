package guru.springframework.services.jpa;

import guru.springframework.domain.Recipe;
import guru.springframework.mappers.RecipeMapper;
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

public class RecipeServiceJpaTest {
    RecipeServiceJpa service;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeMapper recipeMapper;

    final Long id = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new RecipeServiceJpa(recipeRepository, recipeMapper);
    }

    @Test
    public void findAll() {
        Recipe recipeData = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipeData);

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = service.findAll();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe mockRecipe = Recipe.builder()
                .id(id)
                .build();

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));

        Recipe foundRecipe = service.findById(id);

        assertNotNull("Null recipe returned", foundRecipe);
        assertEquals(id, foundRecipe.getId());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}