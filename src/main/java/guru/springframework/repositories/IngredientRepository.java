package guru.springframework.repositories;

import guru.springframework.domains.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
}
