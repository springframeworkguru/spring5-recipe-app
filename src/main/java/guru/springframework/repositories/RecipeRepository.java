package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: hrzayev
 * Date: 03.02.2020
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
