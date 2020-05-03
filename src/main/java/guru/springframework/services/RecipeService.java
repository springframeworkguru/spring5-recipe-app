package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Recipe findById(Long l);
    Set<Recipe> getRecipes();
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(long id);
    void deleteById(Long id);


}
