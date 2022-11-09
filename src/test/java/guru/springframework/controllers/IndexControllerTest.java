package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    IndexController indexController;
    @Mock
    RecipeService recipeServiceMock;
    @Mock
    Model modelMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeServiceMock);
    }

    @Test
    public void indexPage() {

        String templateName = indexController.indexPage(modelMock);

        verify(recipeServiceMock, times(1)).findAll();
        verify(modelMock, times(1)).addAttribute(eq("recipes"), anySet());
        assertEquals("index", templateName);
    }
}