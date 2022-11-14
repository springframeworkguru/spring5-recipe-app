package guru.springframework.services;

import guru.springframework.dtos.UnitOfMeasureDto;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureDto> getAllUom();
}
