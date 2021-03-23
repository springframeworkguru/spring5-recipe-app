package guru.springframework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
