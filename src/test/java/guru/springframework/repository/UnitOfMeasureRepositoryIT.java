package guru.springframework.repository;

import guru.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

//This is a IT!
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() throws Exception {

        UnitOfMeasure uom = unitOfMeasureRepository.findByDescription("Teaspoon");
        //This one Works
        assertEquals("Teaspoon",uom.getDescription());
        //This one Doesn't Work
        //assertEquals("Teasfdsapoon",uom.getDescription());

    }

    @Test
    public void findByDescriptionCup() throws Exception {

        UnitOfMeasure uom = unitOfMeasureRepository.findByDescription("Cup");
        //This one Works
        assertEquals("Cup",uom.getDescription());
        //This one Doesn't Work
        //assertEquals("Teasfdsapoon",uom.getDescription());

    }

}