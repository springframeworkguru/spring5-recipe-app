package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void Setup(){

    }

    @Test
    public void findByDescription() throws Exception{
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getUom());
    }

    @Test
    public void findByCup() throws Exception{
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Cup");
        assertEquals("Cup", uomOptional.get().getUom());
    }
}