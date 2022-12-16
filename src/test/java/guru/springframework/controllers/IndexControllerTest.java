package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class IndexControllerTest {

  @Mock
  RecipeServiceImpl recipeService;
  @Mock
  Model model;

  IndexController indexController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    indexController = new IndexController(recipeService);
  }
  @Test
  public void testGetIndexPage() {
    String viewName = indexController.getIndexPage(model);
    assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes());
  }
}
