package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        // given
        Set<Recipe> recipeData = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipeData.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);

        recipeData.add(recipe2);

        when(recipeService.findAll()).thenReturn(recipeData);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        // when
        String indexPage = controller.getIndexPage(model);
        assertEquals("index", indexPage);

        verify(recipeService, times(1)).findAll();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}