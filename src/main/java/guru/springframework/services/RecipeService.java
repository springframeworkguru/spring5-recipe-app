package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long recipeId);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
