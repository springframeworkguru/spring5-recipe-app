package guru.springframework.domain;

import junit.framework.TestCase;

public class CategoryTest extends TestCase {

    Category category;

    public void testGetId() {
        Long idValue = 5l;

        category = new Category();
        category.setId(idValue);

        assertEquals(idValue,category.getId());

    }

    public void testGetDescription() {
    }

    public void testGetRecipes() {
    }
}