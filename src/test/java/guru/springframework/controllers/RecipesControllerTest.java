package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipesControllerTest {

    RecipesController controller;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.controller = new RecipesController(this.recipeService);
    }

    @Test
    public void testMockMvc() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();

        mockMvc.perform(get("/recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"));
    }

    @Test
    public void getList() throws Exception {

        final Recipe gnocchi = Recipe.builder()
                .id(2l)
                .description("Gnocchi")
                .build();

        final Recipe fileios = Recipe.builder()
                .id(4l)
                .description("Fileios")
                .build();

        Set<Recipe> mockRecipes = new HashSet<>();
        mockRecipes.add(gnocchi);
        mockRecipes.add(fileios);

        when(this.recipeService.getAll()).thenReturn(mockRecipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        final String viewName = this.controller.getList(this.model);

        final String expected = "recipe/index";

        Assert.assertEquals(expected, viewName);
        verify(this.recipeService, times(1)).getAll();
        verify(this.model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> recipesReturned = argumentCaptor.getValue();

        final int expectedSetSize = 2;

        assertEquals(expectedSetSize, recipesReturned.size());

    }

}
