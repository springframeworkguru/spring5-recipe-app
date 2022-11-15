package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    ImageService service;

    final Long recipeId = 1L;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        //given
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imageFile",
                "testing.txt",
                "text/plain", "I am learning".getBytes());
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(recipeId);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));

        //when
        service.saveImageFile(recipeId, mockMultipartFile);

        //then
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertNotNull(savedRecipe.getImage());
        assertEquals(mockMultipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}