package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Recipe findById(Long l);
    Set<Recipe> getRecipes();
}
