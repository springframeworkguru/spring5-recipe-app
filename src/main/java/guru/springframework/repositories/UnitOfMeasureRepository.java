package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.UnitMease;

public interface UnitOfMeasureRepository extends CrudRepository<UnitMease, Long> {

}
