package guru.springframework.converters;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final long ID = 1L;
    public static final String RECIPE_DESCRIPTION = "RECIPE_DESCRIPTION";
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNull(){
        UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(null);
        assertNull(unitOfMeasure);
    }

    @Test
    void testNotNull(){
        UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.
                convert(new UnitOfMeasureCommand());
        assertNotNull(unitOfMeasure);
    }

    @Test
    void testConvert(){
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(RECIPE_DESCRIPTION);

        UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);
        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(RECIPE_DESCRIPTION, unitOfMeasure.getDescription());
    }
}