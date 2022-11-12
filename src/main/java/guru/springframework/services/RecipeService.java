package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.dtos.RecipeDto;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAll();
    Recipe saveRecipe(Recipe recipe);
    RecipeDto saveRecipeDtoImpl(RecipeDto recipeDto);

    Recipe findById(Long id);
}
