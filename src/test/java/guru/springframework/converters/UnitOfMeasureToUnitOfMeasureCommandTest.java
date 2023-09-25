package guru.springframework.converters;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final long ID = 1L;
    public static final String RECIPE_DESCRIPTION = "RECIPE_DESCRIPTION";
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @BeforeEach
    void setUp() {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNull(){
        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(null);
        assertNull(unitOfMeasureCommand);
    }

    @Test
    void testNotNull(){
        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(new UnitOfMeasure());
        assertNotNull(unitOfMeasureCommand);
    }

    @Test
    void testConvert(){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setDescription(RECIPE_DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.
                convert(unitOfMeasure);
        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID, unitOfMeasureCommand.getId());
        assertEquals(RECIPE_DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}