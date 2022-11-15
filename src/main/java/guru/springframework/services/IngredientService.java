package guru.springframework.services;

import guru.springframework.dtos.IngredientDto;

public interface IngredientService {
    IngredientDto getIngredientByIdOfRecipeId(Long recipeId, Long ingredientId);

    IngredientDto saveOrUpdateIngredient(IngredientDto newIngredientDto);

    void deleteIngredientWithIdOfRecipeWithId(Long recipeId, Long ingredientId);
}
