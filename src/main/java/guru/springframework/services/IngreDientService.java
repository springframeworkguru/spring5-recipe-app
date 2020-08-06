package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngreDientService {
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    public IngredientCommand saveIngredienCommand(IngredientCommand command);
}
