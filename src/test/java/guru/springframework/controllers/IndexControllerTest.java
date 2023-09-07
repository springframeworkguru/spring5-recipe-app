package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    String resultOfIndexPage;
    Set<Recipe> recipes;
    @BeforeEach
    void setUp() {
        indexController = new IndexController(recipeService);
        recipes = new HashSet<>();
        Recipe testRecipe = new Recipe();
        testRecipe.setDescription("Test Recipe");
        recipes.add(testRecipe);
        Mockito.when(recipeService.findAllRecipes()).thenReturn(recipes);
        resultOfIndexPage = indexController.getIndexPage(model);
    }

    @Test
    void testIndexPageIsNotNull(){
        assertNotNull(resultOfIndexPage);
    }

    @Test
    void testIndexPageReturnEqualsToIndex(){
        assertEquals(resultOfIndexPage, "index");
    }

    @Test
    void verifyRecipeServiceFindAllRecipeCalledExactlyOneTime(){
        Mockito.verify(recipeService, Mockito.times(1)).findAllRecipes();
    }

    @Test
    void verifyModalAddAttributeCalledExactlyOneTime() {
        Mockito.verify(model, Mockito.times(1)).addAttribute("recipes", recipes);
    }
}