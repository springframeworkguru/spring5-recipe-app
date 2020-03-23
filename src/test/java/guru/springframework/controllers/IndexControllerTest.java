package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    @Mock
    private RecipeService recipeService;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndex() {
        Set<Recipe> recipeDatas = new HashSet<>();
        recipeDatas.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipeDatas);

        Model mock = mock(Model.class);
        String index = indexController.getIndex(mock);

        verify(mock, times(1)).addAttribute(eq("recipes"),
                argThat(recipes -> recipes.equals(recipeDatas)));

        assertEquals("index", index);
    }
}
