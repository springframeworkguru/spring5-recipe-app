package guru.springframework.models;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest extends BaseEntity {

    Category category;

    @Before
    public void setup(){
        category = new Category();
    }

    @Test
    public void testGetId() {

        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}