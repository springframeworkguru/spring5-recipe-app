package guru.springframework.services;

import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
