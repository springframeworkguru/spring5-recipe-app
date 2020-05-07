package guru.springframework.repositories;

import guru.springframework.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Implementing JPA SpringData Repositories
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
