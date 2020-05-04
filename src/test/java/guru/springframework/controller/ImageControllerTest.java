package guru.springframework.controller;

import guru.springframework.command.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    ImageController imageController;

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        // create mockito mock
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(recipeService, imageService);

        // build Mock Mvc from indexController
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

    }

    // test loading the form
    @Test
    public void testImageForm() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);


        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageuploadform"))
                .andExpect(model().attributeExists("recipe"));
    }


    // test posting an image to the DB
    @Test
    public void testImagePost() throws Exception {

        // Mock the file that will be posted by the web browser
        // look for name 'imageFile'
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes());


        // multipart - mimic a multi part upload
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    // test loading an image from the DB
    @Test
    public void renderImageFromDB() throws Exception {

        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String s = "fake image text";
        Byte[] bytesBox = new Byte[s.getBytes().length];

        int i=0;

        for( byte primeByte : s.getBytes()){
            bytesBox[i++] = primeByte;
        }

        recipeCommand.setImage(bytesBox);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        // when
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);
    }
}