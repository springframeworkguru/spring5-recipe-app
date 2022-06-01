package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;

import java.util.Set;


public interface UnitOfMeasureService {
    Set<UnitOfMeasure> getUnitOfMeasures();

    UnitOfMeasure findById(Long id);
}
