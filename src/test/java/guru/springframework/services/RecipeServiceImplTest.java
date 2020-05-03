package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,
                recipeCommandToRecipe,
                recipeToRecipeCommand);
    }

    @Test
    void getRecipesTest() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);
        assertEquals(1, recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();

    }

    @Test
    void getRecipeByIdTestNotFound() {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);


        Exception exception = assertThrows(
                NotFoundException.class,
                () -> recipeService.findById(1L));
        assertThrows(NotFoundException.class, () -> recipeService.findById(1L));
        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().contains("not found"));


    }

    @Test
    void getRecipesByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(4L);

        when(recipeRepository.findById(4L)).thenReturn(Optional.of(recipe));

        Recipe returnedRecipe = recipeService.findById(4L);

        assertNotNull(returnedRecipe, "Null Recipe Returned");
        assertEquals(4L, returnedRecipe.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void deleteRecipeByIDTest() throws Exception {
        recipeService.deleteById(1L);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void saveRecipeCommandTest() {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("Test Description");

        // when(recip)
    }
}
