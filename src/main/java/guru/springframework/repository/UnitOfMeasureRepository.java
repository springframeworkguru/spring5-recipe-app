package guru.springframework.repository;

import guru.springframework.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

    UnitOfMeasure findByDescription(String description);


}
