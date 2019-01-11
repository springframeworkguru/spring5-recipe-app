package guru.springframework.service;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> getRecipe(){
        log.info("In Service Method of RecipeService Class");
        Recipe recipe = recipeRepository.findByDescription("Spicy Grilled Chicken Tacos Recipe").get();
        return recipeRepository.findAll();
    }
}
