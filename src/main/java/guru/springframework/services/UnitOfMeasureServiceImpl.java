package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.controllers.UnitOfMeasureService;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.uomRepository = uomRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {
        List<UnitOfMeasureCommand> uomList = new ArrayList<>();
        UnitOfMeasureCommand empty = new UnitOfMeasureCommand();
        empty.setDescription("");
        uomList.add(empty);

        uomRepository.findAll().iterator().forEachRemaining(uom -> uomList.add(converter.convert(uom)));

        uomList.sort(Comparator.comparing(UnitOfMeasureCommand::getDescription));
        return new HashSet<>(uomList);
    }
}
