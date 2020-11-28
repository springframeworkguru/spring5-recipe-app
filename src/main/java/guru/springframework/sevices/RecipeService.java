package guru.springframework.sevices;

import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface RecipeService {
    Set<Recipe> getRecipes();
}
