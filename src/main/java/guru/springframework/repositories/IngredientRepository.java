package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findByIdAndRecipeId(Long id, Long recipeId);

    void deleteByIdAndRecipeId(Long id, Long recipeId);
}
