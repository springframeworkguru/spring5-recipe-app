package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

  private final Long ID = 1L;
  private final String TEST_DESC = "Test desc";
  private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

  @Before
  public void setUp() throws Exception {
    unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(null);

    // then
    assertNull(unitOfMeasure);
  }

  @Test
  public void shouldConvertEmptyObject() {
    // when
    UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand());

    // then
    assertNotNull(unitOfMeasure);
  }

  @Test
  public void shouldConvertSource() {
    // given
    UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
    uomCommand.setId(ID);
    uomCommand.setDescription(TEST_DESC);

    // when
    UnitOfMeasure uom = unitOfMeasureCommandToUnitOfMeasure.convert(uomCommand);

    // then
    assertNotNull(uom);
    assertEquals(ID, uom.getId());
    assertEquals(TEST_DESC, uom.getDescription());
  }
}