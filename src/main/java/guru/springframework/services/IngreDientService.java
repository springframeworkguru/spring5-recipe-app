package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngreDientService {
    public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    public IngredientCommand saveIngredienCommand(IngredientCommand command);
}
