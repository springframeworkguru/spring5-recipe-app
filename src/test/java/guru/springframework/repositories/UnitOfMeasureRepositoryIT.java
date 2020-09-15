package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() {

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Assertions.assertThat(teaspoon)
                .hasValueSatisfying(unitOfMeasure ->
                        Assertions.assertThat(unitOfMeasure.getDescription()).isEqualTo("Teaspoon"));

    }

    @Test
    public void findByDescriptionCup() {

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Cup");
        Assertions.assertThat(teaspoon)
                .hasValueSatisfying(unitOfMeasure ->
                        Assertions.assertThat(unitOfMeasure.getDescription()).isEqualTo("Cup"));

    }
}