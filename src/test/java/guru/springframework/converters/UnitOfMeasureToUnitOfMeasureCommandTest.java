package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

  private final Long ID = 1L;
  private final String TEST_DESC = "Test desc";
  private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  @Before
  public void setUp() throws Exception {
    unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    UnitOfMeasureCommand command = unitOfMeasureToUnitOfMeasureCommand.convert(null);

    // then
    assertNull(command);
  }

  @Test
  public void shouldConvertEmptyObject() {
    // when
    UnitOfMeasureCommand command = unitOfMeasureToUnitOfMeasureCommand.convert(new UnitOfMeasure());

    // then
    assertNotNull(command);
  }

  @Test
  public void shouldConvertSource() {
    // given
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(ID);
    uom.setDescription(TEST_DESC);

    // when
    UnitOfMeasureCommand uomCommand = unitOfMeasureToUnitOfMeasureCommand.convert(uom);

    // then
    assertNotNull(uomCommand);
    assertEquals(ID, uomCommand.getId());
    assertEquals(TEST_DESC, uomCommand.getDescription());
  }
}