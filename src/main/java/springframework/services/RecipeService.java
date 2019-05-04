package guru.springframework.services;

import guru.springframework.domain.Recipe;

public interface RecipeService {

    public Iterable<Recipe> fetchRecipeList();
}
