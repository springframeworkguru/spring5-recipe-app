package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipes() {
        Set<Recipe> result = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }
}
