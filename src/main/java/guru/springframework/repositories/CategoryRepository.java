package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ccabo 8/17/19
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
