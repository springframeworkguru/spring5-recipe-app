package guru.springframework.services;

import guru.springframework.Converters.IngredientCommandToIngredient;
import guru.springframework.Converters.IngredientToIngredientCommand;
import guru.springframework.Converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.Converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.command.IngredientCommand;
import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.models.Ingredient;
import guru.springframework.models.Recipe;
import guru.springframework.models.UnitOfMeasure;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @Before
    public void setUp() throws Exception {
        // add the RecipeRepository
        MockitoAnnotations.initMocks(this);

        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {

        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setDescription("aaaa");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setDescription("bbbb");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setDescription("cccc");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 2L);

        // then
        assertNotNull(ingredientCommand);
        assertEquals(Long.valueOf(2L) , ingredientCommand.getId());
        assertEquals(Long.valueOf(1L) , ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveIngredientCommand(){

        // Recipe
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        // IngredientCommand
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        ingredient.setRecipe(recipe);
        ingredient.setDescription("description");
        ingredient.setAmount(BigDecimal.valueOf(5L));

        // UnitOfMeasure
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        ingredient.setUom(unitOfMeasure);

        recipe.getIngredients().add(ingredient);

//        Recipe savedRecipe = new Recipe();
//        savedRecipe.setId(2L);
//        savedRecipe.addIngredient(new Ingredient());
//        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(unitOfMeasure));
        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientToIngredientCommand.convert(ingredient));

        assertNotNull(savedIngredientCommand);
        assertEquals(Long.valueOf(3L), savedIngredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());

    }

    @Test
    public void testDeleteById(){

        // given
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        Ingredient ing1 = new Ingredient();
        ing1.setId(2L);
        ing1.setDescription("aaa");
        ing1.setRecipe(recipe);

        Ingredient ing2 = new Ingredient();
        ing2.setId(3L);
        ing2.setDescription("bbb");
        ing2.setRecipe(recipe);

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ing1);
        ingredientSet.add(ing2);

        recipe.setIngredients(ingredientSet);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        ingredientService.deleteById(1L, 2L);

        Optional<Recipe> savedOptionalRecipe = recipeRepository.findById(1L);
        Recipe savedRecipe = savedOptionalRecipe.get();

        assertNotNull(savedRecipe);
        assertEquals(1, savedRecipe.getIngredients().size());
        verify(recipeRepository, times(2)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());
    }
}