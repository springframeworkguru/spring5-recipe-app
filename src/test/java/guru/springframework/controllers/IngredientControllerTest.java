package guru.springframework.controllers;

import guru.springframework.dtos.IngredientDto;
import guru.springframework.dtos.RecipeDto;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;

import guru.springframework.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    final Long recipeId = 2L;
    final Long ingredientId = 1L;
    RecipeDto mockRecipe;
    IngredientDto mockIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockRecipe = new RecipeDto();
        mockRecipe.setId(recipeId);
        mockIngredient = new IngredientDto();
        mockIngredient.setId(ingredientId);
        mockRecipe.setIngredients(Collections.singleton(mockIngredient));
    }

    @Test
    public void listIngredients() throws Exception {
        //given
        when(recipeService.getRecipeDtoById(anyLong())).thenReturn(mockRecipe);

        //when
        mockMvc.perform(get("/recipe/" + recipeId + "/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).getRecipeDtoById(anyLong());
    }

    @Test
    public void showIngredientOfRecipe() throws Exception {
        //given
        when(ingredientService.getIngredientByIdOfRecipeId(anyLong(), anyLong())).thenReturn(mockIngredient);

        mockMvc.perform(get("/recipe/"+ recipeId + "/ingredient/" + ingredientId+"/show"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"));
        //then
        verify(ingredientService, times(1)).getIngredientByIdOfRecipeId(anyLong(), anyLong());
    }

    @Test
    public void showIngredientUpdateForm() throws Exception {
        //given
        when(ingredientService.getIngredientByIdOfRecipeId(anyLong(), anyLong())).thenReturn(mockIngredient);

        mockMvc.perform(get("/recipe/"+ recipeId + "/ingredient/" + ingredientId+"/update"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"));
        //then
        verify(unitOfMeasureService, times(1)).getAllUom();
        verify(ingredientService, times(1)).getIngredientByIdOfRecipeId(anyLong(), anyLong());
    }
}