package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
  Set<UnitOfMeasureCommand> listAllUnitsOfMeasure();
}
