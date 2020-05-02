package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.models.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;


    @Before
    public void setUp() throws Exception {

        // create mockito mock
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {

        // build Mock Mvc from indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void getIndexPage() {

        //given
        Set<Recipe> recipes = new HashSet<>();
        Recipe rec1 = new Recipe();
        rec1.setId(1L);
        rec1.setDescription("aaaa");
        recipes.add(rec1);

        Recipe rec2 = new Recipe();
        rec2.setDirections("bbbb");
        recipes.add(rec2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        // when
        // test that indexController.getIndexPage returns an "Index"
        String retValue = indexController.getIndexPage(model);

        // Create capture for return content of Set type
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // then
        assertEquals(retValue, "index");
        // verify that recipeService.getRecipes() runs exactly one time
        verify(recipeService, times(1)).getRecipes();
        // test that model.addAttribute() runs once, and:
        // addAttribute values are equal to "recipes" and anySet                          // capture what is pass into argumentCaptor
        verify(model, times(1)).addAttribute( eq("recipes"), argumentCaptor.capture());

        // test the return content in argumentCaptor.capture()
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}