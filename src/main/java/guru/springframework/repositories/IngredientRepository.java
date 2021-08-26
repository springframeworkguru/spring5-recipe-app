package guru.springframework.repositories;

import guru.springframework.domains.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
