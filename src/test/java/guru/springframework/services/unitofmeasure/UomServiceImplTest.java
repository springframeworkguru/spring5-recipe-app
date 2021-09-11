package guru.springframework.services.unitofmeasure;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.unitofmeasure.UomToUomCommand;
import guru.springframework.domains.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UomServiceImplTest {
    @Mock
    UnitOfMeasureRepository uomRepository;

    UomService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UomServiceImpl(uomRepository, new UomToUomCommand());
    }


    @Test
    public void findAll() {
        //given
        Set<UnitOfMeasure> uomList = new HashSet<>();
        UnitOfMeasure uomCom1 = new UnitOfMeasure();
        uomCom1.setId("asd");

        UnitOfMeasure uomCom2= new UnitOfMeasure();
        uomCom2.setId("asdf");

        uomList.add(uomCom1);
        uomList.add(uomCom2);

        when(uomRepository.findAll()).thenReturn((List<UnitOfMeasure>) uomList);
        //when
        Set<UnitOfMeasureCommand> fetchedUomList = service.findAll();

        //then
        assertEquals(2, fetchedUomList.size());
        verify(uomRepository, times(1)).findAll();

    }
}