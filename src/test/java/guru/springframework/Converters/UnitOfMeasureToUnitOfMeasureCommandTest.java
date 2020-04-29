package guru.springframework.Converters;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static String DESCRIPTION = "description";
    private static Long ID = 1L;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParam(){
        assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(unitOfMeasureToUnitOfMeasureCommand.convert(new UnitOfMeasure()));
    }


    @Test
    public void convert() {

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(uom);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());

    }
}