package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by ccabo 8/18/19
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
