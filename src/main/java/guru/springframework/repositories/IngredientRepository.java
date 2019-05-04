package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * Created by jt on 6/13/17.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}

