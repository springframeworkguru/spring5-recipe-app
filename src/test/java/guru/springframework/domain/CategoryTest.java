package guru.springframework.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest extends TestCase {
 Category category;
 @Before
 public void setUp(){
     category = new Category();
 }
 @Test
    public void testGetId() {
     Long idValue=1L;
     category.setId(idValue);
     assertEquals(idValue, category.getId());
    }

    public void testGetDescription() {
    }

    public void testGetRecipes() {
    }
}