package guru.springframework.repositories;

import guru.springframework.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

// Load Spring Context
@RunWith(SpringRunner.class)

//DataJpaTest dependencies on
// maven-failsafe-plugin
// and {
//        compile('org.springframework.boot:spring-boot-starter-data-jpa')
//        compile('org.springframework.boot:spring-boot-starter-web')
//        runtime('com.h2database:h2')
//        testCompile('org.springframework.boot:spring-boot-starter-test')
//        testCompile('org.junit.jupiter:junit-jupiter-engine:5.2.0')
//        }
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    // Spring context will start up and we will get an instance of the UnitOfMeasureRepository injected
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {

        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", unitOfMeasureOptional.get().getDescription());
    }
}