package guru.springframework.services.jpa;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.dtos.UnitOfMeasureDto;
import guru.springframework.mappers.UnitOfMeasureMapper;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.UnitOfMeasureService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceJpa implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureMapper unitOfMeasureMapper;

    public UnitOfMeasureServiceJpa(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureMapper unitOfMeasureMapper) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureMapper = unitOfMeasureMapper;
    }

    @Override
    @Transactional
    public Set<UnitOfMeasureDto> getAllUom() {
        Set<UnitOfMeasure> uoms = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(uoms::add);
        return unitOfMeasureMapper.uomsToUomDtos(uoms);
    }
}
