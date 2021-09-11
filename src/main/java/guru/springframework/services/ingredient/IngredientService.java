package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domains.Ingredient;

import java.util.Set;

public interface IngredientService {
    //Ingredient methods
    Set<Ingredient> getAll();
    Ingredient getById(String id);
    void deleteIngredientFromRecipe(String recipeId, String id);

    //IngredientCommand methods
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
