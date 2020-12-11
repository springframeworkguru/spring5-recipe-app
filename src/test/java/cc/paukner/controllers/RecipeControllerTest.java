package cc.paukner.controllers;

import cc.paukner.domain.Recipe;
import cc.paukner.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void testMockMvc() throws Exception {
        // good for Spring MVC controllers
        // With webAppContextSetup that'd no longer be a unit test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/index"));
    }

    @Test
    public void listRecipes() {
        when(recipeService.getAllRecipes()).thenReturn(Set.of(Recipe.builder().description("one").build(),
                Recipe.builder().description("two").build()));
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String view = recipeController.listRecipes(model);

        assertEquals("recipes/index", view);
        verify(recipeService, times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> returnedRecipes = argumentCaptor.getValue();
        assertEquals(2, returnedRecipes.size());
    }
}
