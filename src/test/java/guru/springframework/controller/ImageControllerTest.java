package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.service.ImageService;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ImageControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    ImageService imageService;
    ImageController imageController;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService,recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void showUploadForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        when(recipeService.findCommandById(anyLong()))
                .thenReturn(command);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                .andExpect(MockMvcResultMatchers.view().name("recipe/imageuploadform"));

    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile","testing.txt","text/plain",
                "spring framework guru".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location","/recipe/1/show"));
    }
    @Test
    public void testLoadImageFromDB() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        String s = "TestImage";
        Byte[] imageByte = new Byte[s.getBytes().length];
        int i = 0;
        for (byte b : s.getBytes()){
            imageByte[i++]= b;
        }
        recipeCommand.setImage(imageByte);
        recipeCommand.setId(1l);
        when(recipeService.findCommandById(anyLong()))
                .thenReturn(recipeCommand);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/recipeImage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        assertEquals(contentAsByteArray.length,s.getBytes().length);
    }
}