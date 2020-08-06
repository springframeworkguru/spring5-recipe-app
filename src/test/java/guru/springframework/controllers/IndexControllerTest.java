package guru.springframework.controllers;

import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.Assert.*;

public class IndexControllerTest {

    @Mock
    private CategoryRepository categoryRepo;
    @Mock
    private UnitOfMeasureRepository measRepo;
    @Mock
    private RecipeService recipeService;

    @Mock
    Model model;

    private IndexController indexController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(categoryRepo,measRepo,recipeService);

    }

    @Test
    public void testMockMvc() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        String viewName = indexController.getIndexPage(model);
        assertEquals("index",viewName);
    }
}