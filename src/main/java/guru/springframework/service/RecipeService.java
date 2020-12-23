package guru.springframework.service;

import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();
}
