package guru.springframework.mappers;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDto;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


public class RecipeMapperTest {

    RecipeMapper recipeMapper;

    @Before
    public void setUp() throws Exception {
        recipeMapper = Mappers.getMapper(RecipeMapper.class);
    }

    @Test
    public void recipeToRecipeDto() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);

        assertNotNull(recipeDto);
        assertEquals(recipe.getId(), recipeDto.getId());
    }

    @Test
    public void recipeDtoToRecipe() {
    }
}