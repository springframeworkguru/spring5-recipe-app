package guru.springframework.controller;

import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.service.RecipeServiceImpl;
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
    RecipeServiceImpl recipeService;
    @Mock
    Model model;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMvc() throws Exception {
        //A mock dispatcher servelet is used instead of spring context!
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void getIndexPage() throws Exception {

        //given
        Set<Recipe>recipes = new HashSet<>();
        Recipe test1 = new Recipe();
        //Cuz hash is based by ID I believe
        test1.setId(43L);

        recipes.add(new Recipe());
        recipes.add(test1);


        when(recipeService.getRecipes()).thenReturn(recipes);
            //I guess, this captures everything that is a set being passed through?
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //When
        String test = indexController.getIndexPage(model);


        assertEquals("index",test);
        verify(recipeService,times(1)).getRecipes();
            //In verify (matcher), all REAL values have to be provided by matchers (stuff like eqs)
        verify(model,times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> sentInController = argumentCaptor.getValue();
        assertEquals(2,sentInController.size());

    }

}