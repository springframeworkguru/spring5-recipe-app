package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(long recipeID, long ingredientID);

    IngredientCommand saveOrUpdateIngredient(IngredientCommand ingredientCommand);

    void deleteIngredientById(Long recipeId, Long ingredientId);


}
