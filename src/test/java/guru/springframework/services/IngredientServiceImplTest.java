package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

  private IngredientServiceImpl ingredientService;
  private IngredientToIngredientCommand ingredientToIngredientCommand;

  @Mock
  private RecipeRepository recipeRepository;


  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
  }

  @Test
  public void shouldFindByRecipeIdAndIngredientId() {
    // given
    Recipe recipe = new Recipe();
    recipe.setId(44L);

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
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    // when
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(44L, 1L);

    // then
    assertEquals("Test ingredient", ingredientCommand.getDescription());
    assertEquals(new Long("44"), ingredientCommand.getRecipeId());
    verify(recipeRepository, times(1)).findById(anyLong());
  }
}