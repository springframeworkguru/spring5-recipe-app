package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RecipeServiceImplementation implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Iterable<Recipe> fetchRecipeList() {
        return recipeRepository.findAll();
    }
}
