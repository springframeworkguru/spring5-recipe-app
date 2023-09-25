package guru.springframework.services;

import guru.springframework.command.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceIT {

    public static final String RECIPE_DESCRIPTION = "Test Recipe Description";
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    void testSavedRecipeCommand() {
        Recipe recipe = recipeRepository.findAll().iterator().next();
        recipe.setDescription(RECIPE_DESCRIPTION);
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
        RecipeCommand resultRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        Recipe resultRecipe = recipeCommandToRecipe.convert(resultRecipeCommand);
        assertNotNull(resultRecipe);
        assertEquals(RECIPE_DESCRIPTION, resultRecipe.getDescription());
    }
}