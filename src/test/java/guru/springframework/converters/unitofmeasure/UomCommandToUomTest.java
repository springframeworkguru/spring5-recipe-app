package guru.springframework.converters.unitofmeasure;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domains.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UomCommandToUomTest {
    public static final String DESCRIPTION = "description";
    public static final String STRING_VALUE = "asd";

    UomCommandToUom toUom;

    @Before
    public void setUp() throws Exception {
        toUom = new UomCommandToUom();
    }

    @Test
    public void testNullParam() throws Exception{
        assertNull(toUom.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(toUom.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception{
        //given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(STRING_VALUE);
        uomCommand.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure unitOfMeasure = toUom.convert(uomCommand);

        //then
        assertNotNull(unitOfMeasure);
        assertEquals(STRING_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}