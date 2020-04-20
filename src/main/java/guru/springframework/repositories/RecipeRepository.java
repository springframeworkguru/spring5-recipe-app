package guru.springframework.repositories;

import guru.springframework.models.Recipe;
import org.springframework.data.repository.CrudRepository;

// Implementing JPA SpringData Repositories
public interface RecipeRepository extends CrudRepository<Recipe, Long> {


}
