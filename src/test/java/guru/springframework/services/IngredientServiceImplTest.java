package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class IngredientServiceImplTest {


    @Mock
    RecipeRepository recipeRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;


    private IngredientServiceImpl ingredientService;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand =
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient =
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository,
                ingredientToIngredientCommand,
                ingredientCommandToIngredient,
                unitOfMeasureService);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("Test");

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.getIngredients().add(ingredient);


        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand command = ingredientService.
                findByRecipeIdAndIngredientId(1L,
                        1L);

        assertNotNull(command);
        assertEquals(1L, command.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }


    @Test
    void saveIngredient() {
        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(1L);
        command.setId(1L);

        Optional<Recipe> recipeOpt = Optional.of(new Recipe());

        Recipe saved = new Recipe();
        saved.addIngredient(new Ingredient());
        saved.getIngredients().iterator().next().setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOpt);
        when(recipeRepository.save(any())).thenReturn(saved);

        IngredientCommand savedCommand = ingredientService.saveOrUpdateIngredient(command);

        assertEquals(1L, savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());

    }

    @Test
    void deleteIngredientById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient.setId(2L);

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient2);

        ingredient.setRecipe(recipe);
        ingredient2.setRecipe(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientService.deleteIngredientById(1L, 2L);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));


    }
}
