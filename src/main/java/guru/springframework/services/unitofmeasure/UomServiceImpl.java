package guru.springframework.services.unitofmeasure;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.unitofmeasure.UomToUomCommand;
import guru.springframework.domains.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UomServiceImpl implements UomService {
    private final UnitOfMeasureRepository uomRepository;
    private final UomToUomCommand toUomCommand;

    public UomServiceImpl(UnitOfMeasureRepository uomRepository, UomToUomCommand toUomCommand) {
        this.uomRepository = uomRepository;
        this.toUomCommand = toUomCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {
        return StreamSupport.stream(uomRepository.findAll().spliterator(), false)
                .map(toUomCommand::convert)
                .collect(Collectors.toSet());
    }
}
