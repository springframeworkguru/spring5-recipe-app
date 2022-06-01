package guru.springframework.services;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> getUnitOfMeasures() {
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasureSet::add);
        return unitOfMeasureSet;
    }

    @Override
    public UnitOfMeasure findById(Long id) {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findById(id);

        if(!unitOfMeasureOptional.isPresent()){
            throw new RuntimeException("Category not found!");
        }

        return unitOfMeasureOptional.get();
    }
}
