package guru.springframework.services;

import guru.springframework.dtos.IngredientDto;

public interface IngredientService {
    IngredientDto getIngredientByIdOfRecipeId(Long recipeId, Long ingredientId);
}
