package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    ArgumentCaptor<Set<Recipe>> recipeArgumentCaptor ;
    @BeforeEach
    void setUp() {
        indexController = new IndexController(recipeService);
        mockRecipeServiceFindAllRecipe();
        resultOfIndexPage = indexController.getIndexPage(model);
    }

    private void mockRecipeServiceFindAllRecipe() {
        recipes = new HashSet<>();
        Recipe testRecipe = new Recipe();
        testRecipe.setDescription("Test Recipe");
        recipes.add(testRecipe);
        Recipe tesRecipe2 = new Recipe();
        tesRecipe2.setDescription("Test Recipe 2");
        recipes.add( tesRecipe2 );
        Mockito.when(recipeService.findAllRecipes()).thenReturn(recipes);
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

    //@Test
    void verifyModalAddAttributeCalledExactlyOneTime() {
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), Mockito.anySet());
    }

    @Test
    void verifyMockForRecipeServicePassedArgumentWith2Values(){
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), recipeArgumentCaptor.capture());
        Set<Recipe> argumentSetForRecipeServices = recipeArgumentCaptor.getValue();
        assertEquals(2, argumentSetForRecipeServices.size());
    }
}