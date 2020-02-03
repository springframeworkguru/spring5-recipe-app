package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: hrzayev
 * Date: 03.02.2020
 */

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
