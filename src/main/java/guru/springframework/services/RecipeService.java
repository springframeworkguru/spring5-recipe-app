package guru.springframework.services;

import guru.springframework.command.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {
    Set<Recipe> findAllRecipes();

    Recipe findById(long id);

    RecipeCommand findCommandById(long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(long id);
}
