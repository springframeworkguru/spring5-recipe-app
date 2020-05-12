package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;
    IngredientController controller;
    MockMvc mockMvc;
    Long recipeId = 12345L;
    Long ingredientId = 23456L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void showIngredients() throws Exception {
        //given
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeId);
        when(recipeService.findCommandById(eq(recipeId))).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/" + recipeId + "/ingredients"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"));

        //then
        verify(recipeService).findCommandById(anyLong());
    }

    @Test
    public void showIngredient() throws Exception {
        //given
        when(ingredientService.findByRecipeIdAndIngredientId(eq(recipeId), eq(ingredientId)))
                .thenReturn(new IngredientCommand());

        //when
        mockMvc.perform(get(String.format("/recipe/%d/ingredient/%d/show", recipeId, ingredientId)))
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"));

        //then
        verify(ingredientService).findByRecipeIdAndIngredientId(eq(recipeId), eq(ingredientId));
    }
}
