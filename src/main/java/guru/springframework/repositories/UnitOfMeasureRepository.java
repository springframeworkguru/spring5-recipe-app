package guru.springframework.repositories;

import guru.springframework.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Implementing JPA SpringData Repositories
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
