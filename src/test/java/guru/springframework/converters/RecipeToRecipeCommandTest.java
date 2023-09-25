package guru.springframework.converters;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeToRecipeCommandTest {

    public static final String RECIPE_DESCRIPTION = "RECIPE_DESCRIPTION";
    public static final long ID = 1L;
    @Mock
    private NotesToNotesCommand notesToNotesCommand;
    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    private CategoryToCategoryCommand categoryToCategoryCommand;

    @InjectMocks
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @BeforeEach
    void setUp() {
        recipeToRecipeCommand = new RecipeToRecipeCommand(notesToNotesCommand,
                ingredientToIngredientCommand, categoryToCategoryCommand);
    }

    @Test
    void testNull(){
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(null);
        assertNull(recipeCommand);
    }

    @Test
    void testNotNull(){
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(new Recipe());
        assertNotNull(recipeCommand);
    }

    @Test
    void testConvert(){
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(RECIPE_DESCRIPTION);

        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
        assertNotNull(recipeCommand);
        assertEquals(ID, recipeCommand.getId());
        assertEquals(RECIPE_DESCRIPTION, recipeCommand.getDescription());
    }
}