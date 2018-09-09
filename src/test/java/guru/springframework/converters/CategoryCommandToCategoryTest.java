package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

  private CategoryCommandToCategory categoryCommandToCategory;
  private final Long ID = 1L;
  private final String test_desc = "Test desc";

  @Before
  public void setUp() {
    categoryCommandToCategory = new CategoryCommandToCategory();
  }

  @Test
  public void shouldReturnNullForNullSource() {
    // when
    Category category = categoryCommandToCategory.convert(null);

    // then
    assertNull(category);
  }

  @Test
  public void shouldReturnObjectForEmptySource() {
    // when
    Category category = categoryCommandToCategory.convert(new CategoryCommand());

    // then
    assertNotNull(category);
  }

  @Test
  public void shouldConvertSource() {
    // given
    CategoryCommand source = new CategoryCommand();
    source.setId(ID);
    source.setDescription(test_desc);

    // when
    Category category = categoryCommandToCategory.convert(source);

    // then
    assertNotNull(category);
    assertEquals(ID, category.getId());
    assertEquals(test_desc, category.getDescription());
  }
}