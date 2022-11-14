package guru.springframework.mappers;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.dtos.UnitOfMeasureDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnitOfMeasureMapper {

    UnitOfMeasureDto uomToUomDto(UnitOfMeasure uom);
    UnitOfMeasure uomDtoToUom(UnitOfMeasureDto uomDto);

    Set<UnitOfMeasureDto> uomsToUomDtos(Set<UnitOfMeasure> uom);
    Set<UnitOfMeasure> uomDtosToUoms(Set<UnitOfMeasureDto> uomDto);
}
