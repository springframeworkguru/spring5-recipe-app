package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.RecipeNotFoundException;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    final String nonExistingUrl = "/NONEXISTINGURLHOPEFULLY";
    @Mock
    RecipeService service;
    RecipeController recipeController;
    MockMvc mockMvc;
    Long id = 33L;
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        recipeController = new RecipeController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        recipe = new Recipe();
    }

    @Test
    public void showRecipeById() throws Exception {
        recipe.setId(id);
        when(service.getRecipeById(id)).thenReturn(recipe);
        mockMvc.perform(get("/recipe/33/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attribute("recipe", any(Recipe.class)));
        verify(service).getRecipeById(eq(id));
    }

    @Test
    public void postRecipe() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(id);
        when(service.saveRecipeFromCommandObject(ArgumentMatchers.any()))
                .thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "Recette test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + id + "/show"));
    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);
        when(service.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void deleteRecipeById() throws Exception {
        mockMvc.perform(get("/recipe/" + id + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(service).deleteById(id);
    }

    @Test
    public void showRecipeByIdNotFound() throws Exception {
        //given
        when(service.getRecipeById(anyLong())).thenThrow(RecipeNotFoundException.class);

        //when
        mockMvc.perform(get("/recipe/33/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404error"))
                .andExpect(model().attributeExists("exception"));


        //then
        verify(service).getRecipeById(anyLong());
    }

    @Test
    public void numberFormatExceptionHandler() throws Exception {
        mockMvc.perform(get("/recipe/auieauie/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/400error"))
                .andExpect(model().attributeExists("exception"));
    }

}
