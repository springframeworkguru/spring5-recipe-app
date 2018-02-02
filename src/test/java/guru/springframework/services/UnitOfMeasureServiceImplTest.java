package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

  private UnitOfMeasureServiceImpl service;

  @Mock
  UnitOfMeasureRepository repository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    service = new UnitOfMeasureServiceImpl(repository, new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Test
  public void shouldGetAllUoms() {
    // given
    UnitOfMeasure unit1 = new UnitOfMeasure();
    unit1.setId(1L);
    unit1.setDescription("Desc 1");

    UnitOfMeasure unit2 = new UnitOfMeasure();
    unit2.setId(2L);
    unit2.setDescription("Desc 2");

    UnitOfMeasure unit3 = new UnitOfMeasure();
    unit3.setId(3L);
    unit3.setDescription("Desc 3");

    Set<UnitOfMeasure> units = new HashSet<>();
    units.add(unit1);
    units.add(unit2);
    units.add(unit3);
    when(repository.findAll()).thenReturn(units);

    // when
    Set<UnitOfMeasureCommand> unitOfMeasureCommands = service.listAllUoms();

    // then
    assertEquals(3, unitOfMeasureCommands.size());
    verify(repository, times(1)).findAll();
  }
}