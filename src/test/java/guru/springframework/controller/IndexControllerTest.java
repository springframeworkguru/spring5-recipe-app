package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndex() {

        // given
        String expectedView = "index";

        HashSet<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipes.add(recipe);

        Recipe recipe1 = new Recipe();
        recipe1.setId(2L);
        recipes.add(recipe1);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<HashSet<Recipe>> argumentCaptor = ArgumentCaptor.forClass(HashSet.class);

        // when
        String view = indexController.getIndex(model);

        // then
        assertEquals(expectedView, view);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> actualRecipes = argumentCaptor.getValue();
        assertEquals(actualRecipes.size(), 2);
    }
}