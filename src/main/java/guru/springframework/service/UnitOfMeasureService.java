package guru.springframework.service;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAll();
}
