package guru.springframework.controllers;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    public static final long ID = 1L;
    public static final long RECIPE_CMD_ID = 1L;
    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void testGetRecipeById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    void testNewRecipe() throws Exception {

        mockMvc.perform( get("/recipe/new" ))
                .andExpect( status().isOk() )
                .andExpect( model().attributeExists("recipe" ))
                .andExpect( view().name("recipe/recipeform"));

    }

    @Test
    void testSaveOrUpdateRecipe() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_CMD_ID);
        when( recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform( post("/recipe/save" )
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param( "description", "")
                )
                .andExpect( status().is3xxRedirection() )
                .andExpect( view().name("redirect:/recipe/show/" + RECIPE_CMD_ID ));
    }

    @Test
    void testUpdateRecipe() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_CMD_ID);
        when( recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        mockMvc.perform( get("/recipe/1/update" ))
                .andExpect( status().isOk() )
                .andExpect( model().attributeExists("recipe" ))
                .andExpect( view().name("/recipe/recipeform"));
    }

    @Test
    void testDeleteRecipe() throws Exception{
        mockMvc.perform( get("/recipe/1/delete" ))
                .andExpect( status().is3xxRedirection() )
                .andExpect( view().name("redirect:/" ));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
}