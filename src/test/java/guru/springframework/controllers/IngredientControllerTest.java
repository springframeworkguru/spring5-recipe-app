package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    private IngredientService ingredientService;

    @Mock
    private RecipeService recipeService;

    @Mock
    private UnitOfMeasureService uomService;

    @InjectMocks
    private IngredientController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(ingredientService, recipeService, uomService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getUpdateRecipe() {
        IngredientCommand ingredientCommand = new IngredientCommand();


        when(ingredientService.findByIngredientId(anyLong())).thenReturn(ingredientCommand);
        when(uomService.findAll()).thenReturn(Collections.emptySet());

        try {
            mockMvc.perform(get("/recipe/1/ingredient/3/update"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("ingredient/ingredient_form"))
                    .andExpect(model().attributeExists("ingredient"))
                    .andExpect(model().attributeExists("uomList"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteIngredient() {
        try {
            mockMvc.perform(get("/recipe/1/ingredient/3/delete"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/recipe/1/show"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(ingredientService, times(1)).deleteById(anyLong(), anyLong());
    }
}