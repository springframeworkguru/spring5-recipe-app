package guru.springframework.domain;

import junit.framework.TestCase;
import org.junit.Before;

public class CategoryTest extends TestCase {
  Category category;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    category = new Category();
  }
  public void testGetId() {
    Long idValue = 4L;
    category.setId(idValue);
    assertEquals(idValue, category.getId());
  }

  public void testGetDescription() {
  }

  public void testGetRecipes() {
  }
}
