package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipies(){
        HashSet<Recipe> recipies = new HashSet<>();
        recipeRepository.findAll().forEach(recipe-> recipies.add(recipe));
        return recipies;
    }
}
