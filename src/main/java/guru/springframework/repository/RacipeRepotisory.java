package guru.springframework.repository;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RacipeRepotisory extends CrudRepository<Recipe, Long> {


}
