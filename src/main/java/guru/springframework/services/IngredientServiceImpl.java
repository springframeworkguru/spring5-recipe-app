package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final RecipeRepository recipeRepository;

  public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    log.debug("Finding by ids: recipe - " + recipeId + "; ingredient: " + ingredientId);
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    Recipe recipe = recipeOptional.orElseThrow(() -> new IllegalArgumentException("Recipe with id:" + recipeId + " has no ingredients"));

    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredientToIngredientCommand::convert)
        .findFirst();

    return ingredientCommandOptional.orElseThrow(() -> new IllegalArgumentException("Ingredient with id:" + ingredientId + " not found"));
  }

  @Override
  public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getId());
//    recipeOptional.
    return null;
  }
}
