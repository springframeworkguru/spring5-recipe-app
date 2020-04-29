package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {


    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    void getRecipes() {
        // when(recipeController.getRecipes(model)).

        //Given
        String expected = "index";

        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);

        recipes.add(recipe1);
        recipes.add(recipe2);

        //When
        when(recipeService.getRecipes()).thenReturn(recipes);

        //Then

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
        String actual = indexController.getRecipes(model);
        assertEquals(expected, actual);

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).
                addAttribute(eq("recipes"),
                        captor.capture());
        Set<Recipe> setInController = captor.getValue();

        assertEquals(2, setInController.size());
    }

    @Test
    void getRecipesController() throws Exception {
        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
