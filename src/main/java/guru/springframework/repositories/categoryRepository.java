package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface categoryRepository extends CrudRepository<Category, Long> {
}
