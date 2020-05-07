package guru.springframework.controller;

import guru.springframework.command.IngredientCommand;
import guru.springframework.command.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @InjectMocks
    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;


    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        // create mockito mock
        MockitoAnnotations.initMocks(this);

        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testListIngredient() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testShowIngredient() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }

    @Test
    public void testNewIngredientForm() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUOM()).thenReturn(new HashSet<>());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testUpdateIngredientForm() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUOM()).thenReturn(new HashSet<>());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(2L);
        ingredientCommand.setRecipeId(1L);

        when(ingredientService.saveIngredientCommand(ingredientCommand)).thenReturn(ingredientCommand);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("id", "")                        // mimic an empty string
                .param("description", "some string")    // some description
        )
                .andExpect(status().is3xxRedirection()) // expect 3xx status of redirection
                .andExpect(view().name("redirect:/recipe/1/ingredients")); // redirection string

    }

    @Test
    public void testDeleteIngredient() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/ingredient/2/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("id", "")                        // mimic an empty string
                .param("description", "some string")    // some description
        )
                .andExpect(status().is3xxRedirection()) // expect 3xx status of redirection
                .andExpect(view().name("redirect:/recipe/1/ingredients")); // redirection string
    }


}