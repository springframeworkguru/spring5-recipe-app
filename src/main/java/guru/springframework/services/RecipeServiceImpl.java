package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> result = new HashSet<>();
        recipeRepository.findAll().forEach(result::add);
        return result;
    }
}
