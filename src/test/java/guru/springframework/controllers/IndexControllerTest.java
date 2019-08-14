package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    private IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setup(){
       MockitoAnnotations.initMocks(this);
       indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
       String viewName = indexController.getIndexPage(model);
       assertEquals("index", viewName);
       verify(recipeService, times(1)).getRecipes();
       verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }
}