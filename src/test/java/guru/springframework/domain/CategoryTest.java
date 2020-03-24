package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUP(){
        category = new Category();
    }
    @Test
    public void getId() {
        category.setId(4L);
        assertEquals(new Long(category.getId()), new Long(4l));
    }

    @Test
    public void setId() {
    }

    @Test
    public void getRecipes() {
    }
}
