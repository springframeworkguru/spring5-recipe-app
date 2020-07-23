package guru.springframework.services;

import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepo;

    public RecipeServiceImpl(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet();
        this.recipeRepo.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
