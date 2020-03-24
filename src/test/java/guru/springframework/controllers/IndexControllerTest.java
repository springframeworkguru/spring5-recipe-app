package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @Before
    public void setUP() throws Exception{
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        //given
        Recipe test = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        Recipe test2 = new Recipe();
        recipes.add(test2);
        recipes.add(test);

        when(recipeService.getRecipies()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        assertEquals(indexController.getIndexPage(model), "index");
        verify(recipeService, times(1)).getRecipies();
        verify(model, times(1)).addAttribute(any(), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        System.out.println(setInController);
        assertEquals(2,setInController.size());
    }
}