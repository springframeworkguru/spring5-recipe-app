package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeImageService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeImageControllerTest {

    final Long recipeId = 123L;
    final Long nonExistingRecipeId = 999L;
    MockMvc mockMvc;
    RecipeImageController controller;
    @Mock
    RecipeService recipeService;
    @Mock
    RecipeImageService recipeImageService;
    final byte[] imageBytes = "allo allo bytes".getBytes();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeImageController(recipeService, recipeImageService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void showUploadFormExistingRecipe() throws Exception {
        //given
        when(recipeService.recipeExists(eq(recipeId))).thenReturn(true);

        //when-then
        mockMvc.perform(get(String.format("/recipe/%d/image", recipeId)))
                .andExpect(view().name("recipe/imageuploadform"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("recipeId", recipeId));

        verify(recipeService).recipeExists(eq(recipeId));
    }

    @Test
    public void showUploadFormNonExistingRecipe() throws Exception {
        //given
        when(recipeService.recipeExists(eq(nonExistingRecipeId))).thenReturn(false);

        //when-then
        mockMvc.perform(get(String.format(String.format("/recipe/%d/image", nonExistingRecipeId))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void postImageHappyPath() throws Exception {
        //then
        MockMultipartFile file = new MockMultipartFile("imagefile", "filename.png", "text/plain", "Contenu de l'image".getBytes());
        when(recipeImageService.save(eq(recipeId), any())).thenReturn(true);

        //when
        mockMvc.perform(multipart("/recipe/{recipeId}/image", recipeId).file(file))
                .andExpect(status().is3xxRedirection());
                //.andExpect(header().string("location", String.format("recipe/%d/show", recipeId)));

        //then
        verify(recipeImageService).save(eq(recipeId), notNull());
    }

    @Test
    public void showRecipeImage() throws Exception {
        //given
        final Byte[] wrappedImageBytes = IntStream.range(0, imageBytes.length).mapToObj(i -> imageBytes[i]).toArray(Byte[]::new);

        final Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setImage(wrappedImageBytes);
        when(recipeImageService.findById(eq(recipeId))).thenReturn(wrappedImageBytes);
        //TODO comment est-ce que je vérifie un ResponseEntity?

        //when
        final MockHttpServletResponse response = mockMvc.perform(get("/recipe/{recipeId}/recipeimage", recipeId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //then
        assertArrayEquals(imageBytes, response.getContentAsByteArray());
        verify(recipeImageService).findById(eq(recipeId));
    }
}
