package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController controller;

    MockMvc mockMvc;

    final Long id = 1L;
    RecipeDto recipeDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        recipeDto = new RecipeDto();
        recipeDto.setId(id);
    }

    @Test
    void showById() throws Exception {
        Recipe mockRecipe = Recipe.builder()
                .id(id)
                .build();

        when(recipeService.findById(anyLong())).thenReturn(mockRecipe);

        mockMvc.perform(get("/recipe/" + id + "/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    void getNewRecipeForm() throws Exception {
        //when
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void postNewRecipeForm() throws Exception {
        //given
        when(recipeService.saveRecipeDto(any())).thenReturn(recipeDto);

        //when
        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "My description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + id + "/show"));
        //then
        verify(recipeService, times(1)).saveRecipeDto(any());

    }

    @Test
    void getUpdateForm() throws Exception {
        //given
        when(recipeService.getRecipeDtoById(anyLong())).thenReturn(recipeDto);

        //when
        mockMvc.perform(get("/recipe/" + id + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).getRecipeDtoById(anyLong());
    }


    @Test
    void deleteRecipeById() throws Exception {
        mockMvc.perform(get("/recipe/" + id + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
}