package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> findAll();

    UnitOfMeasure findById(Long id);
}
