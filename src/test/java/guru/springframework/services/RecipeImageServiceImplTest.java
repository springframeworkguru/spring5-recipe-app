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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    final Long recipeId = 111L;
    RecipeImageServiceImpl recipeImageService;

    //TODO setup mocks
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recipeImageService = new RecipeImageServiceImpl(recipeRepository);
    }

    @Test
    public void save() throws IOException {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        when(recipeRepository.findById(eq(recipeId))).thenReturn(Optional.of(recipe));
        final MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "Mon contenu fou".getBytes());

        //when
        recipeImageService.save(recipeId, multipartFile);

        final ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);

        //then
        verify(recipeRepository).save(captor.capture());
        assertEquals(multipartFile.getBytes().length, captor.getValue().getImage().length);
    }

    @Test
    public void findById() {
        //given
        final byte[] primitiveBytes = "primitive bytes".getBytes();
        Byte[] wrappedBytes = new Byte[primitiveBytes.length];
        for (int i = 0; i < primitiveBytes.length; i++) {
            wrappedBytes[i] = primitiveBytes[i];
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setImage(wrappedBytes);
        when(recipeRepository.findById(eq(recipeId))).thenReturn(Optional.of(recipe));

        //when
        final Byte[] foundBytes = recipeImageService.findById(recipeId);

        //then
        assertArrayEquals(wrappedBytes, foundBytes);
        verify(recipeRepository).findById(eq(recipeId));
    }


}
