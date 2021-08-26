package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domains.Ingredient;

import java.util.Set;

public interface IngredientService {
    //Ingredient methods
    Set<Ingredient> getAll();
    Ingredient getById(Long id);
    void deleteById(Long id);

    //IngredientCommand methods
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
}
