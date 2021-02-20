package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByIngredientId(Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
