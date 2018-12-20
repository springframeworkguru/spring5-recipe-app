package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void getIndexPage() {

        // given
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe1);
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes); // substitute our test data Set to the one expected to be returned by the mocked class

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class); // create an ArgumentCaptor for a Set

        // when
        String viewName = indexController.getIndexPage(model);

        // then
        String attributeName = "recipes";
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes(); // verify that getRecipes is called only one time
        verify(model, times(1)).addAttribute(eq(attributeName), argumentCaptor.capture()); // verify that addAttribute is called only one time, with the proper arguments, capturing the second one
        Set<Recipe> setInController = argumentCaptor.getValue(); // retrieve the captured argument
        assertEquals(2, setInController.size());
    }
}