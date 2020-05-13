package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    Long recipeId = 12345L;
    Long ingredientId = 23456L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
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

    @Test
    public void postIngredient() throws Exception {
        //given
        final IngredientCommand ingredienCommandWithId = new IngredientCommand();
        ingredienCommandWithId.setId(ingredientId);

        final IngredientCommand ingredientCommandNoId = new IngredientCommand();

        when(ingredientService.save(any())).thenReturn(ingredienCommandWithId);

        //when
        mockMvc.perform(post(String.format("/recipe/%d/ingredient", recipeId))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "my decsription"))
                //then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(String.format("redirect:/recipe/%d/ingredient/%d/show", recipeId, ingredientId)));
    }

    @Test
    public void updateIngredient() throws Exception {
        //given
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredientId);
        when(ingredientService.findByRecipeIdAndIngredientId(any(), any())).thenReturn(ingredientCommand);
        //when-then
        mockMvc.perform(get(String.format("/recipe/%d/ingredient/%d/update", recipeId, ingredientId)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void newIngredient() throws Exception {
        //given
        final IngredientCommand ingredientCommand = new IngredientCommand();
        when(recipeService.recipeExists(eq(recipeId))).thenReturn(true);
        when(ingredientService.save(same(ingredientCommand))).thenReturn(ingredientCommand);

        //when-then
        mockMvc.perform(get(String.format("/recipe/%d/ingredient/new", recipeId)))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }
}
