package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Set;

class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository repository;

    UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<UnitOfMeasure> findAll() {
        Set<UnitOfMeasure> set = new HashSet<>();
        repository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public UnitOfMeasure findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
