package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT extends TestCase {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testFindByUom() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getUom());
    }

    @Test
    public void testFindByUomCup() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup", uomOptional.get().getUom());
    }
}