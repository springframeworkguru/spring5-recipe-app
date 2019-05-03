package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 6/13/17.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
