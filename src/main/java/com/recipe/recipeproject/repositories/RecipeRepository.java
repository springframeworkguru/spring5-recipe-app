package com.recipe.recipeproject.repositories;

import com.recipe.recipeproject.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
