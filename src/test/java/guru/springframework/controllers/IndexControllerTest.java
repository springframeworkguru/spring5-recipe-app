package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

  IndexController controller;

  @Mock
  RecipeService service;

  @Mock
  Model model;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    controller = new IndexController(service);
  }

  @Test
  public void getIndexPage() {

    String indexPage = controller.getIndexPage(model);

    assertEquals("index", indexPage);
    verify(service, times(2)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), anySet());
  }
}