package guru.springframework.services;

import guru.springframework.converters.*;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository repository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(repository, recipeCommandToRecipe, recipeToRecipeCommand);

    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(repository.findAll()).thenReturn(recipesData);

        Set<Recipe> returnedRecipes = recipeService.getRecipes();

        assertEquals(1, returnedRecipes.size());
        verify(repository).findAll();
        verify(repository, never()).findById(anyLong());
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(repository.findById(anyLong())).thenReturn(optionalRecipe);


        Recipe recipeById = recipeService.getRecipeById(1L);
        assertNotNull("Null recipe returned", recipeById);
        verify(repository).findById(anyLong());
        verify(repository, never()).findAll();

    }

}