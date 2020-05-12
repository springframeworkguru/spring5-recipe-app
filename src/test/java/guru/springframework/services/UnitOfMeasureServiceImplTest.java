package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository repository;
    UnitOfMeasureService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(repository);
    }

    @Test
    public void findAll() {
        //given
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        final UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        final UnitOfMeasure unitOfMeasure3 = new UnitOfMeasure();
        unitOfMeasure3.setId(3L);
        Set<UnitOfMeasure> set = Stream.of(unitOfMeasure1, unitOfMeasure2, unitOfMeasure3)
                .collect(Collectors.toSet());
        when(repository.findAll()).thenReturn(set);

        //when
        final Set<UnitOfMeasure> all = service.findAll();

        //then
        assertEquals(set.size(), all.size());
    }

    @Test
    public void findById() {
        //given
        final Long uomId = 111L;
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(uomId);
        when(repository.findById(eq(uomId))).thenReturn(Optional.of(unitOfMeasure));

        //when
        final UnitOfMeasure found = service.findById(uomId);

        //then
        assertEquals(uomId, found.getId());
    }
}
