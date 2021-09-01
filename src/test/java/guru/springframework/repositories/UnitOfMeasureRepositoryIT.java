package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> teaspoon = repository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", teaspoon.get().getDescription());
    }
    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> teaspoon = repository.findByDescription("Cup");
        assertEquals("Cup", teaspoon.get().getDescription());
    }


}