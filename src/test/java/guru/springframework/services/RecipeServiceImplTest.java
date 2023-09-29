package guru.springframework.services;

import guru.springframework.command.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    public static final long ID = 1L;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @InjectMocks
    RecipeServiceImpl recipeService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void recipeGetSizeTest(){
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> result = recipeService.findAllRecipes();
        assertEquals(1, result.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    private void setMockForRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        Mockito.when(recipeRepository.findById(Mockito.any())).thenReturn(Optional.of(recipe));
    }
    @Test
    void recipeByIdTest(){
        setMockForRecipeById();
        Recipe actualRecipe = recipeService.findById(ID);
        assertNotNull(actualRecipe);
        assertEquals(ID, actualRecipe.getId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.any());
    }


    @Test
    void testRecipeCommandById(){
        setMockForRecipeById();
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        when( recipeToRecipeCommand.convert(any(Recipe.class))).thenReturn(recipeCommand);
        RecipeCommand actualRecipeCommand = recipeService.findCommandById(ID);
        assertNotNull(actualRecipeCommand);
    }

    @Test
    void testDeleteById() {
        Long idToDelete = 2L;
        recipeService.deleteById(2L);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}