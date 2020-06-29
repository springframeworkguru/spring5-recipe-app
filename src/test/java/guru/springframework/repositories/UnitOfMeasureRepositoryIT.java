package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findUnitOfMeasureByDescriptionReturnsTeaspoonOk() {
        final String teaspoon = "Teaspoon";
        final Optional<UnitOfMeasure> optionalTeaspoon = this.unitOfMeasureRepository.findUnitOfMeasureByDescription(teaspoon);
        assertEquals(teaspoon, optionalTeaspoon.get().getDescription());
    }

    @Test
    public void findUnitOfMeasureByDescriptionReturnsCupOk() {
        final String cup = "Cup";
        final Optional<UnitOfMeasure> optionalCup = this.unitOfMeasureRepository.findUnitOfMeasureByDescription(cup);
        assertEquals(cup, optionalCup.get().getDescription());
    }

}
