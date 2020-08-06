package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand findCommandById(long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
