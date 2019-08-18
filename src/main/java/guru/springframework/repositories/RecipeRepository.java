package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ccabo 8/17/19
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
