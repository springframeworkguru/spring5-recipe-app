package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

//@Repository//works without it // b/c CrudRepository  extends Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
/*    Optional<Recipe> findByD(Long aLong);*/
}
