package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    private static final Long RECIPE_ID = Long.valueOf("44");
    private IngredientServiceImpl ingredientService;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void shouldFindByRecipeIdAndIngredientId() {
        // given
        Optional<Recipe> recipeOptional = prepareRecipeOptional();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(44L, 1L);

        // then
        assertEquals("Test ingredient", ingredientCommand.getDescription());
        assertEquals(new Long("44"), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldSaveIngredientCommand() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(RECIPE_ID);

        final Optional<Recipe> recipeOptional = prepareRecipeOptional();

        when(recipeRepository.findById(RECIPE_ID)).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(recipeOptional.get());

        // when
        final IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        // then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    private Optional<Recipe> prepareRecipeOptional() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient i1 = new Ingredient();
        i1.setId(1L);
        i1.setDescription("Test ingredient");
        i1.setRecipe(recipe);
        Ingredient i2 = new Ingredient();
        i2.setId(2L);
        i2.setRecipe(recipe);
        ingredients.add(i1);
        ingredients.add(i2);

        recipe.setIngredients(ingredients);
        return Optional.of(recipe);
    }
}