package guru.springframework.repository;

import guru.springframework.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MasureOfRepository extends CrudRepository<UnitOfMeasure,Long> {

    UnitOfMeasure findByUom(String uom);
}
