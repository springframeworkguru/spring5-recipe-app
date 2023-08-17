package guru.springframework.repositories;

import guru.springframework.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
    Optional<Recipe> findByDescription(String description);

    }