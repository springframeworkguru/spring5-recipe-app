package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {

    IndexController indexController;

    @Mock
    Model model;
    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage_ShouldReturnIndexAndAddsRecipes() {
        String expected = "index";

        Recipe recipe = new Recipe();
        recipe.setId(2L);

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> setCaptor = ArgumentCaptor.forClass(Set.class);

        String actual = indexController.indexPage(model);

        assertEquals(expected, actual);
        verify(model, times(1)).addAttribute(eq("recipes"), setCaptor.capture());
        verify(recipeService, times(1)).getRecipes();
        assertEquals(2, setCaptor.getValue().size());
    }

    @Test
    public void testCallOnLandingPage_ShouldReturnIndexAndStatusOk() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"));
        } catch (Exception e) {
            fail();
        }
    }
}