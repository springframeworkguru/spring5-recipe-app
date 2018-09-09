package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

  private CategoryToCategoryCommand categoryToCategoryCommand;
  private static final Long ID = 1L;
  private String TEST_DESC = "Test desc";

  @Before
  public void setUp() throws Exception {
    categoryToCategoryCommand = new CategoryToCategoryCommand();
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    CategoryCommand command = categoryToCategoryCommand.convert(null);

    // then
    assertNull(command);
  }

  @Test
  public void shouldReturnObjectForEmptySource() {
    // when
    CategoryCommand command = categoryToCategoryCommand.convert(new Category());

    // then
    assertNotNull(command);
  }

  @Test
  public void shouldConvertSource() {
    // given
    Category source = new Category();
    source.setId(ID);
    source.setDescription(TEST_DESC);

    // when
    CategoryCommand command = categoryToCategoryCommand.convert(source);

    // then
    assertNotNull(command);
    assertEquals(ID, command.getId());
    assertEquals(TEST_DESC, command.getDescription());
  }
}