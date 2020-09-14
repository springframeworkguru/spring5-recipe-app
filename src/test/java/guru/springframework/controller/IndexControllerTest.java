package guru.springframework.controller;

import guru.springframework.service.RecipeService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    private Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        String indexPage = indexController.getIndexPage(model);
        Assertions.assertThat(indexPage).isEqualTo("index");
        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }
}