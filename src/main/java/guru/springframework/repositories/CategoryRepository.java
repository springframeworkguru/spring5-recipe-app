package guru.springframework.repositories;

import guru.springframework.domains.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
