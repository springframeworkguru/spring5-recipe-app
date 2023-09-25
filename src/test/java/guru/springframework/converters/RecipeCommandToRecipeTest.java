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
class RecipeCommandToRecipeTest {
    public static final String RECIPE_DESCRIPTION = "RECIPE_DESCRIPTION";
    public static final long ID = 1L;
    @Mock
    private NotesCommandToNotes notesCommandToNotes;
    @Mock
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    private CategoryCommandToCategory categoryCommandToCategory;
    @InjectMocks
    private RecipeCommandToRecipe recipeCommandToRecipe;


    @BeforeEach
    void setUp() {
        recipeCommandToRecipe = new RecipeCommandToRecipe(notesCommandToNotes,
                ingredientCommandToIngredient, categoryCommandToCategory);
    }

    @Test
    void testNull(){
        Recipe recipe = recipeCommandToRecipe.convert(null);
        assertNull(recipe);
    }

    @Test
    void testNotNull(){
        Recipe recipe = recipeCommandToRecipe.convert(new RecipeCommand());
        assertNotNull(recipe);
    }

    @Test
    void testConvert(){
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(RECIPE_DESCRIPTION);

        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
    }
}