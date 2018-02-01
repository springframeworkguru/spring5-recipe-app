package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

  UnitOfMeasureRepository repository;

  public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository) {
    this.repository = repository;
  }

  @Override
  public Set<UnitOfMeasure> listAllUoms() {
    Iterable<UnitOfMeasure> units = repository.findAll();
    Set<UnitOfMeasure> unitOfMeasureList = new HashSet<>();
    units.forEach(unitOfMeasureList::add);
    return unitOfMeasureList;
  }
}
