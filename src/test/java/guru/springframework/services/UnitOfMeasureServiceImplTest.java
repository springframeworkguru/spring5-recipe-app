package guru.springframework.services;

import guru.springframework.Converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.models.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {

        // add the RecipeRepository
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void listAllUOM() {

        // given
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        unitOfMeasure1.setDescription("aaa");

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(3L);
        unitOfMeasure2.setDescription("bbb");

        UnitOfMeasure unitOfMeasure3 = new UnitOfMeasure();
        unitOfMeasure3.setId(3L);
        unitOfMeasure3.setDescription("ccc");

        unitOfMeasureSet.add(unitOfMeasure1);
        unitOfMeasureSet.add(unitOfMeasure2);
        unitOfMeasureSet.add(unitOfMeasure3);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureSet);

        // when
        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = unitOfMeasureService.listAllUOM();

        // then
        assertNotNull(unitOfMeasureCommandSet);
        assertEquals(3, unitOfMeasureCommandSet.size());
    }
}