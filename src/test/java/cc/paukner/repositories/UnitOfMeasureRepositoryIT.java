package cc.paukner.repositories;

import cc.paukner.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest // embedded db + config JPA
// integration tests have full Spring context, so not just mocks here like in unit tests
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() {
        final String teaspoon = "Teaspoon";
        // no when() needed, as not mocked
        Optional<UnitOfMeasure> teaspoonMeasure = unitOfMeasureRepository.findByDescription(teaspoon);
        assertEquals(teaspoon, teaspoonMeasure.get().getDescription());
    }
}
