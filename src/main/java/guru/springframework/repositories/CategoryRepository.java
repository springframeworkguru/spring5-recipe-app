package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: hrzayev
 * Date: 03.02.2020
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
