package guru.springframework.services.unitofmeasure;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domains.UnitOfMeasure;

import java.util.Set;

public interface UomService {
    Set<UnitOfMeasureCommand> findAll();
}
