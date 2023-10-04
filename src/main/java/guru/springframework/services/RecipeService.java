package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand findCommandById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long idToDelete);
}
