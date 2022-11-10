package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

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
        //given
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe2 = Recipe.builder()
                .id(2L)
                .description("recipe2")
                .build();
        recipes.add(recipe2);

        ArgumentCaptor<Set<Recipe>> recipesCaptor = ArgumentCaptor.forClass(Set.class);

        when(recipeServiceMock.findAll()).thenReturn(recipes);

        //when
        String templateName = indexController.indexPage(modelMock);

        //then
        assertEquals("index", templateName);
        verify(recipeServiceMock, times(1)).findAll();
        verify(modelMock, times(1)).addAttribute(eq("recipes"), recipesCaptor.capture());
        Set<Recipe> capturedSet = recipesCaptor.getValue();
        assertEquals(2, capturedSet.size());
    }
}