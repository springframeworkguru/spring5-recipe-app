package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        // given
        String expectedPage = "index";

        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(0L);
        Recipe recipe2 = new Recipe();
        recipe1.setId(1L);

        recipes.add(recipe1);
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String pageString = indexController.getIndexPage(model);

        // then
        assertEquals(expectedPage, pageString);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"), setArgumentCaptor.capture());
        Set<Recipe> setInController = setArgumentCaptor.getValue();
        assertEquals(2,setInController.size() );
    }
}