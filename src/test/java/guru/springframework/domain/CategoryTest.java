package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {


    Category category;

    static final Long idValue = 4L;

    @Before
    public void setUp(){
        category = new Category();
        category.setId(idValue);
    }

    @Test
    public void getId() {

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}