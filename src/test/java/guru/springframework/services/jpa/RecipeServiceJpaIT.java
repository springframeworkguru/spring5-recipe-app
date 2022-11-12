package guru.springframework.services.jpa;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDto;
import guru.springframework.mappers.RecipeMapper;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceJpaIT {

    @Autowired
    RecipeServiceJpa service;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeMapper recipeMapper;

    public static final String NEW_DESCRIPTION = "New description";

    @Test
    @Transactional
    public void saveRecipeDtoImpl() {
        //given
        Iterable<Recipe> recipes =  recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeDto testRecipeDto = recipeMapper.recipeToRecipeDto(testRecipe);

        //when
        testRecipeDto.setDescription(NEW_DESCRIPTION);
        RecipeDto savedRecipeDto =  service.saveRecipeDtoImpl(testRecipeDto);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeDto.getDescription());
        assertEquals(testRecipeDto.getId(), savedRecipeDto.getId());
        assertEquals(testRecipeDto.getCategories().size(), savedRecipeDto.getCategories().size());
        assertEquals(testRecipeDto.getIngredients().size(), savedRecipeDto.getIngredients().size());
    }
}
