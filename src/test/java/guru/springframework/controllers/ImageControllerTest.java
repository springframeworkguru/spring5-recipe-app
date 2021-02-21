package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    @InjectMocks
    ImageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ImageController(recipeService, imageService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getUploadImageForm_ShouldReturnFormViewName() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        try {
            mockMvc.perform(get("/recipe/1/image"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("recipe"))
                    .andExpect(view().name("recipe/image_upload_form"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void uploadImage() {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Test file".getBytes());

        try {
            mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/recipe/1/show"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }
}