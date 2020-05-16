package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.RecipeNotFoundException;
import guru.springframework.repositories.RecipeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    final Long id = 123L;
    @Mock
    RecipeRepository repository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeServiceImpl service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new RecipeServiceImpl(repository,
                recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRecipes() {
        final Recipe recipe = new Recipe();
        Set<Recipe> set = new HashSet<>();
        set.add(recipe);
        when(repository.findAll()).thenReturn(set);

        final Set<Recipe> recipes = service.getRecipes();
        assertEquals(1, recipes.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        Long id = 33L;
        final Recipe recipe = new Recipe();
        recipe.setId(id);
        when(repository.findById(eq(id))).thenReturn(Optional.of(recipe));
        assertEquals((Long) 33L, service.getRecipeById(33L).getId());
    }

    @Test
    public void findCommandById() {
        final RecipeCommand recipeCommand = new RecipeCommand();
        final Recipe recipe = new Recipe();
        recipe.setId(id);
        recipeCommand.setId(id);
        when(repository.findById(eq(id))).thenReturn(Optional.of(recipe));
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);
        final RecipeCommand commandById = service.findCommandById(id);
        assertNotNull(commandById);
        assertEquals(id, commandById.getId());
    }

    @Test
    public void deleteById() {
        service.deleteById(id);
        verify(repository).deleteById(eq(id));
    }

    @Test(expected = RecipeNotFoundException.class)
    public void findRecipeByIdNotFound() {
        //given
        when(repository.findById(id)).thenReturn(Optional.empty());

        //when
        service.findCommandById(id);

        //then exception is thrown
    }
}

