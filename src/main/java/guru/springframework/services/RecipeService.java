package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Author: hrzayev
 * Date: 03.02.2020
 */

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);
}
