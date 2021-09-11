package guru.springframework.domains;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        category.setId("asd");

        assertEquals("asd", category.getId());
    }

    @Test
    public void getName() {
    }

    @Test
    public void getRecipes() {
    }
}