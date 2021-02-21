package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    public static final long RECIPE_ID = 1L;

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeService, recipeRepository);
    }

    @Test
    public void saveImageFile() {
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "original.txt", "text/plain", "Test file".getBytes());
        Recipe recipe = Recipe.builder().id(RECIPE_ID).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(RECIPE_ID, multipartFile);

        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        try {
            assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}