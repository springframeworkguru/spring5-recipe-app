package guru.springframework.service;

import guru.springframework.model.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipes();
}
