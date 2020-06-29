package guru.springframework.services.jpa;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RecipeServiceJpa implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getAll() {
        final Set<Recipe> recipes = new HashSet<>();
        this.recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

}
